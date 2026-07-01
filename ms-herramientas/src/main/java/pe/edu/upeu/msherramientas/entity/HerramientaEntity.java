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

    @ManyToOne
    @JoinColumn(name = "TIPO_ID", nullable = false)
    private TipoHerramientaEntity tipo;

    @ManyToOne
    @JoinColumn(name = "MARCA_ID", nullable = false)
    private MarcaHerramientaEntity marca;

    @ManyToOne
    @JoinColumn(name = "ESTADO_ID", nullable = false)
    private EstadoHerramientaEntity estado;

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

    public HerramientaEntity(Long id, String nombre, TipoHerramientaEntity tipo, MarcaHerramientaEntity marca, EstadoHerramientaEntity estado, LocalDate compra, LocalDate fechaInicio, Integer vidaUtil, String imagenUrl) {
        this.id = id;
        this.nombre = nombre;
        this.tipo = tipo;
        this.marca = marca;
        this.estado = estado;
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

    public TipoHerramientaEntity getTipo() {
        return tipo;
    }

    public void setTipo(TipoHerramientaEntity tipo) {
        this.tipo = tipo;
    }

    public MarcaHerramientaEntity getMarca() {
        return marca;
    }

    public void setMarca(MarcaHerramientaEntity marca) {
        this.marca = marca;
    }

    public EstadoHerramientaEntity getEstado() {
        return estado;
    }

    public void setEstado(EstadoHerramientaEntity estado) {
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
