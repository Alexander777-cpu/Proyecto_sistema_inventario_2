package pe.edu.upeu.msherramientas.errors;

public class HerramientaNotFoundException extends RuntimeException {
    public HerramientaNotFoundException(Long id){
        super("No existe la herramienta con el id: "+ id);
    }
}
