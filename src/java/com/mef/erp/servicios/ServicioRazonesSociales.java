/**
 * @author: Ernesto Valenzuela Fecha de Creación: 07/04/2011 Compañía: Exito
 * Software. Descripción del programa: clase SERVICIO Razones Sociales, para
 * llamados a metodos de objeto DAO
 * -----------------------------------------------------------------------------
 * MODIFICACIONES:
 * -----------------------------------------------------------------------------
 * Clave: AAP01 Autor: Abraham Daniel Arjona Peraza Fecha: 30/07/2011
 * Descripción: Se Cambió clave a tipo String
 * -----------------------------------------------------------------------------
 * Clave:JSA01 Autor:Jose Armando Sanchez Acosta Fecha:10/11/2011 Descripción:Se
 * cambiaron todos los metodos hibernate al dao
 * -----------------------------------------------------------------------------
 */
package com.mef.erp.servicios;

import com.mef.erp.modelo.RazonesSocialesDAO;
import com.mef.erp.modelo.entidad.Mensaje;
import com.mef.erp.modelo.entidad.RazonesSociales;
import java.util.List;

public class ServicioRazonesSociales implements ServicioRazonesSocialesIF {

    private RazonesSocialesDAO razonesSocialesDAO;

    public Mensaje agregar(RazonesSociales entity, String uuidCxn, String uuidCxnMaestra) {//JSA01
        return razonesSocialesDAO.agregar(entity, uuidCxn, uuidCxnMaestra);
    }

    public Mensaje actualizar(RazonesSociales entity, String uuidCxn, String uuidCxnMaestra) {//JSA01
        return razonesSocialesDAO.actualizar(entity, uuidCxn, uuidCxnMaestra);
    }

    public Mensaje eliminar(RazonesSociales entity, String uuidCxn, String uuidCxnMaestra) {//JSA01
        return razonesSocialesDAO.eliminar(entity, uuidCxn, uuidCxnMaestra);
    }

    public Mensaje getRazonesAll(String uuidCxn) {
        return razonesSocialesDAO.getRazonesAll(uuidCxn);
    }

    public Mensaje getRazonesPorClave(String clave, String uuidCxn) {//AAP01//JSA01
        return razonesSocialesDAO.getRazonesPorClave(clave, uuidCxn);
    }

    public Mensaje getRazonesSocialesPorClaves(String[] claveRazonesSociales, String uuidCxn) {
        return getRazonesSocialesDAO().getRazonesSocialesPorClaves(claveRazonesSociales, uuidCxn);
    }

    public Mensaje existeRFC(String rfc, String uuidCxn) {//JSA01
        return razonesSocialesDAO.existeRFC(rfc, uuidCxn);
    }

    public Mensaje consultaPorRangos(Integer inicio, Integer rango, String uuidCxn) {//JSA01
        return razonesSocialesDAO.consultaPorRangos(inicio, rango, uuidCxn);
    }

    public Mensaje saveDeleteRazonesSociales(List<RazonesSociales> entitysCambios, Object[] clavesDelete, int rango, String uuidCxn) {//JSA01
        return razonesSocialesDAO.saveDeleteRazonesSociales(entitysCambios, clavesDelete, rango, uuidCxn);
    }

    public Mensaje existeDato(String campo, Object valor, String uuidCxn) {//JSA01
        return razonesSocialesDAO.existeDato(campo, valor, uuidCxn);
    }

    public Mensaje consultaPorFiltros(String query, Object[] campos, Object[] valores, Integer inicio, Integer rango, String uuidCxn) {//JSA01
        return razonesSocialesDAO.consultaPorFiltrosRazonSocial(query, campos, valores, inicio, rango, uuidCxn);
    }

    public Mensaje consultaPorFiltroIN(String query, Object[] campos, Object[] valores, String uuidCxn) {//JSA01
        return razonesSocialesDAO.consultaPorFiltroIN(query, campos, valores, uuidCxn);
    }

    public RazonesSocialesDAO getRazonesSocialesDAO() {
        return razonesSocialesDAO;
    }

    public void setRazonesSocialesDAO(RazonesSocialesDAO razonesSocialesDAO) {
        this.razonesSocialesDAO = razonesSocialesDAO;
    }
}
