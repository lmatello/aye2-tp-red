package com.caece;

import com.caece.Dispositivo.Terminal;
import com.caece.SO.Windows;

public class Main {

    public static void main(String[] args) throws Exception {
	    // Aca creacion de objetos e interacciones

        IP ip = new IP(172,0,0,120);
        Windows windows10 = new Windows("Windows", "10");

        Terminal pc1 = new Terminal("Dell", "Inspiron", "Laptop");
        pc1.instalar(windows10);

    }
}