/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mef.erp.modelo;

import com.mef.erp.modelo.entidad.Mensaje;
import com.mef.erp.modelo.entidad.Plazas;
import java.util.List;

/**
 *
 * @author daniel
 */
public interface PlazasDAOIF extends GenericDAO<Plazas, Long> {

    Mensaje agregar(Plazas entity, String uuidCxn);

    Mensaje actualizar(Plazas entity, String uuidCxn);

    Mensaje eliminar(Plazas entity, String uuidCxn);

//    List<Plazas> getPlazasAll(, String uuidCxn);

    Mensaje getPlazasPorClave(String clave, String razonSocial, String uuidCxn);

    Mensaje getPlazasPorRazonSocial(String clave, String uuidCxn);

    Mensaje consultaPorRangos(Integer inicio, Integer rango, String uuidCxn);

    Mensaje consultaPorFiltrosPlazas(String query, Object[] campos, Object[] valores, String uuidCxn);

    Mensaje agregarListaPlazas(List<Plazas> entitys, int rango, String uuidCxn);

    Mensaje existeDato(String campo, Object valor, String uuidCxn);

    public Mensaje deleteListQuerys(String tabla, String campo, Object[] valores, String uuidCxn);
}
