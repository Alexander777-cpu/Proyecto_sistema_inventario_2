package pe.edu.upeu.msproyectos.mappers;

import org.springframework.stereotype.Component;
import pe.edu.upeu.msproyectos.entity.ProyectoEntity;
import pe.edu.upeu.msproyectos.dtos.ProyectoRequest;
import pe.edu.upeu.msproyectos.dtos.ProyectoResponse;

@Component
public class ProyectoMapper {

    public ProyectoEntity toEntity(ProyectoRequest request) {
        ProyectoEntity entity = new ProyectoEntity();
        entity.setNombre(request.getNombre());
        entity.setDireccion(request.getDireccion());
        entity.setClienteId(request.getClienteId());
        entity.setAccesorioId(request.getAccesorioId());
        entity.setHerramientaId(request.getHerramientaId());
        return entity;
    }

    public ProyectoResponse toResponse(ProyectoEntity entity) {
        return new ProyectoResponse(
                entity.getId(),
                entity.getNombre(),
                entity.getDireccion(),
                entity.getClienteId(),
                entity.getAccesorioId(),
                entity.getHerramientaId()
        );
    }

    public void updateEntity(ProyectoEntity entity, ProyectoRequest request) {
        entity.setNombre(request.getNombre());
        entity.setDireccion(request.getDireccion());
        entity.setClienteId(request.getClienteId());
        entity.setAccesorioId(request.getAccesorioId());
        entity.setHerramientaId(request.getHerramientaId());
    }
}
