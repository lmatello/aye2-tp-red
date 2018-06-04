package com.caece.Paquete;

import com.caece.IP;

/**
 * Created by lmatello on 30/05/2018.
 */
public class Servicio extends Paquete {

    public Servicio(IP direccionOrigen, IP direccionDestino, Integer timeToLive) {
        super(direccionOrigen, direccionDestino, timeToLive);

    }
}
