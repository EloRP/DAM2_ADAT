package UD2.A3UD2Transformaciones_RodalPerezEloy.EJ3_A3UD2.EJ3B_A3UD2;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamSource;
import javax.xml.transform.sax.SAXResult;

import org.w3c.dom.Document;
import org.w3c.dom.Text;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;
import java.io.File;
import java.io.IOException;

public class EJ3B_A3UD2 {
    public static void main(String[] args) {
        String rutaXSL = "UD2\\A3UD2Transformaciones\\EJ3_A3UD2\\EJ3B_A3UD2\\Alumnos.xsl";
        try {
            Document dom = abrirDocumento();
            Element alumno = crearAlumno(dom, "Juan", 7);
            anhadirAlumno(dom, alumno);
            Manejador manejador = new Manejador();
            DOMSource origen = new DOMSource(dom);
            TransformerFactory tf = TransformerFactory.newInstance();
            Transformer transformer = tf.newTransformer(new StreamSource(new File(rutaXSL)));
            SAXResult destino = new SAXResult(manejador);
            transformer.transform(origen, destino);

        } catch (SAXException e) {
            e.printStackTrace();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (TransformerConfigurationException e) {
            e.printStackTrace();
        } catch (TransformerException e) {
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void anhadirAlumno(Document dom, Element alumno) {
        Element raiz = dom.getDocumentElement();
        raiz.appendChild(alumno);
    }

    private static Document abrirDocumento() throws SAXException, IOException, ParserConfigurationException {
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        dbf.setNamespaceAware(true);
        dbf.setIgnoringElementContentWhitespace(true);
        dbf.setIgnoringComments(true);
        DocumentBuilder db = dbf.newDocumentBuilder();

        Document documento = db.parse("UD2\\A3UD2Transformaciones\\EJ3_A3UD2\\EJ3B_A3UD2\\Alumnos.xml");
        return documento;
    }

    private static Element crearAlumno(Document dom, String nombre, int nota) {
        Element alumno = dom.createElement("alumno");

        Element nombreAlumno = dom.createElement("nome");
        Text textoNombre = dom.createTextNode(nombre);
        nombreAlumno.appendChild(textoNombre);
        alumno.appendChild(nombreAlumno);

        Element notaAlumno = dom.createElement("nota");
        Text textoNota = dom.createTextNode(String.valueOf(nota));
        notaAlumno.appendChild(textoNota);
        alumno.appendChild(notaAlumno);

        return alumno;
    }
}