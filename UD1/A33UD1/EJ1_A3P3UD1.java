package A33UD1;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class EJ1_A3P3UD1 {
    public static void main(String[] args) throws ClassNotFoundException {
        Scanner sc = new Scanner(System.in);
        mostrarMenu();
        int opcion = sc.nextInt();

        while (opcion != 7) {
            switch (opcion) {
                case 1:
                    crearArchivo();
                    break;
                case 2:
                    anhadirRegistros();
                    break;
                case 3:
                    consultarRegistro();
                    break;
                case 4:
                    consultarTodosRegistros();
                    break;
                case 5:
                    modificarRegistro();
                    break;
                case 6:
                    borrarRegistro();
                    break;
                default:
                    System.out.println("Opción inválida.");
                    break;
            }
            sc.nextLine();
            mostrarMenu();
            opcion = sc.nextInt();
        }
    }

    public static void crearArchivo() {
        File archivo = new File("A33UD1\\corredores.dat");
        try (ObjectOutputStream fichero = new ObjectOutputStream(new FileOutputStream(archivo))) {
            Scanner sc = new Scanner(System.in);
            if (archivo.exists()) {
                System.out.println("El fichero ya existe. Quieres sobrescribirlo? (S/N)");
                String respuesta = sc.nextLine();
                if (!respuesta.equalsIgnoreCase("S")) {
                    sc.close();
                    return;
                }
            }
            System.out.println("Introduce los datos de los corredores.");
            String nombre;
            int dorsal;
            double tiempo;
            while (true) {
                System.out.println("Introduce el nombre del corredor: ");
                nombre = sc.next();
                if (nombre.equals("*")) {
                    System.out.println("Fin de la introducción de datos.");
                    break;
                }
                dorsal = pedirDorsal(sc);
                System.out.println("Introduce el tiempo del corredor (en minutos): ");
                tiempo = sc.nextDouble();
                Corredor corredor = new Corredor(nombre, dorsal, tiempo);
                fichero.writeObject(corredor);
            }
            fichero.close();
        } catch (Exception e) {
        }
    }

    public static int pedirDorsal(Scanner scanner) {
            Set<Integer> dorsales = obtenerDorsalesExistentes();
        int dorsal;
        do {
            System.out.print("Número de dorsal: ");
            dorsal = scanner.nextInt();
            scanner.nextLine();
            if (dorsales.contains(dorsal)) {
                System.out.println("El dorsal ya existe. Introduce otro.");
            }
        } while (dorsales.contains(dorsal));
        return dorsal;
    }

    public static Set<Integer> obtenerDorsalesExistentes() {
        Set<Integer> dorsales = new HashSet<>();
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("A33UD1\\corredores.dat"))) {
            Corredor corredor;
            while (true) {
                corredor = (Corredor) ois.readObject();
                dorsales.add(corredor.getDorsal());
            }
        } catch (EOFException e) {
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error al leer el archivo.");
        }

        return dorsales;
    }

    public static void anhadirRegistros() {
        if (!new File("A33UD1\\corredores.dat").exists()) {
            System.out.println("El archivo no existe. Crea el archivo primero.");
            return;
        }
        List<Corredor> corredores = leerTodosLosRegistros();

        try (MiObjectOutputStream oos = new MiObjectOutputStream(
                new FileOutputStream("A33UD1\\corredores.dat", true))) {
            Scanner sc = new Scanner(System.in);
            System.out.println("Introduce los datos de los corredores.");
            String nombre = null;
            int dorsal;
            double tiempo;

            while (true) {
                System.out.println("Introduce el nombre del corredor: ");
                nombre = sc.next();
                if (nombre.equals("*")) {
                    System.out.println("Fin de la introducción de datos.");
                    break;
                }
                dorsal = pedirDorsal(sc);
                System.out.println("Introduce el tiempo del corredor: ");
                tiempo = sc.nextDouble();
                Corredor corredor = new Corredor(nombre, dorsal, tiempo);
                corredores.add(corredor);
            }

            for (Corredor corredor : corredores) {
                oos.writeObject(corredor);
            }

            System.out.println("Registro añadido.");
        } catch (IOException e) {
            System.out.println("Error al añadir el registro: " + e.getMessage());
        }
    }

    public static void consultarRegistro() {
        Scanner sc = new Scanner(System.in);
        if (!new File("A33UD1\\corredores.dat").exists()) {
            System.out.println("El archivo no existe. Crea el archivo primero.");
            return;
        }
        System.out.println("Introduce el dorsal del corredor a consultar: ");
        int dorsal = sc.nextInt();

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("A33UD1\\corredores.dat"))) {
            Corredor corredor;
            while (true) {
                corredor = (Corredor) ois.readObject();
                if (corredor.getDorsal() == dorsal) {
                    System.out.println(corredor);
                    return;
                }
            }
        } catch (EOFException e) {
        } catch (IOException | ClassNotFoundException e) {
        }
    }

    public static void consultarTodosRegistros() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("A33UD1\\corredores.dat"))) {
            Corredor corredor;
            while (true) {
                corredor = (Corredor) ois.readObject();
                System.out.println(corredor);
            }
        } catch (EOFException e) {
            System.out.println("Fin de fichero.");
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error al leer el archivo.");
        }
    }

    public static void modificarRegistro() throws ClassNotFoundException {
        System.out.println("Introduce el número del dorsal del corredor que quieres eliminar:");
        Scanner sc = new Scanner(System.in);
        int dorsal = sc.nextInt();
        Corredor corredor = corredorConDorsal(dorsal);
        Corredor corredorEnFichero;

        if (corredor == null) {
            System.out.println("No existe un corredor con ese dorsal.");
        } else {
            System.out.println("Escribe el nuevo nombre del corredor.");
            String nombre = sc.nextLine();
            System.out.println("Escribe el nuevo tiempo del corredor: ");
            double tiempo = sc.nextDouble();
            corredor.setNombre(nombre);
            corredor.setTiempo(tiempo);
            File ficheroCopiaRuta = new File("A33UD1\\corredoresCopia.dat");
            File ficheroOriginalRuta = new File("A33UD1\\corredores.dat");
            try (ObjectOutputStream ficheroCopia = new ObjectOutputStream(
                    new FileOutputStream(ficheroCopiaRuta))) {
                try (ObjectInputStream ficheroOriginal = new ObjectInputStream(
                        new FileInputStream(ficheroOriginalRuta))) {
                    while (true) {
                        corredorEnFichero = (Corredor) ficheroOriginal.readObject();
                        if (corredorEnFichero.getDorsal() == corredor.getDorsal()) {
                            ficheroCopia.writeObject(corredor);
                        } else {
                            ficheroCopia.writeObject(corredorEnFichero);
                        }
                    }
                }
            } catch (IOException e) {
            }
            ficheroOriginalRuta.delete();
            ficheroCopiaRuta.renameTo(ficheroOriginalRuta);
            System.out.println("Corredor con el dorsal " + dorsal + " modificado con éxito.");
        }
    }

    private static void borrarRegistro() throws ClassNotFoundException {
        System.out.println("Introduce el número del dorsal del corredor que quieres eliminar:");
        Scanner sc = new Scanner(System.in);
        int dorsal = sc.nextInt();
        Corredor corredor = corredorConDorsal(dorsal);
        Corredor corredorEnFichero;

        if (corredor == null) {
            System.out.println("No existe un corredor con ese dorsal.");
        } else {
            File ficheroCopiaRuta = new File("A33UD1\\corredoresCopia.dat");
            File ficheroOriginalRuta = new File("A33UD1\\corredores.dat");
            try (ObjectOutputStream ficheroCopia = new ObjectOutputStream(
                    new FileOutputStream(ficheroCopiaRuta))) {
                try (ObjectInputStream ficheroOriginal = new ObjectInputStream(
                        new FileInputStream(ficheroOriginalRuta))) {
                    while (true) {
                        corredorEnFichero = (Corredor) ficheroOriginal.readObject();
                        if (corredorEnFichero.getDorsal() != corredor.getDorsal()) {
                            ficheroCopia.writeObject(corredorEnFichero);
                        }
                    }
                }
            } catch (IOException e) {
            }
            ficheroOriginalRuta.delete();
            ficheroCopiaRuta.renameTo(ficheroOriginalRuta);
            System.out.println("Corredor con el dorsal " + dorsal + " eliminado con éxito.");
        }
    }

    public static Corredor corredorConDorsal(int dorsal) throws ClassNotFoundException {
        try (ObjectInputStream fichero = new ObjectInputStream(
                new FileInputStream("A33UD1\\corredores.dat"))) {
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

    public static List<Corredor> leerTodosLosRegistros() {
        List<Corredor> corredores = new ArrayList<>();

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("A33UD1\\corredores.dat"))) {
            Corredor corredor;

            while (true) {
                corredor = (Corredor) ois.readObject();
                corredores.add(corredor);
            }
        } catch (EOFException e) {

        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error al leer los registros: " + e.getMessage());
        }

        return corredores;
    }

    public static void mostrarMenu() {
        System.out.println("Menú de opciones\r\n"
                + "---------------------------------\r\n");
        System.out.println("1.- Crear archivo");
        System.out.println("2.- Añadir registro");
        System.out.println("3.- Consultar un registro");
        System.out.println("4.- Consultar todos los registros");
        System.out.println("5.- Modificar un registro");
        System.out.println("6.- Borrar");
        System.out.println("7.- Salir");
        System.out.println("Elige una opción <1-7>");
    }
}
