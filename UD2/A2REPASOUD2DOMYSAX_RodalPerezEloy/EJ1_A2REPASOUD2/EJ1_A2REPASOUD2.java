package UD2.A2REPASOUD2.EJ1_A2REPASOUD2;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Date;
import java.util.Scanner;
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
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class EJ1_A2REPASOUD2 {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();

        System.out.println("Valida? " + dbf.isValidating());
        System.out.println("\n");

        if (!dbf.isValidating()) {
            dbf.setValidating(true);
        }

        System.out.println("Soporta espacios de nombre?" + dbf.isNamespaceAware());
        System.out.println("\n");

        if (!dbf.isNamespaceAware()) {
            dbf.setNamespaceAware(true);
        }

        System.out.println("Ignora comentarios?" + dbf.isIgnoringComments());
        System.out.println("\n");

        if (!dbf.isIgnoringComments()) {
            dbf.setIgnoringComments(true);
        }

        dbf.setIgnoringElementContentWhitespace(true);

        Date fechaNacimientoActor = Date.valueOf("1933-03-14");

        Actor actor = new Actor("A32", "Michael Caine", "home", fechaNacimientoActor);

        try {
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document documento = (Document) db.parse(new File("UD2\\A3UD2\\EJ1_A3UD2\\Actores.xml"));
            BufferedReader ficheroModificarFecha = abrirFicheroModificarFecha();
            String linea = "";
            linea = ficheroModificarFecha.readLine();

            while (linea != null) {
                actualizaFecha(linea, documento);
                linea = ficheroModificarFecha.readLine();
            }
            Element actorElem = anhadirElementoActor(actor, documento);
            guardarActor(actorElem, documento);
            grabarArbolModificado(documento);

        } catch (TransformerException e) {
            System.out.println(e.getMessage());
        } catch (ParserConfigurationException e) {
            System.out.println(e.getMessage());
        } catch (SAXException e) {
            System.out.println(e.getMessage());
        }

        System.out.println("Introduce la ID del actor del que quieras conocer la información: ");
        String idActor = sc.nextLine();
        mostrarInformacionActor(idActor);

        sc.close();
    }

    // a)
    private static void actualizaFecha(String cadena, Document doc) {
        String[] idFecha = cadena.split(",");
        Element actor = doc.getElementById(idFecha[0]);
        if (actor != null) {
            Element dataNacemento = (Element) actor.getElementsByTagName("DataNacemento").item(0);
            System.out.println("Fecha antigua: " + dataNacemento.getTextContent());
            dataNacemento.setTextContent(idFecha[1]);
            System.out.println("Fecha nueva: " + dataNacemento.getTextContent() + "\n");
        } else {
            System.out.println("No existe el actor " + idFecha[0] + "\n");
        }
    }

    private static BufferedReader abrirFicheroModificarFecha() {
        BufferedReader fichero = null;
        try {
            fichero = new BufferedReader(new FileReader("UD2\\A3UD2\\EJ1_A3UD2\\ModificarFecha.txt"));
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
        return fichero;
    }

    // b)
    private static boolean guardarActor(Element actorElem, Document doc) {
        boolean existe = false;
        Element elementoRaiz = doc.getDocumentElement();
        NodeList actores = elementoRaiz.getChildNodes();
        for (int i = 0; i < actores.getLength(); i++) {
            if (((Element) actores.item(i)).getAttribute("id").equals(actorElem.getAttribute("id"))) {
                existe = true;
                break;
            }
        }
        String nombreActor = actorElem.getElementsByTagName("Nome").item(0).getTextContent();

        if (!existe) {
            elementoRaiz.appendChild(actorElem);
            System.out.println("Actor" + nombreActor + " agregado.");
        } else {
            System.out.println("Ya existe otro actor con ese ID. No se agregó el actor " + nombreActor);
        }
        return existe;
    }

    private static Element anhadirElementoActor(Actor actor, Document doc) {
        Element actorElement = doc.createElement("Actor");
        actorElement.setAttribute("id", String.valueOf(actor.getId()));

        Element nombreElement = doc.createElement("Nome");
        nombreElement.setTextContent(actor.getNome());
        actorElement.appendChild(nombreElement);

        Element sexoElement = doc.createElement("Sexo");
        sexoElement.setTextContent(String.valueOf(actor.getSexo()));
        actorElement.appendChild(sexoElement);

        Element direccionElement = doc.createElement("DataNacemento");
        direccionElement.setAttribute("formato", "dd/mm/aaaa");
        direccionElement.setTextContent(actor.getDataNacemento());
        actorElement.appendChild(direccionElement);

        return actorElement;
    }

    private static void grabarArbolModificado(Document documento) throws IOException, TransformerException {
        TransformerFactory transFact;
        try {
            transFact = TransformerFactory.newInstance();
            transFact.setAttribute("indent-number", 3);
            Transformer trans;
            try {
                trans = transFact.newTransformer();
                trans.setOutputProperty(OutputKeys.INDENT, "yes");
                trans.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "no");
                trans.setOutputProperty(OutputKeys.DOCTYPE_SYSTEM, "Actores.dtd");

                DOMSource domSource = new DOMSource(documento);
                FileWriter sw = new FileWriter("UD2\\A3UD2\\EJ1_A3UD2\\Actores.xml");
                StreamResult sr = new StreamResult(sw);
                StreamResult consola = new StreamResult(System.out);
                trans.transform(domSource, sr);
                trans.transform(domSource, consola);
            } catch (TransformerConfigurationException ex) {
                System.out.println("Error al construir el motor de transformación ");
            }
        } catch (TransformerFactoryConfigurationError e) {
            System.out.println("Error a la hora de implementar la transformación");
        }
    }

    // c)
    private static void mostrarInformacionActor(String idActor) {
        try {
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document doc = (Document) db.parse(new File("UD2\\A3UD2\\EJ1_A3UD2\\Actores.xml"));
            NodeList listaActores = doc.getElementsByTagName("Actor");

            for (int i = 0; i < listaActores.getLength(); i++) {
                Node nodo = listaActores.item(i);

                if (nodo.getNodeType() == Node.ELEMENT_NODE) {
                    Element actor = (Element) nodo;

                    if (actor.getAttribute("id").equals(idActor)) {
                        String nombre = actor.getElementsByTagName("Nome").item(0).getTextContent();
                        String sexo = actor.getElementsByTagName("Sexo").item(0).getTextContent();
                        String dataNacemento = actor.getElementsByTagName("DataNacemento").item(0).getTextContent();
                        System.out.println("Nombre: " + nombre);
                        System.out.println("Sexo: " + sexo);
                        System.out.println("Data de nacemento: " + dataNacemento);
                        return;
                    }
                }
            }
            System.out.println("Actor con el ID " + idActor + " no encontrado.");
        } catch (ParserConfigurationException e) {
            System.out.println("Error al parsear.");
        } catch (SAXException e) {
            System.out.println("Error de SAX.");
        } catch (IOException e) {
            System.out.println("Error de entrada/salida.");
        }
    }
}