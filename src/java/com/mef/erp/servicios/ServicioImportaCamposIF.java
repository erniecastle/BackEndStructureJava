/**
 * @author: Ernesto Castillo Fecha de Creación: 24/01/2014 Compañía: Exito
 * Software. Descripción del programa: interface servicio ImportaCampos
 * -----------------------------------------------------------------------------
 * MODIFICACIONES:
 * -----------------------------------------------------------------------------
 * Clave: Autor: Fecha: Descripción:
 * -----------------------------------------------------------------------------
 */
package com.mef.erp.servicios;

import com.mef.erp.modelo.entidad.ImportaCampos;
import com.mef.erp.modelo.entidad.Mensaje;

/**
 *
 * @author Ernesto
 */
public interface ServicioImportaCamposIF {

    /*ImportaCampos*/
    Mensaje agregar(ImportaCampos entity, String uuidCxn);

    /*boolean*/
    Mensaje actualizar(ImportaCampos entity, String uuidCxn);

    /*boolean*/
    Mensaje eliminar(ImportaCampos entity, String uuidCxn);

    /*List<ImportaCampos>*/
    Mensaje getImportaCamposAll(String uuidCxn);

    /*List<ImportaCampos>*/
    Mensaje getImportaCamposPorVentana(String nombreVentana, String uuidCxn);

    /*ImportaCampos*/
    Mensaje saveDeleteImportaCampos(ImportaCampos entity, Object[] eliminarDetalleImportCampos, String uuidCxn);
    
}
