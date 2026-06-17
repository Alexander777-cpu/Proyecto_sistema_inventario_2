package pe.edu.upeu.msherramientas.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class EstadoResponse {

    private Long id;

    @JsonProperty("nombre")
    private String estadoNombre;

    public EstadoResponse() {
    }

    public EstadoResponse(Long id, String estadoNombre) {
        this.id = id;
        this.estadoNombre = estadoNombre;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEstadoNombre() {
        return estadoNombre;
    }

    public void setEstadoNombre(String estadoNombre) {
        this.estadoNombre = estadoNombre;
    }
}
