package Vehiculos;

import java.util.Objects;

class Auto extends Vehiculo {
    private int cantPuertas;

    // Modificamos el constructor para lanzar AMBAS excepciones (Patente y Puertas)
    public Auto(String marca, int modelo, String patente, int kilometraje, int cantPuertas) 
            throws PatenteInvalidaException, PuertasInsuficientesException {
        
        super(marca, modelo, patente, 1, kilometraje);
        
        // --- VALIDACIÓN EJERCICIO 2 (Excepción verificada) ---
        if (cantPuertas < 3) {
            throw new PuertasInsuficientesException("No se puede crear auto con " + cantPuertas + " puertas. Mínimo 3.");
        }
        
        this.cantPuertas = cantPuertas;
    }

    public int getCantPuertas() {
        return cantPuertas;
    }

    public void setCantPuertas(int cantPuertas) {
        this.cantPuertas = cantPuertas;
    }

    // --- EJERCICIO 1: Cálculo de Precio de Venta ---
    public double calcularPrecioVenta(double precioBase, int anioActual) {
        int aniosDeUso = anioActual - this.getModelo();
        
        // 1. Depreciación del 5% por año
        double precioDepreciacion = precioBase * Math.pow(0.95, aniosDeUso);
        
        // 2. Porcentaje adicional por puertas
        double porcentajeAdicional;
        if (cantPuertas == 3) {
            porcentajeAdicional = 0.30;
        } else if (cantPuertas == 4) {
            porcentajeAdicional = 0.40;
        } else {
            porcentajeAdicional = 0.35;
        }
        
        // 3. Precio final
        return precioDepreciacion * (1 + porcentajeAdicional);
    }

    // --- EJERCICIO 3: Igualdad por Patente ---
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        
        // Como Auto extiende de Vehiculo, usamos la patente del padre
        Auto other = (Auto) obj;
        return this.getPatente().equalsIgnoreCase(other.getPatente());
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.getPatente().toUpperCase());
    }

    @Override
    public String verTipoDeVehiculo() {
        return "🚗 AUTO";
    }

    @Override
    public double calcularCostoMantenimiento(double basePorKilometraje) {
        double costoBase = basePorKilometraje * this.getKilometraje();
        return costoBase + (this.cantPuertas * 500.0);
    }

    @Override
    public String toString() {
        return super.toString() + String.format(" | Puertas: %d", cantPuertas);
    }
}
