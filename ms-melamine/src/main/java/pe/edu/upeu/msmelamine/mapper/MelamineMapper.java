package pe.edu.upeu.msmelamine.mapper;

import org.springframework.stereotype.Component;
import pe.edu.upeu.msmelamine.dto.MelamineRequest;
import pe.edu.upeu.msmelamine.dto.MelamineResponse;
import pe.edu.upeu.msmelamine.entity.MelamineEntity;

@Component

public class MelamineMapper {

    public MelamineEntity toEntity(MelamineRequest request) {
        MelamineEntity entity = new MelamineEntity();
        // Se agregaron nombre e imagenUrl
        entity.setNombre(request.getNombre());
        entity.setAncho(request.getAncho());
        entity.setLargo(request.getLargo());
        entity.setColor(request.getColor());
        entity.setMarca(request.getMarca());
        entity.setEstadoId(request.getEstadoId());
        entity.setImagenUrl(request.getImagenUrl());
        return entity;
    }

    public MelamineResponse toResponse(MelamineEntity entity) {
        // Usamos el constructor para mantener el mismo estilo de HerramientaMapper
        return new MelamineResponse(
                entity.getId(),
                entity.getNombre(),
                entity.getAncho(),
                entity.getLargo(),
                entity.getColor(),
                entity.getMarca(),
                entity.getEstadoId(),
                null, // Se llenará en el Service con Feign
                entity.getImagenUrl()
        );
    }

    public void updateEntity(MelamineEntity entity, MelamineRequest request) {
        // Se agregaron nombre e imagenUrl
        entity.setNombre(request.getNombre());
        entity.setAncho(request.getAncho());
        entity.setLargo(request.getLargo());
        entity.setColor(request.getColor());
        entity.setMarca(request.getMarca());
        entity.setEstadoId(request.getEstadoId());
        entity.setImagenUrl(request.getImagenUrl());
    }
}
