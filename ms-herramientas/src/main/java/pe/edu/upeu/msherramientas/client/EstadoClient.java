package pe.edu.upeu.msherramientas.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import pe.edu.upeu.msherramientas.dto.EstadoResponse;

@FeignClient(name = "ms-estado")

public interface EstadoClient {

    @GetMapping("/api/estado/{id}")
    EstadoResponse buscarPorId(@PathVariable("id") Long id);

}
