package Vehiculos;

import java.util.ArrayList;
import java.util.List;

public class Flota {
    // Variable global justificada (Requisito)
    private List<Vehiculo> vehiculos;

    public Flota() {
        // Inicialización con ArrayList (Requisito)
        this.vehiculos = new ArrayList<>();
    }
    
    // ================================== CRUD ==================================

    // Método 1: Crear (Incluye verificación de existencia usando equals/hashCode)
    public boolean crearVehiculo(Vehiculo vehiculo) {
        // Usa anyMatch (Lambda/Stream - Requisito) para verificar si la patente existe
        if (this.vehiculos.stream().anyMatch(v -> v.equals(vehiculo))) {
            return false; // Ya existe
        }
        this.vehiculos.add(vehiculo);
        return true;
    }
    
    // Método 2: Buscar (Implementación recursiva - Requisito)
    public Vehiculo buscarVehiculoRecursivo(String patenteBuscada) {
        return buscarVehiculoRecursivo(patenteBuscada.toUpperCase(), 0);
    }
    
    private Vehiculo buscarVehiculoRecursivo(String patenteBuscada, int indice) {
        // Control de flujo condicional (if) y recursividad
        if (indice >= vehiculos.size()) {
            return null; // Caso base: no encontrado
        }
        if (vehiculos.get(indice).getPatente().equals(patenteBuscada)) {
            return vehiculos.get(indice); // Caso base: encontrado
        }
        // Llamada recursiva
        return buscarVehiculoRecursivo(patenteBuscada, indice + 1);
    }
    
    // Método 3: Actualizar (Demuestra paso por referencia - Requisito)
    public boolean actualizarVehiculo(String patente, int nuevoKilometraje, int nuevoModelo) {
        // Uso de for-each (Requisito)
        for (Vehiculo v : vehiculos) {
            // Uso de equals()
            if (v.getPatente().equals(patente.toUpperCase())) {
                // Paso por referencia: modificamos el objeto directamente en la lista
                v.setKilometraje(nuevoKilometraje);
                v.setModelo(nuevoModelo);
                return true;
            }
        }
        return false;
    }
    
    // Método 4: Eliminar (Uso de removeIf - Lambda/Stream - Requisito)
    public boolean eliminarVehiculo(String patente) {
        // Uso de removeIf (Lambda - Requisito)
        return vehiculos.removeIf(v -> v.getPatente().equals(patente.toUpperCase()));
    }
    
    // Método 5: Conteo (Bucle while y continue - Requisito)
    public int contarVehiculosTipo(int tipo) {
        int count = 0;
        int i = 0;
        // Uso de while (Requisito)
        while (i < vehiculos.size()) {
            if (vehiculos.get(i).getTipo() != tipo) {
                i++;
                continue; // Uso de continue (Requisito)
            }
            count++;
            i++;
        }
        return count;
    }
    
    // Otros métodos de consulta
    public void listarTodos() {
        System.out.println("\n--- LISTADO DE FLOTA COMPLETA ---");
        // Uso de forEach (Lambda/Stream - Requisito)
        vehiculos.forEach(v -> System.out.println(v.toString() + 
                               " | Costo Mant.: $" + 
                               String.format("%.2f", v.calcularCostoMantenimiento(0.1))));
    }

    public List<Vehiculo> getVehiculos() {
        return new ArrayList<>(vehiculos);
    }
}
// --- EJERCICIO 4: Método específico solicitado en el parcial ---
    public boolean agregarAuto(Auto auto) {
        // Verifica si en la colección ya existe un auto con la misma patente
        // Reutilizamos la lógica de Streams que ya tenías, pero específica para la consigna
        if (this.vehiculos.stream().anyMatch(v -> v.getPatente().equalsIgnoreCase(auto.getPatente()))) {
            return false; // Ya existe, no se agrega
        }
        this.vehiculos.add(auto);
        return true; // Agregado exitosamente
    }
    