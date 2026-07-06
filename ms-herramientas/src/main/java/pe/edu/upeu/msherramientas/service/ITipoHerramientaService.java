package pe.edu.upeu.msherramientas.service;

import pe.edu.upeu.msherramientas.entity.TipoHerramientaEntity;

import java.util.List;

public interface ITipoHerramientaService {

    TipoHerramientaEntity crear(TipoHerramientaEntity entity);
    List<TipoHerramientaEntity> listar();
    TipoHerramientaEntity buscarPorId(Long id);
    TipoHerramientaEntity actualizar(Long id, TipoHerramientaEntity entity);
    void eliminar(Long id);

}
