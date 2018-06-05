package com.caece.Excepciones;

import com.caece.Dispositivo.Dispositivo;

/**
 * Created by lmatello on 04/06/2018.
 */

// TODO
public class ExceededConectionException extends NetWorkException {



    public ExceededConectionException() {
    }

    public ExceededConectionException(Dispositivo dispositivo) {
        super("Se ha superado el limite de conexiones del dispositivo. Cantidad de puertos = " + dispositivo.getPuertos());
    }

    public ExceededConectionException(String message) {
        super(message);
    }

    public ExceededConectionException(String message, Throwable cause) {
        super(message, cause);
    }

    public ExceededConectionException(Throwable cause) {
        super(cause);
    }
}
