package pe.edu.upeu.msproyectos.services;

import pe.edu.upeu.msproyectos.dtos.ProyectoRequest;
import pe.edu.upeu.msproyectos.dtos.ProyectoResponse;

import java.util.List;

public interface IProyectoService {
    List<ProyectoResponse> listarTodos();
    ProyectoResponse buscarPorId(Long id);
    ProyectoResponse crear(ProyectoRequest request);
    ProyectoResponse actualizar(Long id, ProyectoRequest request);
    void eliminar(Long id);
}