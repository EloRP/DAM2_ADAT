import java.io.*;

public class FiltroExtension implements FilenameFilter {
    String extension;

    public void Filtro(String extension) {
        this.extension = extension;
    }

    public boolean accept(File dir, String name) {
        return name.endsWith(extension);
    }
}
