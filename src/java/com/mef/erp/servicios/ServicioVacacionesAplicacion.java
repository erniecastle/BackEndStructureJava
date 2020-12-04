/**
 * @author: Victor Lopez Fecha de Creación: 17/05/2016 Compañía: Macropro.
 * Descripción del programa: clase SERVICIO VacacionesAplicacion, para llamados
 * a metodos de objeto DAO
 * -----------------------------------------------------------------------------
 * MODIFICACIONES:
 * -----------------------------------------------------------------------------
 * Clave: Autor: Fecha: Descripción:
 * -----------------------------------------------------------------------------
 */
package com.mef.erp.servicios;

import com.mef.erp.modelo.VacacionesAplicacionDAO;
import com.mef.erp.modelo.entidad.Mensaje;
import com.mef.erp.modelo.entidad.VacacionesAplicacion;
import java.util.List;

public class ServicioVacacionesAplicacion implements ServicioVacacionesAplicacionIF {

    private VacacionesAplicacionDAO vacacionesAplicacionDAO;

    public Mensaje agregar(VacacionesAplicacion entity, String uuidCxn) {
        return vacacionesAplicacionDAO.agregar(entity, uuidCxn);
    }

    public Mensaje actualizar(VacacionesAplicacion entity, String uuidCxn) {
        return vacacionesAplicacionDAO.actualizar(entity, uuidCxn);
    }

    public Mensaje eliminar(VacacionesAplicacion entity, String uuidCxn) {
        return vacacionesAplicacionDAO.eliminar(entity, uuidCxn);
    }

    public Mensaje getVacacionesPorEmpleado(String claveEmpleado, String claveRazonSocial, String uuidCxn) {
        return vacacionesAplicacionDAO.getVacacionesPorEmpleado(claveEmpleado, claveRazonSocial, uuidCxn);
    }

    public Mensaje saveDeleteVacacionesAplicacion(List<VacacionesAplicacion> entitysCambios, Object[] clavesDelete, int rango, String uuidCxn) {
        return vacacionesAplicacionDAO.saveDeleteVacacionesAplicacion(entitysCambios, clavesDelete, rango, uuidCxn);
    }

    public VacacionesAplicacionDAO getVacacionesAplicacionDAO() {
        return vacacionesAplicacionDAO;
    }

    public void setVacacionesAplicacionDAO(VacacionesAplicacionDAO vacacionesAplicacionDAO) {
        this.vacacionesAplicacionDAO = vacacionesAplicacionDAO;
    }

    @Override
    public Mensaje getVacacionesAplicacionAll(String claveRazonesSocial, String uuidCxn) {
        return vacacionesAplicacionDAO.getVacacionesAplicacionAll(claveRazonesSocial, uuidCxn);
    }
}
