import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

public class EJ1_A3P4UD1 {
    public static void main(String[] args) throws FileNotFoundException, IOException {
        File rutaFichero = new File("corredores.dat");
        int tamanhoRegistro = 100;

        if (rutaFichero.exists()) {
            System.out.println("El archivo ya existe.");
        } else {
            System.out.println("El archivo no existe. Se va a crear.");
        }

        try (RandomAccessFile fichero = new RandomAccessFile(rutaFichero, "rw")) {
            long numRegistros = 0;
            if (fichero.length() != 0) {
                numRegistros = fichero.length() / tamanhoRegistro + 1;

            }
        }
    }
}