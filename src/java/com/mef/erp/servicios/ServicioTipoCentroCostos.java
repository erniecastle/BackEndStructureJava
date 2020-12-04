/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mef.erp.servicios;

import com.mef.erp.modelo.TipoCentroCostosDAO;
import com.mef.erp.modelo.entidad.Mensaje;
import com.mef.erp.modelo.entidad.TipoCentroCostos;
import java.util.List;

/**
 *
 * @author daniel
 */
public class ServicioTipoCentroCostos implements ServicioTipoCentroCostosIF {

    private TipoCentroCostosDAO tipoCentroCostosDAO;

    public Mensaje agregar(TipoCentroCostos entity, String uuidCxn) {
        return tipoCentroCostosDAO.agregar(entity, uuidCxn);
    }

    public Mensaje actualizar(TipoCentroCostos entity, String uuidCxn) {
        return tipoCentroCostosDAO.actualizar(entity, uuidCxn);
    }

    public Mensaje eliminar(TipoCentroCostos entity, String uuidCxn) {
        return tipoCentroCostosDAO.eliminar(entity, uuidCxn);
    }

    public Mensaje getTipoCentroCostosAll(String claveRazonesSocial, String uuidCxn) {
        return tipoCentroCostosDAO.getTipoCentroCostosAll(claveRazonesSocial, uuidCxn);
    }

    public Mensaje getTipoCentroCostosPorClave(String clave, String claveRazonesSocial, String uuidCxn) {
        return tipoCentroCostosDAO.getTipoCentroCostosPorClave(clave, claveRazonesSocial, uuidCxn);
    }

    public Mensaje consultaPorFiltros(String query, Object[] campos, Object[] valores, Integer inicio, Integer rango, String uuidCxn) {
        return tipoCentroCostosDAO.consultaPorFiltrosTipoCentroCostos(query, campos, valores, inicio, rango, uuidCxn);
    }

    public Mensaje consultaPorRangos(Integer inicio, Integer rango, String[] camposWhere, Object[] camposWhereValores, String uuidCxn) {
        return tipoCentroCostosDAO.consultaPorRangosTipoCentroCostos(inicio, rango, camposWhere, camposWhereValores, uuidCxn);
    }

    public Mensaje existeDato(String campo, Object valor, String uuidCxn) {
        return tipoCentroCostosDAO.existeDato(campo, valor, uuidCxn);
    }

    public Mensaje saveDeleteTipoCentroCostos(List<TipoCentroCostos> entitysCambios, Object[] clavesDelete, int rango, String uuidCxn) {
        return tipoCentroCostosDAO.saveDeleteTipoCentroCostos(entitysCambios, clavesDelete, rango, uuidCxn);
    }

    public TipoCentroCostosDAO getTipoCentroCostosDAO() {
        return tipoCentroCostosDAO;
    }

    public void setTipoCentroCostosDAO(TipoCentroCostosDAO tipoCentroCostosDAO) {
        this.tipoCentroCostosDAO = tipoCentroCostosDAO;
    }
}
