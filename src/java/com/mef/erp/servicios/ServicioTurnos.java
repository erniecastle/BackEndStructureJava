/**
 * @author: Ernesto Valenzuela Fecha de Creación: 04/10/2011 Compañía: MacroPro.
 * Descripción del programa: clase SERVICIO turnos, para llamados a metodos de
 * objeto DAO
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

import com.mef.erp.modelo.TurnosDAO;
import com.mef.erp.modelo.entidad.Mensaje;
import com.mef.erp.modelo.entidad.Turnos;

/**
 *
 * @author Ernesto
 */
public class ServicioTurnos implements ServicioTurnosIF {

    private TurnosDAO turnosDAO;

    public Mensaje agregar(Turnos entity, String uuidCxn) {//JSA01
        return turnosDAO.agregar(entity, uuidCxn);
    }

    public Mensaje actualizar(Turnos entity, String uuidCxn) {//JSA01
        return turnosDAO.actualizar(entity, uuidCxn);
    }

    public Mensaje eliminar(Turnos entity, String uuidCxn) {//JSA01
        return turnosDAO.eliminar(entity, uuidCxn);
    }

    public Mensaje UpdateTurnos(Turnos entity, Object[] eliminados, String uuidCxn) {
        return getTurnosDAO().UpdateTurnos(entity, eliminados, uuidCxn);
    }

    public Mensaje getTurnosAll(String claveRazonesSocial, String uuidCxn) {//JSA01
        return turnosDAO.getTurnosAll(claveRazonesSocial, uuidCxn);
    }

    public Mensaje getTurnosPorClave(String clave, String uuidCxn) {//JSA01
        return turnosDAO.getTurnosPorClave(clave, uuidCxn);
    }

    public TurnosDAO getTurnosDAO() {
        return turnosDAO;
    }

    public void setTurnosDAO(TurnosDAO turnosDAO) {
        this.turnosDAO = turnosDAO;
    }
}
