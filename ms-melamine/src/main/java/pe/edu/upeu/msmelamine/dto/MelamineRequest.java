package pe.edu.upeu.msmelamine.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;

public class MelamineRequest {

    // Se agregó el nombre con sus validaciones estándar
    @NotBlank(message = "El nombre es obligatorio")
    @Size(max = 100, message = "El nombre no debe exceder 100 caracteres")
    private String nombre;

    @NotNull(message = "El ancho es obligatorio")
    @DecimalMin(value = "0.0", inclusive = false, message = "El ancho debe ser mayor a 0")
    private BigDecimal ancho;

    @NotNull(message = "El largo es obligatorio")
    @DecimalMin(value = "0.0", inclusive = false, message = "El largo debe ser mayor a 0")
    private BigDecimal largo;

    // Se agregaron validaciones de texto para color y marca
    @NotBlank(message = "El color es obligatorio")
    @Size(max = 100, message = "El color no debe exceder 100 caracteres")
    private String color;

    @NotBlank(message = "La marca es obligatoria")
    @Size(max = 100, message = "La marca no debe exceder 100 caracteres")
    private String marca;

    @NotNull(message = "El ID del estado es obligatorio")
    private Long estadoId;

    // Se cambió 'foto' a 'imagenUrl'
    private String imagenUrl;

    public MelamineRequest() {
    }

    public MelamineRequest(String nombre, BigDecimal ancho, BigDecimal largo, String color, String marca, Long estadoId, String imagenUrl) {
        this.nombre = nombre;
        this.ancho = ancho;
        this.largo = largo;
        this.color = color;
        this.marca = marca;
        this.estadoId = estadoId;
        this.imagenUrl = imagenUrl;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public BigDecimal getAncho() {
        return ancho;
    }

    public void setAncho(BigDecimal ancho) {
        this.ancho = ancho;
    }

    public BigDecimal getLargo() {
        return largo;
    }

    public void setLargo(BigDecimal largo) {
        this.largo = largo;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public Long getEstadoId() {
        return estadoId;
    }

    public void setEstadoId(Long estadoId) {
        this.estadoId = estadoId;
    }

    public String getImagenUrl() {
        return imagenUrl;
    }

    public void setImagenUrl(String imagenUrl) {
        this.imagenUrl = imagenUrl;
    }
}
