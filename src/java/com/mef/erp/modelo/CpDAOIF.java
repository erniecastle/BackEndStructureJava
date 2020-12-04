/**
 * @author: Ernesto castillo Fecha de Creación: 15/03/2011 Compañía: Exito
 * Software. Descripción del programa: inteface de clase de codigos postales
 * para llamados a metodos de HIBERNATE
 * -----------------------------------------------------------------------------
 * MODIFICACIONES:
 * -----------------------------------------------------------------------------
 * Clave: AAP01 Autor: Abraham Daniel Arjona Peraza Fecha: 30/07/2011
 * Descripción: Se Cambió clave a tipo String
 * -----------------------------------------------------------------------------
 * Clave:JSA01 Autor:Jose Armando Sanchez Acosta Fecha:10/11/2011 Descripción:Se
 * cambiaron todos los metodos hibernate al dao
 * -----------------------------------------------------------------------------
 */
package com.mef.erp.modelo;

import com.mef.erp.modelo.entidad.Cp;
import com.mef.erp.modelo.entidad.Mensaje;
import java.util.List;

public interface CpDAOIF extends GenericDAO<Cp, String> {

    Mensaje agregar(Cp entity, String uuidCxn);//JSA01

    Mensaje actualizar(Cp entity,String uuidCxn);//JSA01

    Mensaje eliminar(Cp entity,String uuidCxn);//JSA01

    Mensaje getCpAll(String uuidCxn);//JSA01

    Mensaje getCpPorClave(String clave,String uuidCxn);//AAP01

    Mensaje getCpPorCiudades(String claveCiudad,String uuidCxn);//JSA01

    Mensaje getCpPorMunicipio(String claveMunicipio,String uuidCxn);//JEVC01

    Mensaje getCpPorEstado(String claveEstado,String uuidCxn);//JEVC01

    Mensaje getCpPorPais(String clavePais,String uuidCxn);//JEVC01

    Mensaje consultaPorFiltrosCP(String query, Object[] campos, Object[] valores, Integer inicio, Integer rango,String uuidCxn);//JSA01

    Mensaje consultaPorRangos(Integer inicio, Integer rango,String uuidCxn);//JSA01

    Mensaje existeDato(String campo, Object valor,String uuidCxn);//JSA01

    Mensaje saveDeleteCp(List<Cp> entitysCambios, Object[] clavesDelete, int rango,String uuidCxn);//JSA01
}
