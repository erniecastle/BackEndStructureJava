/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mef.erp.servicios;

import com.mef.erp.modelo.entidad.BaseAfectadaGrupo;
import com.mef.erp.modelo.entidad.Grupo;
import com.mef.erp.modelo.entidad.Mensaje;
import java.util.List;

/**
 *
 * @author daniel
 */
public interface ServicioGruposIF {

    /*Grupo*/
    Mensaje agregar(Grupo entity, String uuidCxn);

    /*boolean*/
    Mensaje actualizar(Grupo entity, String uuidCxn);

    /*boolean*/
    Mensaje eliminar(Grupo entity, String uuidCxn);

    /*List<Grupo>*/
    Mensaje getGruposAll(String uuidCxn);

    /*Grupo*/
    Mensaje getGruposPorClave(String clave, String uuidCxn);

    /*List<Grupo>*/
    Mensaje consultaPorRangos(Integer inicio, Integer rango, String uuidCxn);

    /*List<Grupo>*/
    Mensaje consultaPorFiltros(String query, Object[] campos, Object[] valores, Integer inicio, Integer rango, String uuidCxn);

    /*List<Grupo>*/
    Mensaje agregarListaGrupos(List<Grupo> entitys, int rango, String uuidCxn);

    /*Boolean*/
    Mensaje existeDato(String campo, Object valor, String uuidCxn);

    /*boolean*/
    public Mensaje deleteListQuery(String tabla, String campo, Object[] valores, String uuidCxn);

    /*Object*/
    Mensaje agregaGruposBaseAfectadas(Grupo entity, List<BaseAfectadaGrupo> eliminadasAfectadaGrupos, String uuidCxn);

}
