/**
 * @author: Victor Lopez Fecha de Creación: 16/12/2011 Compañía: Macropro.
 * Descripción del programa: interfaz RegistroIncapacidadDAOIF para llamados a
 * metodos de HIBERNATE
 * -----------------------------------------------------------------------------
 * MODIFICACIONES:
 * -----------------------------------------------------------------------------
 * Clave: Autor: Fecha: Descripción:
 * -----------------------------------------------------------------------------
 */
package com.mef.erp.modelo;

import com.mef.erp.modelo.entidad.Empleados;
import com.mef.erp.modelo.entidad.Mensaje;
import com.mef.erp.modelo.entidad.RazonesSociales;
import com.mef.erp.modelo.entidad.RegistroIncapacidad;
import java.util.Date;

public interface RegistroIncapacidadDAOIF {

    Mensaje agregar(RegistroIncapacidad entity, String uuidCxn);

    Mensaje actualizar(RegistroIncapacidad entity, String uuidCxn);

    Mensaje eliminar(RegistroIncapacidad entity, String uuidCxn);

    Mensaje getRegistroIncapacidadAll(String claveRazonesSocial, String uuidCxn);

    Mensaje getRegistroIncapacidadPorClave(String clave, String claveRazonesSocial, String uuidCxn);

    Mensaje getRegistroIncapacidadPorClaveYRazon(String clave, String claveRazon, String uuidCxn);

    Mensaje getRegistroIncapacidadPorEmpleado(Empleados empleado, String uuidCxn);

    Mensaje getIncapacidadPorEmpleadoYFecha(String claveEmpleado, Date fechaInicial, Date fechaFinal, String uuidCxn);

    Mensaje consultaPorRangosIncapacidad(Integer inicio, Integer rango, String[] camposWhere, Object[] camposWhereValores, String uuidCxn);

    Mensaje consultaPorFiltrosIncapacidad(String query, Object[] campos, Object[] valores, String uuidCxn);

    Mensaje existeDato(String campo, Object valor, String uuidCxn);

    Mensaje saveDeleteRegistroIncapacidad(RegistroIncapacidad incapacidad, Object[] clavesDeleteIncapacidad, int rango, Empleados empleados, RazonesSociales razonesSociales, Date fechaInicial, Date fechaFinal, Date fechaInicialAnterior, Date fechaFinalAnterior, Object claveExcepcion, String formatoFecha, Date fechaInicEmpalme, Date fechaFinEmpalme, String uuidCxn, String uuidCxnMaestra);
}
