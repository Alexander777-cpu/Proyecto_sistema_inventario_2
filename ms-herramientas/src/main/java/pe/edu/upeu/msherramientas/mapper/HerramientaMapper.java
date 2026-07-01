package pe.edu.upeu.msherramientas.mapper;

import org.springframework.stereotype.Component;
import pe.edu.upeu.msherramientas.dto.HerramientaRequest;
import pe.edu.upeu.msherramientas.dto.HerramientaResponse;
import pe.edu.upeu.msherramientas.entity.EstadoHerramientaEntity;
import pe.edu.upeu.msherramientas.entity.HerramientaEntity;
import pe.edu.upeu.msherramientas.entity.MarcaHerramientaEntity;
import pe.edu.upeu.msherramientas.entity.TipoHerramientaEntity;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

@Component
public class HerramientaMapper {

    public HerramientaEntity toEntity(HerramientaRequest request) {
        HerramientaEntity entity = new HerramientaEntity();
        entity.setNombre(request.getNombre());
        entity.setCompra(request.getCompra());
        entity.setVidaUtil(request.getVidaUtil());
        entity.setFechaInicio(request.getFechaInicio());
        entity.setImagenUrl(request.getImagenUrl());

        // Asignamos las relaciones solo con el ID
        TipoHerramientaEntity tipo = new TipoHerramientaEntity();
        tipo.setId(request.getTipoId());
        entity.setTipo(tipo);

        MarcaHerramientaEntity marca = new MarcaHerramientaEntity();
        marca.setId(request.getMarcaId());
        entity.setMarca(marca);

        EstadoHerramientaEntity estado = new EstadoHerramientaEntity();
        estado.setId(request.getEstadoId());
        entity.setEstado(estado);

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
                // Extraemos los nombres de las entidades relacionadas de forma segura
                entity.getTipo() != null ? entity.getTipo().getNombre() : null,
                entity.getMarca() != null ? entity.getMarca().getNombre() : null,
                entity.getEstado() != null ? entity.getEstado().getNombre() : null,
                entity.getCompra(),
                entity.getFechaInicio(),
                entity.getImagenUrl(),
                entity.getVidaUtil(),
                diasRestantes
        );
    }

    public void updateEntity(HerramientaEntity entity, HerramientaRequest request) {
        entity.setNombre(request.getNombre());
        entity.setCompra(request.getCompra());
        entity.setVidaUtil(request.getVidaUtil());
        entity.setFechaInicio(request.getFechaInicio());
        entity.setImagenUrl(request.getImagenUrl());

        TipoHerramientaEntity tipo = new TipoHerramientaEntity();
        tipo.setId(request.getTipoId());
        entity.setTipo(tipo);

        MarcaHerramientaEntity marca = new MarcaHerramientaEntity();
        marca.setId(request.getMarcaId());
        entity.setMarca(marca);

        EstadoHerramientaEntity estado = new EstadoHerramientaEntity();
        estado.setId(request.getEstadoId());
        entity.setEstado(estado);
    }
}