/**
 * @author: Victor Fecha de Creaci�n: 06/06/2011 Compa��a: Macropro Descripci�n
 * del programa: clase TablaBase para llamados a metodos de HIBERNATE
 * -----------------------------------------------------------------------------
 * MODIFICACIONES:
 * -----------------------------------------------------------------------------
 * Clave: JSA01 Autor: Jose Armando Sanchez Acosta Fecha: 08-07-2011
 * Descripci�n: Se aplicaron PDM.
 * -----------------------------------------------------------------------------
 * Clave:JSA01 Autor:Jose Armando Sanchez Acosta Fecha:10/11/2011 Descripci�n:Se
 * cambiaron todos los metodos hibernate al dao
 * -----------------------------------------------------------------------------
 * Clave:JSA03 Autor:Jose Armando Sanchez Acosta Fecha:09/05/2013 Descripci�n:se
 * cambio el dato que regresa getTablaBasePorTipoTabla
 * -----------------------------------------------------------------------------
 */
package com.mef.erp.servicios;

import com.mef.erp.modelo.TablaBaseDAO;
import com.mef.erp.modelo.entidad.Mensaje;
import com.mef.erp.modelo.entidad.TablaBase;
import com.mef.erp.modelo.entidad.TipoTabla;

/**
 *
 * @author Jose Armando
 */
public class ServicioTablaBase implements ServicioTablaBaseIF {

    private TablaBaseDAO tablaBaseDAO;

    public Mensaje getTablaBaseAll(String uuidCxnMaestra) {
        return tablaBaseDAO.getTablaBaseAll(uuidCxnMaestra);
    }

    public Mensaje getTablaBasePorClave(String clave, String uuidCxnMaestra) {
        return tablaBaseDAO.getTablaBasePorClave(clave, uuidCxnMaestra);
    }

    public Mensaje agregar(TablaBase entity, String uuidCxnMaestra) {
        return tablaBaseDAO.agregar(entity, uuidCxnMaestra);
    }

    public Mensaje actualizar(TablaBase entity, String uuidCxnMaestra) {
        return tablaBaseDAO.actualizar(entity, uuidCxnMaestra);
    }

    public Mensaje eliminar(TablaBase tablaGenerada, String uuidCxnMaestra) {
        return tablaBaseDAO.eliminar(tablaGenerada, uuidCxnMaestra);
    }

    public Mensaje getTablaBasePorTipoTabla(TipoTabla tipoTabla, String uuidCxnMaestra) {//JSA03
        return tablaBaseDAO.getTablaBasePorTipoTabla(tipoTabla, uuidCxnMaestra);
    }

    public Mensaje getTablaBaseSistema(String uuidCxnMaestra) {
        return tablaBaseDAO.getTablaBaseSistema(uuidCxnMaestra);
    }

    public TablaBaseDAO getTablaBaseDAO() {
        return tablaBaseDAO;
    }

    public void setTablaBaseDAO(TablaBaseDAO tablaBaseDAO) {
        this.tablaBaseDAO = tablaBaseDAO;
    }
}
