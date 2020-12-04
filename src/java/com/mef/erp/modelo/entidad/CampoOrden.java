/**
 * @author: Victor Fecha de Creación: 23/07/2012 Compañía: Macropro. Descripción
 * del programa: clase CampoOrden usado para el ordenado de querys
 * -----------------------------------------------------------------------------
 * MODIFICACIONES:
 * -----------------------------------------------------------------------------
 * Clave: Descripción:
 * -----------------------------------------------------------------------------
 */
package com.mef.erp.modelo.entidad;

import java.io.Serializable;

public class CampoOrden implements Serializable {
    /*camposOrden y tipoDatoOrden son los datos a ordenar y de que tipo son
     usar solo numericos o string por el momento
     */

    private Boolean[] ascendente;
    private String[] camposOrden;
    private Class[] tipoDatoOrden;

    public Boolean[] getAscendente() {
        return ascendente;
    }

    public void setAscendente(Boolean[] ascendente) {
        this.ascendente = ascendente;
    }

    public String[] getCamposOrden() {
        return camposOrden;
    }

    public void setCamposOrden(String[] camposOrden) {
        this.camposOrden = camposOrden;
    }

    public Class[] getTipoDatoOrden() {
        return tipoDatoOrden;
    }

    public void setTipoDatoOrden(Class[] tipoDatoOrden) {
        this.tipoDatoOrden = tipoDatoOrden;
    }
}
