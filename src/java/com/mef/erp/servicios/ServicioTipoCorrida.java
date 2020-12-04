/**
 * @author: Victor Lopez Fecha de Creación: 27/09/2011 Compañía: MacroPro.
 * Descripción del programa: clase SERVICIO tipo corrida, para llamados a
 * metodos de objeto DAO
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

import com.mef.erp.modelo.TipoCorridaDAO;
import com.mef.erp.modelo.entidad.Mensaje;
import com.mef.erp.modelo.entidad.TipoCorrida;
import java.util.List;

public class ServicioTipoCorrida implements ServicioTipoCorridaIF {

    private TipoCorridaDAO tipoCorridaDAO;

    public Mensaje agregar(TipoCorrida entity, String uuidCxn) {//JSA01
        return tipoCorridaDAO.agregar(entity, uuidCxn);
    }

    public Mensaje actualizar(TipoCorrida entity, String uuidCxn) {//JSA01
        return tipoCorridaDAO.actualizar(entity, uuidCxn);
    }

    public Mensaje eliminar(TipoCorrida entity, String uuidCxn) {//JSA01
        return tipoCorridaDAO.eliminar(entity, uuidCxn);
    }

    public Mensaje getTipoCorridaAll(String uuidCxn) {//JSA01
        return tipoCorridaDAO.getTipoCorridaAll(uuidCxn);
    }

    public Mensaje getTipoCorridaPorClave(String clave, String uuidCxn) {//JSA01
        return tipoCorridaDAO.getTipoCorridaPorClave(clave, uuidCxn);
    }

    public Mensaje consultaPorRangosTipoCorrida(Integer inicio, Integer rango, String uuidCxn) {//JSA01
        return tipoCorridaDAO.consultaPorRangosTipoCorrida(inicio, rango, uuidCxn);
    }

    public Mensaje consultaPorFiltrosTipoCorrida(String query, Object[] campos, Object[] valores, String uuidCxn) {//JSA01
        return tipoCorridaDAO.consultaPorFiltrosTipoCorrida(query, campos, valores, uuidCxn);
    }

    public Mensaje existeDato(String campo, Object valor, String uuidCxn) {//JSA01
        return tipoCorridaDAO.existeDato(campo, valor, uuidCxn);
    }

    public Mensaje saveDeleteTipoCorrida(List<TipoCorrida> entitysCambios, Object[] clavesDelete, int rango, String uuidCxn) {
        return tipoCorridaDAO.saveDeleteTipoCorrida(entitysCambios, clavesDelete, rango, uuidCxn);
    }

    public TipoCorridaDAO getTipoCorridaDAO() {
        return tipoCorridaDAO;
    }

    public void setTipoCorridaDAO(TipoCorridaDAO tipoCorridaDAO) {
        this.tipoCorridaDAO = tipoCorridaDAO;
    }
}
