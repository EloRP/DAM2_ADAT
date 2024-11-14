package UD2.A4UD2REPASOVinculación_RodalPerezEloy.EJ1_A4UD2;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;

@XmlRootElement(name = "Pesca")
public class Pesca {
    private ArrayList<Especie> especies;
    private ArrayList<Xornada> xornadas;

    @XmlElementWrapper(name = "Especies")
    @XmlElement(name = "Especie")
    public ArrayList<Especie> getEspecies() {
        return especies;
    }

    public void setEspecies(ArrayList<Especie> especies) {
        this.especies = especies;
    }

    @XmlElement(name = "Xornada")
    public ArrayList<Xornada> getXornadas() {
        return xornadas;
    }

    public void setXornadas(ArrayList<Xornada> xornadas) {
        this.xornadas = xornadas;
    }
}

