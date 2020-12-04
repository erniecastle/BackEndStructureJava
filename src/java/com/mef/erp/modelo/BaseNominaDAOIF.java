/**
 * @author: Victor Lopez Compañía: Macropro. Descripción del programa: interface
 * BaseNominaDAOIF para llamados a metodos de HIBERNATE
 * -----------------------------------------------------------------------------
 * MODIFICACIONES:
 * -----------------------------------------------------------------------------
 * Clave:JSA01 Autor:Jose Armando Sanchez Acosta Fecha:10/11/2011 Descripción:Se
 * cambiaron todos los metodos hibernate al dao
 * -----------------------------------------------------------------------------
 * Clave:JSA02 Autor:Jose Armando Sanchez Acosta Fecha:28/01/2013 Descripción:Se
 * agrego el metodo getBaseNominaReservadosONoReservados(Boolean soloReservados)
 * para obtener las basesNomina reservados o no reservadas.
 * -----------------------------------------------------------------------------
 */
package com.mef.erp.modelo;

import com.mef.erp.modelo.entidad.BaseNomina;
import com.mef.erp.modelo.entidad.Mensaje;
import java.util.List;

/**
 *
 * @author daniel
 */
public interface BaseNominaDAOIF {

    Mensaje agregar(BaseNomina entity, String uuidCxn);//JSA01

    Mensaje actualizar(BaseNomina entity, String uuidCxn);//JSA01

    Mensaje eliminar(BaseNomina entity, String uuidCxn);//JSA01

    Mensaje getBaseNominaAll(String uuidCxn);//JSA01

    public Mensaje getBaseNominaReservadosONoReservados(Boolean soloReservados, String uuidCxn);//JSA02

    Mensaje getBaseNominaPorClave(String clave, String uuidCxn);//JSA01

    Mensaje consultaPorRangos(Integer inicio, Integer rango, String uuidCxn);//JSA01

    Mensaje existeDato(String campo, Object valor, String uuidCxn);//JSA01

    public Mensaje saveDeleteBaseNomina(List<BaseNomina> entitysCambios, Object[] clavesDelete, int rango, String uuidCxn);
}
