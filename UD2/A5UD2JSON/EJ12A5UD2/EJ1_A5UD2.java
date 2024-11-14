package UD2.A5UD2JSON.EJ12A5UD2;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;

import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class EJ1_A5UD2 {
    public static void main(String[] args) throws StreamReadException, DatabindException, IOException {
        ObjectMapper mapper = new ObjectMapper();
        File fichero = new File("UD2\\A5UD2JSON\\jsonMareas.json");
        JsonNode rootNode = mapper.readTree(fichero);
        JsonNode raiz = rootNode.get("mareas");
        SimpleDateFormat outputFormat = new SimpleDateFormat("EEEEEEEE, dd MMMM yyyy");
        SimpleDateFormat fecha = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");

        try {
            for (JsonNode nodoMarea : raiz) {
                String data = nodoMarea.get("data").asText();
                System.out.println("=============================");
                System.out.print("Fecha de la predicción: " + outputFormat.format(fecha.parse(data)) + "\t");
                System.out.print("Puerto:" + nodoMarea.get("nomePorto").asText() + "\n");

                JsonNode listaTiposMareas = nodoMarea.get("listaMareas");

                for (JsonNode tiposMareas : listaTiposMareas) {
                    System.out.print("estado: " + tiposMareas.get("tipoMarea").asText() + " \t");
                    System.out.print("hora: " + tiposMareas.get("hora").asText() + " \t");
                    System.out.print("altura: " + tiposMareas.get("altura").asText() + " \n");
                }

            }
        } catch (Exception e) {
            e.getMessage();
        }
    }
}
