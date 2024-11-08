package A2UD1;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class EJ1_A2UD1 {
    public static void main(String[] args) {
        System.out.println("Menú de opciones\n"
                + "----------------------\n" +
                "[P] Listado por pantalla\n" +
                "[D] Listado a un fichero\n" +
                "------------------------\n" +
                "Elija opción ----------->:\n");

        Scanner sc = new Scanner(System.in);
        String opcion = sc.nextLine().toUpperCase();

        switch (opcion) {
            case "P":
                listarDiscosPantalla();
                break;
            case "D":
                listarDiscosFichero();
                break;
            default:
                System.out.println("Opción no válida");
                break;
        }

        sc.close();
    }

    public static void listarDiscosPantalla() {
        System.out.println("---LISTANDO UNIDADES---");
        metodoListadoUnidades();
        }

    public static void metodoListadoUnidades() {
        int valorGb = 1024 * 1024 * 1024;
        File[] unidades = File.listRoots();
        for (File unidad : unidades) {
            double espacioLibreGB = (double) unidad.getFreeSpace() / valorGb;
            double espacioOcupadoGB = (double) (unidad.getTotalSpace() - unidad.getFreeSpace()) / valorGb;
            double espacioTotalGB = (double) unidad.getTotalSpace() / valorGb;
            System.out.println("Unidad " + unidad);
            System.out.printf("Espacio libre: %.2f GB\n", espacioLibreGB);
            System.out.printf("Espacio ocupado: %.2f GB\n", espacioOcupadoGB);
            System.out.printf("Espacio total: %.2f GB\n", espacioTotalGB);
        }
    }

    public static void listarDiscosFichero() {
        File[] unidades = File.listRoots();
        int valorGb = 1024 * 1024 * 1024;
        File directorio = new File("Discos.txt");

        try (FileWriter escritor = new FileWriter(directorio)) {
            escritor.write("---LISTANDO UNIDADES---\n");
            for (File unidad : unidades) {
                double espacioLibreGB = (double) unidad.getFreeSpace() / valorGb;
                double espacioOcupadoGB = (double) (unidad.getTotalSpace() - unidad.getFreeSpace()) / valorGb;
                double espacioTotalGB = (double) unidad.getTotalSpace() / valorGb;

                escritor.write("Unidad " + unidad + "\n");
                escritor.write(String.format("Espacio libre: %.2f GB\n", espacioLibreGB));
                escritor.write(String.format("Espacio ocupado: %.2f GB\n", espacioOcupadoGB));
                escritor.write(String.format("Espacio total: %.2f GB\n", espacioTotalGB));
                escritor.write("\n");
            }
            System.out.println("El listado se ha guardado en: " + directorio.getAbsolutePath());
        } catch (IOException e) {
            System.out.println("Error al escribir en el fichero: " + e.getMessage());
        }
    }
}