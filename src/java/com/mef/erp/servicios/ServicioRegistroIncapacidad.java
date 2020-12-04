/**
 * @author: Victor Lopez Fecha de Creación: 16/12/2011 Compañía: Macropro.
 * Descripción del programa: clase SERVICIO RegistroIncapacidad, para llamados a
 * metodos de objeto DAO
 * -----------------------------------------------------------------------------
 * MODIFICACIONES:
 * -----------------------------------------------------------------------------
 * Clave: Autor: Fecha: Descripción:
 * -----------------------------------------------------------------------------
 */
package com.mef.erp.servicios;

import com.mef.erp.modelo.RegistroIncapacidadDAO;
import com.mef.erp.modelo.entidad.Empleados;
import com.mef.erp.modelo.entidad.Mensaje;
import com.mef.erp.modelo.entidad.RazonesSociales;
import com.mef.erp.modelo.entidad.RegistroIncapacidad;
import java.util.Date;

public class ServicioRegistroIncapacidad implements ServicioRegistroIncapacidadIF {

    private RegistroIncapacidadDAO registroIncapacidadDAO;

    public Mensaje agregar(RegistroIncapacidad entity, String uuidCxn) {
        return registroIncapacidadDAO.agregar(entity, uuidCxn);
    }

    public Mensaje actualizar(RegistroIncapacidad entity, String uuidCxn) {
        return registroIncapacidadDAO.actualizar(entity, uuidCxn);
    }

    public Mensaje eliminar(RegistroIncapacidad entity, String uuidCxn) {
        return registroIncapacidadDAO.eliminar(entity, uuidCxn);
    }

    public Mensaje getRegistroIncapacidadAll(String claveRazonesSocial, String uuidCxn) {
        return registroIncapacidadDAO.getRegistroIncapacidadAll(claveRazonesSocial, uuidCxn);
    }

    public Mensaje getRegistroIncapacidadPorClave(String clave, String claveRazonesSocial, String uuidCxn) {
        return registroIncapacidadDAO.getRegistroIncapacidadPorClave(clave, claveRazonesSocial, uuidCxn);
    }

    public Mensaje getRegistroIncapacidadPorClaveYRazon(String clave, String claveRazon, String uuidCxn) {
        return registroIncapacidadDAO.getRegistroIncapacidadPorClaveYRazon(clave, claveRazon, uuidCxn);
    }

    public Mensaje getRegistroIncapacidadPorEmpleado(Empleados empleado, String uuidCxn) {
        return registroIncapacidadDAO.getRegistroIncapacidadPorEmpleado(empleado, uuidCxn);
    }

    public Mensaje getIncapacidadPorEmpleadoYFecha(String claveEmpleado, Date fechaInicial, Date fechaFinal, String uuidCxn) {
        return registroIncapacidadDAO.getIncapacidadPorEmpleadoYFecha(claveEmpleado, fechaInicial, fechaFinal, uuidCxn);
    }

    public Mensaje consultaPorRangos(Integer inicio, Integer rango, String[] camposWhere, Object[] camposWhereValores, String uuidCxn) {
        return registroIncapacidadDAO.consultaPorRangosIncapacidad(inicio, rango, camposWhere, camposWhereValores, uuidCxn);
    }

    public Mensaje consultaPorFiltros(String query, Object[] campos, Object[] valores, String uuidCxn) {
        return registroIncapacidadDAO.consultaPorFiltrosIncapacidad(query, campos, valores, uuidCxn);
    }

    public Mensaje saveDeleteRegistroIncapacidad(RegistroIncapacidad incapacidad, Object[] clavesDeleteIncapacidad, int rango, Empleados empleados, RazonesSociales razonesSociales, Date fechaInicial, Date fechaFinal, Date fechaInicialAnterior, Date fechaFinalAnterior, Object claveExcepcion, String formatoFecha, Date fechaInicEmpalme, Date fechaFinEmpalme, String uuidCxn, String uuidCxnMaestra) {
        return registroIncapacidadDAO.saveDeleteRegistroIncapacidad(incapacidad, clavesDeleteIncapacidad, rango, empleados, razonesSociales, fechaInicial, fechaFinal, fechaInicialAnterior, fechaFinalAnterior, claveExcepcion, formatoFecha, fechaInicEmpalme, fechaFinEmpalme, uuidCxn, uuidCxnMaestra);
    }

    public Mensaje existeDato(String campo, Object valor, String uuidCxn) {
        return registroIncapacidadDAO.existeDato(campo, valor, uuidCxn);
    }

    public RegistroIncapacidadDAO getRegistroIncapacidadDAO() {
        return registroIncapacidadDAO;
    }

    public void setRegistroIncapacidadDAO(RegistroIncapacidadDAO registroIncapacidadDAO) {
        this.registroIncapacidadDAO = registroIncapacidadDAO;
    }
}
