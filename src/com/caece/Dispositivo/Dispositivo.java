package com.caece.Dispositivo;

/**
 * Created by lmatello on 30/05/2018.
 */
public abstract class Dispositivo {

    private String marca;
    private String modelo;

    public Dispositivo() {
    }

    public Dispositivo(String marca, String modelo) {
        this.marca = marca;
        this.modelo = modelo;
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
}
