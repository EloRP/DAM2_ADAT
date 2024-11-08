package UtilesUD1;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class Serializacion {
    // Serializar un objeto
    public void serializarObjeto(String nombreArchivo, Object objeto) {
        try (ObjectOutputStream salida = new ObjectOutputStream(new FileOutputStream(nombreArchivo))) {
            salida.writeObject(objeto);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
