package UtilesUD1;

import java.io.File;

public class ListadoArchivosFichero {
    // Listar archivos y subdirectorios en un directorio
    public void listarArchivos(String nombreDirectorio) {
        File directorio = new File(nombreDirectorio);
        if (directorio.isDirectory()) {
            String[] archivos = directorio.list();
            if (archivos != null) {
                for (String archivo : archivos) {
                    System.out.println(archivo);
                }
            } else {
                System.out.println("El directorio está vacío o no se puede leer.");
            }
        } else {
            System.out.println("No es un directorio válido.");
        }
    }

    // Listar solo archivos en un directorio
    public void listarSoloArchivos(String nombreDirectorio) {
        File directorio = new File(nombreDirectorio);
        if (directorio.isDirectory()) {
            File[] archivos = directorio.listFiles();
            if (archivos != null) {
                for (File archivo : archivos) {
                    if (archivo.isFile()) {
                        System.out.println(archivo.getName());
                    }
                }
            }
        }
    }
}
