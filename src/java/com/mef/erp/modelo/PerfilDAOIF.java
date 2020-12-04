/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mef.erp.modelo;

import com.mef.erp.modelo.entidad.Mensaje;
import com.mef.erp.modelo.entidad.Perfiles;
import com.mef.erp.modelo.entidad.Permisos;
import java.util.List;

/**
 *
 * @author Jose Armando
 */
public interface PerfilDAOIF extends GenericDAO<Perfiles, Integer> {

    public Mensaje getPerfilAll( String  uuidCxnMaestra);

    public Mensaje getPerfilesPorClave(String clave, String  uuidCxnMaestra);

    public Mensaje existeDato(String campo, Object valor, String  uuidCxnMaestra);

    public Mensaje savePerfilMenusPermisos(Perfiles entity, List<Object> menus, List<Permisos> permisos, String  uuidCxnMaestra);

    public Mensaje deletePerfilMenusPermisos(Perfiles entity, List<Permisos> permisos, String  uuidCxnMaestra);
}
