package UD2.A1UD2.EJ4_A1UD2.Utilidades;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Scanner;

import UD2.A1UD2.EJ4_A1UD2.Hotel;
import UD2.A1UD2.EJ4_A1UD2.MiObjectOutputStream;

public class UtilidadesDAT {

    public static ObjectOutputStream crearArchivo(String ruta, Scanner sc) throws IOException {
        File rutaFichero = new File(ruta);
        ObjectOutputStream fichero = null;
        if (rutaFichero.exists()) {
            System.out.println("El fichero .dat ya existe. Quieres sobreescribirlo? (S/N)");
            String respuesta = sc.next();
            if (respuesta.equalsIgnoreCase("S")) {
                borrarFichero(rutaFichero);
                System.out.println("Se ha sobreescrito el fichero.");
            } else {
                fichero = new MiObjectOutputStream(new FileOutputStream(ruta, true));
                System.out.println("No se ha sobreescrito el fichero.");
                return fichero;
            }
        }

        try {
            fichero = new ObjectOutputStream(new FileOutputStream(ruta));
            System.out.println("Fichero creado con éxito.");
        } catch (IOException e) {
            System.out.println("Error al crear el fichero.");
        }
        return fichero;
    }

    public static void borrarFichero(File rutaFichero) {
        if (rutaFichero.delete())
            System.out.println("Fichero eliminado / sobreescrito con éxito.");
        else
            System.out.println("Error al eliminar el fichero.");
    }

    public static void cerrarFichero(ObjectOutputStream fichero) throws IOException {
        if (fichero != null) {
            fichero.close();
        }
    }

    public static void cerrarFichero(ObjectInputStream fichero) throws IOException {
        if (fichero != null) {
            fichero.close();
        }
    }

    public static ObjectInputStream crearFicheroEntrada(String ruta) throws IOException {
        ObjectInputStream ficheroEntrada = null;
        try {
            ficheroEntrada = new ObjectInputStream(new FileInputStream(ruta));
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
        return ficheroEntrada;
    }

    public static int getUltimoID(String ruta) throws IOException {
        ObjectInputStream ficheroEntrada = crearFicheroEntrada(ruta);
        int id = 0;
        Hotel hotel;
        while (true) {
            try {
                hotel = (Hotel) ficheroEntrada.readObject();
                id = hotel.getCodigoHotel();
            } catch (ClassNotFoundException e) {
                System.out.println("No se encuentra la clase Hotel.");
            } catch (IOException e) {
                System.out.println("Fin del fichero.");
                return id;
            } finally {
                cerrarFichero(ficheroEntrada);
            }
        }
    }

    public static void leerFichero(String ruta) throws IOException {
        ObjectInputStream ficheroEntrada = crearFicheroEntrada(ruta);
        try {
            Hotel hotel;
            do {
                System.out.println("---------------------------");
                hotel = (Hotel) ficheroEntrada.readObject();
                System.out.println(hotel);
            } while (hotel != null);
        } catch (ClassNotFoundException e) {
            System.out.println("Error.");
        } catch (FileNotFoundException e) {
            System.out.println("La ruta no existe.");
        } catch (IOException e) {
            System.out.println("Error, final del archivo.");
        } finally {
            if (ficheroEntrada != null) {
                try {
                    ficheroEntrada.close();
                } catch (IOException e) {
                    System.out.println("Error al cerrar el fichero de entrada.");
                }
            }
        }
    }

    public static ArrayList<Hotel> getHoteles(String ruta) throws IOException {
        ObjectInputStream ficheroEntrada = crearFicheroEntrada(ruta);
        ArrayList<Hotel> hoteles = new ArrayList<>();

            try {
                while (true) {
                Hotel hotel = (Hotel) ficheroEntrada.readObject();
                hoteles.add(hotel);
                }
            } catch (ClassNotFoundException e) {
                System.out.println("No se encuentra la clase Hotel.");
            } catch (IOException e) {
                System.out.println("Error.");
            } finally {
                    cerrarFichero(ficheroEntrada);
            }
        System.out.println("Hoteles guardados en el ArrayList.");
        return hoteles;
    }

}