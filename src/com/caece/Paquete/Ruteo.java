package com.caece.Paquete;

import com.caece.IP;

/**
 * Created by lmatello on 30/05/2018.
 */
public class Ruteo extends Paquete {

    private IP defaultGateway;

    public Ruteo(IP direccionOrigen, IP direccionDestino, Integer timeToLive) {
        super(direccionOrigen, direccionDestino, timeToLive);
    }

    //Tiene sentido esto?
    public Ruteo(Servicio servicio, IP defaultGateway){
        super(servicio.getDireccionOrigen(), servicio.getDireccionDestino(), servicio.getTimeToLive());
        this.defaultGateway = defaultGateway;
    }

    public IP getDefaultGateway() {
        return defaultGateway;
    }

    public void setDefaultGateway(IP defaultGateway) {
        this.defaultGateway = defaultGateway;
    }
}
