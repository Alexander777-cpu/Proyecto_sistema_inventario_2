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

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.stream.Collectors;

@Service

public class MelamineService {
    private final MelamineRepository repository;
    private final MelamineMapper mapper;
    private final IMelamineManager manager;
    private final EstadoClient estadoClient;

    @Autowired
    public MelamineService(MelamineRepository repository, MelamineMapper mapper,
                           IMelamineManager manager, EstadoClient estadoClient) {
        this.repository = repository;
        this.mapper = mapper;
        this.manager = manager;
        this.estadoClient = estadoClient;
    }

    private String guardarImagen(MultipartFile archivo) {
        if (archivo != null && !archivo.isEmpty()) {
            try {
                Path directorio = Paths.get("uploads");
                if (!Files.exists(directorio)) {
                    Files.createDirectories(directorio);
                }
                String nombreArchivo = System.currentTimeMillis() + "_" + archivo.getOriginalFilename();
                Path rutaFisica = directorio.resolve(nombreArchivo).toAbsolutePath();
                Files.copy(archivo.getInputStream(), rutaFisica, StandardCopyOption.REPLACE_EXISTING);
                return "/uploads/" + nombreArchivo;
            } catch (IOException e) {
                throw new RuntimeException("Error al guardar la imagen", e);
            }
        }
        return null;
    }

    // Sin Circuit Breaker: Si falla, el error saldrá directo en Postman
    public MelamineResponse crear(MelamineRequest request, MultipartFile imagen) throws Exception {
        // 1. Validar estado (Si esto falla, el error llega directo a Postman)
        manager.validarEstadoExterno(request.getEstadoId());

        if (repository.existsByNombreIgnoreCase(request.getNombre())) {
            throw new IllegalArgumentException("Ya existe una melamina con el nombre: " + request.getNombre());
        }

        String urlImagen = guardarImagen(imagen);
        request.setImagenUrl(urlImagen);

        MelamineEntity entity = mapper.toEntity(request);
        MelamineResponse response = mapper.toResponse(repository.save(entity));

        // 2. Enriquecer datos
        EstadoResponse estado = estadoClient.buscarPorId(request.getEstadoId());
        response.setEstadoNombre(estado.getEstadoNombre());

        return response;
    }

    public List<MelamineResponse> listar() {
        return repository.findAll().stream().map(entity -> {
            MelamineResponse res = mapper.toResponse(entity);
            EstadoResponse estado = estadoClient.buscarPorId(entity.getEstadoId());
            res.setEstadoNombre(estado.getEstadoNombre());
            return res;
        }).collect(Collectors.toList());
    }

    public MelamineResponse buscarPorId(Long id) {
        MelamineEntity entity = repository.findById(id)
                .orElseThrow(() -> new MelamineNotFoundException(id));
        MelamineResponse res = mapper.toResponse(entity);
        EstadoResponse estado = estadoClient.buscarPorId(entity.getEstadoId());
        res.setEstadoNombre(estado.getEstadoNombre());
        return res;
    }

    public List<MelamineResponse> buscarPorNombre(String nombre){
        return repository.findByNombreContainingIgnoreCase(nombre).stream().map(entity -> {
            MelamineResponse res = mapper.toResponse(entity);
            EstadoResponse estado = estadoClient.buscarPorId(entity.getEstadoId());
            res.setEstadoNombre(estado.getEstadoNombre());
            return res;
        }).collect(Collectors.toList());
    }

    public MelamineResponse actualizar(Long id, MelamineRequest request, MultipartFile imagen) throws Exception {
        MelamineEntity entity = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("No se encontró la melamina"));

        manager.validarEstadoExterno(request.getEstadoId());

        if (imagen != null && !imagen.isEmpty()) {
            request.setImagenUrl(guardarImagen(imagen));
        } else {
            request.setImagenUrl(entity.getImagenUrl());
        }

        mapper.updateEntity(entity, request);
        MelamineResponse res = mapper.toResponse(repository.save(entity));
        EstadoResponse estado = estadoClient.buscarPorId(entity.getEstadoId());
        res.setEstadoNombre(estado.getEstadoNombre());
        return res;
    }

    public void eliminar(Long id){
        repository.deleteById(id);
    }

}
