/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mef.erp.modelo;

import com.mef.erp.modelo.entidad.Mensaje;
import com.mef.erp.modelo.entidad.Monedas;
import java.util.List;

/**
 *
 * @author daniel
 */
public interface MonedasDAOIF extends GenericDAO<Monedas, Long> {

    public Mensaje agregar(Monedas entity, String uuidCxn);

    public Mensaje actualizar(Monedas entity, String uuidCxn);

    public Mensaje eliminar(Monedas entity, String uuidCxn);

    public Mensaje getMonedasAll(String uuidCxn);

    public Mensaje getMonedasPorClave(String clave, String uuidCxn);

    public Mensaje consultaPorRangos(Integer inicio, Integer rango, String uuidCxn);

    public Mensaje consultaPorFiltrosMonedas(String query, Object[] campos, Object[] valores, Integer inicio, Integer rango, String uuidCxn);

    public Mensaje existeDato(String campo, Object valor, String uuidCxn);

    public Mensaje saveDeleteMonedas(List<Monedas> entitysCambios, Object[] clavesDelete, int rango, String uuidCxn);

}
