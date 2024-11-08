package UD2.A4UD2Vinculación_RodalPerezEloy.EJ4_A4UD2;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "CadenaHotelera")
public class CadenaHotelera implements Serializable {
    String nombre;
    String cif;

    public CadenaHotelera() {
    }

    public CadenaHotelera(String nombre, String cif) {
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

}
