package pe.edu.upeu.msherramientas.dto;

import java.time.LocalDate;

public class HerramientaResponse {

    private Long id;
    private String nombre;
    private Integer cantidad;
    private String tipoNombre;
    private String marcaNombre;
    private String estadoNombre;
    private LocalDate compra;
    private LocalDate fechaInicio;
    private String imagenUrl;
    private Integer vidaUtil;
    private Long diasRestantes;

    public HerramientaResponse() {
    }

    public HerramientaResponse(Long id, String nombre, Integer cantidad, String tipoNombre, String marcaNombre, String estadoNombre, LocalDate compra, LocalDate fechaInicio, String imagenUrl, Integer vidaUtil, Long diasRestantes) {
        this.id = id;
        this.nombre = nombre;
        this.cantidad = cantidad;
        this.tipoNombre = tipoNombre;
        this.marcaNombre = marcaNombre;
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

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public String getTipoNombre() {
        return tipoNombre;
    }

    public void setTipoNombre(String tipoNombre) {
        this.tipoNombre = tipoNombre;
    }

    public String getMarcaNombre() {
        return marcaNombre;
    }

    public void setMarcaNombre(String marcaNombre) {
        this.marcaNombre = marcaNombre;
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