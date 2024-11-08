import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Scanner;

public class EJ1_A3P3UD1 {
    public static void main(String[] args) throws FileNotFoundException, IOException, ClassNotFoundException {
        File rutaFichero = new File("corredores.dat");
        Scanner sc = new Scanner(System.in);

        int entradaUser = sc.nextInt();

        while (entradaUser != 7) {
            switch (entradaUser) {
                case 1:
                    crearArchivo(rutaFichero, sc);
                    break;
                case 2:
                    if (rutaFichero.exists())
                        anhadirRegistros(sc);
                    else
                        System.out.println("Aún no has creado el fichero.");
                    break;
                case 3:
                    if (rutaFichero.exists())
                        consultarRegistro(sc);
                    else
                        System.out.println("Aún no has creado el fichero.");
                    break;
                case 4:
                    if (rutaFichero.exists())
                        consultarTodosLosRegistros();
                    else
                        System.out.println("Aún no has creado el fichero.");

                    break;
                case 5:
                    if (rutaFichero.exists())
                        modificarRegistro(sc);
                    else
                        System.out.println("Aún no has creado el fichero.");
                    break;
                case 6:
                    if (rutaFichero.exists())
                        borrarRegistro(sc);
                    else
                        System.out.println("Aún no has creado el fichero.");
                    break;
                default:
                    System.out.println("Esa opción no existe.");
                    break;
            }
            mostrarMenu();
            entradaUser = sc.nextInt();
        }
    }

    private static void crearArchivo(File rutaFichero, Scanner sc) throws FileNotFoundException, IOException {
        if (rutaFichero.exists()) {
            System.out.println("El archivo ya existe. Quieres sobreescribirlo?");
            String respuesta = sc.nextLine();
            if (!respuesta.equalsIgnoreCase("S")) {
                System.out.println("No se ha sobrescrito el fichero.");
                return;
            }
        } else {
            try (ObjectOutputStream fichero = new ObjectOutputStream(new FileOutputStream(rutaFichero))) {
                System.out.println("Fichero creado con éxito.");
            }
        }
    }

    private static void anhadirRegistros(Scanner sc) throws FileNotFoundException, IOException, ClassNotFoundException {
        try (MiObjectOutputStream fichero = new MiObjectOutputStream(new FileOutputStream("corredores.dat"))) {
            String nombre;
            int dorsal;
            double tiempo;

            System.out.println("Introduce los datos del corredor.");
            System.out.println("Nombre: ");
            nombre = sc.next();

            while (!nombre.equals("*")) {
                System.out.println("Dorsal: ");
                dorsal = sc.nextInt();

                Corredor corredorConDorsal = corredorConDorsal(dorsal);

                if (corredorConDorsal != null) {
                    System.out.println("Ya existe un corredor con ese dorsal. Inténtalo de nuevo.");
                    return;
                }

                System.out.println("Tiempo");
                tiempo = sc.nextDouble();

                fichero.writeObject(new Corredor(nombre, dorsal, tiempo));

                System.out.println("Nombre: ");
                nombre = sc.next();
            }
        }
    }

    private static Corredor corredorConDorsal(int dorsal)
            throws FileNotFoundException, IOException, ClassNotFoundException {
        try (ObjectInputStream fichero = new ObjectInputStream(new FileInputStream("corredores.dat"))) {
            Corredor corredor = (Corredor) fichero.readObject();

            while (dorsal != corredor.getDorsal()) {
                corredor = (Corredor) fichero.readObject();
            }
            if (corredor.getDorsal() == dorsal)
                return corredor;
            return null;
        } catch (IOException e) {
            return null;
        }
    }

    private static void consultarRegistro(Scanner sc) {
        
    }

    private static void consultarTodosLosRegistros() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'consultarTodosLosRegistros'");
    }

    private static void modificarRegistro(Scanner sc) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'modificarRegistro'");
    }

    private static void borrarRegistro(Scanner sc) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'borrarRegistro'");
    }

    private static void mostrarMenu() {
        System.out.println("\nMenú de opciones\r\n" +
                "----------------\r\n" +
                "1.-Crear archivo\r\n" +
                "2.-Añadir registros\r\n" +
                "3.-Consultar un registro\r\n" +
                "4.-Consultar todos los registros\r\n" +
                "5.-Modificar un registro\r\n" +
                "6.-Borrar\r\n" +
                "7.-Salir\r\n" +
                "Elige una opción <1-7>\r");
    }
}
