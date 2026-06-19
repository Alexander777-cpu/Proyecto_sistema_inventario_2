package pe.edu.upeu.msmelamine.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.edu.upeu.msmelamine.entity.MelamineEntity;

import java.util.List;

@Repository
public interface MelamineRepository extends JpaRepository<MelamineEntity, Long> {
    List<MelamineEntity> findByNombreContainingIgnoreCase(String nombre);

    boolean existsByNombreIgnoreCase(String nombre);
}
