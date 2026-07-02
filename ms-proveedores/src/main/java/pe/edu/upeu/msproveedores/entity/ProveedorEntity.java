package pe.edu.upeu.msproveedores.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "proveedores")
public class ProveedorEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "NOMBRES", length = 100, nullable = false)
    private String nombres;

    @Column(name = "APELLIDOS", length = 100, nullable = false)
    private String apellidos;

    @Column(name = "TELEFONO", length = 20, nullable = false)
    private String telefono;

    @Column(name = "CATEGORIA_ID", nullable = false)
    private Long categoriaId;

    @Column(name = "DIRECCION", length = 100, nullable = false)
    private String direccion;

    @Column(name = "URL_UBICACION", length = 500, nullable = false)
    private String ubicacion;

    @Column(name = "IMAGEN_URL")
    private String imagenUrl;

    @Column(name = "DESCRIPCION", length = 200, nullable = false)
    private String descripcion;

    public ProveedorEntity() {
    }

    public ProveedorEntity(Long id, String nombres, String apellidos, String telefono, Long categoriaId, String direccion, String ubicacion, String imagenUrl, String descripcion) {
        this.id = id;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.telefono = telefono;
        this.categoriaId = categoriaId;
        this.direccion = direccion;
        this.ubicacion = ubicacion;
        this.imagenUrl = imagenUrl;
        this.descripcion = descripcion;
    }

    // Getters y Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNombres() { return nombres; }
    public void setNombres(String nombres) { this.nombres = nombres; }

    public String getApellidos() { return apellidos; }
    public void setApellidos(String apellidos) { this.apellidos = apellidos; }

    public String getTelefono() { return telefono; }
    public void setTelefono(String telefono) { this.telefono = telefono; }

    public Long getCategoriaId() { return categoriaId; }
    public void setCategoriaId(Long categoriaId) { this.categoriaId = categoriaId; }

    public String getDireccion() { return direccion; }
    public void setDireccion(String direccion) { this.direccion = direccion; }

    public String getUbicacion() { return ubicacion; }
    public void setUbicacion(String ubicacion) { this.ubicacion = ubicacion; }

    public String getImagenUrl() { return imagenUrl; }
    public void setImagenUrl(String imagenUrl) { this.imagenUrl = imagenUrl; }

    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }
}