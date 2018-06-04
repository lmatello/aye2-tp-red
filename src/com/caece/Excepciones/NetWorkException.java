package com.caece.Excepciones;

/**
 * Created by lmatello on 04/06/2018.
 */
public class NetWorkException extends Exception {

    public NetWorkException() {
    }

    public NetWorkException(String message) {
        super(message);
    }

    public NetWorkException(String message, Throwable cause) {
        super(message, cause);
    }

    public NetWorkException(Throwable cause) {
        super(cause);
    }
}