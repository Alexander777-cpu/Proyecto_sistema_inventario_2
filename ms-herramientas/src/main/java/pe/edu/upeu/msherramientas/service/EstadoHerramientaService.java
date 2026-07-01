package pe.edu.upeu.msherramientas.service;

import org.springframework.stereotype.Service;
import pe.edu.upeu.msherramientas.entity.EstadoHerramientaEntity;
import pe.edu.upeu.msherramientas.repository.EstadoHerramientaRepository;

import java.util.List;

@Service

public class EstadoHerramientaService implements IEstadoHerramientaService{

    private final EstadoHerramientaRepository repository;

    public EstadoHerramientaService(EstadoHerramientaRepository repository) {
        this.repository = repository;
    }

    @Override
    public EstadoHerramientaEntity crear(EstadoHerramientaEntity entity) {
        // Valida si ya existe antes de crear
        if (repository.existsByNombreIgnoreCase(entity.getNombre())) {
            throw new IllegalArgumentException("Ya existe un Estado con el nombre: " + entity.getNombre());
        }
        return repository.save(entity);
    }

    @Override
    public List<EstadoHerramientaEntity> listar() {
        return repository.findAll();
    }

    @Override
    public EstadoHerramientaEntity buscarPorId(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("El Estado con ID " + id + " no existe."));
    }

    @Override
    public EstadoHerramientaEntity actualizar(Long id, EstadoHerramientaEntity entity) {
        EstadoHerramientaEntity existe = buscarPorId(id); // Reutilizamos el buscarPorId para validar que exista

        // Validación opcional: Si le están cambiando el nombre a uno que ya existe en otro ID
        if (!existe.getNombre().equalsIgnoreCase(entity.getNombre()) &&
                repository.existsByNombreIgnoreCase(entity.getNombre())) {
            throw new IllegalArgumentException("Ya existe otro Estado con el nombre: " + entity.getNombre());
        }

        existe.setNombre(entity.getNombre());
        return repository.save(existe);
    }

    @Override
    public void eliminar(Long id) {
        EstadoHerramientaEntity existe = buscarPorId(id);
        repository.delete(existe); // Borrado físico
    }

}
