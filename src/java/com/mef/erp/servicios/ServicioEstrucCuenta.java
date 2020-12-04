/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mef.erp.servicios;

import com.mef.erp.modelo.EstrucCuentaDAO;
import com.mef.erp.modelo.entidad.Mensaje;
import com.mef.erp.modelo.entidad.contabilidad.EstrucCuenta;
import java.util.List;

/**
 *
 * @author Desarrollo 094
 */
public class ServicioEstrucCuenta implements ServicioEstrucCuentaIF {

    private EstrucCuentaDAO estrucCuentaDAO;
    
    @Override
    public Mensaje agregar(List<EstrucCuenta> entity, String uuidCxn) {
        return estrucCuentaDAO.agregar(entity, uuidCxn);
    }

    @Override
    public Mensaje actualizar(List<EstrucCuenta> entity, String uuidCxn) {
        return estrucCuentaDAO.actualizar(entity, uuidCxn);
    }

    @Override
    public Mensaje eliminar(List<EstrucCuenta> entity, String uuidCxn) {
       return estrucCuentaDAO.eliminar(entity, uuidCxn);
    }

    @Override
    public Mensaje getEstrucCuentaAll(String uuidCxn) {
        return estrucCuentaDAO.getEstrucCuentaAll(uuidCxn);
    }
    
    

    public EstrucCuentaDAO getEstrucCuentaDAO() {
        return estrucCuentaDAO;
    }

    public void setEstrucCuentaDAO(EstrucCuentaDAO estrucCuentaDAO) {
        this.estrucCuentaDAO = estrucCuentaDAO;
    }
    
}
