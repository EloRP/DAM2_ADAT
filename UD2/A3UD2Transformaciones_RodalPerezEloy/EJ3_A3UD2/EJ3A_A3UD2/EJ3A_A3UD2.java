package UD2.A3UD2Transformaciones_RodalPerezEloy.EJ3_A3UD2.EJ3A_A3UD2;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.sax.SAXSource;
import javax.xml.transform.stream.StreamSource;
import javax.xml.transform.sax.SAXResult;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import java.io.File;

public class EJ3A_A3UD2 {
    public static void main(String[] args) {
        String rutaXML = "UD2\\A3UD2Transformaciones\\EJ3_A3UD2\\Alumnos.xml";
        String rutaXSL = "UD2\\A3UD2Transformaciones\\EJ3_A3UD2\\Alumnos.xsl";

        try {
            SAXParserFactory spf = SAXParserFactory.newInstance();
            SAXParser saxParser = spf.newSAXParser();
            XMLReader procesadorXML = saxParser.getXMLReader();
            Manejador manejador = new Manejador();

            File archivoXML = new File(rutaXML);
            InputSource inputSource = new InputSource(archivoXML.toURI().toString());
            SAXSource origen = new SAXSource(procesadorXML, inputSource);

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
        }
    }
}