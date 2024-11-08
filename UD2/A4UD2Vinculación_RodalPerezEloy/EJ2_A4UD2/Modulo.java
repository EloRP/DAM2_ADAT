package UD2.A4UD2Vinculación_RodalPerezEloy.EJ2_A4UD2;

import java.util.ArrayList;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "Modulo")
@XmlType(propOrder = {"nome", "contidos"})
public class Modulo {
    String nome;
    ArrayList<Bloque> contidos;

    public Modulo() {
    }

    public Modulo(String nome, ArrayList<Bloque> contidos) {
        this.nome = nome;
        this.contidos = contidos;
    }

    @XmlElement(name= "Nome")
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @XmlElementWrapper(name = "Contidos")
    @XmlElement(name = "Bloque")
    public ArrayList<Bloque> getContidos() {
        return contidos;
    }

    public void setContidos(ArrayList<Bloque> contidos) {
        this.contidos = contidos;
    }

    @Override
    public String toString() {
        return nome + ", Contidos: " + contidos;
    }

}
