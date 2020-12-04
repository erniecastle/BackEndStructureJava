/**
 * @author: Ernesto Valenzuela castillo Fecha de Creación: 06/09/2013 Compañía:
 * Exito Software. Descripción del programa: interface de clase de
 * CreditoMovimientos para llamados a metodos de HIBERNATE
 * -----------------------------------------------------------------------------
 * MODIFICACIONES:
 * -----------------------------------------------------------------------------
 * Clave: Autor: Fecha: Descripción:
 * -----------------------------------------------------------------------------
 * Clave: Autor: Fecha: Descripción:
 * -----------------------------------------------------------------------------
 */
package com.mef.erp.modelo;

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
public interface CreditoMovimientosIF {

    Mensaje getCreditoMovimientos(Date fecha, String tipoCredito, TiposMovimiento tipoMovimiento, String razonesSociales, String tipoConfiguracion,String uuidCxn);

    Mensaje getMaxNumeroCreditoMovimiento(CreditoPorEmpleado credito, TiposMovimiento tiposMovimiento, Date fecha,String uuidCxn);

    Mensaje saveDeleteCreditosMov(List<CreditoMovimientos> entitysCambios, List<CreditoMovimientos> deleteCreditos, TiposMovimiento tiposMovimiento,String uuidCxn);
}
