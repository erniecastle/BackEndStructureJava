/**
 * @author: Victor Lopez Fecha de Creación: 27/09/2011 Compañía: MacroPro.
 * Descripción del programa: interface servicio tabla base nomina
 * -----------------------------------------------------------------------------
 * MODIFICACIONES:
 * -----------------------------------------------------------------------------
 * Clave:JSA01 Autor:Jose Armando Sanchez Acosta Fecha:28/01/2013 Descripción:Se
 * agrego el metodo getBaseNominaReservadosONoReservados(Boolean soloReservados)
 * para obtener las basesNomina reservados o no reservadas.
 * -----------------------------------------------------------------------------
 * Clave: Autor: Fecha: Descripción:
 * -----------------------------------------------------------------------------
 */
package com.mef.erp.servicios;

import com.mef.erp.modelo.entidad.BaseNomina;
import com.mef.erp.modelo.entidad.Mensaje;
import java.util.List;

public interface ServicioBaseNominaIF {

    /*BaseNomina*/
    Mensaje agregar(BaseNomina entity, String uuidCxn);

    /*boolean*/
    Mensaje actualizar(BaseNomina entity, String uuidCxn);

    /*boolean*/
    Mensaje eliminar(BaseNomina entity, String uuidCxn);

    /*List<BaseNomina> */
    Mensaje getBaseNominaAll(String uuidCxn);

    /*List<BaseNomina> */
    Mensaje getBaseNominaReservadosONoReservados(Boolean soloReservados, String uuidCxn);//JSA01

    /*BaseNomina*/
    Mensaje getBaseNominaPorClave(String clave, String uuidCxn);

    /*List<BaseNomina> */
    Mensaje consultaPorRangos(Integer inicio, Integer rango, String uuidCxn);

    /*Boolean*/
    Mensaje existeDato(String campo, Object valor, String uuidCxn);

    /*List<BaseNomina> */
    public Mensaje saveDeleteBaseNomina(List<BaseNomina> entitysCambios, Object[] clavesDelete, int rango, String uuidCxn);

}
