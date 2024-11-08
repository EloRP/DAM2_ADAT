package UD2.A4UD2Vinculación_RodalPerezEloy.EJ1_A4UD2;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

public class EJ1_A4UD2 {
    public static void main(String[] args) throws JAXBException, IOException {
        ArrayList<Empleado> empleados = new ArrayList<>();
        empleados.add(new Empleado("53864409L", "Eloy", 29));
        empleados.add(new Empleado("4835894N", "Juan", 19));
        Empresa empresa = new Empresa("492956N", "Empresa S.A", empleados);

        JAXBContext contexto = null;
        Marshaller m = null;

        try {
            contexto = JAXBContext.newInstance(Empresa.class);
            m = contexto.createMarshaller();
            m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        } catch (JAXBException ex) {
            ex.printStackTrace();
        }
        Writer w = new FileWriter("UD2\\A4UD2Vinculación_RodalPerezEloy\\Empresa.xml");
        m.marshal(empresa, w);

        for (Empleado i : empresa.getEmpleados())
            System.out.println(i.getNombre() + "," + i.getDni() + "," + i.getEdad());
    }
}
