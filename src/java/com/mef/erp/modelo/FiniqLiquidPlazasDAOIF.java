/**
 * @author: Victor Lopez Compañía: Macropro. Descripción del programa: interface
 * FiniqLiquidPlazasDAOIF para llamados a metodos de HIBERNATE Fecha:04-07-2012
 * -----------------------------------------------------------------------------
 * MODIFICACIONES:
 * -----------------------------------------------------------------------------
 * Clave: Autor: Fecha: Descripción:
 * -----------------------------------------------------------------------------
 */
package com.mef.erp.modelo;

import com.mef.erp.modelo.entidad.FiniqLiquidPlazas;
import com.mef.erp.modelo.entidad.FiniquitosLiquidaciones;
import com.mef.erp.modelo.entidad.Mensaje;
import java.util.List;

public interface FiniqLiquidPlazasDAOIF {

    Mensaje agregar(FiniqLiquidPlazas entity, String uuidCxn);

    Mensaje actualizar(FiniqLiquidPlazas entity, String uuidCxn);

    Mensaje eliminar(FiniqLiquidPlazas entity, String uuidCxn);

    Mensaje getFiniqLiquidPlazasAll(String uuidCxn);

    Mensaje getFiniqLiquidPlazasPorFiniquitosLiquidaciones(FiniquitosLiquidaciones finiquitosLiquidacion, String uuidCxn);

    Mensaje getCantidadPlazasFiniquitadaPorEmpleado(String claveEmpleado, String razonSocial, String uuidCxn);

    Mensaje getPlazasFiniquitadaPorEmpleado(String claveEmpleado, String razonSocial, String uuidCxn);

//    List<FiniqLiquidPlazas> getFiniqLiquidPlazasPorRazonSocial(RazonesSociales razonSocial);
    Mensaje consultaPorRangos(Integer inicio, Integer rango, String uuidCxn);

    Mensaje consultaPorFiltrosFiniquitos(String query, Object[] campos, Object[] valores, String uuidCxn);

    Mensaje existeDato(String campo, Object valor, String uuidCxn);

    Mensaje saveDeleteFiniqLiquidPlazas(List<FiniqLiquidPlazas> entitysCambios, Object[] clavesDelete, String uuidCxn);
}
