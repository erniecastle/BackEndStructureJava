/**
 * @author: Victor Lopez Fecha de Creación: 04/07/2012 Compañía: MacroPro.
 * Descripción del programa: clase SERVICIO ServicioFiniquitosLiquidaciones,
 * para llamados a metodos de objeto DAO
 * -----------------------------------------------------------------------------
 * Clave:JSA01 Autor:Jose Armando Fecha:20/09/2013 Descripción: Se agrego el
 * modoBaja y el TipoBaja
 * -----------------------------------------------------------------------------
 */
package com.mef.erp.servicios;

import com.mef.erp.modelo.FiniquitosLiquidacionesDAO;
import com.mef.erp.modelo.entidad.*;
import com.mef.erp.modelo.entidad.ModoBaja;
import com.mef.erp.modelo.entidad.TipoBaja;
import java.util.Date;
import java.util.List;

public class ServicioFiniquitosLiquidaciones implements ServicioFiniquitosLiquidacionesIF {

    private FiniquitosLiquidacionesDAO finiquitosLiquidacionesDAO;
  

    public Mensaje agregar(FiniquitosLiquidaciones entity, String uuidCxn) {
        return getFiniquitosLiquidacionesDAO().agregar(entity, uuidCxn);
    }

    public Mensaje actualizar(FiniquitosLiquidaciones entity, String uuidCxn) {
        return getFiniquitosLiquidacionesDAO().actualizar(entity, uuidCxn);
    }

    public Mensaje eliminar(FiniquitosLiquidaciones entity, String uuidCxn) {
        return getFiniquitosLiquidacionesDAO().eliminar(entity, uuidCxn);
    }

    public Mensaje getFiniquitosLiquidacionesAll(String claveRazonesSociales, TipoBaja tipoBaja, ModoBaja modoBaja, String uuidCxn) {//JSA01
        return getFiniquitosLiquidacionesDAO().getFiniquitosLiquidacionesAll(claveRazonesSociales, tipoBaja, modoBaja, uuidCxn);
    }

    public Mensaje getFiniquitosLiquidacionesPorCamposClave(String referencia, RazonesSociales razonSocial, ModoBaja modoBaja, TipoBaja tipoBaja, String uuidCxn) {
        return getFiniquitosLiquidacionesDAO().getFiniquitosLiquidacionesPorCamposClave(referencia, razonSocial, modoBaja, tipoBaja, uuidCxn);
    }

    public Mensaje getPorEmpleado(String claveEmpleado, String claveRazonSocial, ModoBaja modoBaja, TipoBaja tipoBaja, String uuidCxn) {
        return getFiniquitosLiquidacionesDAO().getPorEmpleado(claveEmpleado, claveRazonSocial, modoBaja, tipoBaja, uuidCxn);
    }

    public Mensaje getFiniquitosLiquidacionesGuardarModificar(FiniquitosLiquidaciones finiquitosLiquidaciones, Object[] clavesDeleteMovimientos,
            List<MovNomConcep> AgreModifMovimientos, List<FiniqLiquidPlazas> finiqLiquidPlazas, Object[] eliminadosfiniqLiquidPlazas,
            List<FiniqLiquidCncNom> listFiniqLiquidCncNom, Object[] clavesDeleteFiniqLiquidCncNom,
            int cantPlazasFiniquitadas, int cantPlazasEmpleado, IngresosReingresosBajas ingresosReingresosBajas, List<PlazasPorEmpleado> cerrarPlazaEmpleado, SalariosIntegrados salariosIntegrado, int rango,  String uuidCxn) {
        return getFiniquitosLiquidacionesDAO().getFiniquitosLiquidacionesGuardarModificar(finiquitosLiquidaciones, clavesDeleteMovimientos,
                AgreModifMovimientos, finiqLiquidPlazas, eliminadosfiniqLiquidPlazas, listFiniqLiquidCncNom, clavesDeleteFiniqLiquidCncNom,
                cantPlazasFiniquitadas, cantPlazasEmpleado, ingresosReingresosBajas, cerrarPlazaEmpleado, salariosIntegrado, rango, uuidCxn);
    }

    public Mensaje getCancelarFiniquito(Object[] eliminadoMovNomConceps, List<PlazasPorEmpleado> listPlazasPorEmpleado, IngresosReingresosBajas ingresosReingresosBajas,
            SalariosIntegrados salariosIntegrado, FiniquitosLiquidaciones finiquitosLiquidaciones, String uuidCxn) {
        return getFiniquitosLiquidacionesDAO().getCancelarFiniquito(eliminadoMovNomConceps, listPlazasPorEmpleado, ingresosReingresosBajas,
                salariosIntegrado, finiquitosLiquidaciones, uuidCxn);
    }

    public Mensaje consultaPorRangos(Integer inicio, Integer rango,  String uuidCxn) {
        return getFiniquitosLiquidacionesDAO().consultaPorRangos(inicio, rango, uuidCxn);
    }

    public Mensaje consultaPorFiltros(String query, Object[] campos, Object[] valores, String uuidCxn) {
        return getFiniquitosLiquidacionesDAO().consultaPorFiltrosFiniquitos(query, campos, valores, uuidCxn);
    }

    public Mensaje existeDato(String campo, Object valor, String uuidCxn) {
        return getFiniquitosLiquidacionesDAO().existeDato(campo, valor, uuidCxn);
    }

    public FiniquitosLiquidacionesDAO getFiniquitosLiquidacionesDAO() {
        return finiquitosLiquidacionesDAO;
    }

    public void setFiniquitosLiquidacionesDAO(FiniquitosLiquidacionesDAO finiquitosLiquidacionesDAO) {
        this.finiquitosLiquidacionesDAO = finiquitosLiquidacionesDAO;
    }

    @Override
    public Mensaje EliminarFiniquitosLiquidacion(FiniquitosLiquidaciones finiquitosLiquidaciones, List<PlazasPorEmpleadosMov> plazasPorEmpleados, String uuidCxn) {
        return finiquitosLiquidacionesDAO.EliminarFiniquitosLiquidacion(finiquitosLiquidaciones, plazasPorEmpleados, uuidCxn);
    }

    @Override
    public Mensaje validaFiniquitosLiquidacionTimbrados(Date fechaCalculo, List<PlazasPorEmpleadosMov> plazasPorEmpleados, String uuidCxn) {
       return finiquitosLiquidacionesDAO.validaFiniquitosLiquidacionTimbrados(fechaCalculo, plazasPorEmpleados, uuidCxn);
    }
}
