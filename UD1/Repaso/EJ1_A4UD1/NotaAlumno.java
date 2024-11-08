package EJ1_A4UD1;

import java.io.*;
import java.util.*;

public class NotaAlumno implements Serializable {
    static final long serialVersionUID = 42L;
    private int numero;
    private ArrayList<NotaModulo> notas;

    public NotaAlumno(int numero, ArrayList<NotaModulo> notas) {
        this.numero = numero;
        this.notas = notas;
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

    public ArrayList<NotaModulo> getNotas() {
        return notas;
    }

    public void setNotas(ArrayList<NotaModulo> notas) {
        this.notas = notas;
    }

    @Override
    public String toString() {
        return "Alumno " + numero + " " + notas + "\n";
    }

    

}
