package A34UD1;

import java.io.Serializable;

public class Corredor implements Serializable {
    static final long serialVersionUID = 42L;
    protected String nombre;
    protected int dorsal;
    protected double tiempo;
    protected boolean borrado;

    public Corredor(String nombre, int dorsal, double tiempo, boolean borrado) {
        this.nombre = nombre;
        this.dorsal = dorsal;
        this.tiempo = tiempo;
        this.borrado = borrado;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getDorsal() {
        return dorsal;
    }

    public void setDorsal(int dorsal) {
        this.dorsal = dorsal;
    }

    public double getTiempo() {
        return tiempo;
    }

    public boolean isBorrado() {
        return borrado;
    }

    public boolean getBorrado() {
        return borrado;
    }

    public void setBorrado(boolean borrado) {
        this.borrado = borrado;
    }

    public void setTiempo(double tiempo) {
        this.tiempo = tiempo;
    }

    @Override
    public String toString() {
        return "Nombre: " + nombre + " Dorsal: " + dorsal + " Tiempo: " + tiempo;
    }

    public static long getSerialversionuid() {
        return serialVersionUID;
    }

}
