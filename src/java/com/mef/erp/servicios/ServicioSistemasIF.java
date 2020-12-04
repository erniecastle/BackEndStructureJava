/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mef.erp.servicios;

import com.mef.erp.modelo.entidad.Mensaje;
import com.mef.erp.modelo.entidad.Sistemas;
import java.util.List;

/**
 *
 * @author daniel
 */
public interface ServicioSistemasIF {

    /*Sistemas*/
    public Mensaje agregar(Sistemas entity, String uuidCxnMaestra);

    /*boolean*/
    public Mensaje actualizar(Sistemas entity, String uuidCxnMaestra);

    /*boolean*/
    public Mensaje eliminar(Sistemas entity, String uuidCxnMaestra);

    /*List<Sistemas>*/
    public Mensaje getSistemasAll(String uuidCxnMaestra);

    /*Sistemas*/
    public Mensaje getSistemasPorClave(String clave, String uuidCxnMaestra);

    /*List<Sistemas>*/
    public Mensaje consultaPorFiltros(String query, Object[] campos, Object[] valores, Integer inicio, Integer rango, String uuidCxnMaestra);

    /*List<Sistemas>*/
    public Mensaje consultaPorRangos(Integer inicio, Integer rango, String uuidCxnMaestra);

    /*Boolean*/
    Mensaje existeDato(String campo, Object valor, String uuidCxnMaestra);

    /*List<Sistemas>*/
    Mensaje saveDeleteSistemas(List<Sistemas> entitysCambios, Object[] clavesDelete, int rango, String uuidCxnMaestra);
}
