package pe.edu.upeu.msherramientas.dto;

import jakarta.validation.constraints.*;
import java.time.LocalDate;

public class HerramientaRequest {

    @NotBlank(message = "El nombre es obligatorio")
    @Size(max = 100, message = "El nombre no debe exceder 100 caracteres")
    private String nombre;

    @NotNull(message = "La cantidad es obligatoria")
    @Min(value = 0, message = "La cantidad no puede ser negativa")
    private Integer cantidad;

    @NotNull(message = "El ID del tipo es obligatorio")
    private Long tipoId;

    @NotNull(message = "El ID de la marca es obligatoria")
    private Long marcaId;

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

    public HerramientaRequest(String nombre, Integer cantidad, Long tipoId, Long marcaId, Long estadoId, LocalDate compra, LocalDate fechaInicio, Integer vidaUtil, String imagenUrl) {
        this.nombre = nombre;
        this.cantidad = cantidad;
        this.tipoId = tipoId;
        this.marcaId = marcaId;
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

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public Long getTipoId() {
        return tipoId;
    }

    public void setTipoId(Long tipoId) {
        this.tipoId = tipoId;
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