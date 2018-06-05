package com.caece.Dispositivo;

import com.caece.Excepciones.DeviceNotConnectedException;
import com.caece.Excepciones.ExceededConectionException;

/**
 * Created by lmatello on 30/05/2018.
 */
public class Hub extends Dispositivo {

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
}
