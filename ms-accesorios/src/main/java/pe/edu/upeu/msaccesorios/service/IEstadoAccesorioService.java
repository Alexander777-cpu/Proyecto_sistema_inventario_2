package pe.edu.upeu.msaccesorios.service;

import pe.edu.upeu.msaccesorios.entity.EstadoAccesorioEntity;

import java.util.List;

public interface IEstadoAccesorioService {

    EstadoAccesorioEntity crear(EstadoAccesorioEntity entity);

    List<EstadoAccesorioEntity> listar();

    EstadoAccesorioEntity buscarPorId(Long id);

    EstadoAccesorioEntity actualizar(Long id, EstadoAccesorioEntity entity);

    void eliminar(Long id);
}
