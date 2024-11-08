package UtilesUD1;

import java.io.File;
import java.io.IOException;

public class Archivos {
    // Crear un archivo
    public boolean crearArchivo(String nombreArchivo) {
        File archivo = new File(nombreArchivo);
        try {
            return archivo.createNewFile(); // Crea el archivo si no existe
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Eliminar un archivo
    public boolean eliminarArchivo(String nombreArchivo) {
        File archivo = new File(nombreArchivo);
        return archivo.delete();
    }

    // Renombrar un archivo
    public boolean renombrarArchivo(String nombreViejo, String nombreNuevo) {
        File archivoViejo = new File(nombreViejo);
        File archivoNuevo = new File(nombreNuevo);
        return archivoViejo.renameTo(archivoNuevo);
    }

    // Crear un directorio
    public boolean crearDirectorio(String nombreDirectorio) {
        File directorio = new File(nombreDirectorio);
        return directorio.mkdir(); // Crea el directorio si no existe
    }
}
