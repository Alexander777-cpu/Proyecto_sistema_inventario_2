package pe.edu.upeu.msaccesorios.manager;

import pe.edu.upeu.msaccesorios.dto.AccesorioRequest;
import pe.edu.upeu.msaccesorios.dto.AccesorioResponse;

import java.util.List;

public interface IAccesorioManager {
    List<AccesorioResponse> listar();
    AccesorioResponse buscarPorId(Long id);
    AccesorioResponse crear(AccesorioRequest request);
    AccesorioResponse actualizar(Long id, AccesorioRequest request);
    void eliminar(Long id);
}