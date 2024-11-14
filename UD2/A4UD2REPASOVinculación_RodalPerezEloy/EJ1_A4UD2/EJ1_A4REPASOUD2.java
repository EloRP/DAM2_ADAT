package UD2.A4UD2REPASOVinculación_RodalPerezEloy.EJ1_A4UD2;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;


public class EJ1_A4REPASOUD2 {
    public static void main(String[] args) {
        try {
            JAXBContext context = JAXBContext.newInstance(Pesca.class);
            Unmarshaller unmarshaller = context.createUnmarshaller();

            File ficheroXML = new File("src\\EJ1_A4REPASOUD2\\Pesca.xml");
            Pesca pesca = (Pesca) unmarshaller.unmarshal(ficheroXML);

            try (BufferedWriter writer =
                    new BufferedWriter(new FileWriter("src\\EJ1_A4REPASOUD2\\Pesca.txt"))) {
                for (Especie especie : pesca.getEspecies()) {
                    writer.write("La especie: " + especie.getNome() + " ("
                            + especie.getNomeCientifico() + ")" + (especie.getValor() != null ? ", tiene un valor " + especie.getValor() + "." : "") + "\n");
                    writer.write("Captura Mínima: " + especie.getCapturaMinima().getValor() + especie.getCapturaMinima().getUnidade() + "\n");
                    writer.write("Otros nombres:\n");
                    writer.write("    -" + especie.getOutrosNomes() + "\n");
                    writer.write("Notas:\n");
                    if (especie.getNotas() != null && !especie.getNotas().isEmpty()) {
                        for (String nota : especie.getNotas()) {
                            writer.write("    - " + nota + "\n");
                        }
                    } else {
                        writer.write("- Ninguna\n");
                    }
                    writer.write("\n");
                }


                for (Xornada xornada : pesca.getXornadas()) {
                    writer.write("Xornada día: " + xornada.getData() + " (Lugar - "
                            + xornada.getLugar() + ")\n");
                    writer.write("Descrición: " + xornada.getDescricion() + "\n");
                    writer.write("Capturas:\n");
                    if (xornada.getCapturas().size() > 0) {
                        for (Captura captura : xornada.getCapturas()) {
                            writer.write("    - " + captura + "\n");
                        }
                    } else {
                        writer.write("    - Ningunha.\n");
                    }
                    writer.write("\n");
                }
            }

        } catch (JAXBException e) {
            System.out.println("Error al unmarshallear el XML.");
        } catch (IOException e) {
            System.out.println("Error al escribir en el archivo o leer el XML.");
        }
    }
}
