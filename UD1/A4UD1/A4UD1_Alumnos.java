package A4UD1;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.io.RandomAccessFile;
import java.io.ObjectInputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Scanner;

public class A4UD1_Alumnos {

    static SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        File archivo = new File("UD1\\A4UD1\\notasAlumno.dat");
        ObjectOutputStream notasAlumnos = null;
        mostrarMenu();
        int opcion = sc.nextInt();

        while (opcion != 7) {
            switch (opcion) {
                case 1:
                    listarDatos();
                    break;
                case 2:
                    listarNotasTodos();
                    break;
                case 3:
                    anhadirAlumno();
                    break;
                case 4:
                    visualizarInfoAlumno();
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

    private static void listarDatos() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("alumnos.dat"))) {
            while (true) {
                Alumno alumno = (Alumno) ois.readObject();
                if (!alumno.isBorrado()) {
                    System.out.println(alumno);
                }
            }
        } catch (EOFException e) {
            System.out.println("Fin del listado de alumnos.");
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error al leer los alumnos: " + e.getMessage());
        }
    }

    private static void listarNotasTodos() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("NotasAlumno.dat"))) {
            while (true) {
                NotaAlumno notaAlumno = (NotaAlumno) ois.readObject();
                System.out.println("Alumno número: " + notaAlumno.getNumero());
                for (NotaModulo nota : notaAlumno.getNotas()) {
                    System.out.println(nota);
                }
            }
        } catch (EOFException e) {
            System.out.println("Fin del listado de notas.");
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error al leer las notas: " + e.getMessage());
        }
    }

    private static void anhadirAlumno() {
        Scanner sc = new Scanner(System.in);

        System.out.println("Introduce el nombre del alumno: ");
        String nombre = sc.nextLine();
        System.out.println("Introduce la fecha de nacimiento del alumno (dd/MM/yyyy): ");
        String fechaNac = sc.nextLine();
        System.out.println("Introduce el teléfono del alumno: ");
        String telefono = sc.nextLine();
        System.out.println("Introduce el módulo: ");
        String modulo = sc.nextLine();
        System.out.println("Introduce la nota: ");
        double nota = sc.nextDouble();
        Alumno nuevoAlumno = new Alumno();

        try (ObjectOutputStream oosAlumnos = new ObjectOutputStream(new FileOutputStream("alumnos.dat", true));
                ObjectOutputStream oosNotas = new ObjectOutputStream(new FileOutputStream("NotasAlumno.dat", true))) {
            oosAlumnos.writeObject(nuevoAlumno);
           // NotaAlumno notaAlumno = new NotaAlumno(nuevoAlumno.getNumero(), notaAlumno);
           // oosNotas.writeObject(notaAlumno);
            System.out.println("Alumno y notas añadidos correctamente.");
        } catch (IOException e) {
            System.out.println("Error al añadir alumno o notas: " + e.getMessage());
        }
    }

    private static void visualizarInfoAlumno() {
        System.out.println("Introduce el número del alumno a consultar: ");
        int numeroAlumno = new Scanner(System.in).nextInt();

        try (ObjectInputStream oisAlumnos = new ObjectInputStream(new FileInputStream("alumnos.dat"));
                ObjectInputStream oisNotas = new ObjectInputStream(new FileInputStream("NotasAlumno.dat"))) {

            Alumno alumno = null;
            NotaAlumno notaAlumno = null;

            while (true) {
                Alumno a = (Alumno) oisAlumnos.readObject();
                if (a.getNumero() == numeroAlumno && !a.isBorrado()) {
                    alumno = a;
                    break;
                }
            }

            while (true) {
                NotaAlumno n = (NotaAlumno) oisNotas.readObject();
                if (n.getNumero() == numeroAlumno) {
                    notaAlumno = n;
                    break;
                }
            }

            if (alumno != null && notaAlumno != null) {
                System.out.println(alumno);
                System.out.println(notaAlumno);
            } else {
                System.out.println("Alumno no encontrado o sin notas.");
            }
        } catch (EOFException e) {
            System.out.println("No se encontró el alumno con el número proporcionado.");
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error al consultar alumno o notas: " + e.getMessage());
        }
    }

    private static void anhadirBorrarNumeros() {
        System.out.println("Introduce el número del alumno: ");
        int numeroAlumno = new Scanner(System.in).nextInt();
        System.out.println("Introduce el teléfono a añadir o borrar: ");
        String telefono = new Scanner(System.in).nextLine();

        try (RandomAccessFile raf = new RandomAccessFile("alumnos.dat", "rw")) {
            long pos = 0;
            Alumno alumno = null;

            while (true) {
                pos = raf.getFilePointer();
                alumno = (Alumno) new ObjectInputStream(new FileInputStream(raf.getFD())).readObject();

                if (alumno.getNumero() == numeroAlumno && !alumno.isBorrado()) {
                    break;
                }
            }

            if (alumno.getTelefono().contains(telefono)) {
                System.out.println("El teléfono " + telefono + " EXISTE, ¿Quieres borrarlo? (S/N): ");
                if (new Scanner(System.in).nextLine().equalsIgnoreCase("S")) {
                    alumno.getTelefono().remove(telefono);
                }
            } else {
                System.out.println("El teléfono " + telefono + " NO EXISTE, ¿Quieres añadirlo? (S/N): ");
                if (new Scanner(System.in).nextLine().equalsIgnoreCase("S")) {
                    alumno.getTelefono().add(telefono);
                }
            }

            // Reescribir el registro actualizado
            raf.seek(pos);
            new ObjectOutputStream(new FileOutputStream(raf.getFD())).writeObject(alumno);
            System.out.println("Teléfono modificado. Información actual: ");
            System.out.println(alumno);
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error al modificar el teléfono: " + e.getMessage());
        }
    }

    private static void escribirTxt() {

        try (ObjectInputStream oisAlumnos = new ObjectInputStream(new FileInputStream("alumnos.dat"));
                ObjectInputStream oisNotas = new ObjectInputStream(new FileInputStream("NotasAlumno.dat"));
                PrintWriter pw = new PrintWriter(new FileWriter("alumnos.txt"))) {

            int totalAlumnos = 0;

            while (true) {
                try {
                    // Leer un alumno y sus notas
                    Alumno alumno = (Alumno) oisAlumnos.readObject();
                    NotaAlumno notaAlumno = (NotaAlumno) oisNotas.readObject();

                    // Escribir los datos del alumno en el archivo de texto
                    pw.println("----------------------DATOS ALUMNOS ----------------------------------------");
                    pw.println("ALUMNO NUMERO: " + alumno.getNumero());
                    pw.println("NOMBRE: " + alumno.getNombre());
                    pw.println("FECHA NACIMIENTO: " + alumno.getFechaNac());
                    pw.println("TELEFONOS: " + alumno.getTelefono());
                    pw.println("MODULO\tNOTA");

                    double sumaNotas = 0;
                    for (NotaModulo nota : notaAlumno.getNotas()) {
                        pw.println(nota.getAsignatura() + "\t" + nota.getNota());
                        sumaNotas += nota.getNota();
                    }

                    double media = sumaNotas / notaAlumno.getNotas().size();
                    pw.println("NOTA MEDIA: " + media);
                    pw.println("------------------------------------------------------------------------");

                    totalAlumnos++;
                } catch (EOFException e) {
                    // Al llegar al final de los archivos, salir del bucle
                    break;
                }
            }

            // Escribir el total de alumnos al final del archivo
            pw.println("TOTAL DE ALUMNOS: " + totalAlumnos);

            System.out.println("Exportación finalizada. Total de alumnos exportados: " + totalAlumnos);

        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error al exportar los alumnos: " + e.getMessage());
        }
    }

    public static void mostrarMenu() {
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

}
