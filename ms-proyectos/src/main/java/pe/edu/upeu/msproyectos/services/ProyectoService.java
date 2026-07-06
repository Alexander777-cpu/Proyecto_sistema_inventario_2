package pe.edu.upeu.msproyectos.services;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pe.edu.upeu.msproyectos.dtos.*;
import pe.edu.upeu.msproyectos.entity.ProyectoEntity;
import pe.edu.upeu.msproyectos.mappers.ProyectoMapper;
import pe.edu.upeu.msproyectos.repository.ProyectoRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProyectoService implements IProyectoService {

    private final ProyectoRepository repository;
    private final ProyectoMapper mapper;

    public ProyectoService(ProyectoRepository repository, ProyectoMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    @Transactional
    public ProyectoResponse crear(ProyectoRequest request) {
        ProyectoEntity entity = mapper.toEntity(request);
        return mapper.toResponse(repository.save(entity));
    }

    @Override
    public List<ProyectoResponse> listar() {
        return repository.findAll().stream()
                .map(mapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public ProyectoResponse buscarPorId(Long id) {
        return repository.findById(id)
                .map(mapper::toResponse)
                .orElseThrow(() -> new RuntimeException("Proyecto no encontrado"));
    }

    @Override
    @Transactional
    public ProyectoResponse actualizar(Long id, ProyectoRequest request) {
        ProyectoEntity entity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Proyecto no encontrado"));

        mapper.updateEntity(entity, request);
        return mapper.toResponse(repository.save(entity));
    }

    @Override
    @Transactional
    public void eliminar(Long id) {
        repository.deleteById(id);
    }
}