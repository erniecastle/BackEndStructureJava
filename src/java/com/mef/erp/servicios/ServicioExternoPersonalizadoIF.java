/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mef.erp.servicios;

import com.mef.erp.modelo.entidad.ExternoPersonalizado;
import com.mef.erp.modelo.entidad.Herramienta;
import com.mef.erp.modelo.entidad.Mensaje;
import com.mef.erp.modelo.entidad.Perfiles;
import com.mef.erp.modelo.entidad.Usuario;

/**
 *
 * @author Jose Armando
 */
public interface ServicioExternoPersonalizadoIF {

    /*List<ExternoPersonalizado>*/
    public Mensaje getExternoPersonalAll(String uuidCxn);

    /*List<ExternoPersonalizado>*/
    public Mensaje getExternoPersonalPerfilHerr(Perfiles perfil, Herramienta herramienta, String uuidCxn);

    /*List<ExternoPersonalizado>*/
    public Mensaje getExternoPersonalUsuarioHerr(Usuario user, Herramienta herramienta, String uuidCxn);

    /*List<ExternoPersonalizado>*/
    public Mensaje getExternosPersonalPerfil(Perfiles perfil, String uuidCxn);

    /*List<ExternoPersonalizado>*/
    public Mensaje getExternosPersonalPerfilSinUsuarios(Perfiles perfil, String uuidCxn);

    /*List<ExternoPersonalizado>*/
    public Mensaje getExternosPersonalUsuario(Usuario user, String uuidCxn);

    /*ExternoPersonalizado*/
    public Mensaje agregar(ExternoPersonalizado entity, String uuidCxn);

    /*boolean*/
    public Mensaje actualizar(ExternoPersonalizado entity, String uuidCxn);

    /*boolean*/
    public Mensaje eliminar(ExternoPersonalizado entity, String uuidCxn);

   
}
