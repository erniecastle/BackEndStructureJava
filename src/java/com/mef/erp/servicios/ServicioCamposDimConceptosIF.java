/**
 * @author: Daniel Ruelas Fecha de Creación: 21/10/2015 Compañía: Exito Software.
 * Descripción del programa: interface servicio tabla Campos Dim Conceptos
 * -----------------------------------------------------------------------------
 * MODIFICACIONES:
 * -----------------------------------------------------------------------------
 * Clave: Autor: Fecha: Descripción:
 * -----------------------------------------------------------------------------
 * Clave: Autor: Fecha: Descripción:
 * -----------------------------------------------------------------------------
 */
package com.mef.erp.servicios;


import com.mef.erp.modelo.entidad.CamposDimConceptos;
import com.mef.erp.modelo.entidad.Mensaje;
import java.util.List;

/**
 *
 * @author Desarrollo 094
 */
public interface ServicioCamposDimConceptosIF {

    /*List<CamposDimConceptos>*/
    public Mensaje getCampoDimConceptoAll(String uuidCxn);

    public Mensaje guardaryEliminar(List<CamposDimConceptos> ListaGuardar, List<CamposDimConceptos> ListaEliminar, String uuidCxn);

     /*List<CamposDimConceptos>*/
    public Mensaje filtradoCampoDimConceptos(Long campoDim, Long Concepto, String uuidCxn);
    
     Mensaje filtradoCampoDim(Long campoDim, String uuidCxn);

    Mensaje filtradoConceptos(Long Concepto, String uuidCxn);
}
