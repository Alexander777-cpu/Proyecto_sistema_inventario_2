package pe.edu.upeu.msherramientas.service;

import org.springframework.stereotype.Service;
import pe.edu.upeu.msherramientas.entity.MarcaHerramientaEntity;
import pe.edu.upeu.msherramientas.repository.MarcaHerramientaRepository;
import java.util.List;

@Service
public class MarcaHerramientaService implements IMarcaHerramientaService {

    private final MarcaHerramientaRepository repository;

    public MarcaHerramientaService(MarcaHerramientaRepository repository) {
        this.repository = repository;
    }

    @Override
    public MarcaHerramientaEntity crear(MarcaHerramientaEntity entity) {
        if (repository.existsByNombreIgnoreCase(entity.getNombre())) {
            throw new IllegalArgumentException("Ya existe una Marca con el nombre: " + entity.getNombre());
        }
        return repository.save(entity);
    }

    @Override
    public List<MarcaHerramientaEntity> listar() {
        return repository.findAll();
    }

    @Override
    public MarcaHerramientaEntity buscarPorId(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("La Marca con ID " + id + " no existe."));
    }

    @Override
    public MarcaHerramientaEntity actualizar(Long id, MarcaHerramientaEntity entity) {
        MarcaHerramientaEntity existe = buscarPorId(id);

        if (!existe.getNombre().equalsIgnoreCase(entity.getNombre()) &&
                repository.existsByNombreIgnoreCase(entity.getNombre())) {
            throw new IllegalArgumentException("Ya existe otra Marca con el nombre: " + entity.getNombre());
        }

        existe.setNombre(entity.getNombre());
        return repository.save(existe);
    }

    @Override
    public void eliminar(Long id) {
        MarcaHerramientaEntity existe = buscarPorId(id);
        repository.delete(existe);
    }
}