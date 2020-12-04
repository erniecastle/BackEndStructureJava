/**
 * @author: Daniel Fecha de Creación: 30/12/2016 Compañía: Exito Software
 * Descripción del programa: clase ConfiguracionAguinaldo para llamados a
 * metodos de HIBERNATE
 * -----------------------------------------------------------------------------
 */
package com.mef.erp.servicios;

import com.mef.erp.modelo.ConfiguracionAguinaldoDAO;
import com.mef.erp.modelo.entidad.AguinaldoConfiguracion;
import com.mef.erp.modelo.entidad.Mensaje;
import java.util.List;

/**
 *
 * @author Desarrollo 094
 */
public class ServicioConfiguracionAguinaldo implements ServicioConfiguracionAguinaldoIF {

    private ConfiguracionAguinaldoDAO configuracionAguinaldoDAO;

    @Override
    public Mensaje agregar(AguinaldoConfiguracion entity, String uuidCxn) {
        return configuracionAguinaldoDAO.agregar(entity, uuidCxn);
    }

    @Override
    public Mensaje actualizar(AguinaldoConfiguracion entity, String uuidCxn) {
        return configuracionAguinaldoDAO.actualizar(entity, uuidCxn);
    }

    @Override
    public Mensaje eliminar(AguinaldoConfiguracion entity, String uuidCxn) {
        return configuracionAguinaldoDAO.eliminar(entity, uuidCxn);
    }

    @Override
    public Mensaje getConfiguracionAguinaldoAll(String uuidCxn) {
        return configuracionAguinaldoDAO.getConfiguracionAguinaldoAll(uuidCxn);
    }

    @Override
    public Mensaje SaveConfiguracionAguinaldo(List<AguinaldoConfiguracion> agrega, Object[] eliminados, String uuidCxn) {
        return configuracionAguinaldoDAO.SaveConfiguracionAguinaldo(agrega, eliminados, uuidCxn);
    }

    public ConfiguracionAguinaldoDAO getConfiguracionAguinaldoDAO() {
        return configuracionAguinaldoDAO;
    }

    public void setConfiguracionAguinaldoDAO(ConfiguracionAguinaldoDAO configuracionAguinaldoDAO) {
        this.configuracionAguinaldoDAO = configuracionAguinaldoDAO;
    }

    @Override
    public Mensaje getConfiguracionAguinaldoPorClave(String claveRazonsocial, String uuidCxn) {
        return configuracionAguinaldoDAO.getConfiguracionAguinaldoPorClave(claveRazonsocial, uuidCxn);
    }

}
