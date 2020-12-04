/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mef.erp.servicios;

import com.mef.erp.modelo.CreditoPorEmpleadoDAO;
import com.mef.erp.modelo.entidad.CreditoPorEmpleado;
import com.mef.erp.modelo.entidad.Mensaje;
import java.util.Date;
import java.util.List;

/**
 *
 * @author daniel
 */
public class ServicioCreditoPorEmpleado implements ServicioCreditoPorEmpleadoIF {

    private CreditoPorEmpleadoDAO creditoPorEmpleadoDAO;
  

    public Mensaje agregar(CreditoPorEmpleado entity,String uuidCxn) {
        return creditoPorEmpleadoDAO.agregar(entity,uuidCxn);
    }

    public Mensaje actualizar(CreditoPorEmpleado entity,String uuidCxn) {
        return creditoPorEmpleadoDAO.actualizar(entity,uuidCxn);
    }

    public Mensaje eliminar(CreditoPorEmpleado entity,String uuidCxn) {
        return creditoPorEmpleadoDAO.eliminar(entity,uuidCxn);
    }

    public Mensaje getCreditosAll(String claveTipoCredito, String tipoConfiguracion,String uuidCxn) {

        return creditoPorEmpleadoDAO.getCreditosAll(claveTipoCredito, tipoConfiguracion,uuidCxn);
    }

    public Mensaje getCreditosPorClave(String numeroCredito, String claveTipoCredito, String tipoConfiguracion,String uuidCxn) {
     
        return creditoPorEmpleadoDAO.getCreditosPorClave(numeroCredito, claveTipoCredito, tipoConfiguracion,uuidCxn);
    }

    public Mensaje getCreditosPorTipoCredito(String claveTipoCredito, String tipoConfiguracion,String uuidCxn) {
   
        return creditoPorEmpleadoDAO.getCreditosPorTipoCredito(claveTipoCredito, tipoConfiguracion,uuidCxn);
    }

    public Mensaje existenMovimientosEnCreditos(String numeroDeCredito, String claveCreditoAhorro, String tipoConfiguracion,String uuidCxn) {
      
        return creditoPorEmpleadoDAO.existenMovimientosEnCreditos(numeroDeCredito, claveCreditoAhorro, tipoConfiguracion,uuidCxn);
    }

    public Mensaje getCreditosPorTipoCreditoYFecha(Date fecha, String tipoCredito, String claveRazonsocial, String tipoConfiguracion,String uuidCxn) {

        return creditoPorEmpleadoDAO.getCreditosPorTipoCreditoYFecha(fecha, tipoCredito, claveRazonsocial, tipoConfiguracion,uuidCxn);
    }

    public Mensaje consultaPorRangos(Integer inicio, Integer rango,String uuidCxn) {
 
        return creditoPorEmpleadoDAO.consultaPorRangos(inicio, rango,uuidCxn);
    }

    public Mensaje existeDato(String campo, Object valor,String uuidCxn) {
       
        return creditoPorEmpleadoDAO.existeDato(campo, valor,uuidCxn);
    }

    public Mensaje saveDeleteCreditos(List<CreditoPorEmpleado> entitysCambios, Object[] eliminados,String uuidCxn) {

        return creditoPorEmpleadoDAO.saveDeleteCreditos(entitysCambios, eliminados,uuidCxn);
    }

    public CreditoPorEmpleadoDAO getCreditoPorEmpleadoDAO() {
        return creditoPorEmpleadoDAO;
    }

    public void setCreditoPorEmpleadoDAO(CreditoPorEmpleadoDAO creditoPorEmpleadoDAO) {
        this.creditoPorEmpleadoDAO = creditoPorEmpleadoDAO;
    }
}
