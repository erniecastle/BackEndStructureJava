/**
 * @author: Victor Lopez Fecha de Creación: 02/07/2012 Compañía: MacroPro.
 * Descripción del programa: interface servicio ServicioSalariosIntegradosIF
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
import com.mef.erp.modelo.entidad.SalariosIntegrados;
import java.util.Date;

public interface ServicioSalariosIntegradosIF {

    /*SalariosIntegrados*/
    Mensaje agregar(SalariosIntegrados entity, String uuidCxn);

    /*boolean*/
    Mensaje actualizar(SalariosIntegrados entity, String uuidCxn);

    /*boolean*/
    Mensaje eliminar(SalariosIntegrados entity, String uuidCxn);

    /*List<SalariosIntegrados>*/
    Mensaje getSalariosIntegradosAll(RazonesSociales razonesSociales, String uuidCxn);

    /*SalariosIntegrados*/
    Mensaje getSalariosIntegradosPorEmpleadoyRegPat(Empleados empleados, RegistroPatronal registroPatronal, Date fecha, String uuidCxn);

    /*SalariosIntegrados*/
    public Mensaje getSDIActualPorEmpleadoyRegPatActual(String claveEmpleados, String claveRegistroPatronal, Date fecha, RazonesSociales razonesSociales, String uuidCxn);

    /*List<SalariosIntegrados>*/
    Mensaje getSalariosIntegradosPorRegPatronal(RegistroPatronal registroPatronal, String uuidCxn);

    /*List<SalariosIntegrados>*/
    Mensaje consultaPorRangos(Integer inicio, Integer rango, String uuidCxn);

    /*List<SalariosIntegrados>*/
    Mensaje consultaPorFiltros(String query, Object[] campos, Object[] valores, String uuidCxn);

    /*Boolean*/
    Mensaje existeDato(String campo, Object valor, String uuidCxn);
}
