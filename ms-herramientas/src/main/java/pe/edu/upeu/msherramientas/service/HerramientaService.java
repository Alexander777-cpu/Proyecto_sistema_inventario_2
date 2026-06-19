package pe.edu.upeu.msherramientas.service;


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

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class HerramientaService {

    private final HerramientaRepository repository;
    private final HerramientaMapper mapper;
    private final IHerramientaManager manager;
    private final EstadoClient estadoClient;

    @Autowired
    public HerramientaService(HerramientaRepository repository, HerramientaMapper mapper,
                              IHerramientaManager manager, EstadoClient estadoClient) {
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
    public HerramientaResponse crear(HerramientaRequest request, MultipartFile imagen) throws Exception {
        // 1. Validar estado (Si esto falla, el error llega directo a Postman)
        manager.validarEstadoExterno(request.getEstadoId());

        if (repository.existsByNombreIgnoreCase(request.getNombre())) {
            throw new IllegalArgumentException("Ya existe una herramienta con el nombre: " + request.getNombre());
        }

        String urlImagen = guardarImagen(imagen);
        request.setImagenUrl(urlImagen);

        HerramientaEntity entity = mapper.toEntity(request);
        HerramientaResponse response = mapper.toResponse(repository.save(entity));

        // 2. Enriquecer datos
        EstadoResponse estado = estadoClient.buscarPorId(request.getEstadoId());
        response.setEstadoNombre(estado.getEstadoNombre());

        return response;
    }

    public List<HerramientaResponse> listar() {
        return repository.findAll().stream().map(entity -> {
            HerramientaResponse res = mapper.toResponse(entity);
            EstadoResponse estado = estadoClient.buscarPorId(entity.getEstadoId());
            res.setEstadoNombre(estado.getEstadoNombre());
            return res;
        }).collect(Collectors.toList());
    }

    public HerramientaResponse buscarPorId(Long id) {
        HerramientaEntity entity = repository.findById(id)
                .orElseThrow(() -> new HerramientaNotFoundException(id));
        HerramientaResponse res = mapper.toResponse(entity);
        EstadoResponse estado = estadoClient.buscarPorId(entity.getEstadoId());
        res.setEstadoNombre(estado.getEstadoNombre());
        return res;
    }

    public List<HerramientaResponse> buscarPorNombre(String nombre){
        return repository.findByNombreContainingIgnoreCase(nombre).stream().map(entity -> {
            HerramientaResponse res = mapper.toResponse(entity);
            EstadoResponse estado = estadoClient.buscarPorId(entity.getEstadoId());
            res.setEstadoNombre(estado.getEstadoNombre());
            return res;
        }).collect(Collectors.toList());
    }

    public HerramientaResponse actualizar(Long id, HerramientaRequest request, MultipartFile imagen) throws Exception {
        HerramientaEntity entity = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("No se encontró la herramienta"));

        manager.validarEstadoExterno(request.getEstadoId());

        if (imagen != null && !imagen.isEmpty()) {
            request.setImagenUrl(guardarImagen(imagen));
        } else {
            request.setImagenUrl(entity.getImagenUrl());
        }

        mapper.updateEntity(entity, request);
        HerramientaResponse res = mapper.toResponse(repository.save(entity));
        EstadoResponse estado = estadoClient.buscarPorId(entity.getEstadoId());
        res.setEstadoNombre(estado.getEstadoNombre());
        return res;
    }

    public void eliminar(Long id){
        repository.deleteById(id);
    }
}