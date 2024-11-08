package A3UD1;

import java.io.*;
import java.util.Random;
import java.util.Scanner;

public class pruebaEj1 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Introduce el nombre para el fichero (sin extensión): ");
        String nombreFichero = sc.nextLine() + ".bin"; // Añadir extensión binaria al fichero
        File archivo = new File(nombreFichero);

        if (archivo.exists()) {
            System.out.println("El fichero ya existe. ¿Desea sobrescribirlo? (S/N): ");
            String respuesta = sc.nextLine();
            if (!respuesta.equalsIgnoreCase("S")) {
                System.out.println("Operación cancelada.");
                sc.close();
                return; // Salir si no desea sobrescribir
            }
        }

        try {
            escribirFicheroAleatorios(150, archivo);
            System.out.println("Contenido del archivo generado:");
            leerFicheroAleatorios(archivo);
        } catch (IOException e) {
            System.out.println("Error al escribir o leer el fichero: " + e.getMessage());
        } finally {
            sc.close();
        }
    }

    public static void escribirFicheroAleatorios(int numEnteros, File archivo) throws IOException {
        try (DataOutputStream fichero = new DataOutputStream(new FileOutputStream(archivo))) {
            Random random = new Random();
            for (int i = 0; i < numEnteros; i++) {
                int numeroAleatorio = random.nextInt(61) + 20; // Generar número entre 20 y 80
                fichero.writeInt(numeroAleatorio);
            }
            System.out.println("Fichero escrito correctamente.");
        }
    }

    public static void leerFicheroAleatorios(File archivo) throws IOException {
        try (DataInputStream fichero = new DataInputStream(new FileInputStream(archivo))) {
            while (true) {
                try {
                    int numero = fichero.readInt();
                    System.out.print(numero + " ");
                } catch (EOFException e) {
                    break; // Fin del archivo
                }
            }
            System.out.println(); // Nueva línea al final
        }
    }
}
