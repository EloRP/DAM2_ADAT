package UtilesUD1;

import java.io.IOException;
import java.io.RandomAccessFile;

public class ArchivosAleatorios {

    // Escribir en una posición específica de un archivo
    public void escribirEnPosicion(String nombreArchivo, long posicion, String texto) {
        try (RandomAccessFile archivo = new RandomAccessFile(nombreArchivo, "rw")) {
            archivo.seek(posicion); // Mover el puntero a la posición
            archivo.writeUTF(texto); // Escribir en esa posición
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Leer desde una posición específica de un archivo
    public String leerDesdePosicion(String nombreArchivo, long posicion) {
        try (RandomAccessFile archivo = new RandomAccessFile(nombreArchivo, "r")) {
            archivo.seek(posicion); // Mover el puntero a la posición
            return archivo.readUTF(); // Leer desde esa posición
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
