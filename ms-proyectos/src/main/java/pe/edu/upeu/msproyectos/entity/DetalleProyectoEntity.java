package pe.edu.upeu.msproyectos.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

@Entity
@Table(name = "detalleproyecto")
public class DetalleProyectoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "HERRAMIENTA_ID", nullable = true)
    private Long herramientaId;

    @Column(name = "ACCESORIO_ID", nullable = true)
    private Long accesorioId;

    @Column(name = "MELAMINE_ID", nullable = true)
    private Long melamineId;

    @ManyToOne
    @JoinColumn(name = "proyecto_id")
    @JsonIgnore // Evita recursión infinita
    private ProyectoEntity proyecto;

    public DetalleProyectoEntity() {
    }

    public DetalleProyectoEntity(Long id, Long herramientaId, Long accesorioId, Long melamineId, ProyectoEntity proyecto) {
        this.id = id;
        this.herramientaId = herramientaId;
        this.accesorioId = accesorioId;
        this.melamineId = melamineId;
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
