package pe.edu.upeu.msmelamine.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.math.BigDecimal;

public class MelamineRequest {

    @NotBlank(message = "El nombre es obligatorio")
    @Size(max = 100, message = "El nombre no debe exceder 100 caracteres")
    private String nombre;

    @NotNull(message = "El ancho es obligatorio")
    @DecimalMin(value = "0.0", inclusive = false, message = "El ancho debe ser mayor a 0")
    private BigDecimal ancho;

    @NotNull(message = "El largo es obligatorio")
    @DecimalMin(value = "0.0", inclusive = false, message = "El largo debe ser mayor a 0")
    private BigDecimal largo;

    @NotNull(message = "La cantidad es obligatoria")
    @Min(value = 0, message = "La cantidad no puede ser negativa")
    private Integer cantidad;

    @NotNull(message = "El ID del color es obligatorio")
    private Long colorId;

    @NotNull(message = "El ID de la marca es obligatorio")
    private Long marcaId;

    @NotNull(message = "El ID del estado es obligatorio")
    private Long estadoId;

    private String imagenUrl;

    public MelamineRequest() {}

    public MelamineRequest(String nombre, BigDecimal ancho, BigDecimal largo, Integer cantidad, Long colorId, Long marcaId, Long estadoId, String imagenUrl) {
        this.nombre = nombre;
        this.ancho = ancho;
        this.largo = largo;
        this.cantidad = cantidad;
        this.colorId = colorId;
        this.marcaId = marcaId;
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

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public Long getColorId() {
        return colorId;
    }

    public void setColorId(Long colorId) {
        this.colorId = colorId;
    }

    public Long getMarcaId() {
        return marcaId;
    }

    public void setMarcaId(Long marcaId) {
        this.marcaId = marcaId;
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