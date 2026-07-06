package pe.edu.upeu.msmelamine.service;

import org.springframework.web.multipart.MultipartFile;
import pe.edu.upeu.msmelamine.dto.MelamineRequest;
import pe.edu.upeu.msmelamine.dto.MelamineResponse;

import java.math.BigDecimal;
import java.util.List;

public interface IMelamineService {

    MelamineResponse crear(MelamineRequest request, MultipartFile imagen) throws Exception;

    List<MelamineResponse> listar();

    MelamineResponse buscarPorId(Long id);

    List<MelamineResponse> buscarPorNombre(String nombre);

    List<MelamineResponse> buscarPorDimensiones(BigDecimal ancho, BigDecimal largo);

    List<MelamineResponse> buscarPorEstado(Long estadoId);

    MelamineResponse actualizar(Long id, MelamineRequest request, MultipartFile imagen) throws Exception;

    void eliminar(Long id);
}