package pe.edu.upeu.msproyectos.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pe.edu.upeu.msproyectos.dtos.ProyectoRequest;
import pe.edu.upeu.msproyectos.dtos.ProyectoResponse;
import pe.edu.upeu.msproyectos.entity.ProyectoEntity;
import pe.edu.upeu.msproyectos.mappers.ProyectoMapper;
import pe.edu.upeu.msproyectos.repository.ProyectoRepository;
import pe.edu.upeu.msproyectos.services.IProyectoService;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProyectoService implements IProyectoService {

    @Autowired
    private ProyectoRepository repository;

    @Autowired
    private ProyectoMapper mapper;

    @Override
    @Transactional(readOnly = true)
    public List<ProyectoResponse> listarTodos() {
        return repository.findAll().stream()
                .map(mapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public ProyectoResponse buscarPorId(Long id) {
        return repository.findById(id)
                .map(mapper::toResponse)
                .orElseThrow(() -> new RuntimeException("Proyecto no encontrado con id: " + id));
    }

    @Override
    @Transactional
    public ProyectoResponse crear(ProyectoRequest request) {
        ProyectoEntity entity = mapper.toEntity(request);
        ProyectoEntity guardado = repository.save(entity);
        return mapper.toResponse(guardado);
    }

    @Override
    @Transactional
    public ProyectoResponse actualizar(Long id, ProyectoRequest request) {
        return repository.findById(id).map(existente -> {

            // 1. Actualizar campos cabecera
            existente.setNombre(request.getNombre());
            existente.setDireccion(request.getDireccion());
            existente.setClienteId(request.getClienteId());

            // 2. Limpiar y reconstruir detalles (vital para CascadeType.ALL y orphanRemoval)
            ProyectoEntity nuevosDatos = mapper.toEntity(request);
            existente.getDetalles().clear();

            if (nuevosDatos.getDetalles() != null) {
                nuevosDatos.getDetalles().forEach(detalle -> {
                    detalle.setProyecto(existente);
                    existente.getDetalles().add(detalle);
                });
            }

            ProyectoEntity actualizado = repository.save(existente);
            return mapper.toResponse(actualizado);

        }).orElseThrow(() -> new RuntimeException("Proyecto no encontrado con id: " + id));
    }

    @Override
    @Transactional
    public void eliminar(Long id) {
        repository.deleteById(id);
    }
}