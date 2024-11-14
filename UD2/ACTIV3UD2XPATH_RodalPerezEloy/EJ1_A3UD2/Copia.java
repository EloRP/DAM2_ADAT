package UD2.ACTIV3UD2XPATH_RodalPerezEloy.EJ1_A3UD2;

public class Copia {
    private int numero;
    private String estado;

    public Copia() {
    }

    public Copia(int numero, String estado) {
        this.numero = numero;
        this.estado = estado;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    @Override
    public String toString() {
        return "{Número de copia:" + numero + ", Estado: " + estado + "}";
    }
}
