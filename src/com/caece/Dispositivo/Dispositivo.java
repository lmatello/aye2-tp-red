package com.caece.Dispositivo;

import com.caece.Excepciones.DeviceNotConnectedException;
import com.caece.Excepciones.ExceededConectionException;

import java.util.ArrayList;

/**
 * Created by lmatello on 30/05/2018.
 */
public abstract class Dispositivo {

    protected String marca;
    protected String modelo;
    protected int puertos; //maximos dispositivos que se pueden conectar
    protected Dispositivo[] dispositivosConectados;

    public Dispositivo() {
    }

    public Dispositivo(String marca, String modelo, int puertos) {
        this.marca = marca;
        this.modelo = modelo;
        this.puertos = puertos;
        this.dispositivosConectados = new Dispositivo[puertos];
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public int getPuertos() {
        return puertos;
    }

    public void setPuertos(int puertos) {
        this.puertos = puertos;
    }

    public Dispositivo[] getDispositivosConectados() {
        return dispositivosConectados;
    }

    public void setDispositivosConectados(Dispositivo[] dispositivosConectados) {
        this.dispositivosConectados = dispositivosConectados;
    }

    //VER ESTE METODO
    public void conectar(Dispositivo dispositivo) throws ExceededConectionException {
        //if (dispositivo.getDispositivosConectados().size() < dispositivo.puertos){
            // validar que dispo actual tenga puertos libres
            // validar que dispo destino tenga puertos libres
            // obtengo puertos libres de ambos dispo y devuelo el indice
            // luego los coencto como abajo
        //    dispositivo.getDispositivosConectados().add(this);
        //} else {
         //   throw new ExceededConectionException(dispositivo);
       // }
    }

    public abstract void desconectar(Dispositivo dispo) throws DeviceNotConnectedException;
}
