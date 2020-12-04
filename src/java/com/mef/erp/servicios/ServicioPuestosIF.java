/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mef.erp.servicios;

import com.mef.erp.modelo.entidad.Mensaje;
import com.mef.erp.modelo.entidad.PercepcionesFijas;
import com.mef.erp.modelo.entidad.Puestos;
import java.util.List;

/**
 *
 * @author daniel
 */
public interface ServicioPuestosIF {

    /*Puestos*/
    public Mensaje agregar(Puestos entity, String uuidCxn);

    /*boolean*/
    public Mensaje actualizar(Puestos entity, String uuidCxn);

    /*boolean*/
    public Mensaje eliminar(Puestos entity, String uuidCxn);

    /*List<Puestos>*/
    public Mensaje getPuestosAll(String uuidCxn);

    /*Puestos*/
    public Mensaje getPuestosPorClave(String clave, String uuidCxn);

    /*Puestos*/
    Mensaje SavePuesto(List<PercepcionesFijas> agrega, Object[] eliminados, Puestos entity, String uuidCxn);

    /*boolean*/
    Mensaje DeletePuesto(Puestos entity, String uuidCxn);

    /*boolean*/
    Mensaje UpdatePuesto(List<PercepcionesFijas> agrega, Object[] eliminados, Puestos entity, String uuidCxn);

    /*List<Puestos>*/
    Mensaje consultaPorFiltros(String query, Object[] campos, Object[] valores, String uuidCxn);

    /*List<Puestos>*/
    Mensaje consultaPorRangos(Integer inicio, Integer rango, String uuidCxn);

    /*Boolean*/
    Mensaje existeDato(String campo, Object valor, String uuidCxn);

    /*List<Puestos>*/
    Mensaje agregarListaPuestos(List<Puestos> entitys, int rango, String uuidCxn);

    /*boolean*/
    Mensaje deleteListQuery(String tabla, String campo, Object[] valores, String uuidCxn);
}
