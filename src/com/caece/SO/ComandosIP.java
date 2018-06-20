package com.caece.SO;

import com.caece.Excepciones.InvalidIPException;
import com.caece.IP;

/**
 * Created by lmatello on 19/06/2018.
 */
public interface ComandosIP {

    void ping(String ipDestino) throws InvalidIPException;

    //public void who(IP ipDestino);
    //public void tracert(IP ipDestino);

}
