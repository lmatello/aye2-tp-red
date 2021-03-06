package com.caece;

import com.caece.Excepciones.InvalidIPException;

import java.util.List;

/**
 * Created by lmatello on 30/05/2018.
 */
public class IP {

    private int octeto1;
    private int octeto2;
    private int octeto3;
    private int octeto4;

    public IP() {
    }

    public IP(int octeto1, int octeto2, int octeto3, int octeto4) throws InvalidIPException {
        if (octeto1 < 0 || octeto1 > 255)
            throw new InvalidIPException();
        else if (octeto2 < 0 || octeto2 > 255)
            throw new InvalidIPException();
        else if (octeto3 < 0 || octeto3 > 255)
            throw new InvalidIPException();
        else if  (octeto4 < 0 || octeto4 > 255 || octeto4 == 255)
            //else if  (octeto4 < 0 || octeto4 > 255 || octeto4 == 0 || octeto4 == 255)
            throw new InvalidIPException();
        else {
            this.octeto1 = octeto1;
            this.octeto2 = octeto2;
            this.octeto3 = octeto3;
            this.octeto4 = octeto4;
            System.out.println("La nueva IP es : " + octeto1 + "." + octeto2 + "." + octeto3 + "." + octeto4);
        }
    }

    public static IP stringToIP (String ip) throws InvalidIPException {
        return new IP(Integer.parseInt(ip.split("\\.")[0]),Integer.parseInt(ip.split("\\.")[1]),Integer.parseInt(ip.split("\\.")[2]),Integer.parseInt(ip.split("\\.")[3]));
    }

    public Boolean iguales(IP ip)
    {
        return     this.octeto1 == ip.octeto1
                && this.octeto2 == ip.octeto2
                && this.octeto3 == ip.octeto3
                && this.octeto4 == ip.octeto4;

    }

    public Boolean mismaRed(IP ip)
    {
        return     this.octeto1 == ip.octeto1
                && this.octeto2 == ip.octeto2
                && this.octeto3 == ip.octeto3;

    }

    public Boolean mismaRed(List<IP> listaIPs) {
        boolean mismaRed = false;
        for (IP ip : listaIPs){
            if (this.octeto1 == ip.octeto1 && this.octeto2 == ip.octeto2 && this.octeto3 == ip.octeto3){
                mismaRed = true;
            }
        }
        return mismaRed;
    }

    public int getOcteto1() {
        return octeto1;
    }

    public void setOcteto1(int octeto1) {
        this.octeto1 = octeto1;
    }

    public int getOcteto2() {
        return octeto2;
    }

    public void setOcteto2(int octeto2) {
        this.octeto2 = octeto2;
    }

    public int getOcteto3() {
        return octeto3;
    }

    public void setOcteto3(int octeto3) {
        this.octeto3 = octeto3;
    }

    public int getOcteto4() {
        return octeto4;
    }

    public void setOcteto4(int octeto4) {
        this.octeto4 = octeto4;
    }

    @Override
    public String toString() {
        return "[" + octeto1 + "." + octeto2 + "." + octeto3 + "." + octeto4 + "]";
    }
}
