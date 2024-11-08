package UtilesUD1;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class ArchivosBinarios {
    // Escribir datos binarios
    public void escribirBinario(String nombreArchivo, byte[] datos) {
        try (FileOutputStream salida = new FileOutputStream(nombreArchivo)) {
            salida.write(datos);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Leer datos binarios
    public byte[] leerBinario(String nombreArchivo) {
        try (FileInputStream entrada = new FileInputStream(nombreArchivo)) {
            byte[] datos = new byte[entrada.available()];
            entrada.read(datos);
            return datos;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
