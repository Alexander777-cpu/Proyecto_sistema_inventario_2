package pe.edu.upeu.msproveedores.service;

import org.springframework.web.multipart.MultipartFile;
import pe.edu.upeu.msproveedores.dto.ProveedorRequest;
import pe.edu.upeu.msproveedores.dto.ProveedorResponse;

import java.util.List;

public interface IProveedorService {
    ProveedorResponse crear(ProveedorRequest request, MultipartFile imagen) throws Exception;

    List<ProveedorResponse> listar();

    ProveedorResponse buscarPorId(Long id);

    List<ProveedorResponse> buscarPorCategoria(Long categoriaId);

    // Usamos el DTO para la actualización
    ProveedorResponse actualizar(Long id, ProveedorRequest request, MultipartFile imagen) throws Exception;

    void eliminar(Long id);

    List<ProveedorResponse> buscarPorNombre(String nombres);

}
