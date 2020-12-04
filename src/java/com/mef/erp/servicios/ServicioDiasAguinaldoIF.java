/**
 * @author: Daniel Fecha de Creación: 30/12/2016 Compañía: Exito Software
 * Descripción del programa: clase Dias Aguinaldo para llamados a metodos de
 * HIBERNATE
 * -----------------------------------------------------------------------------
 */
package com.mef.erp.servicios;

import com.mef.erp.modelo.entidad.DiasAguinaldo;
import com.mef.erp.modelo.entidad.Mensaje;
import java.util.List;

/**
 *
 * @author Desarrollo 094
 */
public interface ServicioDiasAguinaldoIF {

    /*DiasAguinaldo*/
    public Mensaje agregar(DiasAguinaldo entity, String uuidCxn);

    /*boolean*/
    public Mensaje actualizar(DiasAguinaldo entity, String uuidCxn);

    /*boolean*/
    public Mensaje eliminar(DiasAguinaldo entity, String uuidCxn);

    /*List<DiasAguinaldo>*/
    public Mensaje getDiasAguinaldoAll(String uuidCxn);

    /*boolean*/
    Mensaje SaveDiasAguinaldo(List<DiasAguinaldo> agrega, Object[] eliminados, String uuidCxn);
    
    /*List<DaisAguinaldo>*/
     Mensaje getDiasAguinaldoPorClave(String claveRazonsocial,String uuidCxn);
}
