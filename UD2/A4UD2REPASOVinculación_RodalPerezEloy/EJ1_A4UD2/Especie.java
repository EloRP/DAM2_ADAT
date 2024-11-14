package UD2.A4UD2REPASOVinculación_RodalPerezEloy.EJ1_A4UD2;

import java.util.ArrayList;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "Especie")
@XmlType(propOrder = {"nome", "valor", "habitat", "nomeCientifico", "outrosNomes",
        "capturaMinima", "notas"})
public class Especie{
    private String nome;
    private String valor;
    private String habitat;
    private String nomeCientifico;
    private String outrosNomes;
    private CapturaMinima capturaMinima;
    private ArrayList<String> notas = new ArrayList<>();


    public Especie() {}


    @XmlAttribute(name = "nome")
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @XmlAttribute(name = "valor")
    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    @XmlElement(name = "Habitat")
    public String getHabitat() {
        return habitat;
    }

    public void setHabitat(String habitat) {
        this.habitat = habitat;
    }

    @XmlElement(name = "NomeCientifico")
    public String getNomeCientifico() {
        return nomeCientifico;
    }

    public void setNomeCientifico(String nomeCientifico) {
        this.nomeCientifico = nomeCientifico;
    }

    @XmlElement(name = "OutrosNomes")
    public String getOutrosNomes() {
        return (outrosNomes == null ? "Ninguno." : outrosNomes + ".");
    }

    public void setOutrosNomes(String outrosNomes) {
        this.outrosNomes = outrosNomes;
    }

    @XmlElement(name = "CapturaMinima")
    public CapturaMinima getCapturaMinima() {
        return capturaMinima;
    }

    public void setCapturaMinima(CapturaMinima capturaMinima) {
        this.capturaMinima = capturaMinima;
    }

    @XmlElement(name = "Nota")
    public ArrayList<String> getNotas() {
        return notas;
    }

    public void setNotas(ArrayList<String> notas) {
        this.notas = notas;
    }
}
