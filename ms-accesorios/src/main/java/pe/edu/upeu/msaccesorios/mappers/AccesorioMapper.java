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
        // SOLO SE MODIFICÓ ESTO: Ahora recibe el categoriaId (Long)
        entity.setCategoriaId(request.getCategoriaId());
        entity.setMarca(request.getMarca());
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
                entity.getCategoriaId(),
                null, // categoriaNombre: Se llenará en el Service con Feign
                entity.getMarca(),
                entity.getEstadoId(),
                null, // estadoNombre: Se llenará en el Service con Feign
                entity.getImagenUrl()
        );
    }

    public void updateEntity(AccesorioEntity entity, AccesorioRequest request) {
        entity.setNombre(request.getNombre());
        entity.setDescripcion(request.getDescripcion());
        entity.setPrecio(request.getPrecio());
        entity.setStock(request.getStock());
        entity.setCategoriaId(request.getCategoriaId());
        entity.setMarca(request.getMarca());
        entity.setEstadoId(request.getEstadoId());
        entity.setImagenUrl(request.getImagenUrl());
    }
}