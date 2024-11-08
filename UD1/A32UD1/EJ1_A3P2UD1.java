package A32UD1;

import java.util.ArrayList;
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class EJ1_A3P2UD1 {
    public static void main(String[] args) throws FileNotFoundException, IOException {

        ArrayList<String> listaFicheros = lecturaFicheros();
        for (int i = 0; i < listaFicheros.size(); i++) {
            String nombreFichero = listaFicheros.get(i) + (listaFicheros.get(i).endsWith(".txt") ? "" : ".txt");
            try (BufferedReader fichero = new BufferedReader(new FileReader(
                    "Z:\\eloy_rodalperez\\DAM2\\Acceso a datos\\Unidades\\UD1\\A32UD1\\" + nombreFichero))) {
                int numLineas = 0;

                while (fichero.readLine() != null) {
                    numLineas++;
                }
                System.out.println("El fichero " + nombreFichero + " tiene " + numLineas + " lineas. ");
            } catch (IOException e) {
                System.out.println("No se pudo encontrar el fichero");
            }
        }
    }

    public static ArrayList<String> lecturaFicheros() {
        Scanner sc = new Scanner(System.in);
        ArrayList<String> listaFicheros = new ArrayList<>();
        System.out.println(
                "Introduce los nombres de los ficheros que quieras leer");
        String inputUser = sc.nextLine();
        while (!inputUser.isEmpty()) {
            listaFicheros.add(inputUser);
            inputUser = sc.nextLine();
        }

        sc.close();
    return listaFicheros;
    }
}