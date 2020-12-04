/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mef.erp.servicios;

import com.mef.erp.modelo.entidad.Mensaje;
import com.mef.erp.modelo.entidad.PeriodosNomina;
import com.mef.erp.modelo.entidad.TipoCorrida;
import com.mef.erp.modelo.entidad.TipoNomina;
import java.util.Date;
import java.util.List;

/**
 *
 * @author daniel
 */
public interface ServicioPeriodosNominaIF {

    /*PeriodosNomina*/
    Mensaje agregar(PeriodosNomina entity, String uuidCxn);

    /*boolean*/
    Mensaje actualizar(PeriodosNomina entity, String uuidCxn);

    /*boolean*/
    Mensaje eliminar(PeriodosNomina entity, String uuidCxn);

    /*List<PeriodosNomina>*/
    Mensaje getPeriodosNominaAll(String uuidCxn);

    /*PeriodosNomina*/
    Mensaje getPeriodosNominaPorClave(String clave, int year, String claveTipoCorrida, String uuidCxn);

    /*PeriodosNomina*/
    Mensaje getPeriodosNominaPorClaveYTipoDeNominaCorrida(String clave, String claveTipoNomina, String claveTipoCorrida, String uuidCxn);

    /*PeriodosNomina*/
    Mensaje getPeriodosNominaPorClaveYTipoDeNominaECorrida(String clave, TipoNomina tipoNomina, String claveTipoCorrida, String uuidCxn);

    /*PeriodosNomina*/
    Mensaje getPeriodosNominaActualPorFecha(Date fecha, String claveTipoNomina, String claveTipoCorrida, boolean status, String uuidCxn);//JEVC01

    /*List<PeriodosNomina>*/
    Mensaje getPeriodosNominaPorTipoNominaYRangoDeFechas(Date fechaInicial, Date fechaFinal, String claveTipoNomina, String claveTipoCorrida, String uuidCxn);

    /*PeriodosNomina*/
    Mensaje getUltimoPeriodoCerradoPorFecha(Date fecha, String claveTipoNomina, String claveTipoCorrida, String uuidCxn);

    /*boolean*/
    Mensaje getPeriodosNominaPorAño(Integer year, String uuidCxn);

    /*List<PeriodosNomina>*/
    Mensaje getPeriodosNominaPorAñoYTipoNominaYTipoCorrida(Integer año, String tipoNomina, String tipoCorrida, String uuidCxn);

    /*List<PeriodosNomina>*/
    Mensaje getPeriodosNominaPorFechasYTipoNominaCorrida(Date inicio, Date fin, TipoNomina tipoNomina, String claveTipoCorrida, String uuidCxn);

    /*List<PeriodosNomina>*/
    Mensaje consultaPorRangos(Integer inicio, Integer rango, String uuidCxn);

    /*List<PeriodosNomina>*/
    Mensaje consultaPorFiltros(String query, Object[] campos, Object[] valores, String uuidCxn);

    /*Boolean*/
    Mensaje existeDato(String campo, Object valor, String uuidCxn);

    /*List<PeriodosNomina>*/
    Mensaje saveDeletePeriodosNomina(List<PeriodosNomina> entitysCambios, List<PeriodosNomina> eliminados, TipoCorrida tCorrida, String uuidCxn);

    /*Date*/
    Mensaje ObtenerFechaFinalMax(String claveTipoNomina, String claveTipoCorrida, Integer año, String uuidCxn);

    /*Date*/
    Mensaje ObtenerFechaFinalMin(String claveTipoNomina, String claveTipoCorrida, Integer año, String uuidCxn);//JEVC01

    /*PeriodosNomina*/
    Mensaje getUltimoPeriodo(Integer año, String claveTipoNomina, String claveTipoCorrida, String uuidCxn);

    /*PeriodosNomina*/
    Mensaje getPrimerPeriodo(Integer año, String claveTipoNomina, String claveTipoCorrida, String uuidCxn);

    /*PeriodosNomina*/
    Mensaje getPeriodosNominaPorFechaTipoNominaCorrida(Date fecha, String claveTipoNomina, String claveTipoCorrida, String uuidCxn);

    /*int*/
    Mensaje actualizaListaPorCampos(String[] campoModificar, Object[] valoresModificado, String[] camposWhere, Object[] valoresWhere, String uuidCxn);

    /*boolean*/
    Mensaje getStatusPeriodo(Long idPeriodo, String uuidCxn);

    /*List<PeriodosNomina>*/
    Mensaje getPeriodosNominaPorAñoYTipoCorrida(Integer year, String claveTipoCorrida, String uuidCxn);
    
    /*PeriodosNomina*/
    Mensaje getPeriodosNominaPorFechayTipoCorridaSinStatus(Date fecha, String claveTipoNomina, String claveTipoCorrida,String uuidCxn);

}
