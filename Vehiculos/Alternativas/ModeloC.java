package Vehiculos.Alternativas;
import java.util.ArrayList;
import java.util.Scanner;

// ==================== EXCEPCIÓN PERSONALIZADA ====================
class PatenteInvalidaExceptionC extends Exception {
    public PatenteInvalidaExceptionC(String mensaje) {
        super(mensaje);
    }
}

// ==================== INTERFAZ ====================
interface MantenibleC {
    double calcularCostoMantenimiento();
    String getIdentificador();
}

// ==================== CLASE PADRE VEHÍCULO ====================
class VehiculoC implements MantenibleC {
    private String marca;
    private String modelo;
    private int anio;
    private String patente;
    private String tipo;
    private int kilometraje;

    public VehiculoC(String marca, String modelo, int anio, String patente, String tipo, int kilometraje) {
        this.marca = marca;
        this.modelo = modelo;
        this.anio = anio;
        this.patente = patente;
        this.tipo = tipo;
        this.kilometraje = kilometraje;
    }

    public String getMarca() { return marca; }
    public void setMarca(String marca) { this.marca = marca; }
    public String getModelo() { return modelo; }
    public void setModelo(String modelo) { this.modelo = modelo; }
    public int getAnio() { return anio; }
    public void setAnio(int anio) { this.anio = anio; }
    public String getPatente() { return patente; }
    public void setPatente(String patente) { this.patente = patente; }
    public String getTipo() { return tipo; }
    public void setTipo(String tipo) { this.tipo = tipo; }
    public int getKilometraje() { return kilometraje; }
    public void setKilometraje(int kilometraje) { this.kilometraje = kilometraje; }

    public void mostrarInfo() {
        System.out.printf("Marca: %s, Modelo: %s, Año: %d, Patente: %s%n", 
                          marca, modelo, anio, patente);
    }

    public void mostrarInfo(boolean detallado) {
        if (detallado) {
            System.out.printf("=== Información Detallada ===%n");
            System.out.printf("Marca: %s%n", marca);
            System.out.printf("Modelo: %s%n", modelo);
            System.out.printf("Año: %d%n", anio);
            System.out.printf("Patente: %s%n", patente);
            System.out.printf("Tipo: %s%n", tipo);
            System.out.printf("Kilometraje: %d km%n", kilometraje);
        } else {
            mostrarInfo();
        }
    }

    @Override
    public double calcularCostoMantenimiento() {
        return kilometraje * 0.5;
    }

    @Override
    public String getIdentificador() {
        return patente;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        VehiculoC vehiculo = (VehiculoC) obj;
        return patente.equals(vehiculo.patente);
    }

    @Override
    public int hashCode() {
        return patente.hashCode();
    }
}

// ==================== SUBCLASES ====================
class AutoC extends VehiculoC {
    private int numeroPuertas;

    public AutoC(String marca, String modelo, int anio, String patente, int kilometraje, int numeroPuertas) {
        super(marca, modelo, anio, patente, "Auto", kilometraje);
        this.numeroPuertas = numeroPuertas;
    }

    public int getNumeroPuertas() { return numeroPuertas; }
    public void setNumeroPuertas(int numeroPuertas) { this.numeroPuertas = numeroPuertas; }

    @Override
    public double calcularCostoMantenimiento() {
        return super.calcularCostoMantenimiento() + 500;
    }

    @Override
    public void mostrarInfo(boolean detallado) {
        super.mostrarInfo(detallado);
        if (detallado) {
            System.out.printf("Número de puertas: %d%n", numeroPuertas);
        }
    }
}

class CamionC extends VehiculoC {
    private double capacidadCarga;

    public CamionC(String marca, String modelo, int anio, String patente, int kilometraje, double capacidadCarga) {
        super(marca, modelo, anio, patente, "Camion", kilometraje);
        this.capacidadCarga = capacidadCarga;
    }

