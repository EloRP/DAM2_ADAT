import java.io.*;

class Punto implements Serializable {
    int x, y;

    Punto(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}

class Rectangulo implements java.io.Serializable {
    private int ancho;
    private int alto;
    private Punto origen;

    public Rectangulo(int x, int y, int ancho, int alto) {
        origen = new Punto(x, y);
        this.ancho = ancho;
        this.alto = alto;
    }

    public int getX() {
        return origen.getX();
    }

    public int getY() {
        return origen.getY();
    }

    public int getAncho() {
        return ancho;
    }

    public int getAlto() {
        return alto;
    }
}

public class EjSerializacionFicherosObjetosCompuestos {
    public static void main(String[] args)
            throws IOException, ClassNotFoundException {
        ObjectOutputStream salida = new ObjectOutputStream(new FileOutputStream("C:/FicherosJava/ejobjetos2.dat"));
        salida.writeObject(new Rectangulo(3, 4, 5, 8));
        salida.writeObject(new Rectangulo(6, 1, 5, 8));
        salida.writeObject(new Rectangulo(2, 4, 5, 8));
        salida.close();
        System.out.println("leyendo el fichero");
        ObjectInputStream entrada = new ObjectInputStream(new FileInputStream("C:/FicherosJava/ejobjetos2.dat"));
        try {
            while (true) {
                Rectangulo Rectentrada = (Rectangulo) entrada.readObject();
                System.out.println("(x=" + Rectentrada.getX() + ", y=" + Rectentrada.getY() + "), alto=" +
                        Rectentrada.getAlto() + " Ancho=" + Rectentrada.getAncho());
            }
        } // Si llega al final se produce una excepición IOException
        catch (IOException e) {
            System.out.println("llegado al final");
        }
        entrada.close();
    }
}