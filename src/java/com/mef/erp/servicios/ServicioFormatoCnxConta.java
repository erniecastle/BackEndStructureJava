/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mef.erp.servicios;

import com.mef.erp.modelo.FormatoCnxContaDAO;
import com.mef.erp.modelo.entidad.Mensaje;
import com.mef.erp.modelo.entidad.contabilidad.FormatoCnxConta;
import java.util.List;

/**
 *
 * @author Desarrollo 094
 */
public class ServicioFormatoCnxConta implements ServicioFormatoCnxContaIF{
 private FormatoCnxContaDAO formatoCnxContaDAO;
    @Override
    public Mensaje agregar(FormatoCnxConta entity, String uuidCxn) {
        return formatoCnxContaDAO.agregar(entity, uuidCxn);
    }

    @Override
    public Mensaje actualizar(FormatoCnxConta entity, String uuidCxn) {
        return formatoCnxContaDAO.actualizar(entity, uuidCxn);
    }

    @Override
    public Mensaje eliminar(FormatoCnxConta entity, String uuidCxn) {
        return formatoCnxContaDAO.eliminar(entity, uuidCxn);
    }

    @Override
    public Mensaje getFormatoCnxContaAll(String uuidCxn) {
        return formatoCnxContaDAO.getFormatoCnxContaAll(uuidCxn);
    }

    @Override
    public Mensaje getFormatoCnxContaClave(String clave, String uuidCxn) {
        return formatoCnxContaDAO.getFormatoCnxContaClave(clave, uuidCxn);
    }

    @Override
    public Mensaje saveDeleteConfiguracionNivelCuenta(FormatoCnxConta entity, List<Long> eliminados, String uuidCxn) {
        return formatoCnxContaDAO.saveDeleteConfiguracionNivelCuenta(entity, eliminados, uuidCxn);
        
    }
    

    public FormatoCnxContaDAO getFormatoCnxContaDAO() {
        return formatoCnxContaDAO;
    }

    public void setFormatoCnxContaDAO(FormatoCnxContaDAO formatoCnxContaDAO) {
        this.formatoCnxContaDAO = formatoCnxContaDAO;
    }
    
}
