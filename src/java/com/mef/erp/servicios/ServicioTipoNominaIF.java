/**
 * @author: Abraham Daniel Arjona Peraza Fecha de Creación: 14/06/2011 Compañía:
 * Exito Software. Descripción del programa: Interfaface para servicio de tipos
 * de nomina
 * -----------------------------------------------------------------------------
 * MODIFICACIONES:
 * -----------------------------------------------------------------------------
 * Clave: Autor: Fecha: Descripción:
 * -----------------------------------------------------------------------------
 * Clave: Autor: Fecha: Descripción:
 * -----------------------------------------------------------------------------
 */
package com.mef.erp.servicios;

import com.mef.erp.modelo.entidad.Mensaje;
import com.mef.erp.modelo.entidad.TipoNomina;
import java.util.List;

public interface ServicioTipoNominaIF {

    /*List<TipoNomina>*/
    Mensaje getTipoNominaAll(String uuidCxn);

    /*TipoNomina*/
    Mensaje getTipoNominaPorClave(String clave, String uuidCxn);

    /*TipoNomina*/
    Mensaje agregar(TipoNomina entity, String uuidCxn);

    /*boolean*/
    Mensaje actualizar(TipoNomina entity, String uuidCxn);

    /*boolean*/
    Mensaje eliminar(TipoNomina entity, String uuidCxn);

    /*List<TipoNomina>*/
    Mensaje consultaPorFiltros(String query, Object[] campos, Object[] valores, String uuidCxn);

    /*List<TipoNomina>*/
    Mensaje consultaPorRangos(Integer inicio, Integer rango, String uuidCxn);

    /*Boolean*/
    Mensaje existeDato(String campo, Object valor, String uuidCxn);

    /*List<TipoNomina>*/
    Mensaje saveDeleteTipoNomina(List<TipoNomina> entitysCambios, Object[] clavesDelete, int rango, String uuidCxn);

    /*TipoNomina*/
    public Mensaje existeClave(String tabla, String[] campo, Object[] valores, String queryAntesDeFrom, String uuidCxn);
}
