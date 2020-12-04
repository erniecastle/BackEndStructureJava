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
import com.mef.erp.modelo.entidad.SalariosIntegradosDet;
import java.util.Date;

public interface SalariosIntegradosDetDAOIF {

    Mensaje agregar(SalariosIntegradosDet entity, String uuidCxn);

    Mensaje actualizar(SalariosIntegradosDet entity, String uuidCxn);

    Mensaje eliminar(SalariosIntegradosDet entity, String uuidCxn);

    Mensaje getSalariosIntegradosDetAll(RazonesSociales razonesSociales, String uuidCxn);

    Mensaje getSalariosIntegradosDetPorEmpleadoyRegPat(Empleados empleados, RegistroPatronal registroPatronal, Date fecha, String uuidCxn);
    
    Mensaje getSalariosIntegradosDetPorRegPatronal(RegistroPatronal registroPatronal, String uuidCxn);
    
    Mensaje consultaPorRangos(Integer inicio, Integer rango, String uuidCxn);

    Mensaje consultaPorFiltrosSalario(String query, Object[] campos, Object[] valores, String uuidCxn);

    Mensaje existeDato(String campo, Object valor, String uuidCxn);
}
