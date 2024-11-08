package UD2.A1UD2.EJ2_A1UD2;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class EJ12A1UD2 {
    public static void main(String[] args) {
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        File archivo = new File("UD2\\A12UD2\\Actores.txt");

        dbf.setValidating(true);
        dbf.setNamespaceAware(true);
        dbf.setIgnoringElementContentWhitespace(true);

        try (FileWriter fichero = new FileWriter(archivo)) {
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document documento = (Document) db.parse(new File("UD2\\A12UD2\\Actores.xml"));

            fichero.write("Actores\n");
            Element raiz = documento.getDocumentElement();
            Node nodoActual = raiz.getFirstChild();

            do {
                guardadoElementos(fichero, nodoActual);
                guardadoSubelementos(fichero, nodoActual);
                nodoActual = nodoActual.getNextSibling();
            } while (nodoActual != null);
            System.out.println("El fichero se ha guardado en el directorio.");
        } catch (ParserConfigurationException e) {
            System.out.println(e.getMessage());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        } catch (SAXException e) {
            System.out.println(e.getMessage());
        }

    }

    private static void guardadoSubelementos(FileWriter fichero, Node nodoActual) throws IOException {
        NodeList subelementos = nodoActual.getChildNodes();
        for (int i = 0; i < subelementos.getLength(); i++) {
            fichero.write(
                    subelementos.item(i).getNodeName() + ": " + subelementos.item(i).getTextContent() + "\n");
        }
    }

    private static void guardadoElementos(FileWriter fichero, Node nodoActual) throws IOException {
        NamedNodeMap nodos = nodoActual.getAttributes();
        for (int i = 0; i < nodos.getLength(); i++) {
            fichero.write("--------------\n");
            fichero.write(nodos.item(i).getNodeName() + ": " + nodos.item(i).getNodeValue() + "\n");
        }
    }
}
