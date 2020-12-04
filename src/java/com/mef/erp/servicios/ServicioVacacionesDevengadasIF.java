/**
 * @author: Ernesto Valenzuela Fecha de Creación: 16/05/2016 Compañía: Exito
 * Software. Descripción del programa: Interface para Servicio de vacaciones
 * devengadas
 * -----------------------------------------------------------------------------
 * MODIFICACIONES:
 * -----------------------------------------------------------------------------
 * Clave: Autor: Fecha: Descripción:
 * -----------------------------------------------------------------------------
 */
package com.mef.erp.servicios;

import com.mef.erp.modelo.entidad.Mensaje;
import com.mef.erp.modelo.entidad.RazonesSociales;
import com.mef.erp.modelo.entidad.VacacionesDevengadas;
import java.util.List;

/**
 *
 * @author Ernesto
 */
public interface ServicioVacacionesDevengadasIF {

    /*List<VacacionesDevengadas>*/
    public Mensaje getVacacionesDevengadasAll(String uuidCxn);

    /*Object*/
    public Mensaje calcularVacacionesDevengadasEmpleados(RazonesSociales razonesSociales, String uuidCxn, String uuidCxnMaestra);

    /*List<VacacionesDevengadas>*/
    Mensaje getVacacionesDevengadasPorEmpleado(String claveEmpleado, String claveRazonSocial, String uuidCxn);
    /*List<VacacionesDevengadas>*/

    Mensaje saveDeleteVacacionesDevengadas(List<VacacionesDevengadas> entitysCambios,  int rango, String uuidCxn);
}
