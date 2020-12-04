/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mef.erp.modelo.entidad.cfdi;

import com.mef.erp.modelo.entidad.Asistencias;
import java.io.Serializable;

/**
 *
 * @author User
 */
public class DatosIncapacidades implements Serializable {
    private Asistencias asistencia;
    private Integer dias;

    public Asistencias getAsistencia() {
        return asistencia;
    }

    public void setAsistencia(Asistencias asistencia) {
        this.asistencia = asistencia;
    }

    public Integer getDias() {
        return dias;
    }

    public void setDias(Integer dias) {
        this.dias = dias;
    }
}
