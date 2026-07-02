package pe.edu.upeu.msaccesorios.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "marca_accesorios")

public class MarcaAccesorioEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String nombre;


    public MarcaAccesorioEntity() {
    }


    public MarcaAccesorioEntity(Long id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    // Getters y Setters
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
