package pe.edu.upeu.msaccesorios.mappers;

import org.springframework.stereotype.Component;
import pe.edu.upeu.msaccesorios.dto.AccesorioRequest;
import pe.edu.upeu.msaccesorios.dto.AccesorioResponse;
import pe.edu.upeu.msaccesorios.entity.AccesorioEntity;
import pe.edu.upeu.msaccesorios.entity.EstadoAccesorioEntity;
import pe.edu.upeu.msaccesorios.entity.MarcaAccesorioEntity;

@Component
public class AccesorioMapper {

    public AccesorioEntity toEntity(AccesorioRequest request) {
        AccesorioEntity entity = new AccesorioEntity();
        entity.setNombre(request.getNombre());
        entity.setDescripcion(request.getDescripcion());
        entity.setPrecio(request.getPrecio());
        entity.setStock(request.getStock());
        entity.setImagenUrl(request.getImagenUrl());

        MarcaAccesorioEntity marca = new MarcaAccesorioEntity();
        marca.setId(request.getMarcaId());
        entity.setMarca(marca);

        EstadoAccesorioEntity estado = new EstadoAccesorioEntity();
        estado.setId(request.getEstadoId());
        entity.setEstado(estado);

        return entity;
    }

    public AccesorioResponse toResponse(AccesorioEntity entity) {
        return new AccesorioResponse(
                entity.getId(),
                entity.getNombre(),
                entity.getDescripcion(),
                entity.getPrecio(),
                entity.getStock(),
                entity.getMarca() != null ? entity.getMarca().getId() : null,
                entity.getMarca() != null ? entity.getMarca().getNombre() : null,
                entity.getEstado() != null ? entity.getEstado().getId() : null,
                entity.getEstado() != null ? entity.getEstado().getNombre() : null,
                entity.getImagenUrl()
        );
    }

    public void updateEntity(AccesorioEntity entity, AccesorioRequest request) {
        entity.setNombre(request.getNombre());
        entity.setDescripcion(request.getDescripcion());
        entity.setPrecio(request.getPrecio());
        entity.setStock(request.getStock());
        entity.setImagenUrl(request.getImagenUrl());

        MarcaAccesorioEntity marca = new MarcaAccesorioEntity();
        marca.setId(request.getMarcaId());
        entity.setMarca(marca);

        EstadoAccesorioEntity estado = new EstadoAccesorioEntity();
        estado.setId(request.getEstadoId());
        entity.setEstado(estado);
    }
}