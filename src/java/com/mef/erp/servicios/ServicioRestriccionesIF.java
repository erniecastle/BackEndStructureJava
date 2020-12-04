/**
 * @author: Ernesto Castillo
 * Fecha de Creación: 30/06/2011
 * Compañía: Exito Software.
 * Descripción del programa: Interface para servicio Restricciones
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
import com.mef.erp.modelo.entidad.Restriccion;
import com.mef.erp.modelo.entidad.Usuario;
import java.util.List;

public interface ServicioRestriccionesIF {

    /*Restriccion*/
    Mensaje agregar(Restriccion entity, String uuidCxnMaestra);

    /*boolean*/
    Mensaje actualizar(Restriccion entity, String uuidCxnMaestra);

    /*boolean*/
    Mensaje eliminar(Restriccion entity, String uuidCxnMaestra);

    /*List<Restriccion>*/
    Mensaje getRestriccionesAll( String uuidCxnMaestra);

    /*Restriccion*/
    Mensaje getRestriccionesPorClave(String clave, String uuidCxnMaestra);

    /*List<Restriccion>*/
    public Mensaje agregarListaRestricciones(List<Restriccion> entitys, int rango, String uuidCxnMaestra);

    /*List<Restriccion>*/
    public Mensaje deleteListaRestricciones(List<Restriccion> entitys, Usuario user, int rango, String uuidCxnMaestra);
}
