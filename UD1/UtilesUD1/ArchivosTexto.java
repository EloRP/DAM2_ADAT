package UtilesUD1;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class ArchivosTexto {
    // Escribir en un archivo de texto
    public void escribirTexto(String nombreArchivo, String texto) {
        try (BufferedWriter escritor = new BufferedWriter(new FileWriter(nombreArchivo))) {
            escritor.write(texto);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Leer un archivo de texto
    public String leerTexto(String nombreArchivo) {
        StringBuilder contenido = new StringBuilder();
        try (BufferedReader lector = new BufferedReader(new FileReader(nombreArchivo))) {
            String linea;
            while ((linea = lector.readLine()) != null) {
                contenido.append(linea).append("\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return contenido.toString();
    }
}
