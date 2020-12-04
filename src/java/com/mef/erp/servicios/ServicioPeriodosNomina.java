/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mef.erp.servicios;

import com.mef.erp.modelo.PeriodosNominaDAO;
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
public class ServicioPeriodosNomina implements ServicioPeriodosNominaIF {

    private PeriodosNominaDAO periodosNominaDAO;

    public Mensaje agregar(PeriodosNomina entity, String uuidCxn) {
        return periodosNominaDAO.agregar(entity, uuidCxn);
    }

    public Mensaje actualizar(PeriodosNomina entity, String uuidCxn) {
        return periodosNominaDAO.actualizar(entity, uuidCxn);
    }

    public Mensaje eliminar(PeriodosNomina entity, String uuidCxn) {
        return periodosNominaDAO.eliminar(entity, uuidCxn);
    }

    public Mensaje getPeriodosNominaAll(String uuidCxn) {
        return periodosNominaDAO.getPeriodosNominaAll(uuidCxn);
    }

    public Mensaje getPeriodosNominaPorClave(String clave, int year, String claveTipoCorrida, String uuidCxn) {
        return periodosNominaDAO.getPeriodosNominaPorClave(clave, year, claveTipoCorrida, uuidCxn);
    }

    public Mensaje getPeriodosNominaPorClaveYTipoDeNominaCorrida(String clave, String claveTipoNomina, String claveTipoCorrida, String uuidCxn) {
        return periodosNominaDAO.getPeriodosNominaPorClaveYTipoDeNominaCorrida(clave, claveTipoNomina, claveTipoCorrida, uuidCxn);
    }

    public Mensaje getPeriodosNominaPorClaveYTipoDeNominaECorrida(String clave, TipoNomina tipoNomina, String claveTipoCorrida, String uuidCxn) {
        return periodosNominaDAO.getPeriodosNominaPorClaveYTipoDeNominaECorrida(clave, tipoNomina, claveTipoCorrida, uuidCxn);
    }

    public Mensaje getPeriodosNominaActualPorFecha(Date fecha, String claveTipoNomina, String claveTipoCorrida, boolean status, String uuidCxn) {
        return periodosNominaDAO.getPeriodosNominaActualPorFecha(fecha, claveTipoNomina, claveTipoCorrida, status, uuidCxn);
    }

    public Mensaje getPeriodosNominaPorTipoNominaYRangoDeFechas(Date fechaInicial, Date fechaFinal, String claveTipoNomina, String claveTipoCorrida, String uuidCxn) {
        return periodosNominaDAO.getPeriodosNominaPorTipoNominaYRangoDeFechas(fechaInicial, fechaFinal, claveTipoNomina, claveTipoCorrida, uuidCxn);
    }

    public Mensaje getUltimoPeriodoCerradoPorFecha(Date fecha, String claveTipoNomina, String claveTipoCorrida, String uuidCxn) {
        return periodosNominaDAO.getUltimoPeriodoCerradoPorFecha(fecha, claveTipoNomina, claveTipoCorrida, uuidCxn);
    }

    @Override
    public Mensaje getPeriodosNominaPorAño(Integer year, String uuidCxn) {
        return periodosNominaDAO.getPeriodosNominaPorAño(year, uuidCxn);
    }

    public Mensaje getPeriodosNominaPorAñoYTipoNominaYTipoCorrida(Integer año, String tipoNomina, String claveTipoCorrida, String uuidCxn) {
        return periodosNominaDAO.getPeriodosNominaPorAñoYTipoNominaYTipoCorrida(año, tipoNomina, claveTipoCorrida, uuidCxn);
    }

    public Mensaje getPeriodosNominaPorFechasYTipoNominaCorrida(Date inicio, Date fin, TipoNomina tipoNomina, String claveTipoCorrida, String uuidCxn) {
        return periodosNominaDAO.getPeriodosNominaPorFechasYTipoNominaCorrida(inicio, fin, tipoNomina, claveTipoCorrida, uuidCxn);
    }

