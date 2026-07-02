package pe.edu.upeu.msaccesorios.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import pe.edu.upeu.msaccesorios.dto.AccesorioRequest;
import pe.edu.upeu.msaccesorios.dto.AccesorioResponse;
import pe.edu.upeu.msaccesorios.entity.AccesorioEntity;
import pe.edu.upeu.msaccesorios.entity.EstadoAccesorioEntity;
import pe.edu.upeu.msaccesorios.entity.MarcaAccesorioEntity;
import pe.edu.upeu.msaccesorios.errors.AccesorioNotFoundException;
import pe.edu.upeu.msaccesorios.mappers.AccesorioMapper;
import pe.edu.upeu.msaccesorios.repository.AccesorioRepository;
import pe.edu.upeu.msaccesorios.service.cloud.CloudinaryService;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AccesorioService implements IAccesorioService {

    private final AccesorioRepository repository;
    private final AccesorioMapper mapper;
    private final CloudinaryService cloudinaryService;
    private final IMarcaAccesorioService marcaService;
    private final IEstadoAccesorioService estadoService;

    @Autowired
    public AccesorioService(AccesorioRepository repository,
                            AccesorioMapper mapper,
                            CloudinaryService cloudinaryService,
                            IMarcaAccesorioService marcaService,
                            IEstadoAccesorioService estadoService) {
        this.repository = repository;
        this.mapper = mapper;
        this.cloudinaryService = cloudinaryService;
        this.marcaService = marcaService;
        this.estadoService = estadoService;
    }

    private String subirImagenAAlmacenamientoExterno(MultipartFile archivo) throws Exception {
        if (archivo != null && !archivo.isEmpty()) {
            return cloudinaryService.subirImagen(archivo);
        }
        return null;
    }

    // ==========================================
    // MÉTODO MAGICO: LLENA LOS NOMBRES FALTANTES
    // ==========================================
    private AccesorioResponse enriquecerConNombres(AccesorioResponse response) {
        if (response.getMarcaId() != null) {
            MarcaAccesorioEntity marca = marcaService.buscarPorId(response.getMarcaId());
            response.setMarcaNombre(marca.getNombre());
        }
        if (response.getEstadoId() != null) {
            EstadoAccesorioEntity estado = estadoService.buscarPorId(response.getEstadoId());
            response.setEstadoNombre(estado.getNombre());
        }
        return response;
    }

    @Override
    public AccesorioResponse crear(AccesorioRequest request, MultipartFile imagen) throws Exception {
        if (repository.existsByNombreIgnoreCase(request.getNombre())) {
            throw new IllegalArgumentException("Ya existe un accesorio con el nombre: " + request.getNombre());
        }

        MarcaAccesorioEntity marca = marcaService.buscarPorId(request.getMarcaId());
        EstadoAccesorioEntity estado = estadoService.buscarPorId(request.getEstadoId());

        String urlImagen = subirImagenAAlmacenamientoExterno(imagen);
        request.setImagenUrl(urlImagen);

        AccesorioEntity entity = mapper.toEntity(request);
        entity.setMarcaId(marca.getId());
        entity.setEstadoId(estado.getId());

        AccesorioEntity guardado = repository.save(entity);

        // Mapear y luego llenar los nombres
        return enriquecerConNombres(mapper.toResponse(guardado));
    }

    @Override
    public List<AccesorioResponse> listar() {
        return repository.findAll().stream()
                .map(mapper::toResponse)
                .map(this::enriquecerConNombres) // Aquí llenamos los nombres al listar
                .collect(Collectors.toList());
    }

    @Override
    public AccesorioResponse buscarPorId(Long id) {
        AccesorioEntity entity = repository.findById(id)
                .orElseThrow(() -> new AccesorioNotFoundException(id));
        return enriquecerConNombres(mapper.toResponse(entity)); // Llenamos nombres aquí también
    }

    @Override
    public List<AccesorioResponse> buscarPorNombre(String nombre) {
        return repository.findByNombreContainingIgnoreCase(nombre).stream()
                .map(mapper::toResponse)
                .map(this::enriquecerConNombres)
                .collect(Collectors.toList());
    }

    @Override
    public AccesorioResponse actualizar(Long id, AccesorioRequest request, MultipartFile imagen) throws Exception {
        AccesorioEntity entity = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("No se encontró el accesorio con ID: " + id));

        MarcaAccesorioEntity marca = marcaService.buscarPorId(request.getMarcaId());
        EstadoAccesorioEntity estado = estadoService.buscarPorId(request.getEstadoId());

        if (imagen != null && !imagen.isEmpty()) {
            request.setImagenUrl(subirImagenAAlmacenamientoExterno(imagen));
        } else {
            request.setImagenUrl(entity.getImagenUrl());
        }

        mapper.updateEntity(entity, request);
        entity.setMarcaId(marca.getId());
        entity.setEstadoId(estado.getId());

        AccesorioEntity guardado = repository.save(entity);
        return enriquecerConNombres(mapper.toResponse(guardado));
    }

    @Override
    public void eliminar(Long id) {
        AccesorioEntity entity = repository.findById(id)
                .orElseThrow(() -> new AccesorioNotFoundException(id));
        repository.delete(entity);
    }
}