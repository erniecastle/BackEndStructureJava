/**
 * @author: Victor Lopez Fecha de Creación: 27/09/2011 Compañía: MacroPro.
 * Descripción del programa: clase SERVICIO Tabla base nomina, para llamados a
 * metodos de objeto DAO
 * -----------------------------------------------------------------------------
 * MODIFICACIONES:
 * -----------------------------------------------------------------------------
 * Clave:JSA01 Autor:Jose Armando Sanchez Acosta Fecha:10/11/2011 Descripción:Se
 * cambiaron todos los metodos hibernate al dao
 * -----------------------------------------------------------------------------
 * Clave:JSA02 Autor:Jose Armando Sanchez Acosta Fecha:28/01/2013 Descripción:Se
 * agrego el metodo getBaseNominaReservadosONoReservados(Boolean soloReservados)
 * para obtener las basesNomina reservados o no reservadas.
 * -----------------------------------------------------------------------------
 */
package com.mef.erp.servicios;

import com.mef.erp.modelo.BaseNominaDAO;
import com.mef.erp.modelo.entidad.BaseNomina;
import com.mef.erp.modelo.entidad.Mensaje;
import java.util.List;

public class ServicioBaseNomina implements ServicioBaseNominaIF {

    private BaseNominaDAO baseNominaDAO;

    public Mensaje agregar(BaseNomina entity, String uuidCxn) {//JSA01
        return baseNominaDAO.agregar(entity, uuidCxn);
    }

    public Mensaje actualizar(BaseNomina entity, String uuidCxn) {//JSA01
        return baseNominaDAO.actualizar(entity, uuidCxn);
    }

    public Mensaje eliminar(BaseNomina entity, String uuidCxn) {//JSA01
        return baseNominaDAO.eliminar(entity, uuidCxn);
    }

    public Mensaje getBaseNominaAll(String uuidCxn) {//JSA01
        return baseNominaDAO.getBaseNominaAll(uuidCxn);
    }

    public Mensaje getBaseNominaReservadosONoReservados(Boolean soloReservados, String uuidCxn) {//JSA02
        return baseNominaDAO.getBaseNominaReservadosONoReservados(soloReservados, uuidCxn);
    }

    public Mensaje getBaseNominaPorClave(String clave, String uuidCxn) {//JSA01
        return baseNominaDAO.getBaseNominaPorClave(clave, uuidCxn);
    }

    public Mensaje consultaPorRangos(Integer inicio, Integer rango, String uuidCxn) {//JSA01
        return baseNominaDAO.consultaPorRangos(inicio, rango, uuidCxn);
    }

    public Mensaje existeDato(String campo, Object valor, String uuidCxn) {//JSA01
        return baseNominaDAO.existeDato(campo, valor, uuidCxn);
    }

    public Mensaje saveDeleteBaseNomina(List<BaseNomina> entitysCambios, Object[] clavesDelete, int rango, String uuidCxn) {
        return baseNominaDAO.saveDeleteBaseNomina(entitysCambios, clavesDelete, rango, uuidCxn);
    }

    public BaseNominaDAO getBaseNominaDAO() {
        return baseNominaDAO;
    }

    public void setBaseNominaDAO(BaseNominaDAO baseNominaDAO) {
        this.baseNominaDAO = baseNominaDAO;
    }

}
