/**
 * @author: Ernesto Valenzuela Fecha de Creación: 04/10/2011 Compañía: MacroPro.
 * Descripción del programa: interface servicio turnos
 * -----------------------------------------------------------------------------
 * MODIFICACIONES:
 * -----------------------------------------------------------------------------
 * Clave:JSA01 Autor:Jose Armando Sanchez Acosta Fecha:10/11/2011 Descripción:Se
 * cambiaron todos los metodos hibernate al dao
 * -----------------------------------------------------------------------------
 * Clave: Autor: Fecha: Descripción:
 * -----------------------------------------------------------------------------
 */
package com.mef.erp.servicios;

import com.mef.erp.modelo.entidad.Mensaje;
import com.mef.erp.modelo.entidad.Turnos;

/**
 *
 * @author Ernesto
 */
public interface ServicioTurnosIF {

    /*Turnos*/
    Mensaje agregar(Turnos entity, String uuidCxn);//JSA01

    /*boolean*/
    Mensaje actualizar(Turnos entity, String uuidCxn);//JSA01

    /*boolean*/
    Mensaje eliminar(Turnos entity, String uuidCxn);//JSA01

    /*boolean*/
    public Mensaje UpdateTurnos(Turnos entity, Object[] eliminados, String uuidCxn);

    /*List<Turnos>*/
    Mensaje getTurnosAll(String claveRazonesSocial, String uuidCxn);//JSA01

    /*Turnos*/
    Mensaje getTurnosPorClave(String clave, String uuidCxn);//JSA01
}
