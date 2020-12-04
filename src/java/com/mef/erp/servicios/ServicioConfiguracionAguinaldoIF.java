/**
 * @author: Daniel Fecha de Creación: 30/12/2016 Compañía: Exito Software
 * Descripción del programa: clase ConfiguracionAguinaldo para llamados a
 * metodos de HIBERNATE
 * -----------------------------------------------------------------------------
 */
package com.mef.erp.servicios;

import com.mef.erp.modelo.entidad.AguinaldoConfiguracion;
import com.mef.erp.modelo.entidad.Mensaje;
import java.util.List;

/**
 *
 * @author Desarrollo 094
 */
public interface ServicioConfiguracionAguinaldoIF {

    /*ConfiguracionAguinaldo*/
    public Mensaje agregar(AguinaldoConfiguracion entity, String uuidCxn);

    /*boolean*/
    public Mensaje actualizar(AguinaldoConfiguracion entity, String uuidCxn);

    /*boolean*/
    public Mensaje eliminar(AguinaldoConfiguracion entity, String uuidCxn);

    /*List<ConfiguracionAguinaldo>*/
    public Mensaje getConfiguracionAguinaldoAll(String uuidCxn);

    /*boolean*/
    Mensaje SaveConfiguracionAguinaldo(List<AguinaldoConfiguracion> agrega, Object[] eliminados, String uuidCxn);

    /*ConfiguracionAguinaldo*/
    Mensaje getConfiguracionAguinaldoPorClave(String claveRazonsocial, String uuidCxn);
}
