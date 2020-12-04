/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mef.erp.modelo;

import com.mef.erp.modelo.entidad.Mensaje;
import com.mef.erp.modelo.entidad.TipoHerramienta;

/**
 *
 * @author Jose Armando
 */
public interface TipoHerramientaDAOIF extends GenericDAO<TipoHerramienta, Integer> {

    public Mensaje getTipoHerramientaAll(String uuidCxnMaestra);

    public Mensaje getTipoHerramienta(int id, String uuidCxnMaestra);

    public Mensaje agregar(TipoHerramienta entity, String uuidCxnMaestra);

    public Mensaje actualizar(TipoHerramienta entity, String uuidCxnMaestra);

    public Mensaje eliminar(TipoHerramienta entity, String uuidCxnMaestra);
}
