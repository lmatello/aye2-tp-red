package com.caece.SO;

/**
 * Created by lmatello on 04/06/2018.
 *
 * Esta Interfaz se utiliza cuando se desea instalar un SO en un dispositvo que admite instalacion.
 * En nuestro caso del TP, aplicaria para Terminales y Routers.
 */
public interface IInstallable {

    void instalar(SO so);
}
