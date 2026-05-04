package pe.edu.upeu.msaccesorios.mappers;

import org.springframework.stereotype.Component;
import pe.edu.upeu.msaccesorios.dto.AccesorioRequest;
import pe.edu.upeu.msaccesorios.dto.AccesorioResponse;
import pe.edu.upeu.msaccesorios.entity.AccesorioEntity;
import pe.edu.upeu.msaccesorios.errors.ValidationException;

import java.util.LinkedHashMap;
import java.util.Map;

@Component
public class AccesorioMapper {

    public AccesorioEntity toEntity(AccesorioRequest request) {
        Map<String, String> errores = validarRangos(request);
        if (!errores.isEmpty()) {
            throw new ValidationException(errores);
        }
        AccesorioEntity entity = new AccesorioEntity();
        entity.setNombre(request.getNombre());
        entity.setDescripcion(request.getDescripcion());
        entity.setPrecio(request.getPrecioAsDouble());
        entity.setStock(request.getStockAsInteger());
        entity.setCategoria(request.getCategoria());
        entity.setMarca(request.getMarca());
        return entity;
    }

    public AccesorioResponse toResponse(AccesorioEntity entity) {
        return new AccesorioResponse(
                entity.getId(),
                entity.getNombre(),
                entity.getDescripcion(),
                entity.getPrecio(),
                entity.getStock(),
                entity.getCategoria(),
                entity.getMarca(),
                entity.getEstado()
        );
    }

    public void updateEntity(AccesorioEntity entity, AccesorioRequest request) {
        Map<String, String> errores = validarRangos(request);
        if (!errores.isEmpty()) {
            throw new ValidationException(errores);
        }
        entity.setNombre(request.getNombre());
        entity.setDescripcion(request.getDescripcion());
        entity.setPrecio(request.getPrecioAsDouble());
        entity.setStock(request.getStockAsInteger());
        entity.setCategoria(request.getCategoria());
        entity.setMarca(request.getMarca());
    }

    private Map<String, String> validarRangos(AccesorioRequest request) {
        Map<String, String> errores = new LinkedHashMap<>();

        if (request.getNombre() != null && request.getNombre().matches(".*\\d.*")) {
            errores.put("nombre", "El nombre solo debe contener letras, no números");
        }

        if (request.getDescripcion() != null && request.getDescripcion().matches(".*\\d.*")) {
            errores.put("descripcion", "La descripción solo debe contener letras, no números");
        }

        if (request.getPrecio() != null && !request.getPrecio().matches("^-?\\d+(\\.\\d+)?$")) {
            errores.put("precio", "El precio debe ser un número decimal o entero");
        }

        if (request.getStock() != null && !request.getStock().matches("^-?\\d+$")) {
            errores.put("stock", "El stock debe ser un número entero");
        }

        if (request.getCategoria() != null && request.getCategoria().matches(".*\\d.*")) {
            errores.put("categoria", "La categoría solo debe contener letras, no números");
        }

        if (request.getMarca() != null && request.getMarca().matches(".*\\d.*")) {
            errores.put("marca", "La marca solo debe contener letras, no números");
        }

        if (request.getPrecio() != null && request.getPrecio().matches("^-?\\d+(\\.\\d+)?$")) {
            double precio = Double.parseDouble(request.getPrecio());
            if (precio <= 0) errores.put("precio", "El precio debe ser mayor a 0");
            else if (precio > 999.99) errores.put("precio", "El precio no puede exceder 999.99");
        }

        if (request.getStock() != null && request.getStock().matches("^-?\\d+$")) {
            int stock = Integer.parseInt(request.getStock());
            if (stock < 1) errores.put("stock", "El stock debe ser mayor a 0");
            else if (stock > 1000) errores.put("stock", "El stock no puede exceder 1000 unidades");
        }
        return errores;
    }
}