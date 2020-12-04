/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mef.erp.servicios;

import com.mef.erp.modelo.entidad.Mensaje;
import com.mef.erp.modelo.entidad.TipoHerramienta;

/**
 *
 * @author Jose Armando
 */
public interface ServicioTipoHerramientaIF {

    /*List<TipoHerramienta>*/
    public Mensaje getTipoHerramientaAll(String uuidCxnMaestra);

    /*List<TipoHerramienta>*/
    public Mensaje getTipoHerramienta(int id, String uuidCxnMaestra);

    /*TipoHerramienta*/
    public Mensaje agregar(TipoHerramienta entity, String uuidCxnMaestra);

    /*boolean*/
    public Mensaje actualizar(TipoHerramienta entity, String uuidCxnMaestra);

    /*boolean*/
    public Mensaje eliminar(TipoHerramienta entity, String uuidCxnMaestra);
}
