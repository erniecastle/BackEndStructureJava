/**
 * @author: Victor Lopez Fecha de Creación: 16/12/2011 Compañía: Macropro.
 * Descripción del programa: Interface para servicio RegistroIncapacidad
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
import com.mef.erp.modelo.entidad.Empleados;
import com.mef.erp.modelo.entidad.Mensaje;
import com.mef.erp.modelo.entidad.RazonesSociales;
import com.mef.erp.modelo.entidad.RegistroIncapacidad;
import java.util.Date;
import java.util.List;

public interface ServicioRegistroIncapacidadIF {

    /*RegistroIncapacidad*/
    Mensaje agregar(RegistroIncapacidad entity, String uuidCxn);

    /*boolean*/
    Mensaje actualizar(RegistroIncapacidad entity, String uuidCxn);

    /*boolean*/
    Mensaje eliminar(RegistroIncapacidad entity, String uuidCxn);

    /*List<RegistroIncapacidad>*/
    Mensaje getRegistroIncapacidadAll(String claveRazonesSocial, String uuidCxn);

    /*RegistroIncapacidad*/
    Mensaje getRegistroIncapacidadPorClave(String clave, String claveRazonesSocial, String uuidCxn);

    /*RegistroIncapacidad*/
    Mensaje getRegistroIncapacidadPorClaveYRazon(String clave, String claveRazon, String uuidCxn);

    /*List<RegistroIncapacidad>*/
    Mensaje getRegistroIncapacidadPorEmpleado(Empleados empleado, String uuidCxn);

    /*List<RegistroIncapacidad>*/
    Mensaje getIncapacidadPorEmpleadoYFecha(String claveEmpleado, Date fechaInicial, Date fechaFinal, String uuidCxn);

    /*List<RegistroIncapacidad>*/
    Mensaje consultaPorRangos(Integer inicio, Integer rango, String[] camposWhere, Object[] camposWhereValores, String uuidCxn);

    /*List<RegistroIncapacidad>*/
    Mensaje consultaPorFiltros(String query, Object[] campos, Object[] valores, String uuidCxn);

    /*Boolean*/
    Mensaje existeDato(String campo, Object valor, String uuidCxn);

    /*RegistroIncapacidad*/
    Mensaje saveDeleteRegistroIncapacidad(RegistroIncapacidad incapacidad, Object[] clavesDeleteIncapacidad, int rango, Empleados empleados, RazonesSociales razonesSociales, Date fechaInicial, Date fechaFinal, Date fechaInicialAnterior, Date fechaFinalAnterior, Object claveExcepcion, String formatoFecha, Date fechaInicEmpalme, Date fechaFinEmpalme, String uuidCxn, String uuidCxnMaestra);
}
