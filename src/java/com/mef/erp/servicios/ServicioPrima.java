/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mef.erp.servicios;

import com.mef.erp.modelo.PrimasDAO;
import com.mef.erp.modelo.entidad.Mensaje;
import com.mef.erp.modelo.entidad.Primas;
import com.mef.erp.modelo.entidad.RegistroPatronal;

import java.util.List;

/**
 *
 * @author Ernesto Castillo
 */
public class ServicioPrima implements ServicioPrimaIF {

    private PrimasDAO primasDAO;

    public Mensaje agregar(Primas entity, String uuidCxn) {
        return primasDAO.agregar(entity, uuidCxn);
    }

    public Mensaje actualizar(Primas entity, String uuidCxn) {
        return primasDAO.actualizar(entity, uuidCxn);
    }

    public Mensaje eliminar(Primas entity, String uuidCxn) {
        return primasDAO.eliminar(entity, uuidCxn);
    }

    public Mensaje getPrimasAll(String uuidCxn) {
        return primasDAO.getPrimasAll(uuidCxn);
    }

    public Mensaje getPrimasPorClaveRegistroPatronal(Long clave, String uuidCxn) {
        return primasDAO.getPrimasPorClaveRegistroPatronal(clave, uuidCxn);
    }

    public Mensaje DeletePrimasByRegistroPatronal(Long registroPatronal, String uuidCxn) {
        return primasDAO.DeletePrimasByRegistroPatronal(registroPatronal, uuidCxn);
    }

    public Mensaje SavePrimas(List<Primas> agrega, List<Primas> elimina, RegistroPatronal r, String uuidCxn) {
        return primasDAO.SavePrimas(agrega, elimina, r, uuidCxn);
    }

    public Mensaje DeletePrimas(List<Primas> p, String uuidCxn) {
        return primasDAO.DeletePrimas(p, uuidCxn);
    }

    public Mensaje deleteListClavesPrimas(Object[] valores, String uuidCxn) {
        return primasDAO.deleteListClavesPrimas(valores, uuidCxn);
    }

    public PrimasDAO getPrimasDAO() {
        return primasDAO;
    }

    public void setPrimasDAO(PrimasDAO primasDAO) {
        this.primasDAO = primasDAO;
    }

}
