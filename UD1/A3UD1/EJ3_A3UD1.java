package A3UD1;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class EJ3_A3UD1 {

    public static void main(String[] args) throws IOException {
        DataInputStream ficheroDatos1 = null;
        DataInputStream ficheroDatos2 = null;
        DataOutputStream ficheroSalida = null;
        Integer numeroFichero1 = null;
        Integer numeroFichero2 = null;

        try {
            ficheroDatos1 = new DataInputStream(new FileInputStream(
                    "Z:\\eloy_rodalperez\\DAM2\\Acceso a datos\\Unidades\\UD1\\A3UD1\\Num1.dat"));
            ficheroDatos2 = new DataInputStream(new FileInputStream(
                    "Z:\\eloy_rodalperez\\DAM2\\Acceso a datos\\Unidades\\UD1\\A3UD1\\Num2.dat"));
            ficheroSalida = new DataOutputStream(new FileOutputStream(
                    "Z:\\eloy_rodalperez\\DAM2\\Acceso a datos\\Unidades\\UD1\\A3UD1\\Mezcla.dat"));

            numeroFichero1 = ficheroDatos1.readInt();
            numeroFichero2 = ficheroDatos2.readInt();

            while (true) {
                if (numeroFichero1 < numeroFichero2) {
                    ficheroSalida.writeInt(numeroFichero1);
                    numeroFichero1 = ficheroDatos1.readInt();
                } else {
                    ficheroSalida.writeInt(numeroFichero2);
                    numeroFichero2 = ficheroDatos2.readInt();
                }
            }
        } catch (IOException e) {
            System.out.println("Fin de un archivo");
            if (ficheroDatos1 != null && ficheroDatos1.available() > 0) {
                escribirDatosRestantes(ficheroDatos1, ficheroSalida, numeroFichero1);
            } else if (ficheroDatos2 !=null && ficheroDatos2.available() > 0) {
                escribirDatosRestantes(ficheroDatos2, ficheroSalida, numeroFichero2);
            }
        } finally {
            try {
                if (ficheroDatos1 != null) {
                    ficheroDatos1.close();
                }
                if (ficheroDatos2 != null) {
                    ficheroDatos2.close();
                }
                if (ficheroSalida != null) {
                    ficheroSalida.close();
                }
            } catch (IOException e) {
                System.out.println("Error al cerrar los flujos");
            }
        }
    }

    private static void escribirDatosRestantes(DataInputStream ficheroDatos, DataOutputStream ficheroSalida,
            Integer numeroFichero) throws IOException {
            while (true) {
                ficheroSalida.writeInt(numeroFichero);
                numeroFichero = ficheroDatos.readInt();
            }
    }
}