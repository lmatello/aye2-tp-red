package com.caece.Paquete;

import com.caece.IP;

/**
 * Created by lmatello on 04/06/2018.
 */
public class ICMPRequest extends Servicio {

    public ICMPRequest(IP direccionOrigen, IP direccionDestino, Integer timeToLive) {
        super(direccionOrigen, direccionDestino, timeToLive);
    }
}
