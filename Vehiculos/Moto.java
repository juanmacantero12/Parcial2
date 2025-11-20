package Vehiculos;

class Moto extends Vehiculo {

    public Moto(String marca, int modelo, String patente, int kilometraje) throws PatenteInvalidaException {
        super(marca, modelo, patente, 2, kilometraje);
    }

    // Sobreescritura de método abstracto
    @Override
    public String verTipoDeVehiculo() {
        return "🏍️ MOTO";
    }

    // Sobreescritura de método de la interfaz
    @Override
    public double calcularCostoMantenimiento(double basePorKilometraje) {
        double costoBase = basePorKilometraje * this.getKilometraje();
        // Las motos tienen el mantenimiento más barato, descuento del 20%
        return costoBase * 0.8;
    }
}