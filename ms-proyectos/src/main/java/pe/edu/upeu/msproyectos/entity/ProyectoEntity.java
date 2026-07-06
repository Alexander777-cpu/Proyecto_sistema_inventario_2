package pe.edu.upeu.msproyectos.entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

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

    @OneToMany(mappedBy = "proyecto", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<DetalleProyectoEntity> detalles = new ArrayList<>();


    public ProyectoEntity() {
    }

    public ProyectoEntity(Long id, String nombre, String direccion, Long clienteId, List<DetalleProyectoEntity> detalles) {
        this.id = id;
        this.nombre = nombre;
        this.direccion = direccion;
        this.clienteId = clienteId;
        this.detalles = detalles;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public String getDireccion() { return direccion; }
    public void setDireccion(String direccion) { this.direccion = direccion; }
    public Long getClienteId() { return clienteId; }
    public void setClienteId(Long clienteId) { this.clienteId = clienteId; }
    public List<DetalleProyectoEntity> getDetalles() { return detalles; }

    public void setDetalles(List<DetalleProyectoEntity> detalles) {
        this.detalles = detalles;
        if (detalles != null) {
            for (DetalleProyectoEntity detalle : detalles) {
                detalle.setProyecto(this);
            }
        }
    }
}