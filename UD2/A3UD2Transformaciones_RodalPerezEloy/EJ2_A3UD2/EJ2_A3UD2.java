package UD2.A3UD2Transformaciones_RodalPerezEloy.EJ2_A3UD2;

import java.io.FileWriter;
import java.io.IOException;

import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

public class EJ2_A3UD2 {
    public static void main(String[] args) throws IOException {
        String hojaEstilos = "UD2\\A3UD2Transformaciones\\EJ2_A3UD2\\Hardware.xsl";
        String datosActores = "UD2\\A3UD2Transformaciones\\EJ2_A3UD2\\Hardware.xml";
        String xmlTransformado = "UD2\\A3UD2Transformaciones\\EJ2_A3UD2\\Hardware_trans.xml";
        TransformerFactory transFact;
        try {
            transFact = TransformerFactory.newInstance();
            Transformer trans;
            try {
                StreamSource estilos = new StreamSource(hojaEstilos);
                StreamSource datos = new StreamSource(datosActores);
                trans = transFact.newTransformer(estilos);
                FileWriter sw = new FileWriter(xmlTransformado);
                StreamResult sr = new StreamResult(sw);
                try {
                    trans.transform(datos, sr);
                } catch (TransformerException ex) {
                    System.out.println("ERROR al transformar el archivo.");
                };
            } catch (TransformerConfigurationException ex) {
                System.out.println("ERROR al construir el motor de transformación ");
            }
        } catch (TransformerFactoryConfigurationError e) {
            System.out.println("ERROR a la hora de implementar la transformación");
        }
    }
}
