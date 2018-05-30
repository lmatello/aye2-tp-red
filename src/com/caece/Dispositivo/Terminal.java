package com.caece.Dispositivo;

/**
 * Created by lmatello on 30/05/2018.
 */
public class Terminal extends Dispositivo {

    private String tipo; // Tablet, Notebook, Etc.
    private String so; // Win, Mac, Linux.

    public Terminal(String tipo, String so) {
        this.tipo = tipo;
        this.so = so;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getSo() {
        return so;
    }

    public void setSo(String so) {
        this.so = so;
    }
}
