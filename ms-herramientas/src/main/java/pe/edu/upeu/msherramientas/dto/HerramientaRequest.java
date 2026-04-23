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

    private LocalDate compra;

    @NotBlank(message = "La vida útil es obligatoria")
    @Size(max = 100, message = "La vida útil no debe exceder 100 caracteres")
    private String vidaUtil;

    public HerramientaRequest() {
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

    public String getVidaUtil() {
        return vidaUtil;
    }

    public void setVidaUtil(String vidaUtil) {
        this.vidaUtil = vidaUtil;
    }
}
