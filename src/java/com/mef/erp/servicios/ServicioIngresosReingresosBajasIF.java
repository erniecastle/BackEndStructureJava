/**
 * @author: Victor Lopez Fecha de Creación: 04/07/2012 Compañía: MacroPro.
 * Descripción del programa: interface servicio
 * ServicioIngresosReingresosBajasIF
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
import com.mef.erp.modelo.entidad.IngresosReingresosBajas;
import com.mef.erp.modelo.entidad.Mensaje;
import com.mef.erp.modelo.entidad.RazonesSociales;
import com.mef.erp.modelo.entidad.RegistroPatronal;
import java.util.Date;

public interface ServicioIngresosReingresosBajasIF {

    /*IngresosReingresosBajas*/
    Mensaje agregar(IngresosReingresosBajas entity, String uuidCxn);

    /*boolean*/
    Mensaje actualizar(IngresosReingresosBajas entity, String uuidCxn);

    /*boolean*/
    Mensaje eliminar(IngresosReingresosBajas entity, String uuidCxn);

    /*List<IngresosReingresosBajas>*/
    Mensaje getIngresosReingresosBajasAll(String uuidCxn);

    /*IngresosReingresosBajas*/
    Mensaje getIngresosReingresosBajasPorEmpRegPatyRazonSoc(String claveEmpleado, String claveRegPat, String claveRazonSocial, String uuidCxn);

    /*List<IngresosReingresosBajas>*/
    Mensaje getIngresosReingresosBajasPorRegPatronal(RegistroPatronal registroPatronal, String uuidCxn);

    /*List<IngresosReingresosBajas>*/
    Mensaje getIngresosReingresosBajasPorRazonSocial(RazonesSociales razonSocial, String uuidCxn);

    /*IngresosReingresosBajas*/
    Mensaje getPorEmpleadoActivo(String claveEmpleado, String claveRegPatronal, String claveRazonSocial, String uuidCxn);

    /*IngresosReingresosBajas*/
    Mensaje getPorEmpleadoInactivo(String claveEmpleado, String claveRegPatronal, String claveRazonSocial, Date fechaActual, String uuidCxn);

    /*IngresosReingresosBajas*/
    Mensaje getPorReferenciaPlazaEmpInactiva(String claveReferenciaPlazaEmp, String claveRegPatronal, String claveRazonSocial, String uuidCxn);

    /*IngresosReingresosBajas*/
    Mensaje getPorReferenciaPlazaEmpActivo(String claveReferenciaPlazaEmp, String claveRegPatronal, String claveRazonSocial, String uuidCxn);

    /*IngresosReingresosBajas*/
    Mensaje getPorClaveEmpleado(String claveEmp, String claveRegPatronal, String claveRazonSocial, String uuidCxn);

    /*List<IngresosReingresosBajas>*/
    Mensaje consultaPorRangos(Integer inicio, Integer rango, String uuidCxn);

    /*List<IngresosReingresosBajas>*/
    Mensaje consultaPorFiltros(String query, Object[] campos, Object[] valores, String uuidCxn);

    /*Boolean*/
    Mensaje existeDato(String campo, Object valor, String uuidCxn);

    Mensaje getIngresosReingresosBajasPorIdEmpleado(Empleados empleados, String uuidCxn);
}
