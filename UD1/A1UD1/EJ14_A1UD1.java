package A1UD1;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Collections;
import A1UD1.Teclado.ExcepcionEnteroPositivo;

public class EJ14_A1UD1 {

    public static void main(String[] args) throws IOException, ExcepcionEnteroPositivo {
        ArrayList<Integer> lista = new ArrayList<>();

        abrirFichero();
        System.out.println("Introduce el número de enteros positivos para grabar en fichero:");
        int num;
        num = leerPositivo();

        for (int i = 1; i <= num; i++) {
            System.out.print("número " + i + ":");
            int numAnhadido = grabarFichero(leerPositivo());
            lista.add(numAnhadido);
        }
        cerrarFichero();
        LeerFichero();
        Collections.sort(lista);
        System.out.println(lista);
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
        String contenido = LeerFichero();
    }

    private static void cerrarFichero() {
    }

    private static int grabarFichero(int leerPositivo) throws FileNotFoundException {
        String contenidoFichero = LeerFichero();
        contenidoFichero += leerPositivo + ";";
        PrintStream pw = new PrintStream("C:\\ADAT\\Unidades\\UD1\\A1UD1\\NumerosPositivos.txt");
        pw.println(contenidoFichero);
        pw.close();
        return leerPositivo;
    }

    static String LeerFichero() throws FileNotFoundException {
        String linea = "";
        File fichero = new File("C:\\ADAT\\Unidades\\UD1\\A1UD1\\NumerosPositivos.txt");
        Scanner sc = new Scanner(fichero);
        while (sc.hasNextLine()) {
            linea = sc.nextLine();
            System.out.println(linea);
        }
        sc.close();
        return linea;
    }

}