package UD2.A5UD2JSON.EJ13A5UD2;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class EJ1_A5UD2 {
    public static void main(String[] args) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            File fichero = new File("UD2\\A5UD2JSON\\jsonMareas.json");
            Map<String, Object> map = mapper.readValue(fichero, new TypeReference<Map<String, Object>>() {
            });
            @SuppressWarnings("unchecked")
            List<Map<String, Object>> mareas = (List<Map<String, Object>>) map.get("mareas");
            SimpleDateFormat outputFormat = new SimpleDateFormat("EEEEEEEE, dd MMMM yyyy");
            SimpleDateFormat fecha = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
            for (Map<String, Object> marea : mareas) {
                System.out.println("=============================");
                System.out.print("Fecha de la predicción: "
                        + outputFormat.format(fecha.parse(marea.get("data").toString())) + "\t");
                System.out.println("Puerto: " + marea.get("nomePorto"));

                @SuppressWarnings("unchecked")
                List<Map<String, Object>> tiposMarea = (List<Map<String, Object>>) marea.get("listaMareas");

                for (Map<String, Object> tipoMarea : tiposMarea) {
                    System.out.print("estado: " + tipoMarea.get("tipoMarea") + " \t");
                    System.out.print("hora: " + tipoMarea.get("hora") + " \t");
                    System.out.print("altura: " + tipoMarea.get("altura") + "\n");
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
