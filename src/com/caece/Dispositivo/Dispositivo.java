package com.caece.Dispositivo;

/**
 * Created by lmatello on 30/05/2018.
 */
public abstract class Dispositivo {

    protected String marca;
    protected String modelo;
    protected int puertos; //maximos dispositivos que se pueden conectar

    public Dispositivo() {
    }

    public Dispositivo(String marca, String modelo, int puertos) {
        this.marca = marca;
        this.modelo = modelo;
        this.puertos = puertos;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public int getPuertos() {
        return puertos;
    }

    public void setPuertos(int puertos) {
        this.puertos = puertos;
    }
}
