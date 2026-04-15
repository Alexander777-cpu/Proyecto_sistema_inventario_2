package pe.edu.upeu.msaccesorios.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "clientes")
public class AccesorioEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nombre", length = 20,  nullable = false)
    private String nombre;

    public AccesorioEntity() {
    }

    public AccesorioEntity(Long id, String nombre) {
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
