import java.io.File;
import java.io.FileFilter;
import java.util.Scanner;

public class EJ4_A2UD1 {
    public static void main(String[] args) throws EJ4_A2UD1.ExcepcionRutaNoDirectorio, EJ4_A2UD1.ExcepcionRutaNoExiste {
        System.out.println("Introduce la ruta hacia el directorio.");

        try (Scanner sc = new Scanner(System.in)) {
            String rutaDirectorio = sc.nextLine();
            File carpetaRuta = new File(rutaDirectorio);

            if (carpetaRuta.exists() && carpetaRuta.isDirectory()) {
                mostrarContenidoFichero(carpetaRuta);
            } else if (carpetaRuta.exists()) { // SI EXISTE PERO NO ES UN DIRECTORIO
                throw new ExcepcionRutaNoDirectorio("La ruta proporcionada no es un directorio.");
            } else {
                throw new ExcepcionRutaNoExiste("La ruta proporcionada no existe.");
            }
        }
    }

    private static void mostrarContenidoFichero(File carpetaRuta) {
        System.out.println("Introduce la extensión de los ficheros que quieras ver.");
        try (Scanner sc = new Scanner(System.in)) {
            String extensionFichero = sc.nextLine();
            FileFilter filtroExtension = filtro(extensionFichero);
            File[] ficheros = carpetaRuta.listFiles(filtroExtension);

            for (File fichero : ficheros) {
                System.out.println("-| " + fichero.getName() + (fichero.isDirectory() ? " <DIR>"
                        : " <FICHERO> " + fichero.getTotalSpace() + " bytes " + fichero.lastModified()));
            }
        }
    }

    private static FileFilter filtro(String extensionFichero) {
        return new FileFilter() {
            @Override
            public boolean accept(File pathname) {
                return pathname.getName().toLowerCase().endsWith(extensionFichero);
            }
        };
    }

    public static class ExcepcionRutaNoDirectorio extends Exception {
        public ExcepcionRutaNoDirectorio(String mensaje) {
        }
    }

    public static class ExcepcionRutaNoExiste extends Exception {
        public ExcepcionRutaNoExiste(String mensaje) {
        }
    }

}