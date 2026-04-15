package pe.edu.upeu.msaccesorios.dtos;

import jakarta.validation.constraints.Size;
import org.hibernate.validator.constraints.NotBlank;

public class AccesorioRequest {

    @NotBlank(message = "El nombre es obligatorio")
    @Size(max = 20, message = "El nombre no debe exceder 20 caracteres")
    private String nombre;

    public AccesorioRequest() {
    }

    public AccesorioRequest(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
