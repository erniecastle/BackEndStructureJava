/**
 * @author: Victor Lopez Fecha de Creación: 04/07/2012 Compañía: MacroPro.
 * Descripción del programa: interface servicio
 * ServicioFiniquitosLiquidacionesIF
 * -----------------------------------------------------------------------------
 * MODIFICACIONES:
 * -----------------------------------------------------------------------------
 * Clave:JSA01 Autor:Jose Armando Fecha:20/09/2013 Descripción: Se agrego el
 * modoBaja y el TipoBaja
 * -----------------------------------------------------------------------------
 * Clave: Autor: Fecha: Descripción:
 * -----------------------------------------------------------------------------
 */
package com.mef.erp.servicios;

import com.mef.erp.modelo.entidad.*;
import com.mef.erp.modelo.entidad.ModoBaja;
import com.mef.erp.modelo.entidad.TipoBaja;
import java.util.Date;
import java.util.List;

public interface ServicioFiniquitosLiquidacionesIF {

    /*FiniquitosLiquidaciones*/
    Mensaje agregar(FiniquitosLiquidaciones entity, String uuidCxn);

    /*boolean*/
    Mensaje actualizar(FiniquitosLiquidaciones entity, String uuidCxn);

    /*boolean*/
    Mensaje eliminar(FiniquitosLiquidaciones entity, String uuidCxn);

    /*List<FiniquitosLiquidaciones>*/
    Mensaje getFiniquitosLiquidacionesAll(String claveRazonesSociales, TipoBaja tipoBaja, ModoBaja modoBaja, String uuidCxn);//JSA01

    /*FiniquitosLiquidaciones*/
    Mensaje getFiniquitosLiquidacionesPorCamposClave(String referencia, RazonesSociales razonSocial, ModoBaja modoBaja, TipoBaja tipoBaja, String uuidCxn);

    /*FiniquitosLiquidaciones*/
    Mensaje getPorEmpleado(String claveEmpleado, String claveRazonSocial, ModoBaja modoBaja, TipoBaja tipoBaja, String uuidCxn);

    /*Mensaje*/
    Mensaje getFiniquitosLiquidacionesGuardarModificar(FiniquitosLiquidaciones finiquitosLiquidaciones, Object[] clavesDeleteMovimientos,
            List<MovNomConcep> AgreModifMovimientos, List<FiniqLiquidPlazas> finiqLiquidPlazas, Object[] eliminadosfiniqLiquidPlazas,
            List<FiniqLiquidCncNom> listFiniqLiquidCncNom, Object[] clavesDeleteFiniqLiquidCncNom,
            int cantPlazasFiniquitadas, int cantPlazasEmpleado, IngresosReingresosBajas ingresosReingresosBajas, List<PlazasPorEmpleado> cerrarPlazaEmpleado, SalariosIntegrados salariosIntegrado, int rango, String uuidCxn);

    /*Mensaje*/
    Mensaje getCancelarFiniquito(Object[] eliminadoMovNomConceps, List<PlazasPorEmpleado> listPlazasPorEmpleado, IngresosReingresosBajas ingresosReingresosBajas,
            SalariosIntegrados salariosIntegrado, FiniquitosLiquidaciones finiquitosLiquidaciones, String uuidCxn);

    /*List<FiniquitosLiquidaciones>*/
    Mensaje consultaPorRangos(Integer inicio, Integer rango, String uuidCxn);

    /*List<FiniquitosLiquidaciones>*/
    Mensaje consultaPorFiltros(String query, Object[] campos, Object[] valores, String uuidCxn);

    /*Boolean*/
    Mensaje existeDato(String campo, Object valor, String uuidCxn);
    
     Mensaje EliminarFiniquitosLiquidacion(FiniquitosLiquidaciones finiquitosLiquidaciones,List<PlazasPorEmpleadosMov> plazasPorEmpleados,String uuidCxn);
     
     Mensaje validaFiniquitosLiquidacionTimbrados(Date fechaCalculo,List<PlazasPorEmpleadosMov> plazasPorEmpleados,String uuidCxn);

}
