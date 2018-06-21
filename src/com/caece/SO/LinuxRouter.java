package com.caece.SO;

import com.caece.Dispositivo.Router;
import com.caece.Excepciones.InvalidIPException;
import com.caece.IP;
import com.caece.Paquete.*;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by lmatello on 07/06/2018.
 */
public class LinuxRouter extends SO {

    private Map<Integer, IP> tablaRuteo;
    //private Map<Integer, Dispositivo> tablaRuteoDispositivo;

    public LinuxRouter(String nombre, String version) throws InvalidIPException {
        super(nombre, version);
        this.tablaRuteo = new HashMap<Integer, IP>();
        //this.tablaRuteoDispositivo = new HashMap<Integer, Dispositivo>();
    }

    public Map<Integer, IP> getTablaRuteo() {
        return tablaRuteo;
    }

    public void setTablaRuteo(Map<Integer, IP> tablaRuteo) {
        this.tablaRuteo = tablaRuteo;
    }

    @Override
    public void configurarInterfazDeRed(String ip) throws InvalidIPException {
        //TODO - No se implementa este metodo para terminal.
    }

    @Override
    public void configurarInterfazDeRed(Integer interfaz, String ip) throws InvalidIPException {
        this.tablaRuteo.put(interfaz,IP.stringToIP(ip));
    }

    public void enviar(Paquete paquete) {
        if (estaDestinoEnRouter(paquete.getDireccionDestino())) {
            this.getDispositivo().getDispositivosConectados()[obtenerInterfaz(paquete.getDireccionDestino())].recibir(paquete);
        } else {
            System.out.println("Router Interfaz" + obtenerInterfaz((paquete.getDireccionOrigen()) )
            + ": No puede alcanzar el destino " + paquete.getDireccionDestino());

        }
    }

    public void procesar(Paquete paquete) {

            if (paquete instanceof Ruteo) {
                paquete.setTimeToLive(paquete.getTimeToLive()-1);
                if (paquete.getTimeToLive() > 0)
                {
                    int i = 0;
                    while (i < getTablaRuteo().size() && !paquete.getDireccionDestino().iguales(this.getTablaRuteo().get(i))) {
                        i++;
                    } if (i < this.getTablaRuteo().size())
                        {
                        System.out.println("Router Interfaz" + obtenerInterfaz((paquete.getDireccionOrigen()))
                                + " recibio un paquete de Ruteo desde " + ((Ruteo) paquete).getPaqueteARutear().getDireccionOrigen());
                        tratarPaquete(((Ruteo) paquete).getPaqueteARutear());
                        } else tratarPaquete(paquete);
                } else
                    System.out.println("Router Interfaz" + obtenerInterfaz((paquete.getDireccionOrigen()))
                            + " descarta paquete de Ruteo por Timeout de " + ((Ruteo) paquete).getPaqueteARutear().getDireccionOrigen());
            } else if (paquete instanceof Servicio) {
                int i = 0;
                while (i < getTablaRuteo().size() && !paquete.getDireccionDestino().iguales(this.getTablaRuteo().get(i))) {
                    i++;
                }
                    if (i < this.getTablaRuteo().size()) {
                    System.out.println("Router Interfaz" + obtenerInterfaz((paquete.getDireccionOrigen()))
                            + " recibio un paquete de Servicio desde " + (paquete.getDireccionOrigen()));
                        tratarPaquete(paquete);
                    }
            }

    }


    public boolean estaEnRouter(IP ip){
        boolean encontrada = false;
        for (Map.Entry<Integer, IP> entry : tablaRuteo.entrySet()) {
            if (entry.getValue().iguales(ip)) {
                encontrada = true;
            }
        }
        return encontrada;
    }

    public boolean estaDestinoEnRouter(IP ip){
        boolean encontrada = false;
        for (Map.Entry<Integer, IP> entry : tablaRuteo.entrySet()) {
            if (entry.getValue().mismaRed(ip)) {
                encontrada = true;
            }
        }
        return encontrada;
    }

    public Integer obtenerInterfaz(IP ip){
        Integer interfaz = -1;
        for (Map.Entry<Integer, IP> entry : tablaRuteo.entrySet()) {
            if (entry.getValue().mismaRed(ip)) {
                interfaz = entry.getKey();
            }
        }
        return interfaz;
    }

    @Override
    public void tratarPaquete(Paquete paquete) {
        if (paquete instanceof Ruteo) {
            System.out.println("Redireccionando paquete por Interfaz" + obtenerInterfaz(paquete.getDireccionDestino()));
            this.getDispositivo().getDispositivosConectados()[obtenerInterfaz(paquete.getDireccionDestino())].recibir(((Ruteo) paquete).getPaqueteARutear());
        } else if (paquete instanceof Servicio) {
            if (paquete instanceof Who) {
                System.out.println("Router Interfaz" + obtenerInterfaz(paquete.getDireccionDestino()) +
                        " recibe solicitud WHO de: " + paquete.getDireccionOrigen().toString());
                Paquete sendMessage = new SendMessage(paquete.getDireccionDestino(), paquete.getDireccionOrigen(), 10, this);
                this.enviar(sendMessage);
            }

            if (paquete instanceof ICMPRequest) {
                System.out.println("Router Interfaz" + obtenerInterfaz(paquete.getDireccionDestino()) +
                        " recibe ICMPRequest de: " + paquete.getDireccionOrigen().toString());
                Paquete icmpResponse = new ICMPResponse(paquete.getDireccionDestino(), paquete.getDireccionOrigen(), 10);
                this.enviar(icmpResponse);
            } else if (paquete instanceof ICMPResponse) {
                System.out.println("Router Interfaz" + obtenerInterfaz(paquete.getDireccionDestino()) +
                        " recibe ICMPResponse de: " + paquete.getDireccionOrigen().toString());

            }
            }

        }


    @Override
    public void verDatos() {
        System.out.println("SO: " + super.getNombre());
        System.out.println("Version SO: " + super.getVersion());
        System.out.println("Tabla de Ruteo");
        Iterator it = tablaRuteo.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry e = (Map.Entry) it.next();
            System.out.println("Interfaz" + e.getKey() + ": " + e.getValue());
        }
    }

    @Override
    public void ping(String ip) throws InvalidIPException {
        IP ipDestino = IP.stringToIP(ip);
        if (estaDestinoEnRouter(ipDestino))
        {
            System.out.println("Router Interfaz" + obtenerInterfaz(ipDestino) +
                    " haciendo ping a " + ip);

            Paquete icmpRequest = new ICMPRequest(this.tablaRuteo.get(obtenerInterfaz(ipDestino)),ipDestino,10);
            this.enviar(icmpRequest);
        }
    }

    @Override
    public void who(String ip) throws InvalidIPException {
        System.out.println("Operacion inválida. No se puede solicitar  WHO desde un Router");
    }
}
