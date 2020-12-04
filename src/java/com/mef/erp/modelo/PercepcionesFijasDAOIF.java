/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mef.erp.modelo;

import com.mef.erp.modelo.entidad.CategoriasPuestos;
import com.mef.erp.modelo.entidad.Mensaje;
import com.mef.erp.modelo.entidad.PercepcionesFijas;
import com.mef.erp.modelo.entidad.Puestos;
import java.util.List;

/**
 *
 * @author daniel
 */
public interface PercepcionesFijasDAOIF extends GenericDAO<PercepcionesFijas, Long> {

    Mensaje agregar(PercepcionesFijas entity, String uuidCxn);

    Mensaje actualizar(PercepcionesFijas entity, String uuidCxn);

    Mensaje eliminar(PercepcionesFijas entity, String uuidCxn);

    Mensaje getPercepcionesFijasAll(String uuidCxn);

    Mensaje getPercepcionesFijasPorCategoriasPuestos(CategoriasPuestos c, String uuidCxn);

    Mensaje getPercepcionesFijasPorPuestos(Puestos c, String uuidCxn);

    public Mensaje getPercepcionesFijasPorIDCategoriaPuesto(Long clave, String uuidCxn);

    public Mensaje getPercepcionesFijasPorIDPuesto(Long clave, String uuidCxn);

    Mensaje getPercepcionesFijasPorId(Long id, String uuidCxn);//AAP01

    Mensaje consultaPorFiltrosPercepcion(String query, Object[] campos, Object[] valores, String uuidCxn);

    Mensaje consultaPorRangos(Integer inicio, Integer rango, String uuidCxn);

    Mensaje existeDato(String campo, Object valor, String uuidCxn);

    Mensaje agregarListaPercepcionesFijas(List<PercepcionesFijas> entitys, int rango, String uuidCxn);

    Mensaje deleteListQuerys(String tabla, String campo, Object[] valores, String uuidCxn);
}
