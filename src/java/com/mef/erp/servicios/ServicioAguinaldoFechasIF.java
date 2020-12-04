/**
 * @author: Daniel Fecha de Creación: 30/12/2016 Compañía: Exito Software
 * Descripción del programa: clase Agunaldos fechas para llamados a metodos de
 * HIBERNATE
 * -----------------------------------------------------------------------------
 */
package com.mef.erp.servicios;

import com.mef.erp.modelo.entidad.AguinaldoFechas;
import com.mef.erp.modelo.entidad.Mensaje;
import java.util.List;

/**
 *
 *
 */
public interface ServicioAguinaldoFechasIF {

    /*AguinaldoFechas*/
    public Mensaje agregar(AguinaldoFechas entity, String uuidCxn);
    /*boolean*/

    public Mensaje actualizar(AguinaldoFechas entity, String uuidCxn);
    /*boolean*/

    public Mensaje eliminar(AguinaldoFechas entity, String uuidCxn);
    /*List<AguinaldoFechas>*/

    public Mensaje getAguinaldoFechasAll(String uuidCxn);
    /*boolean*/

    Mensaje SaveAguinaldoFechas(List<AguinaldoFechas> agrega, Object[] eliminados, String uuidCxn);

    /*List<AguinaldoFechas>*/
    Mensaje getAguinaldoFechasPorClave(String claveRazonsocial, String uuidCxn);
}
