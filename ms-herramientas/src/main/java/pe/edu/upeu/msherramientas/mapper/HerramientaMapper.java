package pe.edu.upeu.msherramientas.mapper;

import org.springframework.stereotype.Component;
import pe.edu.upeu.msherramientas.dto.HerramientaRequest;
import pe.edu.upeu.msherramientas.dto.HerramientaResponse;
import pe.edu.upeu.msherramientas.entity.HerramientaEntity;

@Component
public class HerramientaMapper {

    public HerramientaEntity toEntity(HerramientaRequest request) {
        HerramientaEntity entity = new HerramientaEntity();
        entity.setNombre(request.getNombre());
        entity.setTipo(request.getTipo());
        entity.setMarca(request.getMarca());
        entity.setEstado(request.getEstado());
        entity.setCompra(request.getCompra());
        entity.setVidaUtil(request.getVidaUtil());
        return entity;
    }

    public HerramientaResponse toResponse(HerramientaEntity entity) {
        return new HerramientaResponse(
                entity.getId(),
                entity.getNombre(),
                entity.getTipo(),
                entity.getMarca(),
                entity.getEstado(),
                entity.getCompra(),
                entity.getVidaUtil()
        );
    }

    public void updateEntity(HerramientaEntity entity, HerramientaRequest request) {
        entity.setNombre(request.getNombre());
        entity.setTipo(request.getTipo());
        entity.setMarca(request.getMarca());
        entity.setEstado(request.getEstado());
        entity.setCompra(request.getCompra());
        entity.setVidaUtil(request.getVidaUtil());
    }
}