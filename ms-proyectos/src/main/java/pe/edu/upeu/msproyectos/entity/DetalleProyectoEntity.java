package pe.edu.upeu.msproyectos.entity;

import jakarta.persistence.*;
import com.fasterxml.jackson.annotation.JsonIgnore;
import pe.edu.upeu.msproyectos.entity.ProyectoEntity;

@Entity
@Table(name = "detalleproyecto")
public class DetalleProyectoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "herramienta_id")
    private Long herramientaId;
    @Column(name = "cantidad_herramienta")
    private Long cantidadHerramienta = 0;

    @Column(name = "accesorio_id")
    private Long accesorioId;
    @Column(name = "cantidad_accesorio")
    private Long cantidadAccesorio = 0;

    @Column(name = "melamine_id")
    private Long melamineId;
    @Column(name = "cantidad_melamine")
    private Long cantidadMelamine = 0;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "proyecto_id")
    @JsonIgnore
    private ProyectoEntity proyecto;

    public DetalleProyectoEntity() {
    }

    public DetalleProyectoEntity(Long id, Long herramientaId, Long cantidadHerramienta, Long accesorioId, Long cantidadAccesorio, Long melamineId, Long cantidadMelamine, ProyectoEntity proyecto) {
        this.id = id;
        this.herramientaId = herramientaId;
        this.cantidadHerramienta = cantidadHerramienta;
        this.accesorioId = accesorioId;
        this.cantidadAccesorio = cantidadAccesorio;
        this.melamineId = melamineId;
        this.cantidadMelamine = cantidadMelamine;
        this.proyecto = proyecto;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getHerramientaId() {
        return herramientaId;
    }

    public void setHerramientaId(Long herramientaId) {
        this.herramientaId = herramientaId;
    }

    public Long getCantidadHerramienta() {
        return cantidadHerramienta;
    }

    public void setCantidadHerramienta(Long cantidadHerramienta) {
        this.cantidadHerramienta = cantidadHerramienta;
    }

    public Long getAccesorioId() {
        return accesorioId;
    }

    public void setAccesorioId(Long accesorioId) {
        this.accesorioId = accesorioId;
    }

    public Long getCantidadAccesorio() {
        return cantidadAccesorio;
    }

    public void setCantidadAccesorio(Long cantidadAccesorio) {
        this.cantidadAccesorio = cantidadAccesorio;
    }

    public Long getMelamineId() {
        return melamineId;
    }

    public void setMelamineId(Long melamineId) {
        this.melamineId = melamineId;
    }

    public Long getCantidadMelamine() {
        return cantidadMelamine;
    }

    public void setCantidadMelamine(Long cantidadMelamine) {
        this.cantidadMelamine = cantidadMelamine;
    }

    public ProyectoEntity getProyecto() {
        return proyecto;
    }

    public void setProyecto(ProyectoEntity proyecto) {
        this.proyecto = proyecto;
    }
}