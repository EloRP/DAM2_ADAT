package UD2.A5UD2JSON.EJ2A5UD2;

import java.util.List;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

@JacksonXmlRootElement(localName = "seccion")
public class Seccion {
    String nombre;
    List<Libro> libros;

    @JacksonXmlProperty(localName = "nombre", isAttribute = true)
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @JacksonXmlProperty(localName = "libros")
    public List<Libro> getLibros() {
        return libros;
    }

    public void setLibros(List<Libro> libros) {
        this.libros = libros;
    }

    public Seccion() {
    }

    public Seccion(String nombre, List<Libro> libros) {
        this.nombre = nombre;
        this.libros = libros;
    }

}
