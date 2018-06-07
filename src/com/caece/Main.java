package com.caece;

import com.caece.Dispositivo.Hub;
import com.caece.Dispositivo.Router;
import com.caece.Dispositivo.Terminal;
import com.caece.Excepciones.NetWorkException;
import com.caece.SO.LinuxRouter;
import com.caece.SO.Windows;

public class Main {

    public static void main(String[] args) throws NetWorkException {

        //IP ip2 = new IP(172,0,0,120);
        //IP ip = IP.stringToIP("192.168.0.10"); //Esto se pediria con el SCANNER

        // Creacion de SO y Terminales
        Windows windows10 = new Windows("Windows", "10");
        Windows windows7 = new Windows("Windows", "7");
        LinuxRouter linuxRouter = new LinuxRouter("Debian", "8");
        Terminal pc1 = new Terminal("Dell", "Vostro", "Laptop");
        Terminal pc2 = new Terminal("Acer", "Inspiron", "Laptop");
        Terminal pc3 = new Terminal("Sony", "Vaio", "NoteBook");

        //Instalacion de SOs Terminales
        pc1.instalar(windows10);
        pc2.instalar(windows7);
        pc3.instalar(windows10);

        //Asignacion de IPs
        //Las IPs se pedirian mediante un SCANNER para que el usuario las ingrese por terminal
        pc1.getSistemaOperativo().asignarIP("192.168.0.10");
        pc1.getSistemaOperativo().asignarIP("192.168.0.11");
        pc2.getSistemaOperativo().asignarIP("192.168.0.20");

        pc3.getSistemaOperativo().asignarIP("192.168.1.30");

        // Creacion de HUBs y Routers
        Hub hub1 = new Hub("TP-LINK", "Sg1005d", 2);
        Hub hub2 = new Hub("Linksys", "Simon", 2);
        Router router1 = new Router("LinkSys","2750 Dual Band", 2);

        //Configuracion Router
        router1.instalar(linuxRouter);
        router1.getSistemaOperativo();

        pc1.conectar(hub1);
        pc2.conectar(hub1);
        pc3.conectar(hub2);

        hub1.conectar(router1);
        hub2.conectar(router1);

//        System.out.println("Hub1 :");
//        hub1.getDispositivosConectados().stream().forEach(dispositivo -> System.out.println("Marca : " + dispositivo.getMarca() + " - Modelo :" + dispositivo.getModelo()));
//        System.out.println("Hub2 :");
//        hub2.getDispositivosConectados().stream().forEach(dispositivo -> System.out.println("Marca : " + dispositivo.getMarca() + " - Modelo :" + dispositivo.getModelo()));
//        System.out.println("Router1 :");
//        router1.getDispositivosConectados().stream().forEach(dispositivo -> System.out.println("Marca : " + dispositivo.getMarca() + " - Modelo :" + dispositivo.getModelo()));

    }
}