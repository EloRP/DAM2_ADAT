import java.io.File;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.Scanner;

public class EJ2_A2UD1 {
    public static void main(String[] args) throws EJ2_A2UD1.ExcepcionRutaNoDirectorio, EJ2_A2UD1.ExcepcionRutaNoExiste {

        System.out.println("Introduce la ruta hacia el directorio: ");

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
        File[] ficheros = carpetaRuta.listFiles();

        SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy");
        for (File fichero : ficheros) {

            double tamanhoArchivo = fichero.length() / 1024;
            String fechaModificacion = formatoFecha.format(new Date(fichero.lastModified()));
            System.out.println("-|" + fichero.getName() + (fichero.isDirectory() ? " <D> " : " <FICHERO> " + tamanhoArchivo + " KBytes " + fechaModificacion));
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
