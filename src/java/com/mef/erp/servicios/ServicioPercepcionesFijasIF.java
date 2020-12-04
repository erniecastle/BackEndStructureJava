/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mef.erp.servicios;

import com.mef.erp.modelo.entidad.CategoriasPuestos;
import com.mef.erp.modelo.entidad.Mensaje;
import com.mef.erp.modelo.entidad.PercepcionesFijas;
import com.mef.erp.modelo.entidad.Puestos;
import java.util.List;

/**
 *
 * @author daniel
 */
public interface ServicioPercepcionesFijasIF {

    /*PercepcionesFijas*/
    Mensaje agregar(PercepcionesFijas entity, String  uuidCxn);

    /*boolean*/
    Mensaje actualizar(PercepcionesFijas entity, String  uuidCxn);

    /*boolean*/
    Mensaje eliminar(PercepcionesFijas entity, String  uuidCxn);

    /*List<PercepcionesFijas>*/
    Mensaje getPercepcionesFijasAll(String  uuidCxn);

    /*List<PercepcionesFijas>*/
    Mensaje getPercepcionesFijasPorCategoriasPuestos(CategoriasPuestos c, String  uuidCxn);

    /*List<PercepcionesFijas>*/
    Mensaje getPercepcionesFijasPorPuestos(Puestos c, String  uuidCxn);

    /*PercepcionesFijas*/
    Mensaje getPercepcionesFijasPorId(Long id, String  uuidCxn);

    /*List<PercepcionesFijas>*/
    public Mensaje getPercepcionesFijasPorIDCategoriaPuesto(Long clave, String  uuidCxn);

    /*List<PercepcionesFijas>*/
    public Mensaje getPercepcionesFijasPorIDPuesto(Long clave, String  uuidCxn);

    /*List<PercepcionesFijas>*/
    Mensaje consultaPorFiltros(String query, Object[] campos, Object[] valores, String  uuidCxn);

    /*List<PercepcionesFijas>*/
    Mensaje consultaPorRangos(Integer inicio, Integer rango, String  uuidCxn);

    /*Boolean*/
    Mensaje existeDato(String campo, Object valor, String  uuidCxn);

    /*List<PercepcionesFijas>*/
    Mensaje agregarListaPercepcionesFijas(List<PercepcionesFijas> entitys, int rango, String  uuidCxn);

    /*Boolean*/
    Mensaje deleteListQuery(String tabla, String campo, Object[] valores, String  uuidCxn);

}
