package A4UD1;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.Date;

public class FicheroAlumnos {
    RandomAccessFile fichero;
    int tamanhoRegistro;

    public FicheroAlumnos(int tamanhoRegistro) throws FileNotFoundException {
        File ruta = new File("A4UD1\\alumno.dat");
        fichero = new RandomAccessFile(ruta, "rw");
        this.tamanhoRegistro = tamanhoRegistro;
    }

    public RandomAccessFile getFichero() {
        return fichero;
    }

    public int getTamanhoRegistro() {
        return tamanhoRegistro;
    }

    public boolean guardarAlumno(Nombre nombre, Date fechaNac, ArrayList<String> telefono) throws IOException {
        Alumno alumno = new Alumno(nombre, fechaNac, telefono, false);
        alumno.setNumero(generarNumAlumno());

        fichero.seek(tamanhoRegistro * (alumno.getNumero() - 1));

        fichero.writeInt(alumno.getNumero());
        fichero.writeUTF(nombre.getNombre());
        fichero.writeUTF(nombre.getApellido1());
        fichero.writeUTF(nombre.getApellido2());
        fichero.writeLong(fechaNac.getTime());

        fichero.writeInt(telefono.size());
        for (String tel : telefono) {
            fichero.writeUTF(tel);
        }

        return true;
    }

    public int generarNumAlumno() throws IOException {
        fichero.seek(tamanhoRegistro * getNumRegistros() - 1);
        int ultimoNumero = fichero.readInt();
        return ultimoNumero + 1;
    }

    private long getNumRegistros() throws IOException {
        return fichero.length() / tamanhoRegistro + 1;
    }
}
