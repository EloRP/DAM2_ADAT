package EJ1_A4UD1;

import java.io.*;
import java.util.*;

public class Alumno implements Serializable {
    static final long serialVersionUID = 42L;
    private int numero;
    private Nombre nombre;
    Date fechaNac;
    ArrayList<String> telefono;
    boolean borrado;

    public Alumno(int numero, Nombre nombre, Date fechaNac, ArrayList<String> telefono, boolean borrado) {
        this.numero = numero;
        this.nombre = nombre;
        this.fechaNac = fechaNac;
        this.telefono = telefono;
        this.borrado = false;
    }

    public Alumno(Nombre nombre, Date fechaNac, ArrayList<String> telefono, boolean borrado) {
        this.nombre = nombre;
        this.fechaNac = fechaNac;
        this.telefono = telefono;
        this.borrado = borrado;
    }



    public static long getSerialversionuid() {
        return serialVersionUID;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public Nombre getNombre() {
        return nombre;
    }

    public void setNombre(Nombre nombre) {
        this.nombre = nombre;
    }

    public Date getFechaNac() {
        return fechaNac;
    }

    public void setFechaNac(Date fechaNac) {
        this.fechaNac = fechaNac;
    }

    public ArrayList<String> getTelefono() {
        return telefono;
    }

    public void setTelefono(ArrayList<String> telefono) {
        this.telefono = telefono;
    }

    public boolean isBorrado() {
        return borrado;
    }

    public void setBorrado(boolean borrado) {
        this.borrado = borrado;
    }

    @Override
    public String toString() {
        return  "Alumno " + numero + "\n" +
                "Nombre: " + nombre + "\n" +
                "Fecha de nacimiento: " + fechaNac + "\n" +
                "Teléfonos: " + telefono + "\n";
    }

}
