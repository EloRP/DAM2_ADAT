package UD2.A4UD2Vinculación_RodalPerezEloy.EJ4_A4UD2;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class GuardarHotelesEnDat {
    public static void main(String[] args) {
        ArrayList<String> telefonos = new ArrayList<>();
        telefonos.add("111111111");
        telefonos.add("123456789");
        telefonos.add("874462125");
        CadenaHotelera cadenaHotelera = new CadenaHotelera(null, "SOL CONFORT",
                "AB237237287");

        Hotel hotel1 = new Hotel(1, "Sancho", "8/10/2000", telefonos, new Direccion("Concepción Arenal", 2, 36999),
                cadenaHotelera);
        Hotel hotel2 = new Hotel(2, "Quijote", "8/10/2001", telefonos, new Direccion("Echegaray", 5, 36999),
                cadenaHotelera);
        Hotel hotel3 = new Hotel(3, "Dulcinea", "8/10/2002", telefonos, new Direccion("Jaime Janer", 7, 36999),
                cadenaHotelera);

        
        File ruta = new File("UD2\\A4UD2Vinculación_RodalPerezEloy\\EJ4_A4UD2\\Hoteles.dat");

        if (!tieneHoteles(ruta)) {
            try (ObjectOutputStream fichero = new ObjectOutputStream(new FileOutputStream(ruta))) {
                fichero.writeObject(hotel1);
                fichero.writeObject(hotel2);
                fichero.writeObject(hotel3);
            } catch (IOException e) {
                System.out.println("Error al escribir los hoteles en Hoteles.dat");
                e.printStackTrace();
            }
        } else {
            try (MiObjectOutputStream fichero = new MiObjectOutputStream(new FileOutputStream(ruta, true))) {
                fichero.writeObject(hotel1);
                fichero.writeObject(hotel2);
                fichero.writeObject(hotel3);
            } catch (IOException e) {
                System.out.println("Error al escribir los hoteles en Hoteles.dat");
                e.printStackTrace();
            }
        }
    }

    private static boolean tieneHoteles(File ruta) {
        boolean tieneDatos = false;
        try (ObjectInputStream fichero = new ObjectInputStream(new FileInputStream(ruta))) {
            while (true) {
                fichero.readObject();
                tieneDatos = true;
            }
        } catch (FileNotFoundException e) {
            System.out.println("Archivo no encontrado. Se va a crear.");
        } catch (EOFException e) {
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error al leer los hoteles");
        }
        return tieneDatos;
    }
}

/*   */