/**
 * @author: Victor Lopez Fecha de Creación: 10/03/2012 Compañía: Macropro
 * Descripción del programa: interface servicio MovNominaBaseAfecta
 * -----------------------------------------------------------------------------
 * MODIFICACIONES:
 * -----------------------------------------------------------------------------
 * Clave: Autor: Fecha: Descripción:
 * -----------------------------------------------------------------------------
 * Clave: Autor: Fecha: Descripción:
 * -----------------------------------------------------------------------------
 */
package com.mef.erp.servicios;

import com.mef.erp.modelo.entidad.Mensaje;
import com.mef.erp.modelo.entidad.MovNomBaseAfecta;
import java.util.List;

public interface ServicioMovNominaBaseAfectaIF {

    /*List<MovNomBaseAfecta>*/
    Mensaje getMovNominaBaseAfectaAll(String uuidCxn);

    /*List<MovNomBaseAfecta>*/
    Mensaje getMovNominaBaseAfectaAsc(String uuidCxn);

    /*MovNomBaseAfecta*/
    Mensaje saveDeleteMovNominaBaseAfecta(List<MovNomBaseAfecta> AgreModif, List<MovNomBaseAfecta> Ordenados, Object[] clavesDelete, String uuidCxn);

}
