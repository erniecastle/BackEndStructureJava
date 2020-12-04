/**
 * @author: Victor Lopez
 * Fecha de Creación: 17/05/2016
 * Compañía: Macropro.
 * Descripción del programa: Interface para servicio VacacionesDisfrutadas
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
import com.mef.erp.modelo.entidad.VacacionesDisfrutadas;
import java.util.List;

public interface ServicioVacacionesDisfrutadasIF {

    /*VacacionesDisfrutadas*/
    Mensaje agregar(VacacionesDisfrutadas entity, String uuidCxn);

    /*boolean*/
    Mensaje actualizar(VacacionesDisfrutadas entity, String uuidCxn);

    /*boolean*/
    Mensaje eliminar(VacacionesDisfrutadas entity, String uuidCxn);

    /*List<VacacionesDisfrutadas>*/
    Mensaje getVacacionesDisfrutadasAll(String claveRazonesSocial, String uuidCxn);

    /*List<VacacionesDisfrutadas>*/
    Mensaje getVacacionesPorEmpleado(String claveEmpleado, String claveRazonSocial, String uuidCxn);

    /*List<VacacionesDisfrutadas>*/
    Mensaje saveDeleteVacacionesDisfrutadas(List<VacacionesDisfrutadas> entitysCambios, Object[] clavesDelete, int rango, String uuidCxn);
    
     /*VacacionesDisfrutadas*/
    Mensaje ObtenerVacacionesDisfruradasMaxima(String claveEmpleado,String claveRazonSocial, String uuidCxn);
    
    /*boolean*/
    Mensaje EliminarVacacionesDisfrutadas(List<VacacionesAplicacion> vacAplicacion, String uuidCxn);
}
