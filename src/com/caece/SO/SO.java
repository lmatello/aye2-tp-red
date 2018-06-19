package com.caece.SO;

import com.caece.Dispositivo.Dispositivo;
import com.caece.Excepciones.InvalidIPException;
import com.caece.IP;
import com.caece.Paquete.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by lmatello on 04/06/2018.
 */
public abstract class SO {

    private String nombre;
    private String version;
    //private ArrayList<IP> listaIps; // Lista de ips de dispositivos conectados?
    protected Map<Integer, IP> tablaRuteo;
    private IP defaultGateway;
    private Dispositivo dispositivo;

    public SO(String nombre, String version) throws InvalidIPException {
        this.nombre = nombre;
        this.version = version;
        //this.listaIps = new ArrayList<>(); //Inicializo en vacio la lista de IPs
        this.defaultGateway = null;
        this.tablaRuteo = new HashMap<Integer, IP>();
    }

    public Map<Integer, IP> getTablaRuteo() {
        return tablaRuteo;
    }

    // Dejando el metodo asi, una terminal no podria tener mas de una IP (se pisan las ips con la Key 0 del map)
    public void asignarIP(String ip) throws InvalidIPException {
        IP newIP = IP.stringToIP(ip);
        this.tablaRuteo.put(0,newIP);
        this.defaultGateway = new IP(newIP.getOcteto1(),newIP.getOcteto2(),newIP.getOcteto3(),254);
    }

    public void asignarIPPuerto(Integer interfaz, String ip) throws InvalidIPException {
        this.tablaRuteo.put(interfaz,IP.stringToIP(ip));
    }

    public Dispositivo getDispositivo() {
        return dispositivo;
    }

    public void setDispositivo(Dispositivo dispositivo) {
        this.dispositivo = dispositivo;
    }

    public void ping (String ip) throws InvalidIPException {
        IP ipDestino = IP.stringToIP(ip);
        Paquete icmpRequest = new ICMPRequest(this.getTablaRuteo().get(0),ipDestino,10);
        this.enviar(icmpRequest);

    }

    public void enviar(Paquete paquete){
        //Chequeo MismaRed.
        if (paquete.getDireccionDestino().mismaRed(this.getTablaRuteo().get(0))){
            this.getDispositivo().getDispositivosConectados()[0].recibir(paquete);
        }else {
            System.out.println("HAY QUE ARMAR PAQUETE DE RUTEO");
            //Ver aca el tema de la IP destino de defaultGateway (donde esta esta IP?)
            Paquete paqueteRuteo = new Ruteo((Servicio)paquete, this.defaultGateway); //Aca podria ir un IF con un instance of
            //Aca en vez de recibir lo deberia mandar al router, no?
            this.getDispositivo().getDispositivosConectados()[0].recibir(paqueteRuteo);
        }
    }

    public void procesar(Paquete paquete) {
        //Si el paquetes es "para mi" hago algo. Sino, lo descarto
        // VER ACA PORQUE ESTA PASANDO 2 veces por la IP que le mando. Estaria bien? -> VER LO DE DESCARTAR
        if (paquete.getDireccionDestino().iguales(this.getTablaRuteo().get(0))){
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
