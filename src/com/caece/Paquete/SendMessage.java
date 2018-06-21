package com.caece.Paquete;

import com.caece.IP;
import com.caece.SO.SO;

public class SendMessage extends Paquete{

    private SO sistemaOperativo;

    public SendMessage(IP direccionOrigen, IP direccionDestino, Integer timeToLive, SO sistemaOperativo) {
        super(direccionOrigen, direccionDestino, timeToLive);
        this.sistemaOperativo = sistemaOperativo;
    }

    public SO getSistemaOperativo() {
        return sistemaOperativo;
    }

    public void setSistemaOperativo(SO sistemaOperativo) {
        this.sistemaOperativo = sistemaOperativo;
    }
}
