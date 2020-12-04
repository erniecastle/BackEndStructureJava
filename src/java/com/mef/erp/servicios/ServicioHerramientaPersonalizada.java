/**
 * @author: Victor Fecha de Creación: 06/06/2011 Compañía: Macropro Descripción
 * del programa: clase HerramientaPersonalizada para llamados a metodos de
 * HIBERNATE
 * -----------------------------------------------------------------------------
 * MODIFICACIONES:
 * -----------------------------------------------------------------------------
 * Clave:JSA01 Autor:Jose Armando Sanchez Acosta Fecha:10/11/2011 Descripción:Se
 * cambiaron todos los metodos hibernate al dao
 * -----------------------------------------------------------------------------
 */
package com.mef.erp.servicios;

import com.mef.erp.modelo.HerramientaPersonalizadaDAO;
import com.mef.erp.modelo.entidad.Herramienta;
import com.mef.erp.modelo.entidad.HerramientaPersonalizada;
import com.mef.erp.modelo.entidad.Mensaje;
import com.mef.erp.modelo.entidad.Perfiles;
import com.mef.erp.modelo.entidad.Usuario;
import java.util.List;

/**
 *
 * @author Jose Armando
 */
public class ServicioHerramientaPersonalizada implements ServicioHerramientaPersonalizadaIF {

    private HerramientaPersonalizadaDAO herramientaPersonalizadaDAO;
   

    public Mensaje getHerramientaPersonalAll(String uuidCxn) {//JSA01
        return herramientaPersonalizadaDAO.getHerramientaPersonalAll(uuidCxn);
    }

    public Mensaje getHerramientaPersonalPrincipales(String uuidCxn) {//JSA01
        return herramientaPersonalizadaDAO.getHerramientaPersonalPrincipales(uuidCxn);
    }

    public Mensaje getHerramientaPersonalPerfilHerr(Perfiles perfil, Herramienta herramienta, String uuidCxn) {//JSA01
        return herramientaPersonalizadaDAO.getHerramientaPersonalPerfilHerr(perfil, herramienta, uuidCxn);
    }

    public Mensaje getHerramientaPersonalUsuarioHerr(Usuario user, Herramienta herramienta, String uuidCxn) {//JSA01
        return herramientaPersonalizadaDAO.getHerramientaPersonalUsuarioHerr(user, herramienta, uuidCxn);
    }

    public Mensaje agregar(HerramientaPersonalizada entity, String uuidCxn) {//JSA01
        return herramientaPersonalizadaDAO.agregar(entity, uuidCxn);
    }

    public Mensaje eliminar(HerramientaPersonalizada entity, String uuidCxn) {//JSA01
        return herramientaPersonalizadaDAO.eliminar(entity, uuidCxn);
    }

    public Mensaje actualizar(HerramientaPersonalizada entity, String uuidCxn) {//JSA01
        return herramientaPersonalizadaDAO.actualizar(entity, uuidCxn);
    }

    public Mensaje getHerramientasPersonalPerfil(Perfiles perfil, String uuidCxn) {//JSA01
        return herramientaPersonalizadaDAO.getHerramientasPersonalPerfil(perfil, uuidCxn);
    }

    public Mensaje getHerramientasPersonalUsuario(Usuario user, String uuidCxn) {//JSA01
        return herramientaPersonalizadaDAO.getHerramientasPersonalUsuario(user, uuidCxn);
    }

    public HerramientaPersonalizadaDAO getHerramientaPersonalizadaDAO() {
        return herramientaPersonalizadaDAO;
    }

    public void setHerramientaPersonalizadaDAO(HerramientaPersonalizadaDAO herramientaPersonalizadaDAO) {
        this.herramientaPersonalizadaDAO = herramientaPersonalizadaDAO;
    }
}
