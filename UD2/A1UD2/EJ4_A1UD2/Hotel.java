package UD2.A1UD2.EJ4_A1UD2;

import java.io.Serializable;
import java.util.Date;

public class Hotel implements Serializable {
    int codigoHotel;
    String nombre;
    Date fechaInauguracion;
    int telefono;
    Direccion direccion;

    public int getCodigoHotel() {
        return codigoHotel;
    }

    public void setCodigoHotel(int codigoHotel) {
        this.codigoHotel = codigoHotel;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Date getFechaInauguracion() {
        return fechaInauguracion;
    }

    public void setFechaInauguracion(Date fechaInauguracion) {
        this.fechaInauguracion = fechaInauguracion;
    }

    public int getTelefono() {
        return telefono;
    }

    public void setTelefono(int telefono) {
        this.telefono = telefono;
    }

    public Direccion getDireccion() {
        return direccion;
    }

    public void setDireccion(Direccion direccion) {
        this.direccion = direccion;
    }

    public Hotel(int codigoHotel, String nombre, Date fechaInauguracion, int telefono, Direccion direccion) {
        this.codigoHotel = codigoHotel;
        this.nombre = nombre;
        this.fechaInauguracion = fechaInauguracion;
        this.telefono = telefono;
        this.direccion = direccion;
    }

    @Override
    public String toString() {
        return "Hotel ID: " + codigoHotel + ", Nombre: " + nombre + ", FechaInauguracion: " + fechaInauguracion
                + ", Teléfono: " + telefono + ", " + direccion + "]";
    }

}
