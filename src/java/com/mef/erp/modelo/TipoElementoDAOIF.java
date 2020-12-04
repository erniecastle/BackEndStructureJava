/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mef.erp.modelo;

import com.mef.erp.modelo.entidad.Mensaje;
import com.mef.erp.modelo.entidad.TipoElemento;

/**
 *
 * @author Jose Armando
 */
public interface TipoElementoDAOIF extends GenericDAO<TipoElemento, Integer> {

    public Mensaje getTipoElementoAll(String uuidCxnMaestra);

    Mensaje getTipoElemento(String nombre, String uuidCxnMaestra);

    public Mensaje agregar(TipoElemento entity, String uuidCxnMaestra);

    public Mensaje actualizar(TipoElemento entity, String uuidCxnMaestra);

    public Mensaje eliminar(TipoElemento entity, String uuidCxnMaestra);
}
