package A2UD1;

import java.io.File;
import java.util.Scanner;
import java.text.SimpleDateFormat;
import java.util.Date;

public class EJ2_A2UD1 {
    public static void main(String[] args) {
        System.out.println("Listado de directorios\n"
                + "Elige una ruta para listar sus directorios: ");
        Scanner sc = new Scanner(System.in);
        String ruta = sc.nextLine();
        try {
            listadoDirectorio(ruta);
        } catch (directorioNoExisteException e) {
            System.out.println(e.getMessage());
        } catch (noEsDirectorioException e) {
            System.out.println(e.getMessage());
        }
        sc.close();
    }

    public static void listadoDirectorio(String ruta) throws directorioNoExisteException, noEsDirectorioException {
        SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yy HH:mm");
        File directorio = new File(ruta);

        if (!directorio.exists()) {
            throw new directorioNoExisteException("La ruta especificada no existe.");
        }

        if (!directorio.isDirectory()) {
            throw new noEsDirectorioException("La ruta no es un directorio");
        }

        System.out.println("--- LISTANDO EL DIRECTORIO " + ruta + " ---");
        File[] directorios = directorio.listFiles();
        if (directorios != null) {
            for (File directorioActual : directorios) {
                if (directorioActual.isDirectory()) {
                    System.out.println("-|" + directorioActual.getName() + " <DIR>");
                } else {
                    String fechaModificacion = formatoFecha.format(new Date(directorioActual.lastModified()));
                    int tamanhoKB = (int) directorioActual.length() / 1024;
                    System.out.println("-|" + directorioActual.getName() + " <FICHERO> " + tamanhoKB + " Kbytes " + fechaModificacion);
                }
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