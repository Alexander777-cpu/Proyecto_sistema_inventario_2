package pe.edu.upeu.msproyectos.mappers;

import org.springframework.stereotype.Component;
import pe.edu.upeu.msproyectos.dtos.DetalleProyectoRequest;
import pe.edu.upeu.msproyectos.dtos.DetalleProyectoResponse;
import pe.edu.upeu.msproyectos.dtos.ProyectoRequest;
import pe.edu.upeu.msproyectos.dtos.ProyectoResponse;
import pe.edu.upeu.msproyectos.entity.DetalleProyectoEntity;
import pe.edu.upeu.msproyectos.entity.ProyectoEntity;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ProyectoMapper {

    // --- De Entidad a Response ---
    public ProyectoResponse toResponse(ProyectoEntity entity) {
        ProyectoResponse response = new ProyectoResponse();
        response.setId(entity.getId());
        response.setNombre(entity.getNombre());
        response.setDireccion(entity.getDireccion());
        response.setClienteId(entity.getClienteId());

        if (entity.getDetalles() != null) {
            List<DetalleProyectoResponse> detallesDto = entity.getDetalles().stream().map(det -> {
                DetalleProyectoResponse dto = new DetalleProyectoResponse();
                dto.setId(det.getId());
                dto.setHerramientaId(det.getHerramientaId());
                dto.setCantidadHerramienta(det.getCantidadHerramienta());
                dto.setAccesorioId(det.getAccesorioId());
                dto.setCantidadAccesorio(det.getCantidadAccesorio());
                dto.setMelamineId(det.getMelamineId());
                dto.setCantidadMelamine(det.getCantidadMelamine());
                return dto;
            }).collect(Collectors.toList());
            response.setDetalles(detallesDto);
        }
        return response;
    }

    // --- De Request a Entidad ---
    public ProyectoEntity toEntity(ProyectoRequest request) {
        ProyectoEntity entity = new ProyectoEntity();
        entity.setNombre(request.getNombre());
        entity.setDireccion(request.getDireccion());
        entity.setClienteId(request.getClienteId());

        if (request.getDetalles() != null) {
            List<DetalleProyectoEntity> detallesEntity = request.getDetalles().stream().map(dto -> {
                DetalleProyectoEntity det = new DetalleProyectoEntity();
                det.setHerramientaId(dto.getHerramientaId());
                // Asignamos 0L si viene nulo
                det.setCantidadHerramienta(dto.getCantidadHerramienta() != null ? dto.getCantidadHerramienta() : 0L);

                det.setAccesorioId(dto.getAccesorioId());
                det.setCantidadAccesorio(dto.getCantidadAccesorio() != null ? dto.getCantidadAccesorio() : 0L);

                det.setMelamineId(dto.getMelamineId());
                det.setCantidadMelamine(dto.getCantidadMelamine() != null ? dto.getCantidadMelamine() : 0L);
                return det;
            }).collect(Collectors.toList());

            // Usamos el setter personalizado que definiste para vincular el padre automáticamente
            entity.setDetalles(detallesEntity);
        }
        return entity;
    }
}