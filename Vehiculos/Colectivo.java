package Vehiculos;

class Colectivo extends Vehiculo {
    private int cantAsientos;

    public Colectivo(String marca, int modelo, String patente, int kilometraje, int cantAsientos) throws PatenteInvalidaException {
        super(marca, modelo, patente, 3, kilometraje);
        this.cantAsientos = cantAsientos;
    }

    public int getCantAsientos() {
        return cantAsientos;
    }

    public void setCantAsientos(int cantAsientos) {
        this.cantAsientos = cantAsientos;
    }

    // Sobreescritura de método abstracto
    @Override
    public String verTipoDeVehiculo() {
        return "🚌 COLECTIVO";
    }

    // Sobreescritura de método de la interfaz
    @Override
    public double calcularCostoMantenimiento(double basePorKilometraje) {
        double costoBase = basePorKilometraje * this.getKilometraje();
        // Los colectivos tienen un costo extra por asiento (mayor complejidad)
        return costoBase + (this.cantAsientos * 150.0);
    }

    @Override
    public String toString() {
        return super.toString() + String.format(" | Asientos: %d", cantAsientos);
    }
}