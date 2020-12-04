/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mef.erp.modelo;

import com.mef.erp.modelo.entidad.*;
import java.util.List;

/**
 *
 * @author Jose Armando
 */
public interface PermisosDAOIF extends GenericDAO<Permisos, Integer> {

    public Mensaje getPermisosAll(String uuidCxnMaestra);

    public Mensaje agregar(Permisos entity, String uuidCxnMaestra);

    public Mensaje actualizar(Permisos entity, String uuidCxnMaestra);

    public Mensaje eliminar(Permisos entity, String uuidCxnMaestra);

    public Mensaje getPermisosPorContenedor(Contenedor c, String uuidCxnMaestra);

    public Mensaje getPermisosPorPerfil(Perfiles perfil, String uuidCxnMaestra);

    public Mensaje getPermisosPorUsuario(Usuario user, String uuidCxnMaestra);

    Mensaje getPermisosTipoAccesoyModulo(Usuario usuario, String nombreModulo, String uuidCxnMaestra);

    public Mensaje agregarListaPermisos(List<Permisos> entitys, int rango, String uuidCxnMaestra);

    Mensaje getPermisosPorTipoVentanaySeccion(Object secion, TipoVentana[] tipoVentanas, String uuidCxnMaestra);
}
