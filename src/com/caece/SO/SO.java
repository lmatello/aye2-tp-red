package com.caece.SO;

import com.caece.Dispositivo.Dispositivo;
import com.caece.Excepciones.InvalidIPException;
import com.caece.IP;
import com.caece.Paquete.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by lmatello on 04/06/2018.
 */
public abstract class SO implements ComandosIP {

    private String nombre;
    private String version;
    private Dispositivo dispositivo;

    public SO(String nombre, String version) throws InvalidIPException {
        this.nombre = nombre;
        this.version = version;
    }

    public abstract void configurarInterfazDeRed(String ip) throws InvalidIPException;

    public abstract void configurarInterfazDeRed(Integer interfaz, String ip) throws InvalidIPException;

    public Dispositivo getDispositivo() {
        return dispositivo;

    }

    public void setDispositivo(Dispositivo dispositivo) {
        this.dispositivo = dispositivo;
    }

    public abstract void enviar(Paquete paquete);

    public abstract void procesar(Paquete paquete);

    public abstract void tratarPaquete(Paquete paquete);
}
