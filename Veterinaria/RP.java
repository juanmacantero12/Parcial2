package Veterinaria; // O el package donde lo vayas a guardar

/**
 * ======================================================================================
 * 🎓 RESUMEN FINAL DEFENSA DE EXAMEN - PROGRAMACIÓN II
 * ======================================================================================
 * ESTE ARCHIVO CONTIENE LA JUSTIFICACIÓN TEÓRICA Y PRÁCTICA DE LOS DOS PROYECTOS:
 * 1. SISTEMA DE VEHÍCULOS (Flota, Vehiculo, Auto...)
 * 2. VETERINARIA (Inventario, Animalito, Avicolas...)
 * * FUENTES TEÓRICAS: Java3 (POO), Java4 (Wrappers), Java5 (Colecciones).
 */

public class RP {

    /*
     * ==================================================================================
     * 1. CLASES ABSTRACTAS vs INTERFACES (La pregunta más probable)
     * ==================================================================================
     * * [cite_start]➤ TEORÍA[cite: 180, 203, 531]:
     * - Clase Abstracta (abstract): Define "QUÉ ES" el objeto. No se puede instanciar.
     * Puede tener atributos y métodos con código. Se usa con 'extends'.
     * - Interfaz (interface): Define "QUÉ HACE" el objeto (Comportamiento).
     * Son solo firmas de métodos (contrato). Se usa con 'implements'.
     * * ➤ EN TUS PROYECTOS (PRÁCTICA):
     * * A) Abstractas: 'Vehiculo' y 'Animalito'.
     * ¿Por qué?: Porque no existe un "Vehículo" genérico en la concesionaria, ni un
     * "Animalito" en la veterinaria. Deben ser concretos (Auto/Moto, Ave/Felino).
     * Además, sirven para reutilizar código: todos heredan 'patente' o 'nombre'.
     * * B) Interfaces: 'Mantenible' (Vehiculos) y 'Cuidados' (Veterinaria).
     * ¿Por qué?: Porque es una capacidad.
     * - En Vehículos: Un Auto 'ES UN' Vehículo (Herencia), y 'TIENE' mantenimiento (Interfaz).
     * - En Veterinaria: Un Ave 'ES UN' Animalito, y 'RECIBE' cuidados.
     * * ➤ CÓDIGO CLAVE A DEFENDER:
     * public abstract class Vehiculo implements Mantenible { ... }
     */

    /*
     * ==================================================================================
     * 2. COLECCIONES Y GENÉRICOS (El motor del sistema)
     * ==================================================================================
     * * [cite_start]➤ TEORÍA[cite: 893, 1335]:
     * - ArrayList: Colección dinámica (crece sola). Acceso rápido por índice (.get(i)).
     * Es mejor que un Array[] simple porque no necesitamos saber el tamaño inicial.
     * - Genéricos (<T>): El diamante < > asegura la "Seguridad de Tipos".
     * * ➤ EN TUS PROYECTOS (PRÁCTICA):
     * * A) Declaración:
     * private List<Vehiculo> vehiculos;  (En Flota)
     * private ArrayList<Avicolas> avicolas; (En Inventario)
     * * ¿Por qué <Vehiculo> y no <Auto>? (Polimorfismo):
     * Al poner la clase PADRE en el genérico, la lista acepta a TODOS los hijos.
     * Así puedo mezclar Autos y Motos en la misma lista 'vehiculos'.
     * * B) Métodos Clave:
     * - .add(): Agregar.
     * - .removeIf(v -> ...): Borrar usando Lambda (Más eficiente que un for).
     * - .stream().anyMatch(...): Buscar si existe.
     */

    /*
     * ==================================================================================
     * 3. MÉTODOS DE LA CLASE OBJECT (equals, hashCode, toString)
     * ==================================================================================
     * * [cite_start]➤ TEORÍA[cite: 737, 769, 842]:
     * - toString(): Representación en texto. Si no lo redefines, sale la dirección de memoria.
     * - equals(): Compara CONTENIDO. '==' compara REFERENCIAS (memoria).
     * * ➤ EN TUS PROYECTOS (PRÁCTICA):
     * * A) El problema del 'contains':
     * Si haces 'lista.contains(nuevoVehiculo)' sin tener 'equals', Java compara punteros.
     * Como el objeto es nuevo, siempre dirá "Falso, no existe", aunque la patente sea igual.
     * * B) Tu Solución:
     * En 'Vehiculo.java' y 'Animalito.java' sobreescribiste 'equals' para comparar
     * por PATENTE y por NOMBRE respectivamente.
     * * @Override
     * public boolean equals(Object o) { ... return this.patente.equals(otro.patente); }
     */

    /*
     * ==================================================================================
     * 4. EXCEPCIONES (Manejo de Errores)
     * ==================================================================================
     * * [cite_start]➤ TEORÍA[cite: 587, 618]:
     * - Checked Exception (Exception): El compilador te obliga a usar try-catch.
     * - throw: Lanza el error.
     * - throws: Avisa que el método es peligroso.
     * * ➤ EN TUS PROYECTOS (PRÁCTICA):
     * * A) Creación: 'PatenteInvalidaException' y 'PesoInsuficienteException'.
     * * B) Lanzamiento (En el Constructor):
     * if (peso < 1.0) throw new PesoInsuficienteException("Muy flaco");
     * Esto evita que nazcan objetos "inválidos" en el sistema.
     * * C) Captura (En el Main):
     * try {
     * crearVehiculo(...);
     * } catch (PatenteInvalidaException e) {
     * System.out.println("Error: " + e.getMessage());
     * }
     * Esto evita que el programa se cierre (crash) en la cara del usuario.
     */

    /*
     * ==================================================================================
     * 5. RECURSIVIDAD (El punto difícil de Vehículos)
     * ==================================================================================
     * * ➤ PRÁCTICA (Solo en SistemaVehiculos - Método buscarVehiculoRecursivo):
     * * 1. Método Público (Llamada inicial):
     * public Vehiculo buscar(String patente) { return buscarAux(patente, 0); }
     * * 2. Método Privado (Lógica):
     * private Vehiculo buscarAux(String patente, int i) {
     * // CASO BASE 1 (Fracaso): Llegué al final de la lista.
     * if (i >= lista.size()) return null;
     * * // CASO BASE 2 (Éxito): Lo encontré.
     * if (lista.get(i).getPatente().equals(patente)) return lista.get(i);
     * * // LLAMADA RECURSIVA: Sigo buscando en el siguiente (i + 1)
     * return buscarAux(patente, i + 1);
     * }
     * * ➤ SI TE PIDEN HACERLO EN VETERINARIA:
     * Es el mismo patrón. Solo cambia 'lista' por 'avicolas' y 'getPatente' por 'getNombre'.
     */

    /*
     * ==================================================================================
     * 6. WRAPPERS Y STRINGS
     * ==================================================================================
     * * [cite_start]➤ TEORÍA[cite: 6, 48]:
     * - String es INMUTABLE. 'nombre.toUpperCase()' NO cambia el nombre, crea uno nuevo.
     * - Wrappers: Integer, Double. Necesarios porque ArrayList no acepta 'int'.
     * * ➤ PRÁCTICA:
     * - En constructores: 'this.nombre = nombre.toUpperCase();' (Asignación obligatoria).
     * - En Listas: 'ArrayList<Integer>' (Uso de Wrapper).
     */
}
