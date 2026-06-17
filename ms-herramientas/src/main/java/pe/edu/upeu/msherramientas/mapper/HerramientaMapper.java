package pe.edu.upeu.msherramientas.mapper;

import org.springframework.stereotype.Component;
import pe.edu.upeu.msherramientas.dto.HerramientaRequest;
import pe.edu.upeu.msherramientas.dto.HerramientaResponse;
import pe.edu.upeu.msherramientas.entity.HerramientaEntity;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

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
        entity.setFechaInicio(request.getFechaInicio());
        entity.setImagenUrl(request.getImagenUrl());
        return entity;
    }

    public HerramientaResponse toResponse(HerramientaEntity entity) {
        long diasRestantes = 0L;

        if (entity.getFechaInicio() != null && entity.getVidaUtil() != null) {
            LocalDate fechaVencimiento = entity.getFechaInicio().plusMonths(entity.getVidaUtil());
            diasRestantes = ChronoUnit.DAYS.between(LocalDate.now(), fechaVencimiento);
        }

        return new HerramientaResponse(
                entity.getId(),
                entity.getNombre(),
                entity.getTipo(),
                entity.getMarca(),
                entity.getEstado(),
                entity.getCompra(),
                entity.getFechaInicio(),
                entity.getVidaUtil(),
                entity.getImagenUrl(),
                diasRestantes
        );
    }

    public void updateEntity(HerramientaEntity entity, HerramientaRequest request) {
        entity.setNombre(request.getNombre());
        entity.setTipo(request.getTipo());
        entity.setMarca(request.getMarca());
        entity.setEstado(request.getEstado());
        entity.setCompra(request.getCompra());
        entity.setVidaUtil(request.getVidaUtil());
        entity.setFechaInicio(request.getFechaInicio());
        entity.setImagenUrl(request.getImagenUrl());
    }
}