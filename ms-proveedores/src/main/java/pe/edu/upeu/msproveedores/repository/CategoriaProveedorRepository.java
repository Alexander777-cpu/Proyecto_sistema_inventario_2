package pe.edu.upeu.msproveedores.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.edu.upeu.msproveedores.entity.CategoriaProveedorEntity;

import java.util.List;

@Repository
public interface CategoriaProveedorRepository extends JpaRepository<CategoriaProveedorEntity, Long> {

    // Busca categorías que contengan el texto, ignorando mayúsculas/minúsculas
    List<CategoriaProveedorEntity> findByNombreContainingIgnoreCase(String nombre);

    // Verifica si una categoría ya existe para evitar duplicados
    boolean existsByNombreIgnoreCase(String nombre);
}