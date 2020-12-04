/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mef.erp.modelo;

import com.mef.erp.modelo.entidad.Mensaje;
import com.mef.erp.modelo.entidad.Permisos;
import com.mef.erp.modelo.entidad.RazonSocialConfiguracion;
import com.mef.erp.modelo.entidad.Restriccion;
import com.mef.erp.modelo.entidad.Usuario;
import java.util.List;

/**
 *
 * @author Jose Armando
 */
public interface UsuarioDAOIF extends GenericDAO<Usuario, Integer> {

    public Mensaje getUsuarioAll(String uuidCxnMaestra);

    public Mensaje agregar(Usuario entity, String uuidCxnMaestra);

    public Mensaje actualizar(Usuario entity, String uuidCxnMaestra);

    public Mensaje eliminar(Usuario entity, String uuidCxnMaestra);

    public Mensaje getUsuarioPorClave(String clave, String uuidCxnMaestra);

    public Mensaje saveUsuariosRestriciones(List<Restriccion> agrega, Usuario entity, List<RazonSocialConfiguracion> listRazonSocial, String uuidCxnMaestra);

    public Mensaje saveUsuariosRestricionesMenus(List<Restriccion> agrega, Usuario entity, List<RazonSocialConfiguracion> listRazonSocial, List<Object> menus, List<Permisos> permisos, String uuidCxnMaestra);

    public Mensaje updateUsuariosRestricciones(List<Restriccion> agrega, List<Restriccion> eliminados, Usuario entity, List<RazonSocialConfiguracion> listRazonSocial, String uuidCxnMaestra);

    public Mensaje updateUsuariosRestriccionesMenus(List<Restriccion> agrega, List<Restriccion> eliminados, Usuario entity, List<RazonSocialConfiguracion> listRazonSocial, List<Object> menus, List<Permisos> permisos, String uuidCxnMaestra);

    public Mensaje deleteUsuarioRestricciones(Usuario entity, String uuidCxnMaestra);

    public Mensaje getAccesoCorrecto(String apodo, String password, String uuidCxnMaestra);

    public Mensaje getAccesoCorrectoConRazonSocialYRazonesSociales(String apodo, String password, String uuidCxn, String uuidCxnMaestra);
}
