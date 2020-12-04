/**
 * @author: Ernesto Castillo Fecha de Creación: 09/06/2016 Compañía: Exito
 * Software. Descripción del programa: interface de clase de Ptu para llamados a
 * metodos de HIBERNATE
 * -----------------------------------------------------------------------------
 * MODIFICACIONES:
 * -----------------------------------------------------------------------------
 * Clave: Autor: Fecha: Descripción:
 */
package com.mef.erp.modelo;

import com.mef.erp.modelo.entidad.Mensaje;
import com.mef.erp.modelo.entidad.PtuDatosGenerales;
import com.mef.erp.modelo.entidad.PtuEmpleados;
import java.util.List;

/**
 *
 * @author Ernesto
 */
public interface CalculoPtuDAOIF {

    Mensaje guardarCargaAcumulados(PtuDatosGenerales ptuDatosGenerales, List<PtuEmpleados> ptuEmpleados, String uuidCxn);

    Mensaje cargaDeAcumulados(Integer ejercicio, String claveRazonsocial, String uuidCxn);

    Mensaje ptuDatosGeneralesPorEjercioyEmpresa(Integer ejercicio, String claveRazonsocial, String uuidCxn);

    Mensaje ptuEmpleadosPorEjercioyEmpresa(Integer ejercicio, String claveRazonsocial, String uuidCxn);

    Mensaje calculoPtu(PtuDatosGenerales ptuDatosGenerales, List<PtuEmpleados> ptuEmpleados, Double cantidadRepartir, Object[] totales, String uuidCxn);

}
