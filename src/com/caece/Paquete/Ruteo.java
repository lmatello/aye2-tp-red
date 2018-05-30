package com.caece.Paquete;

import com.caece.IP;

/**
 * Created by lmatello on 30/05/2018.
 */
public class Ruteo extends Paquete {

    public Ruteo(IP direccionOrigen, IP direccionDestino, Integer timeToLive) {
        super(direccionOrigen, direccionDestino, timeToLive);
    }
}
