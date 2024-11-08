package A34UD1;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Scanner;

public class EJ1_A3P4UD1 {
    public static void main(String[] args) throws FileNotFoundException, IOException {
        Scanner sc = new Scanner(System.in);
        File archivo = new File("UD1\\A34UD1\\corredores.dat");
        RandomAccessFile fichero = new RandomAccessFile(archivo, "rw");
        int tamanhoRegistro = 80;
        long numRegistros;

        if (archivo.length() > 0) {
            numRegistros = fichero.length() / tamanhoRegistro + 1;
        } else {
            numRegistros = 0;
        }

        if (archivo.exists()) {
            System.out.println("El fichero existe.");
            System.out.println("Hay un total de " + numRegistros + " registros");
        } else {
            System.out.println("El fichero no existe.");
        }

        try (fichero) {
            mostrarMenu();
            int opcion = sc.nextInt();

            while (opcion != 6) {
                switch (opcion) {
                    case 1:
                        numRegistros = anhadirRegistros(sc, fichero, numRegistros, tamanhoRegistro);
                        break;
                    case 2:
                        consultarRegistro(sc, fichero, tamanhoRegistro);
                        break;
                    case 3:
                        consultarTodosRegistros(fichero, numRegistros, tamanhoRegistro);
                        break;
                    case 4:
                        modificarRegistro(sc, fichero, numRegistros);
                        break;
                    case 5:
                        borrarRegistro(sc, fichero, tamanhoRegistro);
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
    }

    private static long anhadirRegistros(Scanner sc, RandomAccessFile fichero, long numRegistros, int tamanhoRegistro)
            throws IOException {
        String nombre;
        int dorsal;
        double tiempo;

        System.out.println("Introduce los datos del participante. Introduce '*' para terminar");
        System.out.println("Nombre: ");
        nombre = sc.next();

        while (!nombre.equals("*")) {
            if (fichero.length() > 0) {
                fichero.seek((numRegistros - 1) * tamanhoRegistro);
                fichero.readUTF();
                dorsal = (int) fichero.readInt() + 1;
            } else {
                dorsal = 1;
            }

            System.out.println("Tiempo: ");
            tiempo = sc.nextDouble();

            fichero.seek(numRegistros * tamanhoRegistro);
            fichero.writeUTF(nombre);
            fichero.writeInt(dorsal);
            fichero.writeDouble(tiempo);
            fichero.writeBoolean(false);
            numRegistros++;

            System.out.println("Nombre: ");
            nombre = sc.next();
        }
        return numRegistros;
    }

    private static void consultarRegistro(Scanner sc, RandomAccessFile fichero, int tamanhoRegistro)
            throws IOException {
        System.out.println("Introduce el número de dorsal del corredor: ");
        int dorsal = sc.nextInt();

        try {
            fichero.seek((dorsal - 1) * tamanhoRegistro);
            Corredor corredor = new Corredor(fichero.readUTF(), fichero.readInt(), fichero.readDouble(), fichero.readBoolean());
            if (!corredor.getBorrado()) {
                System.out
                        .println("Corredor: " + corredor.getNombre() + ", dorsal " + corredor.getDorsal() + " y tiempo "
                                + corredor.getTiempo());
            }
        } catch (IOException e) {
            System.out.println("El dorsal no existe.");
        }
    }

    private static void consultarTodosRegistros(RandomAccessFile fichero, long numRegistros, int tamanhoRegistro)
            throws IOException {
        for (int i = 0; i < numRegistros; i++) {
            fichero.seek(i * tamanhoRegistro);
            Corredor corredor = new Corredor(fichero.readUTF(), fichero.readInt(), fichero.readDouble(), fichero.readBoolean());
            if (!corredor.getBorrado()) {
                System.out
                        .println("Corredor: " + corredor.getNombre() + ", dorsal " + corredor.getDorsal() + " y tiempo "
                                + corredor.getTiempo());
            }
        }
    }

    private static void modificarRegistro(Scanner sc, RandomAccessFile fichero, long tamanhoRegistro)
            throws IOException {
        System.out.println("Introduce el dorsal del corredor que quieres modificar: ");
        int dorsal = sc.nextInt();

        try {
            fichero.seek((dorsal - 1) * tamanhoRegistro);

            Corredor corredor = new Corredor(fichero.readUTF(), fichero.readInt(), fichero.readDouble(), fichero.readBoolean());

            if (!corredor.getBorrado()) {
                corredor.setBorrado(true);
                fichero.seek((dorsal - 1) * tamanhoRegistro);

                System.out.println("Introduce el nuevo nombre: ");
                String nombre = sc.next();
                System.out.println("Introduce el nuevo tiempo: ");
                double tiempo = sc.nextDouble();

                fichero.writeUTF(nombre);
                fichero.writeInt(dorsal);
                fichero.writeDouble(tiempo);
            }
        } catch (IOException e) {
            System.out.println("No existe ese dorsal.");
        }
    }

    private static void borrarRegistro(Scanner sc, RandomAccessFile fichero, long tamanhoRegistro) throws IOException {
        System.out.println("Introduce el dorsal del corredor que quieres borrar: ");
        int dorsal = sc.nextInt();
        fichero.seek((dorsal - 1) * tamanhoRegistro);

        Corredor corredor = new Corredor(fichero.readUTF(), fichero.readInt(), fichero.readDouble(), fichero.readBoolean());

        if (!corredor.getBorrado()) {
            corredor.setBorrado(true);
            fichero.seek((dorsal - 1) * tamanhoRegistro);
            fichero.writeUTF(corredor.getNombre());
            fichero.writeInt(corredor.getDorsal());
            fichero.writeDouble(corredor.getTiempo());
            fichero.writeBoolean(true);
        }

    }

    public static void mostrarMenu() {
        System.out.println("Menú de opciones\r\n"
                + "---------------------------------\r\n");
        System.out.println("1.- Añadir registro");
        System.out.println("2.- Consultar un registro");
        System.out.println("3.- Consultar todos los registros");
        System.out.println("4.- Modificar un registro");
        System.out.println("5.- Borrar");
        System.out.println("6.- Salir");
        System.out.println("Elige una opción <1-6>");
    }
}
