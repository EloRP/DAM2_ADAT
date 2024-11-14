package UD2.A5UD2JSON.EJ2A5UD2;

import java.util.ArrayList;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

@JacksonXmlRootElement(localName = "biblioteca")
public class Biblioteca {
    String facultad;
    String campus;
    ArrayList<Seccion> secciones;

    @JacksonXmlProperty(localName = "facultad")
    public String getFacultad() {
        return facultad;
    }

    public void setFacultad(String facultad) {
        this.facultad = facultad;
    }

    @JacksonXmlProperty(localName = "campus")
    public String getCampus() {
        return campus;
    }

    public void setCampus(String campus) {
        this.campus = campus;
    }

    @JacksonXmlProperty(localName = "secciones")
    public ArrayList<Seccion> getSecciones() {
        return secciones;
    }

    public void setSecciones(ArrayList<Seccion> secciones) {
        this.secciones = secciones;
    }

    public Biblioteca(String facultad, String campus, ArrayList<Seccion> secciones) {
        this.facultad = facultad;
        this.campus = campus;
        this.secciones = secciones;
    }

    public Biblioteca() {
    }

}
