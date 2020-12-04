/**
 * @author: Ernesto Valenzuela Fecha de Creación: 13/04/2011 Compañía: Exito
 * Software. Descripción del programa: clase SERVICIO codigos postales, para
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

import com.mef.erp.modelo.CentroDeCostosDAO;
import com.mef.erp.modelo.entidad.CentroDeCosto;
import com.mef.erp.modelo.entidad.Mensaje;
import java.util.List;

public class ServicioCentroDeCostos implements ServicioCentroDeCostosIF {

    private CentroDeCostosDAO centroDeCostosDAO;

    public Mensaje agregar(CentroDeCosto entity, String uuidCxn) {//JSA01
        return centroDeCostosDAO.agregar(entity, uuidCxn);
    }

    public Mensaje actualizar(CentroDeCosto entity, String uuidCxn) {//JSA01
        return centroDeCostosDAO.actualizar(entity, uuidCxn);
    }

    public Mensaje eliminar(CentroDeCosto entity, String uuidCxn) {//JSA01
        return centroDeCostosDAO.eliminar(entity, uuidCxn);
    }

    public Mensaje getCentroDeCostoAll(String claveRazonesSocial, String uuidCxn) {//JSA01
        return centroDeCostosDAO.getCentroDeCostoAll(claveRazonesSocial, uuidCxn);
    }

    public Mensaje getCentroDeCostoPorClave(String clave, String claveRazonesSocial, String uuidCxn) {//JSA01
        return centroDeCostosDAO.getCentroDeCostoPorClave(clave, claveRazonesSocial, uuidCxn);
    }

    public Mensaje getCentroDeCostoPorClaveYRazon(String clave, String claveRazon, String uuidCxn) {//JSA01
        return centroDeCostosDAO.getCentroDeCostoPorClaveYRazon(clave, claveRazon, uuidCxn);
    }

    public Mensaje consultaPorRangos(Integer inicio, Integer rango, String[] camposWhere, Object[] camposWhereValores, String uuidCxn) {//JSA01
        return centroDeCostosDAO.consultaPorRangosCentroCosto(inicio, rango, camposWhere, camposWhereValores, uuidCxn);
    }

    public Mensaje consultaPorFiltros(String query, Object[] campos, Object[] valores, Integer inicio, Integer rango, String uuidCxn) {//JSA01
        return centroDeCostosDAO.consultaPorFiltrosCentroCosto(query, campos, valores, inicio, rango, uuidCxn);
    }

    public Mensaje saveDeleteCentroDeCosto(List<CentroDeCosto> entitysCambios, Object[] clavesDelete, int rango, String uuidCxn) {//JSA01
        return centroDeCostosDAO.saveDeleteCentroDeCosto(entitysCambios, clavesDelete, rango, uuidCxn);
    }

    public Mensaje existeDato(String campo, Object valor, String uuidCxn) {//JSA01
        return centroDeCostosDAO.existeDato(campo, valor, uuidCxn);
    }

    public CentroDeCostosDAO getCentroDeCostosDAO() {
        return centroDeCostosDAO;
    }

    public void setCentroDeCostosDAO(CentroDeCostosDAO centroDeCostosDAO) {
        this.centroDeCostosDAO = centroDeCostosDAO;
    }
}
