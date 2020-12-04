/**
 * @author: Daniel
 * Fecha de Creación: --/--/--
 * Compañía: FineSoft
 * Descripción del programa: clase Bancos para llamados a metodos de HIBERNATE
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

import com.mef.erp.modelo.entidad.Bancos;
import com.mef.erp.modelo.entidad.Contactos;
import com.mef.erp.modelo.entidad.Mensaje;
import java.util.List;

/**
 *
 * @author daniel
 */
public interface BancosDAOIF extends GenericDAO<Bancos, Long> {

    public Mensaje agregar(Bancos entity, String uuidCxn);//JSA01

    public Mensaje actualizar(Bancos entity, String uuidCxn);//JSA01

    public Mensaje eliminar(Bancos entity, String uuidCxn);//JSA01

    public Mensaje getBancosAll(String uuidCxn);

    public Mensaje getBancosPorClave(String clave, String uuidCxn);

    Mensaje SaveBanco(List<Contactos> agrega, Object[] eliminados, Bancos entity, String uuidCxn);//JSA01

    Mensaje DeleteBanco(Bancos entity, String uuidCxn);//JSA01

    Mensaje UpdateBanco(List<Contactos> agrega, Object[] eliminados, Bancos entity, String uuidCxn);//JSA01

    Mensaje consultaPorFiltrosBancos(String query, Object[] campos, Object[] valores, String uuidCxn);//JSA01

    Mensaje consultaPorRangos(Integer inicio, Integer rango, String uuidCxn);//JSA01

    Mensaje existeDato(String campo, Object valor, String uuidCxn);//JSA01

    Mensaje agregarListaBancos(List<Bancos> cambios, List<Bancos> temporales, List<Contactos> cambioContactos, Object[] clavesContactosDelete, int rango, String uuidCxn);//JSA01

    Mensaje existeRFC(String rfc, String claveBancos, String uuidCxn);
}
