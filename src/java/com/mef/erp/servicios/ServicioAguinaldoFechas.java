/**
 * @author: Daniel Fecha de Creación: 30/12/2016 Compañía: Exito Software
 * Descripción del programa: clase Agunaldos fechas para llamados a metodos de
 * HIBERNATE
 * -----------------------------------------------------------------------------
 */
package com.mef.erp.servicios;

import com.mef.erp.modelo.AguinaldoFechasDAO;
import com.mef.erp.modelo.entidad.AguinaldoFechas;
import com.mef.erp.modelo.entidad.Mensaje;
import java.util.List;

/**
 *
 *
 */
public class ServicioAguinaldoFechas implements ServicioAguinaldoFechasIF {

    private AguinaldoFechasDAO aguinaldoFechasDAO;

    @Override
    public Mensaje agregar(AguinaldoFechas entity, String uuidCxn) {
        return aguinaldoFechasDAO.agregar(entity, uuidCxn);
    }

    @Override
    public Mensaje actualizar(AguinaldoFechas entity, String uuidCxn) {
        return aguinaldoFechasDAO.actualizar(entity, uuidCxn);
    }

    @Override
    public Mensaje eliminar(AguinaldoFechas entity, String uuidCxn) {
        return aguinaldoFechasDAO.eliminar(entity, uuidCxn);
    }

    @Override
    public Mensaje getAguinaldoFechasAll(String uuidCxn) {
        return aguinaldoFechasDAO.getAguinaldoFechasAll(uuidCxn);
    }

    @Override
    public Mensaje SaveAguinaldoFechas(List<AguinaldoFechas> agrega, Object[] eliminados, String uuidCxn) {
        return aguinaldoFechasDAO.SaveAguinaldoFechas(agrega, eliminados, uuidCxn);
    }

    @Override
    public Mensaje getAguinaldoFechasPorClave(String claveRazonsocial, String uuidCxn) {
        return aguinaldoFechasDAO.getAguinaldoFechasPorClave(claveRazonsocial, uuidCxn);
    }

    public AguinaldoFechasDAO getAguinaldoFechasDAO() {
        return aguinaldoFechasDAO;
    }

    public void setAguinaldoFechasDAO(AguinaldoFechasDAO aguinaldoFechasDAO) {
        this.aguinaldoFechasDAO = aguinaldoFechasDAO;
    }

}
