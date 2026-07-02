package pe.edu.upeu.msproveedores.service;

import org.springframework.stereotype.Service;
import pe.edu.upeu.msproveedores.entity.CategoriaProveedorEntity;
import pe.edu.upeu.msproveedores.repository.CategoriaProveedorRepository;

import java.util.List;

@Service
public class CategoriaProveedorService implements ICategoriaProveedorService {

    private final CategoriaProveedorRepository repository;

    public CategoriaProveedorService(CategoriaProveedorRepository repository) {
        this.repository = repository;
    }

    @Override
    public CategoriaProveedorEntity crear(CategoriaProveedorEntity entity) {
        // Valida si ya existe antes de crear
        if (repository.existsByNombreIgnoreCase(entity.getNombre())) {
            throw new IllegalArgumentException("Ya existe una Categoría de Proveedor con el nombre: " + entity.getNombre());
        }
        return repository.save(entity);
    }

    @Override
    public List<CategoriaProveedorEntity> listar() {
        return repository.findAll();
    }

    @Override
    public CategoriaProveedorEntity buscarPorId(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("La Categoría de Proveedor con ID " + id + " no existe."));
    }

    @Override
    public CategoriaProveedorEntity actualizar(Long id, CategoriaProveedorEntity entity) {
        CategoriaProveedorEntity existe = buscarPorId(id); // Reutilizamos el buscarPorId para validar que exista

        // Validación: Si le están cambiando el nombre a uno que ya existe en otro ID
        if (!existe.getNombre().equalsIgnoreCase(entity.getNombre()) &&
                repository.existsByNombreIgnoreCase(entity.getNombre())) {
            throw new IllegalArgumentException("Ya existe otra Categoría con el nombre: " + entity.getNombre());
        }

        existe.setNombre(entity.getNombre());
        return repository.save(existe);
    }

    @Override
    public void eliminar(Long id) {
        CategoriaProveedorEntity existe = buscarPorId(id);
        repository.delete(existe); // Borrado físico
    }

}