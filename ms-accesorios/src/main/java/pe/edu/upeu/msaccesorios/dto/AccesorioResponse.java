package pe.edu.upeu.msaccesorios.dto;

public class AccesorioResponse {

    private Long id;
    private String nombre;
    private String descripcion;
    private Double precio;
    private Integer stock;

    // SOLO SE MODIFICÓ ESTO: Ahora enviamos el ID y el Nombre de la categoría
    private Long categoriaId;
    private String categoriaNombre;

    private String marca;

    private Long estadoId;
    private String estadoNombre; // Para enviar el nombre del estado al frontend (llenado con Feign)

    private String imagenUrl;

    public AccesorioResponse() {
    }

    // El orden de este constructor coincide con los nuevos campos
    public AccesorioResponse(Long id, String nombre, String descripcion, Double precio, Integer stock, Long categoriaId, String categoriaNombre, String marca, Long estadoId, String estadoNombre, String imagenUrl) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio = precio;
        this.stock = stock;
        this.categoriaId = categoriaId;
        this.categoriaNombre = categoriaNombre;
        this.marca = marca;
        this.estadoId = estadoId;
        this.estadoNombre = estadoNombre;
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

    public Long getCategoriaId() {
        return categoriaId;
    }

    public void setCategoriaId(Long categoriaId) {
        this.categoriaId = categoriaId;
    }

    public String getCategoriaNombre() {
        return categoriaNombre;
    }

    public void setCategoriaNombre(String categoriaNombre) {
        this.categoriaNombre = categoriaNombre;
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

    public String getImagenUrl() {
        return imagenUrl;
    }

    public void setImagenUrl(String imagenUrl) {
        this.imagenUrl = imagenUrl;
    }
}