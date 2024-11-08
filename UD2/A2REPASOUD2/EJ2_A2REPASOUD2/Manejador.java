package UD2.A2REPASOUD2.EJ2_A2REPASOUD2;

import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class Manejador extends DefaultHandler {
    SimpleDateFormat inputFormat = new SimpleDateFormat("dd/MM/yyyy");
    SimpleDateFormat outputFormat = new SimpleDateFormat("dd-MMM-yy");
    static Document dom;
    private String nombreEtiqueta = "";
    private String data = "";
    private String hora = "";
    private String estado = "";

    @Override
    public void startDocument() throws SAXException {
        dom = crearDomVacio();
        crearRaiz(dom);
    }

    @Override
    public void endDocument() throws SAXException {
        grabarArbolModificado(dom);
    }

    private void crearRaiz(Document dom) {
        Element elemento = dom.createElement("Lunas");
        elemento.setAttribute("Descripcion", "Fases Lunares");
        elemento.setAttribute("Servicio", "MeteoGalicia");
        dom.appendChild(elemento);
    }

    private void crearYAgregarElemento(String nombreElemento, Element elementoPadre) {
        Element elemento = dom.createElement(nombreElemento);
        elementoPadre.appendChild(elemento);
    }

    private void crearYAgregarElemento(String nombreElemento, Element elementoPadre, String contenido) {
        Element elemento = dom.createElement(nombreElemento);
        elemento.setTextContent(contenido);
        elementoPadre.appendChild(elemento);
    }

    @Override
    public void startElement(String uri, String localName, String name, Attributes attributes) throws SAXException {
        if (localName.equals("item")) {
            crearYAgregarElemento("Luna", dom.getDocumentElement());
        } else if (localName.equals("estado")) {
            nombreEtiqueta = "estado";
        } else if (localName.equals("data")) {
            nombreEtiqueta = "data";
        }
    }

    @Override
    public void characters(char[] ch, int start, int length)
            throws SAXException {
        switch (nombreEtiqueta) {
            case "estado":
                estado = String.valueOf(ch, start, length);
                nombreEtiqueta = "";
                break;

            case "data":
                String[] dataHora = String.valueOf(ch, start, length).split(" ");
                data = dataHora[0];
                hora = dataHora[1];
                nombreEtiqueta = "";
                break;
            default:
                break;
        }
    }

    @Override
    public void endElement(String uri, String localName, String name)
            throws SAXException {
        if (localName.equals("item")) {
            Element ultimaLunaAgregada = (Element) dom.getDocumentElement().getLastChild();
            ultimaLunaAgregada.setAttribute("Estado", estado.toUpperCase());
            try {
                crearYAgregarElemento("Data", ultimaLunaAgregada, outputFormat.format(inputFormat.parse(data)));
            } catch (ParseException e) {
                System.out.println("Error al parsear.");
            }
            crearYAgregarElemento("Hora", ultimaLunaAgregada, hora);
        }
    }

    public static Document crearDomVacio() {
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();

        dbf.setNamespaceAware(true);
        dbf.setIgnoringElementContentWhitespace(true);
        dbf.setIgnoringComments(true);

        Document documento = null;
        try {
            DocumentBuilder db = dbf.newDocumentBuilder();
            documento = db.newDocument();
        } catch (ParserConfigurationException e) {
            System.out.println("Error al configurar.");
        }
        return documento;
    }

    private static void grabarArbolModificado(Document documento) {
        TransformerFactory transFact;
        try {
            transFact = TransformerFactory.newInstance();
            transFact.setAttribute("indent-number", 3);
            Transformer trans;
            try {
                trans = transFact.newTransformer();
                trans.setOutputProperty(OutputKeys.INDENT, "yes");
                trans.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "no");

                DOMSource domSource = new DOMSource(documento);
                FileWriter sw = new FileWriter("UD2\\A2REPASOUD2\\EJ2_A3UD2\\FasesLunas.xml");
                StreamResult sr = new StreamResult(sw);
                StreamResult consola = new StreamResult(System.out);
                trans.transform(domSource, sr);
                trans.transform(domSource, consola);
            } catch (TransformerConfigurationException ex) {
                System.out.println("Error al construir el motor de transformación ");
            } catch (IOException e) {
                System.out.println("Error de entrada/salida.");
            } catch (TransformerException e) {
                System.out.println("Error de transformación.");
            }
        } catch (TransformerFactoryConfigurationError e) {
            System.out.println("Error a la hora de implementar la transformación");
        }
    }
}