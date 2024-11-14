package UD2.A5UD2JSON.EJ2A5UD2;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

public class EJ2_A5UD2 {
    public static void main(String[] args) throws IOException {
        XmlMapper xmlMapper = new XmlMapper();

        File archivoXml = new File("UD2\\A5UD2JSON\\EJ2A5UD2\\BibliotecaInformatica.xml");
        Biblioteca productos = xmlMapper.readValue(archivoXml, Biblioteca.class);
        ObjectMapper jsonMapper = new ObjectMapper();

        jsonMapper.enable(SerializationFeature.INDENT_OUTPUT);

        File archivoJson = new File("UD2\\A5UD2JSON\\EJ2A5UD2\\bibliotecainformatica.json");
        jsonMapper.writeValue(archivoJson, productos);

        System.out.println("Archivo JSON creado en bibliotecainformatica.json");
    }

    private static void anhadirLibroAJSON(Biblioteca bibliotecainformatica) {
        Seccion seccion = bibliotecainformatica.getSecciones().get(3);
        String[] autores = {"John Doe"};
        Copia[] copias = {new Copia(1, "Disponible")};

        Libro libroNuevo = new Libro("", "12345567889", "Libro Nuevo", 300, Arrays.asList(autores), "10-10-10", "Editorial", 20, Arrays.asList(copias));
        List<Libro> libros = seccion.getLibros();
        libros.add(libroNuevo);
    }
}
