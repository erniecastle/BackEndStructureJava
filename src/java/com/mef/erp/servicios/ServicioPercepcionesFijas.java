/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mef.erp.servicios;

import com.mef.erp.modelo.PercepcionesFijasDAO;
import com.mef.erp.modelo.entidad.CategoriasPuestos;
import com.mef.erp.modelo.entidad.Mensaje;
import com.mef.erp.modelo.entidad.PercepcionesFijas;
import com.mef.erp.modelo.entidad.Puestos;
import java.util.List;

/**
 *
 * @author daniel
 */
public class ServicioPercepcionesFijas implements ServicioPercepcionesFijasIF {

    private PercepcionesFijasDAO percepcionesFijasDAO;

    public Mensaje agregar(PercepcionesFijas entity, String uuidCxn) {

        return percepcionesFijasDAO.agregar(entity, uuidCxn);
    }

    public Mensaje actualizar(PercepcionesFijas entity, String uuidCxn) {

        return percepcionesFijasDAO.actualizar(entity, uuidCxn);
    }

    public Mensaje eliminar(PercepcionesFijas entity, String uuidCxn) {

        return percepcionesFijasDAO.eliminar(entity, uuidCxn);
    }

    public Mensaje getPercepcionesFijasAll(String uuidCxn) {

        return percepcionesFijasDAO.getPercepcionesFijasAll(uuidCxn);
    }

    public Mensaje getPercepcionesFijasPorId(Long id, String uuidCxn) {//AAP01

        return percepcionesFijasDAO.getPercepcionesFijasPorId(id, uuidCxn);
    }

    public Mensaje getPercepcionesFijasPorCategoriasPuestos(CategoriasPuestos categoriasPuestos, String uuidCxn) {

        return percepcionesFijasDAO.getPercepcionesFijasPorCategoriasPuestos(categoriasPuestos, uuidCxn);
    }

    public Mensaje getPercepcionesFijasPorPuestos(Puestos puestos, String uuidCxn) {

        return percepcionesFijasDAO.getPercepcionesFijasPorPuestos(puestos, uuidCxn);
    }

    public Mensaje consultaPorFiltros(String query, Object[] campos, Object[] valores, String uuidCxn) {

        return percepcionesFijasDAO.consultaPorFiltrosPercepcion(query, campos, valores, uuidCxn);
    }

    public Mensaje consultaPorRangos(Integer inicio, Integer rango, String uuidCxn) {

        return percepcionesFijasDAO.consultaPorRangos(inicio, rango, uuidCxn);
    }

    public Mensaje existeDato(String campo, Object valor, String uuidCxn) {

        return percepcionesFijasDAO.existeDato(campo, valor, uuidCxn);
    }

    public Mensaje agregarListaPercepcionesFijas(List<PercepcionesFijas> entitys, int rango, String uuidCxn) {

        return percepcionesFijasDAO.agregarListaPercepcionesFijas(entitys, rango, uuidCxn);
    }

    public Mensaje deleteListQuery(String tabla, String campo, Object[] valores, String uuidCxn) {

        return percepcionesFijasDAO.deleteListQuerys(tabla, campo, valores, uuidCxn);
    }

    public Mensaje getPercepcionesFijasPorIDCategoriaPuesto(Long clave, String uuidCxn) {

        return percepcionesFijasDAO.getPercepcionesFijasPorIDCategoriaPuesto(clave, uuidCxn);
    }

    public Mensaje getPercepcionesFijasPorIDPuesto(Long clave, String uuidCxn) {

        return percepcionesFijasDAO.getPercepcionesFijasPorIDPuesto(clave, uuidCxn);
    }

    public PercepcionesFijasDAO getPercepcionesFijasDAO(String uuidCxn) {
        return percepcionesFijasDAO;
    }

    public void setPercepcionesFijasDAO(PercepcionesFijasDAO percepcionesFijasDAO, String uuidCxn) {
        this.percepcionesFijasDAO = percepcionesFijasDAO;
    }
}
