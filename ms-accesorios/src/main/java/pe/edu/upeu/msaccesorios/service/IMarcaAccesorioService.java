package pe.edu.upeu.msaccesorios.service;

import pe.edu.upeu.msaccesorios.entity.MarcaAccesorioEntity;

import java.util.List;

public interface IMarcaAccesorioService {

    MarcaAccesorioEntity crear(MarcaAccesorioEntity entity);

    List<MarcaAccesorioEntity> listar();

    MarcaAccesorioEntity buscarPorId(Long id);

    MarcaAccesorioEntity actualizar(Long id, MarcaAccesorioEntity entity);

    void eliminar(Long id);
}
