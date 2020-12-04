/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mef.erp.servicios;

import com.mef.erp.modelo.TipoHerramientaDAO;
import com.mef.erp.modelo.entidad.Mensaje;
import com.mef.erp.modelo.entidad.TipoHerramienta;

/**
 *
 * @author Jose Armando
 */
public class ServicioTipoHerramienta implements ServicioTipoHerramientaIF {

    private TipoHerramientaDAO tipoHerramientaDAO;

    public Mensaje getTipoHerramientaAll(String uuidCxnMaestra) {
        return tipoHerramientaDAO.getTipoHerramientaAll(uuidCxnMaestra);
    }

    public Mensaje getTipoHerramienta(int id, String uuidCxnMaestra) {
        return tipoHerramientaDAO.getTipoHerramienta(id, uuidCxnMaestra);
    }

    public Mensaje agregar(TipoHerramienta entity, String uuidCxnMaestra) {
        return tipoHerramientaDAO.agregar(entity, uuidCxnMaestra);
    }

    public Mensaje eliminar(TipoHerramienta entity, String uuidCxnMaestra) {
        return tipoHerramientaDAO.eliminar(entity, uuidCxnMaestra);
    }

    public Mensaje actualizar(TipoHerramienta entity, String uuidCxnMaestra) {
        return tipoHerramientaDAO.actualizar(entity, uuidCxnMaestra);
    }

    public TipoHerramientaDAO getTipoHerramientaDAO() {
        return tipoHerramientaDAO;
    }

    public void setTipoHerramientaDAO(TipoHerramientaDAO tipoHerramientaDAO) {
        this.tipoHerramientaDAO = tipoHerramientaDAO;
    }
}
