/**
 * @author: Victor Lopeez
 * Fecha de Creación: 11/10/2014
 * Compañía: MacroPro.
 * Descripción del programa: inteface de clase de SemaforoCalculoNomina para llamados a 
 * metodos de HIBERNATE
 * -----------------------------------------------------------------------------
 * MODIFICACIONES:
 * -----------------------------------------------------------------------------
 */
package com.mef.erp.modelo;

import com.mef.erp.modelo.entidad.Mensaje;
import com.mef.erp.modelo.entidad.SemaforoTimbradoPac;

public interface SemaforoTimbradoPacDAOIF extends GenericDAO<SemaforoTimbradoPac, String> {

    Mensaje agregar(SemaforoTimbradoPac entity, String uuidCxn);

    Mensaje eliminar(SemaforoTimbradoPac entity, String uuidCxn);
}
