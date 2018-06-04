package com.caece;

import com.caece.Excepciones.InvalidIPException;

/**
 * Created by lmatello on 30/05/2018.
 */
public class IP {

    int octeto1;
    int octeto2;
    int octeto3;
    int octeto4;

    public IP(int octeto1, int octeto2, int octeto3, int octeto4) throws InvalidIPException {
        if (octeto1 < 0 || octeto1 > 255)
            throw new InvalidIPException("IP fuera de rango");
        else if (octeto2 < 0 || octeto2 > 255)
            throw new InvalidIPException("IP fuera de rango");
        else if (octeto3 < 0 || octeto3 > 255)
            throw new InvalidIPException("IP fuera de rango");
        else if  (octeto4 < 0 || octeto4 > 255 || octeto4 == 0 || octeto4 == 255)
            throw new InvalidIPException("IP fuera de rango");
        else {
            this.octeto1 = octeto1;
            this.octeto2 = octeto2;
            this.octeto3 = octeto3;
            this.octeto4 = octeto4;
            System.out.println("La nueva IP es : " + octeto1 + "." + octeto2 + "." + octeto3 + "." + octeto4);
        }
    }
}
