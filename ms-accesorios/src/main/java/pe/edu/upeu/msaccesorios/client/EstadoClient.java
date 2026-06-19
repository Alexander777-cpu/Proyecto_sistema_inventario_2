package pe.edu.upeu.msaccesorios.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import pe.edu.upeu.msaccesorios.dto.EstadoResponse;

@FeignClient(name = "ms-estado")
@Component
public interface EstadoClient {
    @GetMapping("/api/estado/{id}")
    EstadoResponse buscarPorId(@PathVariable("id") Long id);
}
