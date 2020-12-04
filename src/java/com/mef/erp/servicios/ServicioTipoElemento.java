/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mef.erp.servicios;

import com.mef.erp.modelo.TipoElementoDAO;
import com.mef.erp.modelo.entidad.Mensaje;
import com.mef.erp.modelo.entidad.TipoElemento;

/**
 *
 * @author Jose Armando
 */
public class ServicioTipoElemento implements ServicioTipoElementoIF {

    private TipoElementoDAO tipoElementoDAO;

    public Mensaje getTipoElementoAll(String uuidCxnMaestra) {
        return tipoElementoDAO.getTipoElementoAll(uuidCxnMaestra);
    }

    public Mensaje getTipoElemento(String nombre, String uuidCxnMaestra) {
        return tipoElementoDAO.getTipoElemento(nombre, uuidCxnMaestra);
    }

    public Mensaje agregar(TipoElemento entity, String uuidCxnMaestra) {
        return tipoElementoDAO.agregar(entity, uuidCxnMaestra);
    }

    public Mensaje eliminar(TipoElemento entity, String uuidCxnMaestra) {
        return tipoElementoDAO.eliminar(entity, uuidCxnMaestra);
    }

    public Mensaje actualizar(TipoElemento entity, String uuidCxnMaestra) {
        return tipoElementoDAO.actualizar(entity, uuidCxnMaestra);
    }

    public TipoElementoDAO getTipoElementoDAO() {
        return tipoElementoDAO;
    }

    public void setTipoElementoDAO(TipoElementoDAO tipoElementoDAO) {
        this.tipoElementoDAO = tipoElementoDAO;
    }

}
