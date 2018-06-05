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
    protected ArrayList<Dispositivo> dispositivosConectados;

    public Dispositivo() {
    }

    public Dispositivo(String marca, String modelo, int puertos) {
        this.marca = marca;
        this.modelo = modelo;
        this.puertos = puertos;
        this.dispositivosConectados = new ArrayList<>(); //Inicializo en vacio la lista de Dispositivos conectados
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

    public ArrayList<Dispositivo> getDispositivosConectados() {
        return dispositivosConectados;
    }

    public void setDispositivosConectados(ArrayList<Dispositivo> dispositivosConectados) {
        this.dispositivosConectados = dispositivosConectados;
    }

    //VER ESTE METODO
    public void conectar(Dispositivo dispositivo) throws ExceededConectionException {
        if (dispositivo.getDispositivosConectados().size() < dispositivo.puertos){
            dispositivo.getDispositivosConectados().add(this);
        } else {
            throw new ExceededConectionException(dispositivo);
        }
    }

    public abstract void desconectar(Dispositivo dispo) throws DeviceNotConnectedException;
}
