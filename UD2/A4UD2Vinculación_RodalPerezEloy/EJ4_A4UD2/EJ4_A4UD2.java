package UD2.A4UD2Vinculación_RodalPerezEloy.EJ4_A4UD2;

import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Writer;
import java.util.ArrayList;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

public class EJ4_A4UD2 {
    public static void main(String[] args) throws IOException {
        ArrayList<Hotel> hoteles = leerHotelesDat();

        JAXBContext contexto = null;
        Marshaller m = null;
        CadenaHotelera cadenaHotelera = new CadenaHotelera(hoteles, "SOL CONFORT",
                "AB237237287");

        try {
            contexto = JAXBContext.newInstance(CadenaHotelera.class);
            m = contexto.createMarshaller();
            m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            Writer w = new FileWriter("UD2\\A4UD2Vinculación_RodalPerezEloy\\EJ4_A4UD2\\Hoteles.xml");
            m.marshal(cadenaHotelera, w);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }

    public static ArrayList<Hotel> leerHotelesDat() throws IOException {
        ArrayList<Hotel> hoteles = new ArrayList<>();
        ObjectInputStream ois = null;
        try {
            Hotel hotel;
            ois = new ObjectInputStream(
                    new FileInputStream("UD2\\A4UD2Vinculación_RodalPerezEloy\\EJ4_A4UD2\\Hoteles.dat"));
            while (true) {
                hotel = (Hotel) ois.readObject();
                System.out.println(hotel);
                hoteles.add(hotel);
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }  catch (EOFException e) {
            System.out.println("");
        } catch (IOException e) {
            e.printStackTrace();
        }
        ois.close();
        return hoteles;

    }
}
