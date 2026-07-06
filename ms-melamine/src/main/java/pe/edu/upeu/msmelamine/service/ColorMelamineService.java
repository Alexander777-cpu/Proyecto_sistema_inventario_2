package pe.edu.upeu.msmelamine.service;

import org.springframework.stereotype.Service;
import pe.edu.upeu.msmelamine.entity.ColorMelamineEntity;
import pe.edu.upeu.msmelamine.repository.ColorMelamineRepository;
import java.util.List;

@Service
public class ColorMelamineService implements IColorMelamineService {

    private final ColorMelamineRepository repository;

    public ColorMelamineService(ColorMelamineRepository repository) {
        this.repository = repository;
    }

    @Override
    public ColorMelamineEntity crear(ColorMelamineEntity entity) {
        if (repository.existsByNombreIgnoreCase(entity.getNombre())) {
            throw new IllegalArgumentException("Ya existe el color: " + entity.getNombre());
        }
        return repository.save(entity);
    }

    @Override
    public List<ColorMelamineEntity> listar() {
        return repository.findAll();
    }

    @Override
    public ColorMelamineEntity buscarPorId(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("No se encontró el color con ID: " + id));
    }

    @Override
    public ColorMelamineEntity actualizar(Long id, ColorMelamineEntity entity) {
        ColorMelamineEntity existe = buscarPorId(id);
        if (!existe.getNombre().equalsIgnoreCase(entity.getNombre()) && repository.existsByNombreIgnoreCase(entity.getNombre())) {
            throw new IllegalArgumentException("Ya existe otro color con el nombre: " + entity.getNombre());
        }
        existe.setNombre(entity.getNombre());
        return repository.save(existe);
    }

    @Override
    public void eliminar(Long id) {
        repository.delete(buscarPorId(id));
    }
}