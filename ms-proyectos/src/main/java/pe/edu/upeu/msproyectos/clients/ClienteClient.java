package pe.edu.upeu.msproyectos.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import pe.edu.upeu.msproyectos.dtos.ClienteResponseDTO;

@FeignClient(name = "ms-clientes")
public interface ClienteClient {

    @GetMapping("/api/clientes/{id}")
    ClienteResponseDTO buscarPorId(@PathVariable Long id);
}
