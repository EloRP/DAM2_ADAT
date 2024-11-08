import java.io.*;
import java.util.*;

public class EJ14_A1UD1 {
    public static void main(String[] args) throws FileNotFoundException {
        String nombreFichero = "NumerosPositivos2.txt";

        ArrayList<Integer> numeros = new ArrayList<>();

        try (Scanner sc = new Scanner(new File(nombreFichero))) {
            while (sc.hasNextInt()) {
                numeros.add(sc.nextInt());
            }
        } catch (FileNotFoundException e) {
            System.out.println("Fichero no encontrado.");
            return;
        }

        Collections.sort(numeros);

        try (PrintStream salida = new PrintStream(new File(nombreFichero))) {
            for (int numero : numeros) {
                salida.println(numero);
            }
        } catch (FileNotFoundException e) {
            System.out.println("No se ha encontrado el fichero" + nombreFichero);
        }

        System.out.println("Números ordenados: ");
        for (int numero : numeros) {
            System.out.println(numero);
        }
    }
}
