package pe.edu.upeu.msmelamine.dto;

import java.math.BigDecimal;

public class MelamineResponse {

    private Long id;
    private String nombre;
    private BigDecimal ancho;
    private BigDecimal largo;

    private Long colorId;
    private String colorNombre;

    private Long marcaId;
    private String marcaNombre;

    private Long estadoId;
    private String estadoNombre;

    private String imagenUrl;

    public MelamineResponse() {}

    public MelamineResponse(Long id, String nombre, BigDecimal ancho, BigDecimal largo, Long colorId, String colorNombre, Long marcaId, String marcaNombre, Long estadoId, String estadoNombre, String imagenUrl) {
        this.id = id;
        this.nombre = nombre;
        this.ancho = ancho;
        this.largo = largo;
        this.colorId = colorId;
        this.colorNombre = colorNombre;
        this.marcaId = marcaId;
        this.marcaNombre = marcaNombre;
        this.estadoId = estadoId;
        this.estadoNombre = estadoNombre;
        this.imagenUrl = imagenUrl;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public BigDecimal getAncho() { return ancho; }
    public void setAncho(BigDecimal ancho) { this.ancho = ancho; }
    public BigDecimal getLargo() { return largo; }
    public void setLargo(BigDecimal largo) { this.largo = largo; }
    public Long getColorId() { return colorId; }
    public void setColorId(Long colorId) { this.colorId = colorId; }
    public String getColorNombre() { return colorNombre; }
    public void setColorNombre(String colorNombre) { this.colorNombre = colorNombre; }
    public Long getMarcaId() { return marcaId; }
    public void setMarcaId(Long marcaId) { this.marcaId = marcaId; }
    public String getMarcaNombre() { return marcaNombre; }
    public void setMarcaNombre(String marcaNombre) { this.marcaNombre = marcaNombre; }
    public Long getEstadoId() { return estadoId; }
    public void setEstadoId(Long estadoId) { this.estadoId = estadoId; }
    public String getEstadoNombre() { return estadoNombre; }
    public void setEstadoNombre(String estadoNombre) { this.estadoNombre = estadoNombre; }
    public String getImagenUrl() { return imagenUrl; }
    public void setImagenUrl(String imagenUrl) { this.imagenUrl = imagenUrl; }
}