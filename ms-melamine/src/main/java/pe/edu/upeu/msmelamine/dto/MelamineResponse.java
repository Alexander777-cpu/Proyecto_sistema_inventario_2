package pe.edu.upeu.msmelamine.dto;

import java.math.BigDecimal;

public class MelamineResponse {

    private Long id;

    // Atributo agregado
    private String nombre;

    private BigDecimal ancho;
    private BigDecimal largo;
    private String color;
    private String marca;

    private Long estadoId;
    private String estadoNombre;

    // Cambiado de foto a imagenUrl
    private String imagenUrl;

    public MelamineResponse() {
    }

    public MelamineResponse(Long id, String nombre, BigDecimal ancho, BigDecimal largo, String color, String marca, Long estadoId, String estadoNombre, String imagenUrl) {
        this.id = id;
        this.nombre = nombre;
        this.ancho = ancho;
        this.largo = largo;
        this.color = color;
        this.marca = marca;
        this.estadoId = estadoId;
        this.estadoNombre = estadoNombre;
        this.imagenUrl = imagenUrl;
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

    public String getEstadoNombre() {
        return estadoNombre;
    }

    public void setEstadoNombre(String estadoNombre) {
        this.estadoNombre = estadoNombre;
    }

    public String getImagenUrl() {
        return imagenUrl;
    }

    public void setImagenUrl(String imagenUrl) {
        this.imagenUrl = imagenUrl;
    }
}
