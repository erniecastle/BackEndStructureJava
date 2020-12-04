/**
 * @author: Daniel Fecha de Creación: --/--/-- Compañía: FineSoft Descripción
 * del programa: clase Bancos para llamados a metodos de HIBERNATE
 * -----------------------------------------------------------------------------
 * MODIFICACIONES:
 * -----------------------------------------------------------------------------
 * Clave:JSA01 Autor:Jose Armando Sanchez Acosta Fecha:10/11/2011 Descripción:Se
 * cambiaron todos los metodos hibernate al dao
 * -----------------------------------------------------------------------------
 */
package com.mef.erp.servicios;

import com.mef.erp.modelo.entidad.Bancos;
import com.mef.erp.modelo.entidad.Contactos;
import com.mef.erp.modelo.entidad.Mensaje;
import java.util.List;

/**
 *
 * @author daniel
 */
public interface ServicioBancosIF {

    /*Bancos*/
    public Mensaje agregar(Bancos entity, String uuidCxn);

    /*boolean*/
    public Mensaje actualizar(Bancos entity, String uuidCxn);

    /*boolean*/
    public Mensaje eliminar(Bancos entity, String uuidCxn);

	/*List<Bancos>*/
    public Mensaje getBancosAll(String uuidCxn);

    /*Bancos*/
    public Mensaje getBancosPorClave(String clave, String uuidCxn);

    /*Bancos*/
    Mensaje SaveBanco(List<Contactos> agrega, Object[] eliminados, Bancos entity, String uuidCxn);

    /*boolean*/
    Mensaje DeleteBanco(Bancos entity, String uuidCxn);

    /*boolean*/
    Mensaje UpdateBanco(List<Contactos> agrega, Object[] eliminados, Bancos entity, String uuidCxn);

	/*List<Bancos>*/
    Mensaje consultaPorFiltrosBancos(String query, Object[] campos, Object[] valores, String uuidCxn);

    /*List<Bancos>*/
    Mensaje consultaPorRangos(Integer inicio, Integer rango, String uuidCxn);

    /*Boolean*/
    Mensaje existeDato(String campo, Object valor, String uuidCxn);

    /*ArrayList*/
    Mensaje agregarListaBancos(List<Bancos> cambios, List<Bancos> temporales, List<Contactos> cambioContactos, Object[] clavesContactosDelete, int rango, String uuidCxn);

    /*Boolean*/
    Mensaje existeRFC(String rfc, String claveBancos, String uuidCxn);
    
}
