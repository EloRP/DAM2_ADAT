package UD2.A4UD2Vinculación_RodalPerezEloy.EJ3_A4UD2;

import java.util.ArrayList;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlList;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "Informacion Contacto")
@XmlType(propOrder = { "direccion", "telefonos" })
public class Contacto {
    Direccion direccion;
    ArrayList<String> telefonos;

    public Contacto() {
    }

    public Contacto(Direccion direccion, ArrayList<String> telefonos) {
        this.direccion = direccion;
        this.telefonos = telefonos;
    }

    @XmlElement(name = "Direccion")
    public Direccion getDireccion() {
        return direccion;
    }

    public void setDireccion(Direccion direccion) {
        this.direccion = direccion;
    }

    @XmlElement(name = "Telefonos")
    @XmlList
    public ArrayList<String> getTelefonos() {
        return telefonos;
    }

    public void setTelefonos(ArrayList<String> telefonos) {
        this.telefonos = telefonos;
    }

}
