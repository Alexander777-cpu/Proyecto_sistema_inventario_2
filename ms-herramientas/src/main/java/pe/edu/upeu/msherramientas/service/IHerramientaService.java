package pe.edu.upeu.msherramientas.service;

import org.springframework.web.multipart.MultipartFile;
import pe.edu.upeu.msherramientas.dto.HerramientaRequest;
import pe.edu.upeu.msherramientas.dto.HerramientaResponse;

import java.util.List;

public interface IHerramientaService {

    HerramientaResponse crear(HerramientaRequest request, MultipartFile imagen) throws Exception;

    List<HerramientaResponse> listar();

    HerramientaResponse buscarPorId(Long id);

    List<HerramientaResponse> buscarPorNombre(String nombre);

    List<HerramientaResponse> buscarPorMarca(Long marcaId);

    List<HerramientaResponse> buscarPorEstado(Long estadoId);

    HerramientaResponse actualizar(Long id, HerramientaRequest request, MultipartFile imagen) throws Exception;

    void eliminar(Long id);

}