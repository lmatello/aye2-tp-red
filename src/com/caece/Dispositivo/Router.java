package com.caece.Dispositivo;

import com.caece.IP;
import com.caece.SO.IInstallable;
import com.caece.SO.SO;

/**
 * Created by lmatello on 30/05/2018.
 */
public class Router extends Dispositivo implements IInstallable {

    private SO sistemaOperativo;

    public Router(String marca, String modelo, SO sistemaOperativo, int puertos) {
        super(marca, modelo, puertos);
        this.sistemaOperativo = sistemaOperativo;
        this.puertos = puertos;
    }

    @Override
    public void instalar(SO so) {
        this.sistemaOperativo = so;
    }
}
