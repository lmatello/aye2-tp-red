package com.caece.Excepciones;

/**
 * Created by lmatello on 04/06/2018.
 */
public class InvalidIPException extends NetWorkException{

    public InvalidIPException() {
        super("IP fuera de rango");
    }

    public InvalidIPException(String message) {
        super(message);
    }

    public InvalidIPException(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidIPException(Throwable cause) {
        super(cause);
    }
}
