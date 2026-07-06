package pe.edu.upeu.msproyectos.dtos;

import java.util.List;

public class ProyectoResponse {
    private Long id;
    private String nombre;
    private String direccion;
    private Long clienteId;

    // Lista de detalles para que el frontend pueda pintar la tabla
    private List<DetalleProyectoResponse> detalles;

    public ProyectoResponse() {
    }

    public ProyectoResponse(Long id, String nombre, String direccion, Long clienteId, List<DetalleProyectoResponse> detalles) {
        this.id = id;
        this.nombre = nombre;
        this.direccion = direccion;
        this.clienteId = clienteId;
        this.detalles = detalles;
    }

    // Getters y Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public String getDireccion() { return direccion; }
    public void setDireccion(String direccion) { this.direccion = direccion; }
    public Long getClienteId() { return clienteId; }
    public void setClienteId(Long clienteId) { this.clienteId = clienteId; }
    public List<DetalleProyectoResponse> getDetalles() { return detalles; }
    public void setDetalles(List<DetalleProyectoResponse> detalles) { this.detalles = detalles; }
}