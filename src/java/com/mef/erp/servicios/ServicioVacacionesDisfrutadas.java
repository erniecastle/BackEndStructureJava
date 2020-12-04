/**
 * @author: Victor Lopez Fecha de Creación: 17/05/2016 Compañía: Macropro.
 * Descripción del programa: clase SERVICIO VacacionesDisfrutadas, para llamados a
 * metodos de objeto DAO
 * -----------------------------------------------------------------------------
 * MODIFICACIONES:
 * -----------------------------------------------------------------------------
 * Clave: Autor: Fecha: Descripción:
 * -----------------------------------------------------------------------------
 */
package com.mef.erp.servicios;

import com.mef.erp.modelo.VacacionesDisfrutadasDAO;
import com.mef.erp.modelo.entidad.Mensaje;
import com.mef.erp.modelo.entidad.VacacionesAplicacion;
import com.mef.erp.modelo.entidad.VacacionesDisfrutadas;
import java.util.List;

public class ServicioVacacionesDisfrutadas implements ServicioVacacionesDisfrutadasIF {

    private VacacionesDisfrutadasDAO vacacionesDisfrutadasDAO;

    public Mensaje agregar(VacacionesDisfrutadas entity, String uuidCxn) {
        return vacacionesDisfrutadasDAO.agregar(entity, uuidCxn);
    }

    public Mensaje actualizar(VacacionesDisfrutadas entity, String uuidCxn) {
        return vacacionesDisfrutadasDAO.actualizar(entity, uuidCxn);
    }

    public Mensaje eliminar(VacacionesDisfrutadas entity, String uuidCxn) {
        return vacacionesDisfrutadasDAO.eliminar(entity, uuidCxn);
    }

    public Mensaje getVacacionesDisfrutadasAll(String claveRazonesSocial, String uuidCxn) {
        return vacacionesDisfrutadasDAO.getVacacionesDisfrutadasAll(claveRazonesSocial, uuidCxn);
    }

    public Mensaje getVacacionesPorEmpleado(String claveEmpleado, String claveRazonSocial, String uuidCxn) {
        return vacacionesDisfrutadasDAO.getVacacionesPorEmpleado(claveEmpleado, claveRazonSocial, uuidCxn);
    }

    public Mensaje saveDeleteVacacionesDisfrutadas(List<VacacionesDisfrutadas> entitysCambios, Object[] clavesDelete, int rango, String uuidCxn) {
        return vacacionesDisfrutadasDAO.saveDeleteVacacionesDisfrutadas(entitysCambios, clavesDelete, rango, uuidCxn);
    }

    public VacacionesDisfrutadasDAO getVacacionesDisfrutadasDAO() {
        return vacacionesDisfrutadasDAO;
    }

    public void setVacacionesDisfrutadasDAO(VacacionesDisfrutadasDAO vacacionesDisfrutadasDAO) {
        this.vacacionesDisfrutadasDAO = vacacionesDisfrutadasDAO;
    }

    @Override
    public Mensaje ObtenerVacacionesDisfruradasMaxima(String claveEmpleado, String claveRazonSocial, String uuidCxn) {
        return vacacionesDisfrutadasDAO.ObtenerVacacionesDisfruradasMaxima(claveEmpleado, claveRazonSocial, uuidCxn);
    }

    @Override
    public Mensaje EliminarVacacionesDisfrutadas(List<VacacionesAplicacion> vacAplicacion, String uuidCxn) {
       return vacacionesDisfrutadasDAO.EliminarVacacionesDisfrutadas(vacAplicacion, uuidCxn);
    }
}
