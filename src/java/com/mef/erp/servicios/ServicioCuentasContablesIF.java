/**
 * @author: Daniel Ruelas Fecha de Creación: 26/11/2015 Compañía: Exito Software
 * Descripción del programa: clase Cuentas contables para llamados a metodos de
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


import com.mef.erp.modelo.entidad.Mensaje;
import com.mef.erp.modelo.entidad.contabilidad.CuentasContables;
import java.util.List;

/**
 *
 * @author Daniel Ruelas
 */
public interface ServicioCuentasContablesIF {
     /*CuentasContables*/
    public Mensaje agregar(CuentasContables entity, String uuidCxn);

    /*boolean*/
    public Mensaje actualizar(CuentasContables entity, String uuidCxn);

    /*boolean*/
    public Mensaje eliminar(CuentasContables entity, String uuidCxn);

	/*List<CuentasContables>*/
    public Mensaje getCuentasContablesAll(String uuidCxn);

    /*CuentasContables*/
    public Mensaje getCuentasContablesClave(String clave, String uuidCxn);
    /*List<CuentasContables>*/
    Mensaje consultaPorRangosCuentasContables(Integer inicio, Integer rango, String uuidCxn);
    
     public Mensaje saveDeleteCuentasContables(List<CuentasContables> entitysCambios, Object[] clavesDelete, int rango, String uuidCxn);
     
      public Mensaje existeDato(String campo, Object valor,String uuidCxn);
}
