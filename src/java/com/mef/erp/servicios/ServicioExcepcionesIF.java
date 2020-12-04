/**
 * @author: Ernesto Castillo Fecha de Creación: 07/12/2011 Compañía: Exito
 * Software. Descripción del programa: Interface para servicio Excepciones
 * -----------------------------------------------------------------------------
 * MODIFICACIONES:
 * -----------------------------------------------------------------------------
 * Clave: Autor: Fecha: Descripción:
 * -----------------------------------------------------------------------------
 * Clave: Autor: Fecha: Descripción:
 * -----------------------------------------------------------------------------
 */
package com.mef.erp.servicios;

import com.mef.erp.modelo.entidad.Excepciones;
import com.mef.erp.modelo.entidad.Mensaje;
import java.util.List;

public interface ServicioExcepcionesIF {

    /*Excepciones*/
    Mensaje agregar(Excepciones entity, String uuidCxn);

    /*boolean*/
    public Mensaje agregarExcepciones(List<Excepciones> entitysCambios, String uuidCxn);

    /*List<Excepciones>*/
    Mensaje getExcepcionesAll(String uuidCxn);

   
}
