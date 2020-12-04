/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mef.erp.servicios;

import com.mef.erp.modelo.PuestosDAO;
import com.mef.erp.modelo.entidad.Mensaje;
import com.mef.erp.modelo.entidad.PercepcionesFijas;
import com.mef.erp.modelo.entidad.Puestos;
import java.util.List;

/**
 *
 * @author daniel
 */
public class ServicioPuestos implements ServicioPuestosIF {

    private PuestosDAO puestosDAO;

    public Mensaje agregar(Puestos entity, String uuidCxn) {
        return puestosDAO.agregar(entity, uuidCxn);
    }

    public Mensaje actualizar(Puestos entity, String uuidCxn) {
        return puestosDAO.actualizar(entity, uuidCxn);
    }

    public Mensaje eliminar(Puestos entity, String uuidCxn) {
        return puestosDAO.eliminar(entity, uuidCxn);
    }

    public Mensaje getPuestosAll(String uuidCxn) {
        return puestosDAO.getPuestosAll(uuidCxn);
    }

    public Mensaje getPuestosPorClave(String clave, String uuidCxn) {
        return puestosDAO.getPuestosPorClave(clave, uuidCxn);
    }

    public Mensaje consultaPorFiltros(String query, Object[] campos, Object[] valores, String uuidCxn) {
        return puestosDAO.consultaPorFiltrosPuesto(query, campos, valores, uuidCxn);
    }

    public Mensaje consultaPorRangos(Integer inicio, Integer rango, String uuidCxn) {
        return puestosDAO.consultaPorRangos(inicio, rango, uuidCxn);
    }

    public Mensaje existeDato(String campo, Object valor, String uuidCxn) {
        return puestosDAO.existeDato(campo, valor, uuidCxn);
    }

    public Mensaje agregarListaPuestos(List<Puestos> entitys, int rango, String uuidCxn) {
        return puestosDAO.agregarListaPuestos(entitys, rango, uuidCxn);
    }

    public Mensaje deleteListQuery(String tabla, String campo, Object[] valores, String uuidCxn) {
        return puestosDAO.deleteListQuerys(tabla, campo, valores, uuidCxn);
    }

    public Mensaje SavePuesto(List<PercepcionesFijas> agrega, Object[] eliminados, Puestos entity, String uuidCxn) {
        return puestosDAO.SavePuesto(agrega, eliminados, entity, uuidCxn);
    }

    public Mensaje DeletePuesto(Puestos entity, String uuidCxn) {
        return puestosDAO.DeletePuesto(entity, uuidCxn);
    }

    public Mensaje UpdatePuesto(List<PercepcionesFijas> agrega, Object[] eliminados, Puestos entity, String uuidCxn) {
        return puestosDAO.UpdatePuesto(agrega, eliminados, entity, uuidCxn);
    }

    public PuestosDAO getPuestosDAO() {
        return puestosDAO;
    }

    public void setPuestosDAO(PuestosDAO puestosDAO) {
        this.puestosDAO = puestosDAO;
    }
}
