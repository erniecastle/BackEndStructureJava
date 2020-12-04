/**
 * @author: Victor
 * Fecha de Creación: 06/06/2011
 * Compañía: Macropro
 * Descripción del programa: clase HerramientaPersonalizada para llamados a metodos de HIBERNATE
 * -----------------------------------------------------------------------------
 * MODIFICACIONES:
 * -----------------------------------------------------------------------------
 * Clave:JSA01
 * Autor:Jose Armando Sanchez Acosta
 * Fecha:10/11/2011
 * Descripción:Se cambiaron todos los metodos hibernate al dao
 * -----------------------------------------------------------------------------
 */
package com.mef.erp.servicios;

import com.mef.erp.modelo.entidad.Herramienta;
import com.mef.erp.modelo.entidad.HerramientaPersonalizada;
import com.mef.erp.modelo.entidad.Mensaje;
import com.mef.erp.modelo.entidad.Perfiles;
import com.mef.erp.modelo.entidad.Usuario;

/**
 *
 * @author Jose Armando
 */
public interface ServicioHerramientaPersonalizadaIF {

    /*List<HerramientaPersonalizada>*/
    public Mensaje getHerramientaPersonalAll(String uuidCxn);

    /*List<HerramientaPersonalizada>*/
    public Mensaje getHerramientaPersonalPrincipales(String uuidCxn);

    /*List<HerramientaPersonalizada>*/
    public Mensaje getHerramientaPersonalPerfilHerr(Perfiles perfil, Herramienta herramienta , String uuidCxn);

    /*List<HerramientaPersonalizada>*/
    public Mensaje getHerramientaPersonalUsuarioHerr(Usuario user, Herramienta herramienta , String uuidCxn);

    /*List<HerramientaPersonalizada>*/
    public Mensaje getHerramientasPersonalPerfil(Perfiles perfil , String uuidCxn);

    /*List<HerramientaPersonalizada>*/
    public Mensaje getHerramientasPersonalUsuario(Usuario user , String uuidCxn);

    /*HerramientaPersonalizada*/
    public Mensaje agregar(HerramientaPersonalizada entity , String uuidCxn);

    /*boolean*/
    public Mensaje actualizar(HerramientaPersonalizada entity , String uuidCxn);

    /*boolean*/
    public Mensaje eliminar(HerramientaPersonalizada entity , String uuidCxn);
    
    
}
