/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mef.erp.modelo.entidad;

/**
 *
 * @author Admin
 */
public enum TipoIcono {

    ICONOEXTERNO(0),
    ICONOSISTEMA(1);
    private final int tipo;

    TipoIcono(int tipo) {
        this.tipo = tipo;
    }

    public int getTipoIcono() {
        return tipo;
    }
}
