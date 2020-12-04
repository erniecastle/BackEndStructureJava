/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mef.erp.modelo;

import com.mef.erp.modelo.entidad.CreditoPorEmpleado;
import com.mef.erp.modelo.entidad.Mensaje;
import java.util.Date;
import java.util.List;

/**
 *
 * @author daniel
 */
public interface CreditoPorEmpleadoDAOIF {

    Mensaje agregar(CreditoPorEmpleado entity,String uuidCxn);

    Mensaje actualizar(CreditoPorEmpleado entity,String uuidCxn);

    Mensaje eliminar(CreditoPorEmpleado entity,String uuidCxn);

    Mensaje getCreditosAll(String claveTipoCredito, String tipoConfiguracion,String uuidCxn);

    Mensaje getCreditosPorClave(String numeroCredito, String claveTipoCredito, String tipoConfiguracion,String uuidCxn);

    Mensaje getCreditosPorTipoCredito(String claveTipoCredito, String tipoConfiguracion,String uuidCxn);

    Mensaje existenMovimientosEnCreditos(String numeroDeCredito, String claveCreditoAhorro, String tipoConfiguracion,String uuidCxn);

    Mensaje getCreditosPorTipoCreditoYFecha(Date fecha, String tipoCredito, String claveRazonsocial, String tipoConfiguracion,String uuidCxn);

    Mensaje saveDeleteCreditos(List<CreditoPorEmpleado> entitysCambios, Object[] eliminados,String uuidCxn);

    Mensaje consultaPorRangos(Integer inicio, Integer rango,String uuidCxn);

    Mensaje existeDato(String campo, Object valor,String uuidCxn);
}
