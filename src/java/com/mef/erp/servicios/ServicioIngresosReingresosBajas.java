/**
 * @author: Victor Lopez Fecha de Creación: 04/07/2012 Compañía: MacroPro.
 * Descripción del programa: clase SERVICIO ServicioIngresosReingresosBajas,
 * para llamados a metodos de objeto DAO
 * -----------------------------------------------------------------------------
 * MODIFICACIONES: Clave: Autor: Fecha: Descripción:
 * -----------------------------------------------------------------------------
 */
package com.mef.erp.servicios;

import com.mef.erp.modelo.IngresosReingresosBajasDAO;
import com.mef.erp.modelo.entidad.Empleados;
import com.mef.erp.modelo.entidad.RazonesSociales;
import com.mef.erp.modelo.entidad.RegistroPatronal;
import com.mef.erp.modelo.entidad.IngresosReingresosBajas;
import com.mef.erp.modelo.entidad.Mensaje;
import java.util.Date;

public class ServicioIngresosReingresosBajas implements ServicioIngresosReingresosBajasIF {

    private IngresosReingresosBajasDAO ingresosReingresosBajasDAO;

    public Mensaje agregar(IngresosReingresosBajas entity, String uuidCxn) {

        return ingresosReingresosBajasDAO.agregar(entity,uuidCxn);
    }

    public Mensaje actualizar(IngresosReingresosBajas entity, String uuidCxn) {

        return ingresosReingresosBajasDAO.actualizar(entity,uuidCxn);
    }

    public Mensaje eliminar(IngresosReingresosBajas entity, String uuidCxn) {

        return ingresosReingresosBajasDAO.eliminar(entity,uuidCxn);
    }

    public Mensaje getIngresosReingresosBajasAll(String uuidCxn) {

        return ingresosReingresosBajasDAO.getIngresosReingresosBajasAll(uuidCxn);
    }

    public Mensaje getIngresosReingresosBajasPorEmpRegPatyRazonSoc(String claveEmpleado, String claveRegPat, String claveRazonSocial, String uuidCxn) {

        return ingresosReingresosBajasDAO.getIngresosReingresosBajasPorEmpRegPatyRazonSoc(claveEmpleado, claveRegPat, claveRazonSocial,uuidCxn);
    }

    public Mensaje getIngresosReingresosBajasPorRegPatronal(RegistroPatronal registroPatronal, String uuidCxn) {

        return ingresosReingresosBajasDAO.getIngresosReingresosBajasPorRegPatronal(registroPatronal,uuidCxn);
    }

    public Mensaje getIngresosReingresosBajasPorRazonSocial(RazonesSociales razonSocial, String uuidCxn) {

        return ingresosReingresosBajasDAO.getIngresosReingresosBajasPorRazonSocial(razonSocial,uuidCxn);
    }

    public Mensaje getPorEmpleadoActivo(String claveEmpleado, String claveRegPatronal, String claveRazonSocial, String uuidCxn) {

        return ingresosReingresosBajasDAO.getPorEmpleadoActivo(claveEmpleado, claveRegPatronal, claveRazonSocial,uuidCxn);
    }

    public Mensaje getPorEmpleadoInactivo(String claveEmpleado, String claveRegPatronal, String claveRazonSocial, Date fechaActual, String uuidCxn) {

        return ingresosReingresosBajasDAO.getPorEmpleadoInactivo(claveEmpleado, claveRegPatronal, claveRazonSocial, fechaActual,uuidCxn);
    }

    public Mensaje getPorReferenciaPlazaEmpActivo(String claveReferenciaPlazaEmp, String claveRegPatronal, String claveRazonSocial, String uuidCxn) {

        return ingresosReingresosBajasDAO.getPorReferenciaPlazaEmpActivo(claveReferenciaPlazaEmp, claveRegPatronal, claveRazonSocial,uuidCxn);
    }

    public Mensaje getPorClaveEmpleado(String claveEmp, String claveRegPatronal, String claveRazonSocial, String uuidCxn) {

        return ingresosReingresosBajasDAO.getPorClaveEmpleado(claveEmp, claveRegPatronal, claveRazonSocial,uuidCxn);
    }

    public Mensaje getPorReferenciaPlazaEmpInactiva(String claveReferenciaPlazaEmp, String claveRegPatronal, String claveRazonSocial, String uuidCxn) {

        return ingresosReingresosBajasDAO.getPorReferenciaPlazaEmpInactiva(claveReferenciaPlazaEmp, claveRegPatronal, claveRazonSocial,uuidCxn);
    }

    public Mensaje consultaPorRangos(Integer inicio, Integer rango, String uuidCxn) {

        return ingresosReingresosBajasDAO.consultaPorRangos(inicio, rango,uuidCxn);
    }

    public Mensaje consultaPorFiltros(String query, Object[] campos, Object[] valores, String uuidCxn) {

        return ingresosReingresosBajasDAO.consultaPorFiltrosReIngresos(query, campos, valores,uuidCxn);
    }

    public Mensaje existeDato(String campo, Object valor, String uuidCxn) {

        return ingresosReingresosBajasDAO.existeDato(campo, valor,uuidCxn);
    }

    public Mensaje getIngresosReingresosBajasPorIdEmpleado(Empleados empleados, String uuidCxn) {

        return ingresosReingresosBajasDAO.getIngresosReingresosBajasPorIdEmpleado(empleados,uuidCxn);
    }

    public IngresosReingresosBajasDAO getIngresosReingresosBajasDAO() {
        return ingresosReingresosBajasDAO;
    }

    public void setIngresosReingresosBajasDAO(IngresosReingresosBajasDAO ingresosReingresosBajasDAO) {
        this.ingresosReingresosBajasDAO = ingresosReingresosBajasDAO;
    }
}
