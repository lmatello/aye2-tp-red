package com.caece.Dispositivo;

import com.caece.Excepciones.DeviceNotConnectedException;
import com.caece.Excepciones.ExceededConectionException;
import com.caece.IP;
import com.caece.Paquete.Paquete;
import com.caece.SO.IInstallable;
import com.caece.SO.LinuxRouter;
import com.caece.SO.SO;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by lmatello on 30/05/2018.
 */
public class Router extends Dispositivo implements IInstallable {

    private SO sistemaOperativo;

    public Router(String marca, String modelo, int puertos) {
        super(marca, modelo, puertos);
        this.puertos = puertos;
    }

    public SO getSistemaOperativo() {
        return sistemaOperativo;
    }

    @Override
    public void instalar(SO so) {
        this.sistemaOperativo = so;
    }

    // Este metodo es igual en todos los dispositivos.
    // Lo lleve a "DISPOSITVO". Seria correcto? Dejo de ser un metodo abstracto el conectar.
    @Override
    public void conectar(Dispositivo dispositivo) throws ExceededConectionException {
        super.conectar(dispositivo);
    }

    @Override
    public void desconectar(Dispositivo dispositivo) throws DeviceNotConnectedException {
    }

    @Override
    public void recibir(Paquete paquete) {
        this.getSistemaOperativo().procesar(paquete);
    }
}
