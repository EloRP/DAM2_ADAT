package UD2.A4UD2Vinculación_RodalPerezEloy.EJ4_A4UD2;

import java.io.Serializable;
import java.util.ArrayList;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "CadenaHotelera")
public class CadenaHotelera implements Serializable {
    static final long serialVersionUID = 42L;
    ArrayList<Hotel> hoteles;
    String nombre;
    String cif;

    public CadenaHotelera() {
    }

    public CadenaHotelera(ArrayList<Hotel> hoteles, String nombre, String cif) {
        this.hoteles = hoteles;
        this.nombre = nombre;
        this.cif = cif;
    }

    @XmlAttribute(name = "nombre")
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @XmlAttribute(name = "CIF")
    public String getCif() {
        return cif;
    }

    public void setCif(String cif) {
        this.cif = cif;
    }

    @XmlElement(name = "Hotel")
    public ArrayList<Hotel> getHoteles() {
        return hoteles;
    }

    public void setHoteles(ArrayList<Hotel> hoteles) {
        this.hoteles = hoteles;
    }

    

}
