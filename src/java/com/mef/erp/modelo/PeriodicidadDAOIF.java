/**
 * @author: Abraham Daniel Arjona Peraza Fecha de Creación: 15/06/2011 Compañía:
 * Exito Software. Descripción del programa: inteface de clase de Periodicidad
 * para llamados a metodos de HIBERNATE
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

import com.mef.erp.modelo.entidad.Mensaje;
import com.mef.erp.modelo.entidad.Periodicidad;
import java.util.List;

public interface PeriodicidadDAOIF {

    Mensaje getPeriodicidadAll(String uuidCxn);//JSA01

    Mensaje getPeriodicidadPorClave(String clave, String uuidCxn);//JSA01

    Mensaje agregar(Periodicidad entity, String uuidCxn);//JSA01

    Mensaje actualizar(Periodicidad entity, String uuidCxn);//JSA01

    Mensaje eliminar(Periodicidad entity, String uuidCxn);//JSA01

    Mensaje consultaPorFiltrosPeriodicidad(String query, Object[] campos, Object[] valores, Integer inicio, Integer rango, String uuidCxn);//JSA01

    Mensaje consultaPorRangos(Integer inicio, Integer rango, String uuidCxn);//JSA01

    Mensaje existeDato(String campo, Object valor, String uuidCxn);//JSA01

    Mensaje saveDeletePeriodicidad(List<Periodicidad> entitysCambios, Object[] clavesDelete, int rango, String uuidCxn);
}
