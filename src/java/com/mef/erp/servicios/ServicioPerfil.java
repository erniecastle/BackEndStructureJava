/**
 * @author: Fecha de Creación: 15/03/2011 Compañía: MacroPro. Descripción del
 * programa: clase SERVICIO Perfiles, para llamados a metodos de objeto DAO
 * -----------------------------------------------------------------------------
 * MODIFICACIONES:
 * -----------------------------------------------------------------------------
 * Clave:JSA01 Autor:Jose Armando Sanchez Acosta Fecha:10/11/2011 Descripción:Se
 * cambiaron todos los metodos hibernate al dao
 * -----------------------------------------------------------------------------
 * Clave:JSA03 Autor:Jose Armando Sanchez Acosta Fecha:14/11/2014 Descripción:SE
 * AGREGO EL METODO deletePerfilMenusPermisos PARA CUANDO SE ELIMINA UN PERFIL
 * EL METODO NORMAL ELIMINAR NO FUNCIONA YA QUE TENEMOS QUE BORRAR PRIMERO LOS
 * PERMISOS.
 * -----------------------------------------------------------------------------
 * Clave: Autor: Fecha: Descripción:
 * -----------------------------------------------------------------------------
 */
package com.mef.erp.servicios;

import com.mef.erp.modelo.PerfilDAO;
import com.mef.erp.modelo.entidad.Mensaje;
import com.mef.erp.modelo.entidad.Perfiles;
import com.mef.erp.modelo.entidad.Permisos;
import java.util.List;

public class ServicioPerfil implements ServicioPerfilIF {

    private PerfilDAO perfilDAO;

    public Mensaje getPerfilAll(String uuidCxnMaestra) {//JSA01
        return perfilDAO.getPerfilAll(uuidCxnMaestra);
    }

    public Mensaje getPerfilesPorClave(String clave, String uuidCxnMaestra) {//JSA01
        return perfilDAO.getPerfilesPorClave(clave, uuidCxnMaestra);
    }

    public Mensaje existeDato(String campo, Object valor, String uuidCxnMaestra) {//JSA01
        return perfilDAO.existeDato(campo, valor, uuidCxnMaestra);
    }

    public Mensaje savePerfilMenusPermisos(Perfiles entity, List<Object> menus, List<Permisos> permisos, String uuidCxnMaestra) {
        return perfilDAO.savePerfilMenusPermisos(entity, menus, permisos, uuidCxnMaestra);
    }

    public Mensaje updatePerfilMenusPermisos(Perfiles entity, List<Object> menus, List<Permisos> permisos, String uuidCxnMaestra) {
        return  perfilDAO.savePerfilMenusPermisos(entity, menus, permisos, uuidCxnMaestra);
    }

    public Mensaje deletePerfilMenusPermisos(Perfiles entity, List<Permisos> permisos, String uuidCxnMaestra) {//JSA03
        return getPerfilDAO().deletePerfilMenusPermisos(entity, permisos, uuidCxnMaestra);
    }

    public PerfilDAO getPerfilDAO() {
        return perfilDAO;
    }

    public void setPerfilDAO(PerfilDAO PerfilDAO) {
        this.perfilDAO = PerfilDAO;
    }
}
