package com.caece.Dispositivo;

import com.caece.Excepciones.DeviceNotConnectedException;
import com.caece.Excepciones.ExceededConectionException;
import com.caece.Paquete.Paquete;

/**
 * Created by lmatello on 30/05/2018.
 */
public class Hub extends Dispositivo {

    //el hub tambien tiene un sistema operativo para hacer el multicast a toda la red
    //hay que meter el metodo en la clase abstracta dispositivo

    public Hub(String marca, String modelo, int puertos) {
        super(marca, modelo, puertos);
        this.puertos = puertos;
    }

    @Override
    public void conectar(Dispositivo dispositivo) throws ExceededConectionException {
        super.conectar(dispositivo);
    }

    @Override
    public void desconectar(Dispositivo dispositivo) throws DeviceNotConnectedException {
    }

    public void recibir(Paquete paquete) {
        for (Dispositivo dispositivo : dispositivosConectados) {
            dispositivo.recibir(paquete);
        }
    }
}
