/**
 * @author: Daniel Fecha de Creación: 30/12/2016 Compañía: Exito Software
 * Descripción del programa: clase Agunaldos pagos para llamados a metodos de
 * HIBERNATE
 * -----------------------------------------------------------------------------
 */
package com.mef.erp.servicios;

import com.mef.erp.modelo.entidad.AguinaldoPagos;
import com.mef.erp.modelo.entidad.Mensaje;
import java.util.List;


public interface ServicioAguinaldoPagosIF {

    /*AguinaldoPagos*/
    public Mensaje agregar(AguinaldoPagos entity, String uuidCxn);

    /*boolean*/
    public Mensaje actualizar(AguinaldoPagos entity, String uuidCxn);

    /*boolean*/
    public Mensaje eliminar(AguinaldoPagos entity, String uuidCxn);

    /*List<AguinaldoPagos>*/
    public Mensaje getAguinaldoPagosAll(String uuidCxn);

    /*boolean*/
    Mensaje SaveAguinaldoPagos(List<AguinaldoPagos> agrega, Object[] eliminados, String uuidCxn);
}
