package com.caece.SO;

import com.caece.IP;

import java.util.ArrayList;

/**
 * Created by lmatello on 04/06/2018.
 */
public abstract class SO {

    private String nombre;
    private String version;
    private ArrayList<IP> listaIp;

    public SO(String nombre, String version) {
        this.nombre = nombre;
        this.version = version;
    }

    public SO(String nombre, String version, ArrayList<IP> listaIp) {
        this.nombre = nombre;
        this.version = version;
        this.listaIp = listaIp;
    }
}
