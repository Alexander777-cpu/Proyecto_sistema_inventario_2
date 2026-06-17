package pe.edu.upeu.msherramientas.dto;

import jakarta.validation.constraints.*;
import java.time.LocalDate;

public class HerramientaRequest {

    @NotBlank(message = "El nombre es obligatorio")
    @Size(max = 100, message = "El nombre no debe exceder 100 caracteres")
    private String nombre;

    @NotBlank(message = "El tipo es obligatorio")
    @Size(max = 100, message = "El tipo no debe exceder 100 caracteres")
    private String tipo;

    @NotBlank(message = "La marca es obligatoria")
    @Size(max = 100, message = "La marca no debe exceder 100 caracteres")
    private String marca;

    // AHORA ES UN LONG (Para que coincida con ms-estado)
    @NotNull(message = "El ID del estado es obligatorio")
    private Long estadoId;

    @NotNull(message = "La fecha de compra es obligatoria")
    private LocalDate compra;

    @NotNull(message = "La fecha de inicio de uso es obligatoria")
    private LocalDate fechaInicio;

    @NotNull(message = "La vida útil es obligatoria")
    @Min(value = 1, message = "La vida útil debe ser de al menos 1 mes")
    private Integer vidaUtil;

    private String imagenUrl;

    public HerramientaRequest() {
    }

    public HerramientaRequest(String nombre, String tipo, String marca, Long estadoId, LocalDate compra, LocalDate fechaInicio, Integer vidaUtil, String imagenUrl) {
        this.nombre = nombre;
        this.tipo = tipo;
        this.marca = marca;
        this.estadoId = estadoId;
        this.compra = compra;
        this.fechaInicio = fechaInicio;
        this.vidaUtil = vidaUtil;
        this.imagenUrl = imagenUrl;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
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

    public LocalDate getCompra() {
        return compra;
    }

    public void setCompra(LocalDate compra) {
        this.compra = compra;
    }

    public LocalDate getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(LocalDate fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Integer getVidaUtil() {
        return vidaUtil;
    }

    public void setVidaUtil(Integer vidaUtil) {
        this.vidaUtil = vidaUtil;
    }

    public String getImagenUrl() {
        return imagenUrl;
    }

    public void setImagenUrl(String imagenUrl) {
        this.imagenUrl = imagenUrl;
    }
}