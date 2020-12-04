/**
 * @author: Ernesto Castillo Fecha de Creación: 07/04/2011 Compañía: Exito
 * Software. Descripción del programa: Interface para servicio cruce
 * -----------------------------------------------------------------------------
 * MODIFICACIONES:
 * -----------------------------------------------------------------------------
 * Clave: AAP01 Autor: Abraham Daniel Arjona Peraza Fecha: 30/07/2011
 * Descripción: Se Cambió clave a tipo String
 * -----------------------------------------------------------------------------
 * Clave: Autor: Fecha: Descripción:
 * -----------------------------------------------------------------------------
 */
package com.mef.erp.servicios;

import com.mef.erp.modelo.entidad.Mensaje;
import com.mef.erp.modelo.entidad.RazonesSociales;
import java.util.List;

public interface ServicioRazonesSocialesIF {

    /*RazonesSociales*/
    Mensaje agregar(RazonesSociales entity, String uuidCxn, String uuidCxnMaestra);

    /*Boolean*/
    Mensaje actualizar(RazonesSociales entity, String uuidCxn, String uuidCxnMaestra);

    /*Boolean*/
    Mensaje eliminar(RazonesSociales entity, String uuidCxn, String uuidCxnMaestra);

    /*List<RazonesSociales>*/
    Mensaje getRazonesAll(String uuidCxn);

    /*RazonesSociales*/
    Mensaje getRazonesPorClave(String clave, String uuidCxn);//AAP01

    /*List<RazonesSociales>*/
    Mensaje getRazonesSocialesPorClaves(String[] claveRazonesSociales, String uuidCxn);

    /*Boolean*/
    Mensaje existeRFC(String rfc, String uuidCxn);

    /*List<RazonesSociales>*/
    Mensaje consultaPorRangos(Integer inicio, Integer rango, String uuidCxn);

    /*List<RazonesSociales>*/
    Mensaje consultaPorFiltros(String query, Object[] campos, Object[] valores, Integer inicio, Integer rango, String uuidCxn);

    /*List<RazonesSociales>*/
    Mensaje saveDeleteRazonesSociales(List<RazonesSociales> entitysCambios, Object[] clavesDelete, int rango, String uuidCxn);

    /*Boolean*/
    Mensaje existeDato(String campo, Object valor, String uuidCxn);

    /*List<RazonesSociales>*/
    public Mensaje consultaPorFiltroIN(String query, Object[] campos, Object[] valores, String uuidCxn);
}
