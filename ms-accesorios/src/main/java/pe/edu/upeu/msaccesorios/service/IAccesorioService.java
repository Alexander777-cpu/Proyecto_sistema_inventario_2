package pe.edu.upeu.msaccesorios.service;

import org.springframework.web.multipart.MultipartFile;
import pe.edu.upeu.msaccesorios.dto.AccesorioRequest;
import pe.edu.upeu.msaccesorios.dto.AccesorioResponse;


import java.util.List;

public interface IAccesorioService {
    AccesorioResponse crear(AccesorioRequest request, MultipartFile imagen) throws Exception;

    List<AccesorioResponse> listar();

    AccesorioResponse buscarPorId(Long id);

    List<AccesorioResponse> buscarPorNombre(String nombre);

    AccesorioResponse actualizar(Long id, AccesorioRequest request, MultipartFile imagen) throws Exception;

    void eliminar(Long id);
}
