/**
 * @author: Victor Lopez Fecha de Creación: 26/09/2012 Compañía: Macropro.
 * Descripción del programa: EmpleadoMovimientoNomina es fuente para generar consultas
 * con informacion empleado, movimiento de nomina 
 * -----------------------------------------------------------------------------
 * Clave: JSA01 Autor: Jose Armando Fecha:24/11/2012 Descripción: se cambio de ubicacion: de fuentes consulta a Entidades.
 * -----------------------------------------------------------------------------
 */
package com.mef.erp.modelo.entidad;

import java.io.Serializable;

public class EmpleadoMovimientoNomina implements Serializable {
    private MovNomConcep movNomConcep;
    private PlazasPorEmpleadosMov plazasPorEmpleadosMov;
    
    public EmpleadoMovimientoNomina() {
    }

    public EmpleadoMovimientoNomina(MovNomConcep movNomConcep, PlazasPorEmpleadosMov plazasPorEmpleadosMov) {
        this.movNomConcep = movNomConcep;
        this.plazasPorEmpleadosMov = plazasPorEmpleadosMov;
    }

    public MovNomConcep getMovNomConcep() {
        return movNomConcep;
    }

    public void setMovNomConcep(MovNomConcep movNomConcep) {
        this.movNomConcep = movNomConcep;
    }

    public PlazasPorEmpleadosMov getPlazasPorEmpleadosMov() {
        return plazasPorEmpleadosMov;
    }

    public void setPlazasPorEmpleadosMov(PlazasPorEmpleadosMov plazasPorEmpleadosMov) {
        this.plazasPorEmpleadosMov = plazasPorEmpleadosMov;
    }
}