    public Mensaje consultaPorRangos(Integer inicio, Integer rango, String uuidCxn) {
        return periodosNominaDAO.consultaPorRangos(inicio, rango, uuidCxn);
    }

    public Mensaje consultaPorFiltros(String query, Object[] campos, Object[] valores, String uuidCxn) {
        return periodosNominaDAO.consultaPorFiltrosPeriodos(query, campos, valores, uuidCxn);
    }

    public Mensaje existeDato(String campo, Object valor, String uuidCxn) {
        return periodosNominaDAO.existeDato(campo, valor, uuidCxn);
    }

    public Mensaje saveDeletePeriodosNomina(List<PeriodosNomina> entitysCambios, List<PeriodosNomina> eliminados, TipoCorrida tCorrida, String uuidCxn) {
        return periodosNominaDAO.saveDeletePeriodosNomina(entitysCambios, eliminados, tCorrida, uuidCxn);
    }

    public Mensaje ObtenerFechaFinalMax(String claveTipoNomina, String claveTipoCorrida, Integer año, String uuidCxn) {
        return periodosNominaDAO.ObtenerFechaFinalMax(claveTipoNomina, claveTipoCorrida, año, uuidCxn);
    }

    public Mensaje ObtenerFechaFinalMin(String claveTipoNomina, String claveTipoCorrida, Integer año, String uuidCxn) {
        return periodosNominaDAO.ObtenerFechaFinalMin(claveTipoNomina, claveTipoCorrida, año, uuidCxn);
    }

    public Mensaje getUltimoPeriodo(Integer año, String claveTipoNomina, String claveTipoCorrida, String uuidCxn) {
        return periodosNominaDAO.getUltimoPeriodo(año, claveTipoNomina, claveTipoCorrida, uuidCxn);
    }

    public Mensaje getPrimerPeriodo(Integer año, String claveTipoNomina, String claveTipoCorrida, String uuidCxn) {
        return periodosNominaDAO.getPrimerPeriodo(año, claveTipoNomina, claveTipoCorrida, uuidCxn);
    }

    public Mensaje getPeriodosNominaPorFechaTipoNominaCorrida(Date fecha, String claveTipoNomina, String claveTipoCorrida, String uuidCxn) {
        return periodosNominaDAO.getPeriodosNominaPorFechaTipoNominaCorrida(fecha, claveTipoNomina, claveTipoCorrida, uuidCxn);
    }

    public Mensaje actualizaListaPorCampos(String[] campoModificar, Object[] valoresModificado, String[] camposWhere, Object[] valoresWhere, String uuidCxn) {
        return periodosNominaDAO.actualizaListaPorCampos(campoModificar, valoresModificado, camposWhere, valoresWhere, uuidCxn);
    }

    public Mensaje getStatusPeriodo(Long idPeriodo, String uuidCxn) {
        return periodosNominaDAO.getStatusPeriodo(idPeriodo, uuidCxn);
    }

    public PeriodosNominaDAO getPeriodosNominaDAO() {
        return periodosNominaDAO;
    }

    public void setPeriodosNominaDAO(PeriodosNominaDAO periodosNominaDAO) {
        this.periodosNominaDAO = periodosNominaDAO;
    }

    @Override
    public Mensaje getPeriodosNominaPorAñoYTipoCorrida(Integer year, String claveTipoCorrida, String uuidCxn) {
        return periodosNominaDAO.getPeriodosNominaPorAñoYTipoCorrida(year, claveTipoCorrida, uuidCxn);
    }
    @Override
    public Mensaje getPeriodosNominaPorFechayTipoCorridaSinStatus(Date fecha, String claveTipoNomina, String claveTipoCorrida, String uuidCxn) {
        return periodosNominaDAO.getPeriodosNominaPorFechayTipoCorridaSinStatus(fecha, claveTipoNomina, claveTipoCorrida, uuidCxn);
    }

}
