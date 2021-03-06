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
        System.out.println("Terminal " + this.getListaIPs().get(0) + " haciendo ping a " + ip);
        Paquete icmpRequest = new ICMPRequest(this.getListaIPs().get(0),ipDestino,10);
        this.enviar(icmpRequest);

    }

    @Override
    public void who(String ip) throws InvalidIPException {

        IP ipDestino = IP.stringToIP(ip);
        System.out.println("Terminal " + this.getListaIPs().get(0) + " solicitando WHO a " + ip);
        Paquete who = new Who(this.getListaIPs().get(0),ipDestino,10);
        this.enviar(who);

    }

    public void enviar(Paquete paquete){
        //EN "MismaRed" hacer for/while para ver si esta la IP
        if (paquete.getDireccionDestino().mismaRed(this.getListaIPs())){
            this.getDispositivo().getDispositivosConectados()[0].recibir(paquete);
        }else {

            Ruteo paqueteRuteo = new Ruteo(this.defaultGateway, paquete.getDireccionDestino(), paquete.getTimeToLive(), paquete);
            this.getDispositivo().getDispositivosConectados()[0].recibir(paqueteRuteo);
        }
    }

    public void procesar(Paquete paquete) {

        int i = 0;
        while (i < getListaIPs().size() && !paquete.getDireccionDestino().iguales(this.getListaIPs().get(i))) {
            i++;
        }
        if (i < this.getListaIPs().size())
        {
            tratarPaquete(paquete);
        } else {
            //PASAR ESTO A UNA EXCEPTION del tipo "RejectedPackageException"
                     System.out.println("Terminal " + this.getListaIPs().get(0)
                    + " No es destinatario. Descarta paquete de : " + paquete.getDireccionOrigen().toString());
        }
    }


    @Override
    public void tratarPaquete(Paquete paquete) {
        if (paquete instanceof ICMPRequest) {
            System.out.println("Terminal " + this.getListaIPs().get(0) +
                    " recibe ICMPRequest de: " + paquete.getDireccionOrigen().toString());
            Paquete icmpResponse = new ICMPResponse(paquete.getDireccionDestino(), paquete.getDireccionOrigen(), 10);
            this.enviar(icmpResponse);

        } else if (paquete instanceof ICMPResponse) {
            System.out.println("Terminal " + this.getListaIPs().get(0) + " recibe ICMPResponse de: "
                    + paquete.getDireccionOrigen().toString() + " - " + LocalDateTime.now());
        } else if (paquete instanceof Who)
        {
            System.out.println("Terminal " + this.getListaIPs().get(0) +
                    " recibe solicitud WHO de: " + paquete.getDireccionOrigen().toString());
            Paquete sendMessage = new SendMessage(paquete.getDireccionDestino(),paquete.getDireccionOrigen(),10, this);
            this.enviar(sendMessage);
        } else if (paquete instanceof SendMessage)
        {
            System.out.println("Terminal " + this.getListaIPs().get(0) +
                    " recibe SendMessage de " + paquete.getDireccionOrigen().toString());
            ((SendMessage) paquete).getSistemaOperativo().verDatos();
        }else {
            //PASAR ESTO A UNA EXCEPTION del tipo "UnknownPackageTypeException"
            System.out.println("Terminal " + this.getListaIPs().get(0) +
                    "Descarta Paquete de Tipo Desconocido");
        }
    }

    @Override
    public void verDatos() {
        System.out.println("SO: " + super.getNombre());
        System.out.println("Version SO: " + super.getVersion());
        System.out.println("IP: " + this.getListaIPs().get(0));
        System.out.println("Default Gateway: " + this.defaultGateway);

    }

}



