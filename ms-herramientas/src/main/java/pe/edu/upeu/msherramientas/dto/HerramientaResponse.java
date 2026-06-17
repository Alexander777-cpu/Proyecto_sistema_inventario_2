package pe.edu.upeu.msherramientas.dto;

import java.time.LocalDate;

public class HerramientaResponse {

    private Long id;
    private String nombre;
    private String tipo;
    private String marca;

    private Long estadoId;
    private String estadoNombre; // NUEVO: Para enviar el nombre del estado al frontend

    private LocalDate compra;
    private LocalDate fechaInicio;
    private String imagenUrl;
    private Integer vidaUtil;
    private Long diasRestantes;

    public HerramientaResponse() {
    }

    public HerramientaResponse(Long id, String nombre, String tipo, String marca, Long estadoId, String estadoNombre, LocalDate compra, LocalDate fechaInicio, String imagenUrl, Integer vidaUtil, Long diasRestantes) {
        this.id = id;
        this.nombre = nombre;
        this.tipo = tipo;
        this.marca = marca;
        this.estadoId = estadoId;
        this.estadoNombre = estadoNombre;
        this.compra = compra;
        this.fechaInicio = fechaInicio;
        this.imagenUrl = imagenUrl;
        this.vidaUtil = vidaUtil;
        this.diasRestantes = diasRestantes;
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

    public String getImagenUrl() {
        return imagenUrl;
    }

    public void setImagenUrl(String imagenUrl) {
        this.imagenUrl = imagenUrl;
    }

    public Integer getVidaUtil() {
        return vidaUtil;
    }

    public void setVidaUtil(Integer vidaUtil) {
        this.vidaUtil = vidaUtil;
    }

    public Long getDiasRestantes() {
        return diasRestantes;
    }

    public void setDiasRestantes(Long diasRestantes) {
        this.diasRestantes = diasRestantes;
    }
}