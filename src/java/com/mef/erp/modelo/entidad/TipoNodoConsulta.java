/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mef.erp.modelo.entidad;


/**
 *
 * @author Admin
 */
public enum TipoNodoConsulta {

    CAMPO, ENTIDAD, FORMULA, CAMPOESPECIAL, CLASIFICADOR, DATOCALCULO, DATOFUNCION, DATOASIGNADO, DATOTABLA, DATOREMPLAZO, DATOCALCULOSINPARAMETRO, DATOXML, ETIQUETA,
    DATOIMSS, TABLAFACTORINTEGRACION, TABLAZONASALARIAL, DATOPARAMETRO, DATOPERIODO, DATOMENSUAL, DATOBIMESTRAL, DATOANUAL, SINCLASIFICAR;

    public static TipoNodoConsulta getEnum(String tipoNodoConsulta) {
        tipoNodoConsulta = tipoNodoConsulta.toUpperCase();
        if (CAMPO.name().equalsIgnoreCase(tipoNodoConsulta)) {
            return CAMPO;
        } else if (CAMPOESPECIAL.name().equalsIgnoreCase(tipoNodoConsulta)) {
            return CAMPOESPECIAL;
        } else if (CLASIFICADOR.name().equalsIgnoreCase(tipoNodoConsulta)) {
            return CLASIFICADOR;
        } else if (DATOCALCULO.name().equalsIgnoreCase(tipoNodoConsulta)) {
            return DATOCALCULO;
        } else if (DATOCALCULOSINPARAMETRO.name().equalsIgnoreCase(tipoNodoConsulta)) {
            return DATOCALCULOSINPARAMETRO;
        } else if (DATOREMPLAZO.name().equalsIgnoreCase(tipoNodoConsulta)) {
            return DATOREMPLAZO;
        } else if (DATOXML.name().equalsIgnoreCase(tipoNodoConsulta)) {
            return DATOXML;
        } else if (ENTIDAD.name().equalsIgnoreCase(tipoNodoConsulta)) {
            return ENTIDAD;
        } else if (FORMULA.name().equalsIgnoreCase(tipoNodoConsulta)) {
            return FORMULA;
        } else if (DATOFUNCION.name().equalsIgnoreCase(tipoNodoConsulta)) {
            return DATOFUNCION;
        } else if (DATOASIGNADO.name().equalsIgnoreCase(tipoNodoConsulta)) {
            return DATOASIGNADO;
        } else if (DATOTABLA.name().equalsIgnoreCase(tipoNodoConsulta)) {
            return DATOTABLA;
        } else if (ETIQUETA.name().equalsIgnoreCase(tipoNodoConsulta)) {
            return ETIQUETA;
        } else if (DATOIMSS.name().equalsIgnoreCase(tipoNodoConsulta)) {
            return DATOIMSS;
        } else if (TABLAFACTORINTEGRACION.name().equalsIgnoreCase(tipoNodoConsulta)) {
            return TABLAFACTORINTEGRACION;
        } else if (TABLAZONASALARIAL.name().equalsIgnoreCase(tipoNodoConsulta)) {
            return TABLAZONASALARIAL;
        } else if (DATOPARAMETRO.name().equalsIgnoreCase(tipoNodoConsulta)) {
            return DATOPARAMETRO;
        } else if (DATOMENSUAL.name().equalsIgnoreCase(tipoNodoConsulta)) {
            return DATOMENSUAL;
        } else if (DATOBIMESTRAL.name().equalsIgnoreCase(tipoNodoConsulta)) {
            return DATOBIMESTRAL;
        } else if (DATOANUAL.name().equalsIgnoreCase(tipoNodoConsulta)) {
            return DATOANUAL;
        } else if (DATOPERIODO.name().equalsIgnoreCase(tipoNodoConsulta)) {
            return DATOPERIODO;
        }  
        return TipoNodoConsulta.CLASIFICADOR;
    }
}
