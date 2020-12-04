/**
 * @author: Ernesto Valenzuela Compañía: Exito. Descripción del programa: clase
 * Turnos para llamados a metodos de HIBERNATE
 * -----------------------------------------------------------------------------
 * MODIFICACIONES:
 * -----------------------------------------------------------------------------
 * Clave:JSA01 Autor:Jose Armando Sanchez Acosta Fecha:10/11/2011 Descripción:Se
 * cambiaron todos los metodos hibernate al dao
 * -----------------------------------------------------------------------------
 * Clave: Autor: Fecha: Descripción:
 * -----------------------------------------------------------------------------
 */
package com.mef.erp.modelo;

import com.mef.erp.modelo.entidad.Mensaje;
import com.mef.erp.modelo.entidad.Turnos;

/**
 *
 * @author Ernesto
 */
public interface TurnosDAOIF {

    Mensaje agregar(Turnos entity, String uuidCxn);//JSA01

    Mensaje actualizar(Turnos entity, String uuidCxn);//JSA01

    Mensaje eliminar(Turnos entity, String uuidCxn);//JSA01

    public Mensaje UpdateTurnos(Turnos entity, Object[] eliminados, String uuidCxn);

    Mensaje getTurnosAll(String claveRazonesSocial, String uuidCxn);//JSA01

    Mensaje getTurnosPorClave(String clave, String uuidCxn);//JSA01
}
