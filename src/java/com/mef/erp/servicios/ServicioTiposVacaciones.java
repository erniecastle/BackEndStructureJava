/**
 * @author: Victor Lopez Fecha de Creación: 28/04/2016 Compañía: MacroPro.
 * Descripción del programa: clase SERVICIO, para llamados a
 * metodos de objeto DAO
 * -----------------------------------------------------------------------------
 * MODIFICACIONES:
 * -----------------------------------------------------------------------------
 * -----------------------------------------------------------------------------
 * -----------------------------------------------------------------------------
 */
package com.mef.erp.servicios;

import com.mef.erp.modelo.TiposVacacionesDAO;
import com.mef.erp.modelo.entidad.Mensaje;
import com.mef.erp.modelo.entidad.TiposVacaciones;
import java.util.List;

public class ServicioTiposVacaciones implements ServicioTiposVacacionesIF {

    private TiposVacacionesDAO tiposVacacionesDAO;

    public Mensaje agregar(TiposVacaciones entity, String uuidCxn) {
        return tiposVacacionesDAO.agregar(entity, uuidCxn);
    }

    public Mensaje actualizar(TiposVacaciones entity, String uuidCxn) {
        return tiposVacacionesDAO.actualizar(entity, uuidCxn);
    }

    public Mensaje eliminar(TiposVacaciones entity, String uuidCxn) {
        return tiposVacacionesDAO.eliminar(entity, uuidCxn);
    }

    public Mensaje getTiposVacacionesAll(String uuidCxn) {
        return tiposVacacionesDAO.getTiposVacacionesAll(uuidCxn);
    }

    public Mensaje getTiposVacacionesPorClave(String clave, String uuidCxn) {
        return tiposVacacionesDAO.getTiposVacacionesPorClave(clave, uuidCxn);
    }

    public Mensaje consultaPorFiltros(String query, Object[] campos, Object[] valores, String uuidCxn) {
        return tiposVacacionesDAO.consultaPorFiltrosTiposVacaciones(query, campos, valores, uuidCxn);
    }

    public Mensaje consultaPorRangos(Integer inicio, Integer rango, String uuidCxn) {
        return tiposVacacionesDAO.consultaPorRangos(inicio, rango, uuidCxn);
    }

    public Mensaje existeDato(String campo, Object valor, String uuidCxn) {
        return tiposVacacionesDAO.existeDato(campo, valor, uuidCxn);
    }

    public Mensaje saveDeleteTiposVacaciones(List<TiposVacaciones> entitysCambios, Object[] clavesDelete, int rango, String uuidCxn) {
        return tiposVacacionesDAO.saveDeleteTiposVacaciones(entitysCambios, clavesDelete, rango, uuidCxn);
    }

    public Mensaje existeClave(String tabla, String[] campo, Object[] valores, String queryAntesDeFrom, String uuidCxn) {
        return getTiposVacacionesDAO().existeClave(tabla, campo, valores, queryAntesDeFrom, uuidCxn);
    }

    public TiposVacacionesDAO getTiposVacacionesDAO() {
        return tiposVacacionesDAO;
    }

    public void setTiposVacacionesDAO(TiposVacacionesDAO tiposVacacionesDAO) {
        this.tiposVacacionesDAO = tiposVacacionesDAO;
    }
}
