/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mef.erp.modelo.entidad;

/**
 *
 * @author Admin
 */
public class DatosEspeciales {
    private String expresion;
    private int posicion;
    private Object valor;
    private TipoNodoConsulta nodoConsulta;

    public DatosEspeciales(String expresion, int posicion, TipoNodoConsulta nodoConsulta) {
        this.expresion = expresion;
        this.posicion = posicion;
        this.nodoConsulta = nodoConsulta;
    }

    public String getExpresion() {
        return expresion;
    }

    public void setExpresion(String formula) {
        this.expresion = formula;
    }

    public int getPosicion() {
        return posicion;
    }

    public void setPosicion(int posicion) {
        this.posicion = posicion;
    }

    public Object getValor() {
        return valor;
    }

    public void setValor(Object valor) {
        this.valor = valor;
    }

    public TipoNodoConsulta getNodoConsulta() {
        return nodoConsulta;
    }

    public void setNodoConsulta(TipoNodoConsulta nodoConsulta) {
        this.nodoConsulta = nodoConsulta;
    }
}
