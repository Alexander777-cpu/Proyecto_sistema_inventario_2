package pe.edu.upeu.msherramientas.service;

import org.springframework.stereotype.Service;
import pe.edu.upeu.msherramientas.client.ServicioExternoClient;
import pe.edu.upeu.msherramientas.dto.HerramientaRequest;
import pe.edu.upeu.msherramientas.dto.HerramientaResponse;
import pe.edu.upeu.msherramientas.entity.HerramientaEntity;
import pe.edu.upeu.msherramientas.errors.HerramientaNotFoundException;
import pe.edu.upeu.msherramientas.mapper.HerramientaMapper;
import pe.edu.upeu.msherramientas.repository.HerramientaRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class HerramientaService {

    private final HerramientaRepository herramientaRepository;
    private final HerramientaMapper herramientaMapper;
    private final ServicioExternoClient servicioExternoClient;

    public HerramientaService(HerramientaRepository herramientaRepository,
                              HerramientaMapper herramientaMapper,
                              ServicioExternoClient servicioExternoClient) {
        this.herramientaRepository = herramientaRepository;
        this.herramientaMapper = herramientaMapper;
        this.servicioExternoClient = servicioExternoClient;
    }

    public String obtenerInformacionAdicional(Long id) {
        return servicioExternoClient.obtenerDatosDeOtroMicroservicio(id);
    }

    public List<HerramientaResponse> listar(){
        return herramientaRepository.findAll()
                .stream()
                .map(herramientaMapper::toResponse) // Usamos el Mapper aquí
                .collect(Collectors.toList());
    }

    public HerramientaResponse buscarPorId(Long id) {
        HerramientaEntity entity = herramientaRepository.findById(id)
                .orElseThrow(() -> new HerramientaNotFoundException(id));
        return herramientaMapper.toResponse(entity);
    }

    public HerramientaResponse crear(HerramientaRequest request){
        if (request == null) {
            throw new IllegalArgumentException("Los datos de la herramienta no pueden ser nulos");
        }

        if (herramientaRepository.existsByNombreIgnoreCase(request.getNombre())) {
            throw new IllegalArgumentException("No se puede crear. Ya existe una herramienta con el nombre: " + request.getNombre());
        }

        HerramientaEntity entity = herramientaMapper.toEntity(request);
        return herramientaMapper.toResponse(herramientaRepository.save(entity));
    }

    public List<HerramientaResponse> buscarPorNombre(String nombre){
        if (nombre == null || nombre.trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre de búsqueda no puede estar vacío");
        }
        return herramientaRepository.findByNombreContainingIgnoreCase(nombre)
                .stream()
                .map(herramientaMapper::toResponse) // Usamos el Mapper aquí
                .collect(Collectors.toList());
    }

    public HerramientaResponse actualizar(Long id, HerramientaRequest request){
        if (request == null) {
            throw new IllegalArgumentException("Los datos a actualizar no pueden ser nulos");
        }

        HerramientaEntity entity = herramientaRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("No se puede actualizar. No se encontró la herramienta con ID: " + id));


        if (!entity.getNombre().equalsIgnoreCase(request.getNombre()) &&
                herramientaRepository.existsByNombreIgnoreCase(request.getNombre())) {
            throw new IllegalArgumentException("No se puede actualizar. Ya existe OTRA herramienta con el nombre: " + request.getNombre());
        }

        // Delegamos la actualización de la entidad al Mapper
        herramientaMapper.updateEntity(entity, request);

        return herramientaMapper.toResponse(herramientaRepository.save(entity));
    }

    public void eliminar(Long id){
        if (!herramientaRepository.existsById(id)) {
            throw new IllegalArgumentException("No se puede eliminar. No existe la herramienta con ID: " + id);
        }
        herramientaRepository.deleteById(id);
    }
}