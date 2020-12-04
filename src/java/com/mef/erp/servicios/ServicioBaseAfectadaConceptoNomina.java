/**
 * @author: Victor Lopez Fecha de Creación: 27/09/2011 Compañía: MacroPro.
 * Descripción del programa: clase SERVICIO Tabla BaseAfectadaConceptoNomina,
 * para llamados a metodos de objeto DAO
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

import com.mef.erp.modelo.BaseAfectadaConceptoNominaDAO;
import com.mef.erp.modelo.entidad.BaseAfecConcepNom;
import com.mef.erp.modelo.entidad.Mensaje;
import java.util.List;

public class ServicioBaseAfectadaConceptoNomina implements ServicioBaseAfectadaConceptoNominaIF {

    private BaseAfectadaConceptoNominaDAO baseAfectadaConceptoNominaDAO;

    public Mensaje agregar(BaseAfecConcepNom entity, String uuidCxn) {//JSA01
        return baseAfectadaConceptoNominaDAO.agregar(entity, uuidCxn);
    }

    public Mensaje actualizar(BaseAfecConcepNom entity, String uuidCxn) {//JSA01
        return baseAfectadaConceptoNominaDAO.actualizar(entity, uuidCxn);
    }

    public Mensaje eliminar(BaseAfecConcepNom entity, String uuidCxn) {//JSA01
        return baseAfectadaConceptoNominaDAO.eliminar(entity, uuidCxn);
    }

    public Mensaje getBaseAfectadaConceptoNominaAll(String uuidCxn) {//JSA01
        return baseAfectadaConceptoNominaDAO.getBaseAfectadaConceptoNominaAll(uuidCxn);
    }

    public Mensaje getBaseAfectadaConceptoNominaPorClave(String clave, String uuidCxn) {//JSA01
        return baseAfectadaConceptoNominaDAO.getBaseAfectadaConceptoNominaPorClave(clave, uuidCxn);
    }

    public Mensaje consultaPorRangos(Integer inicio, Integer rango, String uuidCxn) {//JSA01
        return baseAfectadaConceptoNominaDAO.consultaPorRangos(inicio, rango, uuidCxn);
    }

    public Mensaje existeDato(String campo, Object valor, String uuidCxn) {
        return baseAfectadaConceptoNominaDAO.existeDato(campo, valor, uuidCxn);
    }

    public Mensaje agregarListaBaseAfectadaConceptoNomina(List<BaseAfecConcepNom> entitys, int rango, String uuidCxn) {//JSA01
        return baseAfectadaConceptoNominaDAO.agregarListaBaseAfectadaConceptoNomina(entitys, rango, uuidCxn);
    }

    public Mensaje deleteListQuery(String tabla, String campo, Object[] valores, String uuidCxn) {//JSA01
        return baseAfectadaConceptoNominaDAO.deleteListQuerys(tabla, campo, valores, uuidCxn);
    }

    public BaseAfectadaConceptoNominaDAO getBaseAfectadaConceptoNominaDAO() {
        return baseAfectadaConceptoNominaDAO;
    }

    public void setBaseAfectadaConceptoNominaDAO(BaseAfectadaConceptoNominaDAO baseAfectadaConceptoNominaDAO) {
        this.baseAfectadaConceptoNominaDAO = baseAfectadaConceptoNominaDAO;
    }
}
