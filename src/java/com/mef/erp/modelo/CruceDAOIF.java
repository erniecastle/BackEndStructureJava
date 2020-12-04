/**
 * @author: Ernesto castillo Fecha de Creación: 27/05/2011 Compañía: Exito
 * Software. Descripción del programa: inteface de clase de cruce para llamados
 * a metodos de HIBERNATE
 * -----------------------------------------------------------------------------
 * MODIFICACIONES:
 * -----------------------------------------------------------------------------
 * Clave:JSA01 Autor:Jose Armando Sanchez Acosta Fecha:10/11/2011 Descripción:Se
 * cambiaron todos los metodos hibernate al dao
 * -----------------------------------------------------------------------------
 * Clave: Autor: Fecha: Descripción:
 * -----------------------------------------------------------------------------
 */
package com.mef.erp.modelo;

import com.mef.erp.modelo.entidad.Cruce;
import com.mef.erp.modelo.entidad.Mensaje;
import java.util.List;

public interface CruceDAOIF extends GenericDAO<Cruce, Integer> {

    Mensaje agregar(String uuidCxn, Cruce entity);//JSA01

    Mensaje actualizar(String uuidCxn, Cruce entity);//JSA01

    Mensaje eliminar(String uuidCxn, Cruce entity);//JSA01

    Mensaje getCruceAll(String uuidCxn);//JSA01

    Mensaje existeClaveElemento(String uuidCxn, String claveelemento, String elemento, Long parametro);//JSA01

    Mensaje getCrucePorParametros(String uuidCxn, Long claveParametro);//JSA01

    Mensaje getCrucePorElemento(String uuidCxn, String elemento);//JSA01

    Mensaje getCrucePorParamElemento(String uuidCxn, Long claveParametro, String elemento);//JSA01

    Mensaje getCrucePorElemeYClave(String uuidCxn, String elemento, String claveelemento);//JSA01

    Mensaje getCrucePorParaElemeYClave(String uuidCxn, Long claveParametro, String elemento, String claveelemento);//JSA01

    Mensaje SaveDeleteCruces(String uuidCxn, List<Cruce> AgreModif, List<Cruce> Ordenados, Object[] clavesDelete);

    Mensaje SaveCruces(String uuidCxn, List<Cruce> c);//JSA01

    Mensaje DeleteCruces(String uuidCxn, List<Cruce> c);//JSA01
}
