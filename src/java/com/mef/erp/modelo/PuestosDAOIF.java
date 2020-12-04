/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mef.erp.modelo;

import com.mef.erp.modelo.entidad.Mensaje;
import com.mef.erp.modelo.entidad.PercepcionesFijas;
import com.mef.erp.modelo.entidad.Puestos;
import java.util.List;

/**
 *
 * @author daniel
 */
public interface PuestosDAOIF extends GenericDAO<Puestos, Long> {

    public Mensaje agregar(Puestos entity, String uuidCxn);

    public Mensaje actualizar(Puestos entity, String uuidCxn);

    public Mensaje eliminar(Puestos entity, String uuidCxn);

    public Mensaje getPuestosAll(String uuidCxn);

    public Mensaje getPuestosPorClave(String clave, String uuidCxn);

    Mensaje SavePuesto(List<PercepcionesFijas> agrega, Object[] eliminados, Puestos entity, String uuidCxn);

    Mensaje DeletePuesto(Puestos entity, String uuidCxn);

    Mensaje UpdatePuesto(List<PercepcionesFijas> agrega, Object[] eliminados, Puestos entity, String uuidCxn);

    Mensaje consultaPorFiltrosPuesto(String query, Object[] campos, Object[] valores, String uuidCxn);

    Mensaje consultaPorRangos(Integer inicio, Integer rango, String uuidCxn);

    Mensaje existeDato(String campo, Object valor, String uuidCxn);

    Mensaje agregarListaPuestos(List<Puestos> entitys, int rango, String uuidCxn);

    Mensaje deleteListQuerys(String tabla, String campo, Object[] valores, String uuidCxn);

}
