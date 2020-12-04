/**
 * @author: Jose Armando Fecha de Creación: 19/06/2012 Compañía: Exito Software.
 * Descripción del programa: Interface para servicio Tipo de contrato
 * -----------------------------------------------------------------------------
 * MODIFICACIONES:
 * -----------------------------------------------------------------------------
 * Clave: Autor: Fecha: Descripción:
 * -----------------------------------------------------------------------------
 */
package com.mef.erp.servicios;

import com.mef.erp.modelo.TipoContratoDAO;
import com.mef.erp.modelo.entidad.Mensaje;
import com.mef.erp.modelo.entidad.TipoContrato;
import java.util.List;

public class ServicioTipoContrato implements ServicioTipoContratoIF {

    private TipoContratoDAO tipoContratoDAO;

    public Mensaje agregar(TipoContrato entity, String uuidCxn) {
        return tipoContratoDAO.agregar(entity, uuidCxn);
    }

    public Mensaje actualizar(TipoContrato entity, String uuidCxn) {
        return tipoContratoDAO.actualizar(entity, uuidCxn);
    }

    public Mensaje eliminar(TipoContrato entity, String uuidCxn) {
        return tipoContratoDAO.eliminar(entity, uuidCxn);
    }

    public Mensaje getTipoContratoAll(String uuidCxn) {
        return tipoContratoDAO.getTipoContratoAll(uuidCxn);
    }

    public Mensaje getTipoContratoPorClave(String clave, String uuidCxn) {//AAP01
        return tipoContratoDAO.getTipoContratoPorClave(clave, uuidCxn);
    }

    public Mensaje consultaPorFiltros(String query, Object[] campos, Object[] valores, String uuidCxn) {
        return tipoContratoDAO.consultaPorFiltrosTipoContrato(query, campos, valores, uuidCxn);
    }

    public Mensaje consultaPorRangos(Integer inicio, Integer rango, String uuidCxn) {
        return tipoContratoDAO.consultaPorRangosTipoContrato(inicio, rango, uuidCxn);
    }

    public Mensaje existeDato(String campo, Object valor, String uuidCxn) {
        return tipoContratoDAO.existeDato(campo, valor, uuidCxn);
    }

    public Mensaje saveDeleteTipoContrato(List<TipoContrato> entitysCambios, Object[] clavesDelete, int rango, String uuidCxn) {
        return tipoContratoDAO.saveDeleteTipoContrato(entitysCambios, clavesDelete, rango, uuidCxn);
    }

    public TipoContratoDAO getTipoContratoDAO() {
        return tipoContratoDAO;
    }

    public void setTipoContratoDAO(TipoContratoDAO tipoContratoDAO) {
        this.tipoContratoDAO = tipoContratoDAO;
    }

}
