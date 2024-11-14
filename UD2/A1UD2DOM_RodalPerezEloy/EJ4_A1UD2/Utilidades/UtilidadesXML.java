package UD2.A1UD2.EJ4_A1UD2.Utilidades;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

import UD2.A1UD2.EJ4_A1UD2.Hotel;

public class UtilidadesXML {

    public static Document crearDocumentoConXSD(String ruta) {
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        File ficheroRuta = new File(ruta);

        if (!ficheroRuta.exists()) {
            System.out.println("Se va a crear el fichero XML.");
            try {
                ficheroRuta.createNewFile();
            } catch (IOException e) {
                System.out.println("Error al crear el ficheor XML.");
            }
        }

        dbf.setValidating(true);
        dbf.setNamespaceAware(true);
        dbf.setAttribute("http://java.sun.com/xml/jaxp/properties/schemaLanguage",
                "http://www.w3.org/2001/XMLSchema");
        dbf.setIgnoringElementContentWhitespace(true);
        dbf.setIgnoringComments(true);

        DocumentBuilder db = null;
        try {
            db = dbf.newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            System.out.println("Error al crear el documento xml.");
        }
        Document documento = null;

        try {
            documento = db.parse(new File(ruta));
        } catch (IOException e) {
            System.out.println("Error al crear el fichero de salida. La ruta existe?. ");
        } catch (SAXException e) {
            System.out.println(
                    "Error al crear el fichero de salida. Existe el XSD? Cumple el XSD? (si está vacío dará error)");
        }
        return documento;
    }

    public static int getUltimoID(Document documentoXML) {
        int id = 0;
        if (!(documentoXML == null)) {
            Element raiz = documentoXML.getDocumentElement();
            Element ultimoElemento = (Element) raiz.getLastChild();
            id = Integer.parseInt(ultimoElemento.getAttribute("id"));
        }
        return id;
    }

    public static Document crearDocumentoConDTD(String ruta) {
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        File ficheroRuta = new File(ruta);

        if (!ficheroRuta.exists()) {
            System.out.println("Se va a crear el fichero XML.");
            try {
                ficheroRuta.createNewFile();
            } catch (IOException e) {
                System.out.println("Error al crear el fichero XML.");
            }
        }

        dbf.setValidating(true);
        dbf.setNamespaceAware(true);
        dbf.setIgnoringElementContentWhitespace(true);

        DocumentBuilder db = null;
        try {
            db = dbf.newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            System.out.println("Error al crear el documento xml.");
        }
        Document documento = null;

        try {
            documento = db.parse(new File(ruta));
        } catch (IOException e) {
            System.out.println("Error al crear el fichero de salida. La ruta existe?. ");
        } catch (SAXException e) {
            System.out.println(
                    "Error al crear el fichero de salida. Existe el DTD? Cumple el DTD? (si está vacío dará error)");
        }
        return documento;
    }

    public static void grabarEnDOM(Document documento, String ruta) throws IOException, TransformerException {
        TransformerFactory transFact;
        try {
            transFact = TransformerFactory.newInstance();
            // añadimos sangrado
            transFact.setAttribute("indent-number", 3);
            Transformer trans;
            try {
                trans = transFact.newTransformer();
                trans.setOutputProperty(OutputKeys.INDENT, "yes");
                trans.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "no");
                DOMSource domSource = new DOMSource(documento);
                // creamos fichero para escribir en modo texto
                FileWriter sw = new FileWriter(ruta);
                // escribimos todo el arbol en el fichero
                StreamResult sr = new StreamResult(sw);
                // También podemos especificar el destino en la consola
                StreamResult consola = new StreamResult(System.out);
                trans.transform(domSource, sr);
                trans.transform(domSource, consola);
            } catch (TransformerConfigurationException ex) {
                System.out.println("ERROR al construir el motor de transformación ");
            }
        } catch (TransformerFactoryConfigurationError e) {
            System.out.println("ERROR a la hora de implementar la transformación");
        }
    }

    public static Document crearDocumentoXML(ArrayList<Hotel> listaHoteles) throws ParserConfigurationException {
        DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

        Document doc = docBuilder.newDocument();

        Element rootElement = doc.createElement("Hoteles");
        doc.appendChild(rootElement);

        for (Hotel hotel : listaHoteles) {
            Element hotelElement = doc.createElement("Hotel");
            hotelElement.setAttribute("id", String.valueOf(hotel.getCodigoHotel()));
            rootElement.appendChild(hotelElement);

            Element nombreElement = doc.createElement("Nombre");
            nombreElement.setTextContent(hotel.getNombre());
            hotelElement.appendChild(nombreElement);

            Element telefonoElement = doc.createElement("Telefono");
            telefonoElement.setTextContent(String.valueOf(hotel.getTelefono()));
            hotelElement.appendChild(telefonoElement);

            Element direccionElement = doc.createElement("Direccion");
            hotelElement.appendChild(direccionElement);

            Element calleElement = doc.createElement("Calle");
            calleElement.setTextContent(hotel.getDireccion().getCalle());
            direccionElement.appendChild(calleElement);

            Element numeroElement = doc.createElement("Numero");
            numeroElement.setTextContent(String.valueOf(hotel.getDireccion().getNumero()));
            direccionElement.appendChild(numeroElement);

            Element cpElement = doc.createElement("CodigoPostal");
            cpElement.setTextContent(hotel.getDireccion().getCodPostal());
            direccionElement.appendChild(cpElement);
        }
        return doc;
    }

    public static void escribirXML(String rutaXML, Document doc) throws TransformerException {
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();

        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        transformer.setOutputProperty(OutputKeys.ENCODING, "ISO-8859-1");

        transformer.setOutputProperty(OutputKeys.DOCTYPE_SYSTEM, "Hoteles.dtd");

        DOMSource source = new DOMSource(doc);
        StreamResult result = new StreamResult(rutaXML);

        transformer.transform(source, result);

        System.out.println("Fichero XML creado correctamente en: " + rutaXML);
    }
}
