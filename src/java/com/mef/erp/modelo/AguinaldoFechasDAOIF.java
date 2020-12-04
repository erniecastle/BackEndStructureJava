/**
 * @author: Daniel
 * Fecha de Creación: 29/12/2016
 * Compañía: Exito Software
 * Descripción del programa: clase AguinaldoFechas para llamados a metodos de HIBERNATE
 * -----------------------------------------------------------------------------
*/
package com.mef.erp.modelo;

import com.mef.erp.modelo.entidad.AguinaldoFechas;
import com.mef.erp.modelo.entidad.Mensaje;
import java.util.List;

/**
 *
 * @author Desarrollo 094
 */
public interface AguinaldoFechasDAOIF extends GenericDAO<AguinaldoFechas, Long> {

    public Mensaje agregar(AguinaldoFechas entity, String uuidCxn);

    public Mensaje actualizar(AguinaldoFechas entity, String uuidCxn);

    public Mensaje eliminar(AguinaldoFechas entity, String uuidCxn);

    public Mensaje getAguinaldoFechasAll(String uuidCxn);

    Mensaje SaveAguinaldoFechas(List<AguinaldoFechas> agrega, Object[] eliminados,String uuidCxn);
    
     /*List<AguinaldoFechas>*/
     Mensaje getAguinaldoFechasPorClave(String claveRazonsocial,String uuidCxn);
}
