/**
 * @author: Daniel Fecha de Creación: 29/12/2016 Compañía: Exito Software
 * Descripción del programa: clase ConfiguracionAguinaldo para llamados a
 * metodos de HIBERNATE
 * -----------------------------------------------------------------------------
 */
package com.mef.erp.modelo;

import com.mef.erp.modelo.entidad.AguinaldoConfiguracion;
import com.mef.erp.modelo.entidad.Mensaje;
import java.util.List;

/**
 *
 * @author Desarrollo 094
 */
public interface ConfiguracionAguinaldoDAOIF extends GenericDAO<AguinaldoConfiguracion, Long> {

    public Mensaje agregar(AguinaldoConfiguracion entity, String uuidCxn);

    public Mensaje actualizar(AguinaldoConfiguracion entity, String uuidCxn);

    public Mensaje eliminar(AguinaldoConfiguracion entity, String uuidCxn);

    public Mensaje getConfiguracionAguinaldoAll(String uuidCxn);

    Mensaje SaveConfiguracionAguinaldo(List<AguinaldoConfiguracion> agrega, Object[] eliminados, String uuidCxn);

    Mensaje getConfiguracionAguinaldoPorClave(String claveRazonsocial, String uuidCxn);
}
