/**
 * @author: Ernesto Valenzuela Fecha de Creación: 06/09/2013 Compañía: Exito
 * Software. Descripción del programa: Interface para servicio Creditos
 * Movimientos
 * -----------------------------------------------------------------------------
 * MODIFICACIONES:
 * -----------------------------------------------------------------------------
 * Clave: Autor: Fecha: Descripción:
 * -----------------------------------------------------------------------------
 * Clave: Autor: Fecha: Descripción:
 * -----------------------------------------------------------------------------
 */
package com.mef.erp.servicios;

import com.mef.erp.modelo.entidad.CreditoMovimientos;
import com.mef.erp.modelo.entidad.CreditoPorEmpleado;
import com.mef.erp.modelo.entidad.Mensaje;
import com.mef.erp.modelo.entidad.TiposMovimiento;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Ernesto
 */
public interface ServicioCreditoMovimientosIF {

    /*List<CreditoMovimientos>*/
    Mensaje getCreditoMovimientos(Date fecha, String tipoCredito, TiposMovimiento tipoMovimiento, String razonesSociales, String tipoConfiguracion, String uuidCxn);

    /*Object*/
    Mensaje getMaxNumeroCreditoMovimiento(CreditoPorEmpleado credito, TiposMovimiento tiposMovimiento, Date fecha, String uuidCxn);

    /*CreditoMovimientos*/
    Mensaje saveDeleteCreditosMov(List<CreditoMovimientos> entitysCambios, List<CreditoMovimientos> deleteCreditos, TiposMovimiento tiposMovimiento, String uuidCxn);

}
