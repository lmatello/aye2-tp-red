package com.caece.Paquete;

import com.caece.IP;

/**
 * Created by lmatello on 30/05/2018.
 */
public abstract class Paquete {

    protected IP direccionOrigen;
    protected IP direccionDestino;
    protected Integer timeToLive;

    //Constructor
    public Paquete(IP direccionOrigen, IP direccionDestino, Integer timeToLive) {
        this.direccionOrigen = direccionOrigen;
        this.direccionDestino = direccionDestino;
        this.timeToLive = timeToLive;
    }

    public IP getDireccionOrigen() {
        return direccionOrigen;
    }

    public void setDireccionOrigen(IP direccionOrigen) {
        this.direccionOrigen = direccionOrigen;
    }

    public IP getDireccionDestino() {
        return direccionDestino;
    }

    public void setDireccionDestino(IP direccionDestino) {
        this.direccionDestino = direccionDestino;
    }

    public Integer getTimeToLive() {
        return timeToLive;
    }

    public void setTimeToLive(Integer timeToLive) {
        this.timeToLive = timeToLive;
    }
}
