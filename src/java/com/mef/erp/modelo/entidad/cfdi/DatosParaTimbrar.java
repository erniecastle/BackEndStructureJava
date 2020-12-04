/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mef.erp.modelo.entidad.cfdi;

import com.mef.erp.modelo.entidad.MovNomConcep;
import java.io.Serializable;
import java.util.List;

/**
 *
 * @author User
 */
public class DatosParaTimbrar implements Serializable {

    private DatosPorEmpleado datosPorEmpleado;
    private List<MovNomConcep> movimientos;
    private List<DatosHorasExtras> datosHorasExtras;
    private List<DatosIncapacidades> datosIncapacidades;

    public DatosPorEmpleado getDatosPorEmpleado() {
        return datosPorEmpleado;
    }

    public void setDatosPorEmpleado(DatosPorEmpleado datosPorEmpleado) {
        this.datosPorEmpleado = datosPorEmpleado;
    }
    
    public List<MovNomConcep> getMovimientos() {
        return movimientos;
    }

    public void setMovimientos(List<MovNomConcep> movimientos) {
        this.movimientos = movimientos;
    }

    public List<DatosHorasExtras> getDatosHorasExtras() {
        return datosHorasExtras;
    }

    public void setDatosHorasExtras(List<DatosHorasExtras> datosHorasExtras) {
        this.datosHorasExtras = datosHorasExtras;
    }

    public List<DatosIncapacidades> getDatosIncapacidades() {
        return datosIncapacidades;
    }

    public void setDatosIncapacidades(List<DatosIncapacidades> datosIncapacidades) {
        this.datosIncapacidades = datosIncapacidades;
    }

}
