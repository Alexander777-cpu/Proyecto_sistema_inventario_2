package pe.edu.upeu.msaccesorios.manager;

import org.springframework.stereotype.Component;
import pe.edu.upeu.msaccesorios.dto.AccesorioRequest;
import pe.edu.upeu.msaccesorios.dto.AccesorioResponse;
import pe.edu.upeu.msaccesorios.entity.AccesorioEntity;
import pe.edu.upeu.msaccesorios.errors.AccesorioNotFoundException;
import pe.edu.upeu.msaccesorios.mappers.AccesorioMapper;
import pe.edu.upeu.msaccesorios.repository.AccesorioRepository;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class AccesorioManager implements IAccesorioManager {
    private final AccesorioRepository repository;
    private final AccesorioMapper mapper;

    public AccesorioManager(AccesorioRepository repository, AccesorioMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public List<AccesorioResponse> listar() {
        return repository.findAll()
                .stream()
                .map(mapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public AccesorioResponse buscarPorId(Long id) {
        AccesorioEntity entity = repository.findById(id)
                .orElseThrow(() -> new AccesorioNotFoundException(id));
        return mapper.toResponse(entity);
    }

    @Override
    public AccesorioResponse crear(AccesorioRequest request) {
        repository.findByNombreIgnoreCase(request.getNombre()).ifPresent(a -> {
            throw new IllegalArgumentException("Ya existe un accesorio con el nombre: " + request.getNombre());
        });
        AccesorioEntity entity = mapper.toEntity(request);
        entity.setEstado("ACTIVO");
        return mapper.toResponse(repository.save(entity));
    }

    @Override
    public AccesorioResponse actualizar(Long id, AccesorioRequest request) {
        AccesorioEntity entity = repository.findById(id)
                .orElseThrow(() -> new AccesorioNotFoundException(id));
        mapper.updateEntity(entity, request);
        return mapper.toResponse(repository.save(entity));
    }

    @Override
    public void eliminar(Long id) {
        AccesorioEntity entity = repository.findById(id)
                .orElseThrow(() -> new AccesorioNotFoundException(id));
        entity.setEstado("INACTIVO");
        repository.save(entity);
    }
}