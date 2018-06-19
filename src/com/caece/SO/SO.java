package com.caece.SO;

import com.caece.Dispositivo.Dispositivo;
import com.caece.Excepciones.InvalidIPException;
import com.caece.IP;
import com.caece.Paquete.ICMPRequest;
import com.caece.Paquete.ICMPResponse;
import com.caece.Paquete.Paquete;

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
        this.defaultGateway = new IP(0,0,0,0);
        this.tablaRuteo = new HashMap<Integer, IP>();
    }

    public Map<Integer, IP> getTablaRuteo() {
        return tablaRuteo;
    }

    // Dejando el metodo asi, una terminal no podria tener mas de una IP (se pisan las ips con la Key 0 del map)
    public void asignarIP(String ip) throws InvalidIPException {
        this.tablaRuteo.put(0,IP.stringToIP(ip));
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
        // ACA IF DE ESTA EN LA MISMA RED
        Paquete icmpRequest = new ICMPRequest(this.getTablaRuteo().get(0),ipDestino,10);
        this.enviar(icmpRequest);

    }

    public void enviar(Paquete paquete){
        this.getDispositivo().getDispositivosConectados()[0].recibir(paquete);
    }

    public void procesar(Paquete paquete) {
        //Si el paquetes es "para mi" hago algo. Sino, lo descarto
        // VER ACA PORQUE ESTA PASANDO 2 veces por la IP que le mando. Estaria bien? -> VER LO DE DESCARTAR
        if (paquete.getDireccionDestino().iguales(this.getTablaRuteo().get(0))){
            // ACA FALTA IF DE SI ESTA EN LA MISMA RED
            if (paquete instanceof ICMPRequest){
                Paquete icmpResponse = new ICMPResponse(paquete.getDireccionDestino(),paquete.getDireccionOrigen(),10);
                this.enviar(icmpResponse);

            }else if (paquete instanceof ICMPResponse){
                System.out.println("Recibido ICMP desde : " + paquete.getDireccionOrigen().toString() + " - timeStamp : " + LocalDateTime.now());
            }
        }else{
            System.out.println("DESCARTO PAQUETE desde IP : " + paquete.getDireccionOrigen().toString());
        }
    }
}
