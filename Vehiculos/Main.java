package Vehiculos;

import java.util.Scanner;

public class Main {
    
    // Justificación: Sistema de gestión de flota para una empresa de reparto.
    
    public static void main(String[] args) {
        Flota flota = new Flota();
        Scanner scanner = new Scanner(System.in);
        int opcion;

        // Uso de do-while (Requisito)
        do {
            mostrarMenuPrincipal();
            String input = scanner.nextLine();

            try {
                opcion = Integer.parseInt(input);
            } catch (NumberFormatException e) {
                opcion = 0; // Sale del bucle si no es número
            }

            // Uso de switch (Requisito)
            switch (opcion) {
                case 1: crearVehiculo(flota, scanner); break;
                case 2: flota.listarTodos(); break;
                case 3: buscarVehiculo(flota, scanner); break;
                case 4: actualizarVehiculo(flota, scanner); break;
                case 5: eliminarVehiculo(flota, scanner); break;
                case 6: calcularEstadisticas(flota); break;
                default: System.out.println("Saliendo del sistema...");
            }
            
            // Uso de if / else if / else y break (Requisito)
            if (opcion < 1 || opcion > 6) {
                break;
            } else if (opcion != 0) {
                System.out.print("\nPresione ENTER para continuar...");
                scanner.nextLine();
            } else {
                System.out.println("Opción inválida. Intente de nuevo.");
            }

        } while (true);

        // Uso de finally para asegurar el cierre de recursos (Requisito)
        try {
            if (scanner != null) scanner.close();
        } finally {
            System.out.println("\n✅ Sesión finalizada.");
        }
    }
    
    private static void mostrarMenuPrincipal() {
        System.out.println("=".repeat(40));
        System.out.println("      ADMINISTRACIÓN DE FLOTA");
        System.out.println("=".repeat(40));
        System.out.println("1. Crear nuevo vehículo");
        System.out.println("2. Listar toda la flota");
        System.out.println("3. Buscar vehículo por patente (Recursivo)");
        System.out.println("4. Actualizar kilometraje/modelo");
        System.out.println("5. Eliminar vehículo");
        System.out.println("6. Mostrar estadísticas");
        System.out.println("0. Salir");
        System.out.print(">>> Elija una opción: ");
    }
    
    private static void crearVehiculo(Flota flota, Scanner scanner) {
        try {
            System.out.println("\n--- CREAR VEHÍCULO ---");
            System.out.print("Patente (7 chars, Ej. ABC1234): ");
            String patente = scanner.nextLine();
            System.out.print("Marca: ");
            String marca = scanner.nextLine();
            System.out.print("Modelo (Año): ");
            int modelo = Integer.parseInt(scanner.nextLine());
            System.out.print("Kilometraje: ");
            int km = Integer.parseInt(scanner.nextLine());

            System.out.print("Tipo (1=Auto, 2=Moto, 3=Colectivo): ");
            int tipo = Integer.parseInt(scanner.nextLine());

            Vehiculo nuevoVehiculo = null;

            // Uso de switch
            switch (tipo) {
                case 1: 
                    System.out.print("Puertas: ");
                    int puertas = Integer.parseInt(scanner.nextLine());
                    nuevoVehiculo = new Auto(marca, modelo, patente, km, puertas); 
                    break;
                case 2: 
                    nuevoVehiculo = new Moto(marca, modelo, patente, km); 
                    break;
                case 3: 
                    System.out.print("Asientos: ");
                    int asientos = Integer.parseInt(scanner.nextLine());
                    nuevoVehiculo = new Colectivo(marca, modelo, patente, km, asientos); 
                    break;
                default: 
                    System.out.println("Tipo de vehículo no reconocido.");
                    return;
            }

            if (flota.crearVehiculo(nuevoVehiculo)) {
                System.out.println("✅ Vehículo creado y añadido.");
            } else {
                System.out.println("❌ Error: Patente ya registrada.");
            }

        } catch (NumberFormatException e) {
            System.out.println("❌ Error de formato de número. Intente de nuevo.");
        } catch (PatenteInvalidaException e) {
            // Manejo de la Excepción Personalizada (Requisito)
            System.out.println("❌ Error de Patente: " + e.getMessage());
        }
    }
    
    private static void buscarVehiculo(Flota flota, Scanner scanner) {
        System.out.print("\n--- BUSCAR VEHÍCULO ---\nPatente a buscar: ");
        String patente = scanner.nextLine();
        
        // Búsqueda recursiva (Requisito)
        Vehiculo v = flota.buscarVehiculoRecursivo(patente); 
        
        if (v != null) {
            // Uso de printf (Requisito)
            System.out.printf("✅ Encontrado: %s | Patente: %s\n", v.verTipoDeVehiculo(), v.getPatente());
            // Uso de length() y toUpperCase() (Requisito)
            System.out.printf("Patente en mayúsculas tiene %d caracteres.\n", v.getPatente().toUpperCase().length()); 
        } else {
            System.out.println("❌ Vehículo no encontrado.");
        }
    }
    
    private static void actualizarVehiculo(Flota flota, Scanner scanner) {
        try {
            System.out.print("\n--- ACTUALIZAR VEHÍCULO ---\nPatente del vehículo: ");
            String patente = scanner.nextLine();
            System.out.print("Nuevo kilometraje: ");
            int km = Integer.parseInt(scanner.nextLine());
            System.out.print("Nuevo modelo (Año): ");
            int modelo = Integer.parseInt(scanner.nextLine());

            if (flota.actualizarVehiculo(patente, km, modelo)) {
                System.out.println("✅ Datos actualizados.");
            } else {
                System.out.println("❌ Patente no encontrada.");
            }
        } catch (NumberFormatException e) {
            System.out.println("❌ Error: Kilometraje o modelo deben ser números.");
        }
    }
    
    private static void eliminarVehiculo(Flota flota, Scanner scanner) {
        System.out.print("\n--- ELIMINAR VEHÍCULO ---\nPatente a eliminar: ");
        String patente = scanner.nextLine();
        
        // Uso de removeIf dentro de Flota (Lambda/Stream)
        if (flota.eliminarVehiculo(patente)) {
            System.out.println("✅ Vehículo eliminado.");
        } else {
            System.out.println("❌ Patente no encontrada.");
        }
    }
    
    private static void calcularEstadisticas(Flota flota) {
        System.out.println("\n--- ESTADÍSTICAS ---");
        // Uso de while y continue dentro de Flota
        System.out.println("Total de Autos: " + flota.contarVehiculosTipo(1));
        System.out.println("Total de Motos: " + flota.contarVehiculosTipo(2));
        System.out.println("Total de Colectivos: " + flota.contarVehiculosTipo(3));
        
        // Otro ejemplo de Lambda/Stream (Opcional, pero bueno para el examen)
        long vehiculosLargoRecorrido = flota.getVehiculos().stream()
            .filter(v -> v.getKilometraje() > 10000)
            .count();

        System.out.println("Vehículos con >10,000 km: " + vehiculosLargoRecorrido);
    }
}
