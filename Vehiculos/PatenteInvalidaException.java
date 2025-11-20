package Vehiculos;

public class PatenteInvalidaException extends Exception {

    public PatenteInvalidaException(String mensaje) {
        super(mensaje);
    }

    public PatenteInvalidaException() {
        super("Error: El formato de la patente es inválido.");
    }

}
