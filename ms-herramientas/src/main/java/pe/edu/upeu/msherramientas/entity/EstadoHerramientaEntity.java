package pe.edu.upeu.msherramientas.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "estado_herramientas")
public class EstadoHerramientaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String nombre;

    public EstadoHerramientaEntity() {
    }

    public EstadoHerramientaEntity(Long id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

}
