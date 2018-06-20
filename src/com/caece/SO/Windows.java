package com.caece.SO;

import com.caece.Excepciones.InvalidIPException;
import com.caece.Excepciones.NetWorkException;
import com.caece.IP;
import com.caece.Paquete.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by lmatello on 04/06/2018.
 */
public class Windows extends SO {

    private IP defaultGateway;
    private List<IP> listaIPs;

    //VER ESTE THROWS CON EL PROFE. Queda feo aca.
    public Windows(String nombre, String version) throws NetWorkException {
        super(nombre, version);
        this.listaIPs = new ArrayList<>(); //Inicializo en vacio la lista de IPs
    }

    @Override
    public void configurarInterfazDeRed(String ip) throws InvalidIPException {
        IP newIP = IP.stringToIP(ip);
        if (this.listaIPs.isEmpty()) {
            this.defaultGateway = new IP(newIP.getOcteto1(), newIP.getOcteto2(), newIP.getOcteto3(), 254);
        }

        this.listaIPs.add(newIP);
    }

    @Override
    public void configurarInterfazDeRed(Integer interfaz, String ip) {
        //TODO - No se implementa este metodo para terminal.
    }

    public IP getDefaultGateway() {
        return defaultGateway;
    }

    public void setDefaultGateway(IP defaultGateway) {
        this.defaultGateway = defaultGateway;
    }

    public void enviar(Paquete paquete){
        //Chequeo MismaRed.
        //EN "MismaRed" hacer for/while para ver si esta la IP
        if (paquete.getDireccionDestino().mismaRed(this.getListaIPs().get(0))){
            this.getDispositivo().getDispositivosConectados()[0].recibir(paquete);
        }else {
            System.out.println("HAY QUE ARMAR PAQUETE DE RUTEO");
            //Ver aca el tema de la IP destino de defaultGateway (donde esta esta IP?)
            Ruteo paqueteRuteo = new Ruteo((Servicio)paquete, this.defaultGateway); //Aca podria ir un IF con un instance of
            this.getDispositivo().getDispositivosConectados()[0].recibir(paqueteRuteo);
        }
    }

    public List<IP> getListaIPs() {
        return listaIPs;
    }

    public void setListaIPs(List<IP> listaIPs) {
        this.listaIPs = listaIPs;
    }

    @Override
    public void ping (String ip) throws InvalidIPException {
        IP ipDestino = IP.stringToIP(ip);
        //Siempre que hacemos un PING, asumimos que sale de la IP de la primer posicion
        Paquete icmpRequest = new ICMPRequest(this.getListaIPs().get(0),ipDestino,10);
        this.enviar(icmpRequest);
    }

    //ESTE DEBERIA SER ABSTRACT Y MANDARLO A CADA TERMINAL O ROUTER
    public void procesar(Paquete paquete) {

        //con este while pregunto si el paquete es para alguna de mis ip
        // si es asi, trato el paquete
        int i = 0;
        while (i < getListaIPs().size() && !paquete.getDireccionDestino().iguales(this.getListaIPs().get(i))) {
            i++;
        }
        if (i < this.getListaIPs().size()) {
            //if (paquete.getDireccionDestino().iguales(this.getListaIPs().get(i))) {
            tratarPaquete(paquete);
        } else {
            //La IP del paquete destino, no es para el dispositivo en cuestion
            System.out.println("DESCARTO PAQUETE desde IP : " + paquete.getDireccionOrigen().toString());
        }
    }


    @Override
    public void tratarPaquete(Paquete paquete) {
        if (paquete instanceof ICMPRequest) {
            Paquete icmpResponse = new ICMPResponse(paquete.getDireccionDestino(), paquete.getDireccionOrigen(), 10);
            this.enviar(icmpResponse);
        } else if (paquete instanceof ICMPResponse) {
            System.out.println(" ICMPResponse desde : " + paquete.getDireccionOrigen().toString() + " - " + LocalDateTime.now());
        } else {
            //Tipo de paquete desconocido
            System.out.println("DESCARTO PAQUETE - Tipo Paquete DESCONOCIDO");
        }

        }



 }



