package pe.edu.upeu.msherramientas.entity;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "herramientas")
public class HerramientaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "NOMBRE", nullable = false, length = 100)
    private String nombre;

    @Column(name = "TIPO", nullable = false, length = 100)
    private String tipo;

    @Column(name = "MARCA", nullable = false, length = 100)
    private String marca;

    // CORREGIDO: Ahora es un Long, sin el 'length = 100'
    @Column(name = "ESTADO_ID", nullable = false)
    private Long estadoId;

    @Column(name = "FECHA_COMPRA", nullable = false)
    private LocalDate compra;

    @Column(name = "FECHA_INICIO", nullable = false)
    private LocalDate fechaInicio;

    @Column(name = "VIDA_UTIL_MESES", nullable = false)
    private Integer vidaUtil;

    @Column(name = "IMAGEN_URL")
    private String imagenUrl;

    public HerramientaEntity() {
    }

    public HerramientaEntity(Long id, String nombre, String tipo, String marca, Long estadoId, LocalDate compra, LocalDate fechaInicio, Integer vidaUtil, String imagenUrl) {
        this.id = id;
        this.nombre = nombre;
        this.tipo = tipo;
        this.marca = marca;
        this.estadoId = estadoId;
        this.compra = compra;
        this.fechaInicio = fechaInicio;
        this.vidaUtil = vidaUtil;
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
