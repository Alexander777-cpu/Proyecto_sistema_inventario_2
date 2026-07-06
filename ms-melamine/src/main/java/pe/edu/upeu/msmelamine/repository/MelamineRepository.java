package pe.edu.upeu.msmelamine.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.edu.upeu.msmelamine.entity.MelamineEntity;

import java.math.BigDecimal;
import java.util.List;

public interface MelamineRepository extends JpaRepository<MelamineEntity, Long> {
    List<MelamineEntity> findByNombreContainingIgnoreCase(String nombre);

    boolean existsByNombreIgnoreCase(String nombre);

    List<MelamineEntity> findByAnchoAndLargo(BigDecimal ancho, BigDecimal largo);

    List<MelamineEntity> findByEstadoId(Long estadoId);
}