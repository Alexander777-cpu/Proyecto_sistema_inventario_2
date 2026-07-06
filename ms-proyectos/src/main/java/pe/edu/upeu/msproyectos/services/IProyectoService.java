package pe.edu.upeu.msproyectos.services;

import pe.edu.upeu.msproyectos.dtos.ProyectoRequest;
import pe.edu.upeu.msproyectos.dtos.ProyectoResponse;
import java.util.List;

public interface IProyectoService {
    ProyectoResponse crear(ProyectoRequest request);
    List<ProyectoResponse> listar();
    ProyectoResponse buscarPorId(Long id);
    ProyectoResponse actualizar(Long id, ProyectoRequest request);
    void eliminar(Long id);
}