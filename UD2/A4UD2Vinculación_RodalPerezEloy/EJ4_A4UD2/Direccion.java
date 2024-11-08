package UD2.A4UD2Vinculación_RodalPerezEloy.EJ4_A4UD2;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "Direccion")
@XmlType(propOrder = { "calle", "numero", "codPostal" })
public class Direccion implements Serializable {
    String calle;
    int numero;
    int codPostal;

    public Direccion() {
    }

    public Direccion(String calle, int numero, int codPostal) {
        this.calle = calle;
        this.numero = numero;
        this.codPostal = codPostal;
    }

    @XmlElement(name = "Calle")
    public String getCalle() {
        return calle;
    }

    public void setCalle(String calle) {
        this.calle = calle;
    }

    @XmlElement(name = "Numero")
    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    @XmlElement(name = "CodigoPostal")
    public int getCodPostal() {
        return codPostal;
    }

    public void setCodPostal(int codPostal) {
        this.codPostal = codPostal;
    }

}
