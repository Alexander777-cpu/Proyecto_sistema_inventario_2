package pe.edu.upeu.msaccesorios.service;

import org.springframework.stereotype.Service;
import pe.edu.upeu.msaccesorios.entity.EstadoAccesorioEntity;
import pe.edu.upeu.msaccesorios.repository.EstadoAccesorioRepository;

import java.util.List;

@Service // Añadida la anotación de Spring Boot
public class EstadoAccesorioService implements IEstadoAccesorioService {

    private final EstadoAccesorioRepository repository;

    public EstadoAccesorioService(EstadoAccesorioRepository repository) {
        this.repository = repository;
    }

    @Override
    public EstadoAccesorioEntity crear(EstadoAccesorioEntity entity) {
        // Valida si ya existe antes de crear
        if (repository.existsByNombreIgnoreCase(entity.getNombre())) {
            throw new IllegalArgumentException("Ya existe un Estado con el nombre: " + entity.getNombre());
        }
        return repository.save(entity);
    }

    @Override
    public List<EstadoAccesorioEntity> listar() {
        return repository.findAll();
    }

    @Override
    public EstadoAccesorioEntity buscarPorId(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("El Estado con ID " + id + " no existe."));
    }

    @Override
    public EstadoAccesorioEntity actualizar(Long id, EstadoAccesorioEntity entity) {
        EstadoAccesorioEntity existe = buscarPorId(id); // Reutilizamos el buscarPorId para validar que exista

        // Validación: Si le están cambiando el nombre a uno que ya existe en otro ID
        if (!existe.getNombre().equalsIgnoreCase(entity.getNombre()) &&
                repository.existsByNombreIgnoreCase(entity.getNombre())) {
            throw new IllegalArgumentException("Ya existe otro Estado con el nombre: " + entity.getNombre());
        }

        existe.setNombre(entity.getNombre());
        return repository.save(existe);
    }

    @Override
    public void eliminar(Long id) {
        EstadoAccesorioEntity existe = buscarPorId(id);
        repository.delete(existe); // Borrado físico
    }

}
