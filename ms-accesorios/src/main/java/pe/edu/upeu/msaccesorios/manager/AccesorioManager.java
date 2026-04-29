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
        try {
            return repository.findAll()
                    .stream()
                    .map(mapper::toResponse)
                    .collect(Collectors.toList());
        } catch (Exception ex) {
            throw ex;
        }
    }

    @Override
    public AccesorioResponse buscarPorId(Long id) {
        try {
            AccesorioEntity entity = repository.findById(id)
                    .orElseThrow(() -> new AccesorioNotFoundException(id));
            return mapper.toResponse(entity);
        } catch (Exception ex) {
            throw ex;
        }
    }

    @Override
    public AccesorioResponse crear(AccesorioRequest request) {
        try {
            repository.findByNombreIgnoreCase(request.getNombre()).ifPresent(a -> {
                throw new IllegalArgumentException("Ya existe un accesorio con el nombre: " + request.getNombre());
            });
            AccesorioEntity entity = mapper.toEntity(request);
            entity.setEstado("ACTIVO");
            return mapper.toResponse(repository.save(entity));
        } catch (Exception ex) {
            throw ex;
        }
    }

    @Override
    public AccesorioResponse actualizar(Long id, AccesorioRequest request) {
        try {
            AccesorioEntity entity = repository.findById(id)
                    .orElseThrow(() -> new AccesorioNotFoundException(id));
            mapper.updateEntity(entity, request);
            return mapper.toResponse(repository.save(entity));
        } catch (Exception ex) {
            throw ex;
        }
    }

    @Override
    public void eliminar(Long id) {
        try {
            AccesorioEntity entity = repository.findById(id)
                    .orElseThrow(() -> new AccesorioNotFoundException(id));
            entity.setEstado("INACTIVO");
            repository.save(entity);
        } catch (Exception ex) {
            throw ex;
        }
    }
}