package pe.edu.upeu.msaccesorios.service;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
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
import pe.edu.upeu.msaccesorios.service.cloud.CloudinaryService;


import java.util.List;
import java.util.stream.Collectors;

@Service
public class AccesorioService {

    private final AccesorioRepository repository;
    private final AccesorioMapper mapper;
    private final IAccesorioManager manager;
    private final EstadoClient estadoClient;
    private final CategoriaClient categoriaClient;
    private final CloudinaryService cloudinaryService;

    @Autowired
    public AccesorioService(AccesorioRepository repository,
                            AccesorioMapper mapper,
                            IAccesorioManager manager,
                            EstadoClient estadoClient,
                            CategoriaClient categoriaClient,
                            CloudinaryService cloudinaryService) {
        this.repository = repository;
        this.mapper = mapper;
        this.manager = manager;
        this.estadoClient = estadoClient;
        this.categoriaClient = categoriaClient;
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
    @CircuitBreaker(name = "accesoriosCB", fallbackMethod = "fallbackMethod")
    public AccesorioResponse crear(AccesorioRequest request, MultipartFile imagen) throws Exception {
        manager.validarEstadoExterno(request.getEstadoId());
        manager.validarCategoriaExterna(request.getCategoriaId());

        if (repository.findByNombreIgnoreCase(request.getNombre()).isPresent()) {
            throw new IllegalArgumentException("Ya existe un accesorio con el nombre: " + request.getNombre());
        }

        // Subida a Cloudinary reemplazando el guardado local
        String urlImagen = subirImagenAAlmacenamientoExterno(imagen);
        request.setImagenUrl(urlImagen);

        AccesorioEntity entity = mapper.toEntity(request);
        AccesorioResponse response = mapper.toResponse(repository.save(entity));

        // Enriquecer datos con manejo de posibles caídas temporales de red en el momento de crear
        try {
            EstadoResponse estado = estadoClient.buscarPorId(request.getEstadoId());
            response.setEstadoNombre(estado.getEstadoNombre());
        } catch (Exception e) {
            response.setEstadoNombre("No disponible");
        }

        try {
            CategoriaResponse categoria = categoriaClient.buscarPorId(request.getCategoriaId());
            response.setCategoriaNombre(categoria.getNombre());
        } catch (Exception e) {
            response.setCategoriaNombre("No disponible");
        }

        return response;
    }

    // Método de contingencia (Fallback) si falla validar o guardar
    public AccesorioResponse fallbackMethod(AccesorioRequest request, MultipartFile imagen, Exception e) {
        AccesorioResponse response = new AccesorioResponse();
        response.setId(0L);
        response.setNombre("FALLBACK: " + e.getMessage());
        return response;
    }

    public List<AccesorioResponse> listar() {
        return repository.findAll().stream().map(entity -> {
            AccesorioResponse res = mapper.toResponse(entity);
            enriquecerConDatosExternos(entity, res);
            return res;
        }).collect(Collectors.toList());
    }

    public AccesorioResponse buscarPorId(Long id) {
        AccesorioEntity entity = repository.findById(id)
                .orElseThrow(() -> new AccesorioNotFoundException(id));
        AccesorioResponse res = mapper.toResponse(entity);
        enriquecerConDatosExternos(entity, res);
        return res;
    }

    public AccesorioResponse actualizar(Long id, AccesorioRequest request, MultipartFile imagen) throws Exception {
        AccesorioEntity entity = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("No se encontró el accesorio"));

        manager.validarEstadoExterno(request.getEstadoId());
        manager.validarCategoriaExterna(request.getCategoriaId());

        if (imagen != null && !imagen.isEmpty()) {
            request.setImagenUrl(subirImagenAAlmacenamientoExterno(imagen));
        } else {
            request.setImagenUrl(entity.getImagenUrl());
        }

        mapper.updateEntity(entity, request);
        AccesorioResponse res = mapper.toResponse(repository.save(entity));
        enriquecerConDatosExternos(entity, res);
        return res;
    }

    public void eliminar(Long id){
        repository.deleteById(id);
    }

    // --- Métodos extra ---

    public List<AccesorioResponse> buscarPorNombre(String nombre) {
        List<AccesorioResponse> resultado = repository.findByNombreContainingIgnoreCase(nombre).stream().map(entity -> {
            AccesorioResponse res = mapper.toResponse(entity);
            enriquecerConDatosExternos(entity, res);
            return res;
        }).collect(Collectors.toList());

        if (resultado.isEmpty()) {
            throw new IllegalArgumentException("No se encontraron accesorios con el nombre: " + nombre);
        }
        return resultado;
    }

    public List<AccesorioResponse> buscarPorCategoriaId(Long categoriaId) {
        List<AccesorioResponse> resultado = repository.findByCategoriaId(categoriaId).stream().map(entity -> {
            AccesorioResponse res = mapper.toResponse(entity);
            enriquecerConDatosExternos(entity, res);
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
            enriquecerConDatosExternos(entity, res);
            return res;
        }).collect(Collectors.toList());
    }

    // --- Método auxiliar para evitar código repetido en las consultas ---
    private void enriquecerConDatosExternos(AccesorioEntity entity, AccesorioResponse res) {
        try {
            EstadoResponse estado = estadoClient.buscarPorId(entity.getEstadoId());
            res.setEstadoNombre(estado.getEstadoNombre());
        } catch (Exception e) {
            res.setEstadoNombre("No disponible");
        }

        try {
            CategoriaResponse categoria = categoriaClient.buscarPorId(entity.getCategoriaId());
            res.setCategoriaNombre(categoria.getNombre());
        } catch (Exception e) {
            res.setCategoriaNombre("No disponible");
        }
    }
}