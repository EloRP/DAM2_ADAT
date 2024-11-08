package UD2.A4UD2Vinculación_RodalPerezEloy.EJ2_A4UD2;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "Bloque")
@XmlType(propOrder = { "sesions", "num", "titulo", "descripcion" })
public class Bloque {
    int sesions;
    int num;
    String titulo;
    String descripcion;

    public Bloque() {
    }

    public Bloque(int sesions, int num, String titulo, String descripcion) {
        this.sesions = sesions;
        this.num = num;
        this.titulo = titulo;
        this.descripcion = descripcion;
    }

    @XmlAttribute(name = "sesions")
    public int getSesions() {
        return sesions;
    }

    public void setSesions(int sesions) {
        this.sesions = sesions;
    }

    @XmlAttribute(name = "num")
    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    @XmlElement(name = "Titulo")
    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    @XmlElement(name = "Descripcion")
    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @Override
    public String toString() {
        return "Bloque: " + sesions + " sesións, " + "Num" + num + ", Titulo: " + titulo + ", Descripcion: " + descripcion;
    }

}
