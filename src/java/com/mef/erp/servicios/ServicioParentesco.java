/**
 * @author: Dayane Rocha Fecha de Creación: 15/06/2013 Compañía: Finesoft.
 * Descripción del programa: clase SERVICIO Parentesco, para llamados a metodos
 * de objeto DAO
 * -----------------------------------------------------------------------------
 * MODIFICACIONES:
 * -----------------------------------------------------------------------------
 * Clave: Autor: Fecha: Descripción:
 * -----------------------------------------------------------------------------
 * Clave: Autor: Fecha: Descripción:
 * -----------------------------------------------------------------------------
 */
package com.mef.erp.servicios;

import com.mef.erp.modelo.ParentescoDAO;
import com.mef.erp.modelo.entidad.Mensaje;
import com.mef.erp.modelo.entidad.Parentesco;
import java.util.List;

/**
 *
 * @author Dayane
 */
public class ServicioParentesco implements ServicioParentescoIF {

    private ParentescoDAO parentescoDAO;

    public Mensaje agregar(Parentesco entity, String uuidCxn) {

        return getParentescoDAO().agregar(entity, uuidCxn);
    }

    public Mensaje actualizar(Parentesco entity, String uuidCxn) {

        return getParentescoDAO().actualizar(entity, uuidCxn);
    }

    public Mensaje eliminar(Parentesco entity, String uuidCxn) {

        return getParentescoDAO().eliminar(entity, uuidCxn);
    }

    public Mensaje getParentescoAll(String uuidCxn) {

        return getParentescoDAO().getParentescoAll(uuidCxn);
    }

    public Mensaje getParentescosPorClave(String clave, String uuidCxn) {

        return getParentescoDAO().getParentescosPorClave(clave, uuidCxn);
    }

    public Mensaje consultaPorFiltrosParentescos(String query, Object[] campos, Object[] valores, String uuidCxn) {

        return getParentescoDAO().consultaPorFiltrosParentescos(query, campos, valores, uuidCxn);
    }

    public Mensaje consultaPorRangosParentescos(Integer inicio, Integer rango, String uuidCxn) {

        return getParentescoDAO().consultaPorRangosParentescos(inicio, rango, uuidCxn);
    }

    public Mensaje existeDato(String campo, Object valor, String uuidCxn) {

        return getParentescoDAO().existeDato(campo, valor, uuidCxn);
    }

    public Mensaje saveDeleteParentesco(List<Parentesco> entitysCambios, Object[] clavesDelete, int rango, String uuidCxn) {

        return getParentescoDAO().saveDeleteParentesco(entitysCambios, clavesDelete, rango, uuidCxn);
    }

    public ParentescoDAO getParentescoDAO() {
        return parentescoDAO;
    }

    public void setParentescoDAO(ParentescoDAO parentescoDAO) {
        this.parentescoDAO = parentescoDAO;
    }

}
