/**
 * @author: Victor Lopez Compañía: Macropro. Descripción del programa: interface
 * SalariosIntegradosDAOIF para llamados a metodos de HIBERNATE Fecha:02-07-2012
 * -----------------------------------------------------------------------------
 * MODIFICACIONES:
 * -----------------------------------------------------------------------------
 * Clave: Autor: Fecha: Descripción:
 * -----------------------------------------------------------------------------
 */
package com.mef.erp.modelo;

import com.mef.erp.modelo.entidad.Empleados;
import com.mef.erp.modelo.entidad.Mensaje;
import com.mef.erp.modelo.entidad.RazonesSociales;
import com.mef.erp.modelo.entidad.RegistroPatronal;
import com.mef.erp.modelo.entidad.SalariosIntegrados;
import java.util.Date;

public interface SalariosIntegradosDAOIF {

    Mensaje agregar(SalariosIntegrados entity, String uuidCxn);

    Mensaje actualizar(SalariosIntegrados entity, String uuidCxn);

    Mensaje eliminar(SalariosIntegrados entity, String uuidCxn);

    Mensaje getSalariosIntegradosAll(RazonesSociales razonesSociales, String uuidCxn);

    Mensaje getSalariosIntegradosPorEmpleadoyRegPat(Empleados empleados, RegistroPatronal registroPatronal, Date fecha, String uuidCxn);

    public Mensaje getSDIActualPorEmpleadoyRegPatActual(String claveEmpleados, String claveRegistroPatronal, Date fecha,RazonesSociales razonesSociales, String uuidCxn);

    Mensaje getSalariosIntegradosPorRegPatronal(RegistroPatronal registroPatronal, String uuidCxn);

    Mensaje consultaPorRangos(Integer inicio, Integer rango, String uuidCxn);

    Mensaje consultaPorFiltrosSalario(String query, Object[] campos, Object[] valores, String uuidCxn);

    Mensaje existeDato(String campo, Object valor, String uuidCxn);
}
