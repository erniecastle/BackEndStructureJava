/**
 * @author: Victor Lopez
 * Fecha de Creación: 17/05/2016
 * Compañía: Macropro.
 * Descripción del programa: Interface para servicio VacacionesAplicacion
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
package com.mef.erp.servicios;

import com.mef.erp.modelo.entidad.Mensaje;
import com.mef.erp.modelo.entidad.VacacionesAplicacion;
import java.util.List;

public interface ServicioVacacionesAplicacionIF {

    /*VacacionesAplicacion*/
    Mensaje agregar(VacacionesAplicacion entity, String uuidCxn);

    /*boolean*/
    Mensaje actualizar(VacacionesAplicacion entity, String uuidCxn);

    /*boolean*/
    Mensaje eliminar(VacacionesAplicacion entity, String uuidCxn);
    
    /*List<VacacionesAplicacion>*/
    Mensaje getVacacionesAplicacionAll(String claveRazonesSocial, String uuidCxn);
    
    /*List<VacacionesAplicacion>*/
    Mensaje getVacacionesPorEmpleado(String claveEmpleado, String claveRazonSocial, String uuidCxn);

    /*List<VacacionesAplicacion>*/
    Mensaje saveDeleteVacacionesAplicacion(List<VacacionesAplicacion> entitysCambios, Object[] clavesDelete, int rango, String uuidCxn);
}
