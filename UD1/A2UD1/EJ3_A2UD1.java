package A2UD1;

import java.io.File;
import java.util.Scanner;
import java.text.SimpleDateFormat;
import java.util.Date;

public class EJ3_A2UD1 {
    public static void main(String[] args) {
        System.out.println("Listado de directorios\n"
                + "Elige una ruta para listar sus directorios: ");

        Scanner sc = new Scanner(System.in);
        File ruta = new File(sc.nextLine());
        try {
            listadoDirectorio(ruta, 0);
        } catch (directorioNoExisteException e) {
            System.out.println(e.getMessage());
        } catch (noEsDirectorioException e) {
            System.out.println(e.getMessage());
        }
        sc.close();
    }

    public static void listadoDirectorio(File archivo, int nivelDirectorio) throws directorioNoExisteException, noEsDirectorioException {
        SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yy HH:mm");

        if (!archivo.exists()) {
            throw new directorioNoExisteException("La ruta especificada no existe.");
        }

        if (!archivo.isDirectory()) {
            throw new noEsDirectorioException("La ruta no es un directorio");
        }

        File[] directorios = archivo.listFiles();
        if (directorios != null) {
            for (File file : directorios) {
                String indent = " ".repeat(nivelDirectorio);
                if (file.isDirectory()) {
                    System.out.println(indent + "-|" + file.getName() + " <DIR>");
                    listadoDirectorio(file, nivelDirectorio + 1);
                } else {
                    String fechaModificacion = formatoFecha.format(new Date(file.lastModified()));
                    int tamanhoKB = (int) file.length() / 1024;
                    System.out.println("-|" + file.getName() + " <FICHERO> " + tamanhoKB + " Kbytes " + fechaModificacion);
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