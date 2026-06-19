package pe.edu.upeu.msaccesorios.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import pe.edu.upeu.msaccesorios.client.CategoriaClient;
import pe.edu.upeu.msaccesorios.client.EstadoClient;
import pe.edu.upeu.msaccesorios.dto.AccesorioRequest;
import pe.edu.upeu.msaccesorios.dto.AccesorioResponse;
import pe.edu.upeu.msaccesorios.dto.CategoriaResponse;
import pe.edu.upeu.msaccesorios.dto.EstadoResponse; // Asegúrate de tener este DTO importado
import pe.edu.upeu.msaccesorios.entity.AccesorioEntity;
import pe.edu.upeu.msaccesorios.errors.AccesorioNotFoundException;
import pe.edu.upeu.msaccesorios.manager.IAccesorioManager;
import pe.edu.upeu.msaccesorios.mappers.AccesorioMapper;
import pe.edu.upeu.msaccesorios.repository.AccesorioRepository;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AccesorioService {

    private final AccesorioRepository repository;
    private final AccesorioMapper mapper;
    private final IAccesorioManager manager;
    private final EstadoClient estadoClient;
    // SOLO SE AGREGÓ ESTO: Cliente Feign para las categorías
    private final CategoriaClient categoriaClient;

    @Autowired
    public AccesorioService(AccesorioRepository repository, AccesorioMapper mapper,
                            IAccesorioManager manager, EstadoClient estadoClient,
                            CategoriaClient categoriaClient) {
        this.repository = repository;
        this.mapper = mapper;
        this.manager = manager;
        this.estadoClient = estadoClient;
        this.categoriaClient = categoriaClient;
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
    public AccesorioResponse crear(AccesorioRequest request, MultipartFile imagen) throws Exception {
        // 1. Validar estado y categoría (Si fallan, el error llega directo a Postman)
        manager.validarEstadoExterno(request.getEstadoId());
        manager.validarCategoriaExterna(request.getCategoriaId()); // NUEVO

        if (repository.findByNombreIgnoreCase(request.getNombre()).isPresent()) {
            throw new IllegalArgumentException("Ya existe un accesorio con el nombre: " + request.getNombre());
        }

        String urlImagen = guardarImagen(imagen);
        request.setImagenUrl(urlImagen);

        AccesorioEntity entity = mapper.toEntity(request);
        AccesorioResponse response = mapper.toResponse(repository.save(entity));

        // 2. Enriquecer datos (Estado y Categoría)
        EstadoResponse estado = estadoClient.buscarPorId(request.getEstadoId());
        response.setEstadoNombre(estado.getEstadoNombre());

        CategoriaResponse categoria = categoriaClient.buscarPorId(request.getCategoriaId());
        response.setCategoriaNombre(categoria.getNombre());

        return response;
    }

    public List<AccesorioResponse> listar() {
        return repository.findAll().stream().map(entity -> {
            AccesorioResponse res = mapper.toResponse(entity);

            EstadoResponse estado = estadoClient.buscarPorId(entity.getEstadoId());
            res.setEstadoNombre(estado.getEstadoNombre());

            CategoriaResponse categoria = categoriaClient.buscarPorId(entity.getCategoriaId());
            res.setCategoriaNombre(categoria.getNombre());

            return res;
        }).collect(Collectors.toList());
    }

    public AccesorioResponse buscarPorId(Long id) {
        AccesorioEntity entity = repository.findById(id)
                .orElseThrow(() -> new AccesorioNotFoundException(id));

        AccesorioResponse res = mapper.toResponse(entity);

        EstadoResponse estado = estadoClient.buscarPorId(entity.getEstadoId());
        res.setEstadoNombre(estado.getEstadoNombre());

        CategoriaResponse categoria = categoriaClient.buscarPorId(entity.getCategoriaId());
        res.setCategoriaNombre(categoria.getNombre());

        return res;
    }

    public AccesorioResponse actualizar(Long id, AccesorioRequest request, MultipartFile imagen) throws Exception {
        AccesorioEntity entity = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("No se encontró el accesorio"));

        manager.validarEstadoExterno(request.getEstadoId());
        manager.validarCategoriaExterna(request.getCategoriaId()); // NUEVO

        if (imagen != null && !imagen.isEmpty()) {
            request.setImagenUrl(guardarImagen(imagen));
        } else {
            request.setImagenUrl(entity.getImagenUrl());
        }

        mapper.updateEntity(entity, request);
        AccesorioResponse res = mapper.toResponse(repository.save(entity));

        EstadoResponse estado = estadoClient.buscarPorId(entity.getEstadoId());
        res.setEstadoNombre(estado.getEstadoNombre());

        CategoriaResponse categoria = categoriaClient.buscarPorId(entity.getCategoriaId());
        res.setCategoriaNombre(categoria.getNombre());

        return res;
    }

    public void eliminar(Long id){
        repository.deleteById(id);
    }

    // --- Métodos extra ---

    public List<AccesorioResponse> buscarPorNombre(String nombre) {
        List<AccesorioResponse> resultado = repository.findByNombreContainingIgnoreCase(nombre).stream().map(entity -> {
            AccesorioResponse res = mapper.toResponse(entity);

            EstadoResponse estado = estadoClient.buscarPorId(entity.getEstadoId());
            res.setEstadoNombre(estado.getEstadoNombre());

            CategoriaResponse categoria = categoriaClient.buscarPorId(entity.getCategoriaId());
            res.setCategoriaNombre(categoria.getNombre());

            return res;
        }).collect(Collectors.toList());

        if (resultado.isEmpty()) {
            throw new IllegalArgumentException("No se encontraron accesorios con el nombre: " + nombre);
        }
        return resultado;
    }

    // SOLO SE MODIFICÓ ESTO: Ahora busca por categoriaId en lugar de String
    public List<AccesorioResponse> buscarPorCategoriaId(Long categoriaId) {
        List<AccesorioResponse> resultado = repository.findByCategoriaId(categoriaId).stream().map(entity -> {
            AccesorioResponse res = mapper.toResponse(entity);

            EstadoResponse estado = estadoClient.buscarPorId(entity.getEstadoId());
            res.setEstadoNombre(estado.getEstadoNombre());

            CategoriaResponse categoria = categoriaClient.buscarPorId(entity.getCategoriaId());
            res.setCategoriaNombre(categoria.getNombre());

            return res;
        }).collect(Collectors.toList());

        if (resultado.isEmpty()) {
            throw new IllegalArgumentException("No se encontraron accesorios con la categoría ID: " + categoriaId);
        }
        return resultado;
    }

    public List<AccesorioResponse> listarConStock() {
        return repository.findByStockGreaterThan(0).stream().map(entity -> {
            AccesorioResponse res = mapper.toResponse(entity);

            EstadoResponse estado = estadoClient.buscarPorId(entity.getEstadoId());
            res.setEstadoNombre(estado.getEstadoNombre());

            CategoriaResponse categoria = categoriaClient.buscarPorId(entity.getCategoriaId());
            res.setCategoriaNombre(categoria.getNombre());

            return res;
        }).collect(Collectors.toList());
    }
}