package A1UD1;

import java.io.PrintStream;

public class EJ12B_A1UD1 {
    public static void main(String[] args) {

        PrintStream pw = new PrintStream(System.out, true);

        pw.println("Leer cadena: ");
        String s = Teclado.leer();

        pw.println("Leer caracter: ");
        char car = Teclado.leerChar();

        pw.println("Leer numero entero: ");
        int num1 = Teclado.leerInt();

        pw.println("Leer numero double: ");
        double num2 = Teclado.leerDouble();

        pw.println(" cadena: " + s);
        pw.println(" caracter: " + car);
        pw.println(" entero: " + num1);
        pw.println(" numero real double: " + num2);
    }

}
