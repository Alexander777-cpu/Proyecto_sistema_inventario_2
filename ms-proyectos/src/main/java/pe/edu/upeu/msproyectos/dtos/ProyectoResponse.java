package pe.edu.upeu.msproyectos.dtos;


public class ProyectoResponse {
    private Long id;
    private String nombre;
    private String direccion;
    private Long clienteId;
    private Long herramientaId;
    private Long accesorioId;

    public ProyectoResponse() {
    }

    public ProyectoResponse(Long id, String nombre, String direccion, Long clienteId, Long herramientaId, Long accesorioId) {
        this.id = id;
        this.nombre = nombre;
        this.direccion = direccion;
        this.clienteId = clienteId;
        this.herramientaId = herramientaId;
        this.accesorioId = accesorioId;
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

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public Long getClienteId() {
        return clienteId;
    }

    public void setClienteId(Long clienteId) {
        this.clienteId = clienteId;
    }

    public Long getHerramientaId() {
        return herramientaId;
    }

    public void setHerramientaId(Long herramientaId) {
        this.herramientaId = herramientaId;
    }

    public Long getAccesorioId() {
        return accesorioId;
    }

    public void setAccesorioId(Long accesorioId) {
        this.accesorioId = accesorioId;
    }
}
