package pe.edu.upeu.msmelamine.service;

import pe.edu.upeu.msmelamine.entity.MarcaMelamineEntity;
import java.util.List;

public interface IMarcaMelamineService {
    MarcaMelamineEntity crear(MarcaMelamineEntity entity);
    List<MarcaMelamineEntity> listar();
    MarcaMelamineEntity buscarPorId(Long id);
    MarcaMelamineEntity actualizar(Long id, MarcaMelamineEntity entity);
    void eliminar(Long id);
}