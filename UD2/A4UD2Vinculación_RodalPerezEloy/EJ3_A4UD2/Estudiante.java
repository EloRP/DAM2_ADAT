package UD2.A4UD2Vinculación_RodalPerezEloy.EJ3_A4UD2;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "Estudiante")
@XmlType(propOrder = { "universidad", "carrera" })
public class Estudiante extends Personas {
    String universidad;
    String carrera;

    public Estudiante() {
    }

    public Estudiante(String nombre, int edad, Contacto contacto, String universidad, String carrera) {
        super(nombre, edad, contacto);
        this.universidad = universidad;
        this.carrera = carrera;
    }

    @XmlElement(name = "Universidad")
    public String getUniversidad() {
        return universidad;
    }

    public void setUniversidad(String universidad) {
        this.universidad = universidad;
    }

    @XmlElement(name = "Carrera")
    public String getCarrera() {
        return carrera;
    }

    public void setCarrera(String carrera) {
        this.carrera = carrera;
    }

}
