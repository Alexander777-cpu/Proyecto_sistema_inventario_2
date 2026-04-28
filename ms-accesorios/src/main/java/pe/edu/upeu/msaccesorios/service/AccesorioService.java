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

    private final AccesorioRepository accesorioRepository;
    private final AccesorioMapper accesorioMapper;

    public AccesorioService(AccesorioRepository accesorioRepository, AccesorioMapper accesorioMapper) {
        this.accesorioRepository = accesorioRepository;
        this.accesorioMapper = accesorioMapper;
    }

    public List<AccesorioResponse> listar() {
        return accesorioRepository.findAll()
                .stream()
                .map(accesorioMapper::toResponse)
                .collect(Collectors.toList());
    }

    public AccesorioResponse buscarPorId(Long id) {
        AccesorioEntity entity = accesorioRepository.findById(id)
                .orElseThrow(() -> new AccesorioNotFoundException(id));
        return accesorioMapper.toResponse(entity);
    }



    public AccesorioResponse crear(AccesorioRequest request) {
        accesorioRepository.findByNombreIgnoreCase(request.getNombre()).ifPresent(a -> {
            throw new IllegalArgumentException("Ya existe un accesorio con el nombre: " + request.getNombre());
        }); 
        if (request.getStock() <= 0) {
            throw new IllegalArgumentException("El stock debe ser mayor a 0");
        }
        if (request.getPrecio() <= 0) {
            throw new IllegalArgumentException("El precio debe ser mayor a 0");
        }
        AccesorioEntity entity = accesorioMapper.toEntity(request);
        entity.setEstado("ACTIVO");
        return accesorioMapper.toResponse(accesorioRepository.save(entity));
    }

    public AccesorioResponse actualizar(Long id, AccesorioRequest request) {
        AccesorioEntity entity = accesorioRepository.findById(id)
                .orElseThrow(() -> new AccesorioNotFoundException(id));
        if (request.getStock() <= 0) {
            throw new IllegalArgumentException("El stock debe ser mayor a 0");
        }
        if (request.getPrecio() <= 0) {
            throw new IllegalArgumentException("El precio debe ser mayor a 0");
        }
        accesorioMapper.updateEntity(entity, request);
        return accesorioMapper.toResponse(accesorioRepository.save(entity));
    }

    public void eliminar(Long id) {
        AccesorioEntity entity = accesorioRepository.findById(id)
                .orElseThrow(() -> new AccesorioNotFoundException(id));
        entity.setEstado("INACTIVO");
        accesorioRepository.save(entity);
    }

    public List<AccesorioResponse> buscarPorNombre(String nombre) {
        return accesorioRepository.findByNombreContainingIgnoreCase(nombre)
                .stream()
                .map(accesorioMapper::toResponse)
                .collect(Collectors.toList());
    }

    public List<AccesorioResponse> buscarPorCategoria(String categoria) {
        return accesorioRepository.findByCategoria(categoria)
                .stream()
                .map(accesorioMapper::toResponse)
                .collect(Collectors.toList());
    }

    public List<AccesorioResponse> listarConStock() {
        return accesorioRepository.findByStockGreaterThan(0)
                .stream()
                .map(accesorioMapper::toResponse)
                .collect(Collectors.toList());
    }
}