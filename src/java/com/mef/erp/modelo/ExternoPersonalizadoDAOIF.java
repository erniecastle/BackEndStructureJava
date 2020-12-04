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
 * Clave:JSA02 Autor:Jose Armando Fecha:31/07/2014 Descripción: se agrego el
 * metodo getExternosPersonalPerfilSinUsuarios para obtener la informacion de
 * los externos que no tengan usuarios.
 * -----------------------------------------------------------------------------
 */
package com.mef.erp.modelo;

import com.mef.erp.modelo.entidad.ExternoPersonalizado;
import com.mef.erp.modelo.entidad.Herramienta;
import com.mef.erp.modelo.entidad.Mensaje;
import com.mef.erp.modelo.entidad.Perfiles;
import com.mef.erp.modelo.entidad.Usuario;

/**
 *
 * @author Jose Armando
 */
public interface ExternoPersonalizadoDAOIF extends GenericDAO<ExternoPersonalizado, Integer> {

    public Mensaje getExternoPersonalAll(String uuidCxn);//JSA01

    public Mensaje getExternoPersonalPerfilHerr(Perfiles perfil, Herramienta herramienta, String uuidCxn);//JSA01

    public Mensaje getExternoPersonalUsuarioHerr(Usuario user, Herramienta herramienta, String uuidCxn);//JSA01

    public Mensaje getExternosPersonalPerfil(Perfiles perfil, String uuidCxn);//JSA01

    public Mensaje getExternosPersonalPerfilSinUsuarios(Perfiles perfil, String uuidCxn);//JSA02

    public Mensaje getExternosPersonalUsuario(Usuario user, String uuidCxn);//JSA01

    public Mensaje agregar(ExternoPersonalizado entity, String uuidCxn);//JSA01

    public Mensaje actualizar(ExternoPersonalizado entity, String uuidCxn);//JSA01

    public Mensaje eliminar(ExternoPersonalizado entity, String uuidCxn);//JSA01
}
