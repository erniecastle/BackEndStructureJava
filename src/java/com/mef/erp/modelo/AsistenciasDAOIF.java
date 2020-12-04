/**
 * @author: Ernesto Castillo
 * Fecha de Creación: 01/12/2011
 * Compañía: Exito Software.
 * Descripción del programa: interface de clase de asistencias para llamados a metodos de HIBERNATE
 * -----------------------------------------------------------------------------
 * MODIFICACIONES:
 * -----------------------------------------------------------------------------
 * Clave:
 * Autor:
 * Fecha:
 * Descripción:
 * -----------------------------------------------------------------------------
 * Clave:
 * Autor:
 * Fecha:
 * Descripción:
 * -----------------------------------------------------------------------------
 */
package com.mef.erp.modelo;

import com.mef.erp.modelo.entidad.Asistencias;
import com.mef.erp.modelo.entidad.DetalleAsistencia;
import com.mef.erp.modelo.entidad.Mensaje;
import com.mef.erp.modelo.entidad.RegistroIncapacidad;
import java.util.Date;
import java.util.List;

public interface AsistenciasDAOIF extends GenericDAO<Object, Long> {

    Mensaje getAsistenciasAll(String claveRazonesSociales, String uuidCxn);

    Mensaje saveDeleteAsistencias(List<Asistencias> AgreModif, List<Asistencias> Ordenados, Object[] clavesDelete,
            List<DetalleAsistencia> AgreModifDet, Object[] clavesDeleteDet, List<RegistroIncapacidad> incapacidades, Object[] clavesDeleteIncapacidades, String uuidCxn);

    Mensaje getAsistenciasPorRangoFechas(String claveEmpleado, Date fechaInicio, Date fechaFinal,String claveRazonesSociales, String uuidCxn);
}
