package pe.edu.upeu.msherramientas.service;


import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import pe.edu.upeu.msherramientas.client.EstadoClient;
import pe.edu.upeu.msherramientas.dto.EstadoResponse;
import pe.edu.upeu.msherramientas.dto.HerramientaRequest;
import pe.edu.upeu.msherramientas.dto.HerramientaResponse;
import pe.edu.upeu.msherramientas.entity.HerramientaEntity;
import pe.edu.upeu.msherramientas.errors.HerramientaNotFoundException;
import pe.edu.upeu.msherramientas.manager.IHerramientaManager;
import pe.edu.upeu.msherramientas.mapper.HerramientaMapper;
import pe.edu.upeu.msherramientas.repository.HerramientaRepository;
import pe.edu.upeu.msherramientas.service.cloud.CloudinaryService;


import java.util.List;
import java.util.stream.Collectors;

@Service
public class HerramientaService {

    private final HerramientaRepository repository;
    private final HerramientaMapper mapper;
    private final IHerramientaManager manager;
    private final EstadoClient estadoClient;
    private final CloudinaryService cloudinaryService;

    @Autowired
    public HerramientaService(HerramientaRepository repository,
                              HerramientaMapper mapper,
                              IHerramientaManager manager,
                              EstadoClient estadoClient,
                              CloudinaryService cloudinaryService) {
        this.repository = repository;
        this.mapper = mapper;
        this.manager = manager;
        this.estadoClient = estadoClient;
        this.cloudinaryService = cloudinaryService;
    }

    // Método adaptado para subir la imagen a Cloudinary
    private String subirImagenAAlmacenamientoExterno(MultipartFile archivo) throws Exception {
        if (archivo != null && !archivo.isEmpty()) {
            return cloudinaryService.subirImagen(archivo);
        }
        return null;
    }

    // Implementación de Circuit Breaker para la creación
    @CircuitBreaker(name = "herramientasCB", fallbackMethod = "fallbackMethod")
    public HerramientaResponse crear(HerramientaRequest request, MultipartFile imagen) throws Exception {
        manager.validarEstadoExterno(request.getEstadoId());

        if (repository.existsByNombreIgnoreCase(request.getNombre())) {
            throw new IllegalArgumentException("Ya existe una herramienta con el nombre: " + request.getNombre());
        }

        // Subida a Cloudinary
        String urlImagen = subirImagenAAlmacenamientoExterno(imagen);
        request.setImagenUrl(urlImagen);

        HerramientaEntity entity = mapper.toEntity(request);
        HerramientaResponse response = mapper.toResponse(repository.save(entity));

        EstadoResponse estado = estadoClient.buscarPorId(request.getEstadoId());
        response.setEstadoNombre(estado.getEstadoNombre());

        return response;
    }

    // Método de contingencia (Fallback)
    public HerramientaResponse fallbackMethod(HerramientaRequest request, MultipartFile imagen, Exception e) {
        HerramientaResponse response = new HerramientaResponse();
        response.setId(0L);
        response.setNombre("FALLBACK: " + e.getMessage());
        return response;
    }

    public List<HerramientaResponse> listar() {
        return repository.findAll().stream().map(entity -> {
            HerramientaResponse res = mapper.toResponse(entity);
            try {
                EstadoResponse estado = estadoClient.buscarPorId(entity.getEstadoId());
                res.setEstadoNombre(estado.getEstadoNombre());
            } catch (Exception e) {
                res.setEstadoNombre("No disponible");
            }
            return res;
        }).collect(Collectors.toList());
    }

    public HerramientaResponse buscarPorId(Long id) {
        HerramientaEntity entity = repository.findById(id)
                .orElseThrow(() -> new HerramientaNotFoundException(id));
        HerramientaResponse res = mapper.toResponse(entity);
        try {
            EstadoResponse estado = estadoClient.buscarPorId(entity.getEstadoId());
            res.setEstadoNombre(estado.getEstadoNombre());
        } catch (Exception e) {
            res.setEstadoNombre("No disponible");
        }
        return res;
    }

    public List<HerramientaResponse> buscarPorNombre(String nombre){
        return repository.findByNombreContainingIgnoreCase(nombre).stream().map(entity -> {
            HerramientaResponse res = mapper.toResponse(entity);
            try {
                EstadoResponse estado = estadoClient.buscarPorId(entity.getEstadoId());
                res.setEstadoNombre(estado.getEstadoNombre());
            } catch (Exception e) {
                res.setEstadoNombre("No disponible");
            }
            return res;
        }).collect(Collectors.toList());
    }

    public HerramientaResponse actualizar(Long id, HerramientaRequest request, MultipartFile imagen) throws Exception {
        HerramientaEntity entity = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("No se encontró la herramienta"));

        manager.validarEstadoExterno(request.getEstadoId());

        // Actualización de URL en Cloudinary si se envía una nueva imagen
        if (imagen != null && !imagen.isEmpty()) {
            request.setImagenUrl(subirImagenAAlmacenamientoExterno(imagen));
        } else {
            request.setImagenUrl(entity.getImagenUrl());
        }

        mapper.updateEntity(entity, request);
        HerramientaResponse res = mapper.toResponse(repository.save(entity));

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