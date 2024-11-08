package EJ1_A4UD1;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.RandomAccessFile;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Scanner;

public class EJ1_A4UD1 {
    public static void main(String[] args) throws FileNotFoundException, IOException, ParseException {
        Scanner sc = new Scanner(System.in);
        String rutaAlumno = "EJ1_A4UD1\\alumno.dat";
        String rutaNotaAlumno = "EJ1_A4UD1\\notasAlumno.dat";
        ObjectOutputStream notasAlumnos = null;
        File notaAlumno = new File(rutaNotaAlumno);
        if (!notaAlumno.exists()) {
            notaAlumno.createNewFile();
        }
        File archivoAlumno = new File(rutaAlumno);
        if (!archivoAlumno.exists()) {
            archivoAlumno.createNewFile();
        }

        mostrarMenu();
        int opcion = sc.nextInt();

        while (opcion != 7) {
            switch (opcion) {
                case 1:
                    listarDatosAlumnos(rutaAlumno);
                    break;
                case 2:
                    listarNotasTodos(rutaNotaAlumno);
                    break;
                case 3:
                    anhadirAlumno(rutaNotaAlumno, rutaAlumno);
                    break;
                case 4:
                    visualizarInfoAlumno(rutaAlumno, rutaNotaAlumno);
                    break;
                case 5:
                    anhadirBorrarNumeros();
                    break;
                case 6:
                    escribirTxt();
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

    private static void mostrarMenu() {
        System.out.println("Menú de opciones\r\n"
                + "---------------------------------\r\n");
        System.out.println("1.- Listar datos de todos los alumnos");
        System.out.println("2.- Listar las notas de todos los alumnos");
        System.out.println("3.- Añadir alumno");
        System.out.println("4.- Visualizar información de un alumno");
        System.out.println("5.- Añadir o borrar número de teléfono");
        System.out.println("6.- Escribir alumnos en fichero .txt");
        System.out.println("7.- Salir");
        System.out.println("Elige una opción <1-7>");
    }

    public static final int TAMANIO_REGISTRO = 100;

    private static void listarDatosAlumnos(String nombreArchivo) {
        try (RandomAccessFile rafAlumnos = new RandomAccessFile(nombreArchivo, "r")) {
            long numRegistros = rafAlumnos.length() / TAMANIO_REGISTRO;

            for (int i = 0; i < numRegistros; i++) {
                rafAlumnos.seek(i * TAMANIO_REGISTRO);
                int numero = rafAlumnos.readInt();
                String nombre = leerCadena(rafAlumnos, 50);
                String apellido1 = leerCadena(rafAlumnos, 50);
                String apellido2 = leerCadena(rafAlumnos, 50);
                long fechaNacimiento = rafAlumnos.readLong();
                int numTelefonos = rafAlumnos.readInt();
                boolean borrado = rafAlumnos.readBoolean();

                // Mostrar solo los alumnos que no están borrados
                if (!borrado) {
                    Date fechaNac = new Date(fechaNacimiento);
                    System.out.printf(
                            "Número: %d, Nombre: %s, Apellido1: %s, Apellido2: %s, Fecha Nac: %s, Teléfonos: %d\n",
                            numero, nombre, apellido1, apellido2, fechaNac, numTelefonos);

                    // Si es necesario, también puedes leer y mostrar los teléfonos aquí
                    for (int j = 0; j < numTelefonos; j++) {
                        String telefono = leerCadena(rafAlumnos, 20);
                        System.out.printf("Teléfono %d: %s\n", j + 1, telefono);
                    }
                } else {
                    // Si quieres mostrar los alumnos borrados, puedes hacerlo aquí
                    // System.out.printf("Alumno borrado: %d\n", numero);
                }
            }
        } catch (IOException e) {
            System.out.println("Error al leer el archivo: " + e.getMessage());
        }
    }

    private static String leerCadena(RandomAccessFile raf, int longitud) throws IOException {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < longitud; i++) {
            sb.append(raf.readChar());
        }
        return sb.toString().trim(); // Eliminar espacios en blanco al final
    }

    private static void listarNotasTodos(String nombreArchivo) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(nombreArchivo))) {
            while (true) {
                try {
                    NotaAlumno notaAlumno = (NotaAlumno) ois.readObject();
                    System.out.println(notaAlumno.toString());
                } catch (EOFException e) {
                    break;
                }
            }

        } catch (IOException e) {
            System.out.println("Error de entrada/salida: " + e.getMessage());
        } catch (ClassNotFoundException e) {
            System.out.println("Clase no encontrada: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Se produjo un error inesperado: " + e.getMessage());
        }
    }

    private static void anhadirAlumno(String archivoNotas, String archivoAlumno)
            throws FileNotFoundException, IOException, ParseException {
        Scanner sc = new Scanner(System.in);
        int numero = generarNumAlumno(archivoAlumno);
        System.out.println("Introduce el nombre del alumno:");
        String nombre = sc.nextLine();
        System.out.println("Introduce el primer apellido del alumno:");
        String apellido1 = sc.nextLine();
        System.out.println("Introduce el segundo apellido del alumno:");
        String apellido2 = sc.nextLine();
        System.out.println("Introduce la fecha de nacimiento del alumno (dd/MM/yyyy):");
        String fechaNac = sc.nextLine();
        Date fecha = new SimpleDateFormat("dd/MM/yyyy").parse(fechaNac);

        ArrayList<String> telefonos = new ArrayList<>();
        HashSet<String> telefonoSet = new HashSet<>(telefonos);

        System.out.println("Introduce la cantidad de números de teléfono que tiene el alumno.");
        int numTelefonos = Integer.parseInt(sc.nextLine());

        for (int i = 0; i < numTelefonos; i++) {
            System.out.println("Introduce el teléfono " + (i + 1) + ":");
            String telefono = sc.nextLine();
            telefonos.add(telefono);
            telefonoSet.add(telefono);
        }

        if (telefonoSet.size() != telefonos.size()) {
            System.out.println("Error: Los números de teléfono deben ser únicos.");
            return;
        }

        ArrayList<NotaModulo> notasModulo = new ArrayList<>();

        System.out.println("Introduce el número de módulos que tiene el alumno.");
        int numModulos = sc.nextInt();
        sc.nextLine();

        for (int i = 0; i < numModulos; i++) {
            System.out.println("Introduce el nombre del módulo " + (i + 1) + ":");

            String nombreModulo = sc.nextLine();

            System.out.println("Introduce la nota del módulo " + (i + 1) + ":");
            double notaModulo = sc.nextDouble();

            notasModulo.add(new NotaModulo(nombreModulo, notaModulo));
            sc.nextLine();
        }

        try (
                RandomAccessFile rafAlumnos = new RandomAccessFile(archivoAlumno, "rw");
                ObjectOutputStream oosNotas = new ObjectOutputStream(new FileOutputStream(archivoNotas, true))) {

            rafAlumnos.seek(rafAlumnos.length());

            rafAlumnos.writeInt(numero);
            escribirCadena(rafAlumnos, nombre, 50);
            escribirCadena(rafAlumnos, apellido1, 50);
            escribirCadena(rafAlumnos, apellido2, 50);
            rafAlumnos.writeLong(fecha.getTime());
            rafAlumnos.writeInt(telefonos.size());

            for (String telefono : telefonos) {
                escribirCadena(rafAlumnos, telefono, 20);
            }

            rafAlumnos.writeBoolean(false);

            // Guardar notas del alumno
            oosNotas.writeObject(new NotaAlumno(numero, notasModulo));

            oosNotas.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void escribirCadena(RandomAccessFile raf, String cadena, int longitud) throws IOException {
        StringBuilder sb = new StringBuilder(cadena);
        while (sb.length() < longitud) {
            sb.append(' ');
        }
        raf.writeChars(sb.toString());
    }

    private static int generarNumAlumno(String nombreArchivo) throws FileNotFoundException, IOException {
        int maxNumero = 0;

        try (RandomAccessFile archivoAcceso = new RandomAccessFile(nombreArchivo, "r")) {
            long numRegistros = archivoAcceso.length() / TAMANIO_REGISTRO;

            for (int i = 0; i < numRegistros; i++) {
                archivoAcceso.seek(i * TAMANIO_REGISTRO);
                int numero = archivoAcceso.readInt();

                if (numero > maxNumero) {
                    maxNumero = numero;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return maxNumero + 1;

    }

    private static void visualizarInfoAlumno(String archivoAlumno, String archivoNotas) {
        System.out.println("Introduce el número del alumno que desees visualizar:");
        Scanner sc = new Scanner(System.in);
        int numAlumno = sc.nextInt();
        try (ObjectInputStream oisAlumnos = new ObjectInputStream(new FileInputStream(archivoAlumno));
                ObjectInputStream oisNotas = new ObjectInputStream(new FileInputStream(archivoNotas))) {
            while (true) {
                try {
                    Alumno alumno = (Alumno) oisAlumnos.readObject();
                    NotaAlumno notaAlumno = (NotaAlumno) oisNotas.readObject();
                    if (alumno.getNumero() == numAlumno) {
                        System.out.println(alumno);
                        System.out.println(notaAlumno);
                        break;
                    }
                } catch (EOFException e) {
                    System.out.println("No se ha encontrado el alumno con el número " + numAlumno);
                    break;
                }
            }
            sc.close();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private static void anhadirBorrarNumeros() {
        System.out.println("Introduce el número del alumno.");
        Scanner sc = new Scanner(System.in);
        int numAlumno = sc.nextInt();
        System.out.println("Introduce el número de teléfono que quieres añadir.");
        int numTelefono = sc.nextInt();
    }

    private static void escribirTxt() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'escribirTxt'");
    }

}