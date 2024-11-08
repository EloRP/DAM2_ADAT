package UD2.A2UD2.EJ1_A2UD2.schema;

import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

public class EJ1_A2UD2 {
    public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException {
        try {
            //A

            // Crear una fábrica de SAXParser
            SAXParserFactory factoria = SAXParserFactory.newInstance();
            SAXParser saxProcesador = factoria.newSAXParser();
            // Obtener el XMLReader
            XMLReader procesador = saxProcesador.getXMLReader();

            String validar = "http://xml.org/sax/features/validation";
            String namespaces = "http://xml.org/sax/features/namespaces";

            System.out.println("1.");
            if (procesador.getFeature(validar)) {
                System.out.println("Valida.");
            } else {
                System.out.println("No valida.");
                procesador.setFeature(validar, true);
                System.out.println("Se ha activado la validación.");
            }

            if (procesador.getFeature(namespaces)) {
                System.out.println("Soporta espacios de nombres.");
            } else {
                System.out.println("No soporta espacios de nombres.");
                procesador.setFeature(namespaces, true);
                System.out.println("Se ha activado el soporte para espacios de nombres.");
            }

            // Configurar el manejador
            Manejador manejador = new Manejador();
            procesador.setContentHandler(manejador);
            // Analizar el documento XML
            procesador.parse(new InputSource("UD2\\EJ1_A2UD2\\Actores.xml"));

            //C

            
        } catch (SAXException e) {
            System.out.println(e.getMessage());
        } catch (ParserConfigurationException e) {
            System.out.println(e.getMessage());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

    }
}
