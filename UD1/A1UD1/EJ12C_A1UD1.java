package A1UD1;

import java.io.FileNotFoundException;
import java.io.PrintStream;

public class EJ12C_A1UD1 {
    public static void main(String[] args) {

        PrintStream pw = null;

        try {

            pw = new PrintStream("A1UD1\\Datos.txt");

            System.out.println("Leer cadena: ");
            String s = Teclado.leer();

            System.out.println("Leer caracter: ");
            char car = Teclado.leerChar();

            System.out.println("Leer numero entero: ");
            int num1 = Teclado.leerInt();

            System.out.println("Leer numero double: ");
            double num2 = Teclado.leerDouble();

            pw.println(" cadena: " + s);
            pw.println(" caracter: " + car);
            pw.println(" entero: " + num1);
            pw.println(" numero real double: " + num2);

        } catch (FileNotFoundException e) {
            System.out.println("Fichero no encontrado");
        } finally {
            if (pw != null)
                pw.close();
        }

    }
}
