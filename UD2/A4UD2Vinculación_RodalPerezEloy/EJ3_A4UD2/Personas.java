package UD2.A4UD2Vinculación_RodalPerezEloy.EJ3_A4UD2;


import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;

@XmlSeeAlso({ Trabajador.class, Estudiante.class })
@XmlRootElement(name = "Personas")
@XmlType(propOrder = { "nombre", "edad", "contacto" })
public class Personas {
    String nombre;
    int edad;
    Contacto contacto;

    public Personas() {
    }

    public Personas(String nombre, int edad, Contacto contacto) {
        this.nombre = nombre;
        this.edad = edad;
        this.contacto = contacto;
    }

    @XmlElement(name = "")
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @XmlElement(name = "Edad")
    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    @XmlElement(name = "Informacion_Contacto")
    public Contacto getContacto() {
        return contacto;
    }

    public void setContacto(Contacto contacto) {
        this.contacto = contacto;
    }

}
