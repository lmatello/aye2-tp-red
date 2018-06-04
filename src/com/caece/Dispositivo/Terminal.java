package com.caece.Dispositivo;

import com.caece.SO.IInstallable;
import com.caece.SO.SO;

/**
 * Created by lmatello on 30/05/2018.
 */
public class Terminal extends Dispositivo implements IInstallable{

    private String tipo; // Tablet, Notebook, Etc.
    private SO sistemaOperativo;

    public Terminal(String marca, String modelo, String tipo) {
        super(marca,modelo);
        this.tipo = tipo;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    @Override
    public void instalar(SO so) {
        this.sistemaOperativo = so;
    }
}
