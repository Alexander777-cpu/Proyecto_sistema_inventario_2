package pe.edu.upeu.msherramientas.service;

import org.springframework.stereotype.Service;
import pe.edu.upeu.msherramientas.entity.TipoHerramientaEntity;
import pe.edu.upeu.msherramientas.repository.TipoHerramientaRepository;
import java.util.List;

@Service
public class TipoHerramientaService implements ITipoHerramientaService {

    private final TipoHerramientaRepository repository;

    public TipoHerramientaService(TipoHerramientaRepository repository) {
        this.repository = repository;
    }

    @Override
    public TipoHerramientaEntity crear(TipoHerramientaEntity entity) {
        if (repository.existsByNombreIgnoreCase(entity.getNombre())) {
            throw new IllegalArgumentException("Ya existe un Tipo con el nombre: " + entity.getNombre());
        }
        return repository.save(entity);
    }

    @Override
    public List<TipoHerramientaEntity> listar() {
        return repository.findAll();
    }

    @Override
    public TipoHerramientaEntity buscarPorId(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("El Tipo con ID " + id + " no existe."));
    }

    @Override
    public TipoHerramientaEntity actualizar(Long id, TipoHerramientaEntity entity) {
        TipoHerramientaEntity existe = buscarPorId(id);

        if (!existe.getNombre().equalsIgnoreCase(entity.getNombre()) &&
                repository.existsByNombreIgnoreCase(entity.getNombre())) {
            throw new IllegalArgumentException("Ya existe otro Tipo con el nombre: " + entity.getNombre());
        }

        existe.setNombre(entity.getNombre());
        return repository.save(existe);
    }

    @Override
    public void eliminar(Long id) {
        TipoHerramientaEntity existe = buscarPorId(id);
        repository.delete(existe);
    }
}