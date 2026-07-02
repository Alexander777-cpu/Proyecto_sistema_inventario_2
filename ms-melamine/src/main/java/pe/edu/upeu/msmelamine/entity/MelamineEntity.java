package pe.edu.upeu.msmelamine.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "melamine")
public class MelamineEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "NOMBRE", nullable = false, length = 100)
    private String nombre;

    @Column(name = "ANCHO", nullable = false)
    private BigDecimal ancho;

    @Column(name = "LARGO", nullable = false)
    private BigDecimal largo;

    // Relaciones correctas (Adiós N+1)
    @ManyToOne
    @JoinColumn(name = "COLOR_ID", nullable = false)
    private ColorMelamineEntity color;

    @ManyToOne
    @JoinColumn(name = "MARCA_ID", nullable = false)
    private MarcaMelamineEntity marca;

    @ManyToOne
    @JoinColumn(name = "ESTADO_ID", nullable = false)
    private EstadoMelamineEntity estado;

    @Column(name = "IMAGEN_URL")
    private String imagenUrl;

    public MelamineEntity() {
    }

    public MelamineEntity(Long id, String nombre, BigDecimal ancho, BigDecimal largo, ColorMelamineEntity color, MarcaMelamineEntity marca, EstadoMelamineEntity estado, String imagenUrl) {
        this.id = id;
        this.nombre = nombre;
        this.ancho = ancho;
        this.largo = largo;
        this.color = color;
        this.marca = marca;
        this.estado = estado;
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

    public ColorMelamineEntity getColor() {
        return color;
    }

    public void setColor(ColorMelamineEntity color) {
        this.color = color;
    }

    public MarcaMelamineEntity getMarca() {
        return marca;
    }

    public void setMarca(MarcaMelamineEntity marca) {
        this.marca = marca;
    }

    public EstadoMelamineEntity getEstado() {
        return estado;
    }

    public void setEstado(EstadoMelamineEntity estado) {
        this.estado = estado;
    }

    public String getImagenUrl() {
        return imagenUrl;
    }

    public void setImagenUrl(String imagenUrl) {
        this.imagenUrl = imagenUrl;
    }

}