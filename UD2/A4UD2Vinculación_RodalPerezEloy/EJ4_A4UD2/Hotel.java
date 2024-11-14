package UD2.A4UD2Vinculación_RodalPerezEloy.EJ4_A4UD2;

import java.io.Serializable;
import java.util.ArrayList;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlList;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "Hotel")
@XmlType(propOrder = { "nombre", "telefonos", "direccion" })
public class Hotel implements Serializable {
    static final long serialVersionUID = 42L;
    int codigoHotel;
    String nombre;
    String fechaInauguracion;
    ArrayList<String> telefonos;
    Direccion direccion;
    CadenaHotelera cadenaHotelera;

    public Hotel() {
    }

    public Hotel(int codigoHotel, String nombre, String fechaInauguracion, ArrayList<String> telefonos,
            Direccion direccion, CadenaHotelera cadenaHotelera) {
        this.codigoHotel = codigoHotel;
        this.nombre = nombre;
        this.fechaInauguracion = fechaInauguracion;
        this.telefonos = telefonos;
        this.direccion = direccion;
        this.cadenaHotelera = cadenaHotelera;
    }

    @XmlAttribute(name = "id")
    public int getCodigoHotel() {
        return codigoHotel;
    }

    public void setCodigoHotel(int codigoHotel) {
        this.codigoHotel = codigoHotel;
    }

    @XmlElement(name = "Nombre")
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @XmlTransient
    public String getFechaInauguracion() {
        return fechaInauguracion;
    }

    public void setFechaInauguracion(String fechaInauguracion) {
        this.fechaInauguracion = fechaInauguracion;
    }

    @XmlElement(name = "Telefonos")
    @XmlList
    public ArrayList<String> getTelefonos() {
        return telefonos;
    }

    public void setTelefonos(ArrayList<String> telefonos) {
        this.telefonos = telefonos;
    }

    @XmlElement(name = "Direccion")
    public Direccion getDireccion() {
        return direccion;
    }

    public void setDireccion(Direccion direccion) {
        this.direccion = direccion;
    }

}
