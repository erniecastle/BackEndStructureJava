/**
 * @author: Victor Lopez Fecha de Creación: 04/07/2012 Compañía: MacroPro.
 * Descripción del programa: interface servicio ServicioFiniqLiquidPlazasIF
 * -----------------------------------------------------------------------------
 * MODIFICACIONES:
 * -----------------------------------------------------------------------------
 * Clave: Autor: Fecha: Descripción:
 * -----------------------------------------------------------------------------
 * Clave: Autor: Fecha: Descripción:
 * -----------------------------------------------------------------------------
 */
package com.mef.erp.servicios;

import com.mef.erp.modelo.entidad.FiniqLiquidPlazas;
import com.mef.erp.modelo.entidad.FiniquitosLiquidaciones;
import com.mef.erp.modelo.entidad.Mensaje;
import java.util.List;

public interface ServicioFiniqLiquidPlazasIF {

    /*FiniqLiquidPlazas*/
    Mensaje agregar(FiniqLiquidPlazas entity, String uuidCxn);

    /*boolean*/
    Mensaje actualizar(FiniqLiquidPlazas entity, String uuidCxn);

    /*boolean*/
    Mensaje eliminar(FiniqLiquidPlazas entity, String uuidCxn);

    /*List<FiniqLiquidPlazas>*/
    Mensaje getFiniqLiquidPlazasAll(String uuidCxn);

    /*List<FiniqLiquidPlazas>*/
    Mensaje getFiniqLiquidPlazasPorFiniquitosLiquidaciones(FiniquitosLiquidaciones finiquitosLiquidacion, String uuidCxn);

    /*int*/
    Mensaje getCantidadPlazasFiniquitadaPorEmpleado(String claveEmpleado, String razonSocial, String uuidCxn);

    /*List<FiniqLiquidPlazas>*/
    Mensaje getPlazasFiniquitadaPorEmpleado(String claveEmpleado, String razonSocial, String uuidCxn);

//    List<FiniqLiquidPlazas> getFiniqLiquidPlazasPorRazonSocial(RazonesSociales razonSocial);
    /*List<FiniqLiquidPlazas>*/
    Mensaje consultaPorRangos(Integer inicio, Integer rango, String uuidCxn);

    /*List<FiniqLiquidPlazas>*/
    Mensaje consultaPorFiltros(String query, Object[] campos, Object[] valores, String uuidCxn);

    /*Boolean*/
    Mensaje existeDato(String campo, Object valor, String uuidCxn);

    /*List<FiniqLiquidPlazas>*/
    Mensaje saveDeleteFiniqLiquidPlazas(List<FiniqLiquidPlazas> entitysCambios, Object[] clavesDelete, String uuidCxn);

}
