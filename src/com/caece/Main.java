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
        Windows windowsPc1 = new Windows("Windows", "10");
        Windows windowsPc2 = new Windows("Windows", "7");
        Windows windowsPc3 = new Windows("Windows", "8");
        LinuxRouter linuxRouter = new LinuxRouter("Debian", "8");
        Terminal pc1 = new Terminal("Dell", "Vostro", "Laptop");
        Terminal pc2 = new Terminal("Acer", "Inspiron", "Laptop");
        Terminal pc3 = new Terminal("Sony", "Vaio", "NoteBook");

        //Instalacion de SOs Terminales
        pc1.instalar(windowsPc1);
        pc2.instalar(windowsPc2);
        pc3.instalar(windowsPc3);

        //Asignacion de IPs
        //Las IPs se pedirian mediante un SCANNER para que el usuario las ingrese por terminal
        pc1.getSistemaOperativo().asignarIP("192.168.0.10");
        pc2.getSistemaOperativo().asignarIP("192.168.0.20");
        pc3.getSistemaOperativo().asignarIP("192.168.1.30");

        // Creacion de HUBs y Routers
        Hub hub1 = new Hub("TP-LINK", "Sg1005d", 3);
        Hub hub2 = new Hub("Linksys", "Simon", 3);
        Router router1 = new Router("LinkSys","2750 Dual Band", 4);

        //Configuracion Router
        router1.instalar(linuxRouter);
        router1.getSistemaOperativo().asignarIPPuerto(0,"192.168.0.254");
        router1.getSistemaOperativo().asignarIPPuerto(1,"192.168.1.254");

        pc1.conectar(hub1);
        pc2.conectar(hub1);
        pc3.conectar(hub2);

        hub1.conectar(router1);
        hub2.conectar(router1);

        System.out.println("Ips de PC1 :");
        for (int i=0; i<pc1.getSistemaOperativo().getTablaRuteo().size(); i++) {
            System.out.println(pc1.getSistemaOperativo().getTablaRuteo().get(i));
        }
        System.out.println("Ips de Router1 :");
        for (int i=0; i<router1.getSistemaOperativo().getTablaRuteo().size(); i++) {
            System.out.println(router1.getSistemaOperativo().getTablaRuteo().get(i));
        }

        System.out.println("Hub1 :");
        for (int i=0; i < hub1.getDispositivosConectados().length; i++){
            System.out.println(hub1.getDispositivosConectados()[i]);
        }
    }
}