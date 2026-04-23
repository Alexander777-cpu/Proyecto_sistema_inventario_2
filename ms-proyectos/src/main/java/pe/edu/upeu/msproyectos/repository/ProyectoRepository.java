package pe.edu.upeu.msproyectos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.edu.upeu.msproyectos.entity.ProyectoEntity;

import java.util.List;

public interface ProyectoRepository extends JpaRepository<ProyectoEntity, Long> {
    List<ProyectoEntity> findByNombreContainingIgnoreCase(String nombre);
    List<ProyectoEntity> findByDireccionContainingIgnoreCase(String direccion);
    List<ProyectoEntity> findByClienteId(Long clienteId);
    List<ProyectoEntity> findByHerramientaId(Long herramientaId);
    List<ProyectoEntity> findByAccesorioId(Long accesorioId);
}
