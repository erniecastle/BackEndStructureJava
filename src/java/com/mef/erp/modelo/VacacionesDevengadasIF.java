/**
 * @author: Ernesto Valenzuela Fecha de Creación: 16/05/2016 Compañía: Exito
 * Software. Descripción del programa: inteface de clase de VacacionesDevengadas
 * para llamados a metodos de HIBERNATE
 * -----------------------------------------------------------------------------
 * MODIFICACIONES:
 * -----------------------------------------------------------------------------
 */
package com.mef.erp.modelo;

import com.mef.erp.modelo.entidad.Mensaje;
import com.mef.erp.modelo.entidad.RazonesSociales;
import com.mef.erp.modelo.entidad.VacacionesDevengadas;
import java.util.List;

/**
 *
 * @author Ernesto
 */
public interface VacacionesDevengadasIF extends GenericDAO<VacacionesDevengadas, Long> {

    public Mensaje getVacacionesDevengadasAll(String uuidCxn);

    public Mensaje calcularVacacionesDevengadasEmpleados(RazonesSociales razonesSociales, String uuidCxn, String uuidCxnMaestra);

    Mensaje getVacacionesDenvengadasPorEmpleado(String claveEmpleado, String claveRazonSocial, String uuidCxn);

    Mensaje saveDeleteVacacionesDevengadas(List<VacacionesDevengadas> entitysCambios,  int rango, String uuidCxn);
}
