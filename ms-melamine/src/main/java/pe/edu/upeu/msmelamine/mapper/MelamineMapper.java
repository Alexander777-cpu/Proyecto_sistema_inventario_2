package pe.edu.upeu.msmelamine.mapper;

import org.springframework.stereotype.Component;
import pe.edu.upeu.msmelamine.dto.MelamineRequest;
import pe.edu.upeu.msmelamine.dto.MelamineResponse;
import pe.edu.upeu.msmelamine.entity.ColorMelamineEntity;
import pe.edu.upeu.msmelamine.entity.EstadoMelamineEntity;
import pe.edu.upeu.msmelamine.entity.MarcaMelamineEntity;
import pe.edu.upeu.msmelamine.entity.MelamineEntity;

@Component
public class MelamineMapper {

    public MelamineEntity toEntity(MelamineRequest request) {
        MelamineEntity entity = new MelamineEntity();
        entity.setNombre(request.getNombre());
        entity.setAncho(request.getAncho());
        entity.setLargo(request.getLargo());
        entity.setImagenUrl(request.getImagenUrl());

        ColorMelamineEntity color = new ColorMelamineEntity();
        color.setId(request.getColorId());
        entity.setColor(color);

        MarcaMelamineEntity marca = new MarcaMelamineEntity();
        marca.setId(request.getMarcaId());
        entity.setMarca(marca);

        EstadoMelamineEntity estado = new EstadoMelamineEntity();
        estado.setId(request.getEstadoId());
        entity.setEstado(estado);

        return entity;
    }

    public MelamineResponse toResponse(MelamineEntity entity) {
        return new MelamineResponse(
                entity.getId(),
                entity.getNombre(),
                entity.getAncho(),
                entity.getLargo(),
                entity.getColor() != null ? entity.getColor().getId() : null,
                entity.getColor() != null ? entity.getColor().getNombre() : null,
                entity.getMarca() != null ? entity.getMarca().getId() : null,
                entity.getMarca() != null ? entity.getMarca().getNombre() : null,
                entity.getEstado() != null ? entity.getEstado().getId() : null,
                entity.getEstado() != null ? entity.getEstado().getNombre() : null,
                entity.getImagenUrl()
        );
    }

    public void updateEntity(MelamineEntity entity, MelamineRequest request) {
        entity.setNombre(request.getNombre());
        entity.setAncho(request.getAncho());
        entity.setLargo(request.getLargo());
        entity.setImagenUrl(request.getImagenUrl());

        ColorMelamineEntity color = new ColorMelamineEntity();
        color.setId(request.getColorId());
        entity.setColor(color);

        MarcaMelamineEntity marca = new MarcaMelamineEntity();
        marca.setId(request.getMarcaId());
        entity.setMarca(marca);

        EstadoMelamineEntity estado = new EstadoMelamineEntity();
        estado.setId(request.getEstadoId());
        entity.setEstado(estado);
    }
}