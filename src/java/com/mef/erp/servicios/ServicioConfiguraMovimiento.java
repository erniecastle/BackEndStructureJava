/**
 * @author: Victor Lopez Fecha de Creación: 09/12/2011 Compañía: Macropro.
 * Descripción del programa: clase SERVICIO ConfiguraMovimiento, para llamados a
 * metodos de objeto DAO
 * -----------------------------------------------------------------------------
 * MODIFICACIONES:
 * -----------------------------------------------------------------------------
 * Clave:JSA01 Autor:Armando Sanchez Fecha:09/11/2012 Descripción:Se agrego el
 * metodo para obtener los movimientos por un contenedor
 * -----------------------------------------------------------------------------
 */
package com.mef.erp.servicios;

import com.mef.erp.modelo.ConfiguraMovimientoDAO;
import com.mef.erp.modelo.entidad.ConfiguraMovimiento;
import com.mef.erp.modelo.entidad.Mensaje;
import com.mef.erp.modelo.entidad.RazonesSociales;
import java.util.List;

public class ServicioConfiguraMovimiento implements ServicioConfiguraMovimientoIF {

    private ConfiguraMovimientoDAO configuraMovimientoDAO;

    public Mensaje agregar(ConfiguraMovimiento entity, String uuidCxn) {
        return configuraMovimientoDAO.agregar(entity, uuidCxn);
    }

    public Mensaje actualizar(ConfiguraMovimiento entity, String uuidCxn) {
        return configuraMovimientoDAO.actualizar(entity, uuidCxn);
    }

    public Mensaje eliminar(ConfiguraMovimiento entity, String uuidCxn) {
        return configuraMovimientoDAO.eliminar(entity, uuidCxn);
    }

    public Mensaje getConfiguraMovimientoAll(String claveRazonesSocial, String uuidCxn) {
        return configuraMovimientoDAO.getConfiguraMovimientoAll(claveRazonesSocial, uuidCxn);
    }

    public Mensaje consultaPorRangos(Integer inicio, Integer rango, String[] camposWhere, Object[] camposWhereValores, String uuidCxn) {
        return configuraMovimientoDAO.consultaPorRangosConfigMov(inicio, rango, camposWhere, camposWhereValores, uuidCxn);
    }

    public Mensaje saveDeleteConfiguraMovimiento(List<ConfiguraMovimiento> entitysCambios, Object[] clavesDelete, int rango, String uuidCxn) {
        return configuraMovimientoDAO.saveDeleteConfiguraMovimiento(entitysCambios, clavesDelete, rango, uuidCxn);
    }

    public Mensaje PorGrupoMenu(String idContenedor, String claveRazonSocial, String uuidCxn) {//JSA01
        return configuraMovimientoDAO.PorGrupoMenu(idContenedor, claveRazonSocial, uuidCxn);
    }

    public Mensaje existeDato(String campo, Object valor, String uuidCxn) {
        return configuraMovimientoDAO.existeDato(campo, valor, uuidCxn);
    }

    public Mensaje buscaPorIdYRazonSocial(Long id, String claveRazonSocial, String uuidCxn) {
        return configuraMovimientoDAO.buscaPorIdYRazonSocial(id, claveRazonSocial, uuidCxn);
    }

    public Mensaje buscaConfiguracionMovimSistema(Long id, String uuidCxn) {
        return configuraMovimientoDAO.buscaConfiguracionMovimSistema(id, uuidCxn);
    }
    
      @Override
    public Mensaje getAllConfiguracionMovimSistema(String uuidCxn) {
        return configuraMovimientoDAO.getAllConfiguracionMovimSistema(uuidCxn);
    }

    public ConfiguraMovimientoDAO getConfiguraMovimientoDAO() {
        return configuraMovimientoDAO;
    }

    public void setConfiguraMovimientoDAO(ConfiguraMovimientoDAO configuraMovimientoDAO) {
        this.configuraMovimientoDAO = configuraMovimientoDAO;
    }

}
