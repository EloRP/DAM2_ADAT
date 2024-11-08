import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Teclado {

    static BufferedReader bfr = new BufferedReader(new InputStreamReader(System.in));

    public static String leer() throws IOException {
        String cadena = bfr.readLine();
        return cadena;
    }

    public static char leerChar() {
        char caracter = 0;
        try {
            caracter = (char) bfr.read();
            bfr.readLine();
        } catch (IOException e) {
        }
        return caracter;
    }

    public static int leerInt() throws IOException {
        String[] cadena = bfr.readLine().split(" ");
        int entero = Integer.parseInt(cadena[0]);
        return entero;
    }

    public static double leerDouble() throws IOException {
        String[] cadena = bfr.readLine().split(" ");
        double enteroDouble = Double.parseDouble(cadena[0]);
        return enteroDouble;
    }

    public static int leerPositivo() throws IOException {
        String[] cadena;
        int entero = 0;

        while (entero <= 0) {
            try {
                cadena = bfr.readLine().split(" ");
                entero = Integer.parseInt(cadena[0]);
                if (entero <= 0) {
                    throw new ExcepcionNumEnteroPositivo();
                }
            } catch (ExcepcionNumEnteroPositivo e) {
                System.out.println("El número no es positivo.");
            } catch (NumberFormatException e) {
                System.out.println("El input no es un número.");
            }
        }
        return entero;

    }

    public static class ExcepcionNumEnteroPositivo extends Exception {
        public ExcepcionNumEnteroPositivo() {
        }
    }

    public static void main(String[] args) throws IOException {
        System.out.println("Introduce un número entero positivo.");
        int num = leerPositivo();
        System.out.println("Número: " + num);
    }
}
