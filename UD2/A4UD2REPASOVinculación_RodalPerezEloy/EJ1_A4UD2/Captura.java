package UD2.A4UD2REPASOVinculación_RodalPerezEloy.EJ1_A4UD2;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlValue;

public class Captura {
    private int numUnidades;
    private double peso;
    private String especie;

    public Captura() {
    }

    @XmlAttribute(name = "numUnidades")
    public int getNumUnidades() {
        return numUnidades;
    }

    public void setNumUnidades(int numUnidades) {
        this.numUnidades = numUnidades;
    }

    @XmlAttribute(name = "peso")
    public double getPeso() {
        return peso;
    }

    public void setPeso(double peso) {
        this.peso = peso;
    }

    @XmlValue
    public String getEspecie() {
        return especie;
    }

    public void setEspecie(String especie) {
        this.especie = especie;
    }

    @Override
    public String toString() {
        return("Captura de: " + especie + ", Unidades: " + numUnidades + ", Peso: " + peso);
    }


}
