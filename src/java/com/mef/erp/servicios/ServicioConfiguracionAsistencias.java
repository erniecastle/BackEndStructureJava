/**
 * @author: Ernesto Castillo Fecha de Creación: 07/12/2011 Compañía: Exito
 * Software. Descripción del programa: clase para servicio
 * ConfiguracionAsistencias
 * -----------------------------------------------------------------------------
 * MODIFICACIONES:
 * -----------------------------------------------------------------------------
 * Clave:JSA01 Autor:Jose Armando Fecha:10/NOV/2012 Descripción:Se agrego el
 * metodo PorGrupoMenu para regresar las configuraciones con ese tipo de
 * contenedor.
 * -----------------------------------------------------------------------------
 * Clave: Autor: Fecha: Descripción:
 * -----------------------------------------------------------------------------
 */
package com.mef.erp.servicios;

import com.mef.erp.modelo.ConfiguracionAsistenciasDAO;
import com.mef.erp.modelo.entidad.ConfiguracionAsistencias;
import com.mef.erp.modelo.entidad.Mensaje;
import java.util.List;

/**
 *
 * @author Desarrollo 18
 */
public class ServicioConfiguracionAsistencias implements ServicioConfiguracionAsistenciasIF {

    private ConfiguracionAsistenciasDAO configuracionAsistenciasDAO;

    public Mensaje agregar(ConfiguracionAsistencias entity, String uuidCxn) {
        return getConfiguracionAsistenciasDAO().agregar(entity, uuidCxn);
    }

    public Mensaje getExcepcionesPorConfiguracionAsistencias(String clave, String uuidCxn) {
        return getConfiguracionAsistenciasDAO().getExcepcionesPorConfiguracionAsistencias(clave, uuidCxn);
    }

    public Mensaje getConfiguracionAsistenciasAll(String claveRazonesSocial, String uuidCxn) {
        return getConfiguracionAsistenciasDAO().getConfiguracionAsistenciasAll(claveRazonesSocial, uuidCxn);
    }

    public Mensaje saveDeleteConfiguracionAsistencias(List<ConfiguracionAsistencias> AgreModif, List<ConfiguracionAsistencias> Ordenados, List<ConfiguracionAsistencias> eliminados, String uuidCxn) {
        return getConfiguracionAsistenciasDAO().saveDeleteConfiguracionAsistencias(AgreModif, Ordenados, eliminados, uuidCxn);
    }

    public Mensaje PorGrupoMenu(String idContenedor, String claveRazonSocial, String uuidCxn) {//JSA01
        return getConfiguracionAsistenciasDAO().PorGrupoMenu(idContenedor, claveRazonSocial, uuidCxn);
    }

    public Mensaje buscaPorIdYRazonSocial(Long id, String claveRazonSocial, String uuidCxn) {
        return getConfiguracionAsistenciasDAO().buscaPorIdYRazonSocial(id, claveRazonSocial, uuidCxn);
    }
    
    
    @Override
    public Mensaje buscaConfiguracionAsistenciasSistema(Long id, String uuidCxn) {
        return getConfiguracionAsistenciasDAO().buscaConfiguracionAsistenciasSistema(id, uuidCxn);
    }

    @Override
    public Mensaje getAllConfiguracionAsistenciasSistema(String uuidCxn) {
        return getConfiguracionAsistenciasDAO().getAllConfiguracionAsistenciasSistema(uuidCxn);
    }

    public ConfiguracionAsistenciasDAO getConfiguracionAsistenciasDAO() {
        return configuracionAsistenciasDAO;
    }

    public void setConfiguracionAsistenciasDAO(ConfiguracionAsistenciasDAO configuracionAsistenciasDAO) {
        this.configuracionAsistenciasDAO = configuracionAsistenciasDAO;
    }

}
