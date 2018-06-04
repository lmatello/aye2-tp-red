package com.caece.Dispositivo;

import com.caece.Excepciones.InvalidIPException;
import com.caece.IP;
import com.caece.SO.IInstallable;
import com.caece.SO.SO;

import java.util.ArrayList;

/**
 * Created by lmatello on 30/05/2018.
 */
public class Terminal extends Dispositivo implements IInstallable{

    private String tipo; // Tablet, Notebook, Etc.
    private SO sistemaOperativo;

    public Terminal(String marca, String modelo, String tipo, int puertos) {
        super(marca,modelo,puertos);
        this.tipo = tipo;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public SO getSistemaOperativo() {
        return sistemaOperativo;
    }

    public void setSistemaOperativo(SO sistemaOperativo) {
        this.sistemaOperativo = sistemaOperativo;
    }

    public void asignarIP (String ip) throws InvalidIPException {
        ArrayList<IP> listaIps = this.sistemaOperativo.getListaIps();
        listaIps.add(IP.stringToIP(ip));
        this.sistemaOperativo.setListaIps(listaIps);
    }

    @Override
    public void instalar(SO so) {
        this.sistemaOperativo = so;
    }
}
