import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.Scanner;

public class EJ13_A1UD1 {
    public static void main(String[] args) throws FileNotFoundException {

        abrirFichero();
        int num;

        System.out.println("Introduce el número (N) de números positivos que quieras grabar.");
        num = leerPositivo();
        for (int i = 0; i < num; i++) {
            System.out.println("Número " + (i + 1) + ": ");
            grabarFichero(leerPositivo());
        }
    }

    private static void grabarFichero(int numero) throws FileNotFoundException {
        String contenido = leerFichero();
        contenido += numero + "; ";
        try (PrintStream fichero = new PrintStream("NumerosPositivos.txt")) {
            fichero.print(contenido);
        }
    }

    private static int leerPositivo() {
        int entero = 0;
        try {
            Scanner sc = new Scanner(System.in);
            entero = sc.nextInt();
            if (entero <= 0)
                throw new Exception(); // AQUI IRÍA LA EXCEPCIÓN PERSONALIZADA. NO VA.
        } catch (Exception e) {
            System.out.println("El número no es positivo.");
            leerPositivo(); // MÉTODO RECURSIVO
        }
        return entero;
    }

    private static void abrirFichero() {
        String contenido = leerFichero();
        System.out.println(contenido);

    }

    private static String leerFichero() {
        String contenidoFichero = "";
        File fichero = new File("NumerosPositivos.txt");

        try (Scanner sc = new Scanner(fichero)) {
            while (sc.hasNext()) {
                contenidoFichero += sc.next();
            }
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
        return contenidoFichero;
    }

}
