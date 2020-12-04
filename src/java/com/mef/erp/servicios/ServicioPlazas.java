/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mef.erp.servicios;

import com.mef.erp.modelo.PlazasDAO;
import com.mef.erp.modelo.entidad.Mensaje;
import com.mef.erp.modelo.entidad.Plazas;
import java.util.List;

/**
 *
 * @author daniel
 */
public class ServicioPlazas implements ServicioPlazasIF {

    private PlazasDAO plazasDAO;

    public Mensaje agregar(Plazas entity, String uuidCxn) {
        return plazasDAO.agregar(entity, uuidCxn);
    }

    public Mensaje actualizar(Plazas entity, String uuidCxn) {
        return plazasDAO.actualizar(entity, uuidCxn);
    }

    public Mensaje eliminar(Plazas entity, String uuidCxn) {
        return plazasDAO.eliminar(entity, uuidCxn);
    }

//    public List<Plazas> getPlazasAll(, String uuidCxn) {
//        return plazasDAO.getPlazasAll(, uuidCxn);
//    }
    public Mensaje getPlazasPorClave(String clave, String razonSocial, String uuidCxn) {
        return plazasDAO.getPlazasPorClave(clave, razonSocial, uuidCxn);
    }

    public Mensaje getPlazasPorRazonSocial(String clave, String uuidCxn) {
        return plazasDAO.getPlazasPorRazonSocial(clave, uuidCxn);
    }

    public Mensaje consultaPorRangos(Integer inicio, Integer rango, String uuidCxn) {
        return plazasDAO.consultaPorRangos(inicio, rango, uuidCxn);
    }

    public Mensaje consultaPorFiltros(String query, Object[] campos, Object[] valores, String uuidCxn) {
        return plazasDAO.consultaPorFiltrosPlazas(query, campos, valores, uuidCxn);
    }

    public Mensaje agregarListaPlazas(List<Plazas> entitys, int rango, String uuidCxn) {
        return plazasDAO.agregarListaPlazas(entitys, rango, uuidCxn);
    }

    public Mensaje existeDato(String campo, Object valor, String uuidCxn) {
        return plazasDAO.existeDato(campo, valor, uuidCxn);
    }

    public Mensaje deleteListQuery(String tabla, String campo, Object[] valores, String uuidCxn) {
        return plazasDAO.deleteListQuerys(tabla, campo, valores, uuidCxn);
    }

    public PlazasDAO getPlazasDAO() {
        return plazasDAO;
    }

    public void setPlazasDAO(PlazasDAO plazasDAO) {
        this.plazasDAO = plazasDAO;
    }
}
