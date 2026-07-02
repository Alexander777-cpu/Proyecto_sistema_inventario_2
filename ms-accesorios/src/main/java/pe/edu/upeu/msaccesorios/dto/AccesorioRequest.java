package pe.edu.upeu.msaccesorios.dto;

import jakarta.validation.constraints.*;

public class AccesorioRequest {

    @NotBlank(message = "El nombre es obligatorio")
    @Size(min = 2, max = 100, message = "El nombre debe tener entre 2 y 100 caracteres")
    private String nombre;

    @NotBlank(message = "La descripción es obligatoria")
    @Size(min = 5, max = 255, message = "La descripción debe tener entre 5 y 255 caracteres")
    private String descripcion;

    @NotNull(message = "El precio es obligatorio")
    @Positive(message = "El precio debe ser mayor a 0")
    @DecimalMax(value = "999.99", message = "El precio no puede exceder 999.99")
    private Double precio;

    @NotNull(message = "El stock es obligatorio")
    @Min(value = 1, message = "El stock debe ser de al menos 1 unidad")
    @Max(value = 1000, message = "El stock no puede exceder 1000 unidades")
    private Integer stock;

    // Se eliminó por completo el categoriaId y su validación

    @NotNull(message = "El ID de la marca es obligatorio")
    private Long marcaId;

    @NotNull(message = "El ID del estado es obligatorio")
    private Long estadoId;

    private String imagenUrl;

    // Constructor vacío
    public AccesorioRequest() {
    }

    // Constructor con parámetros (actualizado sin categoriaId)
    public AccesorioRequest(String nombre, String descripcion, Double precio, Integer stock, Long marcaId, Long estadoId, String imagenUrl) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio = precio;
        this.stock = stock;
        this.marcaId = marcaId;
        this.estadoId = estadoId;
        this.imagenUrl = imagenUrl;
    }

    // Getters y Setters
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