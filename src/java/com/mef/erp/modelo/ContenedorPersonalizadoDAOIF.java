/**
 * @author: Victor Lopez Fecha de Creación: 15/03/2011 Compañía: MacroPro.
 * Descripción del programa:
 * -----------------------------------------------------------------------------
 * MODIFICACIONES:
 * -----------------------------------------------------------------------------
 * Clave:JSA01 Autor:Jose Armando Fecha:31/07/2014 Descripción: se agrego el
 * metodo getContenedoresPersonalesSinUsuarios para obtener la informacion de
 * los contenedores que no tengan usuarios.
 * -----------------------------------------------------------------------------
 */
package com.mef.erp.modelo;

import com.mef.erp.modelo.entidad.ContenedorPersonalizado;
import com.mef.erp.modelo.entidad.Herramienta;
import com.mef.erp.modelo.entidad.Mensaje;
import com.mef.erp.modelo.entidad.Perfiles;
import com.mef.erp.modelo.entidad.Usuario;
import java.util.List;

/**
 *
 * @author Jose Armando
 */
public interface ContenedorPersonalizadoDAOIF extends GenericDAO<ContenedorPersonalizado, Integer> {

    public Mensaje getContenedorPersonal(int parentId, Herramienta herramienta, String uuidCxn);

    public Mensaje getContenedorPersonalAll(String uuidCxn);

    public Mensaje getContenedorPersonalHert(Herramienta herramienta, String uuidCxn);

    public Mensaje getContenedorPersonalMax(String uuidCxn);

    public Mensaje getContenedorPersonalPerfilHerr(Perfiles perfil, Herramienta herramienta, String uuidCxn);

    public Mensaje getContenedorPersonalUsuarioHerr(Usuario user, Herramienta herramienta, String uuidCxn);

    public Mensaje getContenedoresPersonalPerfil(Perfiles perfil, String uuidCxn);

    public Mensaje getContenedoresPersonalesSinUsuarios(Perfiles perfil, String uuidCxn);//JSA01

    public Mensaje getContenedoresPersonalUsuario(Usuario user, String uuidCxn);

    public Mensaje agregar(ContenedorPersonalizado entity, String uuidCxn);

    public Mensaje actualizar(ContenedorPersonalizado entity, String uuidCxn);

    public Mensaje eliminar(ContenedorPersonalizado entity, String uuidCxn);
}
