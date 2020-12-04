/**
 * @author: Victor Lopez Fecha de Creación: 04/07/2012 Compañía: MacroPro.
 * Descripción del programa: clase SERVICIO ServicioFiniqLiquidPlazas, para
 * llamados a metodos de objeto DAO
 * -----------------------------------------------------------------------------
 * MODIFICACIONES: Clave: Autor: Fecha: Descripción:
 * -----------------------------------------------------------------------------
 */
package com.mef.erp.servicios;

import com.mef.erp.modelo.FiniqLiquidPlazasDAO;
import com.mef.erp.modelo.entidad.FiniqLiquidPlazas;
import com.mef.erp.modelo.entidad.FiniquitosLiquidaciones;
import com.mef.erp.modelo.entidad.Mensaje;
import java.util.List;

public class ServicioFiniqLiquidPlazas implements ServicioFiniqLiquidPlazasIF {

    private FiniqLiquidPlazasDAO finiqLiquidPlazasDAO;
 
    public Mensaje agregar(FiniqLiquidPlazas entity, String uuidCxn) {
        return finiqLiquidPlazasDAO.agregar(entity, uuidCxn);
    }

    public Mensaje actualizar(FiniqLiquidPlazas entity, String uuidCxn) {
        return finiqLiquidPlazasDAO.actualizar(entity, uuidCxn);
    }

    public Mensaje eliminar(FiniqLiquidPlazas entity, String uuidCxn) {
        return finiqLiquidPlazasDAO.eliminar(entity, uuidCxn);
    }

    public Mensaje getFiniqLiquidPlazasAll(String uuidCxn) {
        return finiqLiquidPlazasDAO.getFiniqLiquidPlazasAll(uuidCxn);
    }

//    public FiniqLiquidPlazas getFiniqLiquidPlazasPorEmpERegPatyRazonSoc(Empleados empleados, RegistroPatronal registroPatronal, RazonesSociales razonSocial) {
//        return finiqLiquidPlazasDAO.getFiniqLiquidPlazasPorEmpERegPatyRazonSoc(empleados, registroPatronal, razonSocial);
//    }
//
//    public List<FiniqLiquidPlazas> getFiniqLiquidPlazasPorRegPatronal(RegistroPatronal registroPatronal) {
//        return finiqLiquidPlazasDAO.getFiniqLiquidPlazasPorRegPatronal(registroPatronal);
//    }
//
//    public List<FiniqLiquidPlazas> getFiniqLiquidPlazasPorRazonSocial(RazonesSociales razonSocial) {
//        return finiqLiquidPlazasDAO.getFiniqLiquidPlazasPorRazonSocial(razonSocial);
//    }
    public Mensaje consultaPorRangos(Integer inicio, Integer rango, String uuidCxn) {
        return finiqLiquidPlazasDAO.consultaPorRangos(inicio, rango, uuidCxn);
    }

    public Mensaje consultaPorFiltros(String query, Object[] campos, Object[] valores, String uuidCxn) {
        return finiqLiquidPlazasDAO.consultaPorFiltrosFiniquitos(query, campos, valores, uuidCxn);
    }

    public Mensaje existeDato(String campo, Object valor, String uuidCxn) {
        return finiqLiquidPlazasDAO.existeDato(campo, valor, uuidCxn);
    }

    public Mensaje saveDeleteFiniqLiquidPlazas(List<FiniqLiquidPlazas> entitysCambios, Object[] clavesDelete, String uuidCxn) {
        return finiqLiquidPlazasDAO.saveDeleteFiniqLiquidPlazas(entitysCambios, clavesDelete, uuidCxn);
    }

    public Mensaje getFiniqLiquidPlazasPorFiniquitosLiquidaciones(FiniquitosLiquidaciones finiquitosLiquidacion, String uuidCxn) {
        return finiqLiquidPlazasDAO.getFiniqLiquidPlazasPorFiniquitosLiquidaciones(finiquitosLiquidacion, uuidCxn);
    }

    public Mensaje getCantidadPlazasFiniquitadaPorEmpleado(String claveEmpleado, String razonSocial, String uuidCxn) {
        return finiqLiquidPlazasDAO.getCantidadPlazasFiniquitadaPorEmpleado(claveEmpleado, razonSocial, uuidCxn);
    }

    public Mensaje getPlazasFiniquitadaPorEmpleado(String claveEmpleado, String razonSocial, String uuidCxn) {
        return finiqLiquidPlazasDAO.getPlazasFiniquitadaPorEmpleado(claveEmpleado, razonSocial, uuidCxn);
    }

    public FiniqLiquidPlazasDAO getFiniqLiquidPlazasDAO() {
        return finiqLiquidPlazasDAO;
    }

    public void setFiniqLiquidPlazasDAO(FiniqLiquidPlazasDAO finiqLiquidPlazasDAO) {
        this.finiqLiquidPlazasDAO = finiqLiquidPlazasDAO;
    }
}
