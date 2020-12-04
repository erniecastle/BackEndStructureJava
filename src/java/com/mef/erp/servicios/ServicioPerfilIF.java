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

import com.mef.erp.modelo.entidad.Mensaje;
import com.mef.erp.modelo.entidad.Perfiles;
import com.mef.erp.modelo.entidad.Permisos;
import java.util.List;

/**
 *
 * @author Jose Armando
 */
public interface ServicioPerfilIF {

    /*List<Perfiles>*/
    public Mensaje getPerfilAll( String  uuidCxnMaestra);

    /*Perfiles*/
    public Mensaje getPerfilesPorClave(String clave, String  uuidCxnMaestra);

    /*Boolean*/
    public Mensaje existeDato(String campo, Object valor, String  uuidCxnMaestra);

    /*Perfiles*/
    public Mensaje savePerfilMenusPermisos(Perfiles entity, List<Object> menus, List<Permisos> permisos, String  uuidCxnMaestra);

    /*boolean*/
    public Mensaje updatePerfilMenusPermisos(Perfiles entity, List<Object> menus, List<Permisos> permisos, String  uuidCxnMaestra);

    /*boolean*/
    public Mensaje deletePerfilMenusPermisos(Perfiles entity, List<Permisos> permisos, String  uuidCxnMaestra);//JSA03
}
