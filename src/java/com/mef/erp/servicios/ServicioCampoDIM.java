/**
 * @author: Victor Lopez Fecha de Creación: 27/09/2011 Compañía: MacroPro.
 * Descripción del programa: clase SERVICIO ServicioCampoDIM, para llamados a
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

import com.mef.erp.modelo.CampoDIMDAO;
import com.mef.erp.modelo.entidad.CampoDIM;
import com.mef.erp.modelo.entidad.Mensaje;

public class ServicioCampoDIM implements ServicioCampoDIMIF {

    private CampoDIMDAO campoDIMDAO;

    public Mensaje agregar(CampoDIM entity, String uuidCxn) {//JSA01
        return campoDIMDAO.agregar(entity, uuidCxn);
    }

    public Mensaje actualizar(CampoDIM entity, String uuidCxn) {//JSA01
        return campoDIMDAO.actualizar(entity, uuidCxn);
    }

    public Mensaje eliminar(CampoDIM entity, String uuidCxn) {//JSA01
        return campoDIMDAO.eliminar(entity, uuidCxn);
    }

    public Mensaje getCampoDIMAll(String uuidCxn) {//JSA01
        return campoDIMDAO.getCampoDIMAll(uuidCxn);
    }

    public Mensaje getCampoDIMPorClave(String clave, String uuidCxn) {//JSA01
        return campoDIMDAO.getCampoDIMPorClave(clave, uuidCxn);
    }

    public Mensaje consultaPorRangos(Integer inicio, Integer rango, String uuidCxn) {//JSA01
        return campoDIMDAO.consultaPorRangos(inicio, rango, uuidCxn);
    }

    public Mensaje existeDato(String campo, Object valor, String uuidCxn) {
        return campoDIMDAO.existeDato(campo, valor, uuidCxn);
    }

    public CampoDIMDAO getCampoDIMDAO() {
        return campoDIMDAO;
    }

    public void setCampoDIMDAO(CampoDIMDAO campoDIMDAO) {
        this.campoDIMDAO = campoDIMDAO;
    }
}
