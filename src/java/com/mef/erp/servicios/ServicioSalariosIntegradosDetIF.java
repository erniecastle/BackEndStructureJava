/**
 * @author: Victor Lopez Fecha de Creación: 02/07/2012 Compañía: MacroPro.
 * Descripción del programa: interface servicio ServicioSalariosIntegradosDetIF
 * -----------------------------------------------------------------------------
 * MODIFICACIONES:
 * -----------------------------------------------------------------------------
 * Clave: Autor: Fecha: Descripción:
 * -----------------------------------------------------------------------------
 * Clave: Autor: Fecha: Descripción:
 * -----------------------------------------------------------------------------
 */
package com.mef.erp.servicios;

import com.mef.erp.modelo.entidad.Empleados;
import com.mef.erp.modelo.entidad.Mensaje;
import com.mef.erp.modelo.entidad.RazonesSociales;
import com.mef.erp.modelo.entidad.RegistroPatronal;
import com.mef.erp.modelo.entidad.SalariosIntegradosDet;
import java.util.Date;

public interface ServicioSalariosIntegradosDetIF {

    /*SalariosIntegradosDet*/
    Mensaje agregar(SalariosIntegradosDet entity, String uuidCxn);

    /*boolean*/
    Mensaje actualizar(SalariosIntegradosDet entity, String uuidCxn);

    /*boolean*/
    Mensaje eliminar(SalariosIntegradosDet entity, String uuidCxn);

    /*List<SalariosIntegradosDet>*/
    Mensaje getSalariosIntegradosDetAll(RazonesSociales razonesSociales, String uuidCxn);

    /*SalariosIntegradosDet*/
    Mensaje getSalariosIntegradosDetPorEmpleadoyRegPat(Empleados empleados, RegistroPatronal registroPatronal, Date fecha, String uuidCxn);

    /*List<SalariosIntegradosDet>*/
    Mensaje getSalariosIntegradosDetPorRegPatronal(RegistroPatronal registroPatronal, String uuidCxn);

    /*List<SalariosIntegradosDet>*/
    Mensaje consultaPorRangos(Integer inicio, Integer rango, String uuidCxn);

    /*List<SalariosIntegradosDet>*/
    Mensaje consultaPorFiltros(String query, Object[] campos, Object[] valores, String uuidCxn);

    /*Boolean*/
    Mensaje existeDato(String campo, Object valor, String uuidCxn);
}
