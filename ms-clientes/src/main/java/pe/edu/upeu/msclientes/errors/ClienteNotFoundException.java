package pe.edu.upeu.msclientes.errors;

public class ClienteNotFoundException extends RuntimeException {
    public ClienteNotFoundException(Long id) {
        super("No existe el cliente con el id: " + id);
    }
}