package pe.edu.upeu.msaccesorios.service;

import org.springframework.stereotype.Service;
import pe.edu.upeu.msaccesorios.entity.MarcaAccesorioEntity;
import pe.edu.upeu.msaccesorios.repository.MarcaAccesorioRepository;

import java.util.List;

@Service
public class MarcaAccesorioService implements IMarcaAccesorioService {

    private final MarcaAccesorioRepository repository;

    public MarcaAccesorioService(MarcaAccesorioRepository repository) {
        this.repository = repository;
    }

    @Override
    public MarcaAccesorioEntity crear(MarcaAccesorioEntity entity) {
        // Valida si ya existe antes de crear
        if (repository.existsByNombreIgnoreCase(entity.getNombre())) {
            throw new IllegalArgumentException("Ya existe una Marca con el nombre: " + entity.getNombre());
        }
        return repository.save(entity);
    }

    @Override
    public List<MarcaAccesorioEntity> listar() {
        return repository.findAll();
    }

    @Override
    public MarcaAccesorioEntity buscarPorId(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("La Marca con ID " + id + " no existe."));
    }

    @Override
    public MarcaAccesorioEntity actualizar(Long id, MarcaAccesorioEntity entity) {
        MarcaAccesorioEntity existe = buscarPorId(id); // Reutilizamos el buscarPorId para validar que exista

        // Validación: Si le están cambiando el nombre a uno que ya existe en otro ID
        if (!existe.getNombre().equalsIgnoreCase(entity.getNombre()) &&
                repository.existsByNombreIgnoreCase(entity.getNombre())) {
            throw new IllegalArgumentException("Ya existe otra Marca con el nombre: " + entity.getNombre());
        }

        existe.setNombre(entity.getNombre());
        return repository.save(existe);
    }

    @Override
    public void eliminar(Long id) {
        MarcaAccesorioEntity existe = buscarPorId(id);
        repository.delete(existe); // Borrado físico
    }

}
