/**
 * @author: Daniel
 * Fecha de Creación: 29/12/2016
 * Compañía: Exito Software
 * Descripción del programa: clase AguinaldoPagos para llamados a metodos de HIBERNATE
 * -----------------------------------------------------------------------------
*/
package com.mef.erp.modelo;

import com.mef.erp.modelo.entidad.AguinaldoPagos;
import com.mef.erp.modelo.entidad.Mensaje;
import java.util.List;

/**
 *
 * @author Desarrollo 094
 */
public interface AguinaldoPagosDAOIF extends GenericDAO<AguinaldoPagos, Long> {

    public Mensaje agregar(AguinaldoPagos entity, String uuidCxn);

    public Mensaje actualizar(AguinaldoPagos entity, String uuidCxn);

    public Mensaje eliminar(AguinaldoPagos entity, String uuidCxn);

    public Mensaje getAguinaldoPagosAll(String uuidCxn);
    
     Mensaje SaveAguinaldoPagos(List<AguinaldoPagos> agrega, Object[] eliminados,String uuidCxn);
}
