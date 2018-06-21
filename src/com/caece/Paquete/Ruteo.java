package com.caece.Paquete;

import com.caece.IP;

/**
 * Created by lmatello on 30/05/2018.
 */
public class Ruteo extends Paquete {

    private Paquete paqueteARutear;


     //Armo un paquete ruteo con un servicio dentro, y la direccion destino ahora va a ser la direccion que reciba
    //La direccion que recibo es el default gateway del equipo que encontro que el paquete no lo puedo enviar
    //dentro de su red
    public Ruteo(IP direccionOrigen, IP direccionDestino, Integer timeToLive, Paquete paqueteARutear){
        super(direccionOrigen, direccionDestino, timeToLive);
        this.paqueteARutear = paqueteARutear;
    }

    public Paquete getPaqueteARutear() {
        return paqueteARutear;
    }

    public void setPaqueteARutear(Paquete paqueteARutear) {
        this.paqueteARutear = paqueteARutear;
    }
}
