/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mef.erp.servicios;

import com.mef.erp.modelo.entidad.Mensaje;
import com.mef.erp.modelo.entidad.contabilidad.EstrucCuenta;
import java.util.List;

/**
 *
 * @author Desarrollo 094
 */
public interface ServicioEstrucCuentaIF {
    /*ConfiguracionNivelCuenta*/

    public Mensaje agregar(List<EstrucCuenta> entity, String uuidCxn);

    /*boolean*/
    public Mensaje actualizar(List<EstrucCuenta> entity, String uuidCxn);

    /*boolean*/
    public Mensaje eliminar(List<EstrucCuenta> entity, String uuidCxn);

    /*lista*/
    public Mensaje getEstrucCuentaAll(String uuidCxn);
}
