package pe.edu.upeu.msproveedores.service;

import pe.edu.upeu.msproveedores.entity.CategoriaProveedorEntity;

import java.util.List;

public interface ICategoriaProveedorService {
    CategoriaProveedorEntity crear(CategoriaProveedorEntity entity);

    List<CategoriaProveedorEntity> listar();

    CategoriaProveedorEntity buscarPorId(Long id);

    CategoriaProveedorEntity actualizar(Long id, CategoriaProveedorEntity entity);

    void eliminar(Long id);
}
