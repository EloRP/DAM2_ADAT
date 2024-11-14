package UD2.A2UD2.EJ3_A2UD2;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Locale;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class Manejador extends DefaultHandler {

    private String nombreEtiqueta = "";
    private boolean haEscritoCategoria;
    SimpleDateFormat formatoSalida = new SimpleDateFormat("dd-MMMM-yyyy HH:mm:ss", Locale.forLanguageTag("es-ES"));
    SimpleDateFormat formatoFecha = new SimpleDateFormat("E, dd MMM yyyy HH:mm:ss z", Locale.ENGLISH);
    int numNoticia = 1;
    boolean empiezanLasNoticias = false;

    @Override
    public void startDocument() throws SAXException {
        System.out.println("Noticias\n");
        System.out.println("=====================================");
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {

        if (qName.equals("title")) {
            if (empiezanLasNoticias) {
                haEscritoCategoria = false;
                System.out.println("\n============= NOTICIA " + numNoticia + "========================");
                numNoticia++;
            } else {
                empiezanLasNoticias = true;
            }
            System.out.print("Titular: ");
            nombreEtiqueta = "titulo";
        } else if (qName.equals("pubDate")) {
            System.out.print("Fecha Publicacion: ");
            nombreEtiqueta = "fecha";
        } else if (qName.equals("creator")) {
            System.out.print("Autor: ");
            nombreEtiqueta = "autor";
        } else if (qName.equals("description")) {
            System.out.print("Descripcion: ");
            nombreEtiqueta = "descripcion";

        } else if (qName.equals("category")) {
            if (!haEscritoCategoria) {
                System.out.print("Categorías: ");
                haEscritoCategoria = true;
            }
            nombreEtiqueta = "categorias";
        }
    }

    @Override
    public void endElement(String uri, String qName, String name)
            throws SAXException {
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        switch (nombreEtiqueta) {
            case "titulo":
                System.out.print((String.valueOf(ch, start, length)).toUpperCase() + "\n");
                nombreEtiqueta = "";
                break;

            case "fecha":
                try {
                    DateTimeFormatter formatoEntrada = DateTimeFormatter
                            .ofPattern("EEE, dd MMM yyyy HH:mm:ss z", Locale.ENGLISH);
                    LocalDate fechaNoticia = LocalDate.parse(String.valueOf(ch, start, length), formatoEntrada);
                    LocalDate fechaActual = LocalDate.now();
                    long diferenciaDias = ChronoUnit.DAYS.between(fechaNoticia, fechaActual);
                    System.out.print(formatoSalida.format(
                            formatoFecha.parse(String.valueOf(ch, start, length))) +
                            ((diferenciaDias == 1) ? ", hace " + diferenciaDias + " día\n"
                                    : (diferenciaDias == 0) ? ", hoy\n" : ", hace " + diferenciaDias + " días\n"));
                } catch (ParseException e) {
                    System.out.println("Error al parsear.");
                }
                nombreEtiqueta = "";
                break;
            case "autor":
                System.out.print(String.valueOf(ch, start, length) + "\n");
                nombreEtiqueta = "";
                break;

            case "descripcion":
                System.out.print(String.valueOf(ch, start, length) + "\n");
                nombreEtiqueta = "";
                break;

            case "categorias":
                System.out.print("/" + String.valueOf(ch, start, length));
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
