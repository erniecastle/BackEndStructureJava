/**
 * @author: Daniel Fecha de Creación: 30/12/2016 Compañía: Exito Software
 * Descripción del programa: clase Agunaldos pagos para llamados a metodos de
 * HIBERNATE
 * -----------------------------------------------------------------------------
 */
package com.mef.erp.servicios;

import com.mef.erp.modelo.AguinaldoPagosDAO;
import com.mef.erp.modelo.entidad.AguinaldoPagos;
import com.mef.erp.modelo.entidad.Mensaje;
import java.util.List;

public class ServicioAguinaldoPagos implements ServicioAguinaldoPagosIF {

    private AguinaldoPagosDAO aguinaldoPagosDAO;

    @Override
    public Mensaje agregar(AguinaldoPagos entity, String uuidCxn) {
        return aguinaldoPagosDAO.agregar(entity, uuidCxn);
    }

    @Override
    public Mensaje actualizar(AguinaldoPagos entity, String uuidCxn) {
        return aguinaldoPagosDAO.actualizar(entity, uuidCxn);
    }

    @Override
    public Mensaje eliminar(AguinaldoPagos entity, String uuidCxn) {
        return aguinaldoPagosDAO.eliminar(entity, uuidCxn);
    }

    @Override
    public Mensaje getAguinaldoPagosAll(String uuidCxn) {
        return aguinaldoPagosDAO.getAguinaldoPagosAll(uuidCxn);
    }

    @Override
    public Mensaje SaveAguinaldoPagos(List<AguinaldoPagos> agrega, Object[] eliminados, String uuidCxn) {
        return aguinaldoPagosDAO.SaveAguinaldoPagos(agrega, eliminados, uuidCxn);
    }

    public AguinaldoPagosDAO getAguinaldoPagosDAO() {
        return aguinaldoPagosDAO;
    }

    public void setAguinaldoPagosDAO(AguinaldoPagosDAO aguinaldoPagosDAO) {
        this.aguinaldoPagosDAO = aguinaldoPagosDAO;
    }

}
