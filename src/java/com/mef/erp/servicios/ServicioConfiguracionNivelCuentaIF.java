/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mef.erp.servicios;

import com.mef.erp.modelo.entidad.Mensaje;
import com.mef.erp.modelo.entidad.contabilidad.ConfiguracionNivelCuenta;
import java.util.List;

/**
 *
 * @author Desarrollo 094
 */
public interface ServicioConfiguracionNivelCuentaIF {
    /*ConfiguracionNivelCuenta*/

    public Mensaje agregar(ConfiguracionNivelCuenta entity, String uuidCxn);

    /*boolean*/
    public Mensaje actualizar(ConfiguracionNivelCuenta entity, String uuidCxn);

    /*boolean*/
    public Mensaje eliminar(ConfiguracionNivelCuenta entity, String uuidCxn);

    /*boolean*/
    public Mensaje saveDeleteConfiguracionNivelCuenta(ConfiguracionNivelCuenta entity,List<Long> eliminados, String uuidCxn);

    /*lista*/
    public Mensaje getConfiguracionNivelCuentaUnico(String uuidCxn);
}
