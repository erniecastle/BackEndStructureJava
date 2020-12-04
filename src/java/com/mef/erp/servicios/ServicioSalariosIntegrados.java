/**
 * @author: Victor Lopez Fecha de Creación: 02/07/2012 Compañía: MacroPro.
 * Descripción del programa: clase SERVICIO ServicioSalariosIntegrados, para
 * llamados a metodos de objeto DAO
 * -----------------------------------------------------------------------------
 * MODIFICACIONES: Clave: Autor: Fecha: Descripción:
 * -----------------------------------------------------------------------------
 */
package com.mef.erp.servicios;

import com.mef.erp.modelo.SalariosIntegradosDAO;
import com.mef.erp.modelo.entidad.Empleados;
import com.mef.erp.modelo.entidad.Mensaje;
import com.mef.erp.modelo.entidad.RazonesSociales;
import com.mef.erp.modelo.entidad.RegistroPatronal;
import com.mef.erp.modelo.entidad.SalariosIntegrados;
import java.util.Date;

public class ServicioSalariosIntegrados implements ServicioSalariosIntegradosIF {

    private SalariosIntegradosDAO salariosIntegradosDAO;

    public Mensaje agregar(SalariosIntegrados entity, String uuidCxn) {
        return salariosIntegradosDAO.agregar(entity, uuidCxn);
    }

    public Mensaje actualizar(SalariosIntegrados entity, String uuidCxn) {
        return salariosIntegradosDAO.actualizar(entity, uuidCxn);
    }

    public Mensaje eliminar(SalariosIntegrados entity, String uuidCxn) {
        return salariosIntegradosDAO.eliminar(entity, uuidCxn);
    }

    public Mensaje getSalariosIntegradosAll(RazonesSociales razonesSociales, String uuidCxn) {
        return salariosIntegradosDAO.getSalariosIntegradosAll(razonesSociales, uuidCxn);
    }

    public Mensaje getSalariosIntegradosPorEmpleadoyRegPat(Empleados empleados, RegistroPatronal registroPatronal, Date fecha, String uuidCxn) {
        return salariosIntegradosDAO.getSalariosIntegradosPorEmpleadoyRegPat(empleados, registroPatronal, fecha, uuidCxn);
    }

    public Mensaje getSDIActualPorEmpleadoyRegPatActual(String claveEmpleados, String claveRegistroPatronal, Date fecha, RazonesSociales razonesSociales, String uuidCxn) {
        return salariosIntegradosDAO.getSDIActualPorEmpleadoyRegPatActual(claveEmpleados, claveRegistroPatronal, fecha, razonesSociales, uuidCxn);
    }

    public Mensaje getSalariosIntegradosPorRegPatronal(RegistroPatronal registroPatronal, String uuidCxn) {
        return salariosIntegradosDAO.getSalariosIntegradosPorRegPatronal(registroPatronal, uuidCxn);
    }

    public Mensaje consultaPorRangos(Integer inicio, Integer rango, String uuidCxn) {
        return salariosIntegradosDAO.consultaPorRangos(inicio, rango, uuidCxn);
    }

    public Mensaje consultaPorFiltros(String query, Object[] campos, Object[] valores, String uuidCxn) {
        return salariosIntegradosDAO.consultaPorFiltrosSalario(query, campos, valores, uuidCxn);
    }

    public Mensaje existeDato(String campo, Object valor, String uuidCxn) {
        return salariosIntegradosDAO.existeDato(campo, valor, uuidCxn);
    }

    public SalariosIntegradosDAO getSalariosIntegradosDAO() {
        return salariosIntegradosDAO;
    }

    public void setSalariosIntegradosDAO(SalariosIntegradosDAO salariosIntegradosDAO) {
        this.salariosIntegradosDAO = salariosIntegradosDAO;
    }
}
