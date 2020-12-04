/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mef.erp.modelo;

import com.mef.erp.modelo.entidad.Mensaje;
import com.mef.erp.modelo.entidad.TablaPersonalizada;

public interface TablaPersonalizadaDAOIF extends GenericDAO<TablaPersonalizada, Integer> {

    public Mensaje getTablaPersonalizadaAll(String uuidCxnMaestra);

    public Mensaje getTablaPersonalizadaPorClave(String clave, String uuidCxnMaestra);

    public Mensaje agregar(TablaPersonalizada entity, String uuidCxnMaestra);

    public Mensaje actualizar(TablaPersonalizada entity, String uuidCxnMaestra);

    public Mensaje eliminar(TablaPersonalizada tablaPersonalizada, String uuidCxnMaestra);

    public Mensaje existeClaveTablaPersonalizada(String clave, String uuidCxnMaestra);
}
