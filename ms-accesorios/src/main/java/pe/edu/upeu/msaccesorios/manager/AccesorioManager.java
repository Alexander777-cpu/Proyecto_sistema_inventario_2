package pe.edu.upeu.msaccesorios.manager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pe.edu.upeu.msaccesorios.client.CategoriaClient;
import pe.edu.upeu.msaccesorios.client.EstadoClient;


@Component
public class AccesorioManager implements IAccesorioManager {

    private final EstadoClient estadoClient;
    // SOLO SE MODIFICÓ ESTO: Inyectamos el cliente Feign de categorías
    private final CategoriaClient categoriaClient;

    @Autowired
    public AccesorioManager(EstadoClient estadoClient, CategoriaClient categoriaClient) {
        this.estadoClient = estadoClient;
        this.categoriaClient = categoriaClient;
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

    // SOLO SE AGREGÓ ESTO: Implementación de la validación de categoría externa
    @Override
    public String validarCategoriaExterna(Long categoriaId) throws Exception {
        try {
            // Llamamos a ms-categorias vía Feign
            categoriaClient.buscarPorId(categoriaId);
            return "OK";
        } catch (Exception ex) {
            throw new Exception("Categoría no válida o no encontrada: " + ex.getMessage());
        }
    }
}