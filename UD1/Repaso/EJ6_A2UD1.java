import java.io.File;
import java.io.FileFilter;
import java.util.Scanner;

public class EJ6_A2UD1 {
    public static void main(String[] args) throws EJ6_A2UD1.ExcepcionRutaNoDirectorio, EJ6_A2UD1.ExcepcionRutaNoExiste {
        System.out.println("Introduce la ruta al directorio.");

        try (Scanner sc = new Scanner(System.in)) {
            String ruta = sc.nextLine();
            File carpetaRuta = new File(ruta);

            if (carpetaRuta.exists() && carpetaRuta.isDirectory()) {
                FileFilter subcadenaArchivo = pedirSubcadena();
                mostrarContenidoFichero(carpetaRuta, 1, subcadenaArchivo);
            } else if (carpetaRuta.exists()) {
                throw new ExcepcionRutaNoDirectorio("La ruta proporcionada no es un directorio.");
            } else {
                throw new ExcepcionRutaNoExiste("La ruta proporcionada no existe.");
            }
        }
    }

    private static void mostrarContenidoFichero(File carpetaRuta, int nivel, FileFilter subcadenaArchivo) {
        File[] ficheros = carpetaRuta.listFiles(subcadenaArchivo);

        for (File fichero : ficheros) {
            if (fichero.isDirectory()) {
                System.out.println("-".repeat(nivel) + "| " + fichero.getName() + " <DIR>");
                nivel = nivel + 4;
                mostrarContenidoFichero(fichero, nivel, subcadenaArchivo);
            } else {
                System.out.println("-".repeat(nivel) + "| " + fichero.getName() + " <FICHERO> "
                        + fichero.getTotalSpace() + " bytes " + fichero.lastModified());
            }
        }
    }

    private static FileFilter pedirSubcadena() {
        System.out.println("Introduce la subcadena.");
        try (Scanner sc = new Scanner(System.in)) {
            String subcadena = sc.nextLine().toLowerCase();
            return new FileFilter() {
                @Override
                public boolean accept(File pathname) {
                    if (pathname.isDirectory()) {
                        return true;
                    }
                    return pathname.getName().toLowerCase().contains(subcadena);
                }
            };
        }
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
