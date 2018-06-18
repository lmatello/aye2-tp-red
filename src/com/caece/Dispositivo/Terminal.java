package com.caece.Dispositivo;

import com.caece.Excepciones.DeviceNotConnectedException;
import com.caece.Excepciones.ExceededConectionException;
import com.caece.Excepciones.InvalidIPException;
import com.caece.IP;
import com.caece.Paquete.Paquete;
import com.caece.SO.IInstallable;
import com.caece.SO.SO;

import java.util.ArrayList;

/**
 * Created by lmatello on 30/05/2018.
 */
public class Terminal extends Dispositivo implements IInstallable{

    private String tipo; // Tablet, Notebook, Etc.
    private SO sistemaOperativo;

    public Terminal(String marca, String modelo, String tipo) {
        super(marca,modelo,1);
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


    @Override
    public void instalar(SO so) {
        this.sistemaOperativo = so;
        sistemaOperativo.setDispositivo(this);
    }

    @Override
    public void conectar(Dispositivo dispositivo) throws ExceededConectionException {
        super.conectar(dispositivo);
    }

    @Override
    public void desconectar(Dispositivo dispositivo) throws DeviceNotConnectedException {
    }

    public void recibir(Paquete paquete) {

        //REVISAR LA IP QUE SE LE MANDA EL IGUAL.
        if (paquete.getDireccionDestino().iguales(this.getSistemaOperativo().getTablaRuteo().get(0))){
            this.getSistemaOperativo().procesar(paquete);
        }
    }
}
