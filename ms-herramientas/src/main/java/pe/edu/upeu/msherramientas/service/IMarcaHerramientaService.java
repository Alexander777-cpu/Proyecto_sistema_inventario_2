package pe.edu.upeu.msherramientas.service;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.edu.upeu.msherramientas.entity.MarcaHerramientaEntity;

import java.util.List;

public interface IMarcaHerramientaService {

    MarcaHerramientaEntity crear(MarcaHerramientaEntity entity);
    List<MarcaHerramientaEntity> listar();
    MarcaHerramientaEntity buscarPorId(Long id);
    MarcaHerramientaEntity actualizar(Long id, MarcaHerramientaEntity entity);
    void eliminar(Long id);

}
