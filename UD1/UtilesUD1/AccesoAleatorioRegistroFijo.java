package UtilesUD1;

import java.io.IOException;
import java.io.RandomAccessFile;

public class AccesoAleatorioRegistroFijo {

    // Tamaño de cada registro: 28 bytes
    public static final int TAMANO_REGISTRO = 28;

    // Método para escribir un registro en una posición dada
    public void escribirRegistro(String archivo, int id, String nombre, int edad, int numeroRegistro) {
        try (RandomAccessFile archivoAcceso = new RandomAccessFile(archivo, "rw")) {
            // Calculamos la posición del registro (númeroRegistro * tamaño del registro)
            long posicion = numeroRegistro * TAMANO_REGISTRO;
            archivoAcceso.seek(posicion); // Mover el puntero a la posición

            // Escribir los datos en el archivo
            archivoAcceso.writeInt(id); // Escribir el ID (4 bytes)
            String nombreFormateado = String.format("%-20s", nombre); // Ajustar el nombre a 20 caracteres
            archivoAcceso.writeBytes(nombreFormateado); // Escribir el nombre (20 bytes)
            archivoAcceso.writeInt(edad); // Escribir la edad (4 bytes)
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Método para leer un registro en una posición dada
    public void leerRegistro(String archivo, int numeroRegistro) {
        try (RandomAccessFile archivoAcceso = new RandomAccessFile(archivo, "r")) {
            // Calcular la posición del registro
            long posicion = numeroRegistro * TAMANO_REGISTRO;
            archivoAcceso.seek(posicion); // Mover el puntero a la posición

            // Leer los datos del archivo
            int id = archivoAcceso.readInt(); // Leer el ID (4 bytes)
            byte[] nombreBytes = new byte[20];
            archivoAcceso.read(nombreBytes); // Leer el nombre (20 bytes)
            String nombre = new String(nombreBytes).trim(); // Convertir a String y quitar espacios extra
            int edad = archivoAcceso.readInt(); // Leer la edad (4 bytes)

            // Mostrar los datos leídos
            System.out.println("ID: " + id + ", Nombre: " + nombre + ", Edad: " + edad);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void actualizarRegistro(String archivo, int id, String nuevoNombre, int nuevaEdad, int numeroRegistro) {
        try (RandomAccessFile archivoAcceso = new RandomAccessFile(archivo, "rw")) {
            // Calcular la posición del registro a actualizar
            long posicion = numeroRegistro * TAMANO_REGISTRO;
            archivoAcceso.seek(posicion); // Mover el puntero a la posición

            // Escribir los nuevos datos en el archivo
            archivoAcceso.writeInt(id); // Actualizar el ID
            String nombreFormateado = String.format("%-20s", nuevoNombre); // Ajustar el nombre a 20 caracteres
            archivoAcceso.writeBytes(nombreFormateado); // Escribir el nuevo nombre
            archivoAcceso.writeInt(nuevaEdad); // Actualizar la edad
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        AccesoAleatorioRegistroFijo ejemplo = new AccesoAleatorioRegistroFijo();

        // Escribir registros
        ejemplo.escribirRegistro("datos.dat", 1, "Juan Perez", 25, 0);
        ejemplo.escribirRegistro("datos.dat", 2, "Ana Gomez", 30, 1);

        // Leer registros
        ejemplo.leerRegistro("datos.dat", 0);
        ejemplo.leerRegistro("datos.dat", 1);
    }
}
