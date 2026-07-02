package pe.edu.upeu.msproveedores.service;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import pe.edu.upeu.msproveedores.dto.ProveedorRequest;
import pe.edu.upeu.msproveedores.dto.ProveedorResponse;
import pe.edu.upeu.msproveedores.entity.CategoriaProveedorEntity;
import pe.edu.upeu.msproveedores.entity.ProveedorEntity;

import pe.edu.upeu.msproveedores.mapper.ProveedorMapper;
import pe.edu.upeu.msproveedores.repository.ProveedorRepository;
import pe.edu.upeu.msproveedores.service.cloud.CloudinaryService;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProveedorService implements IProveedorService {

    private final ProveedorRepository repository;
    private final ProveedorMapper mapper;
    private final ICategoriaProveedorService categoriaService; // Cambio principal
    private final CloudinaryService cloudinaryService;

    @Autowired
    public ProveedorService(ProveedorRepository repository,
                            ProveedorMapper mapper,
                            ICategoriaProveedorService categoriaService,
                            CloudinaryService cloudinaryService) {
        this.repository = repository;
        this.mapper = mapper;
        this.categoriaService = categoriaService;
        this.cloudinaryService = cloudinaryService;
    }

    // Método ayudante para inyectar el nombre de la categoría
    private ProveedorResponse enriquecerConNombre(ProveedorResponse res) {
        try {
            CategoriaProveedorEntity cat = categoriaService.buscarPorId(res.getCategoriaId());
            res.setCategoriaNombre(cat.getNombre());
        } catch (Exception e) {
            res.setCategoriaNombre("Categoría no disponible");
        }
        return res;
    }

    private String subirImagen(MultipartFile archivo) throws Exception {
        return (archivo != null && !archivo.isEmpty()) ? cloudinaryService.subirImagen(archivo) : null;
    }

    @Override
    public List<ProveedorResponse> listar() {
        return repository.findAll().stream()
                .map(mapper::toResponse)
                .map(this::enriquecerConNombre)
                .collect(Collectors.toList());
    }

    @Override
    public ProveedorResponse buscarPorId(Long id) {
        ProveedorEntity entity = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Proveedor no encontrado con ID: " + id));
        return enriquecerConNombre(mapper.toResponse(entity));
    }

    @Override
    public List<ProveedorResponse> buscarPorNombre(String nombres) {
        return repository.findByNombresContainingIgnoreCase(nombres)
                .stream()
                .map(mapper::toResponse)
                .map(this::enriquecerConNombre) // Usamos tu método ayudante para llenar el nombre de categoría
                .collect(Collectors.toList());
    }

    @Override
    @CircuitBreaker(name = "proveedoresCB", fallbackMethod = "fallbackMethod")
    public ProveedorResponse crear(ProveedorRequest request, MultipartFile imagen) throws Exception {
        if (repository.findByNombresAndApellidos(request.getNombres(), request.getApellidos()).isPresent()) {
            throw new IllegalArgumentException("Ya existe un proveedor con ese nombre: " + request.getNombres());
        }

        request.setImagenUrl(subirImagen(imagen));
        ProveedorEntity entity = mapper.toEntity(request);

        return enriquecerConNombre(mapper.toResponse(repository.save(entity)));
    }

    public ProveedorResponse fallbackMethod(ProveedorRequest request, MultipartFile imagen, Exception e) {
        ProveedorResponse response = new ProveedorResponse();
        response.setNombres("FALLBACK: " + e.getMessage());
        return response;
    }

    @Override
    public ProveedorResponse actualizar(Long id, ProveedorRequest request, MultipartFile imagen) throws Exception {
        ProveedorEntity entity = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Proveedor no encontrado"));

        if (imagen != null && !imagen.isEmpty()) {
            request.setImagenUrl(subirImagen(imagen));
        } else {
            request.setImagenUrl(entity.getImagenUrl());
        }

        mapper.updateEntity(entity, request);
        return enriquecerConNombre(mapper.toResponse(repository.save(entity)));
    }

    @Override
    public void eliminar(Long id) {
        repository.delete(buscarPorId(id) != null ? repository.findById(id).get() : null);
    }
}