import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.exc.InvalidDefinitionException;

import UD2.A5UD2JSON.ListaMareas;
import UD2.A5UD2JSON.Mareas;
import UD2.A5UD2JSON.TiposMarea;

public class EJ1_A5UD2 {
    public static void main(String[] args) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            File fichero = new File("UD2\\A5UD2JSON\\jsonMareas.json");

            ListaMareas listaMareas = mapper.readValue(fichero, ListaMareas.class);
            List<Mareas> mareas = listaMareas.getMareas();

            SimpleDateFormat outputFormat = new SimpleDateFormat("EEEEEEEE, dd MMMM yyyy");
            SimpleDateFormat fecha = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");

            for (Mareas marea : mareas) {
                System.out.println("=============================");
                System.out.print("Fecha de la predicción: " + outputFormat.format(fecha.parse(marea.getData())) + "\t");
                System.out.print("Puerto:" + marea.getNomePorto() + "\n");
                imprimirInfoMareas(marea);
            }

        } catch (InvalidDefinitionException e) {
            e.printStackTrace();
        } catch (StreamReadException e) {
            e.printStackTrace();
        } catch (DatabindException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    private static void imprimirInfoMareas(Mareas marea) {
        for (TiposMarea tipos : marea.getListaMareas()) {
            System.out.print("estado: " + tipos.getTipoMarea() + " \t");
            System.out.print("hora: " + tipos.getHora() + " \t");
            System.out.print("altura: " + tipos.getAltura() + " \n");
        }
    }
}
