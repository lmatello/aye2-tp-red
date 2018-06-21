package com.caece.SO;

import com.caece.Dispositivo.Router;
import com.caece.Excepciones.InvalidIPException;
import com.caece.IP;
import com.caece.Paquete.*;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by lmatello on 07/06/2018.
 */
public class LinuxRouter extends SO {

    private Map<Integer, IP> tablaRuteo;
    //private Map<Integer, Dispositivo> tablaRuteoDispositivo;

    public LinuxRouter(String nombre, String version) throws InvalidIPException {
        super(nombre, version);
        this.tablaRuteo = new HashMap<Integer, IP>();
        //this.tablaRuteoDispositivo = new HashMap<Integer, Dispositivo>();
    }

    public Map<Integer, IP> getTablaRuteo() {
        return tablaRuteo;
    }

    public void setTablaRuteo(Map<Integer, IP> tablaRuteo) {
        this.tablaRuteo = tablaRuteo;
    }

    @Override
    public void configurarInterfazDeRed(String ip) throws InvalidIPException {
        //TODO - No se implementa este metodo para terminal.
    }

    @Override
    public void configurarInterfazDeRed(Integer interfaz, String ip) throws InvalidIPException {
        this.tablaRuteo.put(interfaz,IP.stringToIP(ip));
    }

    public void enviar(Paquete paquete){
        //TODO
    }

    //ESTE DEBERIA SER ABSTRACT Y MANDARLO A CADA TERMINAL O ROUTER
    public void procesar(Paquete paquete) {
        if (paquete instanceof Ruteo) {
            System.out.println("Router Interfaz" + obtenerInterfaz((paquete.getDireccionOrigen()))
                    + " recibio un paquete desde " + ((Ruteo) paquete).getPaqueteARutear().getDireccionOrigen());
            tratarPaquete(paquete);
            }
            else
                System.out.println("NO SE ENCONTRO defaultGateway en Tabla de ruteo de ROUTER");
            //Ir a otro Router?
        }


    public boolean estaEnRouter(IP ip){
        boolean encontrada = false;
        for (Map.Entry<Integer, IP> entry : tablaRuteo.entrySet()) {
            if (entry.getValue().iguales(ip)) {
                encontrada = true;
            }
        }
        return encontrada;
    }

    public Integer obtenerInterfaz(IP ip){
        Integer interfaz = -1;
        for (Map.Entry<Integer, IP> entry : tablaRuteo.entrySet()) {
            if (entry.getValue().mismaRed(ip)) {
                interfaz = entry.getKey();
            }
        }
        return interfaz;
    }

    @Override
    public void tratarPaquete(Paquete paquete)
    {
        System.out.println("Redireccionando paquete por Interfaz" + obtenerInterfaz(paquete.getDireccionDestino()) );
        this.getDispositivo().getDispositivosConectados()[obtenerInterfaz(paquete.getDireccionDestino())].recibir(((Ruteo) paquete).getPaqueteARutear());


    }

    @Override
    public void ping(String ipDestino) throws InvalidIPException {
        if (estaEnRouter(IP.stringToIP(ipDestino)))
        {

        }
    }
}
