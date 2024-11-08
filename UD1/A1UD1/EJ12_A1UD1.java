package A1UD1;

import java.io.IOException;

public class EJ12_A1UD1 {
    public static void main(String[] args) throws IOException {
        System.out.print("Leer cadena: ");
        String s = Teclado.leer();

        System.out.print("Leer caracter: ");
        char car = Teclado.leerChar();

        System.out.print("Leer numero entero: ");
        int num1 = Teclado.leerInt();

        System.out.print("Leer numero double: ");
        double num2 = Teclado.leerDouble();

        System.out.println(" cadena: " + s);
        System.out.println(" caracter: " + car);
        System.out.println(" entero: " + num1);
        System.out.println(" numero real double: " + num2);
    }

}
