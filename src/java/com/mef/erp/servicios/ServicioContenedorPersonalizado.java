/**
 * @author: Victor Lopez Fecha de Creación: 15/03/2011 Compañía: MacroPro.
 * Descripción del programa: clase SERVICIO Contenedor Pezonalizado, para
 * llamados a metodos de objeto DAO
 * -----------------------------------------------------------------------------
 * MODIFICACIONES:
 * -----------------------------------------------------------------------------
 * Clave: Autor: Fecha: Descripción:
 * -----------------------------------------------------------------------------
 * Clave: Autor: Fecha: Descripción:
 * -----------------------------------------------------------------------------
 */
package com.mef.erp.servicios;

import com.mef.erp.modelo.ContenedorPersonalizadoDAO;
import com.mef.erp.modelo.entidad.ContenedorPersonalizado;
import com.mef.erp.modelo.entidad.Herramienta;
import com.mef.erp.modelo.entidad.Mensaje;
import com.mef.erp.modelo.entidad.Perfiles;
import com.mef.erp.modelo.entidad.Usuario;

public class ServicioContenedorPersonalizado implements ServicioContenedorPersonalizadoIF {

    private ContenedorPersonalizadoDAO contenedorPersonalizadoDAO;
  
 
    public Mensaje getContenedorPersonal(int parentId, Herramienta herramienta , String uuidCxn) {
       
        return contenedorPersonalizadoDAO.getContenedorPersonal(parentId, herramienta, uuidCxn);
    }

    public Mensaje getContenedorPersonalAll( String uuidCxn ) {
        
        return contenedorPersonalizadoDAO.getContenedorPersonalAll(uuidCxn);
    }

    public Mensaje getContenedorPersonalHert(Herramienta herramienta , String uuidCxn) {
 
        return contenedorPersonalizadoDAO.getContenedorPersonalHert(herramienta , uuidCxn);
    }

    public Mensaje getContenedorPersonalMax(String uuidCxn) {
        
        return contenedorPersonalizadoDAO.getContenedorPersonalMax(uuidCxn);
    }

    public Mensaje getContenedorPersonalPerfilHerr(Perfiles perfil, Herramienta herramienta , String uuidCxn) {
        
        return contenedorPersonalizadoDAO.getContenedorPersonalPerfilHerr(perfil, herramienta,uuidCxn);
    }

    public Mensaje getContenedorPersonalUsuarioHerr(Usuario user, Herramienta herramienta ,String uuidCxn) {
      
        return contenedorPersonalizadoDAO.getContenedorPersonalUsuarioHerr(user, herramienta,uuidCxn);
    }

    public Mensaje getContenedoresPersonalPerfil(Perfiles perfil,String uuidCxn) {
        
        return contenedorPersonalizadoDAO.getContenedoresPersonalPerfil(perfil,uuidCxn);
    }

    public Mensaje getContenedoresPersonalesSinUsuarios(Perfiles perfil , String uuidCxn) {
        
        return getContenedorPersonalizadoDAO().getContenedoresPersonalesSinUsuarios(perfil,uuidCxn);
    }

    public Mensaje getContenedoresPersonalUsuario(Usuario user , String uuidCxn) {
       
        return contenedorPersonalizadoDAO.getContenedoresPersonalUsuario(user,uuidCxn);
    }

    public Mensaje agregar(ContenedorPersonalizado entity , String uuidCxn) {
        return contenedorPersonalizadoDAO.agregar(entity,uuidCxn);
    }

    public Mensaje eliminar(ContenedorPersonalizado entity , String uuidCxn) {
        return contenedorPersonalizadoDAO.eliminar(entity,uuidCxn);
    }

    public Mensaje actualizar(ContenedorPersonalizado entity , String uuidCxn) {

        return contenedorPersonalizadoDAO.actualizar(entity,uuidCxn);
    }

    public ContenedorPersonalizadoDAO getContenedorPersonalizadoDAO() {
        return contenedorPersonalizadoDAO;
    }

    public void setContenedorPersonalizadoDAO(ContenedorPersonalizadoDAO contenedorPersonalizadoDAO) {
        this.contenedorPersonalizadoDAO = contenedorPersonalizadoDAO;
    }
}
