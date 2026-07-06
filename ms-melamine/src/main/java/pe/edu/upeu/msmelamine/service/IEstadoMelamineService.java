package pe.edu.upeu.msmelamine.service;

import pe.edu.upeu.msmelamine.entity.EstadoMelamineEntity;
import java.util.List;

public interface IEstadoMelamineService {
    EstadoMelamineEntity crear(EstadoMelamineEntity entity);
    List<EstadoMelamineEntity> listar();
    EstadoMelamineEntity buscarPorId(Long id);
    EstadoMelamineEntity actualizar(Long id, EstadoMelamineEntity entity);
    void eliminar(Long id);
}