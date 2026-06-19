package pe.edu.upeu.msproveedores.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import pe.edu.upeu.msproveedores.clients.CategoriaClient;
import pe.edu.upeu.msproveedores.dto.CategoriaResponse;
import pe.edu.upeu.msproveedores.dto.ProveedorRequest;
import pe.edu.upeu.msproveedores.dto.ProveedorResponse;
import pe.edu.upeu.msproveedores.entity.ProveedorEntity;
import pe.edu.upeu.msproveedores.errors.ProveedorNotFoundException;
import pe.edu.upeu.msproveedores.manager.IProveedorManager;
import pe.edu.upeu.msproveedores.mapper.ProveedorMapper;
import pe.edu.upeu.msproveedores.repository.ProveedorRepository;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProveedorService  {

    private final ProveedorRepository repository;
    private final ProveedorMapper mapper;
    private final IProveedorManager manager;
    private final CategoriaClient categoriaClient;

    @Autowired
    public ProveedorService(ProveedorRepository repository, ProveedorMapper mapper,
                            IProveedorManager manager, CategoriaClient categoriaClient) {
        this.repository = repository;
        this.mapper = mapper;
        this.manager = manager;
        this.categoriaClient = categoriaClient;
    }

    // Método para guardar archivo y generar URL
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

    // Se quitó el @Override
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

    // Se quitó el @Override
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

    // Se quitó el @Override
    @CircuitBreaker(name = "proveedoresCB", fallbackMethod = "fallbackMethod")
    public ProveedorResponse crear(ProveedorRequest request, MultipartFile imagen) throws Exception {
        repository.findByNombresAndApellidos(request.getNombres(), request.getApellidos()).ifPresent(p -> {
            throw new IllegalArgumentException("Ya existe un proveedor con ese nombre: " + request.getNombres());
        });

        manager.validarCategoriaExterno(request.getCategoriaId());

        // Seteamos la URL en el request antes de mapear
        String urlImagen = guardarImagen(imagen);
        request.setImagenUrl(urlImagen);

        ProveedorEntity entity = mapper.toEntity(request);
        ProveedorResponse response = mapper.toResponse(repository.save(entity));

        CategoriaResponse cat = categoriaClient.buscarPorId(request.getCategoriaId());
        response.setCategoriaNombre(cat.getNombre());

        return response;
    }

    public ProveedorResponse fallbackMethod(ProveedorRequest request, MultipartFile imagen, Exception e) {
        System.err.println("Causa del Fallback: " + e.getMessage());
        e.printStackTrace();

        ProveedorResponse response = new ProveedorResponse();
        response.setId(0L);
        response.setNombres("FALLBACK: " + e.getMessage());
        return response;
    }

    // Se quitó el @Override
    public ProveedorResponse actualizar(Long id, ProveedorRequest request, MultipartFile imagen) {
        ProveedorEntity entity = repository.findById(id)
                .orElseThrow(() -> new ProveedorNotFoundException(id));

        // Lógica para actualizar URL de imagen
        if (imagen != null && !imagen.isEmpty()) {
            request.setImagenUrl(guardarImagen(imagen));
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

    // Se quitó el @Override
    public void eliminar(Long id) {
        ProveedorEntity entity = repository.findById(id)
                .orElseThrow(() -> new ProveedorNotFoundException(id));
        repository.delete(entity);
    }

    // Se quitó el @Override
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