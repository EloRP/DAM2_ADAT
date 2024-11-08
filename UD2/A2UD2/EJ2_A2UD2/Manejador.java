package UD2.A2UD2.EJ2_A2UD2;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class Manejador extends DefaultHandler {

    private String nombreEtiqueta = "";
    SimpleDateFormat outputFormat = new SimpleDateFormat("EEEEEEEE, dd MMMM yyyy");
    SimpleDateFormat fecha = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");

    @Override
    public void startDocument() throws SAXException {
        System.out.println("=====================================\n");
        System.out.println("Táboa de Mareas\n");
    }

    @Override
    public void startElement(String uri, String localName, String name, Attributes attributes) throws SAXException {
        if (localName.equals("date")) {
            System.out.println("=====================================");
            System.out.print("Fecha de la predicción: ");
            nombreEtiqueta = "date";
        } else if (localName.equals("nomePorto")) {
            System.out.print("Puerto: ");
            nombreEtiqueta = "nomePorto";
        } else if (localName.equals("mareas")) {
            for (int i = 0; i < attributes.getLength(); i++) {
                if (!attributes.getQName(i).contains("id"))
                    System.out.print(attributes.getQName(i) + ": " + attributes.getValue(i) + " \t");
            }
            System.out.println();
        }
    }

    @Override
    public void endElement(String uri, String localName, String name)
            throws SAXException {
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        switch (nombreEtiqueta) {
            case "date":
                try {
                    System.out.print(outputFormat.format(fecha.parse(String.valueOf(ch, start, length))) + "\t");
                    nombreEtiqueta = "";
                } catch (ParseException e) {
                    System.out.println("Error al parsear.");
                }

                break;
            case "nomePorto":
                System.out.println((String.valueOf(ch, start, length)).toUpperCase());
                nombreEtiqueta = "";
                break;

            default:
                break;
        }
    }

    @Override
    public void endDocument() throws SAXException {
        System.out.println("\n=====================================\n");
    }
}
