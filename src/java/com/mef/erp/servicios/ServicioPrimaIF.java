/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mef.erp.servicios;

import com.mef.erp.modelo.entidad.Mensaje;
import com.mef.erp.modelo.entidad.Primas;
import com.mef.erp.modelo.entidad.RegistroPatronal;
import java.util.List;

/**
 *
 * @author Ernesto Castillo
 */
public interface ServicioPrimaIF {

    /*Primas*/
    public Mensaje agregar(Primas entity, String uuidCxn);

    /*boolean*/
    public Mensaje actualizar(Primas entity, String uuidCxn);

    /*boolean*/
    public Mensaje eliminar(Primas entity, String uuidCxn);

    /*List<Primas>*/
    public Mensaje getPrimasAll(String uuidCxn);

    /*List<Primas>*/
    public Mensaje getPrimasPorClaveRegistroPatronal(Long clave, String uuidCxn);

    /*Boolean*/
    public Mensaje DeletePrimasByRegistroPatronal(Long registroPatronal, String uuidCxn);

    /*boolean*/
    public Mensaje SavePrimas(List<Primas> agrega, List<Primas> elimina, RegistroPatronal r, String uuidCxn);

    /*boolean*/
    public Mensaje DeletePrimas(List<Primas> p, String uuidCxn);

    /*boolean*/
    Mensaje deleteListClavesPrimas(Object[] valores, String uuidCxn);
}
