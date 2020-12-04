/**
 * @author: Victor Lopez Fecha de Creación: 27/09/2011 Compañía: MacroPro.
 * Descripción del programa: clase SERVICIO Tabla BaseAfectadaGrupo, para
 * llamados a metodos de objeto DAO
 * -----------------------------------------------------------------------------
 * MODIFICACIONES:
 * -----------------------------------------------------------------------------
 * Clave:JSA01 Autor:Jose Armando Sanchez Acosta Fecha:10/11/2011 Descripción:Se
 * cambiaron todos los metodos hibernate al dao
 * -----------------------------------------------------------------------------
 * Clave: Autor: Fecha: Descripción:
 * -----------------------------------------------------------------------------
 */
package com.mef.erp.servicios;

import com.mef.erp.modelo.BaseAfectadaGrupoDAO;
import com.mef.erp.modelo.entidad.BaseAfectadaGrupo;
import com.mef.erp.modelo.entidad.Mensaje;
import java.util.List;

public class ServicioBaseAfectadaGrupo implements ServicioBaseAfectadaGrupoIF {

    private BaseAfectadaGrupoDAO baseAfectadaGrupoDAO;

    public Mensaje agregar(BaseAfectadaGrupo entity, String uuidCxn) {//JSA01
        return baseAfectadaGrupoDAO.agregar(entity, uuidCxn);
    }

    public Mensaje actualizar(BaseAfectadaGrupo entity, String uuidCxn) {//JSA01
        return baseAfectadaGrupoDAO.actualizar(entity, uuidCxn);
    }

    public Mensaje eliminar(BaseAfectadaGrupo entity, String uuidCxn) {//JSA01
        return baseAfectadaGrupoDAO.eliminar(entity, uuidCxn);
    }

    public Mensaje getBaseAfectadaGrupoAll(String uuidCxn) {//JSA01
        return baseAfectadaGrupoDAO.getBaseAfectadaGrupoAll(uuidCxn);
    }

    public Mensaje getBaseAfectadaGrupoPorClave(String clave, String uuidCxn) {//JSA01
        return baseAfectadaGrupoDAO.getBaseAfectadaGrupoPorClave(clave, uuidCxn);
    }

    public Mensaje consultaPorRangos(Integer inicio, Integer rango, String uuidCxn) {//JSA01
        return baseAfectadaGrupoDAO.consultaPorRangos(inicio, rango, uuidCxn);
    }

    public Mensaje existeDato(String campo, Object valor, String uuidCxn) {
        return baseAfectadaGrupoDAO.existeDato(campo, valor, uuidCxn);
    }

    public Mensaje agregarListaBaseAfectadaGrupo(List<BaseAfectadaGrupo> entitys, int rango, String uuidCxn) {//JSA01
        return baseAfectadaGrupoDAO.agregarListaBaseAfectadaGrupo(entitys, rango, uuidCxn);
    }

    public Mensaje deleteListQuery(String tabla, String campo, Object[] valores, String uuidCxn) {//JSA01
        return baseAfectadaGrupoDAO.deleteListQuerys(tabla, campo, valores, uuidCxn);
    }

    public BaseAfectadaGrupoDAO getBaseAfectadaGrupoDAO() {
        return baseAfectadaGrupoDAO;
    }

    public void setBaseAfectadaGrupoDAO(BaseAfectadaGrupoDAO baseAfectadaGrupoDAO) {
        this.baseAfectadaGrupoDAO = baseAfectadaGrupoDAO;
    }
}
