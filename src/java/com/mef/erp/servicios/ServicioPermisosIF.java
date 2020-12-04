/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mef.erp.servicios;

import com.mef.erp.modelo.entidad.*;
import java.util.List;

/**
 *
 * @author Jose Armando
 */
public interface ServicioPermisosIF {

    /*List<Permisos>*/
    public Mensaje getPermisosAll(String uuidCxnMaestra);

    /*Permisos*/
    public Mensaje agregar(Permisos entity, String uuidCxnMaestra);

    /*boolean*/
    public Mensaje actualizar(Permisos entity, String uuidCxnMaestra);

    /*boolean*/
    public Mensaje eliminar(Permisos entity, String uuidCxnMaestra);

    /*Permisos*/
    public Mensaje getPermisosPorContenedor(Contenedor c, String uuidCxnMaestra);

    /*List<Permisos>*/
    public Mensaje getPermisosPorPerfil(Perfiles perfil, String uuidCxnMaestra);

    /*List<Permisos>*/
    public Mensaje getPermisosPorUsuario(Usuario user, String uuidCxnMaestra);

    /*Permisos*/
    Mensaje getPermisosTipoAccesoyModulo(Usuario usuario, String nombreModulo, String uuidCxnMaestra);

    /*boolean*/
    public Mensaje agregarListaPermisos(List<Permisos> entitys, int rango, String uuidCxnMaestra);

    /*List<Permisos>*/
    Mensaje getPermisosPorTipoVentanaySeccion(Object secion, TipoVentana[] tipoVentanas, String uuidCxnMaestra);
}
