package pe.edu.upeu.msproyectos.dtos;

public class DetalleProyectoResponse {
    private Long id;
    private Long herramientaId;
    private Long accesorioId;
    private Long melamineId;

    public DetalleProyectoResponse() {
    }

    public DetalleProyectoResponse(Long id, Long herramientaId, Long accesorioId, Long melamineId) {
        this.id = id;
        this.herramientaId = herramientaId;
        this.accesorioId = accesorioId;
        this.melamineId = melamineId;
    }

    // Getters y Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Long getHerramientaId() { return herramientaId; }
    public void setHerramientaId(Long herramientaId) { this.herramientaId = herramientaId; }
    public Long getAccesorioId() { return accesorioId; }
    public void setAccesorioId(Long accesorioId) { this.accesorioId = accesorioId; }
    public Long getMelamineId() { return melamineId; }
    public void setMelamineId(Long melamineId) { this.melamineId = melamineId; }
}