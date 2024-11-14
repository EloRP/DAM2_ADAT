package UD2.A5UD2JSON.EJ2A5UD2;

import java.util.List;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

@JacksonXmlRootElement(localName = "libro")
public class Libro {

    private String ID;
    private String isbn;
    private String titulo;
    private int numero_de_paginas;
    private List<String> autores;
    private String fechaEdicion;
    private String editorial;
    private double precio;
    private List<Copia> copias;

    public Libro() {
    }

    public Libro(String ID, String isbn, String titulo, int numero_de_paginas, List<String> autores,
            String fechaEdicion, String editorial, double precio, List<Copia> copias) {
        this.ID = ID;
        this.isbn = isbn;
        this.titulo = titulo;
        this.numero_de_paginas = numero_de_paginas;
        this.autores = autores;
        this.fechaEdicion = fechaEdicion;
        this.editorial = editorial;
        this.precio = precio;
        this.copias = copias;
    }

    @JacksonXmlProperty(localName = "ID", isAttribute = true)
    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    @JacksonXmlProperty(localName = "isbn", isAttribute = true)
    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    @JacksonXmlProperty(localName = "titulo", isAttribute = true)
    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    @JacksonXmlProperty(localName = "numero_de_paginas", isAttribute = true)
    public int getNumero_de_paginas() {
        return numero_de_paginas;
    }

    public void setNumero_de_paginas(int numero_de_paginas) {
        this.numero_de_paginas = numero_de_paginas;
    }

    @JacksonXmlProperty(localName = "autores")
    public List<String> getAutores() {
        return autores;
    }

    public void setAutores(List<String> autores) {
        this.autores = autores;
    }

    @JacksonXmlProperty(localName = "fechaEdicion")
    public String getFechaEdicion() {
        return fechaEdicion;
    }

    public void setFechaEdicion(String fechaEdicion) {
        this.fechaEdicion = fechaEdicion;
    }

    @JacksonXmlProperty(localName = "editorial")
    public String getEditorial() {
        return editorial;
    }

    public void setEditorial(String editorial) {
        this.editorial = editorial;
    }

    @JacksonXmlProperty(localName = "precio")
    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    @JacksonXmlProperty(localName = "copias")
    public List<Copia> getCopias() {
        return copias;
    }

    public void setCopias(List<Copia> copias) {
        this.copias = copias;
    }
}
