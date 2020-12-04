/*
 * @author: Daniel Ruelas
 * Fecha de Creación: 26/11/2015
 * Compañía: Exito Software
 * Descripción del programa: clase Cuentas contables para llamados a metodos de HIBERNATE
 */
package com.mef.erp.modelo;

import com.mef.erp.modelo.entidad.Mensaje;
import com.mef.erp.modelo.entidad.contabilidad.CuentasContables;
import java.util.List;

/**
 *
 * @author Daniel Ruelas
 */
public interface CuentasContablesDAOIF extends GenericDAO<CuentasContables, Long> {

    public Mensaje agregar(CuentasContables entity, String uuidCxn);

    public Mensaje actualizar(CuentasContables entity, String uuidCxn);

    public Mensaje eliminar(CuentasContables entity, String uuidCxn);

    public Mensaje getCuentasContablesAll(String uuidCxn);

    public Mensaje getCuentasContablesClave(String clave, String uuidCxn);
    
     public Mensaje consultaPorRangosCuentasContables(Integer inicio, Integer rango, String uuidCxn);
     
     public Mensaje saveDeleteCuentasContables(List<CuentasContables> entitysCambios, Object[] clavesDelete, int rango, String uuidCxn);
     
     public Mensaje existeDato(String campo, Object valor,String uuidCxn);

}
