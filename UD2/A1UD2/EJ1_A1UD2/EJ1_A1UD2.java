package UD2.A1UD2.EJ1_A1UD2;

import java.io.File;
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

public class EJ1_A1UD2 {
    public static void main(String[] args) throws SAXException, IOException {
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();

        // 1
        System.out.println("Valida? " + dbf.isValidating());
        System.out.println("\n");

        if (!dbf.isValidating()) {
            dbf.setValidating(true);
        }

        // 2
        System.out.println("Soporta espacios de nombre?" + dbf.isNamespaceAware());
        System.out.println("\n");

        if (!dbf.isNamespaceAware()) {
            dbf.setNamespaceAware(true);
        }

        // 3
        System.out.println("Ignora comentarios?" + dbf.isIgnoringComments());
        System.out.println("\n");

        if (!dbf.isIgnoringComments()) {
            dbf.setIgnoringComments(true);
        }

        dbf.setIgnoringElementContentWhitespace(true);

        // 4
        try {
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document documento = (Document) db.parse(new File("UD2\\A1UD2\\Actores.xml"));
            System.out.println("\n");
            System.out.println("Tipo de codificación: " + documento.getInputEncoding());
            System.out.println("\n");
            // 5
            System.out.println("Tiene el documento un DTD asociado? " + documento.getDoctype());
            System.out.println("\n");
            // 6
            Element raiz = documento.getDocumentElement();
            System.out.println("Elemento raíz del documento: " + raiz.getNodeName());
            System.out.println("Valor del nodo: " + raiz.getNodeValue());
            System.out.println("Número de hijos: " + raiz.getChildNodes().getLength());

            System.out.println("\n");
            // 7

            // 8
            Node nodo = raiz.getFirstChild().getNextSibling();
            System.out.println("Nombre nodo:" + nodo.getNodeName() + " Valor del nodo:" + nodo.getNodeValue());

            // El nodo accede al primer hijo, pero debido al método getNextSibling, accede
            // en su lugar al segundo.
            // 9
            System.out.println("Nombre del nodo: " + nodo.getNodeName());
            System.out.println("Valor del nodo: " + nodo.getNodeValue());
            NamedNodeMap atributos = nodo.getAttributes();

            for (int i = 0; i < atributos.getLength(); i++) {
                System.out.println("<" + atributos.item(i).getNodeName() + ">: " + atributos.item(i).getNodeValue());
            }

            getElementos(nodo);
            System.out.println("\n");

            // 10
            nodo = nodo.getNextSibling().getNextSibling();
            getElementos(nodo);
            System.out.println("\n");

            // 11
            nodo = nodo.getPreviousSibling();
            getElementos(nodo);
            System.out.println("\n");

            // 12
            mostrarDatos(documento, nodo);

        } catch (ParserConfigurationException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void mostrarDatos(Document documento, Node nodo) {
        System.out.println("Nombre del nodo: " + nodo.getNodeName());
        System.out.println("Valor del nodo: " + nodo.getNodeValue());

        if (nodo.getFirstChild() != null)
            mostrarDatos(documento, nodo.getFirstChild());
        else if (nodo.getNextSibling() != null)
            mostrarDatos(documento, nodo.getNextSibling());
    }

    private static void getElementos(Node nodo) {
        NodeList elementos = nodo.getChildNodes();

        for (int i = 0; i < elementos.getLength(); i++) {
            System.out.println("<" + elementos.item(i).getNodeName() + ">: " + elementos.item(i).getTextContent());
        }
    }

}
