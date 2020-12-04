/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mef.erp.servicios;

import com.mef.erp.modelo.TiposDeCambioDAO;
import com.mef.erp.modelo.entidad.Mensaje;
import com.mef.erp.modelo.entidad.Monedas;
import com.mef.erp.modelo.entidad.TiposDeCambio;
import java.util.Date;
import java.util.List;

/**
 *
 * @author daniel
 */
public class ServicioTiposDeCambio implements ServicioTiposDeCambioIF {

    private TiposDeCambioDAO tiposDeCambioDAO;

    public Mensaje agregar(TiposDeCambio entity, String uuidCxn) {
        return tiposDeCambioDAO.agregar(entity, uuidCxn);
    }

    public Mensaje actualizar(TiposDeCambio entity, String uuidCxn) {
        return tiposDeCambioDAO.actualizar(entity, uuidCxn);
    }

    public Mensaje eliminar(TiposDeCambio entity, String uuidCxn) {
        return tiposDeCambioDAO.eliminar(entity, uuidCxn);
    }

    public Mensaje getTiposDeCambioAll(String uuidCxn) {
        return tiposDeCambioDAO.getTiposDeCambioAll(uuidCxn);
    }

    public Mensaje getTiposDeCambioPorClave(String clave, String uuidCxn) {
        return tiposDeCambioDAO.getTiposDeCambioPorClave(clave, uuidCxn);
    }

    public Mensaje getTiposDeCambioPorMoneda(Monedas m, String uuidCxn) {
        return tiposDeCambioDAO.getTiposDeCambioPorMoneda(m, uuidCxn);
    }

    public Mensaje guardaTiposDeCambio(List<TiposDeCambio> agrega, Object[] eliminados, String uuidCxn) {
        return tiposDeCambioDAO.guardaTiposDeCambio(agrega, eliminados, uuidCxn);
    }

    public Mensaje actualizaTiposDeCambio(List<TiposDeCambio> agrega, Object[] eliminados, String uuidCxn) {
        return tiposDeCambioDAO.actualizaTiposDeCambio(agrega, eliminados, uuidCxn);
    }

    public Mensaje eliminaTiposDeCambio(Object[] eliminados, String uuidCxn) {
        return tiposDeCambioDAO.eliminaTiposDeCambio(eliminados, uuidCxn);
    }

    public Mensaje getTiposDeCambioPorFecha(Date fecha, String uuidCxn) {
        return tiposDeCambioDAO.getTiposDeCambioPorFecha(fecha, uuidCxn);
    }

    public TiposDeCambioDAO getTiposDeCambioDAO() {
        return tiposDeCambioDAO;
    }

    public void setTiposDeCambioDAO(TiposDeCambioDAO tiposDeCambioDAO) {
        this.tiposDeCambioDAO = tiposDeCambioDAO;
    }
}
