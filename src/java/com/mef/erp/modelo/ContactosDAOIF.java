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
 * 
 */
package com.mef.erp.modelo;

import com.mef.erp.modelo.entidad.Bancos;
import com.mef.erp.modelo.entidad.Contactos;
import com.mef.erp.modelo.entidad.Mensaje;

/**
 *
 * @author daniel
 */
public interface ContactosDAOIF {

    public Mensaje agregar(Contactos entity, String uuidCxn);//JSA01

    public Mensaje actualizar(Contactos entity, String uuidCxn);//JSA01

    public Mensaje eliminar(Contactos entity, String uuidCxn);//JSA01

    public Mensaje getContactosAll(String uuidCxn);//JSA01

    public Mensaje getContactosPorId(Long id, String uuidCxn);//JSA01

    public Mensaje getContactosPorBancos(Bancos b, String uuidCxn);//JSA01

    public Mensaje getContactosPorIDBanco(Long clave, String uuidCxn);//JSA01

    public Mensaje eliminaContactosPorBanco(Bancos banco, String uuidCxn);//JSA01
}
