/**
 * @author: Ernesto Castillo Fecha de Creación: 09/06/2016 Compañía: Exito
 * Software. Descripción del programa: clase para servicio PTU
 * -----------------------------------------------------------------------------
 * MODIFICACIONES:
 * -----------------------------------------------------------------------------
 * Clave: Autor: Fecha: Descripción:
 * -----------------------------------------------------------------------------
 * Clave: Autor: Fecha: Descripción:
 * -----------------------------------------------------------------------------
 */
package com.mef.erp.servicios;

import com.mef.erp.modelo.CalculoPtuDAO;
import com.mef.erp.modelo.entidad.Mensaje;
import com.mef.erp.modelo.entidad.PtuDatosGenerales;
import com.mef.erp.modelo.entidad.PtuEmpleados;
import java.util.List;

/**
 *
 * @author Ernesto
 */
public class ServicioCalculoPtu implements ServicioCalculoPtuIF {

    private CalculoPtuDAO calculoPtuDAO;

    @Override
    public Mensaje guardarCargaAcumulados(PtuDatosGenerales ptuDatosGenerales, List<PtuEmpleados> ptuEmpleados, String uuidCxn) {
        return getCalculoPtuDAO().guardarCargaAcumulados(ptuDatosGenerales, ptuEmpleados, uuidCxn);
    }

    @Override
    public Mensaje cargaDeAcumulados(Integer ejercicio, String claveRazonsocial, String uuidCxn) {
        return getCalculoPtuDAO().cargaDeAcumulados(ejercicio, claveRazonsocial, uuidCxn);
    }

    @Override
    public Mensaje ptuDatosGeneralesPorEjercioyEmpresa(Integer ejercicio, String claveRazonsocial, String uuidCxn) {
        return calculoPtuDAO.ptuDatosGeneralesPorEjercioyEmpresa(ejercicio, claveRazonsocial, uuidCxn);
    }

    @Override
    public Mensaje ptuEmpleadosPorEjercioyEmpresa(Integer ejercicio, String claveRazonsocial, String uuidCxn) {
        return calculoPtuDAO.ptuEmpleadosPorEjercioyEmpresa(ejercicio, claveRazonsocial, uuidCxn);
    }

    @Override
    public Mensaje calculoPtu(PtuDatosGenerales ptuDatosGenerales, List<PtuEmpleados> ptuEmpleados, Double cantidadRepartir, Object[] totales, String uuidCxn) {
        return calculoPtuDAO.calculoPtu(ptuDatosGenerales, ptuEmpleados, cantidadRepartir, totales, uuidCxn);
    }

    public CalculoPtuDAO getCalculoPtuDAO() {
        return calculoPtuDAO;
    }

    public void setCalculoPtuDAO(CalculoPtuDAO calculoPtuDAO) {
        this.calculoPtuDAO = calculoPtuDAO;
    }

}
