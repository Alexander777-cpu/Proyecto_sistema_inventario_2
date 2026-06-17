package pe.edu.upeu.msherramientas.dto;

import java.time.LocalDate;

public class HerramientaResponse {

    private Long id;
    private String nombre;
    private String tipo;
    private String marca;
    private String estado;
    private LocalDate compra;

    // 1. NUEVOS CAMPOS AÑADIDOS
    private LocalDate fechaInicio;
    private String imagenUrl;

    // 2. CORREGIDO A INTEGER
    private Integer vidaUtil;

    // 3. CAMPO CALCULADO: ¡Para que tu frontend no sufra haciendo matemáticas!
    private Long diasRestantes;

    public HerramientaResponse() {
    }

    public HerramientaResponse(Long id, String nombre, String tipo, String marca, String estado,
                               LocalDate compra, LocalDate fechaInicio, Integer vidaUtil,
                               String imagenUrl, Long diasRestantes) {
        this.id = id;
        this.nombre = nombre;
        this.tipo = tipo;
        this.marca = marca;
        this.estado = estado;
        this.compra = compra;
        this.fechaInicio = fechaInicio;
        this.vidaUtil = vidaUtil;
        this.imagenUrl = imagenUrl;
        this.diasRestantes = diasRestantes;
    }

    // --- GETTERS Y SETTERS ---

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getTipo() { return tipo; }
    public void setTipo(String tipo) { this.tipo = tipo; }

    public String getMarca() { return marca; }
    public void setMarca(String marca) { this.marca = marca; }

    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }

    public LocalDate getCompra() { return compra; }
    public void setCompra(LocalDate compra) { this.compra = compra; }

    public LocalDate getFechaInicio() { return fechaInicio; }
    public void setFechaInicio(LocalDate fechaInicio) { this.fechaInicio = fechaInicio; }

    public Integer getVidaUtil() { return vidaUtil; }
    public void setVidaUtil(Integer vidaUtil) { this.vidaUtil = vidaUtil; }

    public String getImagenUrl() { return imagenUrl; }
    public void setImagenUrl(String imagenUrl) { this.imagenUrl = imagenUrl; }

    public Long getDiasRestantes() { return diasRestantes; }
    public void setDiasRestantes(Long diasRestantes) { this.diasRestantes = diasRestantes; }
}