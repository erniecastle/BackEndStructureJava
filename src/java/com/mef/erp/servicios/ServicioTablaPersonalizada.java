/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mef.erp.servicios;

import com.mef.erp.modelo.TablaPersonalizadaDAO;
import com.mef.erp.modelo.entidad.Mensaje;
import com.mef.erp.modelo.entidad.TablaPersonalizada;

/**
 *
 * @author Jose Armando
 */
public class ServicioTablaPersonalizada implements ServicioTablaPersonalizadaIF {

    private TablaPersonalizadaDAO tablaPersonalizadaDAO;

    public Mensaje getTablaPersonalizadaAll(String uuidCxnMaestra) {
        return tablaPersonalizadaDAO.getTablaPersonalizadaAll(uuidCxnMaestra);
    }

    public Mensaje getTablaPersonalizadaPorClave(String clave, String uuidCxnMaestra) {
        return tablaPersonalizadaDAO.getTablaPersonalizadaPorClave(clave, uuidCxnMaestra);
    }

    public Mensaje agregar(TablaPersonalizada entity, String uuidCxnMaestra) {
        return tablaPersonalizadaDAO.agregar(entity, uuidCxnMaestra);
    }

    public Mensaje actualizar(TablaPersonalizada entity, String uuidCxnMaestra) {
        return tablaPersonalizadaDAO.actualizar(entity, uuidCxnMaestra);
    }

    public Mensaje eliminar(TablaPersonalizada tablaPersonalizada, String uuidCxnMaestra) {
        return tablaPersonalizadaDAO.eliminar(tablaPersonalizada, uuidCxnMaestra);
    }

    public Mensaje existeClaveTablaPersonalizada(String clave, String uuidCxnMaestra) {
        return tablaPersonalizadaDAO.existeClaveTablaPersonalizada(clave, uuidCxnMaestra);
    }

    public TablaPersonalizadaDAO getTablaPersonalizadaDAO() {
        return tablaPersonalizadaDAO;
    }

    public void setTablaPersonalizadaDAO(TablaPersonalizadaDAO tablaPersonalizadaDAO) {
        this.tablaPersonalizadaDAO = tablaPersonalizadaDAO;
    }
}
