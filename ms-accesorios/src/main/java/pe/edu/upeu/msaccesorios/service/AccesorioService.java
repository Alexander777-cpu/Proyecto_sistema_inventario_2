package pe.edu.upeu.msaccesorios.service;

import org.springframework.stereotype.Service;
import pe.edu.upeu.msaccesorios.dto.AccesorioRequest;
import pe.edu.upeu.msaccesorios.dto.AccesorioResponse;
import pe.edu.upeu.msaccesorios.entity.AccesorioEntity;
import pe.edu.upeu.msaccesorios.errors.AccesorioNotFoundException;
import pe.edu.upeu.msaccesorios.mappers.AccesorioMapper;
import pe.edu.upeu.msaccesorios.repository.AccesorioRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AccesorioService {

    private final AccesorioRepository repository;
    private final AccesorioMapper mapper;

    public AccesorioService(AccesorioRepository repository, AccesorioMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public List<AccesorioResponse> listar() {
        return repository.findAll()
                .stream()
                .map(mapper::toResponse)
                .collect(Collectors.toList());
    }

    public AccesorioResponse buscarPorId(Long id) {
        AccesorioEntity entity = repository.findById(id)
                .orElseThrow(() -> new AccesorioNotFoundException(id));
        return mapper.toResponse(entity);
    }

    public AccesorioResponse crear(AccesorioRequest request) {
        if (request.getStock() <= 0) {
            throw new IllegalArgumentException("El stock debe ser mayor a 0");
        }
        if (request.getPrecio() <= 0) {
            throw new IllegalArgumentException("El precio debe ser mayor a 0");
        }
        AccesorioEntity entity = mapper.toEntity(request);
        entity.setEstado("ACTIVO");
        return mapper.toResponse(repository.save(entity));
    }

    public AccesorioResponse actualizar(Long id, AccesorioRequest request) {
        AccesorioEntity entity = repository.findById(id)
                .orElseThrow(() -> new AccesorioNotFoundException(id));
        if (request.getStock() <= 0) {
            throw new IllegalArgumentException("El stock debe ser mayor a 0");
        }
        if (request.getPrecio() <= 0) {
            throw new IllegalArgumentException("El precio debe ser mayor a 0");
        }
        mapper.updateEntity(entity, request);
        return mapper.toResponse(repository.save(entity));
    }

    public void eliminar(Long id) {
        AccesorioEntity entity = repository.findById(id)
                .orElseThrow(() -> new AccesorioNotFoundException(id));
        entity.setEstado("INACTIVO");
        repository.save(entity);
    }

    public List<AccesorioResponse> buscarPorNombre(String nombre) {
        return repository.findByNombreContainingIgnoreCase(nombre)
                .stream()
                .map(mapper::toResponse)
                .collect(Collectors.toList());
    }

    public List<AccesorioResponse> buscarPorCategoria(String categoria) {
        return repository.findByCategoria(categoria)
                .stream()
                .map(mapper::toResponse)
                .collect(Collectors.toList());
    }

    public List<AccesorioResponse> listarConStock() {
        return repository.findByStockGreaterThan(0)
                .stream()
                .map(mapper::toResponse)
                .collect(Collectors.toList());
    }
}