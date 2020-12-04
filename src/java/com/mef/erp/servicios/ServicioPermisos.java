/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mef.erp.servicios;

import com.mef.erp.modelo.PermisosDAO;
import com.mef.erp.modelo.entidad.*;
import java.util.List;

/**
 *
 * @author Jose Armando
 */
public class ServicioPermisos implements ServicioPermisosIF {

    private PermisosDAO permisosDAO;

    public Mensaje getPermisosAll(String uuidCxnMaestra) {
        return permisosDAO.getPermisosAll(uuidCxnMaestra);
    }

    public Mensaje agregar(Permisos entity, String uuidCxnMaestra) {
        return permisosDAO.agregar(entity, uuidCxnMaestra);
    }

    public Mensaje eliminar(Permisos entity, String uuidCxnMaestra) {
        return permisosDAO.eliminar(entity, uuidCxnMaestra);
    }

    public Mensaje actualizar(Permisos entity, String uuidCxnMaestra) {
        return permisosDAO.actualizar(entity, uuidCxnMaestra);
    }

    public Mensaje getPermisosPorContenedor(Contenedor c, String uuidCxnMaestra) {
        return permisosDAO.getPermisosPorContenedor(c, uuidCxnMaestra);
    }

    public Mensaje getPermisosPorPerfil(Perfiles perfil, String uuidCxnMaestra) {
        return permisosDAO.getPermisosPorPerfil(perfil, uuidCxnMaestra);
    }

    public Mensaje getPermisosPorUsuario(Usuario user, String uuidCxnMaestra) {
        return permisosDAO.getPermisosPorUsuario(user, uuidCxnMaestra);
    }

    public Mensaje getPermisosTipoAccesoyModulo(Usuario usuario, String nombreModulo, String uuidCxnMaestra) {
        return permisosDAO.getPermisosTipoAccesoyModulo(usuario, nombreModulo, uuidCxnMaestra);
    }

    public Mensaje agregarListaPermisos(List<Permisos> entitys, int rango, String uuidCxnMaestra) {
        return permisosDAO.agregarListaPermisos(entitys, rango, uuidCxnMaestra);
    }

    public Mensaje getPermisosPorTipoVentanaySeccion(Object secion, TipoVentana[] tipoVentanas, String uuidCxnMaestra) {
        return permisosDAO.getPermisosPorTipoVentanaySeccion(secion, tipoVentanas, uuidCxnMaestra);
    }

    public PermisosDAO getPermisosDAO() {
        return permisosDAO;
    }

    public void setPermisosDAO(PermisosDAO permisosDAO) {
        this.permisosDAO = permisosDAO;
    }

}
