/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mef.erp.servicios;

import com.mef.erp.modelo.entidad.Mensaje;
import com.mef.erp.modelo.entidad.MovNomConcep;
import com.mef.erp.modelo.entidad.cfdi.CFDIEmpleado;
import java.util.List;

/**
 *
 * @author Ernesto
 */
public interface ServicioMovimientosNominaIF {

    /*List<MovNomConcep>*/
    Mensaje getMovimientosNominaAll(String uuidCxn);

    /*List<MovNomConcep>*/
    Mensaje getMovimientosNominaAsc(String uuidCxn);

    /*Object*/
    Mensaje getMaxNumeroMovimientoPorTipoNominaYPeriodo(String claveTipoNomina, Long idPeriodo, String uuidCxn);

    /*List<MovNomConcep>*/
    Mensaje saveDeleteMovimientosNomina(List<MovNomConcep> AgreModif, Object[] clavesDelete, Object[] valoresReestablecer, boolean incluirEliminadoDiferenteTipoPantalla100, String uuidCxn);

    /*List<MovNomConcep>*/
    Mensaje getMovimientosPorPlazaEmpleado(Object[] clavesPlazasPorEmpleado, String claveTipoCorrida, String claveRazonSocial, String uuidCxn);

    /*List<MovNomConcep>*/
    Mensaje getMovimientosPorFiltro(String[] camposWhere, Object[] valoresWhere, String uuidCxn);

    /*List<Object>*/
    public Mensaje getMovimientosPorFiltroEspecifico(String[] camposWhere, Object[] valoresWhere, String uuidCxn);

    /*List<Object>*/
    public Mensaje getCalculosUnidadesPorFiltroEspecifico(String[] camposWhere, Object[] valoresWhere, List<CFDIEmpleado> listCFDIEmpleado, String uuidCxn);

    /*int*/
    Mensaje eliminaListaMovimientos(String campo, Object[] valores, List<CFDIEmpleado> valoresCFDI, Object[] valoresCalculoUnidades, Object[] valoresReestablecer, boolean incluirEliminadoDiferenteTipoPantalla100, String uuidCxn);

    Mensaje buscaMovimientosNominaFiltrado(List<Object> valoresDeFiltrado, String uuidCxn);

}
