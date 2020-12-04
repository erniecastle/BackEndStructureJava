/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mef.erp.modelo;

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
public interface PeriodosNominaDAOIF {

    Mensaje agregar(PeriodosNomina entity, String uuidCxn);

    Mensaje actualizar(PeriodosNomina entity, String uuidCxn);

    Mensaje eliminar(PeriodosNomina entity, String uuidCxn);

    Mensaje getPeriodosNominaAll(String uuidCxn);

    Mensaje getPeriodosNominaPorClave(String clave, int year, String claveTipoCorrida, String uuidCxn);

    Mensaje getPeriodosNominaPorClaveYTipoDeNominaCorrida(String clave, String claveTipoNomina, String claveTipoCorrida, String uuidCxn);

    Mensaje getPeriodosNominaPorClaveYTipoDeNominaECorrida(String clave, TipoNomina tipoNomina, String claveTipoCorrida, String uuidCxn);

    Mensaje getPeriodosNominaActualPorFecha(Date fecha, String claveTipoNomina, String claveTipoCorrida, boolean status, String uuidCxn);//JEVC01

    Mensaje getPeriodosNominaPorTipoNominaYRangoDeFechas(Date fechaInicial, Date fechaFinal, String claveTipoNomina, String claveTipoCorrida, String uuidCxn);

    Mensaje getUltimoPeriodoCerradoPorFecha(Date fecha, String claveTipoNomina, String claveTipoCorrida, String uuidCxn);

    Mensaje getPeriodosNominaPorAño(Integer year, String uuidCxn);

    Mensaje getPeriodosNominaPorAñoYTipoNominaYTipoCorrida(Integer año, String tipoNomina, String tipoCorrida, String uuidCxn);

    Mensaje getPeriodosNominaPorFechasYTipoNominaCorrida(Date inicio, Date fin, TipoNomina tipoNomina, String claveTipoCorrida, String uuidCxn);

    Mensaje consultaPorRangos(Integer inicio, Integer rango, String uuidCxn);

    Mensaje consultaPorFiltrosPeriodos(String query, Object[] campos, Object[] valores, String uuidCxn);

    Mensaje existeDato(String campo, Object valor, String uuidCxn);

    Mensaje saveDeletePeriodosNomina(List<PeriodosNomina> entitysCambios, List<PeriodosNomina> eliminados, TipoCorrida tCorrida, String uuidCxn);

    Mensaje ObtenerFechaFinalMax(String claveTipoNomina, String claveTipoCorrida, Integer año, String uuidCxn);

    Mensaje ObtenerFechaFinalMin(String claveTipoNomina, String claveTipoCorrida, Integer año, String uuidCxn);//JEVC01

    Mensaje getUltimoPeriodo(Integer año, String claveTipoNomina, String claveTipoCorrida, String uuidCxn);

    Mensaje getPrimerPeriodo(Integer año, String claveTipoNomina, String claveTipoCorrida, String uuidCxn);

    Mensaje getPeriodosNominaPorFechaTipoNominaCorrida(Date fecha, String claveTipoNomina, String claveTipoCorrida, String uuidCxn);

    Mensaje actualizaListaPorCampos(String[] campoModificar, Object[] valoresModificado, String[] camposWhere, Object[] valoresWhere, String uuidCxn);

    /*boolean*/
    Mensaje getStatusPeriodo(Long idPeriodo, String uuidCxn);
 
    /*List<PeriodosNomina>*/
    Mensaje getPeriodosNominaPorAñoYTipoCorrida(Integer year, String claveTipoCorrida, String uuidCxn);
    
    Mensaje getPeriodosNominaPorFechayTipoCorridaSinStatus(Date fecha, String claveTipoNomina, String claveTipoCorrida,String uuidCxn);
}
