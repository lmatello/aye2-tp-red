package com.caece.Dispositivo;

import com.caece.Excepciones.DeviceNotConnectedException;
import com.caece.Excepciones.ExceededConectionException;
import com.caece.Paquete.Paquete;

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

    public void conectar(Dispositivo dispositivo) throws ExceededConectionException {
        if (validarPuertosLibres(this) && validarPuertosLibres(dispositivo)){
            this.dispositivosConectados[getIndiceLibre(this)] = dispositivo;
            dispositivo.dispositivosConectados[getIndiceLibre(dispositivo)] = this;
        }
        else {
             throw new ExceededConectionException(dispositivo);
        }
    }

    private boolean validarPuertosLibres(Dispositivo dispositivo) throws ExceededConectionException {
        boolean puertoLibres = false;
        for (int i=0; i < dispositivo.dispositivosConectados.length; i++){
            if (dispositivo.getDispositivosConectados()[i] == null){
                puertoLibres = true;
            }
        }
        if (puertoLibres){
            return puertoLibres;
        }else {
            throw new ExceededConectionException(dispositivo);
        }
    }

    private int getIndiceLibre(Dispositivo dispositivo) {
        int indice = -1;
        for (int i=0; i < dispositivo.dispositivosConectados.length; i++){
            if (dispositivo.getDispositivosConectados()[i] == null){
                indice = i;
            }
        }
        return indice;
    }

    public abstract void desconectar(Dispositivo dispo) throws DeviceNotConnectedException;

    public abstract void recibir (Paquete paquete);

}
