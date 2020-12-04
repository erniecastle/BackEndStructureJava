/**
 * @author: Daniel Fecha de Creación: 30/12/2016 Compañía: Exito Software
 * Descripción del programa: clase Dias Aguinaldo para llamados a metodos de
 * HIBERNATE
 * -----------------------------------------------------------------------------
 */
package com.mef.erp.servicios;

import com.mef.erp.modelo.DiasAguinaldoDAO;
import com.mef.erp.modelo.entidad.DiasAguinaldo;
import com.mef.erp.modelo.entidad.Mensaje;
import java.util.List;

/**
 *
 * @author Desarrollo 094
 */
public class ServicioDiasAguinaldo implements ServicioDiasAguinaldoIF {

    private DiasAguinaldoDAO diasAguinaldoDAO;

    @Override
    public Mensaje agregar(DiasAguinaldo entity, String uuidCxn) {
        return diasAguinaldoDAO.agregar(entity, uuidCxn);
    }

    @Override
    public Mensaje actualizar(DiasAguinaldo entity, String uuidCxn) {
        return diasAguinaldoDAO.actualizar(entity, uuidCxn);
    }

    @Override
    public Mensaje eliminar(DiasAguinaldo entity, String uuidCxn) {
        return diasAguinaldoDAO.eliminar(entity, uuidCxn);
    }

    @Override
    public Mensaje getDiasAguinaldoAll(String uuidCxn) {
        return diasAguinaldoDAO.getDiasAguinaldoAll(uuidCxn);
    }

    @Override
    public Mensaje SaveDiasAguinaldo(List<DiasAguinaldo> agrega, Object[] eliminados, String uuidCxn) {
        return diasAguinaldoDAO.SaveDiasAguinaldo(agrega, eliminados, uuidCxn);
    }

    @Override
    public Mensaje getDiasAguinaldoPorClave(String claveRazonsocial, String uuidCxn) {
        return diasAguinaldoDAO.getDiasAguinaldoPorClave(claveRazonsocial, uuidCxn);
    }

    public DiasAguinaldoDAO getDiasAguinaldoDAO() {
        return diasAguinaldoDAO;
    }

    public void setDiasAguinaldoDAO(DiasAguinaldoDAO diasAguinaldoDAO) {
        this.diasAguinaldoDAO = diasAguinaldoDAO;
    }

}
