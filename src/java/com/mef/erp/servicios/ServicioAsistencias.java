/**
 * @author: Ernesto Castillo Fecha de Creación: 01/12/2011 Compañía: Exito
 * Software. Descripción del programa: clase para servicio Asistencias
 * -----------------------------------------------------------------------------
 * MODIFICACIONES:
 * -----------------------------------------------------------------------------
 * Clave: Autor: Fecha: Descripción:
 * -----------------------------------------------------------------------------
 * Clave: Autor: Fecha: Descripción:
 * -----------------------------------------------------------------------------
 */
package com.mef.erp.servicios;

import com.mef.erp.modelo.AsistenciasDAO;
import com.mef.erp.modelo.entidad.Asistencias;
import com.mef.erp.modelo.entidad.DetalleAsistencia;
import com.mef.erp.modelo.entidad.Mensaje;
import com.mef.erp.modelo.entidad.RegistroIncapacidad;
import java.util.Date;
import java.util.List;

public class ServicioAsistencias implements ServicioAsistenciasIF {

    private AsistenciasDAO asistenciasDAO;

    public Mensaje getAsistenciasAll(String claveRazonesSociales, String uuidCxn) {
        return getAsistenciasDAO().getAsistenciasAll(claveRazonesSociales, uuidCxn);
    }

    public Mensaje saveDeleteAsistencias(List<Asistencias> AgreModif, List<Asistencias> Ordenados, Object[] clavesDelete, List<DetalleAsistencia> AgreModifDet, Object[] clavesDeleteDet, List<RegistroIncapacidad> incapacidades, Object[] clavesDeleteIncapacidades, String uuidCxn) {
        return getAsistenciasDAO().saveDeleteAsistencias(AgreModif, Ordenados, clavesDelete, AgreModifDet, clavesDeleteDet, incapacidades, clavesDeleteIncapacidades, uuidCxn);
    }

    public Mensaje getAsistenciasPorRangoFechas(String claveEmpleado, Date fechaInicio, Date fechaFinal, String claveRazonesSociales, String uuidCxn) {
        return getAsistenciasDAO().getAsistenciasPorRangoFechas(claveEmpleado, fechaInicio, fechaFinal, claveRazonesSociales, uuidCxn);
    }

    public AsistenciasDAO getAsistenciasDAO() {
        return asistenciasDAO;
    }

    public void setAsistenciasDAO(AsistenciasDAO asistenciasDAO) {
        this.asistenciasDAO = asistenciasDAO;
    }
}
