package Veterinaria; 

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * ==========================================================================
 * 📄 HELPER: PATRONES Y UTILIDADES
 * ==========================================================================
 * Clase de soporte técnico con estructuras comunes.
 * Referencia para lógica de negocio, validaciones y manejo de datos.
 */
public class GuiaPractica {

    /*
     * ======================================================================
     * 1. IDENTIDAD (equals & hashCode)
     * ======================================================================
     * PATRÓN: Identificar objetos por CLAVE (Patente/Nombre) y no por memoria.
     * IMPORTANTE: Copiar en la clase Modelo (Vehiculo / Animalito).
     */
    
    /*
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true; 
        if (obj == null || getClass() != obj.getClass()) return false; 
        
        // 1. Casting (Ajustar clase según corresponda)
        Vehiculo other = (Vehiculo) obj; 
        
        // 2. Comparación (IgnoreCase para Strings)
        return this.patente.equalsIgnoreCase(other.patente);
    }

    @Override
    public int hashCode() {
        return java.util.Objects.hash(patente);
    }
    */

    /*
     * ======================================================================
     * 2. EXCEPCIONES PERSONALIZADAS
     * ======================================================================
     */

    // A. DEFINICIÓN
    /*
    public class DatoInvalidoException extends Exception {
        public DatoInvalidoException(String mensaje) {
            super(mensaje);
        }
    }
    */

    // B. USO EN CONSTRUCTOR
    /*
    public Avicolas(String especie, double peso) throws DatoInvalidoException {
        super(especie);
        if (peso < 1.0) {
            throw new DatoInvalidoException("Error: Peso insuficiente.");
        }
        this.peso = peso;
    }
    */

    /*
     * ======================================================================
     * 3. RECURSIVIDAD (Algoritmos de Búsqueda)
     * ======================================================================
     */

    // A. BÚSQUEDA
    /*
    private Vehiculo buscarRecursivo(ArrayList<Vehiculo> lista, String clave, int i) {
        // CASO BASE 1: Fin (No encontrado)
        if (i >= lista.size()) return null;
        
        // CASO BASE 2: Encontrado
        if (lista.get(i).getPatente().equalsIgnoreCase(clave)) {
            return lista.get(i);
        }
        
        // RECURSIÓN
        return buscarRecursivo(lista, clave, i + 1);
    }
    */

    // B. CONTEO
    /*
    private int contarTipoRecursivo(ArrayList<Vehiculo> lista, String tipo, int i) {
        if (i >= lista.size()) return 0;
        
        int suma = lista.get(i).getTipo().equals(tipo) ? 1 : 0;
        
        return suma + contarTipoRecursivo(lista, tipo, i + 1);
    }
    */

    /*
     * ======================================================================
     * 4. LAMBDAS Y STREAMS (Sintaxis compacta)
     * ======================================================================
     */

    public void ejemplosLambdas(List<String> lista) {
        String dato = "Ejemplo";

        // A. EXISTENCIA (anyMatch)
        boolean existe = lista.stream()
             .anyMatch(elem -> elem.equalsIgnoreCase(dato));
        
        if (existe) System.out.println("Registrado.");

        // B. ELIMINAR (removeIf)
        boolean eliminado = lista.removeIf(elem -> elem.equalsIgnoreCase(dato));

        // C. ITERAR (forEach)
        lista.forEach(elem -> System.out.println(elem));
    }

    /*
     * ======================================================================
     * 5. ESTRUCTURA CRUD (Referencia para Main)
     * ======================================================================
     */
    /*
    public static void crearObjeto() {
        try {
            System.out.print("Ingrese Clave: ");
            String clave = scanner.nextLine();

            if (lista.stream().anyMatch(v -> v.getClave().equals(clave))) {
                System.out.println("Error: Duplicado.");
                return;
            }

            Vehiculo nuevo = new Auto(..., clave);
            lista.add(nuevo);

        } catch (ExcepcionPropia e) {
            System.out.println("Datos inválidos: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("Error: Ingrese números válidos.");
        }
    }
    */
}