package com.caece.Excepciones;

import sun.nio.ch.Net;

/**
 * Created by lmatello on 04/06/2018.
 */

// TODO
public class DeviceNotConnectedException extends NetWorkException {

    public DeviceNotConnectedException() {
    }

    public DeviceNotConnectedException(String message) {
        super(message);
    }

    public DeviceNotConnectedException(String message, Throwable cause) {
        super(message, cause);
    }

    public DeviceNotConnectedException(Throwable cause) {
        super(cause);
    }
}
