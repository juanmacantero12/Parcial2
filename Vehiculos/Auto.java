package Vehiculos;

class Auto extends Vehiculo {
    private int cantPuertas;

    public Auto(String marca, int modelo, String patente, int kilometraje, int cantPuertas) throws PatenteInvalidaException {
        super(marca, modelo, patente, 1, kilometraje);
        this.cantPuertas = cantPuertas;
    }

    public int getCantPuertas() {
        return cantPuertas;
    }

    public void setCantPuertas(int cantPuertas) {
        this.cantPuertas = cantPuertas;
    }

    // Sobreescritura de método abstracto
    @Override
    public String verTipoDeVehiculo() {
        return "🚗 AUTO";
    }

    // Sobreescritura de método de la interfaz
    @Override
    public double calcularCostoMantenimiento(double basePorKilometraje) {
        double costoBase = basePorKilometraje * this.getKilometraje();
        // Los autos tienen un costo extra por puerta
        return costoBase + (this.cantPuertas * 500.0);
    }

    @Override
    public String toString() {
        return super.toString() + String.format(" | Puertas: %d", cantPuertas);
    }
}