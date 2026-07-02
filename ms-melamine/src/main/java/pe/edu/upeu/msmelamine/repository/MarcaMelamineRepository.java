
package pe.edu.upeu.msmelamine.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import pe.edu.upeu.msmelamine.entity.MarcaMelamineEntity;
import java.util.List;

public interface MarcaMelamineRepository extends JpaRepository<MarcaMelamineEntity, Long> {
    List<MarcaMelamineEntity> findByNombreContainingIgnoreCase(String nombre);
    boolean existsByNombreIgnoreCase(String nombre);
}