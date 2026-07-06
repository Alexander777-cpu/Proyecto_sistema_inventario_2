package pe.edu.upeu.msproyectos.dtos;


import jakarta.validation.constraints.NotNull;
import pe.edu.upeu.msproyectos.entity.ProyectoEntity;

public class DetalleProyectoRequest {

    @NotNull(message = "La herramienta es obligatoria")
    private Long herramientaId;

    @NotNull(message = "El accesorio es obligatorio")
    private Long accesorioId;

    @NotNull(message = "El melamine es obligatorio")
    private Long melamineId;


    private ProyectoEntity proyecto;

    public DetalleProyectoRequest() {
    }

    public Long getHerramientaId() {
        return herramientaId;
    }

    public void setHerramientaId(Long herramientaId) {
        this.herramientaId = herramientaId;
    }

    public Long getAccesorioId() {
        return accesorioId;
    }

    public void setAccesorioId(Long accesorioId) {
        this.accesorioId = accesorioId;
    }

    public Long getMelamineId() {
        return melamineId;
    }

    public void setMelamineId(Long melamineId) {
        this.melamineId = melamineId;
    }

    public ProyectoEntity getProyecto() {
        return proyecto;
    }

    public void setProyecto(ProyectoEntity proyecto) {
        this.proyecto = proyecto;
    }
}
