package pe.edu.upeu.msaccesorios.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.edu.upeu.msaccesorios.entity.MarcaAccesorioEntity;

import java.util.List;

public interface MarcaAccesorioRepository extends JpaRepository<MarcaAccesorioEntity, Long> {

    List<MarcaAccesorioEntity> findByNombreContainingIgnoreCase(String nombre);

    boolean existsByNombreIgnoreCase(String nombre);

}
