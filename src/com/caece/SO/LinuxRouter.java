package com.caece.SO;

import com.caece.Excepciones.InvalidIPException;
import com.caece.IP;
import com.caece.Paquete.*;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by lmatello on 07/06/2018.
 */
public class LinuxRouter extends SO {

    private Map<Integer, IP> tablaRuteo;

    public LinuxRouter(String nombre, String version) throws InvalidIPException {
        super(nombre, version);
        this.tablaRuteo = new HashMap<Integer, IP>();
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
            Integer interfaz = obtenerInterfaz(((Ruteo) paquete).getDefaultGateway());
            if (interfaz >= 0) {
                System.out.println("ENCONTRADA DEFAULTGATEWAY en ROUTER en interfaz : " + interfaz);
            }
            else
                System.out.println("NO SE ENCONTRO defaultGateway en Tabla de ruteo de ROUTER");
        }
    }

    public Integer obtenerInterfaz(IP ip){
        Integer interfaz = -1;
        for (Map.Entry<Integer, IP> entry : tablaRuteo.entrySet()) {
            if (entry.getValue().iguales(ip)) {
                interfaz = entry.getKey();
            }
        }
        return interfaz;
    }

    @Override
    public void tratarPaquete(Paquete paquete) {
        //TODO
    }

    @Override
    public void ping(String ipDestino) throws InvalidIPException {
        //TODO
    }
}
