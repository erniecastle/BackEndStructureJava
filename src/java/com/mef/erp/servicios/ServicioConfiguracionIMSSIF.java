/**
 * @author: Daniel Fecha de Creacion: --/--/---- Compania: Finesoft Descripcion
 * del programa: Interface para configuracion IMSS
 * -----------------------------------------------------------------------------
 * MODIFICACIONES:
 * -----------------------------------------------------------------------------
 * Clave: JSA01 Autor:Jose Armando Fecha: 23-07/2012 Descripcion: Se agrego el
 * metodo getConfiguracionIMSSActual el cual trae la configuracion mas cerca a
 * la fecha enviada.
 * -----------------------------------------------------------------------------
 * Clave:JSA02 Autor:Jose Armando Sanchez Acosta Fecha:05/02/2013 Descripcin:Se
 * agrego este servicio getConfiguracionIMSSAll para traer todas las
 * configuraciones del imss (busqueda avanzada)
 * -----------------------------------------------------------------------------
 * Clave:JSA03 Autor:Jose Armando Sanchez Acosta Fecha:31/07/2014 Descripción:Se
 * cambio el retorno del metodo eliminar para la configuracion, tambien se
 * agrego un metodo para saber si una configuracion existe.
 * -----------------------------------------------------------------------------
 */
package com.mef.erp.servicios;

import com.mef.erp.modelo.entidad.ConfiguracionIMSS;
import com.mef.erp.modelo.entidad.Mensaje;
import java.util.Date;

/**
 *
 * @author daniel
 */
public interface ServicioConfiguracionIMSSIF {

    /*ConfiguracionIMSS*/
    public Mensaje agregar(ConfiguracionIMSS entity, String uuidCxn);

    /*boolean*/
    public Mensaje actualizar(ConfiguracionIMSS entity, String uuidCxn);

    /*boolean*/
    public Mensaje eliminar(ConfiguracionIMSS entity, String uuidCxn);//JSA03

    /*ConfiguracionIMSS*/
    public Mensaje getConfiguracionIMSSPorClave(Long clave, String uuidCxn);

    /*ConfiguracionIMSS*/
    public Mensaje getConfiguracionIMSSActual(Date date, String uuidCxn);//JSA01

    /*ConfiguracionIMSS*/
    public Mensaje exiteConfiguracionIMSS(Date date, String uuidCxn);//JSA03

    /*List<ConfiguracionIMSS>*/
    public Mensaje getConfiguracionIMSSAll(String uuidCxn);//JSA02

    public Mensaje obtenerRelacionConfIMSS(Long id, String uuidCxn);

}
