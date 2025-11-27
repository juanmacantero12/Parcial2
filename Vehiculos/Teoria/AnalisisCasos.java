package Parcial2.Teoria;

import java.io.File;
import java.util.*;

/**
 * Clase de análisis de casos borde y comportamiento de la JVM
 * para validación de conceptos de POO y Estructuras de Datos.
 */

public class AnalisisCasos {

    public static void main(String[] args) {
        System.out.println("--- INICIO DE VALIDACIÓN DE CONCEPTOS ---");
        validarColecciones();
        validarStrings();
        validarExcepciones();
        validarThreads();
        validarHerencia();
    }

    // -------------------------------------------------------------------------
    // 1. ITERADORES Y COLECCIONES
    // -------------------------------------------------------------------------
    public static void validarColecciones() {
        /* CASO 1: Iteradores
         * Hipótesis Incorrecta: "Solo List permite iteradores".
         * Realidad: TODAS las clases que implementan Iterable (Set, Queue, List) lo permiten.
         */
        Set<String> setPrueba = new HashSet<>();
        Iterator<String> it = setPrueba.iterator(); // Compila -> Demuestra que Set tiene iterador.
        
        /* CASO 11 & 13: Definición de Set
         * Estructura para elementos únicos SIN orden específico.
         */
        // Set<Object> s = new HashSet<>(); // No mantiene orden, 1 elemento max por valor.

        /* CASO 15: HashSet de Object
         * Se insertan: boolean, String, int, boolean.
         * Resultado: Se eliminan duplicados (true aparece 1 vez) y el orden es aleatorio.
         * Salida esperada: "JAVA 5 true" (orden no garantizado).
         */

        /* CASO 16: Mapas
         * Error común: map.add().
         * Correcto: map.put(k,v). Map no hereda de Collection, no tiene add().
         */
    }

    // -------------------------------------------------------------------------
    // 2. STRINGS Y WRAPPERS
    // -------------------------------------------------------------------------
    public static void validarStrings() {
        /* CASO 2 & 9: Inmutabilidad (Clase Maize)
         * Código: s.concat("ab"); sin asignación.
         * Resultado: "12".
         * Por qué: String es inmutable. Si no se asigna el retorno, el cambio se pierde.
         * Definición (Caso 19): Objeto cuyo estado no cambia una vez creado.
         */
        String s = "12";
        s.concat("ab"); // Se pierde en el limbo
        // System.out.println(s); // Imprime "12"

        /* CASO 4: Comparación
         * == compara referencias de memoria.
         * .equals() compara contenido (semántica).
         * Para cadenas siempre usar .equals().
         */
        
        /* CASO 14: StringBuffer (Clase Twine)
         * StringBuffer NO sobrescribe equals(). Usa el de Object (==).
         * Resultado visual del ejercicio: "3 4 5 "
         * (Solo da true cuando se comparan referencias del mismo objeto sb3 y sb4).
         */
    }

    // -------------------------------------------------------------------------
    // 3. EXCEPCIONES Y ARCHIVOS
    // -------------------------------------------------------------------------
    public static void validarExcepciones() {
        /* CASO 5: File con nulo
         * File f = new File("/folder", null);
         * Resultado: NullPointerException en constructor (Línea 2).
         */

        /* CASO 6: Auto-unboxing de Null (ComparadorRaro)
         * Integer x = null;
         * return x == y; // Intenta convertir null a int primitivo.
         * Resultado: NullPointerException en TIEMPO DE EJECUCIÓN (El código compila bien).
         */

        /* CASO 12: Flujo Try-Catch (i++ j--)
         * Lógica: i=1, j=1. En try: i->2, j->0. if(i==j) falso.
         * No hay excepción. Finally imprime 3. Fuera imprime 4.
         * Resultado: "3,4".
         */
        
        /* CASO 17: Jerarquía
         * Throwable es padre de Error y Exception.
         * RuntimeException es unchecked.
         */
    }

    // -------------------------------------------------------------------------
    // 4. THREADS (HILOS)
    // -------------------------------------------------------------------------
    public static void validarThreads() {
        /* CASO 7: Start vs Run
         * .start() -> Inicia nuevo hilo (concurrencia real).
         * .run() -> Ejecuta el método en el hilo actual (bloqueante, no es hilo nuevo).
         */

        /* CASO 8: Sincronización (Checkout2)
         * Escenario: 2 objetos distintos (new Checkout2()).
         * synchronized bloquea 'this'. Al ser objetos distintos, NO se bloquean.
         * Tiempos: Se ejecutan en paralelo.
         * Total: Tiempo del más lento (~8-9 seg), no la suma (12s).
         */
    }

    // -------------------------------------------------------------------------
    // 5. HERENCIA Y POLIMORFISMO
    // -------------------------------------------------------------------------
    public static void validarHerencia() {
        /* CASO 3: Interfaces
         * "implements" es un contrato estricto.
         * Garantiza que la clase TIENE que implementar los métodos abstractos.
         */

        /* CASO 10: Equals en Herencia (Noodle/AsianNoodle)
         * Noodle n1, n2: Usan Object.equals (referencia) -> False.
         * AsianNoodle a1, a2: Sobreescriben equals (nombre) -> True.
         * Soba s1, s2: Heredan de AsianNoodle -> True.
         * Salida: false false | true false | true false
         */
    }
    
    // -------------------------------------------------------------------------
    // 6. LOGICA ALGORITMICA
    // -------------------------------------------------------------------------
    public static int metodoIncognita(String input) {
        /* CASO 18: Conteo de Vocales
         * Input: "Otorrinolaringologo"
         * Switch case con 'a','e','i','o','u'.
         * Resultado: 9 vocales.
         */
        return 9;
    }
}