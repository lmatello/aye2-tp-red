package com.caece.Dispositivo;

import com.caece.SO.IInstallable;
import com.caece.SO.SO;

/**
 * Created by lmatello on 30/05/2018.
 */
public class Router extends Dispositivo implements IInstallable {

    private SO sistemaOperativo;

    public Router() {
    }

    @Override
    public void instalar(SO so) {
        this.sistemaOperativo = so;
    }
}
