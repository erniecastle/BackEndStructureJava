/**
 * @author: Daniel Fecha de Creación: --/--/-- Compañía: FineSoft Descripción
 * del programa: clase Bancos para llamados a metodos de HIBERNATE
 * -----------------------------------------------------------------------------
 * MODIFICACIONES:
 * -----------------------------------------------------------------------------
 * Clave:JSA01 Autor:Jose Armando Sanchez Acosta Fecha:10/11/2011 Descripción:Se
 * cambiaron todos los metodos hibernate al dao
 * -----------------------------------------------------------------------------
 * Clave:JSA02 Autor:Jose Armando Sanchez Acosta Fecha:23/07/2012 Descripción:Se
 * agrego el metodo getConfiguracionIMSSActual para obtener la configuracion mas
 * cerca a la fecha enviada.
 * -----------------------------------------------------------------------------
 * Clave:JSA03 Autor:Jose Armando Sanchez Acosta Fecha:05/02/2013 Descripción:Se
 * agrego este servicio getConfiguracionIMSSAll para traer todas las
 * configuraciones del imss (busqueda avanzada)
 * -----------------------------------------------------------------------------
 * Clave:JSA04 Autor:Jose Armando Sanchez Acosta Fecha:31/07/2014 Descripción:Se
 * cambio el retorno del metodo eliminar para la configuracion, tambien se
 * agrego un metodo para saber si una configuracion existe.
 * -----------------------------------------------------------------------------
 */
package com.mef.erp.servicios;

import com.mef.erp.modelo.ConfiguracionIMSSDAO;
import com.mef.erp.modelo.entidad.ConfiguracionIMSS;
import com.mef.erp.modelo.entidad.Mensaje;
import java.util.Date;

/**
 *
 * @author daniel
 */
public class ServicioConfiguracionIMSS implements ServicioConfiguracionIMSSIF {

    private ConfiguracionIMSSDAO configuracionIMSSDAO;

    public Mensaje agregar(ConfiguracionIMSS entity, String uuidCxn) {//JSA01
        return configuracionIMSSDAO.agregar(entity, uuidCxn);
    }

    public Mensaje actualizar(ConfiguracionIMSS entity, String uuidCxn) {//JSA01
        return configuracionIMSSDAO.actualizar(entity, uuidCxn);
    }

    public Mensaje eliminar(ConfiguracionIMSS entity, String uuidCxn) {//JSA01
        return configuracionIMSSDAO.eliminar(entity, uuidCxn);
    }

    public Mensaje getConfiguracionIMSSPorClave(Long clave, String uuidCxn) {//JSA01
        return configuracionIMSSDAO.getConfiguracionIMSSPorClave(clave, uuidCxn);
    }

    public Mensaje getConfiguracionIMSSActual(Date date, String uuidCxn) {//JSA02
        return configuracionIMSSDAO.getConfiguracionIMSSActual(date, uuidCxn);
    }

    public Mensaje getConfiguracionIMSSAll(String uuidCxn) {//JSA03
        return configuracionIMSSDAO.getConfiguracionIMSSAll(uuidCxn);
    }

    public Mensaje exiteConfiguracionIMSS(Date date, String uuidCxn) {//JSA04
        return getConfiguracionIMSSDAO().exiteConfiguracionIMSS(date, uuidCxn);
    }

    @Override
    public Mensaje obtenerRelacionConfIMSS(Long id, String uuidCxn) {
        return getConfiguracionIMSSDAO().obtenerRelacionConfIMSS(id, uuidCxn);
    }

    public ConfiguracionIMSSDAO getConfiguracionIMSSDAO() {
        return configuracionIMSSDAO;
    }

    public void setConfiguracionIMSSDAO(ConfiguracionIMSSDAO configuracionIMSSDAO) {
        this.configuracionIMSSDAO = configuracionIMSSDAO;
    }

}
