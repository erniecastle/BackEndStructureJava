/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mef.erp.servicios;

import com.mef.erp.modelo.entidad.Mensaje;
import com.mef.erp.modelo.entidad.TipoElemento;

/**
 *
 * @author Jose Armando
 */
public interface ServicioTipoElementoIF {

    /*List<TipoElemento>*/
    public Mensaje getTipoElementoAll(String uuidCxnMaestra);

    /*TipoElemento*/
    public Mensaje getTipoElemento(String nombre, String uuidCxnMaestra);

    /*TipoElemento*/
    public Mensaje agregar(TipoElemento entity, String uuidCxnMaestra);

    /*boolean*/
    public Mensaje actualizar(TipoElemento entity, String uuidCxnMaestra);

    /*boolean*/
    public Mensaje eliminar(TipoElemento entity, String uuidCxnMaestra);
}
