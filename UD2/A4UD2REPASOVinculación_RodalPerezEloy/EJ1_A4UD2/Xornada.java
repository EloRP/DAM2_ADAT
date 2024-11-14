package UD2.A4UD2REPASOVinculación_RodalPerezEloy.EJ1_A4UD2;

import javax.xml.bind.annotation.*;
import java.util.ArrayList;


@XmlRootElement(name = "Xornada")
@XmlType(propOrder = {"lugar", "data", "capturas", "descricion"})
public class Xornada {
    private String lugar;
    private String data;
    private ArrayList<Captura> capturas = new ArrayList<>();
    private String descricion;


    public Xornada() {}

    @XmlAttribute(name = "lugar")
    public String getLugar() {
        return lugar;
    }

    public void setLugar(String lugar) {
        this.lugar = lugar;
    }

    @XmlAttribute(name = "data")
    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    @XmlElement(name = "Captura")
    public ArrayList<Captura> getCapturas() {
        return capturas;
    }

    public void setCapturas(ArrayList<Captura> capturas) {
        this.capturas = capturas;
    }

    @XmlElement(name = "Descricion")
    public String getDescricion() {
        return (descricion == null ? "Ningunha." : descricion.strip());
    }

    public void setDescricion(String descricion) {
        this.descricion = descricion;
    }
}
