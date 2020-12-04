/**
 * @author: Victor Lopez Compañía: Macropro. Descripción del programa: interface
 * IngresosReingresosBajasDAOIF para llamados a metodos de HIBERNATE
 * Fecha:03-07-2012
 * -----------------------------------------------------------------------------
 * MODIFICACIONES:
 * -----------------------------------------------------------------------------
 * Clave: Autor: Fecha: Descripción:
 * -----------------------------------------------------------------------------
 */
package com.mef.erp.modelo;

import com.mef.erp.modelo.entidad.Empleados;
import com.mef.erp.modelo.entidad.IngresosReingresosBajas;
import com.mef.erp.modelo.entidad.Mensaje;
import com.mef.erp.modelo.entidad.RazonesSociales;
import com.mef.erp.modelo.entidad.RegistroPatronal;
import java.util.Date;

public interface IngresosReingresosBajasDAOIF {

    Mensaje agregar(IngresosReingresosBajas entity, String uuidCxn);

    Mensaje actualizar(IngresosReingresosBajas entity, String uuidCxn);

    Mensaje eliminar(IngresosReingresosBajas entity, String uuidCxn);

    Mensaje getIngresosReingresosBajasAll(String uuidCxn);

    Mensaje getIngresosReingresosBajasPorEmpRegPatyRazonSoc(String claveEmpleado, String claveRegPat, String claveRazonSocial, String uuidCxn);

    Mensaje getIngresosReingresosBajasPorRegPatronal(RegistroPatronal registroPatronal, String uuidCxn);

    Mensaje getIngresosReingresosBajasPorRazonSocial(RazonesSociales razonSocial, String uuidCxn);

    Mensaje getPorEmpleadoActivo(String claveEmpleado, String claveRegPatronal, String claveRazonSocial, String uuidCxn);

    Mensaje getPorEmpleadoInactivo(String claveEmpleado, String claveRegPatronal, String claveRazonSocial, Date fechaActual, String uuidCxn);

    Mensaje getPorReferenciaPlazaEmpActivo(String claveReferenciaPlazaEmp, String claveRegPatronal, String claveRazonSocial, String uuidCxn);

    Mensaje getPorClaveEmpleado(String claveEmp, String claveRegPatronal, String claveRazonSocial, String uuidCxn);

    Mensaje getPorReferenciaPlazaEmpInactiva(String claveReferenciaPlazaEmp, String claveRegPatronal, String claveRazonSocial, String uuidCxn);

    Mensaje consultaPorRangos(Integer inicio, Integer rango, String uuidCxn);

    Mensaje consultaPorFiltrosReIngresos(String query, Object[] campos, Object[] valores, String uuidCxn);

    Mensaje existeDato(String campo, Object valor, String uuidCxn);

    Mensaje getIngresosReingresosBajasPorIdEmpleado(Empleados empleados, String uuidCxn);
}
