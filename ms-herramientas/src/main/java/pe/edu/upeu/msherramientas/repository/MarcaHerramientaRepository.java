package pe.edu.upeu.msherramientas.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.edu.upeu.msherramientas.entity.MarcaHerramientaEntity;


import java.util.List;

public interface MarcaHerramientaRepository extends JpaRepository<MarcaHerramientaEntity, Long> {

    List<MarcaHerramientaEntity> findByNombreContainingIgnoreCase(String nombre);
    boolean existsByNombreIgnoreCase(String nombre);

}
