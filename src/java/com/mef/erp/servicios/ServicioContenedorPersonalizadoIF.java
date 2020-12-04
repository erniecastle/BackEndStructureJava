/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mef.erp.servicios;

import com.mef.erp.modelo.entidad.ContenedorPersonalizado;
import com.mef.erp.modelo.entidad.Herramienta;
import com.mef.erp.modelo.entidad.Mensaje;
import com.mef.erp.modelo.entidad.Perfiles;
import com.mef.erp.modelo.entidad.Usuario;

/**
 *
 * @author Jose Armando
 */
public interface ServicioContenedorPersonalizadoIF {

    /*List<ContenedorPersonalizado>*/
    public Mensaje getContenedorPersonal(int parentId, Herramienta herramienta,String uuidCxn);

    /*List<ContenedorPersonalizado>*/
    public Mensaje getContenedorPersonalAll(String uuidCxn);

    /*List<ContenedorPersonalizado>*/
    public Mensaje getContenedorPersonalHert(Herramienta herramienta,String uuidCxn);

    /*List<ContenedorPersonalizado>*/
    public Mensaje getContenedorPersonalMax(String uuidCxn);

    /*List<ContenedorPersonalizado>*/
    public Mensaje getContenedorPersonalPerfilHerr(Perfiles perfil, Herramienta herramienta,String uuidCxn);

    /*List<ContenedorPersonalizado>*/
    public Mensaje getContenedorPersonalUsuarioHerr(Usuario user, Herramienta herramienta,String uuidCxn);

    /*List<ContenedorPersonalizado>*/
    public Mensaje getContenedoresPersonalPerfil(Perfiles perfil,String uuidCxn);

    /*List<ContenedorPersonalizado>*/
    public Mensaje getContenedoresPersonalesSinUsuarios(Perfiles perfil,String uuidCxn);

    /*List<ContenedorPersonalizado>*/
    public Mensaje getContenedoresPersonalUsuario(Usuario user,String uuidCxn);

    /*ContenedorPersonalizado*/
    public Mensaje agregar(ContenedorPersonalizado entity,String uuidCxn);

    /*boolean*/
    public Mensaje actualizar(ContenedorPersonalizado entity,String uuidCxn);

    /*boolean*/
    public Mensaje eliminar(ContenedorPersonalizado entity,String uuidCxn);
    
    
}
