/**
 * @author: Victor Lopez Compañía: Macropro. Descripción del programa: interface
 * FiniquitosLiquidacionesDAOIF para llamados a metodos de HIBERNATE
 * Fecha:04-07-2012
 * -----------------------------------------------------------------------------
 * MODIFICACIONES:
 * -----------------------------------------------------------------------------
 * Clave:JSA01 Autor:Jose Armando Fecha:20/09/2013 Descripción: Se agrego el
 * modoBaja y el TipoBaja
 * -----------------------------------------------------------------------------
 */
package com.mef.erp.modelo;

import com.mef.erp.modelo.entidad.*;
import com.mef.erp.modelo.entidad.ModoBaja;
import com.mef.erp.modelo.entidad.TipoBaja;
import java.util.Date;
import java.util.List;

public interface FiniquitosLiquidacionesDAOIF {

    Mensaje agregar(FiniquitosLiquidaciones entity, String uuidCxn);

    Mensaje actualizar(FiniquitosLiquidaciones entity, String uuidCxn);

    Mensaje eliminar(FiniquitosLiquidaciones entity, String uuidCxn);

    Mensaje getFiniquitosLiquidacionesAll(String claveRazonesSociales, TipoBaja tipoBaja, ModoBaja modoBaja, String uuidCxn);//JSA

    Mensaje getFiniquitosLiquidacionesPorCamposClave(String referencia, RazonesSociales razonSocial, ModoBaja modoBaja, TipoBaja tipoBaja, String uuidCxn);

    Mensaje getPorEmpleado(String claveEmpleado, String claveRazonSocial, ModoBaja modoBaja, TipoBaja tipoBaja, String uuidCxn);

    Mensaje getFiniquitosLiquidacionesGuardarModificar(FiniquitosLiquidaciones finiquitosLiquidaciones, Object[] clavesDeleteMovimientos,
            List<MovNomConcep> AgreModifMovimientos, List<FiniqLiquidPlazas> finiqLiquidPlazas, Object[] eliminadosfiniqLiquidPlazas,
            List<FiniqLiquidCncNom> listFiniqLiquidCncNom, Object[] clavesDeleteFiniqLiquidCncNom,
            int cantPlazasFiniquitadas, int cantPlazasEmpleado, IngresosReingresosBajas ingresosReingresosBajas, List<PlazasPorEmpleado> cerrarPlazaEmpleado, SalariosIntegrados salariosIntegrado, int rango, String uuidCxn);

    Mensaje getCancelarFiniquito(Object[] eliminadoMovNomConceps, List<PlazasPorEmpleado> listPlazasPorEmpleado, IngresosReingresosBajas ingresosReingresosBajas,
            SalariosIntegrados salariosIntegrado, FiniquitosLiquidaciones finiquitosLiquidaciones, String uuidCxn);

    Mensaje consultaPorRangos(Integer inicio, Integer rango, String uuidCxn);

    Mensaje consultaPorFiltrosFiniquitos(String query, Object[] campos, Object[] valores, String uuidCxn);

    Mensaje existeDato(String campo, Object valor, String uuidCxn);
    
    Mensaje EliminarFiniquitosLiquidacion(FiniquitosLiquidaciones finiquitosLiquidaciones,List<PlazasPorEmpleadosMov> plazasPorEmpleados,String uuidCxn);
    
    Mensaje validaFiniquitosLiquidacionTimbrados(Date fechaCalculo,List<PlazasPorEmpleadosMov> plazasPorEmpleados,String uuidCxn);
}
