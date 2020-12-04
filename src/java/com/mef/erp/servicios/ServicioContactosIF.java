/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mef.erp.servicios;

import com.mef.erp.modelo.entidad.Bancos;
import com.mef.erp.modelo.entidad.Contactos;
import com.mef.erp.modelo.entidad.Mensaje;

/**
 *
 * @author daniel
 */
public interface ServicioContactosIF {
    
    /*Contactos*/
    public Mensaje agregar(Contactos entity, String uuidCxn);

    /*boolean*/
    public Mensaje actualizar(Contactos entity, String uuidCxn);

    /*boolean*/
    public Mensaje eliminar(Contactos entity, String uuidCxn);

    /*List<Contactos>*/
    public Mensaje getContactosAll(String uuidCxn);

    /*Contactos*/
    public Mensaje getContactosPorId(Long id, String uuidCxn);

    /*List<Contactos>*/
    public Mensaje getContactosPorBancos(Bancos b, String uuidCxn);

    /*List<Contactos>*/
    public Mensaje getContactosPorIDBanco(Long clave, String uuidCxn);
    
    /*boolean*/
    public Mensaje EliminaContactosPorBanco(Bancos banco, String uuidCxn);
 
}
