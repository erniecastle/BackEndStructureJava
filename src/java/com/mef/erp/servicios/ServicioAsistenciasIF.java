/**
 * @author: Ernesto Castillo Fecha de Creación: 07/12/2011 Compañía: Exito
 * Software. Descripción del programa: Interface para servicio Asistencias
 * -----------------------------------------------------------------------------
 * MODIFICACIONES:
 * -----------------------------------------------------------------------------
 * Clave: Autor: Fecha: Descripción:
 * -----------------------------------------------------------------------------
 * Clave: Autor: Fecha: Descripción:
 * -----------------------------------------------------------------------------
 */
package com.mef.erp.servicios;

import com.mef.erp.modelo.entidad.Asistencias;
import com.mef.erp.modelo.entidad.DetalleAsistencia;
import com.mef.erp.modelo.entidad.Mensaje;
import com.mef.erp.modelo.entidad.RegistroIncapacidad;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Desarrollo 18
 */
public interface ServicioAsistenciasIF {

    /*List<Asistencias>*/
    Mensaje getAsistenciasAll(String claveRazonesSociales, String uuidCxn);

	/*Asistencias*/
    Mensaje saveDeleteAsistencias(List<Asistencias> AgreModif, List<Asistencias> Ordenados,
            Object[] clavesDelete, List<DetalleAsistencia> AgreModifDet, Object[] clavesDeleteDet, List<RegistroIncapacidad> incapacidades, Object[] clavesDeleteIncapacidades, String uuidCxn);

    /*List<Asistencias>*/
    Mensaje getAsistenciasPorRangoFechas(String claveEmpleado, Date fechaInicio, Date fechaFinal, String claveRazonesSociales, String uuidCxn);

}
