/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mef.erp.servicios;

import com.mef.erp.modelo.UsuarioDAO;
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
public class ServicioUsuario implements ServicioUsuarioIF {

    private UsuarioDAO usuarioDAO;

    public Mensaje getUsuarioAll(String uuidCxnMaestra) {
        return usuarioDAO.getUsuarioAll(uuidCxnMaestra);
    }

    public Mensaje agregar(Usuario entity, String uuidCxnMaestra) {
        return usuarioDAO.agregar(entity, uuidCxnMaestra);
    }

    public Mensaje eliminar(Usuario entity, String uuidCxnMaestra) {
        return usuarioDAO.eliminar(entity, uuidCxnMaestra);
    }

    public Mensaje actualizar(Usuario entity, String uuidCxnMaestra) {
        return usuarioDAO.actualizar(entity, uuidCxnMaestra);
    }

    public Mensaje getUsuarioPorClave(String clave, String uuidCxnMaestra) {
        return usuarioDAO.getUsuarioPorClave(clave, uuidCxnMaestra);
    }

    public Mensaje saveUsuariosRestriciones(List<Restriccion> agrega, Usuario entity, List<RazonSocialConfiguracion> listRazonSocial, String uuidCxnMaestra) {
        return usuarioDAO.saveUsuariosRestriciones(agrega, entity, listRazonSocial, uuidCxnMaestra);
    }

    public Mensaje saveUsuariosRestricionesMenus(List<Restriccion> agrega, Usuario entity, List<RazonSocialConfiguracion> listRazonSocial, List<Object> menus, List<Permisos> permisos, String uuidCxnMaestra) {
        return usuarioDAO.saveUsuariosRestricionesMenus(agrega, entity, listRazonSocial, menus, permisos, uuidCxnMaestra);
    }

    public Mensaje updateUsuariosRestricciones(List<Restriccion> agrega, List<Restriccion> eliminados, Usuario entity, List<RazonSocialConfiguracion> listRazonSocial, String uuidCxnMaestra) {
        return usuarioDAO.updateUsuariosRestricciones(agrega, eliminados, entity, listRazonSocial, uuidCxnMaestra);
    }

    public Mensaje updateUsuariosRestriccionesMenus(List<Restriccion> agrega, List<Restriccion> eliminados, Usuario entity, List<RazonSocialConfiguracion> listRazonSocial, List<Object> menus, List<Permisos> permisos, String uuidCxnMaestra) {
        return usuarioDAO.updateUsuariosRestriccionesMenus(agrega, eliminados, entity, listRazonSocial, menus, permisos, uuidCxnMaestra);
    }

    public Mensaje deleteUsuarioRestricciones(Usuario entity, String uuidCxnMaestra) {
        return usuarioDAO.deleteUsuarioRestricciones(entity, uuidCxnMaestra);
    }

    public Mensaje getAccesoCorrecto(String apodo, String password, String uuidCxnMaestra) {
        return usuarioDAO.getAccesoCorrecto(apodo, password, uuidCxnMaestra);
    }

    public Mensaje getAccesoCorrectoConRazonSocialYRazonesSociales(String apodo, String password, String uuidCxn, String uuidCxnMaestra) {
        return usuarioDAO.getAccesoCorrectoConRazonSocialYRazonesSociales(apodo, password, uuidCxn, uuidCxnMaestra);
    }

    public UsuarioDAO getUsuarioDAO() {
        return usuarioDAO;
    }

    public void setUsuarioDAO(UsuarioDAO usuarioDAO) {
        this.usuarioDAO = usuarioDAO;
    }

}
