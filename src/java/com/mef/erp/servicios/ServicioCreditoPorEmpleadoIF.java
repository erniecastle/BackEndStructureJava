/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mef.erp.servicios;

import com.mef.erp.modelo.entidad.CreditoPorEmpleado;
import com.mef.erp.modelo.entidad.Mensaje;
import java.util.Date;
import java.util.List;

/**
 *
 * @author daniel
 */
public interface ServicioCreditoPorEmpleadoIF {

    /*CreditoPorEmpleado*/
    Mensaje agregar(CreditoPorEmpleado entity,String uuidCxn);

    /*boolean*/
    Mensaje actualizar(CreditoPorEmpleado entity,String uuidCxn);

    /*boolean*/
    Mensaje eliminar(CreditoPorEmpleado entity,String uuidCxn);

    /*List<CreditoPorEmpleado>*/
    Mensaje getCreditosAll(String claveTipoCredito, String tipoConfiguracion,String uuidCxn);

    /*CreditoPorEmpleado*/
    Mensaje getCreditosPorClave(String numeroCredito, String claveTipoCredito, String tipoConfiguracion,String uuidCxn);

    /*List<CreditoPorEmpleado>*/
    Mensaje getCreditosPorTipoCredito(String claveTipoCredito, String tipoConfiguracion,String uuidCxn);

    /*boolean*/
    Mensaje existenMovimientosEnCreditos(String numeroDeCredito, String claveCreditoAhorro, String tipoConfiguracion,String uuidCxn);

    /*List<CreditoPorEmpleado>*/
    Mensaje getCreditosPorTipoCreditoYFecha(Date fecha, String tipoCredito, String claveRazonsocial, String tipoConfiguracion,String uuidCxn);

    /*CreditoPorEmpleado*/
    Mensaje saveDeleteCreditos(List<CreditoPorEmpleado> entitysCambios, Object[] eliminados,String uuidCxn);

    /*List<CreditoPorEmpleado>*/
    Mensaje consultaPorRangos(Integer inicio, Integer rango,String uuidCxn);

    /*Boolean*/
    Mensaje existeDato(String campo, Object valor,String uuidCxn);
    
}
