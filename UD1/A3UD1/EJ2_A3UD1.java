package A3UD1;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Scanner;

public class EJ2_A3UD1 {
    public static void main(String[] args) throws IOException, numeroMenorException {
        Scanner sc = new Scanner(System.in);

        System.out.println("Introduce un valor entero");
        String valorUser = sc.nextLine();
        int entradaAnterior = -1000000000;

        guardadoNumerosBinario(sc, valorUser, entradaAnterior);

        DataInputStream datos = null;

        try {
            datos = new DataInputStream(new DataInputStream(new FileInputStream(
                    "Z:\\eloy_rodalperez\\DAM2\\Acceso a datos\\Unidades\\UD1\\A3UD1\\ficheroEJ4.bin")));
            int entero = datos.readInt();
            while (true) {
                System.out.println(entero);
                entero = datos.readInt();
            }
        } catch (EOFException e) {
            System.out.println("Fin del archivo");
        }
        datos.close();
    }

    private static void guardadoNumerosBinario(Scanner sc, String valorUser, int entradaAnterior)
            throws IOException, numeroMenorException {
        DataOutputStream fichero = null;
        try {
            fichero = new DataOutputStream(new FileOutputStream(
                    "Z:\\eloy_rodalperez\\DAM2\\Acceso a datos\\Unidades\\UD1\\A3UD1\\ficheroEJ4.bin"));
            while (valorUser.matches("[0-9]+")) {
                if (Integer.parseInt(valorUser) >= entradaAnterior) {
                    fichero.writeInt(Integer.parseInt(valorUser));
                    entradaAnterior = Integer.parseInt(valorUser);
                    valorUser = sc.nextLine();
                } else {
                    throw new numeroMenorException("El número introducido es menor que el anterior.");
                }
            }
        } catch (IOException e) {
            System.out.println("Error de escritura");
        } finally {
            if (fichero != null) {
                fichero.close();
            }
        }
    }
}