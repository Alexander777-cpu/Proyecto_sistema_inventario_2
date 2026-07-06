package pe.edu.upeu.msmelamine.service;

import org.springframework.stereotype.Service;
import pe.edu.upeu.msmelamine.entity.EstadoMelamineEntity;
import pe.edu.upeu.msmelamine.repository.EstadoMelamineRepository;

import java.util.List;

@Service
public class EstadoMelamineService implements IEstadoMelamineService {

    private final EstadoMelamineRepository repository;

    public EstadoMelamineService(EstadoMelamineRepository repository) {
        this.repository = repository;
    }

    @Override
    public EstadoMelamineEntity crear(EstadoMelamineEntity entity) {
        if (repository.existsByNombreIgnoreCase(entity.getNombre())) {
            throw new IllegalArgumentException("Ya existe el estado: " + entity.getNombre());
        }
        return repository.save(entity);
    }

    @Override
    public List<EstadoMelamineEntity> listar() {
        return repository.findAll();
    }

    @Override
    public EstadoMelamineEntity buscarPorId(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("No se encontró el estado con ID: " + id));
    }

    @Override
    public EstadoMelamineEntity actualizar(Long id, EstadoMelamineEntity entity) {
        EstadoMelamineEntity existe = buscarPorId(id);
        if (!existe.getNombre().equalsIgnoreCase(entity.getNombre()) && repository.existsByNombreIgnoreCase(entity.getNombre())) {
            throw new IllegalArgumentException("Ya existe otro estado con el nombre: " + entity.getNombre());
        }
        existe.setNombre(entity.getNombre());
        return repository.save(existe);
    }

    @Override
    public void eliminar(Long id) {
        repository.delete(buscarPorId(id));
    }
}