package pe.edu.upeu.msherramientas.dto;

import java.time.LocalDate;

public class HerramientaResponse {

    private Long id;
    private String nombre;
    private String tipo;
    private String marca;
    private String estado;
    private LocalDate compra;
    private String vidaUtil;

    public HerramientaResponse() {
    }

    public HerramientaResponse(Long id, String nombre, String tipo, String marca, String estado, LocalDate compra, String vidaUtil) {
        this.id = id;
        this.nombre = nombre;
        this.tipo = tipo;
        this.marca = marca;
        this.estado = estado;
        this.compra = compra;
        this.vidaUtil = vidaUtil;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
