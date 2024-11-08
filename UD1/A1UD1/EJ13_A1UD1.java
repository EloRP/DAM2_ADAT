package A1UD1;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.File;
import A1UD1.Teclado.ExcepcionEnteroPositivo;

public class EJ13_A1UD1 {

    public static void main(String[] args) throws IOException, ExcepcionEnteroPositivo {

        abrirFichero();
        System.out.println("Introduce el número de enteros positivos para grabar en fichero:");
        int num;

        num = leerPositivo();
        for (int i = 1; i <= num; i++) {
            System.out.print("número " + i + ":");
            grabarFichero(leerPositivo());
        }
        cerrarFichero();
        LeerFichero();
    }

    public static int leerPositivo() {
        BufferedReader entrada = new BufferedReader(new InputStreamReader(System.in));
        int num;
        try {
            num = Integer.parseInt(entrada.readLine());
            if (num < 0) {
                throw new ExcepcionEnteroPositivo("El número introducido no es positivo");
            }
        } catch (Exception ExcepcionEnteroPositivo) {
            System.out.println("Eso no es un número entero positivo. Inténtalo de nuevo.");
            num = leerPositivo();
        }
        return num;
    }

    private static void abrirFichero() throws FileNotFoundException {
        LeerFichero();
    }

    private static void cerrarFichero() {
    }

    private static void grabarFichero(int leerPositivo) throws FileNotFoundException {
        String contenidoFichero = LeerFichero();
        contenidoFichero += leerPositivo + ";";
        PrintStream pw = new PrintStream("NumerosPositivos.txt");
        pw.println(contenidoFichero);
        pw.close();
    }

    static String LeerFichero() throws FileNotFoundException {
        String linea = "";
        File fichero = new File("NumerosPositivos.txt");
        Scanner sc = new Scanner(fichero);
        while (sc.hasNextLine()) {
            linea = sc.nextLine();
            System.out.println(linea);
        }
        sc.close();
        return linea;
    }

}