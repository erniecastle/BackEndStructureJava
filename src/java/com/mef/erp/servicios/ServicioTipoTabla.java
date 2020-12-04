/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mef.erp.servicios;

import com.mef.erp.modelo.TipoTablaDAO;
import com.mef.erp.modelo.entidad.Mensaje;
import com.mef.erp.modelo.entidad.TipoTabla;
import java.util.List;

/**
 *
 * @author Jose Armando
 */
public class ServicioTipoTabla implements ServicioTipoTablaIF {

    private TipoTablaDAO tipoTablaDAO;

    public Mensaje getTipoTablaAll(String uuidCxn) {
        return tipoTablaDAO.getTipoTablaAll(uuidCxn);
    }

    public Mensaje getTipoTablaPorClave(String clave, String uuidCxn) {
        return tipoTablaDAO.getTipoTablaPorClave(clave, uuidCxn);
    }

    public Mensaje agregar(TipoTabla entity, String uuidCxn) {
        return tipoTablaDAO.agregar(entity, uuidCxn);
    }

    public Mensaje actualizar(TipoTabla entity, String uuidCxn) {
        return tipoTablaDAO.actualizar(entity, uuidCxn);
    }

    public Mensaje eliminar(TipoTabla tipoTabla, String uuidCxn) {
        return tipoTablaDAO.eliminar(tipoTabla, uuidCxn);
    }

    public Mensaje consultaPorFiltros(String query, Object[] campos, Object[] valores, Integer inicio, Integer rango, String uuidCxn) {
        return tipoTablaDAO.consultaPorFiltrosTipoTabla(query, campos, valores, inicio, rango, uuidCxn);
    }

    public Mensaje consultaPorRangos(Integer inicio, Integer rango, String uuidCxn) {
        return tipoTablaDAO.consultaPorRangos(inicio, rango, uuidCxn);
    }

    public Mensaje saveDeleteTipoTabla(List<TipoTabla> entitysCambios, Object[] clavesDelete, int rango, String uuidCxn) {
        return tipoTablaDAO.saveDeleteTipoTabla(entitysCambios, clavesDelete, rango, uuidCxn);
    }

    public Mensaje existeDato(String campo, Object valor, String uuidCxn) {
        return tipoTablaDAO.existeDato(campo, valor, uuidCxn);
    }

    public TipoTablaDAO getTipoTablaDAO() {
        return tipoTablaDAO;
    }

    public void setTipoTablaDAO(TipoTablaDAO tipoTablaDAO) {
        this.tipoTablaDAO = tipoTablaDAO;
    }
}
