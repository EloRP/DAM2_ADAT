package A2UD1;

import java.io.File;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;
import java.text.SimpleDateFormat;
import java.util.Date;

public class EJ5_A2UD1 {

    public static void main(String[] args) {
        System.out.println("Listado de directorios\n"
                + "Elige una ruta para listar sus archivos: ");
        Scanner sc = new Scanner(System.in);
        String ruta = sc.nextLine();

        System.out.println("Introduce la extensión de los archivos (sin el punto): ");
        String extension = sc.nextLine();

        try {
            listadoArchivosPorExtension(ruta, extension);
        } catch (directorioNoExisteException e) {
            System.out.println(e.getMessage());
        } catch (noEsDirectorioException e) {
            System.out.println(e.getMessage());
        }
        sc.close();
    }

    public static void listadoArchivosPorExtension(String ruta, String extension) throws directorioNoExisteException, noEsDirectorioException {
        SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yy HH:mm");
        File directorio = new File(ruta);

        if (!directorio.exists()) {
            throw new directorioNoExisteException("La ruta especificada no existe.");
        }

        if (!directorio.isDirectory()) {
            throw new noEsDirectorioException("La ruta no es un directorio");
        }

        System.out.println("--- LISTANDO ARCHIVOS CON EXTENSIÓN ." + extension + " EN EL DIRECTORIO " + ruta + " ---");
        File[] archivos = directorio.listFiles();

        if (archivos != null) {
            File[] archivosFiltrados = Arrays.stream(archivos)
                    .filter(archivo -> archivo.isFile() && archivo.getName().toLowerCase().endsWith("." + extension.toLowerCase()))
                    .toArray(File[]::new);

            Arrays.sort(archivosFiltrados, Comparator.comparing(File::getName, String.CASE_INSENSITIVE_ORDER).reversed());

            for (File archivo : archivosFiltrados) {
                String fechaModificacion = formatoFecha.format(new Date(archivo.lastModified()));
                int tamanhoKB = (int) Math.ceil(archivo.length() / 1024.0);
                System.out.println("-|" + archivo.getName() + " <FICHERO> " + tamanhoKB + " Kbytes " + fechaModificacion);
            }
        }
    }

    static class directorioNoExisteException extends Exception {
        public directorioNoExisteException(String mensaje) {
            super(mensaje);
        }
    }

    static class noEsDirectorioException extends Exception {
        public noEsDirectorioException(String mensaje) {
            super(mensaje);
        }
    }
}
