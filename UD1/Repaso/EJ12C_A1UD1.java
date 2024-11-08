import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;

public class EJ12C_A1UD1 {
    public static void main(String[] args) throws IOException {
        PrintStream prst = new PrintStream("Datos.txt"); // SI ES SYSTEM OUT ES COMO UN PRINT
        try {
            System.out.println("Leer cadena: ");
            String s = Teclado.leer();
            System.out.println("Leer caracter: ");
            char car = Teclado.leerChar();
            System.out.println("Leer numero entero: ");
            int num1 = Teclado.leerInt();
            System.out.println("Leer numero double: ");
            double num2 = Teclado.leerDouble();
            prst.println(" cadena: " + s);
            prst.println(" el caracter tecleado: " + car);
            prst.println(" entero: " + num1);
            prst.println(" numero real double: " + num2);

        } catch (FileNotFoundException e) {
            System.out.println("No se ha encontrado el archivo.");
        }
        prst.close();
    }
}
