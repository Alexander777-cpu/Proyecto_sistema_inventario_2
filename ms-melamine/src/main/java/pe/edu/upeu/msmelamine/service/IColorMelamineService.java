package pe.edu.upeu.msmelamine.service;
import pe.edu.upeu.msmelamine.entity.ColorMelamineEntity;
import java.util.List;

public interface IColorMelamineService {
    ColorMelamineEntity crear(ColorMelamineEntity entity);
    List<ColorMelamineEntity> listar();
    ColorMelamineEntity buscarPorId(Long id);
    ColorMelamineEntity actualizar(Long id, ColorMelamineEntity entity);
    void eliminar(Long id);
}