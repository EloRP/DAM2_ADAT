package UD2.A2REPASOUD2.EJ1_A2REPASOUD2;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Actor {
    String id;
    String Nome;
    String sexo;
    Date dataNacemento;
    static SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNome() {
        return Nome;
    }

    public void setNome(String nome) {
        Nome = nome;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getDataNacemento() {
        return formato.format(dataNacemento);
    }

    public void setDataNacemento(Date dataNacemento) {
        this.dataNacemento = dataNacemento;
    }

    public Actor(String id, String nome, String sexo, Date dataNacemento) {
        this.id = id;
        Nome = nome;
        this.sexo = sexo;
        this.dataNacemento = dataNacemento;
    }

    

}
