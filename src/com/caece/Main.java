package com.caece;

import com.caece.Dispositivo.Hub;
import com.caece.Dispositivo.Router;
import com.caece.Dispositivo.Terminal;
import com.caece.Excepciones.NetWorkException;
import com.caece.SO.LinuxRouter;
import com.caece.SO.Windows;

public class Main {

    public static void main(String[] args) throws NetWorkException {

        // Creacion de SO y Terminales
        Windows windowsPc1 = new Windows("Windows", "10");
        Windows windowsPc2 = new Windows("Windows", "7");
        Windows windowsPc3 = new Windows("Windows", "8");
        LinuxRouter linuxRouter = new LinuxRouter("Debian", "8");
        Terminal pc1 = new Terminal("Dell", "Vostro", "Laptop");
        Terminal pc2 = new Terminal("Acer", "Inspiron", "Laptop");
        Terminal pc3 = new Terminal("Sony", "Vaio", "NoteBook");

        // Creacion de HUBs y Routers
        Hub hub1 = new Hub("TP-LINK", "Sg1005d", 3);
        Hub hub2 = new Hub("Linksys", "Simon", 3);
        Router router1 = new Router("LinkSys","2750 Dual Band", 4);

        //Configuracion Terminales (Instalacion SO y Asignacion de IPs
        //Las IPs se pedirian mediante un SCANNER para que el usuario las ingrese por terminal
        pc1.instalar(windowsPc1);
        pc2.instalar(windowsPc2);
        pc3.instalar(windowsPc3);
        pc1.getSistemaOperativo().configurarInterfazDeRed("192.168.0.10");
        pc1.getSistemaOperativo().configurarInterfazDeRed("192.168.0.15");
        pc2.getSistemaOperativo().configurarInterfazDeRed("192.168.0.20");
        pc3.getSistemaOperativo().configurarInterfazDeRed("192.168.1.30");

        //Configuracion Router (Instalacion SO y Asignacion de IPs
        router1.instalar(linuxRouter);
        router1.getSistemaOperativo().configurarInterfazDeRed(0,"192.168.0.254");
        router1.getSistemaOperativo().configurarInterfazDeRed(1,"192.168.1.254");

        //Conexiones
        pc1.conectar(hub1);
        pc2.conectar(hub1);
        pc3.conectar(hub2);
        hub1.conectar(router1);
        hub2.conectar(router1);

        System.out.println("--------Envio de PING en LAN-----------");
        pc1.getSistemaOperativo().ping("192.168.0.20");
        // pc2.getSistemaOperativo().ping("192.168.0.15");
        System.out.println("--------Fin--------");

        System.out.println("--------Envio de PING a otra VLAN-----------");
        pc1.getSistemaOperativo().ping("192.168.1.30");
        System.out.println("--------Fin--------");

        System.out.println("--------Envio de WHO a Terminal-----------");
        pc1.getSistemaOperativo().who("192.168.1.30");
        System.out.println("--------Fin--------");

        System.out.println("--------Envio de Ping a Router-----------");
        pc1.getSistemaOperativo().ping("192.168.0.254");
        System.out.println("--------Fin--------");

        System.out.println("--------Envio de WHO a Router-----------");
        pc1.getSistemaOperativo().who("192.168.0.254");
        System.out.println("--------Fin--------");

        System.out.println("--------Envio de Ping de Router a Terminal-----------");
        router1.getSistemaOperativo().ping("192.168.0.10");
        System.out.println("--------Fin--------");

        System.out.println("--------Envio de WHO de Router a Terminal-----------");
        router1.getSistemaOperativo().who("192.168.0.10");
        System.out.println("--------Fin--------");
    }
}