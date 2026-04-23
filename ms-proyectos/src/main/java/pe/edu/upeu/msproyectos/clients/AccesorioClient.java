package pe.edu.upeu.msproyectos.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import pe.edu.upeu.msproyectos.dtos.AccesorioResponseDTO;

@FeignClient(name = "ms-accesorios")
public interface AccesorioClient {

    @GetMapping("/api/accesorios/{id}")
    AccesorioResponseDTO buscarPorId(@PathVariable Long id);
}
