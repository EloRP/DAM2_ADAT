package UD2.A1UD2.EJ4_A1UD2;

import java.io.Serializable;

public class Direccion implements Serializable{
    String calle;
    int numero;
    String codPostal;


    public String getCalle() {
        return calle;
    }
    public void setCalle(String calle) {
        this.calle = calle;
    }
    public int getNumero() {
        return numero;
    }
    public void setNumero(int numero) {
        this.numero = numero;
    }
    public String getCodPostal() {
        return codPostal;
    }
    public void setCodPostal(String codPostal) {
        this.codPostal = codPostal;
    }

    public Direccion(String calle, int numero, String codPostal) {
        this.calle = calle;
        this.numero = numero;
        this.codPostal = codPostal;
    }
    @Override
    public String toString() {
        return "Calle: " + calle + ", Numero: " + numero + ", codPostal: " + codPostal + "]";
    }

}
