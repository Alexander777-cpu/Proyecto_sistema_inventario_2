package pe.edu.upeu.msproyectos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.edu.upeu.msproyectos.entity.DetalleProyectoEntity;
import java.util.List;

public interface DetalleProyectoRepository extends JpaRepository<DetalleProyectoEntity, Long> {
    // Buscar proyectos que contengan cierta herramienta, accesorio o melamine
    List<DetalleProyectoEntity> findByHerramientaId(Long herramientaId);
    List<DetalleProyectoEntity> findByAccesorioId(Long accesorioId);
    List<DetalleProyectoEntity> findByMelamineId(Long melamineId);

    // Buscar todos los detalles de un proyecto específico
    List<DetalleProyectoEntity> findByProyectoId(Long proyectoId);
}