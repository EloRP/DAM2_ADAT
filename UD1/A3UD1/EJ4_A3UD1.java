package A3UD1;

import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Map;
import java.util.TreeMap;
import java.io.EOFException;

public class EJ4_A3UD1 {
    public static void main(String[] args) throws IOException {
        DataInputStream fichero = null;
        Map<Integer, Integer> numEnteros = null;

        try {
            fichero = new DataInputStream(new FileInputStream(
                    "Z:\\eloy_rodalperez\\DAM2\\Acceso a datos\\Unidades\\UD1\\A3UD1\\ficheroEJ4.bin"));
            numEnteros = new TreeMap<>();
            lecturaMapa(fichero, numEnteros);
        } catch (EOFException e) {
            System.out.println("Fin del archivo");
            recorrerMapa(numEnteros);
        } finally {
            fichero.close();
        }
    }

    private static void lecturaMapa(DataInputStream fichero, Map<Integer, Integer> numEnteros) throws IOException {
        Integer numero;
        while (true) {
            numero = fichero.readInt();
            if (numEnteros.containsKey(numero)) {
                numEnteros.put(numero, (int) numEnteros.get(numero) + 1);
            } else {
                numEnteros.put(numero, 1);
            }
        }
    }

    public static void recorrerMapa(Map<Integer, Integer> numEnteros) {
        for (Map.Entry<Integer, Integer> n : numEnteros.entrySet()) {
            System.out.println("El número " + n.getKey() + " se repite " + n.getValue()
                    + (n.getValue() == 1 ? " vez." : " veces."));
        }
    }

}
