package UD2.A2UD2.EJ1_A2UD2;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

/*
* Gestiona los eventos cuando se procesa un documento xml.
* Se le indica al analizador lo que tiene que hacer.
*/

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class Manejador extends DefaultHandler {

    BufferedWriter fichero;

    int numActores = 0;
    BufferedWriter archivo;

    @Override
    public void startDocument() throws SAXException {
        try {
            fichero = inicializarFichero();
            fichero.write("Información de actores:\n");
            fichero.write("========================\n");
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void startElement(String uri, String localName, String name, Attributes attributes) throws SAXException {
        try {
            if (localName.equals("Actor")) {
                numActores++;
                fichero.write(localName + " " + numActores + "\n");
                fichero.write("***********\n");
                for (int i = 0; i < attributes.getLength(); i++) {
                    fichero.write(attributes.getQName(i) + ": " + attributes.getValue(i) + "\n");
                }
            } else if (!localName.equals("Actores")) {
                fichero.write(localName + ": ");
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void endElement(String uri, String localName, String name)
            throws SAXException {
        try {
            if (localName.equals("DataNacemento")) {
                fichero.write("-----------------\n");
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        try {
            fichero.write(String.valueOf(ch, start, length) + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void endDocument() throws SAXException {
        try {
            fichero.write("\nTotal de actores: " + numActores);
            fichero.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private BufferedWriter inicializarFichero() {
        try {
            fichero = new BufferedWriter(new FileWriter("UD2\\EJ1_A2UD2\\Actores.txt"));
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return fichero;
    }
}
