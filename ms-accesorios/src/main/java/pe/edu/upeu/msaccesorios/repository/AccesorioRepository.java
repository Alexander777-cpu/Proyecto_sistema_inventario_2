package pe.edu.upeu.msaccesorios.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.edu.upeu.msaccesorios.entity.AccesorioEntity;
import java.util.List;
import java.util.Optional;


public interface AccesorioRepository extends JpaRepository<AccesorioEntity, Long> {
    List<AccesorioEntity> findByNombreContainingIgnoreCase(String nombre);

    List<AccesorioEntity> findByEstadoId(Long estadoId);


    List<AccesorioEntity> findByCategoriaId(Long categoriaId);

    List<AccesorioEntity> findByStockGreaterThan(Integer stock);

    Optional<AccesorioEntity> findByNombreIgnoreCase(String nombre);
}