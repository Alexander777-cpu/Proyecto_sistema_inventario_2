package pe.edu.upeu.msherramientas.service;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.edu.upeu.msherramientas.entity.EstadoHerramientaEntity;

import java.util.List;

public interface IEstadoHerramientaService {

    EstadoHerramientaEntity crear(EstadoHerramientaEntity entity);
    List<EstadoHerramientaEntity> listar();
    EstadoHerramientaEntity buscarPorId(Long id);
    EstadoHerramientaEntity actualizar(Long id, EstadoHerramientaEntity entity);
    void eliminar(Long id);

}
