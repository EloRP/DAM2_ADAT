package UD2.A4UD2Vinculación_RodalPerezEloy.EJ3_A4UD2;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "Trabajador")
@XmlType(propOrder = { "empresa", "puesto", "salario" })
public class Trabajador extends Personas {
    String empresa;
    String puesto;
    int salario;

    public Trabajador() {
    }

    public Trabajador(String nombre, int edad, Contacto contacto, String empresa, String puesto, int salario) {
        super(nombre, edad, contacto);
        this.empresa = empresa;
        this.puesto = puesto;
        this.salario = salario;
    }

    @XmlElement(name = "Empresa")
    public String getEmpresa() {
        return empresa;
    }

    public void setEmpresa(String empresa) {
        this.empresa = empresa;
    }

    @XmlElement(name = "Puesto")
    public String getPuesto() {
        return puesto;
    }

    public void setPuesto(String puesto) {
        this.puesto = puesto;
    }

    @XmlElement(name = "Salario")
    public int getSalario() {
        return salario;
    }

    public void setSalario(int salario) {
        this.salario = salario;
    }

}
