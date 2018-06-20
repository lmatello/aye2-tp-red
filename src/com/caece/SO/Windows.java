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
        this.listaIPs.add(newIP);
        if (this.listaIPs.isEmpty())
            this.defaultGateway = new IP(newIP.getOcteto1(),newIP.getOcteto2(),newIP.getOcteto3(),254);
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
        //Si el paquetes es "para mi" hago algo. Sino, lo descarto
        // VER ACA PORQUE ESTA PASANDO 2 veces por la IP que le mando. Estaria bien? -> VER LO DE DESCARTAR

        //recorrer toda la lista de ips de la terminal.
        if (paquete.getDireccionDestino().iguales(this.getListaIPs().get(0))){
            if (paquete instanceof ICMPRequest){
                Paquete icmpResponse = new ICMPResponse(paquete.getDireccionDestino(),paquete.getDireccionOrigen(),10);
                this.enviar(icmpResponse);

            }else if (paquete instanceof ICMPResponse){
                System.out.println("Recibido ICMP desde : " + paquete.getDireccionOrigen().toString() + " - " + LocalDateTime.now());
            }else{
                //Tipo de paquete desconocido
                System.out.println("DESCARTO PAQUETE - Tipo Paquete DESCONOCIDO");
            }
        }else{
            //La IP del paquete destino, no es para el dispositivo en cuestion
            System.out.println("DESCARTO PAQUETE desde IP : " + paquete.getDireccionOrigen().toString());
        }
    }
}
