package pe.edu.upeu.msherramientas.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.edu.upeu.msherramientas.entity.EstadoHerramientaEntity;

import java.util.List;

public interface EstadoHerramientaRepository extends JpaRepository<EstadoHerramientaEntity, Long> {

    List<EstadoHerramientaEntity> findByNombreContainingIgnoreCase(String nombre);
    boolean existsByNombreIgnoreCase(String nombre);

}
