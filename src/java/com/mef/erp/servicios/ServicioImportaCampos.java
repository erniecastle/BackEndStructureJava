/**
 * @author: Ernesto castillo Fecha de Creación: 24/01/2014 Compañía: Exito
 * Software. Descripción del programa: Interface de clase de ImportaCampos para
 * llamados a metodos de HIBERNATE
 * -----------------------------------------------------------------------------
 * MODIFICACIONES:
 * -----------------------------------------------------------------------------
 * Clave: Autor: Fecha: Descripción:
 * -----------------------------------------------------------------------------
 * Clave: Autor: Fecha: Descripción:
 * -----------------------------------------------------------------------------
 */
package com.mef.erp.servicios;

import com.mef.erp.modelo.ImportaCamposDAO;
import com.mef.erp.modelo.entidad.ImportaCampos;
import com.mef.erp.modelo.entidad.Mensaje;

/**
 *
 * @author Ernesto
 */
public class ServicioImportaCampos implements ServicioImportaCamposIF {

    private ImportaCamposDAO importaCamposDAO;

    public Mensaje agregar(ImportaCampos entity, String uuidCxn) {

        return importaCamposDAO.agregar(entity, uuidCxn);
    }

    public Mensaje actualizar(ImportaCampos entity, String uuidCxn) {

        return importaCamposDAO.actualizar(entity, uuidCxn);
    }

    public Mensaje eliminar(ImportaCampos entity, String uuidCxn) {

        return importaCamposDAO.eliminar(entity, uuidCxn);
    }

    public Mensaje getImportaCamposAll(String uuidCxn) {

        return importaCamposDAO.getImportaCamposAll(uuidCxn);
    }

    public Mensaje getImportaCamposPorVentana(String nombreVentana, String uuidCxn) {

        return importaCamposDAO.getImportaCamposPorVentana(nombreVentana, uuidCxn);
    }

    public Mensaje saveDeleteImportaCampos(ImportaCampos entity, Object[] eliminarDetalleImportCampos, String uuidCxn) {

        return importaCamposDAO.saveDeleteImportaCampos(entity, eliminarDetalleImportCampos, uuidCxn);
    }

    public ImportaCamposDAO getImportaCamposDAO() {
        return importaCamposDAO;
    }

    public void setImportaCamposDAO(ImportaCamposDAO importaCamposDAO) {
        this.importaCamposDAO = importaCamposDAO;
    }

}
