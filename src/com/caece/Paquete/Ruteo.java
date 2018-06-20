package com.caece.Paquete;

import com.caece.IP;

/**
 * Created by lmatello on 30/05/2018.
 */
public class Ruteo extends Paquete {

    private IP direccionRuteo;

    public Ruteo(IP direccionOrigen, IP direccionDestino, Integer timeToLive) {
        super(direccionOrigen, direccionDestino, timeToLive);
    }

    public Ruteo(Servicio servicio, IP direccionRuteo){
        super(servicio.getDireccionOrigen(), servicio.getDireccionDestino(), servicio.getTimeToLive());
        this.direccionRuteo = direccionRuteo;
    }

    public IP getDireccionRuteo() {
        return direccionRuteo;
    }

    public void setDireccionRuteo(IP direccionRuteo) {
        this.direccionRuteo = direccionRuteo;
    }
}
