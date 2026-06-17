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

    @NotBlank(message = "El estado es obligatorio")
    @Size(max = 50, message = "El estado no debe exceder 50 caracteres")
    private String estado;

    @NotNull(message = "La fecha de compra es obligatoria")
    private LocalDate compra;

    // 1. NUEVO CAMPO AÑADIDO
    @NotNull(message = "La fecha de inicio de uso es obligatoria")
    private LocalDate fechaInicio;

    // 2. CORREGIDO A INTEGER CON VALIDACIÓN NUMÉRICA
    @NotNull(message = "La vida útil es obligatoria")
    @Min(value = 1, message = "La vida útil debe ser de al menos 1 mes")
    private Integer vidaUtil;

    // 3. CAMPO OPCIONAL PARA LA IMAGEN (Sin validaciones estrictas porque puede venir vacío)
    private String imagenUrl;

    public HerramientaRequest() {
    }

    public HerramientaRequest(String nombre, String tipo, String marca, String estado, LocalDate compra, LocalDate fechaInicio, Integer vidaUtil, String imagenUrl) {
        this.nombre = nombre;
        this.tipo = tipo;
        this.marca = marca;
        this.estado = estado;
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

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
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