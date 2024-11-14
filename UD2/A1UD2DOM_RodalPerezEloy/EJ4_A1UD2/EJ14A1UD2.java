package UD2.A1UD2.EJ4_A1UD2;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Scanner;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

import UD2.A1UD2.EJ4_A1UD2.Utilidades.UtilidadesDAT;
import UD2.A1UD2.EJ4_A1UD2.Utilidades.UtilidadesXML;

public class EJ14A1UD2 {
    public static void main(String[] args)
            throws IOException, ClassNotFoundException, TransformerException, ParserConfigurationException {

        Scanner sc = new Scanner(System.in);
        mostrarMenu();
        int opcion = sc.nextInt();
        String ruta = "UD2\\A14UD2\\Hoteles.dat";
        String rutaXML = "UD2\\A14UD2\\Hoteles.xml";
        ObjectOutputStream ficheroDat = UtilidadesDAT.crearArchivo(ruta, sc);
        while (opcion != 4) {
            switch (opcion) {
                case 1:
                    Hotel hotel = pedirDatosHotel(sc, ruta);
                    ficheroDat.writeObject(hotel);
                    break;
                case 2:
                    UtilidadesDAT.leerFichero(ruta);
                    break;
                case 3:
                    ArrayList<Hotel> hoteles = UtilidadesDAT.getHoteles(ruta);
                    UtilidadesXML.grabarEnDOM(UtilidadesXML.crearDocumentoXML(hoteles), rutaXML);
                    break;
                default:
                    System.out.println("Opción inválida.");
                    break;
            }
            mostrarMenu();
            opcion = sc.nextInt();
        }
        UtilidadesDAT.cerrarFichero(ficheroDat);
    }

    private static Hotel pedirDatosHotel(Scanner sc, String rutaDat) throws IOException {
        int idHotel = UtilidadesDAT.getUltimoID(rutaDat) + 1;

        System.out.println("Introduce el nombre del hotel.");
        sc.nextLine();
        String nombreHotel = sc.nextLine();
        System.out.println("Introduce la fecha de inauguración del hotel (aaaa/mm/dd).");
        Date fechaInauguracionHotel = Date.valueOf(sc.next());
        sc.nextLine();
        System.out.println("Introduce el teléfono del hotel.");
        int telefonoHotel = sc.nextInt();
        sc.nextLine();
        System.out.println("Introduce la calle del hotel.");
        String calleHotel = sc.nextLine();
        System.out.println("Introduce el número de edificio del hotel.");
        int edificioHotel = sc.nextInt();
        sc.nextLine();
        System.out.println("Introduce el código postal del hotel.");
        String cpHotel = sc.nextLine();

        Direccion direccion = new Direccion(calleHotel, edificioHotel, cpHotel);
        Hotel hotel = new Hotel(idHotel, nombreHotel, fechaInauguracionHotel, telefonoHotel, direccion);
        return hotel;
    }

    private static void mostrarMenu() {
        System.out.println("Menú de opciones\r\n"
                + "---------------------------------\r\n");
        System.out.println("1.- Introducir hoteles");
        System.out.println("2.- Listar hoteles");
        System.out.println("3.- Crear XML de hoteles");
        System.out.println("4.- Salir");
        System.out.println("Elige una opción <1-3>");
    }
}