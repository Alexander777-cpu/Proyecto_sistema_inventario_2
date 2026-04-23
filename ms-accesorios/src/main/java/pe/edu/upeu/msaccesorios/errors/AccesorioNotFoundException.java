package pe.edu.upeu.msaccesorios.errors;

public class AccesorioNotFoundException extends RuntimeException{
    public AccesorioNotFoundException(Long id){
        super("No existe el accesorio con el id: "+ id);
    }
}
