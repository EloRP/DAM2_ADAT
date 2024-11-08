import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class EJ1_A1UD1 {

    public static void main(String[] args) {
        BufferedReader entradaBuffer = new BufferedReader(new InputStreamReader(System.in));
        String n, m;
        try {
        System.out.println("Introduce el primer número entero.");
        n = entradaBuffer.readLine();

        while (!leerEntero(n)) {
            System.out.println("Eso no es un entero. Inténtalo de nuevo.");
            return;
        }

        System.out.println("Introduce el segundo número entero.");
        m = entradaBuffer.readLine();

        while (!leerEntero(m)) {
            System.out.println("Eso no es un entero. Inténtalo de nuevo.");
            return;
        }

        System.out.println("La suma es: " + (Integer.parseInt(n) + Integer.parseInt(m))); //IMPORTANTE. RECUERDA PARSEAR
        System.out.println("La resta es: " + (Integer.parseInt(n) - Integer.parseInt(m)));
        System.out.println("La multiplicación es: " + (Integer.parseInt(n) * Integer.parseInt(m)));
        try {
            System.out.println("La división entera es: " + (Integer.parseInt(n) * Integer.parseInt(m)));
            System.out.println("La división real es: " + (Double.parseDouble(n) * Double.parseDouble(m)));
            System.out.println("El resto: " + (Double.parseDouble(n) % Double.parseDouble(m)));
        } catch (ArithmeticException e) {
            System.out.println("No se puede dividir entre 0.");
        }
    } catch (IOException e) {
        System.out.println(e.getMessage());
    }
    }

    private static boolean leerEntero(String n) {
        boolean esEntero = false;

        if (n.matches("[0-9]+")) {
            esEntero = true;
        }
        return esEntero;

    }
}