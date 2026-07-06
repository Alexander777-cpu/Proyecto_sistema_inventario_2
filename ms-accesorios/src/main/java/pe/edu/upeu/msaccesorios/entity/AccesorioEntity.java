package pe.edu.upeu.msaccesorios.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "accesorios")
public class AccesorioEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "NOMBRE", nullable = false, length = 100)
    private String nombre;

    @Column(name = "DESCRIPCION", nullable = false, length = 150)
    private String descripcion;

    @Column(name = "PRECIO", nullable = false)
    private Double precio;

    @Column(name = "STOCK", nullable = false)
    private Integer stock;

    @ManyToOne
    @JoinColumn(name = "MARCA_ID", nullable = false)
    private MarcaAccesorioEntity marca;

    @ManyToOne
    @JoinColumn(name = "ESTADO_ID", nullable = false)
    private EstadoAccesorioEntity estado;

    @Column(name = "IMAGEN_URL")
    private String imagenUrl;

    public AccesorioEntity() {
    }

    public AccesorioEntity(Long id, String nombre, String descripcion, Double precio, Integer stock, MarcaAccesorioEntity marca, EstadoAccesorioEntity estado, String imagenUrl) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio = precio;
        this.stock = stock;
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

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public MarcaAccesorioEntity getMarca() {
        return marca;
    }

    public void setMarca(MarcaAccesorioEntity marca) {
        this.marca = marca;
    }

    public EstadoAccesorioEntity getEstado() {
        return estado;
    }

    public void setEstado(EstadoAccesorioEntity estado) {
        this.estado = estado;
    }

    public String getImagenUrl() {
        return imagenUrl;
    }

    public void setImagenUrl(String imagenUrl) {
        this.imagenUrl = imagenUrl;
    }
}