package pe.edu.upeu.msaccesorios.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.edu.upeu.msaccesorios.entity.EstadoAccesorioEntity;

import java.util.List;

public interface EstadoAccesorioRepository extends JpaRepository<EstadoAccesorioEntity, Long> {

    List<EstadoAccesorioEntity> findByNombreContainingIgnoreCase(String nombre);

    boolean existsByNombreIgnoreCase(String nombre);

}
