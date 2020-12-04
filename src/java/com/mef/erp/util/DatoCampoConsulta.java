/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mef.erp.util;

import com.mef.erp.modelo.entidad.TipoNodoConsulta;

/**
 *
 * @author Admin
 */
public class DatoCampoConsulta {

    private String campo;
    private TipoNodoConsulta nodoConsulta;
    private Object valor;

    public DatoCampoConsulta(String campo, TipoNodoConsulta nodoConsulta, Object valor) {
        this.campo = campo;
        this.nodoConsulta = nodoConsulta;
        this.valor = valor;
    }

    public String getCampo() {
        return campo;
    }

    public void setCampo(String campo) {
        this.campo = campo;
    }

    public TipoNodoConsulta getNodoConsulta() {
        return nodoConsulta;
    }

    public void setNodoConsulta(TipoNodoConsulta nodoConsulta) {
        this.nodoConsulta = nodoConsulta;
    }

    public Object getValor() {
        return valor;
    }

    public void setValor(Object valor) {
        this.valor = valor;
    }
}
