import java.io.DataInputStream;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class EJ4_A3UD1 {
    public static void main(String[] args) throws IOException, FileNotFoundException {
        DataInputStream fichero = null;
        Map<Integer, Integer> numsEnteros = null;

        try {
            fichero = new DataInputStream(new FileInputStream("FicheroEJ4A3.bin"));
            numsEnteros = new HashMap<>();

            guardarNumsFicheroEnMapa(fichero, numsEnteros);
        } catch (EOFException e) {
            recorrerMapa(numsEnteros);
            System.out.println("Fin del archivo.");
        }

    }

    private static void recorrerMapa(Map<Integer, Integer> numsEnteros) {
        for (Map.Entry<Integer, Integer> n : numsEnteros.entrySet()) {
            System.out.println(
                    "El número " + n.getKey() + " se repite " + n.getValue() + (n.getValue() == 1 ? "vez" : "veces"));
        }
    }

    private static void guardarNumsFicheroEnMapa(DataInputStream fichero, Map<Integer, Integer> numsEnteros)
            throws IOException {
        Integer numero;
        while (true) {
            numero = fichero.readInt();
            if (numsEnteros.get(numero) == null) {
                numsEnteros.put(numero, 1);
            } else {
                numsEnteros.put(numero, numsEnteros.get(numero) + 1);
            }
        }
    }

}
