package pe.edu.upeu.msherramientas.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.edu.upeu.msherramientas.entity.HerramientaEntity;

import java.util.List;

public interface HerramientaRepository extends JpaRepository <HerramientaEntity, Long>  {
    List<HerramientaEntity> findByNombreContainingIgnoreCase(String nombre);

    boolean existsByNombreIgnoreCase(String nombre);
}
