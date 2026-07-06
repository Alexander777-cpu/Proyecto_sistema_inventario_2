package pe.edu.upeu.msproyectos.dtos;


import jakarta.validation.constraints.NotNull;
import pe.edu.upeu.msproyectos.entity.ProyectoEntity;

public class DetalleProyectoRequest {

    private Long herramientaId;
    private Long cantidadHerramienta;
    private Long accesorioId;
    private Long cantidadAccesorio;
    private Long melamineId;
    private Long cantidadMelamine;

    private ProyectoEntity proyecto;

    public DetalleProyectoRequest() {
    }

    public ProyectoEntity getProyecto() {
        return proyecto;
    }

    public void setProyecto(ProyectoEntity proyecto) {
        this.proyecto = proyecto;
    }

    public Long getCantidadMelamine() {
        return cantidadMelamine;
    }

    public void setCantidadMelamine(Long cantidadMelamine) {
        this.cantidadMelamine = cantidadMelamine;
    }

    public Long getMelamineId() {
        return melamineId;
    }

    public void setMelamineId(Long melamineId) {
        this.melamineId = melamineId;
    }

    public Long getCantidadAccesorio() {
        return cantidadAccesorio;
    }

    public void setCantidadAccesorio(Long cantidadAccesorio) {
        this.cantidadAccesorio = cantidadAccesorio;
    }

    public Long getAccesorioId() {
        return accesorioId;
    }

    public void setAccesorioId(Long accesorioId) {
        this.accesorioId = accesorioId;
    }

    public Long getCantidadHerramienta() {
        return cantidadHerramienta;
    }

    public void setCantidadHerramienta(Long cantidadHerramienta) {
        this.cantidadHerramienta = cantidadHerramienta;
    }

    public Long getHerramientaId() {
        return herramientaId;
    }

    public void setHerramientaId(Long herramientaId) {
        this.herramientaId = herramientaId;
    }
}