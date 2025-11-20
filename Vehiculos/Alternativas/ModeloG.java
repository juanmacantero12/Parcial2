package Vehiculos.Alternativas;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.InputMismatchException;

// 1. EXCEPCIONES
class PatenteInvalidaExceptionG extends Exception {
    public PatenteInvalidaExceptionG(String mensaje) {
        super(mensaje);
    }
}

// 2. INTERFAZ
interface MantenibleG {
    double calcularCostoMantenimiento();
}

// 3. CLASE PADRE
abstract class VehiculoG implements MantenibleG {
    private String marca;
    private String modelo;
    private int anio;
    private String patente;
    private double kilometraje;

    public VehiculoG(String marca, String modelo, int anio, String patente, double kilometraje) {
        this.marca = marca;
        this.modelo = modelo;
        this.anio = anio;
        this.patente = patente.toUpperCase();
        this.kilometraje = kilometraje;
    }

    public String getPatente() { return patente; }
    public double getKilometraje() { return kilometraje; }
    public void setKilometraje(double kilometraje) { this.kilometraje = kilometraje; }
    public String getMarca() { return marca; }
    public String getModelo() { return modelo; }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        VehiculoG vehiculo = (VehiculoG) obj;
        return patente.equals(vehiculo.patente);
    }

    @Override
    public int hashCode() {
        return patente.hashCode();
    }

    public abstract void mostrarDetalles();
}

// 4. SUBCLASES
class AutoG extends VehiculoG {
    private int cantidadPuertas;

    public AutoG(String marca, String modelo, int anio, String patente, double kilometraje, int cantidadPuertas) {
        super(marca, modelo, anio, patente, kilometraje);
        this.cantidadPuertas = cantidadPuertas;
    }

    @Override
    public double calcularCostoMantenimiento() {
        return getKilometraje() * 5.0;
    }

    @Override
    public void mostrarDetalles() {
        System.out.printf("AUTO (G) -> Patente: %s | Marca: %s | Puertas: %d | Mantenimiento: $%.2f%n", 
            getPatente(), getMarca(), cantidadPuertas, calcularCostoMantenimiento());
    }
}

class CamionG extends VehiculoG {
    private double capacidadCargaTon;

    public CamionG(String marca, String modelo, int anio, String patente, double kilometraje, double carga) {
        super(marca, modelo, anio, patente, kilometraje);
        this.capacidadCargaTon = carga;
    }

    @Override
    public double calcularCostoMantenimiento() {
        return getKilometraje() * 15.0 + (capacidadCargaTon * 100); 
    }

    @Override
    public void mostrarDetalles() {
        System.out.printf("CAMION (G) -> Patente: %s | Marca: %s | Carga: %.1f Ton | Mantenimiento: $%.2f%n", 
            getPatente(), getMarca(), capacidadCargaTon, calcularCostoMantenimiento());
    }
}

// 5. CLASE PRINCIPAL (ModeloG)
public class ModeloG {

    static ArrayList<VehiculoG> flota = new ArrayList<>(); 
    static Scanner sc = new Scanner(System.in); 

    public static void main(String[] args) {
        int opcion = 0;
        do {
            System.out.println("\n--- GESTIÓN DE FLOTA (MODELO GEMINI) ---");
            System.out.println("1. Registrar Vehículo");
            System.out.println("2. Listar (Lambda)");
            System.out.println("3. Buscar (Recursivo)");
            System.out.println("4. Actualizar Km");
            System.out.println("5. Eliminar (Lambda)");
            System.out.println("6. Salir");
            System.out.print("Opción: ");

            try {
                opcion = Integer.parseInt(sc.nextLine());
                switch (opcion) { 
                    case 1: registrarVehiculo(); break;
                    case 2: listarVehiculos(); break;
                    case 3: buscarVehiculoInterfaz(); break;
                    case 4: actualizarKilometraje(); break;
                    case 5: eliminarPorCondicion(); break;
                    case 6: System.out.println("Fin."); break;
                    default: System.out.println("Opción inválida.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Error: Ingrese un número.");
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        } while (opcion != 6); 
    }

    // METODOS ESTATICOS DE GESTION
    
    public static void registrarVehiculo() {
        try {
            System.out.print("Patente: ");
            String patente = sc.nextLine();

            if (patente.length() < 6) throw new PatenteInvalidaExceptionG("Patente muy corta.");
            
            if (flota.stream().anyMatch(v -> v.getPatente().equalsIgnoreCase(patente))) {
                System.out.println("Error: Ya existe.");
                return;
            }

            System.out.print("Marca: "); String marca = sc.nextLine();
            System.out.print("Modelo: "); String modelo = sc.nextLine();
            System.out.print("Año: "); int anio = Integer.parseInt(sc.nextLine());
            System.out.print("Km: "); double km = Double.parseDouble(sc.nextLine());
            System.out.print("Tipo (1: Auto, 2: Camion): ");
            int tipo = Integer.parseInt(sc.nextLine());

            if (tipo == 1) {
                System.out.print("Puertas: ");
                int puertas = Integer.parseInt(sc.nextLine());
                flota.add(new AutoG(marca, modelo, anio, patente, km, puertas));
            } else if (tipo == 2) {
                System.out.print("Carga (ton): ");
                double carga = Double.parseDouble(sc.nextLine());
                flota.add(new CamionG(marca, modelo, anio, patente, km, carga));
            }
            System.out.println("Registrado.");

        } catch (PatenteInvalidaExceptionG e) {
            System.out.println("Validación: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Error de datos.");
        }
    }

    public static void listarVehiculos() {
        if (flota.isEmpty()) System.out.println("Vacío.");
        else flota.forEach(VehiculoG::mostrarDetalles); 
    }

    public static void buscarVehiculoInterfaz() {
        System.out.print("Patente a buscar: ");
        String pat = sc.nextLine();
        int idx = buscarRecursivo(pat, 0);
        if (idx != -1) flota.get(idx).mostrarDetalles();
        else System.out.println("No encontrado.");
    }

    private static int buscarRecursivo(String pat, int i) {
        if (i >= flota.size()) return -1;
        if (flota.get(i).getPatente().equalsIgnoreCase(pat)) return i;
        return buscarRecursivo(pat, i + 1);
    }

    public static void actualizarKilometraje() {
        System.out.print("Patente: ");
        String pat = sc.nextLine();
        for (VehiculoG v : flota) {
            if (v.getPatente().equalsIgnoreCase(pat)) {
                System.out.print("Nuevo Km: ");
                double km = Double.parseDouble(sc.nextLine());
                v.setKilometraje(km);
                System.out.println("Actualizado.");
                return;
            }
        }
        System.out.println("No encontrado.");
    }

    public static void eliminarPorCondicion() {
        System.out.print("Eliminar con más de X km. X: ");
        try {
            double lim = Double.parseDouble(sc.nextLine());
            boolean borro = flota.removeIf(v -> v.getKilometraje() > lim);
            System.out.println(borro ? "Eliminados." : "Ninguno cumplía.");
        } catch (Exception e) {
            System.out.println("Error en número.");
        }
    }
}