    public double getCapacidadCarga() { return capacidadCarga; }
    public void setCapacidadCarga(double capacidadCarga) { this.capacidadCarga = capacidadCarga; }

    @Override
    public double calcularCostoMantenimiento() {
        return super.calcularCostoMantenimiento() + (capacidadCarga * 100);
    }

    @Override
    public void mostrarInfo(boolean detallado) {
        super.mostrarInfo(detallado);
        if (detallado) {
            System.out.printf("Capacidad de carga: %.2f toneladas%n", capacidadCarga);
        }
    }
}

class MotoC extends VehiculoC {
    private int cilindrada;

    public MotoC(String marca, String modelo, int anio, String patente, int kilometraje, int cilindrada) {
        super(marca, modelo, anio, patente, "Moto", kilometraje);
        this.cilindrada = cilindrada;
    }

    public int getCilindrada() { return cilindrada; }
    public void setCilindrada(int cilindrada) { this.cilindrada = cilindrada; }

    @Override
    public double calcularCostoMantenimiento() {
        return super.calcularCostoMantenimiento() + 300;
    }

    @Override
    public void mostrarInfo(boolean detallado) {
        super.mostrarInfo(detallado);
        if (detallado) {
            System.out.printf("Cilindrada: %d cc%n", cilindrada);
        }
    }
}

// ==================== CLASE PRINCIPAL (ModeloC) ====================
public class ModeloC {
    private static ArrayList<VehiculoC> listaVehiculos = new ArrayList<>();
    private static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        int opcion;
        
        do {
            mostrarMenu();
            opcion = leerEntero("Seleccione una opción: ");
            
            switch (opcion) {
                case 1: crearVehiculo(); break;
                case 2: listarVehiculos(); break;
                case 3: buscarVehiculoPorPatente(); break;
                case 4: modificarVehiculo(); break;
                case 5: eliminarVehiculo(); break;
                case 6: contarVehiculosPorTipo(); break;
                case 7: calcularCostosMantenimiento(); break;
                case 8: System.out.println("¡Gracias por usar el sistema!"); break;
                default: System.out.println("Opción inválida. Intente nuevamente.");
            }
        } while (opcion != 8);
        
