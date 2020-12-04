/**
 * @author: Victor Fecha de Creación: 23/07/2012 Compañía: Macropro. Descripción
 * del programa: clase CampoOrden usado para el ordenado de querys
 * -----------------------------------------------------------------------------
 * MODIFICACIONES:
 * -----------------------------------------------------------------------------
 * Clave:JSA01 Autor: Jose Armando Descripción: Se agregaron los datos
 * descontarAhorro, descontarPrestamos, soloPrestamo.
 * -----------------------------------------------------------------------------
 * Clave:JSA02 Autor: Jose Armando Fecha: 15/10/2014 Descripción: Se agregaron los campos
 * mascaras y tipoAccionMascara para agregar la mascara y redondear o truncar.
 * -----------------------------------------------------------------------------
 * Clave:JSA03 Autor: Jose Armando Fecha: 10/12/2014 Descripción: Se quito el campo List<TablaBase> tablasBaseSistema
 * para obtener la info mejor desde el servidor.
 * -----------------------------------------------------------------------------
 */
package com.mef.erp.modelo.entidad;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class ParametrosExtra implements Serializable {
    /* variables predefinidas usadas en casos del calculo nomina */

    private Date fechaCalculoFiniq;
    private Date fechaBajaFiniq;
    private List<Object> valoresExtras;
    private Date fechaCalculoSDI;
    private Date fechaInicioPeriodo;
    private Date fechaFinPeriodo;
    private Boolean descontarAhorro;//JSA01
    private Boolean descontarPrestamos;//JSA01
    private Boolean soloPrestamo;//JSA01
    private TipoAccionMascaras tipoAccionMascaras;//JSA02
    private String[] mascaraResultado;//JSA02

    public Date getFechaCalculoFiniq() {
        return fechaCalculoFiniq;
    }

    public List<Object> getValoresExtras() {
        return valoresExtras;
    }

    public void setValoresExtras(List<Object> valoresExtras) {
        this.valoresExtras = valoresExtras;
    }

    public void setFechaCalculoFiniq(Date fechaCalculoFiniq) {
        this.fechaCalculoFiniq = fechaCalculoFiniq;
    }

    public Date getFechaBajaFiniq() {
        return fechaBajaFiniq;
    }

    public void setFechaBajaFiniq(Date fechaBajaFiniq) {
        this.fechaBajaFiniq = fechaBajaFiniq;
    }

    public Date getFechaCalculoSDI() {
        return fechaCalculoSDI;
    }

    public void setFechaCalculoSDI(Date fechaCalculoSDI) {
        this.fechaCalculoSDI = fechaCalculoSDI;
    }

    public Date getFechaFinPeriodo() {
        return fechaFinPeriodo;
    }

    public void setFechaFinPeriodo(Date fechaFinPeriodo) {
        this.fechaFinPeriodo = fechaFinPeriodo;
    }

    public Date getFechaInicioPeriodo() {
        return fechaInicioPeriodo;
    }

    public void setFechaInicioPeriodo(Date fechaInicioPeriodo) {
        this.fechaInicioPeriodo = fechaInicioPeriodo;
    }

    public Boolean getDescontarAhorro() {
        return descontarAhorro;
    }

    public void setDescontarAhorro(Boolean descontarAhorro) {
        this.descontarAhorro = descontarAhorro;
    }

    public Boolean getDescontarPrestamos() {
        return descontarPrestamos;
    }

    public void setDescontarPrestamos(Boolean descontarPrestamos) {
        this.descontarPrestamos = descontarPrestamos;
    }

    public Boolean getSoloPrestamo() {
        return soloPrestamo;
    }

    public void setSoloPrestamo(Boolean soloPrestamo) {
        this.soloPrestamo = soloPrestamo;
    }

    public TipoAccionMascaras getTipoAccionMascaras() {
        return tipoAccionMascaras;
    }

    public void setTipoAccionMascaras(TipoAccionMascaras tipoAccionMascaras) {
        this.tipoAccionMascaras = tipoAccionMascaras;
    }

    public String[] getMascaraResultado() {
        return mascaraResultado;
    }

    public void setMascaraResultado(String[] mascaraResultado) {
        this.mascaraResultado = mascaraResultado;
    }

}
