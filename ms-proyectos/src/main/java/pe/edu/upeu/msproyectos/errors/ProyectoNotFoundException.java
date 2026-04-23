package pe.edu.upeu.msproyectos.errors;

public class ProyectoNotFoundException extends RuntimeException {
    public ProyectoNotFoundException(Long id) {
        super("No existe el proyecto con el id: " + id);
    }
}
