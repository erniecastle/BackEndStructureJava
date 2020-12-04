/**
 * @author: Daniel Ruelas Fecha de Creación: 26/11/2015 Compañía: Exito Software
 * Descripción del programa: servicio de  Cuentas contables para llamados a metodos de
 * HIBERNATE
 * -----------------------------------------------------------------------------
 * MODIFICACIONES:
 * -----------------------------------------------------------------------------
 * Clave: Autor Fecha: Descripción:
 * -----------------------------------------------------------------------------
 * Clave: Autor: Fecha: Descripción:
 * -----------------------------------------------------------------------------
 */

package com.mef.erp.servicios;

import com.mef.erp.modelo.CuentasContablesDAO;
import com.mef.erp.modelo.entidad.Mensaje;
import com.mef.erp.modelo.entidad.contabilidad.CuentasContables;
import java.util.List;

/**
 *
 * @author Daniel Ruelas
 */
public class ServicioCuentasContables implements ServicioCuentasContablesIF{
  private CuentasContablesDAO cuentasContablesDAO;
    @Override
    public Mensaje agregar(CuentasContables entity, String uuidCxn) {
        return cuentasContablesDAO.agregar(entity, uuidCxn);
    }

    @Override
    public Mensaje actualizar(CuentasContables entity, String uuidCxn) {
        return cuentasContablesDAO.actualizar(entity, uuidCxn);
    }

    @Override
    public Mensaje eliminar(CuentasContables entity, String uuidCxn) {
       return cuentasContablesDAO.eliminar(entity, uuidCxn);
    }

    @Override
    public Mensaje getCuentasContablesAll(String uuidCxn) {
        return cuentasContablesDAO.getCuentasContablesAll(uuidCxn);
    }

    @Override
    public Mensaje getCuentasContablesClave(String clave, String uuidCxn) {
         return cuentasContablesDAO.getCuentasContablesClave(clave, uuidCxn);
    }

    @Override
    public Mensaje consultaPorRangosCuentasContables(Integer inicio, Integer rango, String uuidCxn) {
        return cuentasContablesDAO.consultaPorRangosCuentasContables(inicio, rango, uuidCxn);
    }

    @Override
    public Mensaje saveDeleteCuentasContables(List<CuentasContables> entitysCambios, Object[] clavesDelete, int rango, String uuidCxn) {
       return cuentasContablesDAO.saveDeleteCuentasContables(entitysCambios, clavesDelete, rango, uuidCxn);
    }

    @Override
    public Mensaje existeDato(String campo, Object valor, String uuidCxn) {
        return cuentasContablesDAO.existeDato(campo, valor, uuidCxn);
    }

    public CuentasContablesDAO getCuentasContablesDAO() {
        return cuentasContablesDAO;
    }

    public void setCuentasContablesDAO(CuentasContablesDAO cuentasContablesDAO) {
        this.cuentasContablesDAO = cuentasContablesDAO;
    }
    
}
