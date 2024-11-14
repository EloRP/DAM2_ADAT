package UD2.A5UD2JSON;

import java.util.ArrayList;

public class Mareas {
    String data;
    int idPorto;
    int idPortoRef;
    double latitude;
    ArrayList<TiposMarea> listaMareas;
    double lonxitude;
    String nomePorto;

    public Mareas(String data, int idPorto, int idPortoRef, double latitude, ArrayList<TiposMarea> listaTiposMarea,
            double lonxitude, String nomePorto) {
        this.data = data;
        this.idPorto = idPorto;
        this.idPortoRef = idPortoRef;
        this.latitude = latitude;
        this.listaMareas = listaTiposMarea;
        this.lonxitude = lonxitude;
        this.nomePorto = nomePorto;
    }

    public Mareas() {
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public int getIdPorto() {
        return idPorto;
    }

    public void setIdPorto(int idPorto) {
        this.idPorto = idPorto;
    }

    public int getIdPortoRef() {
        return idPortoRef;
    }

    public void setIdPortoRef(int idPortoRef) {
        this.idPortoRef = idPortoRef;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public ArrayList<TiposMarea> getListaMareas() {
        return listaMareas;
    }

    public void setListaMareas(ArrayList<TiposMarea> listaTiposMarea) {
        this.listaMareas = listaTiposMarea;
    }

    public double getLonxitude() {
        return lonxitude;
    }

    public void setLonxitude(double lonxitude) {
        this.lonxitude = lonxitude;
    }

    public String getNomePorto() {
        return nomePorto;
    }

    public void setNomePorto(String nomePorto) {
        this.nomePorto = nomePorto;
    }

}
