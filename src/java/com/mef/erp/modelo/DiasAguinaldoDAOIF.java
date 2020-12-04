/**
 * @author: Daniel
 * Fecha de Creación: 29/12/2016
 * Compañía: Exito Software
 * Descripción del programa: clase DiasAguinaldo para llamados a metodos de HIBERNATE
 * -----------------------------------------------------------------------------
*/
package com.mef.erp.modelo;

import com.mef.erp.modelo.entidad.Despensa;
import com.mef.erp.modelo.entidad.DiasAguinaldo;
import com.mef.erp.modelo.entidad.Mensaje;
import java.util.List;

/**
 *
 * @author Desarrollo 094
 */
public interface DiasAguinaldoDAOIF extends GenericDAO<DiasAguinaldo, Long> {

    public Mensaje agregar(DiasAguinaldo entity, String uuidCxn);

    public Mensaje actualizar(DiasAguinaldo entity, String uuidCxn);

    public Mensaje eliminar(DiasAguinaldo entity, String uuidCxn);

    public Mensaje getDiasAguinaldoAll(String uuidCxn);
    
    Mensaje SaveDiasAguinaldo(List<DiasAguinaldo> agrega,Object[] eliminados,String uuidCxn);
    
    Mensaje getDiasAguinaldoPorClave(String claveRazonsocial,String uuidCxn);

}
