package pe.edu.upeu.msproyectos.mappers;

import org.springframework.stereotype.Component;
import pe.edu.upeu.msproyectos.entity.ProyectoEntity;
import pe.edu.upeu.msproyectos.entity.DetalleProyectoEntity;
import pe.edu.upeu.msproyectos.dtos.*;

import java.util.ArrayList;
import java.util.List;

@Component
public class ProyectoMapper {

    private final DetalleProyectoMapper detalleMapper;

    public ProyectoMapper(DetalleProyectoMapper detalleMapper) {
        this.detalleMapper = detalleMapper;
    }

    public ProyectoEntity toEntity(ProyectoRequest request) {
        ProyectoEntity entity = new ProyectoEntity();
        updateEntity(entity, request);
        return entity;
    }

    public void updateEntity(ProyectoEntity entity, ProyectoRequest request) {
        entity.setNombre(request.getNombre());
        entity.setDireccion(request.getDireccion());
        entity.setClienteId(request.getClienteId());

        // Mapeo sin Streams para los detalles
        if (request.getDetalles() != null) {
            entity.getDetalles().clear(); // Limpiamos la lista actual
            for (DetalleProyectoRequest dto : request.getDetalles()) {
                DetalleProyectoEntity detalle = detalleMapper.toEntity(dto);
                detalle.setProyecto(entity); // Vincular padre
                entity.getDetalles().add(detalle);
            }
        }
    }

    public ProyectoResponse toResponse(ProyectoEntity entity) {
        ProyectoResponse response = new ProyectoResponse();
        response.setId(entity.getId());
        response.setNombre(entity.getNombre());
        response.setDireccion(entity.getDireccion());
        response.setClienteId(entity.getClienteId());

        // Mapeo de la lista de detalles sin Streams
        List<DetalleProyectoResponse> detallesResponse = new ArrayList<>();
        if (entity.getDetalles() != null) {
            for (DetalleProyectoEntity detalle : entity.getDetalles()) {
                detallesResponse.add(detalleMapper.toResponse(detalle));
            }
        }
        response.setDetalles(detallesResponse);

        return response;
    }
}