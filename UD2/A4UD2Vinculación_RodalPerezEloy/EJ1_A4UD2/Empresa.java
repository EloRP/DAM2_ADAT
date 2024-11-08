package UD2.A4UD2Vinculación_RodalPerezEloy.EJ1_A4UD2;

import java.util.ArrayList;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "empresa")
@XmlType(propOrder = { "cif", "nombre", "empleados" })
public class Empresa {

    String cif;
    String nombre;
    ArrayList<Empleado> empleados;

    public Empresa() {
    }

    public Empresa(String cif, String nombre, ArrayList<Empleado> empleados) {
        this.cif = cif;
        this.nombre = nombre;
        this.empleados = empleados;
    }

    @XmlAttribute
    public String getCif() {
        return cif;
    }

    @XmlElement
    public String getNombre() {
        return nombre;
    }

    public void setCif(String cif) {
        this.cif = cif;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @XmlElementWrapper(name = "empleados")
    @XmlElement(name = "empleado")
    public ArrayList<Empleado> getEmpleados() {
        return empleados;
    }

    public void setEmpleados(ArrayList<Empleado> empleados) {
        this.empleados = empleados;
    }

    public void addEmpleado(Empleado empleado) {
        this.empleados.add(empleado);
    }
}
