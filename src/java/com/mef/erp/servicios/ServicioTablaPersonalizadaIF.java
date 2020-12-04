/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mef.erp.servicios;

import com.mef.erp.modelo.entidad.Mensaje;
import com.mef.erp.modelo.entidad.TablaPersonalizada;

/**
 *
 * @author Jose Armando
 */
public interface ServicioTablaPersonalizadaIF {

    /*List<TablaPersonalizada>*/
    public Mensaje getTablaPersonalizadaAll(String uuidCxnMaestra);

    /*TablaPersonalizada*/
    public Mensaje getTablaPersonalizadaPorClave(String clave, String uuidCxnMaestra);

    /*TablaPersonalizada*/
    public Mensaje agregar(TablaPersonalizada entity, String uuidCxnMaestra);

    /*boolean*/
    public Mensaje actualizar(TablaPersonalizada entity, String uuidCxnMaestra);

    /*boolean*/
    public Mensaje eliminar(TablaPersonalizada tablaPersonalizada, String uuidCxnMaestra);

    /*Boolean*/
    public Mensaje existeClaveTablaPersonalizada(String clave, String uuidCxnMaestra);
}
