package UD2.A4UD2Vinculación_RodalPerezEloy.EJ2_A4UD2;

import java.io.File;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

public class EJ2_A4UD2 {
    public static void main(String[] args) throws JAXBException {
        Modulo modulo;
        JAXBContext contexto = null;
        Unmarshaller m = null;

        try {
            contexto = JAXBContext.newInstance(Modulo.class);
            m = contexto.createUnmarshaller();
        } catch (JAXBException ex) {
            ex.printStackTrace();
        }
        File f = new File("UD2\\A4UD2Vinculación_RodalPerezEloy\\EJ2_A4UD2\\Modulo.xml");
        modulo = (Modulo) m.unmarshal(f);
        System.out.println(modulo);
    }
}
