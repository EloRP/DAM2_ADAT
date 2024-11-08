package UD2.A4UD2Vinculación_RodalPerezEloy.EJ3_A4UD2;

import java.util.ArrayList;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "personas")
public class ListaPersonas {
    private ArrayList<Personas> personas;

    public ListaPersonas() {
        this.personas = new ArrayList<>();
    }

    public ListaPersonas(ArrayList<Personas> personas) {
        this.personas = personas;
    }

    @XmlElement(name = "Persona")
    public ArrayList<Personas> getPersonas() {
        return personas;
    }

    public void setPersonas(ArrayList<Personas> personas) {
        this.personas = personas;
    }
}