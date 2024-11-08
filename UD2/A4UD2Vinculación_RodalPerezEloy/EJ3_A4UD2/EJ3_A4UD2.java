package UD2.A4UD2Vinculación_RodalPerezEloy.EJ3_A4UD2;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

public class EJ3_A4UD2 {
    public static void main(String[] args) {
        ArrayList<Personas> listaPersonas = new ArrayList<>();

        Direccion direccion = new Direccion("República Argentina", 2, "Marín", "Pontevedra");
        ArrayList<String> telefonos = new ArrayList<>();
        telefonos.add("958345285");
        telefonos.add("978325215");
        telefonos.add("923344295");

        Contacto contacto = new Contacto(direccion, telefonos);
        Estudiante estudiante = new Estudiante("Pepe", 20, contacto, "Universidad Complutense de Madrid",
                "Ingeniería Forestal");
        Trabajador trabajador1 = new Trabajador("Armando Barreda", 45, contacto, "IES Chan do Monte", "Profesor", 4000);
        Trabajador trabajador2 = new Trabajador("José José", 30, contacto, "Empresa S.A", "Oficinista", 2000);

        listaPersonas.add(estudiante);
        listaPersonas.add(trabajador1);
        listaPersonas.add(trabajador2);

        ListaPersonas listaPersonasWrapper = new ListaPersonas(listaPersonas);

        JAXBContext contexto = null;
        Marshaller m = null;

        try {
            contexto = JAXBContext.newInstance(ListaPersonas.class);
            m = contexto.createMarshaller();
            m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            Writer w = new FileWriter("UD2\\A4UD2Vinculación_RodalPerezEloy\\EJ3_A4UD2\\Personas.xml");
            m.marshal(listaPersonasWrapper, w);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }
}
