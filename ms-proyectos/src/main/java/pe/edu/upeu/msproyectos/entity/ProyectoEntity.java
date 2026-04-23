package pe.edu.upeu.msproyectos.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "proyectos")
public class ProyectoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "NOMBRE", nullable = false, length = 100)
    private String nombre;

    @Column(name = "DIRECCION", nullable = false, length = 150)
    private String direccion;

    @Column(name = "CLIENTE_ID", nullable = false)
    private Long clienteId;

    @Column(name = "HERRAMIENTA_ID", nullable = false)
    private Long herramientaId;

    @Column(name = "ACCESORIO_ID", nullable = false)
    private Long accesorioId;

    public ProyectoEntity() {
    }


    public ProyectoEntity(Long id, String nombre, String direccion, Long herramientaId, Long clienteId, Long accesorioId) {
        this.id = id;
        this.nombre = nombre;
        this.direccion = direccion;
        this.herramientaId = herramientaId;
        this.clienteId = clienteId;
        this.accesorioId = accesorioId;
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

    public void setNombre(String nombreProyecto) {
        this.nombre = nombreProyecto;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public Long getHerramientaId() {
        return herramientaId;
    }

    public void setHerramientaId(Long herramientaId) {
        this.herramientaId = herramientaId;
    }

    public Long getClienteId() {
        return clienteId;
    }

    public void setClienteId(Long clienteId) {
        this.clienteId = clienteId;
    }

    public Long getAccesorioId() {
        return accesorioId;
    }

    public void setAccesorioId(Long accesorioId) {
        this.accesorioId = accesorioId;
    }
}
