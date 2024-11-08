package A2UD1;

import java.io.File;
import java.util.Scanner;
import java.text.SimpleDateFormat;
import java.util.Date;

public class EJ6_A2UD1 {
    public static void main(String[] args) {
        System.out.println("Listado de directorios con búsqueda por subcadena\n"
                + "Elige una ruta para listar sus directorios: ");
        Scanner sc = new Scanner(System.in);
        File ruta = new File(sc.nextLine());

        System.out.println("Introduce la subcadena para buscar archivos: ");
        String subcadena = sc.nextLine();

        try {
            listadoDirectorio(ruta, 0, subcadena);
        } catch (directorioNoExisteException e) {
            System.out.println(e.getMessage());
        } catch (noEsDirectorioException e) {
            System.out.println(e.getMessage());
        }
        sc.close();
    }

    public static void listadoDirectorio(File archivo, int nivelDirectorio, String subcadena) throws directorioNoExisteException, noEsDirectorioException {
        SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yy HH:mm");

        // Verificar si el archivo o directorio existe
        if (!archivo.exists()) {
            throw new directorioNoExisteException("La ruta especificada no existe.");
        }

        // Verificar si es un directorio
        if (!archivo.isDirectory()) {
            throw new noEsDirectorioException("La ruta no es un directorio");
        }

        // Obtener los archivos y directorios dentro del directorio actual
        File[] directorios = archivo.listFiles();
        if (directorios != null) {
            for (File file : directorios) {
                String indent = " ".repeat(nivelDirectorio);
                
                // Si es un directorio, recorrerlo recursivamente
                if (file.isDirectory()) {
                    System.out.println(indent + "-|" + file.getName() + " <DIR>");
                    listadoDirectorio(file, nivelDirectorio + 1, subcadena);  // Recursividad
                } else {
                    // Si es un archivo, verificar si su nombre contiene la subcadena
                    if (file.getName().toLowerCase().contains(subcadena.toLowerCase())) {
                        String fechaModificacion = formatoFecha.format(new Date(file.lastModified()));
                        int tamanhoKB = (int) Math.ceil(file.length() / 1024.0);
                        System.out.println(indent + "-|" + file.getName() + " <FICHERO> " + tamanhoKB + " Kbytes " + fechaModificacion);
                    }
                }
            }
        }
    }

    // Excepción personalizada para cuando el directorio no existe
    static class directorioNoExisteException extends Exception {
        public directorioNoExisteException(String mensaje) {
            super(mensaje);
        }
    }

    // Excepción personalizada para cuando la ruta no es un directorio
    static class noEsDirectorioException extends Exception {
        public noEsDirectorioException(String mensaje) {
            super(mensaje);
        }
    }
}