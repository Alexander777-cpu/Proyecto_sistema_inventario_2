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

        entity.setMarcaId(request.getMarcaId());
        entity.setEstadoId(request.getEstadoId());
        entity.setImagenUrl(request.getImagenUrl());
        return entity;
    }

    public AccesorioResponse toResponse(AccesorioEntity entity) {
        return new AccesorioResponse(
                entity.getId(),
                entity.getNombre(),
                entity.getDescripcion(),
                entity.getPrecio(),
                entity.getStock(),

                // Agregados los 4 campos que faltaban para cumplir con el constructor:
                entity.getMarcaId(),
                null, // marcaNombre: se llena en el AccesorioService

                entity.getEstadoId(),
                null, // estadoNombre: se llena en el AccesorioService

                entity.getImagenUrl()
        );
    }

    public void updateEntity(AccesorioEntity entity, AccesorioRequest request) {
        entity.setNombre(request.getNombre());
        entity.setDescripcion(request.getDescripcion());
        entity.setPrecio(request.getPrecio());
        entity.setStock(request.getStock());

        entity.setMarcaId(request.getMarcaId());
        entity.setEstadoId(request.getEstadoId());
        entity.setImagenUrl(request.getImagenUrl());
    }
}