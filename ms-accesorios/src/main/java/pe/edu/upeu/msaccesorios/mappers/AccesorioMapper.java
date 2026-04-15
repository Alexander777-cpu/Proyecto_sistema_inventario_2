package pe.edu.upeu.msaccesorios.mappers;

import org.springframework.stereotype.Component;
import pe.edu.upeu.msaccesorios.dtos.AccesorioRequest;
import pe.edu.upeu.msaccesorios.dtos.AccesorioResponse;
import pe.edu.upeu.msaccesorios.entity.AccesorioEntity;

@Component
public class AccesorioMapper {

    public AccesorioEntity toEntity(AccesorioRequest request) {
        AccesorioEntity entity = new AccesorioEntity();
        entity.setNombre(request.getNombre());
        return entity;
    }

    public AccesorioResponse toResponse(AccesorioEntity entity) {
        return new AccesorioResponse(
                entity.getId(),
                entity.getNombre()
        );
    }

    public void updateEntity(AccesorioEntity entity, AccesorioRequest request) {
        entity.setNombre(request.getNombre());
    }
}
