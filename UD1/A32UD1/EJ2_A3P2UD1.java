package A32UD1;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.File;
import java.io.BufferedWriter;
import java.io.FileWriter;

public class EJ2_A3P2UD1 {

    public static void main(String[] args) {

        try (BufferedReader br = new BufferedReader(
                new FileReader("Z:\\eloy_rodalperez\\DAM2\\Acceso a datos\\Unidades\\UD1\\A32UD1\\alumnos.txt"));
                BufferedWriter bw = new BufferedWriter(new FileWriter(
                        "Z:\\eloy_rodalperez\\DAM2\\Acceso a datos\\Unidades\\UD1\\A32UD1\\ficherolog.txt", true))) {

            String linea;

            while ((linea = br.readLine()) != null) {

                String[] partes = linea.split("/");
                if (partes.length == 3) {
                    String curso = partes[0];
                    String numero = partes[1];
                    String alumno = partes[2];

                    File directorioCurso = new File("ALUMNOS/" + curso);
                    if (!directorioCurso.exists()) {
                        if (directorioCurso.mkdirs()) {
                            bw.write("-" + curso + " ------> se creó correctamente el directorio\n");
                        } else {
                            bw.write("-" + curso + " ------> Error al crear el directorio\n");
                        }
                    }

                    File directorioAlumno = new File(directorioCurso, numero + "-" + alumno);
                    if (!directorioAlumno.exists()) {
                        if (directorioAlumno.mkdir()) {
                            bw.write("-" + alumno + " ------> se creó correctamente el directorio\n");
                        } else {
                            bw.write("-" + alumno + " ------> Error al crear el directorio\n");
                        }
                    } else {
                        bw.write("-" + alumno + " ------> El directorio ya existe\n");
                    }
                } else {
                    bw.write(linea
                            + "------> no tiene el formato CURSO/NUMERO/ALUMNO. NO SE PUEDE CREAR EL DIRECTORIO\n");
                }
            }
        } catch (IOException e) {
            System.out.println("Error al leer el archivo o escribir en el log: " + e.getMessage());
        }
    }
}