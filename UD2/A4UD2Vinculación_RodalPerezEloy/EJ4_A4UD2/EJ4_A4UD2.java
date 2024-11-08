package UD2.A4UD2Vinculación_RodalPerezEloy.EJ4_A4UD2;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;

public class EJ4_A4UD2 {
    public static void main(String[] args) {
        ArrayList<Hotel> hoteles = new ArrayList<>();
    }

    public static ArrayList<Hotel> leerHotelesDat() throws IOException {
        ArrayList<Hotel> hoteles = new ArrayList<>();
        ObjectInputStream ois = null;
        try {
            Hotel hotel;
            ois = new ObjectInputStream(
                    new FileInputStream("UD2\\A4UD2Vinculación_RodalPerezEloy\\EJ4_A4UD2\\hoteles.dat"));
            while (true) {
                hotel = (Hotel) ois.readObject();
                System.out.println(hotel);
                hoteles.add(hotel);
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        ois.close();
        return hoteles;

    }
}
