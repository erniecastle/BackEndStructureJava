/**
 * @author: Victor Lopez Fecha de Creación: 04/07/2012 Compañía: MacroPro.
 * Descripción del programa: clase SERVICIO ServicioFiniqLiquidCncNom, para
 * llamados a metodos de objeto DAO
 * -----------------------------------------------------------------------------
 * MODIFICACIONES: Clave: Autor: Fecha: Descripción:
 * -----------------------------------------------------------------------------
 */
package com.mef.erp.servicios;

import com.mef.erp.modelo.FiniqLiquidCncNomDAO;
import com.mef.erp.modelo.entidad.FiniqLiquidCncNom;
import com.mef.erp.modelo.entidad.FiniquitosLiquidaciones;
import com.mef.erp.modelo.entidad.Mensaje;
import java.util.List;

public class ServicioFiniqLiquidCncNom implements ServicioFiniqLiquidCncNomIF {

    private FiniqLiquidCncNomDAO finiqLiquidCncNomDAO;
 

    public Mensaje agregar(FiniqLiquidCncNom entity, String uuidCxn) {
        return finiqLiquidCncNomDAO.agregar(entity, uuidCxn);
    }

    public Mensaje actualizar(FiniqLiquidCncNom entity, String uuidCxn) {
        return finiqLiquidCncNomDAO.actualizar(entity, uuidCxn);
    }

    public Mensaje eliminar(FiniqLiquidCncNom entity, String uuidCxn) {
        return finiqLiquidCncNomDAO.eliminar(entity, uuidCxn);
    }

    public Mensaje getFiniqLiquidCncNomAll(String uuidCxn) {
        return finiqLiquidCncNomDAO.getFiniqLiquidCncNomAll(uuidCxn);
    }

    public Mensaje getFiniqLiquidCncNomPorFiniquitosLiquidaciones(FiniquitosLiquidaciones finiquitosLiquidacion, String uuidCxn) {
        return finiqLiquidCncNomDAO.getFiniqLiquidCncNomPorFiniquitosLiquidaciones(finiquitosLiquidacion, uuidCxn);
    }

//    public FiniqLiquidCncNom getFiniqLiquidCncNomPorEmpERegPatyRazonSoc(Empleados empleados, RegistroPatronal registroPatronal, RazonesSociales razonSocial) {
//        return finiqLiquidCncNomDAO.getFiniqLiquidCncNomPorEmpERegPatyRazonSoc(empleados, registroPatronal, razonSocial);
//    }
//
//    public List<FiniqLiquidCncNom> getFiniqLiquidCncNomPorRegPatronal(RegistroPatronal registroPatronal) {
//        return finiqLiquidCncNomDAO.getFiniqLiquidCncNomPorRegPatronal(registroPatronal);
//    }
//
//    public List<FiniqLiquidCncNom> getFiniqLiquidCncNomPorRazonSocial(RazonesSociales razonSocial) {
//        return finiqLiquidCncNomDAO.getFiniqLiquidCncNomPorRazonSocial(razonSocial);
//    }
    public Mensaje consultaPorRangos(Integer inicio, Integer rango, String uuidCxn) {
        return finiqLiquidCncNomDAO.consultaPorRangos(inicio, rango, uuidCxn);
    }

    public Mensaje consultaPorFiltros(String query, Object[] campos, Object[] valores, String uuidCxn) {
        return finiqLiquidCncNomDAO.consultaPorFiltrosFiniquitos(query, campos, valores, uuidCxn);
    }

    public Mensaje existeDato(String campo, Object valor, String uuidCxn) {
        return finiqLiquidCncNomDAO.existeDato(campo, valor, uuidCxn);
    }

    public Mensaje saveDeleteFiniqLiquidCncNom(List<FiniqLiquidCncNom> entitysCambios, Object[] clavesDelete, String uuidCxn) {
        return finiqLiquidCncNomDAO.saveDeleteFiniqLiquidCncNom(entitysCambios, clavesDelete, uuidCxn);
    }

    public FiniqLiquidCncNomDAO getFiniqLiquidCncNomDAO() {
        return finiqLiquidCncNomDAO;
    }

    public void setFiniqLiquidCncNomDAO(FiniqLiquidCncNomDAO finiqLiquidCncNomDAO) {
        this.finiqLiquidCncNomDAO = finiqLiquidCncNomDAO;
    }
}
