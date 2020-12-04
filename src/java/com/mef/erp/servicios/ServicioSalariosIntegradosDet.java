/**
 * @author: Victor Lopez Fecha de Creación: 02/07/2012 Compañía: MacroPro.
 * Descripción del programa: clase SERVICIO ServicioSalariosIntegrados, para
 * llamados a metodos de objeto DAO
 * -----------------------------------------------------------------------------
 * MODIFICACIONES: Clave: Autor: Fecha: Descripción:
 * -----------------------------------------------------------------------------
 */
package com.mef.erp.servicios;

import com.mef.erp.modelo.SalariosIntegradosDetDAO;
import com.mef.erp.modelo.entidad.Empleados;
import com.mef.erp.modelo.entidad.Mensaje;
import com.mef.erp.modelo.entidad.RazonesSociales;
import com.mef.erp.modelo.entidad.RegistroPatronal;
import com.mef.erp.modelo.entidad.SalariosIntegradosDet;
import java.util.Date;

public class ServicioSalariosIntegradosDet implements ServicioSalariosIntegradosDetIF {

    private SalariosIntegradosDetDAO salariosIntegradosDetDAO;

    public Mensaje agregar(SalariosIntegradosDet entity, String uuidCxn) {
        return salariosIntegradosDetDAO.agregar(entity, uuidCxn);
    }

    public Mensaje actualizar(SalariosIntegradosDet entity, String uuidCxn) {
        return salariosIntegradosDetDAO.actualizar(entity, uuidCxn);
    }

    public Mensaje eliminar(SalariosIntegradosDet entity, String uuidCxn) {
        return salariosIntegradosDetDAO.eliminar(entity, uuidCxn);
    }

    public Mensaje getSalariosIntegradosDetAll(RazonesSociales razonesSociales, String uuidCxn) {
        return salariosIntegradosDetDAO.getSalariosIntegradosDetAll(razonesSociales, uuidCxn);
    }

    public Mensaje getSalariosIntegradosDetPorEmpleadoyRegPat(Empleados empleados, RegistroPatronal registroPatronal, Date fecha, String uuidCxn) {
        return salariosIntegradosDetDAO.getSalariosIntegradosDetPorEmpleadoyRegPat(empleados, registroPatronal, fecha, uuidCxn);
    }

    public Mensaje getSalariosIntegradosDetPorRegPatronal(RegistroPatronal registroPatronal, String uuidCxn) {
        return salariosIntegradosDetDAO.getSalariosIntegradosDetPorRegPatronal(registroPatronal, uuidCxn);
    }

    public Mensaje consultaPorRangos(Integer inicio, Integer rango, String uuidCxn) {
        return salariosIntegradosDetDAO.consultaPorRangos(inicio, rango, uuidCxn);
    }

    public Mensaje consultaPorFiltros(String query, Object[] campos, Object[] valores, String uuidCxn) {
        return salariosIntegradosDetDAO.consultaPorFiltrosSalario(query, campos, valores, uuidCxn);
    }

    public Mensaje existeDato(String campo, Object valor, String uuidCxn) {
        return salariosIntegradosDetDAO.existeDato(campo, valor, uuidCxn);
    }

    public SalariosIntegradosDetDAO getSalariosIntegradosDetDAO() {
        return salariosIntegradosDetDAO;
    }

    public void setSalariosIntegradosDetDAO(SalariosIntegradosDetDAO salariosIntegradosDetDAO) {
        this.salariosIntegradosDetDAO = salariosIntegradosDetDAO;
    }
}
