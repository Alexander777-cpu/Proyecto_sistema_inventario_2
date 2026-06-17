package pe.edu.upeu.msherramientas.manager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pe.edu.upeu.msherramientas.client.EstadoClient;

@Component
public class HerramientaManager implements IHerramientaManager {

    private final EstadoClient estadoClient;

    @Autowired
    public HerramientaManager(EstadoClient estadoClient) {
        this.estadoClient = estadoClient;
    }

    @Override
    public String validarEstadoExterno(Long estadoId) throws Exception {
        try {
            // Llamamos a ms-estado vía Feign
            estadoClient.buscarPorId(estadoId);
            return "OK";
        } catch (Exception ex) {
            throw new Exception("Estado no válido o no encontrado: " + ex.getMessage());
        }
    }

}
