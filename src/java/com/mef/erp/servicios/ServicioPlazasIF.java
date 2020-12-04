/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mef.erp.servicios;

import com.mef.erp.modelo.entidad.Mensaje;
import com.mef.erp.modelo.entidad.Plazas;
import java.util.List;

/**
 *
 * @author daniel
 */
public interface ServicioPlazasIF {

    /*Plazas*/
    Mensaje agregar(Plazas entity, String uuidCxn);

    /*boolean*/
    Mensaje actualizar(Plazas entity, String uuidCxn);

    /*boolean*/
    Mensaje eliminar(Plazas entity, String uuidCxn);

//    List<Plazas> getPlazasAll(, String uuidCxn);
    /*Plazas*/
    Mensaje getPlazasPorClave(String clave, String razonSocial, String uuidCxn);

    /*List<Plazas>*/
    Mensaje getPlazasPorRazonSocial(String clave, String uuidCxn);

    /*List<Plazas>*/
    Mensaje consultaPorRangos(Integer inicio, Integer rango, String uuidCxn);

    /*List<Plazas>*/
    Mensaje consultaPorFiltros(String query, Object[] campos, Object[] valores, String uuidCxn);

    /*List<Plazas>*/
    Mensaje agregarListaPlazas(List<Plazas> entitys, int rango, String uuidCxn);

    /*Boolean*/
    Mensaje existeDato(String campo, Object valor, String uuidCxn);

    /*boolean*/
    public Mensaje deleteListQuery(String tabla, String campo, Object[] valores, String uuidCxn);
}
