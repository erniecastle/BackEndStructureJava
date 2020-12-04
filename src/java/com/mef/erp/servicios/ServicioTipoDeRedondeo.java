/**
 * @author: Victor Lopez Fecha de Creación: 06/09/2011 Compañía: Macropro.
 * Descripción del programa: clase SERVICIO, para llamados a metodos de objeto
 * DAO
 * -----------------------------------------------------------------------------
 * MODIFICACIONES:
 * -----------------------------------------------------------------------------
 * Clave:JSA01 Autor:Jose Armando Sanchez Acosta Fecha:10/11/2011 Descripción:Se
 * cambiaron todos los metodos hibernate al dao
 * -----------------------------------------------------------------------------
 */
package com.mef.erp.servicios;

import com.mef.erp.modelo.TipoDeRedondeoDAO;
import com.mef.erp.modelo.entidad.DatosTipoValor;
import com.mef.erp.modelo.entidad.Mensaje;
import com.mef.erp.modelo.entidad.TipoDeRedondeo;
import com.mef.erp.modelo.entidad.TipoDeValor;
import java.util.List;

public class ServicioTipoDeRedondeo implements ServicioTipoDeRedondeoIF {

    private TipoDeRedondeoDAO tipoDeRedondeoDAO;

    public Mensaje agregar(TipoDeRedondeo entity, String uuidCxn) {//JSA01
        return tipoDeRedondeoDAO.agregar(entity, uuidCxn);
    }

    public Mensaje actualizar(TipoDeRedondeo entity, String uuidCxn) {//JSA01
        return tipoDeRedondeoDAO.actualizar(entity, uuidCxn);
    }

    public Mensaje eliminar(TipoDeRedondeo entity, String uuidCxn) {//JSA01
        return tipoDeRedondeoDAO.eliminar(entity, uuidCxn);
    }

    public Mensaje getTipoDeRedondeoAll(String uuidCxn) {//JSA01
        return tipoDeRedondeoDAO.getTipoDeRedondeoAll(uuidCxn);
    }

    public Mensaje getTipoDeRedondeoPorClave(String clave, String uuidCxn) {//JSA01
        return tipoDeRedondeoDAO.getTipoDeRedondeoPorClave(clave, uuidCxn);
    }

    public Mensaje consultaPorFiltros(String query, Object[] campos, Object[] valores, String uuidCxn) {//JSA01
        return tipoDeRedondeoDAO.consultaPorFiltrosTipoRedondeo(query, campos, valores, uuidCxn);
    }

    public Mensaje consultaPorRangos(Integer inicio, Integer rango, String uuidCxn) {//JSA01
        return tipoDeRedondeoDAO.consultaPorRangos(inicio, rango, uuidCxn);
    }

    public Mensaje existeDato(String campo, Object valor, String uuidCxn) {//JSA01
        return tipoDeRedondeoDAO.existeDato(campo, valor, uuidCxn);
    }

    public Mensaje agregarListTipoDeRedondeo(List<TipoDeRedondeo> entitys, int rango, String uuidCxn) {//AAP01
        return tipoDeRedondeoDAO.agregarListTipoDeRedondeo(entitys, rango, uuidCxn);
    }

    public Mensaje deleteListQuery(String tabla, String campo, Object[] valores, String uuidCxn) {//AAP01
        return tipoDeRedondeoDAO.deleteListQuerys(tabla, campo, valores, uuidCxn);
    }

    public Mensaje getTipoDeRedondeoPorTipoValor(TipoDeValor tipoValor, String uuidCxn) {//JSA01
        return tipoDeRedondeoDAO.getTipoDeRedondeoPorTipoValor(tipoValor, uuidCxn);
    }

    public Mensaje updateTipoRedondeoValor(TipoDeRedondeo entity, List<DatosTipoValor> eliminaDatosTipoValores, String uuidCxn) {//JSA01
        return tipoDeRedondeoDAO.updateTipoRedondeoValor(entity, eliminaDatosTipoValores, uuidCxn);
    }

    public TipoDeRedondeoDAO getTipoDeRedondeoDAO() {
        return tipoDeRedondeoDAO;
    }

    public void setTipoDeRedondeoDAO(TipoDeRedondeoDAO tipoDeRedondeoDAO) {
        this.tipoDeRedondeoDAO = tipoDeRedondeoDAO;
    }
}
