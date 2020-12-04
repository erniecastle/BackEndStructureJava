/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mef.erp.servicios;

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
public interface ServicioUsuarioIF {

    /*List<Usuario>*/
    public Mensaje getUsuarioAll(String uuidCxnMaestra);

    /*Usuario*/
    public Mensaje agregar(Usuario entity, String uuidCxnMaestra);

    /*boolean*/
    public Mensaje actualizar(Usuario entity, String uuidCxnMaestra);

    /*boolean*/
    public Mensaje eliminar(Usuario entity, String uuidCxnMaestra);

    /*Usuario*/
    public Mensaje getUsuarioPorClave(String clave, String uuidCxnMaestra);

    /*Usuario*/
    public Mensaje saveUsuariosRestriciones(List<Restriccion> agrega, Usuario entity, List<RazonSocialConfiguracion> listRazonSocial, String uuidCxnMaestra);

    /*Usuario*/
    public Mensaje saveUsuariosRestricionesMenus(List<Restriccion> agrega, Usuario entity, List<RazonSocialConfiguracion> listRazonSocial, List<Object> menus, List<Permisos> permisos, String uuidCxnMaestra);

    /*boolean*/
    public Mensaje updateUsuariosRestricciones(List<Restriccion> agrega, List<Restriccion> eliminados, Usuario entity, List<RazonSocialConfiguracion> listRazonSocial, String uuidCxnMaestra);

    /*boolean*/
    public Mensaje updateUsuariosRestriccionesMenus(List<Restriccion> agrega, List<Restriccion> eliminados, Usuario entity, List<RazonSocialConfiguracion> listRazonSocial, List<Object> menus, List<Permisos> permisos, String uuidCxnMaestra);

    /*boolean*/
    public Mensaje deleteUsuarioRestricciones(Usuario entity, String uuidCxnMaestra);

    /*Usuario*/
    public Mensaje getAccesoCorrecto(String apodo, String password, String uuidCxnMaestra);

    /*List<Object>{usuario,List<RazonesSociales>,List<RazonSocial>}*/
    public Mensaje getAccesoCorrectoConRazonSocialYRazonesSociales(String apodo, String password, String uuidCxn, String uuidCxnMaestra);
}
