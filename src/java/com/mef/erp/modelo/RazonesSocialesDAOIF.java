/**
 * @author: Ernesto castillo Fecha de Creación: 07/04/2011 Compañía: Exito
 * Software. Descripción del programa: inteface de clase de razones sociales
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

import com.mef.erp.modelo.entidad.Mensaje;
import com.mef.erp.modelo.entidad.RazonesSociales;
import java.util.List;

public interface RazonesSocialesDAOIF extends GenericDAO<RazonesSociales, String> {//AAP01

    Mensaje agregar(RazonesSociales entity, String uuidCxn, String uuidCxnMaestra);//JSA01

    Mensaje actualizar(RazonesSociales entity, String uuidCxn, String uuidCxnMaestra);//JSA01

    Mensaje eliminar(RazonesSociales entity, String uuidCxn, String uuidCxnMaestra);//JSA01

    Mensaje getRazonesAll(String uuidCxn);//JSA01

    Mensaje getRazonesPorClave(String clave, String uuidCxn);//AAP01//JSA01

    Mensaje getRazonesSocialesPorClaves(String[] claveRazonesSociales, String uuidCxn);

    Mensaje existeRFC(String rfc, String uuidCxn);//JSA01

    Mensaje consultaPorRangos(Integer inicio, Integer rango, String uuidCxn);//JSA01

    Mensaje consultaPorFiltrosRazonSocial(String query, Object[] campos, Object[] valores, Integer inicio, Integer rango, String uuidCxn);//JSA01

    Mensaje saveDeleteRazonesSociales(List<RazonesSociales> entitysCambios, Object[] clavesDelete, int rango, String uuidCxn);//JSA01

    Mensaje existeDato(String campo, Object valor, String uuidCxn);//JSA01

    Mensaje consultaPorFiltroIN(String query, Object[] campos, Object[] valores, String uuidCxn);//JSA01
}
