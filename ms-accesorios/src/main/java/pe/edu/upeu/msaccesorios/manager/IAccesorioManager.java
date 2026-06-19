package pe.edu.upeu.msaccesorios.manager;

import pe.edu.upeu.msaccesorios.dto.AccesorioRequest;
import pe.edu.upeu.msaccesorios.dto.AccesorioResponse;

import java.util.List;

public interface IAccesorioManager {
    String validarEstadoExterno(Long estadoId) throws Exception;


    String validarCategoriaExterna(Long categoriaId) throws Exception;
}