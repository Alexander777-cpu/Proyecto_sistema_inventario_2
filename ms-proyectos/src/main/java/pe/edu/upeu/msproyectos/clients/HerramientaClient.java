package pe.edu.upeu.msproyectos.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import pe.edu.upeu.msproyectos.dtos.HerramientaResponseDTO;

@FeignClient(name = "ms-herramientas")
public interface HerramientaClient {

    @GetMapping("/api/herramientas/{id}")
    HerramientaResponseDTO buscarPorId(@PathVariable Long id);
}
