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
package com.mef.erp.modelo;

import com.mef.erp.modelo.entidad.Herramienta;
import com.mef.erp.modelo.entidad.HerramientaPersonalizada;
import com.mef.erp.modelo.entidad.Mensaje;
import com.mef.erp.modelo.entidad.Perfiles;
import com.mef.erp.modelo.entidad.Usuario;

/**
 *
 * @author Jose Armando
 */
public interface HerramientaPersonalizadaDAOIF extends GenericDAO<HerramientaPersonalizada, Long> {

    public Mensaje getHerramientaPersonalAll(String uuidCxn);//JSA01

    public Mensaje getHerramientaPersonalPrincipales(String uuidCxn);//JSA01

    public Mensaje getHerramientaPersonalPerfilHerr(Perfiles perfil, Herramienta herramienta,String uuidCxn);//JSA01

    public Mensaje getHerramientaPersonalUsuarioHerr(Usuario user, Herramienta herramienta,String uuidCxn);//JSA01

    public Mensaje getHerramientasPersonalPerfil(Perfiles perfil , String uuidCxn);//JSA01

    public Mensaje getHerramientasPersonalUsuario(Usuario user , String uuidCxn);//JSA01

    public Mensaje agregar(HerramientaPersonalizada entity , String uuidCxn);//JSA01

    public Mensaje actualizar(HerramientaPersonalizada entity , String uuidCxn);//JSA01

    public Mensaje eliminar(HerramientaPersonalizada entity , String uuidCxn);//JSA01
}
