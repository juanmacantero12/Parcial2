package Vehiculos;

import java.util.Objects;

abstract class Vehiculo implements Mantenible {
    private String marca;
    private int modelo;
    private String patente; // Atributo clave para equals/hashCode
    private int tipo; // 1: Auto, 2: Moto, 3: Colectivo
    private int kilometraje;

    // Constructor Parametrizado (Requisito)
    public Vehiculo(String marca, int modelo, String patente, int tipo, int kilometraje) throws PatenteInvalidaException {
        // Validación de Patente (parte del manejo de excepciones)
        if (patente == null || patente.length() != 7) {
            throw new PatenteInvalidaException("La patente debe tener exactamente 7 caracteres.");
        }
        
        this.marca = marca.toUpperCase();
        this.modelo = modelo;
        this.patente = patente.toUpperCase();
        this.tipo = tipo;
        this.kilometraje = kilometraje;
    }
    
    // Sobrecarga de Constructor: Útil para clonar o inicializar de forma mínima
    public Vehiculo(String patente) throws PatenteInvalidaException {
        this("Desconocida", 0, patente, 0, 0);
    }

    // Getters
    public String getMarca() {
        return marca;
    }

    public int getModelo() {
        return modelo;
    }

    public String getPatente() {
        return patente;
    }

    public int getTipo() {
        return tipo;
    }

    public int getKilometraje() {
        return kilometraje;
    }

    // Setters
    public void setMarca(String marca) {
        this.marca = marca;
    }

    public void setModelo(int modelo) {
        this.modelo = modelo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }

    public void setKilometraje(int kilometraje) {
        this.kilometraje = kilometraje;
    }

    // Implementación parcial de la Interfaz Mantenible
    @Override
    public String getIdentificador() {
        return this.patente;
    }

    // Método a Sobreescribir por las subclases (Ej. tipo de vehículo)
    public abstract String verTipoDeVehiculo();

    // Redefinición de equals() y hashCode() para identificar por Patente (Requisito)
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vehiculo vehiculo = (Vehiculo) o;
        return Objects.equals(patente, vehiculo.patente);
    }

    @Override
    public int hashCode() {
        return Objects.hash(patente);
    }

    @Override
    public String toString() {
        return String.format("%s | Patente: %s | Modelo: %d | Km: %d",
                verTipoDeVehiculo(), patente, modelo, kilometraje);
    }
}