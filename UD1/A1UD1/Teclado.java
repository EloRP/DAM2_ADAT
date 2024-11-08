package A1UD1;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Teclado {

    static BufferedReader entrada = new BufferedReader(new InputStreamReader(System.in));

    public static int leerInt() {
        int numInt;
        try {
            numInt = Integer.parseInt(entrada.readLine());
            entrada.readLine();
        } catch (Exception e) {
            System.out.println("Eso no es un número entero. Inténtalo de nuevo.");
            numInt = leerInt();
        }
        return numInt;
    }

    public static double leerDouble() {
        double numDouble;
        try {
            numDouble = Double.parseDouble(entrada.readLine());
            entrada.readLine();
        } catch (Exception e) {
            System.out.println("Eso no es un número real. Inténtalo de nuevo.");
            numDouble = leerDouble();
        }
        return numDouble;
    }

    public static String leer() {
        String cadena;
        try {
            return entrada.readLine();
        } catch (Exception e) {
            System.out.println("Eso no es una cadena. Inténtalo de nuevo.");
            cadena = leer();
        }
        return cadena;
    }

    public static char leerChar() {
        char caracter;
        try {
            caracter = (char) entrada.read();
            entrada.readLine();
        } catch (Exception e) {
            System.out.println("Eso no es un caracter. Inténtalo de nuevo.");
            caracter = leerChar();
        }
        return caracter;
    }

    /*
     * Añadir un método llamado leerPositivo() en la clase Teclado.java para que
     * solo nos permita leer
     * números enteros positivos. Si el dato no es correcto, hay que volver a
     * introducirlo. Crea tu propia
     * excepción para controlar si el número introducido es positivo.
     */

    public static int leerPositivo() {
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

    public static class ExcepcionEnteroPositivo extends Exception {
        public ExcepcionEnteroPositivo(String mensaje) {
            super("El número introducido no es positivo");
        }
    }
}
