package pe.edu.upeu.msproyectos.dtos;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.List;

public class ProyectoRequest {
    @NotBlank(message = "El nombre del proyecto es obligatorio")
    @Size(max = 100, message = "El nombre del proyecto no debe exceder 100 caracteres")
    private String nombre;

    @NotBlank(message = "La dirección es obligatoria")
    @Size(max = 150, message = "La dirección no debe exceder 150 caracteres")
    private String direccion;

    @NotNull(message = "El cliente es obligatorio")
    private Long clienteId;



    @Valid // IMPORTANTE: Esto le dice a Spring que también valide los objetos dentro de la lista
    @NotEmpty(message = "El proyecto debe tener al menos un detalle")
    private List<DetalleProyectoRequest> detalles;


    public ProyectoRequest() {
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public Long getClienteId() {
        return clienteId;
    }

    public void setClienteId(Long clienteId) {
        this.clienteId = clienteId;
    }

    public List<DetalleProyectoRequest> getDetalles() {
        return detalles;
    }

    public void setDetalles(List<DetalleProyectoRequest> detalles) {
        this.detalles = detalles;
    }
}