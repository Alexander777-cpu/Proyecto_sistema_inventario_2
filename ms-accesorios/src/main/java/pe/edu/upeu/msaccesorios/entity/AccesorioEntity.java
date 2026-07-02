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

    // Se eliminó por completo el campo categoriaId

    @Column(name = "MARCA_ID", nullable = false)
    private Long marcaId;

    @Column(name = "ESTADO_ID", nullable = false)
    private Long estadoId;

    @Column(name = "IMAGEN_URL")
    private String imagenUrl;

    public AccesorioEntity() {
    }

    // Constructor actualizado sin categoriaId
    public AccesorioEntity(Long id, String nombre, String descripcion, Double precio, Integer stock, Long marcaId, Long estadoId, String imagenUrl) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio = precio;
        this.stock = stock;
        this.marcaId = marcaId;
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

    public Long getMarcaId() {
        return marcaId;
    }

    public void setMarcaId(Long marcaId) {
        this.marcaId = marcaId;
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