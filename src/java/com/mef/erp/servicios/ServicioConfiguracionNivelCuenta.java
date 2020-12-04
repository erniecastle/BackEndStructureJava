/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mef.erp.servicios;

import com.mef.erp.modelo.ConfiguracionNivelCuentaDAO;
import com.mef.erp.modelo.entidad.Mensaje;
import com.mef.erp.modelo.entidad.contabilidad.ConfiguracionNivelCuenta;
import java.util.List;

/**
 *
 * @author Desarrollo 094
 */
public class ServicioConfiguracionNivelCuenta implements ServicioConfiguracionNivelCuentaIF {

    private ConfiguracionNivelCuentaDAO configuracionNivelCuentaDAO;

    @Override
    public Mensaje agregar(ConfiguracionNivelCuenta entity, String uuidCxn) {
        return configuracionNivelCuentaDAO.agregar(entity, uuidCxn);
    }

    @Override
    public Mensaje actualizar(ConfiguracionNivelCuenta entity, String uuidCxn) {
        return configuracionNivelCuentaDAO.actualizar(entity, uuidCxn);
    }

    @Override
    public Mensaje eliminar(ConfiguracionNivelCuenta entity, String uuidCxn) {
        return configuracionNivelCuentaDAO.eliminar(entity, uuidCxn);
    }

    @Override
    public Mensaje saveDeleteConfiguracionNivelCuenta(ConfiguracionNivelCuenta entity, List<Long> eliminados, String uuidCxn) {
        return configuracionNivelCuentaDAO.saveDeleteConfiguracionNivelCuenta(entity, eliminados, uuidCxn);
    }

    @Override
    public Mensaje getConfiguracionNivelCuentaUnico(String uuidCxn) {
        return configuracionNivelCuentaDAO.getConfiguracionNivelCuentaUnico(uuidCxn);
    }

    public ConfiguracionNivelCuentaDAO getConfiguracionNivelCuentaDAO() {
        return configuracionNivelCuentaDAO;
    }

    public void setConfiguracionNivelCuentaDAO(ConfiguracionNivelCuentaDAO configuracionNivelCuentaDAO) {
        this.configuracionNivelCuentaDAO = configuracionNivelCuentaDAO;
    }

}
