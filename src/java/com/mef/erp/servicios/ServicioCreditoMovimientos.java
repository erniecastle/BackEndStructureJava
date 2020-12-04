/**
 * @author: Ernesto Valenzuela Castillo Fecha de Creación: 06/09/2013 Compañía:
 * Exito Software. Descripción del programa: clase para Servicio creditos
 * Movimientos
 * -----------------------------------------------------------------------------
 * MODIFICACIONES:
 * -----------------------------------------------------------------------------
 * -----------------------------------------------------------------------------
 * Clave: Autor: Fecha: Descripción:
 * -----------------------------------------------------------------------------
 */
package com.mef.erp.servicios;

import com.mef.erp.modelo.CreditoMovimientosDAO;
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
public class ServicioCreditoMovimientos implements ServicioCreditoMovimientosIF {

    private CreditoMovimientosDAO creditoMovimientosDAO;
   

    public Mensaje getCreditoMovimientos(Date fecha, String tipoCredito, TiposMovimiento tipoMovimiento, String razonesSociales, String tipoConfiguracion,String uuidCxn) {
       
        return getCreditoMovimientosDAO().getCreditoMovimientos(fecha, tipoCredito, tipoMovimiento, razonesSociales, tipoConfiguracion,uuidCxn);
    }

    public Mensaje getMaxNumeroCreditoMovimiento(CreditoPorEmpleado credito, TiposMovimiento tiposMovimiento, Date fecha,String uuidCxn) {
        
        return getCreditoMovimientosDAO().getMaxNumeroCreditoMovimiento(credito, tiposMovimiento, fecha,uuidCxn);
    }

    public Mensaje saveDeleteCreditosMov(List<CreditoMovimientos> entitysCambios, List<CreditoMovimientos> deleteCreditos, TiposMovimiento tiposMovimiento,String uuidCxn) {
       
        return getCreditoMovimientosDAO().saveDeleteCreditosMov(entitysCambios, deleteCreditos, tiposMovimiento,uuidCxn);
    }

    public CreditoMovimientosDAO getCreditoMovimientosDAO() {
        return creditoMovimientosDAO;
    }

    public void setCreditoMovimientosDAO(CreditoMovimientosDAO creditoMovimientosDAO) {
        this.creditoMovimientosDAO = creditoMovimientosDAO;
    }
}
