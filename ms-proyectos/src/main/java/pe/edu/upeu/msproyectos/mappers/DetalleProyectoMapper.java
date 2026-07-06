package pe.edu.upeu.msproyectos.mappers;

import org.springframework.stereotype.Component;
import pe.edu.upeu.msproyectos.entity.DetalleProyectoEntity;
import pe.edu.upeu.msproyectos.dtos.DetalleProyectoRequest;
import pe.edu.upeu.msproyectos.dtos.DetalleProyectoResponse;

@Component
public class DetalleProyectoMapper {

    public DetalleProyectoEntity toEntity(DetalleProyectoRequest request) {
        DetalleProyectoEntity entity = new DetalleProyectoEntity();
        entity.setHerramientaId(request.getHerramientaId());
        entity.setAccesorioId(request.getAccesorioId());
        entity.setMelamineId(request.getMelamineId());
        return entity;
    }

    public DetalleProyectoResponse toResponse(DetalleProyectoEntity entity) {
        return new DetalleProyectoResponse(
                entity.getId(),
                entity.getHerramientaId(),
                entity.getAccesorioId(),
                entity.getMelamineId()
        );
    }
}