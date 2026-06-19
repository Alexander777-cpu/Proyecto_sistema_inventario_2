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

    // Atributo agregado para mantener el estándar de los otros microservicios
    @Column(name = "NOMBRE", nullable = false, length = 100)
    private String nombre;

    @Column(name = "ANCHO", nullable = false)
    private BigDecimal ancho;

    @Column(name = "LARGO", nullable = false)
    private BigDecimal largo;

    @Column(name = "COLOR", nullable = false, length = 100)
    private String color;

    @Column(name = "MARCA", nullable = false, length = 100)
    private String marca;

    @Column(name = "ESTADO_ID", nullable = false)
    private Long estadoId;

    // Estandarizado a imagenUrl (igual que en Herramienta)
    @Column(name = "IMAGEN_URL")
    private String imagenUrl;

    public MelamineEntity() {
    }

    public MelamineEntity(Long id, String nombre, BigDecimal ancho, BigDecimal largo, String color, String marca, Long estadoId, String imagenUrl) {
        this.id = id;
        this.nombre = nombre;
        this.ancho = ancho;
        this.largo = largo;
        this.color = color;
        this.marca = marca;
        this.estadoId = estadoId;
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

    public String getImagenUrl() {
        return imagenUrl;
    }

    public void setImagenUrl(String imagenUrl) {
        this.imagenUrl = imagenUrl;
    }
}