/**
 * @author: Ernesto Valenzuela Fecha de Creación: 13/04/2011 Compañía: Exito
 * Software. Descripción del programa: Interface primas para llamados a metodos
 * de HIBERNATE
 * -----------------------------------------------------------------------------
 * MODIFICACIONES:
 * -----------------------------------------------------------------------------
 * Clave:JSA02 Autor:Jose Armando Sanchez Acosta Fecha:10/11/2011 Descripción:Se
 * cambiaron todos los metodos hibernate al dao
 * -----------------------------------------------------------------------------
 * Clave: Autor: Fecha: Descripción:
 * -----------------------------------------------------------------------------
 */
package com.mef.erp.modelo;

import com.mef.erp.modelo.entidad.Mensaje;
import com.mef.erp.modelo.entidad.Primas;
import com.mef.erp.modelo.entidad.RegistroPatronal;
import java.util.List;

public interface PrimasIF {

    public Mensaje agregar(Primas entity, String uuidCxn);//JSA02

    public Mensaje actualizar(Primas entity, String uuidCxn);//JSA02

    public Mensaje eliminar(Primas entity, String uuidCxn);//JSA02

    public Mensaje getPrimasAll(String uuidCxn);//JSA02

    public Mensaje getPrimasPorClaveRegistroPatronal(Long clave, String uuidCxn);//JSA02

    public Mensaje DeletePrimasByRegistroPatronal(Long registroPatronal, String uuidCxn);//JSA02

    public Mensaje SavePrimas(List<Primas> agrega, List<Primas> elimina, RegistroPatronal r, String uuidCxn);//JSA02

    public Mensaje DeletePrimas(List<Primas> p, String uuidCxn);//JSA02

    Mensaje deleteListClavesPrimas(Object[] valores, String uuidCxn);
}
