package pe.edu.upeu.msaccesorios.dto;

import jakarta.validation.constraints.*;

public class AccesorioRequest {

    @NotBlank(message = "El nombre es obligatorio")
    @Size(min = 2, max = 100, message = "El nombre debe tener entre 2 y 100 caracteres")
    private String nombre;

    @Size(min = 5, max = 255, message = "La descripción debe tener entre 5 y 255 caracteres")
    private String descripcion;

    @NotNull(message = "El precio es obligatorio")
    @Positive(message = "El precio debe ser mayor a 0")
    @DecimalMax(value = "999999.99", message = "El precio no puede exceder 999999.99")
    private Double precio;

    @NotNull(message = "El stock es obligatorio")
    @Min(value = 1, message = "El stock debe ser mayor a 0")
    @Max(value = 10000, message = "El stock no puede exceder 10000 unidades")
    private Integer stock;

    @Size(max = 50, message = "La categoría no debe exceder 50 caracteres")
    private String categoria;

    @Size(max = 50, message = "La marca no debe exceder 50 caracteres")
    private String marca;

    public AccesorioRequest() {
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

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }
}