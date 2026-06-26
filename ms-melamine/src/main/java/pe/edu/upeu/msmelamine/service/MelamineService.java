package pe.edu.upeu.msmelamine.service;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import pe.edu.upeu.msmelamine.client.EstadoClient;
import pe.edu.upeu.msmelamine.dto.EstadoResponse;
import pe.edu.upeu.msmelamine.dto.MelamineRequest;
import pe.edu.upeu.msmelamine.dto.MelamineResponse;
import pe.edu.upeu.msmelamine.entity.MelamineEntity;
import pe.edu.upeu.msmelamine.errors.MelamineNotFoundException;
import pe.edu.upeu.msmelamine.manager.IMelamineManager;
import pe.edu.upeu.msmelamine.mapper.MelamineMapper;
import pe.edu.upeu.msmelamine.repository.MelamineRepository;
import pe.edu.upeu.msmelamine.service.cloud.CloudinaryService;


import java.util.List;
import java.util.stream.Collectors;

@Service
public class MelamineService {

    private final MelamineRepository repository;
    private final MelamineMapper mapper;
    private final IMelamineManager manager;
    private final EstadoClient estadoClient;
    private final CloudinaryService cloudinaryService;

    @Autowired
    public MelamineService(MelamineRepository repository,
                           MelamineMapper mapper,
                           IMelamineManager manager,
                           EstadoClient estadoClient,
                           CloudinaryService cloudinaryService) {
        this.repository = repository;
        this.mapper = mapper;
        this.manager = manager;
        this.estadoClient = estadoClient;
        this.cloudinaryService = cloudinaryService;
    }

    private String subirImagenAAlmacenamientoExterno(MultipartFile archivo) throws Exception {
        if (archivo != null && !archivo.isEmpty()) {
            return cloudinaryService.subirImagen(archivo);
        }
        return null;
    }

    // Implementación de Circuit Breaker para la creación
    @CircuitBreaker(name = "melamineCB", fallbackMethod = "fallbackMethod")
    public MelamineResponse crear(MelamineRequest request, MultipartFile imagen) throws Exception {
        manager.validarEstadoExterno(request.getEstadoId());

        if (repository.existsByNombreIgnoreCase(request.getNombre())) {
            throw new IllegalArgumentException("Ya existe una melamina con el nombre: " + request.getNombre());
        }

        String urlImagen = subirImagenAAlmacenamientoExterno(imagen);
        request.setImagenUrl(urlImagen);

        MelamineEntity entity = mapper.toEntity(request);
        MelamineResponse response = mapper.toResponse(repository.save(entity));

        EstadoResponse estado = estadoClient.buscarPorId(request.getEstadoId());
        response.setEstadoNombre(estado.getEstadoNombre());

        return response;
    }

    // Método de contingencia (Fallback) si falla crear o la comunicación externa
    public MelamineResponse fallbackMethod(MelamineRequest request, MultipartFile imagen, Exception e) {
        MelamineResponse response = new MelamineResponse();
        response.setId(0L);
        response.setNombre("FALLBACK: " + e.getMessage());
        return response;
    }

    public List<MelamineResponse> listar() {
        return repository.findAll().stream().map(entity -> {
            MelamineResponse res = mapper.toResponse(entity);
            try {
                EstadoResponse estado = estadoClient.buscarPorId(entity.getEstadoId());
                res.setEstadoNombre(estado.getEstadoNombre());
            } catch (Exception e) {
                res.setEstadoNombre("No disponible"); // Manejo de fallo estandarizado
            }
            return res;
        }).collect(Collectors.toList());
    }

    public MelamineResponse buscarPorId(Long id) {
        MelamineEntity entity = repository.findById(id)
                .orElseThrow(() -> new MelamineNotFoundException(id));
        MelamineResponse res = mapper.toResponse(entity);
        try {
            EstadoResponse estado = estadoClient.buscarPorId(entity.getEstadoId());
            res.setEstadoNombre(estado.getEstadoNombre());
        } catch (Exception e) {
            res.setEstadoNombre("No disponible");
        }
        return res;
    }

    public List<MelamineResponse> buscarPorNombre(String nombre){
        return repository.findByNombreContainingIgnoreCase(nombre).stream().map(entity -> {
            MelamineResponse res = mapper.toResponse(entity);
            try {
                EstadoResponse estado = estadoClient.buscarPorId(entity.getEstadoId());
                res.setEstadoNombre(estado.getEstadoNombre());
            } catch (Exception e) {
                res.setEstadoNombre("No disponible");
            }
            return res;
        }).collect(Collectors.toList());
    }

    public MelamineResponse actualizar(Long id, MelamineRequest request, MultipartFile imagen) throws Exception {
        MelamineEntity entity = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("No se encontró la melamina"));

        manager.validarEstadoExterno(request.getEstadoId());

        if (imagen != null && !imagen.isEmpty()) {
            request.setImagenUrl(subirImagenAAlmacenamientoExterno(imagen));
        } else {
            request.setImagenUrl(entity.getImagenUrl());
        }

        mapper.updateEntity(entity, request);
        MelamineResponse res = mapper.toResponse(repository.save(entity));

        try {
            EstadoResponse estado = estadoClient.buscarPorId(entity.getEstadoId());
            res.setEstadoNombre(estado.getEstadoNombre());
        } catch (Exception e) {
            res.setEstadoNombre("No disponible");
        }
        return res;
    }

    public void eliminar(Long id){
        repository.deleteById(id);
    }
}