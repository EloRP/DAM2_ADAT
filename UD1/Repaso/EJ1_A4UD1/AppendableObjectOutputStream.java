package EJ1_A4UD1;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class AppendableObjectOutputStream extends ObjectOutputStream {
    public AppendableObjectOutputStream(FileOutputStream fileOutputStream) throws IOException {
        super(fileOutputStream);
    }

    @Override protected void writeStreamHeader() {
        try {
            reset();
        } catch (IOException e) {
            System.out.println("ERROR AL ESCRIBIR LA CABECERA");
        }
    }
}
