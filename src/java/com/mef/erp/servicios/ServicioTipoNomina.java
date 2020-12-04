/**
 * @author: Abraham Daniel Arjona Peraza Fecha de Creación: 15/06/2011 Compañía:
 * Exito Software. Descripción del programa: clase SERVICIO, para llamados a
 * metodos de objeto DAO
 * -----------------------------------------------------------------------------
 * MODIFICACIONES:
 * -----------------------------------------------------------------------------
 * Clave: AAP01 Autor: Abraham Daniel Arjona Peraza Fecha: 27/07/2011
 * Descripción: se agregaron los metodos agregarListTipoNomina() y
 * deleteListQuery() para el manejo de la clase GridTipoNomina
 * -----------------------------------------------------------------------------
 * Clave:JSA01 Autor:Jose Armando Sanchez Acosta Fecha:10/11/2011 Descripción:Se
 * cambiaron todos los metodos hibernate al dao
 * -----------------------------------------------------------------------------
 */
package com.mef.erp.servicios;

import com.mef.erp.modelo.TipoNominaDAO;
import com.mef.erp.modelo.entidad.Mensaje;
import com.mef.erp.modelo.entidad.TipoNomina;
import java.util.List;

public class ServicioTipoNomina implements ServicioTipoNominaIF {

    private TipoNominaDAO tipoNominaDAO;

    public Mensaje agregar(TipoNomina entity, String uuidCxn) {//JSA01
        return tipoNominaDAO.agregar(entity, uuidCxn);
    }

    public Mensaje actualizar(TipoNomina entity, String uuidCxn) {//JSA01
        return tipoNominaDAO.actualizar(entity, uuidCxn);
    }

    public Mensaje eliminar(TipoNomina entity, String uuidCxn) {//JSA01
        return tipoNominaDAO.eliminar(entity, uuidCxn);
    }

    public Mensaje getTipoNominaAll(String uuidCxn) {//JSA01
        return tipoNominaDAO.getTipoNominaAll(uuidCxn);
    }

    public Mensaje getTipoNominaPorClave(String clave, String uuidCxn) {//JSA01
        return tipoNominaDAO.getTipoNominaPorClave(clave, uuidCxn);
    }

    public Mensaje consultaPorFiltros(String query, Object[] campos, Object[] valores, String uuidCxn) {//JSA01
        return tipoNominaDAO.consultaPorFiltrosTipoNomina(query, campos, valores, uuidCxn);
    }

    public Mensaje consultaPorRangos(Integer inicio, Integer rango, String uuidCxn) {//JSA01
        return tipoNominaDAO.consultaPorRangos(inicio, rango, uuidCxn);
    }

    public Mensaje existeDato(String campo, Object valor, String uuidCxn) {//JSA01
        return tipoNominaDAO.existeDato(campo, valor, uuidCxn);
    }

    public Mensaje saveDeleteTipoNomina(List<TipoNomina> entitysCambios, Object[] clavesDelete, int rango, String uuidCxn) {//JSA01
        return tipoNominaDAO.saveDeleteTipoNomina(entitysCambios, clavesDelete, rango, uuidCxn);
    }

    public Mensaje existeClave(String tabla, String[] campo, Object[] valores, String queryAntesDeFrom, String uuidCxn) {
        return getTipoNominaDAO().existeClave(tabla, campo, valores, queryAntesDeFrom, uuidCxn);
    }

    public TipoNominaDAO getTipoNominaDAO() {
        return tipoNominaDAO;
    }

    public void setTipoNominaDAO(TipoNominaDAO tipoNominaDAO) {
        this.tipoNominaDAO = tipoNominaDAO;
    }
}