        sc.close();
    }

    // ==================== MÉTODOS DEL SISTEMA ====================
    
    private static void mostrarMenu() {
        System.out.println("\n--- RENTAUTO S.A. (MODELO CLAUDE) ---");
        System.out.println("1. Crear vehículo");
        System.out.println("2. Listar todos");
        System.out.println("3. Buscar por patente");
        System.out.println("4. Modificar vehículo");
        System.out.println("5. Eliminar vehículo");
        System.out.println("6. Contar por tipo");
        System.out.println("7. Calcular costos");
        System.out.println("8. Salir");
    }

    private static void crearVehiculo() {
        System.out.println("\n=== CREAR VEHÍCULO ===");
        try {
            String patente = leerCadena("Patente (ej: ABC1234): ").toUpperCase();
            if (!validarPatente(patente)) throw new PatenteInvalidaExceptionC("Formato inválido");
            if (buscarVehiculoRecursivo(listaVehiculos, patente, 0) != null) {
                System.out.println("ERROR: Ya existe.");
                return;
            }
            
            String marca = leerCadena("Marca: ");
            String modelo = leerCadena("Modelo: ");
            int anio = leerEntero("Año: ");
            int kilometraje = leerEntero("Km: ");
            
            System.out.println("1. Auto | 2. Camión | 3. Moto");
            int tipo = leerEntero("Opción: ");
            VehiculoC nuevo = null;
            
            switch (tipo) {
                case 1: nuevo = new AutoC(marca, modelo, anio, patente, kilometraje, leerEntero("Puertas: ")); break;
                case 2: nuevo = new CamionC(marca, modelo, anio, patente, kilometraje, leerDecimal("Carga (ton): ")); break;
                case 3: nuevo = new MotoC(marca, modelo, anio, patente, kilometraje, leerEntero("Cilindrada: ")); break;
                default: System.out.println("Inválido."); return;
            }
            listaVehiculos.add(nuevo);
            System.out.println("Creado.");
            
        } catch (PatenteInvalidaExceptionC e) {
            System.out.println("ERROR: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("ERROR: " + e.getMessage());
        }
    }

    private static void listarVehiculos() {
        if (listaVehiculos.isEmpty()) { System.out.println("Vacío"); return; }
        listaVehiculos.forEach(v -> {
            System.out.println("----------------");
            v.mostrarInfo(true);
        });
    }

    private static void buscarVehiculoPorPatente() {
        String patente = leerCadena("Patente a buscar: ").toUpperCase();
        VehiculoC enc = buscarVehiculoRecursivo(listaVehiculos, patente, 0);
        if (enc != null) enc.mostrarInfo(true);
        else System.out.println("No encontrado.");
    }

    private static void modificarVehiculo() {
        String patente = leerCadena("Patente a modificar: ").toUpperCase();
        VehiculoC v = buscarVehiculoRecursivo(listaVehiculos, patente, 0);
        if (v == null) { System.out.println("No encontrado."); return; }
        
        System.out.println("1. Marca | 2. Modelo | 3. Año | 4. Km");
        int op = leerEntero("Opción: ");
        switch (op) {
            case 1: v.setMarca(leerCadena("Marca: ")); break;
            case 2: v.setModelo(leerCadena("Modelo: ")); break;
            case 3: v.setAnio(leerEntero("Año: ")); break;
            case 4: v.setKilometraje(leerEntero("Km: ")); break;
        }
        System.out.println("Modificado.");
    }

    private static void eliminarVehiculo() {
        String patente = leerCadena("Patente a eliminar: ").toUpperCase();
        boolean elim = listaVehiculos.removeIf(v -> v.getPatente().equalsIgnoreCase(patente));
        System.out.println(elim ? "Eliminado." : "No encontrado.");
    }

    private static void contarVehiculosPorTipo() {
        System.out.println("Autos: " + contarPorTipoRecursivo(listaVehiculos, "Auto", 0));
        System.out.println("Camiones: " + contarPorTipoRecursivo(listaVehiculos, "Camion", 0));
        System.out.println("Motos: " + contarPorTipoRecursivo(listaVehiculos, "Moto", 0));
    }

    private static void calcularCostosMantenimiento() {
        double total = listaVehiculos.stream().mapToDouble(VehiculoC::calcularCostoMantenimiento).sum();
        System.out.printf("Total Mantenimiento: $%.2f%n", total);
    }

    // RECURSIVIDAD
    private static VehiculoC buscarVehiculoRecursivo(ArrayList<VehiculoC> lista, String pat, int i) {
        if (i >= lista.size()) return null;
        if (lista.get(i).getPatente().equalsIgnoreCase(pat)) return lista.get(i);
        return buscarVehiculoRecursivo(lista, pat, i + 1);
    }

    private static int contarPorTipoRecursivo(ArrayList<VehiculoC> lista, String tipo, int i) {
        if (i >= lista.size()) return 0;
        int suma = lista.get(i).getTipo().equals(tipo) ? 1 : 0;
        return suma + contarPorTipoRecursivo(lista, tipo, i + 1);
    }

    // HELPERS
    private static boolean validarPatente(String p) {
        return p != null && p.length() >= 6;
    }
    private static String leerCadena(String m) { System.out.print(m); return sc.nextLine(); }
    private static int leerEntero(String m) {
        while(true) { try { System.out.print(m); return Integer.parseInt(sc.nextLine()); } catch(Exception e) {} }
    }
    private static double leerDecimal(String m) {
        while(true) { try { System.out.print(m); return Double.parseDouble(sc.nextLine()); } catch(Exception e) {} }
    }
}