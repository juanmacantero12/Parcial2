# 📚 REPASO TEÓRICO - SEGUNDO PARCIAL JAVA

Preguntas de multiple choice analizadas y corregidas.

---

## 1. Colecciones e Iteradores
**Pregunta:** Seleccione la afirmación INCORRECTA sobre iteradores.
* **Respuesta Correcta (la falsa):** ❌ "Únicamente las clases que implementan la interfaz List permiten el uso de iteradores."
* **Explicación:** Esto es falso porque **todas** las colecciones que implementan `Iterable` (como `Set`, `Queue`, no solo `List`) pueden usar iteradores.

## 2. Inmutabilidad de Strings (Clase Maize)
**Pregunta:** ¿Cuál es el resultado del código donde se hace `s.concat("ab")` sin asignarlo?
* **Respuesta Correcta:** "12"
* **Explicación:** Los Strings son inmutables. Si haces `s.concat(...)` pero no guardas el resultado en una variable, el cambio se pierde. La variable `s` original nunca cambió.

## 3. Contratos de Interfaz
**Pregunta:** ¿Qué garantiza que `Docentes implements InformeDeDatos`?
* **Respuesta Correcta:** Que la clase Docentes debe implementar el método informe.
* **Explicación:** Es un contrato obligatorio. Si implementas una interfaz, estás obligado a escribir el código de sus métodos abstractos.

## 4. Comparación de Strings
**Pregunta:** ¿Cómo se comparan dos cadenas `c1` y `c2` por su contenido?
* **Respuesta Correcta:** `c1.equals(c2)`
* **Explicación:** `==` compara si son el mismo objeto en memoria (referencia). `.equals()` compara si las letras dentro son las mismas (contenido).

## 5. Excepciones con Archivos (File)
**Pregunta:** `File file = new File("/folder", nombre);` donde `nombre` es null.
* **Respuesta Correcta:** `NullPointerException` en línea 2.
* **Explicación:** El constructor de `File` no acepta `null` como nombre de archivo hijo. Falla inmediatamente.

## 6. NullPointerException y Wrappers (ComparadorRaro)
**Pregunta:** `private Integer x;` (es null). Luego `return x == y;`.
* **Respuesta Correcta:** Se lanza una Excepción durante la ejecución (`NullPointerException`).
* **Explicación:** La variable `x` es un objeto `Integer` y vale `null`. Cuando Java intenta compararlo con un `int` primitivo (`y`), intenta extraer el valor numérico (*auto-unboxing*). Como es null, explota. (Nota: El código SÍ compila, el error es al correrlo).

## 7. Diferencia start() vs run()
* **t1.start():** Crea un nuevo hilo paralelo.
* **t2.run():** Ejecuta el código en el mismo hilo principal (bloqueante), no crea concurrencia real.

## 8. Tiempos en Threads (Sincronización)
**Pregunta:** Dos hilos (`Checkout2`) corren en paralelo. ¿Tiempo total?
* **Respuesta Correcta:** Alrededor de 8 o 9 segundos.
* **Explicación:** Se crean dos objetos `Checkout2` distintos (`new Checkout2()`). El bloqueo `synchronized` es sobre el objeto (`this`). Como son objetos distintos, no se bloquean entre sí. Corren en paralelo. El tiempo es el del hilo más lento, no la suma de ambos.

## 9. Salida de Concat (Maize con return)
**Pregunta:** Método `go(String s)` que concatena "56" pero retorna `s` original.
* **Respuesta Correcta:** "12"
* **Explicación:** Dentro del método `go`, se hace `s.concat("56")`, pero el resultado se ignora. Luego se hace `return s;` devolviendo el String original sin cambios.
---
## 📝 NUEVAS PREGUNTAS (Análisis de Imágenes Batch 2)

### 10. Herencia y Equals (Caso Noodle/AsianNoodle) [IMAGEN DEL CÓDIGO LARGO]
**Pregunta:** ¿Cuál es el resultado por consola?
* **Código:** `Noodle` no tiene equals. `AsianNoodle` compara nombres. `Soba` hereda de `AsianNoodle`.
* **Análisis:**
    1. `n1.equals(n2)`: Son clase `Noodle`. Usan equals de Object (referencia). **FALSE**.
    2. `a1.equals(a2)`: Son `AsianNoodle`. Comparan nombre "fred". **TRUE**.
    3. `s1.equals(s2)`: Son `Soba`. Heredan equals de Asian. Comparan "jill". **TRUE**.
* **Respuesta Correcta:** `false false | true false | true false`
* **Corrección:** En la imagen seleccionaste la opción que empieza con *true*, pero es incorrecta porque la clase padre `Noodle` no sobrescribe `equals`.

### 11. Estructura para elementos únicos (Sin orden)
**Pregunta:** Clase que almacene objetos únicos, sin importar el orden.
* **Respuesta Correcta:** **d. Set**
* **Corrección:** En la imagen seleccionaste *Map*. Un Map es para pares Clave-Valor. Para guardar elementos únicos "sueltos", se usa un Set (Conjunto).

### 12. Try-Catch con flujo extraño (i++ j--)
**Pregunta:** Resultado del código `test`.
* **Análisis:**
    * `i=1, j=1`.
    * `try`: `i` sube a 2. `j` baja a 0. `if(i==j)` (2==0) es Falso.
    * No hay excepciones (división por cero ni index out of bounds).
    * `finally`: Imprime **3**.
    * Final: Imprime **4**.
* **Respuesta Correcta:** **d. 3,4**

### 13. Definición de Set
**Pregunta:** Un Set es una estructura...
* **Respuesta Correcta:** **c. Que almacena cada elemento una sola vez como máximo. No mantiene un orden específico.**
* **Corrección:** Un Set no garantiza orden (a menos que sea TreeSet/LinkedHashSet, pero la definición general es sin orden). Lo más importante es que no admite duplicados (1 vez máximo).

### 14. StringBuffer y Equals (Twine)
**Pregunta:** Comparación de StringBuffers.
* **Respuesta Correcta:** **"3 4 5 "**
* **Explicación:** `StringBuffer` no tiene método `equals` (usa el de Object). Solo da true si son el mismo objeto (`sb3` y `sb4`). Los Strings (`s2`, `s3`) sí comparan texto.

### 15. HashSet de Object
**Pregunta:** Se agregan `boolean`, `String`, `int` a un `HashSet<Object>`.
* **Respuesta Correcta:** **b. Se muestran JAVA 5 y true en un orden no determinado.**
* **Explicación:** El Set elimina duplicados (el segundo `true` se ignora) y no garantiza el orden de impresión.

### 16. HashMap declaración
**Pregunta:** ¿Cuál opción es correcta para agregar a `Map<String, Double>`?
* **Respuesta Correcta:** **d. Ninguna de las anteriores.**
* **Explicación:** Los mapas usan el método `.put(clave, valor)`, no `.add()`.