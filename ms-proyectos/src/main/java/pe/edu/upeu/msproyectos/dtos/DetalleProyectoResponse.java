package pe.edu.upeu.msproyectos.dtos;

public class DetalleProyectoResponse {
    private Long id;
    private Long herramientaId;
    private Long cantidadHerramienta;
    private Long accesorioId;
    private Long cantidadAccesorio;
    private Long melamineId;
    private Long cantidadMelamine;

    public DetalleProyectoResponse() {
    }

    public DetalleProyectoResponse(Long id, Long herramientaId, Long cantidadHerramienta, Long accesorioId, Long cantidadAccesorio, Long melamineId, Long cantidadMelamine) {
        this.id = id;
        this.herramientaId = herramientaId;
        this.cantidadHerramienta = cantidadHerramienta;
        this.accesorioId = accesorioId;
        this.cantidadAccesorio = cantidadAccesorio;
        this.melamineId = melamineId;
        this.cantidadMelamine = cantidadMelamine;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getHerramientaId() {
        return herramientaId;
    }

    public void setHerramientaId(Long herramientaId) {
        this.herramientaId = herramientaId;
    }

    public Long getCantidadHerramienta() {
        return cantidadHerramienta;
    }

    public void setCantidadHerramienta(Long cantidadHerramienta) {
        this.cantidadHerramienta = cantidadHerramienta;
    }

    public Long getAccesorioId() {
        return accesorioId;
    }

    public void setAccesorioId(Long accesorioId) {
        this.accesorioId = accesorioId;
    }

    public Long getCantidadAccesorio() {
        return cantidadAccesorio;
    }

    public void setCantidadAccesorio(Long cantidadAccesorio) {
        this.cantidadAccesorio = cantidadAccesorio;
    }

    public Long getMelamineId() {
        return melamineId;
    }

    public void setMelamineId(Long melamineId) {
        this.melamineId = melamineId;
    }

    public Long getCantidadMelamine() {
        return cantidadMelamine;
    }

    public void setCantidadMelamine(Long cantidadMelamine) {
        this.cantidadMelamine = cantidadMelamine;
    }
}