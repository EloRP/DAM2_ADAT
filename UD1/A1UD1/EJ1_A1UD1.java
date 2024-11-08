package A1UD1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class EJ1_A1UD1 {
    public static void main(String[] args) throws IOException {
        try {
            String entero1, entero2;
            BufferedReader entrada = new BufferedReader(new InputStreamReader(System.in));

            System.out.println("Introduce primer número entero: ");
            entrada.read();
            entero1 = entrada.readLine();
            while (!leerEntero(entero1)) {
                System.out.print("Eso no es un número entero. Inténtalo de nuevo.");
                entero1 = entrada.readLine();
            }

            System.out.println("Introduce segundo número entero: ");
            entrada.read();
            entero2 = entrada.readLine();
            while (!leerEntero(entero2)) {
                System.out.print("Eso no es un número entero. Inténtalo de nuevo.");
                entero2 = entrada.readLine();
            }

            System.out.println("La suma es: " + (Integer.parseInt(entero1) + Integer.parseInt(entero2)));
            System.out.println("La resta es: " + (Integer.parseInt(entero1) - Integer.parseInt(entero2)));
            System.out.println("La multiplicacion es: " + (Integer.parseInt(entero1) * Integer.parseInt(entero2)));
            try {
                System.out.println("La division entera es: " + (Integer.parseInt(entero1) / Integer.parseInt(entero2)));
            } catch (ArithmeticException e) {
                System.out.println("No se puede dividir por 0");
            }
            System.out.println("La division real es: " + (Double.parseDouble(entero1) / Double.parseDouble(entero2)));
            try {
                System.out.println("El resto: " + (Double.parseDouble(entero1) % Double.parseDouble(entero2)));
            } catch (ArithmeticException e) {
                System.out.println("No se puede dividir por 0");
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public static boolean leerEntero(String entero) {
        boolean esEntero = false;
        if (entero.matches("[0-9]+"))
            esEntero = true;
        return esEntero;
    }
}
