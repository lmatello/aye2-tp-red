package com.caece.Paquete;

import com.caece.IP;

/**
 * Created by lmatello on 30/05/2018.
 */
public class Servicio extends Paquete {

    enum TipoServicio {WHO};
    private TipoServicio tipoServicio; // O Directamente un string?

    public Servicio(IP direccionOrigen, IP direccionDestino, Integer timeToLive, TipoServicio tipoServicio) {
        super(direccionOrigen, direccionDestino, timeToLive);
        this.tipoServicio = tipoServicio;
    }

    public TipoServicio getTipoServicio() {
        return tipoServicio;
    }

    public void setTipoServicio(TipoServicio tipoServicio) {
        this.tipoServicio = tipoServicio;
    }
}
