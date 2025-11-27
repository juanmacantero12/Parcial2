package Vehiculos;

public class PuertasInsuficientesException extends Exception {
    public PuertasInsuficientesException() {
        super("Error: El auto debe tener al menos 3 puertas.");
    }

    public PuertasInsuficientesException(String mensaje) {
        super(mensaje);
    }
}