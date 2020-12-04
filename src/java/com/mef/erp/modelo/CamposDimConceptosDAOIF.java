/**
 * @author: Daniel Ruelas Compañía: Exito Software. Descripción del programa:
 * interface CampoDimConceptosDAOIF para llamados a metodos de HIBERNATE
 * -----------------------------------------------------------------------------
 * MODIFICACIONES:
 * -----------------------------------------------------------------------------
 */
package com.mef.erp.modelo;


import com.mef.erp.modelo.entidad.CamposDimConceptos;
import com.mef.erp.modelo.entidad.Mensaje;
import java.util.List;

/**
 *
 * @author Desarrollo 094
 */
public interface CamposDimConceptosDAOIF {

    Mensaje getCampoDimConceptoAll(String uuidCxn);

    Mensaje guardaryEliminar(List<CamposDimConceptos> ListaGuardar, List<CamposDimConceptos> ListaEliminar, String uuidCxn);

    Mensaje filtradoCampoDimConceptos(Long campoDim, Long Concepto, String uuidCxn);

    Mensaje filtradoCampoDim(Long campoDim, String uuidCxn);

    Mensaje filtradoConceptos(Long Concepto, String uuidCxn);
}
