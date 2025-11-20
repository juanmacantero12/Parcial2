package Vehiculos;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Clase auxiliar para operaciones complejas sobre la Flota.
 * Contiene implementaciones de referencia para búsquedas, filtros y validaciones.
 */
public class FlotaHelper {

    // ======================================================================
    // 1. IDENTIDAD DEL OBJETO (Para copiar en clase Modelo)
    // ======================================================================
    /*
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        
        // Casting seguro
        Vehiculo other = (Vehiculo) obj;
        
        // Comparación por atributo único (IgnoreCase es clave para Strings)
        return this.patente.equalsIgnoreCase(other.patente);
    }

    @Override
    public int hashCode() {
        return java.util.Objects.hash(patente);
    }
    */

    // ======================================================================
    // 2. EXCEPCIONES Y VALIDACIÓN
    // ======================================================================
    
    // Definición de Excepción
    /*
    class PatenteInvalidaException extends Exception {
        public PatenteInvalidaException(String mensaje) {
            super(mensaje);
        }
    }
    */

    // Uso en Constructor (Validación de integridad)
    /*
    public Vehiculo(String patente, double km) throws PatenteInvalidaException {
        if (km < 0) {
            throw new IllegalArgumentException("Kilometraje negativo.");
        }
        if (patente == null || patente.length() < 6) {
            throw new PatenteInvalidaException("Formato inválido: " + patente);
        }
        this.patente = patente;
    }
    */

    // ======================================================================
    // 3. RECURSIVIDAD (Búsqueda y Conteo)
    // ======================================================================

    /**
     * Búsqueda Recursiva Genérica.
     * Se necesitan dos métodos: Uno público (interfaz) y uno privado (lógica).
     */
    // Método Público
    /*
    public Vehiculo buscar(String patente) {
        return buscarRecursivo(listaVehiculos, patente, 0);
    }
    */

    // Método Privado
    /*
    private Vehiculo buscarRecursivo(ArrayList<Vehiculo> lista, String patente, int i) {
        // CASO BASE 1: Fin de lista (No encontrado)
        if (i >= lista.size()) return null;

        // CASO BASE 2: Encontrado
        if (lista.get(i).getPatente().equalsIgnoreCase(patente)) {
            return lista.get(i);
        }

        // LLAMADA RECURSIVA: Siguiente índice
        return buscarRecursivo(lista, patente, i + 1);
    }
    */

    /**
     * Conteo Recursivo (Ej: Contar por Tipo).
     */
    /*
    private int contarPorTipo(ArrayList<Vehiculo> lista, String tipo, int i) {
        // Base
        if (i >= lista.size()) return 0;

        // Condición ternaria: Si coincide suma 1, sino 0
        int suma = lista.get(i).getTipo().equals(tipo) ? 1 : 0;

        // Recursión
        return suma + contarPorTipo(lista, tipo, i + 1);
    }
    */

    // ======================================================================
    // 4. OPERACIONES CRUD CON STREAMS (Lambdas)
    // ======================================================================

    public void operacionesLambda(List<Vehiculo> flota) {
        String patenteBuscada = "ABC1234";

        // A. VERIFICAR EXISTENCIA (anyMatch)
        // Retorna boolean. Útil para evitar duplicados al crear.
        boolean existe = flota.stream()
                .anyMatch(v -> v.getPatente().equalsIgnoreCase(patenteBuscada));
        
        if (existe) System.out.println("Error: Duplicado.");


        // B. ELIMINAR (removeIf)
        // Retorna boolean (true si borró algo).
        boolean eliminado = flota.removeIf(v -> v.getPatente().equalsIgnoreCase(patenteBuscada));
        
        if (eliminado) System.out.println("Eliminado correctamente.");


        // C. LISTAR (forEach)
        flota.forEach(v -> {
            System.out.println("Vehículo: " + v.getPatente());
            // v.mostrarDetalles();
        });


        // D. CÁLCULOS AVANZADOS (filter + map + sum)
        // Ejemplo: Costo total de mantenimiento de Camiones con > 1000 km
        /*
        double total = flota.stream()
                .filter(v -> v instanceof Camion)           // 1. Filtrar clase
                .filter(v -> v.getKilometraje() > 1000)     // 2. Filtrar condición
                .mapToDouble(v -> v.calcularCosto())        // 3. Mapear a número
                .sum();                                     // 4. Reducir
        */
    }

    // ======================================================================
    // 5. PATRÓN CRUD PARA EL MAIN (Scanner + Validaciones)
    // ======================================================================
    /*
    public static void crearVehiculoSeguro() {
        try {
            System.out.print("Ingrese Patente: ");
            String pat = scanner.nextLine();

            // Validación de negocio antes de crear
            if (flota.stream().anyMatch(v -> v.getPatente().equals(pat))) {
                System.out.println("Ya existe.");
                return;
            }

            // Creación (Puede lanzar Excepción)
            Vehiculo v = new Auto(pat);
            flota.add(v);
            System.out.println("Éxito.");

        } catch (PatenteInvalidaException e) {
            System.out.println("Error de validación: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("Error: Ingrese números válidos.");
        }
    }
    */
    
    // ======================================================================
    // 6. ACTUALIZACIÓN (Update)
    // ======================================================================
    /*
    public void modificar(String patente, int nuevoKm) {
        // 1. Buscar
        Vehiculo v = buscarRecursivo(lista, patente, 0);
        
        if (v != null) {
            // 2. Modificar (Paso por referencia)
            v.setKilometraje(nuevoKm);
            System.out.println("Actualizado.");
        } else {
            System.out.println("No encontrado.");
        }
    }
    */
}