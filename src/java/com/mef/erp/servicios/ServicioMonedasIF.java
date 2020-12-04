/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mef.erp.servicios;

import com.mef.erp.modelo.entidad.Mensaje;
import com.mef.erp.modelo.entidad.Monedas;
import java.util.List;

/**
 *
 * @author daniel
 */
public interface ServicioMonedasIF {

    /*Monedas*/
    public Mensaje agregar(Monedas entity, String uuidCxn);

    /*boolean*/
    public Mensaje actualizar(Monedas entity, String uuidCxn);

    /*boolean*/
    public Mensaje eliminar(Monedas entity, String uuidCxn);

    /*List<Monedas>*/
    public Mensaje getMonedasAll(String uuidCxn);

    /*Monedas*/
    public Mensaje getMonedasPorClave(String clave, String uuidCxn);

    /*List<Monedas>*/
    public Mensaje consultaPorRangos(Integer inicio, Integer rango, String uuidCxn);

    /*List<Monedas>*/
    public Mensaje consultaPorFiltros(String query, Object[] campos, Object[] valores, Integer inicio, Integer rango, String uuidCxn);

    /*Boolean*/
    public Mensaje existeDato(String campo, Object valor, String uuidCxn);

    public Mensaje saveDeleteMonedas(List<Monedas> entitysCambios, Object[] clavesDelete, int rango, String uuidCxn);
    
}
