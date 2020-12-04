/**
 * @author: Ernesto castillo Fecha de Creación: 23/01/2014 Compañía: Exito
 * Software. Descripción del programa: inteface de clase de ciudades para
 * llamados a metodos de HIBERNATE
 * -----------------------------------------------------------------------------
 * MODIFICACIONES:
 * -----------------------------------------------------------------------------
 * Clave: Autor: Fecha: Descripción:
 * -----------------------------------------------------------------------------
 * Clave: Autor: Fecha: Descripción:
 * -----------------------------------------------------------------------------
 */
package com.mef.erp.modelo;

import com.mef.erp.modelo.entidad.ImportaCampos;
import com.mef.erp.modelo.entidad.Mensaje;

/**
 *
 * @author Ernesto
 */
public interface ImportaCamposDAOIF extends GenericDAO<ImportaCampos, String> {

    Mensaje agregar(ImportaCampos entity, String uuidCxn);

    Mensaje actualizar(ImportaCampos entity, String uuidCxn);

    Mensaje eliminar(ImportaCampos entity, String uuidCxn);

    Mensaje getImportaCamposAll(String uuidCxn);
    
    Mensaje getImportaCamposPorVentana(String nombreVentana, String uuidCxn);

    Mensaje saveDeleteImportaCampos(ImportaCampos entity, Object[] eliminarDetalleImportCampos, String uuidCxn);

}
