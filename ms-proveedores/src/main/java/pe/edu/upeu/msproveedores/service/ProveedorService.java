package pe.edu.upeu.msproveedores.service;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import pe.edu.upeu.msproveedores.clients.CategoriaClient;
import pe.edu.upeu.msproveedores.dto.CategoriaResponse;
import pe.edu.upeu.msproveedores.dto.ProveedorRequest;
import pe.edu.upeu.msproveedores.dto.ProveedorResponse;
import pe.edu.upeu.msproveedores.entity.ProveedorEntity;
import pe.edu.upeu.msproveedores.errors.ProveedorNotFoundException;
import pe.edu.upeu.msproveedores.manager.IProveedorManager;
import pe.edu.upeu.msproveedores.mapper.ProveedorMapper;
import pe.edu.upeu.msproveedores.repository.ProveedorRepository;
import pe.edu.upeu.msproveedores.service.cloud.CloudinaryService;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProveedorService {

    private final ProveedorRepository repository;
    private final ProveedorMapper mapper;
    private final IProveedorManager manager;
    private final CategoriaClient categoriaClient;
    private final CloudinaryService cloudinaryService;

    @Autowired
    public ProveedorService(ProveedorRepository repository,
                            ProveedorMapper mapper,
                            IProveedorManager manager,
                            CategoriaClient categoriaClient,
                            CloudinaryService cloudinaryService) {
        this.repository = repository;
        this.mapper = mapper;
        this.manager = manager;
        this.categoriaClient = categoriaClient;
        this.cloudinaryService = cloudinaryService;
    }

    // Método para subir imagen a Cloudinary (Servicio Externo)
    private String subirImagenAAlmacenamientoExterno(MultipartFile archivo) throws Exception {
        if (archivo != null && !archivo.isEmpty()) {
            return cloudinaryService.subirImagen(archivo);
        }
        return null;
    }

    public List<ProveedorResponse> listar() {
        return repository.findAll().stream().map(entity -> {
            ProveedorResponse res = mapper.toResponse(entity);
            try {
                CategoriaResponse cat = categoriaClient.buscarPorId(entity.getCategoriaId());
                res.setCategoriaNombre(cat.getNombre());
            } catch (Exception e) {
                res.setCategoriaNombre("Nombre no disponible");
            }
            return res;
        }).collect(Collectors.toList());
    }

    public ProveedorResponse buscarPorId(Long id) {
        ProveedorEntity entity = repository.findById(id)
                .orElseThrow(() -> new ProveedorNotFoundException(id));

        ProveedorResponse res = mapper.toResponse(entity);
        try {
            CategoriaResponse cat = categoriaClient.buscarPorId(entity.getCategoriaId());
            res.setCategoriaNombre(cat.getNombre());
        } catch (Exception e) {
            res.setCategoriaNombre("Nombre no disponible");
        }
        return res;
    }

    @CircuitBreaker(name = "proveedoresCB", fallbackMethod = "fallbackMethod")
    public ProveedorResponse crear(ProveedorRequest request, MultipartFile imagen) throws Exception {
        repository.findByNombresAndApellidos(request.getNombres(), request.getApellidos()).ifPresent(p -> {
            throw new IllegalArgumentException("Ya existe un proveedor con ese nombre: " + request.getNombres());
        });

        manager.validarCategoriaExterno(request.getCategoriaId());

        // Subida a Cloudinary
        String urlImagen = subirImagenAAlmacenamientoExterno(imagen);
        request.setImagenUrl(urlImagen);

        ProveedorEntity entity = mapper.toEntity(request);
        ProveedorResponse response = mapper.toResponse(repository.save(entity));

        CategoriaResponse cat = categoriaClient.buscarPorId(request.getCategoriaId());
        response.setCategoriaNombre(cat.getNombre());

        return response;
    }

    public ProveedorResponse fallbackMethod(ProveedorRequest request, MultipartFile imagen, Exception e) {
        ProveedorResponse response = new ProveedorResponse();
        response.setId(0L);
        response.setNombres("FALLBACK: " + e.getMessage());
        return response;
    }

    public ProveedorResponse actualizar(Long id, ProveedorRequest request, MultipartFile imagen) throws Exception {
        ProveedorEntity entity = repository.findById(id)
                .orElseThrow(() -> new ProveedorNotFoundException(id));

        // Actualización de URL en Cloudinary si se envía una nueva imagen
        if (imagen != null && !imagen.isEmpty()) {
            request.setImagenUrl(subirImagenAAlmacenamientoExterno(imagen));
        } else {
            request.setImagenUrl(entity.getImagenUrl());
        }

        mapper.updateEntity(entity, request);
        ProveedorResponse res = mapper.toResponse(repository.save(entity));

        try {
            CategoriaResponse cat = categoriaClient.buscarPorId(request.getCategoriaId());
            res.setCategoriaNombre(cat.getNombre());
        } catch (Exception e) {
            res.setCategoriaNombre("Nombre no disponible");
        }
        return res;
    }

    public void eliminar(Long id) {
        ProveedorEntity entity = repository.findById(id)
                .orElseThrow(() -> new ProveedorNotFoundException(id));
        repository.delete(entity);
    }

    public List<ProveedorResponse> buscarPorNombre(String nombres) {
        return repository.findByNombresContainingIgnoreCase(nombres)
                .stream()
                .map(entity -> {
                    ProveedorResponse res = mapper.toResponse(entity);
                    try {
                        CategoriaResponse cat = categoriaClient.buscarPorId(entity.getCategoriaId());
                        res.setCategoriaNombre(cat.getNombre());
                    } catch (Exception e) {
                        res.setCategoriaNombre("No disponible");
                    }
                    return res;
                })
                .collect(Collectors.toList());
    }
}