/**
 * @author: Daniel Fecha de Creación: --/--/-- Compañía: FineSoft Descripción
 * del programa: clase Bancos para llamados a metodos de HIBERNATE
 * -----------------------------------------------------------------------------
 * MODIFICACIONES:
 * -----------------------------------------------------------------------------
 * Clave:JSA01 Autor:Jose Armando Sanchez Acosta Fecha:10/11/2011 Descripción:Se
 * cambiaron todos los metodos hibernate al dao
 * -----------------------------------------------------------------------------
 * Clave:JSA02 Autor:Jose Armando Sanchez Acosta Fecha:05/02/2013 Descripción:Se
 * agrego este servicio getConfiguracionIMSSAll para traer todas las
 * configuraciones del imss (busqueda avanzada)
 */
package com.mef.erp.modelo;

import com.mef.erp.modelo.entidad.ConfiguracionIMSS;
import com.mef.erp.modelo.entidad.Mensaje;
import java.util.Date;

public interface ConfiguracionIMSSDAOIF extends GenericDAO<ConfiguracionIMSS, Long> {

    public Mensaje agregar(ConfiguracionIMSS entity, String uuidCxn);//JSA01

    public Mensaje actualizar(ConfiguracionIMSS entity, String uuidCxn);//JSA01

    public Mensaje eliminar(ConfiguracionIMSS entity, String uuidCxn);//JSA01

    public Mensaje getConfiguracionIMSSPorClave(Long clave, String uuidCxn);//JSA01

    public Mensaje getConfiguracionIMSSActual(Date date, String uuidCxn);//JSA01

    public Mensaje exiteConfiguracionIMSS(Date date, String uuidCxn);

    public Mensaje getConfiguracionIMSSAll(String uuidCxn);//JSA02

    public Mensaje obtenerRelacionConfIMSS(Long id, String uuidCxn);

}
