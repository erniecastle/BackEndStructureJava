/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mef.erp.modelo.entidad;

/**
 *
 * @author Admin
 */
public enum TipoControlador {

    CONTROLPORFECHA("ControlPorFecha"), CONTROLPORAÑO("ControlPorAño"), CONTROLADORENTIDAD("ControlPorEntidad");
    private final String tipo;

    TipoControlador(String tipo) {
        this.tipo = tipo;
    }

    public String getTipoControlador() {
        return tipo;
    }

    public static TipoControlador getEnum(String tipoControlador) {
        if (CONTROLPORFECHA.getTipoControlador().equalsIgnoreCase(tipoControlador)) {
            return CONTROLPORFECHA;
        } else if (CONTROLPORAÑO.getTipoControlador().equalsIgnoreCase(tipoControlador)) {
            return CONTROLPORAÑO;
        } else if (CONTROLADORENTIDAD.getTipoControlador().equalsIgnoreCase(tipoControlador)) {
            return CONTROLADORENTIDAD;
        }
        throw new IllegalArgumentException("No Enum specified for this string");
    }
}
