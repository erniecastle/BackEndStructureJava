/**
 * @author: Victor Lopez Fecha de Creación: 15/03/2011 Compañía: MacroPro.
 * Descripción del programa: clase SERVICIO Contenedor Pezonalizado, para
 * llamados a metodos de objeto DAO
 * -----------------------------------------------------------------------------
 * MODIFICACIONES:
 * -----------------------------------------------------------------------------
 * Clave:JSA01 Autor:Jose Armando Sanchez Acosta Fecha:10/11/2011 Descripción:Se
 * cambiaron todos los metodos hibernate al dao
 * -----------------------------------------------------------------------------
 * Clave: Autor: Fecha: Descripción:
 * -----------------------------------------------------------------------------
 */
package com.mef.erp.servicios;

import com.mef.erp.modelo.ExternoPersonalizadoDAO;
import com.mef.erp.modelo.entidad.ExternoPersonalizado;
import com.mef.erp.modelo.entidad.Herramienta;
import com.mef.erp.modelo.entidad.Mensaje;
import com.mef.erp.modelo.entidad.Perfiles;
import com.mef.erp.modelo.entidad.Usuario;

public class ServicioExternoPersonalizado implements ServicioExternoPersonalizadoIF {

    private ExternoPersonalizadoDAO externoPersonalizadoDAO;
  

    public Mensaje getExternoPersonalAll(String uuidCxn) {//JSA01
        return externoPersonalizadoDAO.getExternoPersonalAll(uuidCxn);
    }

    public Mensaje getExternoPersonalPerfilHerr(Perfiles perfil, Herramienta herramienta, String uuidCxn) {//JSA01
        return externoPersonalizadoDAO.getExternoPersonalPerfilHerr(perfil, herramienta, uuidCxn);
    }

    public Mensaje getExternoPersonalUsuarioHerr(Usuario user, Herramienta herramienta, String uuidCxn) {//JSA01
        return externoPersonalizadoDAO.getExternoPersonalUsuarioHerr(user, herramienta, uuidCxn);
    }

    public Mensaje getExternosPersonalPerfil(Perfiles perfil, String uuidCxn) {//JSA01
        return externoPersonalizadoDAO.getExternosPersonalPerfil(perfil, uuidCxn);
    }

    public Mensaje getExternosPersonalUsuario(Usuario user, String uuidCxn) {//JSA01
        return externoPersonalizadoDAO.getExternosPersonalUsuario(user, uuidCxn);
    }

    public Mensaje agregar(ExternoPersonalizado entity, String uuidCxn) {//JSA01
        return externoPersonalizadoDAO.agregar(entity, uuidCxn);
    }

    public Mensaje eliminar(ExternoPersonalizado entity, String uuidCxn) {//JSA01
        return externoPersonalizadoDAO.eliminar(entity, uuidCxn);
    }

    public Mensaje actualizar(ExternoPersonalizado entity, String uuidCxn) {//JSA01
        return externoPersonalizadoDAO.actualizar(entity, uuidCxn);
    }

    public Mensaje getExternosPersonalPerfilSinUsuarios(Perfiles perfil, String uuidCxn) {
        return getExternoPersonalizadoDAO().getExternosPersonalPerfilSinUsuarios(perfil, uuidCxn);
    }

    public ExternoPersonalizadoDAO getExternoPersonalizadoDAO() {
        return externoPersonalizadoDAO;
    }

    public void setExternoPersonalizadoDAO(ExternoPersonalizadoDAO externoPersonalizadoDAO) {
        this.externoPersonalizadoDAO = externoPersonalizadoDAO;
    }
}
