/**
 * @author: Victor Lopez
 * Fecha de Creación: 10/03/2012
 * Compañía: Macropro
 * Descripción del programa: interface de clase MovNominaBaseAfecta para llamado metodos Hibernate
 * -----------------------------------------------------------------------------
 * MODIFICACIONES:
 * -----------------------------------------------------------------------------
 * Clave:
 * Autor:
 * Fecha:
 * Descripción:
 * -----------------------------------------------------------------------------
 * Clave:
 * Autor:
 * Fecha:
 * Descripción:
 * -----------------------------------------------------------------------------
 */
package com.mef.erp.modelo;

import com.mef.erp.modelo.entidad.Mensaje;
import com.mef.erp.modelo.entidad.MovNomBaseAfecta;
import java.util.List;

public interface MovNominaBaseAfectaIF extends GenericDAO<MovNomBaseAfecta, Long> {

    Mensaje getMovNominaBaseAfectaAll(String uuidCxn);

    Mensaje getMovNominaBaseAfectaAsc(String uuidCxn);

    Mensaje saveDeleteMovNominaBaseAfecta(List<MovNomBaseAfecta> AgreModif, List<MovNomBaseAfecta> Ordenados, Object[] clavesDelete,String uuidCxn);//JSA01
}
