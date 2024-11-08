package UD2.A4UD2Vinculación_RodalPerezEloy.EJ4_A4UD2;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class GuardarHotelesFichero {
    public static void main(String[] args) {
        File rutaArchivo = new File("UD2\\A4UD2Vinculación_RodalPerezEloy\\EJ4_A4UD2\\hoteles.dat");
        ObjectOutputStream ficheroSinCabecera = null;
        MiObjectOutputStream ficheroConCabecera = null;
        if (rutaArchivo.exists()) {
            rutaArchivo.delete();
        }
        try {
            ficheroSinCabecera = new ObjectOutputStream(new FileOutputStream(rutaArchivo));
            ficheroConCabecera = new MiObjectOutputStream(new FileOutputStream(rutaArchivo));
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        ArrayList<String> telefonos = new ArrayList<>();
        CadenaHotelera cadenaHotelera = new CadenaHotelera("SOL CONFORT",
                "AB237237287");
        telefonos.add("111111111");
        telefonos.add("123456789");
        telefonos.add("874462125");

        Hotel hotel1 = new Hotel(1, "Sancho", "8/10/2000", telefonos, new Direccion("Concepción Arenal", 2, 36999),
                cadenaHotelera);
        Hotel hotel2 = new Hotel(2, "Quijote", "8/10/2001", telefonos, new Direccion("Echegaray", 5, 36999),
                cadenaHotelera);
        Hotel hotel3 = new Hotel(3, "Dulcinea", "8/10/2002", telefonos, new Direccion("Jaime Janer", 7, 36999),
                cadenaHotelera);

        try {
            ficheroSinCabecera.writeObject(hotel1);
            ficheroConCabecera.writeObject(hotel2);
            ficheroConCabecera.writeObject(hotel3);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}