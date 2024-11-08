package UD2.A3UD2Transformaciones_RodalPerezEloy.EJ3_A3UD2.EJ3A_A3UD2;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class Manejador extends DefaultHandler {
    int numAlumnos = 0;
    int numAlumnosTotales = 0;
    String nombreEtiqueta = "";

    @Override
    public void startDocument() throws SAXException {
        System.out.println("NOTAS");
        System.out.println("======================");
    }

    @Override
    public void startElement(String uri, String localName, String name, Attributes attributes) throws SAXException {
        if (localName.equals("nota")) {
            int nota = Integer.valueOf(attributes.getValue("valor"));
            numAlumnos = 0;
            switch (nota) {
                case 10, 9:
                    System.out.println("Sobresaliente " + nota);
                    break;

                case 8, 7:
                    System.out.println("Notable " + nota);
                    break;

                case 6:
                    System.out.println("Bien");
                    break;

                case 5:
                    System.out.println("Suficiente");
                    break;

                default:
                    System.out.println("Insuficiente " + nota);
                    break;
            }
        } else if (localName.equals("alumno")) {
            nombreEtiqueta = "alumno";
            numAlumnos++;
            numAlumnosTotales++;
        }
    }

    @Override
    public void endElement(String uri, String localName, String name)
            throws SAXException {
        if (localName.equals("nota")) {
            System.out.println("Num. de alumnos: " + numAlumnos);
            System.out.println("-------------------------------");
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        if (nombreEtiqueta.equals("alumno")) {
            System.out.println("\t\t" + String.valueOf(ch, start, length));
            nombreEtiqueta = "";
        }
    }

    @Override
    public void endDocument() throws SAXException {
        System.out.print("\n=====================================\n");
        System.out.println("Total de alumnos: " + numAlumnosTotales);
    }
}
