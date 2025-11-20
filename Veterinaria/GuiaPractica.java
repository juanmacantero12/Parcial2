package Veterinaria; // O package Vehiculos;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * ==========================================================================
 * 📄 GUÍA PRÁCTICA DE CÓDIGO (MACHETE TÉCNICO)
 * ==========================================================================
 * Resumen de patrones de código para el Examen.
 * INCLUYE: Recursividad, Lambdas, Excepciones y CRUD.
 */
public class GuiaPractica {

    /*
     * ======================================================================
     * 1. IDENTIDAD (equals & hashCode)
     * ======================================================================
     * OBJETIVO: Que 'contains' y 'remove' encuentren objetos por SU CLAVE
     * (Patente o Nombre) y no por memoria.
     * * COPIAR Y PEGAR EN LA CLASE MODELO (Vehiculo / Animalito):
     */
    
    /*
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true; // Misma memoria
        if (obj == null || getClass() != obj.getClass()) return false; // Misma clase
        
        // 1. Casting (Cambiar 'Vehiculo' por la clase que corresponda)
        Vehiculo other = (Vehiculo) obj; 
        
        // 2. Comparación (Usar equalsIgnoreCase para Strings)
        // Cambiar 'patente' por 'nombre' si es Veterinaria
        return this.patente.equalsIgnoreCase(other.patente);
    }

    @Override
    public int hashCode() {
        // Retorna el hash del atributo clave
        return java.util.Objects.hash(patente);
    }
    */

    /*
     * ======================================================================
     * 2. EXCEPCIONES PERSONALIZADAS (Validación)
     * ======================================================================
     */

    // A. DEFINICIÓN DE LA CLASE DE ERROR
    /*
    public class DatoInvalidoException extends Exception {
        public DatoInvalidoException(String mensaje) {
            super(mensaje);
        }
    }
    */

    // B. USO EN CONSTRUCTOR (Para validar datos al crear)
    /*
    public Avicolas(String especie, double peso) throws DatoInvalidoException {
        super(especie);
        
        // Validación
        if (peso < 1.0) {
            throw new DatoInvalidoException("Error: Peso insuficiente (< 1.0 kg).");
        }
        
        this.peso = peso;
    }
    */

    /*
     * ======================================================================
     * 3. RECURSIVIDAD (Patrones de Búsqueda y Conteo)
     * ======================================================================
     * NOTA: Siempre se necesitan dos métodos: el Público (que llama alguien)
     * y el Privado (que hace la lógica con el índice).
     */

    // A. PATRÓN DE BÚSQUEDA (Buscar por Patente/Nombre)
    /*
    // Método Privado Auxiliar
    private Vehiculo buscarRecursivo(ArrayList<Vehiculo> lista, String clave, int i) {
        // 1. CASO BASE: Fin de la lista (No encontrado)
        if (i >= lista.size()) {
            return null;
        }
        
        // 2. CASO BASE: Encontrado
        if (lista.get(i).getPatente().equalsIgnoreCase(clave)) {
            return lista.get(i);
        }
        
        // 3. LLAMADA RECURSIVA: Probar con el siguiente (i + 1)
        return buscarRecursivo(lista, clave, i + 1);
    }
    */

    // B. PATRÓN DE CONTEO (Contar cuántos "Autos" hay)
    /*
    private int contarTipoRecursivo(ArrayList<Vehiculo> lista, String tipo, int i) {
        // 1. CASO BASE: Fin
        if (i >= lista.size()) return 0;
        
        // 2. CÁLCULO: Si coincide suma 1, sino 0
        int suma = lista.get(i).getTipo().equals(tipo) ? 1 : 0;
        
        // 3. RECURSIÓN: Sumar actual + el resto
        return suma + contarTipoRecursivo(lista, tipo, i + 1);
    }
    */

    /*
     * ======================================================================
     * 4. LAMBDAS Y STREAMS (Operaciones en una línea)
     * ======================================================================
     */

    public void ejemplosLambdas(List<String> lista) {
        String dato = "Ejemplo";

        // A. VERIFICAR EXISTENCIA (anyMatch) -> Devuelve boolean
        // -----------------------------------------------------
        boolean existe = lista.stream()
             .anyMatch(elem -> elem.equalsIgnoreCase(dato));
        
        if (existe) System.out.println("Ya existe!");


        // B. ELIMINAR POR CONDICIÓN (removeIf) -> Devuelve boolean
        // -----------------------------------------------------
        // "Borrar si el elemento es igual al dato"
        boolean eliminado = lista.removeIf(elem -> elem.equalsIgnoreCase(dato));


        // C. LISTAR / MOSTRAR (forEach)
        // -----------------------------------------------------
        lista.forEach(elem -> System.out.println(elem));
        
        // Versión bloque (para hacer más cosas):
        /*
        lista.forEach(v -> {
            v.mostrarDetalles();
            System.out.println("----------------");
        });
        */


        // D. FILTRAR Y CALCULAR (filter + map)
        // -----------------------------------------------------
        // Ejemplo: Sumar costos solo de Camiones
        /*
        double total = listaVehiculos.stream()
            .filter(v -> v instanceof Camion)       // 1. Solo camiones
            .mapToDouble(v -> v.calcularCosto())    // 2. Convertir objeto a precio
            .sum();                                 // 3. Sumar
        */
    }

    /*
     * ======================================================================
     * 5. ESTRUCTURA CRUD SEGURA (Para el Main)
     * ======================================================================
     */
    /*
    public static void crearObjetoSeguro() {
        try {
            // 1. Pedir dato clave
            System.out.print("Ingrese Patente: ");
            String patente = scanner.nextLine();

            // 2. Validar si existe (usando lambda o recursividad)
            if (flota.stream().anyMatch(v -> v.getPatente().equals(patente))) {
                System.out.println("Error: Ya existe.");
                return; // Salir
            }

            // 3. Crear (Esto puede lanzar la Excepción Personalizada)
            Vehiculo nuevo = new Auto(..., patente);
            
            // 4. Guardar
            flota.add(nuevo);
            System.out.println("Éxito!");

        } catch (PatenteInvalidaException e) {
            // 5. Capturar error de lógica de negocio
            System.out.println("Dato inválido: " + e.getMessage());

        } catch (NumberFormatException e) {
            // 6. Capturar error de usuario escribiendo letras en números
            System.out.println("Error: Ingrese solo números.");
        }
    }
    */
}