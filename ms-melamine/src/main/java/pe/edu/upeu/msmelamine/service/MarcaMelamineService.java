package pe.edu.upeu.msmelamine.service;

import org.springframework.stereotype.Service;
import pe.edu.upeu.msmelamine.entity.MarcaMelamineEntity;
import pe.edu.upeu.msmelamine.repository.MarcaMelamineRepository;

import java.util.List;

@Service
public class MarcaMelamineService implements IMarcaMelamineService {

    private final MarcaMelamineRepository repository;

    public MarcaMelamineService(MarcaMelamineRepository repository) {
        this.repository = repository;
    }

    @Override
    public MarcaMelamineEntity crear(MarcaMelamineEntity entity) {
        if (repository.existsByNombreIgnoreCase(entity.getNombre())) {
            throw new IllegalArgumentException("Ya existe la marca: " + entity.getNombre());
        }
        return repository.save(entity);
    }

    @Override
    public List<MarcaMelamineEntity> listar() {
        return repository.findAll();
    }

    @Override
    public MarcaMelamineEntity buscarPorId(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("No se encontró la marca con ID: " + id));
    }

    @Override
    public MarcaMelamineEntity actualizar(Long id, MarcaMelamineEntity entity) {
        MarcaMelamineEntity existe = buscarPorId(id);
        if (!existe.getNombre().equalsIgnoreCase(entity.getNombre()) && repository.existsByNombreIgnoreCase(entity.getNombre())) {
            throw new IllegalArgumentException("Ya existe otra marca con el nombre: " + entity.getNombre());
        }
        existe.setNombre(entity.getNombre());
        return repository.save(existe);
    }

    @Override
    public void eliminar(Long id) {
        repository.delete(buscarPorId(id));
    }
}