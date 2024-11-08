import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Random;
import java.util.Scanner;

public class EJ1_A3UD1 {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        System.out.println("Introduce el nombre del fichero.");

        String nombreFichero = sc.nextLine();
        int numNumerosEnteros = 180;
        guardarEnFichero(numNumerosEnteros, nombreFichero);

        sc.close();

        leerFichero(nombreFichero);
    }

    private static void leerFichero(String nombreFichero) throws IOException {
        DataInputStream fichero = null;
        try {
            fichero = new DataInputStream(
                    new FileInputStream("C:\\Users\\luis\\Desktop\\REPASO_UD1\\" + nombreFichero));
            int datosFichero = fichero.readInt();
            while (true) {
                System.out.println(datosFichero);
                datosFichero = fichero.readInt();
            }
        } catch (FileNotFoundException e) {
            System.out.println("No se ha encontrado el archivo");
        } catch (EOFException e) {
            System.out.println("Error al leer el archivo.");
        }
    }

    private static void guardarEnFichero(int numNumerosEnteros, String nombreFichero) {
        DataOutputStream fichero = null; // SE USA DATA---STREAM AL SER LOS NÚMEROS TIPOS PRIMITIVOS.

        try {
            fichero = new DataOutputStream(
                    new FileOutputStream("C:\\Users\\luis\\Desktop\\REPASO_UD1\\" + nombreFichero));
            generarValoresAleatorios(numNumerosEnteros, fichero);
        } catch (FileNotFoundException e) {
            System.out.println("No se ha encontrado el archivo.");
        } catch (IOException e) {
            System.out.println("Error al guardar el fichero");
        }
    }

    private static void generarValoresAleatorios(int numNumerosEnteros, DataOutputStream fichero) throws IOException {
        Random r;
        for (int i = 0; i <= numNumerosEnteros; i++) {
            r = new Random();
            fichero.writeInt(r.nextInt(20, 80));
        }
    }
}
