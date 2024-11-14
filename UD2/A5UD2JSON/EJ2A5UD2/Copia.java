package UD2.A5UD2JSON.EJ2A5UD2;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

@JacksonXmlRootElement(localName = "copia")
public class Copia {
    private int numero;
    private String estado;

    public Copia() {
    }

    public Copia(int numero, String estado) {
        this.numero = numero;
        this.estado = estado;
    }

    @JacksonXmlProperty(localName = "numero", isAttribute = true)
    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    @JacksonXmlProperty(localName = "estado", isAttribute = true)
    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}
