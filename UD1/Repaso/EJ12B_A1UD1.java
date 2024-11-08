import java.io.IOException;
import java.io.PrintStream;

public class EJ12B_A1UD1 {
    public static void main(String[] args) {
        PrintStream prst = new PrintStream(System.out); // SI ES SYSTEM OUT ES COMO UN PRINT
        try {
            prst.println("Leer cadena: ");
            String s = Teclado.leer();
            prst.println("Leer caracter: ");
            char car = Teclado.leerChar();
            prst.println("Leer numero entero: ");
            int num1 = Teclado.leerInt();
            prst.println("Leer numero double: ");
            double num2 = Teclado.leerDouble();
            prst.println(" cadena: " + s);
            prst.println(" caracter: " + car);
            prst.println(" entero: " + num1);
            prst.println(" numero real double: " + num2);
            
        } catch (IOException e) {
            System.out.println("Error.");
        }
    }
}