package pe.edu.upeu.msmelamine.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import pe.edu.upeu.msmelamine.entity.EstadoMelamineEntity;
import java.util.List;

public interface EstadoMelamineRepository extends JpaRepository<EstadoMelamineEntity, Long> {
    List<EstadoMelamineEntity> findByNombreContainingIgnoreCase(String nombre);
    boolean existsByNombreIgnoreCase(String nombre);
}