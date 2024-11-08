package EJ1_A4UD1;

import java.io.*;

public class NotaModulo implements Serializable {
    static final long serialVersionUID = 42L;
    String asignatura;
    Double nota;

    public NotaModulo(String asignatura, Double nota) {
        this.asignatura = asignatura;
        this.nota = nota;
    }

    public String getAsignatura() {
        return asignatura;
    }

    public void setAsignatura(String asignatura) {
        this.asignatura = asignatura;
    }

    public Double getNota() {
        return nota;
    }

    public void setNota(Double nota) {
        this.nota = nota;
    }

    @Override
    public String toString() {
        return asignatura + ": " + nota;
    }

}
