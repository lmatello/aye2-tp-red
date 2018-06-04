package com.caece;

import com.caece.Dispositivo.Terminal;
import com.caece.Excepciones.NetWorkException;
import com.caece.SO.Windows;

public class Main {

    public static void main(String[] args) throws NetWorkException {

        //IP ip2 = new IP(172,0,0,120);
        //IP ip = IP.stringToIP("192.168.0.10"); //Esto se pediria con el SCANNER

        // Aca creacion de objetos
        Windows windows10 = new Windows("Windows", "10");
        Windows windows7 = new Windows("Windows", "7");
        Terminal pc1 = new Terminal("Dell", "Vostro", "Laptop", 1);
        Terminal pc2 = new Terminal("Acer", "Inspiron", "Laptop", 1);

        //Instalacion de SOs
        pc1.instalar(windows10);
        pc2.instalar(windows7);

        //Asignacion de IPs
        //Las IPs se pedirian mediante un SCANNER para que el usuario las ingrese por terminal
        pc1.asignarIP("192.168.0.10");
        pc2.asignarIP("192.168.0.11");

    }
}