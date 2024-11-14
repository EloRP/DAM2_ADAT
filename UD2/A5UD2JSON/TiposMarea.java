package UD2.A5UD2JSON;

public class TiposMarea {
    double altura;
    String data;
    String hora;
    int idPorto;
    int idTipoMarea;
    String tipoMarea;

    public TiposMarea(double altura, String data, String hora, int idPorto, int idTipoMarea, String tipoMarea) {
        this.altura = altura;
        this.data = data;
        this.hora = hora;
        this.idPorto = idPorto;
        this.idTipoMarea = idTipoMarea;
        this.tipoMarea = tipoMarea;
    }

    public TiposMarea() {
    }

    public double getAltura() {
        return altura;
    }

    public void setAltura(double altura) {
        this.altura = altura;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public int getIdPorto() {
        return idPorto;
    }

    public void setIdPorto(int idPorto) {
        this.idPorto = idPorto;
    }

    public int getIdTipoMarea() {
        return idTipoMarea;
    }

    public void setIdTipoMarea(int idTipoMarea) {
        this.idTipoMarea = idTipoMarea;
    }

    public String getTipoMarea() {
        return tipoMarea;
    }

    public void setTipoMarea(String tipoMarea) {
        this.tipoMarea = tipoMarea;
    }

}
