package com.caece.SO;

import com.caece.Dispositivo.Dispositivo;
import com.caece.Excepciones.InvalidIPException;
import com.caece.IP;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by lmatello on 04/06/2018.
 */
public abstract class SO {

    private String nombre;
    private String version;
    private ArrayList<IP> listaIps; // Lista de ips de dispositivos conectados?
    protected Map<Integer, IP> tablaRuteo;
    private IP defaultGateway;

    public SO(String nombre, String version) throws InvalidIPException {
        this.nombre = nombre;
        this.version = version;
        this.listaIps = new ArrayList<>(); //Inicializo en vacio la lista de IPs
        this.defaultGateway = new IP(0,0,0,0);
        this.tablaRuteo = new HashMap<Integer, IP>();
    }

    public SO(String nombre, String version, ArrayList<IP> listaIps) {
        this.nombre = nombre;
        this.version = version;
        this.listaIps = listaIps;
    }

    public Map<Integer, IP> getTablaRuteo() {
        return tablaRuteo;
    }

    public void setTablaRuteo(Map<Integer, IP> tablaRuteo) {
        this.tablaRuteo = tablaRuteo;
    }

    public void setListaIps(ArrayList<IP> listaIps) {
        this.listaIps = listaIps;
    }

    public ArrayList<IP> getListaIps() {
        return listaIps;
    }

    public void asignarIP (String ip) throws InvalidIPException {
        ArrayList<IP> listaIps = this.getListaIps();
        listaIps.add(IP.stringToIP(ip));
        this.setListaIps(listaIps);
    }
}
