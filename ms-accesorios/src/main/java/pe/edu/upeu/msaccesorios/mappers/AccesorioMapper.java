package pe.edu.upeu.msaccesorios.mappers;

import org.springframework.stereotype.Component;
import pe.edu.upeu.msaccesorios.dto.AccesorioRequest;
import pe.edu.upeu.msaccesorios.dto.AccesorioResponse;
import pe.edu.upeu.msaccesorios.entity.AccesorioEntity;

@Component
public class AccesorioMapper {

    public AccesorioEntity toEntity(AccesorioRequest request) {
        AccesorioEntity entity = new AccesorioEntity();
        entity.setNombre(request.getNombre());
        entity.setDescripcion(request.getDescripcion());
        entity.setPrecio(request.getPrecio());
        entity.setStock(request.getStock());
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
        entity.setNombre(request.getNombre());
        entity.setDescripcion(request.getDescripcion());
        entity.setPrecio(request.getPrecio());
        entity.setStock(request.getStock());
        entity.setCategoria(request.getCategoria());
        entity.setMarca(request.getMarca());
    }
}