/**
 * @author: Ernesto Valenzuela Fecha de Creación: 16/05/2016 Compañía: Exito.
 * Descripción del programa: clase Servicio VacacionesDevengadas, para llamados
 * a metodos de objeto DAO
 * -----------------------------------------------------------------------------
 * MODIFICACIONES:
 * -----------------------------------------------------------------------------
 */
package com.mef.erp.servicios;

import com.mef.erp.modelo.VacacionesDevengadasDAO;
import com.mef.erp.modelo.entidad.Mensaje;
import com.mef.erp.modelo.entidad.RazonesSociales;
import com.mef.erp.modelo.entidad.VacacionesDevengadas;

import java.util.List;

/**
 *
 * @author Ernesto
 */
public class ServicioVacacionesDevengadas implements ServicioVacacionesDevengadasIF {

    private VacacionesDevengadasDAO vacacionesDevengadasDAO;

    @Override
    public Mensaje getVacacionesDevengadasAll(String uuidCxn) {
        return vacacionesDevengadasDAO.getVacacionesDevengadasAll(uuidCxn);
    }

    @Override
    public Mensaje calcularVacacionesDevengadasEmpleados(RazonesSociales razonesSociales, String uuidCxn, String uuidCxnMaestra) {
        return vacacionesDevengadasDAO.calcularVacacionesDevengadasEmpleados(razonesSociales, uuidCxn, uuidCxnMaestra);
    }

    public VacacionesDevengadasDAO getVacacionesDevengadasDAO() {
        return vacacionesDevengadasDAO;
    }

    public void setVacacionesDevengadasDAO(VacacionesDevengadasDAO vacacionesDevengadasDAO) {
        this.vacacionesDevengadasDAO = vacacionesDevengadasDAO;
    }

    @Override
    public Mensaje getVacacionesDevengadasPorEmpleado(String claveEmpleado, String claveRazonSocial, String uuidCxn) {
        return vacacionesDevengadasDAO.getVacacionesDenvengadasPorEmpleado(claveEmpleado, claveRazonSocial, uuidCxn);
    }

    public Mensaje saveDeleteVacacionesDevengadas(List<VacacionesDevengadas> entitysCambios,  int rango, String uuidCxn) {
        return vacacionesDevengadasDAO.saveDeleteVacacionesDevengadas(entitysCambios,  rango, uuidCxn);
    }
}
