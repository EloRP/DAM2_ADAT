import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class EJ3_A2UD1 {
    public static void main(String[] args) throws EJ3_A2UD1.ExcepcionRutaNoExiste, EJ3_A2UD1.ExcepcionRutaNoDirectorio {
        System.out.println("Introduce la ruta hacia el directorio.");

        try (Scanner sc = new Scanner(System.in)) {
            String rutaDirectorio = sc.nextLine();
            File carpetaRuta = new File(rutaDirectorio);

            if (carpetaRuta.exists() && carpetaRuta.isDirectory()) {
                mostrarContenidoFichero(carpetaRuta, 1);
            } else if (carpetaRuta.exists()) { // SI EXISTE PERO NO ES UN DIRECTORIO
                throw new ExcepcionRutaNoDirectorio("La ruta proporcionada no es un directorio.");
            } else {
                throw new ExcepcionRutaNoExiste("La ruta proporcionada no existe.");
            }
        }
    }

    private static void mostrarContenidoFichero(File carpetaRuta, int nivel) {
        File[] ficheros = carpetaRuta.listFiles();
        SimpleDateFormat formatoFecha = new SimpleDateFormat();

        for (File fichero : ficheros) {
            double tamanhoArchivo = fichero.length() / 1024;
            String fechaModificacion = formatoFecha.format(new Date(fichero.lastModified()));
            if (fichero.isDirectory()) {
                System.out.println("-".repeat(nivel) + "|" + fichero.getName() + " <DIR> ");
                mostrarContenidoFichero(fichero, nivel + 4);        //RECURSIVIDAD
            } else {
                System.out.println("-".repeat(nivel) + "|" + fichero.getName() + " <FICHERO> " + tamanhoArchivo
                        + " KBytes " + fechaModificacion);
            }
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
