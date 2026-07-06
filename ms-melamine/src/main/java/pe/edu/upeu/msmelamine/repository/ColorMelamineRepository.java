package pe.edu.upeu.msmelamine.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.edu.upeu.msmelamine.entity.ColorMelamineEntity;
import java.util.List;

public interface ColorMelamineRepository extends JpaRepository<ColorMelamineEntity, Long> {
    List<ColorMelamineEntity> findByNombreContainingIgnoreCase(String nombre);
    boolean existsByNombreIgnoreCase(String nombre);
}