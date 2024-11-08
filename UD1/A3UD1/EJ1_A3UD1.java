package A3UD1;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Random;
import java.util.Scanner;

public class EJ1_A3UD1 {
    public static void main(String[] args) throws IOException {

        System.out.println("Introduce el nombre para el fichero (sin extensión): ");
        Scanner sc = new Scanner(System.in);
        String nombreFichero = (sc.nextLine() + ".bin");
        File archivo = new File(nombreFichero);

        int numAleatorios = 150;

        if (archivo.exists()) {
            System.out.println("El fichero ya existe. Quieres sobrescribirlo? (S/N)");
            String respuesta = sc.nextLine();

            if (!respuesta.equalsIgnoreCase("S")) {
                sc.close();
                return;
            }
        }

        try {
            escribirFicheroAleatorios(numAleatorios, nombreFichero);
            leerFicheroAleatorios(archivo);
        } catch (IOException e) {
            System.out.println("Error al leer el archivo");
        } finally {
            sc.close();
        }
        sc.close();
    }

    public static void escribirFicheroAleatorios(int numEnteros, String nombreFichero) throws IOException {
        try (DataOutputStream fichero = new DataOutputStream(new FileOutputStream(
                "Z:\\eloy_rodalperez\\DAM2\\Acceso a datos\\Unidades\\UD1\\A3UD1\\" + nombreFichero))) {
            generarAleatorios(150, fichero);
            System.out.println("Fichero escrito.");
        } catch (IOException e) {
        }
    }

    public static void generarAleatorios(int numEnteros, DataOutputStream nombreFichero) throws IOException {
        Random r;
        for (int i = 0; i <= numEnteros; i++) {
            r = new Random();
            nombreFichero.writeInt(r.nextInt(20, 80));
        }
    }

    public static void leerFicheroAleatorios(File archivo) throws IOException {
        try (DataInputStream fichero = new DataInputStream(new FileInputStream(archivo))) {
            while (true) {
                try {
                    int numero = fichero.readInt();
                    System.out.print(numero + " ");
                } catch (EOFException e) {
                    break;
                }
            }
        }
    }
}
