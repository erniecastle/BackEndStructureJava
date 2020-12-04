/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mef.erp.servicios;

import com.mef.erp.modelo.MonedasDAO;
import com.mef.erp.modelo.entidad.Mensaje;
import com.mef.erp.modelo.entidad.Monedas;
import java.util.List;

/**
 *
 * @author daniel
 */
public class ServicioMonedas implements ServicioMonedasIF {

    private MonedasDAO monedasDAO;

    public Mensaje agregar(Monedas entity, String uuidCxn) {

        return monedasDAO.agregar(entity, uuidCxn);
    }

    public Mensaje actualizar(Monedas entity, String uuidCxn) {

        return monedasDAO.actualizar(entity, uuidCxn);
    }

    public Mensaje eliminar(Monedas entity, String uuidCxn) {

        return monedasDAO.eliminar(entity, uuidCxn);
    }

    public Mensaje getMonedasAll(String uuidCxn) {

        return monedasDAO.getMonedasAll(uuidCxn);
    }

    public Mensaje getMonedasPorClave(String clave, String uuidCxn) {

        return monedasDAO.getMonedasPorClave(clave, uuidCxn);
    }

    public Mensaje consultaPorFiltros(String query, Object[] campos, Object[] valores, Integer inicio, Integer rango, String uuidCxn) {

        return monedasDAO.consultaPorFiltrosMonedas(query, campos, valores, inicio, rango, uuidCxn);
    }

    public Mensaje consultaPorRangos(Integer inicio, Integer rango, String uuidCxn) {

        return monedasDAO.consultaPorRangos(inicio, rango, uuidCxn);
    }

    public Mensaje existeDato(String campo, Object valor, String uuidCxn) {

        return monedasDAO.existeDato(campo, valor, uuidCxn);
    }

    public Mensaje saveDeleteMonedas(List<Monedas> entitysCambios, Object[] clavesDelete, int rango, String uuidCxn) {

        return monedasDAO.saveDeleteMonedas(entitysCambios, clavesDelete, rango, uuidCxn);
    }

    public MonedasDAO getMonedasDAO() {

        return monedasDAO;
    }

    public void setMonedasDAO(MonedasDAO monedasDAO) {

        this.monedasDAO = monedasDAO;
    }
}
