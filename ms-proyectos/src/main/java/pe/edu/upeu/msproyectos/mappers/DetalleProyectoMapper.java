package pe.edu.upeu.msproyectos.mappers;

import org.springframework.stereotype.Component;
import pe.edu.upeu.msproyectos.entity.DetalleProyectoEntity;
import pe.edu.upeu.msproyectos.dtos.DetalleProyectoRequest;
import pe.edu.upeu.msproyectos.dtos.DetalleProyectoResponse;

@Component
public class DetalleProyectoMapper {

    public DetalleProyectoEntity toEntity(DetalleProyectoRequest request) {
        DetalleProyectoEntity entity = new DetalleProyectoEntity();

        // Mapeo de Herramienta y su cantidad
        entity.setHerramientaId(request.getHerramientaId());
        entity.setCantidadHerramienta(request.getCantidadHerramienta() != null ? request.getCantidadHerramienta() : 0L);

        // Mapeo de Accesorio y su cantidad
        entity.setAccesorioId(request.getAccesorioId());
        entity.setCantidadAccesorio(request.getCantidadAccesorio() != null ? request.getCantidadAccesorio() : 0L);

        // Mapeo de Melamine y su cantidad
        entity.setMelamineId(request.getMelamineId());
        entity.setCantidadMelamine(request.getCantidadMelamine() != null ? request.getCantidadMelamine() : 0L);

        return entity;
    }

    public DetalleProyectoResponse toResponse(DetalleProyectoEntity entity) {
        return new DetalleProyectoResponse(
                entity.getId(),
                entity.getHerramientaId(),
                entity.getCantidadHerramienta(),
                entity.getAccesorioId(),
                entity.getCantidadAccesorio(),
                entity.getMelamineId(),
                entity.getCantidadMelamine()
        );
    }
}