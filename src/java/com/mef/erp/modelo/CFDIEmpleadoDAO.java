/**
 * @author: Fecha de Creación: Compañía: Descripción del programa:
 * -----------------------------------------------------------------------------
 * MODIFICACIONES:
 * -----------------------------------------------------------------------------
 * Clave: JSA01 Autor: Jose Armando Fecha: 02/05/2015 Descripción: se agrego el
 * metodo getCFDIEmpleadoStatusPorFiltroPorRangoPeriodosNomina para obtener los
 * timbre del empleado por un rango de fechas.
 * -----------------------------------------------------------------------------
 * Clave: Autor: Fecha: Descripción:
 * -----------------------------------------------------------------------------
 */
package com.mef.erp.modelo;

import com.mef.erp.modelo.entidad.Asistencias;
import com.mef.erp.modelo.entidad.CentroDeCosto;
import com.mef.erp.modelo.entidad.Departamentos;
import com.mef.erp.modelo.entidad.Empleados;
import com.mef.erp.modelo.entidad.ManejoHorasPor;
import com.mef.erp.modelo.entidad.ManejoSalarioDiario;
import com.mef.erp.modelo.entidad.Mensaje;
import com.mef.erp.modelo.entidad.MovNomBaseAfecta;
import com.mef.erp.modelo.entidad.MovNomConceParam;
import com.mef.erp.modelo.entidad.MovNomConcep;
import com.mef.erp.modelo.entidad.Naturaleza;
import com.mef.erp.modelo.entidad.Periodicidad;
import com.mef.erp.modelo.entidad.PeriodosNomina;
import com.mef.erp.modelo.entidad.PlazasPorEmpleadosMov;
import com.mef.erp.modelo.entidad.RazonesSociales;
import com.mef.erp.modelo.entidad.RegistroPatronal;
import com.mef.erp.modelo.entidad.TipoCorrida;
import com.mef.erp.modelo.entidad.TipoNomina;
import com.mef.erp.modelo.entidad.cfdi.CFDIEmpleado;
import com.mef.erp.modelo.entidad.cfdi.CFDIRecibo;
import com.mef.erp.modelo.entidad.cfdi.DatosHorasExtras;
import com.mef.erp.modelo.entidad.cfdi.DatosIncapacidades;
import com.mef.erp.modelo.entidad.cfdi.DatosParaTimbrar;
import com.mef.erp.modelo.entidad.cfdi.DatosPorEmpleado;
import com.mef.erp.modelo.entidad.cfdi.StatusTimbrado;
import com.mef.erp.util.ControlErroresHibernate;
import com.mef.erp.util.HibernateUtil;
import com.mef.funciones.ClavesParametrosModulos;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import org.hibernate.HibernateException;
import org.hibernate.Query;

public class CFDIEmpleadoDAO extends GenericHibernateDAO<CFDIEmpleado, Long>
        implements CFDIEmpleadoDAOIF {

    private final StringBuilder concatena = new StringBuilder(200);
    private final String msgError = concatena.append("ServerERP").append(".MSERR_C_").append(this.getClass().getName()).append(".").toString();
    private Boolean manejaPagosPorHora = null;
    private Boolean manejaPagoDiasNaturales = null;
    private ManejoHorasPor manejoHorasPor = null;
    private ManejoSalarioDiario manejoSalarioDiario = null;
    private StringBuilder strQuery = new StringBuilder();

    public Mensaje agregar(CFDIEmpleado entity, String uuidCxn) {
        CFDIEmpleado cfdiEmpleado;
        try {
            inicializaVariableMensaje();
            setSession(HibernateUtil.currentSession(uuidCxn));
            getSession().beginTransaction();
            cfdiEmpleado = makePersistent(entity);
            mensajeResultado.setResultado(cfdiEmpleado);
            mensajeResultado.setNoError(0);
            mensajeResultado.setError("");
            getSession().getTransaction().commit();
        } catch (HibernateException ex) {
            System.err.println(concatena.delete(0, concatena.length()).append(msgError).append("agregar()1_Error: ").append(ex));
            try {
                if (getSession().getTransaction().isActive()) {
                    getSession().getTransaction().rollback();
                }
                mensajeResultado.setNoError(ControlErroresHibernate.buscaNoErrorPorExcepcion(ex.getClass()));
                mensajeResultado.setError(ex.getLocalizedMessage());
            } catch (HibernateException exc) {  //cuando marca error al hacer rollback pro ejemplo se cae conexion servidor
                mensajeResultado.setNoError(ControlErroresHibernate.buscaNoErrorPorExcepcion(exc.getClass()));
                mensajeResultado.setError(exc.getLocalizedMessage());
            }
            mensajeResultado.setResultado(null);
        }
        return mensajeResultado;
    }

    public Mensaje actualizar(CFDIEmpleado entity, String uuidCxn) {
        try {
            inicializaVariableMensaje();
            setSession(HibernateUtil.currentSession(uuidCxn));
            getSession().beginTransaction();
            makePersistent(entity);
            mensajeResultado.setResultado(true);
            mensajeResultado.setNoError(0);
            mensajeResultado.setError("");
            getSession().getTransaction().commit();

        } catch (HibernateException ex) {
            System.err.println(concatena.delete(0, concatena.length()).append(msgError).append("actualizar()1_Error: ").append(ex));
            try {
                if (getSession().getTransaction().isActive()) {
                    getSession().getTransaction().rollback();
                }
                mensajeResultado.setNoError(ControlErroresHibernate.buscaNoErrorPorExcepcion(ex.getClass()));
                mensajeResultado.setError(ex.getLocalizedMessage());
            } catch (HibernateException exc) {  //cuando marca error al hacer rollback pro ejemplo se cae conexion servidor
                mensajeResultado.setNoError(ControlErroresHibernate.buscaNoErrorPorExcepcion(exc.getClass()));
                mensajeResultado.setError(exc.getLocalizedMessage());
            }
            mensajeResultado.setResultado(null);
        }
        return mensajeResultado;
    }

    public Mensaje eliminar(CFDIEmpleado entity, String uuidCxn) {
        try {
            inicializaVariableMensaje();
            setSession(HibernateUtil.currentSession(uuidCxn));
            getSession().beginTransaction();
            makeTransient(entity);
            mensajeResultado.setResultado(true);
            mensajeResultado.setNoError(0);
            mensajeResultado.setError("");
            getSession().getTransaction().commit();
        } catch (HibernateException ex) {
            System.err.println(concatena.delete(0, concatena.length()).append(msgError).append("eliminar()1_Error: ").append(ex));
            try {
                if (getSession().getTransaction().isActive()) {
                    getSession().getTransaction().rollback();
                }
                mensajeResultado.setNoError(ControlErroresHibernate.buscaNoErrorPorExcepcion(ex.getClass()));
                mensajeResultado.setError(ex.getLocalizedMessage());
            } catch (HibernateException exc) {  //cuando marca error al hacer rollback pro ejemplo se cae conexion servidor
                mensajeResultado.setNoError(ControlErroresHibernate.buscaNoErrorPorExcepcion(exc.getClass()));
                mensajeResultado.setError(exc.getLocalizedMessage());
            }
            mensajeResultado.setResultado(null);
        }
        return mensajeResultado;
    }

    public Mensaje getCFDIEmpleadoAll(String uuidCxn) {
        List<CFDIEmpleado> values;
        try {
            inicializaVariableMensaje();
            setSession(HibernateUtil.currentSession(uuidCxn));
            getSession().beginTransaction();
            q = getSession().createQuery("from CFDIEmpleado c");
            values = ((List<CFDIEmpleado>) q.list());
            mensajeResultado.setResultado(values);
            mensajeResultado.setNoError(0);
            mensajeResultado.setError("");
            getSession().getTransaction().commit();
        } catch (HibernateException ex) {
            System.err.println(concatena.delete(0, concatena.length()).append(msgError).append("getCFDIEmpleadoAll()1_Error: ").append(ex));
            try {
                if (getSession().getTransaction().isActive()) {
                    getSession().getTransaction().rollback();
                }
                mensajeResultado.setNoError(ControlErroresHibernate.buscaNoErrorPorExcepcion(ex.getClass()));
                mensajeResultado.setError(ex.getLocalizedMessage());
            } catch (HibernateException exc) {  //cuando marca error al hacer rollback pro ejemplo se cae conexion servidor
                mensajeResultado.setNoError(ControlErroresHibernate.buscaNoErrorPorExcepcion(exc.getClass()));
                mensajeResultado.setError(exc.getLocalizedMessage());
            }
            mensajeResultado.setResultado(null);
        }
        return mensajeResultado;
    }

    public Mensaje getCFDIEmpleadoPorFiltro(String claveRazonSocial, String claveTipoNomina, String claveTipoCorrida, Long idPeriodoNomina, StatusTimbrado statusTimbre, Object[] rangoEmpleados, String uuidCxn) {
        List<CFDIEmpleado> values;
        try {
            strQuery = new StringBuilder();
            inicializaVariableMensaje();
            setSession(HibernateUtil.currentSession(uuidCxn));
            getSession().beginTransaction();
            strQuery.append("from CFDIEmpleado o where o.razonesSociales.clave = :claveRazonSocial ");
            strQuery.append("and o.tipoNomina.clave = :claveTipoNomina and o.tipoCorrida.clave = :claveTipoCorrida ");
            strQuery.append("and o.periodosNomina.id = :idPeriodoNomina ");
            strQuery.append("and o.plazaPorEmpleadoMov.plazasPorEmpleado.empleados.clave IN (:rangoEmpleados) ");
            if (statusTimbre != null) {
                strQuery.append("and o.cfdiRecibo.statusTimbrado = :statusTimbre ");
            }
            q = getSession().createQuery(strQuery.toString());
            q.setParameter("claveRazonSocial", claveRazonSocial);
            q.setParameter("claveTipoNomina", claveTipoNomina);
            q.setParameter("claveTipoCorrida", claveTipoCorrida);
            q.setParameter("idPeriodoNomina", idPeriodoNomina);
            q.setParameterList("rangoEmpleados", rangoEmpleados);
            if (statusTimbre != null) {
                q.setParameter("statusTimbre", statusTimbre);
            }
            values = ((List<CFDIEmpleado>) q.list());
            mensajeResultado.setResultado(values);
            mensajeResultado.setNoError(0);
            mensajeResultado.setError("");
            getSession().getTransaction().commit();
        } catch (HibernateException ex) {
            System.err.println(concatena.delete(0, concatena.length()).append(msgError).append("getCFDIEmpleadoPorFiltro()1_Error: ").append(ex));
            try {
                if (getSession().getTransaction().isActive()) {
                    getSession().getTransaction().rollback();
                }
                mensajeResultado.setNoError(ControlErroresHibernate.buscaNoErrorPorExcepcion(ex.getClass()));
                mensajeResultado.setError(ex.getLocalizedMessage());
            } catch (HibernateException exc) {  //cuando marca error al hacer rollback pro ejemplo se cae conexion servidor
                mensajeResultado.setNoError(ControlErroresHibernate.buscaNoErrorPorExcepcion(exc.getClass()));
                mensajeResultado.setError(exc.getLocalizedMessage());
            }
            mensajeResultado.setResultado(null);
        }
        return mensajeResultado;
    }

    public Mensaje getCFDIEmpleadoStatusErrorOEnProceso(String claveRazonSocial, String claveTipoNomina, String claveTipoCorrida, Long idPeriodoNomina, Object[] rangoEmpleados, String uuidCxn) {
        List<CFDIEmpleado> values;
        try {
            inicializaVariableMensaje();
            setSession(HibernateUtil.currentSession(uuidCxn));
            getSession().beginTransaction();
            strQuery = new StringBuilder();
            strQuery.append("select o from CFDIEmpleado o LEFT OUTER JOIN o.cfdiRecibo cr ");
            strQuery.append("where o.razonesSociales.clave = :claveRazonSocial ");
            strQuery.append("and o.tipoNomina.clave = :claveTipoNomina ");
            strQuery.append("and o.tipoCorrida.clave = :claveTipoCorrida ");
            strQuery.append("and o.periodosNomina.id = :idPeriodoNomina ");
            strQuery.append("and o.plazaPorEmpleadoMov.plazasPorEmpleado.empleados.clave IN (:rangoEmpleados) ");
            strQuery.append("AND (cr.statusTimbrado = :statusError OR cr.statusTimbrado = :statusEnProceso)");
            q = getSession().createQuery(strQuery.toString());
            q.setParameter("claveRazonSocial", claveRazonSocial);
            q.setParameter("claveTipoNomina", claveTipoNomina);
            q.setParameter("claveTipoCorrida", claveTipoCorrida);
            q.setParameter("idPeriodoNomina", idPeriodoNomina);
            q.setParameterList("rangoEmpleados", rangoEmpleados);
            q.setParameter("statusError", StatusTimbrado.ERROR);
            q.setParameter("statusEnProceso", StatusTimbrado.EN_PROCESO);
            values = ((List<CFDIEmpleado>) q.list());
            mensajeResultado.setResultado(values);
            mensajeResultado.setNoError(0);
            mensajeResultado.setError("");
            getSession().getTransaction().commit();
        } catch (HibernateException ex) {
            System.err.println(concatena.delete(0, concatena.length()).append(msgError).append("getCFDIEmpleadoStatusErrorOEnProceso()1_Error: ").append(ex));
            try {
                if (getSession().getTransaction().isActive()) {
                    getSession().getTransaction().rollback();
                }
                mensajeResultado.setNoError(ControlErroresHibernate.buscaNoErrorPorExcepcion(ex.getClass()));
                mensajeResultado.setError(ex.getLocalizedMessage());
            } catch (HibernateException exc) {  //cuando marca error al hacer rollback pro ejemplo se cae conexion servidor
                mensajeResultado.setNoError(ControlErroresHibernate.buscaNoErrorPorExcepcion(exc.getClass()));
                mensajeResultado.setError(exc.getLocalizedMessage());
            }
            mensajeResultado.setResultado(null);
        }
        return mensajeResultado;
    }

    public Mensaje getLimpiaConStatusErrorOEnProceso(String claveRazonSocial, String claveTipoNomina, String claveTipoCorrida, Long idPeriodoNomina, Object[] rangoEmpleados, String uuidCxn) {
        try {
            List<CFDIEmpleado> values;
            inicializaVariableMensaje();
            setSession(HibernateUtil.currentSession(uuidCxn));
            getSession().beginTransaction();
            strQuery = new StringBuilder();
            strQuery.append("select o from CFDIEmpleado o LEFT OUTER JOIN o.cfdiRecibo cr ");
            strQuery.append("where o.razonesSociales.clave = :claveRazonSocial ");
            strQuery.append("and o.tipoNomina.clave = :claveTipoNomina ");
            strQuery.append("and o.tipoCorrida.clave = :claveTipoCorrida ");
            strQuery.append("and o.periodosNomina.id = :idPeriodoNomina ");
            strQuery.append("and o.plazaPorEmpleadoMov.plazasPorEmpleado.empleados.clave IN (:rangoEmpleados) ");
            strQuery.append("AND (cr.statusTimbrado = :statusError OR cr.statusTimbrado = :statusEnProceso)");
            q = getSession().createQuery(strQuery.toString());
            q.setParameter("claveRazonSocial", claveRazonSocial);
            q.setParameter("claveTipoNomina", claveTipoNomina);
            q.setParameter("claveTipoCorrida", claveTipoCorrida);
            q.setParameter("idPeriodoNomina", idPeriodoNomina);
            q.setParameterList("rangoEmpleados", rangoEmpleados);
            q.setParameter("statusError", StatusTimbrado.ERROR);
            q.setParameter("statusEnProceso", StatusTimbrado.EN_PROCESO);
            values = ((List<CFDIEmpleado>) q.list());
            values = values == null ? new ArrayList<CFDIEmpleado>() : values;
            int i;
            if (values.size() > 0) {
                for (i = 0; i < values.size(); i++) {
                    getSession().delete(values.get(i));
                }
            }
            mensajeResultado.setResultado(true);
            mensajeResultado.setNoError(0);
            mensajeResultado.setError("");
            getSession().getTransaction().commit();
        } catch (HibernateException ex) {
            System.err.println(concatena.delete(0, concatena.length()).append(msgError).append("getCFDIEmpleadoStatusErrorOEnProceso()1_Error: ").append(ex));
            try {
                if (getSession().getTransaction().isActive()) {
                    getSession().getTransaction().rollback();
                }
                mensajeResultado.setNoError(ControlErroresHibernate.buscaNoErrorPorExcepcion(ex.getClass()));
                mensajeResultado.setError(ex.getLocalizedMessage());
            } catch (HibernateException exc) {  //cuando marca error al hacer rollback pro ejemplo se cae conexion servidor
                mensajeResultado.setNoError(ControlErroresHibernate.buscaNoErrorPorExcepcion(exc.getClass()));
                mensajeResultado.setError(exc.getLocalizedMessage());
            }
            mensajeResultado.setResultado(null);
        }
        return mensajeResultado;
    }

    public Mensaje getCFDIEmpleadoStatusPorFiltro(String claveRazonSocial, String claveTipoNomina, String claveTipoCorrida, Long idPeriodoNomina, List<StatusTimbrado> tiposTimbre, Object[] rangoEmpleados, String uuidCxn) {
        List<Object[]> values;

        try {
            strQuery = new StringBuilder();
            inicializaVariableMensaje();
            setSession(HibernateUtil.currentSession(uuidCxn));
            getSession().beginTransaction();
            strQuery.append("select o.plazaPorEmpleadoMov.plazasPorEmpleado.empleados.clave,o.cfdiRecibo.statusTimbrado ");
            strQuery.append("from CFDIEmpleado o where o.razonesSociales.clave = :claveRazonSocial ");
            strQuery.append("and o.tipoNomina.clave = :claveTipoNomina and o.tipoCorrida.clave = :claveTipoCorrida ");
            strQuery.append("and o.periodosNomina.id = :idPeriodoNomina ");
            strQuery.append("and o.plazaPorEmpleadoMov.plazasPorEmpleado.empleados.clave IN (:rangoEmpleados) ");
            if (tiposTimbre != null) {
                strQuery.append("and o.cfdiRecibo.statusTimbrado IN(:tiposTimbre) ");
            }
            q = getSession().createQuery(strQuery.toString());
            q.setParameter("claveRazonSocial", claveRazonSocial);
            q.setParameter("claveTipoNomina", claveTipoNomina);
            q.setParameter("claveTipoCorrida", claveTipoCorrida);
            q.setParameter("idPeriodoNomina", idPeriodoNomina);
            q.setParameterList("rangoEmpleados", rangoEmpleados);
            q.setParameterList("tiposTimbre", tiposTimbre);
            values = ((List<Object[]>) q.list());
            mensajeResultado.setResultado(values);
            mensajeResultado.setNoError(0);
            mensajeResultado.setError("");
            getSession().getTransaction().commit();
        } catch (HibernateException ex) {
            System.err.println(concatena.delete(0, concatena.length()).append(msgError).append("getCFDIEmpleadoStatusPorFiltro()1_Error: ").append(ex));
            try {
                if (getSession().getTransaction().isActive()) {
                    getSession().getTransaction().rollback();
                }
                mensajeResultado.setNoError(ControlErroresHibernate.buscaNoErrorPorExcepcion(ex.getClass()));
                mensajeResultado.setError(ex.getLocalizedMessage());
            } catch (HibernateException exc) {  //cuando marca error al hacer rollback pro ejemplo se cae conexion servidor
                mensajeResultado.setNoError(ControlErroresHibernate.buscaNoErrorPorExcepcion(exc.getClass()));
                mensajeResultado.setError(exc.getLocalizedMessage());
            }
            mensajeResultado.setResultado(null);
        }
        return mensajeResultado;
    }

    public Mensaje getCFDIEmpleadoStatusPorFiltroPorRangoPeriodosNomina(String claveRazonSocial, String claveTipoNomina, String claveTipoCorrida, Date fechaInicial, Date fechaFinal, List<StatusTimbrado> tiposTimbre, Object[] rangoEmpleados, String uuidCxn) {//JSA01
        List<Object[]> values;
        Boolean existenTimbresActivos = false;
        try {
            strQuery = new StringBuilder();
            inicializaVariableMensaje();
            setSession(HibernateUtil.currentSession(uuidCxn));
            getSession().beginTransaction();
            q = getSession().createQuery("from PeriodosNomina p where p.tipoNomina.clave =:claveTipoNomina and p.tipoCorrida.clave = :claveTipoCorrida and p.fechaFinal >=:fechaInicial and p.fechaInicial <=:fechaFinal ");
            q.setDate("fechaInicial", fechaInicial);
            q.setDate("fechaFinal", fechaFinal);
            q.setParameter("claveTipoNomina", claveTipoNomina);
            q.setParameter("claveTipoCorrida", claveTipoCorrida);

            List<PeriodosNomina> listPeriodosNomina = q.list();
            if (listPeriodosNomina == null) {
                listPeriodosNomina = new ArrayList<PeriodosNomina>();
            }
            if (!listPeriodosNomina.isEmpty()) {
                Long[] listId = new Long[listPeriodosNomina.size()];
                for (int i = 0; i < listPeriodosNomina.size(); i++) {
                    listId[i] = listPeriodosNomina.get(i).getId();
                }
                strQuery.delete(0, strQuery.length()).append("select o.plazaPorEmpleadoMov.plazasPorEmpleado.empleados.clave,o.cfdiRecibo.statusTimbrado ");
                strQuery.append("from CFDIEmpleado o where o.razonesSociales.clave = :claveRazonSocial ");
                strQuery.append("and o.tipoNomina.clave = :claveTipoNomina and o.tipoCorrida.clave = :claveTipoCorrida ");
                strQuery.append("and o.periodosNomina.id IN (:idsPeriodoNomina) ");
                strQuery.append("and o.plazaPorEmpleadoMov.plazasPorEmpleado.empleados.clave IN (:rangoEmpleados) ");
                if (tiposTimbre != null) {
                    strQuery.append("and o.cfdiRecibo.statusTimbrado IN (:tiposTimbre) ");
                }
                ///////System.out.println("query " + strQuery.toString());
                q = getSession().createQuery(strQuery.toString());
                q.setParameter("claveRazonSocial", claveRazonSocial);
                q.setParameter("claveTipoNomina", claveTipoNomina);
                q.setParameter("claveTipoCorrida", claveTipoCorrida);
                q.setParameterList("idsPeriodoNomina", listId);
                q.setParameterList("rangoEmpleados", rangoEmpleados);
                q.setParameterList("tiposTimbre", tiposTimbre);
                values = ((List<Object[]>) q.list());
                if (values == null) {
                    values = new ArrayList<Object[]>();
                }
                if (!values.isEmpty()) {
                    if (values.get(0)[1] == StatusTimbrado.TIMBRADO) {
                        existenTimbresActivos = true;
                    }
                }
            }
            mensajeResultado.setResultado(existenTimbresActivos);
            mensajeResultado.setNoError(0);
            mensajeResultado.setError("");
            getSession().getTransaction().commit();
        } catch (HibernateException ex) {
            System.err.println(concatena.delete(0, concatena.length()).append(msgError).append("getCFDIEmpleadoStatusPorFiltro()1_Error: ").append(ex));
            try {
                if (getSession().getTransaction().isActive()) {
                    getSession().getTransaction().rollback();
                }
                mensajeResultado.setNoError(ControlErroresHibernate.buscaNoErrorPorExcepcion(ex.getClass()));
                mensajeResultado.setError(ex.getLocalizedMessage());
            } catch (HibernateException exc) {  //cuando marca error al hacer rollback pro ejemplo se cae conexion servidor
                mensajeResultado.setNoError(ControlErroresHibernate.buscaNoErrorPorExcepcion(exc.getClass()));
                mensajeResultado.setError(exc.getLocalizedMessage());
            }
            mensajeResultado.setResultado(null);
        }
        return mensajeResultado;
    }

    public Mensaje consultaPorRangos(Integer inicio, Integer rango, String uuidCxn) {
        List<CFDIEmpleado> listEsp;
        try {
            inicializaVariableMensaje();
            setSession(HibernateUtil.currentSession(uuidCxn));
            getSession().beginTransaction();
            listEsp = ((List<CFDIEmpleado>) consultaPorRangos(inicio, rango, null, null));
            mensajeResultado.setResultado(listEsp);
            mensajeResultado.setNoError(0);
            mensajeResultado.setError("");
            getSession().getTransaction().commit();
        } catch (HibernateException ex) {
            System.err.println(concatena.delete(0, concatena.length()).append(msgError).append("consultaPorRangos()1_Error: ").append(ex));
            try {
                if (getSession().getTransaction().isActive()) {
                    getSession().getTransaction().rollback();
                }
                mensajeResultado.setNoError(ControlErroresHibernate.buscaNoErrorPorExcepcion(ex.getClass()));
                mensajeResultado.setError(ex.getLocalizedMessage());
            } catch (HibernateException exc) {  //cuando marca error al hacer rollback pro ejemplo se cae conexion servidor
                mensajeResultado.setNoError(ControlErroresHibernate.buscaNoErrorPorExcepcion(exc.getClass()));
                mensajeResultado.setError(exc.getLocalizedMessage());
            }
            mensajeResultado.setResultado(null);
        }
        return mensajeResultado;
    }

    public Mensaje getCFDIEmpleadoTimbrados(Long[] idsCFDIEmpleado, String uuidCxn) {
        List<CFDIEmpleado> listEsp;
        try {
            inicializaVariableMensaje();
            setSession(HibernateUtil.currentSession(uuidCxn));
            getSession().beginTransaction();
            strQuery = new StringBuilder();
            strQuery.append("SELECT o FROM CFDIEmpleado o LEFT OUTER JOIN o.cfdiRecibo cr ");
            strQuery.append("WHERE o.id IN (:idsCFDIEmpleado) AND (cr.statusTimbrado = :statusTimbrado)");
            q = getSession().createQuery(strQuery.toString());
            q.setParameterList("idsCFDIEmpleado", idsCFDIEmpleado);
            q.setParameter("statusTimbrado", StatusTimbrado.TIMBRADO);
            listEsp = ((List<CFDIEmpleado>) q.list());
            mensajeResultado.setResultado(listEsp);
            mensajeResultado.setNoError(0);
            mensajeResultado.setError("");
            getSession().getTransaction().commit();
        } catch (HibernateException ex) {
            System.err.println(concatena.delete(0, concatena.length()).append(msgError).append("consultaPorRangos()1_Error: ").append(ex));
            try {
                if (getSession().getTransaction().isActive()) {
                    getSession().getTransaction().rollback();
                }
                mensajeResultado.setNoError(ControlErroresHibernate.buscaNoErrorPorExcepcion(ex.getClass()));
                mensajeResultado.setError(ex.getLocalizedMessage());
            } catch (HibernateException exc) {  //cuando marca error al hacer rollback pro ejemplo se cae conexion servidor
                mensajeResultado.setNoError(ControlErroresHibernate.buscaNoErrorPorExcepcion(exc.getClass()));
                mensajeResultado.setError(exc.getLocalizedMessage());
            }
            mensajeResultado.setResultado(null);
        }
        return mensajeResultado;
    }

    public Mensaje existeDato(String campo, Object valor, String uuidCxn) {
        Boolean existe;
        try {
            inicializaVariableMensaje();
            setSession(HibernateUtil.currentSession(uuidCxn));
            getSession().beginTransaction();
            existe = existeDato(CFDIEmpleado.class.getSimpleName(), campo, valor);
            mensajeResultado.setResultado(existe);
            mensajeResultado.setNoError(0);
            mensajeResultado.setError("");
            getSession().getTransaction().commit();
        } catch (HibernateException ex) {
            System.err.println(concatena.delete(0, concatena.length()).append(msgError).append("existeDato()1_Error: ").append(ex));
            try {
                if (getSession().getTransaction().isActive()) {
                    getSession().getTransaction().rollback();
                }
                mensajeResultado.setNoError(ControlErroresHibernate.buscaNoErrorPorExcepcion(ex.getClass()));
                mensajeResultado.setError(ex.getLocalizedMessage());
            } catch (HibernateException exc) {  //cuando marca error al hacer rollback pro ejemplo se cae conexion servidor
                mensajeResultado.setNoError(ControlErroresHibernate.buscaNoErrorPorExcepcion(exc.getClass()));
                mensajeResultado.setError(exc.getLocalizedMessage());
            }
            mensajeResultado.setResultado(null);
        }
        return mensajeResultado;
    }

    public Mensaje saveDeleteCFDIEmpleado(List<CFDIEmpleado> entitysCambios, Object[] idEliminar, int rango, String uuidCxn) {
        List<CFDIEmpleado> listEsp = new ArrayList<CFDIEmpleado>();
        int i;
        try {
            inicializaVariableMensaje();
            setSession(HibernateUtil.currentSession(uuidCxn));
            getSession().beginTransaction();
            if (idEliminar != null) {
                deleteListQuery(CFDIEmpleado.class.getSimpleName(), "id", idEliminar);
                clear();
            }
            entitysCambios = (entitysCambios == null ? new ArrayList<CFDIEmpleado>() : entitysCambios);
            if (!entitysCambios.isEmpty()) {
                for (i = 0; i < entitysCambios.size(); i++) {
                    if (entitysCambios.get(i).getId() == null) {
                        makePersistent(entitysCambios.get(i));
                    } else {
                        makePersistent(entitysCambios.get(i));
                    }
                    listEsp.add(entitysCambios.get(i));
                    if (i % rango == 0 & i > 0) {
                        flush();
                        clear();
                    }
                }
            }
            mensajeResultado.setResultado(listEsp);
            mensajeResultado.setNoError(0);
            mensajeResultado.setError("");
            getSession().getTransaction().commit();
        } catch (HibernateException ex) {
            System.err.println(concatena.delete(0, concatena.length()).append(msgError).append("saveDeleteCFDIEmpleado()1_Error: ").append(ex));
            try {
                if (getSession().getTransaction().isActive()) {
                    getSession().getTransaction().rollback();
                }
                mensajeResultado.setNoError(ControlErroresHibernate.buscaNoErrorPorExcepcion(ex.getClass()));
                mensajeResultado.setError(ex.getLocalizedMessage());
            } catch (HibernateException exc) {  //cuando marca error al hacer rollback pro ejemplo se cae conexion servidor
                mensajeResultado.setNoError(ControlErroresHibernate.buscaNoErrorPorExcepcion(exc.getClass()));
                mensajeResultado.setError(exc.getLocalizedMessage());
            }
            ////mensajeResultado.setError(concatena.delete(0, concatena.length()).append("No se guardo la informacion, la causa fue por el empleado con clave ").append(entitysCambios.get(i).getPlazaPorEmpleadoMov().getPlazasPorEmpleado().getEmpleados().getClave()).append(" ").append(msgError).append("saveDeleteCFDIEmpleado()1_Error: ").append(ex).toString());
            mensajeResultado.setResultado(listEsp);
        }
        return mensajeResultado;
    }

    public Mensaje buscaCFDIEmpleadosFiltrado(List<Object> valoresDeFiltrado, String uuidCxn) {
        List<CFDIEmpleado> cFDIEmpleados = new ArrayList<CFDIEmpleado>();
        valoresDeFiltrado = valoresDeFiltrado == null ? new ArrayList<Object>() : valoresDeFiltrado;
        /**
         * ******************************Carga datos para
         * filtrado******************************************************
         */
        strQuery = new StringBuilder("SELECT cem FROM ").append(CFDIEmpleado.class.getSimpleName()).append(" cem LEFT OUTER JOIN cem.razonesSociales rs LEFT OUTER JOIN cem.plazaPorEmpleadoMov ppm LEFT OUTER JOIN ppm.plazasPorEmpleado pp LEFT OUTER JOIN pp.empleados em ").append(" ");
        StringBuilder strQueryWhere = new StringBuilder("");
        int i;
        List<String> camposWhere = new ArrayList<String>();
        List<Object> valoresWhere = new ArrayList<Object>();
        boolean empleadoInicio = true;
//////        strQueryWhere.append("(cfdiR.statusTimbrado = :statusProceso OR cfdiR.statusTimbrado = :statusError) AND").append(" ");
//////        camposWhere.add("statusProceso");
//////        camposWhere.add("statusError");
//////        valoresWhere.add(StatusTimbrado.EN_PROCESO);
//////        valoresWhere.add(StatusTimbrado.ERROR);
        mensajeResultado.setNoError(0);
        mensajeResultado.setError("");
        mensajeResultado.setResultado(cFDIEmpleados);
        if (valoresDeFiltrado.size() > 0) {
            Empleados empIni = null;
            Empleados empFin = null;
            try {
                List<StatusTimbrado> listStatusTimbrado = new ArrayList<StatusTimbrado>();
                for (i = 0; i < valoresDeFiltrado.size(); i++) {
                    if (valoresDeFiltrado.get(i) instanceof RazonesSociales) {
                        strQueryWhere.append(strQueryWhere.length() > 0 ? "AND " : "").append("rs.clave = :claveRazonSocial").append(" ");
                        camposWhere.add("claveRazonSocial");
                        valoresWhere.add(((RazonesSociales) valoresDeFiltrado.get(i)).getClave());
                    } else if (valoresDeFiltrado.get(i) instanceof TipoNomina) {
                        strQuery.append("LEFT OUTER JOIN cem.tipoNomina tn").append(" ");
                        strQueryWhere.append(strQueryWhere.length() > 0 ? "AND " : "").append("tn.clave = :claveTipoNomina").append(" ");
                        camposWhere.add("claveTipoNomina");
                        valoresWhere.add(((TipoNomina) valoresDeFiltrado.get(i)).getClave());
                    } else if (valoresDeFiltrado.get(i) instanceof TipoCorrida) {
                        strQuery.append("LEFT OUTER JOIN cem.tipoCorrida tc").append(" ");
                        strQueryWhere.append(strQueryWhere.length() > 0 ? "AND " : "").append("tc.clave = :claveTipoCorrida").append(" ");
                        camposWhere.add("claveTipoCorrida");
                        valoresWhere.add(((TipoCorrida) valoresDeFiltrado.get(i)).getClave());
                    } else if (valoresDeFiltrado.get(i) instanceof PeriodosNomina) {
                        strQuery.append("LEFT OUTER JOIN cem.periodosNomina pn").append(" ");
                        strQueryWhere.append(strQueryWhere.length() > 0 ? "AND " : "").append("pn.id = :idPeriodo").append(" ");
                        camposWhere.add("idPeriodo");
                        valoresWhere.add(((PeriodosNomina) valoresDeFiltrado.get(i)).getId());
                    } else if (valoresDeFiltrado.get(i) instanceof RegistroPatronal) {
                        strQuery.append("LEFT OUTER JOIN pp.registroPatronal rp").append(" ");
                        strQueryWhere.append(strQueryWhere.length() > 0 ? "AND " : "").append("rp.clave = :claveRegPatronal").append(" ");
                        camposWhere.add("claveRegPatronal");
                        valoresWhere.add(((RegistroPatronal) valoresDeFiltrado.get(i)).getClave());
                    } else if (valoresDeFiltrado.get(i) instanceof CentroDeCosto) {
                        strQuery.append("LEFT OUTER JOIN ppm.centroDeCosto cc").append(" ");
                        strQueryWhere.append(strQueryWhere.length() > 0 ? "AND " : "").append("cc.clave = :claveCentroDeCosto").append(" ");
                        camposWhere.add("claveCentroDeCosto");
                        valoresWhere.add(((CentroDeCosto) valoresDeFiltrado.get(i)).getClave());
                    } else if (valoresDeFiltrado.get(i) instanceof Departamentos) {
                        strQuery.append("LEFT OUTER JOIN ppm.departamentos dp").append(" ");
                        strQueryWhere.append(strQueryWhere.length() > 0 ? "AND " : "").append(" dp.clave = :claveDepartamentos").append(" ");
                        camposWhere.add("claveDepartamentos");
                        valoresWhere.add(((Departamentos) valoresDeFiltrado.get(i)).getClave());
                    } else if (valoresDeFiltrado.get(i) instanceof StatusTimbrado) {
                        strQuery.append("LEFT OUTER JOIN cem.cfdiRecibo cfdiR ").append(" ");
                        listStatusTimbrado.add((StatusTimbrado) valoresDeFiltrado.get(i));
                    } else if (valoresDeFiltrado.get(i) instanceof Empleados & empleadoInicio) {
                        empleadoInicio = false;
                        empIni = (Empleados) valoresDeFiltrado.get(i);
                        if (empIni.getId() == null) {
                            empIni = null;
                        }
                    } else if (valoresDeFiltrado.get(i) instanceof Empleados) {
                        empFin = (Empleados) valoresDeFiltrado.get(i);
                        if (empFin.getId() == null) {
                            empFin = null;
                        }
                    }
                }

                if (empIni != null && empFin != null) {
                    if (strQueryWhere.length() > 0) {
                        strQueryWhere.append(" AND ");
                    }
                    strQueryWhere.append("(em.clave >= :claveEmpIni AND em.clave <= :claveEmpFin)").append(" ");
                    camposWhere.add("claveEmpIni");
                    valoresWhere.add(empIni.getClave());
                    camposWhere.add("claveEmpFin");
                    valoresWhere.add(empFin.getClave());
                } else if (empIni != null) {
                    if (strQueryWhere.length() > 0) {
                        strQueryWhere.append(" AND ");
                    }
                    strQueryWhere.append("em.clave >= :claveEmpleado").append(" ");
                    camposWhere.add("claveEmpleado");
                    valoresWhere.add(empIni.getClave());
                } else if (empFin != null) {
                    if (strQueryWhere.length() > 0) {
                        strQueryWhere.append(" AND ");
                    }
                    strQueryWhere.append("em.clave <= :claveEmpleado").append(" ");
                    camposWhere.add("claveEmpleado");
                    valoresWhere.add(empFin.getClave());
                }

                if (listStatusTimbrado.size() > 0) {
                    if (listStatusTimbrado.size() > 1) {
                        strQueryWhere.append(" AND (");
                    } else {
                        strQueryWhere.append(" AND ");
                    }

                    for (i = 0; i < listStatusTimbrado.size(); i++) {
                        strQueryWhere.append("cfdiR.statusTimbrado = :").append("statusTimbre".concat(String.valueOf(i)));
                        if (i < listStatusTimbrado.size() - 1) {
                            strQueryWhere.append(" OR ");
                        }
                        camposWhere.add("statusTimbre".concat(String.valueOf(i)));
                        valoresWhere.add(listStatusTimbrado.get(i));
                    }
                    if (listStatusTimbrado.size() > 1) {
                        strQueryWhere.append(") ");
                    }
                }
                inicializaVariableMensaje();
                setSession(HibernateUtil.currentSession(uuidCxn));
                getSession().beginTransaction();
                strQuery.append("WHERE").append(" ").append(strQueryWhere);
                cFDIEmpleados = (List<CFDIEmpleado>) ejecutaQueryList(strQuery.toString(), camposWhere.toArray(new String[]{}), valoresWhere.toArray(), 0);
                mensajeResultado.setResultado(cFDIEmpleados);
                getSession().getTransaction().commit();
            } catch (HibernateException ex) {
                System.err.println(concatena.delete(0, concatena.length()).append(msgError).append("buscaCFDIEmpleadosFiltrado()1_Error: ").append(ex));
                try {
                    if (getSession().getTransaction().isActive()) {
                        getSession().getTransaction().rollback();
                    }
                    mensajeResultado.setNoError(ControlErroresHibernate.buscaNoErrorPorExcepcion(ex.getClass()));
                    mensajeResultado.setError(ex.getLocalizedMessage());
                } catch (HibernateException exc) {  //cuando marca error al hacer rollback pro ejemplo se cae conexion servidor
                    mensajeResultado.setNoError(ControlErroresHibernate.buscaNoErrorPorExcepcion(exc.getClass()));
                    mensajeResultado.setError(exc.getLocalizedMessage());
                }
                ////mensajeResultado.setError(concatena.delete(0, concatena.length()).append("No se guardo la informacion, la causa fue por el empleado con clave ").append(entitysCambios.get(i).getPlazaPorEmpleadoMov().getPlazasPorEmpleado().getEmpleados().getClave()).append(" ").append(msgError).append("saveDeleteCFDIEmpleado()1_Error: ").append(ex).toString());
                mensajeResultado.setResultado(null);
            }
        }
        return mensajeResultado;
    }

    private List<Object> construyeQueryDatosGlobalesEmpleados(DatosFiltradoEmpleados datosFiltradoEmpleado) {
        List<Object> datosEmpleado;

        StringBuilder select = new StringBuilder();
        StringBuilder from = new StringBuilder();
        StringBuilder where = new StringBuilder();
        StringBuilder orden = new StringBuilder();

        select.append("SELECT DISTINCT  CASE WHEN (empleado IS NULL) THEN '' ELSE CASE WHEN (empleado.clave IS NULL) THEN '' ELSE empleado.clave END END, ppm, ");
        select.append("CASE WHEN (sdi IS NULL) THEN 0.0 ELSE CASE WHEN (sdi.salarioDiarioIntegrado IS NULL) THEN 0.0 ELSE sdi.salarioDiarioIntegrado END END, ");
        select.append("(SELECT CASE WHEN (ing.fechaIngreso IS NULL) THEN cast('1900-01-01' as date) ELSE ing.fechaIngreso END ");
        select.append("FROM IngresosReingresosBajas ing  RIGHT OUTER JOIN ing.empleados emp WHERE emp.id = empleado.id  AND  ing.fechaBaja > :fechaFinal AND ing.fechaIngreso <= :fechaFinal), ");
        select.append("CASE WHEN (periodo IS NULL) THEN '' ELSE CASE WHEN (periodo.detalleConceptoRecibo IS NULL) THEN '' ELSE periodo.detalleConceptoRecibo END END, ");
        select.append("CASE WHEN (corrida IS NULL) THEN '' ELSE CASE WHEN (corrida.detalleConceptoRecibo IS NULL) THEN '' ELSE corrida.detalleConceptoRecibo END END, ");
        select.append("CASE WHEN (nomina IS NULL) THEN '' ELSE CASE WHEN (nomina.detalleConceptoRecibo IS NULL) THEN '' ELSE nomina.detalleConceptoRecibo END END ");

        from.append("FROM PlazasPorEmpleadosMov ppm LEFT OUTER JOIN ppm.plazasPorEmpleado pp LEFT OUTER JOIN pp.empleados empleado, SalariosIntegrados sdi, MovNomConcep mvn ");
        from.append("LEFT OUTER JOIN mvn.periodosNomina periodo LEFT OUTER JOIN mvn.tipoCorrida corrida LEFT OUTER JOIN ppm.tipoNomina nomina LEFT OUTER JOIN pp.razonesSociales razonSocial ");
        from.append("RIGHT OUTER JOIN mvn.empleado mvmEmp RIGHT OUTER JOIN mvn.periodosNomina periodo RIGHT OUTER JOIN sdi.empleados sdiEmp ");
        List<String> camposFiltrado = new ArrayList<String>();
        List<Object> valoresWhere = new ArrayList<Object>();
        where.append("WHERE mvmEmp.id = empleado.id AND sdiEmp.id = empleado.id ");
        where.append("AND empleado.id NOT IN (SELECT CASE WHEN (COUNT(cfdiEmp) = 0) THEN 0 ELSE em.id END FROM CFDIEmpleado cfdiEmp RIGHT OUTER JOIN cfdiEmp.plazaPorEmpleadoMov cfdiPPM LEFT OUTER JOIN cfdiEmp.cfdiRecibo recibo ");
        where.append("LEFT OUTER JOIN cfdiEmp.periodosNomina cfdiPeriodo LEFT OUTER JOIN cfdiPeriodo.tipoCorrida cfdiCorrida LEFT OUTER JOIN cfdiPPM.plazasPorEmpleado cfdiPP LEFT OUTER JOIN cfdiPP.empleados em ");
        where.append("WHERE (recibo.statusTimbrado = :statusTimbre) AND (cfdiPeriodo.fechaInicial BETWEEN :fechaPeriodoInicio AND :fechaPeriodoFinal OR cfdiPeriodo.fechaFinal BETWEEN :fechaPeriodoInicio AND :fechaPeriodoFinal) ");
        if (!datosFiltradoEmpleado.getClaveTipoCorrida().isEmpty()) {
            where.append("AND cfdiCorrida.clave =:tipoCorrida ");
        }
        where.append("AND em.razonesSociales.id = razonSocial.id GROUP By  em.id) ");
        where.append("AND sdi.fecha IN (SELECT MAX(s0.fecha) FROM SalariosIntegrados s0 LEFT OUTER JOIN s0.empleados s0Emp WHERE s0.fecha <= :fechaFinal AND s0Emp.id = empleado.id) ");
        where.append("AND ppm.id IN (SELECT MAX(pem.id) FROM PlazasPorEmpleadosMov pem INNER JOIN pem.plazasPorEmpleado pe INNER JOIN pe.empleados e INNER JOIN pem.tipoNomina nn ");
        where.append("WHERE e.id = empleado.id AND :fechaFinal >= pem.fechaInicial AND pe.fechaFinal > :fechaFinal ");
        camposFiltrado.add("statusTimbre");
        valoresWhere.add(StatusTimbrado.TIMBRADO);
        camposFiltrado.add("fechaFinal");
        valoresWhere.add(datosFiltradoEmpleado.getFechaFin());

        if (!datosFiltradoEmpleado.getClaveTipoNomina().isEmpty()) {
            where.append("AND nn.clave = :tipoNomina) "); ///para busqueda maximo id de plaza empleado mov
            where.append("AND nomina.clave = :tipoNomina ");
            camposFiltrado.add("tipoNomina");
            valoresWhere.add(datosFiltradoEmpleado.getClaveTipoNomina());
        } else {
            where.append(") "); //cierra la busqusqueda max plaza por empleado id 
        }

        if (!datosFiltradoEmpleado.getClaveRazonSocial().isEmpty()) {
            where.append("AND razonSocial.clave = :razonSocial ");
            camposFiltrado.add("razonSocial");
            valoresWhere.add(datosFiltradoEmpleado.getClaveRazonSocial());
        }

        if (!datosFiltradoEmpleado.getClaveTipoCorrida().isEmpty()) {
            where.append("AND corrida.clave = :tipoCorrida AND periodo.tipoCorrida.clave = :tipoCorrida ");
            camposFiltrado.add("tipoCorrida");
            valoresWhere.add(datosFiltradoEmpleado.getClaveTipoCorrida());
        }
        if (datosFiltradoEmpleado.getFechaFin() != null & datosFiltradoEmpleado.getFechaInicio() != null) {
            where.append("AND (periodo.fechaInicial BETWEEN :fechaPeriodoInicio AND :fechaPeriodoFinal OR periodo.fechaFinal BETWEEN :fechaPeriodoInicio AND :fechaPeriodoFinal) ");
            camposFiltrado.add("fechaPeriodoInicio");
            valoresWhere.add(datosFiltradoEmpleado.getFechaInicio());
            camposFiltrado.add("fechaPeriodoFinal");
            valoresWhere.add(datosFiltradoEmpleado.getFechaFin());
        }
        if (!datosFiltradoEmpleado.getClaveCentroCosto().isEmpty()) {
            from.append("LEFT OUTER JOIN ppm.centroDeCosto cc ");
            where.append("AND cc.clave = :centroCosto ");
            camposFiltrado.add("centroCosto");
            valoresWhere.add(datosFiltradoEmpleado.getClaveCentroCosto());
        }
        if (!datosFiltradoEmpleado.getClaveDepartamento().isEmpty()) {
            from.append("LEFT OUTER JOIN ppm.departamentos depto ");
            where.append("AND depto.clave = :departamento ");
            camposFiltrado.add("departamento");
            valoresWhere.add(datosFiltradoEmpleado.getClaveDepartamento());
        }
        if (!datosFiltradoEmpleado.getClaveRegistroPatronal().isEmpty()) {
            from.append("LEFT OUTER JOIN pp.registroPatronal rp ");
            where.append("AND rp.clave = :registroPatronal ");
            camposFiltrado.add("registroPatronal");
            valoresWhere.add(datosFiltradoEmpleado.getClaveRegistroPatronal());
        }
        if (!datosFiltradoEmpleado.getClaveInicioEmpleado().isEmpty() && !datosFiltradoEmpleado.getClaveFinEmpleado().isEmpty()) {
            where.append("AND (empleado.clave >= :claveEmpIni AND empleado.clave <= :claveEmpFin) ");
            camposFiltrado.add("claveEmpIni");
            valoresWhere.add(datosFiltradoEmpleado.getClaveInicioEmpleado());
            camposFiltrado.add("claveEmpFin");
            valoresWhere.add(datosFiltradoEmpleado.getClaveFinEmpleado());
        } else if (!datosFiltradoEmpleado.getClaveInicioEmpleado().isEmpty()) {
            camposFiltrado.add("claveEmpIni");
            valoresWhere.add(datosFiltradoEmpleado.getClaveInicioEmpleado());
            where.append("AND empleado.clave >= :claveEmpIni ");
        } else if (!datosFiltradoEmpleado.getClaveFinEmpleado().isEmpty()) {
            camposFiltrado.add("claveEmpIni");
            valoresWhere.add(datosFiltradoEmpleado.getClaveFinEmpleado());
            where.append("AND empleado.clave <= :claveEmpIni ");
        }
        orden.append("ORDER BY CASE WHEN (empleado IS NULL) THEN '' ELSE CASE WHEN (empleado.clave IS NULL) THEN '' ELSE empleado.clave END END");
        select.append(from).append(where).append(orden);
        datosEmpleado = (List<Object>) ejecutaQueryList(select.toString(), camposFiltrado.toArray(new String[]{}), valoresWhere.toArray(), 0);
        return datosEmpleado;
    }

    private List<Object> construyeQueryDatosGlobalesMovNom(DatosFiltradoMovNom datosFiltradoMovNom, DatosFiltradoEmpleados datosFiltradoEmpleado) {
        List<Object> datosMovimientos;

        StringBuilder select = new StringBuilder();
        StringBuilder from = new StringBuilder();
        StringBuilder where = new StringBuilder();
        StringBuilder orden = new StringBuilder();

        select.append("SELECT DISTINCT  CASE WHEN (x1 IS NULL) THEN '' ELSE CASE WHEN (x1.clave IS NULL) THEN '' ELSE x1.clave END END, x0, CASE WHEN (x6 IS NULL) THEN '' ELSE CASE WHEN (x6.clave IS NULL) THEN '' ELSE x6.clave END END ");

        from.append("FROM MovNomConcep x0 LEFT OUTER JOIN x0.empleado x1 LEFT OUTER JOIN x0.razonesSociales x2 LEFT OUTER JOIN x0.tipoNomina x3 LEFT OUTER JOIN x0.tipoCorrida x4 ");
        from.append("LEFT OUTER JOIN x0.periodosNomina x5 LEFT OUTER JOIN x0.concepNomDefi x6  RIGHT OUTER JOIN x0.empleado x1  RIGHT OUTER JOIN x0.periodosNomina x5 ");

        from.append(", PlazasPorEmpleadosMov ppm LEFT OUTER JOIN ppm.plazasPorEmpleado pp LEFT OUTER JOIN pp.empleados empleado, SalariosIntegrados sdi ");
        from.append("LEFT OUTER JOIN ppm.tipoNomina nomina LEFT OUTER JOIN pp.razonesSociales razonSocial RIGHT OUTER JOIN sdi.empleados sdiEmp ");
        List<String> camposFiltrado = new ArrayList<String>();
        List<Object> valoresWhere = new ArrayList<Object>();

        where.append("WHERE x1.id = empleado.id AND sdiEmp.id = empleado.id ");
        where.append("AND x1.id NOT IN (SELECT CASE WHEN (COUNT(cfdiEmp) = 0) THEN 0 ELSE em.id END FROM CFDIEmpleado cfdiEmp RIGHT OUTER JOIN cfdiEmp.plazaPorEmpleadoMov cfdiPPM LEFT OUTER JOIN cfdiEmp.cfdiRecibo recibo ");
        where.append("LEFT OUTER JOIN cfdiEmp.periodosNomina cfdiPeriodo LEFT OUTER JOIN cfdiPPM.plazasPorEmpleado cfdiPP LEFT OUTER JOIN cfdiPP.empleados em ");
        where.append("WHERE (recibo.statusTimbrado = :statusTimbre) AND (cfdiPeriodo.fechaInicial BETWEEN :fechaPeriodoInicio AND :fechaPeriodoFinal OR cfdiPeriodo.fechaFinal BETWEEN :fechaPeriodoInicio AND :fechaPeriodoFinal) ");
        if (!datosFiltradoEmpleado.getClaveTipoCorrida().isEmpty()) {
            where.append("AND cfdiPeriodo.tipoCorrida.clave =:tipoCorrida ");
        }
        where.append("AND em.razonesSociales.id = razonSocial.id GROUP By  em.id) ");
        where.append("AND sdi.fecha IN (SELECT MAX(s0.fecha) FROM SalariosIntegrados s0 LEFT OUTER JOIN s0.empleados s0Emp WHERE s0.fecha <= :fechaFinal AND s0Emp.id = empleado.id) ");
        where.append("AND ppm.id IN (SELECT MAX(pem.id) FROM PlazasPorEmpleadosMov pem INNER JOIN pem.plazasPorEmpleado pe INNER JOIN pe.empleados e INNER JOIN pem.tipoNomina nn ");

        where.append("WHERE e.id = empleado.id AND :fechaFinal >= pem.fechaInicial AND pe.fechaFinal > :fechaFinal ");
        camposFiltrado.add("statusTimbre");
        valoresWhere.add(StatusTimbrado.TIMBRADO);
        camposFiltrado.add("fechaFinal");
        valoresWhere.add(datosFiltradoEmpleado.getFechaFin());

        if (!datosFiltradoEmpleado.getClaveTipoNomina().isEmpty()) {
            where.append("AND nn.clave = :tipoNomina) "); ///para busqueda maximo id de plaza empleado mov
            where.append("AND nomina.clave = :tipoNomina ");
            camposFiltrado.add("tipoNomina");
            valoresWhere.add(datosFiltradoEmpleado.getClaveTipoNomina());
        } else {
            where.append(") "); //cierra la busqusqueda max plaza por empleado id 
        }

        if (!datosFiltradoEmpleado.getClaveRazonSocial().isEmpty()) {
            where.append("AND razonSocial.clave = :razonSocial ");
            camposFiltrado.add("razonSocial");
            valoresWhere.add(datosFiltradoEmpleado.getClaveRazonSocial());
        }

        if (!datosFiltradoEmpleado.getClaveCentroCosto().isEmpty()) {
            from.append("LEFT OUTER JOIN ppm.centroDeCosto cc ");
            where.append("AND cc.clave = :centroCosto ");
            camposFiltrado.add("centroCosto");
            valoresWhere.add(datosFiltradoEmpleado.getClaveCentroCosto());
        }
        if (!datosFiltradoEmpleado.getClaveDepartamento().isEmpty()) {
            from.append("LEFT OUTER JOIN ppm.departamentos depto ");
            where.append("AND depto.clave = :departamento ");
            camposFiltrado.add("departamento");
            valoresWhere.add(datosFiltradoEmpleado.getClaveDepartamento());
        }
        if (!datosFiltradoEmpleado.getClaveRegistroPatronal().isEmpty()) {
            from.append("LEFT OUTER JOIN pp.registroPatronal rp ");
            where.append("AND rp.clave = :registroPatronal ");
            camposFiltrado.add("registroPatronal");
            valoresWhere.add(datosFiltradoEmpleado.getClaveRegistroPatronal());
        }
        if (!datosFiltradoEmpleado.getClaveInicioEmpleado().isEmpty() && !datosFiltradoEmpleado.getClaveFinEmpleado().isEmpty()) {
            where.append("AND (empleado.clave >= :claveEmpIni AND empleado.clave <= :claveEmpFin) ");
            camposFiltrado.add("claveEmpIni");
            valoresWhere.add(datosFiltradoEmpleado.getClaveInicioEmpleado());
            camposFiltrado.add("claveEmpFin");
            valoresWhere.add(datosFiltradoEmpleado.getClaveFinEmpleado());
        } else if (!datosFiltradoEmpleado.getClaveInicioEmpleado().isEmpty()) {
            camposFiltrado.add("claveEmpIni");
            valoresWhere.add(datosFiltradoEmpleado.getClaveInicioEmpleado());
            where.append("AND empleado.clave >= :claveEmpIni ");
        } else if (!datosFiltradoEmpleado.getClaveFinEmpleado().isEmpty()) {
            camposFiltrado.add("claveEmpIni");
            valoresWhere.add(datosFiltradoEmpleado.getClaveFinEmpleado());
            where.append("AND empleado.clave <= :claveEmpIni ");
        }

        where.append("AND (x6.naturaleza  = :percepcion OR x6.naturaleza  = :deduccion) ");
        camposFiltrado.add("percepcion");
        valoresWhere.add(Naturaleza.PERCEPCION);
        camposFiltrado.add("deduccion");
        valoresWhere.add(Naturaleza.DEDUCCION);
        if (!datosFiltradoMovNom.getClaveRazonSocial().isEmpty()) {
            where.append("AND x2.clave = :razonSocial ");
            camposFiltrado.add("razonSocial");
            valoresWhere.add(datosFiltradoMovNom.getClaveRazonSocial());
        }
        if (!datosFiltradoMovNom.getClaveTipoNomina().isEmpty()) {
            where.append("AND x3.clave = :tipoNomina ");
            camposFiltrado.add("tipoNomina");
            valoresWhere.add(datosFiltradoMovNom.getClaveTipoNomina());
        }
        if (!datosFiltradoMovNom.getClaveTipoCorrida().isEmpty()) {
            where.append("AND x4.clave = :tipoCorrida ");
            camposFiltrado.add("tipoCorrida");
            valoresWhere.add(datosFiltradoMovNom.getClaveTipoCorrida());
        }
        if (datosFiltradoMovNom.getFechaFin() != null & datosFiltradoMovNom.getFechaInicio() != null) {
            where.append("AND (x5.fechaInicial BETWEEN :fechaPeriodoInicio AND  :fechaPeriodoFinal OR x5.fechaFinal BETWEEN :fechaPeriodoInicio AND  :fechaPeriodoFinal) ");
            camposFiltrado.add("fechaPeriodoInicio");
            valoresWhere.add(datosFiltradoMovNom.getFechaInicio());
            camposFiltrado.add("fechaPeriodoFinal");
            valoresWhere.add(datosFiltradoMovNom.getFechaFin());
            if (!datosFiltradoEmpleado.getClaveTipoCorrida().isEmpty()) {
                where.append("AND x5.tipoCorrida.clave = :tipoCorrida ");
            }
        }
        if (!datosFiltradoMovNom.getClaveCentroCosto().isEmpty()) {
            from.append("LEFT OUTER JOIN x0.centroDeCosto x8 ");
            where.append("AND x8.clave = :centroDeCosto ");
            camposFiltrado.add("centroDeCosto");
            valoresWhere.add(datosFiltradoMovNom.getClaveCentroCosto());
        }

//        if (datosFiltradoMovNom.getClavesEmpleados() != null) {
//            if (datosFiltradoMovNom.getClavesEmpleados().length > 0) {
//                where.append("AND x1.clave IN (:claveEmpleados) ");
//                camposFiltrado.add("claveEmpleados");
//                valoresWhere.add(datosFiltradoMovNom.getClavesEmpleados());
//            }
//        }
        orden.append("ORDER BY CASE WHEN (x1 IS NULL) THEN '' ELSE CASE WHEN (x1.clave IS NULL) THEN '' ELSE x1.clave END END, CASE WHEN (x6 IS NULL) THEN '' ELSE CASE WHEN (x6.clave IS NULL) THEN '' ELSE x6.clave END END");
        select.append(from).append(where).append(orden);
        datosMovimientos = (List<Object>) ejecutaQueryList(select.toString(), camposFiltrado.toArray(new String[]{}), valoresWhere.toArray(), 0);
        return datosMovimientos;
    }

    private List<Object> construyeQueryDatosGlobalesAsistencias(DatosFiltradoAsistencias datosFiltradoAsistencias, DatosFiltradoEmpleados datosFiltradoEmpleado) {
        List<Object> datosAsistencias;

        StringBuilder select = new StringBuilder();
        StringBuilder from = new StringBuilder();
        StringBuilder where = new StringBuilder();
        StringBuilder orden = new StringBuilder();

        select.append("SELECT DISTINCT CASE WHEN (x1 IS NULL) THEN '' ELSE CASE WHEN (x1.clave IS NULL) THEN '' ELSE x1.clave END END, x0 ");

        from.append("FROM Asistencias x0 LEFT OUTER JOIN x0.empleados x1 LEFT OUTER JOIN x0.razonesSociales x2 LEFT OUTER JOIN x0.tipoNomina x3 LEFT OUTER JOIN x0.excepciones x4 ");
        from.append("LEFT OUTER JOIN x0.periodosNomina x5 ");
        from.append(", PlazasPorEmpleadosMov ppm LEFT OUTER JOIN ppm.plazasPorEmpleado pp LEFT OUTER JOIN pp.empleados empleado, SalariosIntegrados sdi ");
        from.append("LEFT OUTER JOIN ppm.tipoNomina nomina LEFT OUTER JOIN pp.razonesSociales razonSocial RIGHT OUTER JOIN sdi.empleados sdiEmp ");

        List<String> camposFiltrado = new ArrayList<String>();
        List<Object> valoresWhere = new ArrayList<Object>();

        where.append("WHERE x1.id = empleado.id AND sdiEmp.id = empleado.id ");
        where.append("AND x1.id NOT IN (SELECT CASE WHEN (COUNT(cfdiEmp) = 0) THEN 0 ELSE em.id END FROM CFDIEmpleado cfdiEmp RIGHT OUTER JOIN cfdiEmp.plazaPorEmpleadoMov cfdiPPM LEFT OUTER JOIN cfdiEmp.cfdiRecibo recibo ");
        where.append("LEFT OUTER JOIN cfdiEmp.periodosNomina cfdiPeriodo LEFT OUTER JOIN cfdiPPM.plazasPorEmpleado cfdiPP LEFT OUTER JOIN cfdiPP.empleados em ");
        where.append("WHERE (recibo.statusTimbrado = :statusTimbre) AND (cfdiPeriodo.fechaInicial BETWEEN :fechaPeriodoInicio AND :fechaPeriodoFinal OR cfdiPeriodo.fechaFinal BETWEEN :fechaPeriodoInicio AND :fechaPeriodoFinal) ");
        if (!datosFiltradoEmpleado.getClaveTipoCorrida().isEmpty()) {
            where.append("AND cfdiPeriodo.tipoCorrida.clave =:tipoCorrida ");
        }
        where.append("AND em.razonesSociales.id = razonSocial.id GROUP By  em.id) ");
        where.append("AND sdi.fecha IN (SELECT MAX(s0.fecha) FROM SalariosIntegrados s0 LEFT OUTER JOIN s0.empleados s0Emp WHERE s0.fecha <= :fechaFinal AND s0Emp.id = empleado.id) ");
        where.append("AND ppm.id IN (SELECT MAX(pem.id) FROM PlazasPorEmpleadosMov pem INNER JOIN pem.plazasPorEmpleado pe INNER JOIN pe.empleados e INNER JOIN pem.tipoNomina nn ");

        where.append("WHERE e.id = empleado.id AND :fechaFinal >= pem.fechaInicial AND pe.fechaFinal > :fechaFinal ");
        camposFiltrado.add("statusTimbre");
        valoresWhere.add(StatusTimbrado.TIMBRADO);
        camposFiltrado.add("fechaFinal");
        valoresWhere.add(datosFiltradoEmpleado.getFechaFin());

        if (!datosFiltradoEmpleado.getClaveTipoNomina().isEmpty()) {
            where.append("AND nn.clave = :tipoNomina) "); ///para busqueda maximo id de plaza empleado mov
            where.append("AND nomina.clave = :tipoNomina ");
            camposFiltrado.add("tipoNomina");
            valoresWhere.add(datosFiltradoEmpleado.getClaveTipoNomina());
        } else {
            where.append(") "); //cierra la busqusqueda max plaza por empleado id 
        }

        if (!datosFiltradoEmpleado.getClaveRazonSocial().isEmpty()) {
            where.append("AND razonSocial.clave = :razonSocial ");
            camposFiltrado.add("razonSocial");
            valoresWhere.add(datosFiltradoEmpleado.getClaveRazonSocial());
        }

        if (!datosFiltradoEmpleado.getClaveCentroCosto().isEmpty()) {
            from.append("LEFT OUTER JOIN ppm.centroDeCosto cc ");
            where.append("AND cc.clave = :centroCosto ");
            camposFiltrado.add("centroCosto");
            valoresWhere.add(datosFiltradoEmpleado.getClaveCentroCosto());
        }
        if (!datosFiltradoEmpleado.getClaveDepartamento().isEmpty()) {
            from.append("LEFT OUTER JOIN ppm.departamentos depto ");
            where.append("AND depto.clave = :departamento ");
            camposFiltrado.add("departamento");
            valoresWhere.add(datosFiltradoEmpleado.getClaveDepartamento());
        }
        if (!datosFiltradoEmpleado.getClaveRegistroPatronal().isEmpty()) {
            from.append("LEFT OUTER JOIN pp.registroPatronal rp ");
            where.append("AND rp.clave = :registroPatronal ");
            camposFiltrado.add("registroPatronal");
            valoresWhere.add(datosFiltradoEmpleado.getClaveRegistroPatronal());
        }
        if (!datosFiltradoEmpleado.getClaveInicioEmpleado().isEmpty() && !datosFiltradoEmpleado.getClaveFinEmpleado().isEmpty()) {
            where.append("AND (empleado.clave >= :claveEmpIni AND empleado.clave <= :claveEmpFin) ");
            camposFiltrado.add("claveEmpIni");
            valoresWhere.add(datosFiltradoEmpleado.getClaveInicioEmpleado());
            camposFiltrado.add("claveEmpFin");
            valoresWhere.add(datosFiltradoEmpleado.getClaveFinEmpleado());
        } else if (!datosFiltradoEmpleado.getClaveInicioEmpleado().isEmpty()) {
            camposFiltrado.add("claveEmpIni");
            valoresWhere.add(datosFiltradoEmpleado.getClaveInicioEmpleado());
            where.append("AND empleado.clave >= :claveEmpIni ");
        } else if (!datosFiltradoEmpleado.getClaveFinEmpleado().isEmpty()) {
            camposFiltrado.add("claveEmpIni");
            valoresWhere.add(datosFiltradoEmpleado.getClaveFinEmpleado());
            where.append("AND empleado.clave <= :claveEmpIni ");
        }

        where.append(" AND (x4.clave = :exepcionExtraDoble OR x4.clave = :exepcionExtraTriple OR x4.clave = :excepcionAccidente  OR x4.clave  = :excepcionEnfermedad  OR x4.clave  = :excepcionMaternidad) ");
        camposFiltrado.add("exepcionExtraDoble");
        valoresWhere.add(ClavesParametrosModulos.claveExcepcionExtraDoble);
        camposFiltrado.add("exepcionExtraTriple");
        valoresWhere.add(ClavesParametrosModulos.claveExcepcionExtraTriple);
        camposFiltrado.add("excepcionAccidente");
        valoresWhere.add(ClavesParametrosModulos.claveExcepcionIncapacidadPorAccidente);
        camposFiltrado.add("excepcionEnfermedad");
        valoresWhere.add(ClavesParametrosModulos.claveExcepcionIncapacidadPorEnfermedad);
        camposFiltrado.add("excepcionMaternidad");
        valoresWhere.add(ClavesParametrosModulos.claveExcepcionIncapacidadPorMaternidad);
        if (!datosFiltradoAsistencias.getClaveRazonSocial().isEmpty()) {
            where.append("AND x2.clave = :razonSocial ");
            camposFiltrado.add("razonSocial");
            valoresWhere.add(datosFiltradoAsistencias.getClaveRazonSocial());
        }
        if (!datosFiltradoAsistencias.getClaveTipoNomina().isEmpty()) {
            where.append("AND x3.clave = :tipoNomina ");
            camposFiltrado.add("tipoNomina");
            valoresWhere.add(datosFiltradoAsistencias.getClaveTipoNomina());
        }
        if (datosFiltradoAsistencias.getFechaFin() != null & datosFiltradoAsistencias.getFechaInicio() != null) {
            where.append("AND (x5.fechaInicial BETWEEN :fechaPeriodoInicio AND  :fechaPeriodoFinal OR x5.fechaFinal BETWEEN :fechaPeriodoInicio AND  :fechaPeriodoFinal) ");
            camposFiltrado.add("fechaPeriodoInicio");
            valoresWhere.add(datosFiltradoAsistencias.getFechaInicio());
            camposFiltrado.add("fechaPeriodoFinal");
            valoresWhere.add(datosFiltradoAsistencias.getFechaFin());
            if (!datosFiltradoEmpleado.getClaveTipoCorrida().isEmpty()) {
                where.append("AND x5.tipoCorrida.clave = :tipoCorrida ");
                camposFiltrado.add("tipoCorrida");
                valoresWhere.add("PER");
            }
        }
        if (!datosFiltradoAsistencias.getClaveCentroCosto().isEmpty()) {
            from.append("LEFT OUTER JOIN x0.centroDeCosto x8 ");
            where.append("AND x8.clave = :centroDeCosto ");
            camposFiltrado.add("centroDeCosto");
            valoresWhere.add(datosFiltradoAsistencias.getClaveCentroCosto());
        }
//        if (datosFiltradoAsistencias.getClavesEmpleados() != null) {
//            if (datosFiltradoAsistencias.getClavesEmpleados().length > 0) {
//                where.append("AND x1.clave IN (:claveEmpleados) ");
//                camposFiltrado.add("claveEmpleados");
//                valoresWhere.add(datosFiltradoAsistencias.getClavesEmpleados());
//            }
//        }
        orden.append("ORDER BY CASE WHEN (x1 IS NULL) THEN '' ELSE CASE WHEN (x1.clave IS NULL) THEN '' ELSE x1.clave END END");
        select.append(from).append(where).append(orden);
        datosAsistencias = (List<Object>) ejecutaQueryList(select.toString(), camposFiltrado.toArray(new String[]{}), valoresWhere.toArray(), 0);
        return datosAsistencias;
    }

    public Mensaje generaDatosParaTimbrado(List<Object> valoresDeFiltrado, String claveRazonSocial, String uuidCxn, String uuidCxnMaestra) {
        List<DatosParaTimbrar> datosParaTimbrar = new ArrayList<DatosParaTimbrar>();
        valoresDeFiltrado = valoresDeFiltrado == null ? new ArrayList<Object>() : valoresDeFiltrado;
        try {
            /**
             * ******************************Carga datos para
             * filtrado******************************************************
             */
            Periodicidad periodicidad = null;

            int i, j;
            DatosFiltradoEmpleados datosFiltradoEmpleado = new DatosFiltradoEmpleados();
            DatosFiltradoMovNom datosFiltradoMovNom = new DatosFiltradoMovNom();
            DatosFiltradoAsistencias datosFiltradoAsistencia = new DatosFiltradoAsistencias();

            datosFiltradoEmpleado.setClaveRazonSocial(claveRazonSocial);
            datosFiltradoMovNom.setClaveRazonSocial(claveRazonSocial);
            datosFiltradoAsistencia.setClaveRazonSocial(claveRazonSocial);

            boolean empleadoInicio = true;
            if (valoresDeFiltrado.size() > 0) {
                Empleados empIni = null;
                Empleados empFin = null;
                for (i = 0; i < valoresDeFiltrado.size(); i++) {
                    if (valoresDeFiltrado.get(i) instanceof TipoNomina) {
                        periodicidad = ((TipoNomina) valoresDeFiltrado.get(i)).getPeriodicidad();
                        datosFiltradoEmpleado.setClaveTipoNomina(((TipoNomina) valoresDeFiltrado.get(i)).getClave());
                        datosFiltradoMovNom.setClaveTipoNomina(((TipoNomina) valoresDeFiltrado.get(i)).getClave());
                        datosFiltradoAsistencia.setClaveTipoNomina(((TipoNomina) valoresDeFiltrado.get(i)).getClave());
                    } else if (valoresDeFiltrado.get(i) instanceof TipoCorrida) {
                        datosFiltradoMovNom.setClaveTipoCorrida(((TipoCorrida) valoresDeFiltrado.get(i)).getClave());
                        datosFiltradoEmpleado.setClaveTipoCorrida(((TipoCorrida) valoresDeFiltrado.get(i)).getClave());
                    } else if (valoresDeFiltrado.get(i) instanceof PeriodosNomina) {
                        datosFiltradoEmpleado.setFechaInicio(((PeriodosNomina) valoresDeFiltrado.get(i)).getFechaInicial());
                        datosFiltradoEmpleado.setFechaFin(((PeriodosNomina) valoresDeFiltrado.get(i)).getFechaFinal());

                        datosFiltradoMovNom.setFechaInicio(((PeriodosNomina) valoresDeFiltrado.get(i)).getFechaInicial());
                        datosFiltradoMovNom.setFechaFin(((PeriodosNomina) valoresDeFiltrado.get(i)).getFechaFinal());

                        datosFiltradoAsistencia.setFechaInicio(((PeriodosNomina) valoresDeFiltrado.get(i)).getFechaInicial());
                        datosFiltradoAsistencia.setFechaFin(((PeriodosNomina) valoresDeFiltrado.get(i)).getFechaFinal());
                    } else if (valoresDeFiltrado.get(i) instanceof RegistroPatronal) {
                        datosFiltradoEmpleado.setClaveRegistroPatronal(((RegistroPatronal) valoresDeFiltrado.get(i)).getClave());
                    } else if (valoresDeFiltrado.get(i) instanceof CentroDeCosto) {
                        datosFiltradoEmpleado.setClaveCentroCosto(((CentroDeCosto) valoresDeFiltrado.get(i)).getClave());
                        datosFiltradoMovNom.setClaveCentroCosto(((CentroDeCosto) valoresDeFiltrado.get(i)).getClave());
                        datosFiltradoAsistencia.setClaveCentroCosto(((CentroDeCosto) valoresDeFiltrado.get(i)).getClave());
                    } else if (valoresDeFiltrado.get(i) instanceof Departamentos) {
                        datosFiltradoEmpleado.setClaveDepartamento(((Departamentos) valoresDeFiltrado.get(i)).getClave());
                    } else if (valoresDeFiltrado.get(i) instanceof Empleados & empleadoInicio) {
                        empleadoInicio = false;
                        empIni = (Empleados) valoresDeFiltrado.get(i);
                        if (empIni.getId() == null) {
                            empIni = null;
                        }
                    } else if (valoresDeFiltrado.get(i) instanceof Empleados) {
                        empFin = (Empleados) valoresDeFiltrado.get(i);
                        if (empFin.getId() == null) {
                            empFin = null;
                        }
                    }
                }
                if (empIni != null && empFin != null) {
                    datosFiltradoEmpleado.setClaveInicioEmpleado(empIni.getClave());
                    datosFiltradoEmpleado.setClaveFinEmpleado(empFin.getClave());
                } else if (empIni != null) {
                    datosFiltradoEmpleado.setClaveInicioEmpleado(empIni.getClave());
                } else if (empFin != null) {
                    datosFiltradoEmpleado.setClaveFinEmpleado(empFin.getClave());
                }
            }
            /**
             * **************Busca parametros**********
             */
            inicializaVariableMensaje();
            setSession(HibernateUtil.currentSession(uuidCxnMaestra));
            getSession().beginTransaction();
            obtenerFactores(claveRazonSocial, periodicidad);
            getSession().getTransaction().commit();
            /**
             * **************fin busqueda parametros**********
             */
            setSession(HibernateUtil.currentSession(uuidCxn));  //crea conexion
            getSession().beginTransaction();
            List<Object> datosEmpleado = construyeQueryDatosGlobalesEmpleados(datosFiltradoEmpleado);
            if (datosEmpleado == null) {
                return mensajeResultado;
            }
            if (datosEmpleado.size() > 0) {
                Object[] valores;
                //  List<String> clavesEmpleados = new ArrayList<String>();
                String claveEmpleado;
                for (i = 0; i < datosEmpleado.size(); i++) {
                    valores = (Object[]) datosEmpleado.get(i);
                    ///claveEmpleado = (String) valores[0];
                    // clavesEmpleados.add(claveEmpleado);
                    valores[1] = calculaSueldoDiario((PlazasPorEmpleadosMov) valores[1]);
                    datosEmpleado.set(i, valores);
                }
                ///datosFiltradoAsistencia.setClavesEmpleados(clavesEmpleados.toArray(new String[]{}));
                ///datosFiltradoMovNom.setClavesEmpleados(clavesEmpleados.toArray(new String[]{}));
                List<Object> datosMovNomina = (List<Object>) construyeQueryDatosGlobalesMovNom(datosFiltradoMovNom, datosFiltradoEmpleado);
                if (datosMovNomina == null) {
                    return mensajeResultado;
                }
                List<Object> datosAsistencias = (List<Object>) construyeQueryDatosGlobalesAsistencias(datosFiltradoAsistencia, datosFiltradoEmpleado);

                //****************************eliminar movimientos repetidos y suma bases afecta y resultado de movimiento*********************************************************/
                if (datosMovNomina.size() > 1) {
                    i = 0;
                    int k, l;
                    int contEx = 0, total = datosMovNomina.size();
                    Object[] tmp;

                    MovNomConcep mov1, mov2;
                    List<MovNomBaseAfecta> basesAfectaMov1;
                    List<MovNomBaseAfecta> basesAfectaMov2;
                    String claveEmp, claveEmpTemp;
                    while (i < datosMovNomina.size()) {
                        valores = (Object[]) datosMovNomina.get(i);
                        mov1 = nuevaInstanciaMovNomina((MovNomConcep) valores[1]);
                        claveEmp = (String) valores[0];
                        j = i + 1;
                        if (j < datosMovNomina.size()) {
                            while (j < datosMovNomina.size()) {
                                tmp = (Object[]) datosMovNomina.get(j);
                                claveEmpTemp = (String) tmp[0];
                                mov2 = (MovNomConcep) tmp[1];
                                if (claveEmpTemp.equalsIgnoreCase(claveEmp) & mov1.getConcepNomDefi().getClave().equalsIgnoreCase(mov2.getConcepNomDefi().getClave())) {
                                    mov1.setResultado((mov1.getResultado() == null ? 0.0 : mov1.getResultado()) + (mov2.getResultado() == null ? 0.0 : mov2.getResultado()));
                                    mov1.setCalculado((mov1.getCalculado() == null ? 0.0 : mov1.getCalculado()) + (mov2.getCalculado() == null ? 0.0 : mov2.getCalculado()));
                                    basesAfectaMov1 = mov1.getMovNomBaseAfecta() == null ? new ArrayList<MovNomBaseAfecta>() : mov1.getMovNomBaseAfecta();
                                    basesAfectaMov2 = mov2.getMovNomBaseAfecta() == null ? new ArrayList<MovNomBaseAfecta>() : mov2.getMovNomBaseAfecta();
                                    /**
                                     * *suma bases afecta*
                                     */
                                    if (basesAfectaMov1.isEmpty()) {
                                        if (basesAfectaMov2.size() > 0) {
                                            mov1.setMovNomBaseAfecta(creaMovimBaseAfectar(basesAfectaMov2, mov1));
                                        }
                                    } else {
                                        if (basesAfectaMov2.size() > 0) {
                                            for (l = 0; l < basesAfectaMov1.size(); l++) {
                                                for (k = 0; k < basesAfectaMov2.size(); k++) {
                                                    if (basesAfectaMov1.get(l).getBaseAfecConcepNom().getId() == basesAfectaMov2.get(k).getBaseAfecConcepNom().getId()) {
                                                        basesAfectaMov1.get(l).setResultado((basesAfectaMov1.get(l).getResultado() == null ? 0.0 : basesAfectaMov1.get(l).getResultado()) + (basesAfectaMov2.get(k).getResultado() == null ? 0.0 : basesAfectaMov2.get(k).getResultado()));
                                                        basesAfectaMov1.get(l).setResultadoExento((basesAfectaMov1.get(l).getResultadoExento() == null ? 0.0 : basesAfectaMov1.get(l).getResultadoExento()) + (basesAfectaMov2.get(k).getResultadoExento() == null ? 0.0 : basesAfectaMov2.get(k).getResultadoExento()));
                                                        break;
                                                    }
                                                }
                                            }
                                            mov1.setMovNomBaseAfecta(basesAfectaMov1);
                                        }
                                    }
                                    datosMovNomina.remove(j);
                                    valores[1] = mov1;
                                    datosMovNomina.set(i, valores);
                                    contEx++;
                                } else {
                                    j++;
                                }
                            }
                        }
                        i++;
                    }
                    /////////// System.out.println(" cont elim " + contEx + " resultado " + datosMovNomina.size() + " total " + total);
                }
                //****************************agrupa informacion por empleado*********************************************************/
                DatosParaTimbrar datoPorTimbrar;
                List<Asistencias> listAsistencias;
                List<MovNomConcep> listMovNom;
                Object[] valoresComp;
                DatosPorEmpleado dpe;
                for (i = 0; i < datosEmpleado.size(); i++) {
                    datoPorTimbrar = new DatosParaTimbrar();
                    valores = (Object[]) datosEmpleado.get(i);
                    dpe = new DatosPorEmpleado();
                    dpe.setPlazasPorEmpleadosMov((PlazasPorEmpleadosMov) valores[1]);
                    dpe.setSalarioDiarioIntegrado((Double) valores[2]);
                    dpe.setFechaIngreso((Date) valores[3]);
                    dpe.setDetalleReciboPeriodo((String) valores[4]);
                    dpe.setDetalleReciboCorrida((String) valores[5]);
                    dpe.setDetalleReciboNomina((String) valores[6]);
                    datoPorTimbrar.setDatosPorEmpleado(dpe);
                    claveEmpleado = (String) valores[0];
                    if (datosMovNomina.size() > 0) {
                        j = 0;
                        listMovNom = new ArrayList<MovNomConcep>();
                        while (j < datosMovNomina.size()) {
                            valoresComp = (Object[]) datosMovNomina.get(j);
                            if (claveEmpleado.equalsIgnoreCase(valoresComp[0].toString())) {
                                listMovNom.add((MovNomConcep) valoresComp[1]);
                                datosMovNomina.remove(j);
                            } else {
                                j++;
                            }
                        }
                        datoPorTimbrar.setMovimientos(listMovNom);
                    }

                    if (datosAsistencias.size() > 0) {
                        j = 0;
                        listAsistencias = new ArrayList<Asistencias>();
                        while (j < datosAsistencias.size()) {
                            valoresComp = (Object[]) datosAsistencias.get(j);
                            if (claveEmpleado.equalsIgnoreCase(valoresComp[0].toString())) {
                                listAsistencias.add((Asistencias) valoresComp[1]);
                                datosAsistencias.remove(j);
                            } else {
                                j++;
                            }
                        }
                        datoPorTimbrar = agregaIncapacidadesHorasExtras(listAsistencias, datoPorTimbrar);
                        //////////datosParaTimbrar.setDatosIncapacidades(listAsistencias);
                    }
                    if (datoPorTimbrar.getMovimientos() == null ? false : !datoPorTimbrar.getMovimientos().isEmpty()) {
                        datosParaTimbrar.add(datoPorTimbrar);
                    }
                }
            }

            nullVariablesGlobales();
            mensajeResultado.setResultado(datosParaTimbrar);
            mensajeResultado.setNoError(0);
            mensajeResultado.setError("");
            getSession().getTransaction().commit();
        } catch (HibernateException ex) {
            System.err.println(concatena.delete(0, concatena.length()).append(msgError).append("agregar()2_Error: ").append(ex));
            try {
                if (getSession().getTransaction().isActive()) {
                    getSession().getTransaction().rollback();
                }
                mensajeResultado.setNoError(ControlErroresHibernate.buscaNoErrorPorExcepcion(ex.getClass()));
                mensajeResultado.setError(ex.getLocalizedMessage());
            } catch (HibernateException exc) {  //cuando marca error al hacer rollback pro ejemplo se cae conexion servidor
                mensajeResultado.setNoError(ControlErroresHibernate.buscaNoErrorPorExcepcion(exc.getClass()));
                mensajeResultado.setError(exc.getLocalizedMessage());
            }
            mensajeResultado.setResultado(null);
        }
        return mensajeResultado;
    }

    private MovNomConcep nuevaInstanciaMovNomina(MovNomConcep movNomConcep) {
        MovNomConcep nueva = new MovNomConcep();
        nueva.setCalculado(movNomConcep.getCalculado());
        nueva.setCentroDeCosto(movNomConcep.getCentroDeCosto());
        nueva.setConcepNomDefi(movNomConcep.getConcepNomDefi());
        nueva.setEjercicio(movNomConcep.getEjercicio());
        nueva.setEmpleado(movNomConcep.getEmpleado());
        nueva.setFechaCierr(movNomConcep.getFechaCierr());
        nueva.setFechaIni(movNomConcep.getFechaIni());
        nueva.setFiniqLiquidCncNom(movNomConcep.getFiniqLiquidCncNom());
        nueva.setId(null);
        nueva.setIsEnBD(movNomConcep.isIsEnBD());
        nueva.setMes(movNomConcep.getMes());
        nueva.setMovNomBaseAfecta(creaMovimBaseAfectar(movNomConcep.getMovNomBaseAfecta(), nueva));
        nueva.setMovNomConceParam(creaMovNomConceParam(nueva, movNomConcep.getMovNomConceParam()));
        nueva.setNumMovParticion(movNomConcep.getNumMovParticion());
        nueva.setNumero(movNomConcep.getNumero());
        nueva.setOrdenId(movNomConcep.getOrdenId());
        nueva.setPeriodosNomina(movNomConcep.getPeriodosNomina());
        nueva.setPlazasPorEmpleado(movNomConcep.getPlazasPorEmpleado());
        nueva.setRazonesSociales(movNomConcep.getRazonesSociales());
        nueva.setResultado(movNomConcep.getResultado());
        nueva.setTipoCorrida(movNomConcep.getTipoCorrida());
        nueva.setTipoNomina(movNomConcep.getTipoNomina());
        nueva.setUso(movNomConcep.getUso());
        return nueva;
    }

    private List<MovNomConceParam> creaMovNomConceParam(MovNomConcep mnc, List<MovNomConceParam> params) {
        if (params == null) {
            return null;
        }
        List<MovNomConceParam> movparametros = new ArrayList<MovNomConceParam>();
        MovNomConceParam m;
        for (MovNomConceParam movParam : params) {
            m = new MovNomConceParam();
            m.setMovNomConcep(mnc);
            m.setParaConcepDeNom(movParam.getParaConcepDeNom());
            m.setValor(movParam.getValor());
            movparametros.add(m);
        }
        return movparametros;
    }

    private List<MovNomBaseAfecta> creaMovimBaseAfectar(List<MovNomBaseAfecta> baseAfecConcepNominas, MovNomConcep mnc) {
        if (baseAfecConcepNominas == null) {
            return null;
        }
        List<MovNomBaseAfecta> movNominaBaseAfectas = new ArrayList<MovNomBaseAfecta>(0);
        MovNomBaseAfecta m;
        for (MovNomBaseAfecta afecConcepNom : baseAfecConcepNominas) {
            m = new MovNomBaseAfecta();
            m.setBaseAfecConcepNom(afecConcepNom.getBaseAfecConcepNom());
            m.setMovNomConcep(mnc);
            m.setUso(afecConcepNom.getUso());
            m.setResultado(afecConcepNom.getResultado());
            m.setResultadoExento(afecConcepNom.getResultadoExento());
            movNominaBaseAfectas.add(m);
        }
        return movNominaBaseAfectas;
    }

    private PlazasPorEmpleadosMov calculaSueldoDiario(PlazasPorEmpleadosMov ppem) {
        double sueldoDiario;
        if (ppem.getSalarioPor() == 2) {
            sueldoDiario = ppem.getImporte();
        } else {
            sueldoDiario = ppem.getPuestos().getSalarioTabular();
        }

//        if (manejaPagosPorHora != null) {
//            if (manejaPagosPorHora & ppem.getPuestos().getCategoriasPuestos().getPagarPorHoras()) {
//                if (manejoHorasPor == ManejoHorasPor.HSM) {
//                    if (manejoSalarioDiario == ManejoSalarioDiario.SEMANAL) {
//                        sueldoDiario = (ppem.getHoras() / 1) * sueldoDiario;
//                    } else if (manejoSalarioDiario == ManejoSalarioDiario.QUINCENAL) {
//                        sueldoDiario = (ppem.getHoras() / 2) * sueldoDiario;
//                    } else if (manejoSalarioDiario == ManejoSalarioDiario.MENSUAL) {
//                        sueldoDiario = (ppem.getHoras() / 4) * sueldoDiario;
//                    }
//                }
//            } else {
//                if (manejoSalarioDiario == ManejoSalarioDiario.SEMANAL) {
//                    sueldoDiario = sueldoDiario / 7;
//                } else if (manejoSalarioDiario == ManejoSalarioDiario.QUINCENAL) {
//                    sueldoDiario = sueldoDiario / 15;
//                } else if (manejoSalarioDiario == ManejoSalarioDiario.MENSUAL) {
//                    sueldoDiario = sueldoDiario / 30;
//                }
//            }
//        } else {
//            if (manejoSalarioDiario == ManejoSalarioDiario.SEMANAL) {
//                sueldoDiario = sueldoDiario / 7;
//            } else if (manejoSalarioDiario == ManejoSalarioDiario.QUINCENAL) {
//                sueldoDiario = sueldoDiario / 15;
//            } else if (manejoSalarioDiario == ManejoSalarioDiario.MENSUAL) {
//                sueldoDiario = sueldoDiario / 30;
//            }
//        }
        ppem.setSueldoDiario(sueldoDiario);
        return ppem;
    }

    private void nullVariablesGlobales() {
        manejaPagosPorHora = null;
        manejoHorasPor = null;
        manejoSalarioDiario = null;
    }

    private void obtenerFactores(Object clavesElementoAplicacion, Periodicidad periodicidadTipoNomina) {
        try {
            String valor = null;

            //<editor-fold defaultstate="collapsed" desc="Maneja pagos por hora">
            if (manejaPagosPorHora == null) {
                strQuery.delete(0, strQuery.length()).append("SELECT cr.valor");
                strQuery.append(" FROM Cruce cr ");
                strQuery.append(" INNER JOIN cr.parametros pr ");
                strQuery.append(" INNER JOIN cr.elementosaplicacion ea ");
                strQuery.append(" WHERE pr.clave = :parametro ");
                strQuery.append(" and ea.clave = :elementoAplicacion ");
                strQuery.append(" and cr.claveElemento = :claveElemento");
                valor = (String) ejecutaQueryUnico(strQuery.toString(), new String[]{"parametro", "elementoAplicacion", "claveElemento"}, new Object[]{ClavesParametrosModulos.claveParametroPagosPorHora, ClavesParametrosModulos.claveElementoAplicacionRazonSocial, clavesElementoAplicacion});
                valor = (valor == null ? "" : valor);
                if (valor.isEmpty()) {
                    strQuery.delete(0, strQuery.length()).append("SELECT pr.valor ");
                    strQuery.append(" FROM Parametros pr ");
                    strQuery.append(" INNER JOIN pr.modulo m ");
                    strQuery.append(" WHERE pr.clave = :parametro ");
                    strQuery.append(" AND m.clave =:modulo");
                    valor = (String) ejecutaQueryUnico(strQuery.toString(), new String[]{"parametro", "modulo"}, new Object[]{ClavesParametrosModulos.claveParametroPagosPorHora, ClavesParametrosModulos.claveModuloGlobal});
                }
                manejaPagosPorHora = valor.toString().equalsIgnoreCase(ClavesParametrosModulos.opcionParametroPagarPorHoras.toString());
            }
            //</editor-fold>

            //<editor-fold defaultstate="collapsed" desc="Manejo horas Por">
            if (manejoHorasPor == null) {
                strQuery.delete(0, strQuery.length()).append("SELECT cr.valor ");
                strQuery.append(" FROM Cruce cr ");
                strQuery.append(" INNER JOIN cr.parametros pr ");
                strQuery.append(" INNER JOIN cr.elementosaplicacion ea ");
                strQuery.append(" WHERE pr.clave = :parametro ");
                strQuery.append(" and ea.clave = :elementoAplicacion ");
                strQuery.append(" and cr.claveElemento = :claveElemento");
                valor = (String) ejecutaQueryUnico(strQuery.toString(), new String[]{"parametro", "elementoAplicacion", "claveElemento"}, new Object[]{ClavesParametrosModulos.claveParametroManejarHorasPor, ClavesParametrosModulos.claveElementoAplicacionRazonSocial, clavesElementoAplicacion});
                valor = (valor == null ? "" : valor);
                if (valor.isEmpty()) {
                    strQuery.delete(0, strQuery.length()).append("SELECT pr.valor ");
                    strQuery.append(" FROM Parametros pr");
                    strQuery.append(" INNER JOIN pr.modulo m ");
                    strQuery.append(" WHERE pr.clave = :parametro ");
                    strQuery.append(" AND m.clave =:modulo");
                    valor = (String) ejecutaQueryUnico(strQuery.toString(), new String[]{"parametro", "modulo"}, new Object[]{ClavesParametrosModulos.claveParametroManejarHorasPor, ClavesParametrosModulos.claveModuloGlobal});
                }
                if (valor.equals(ClavesParametrosModulos.opcionParametroHorasNaturales)) {
                    manejoHorasPor = ManejoHorasPor.HORASNATURALES;
                } else if (valor.equals(ClavesParametrosModulos.opcionParametroHorasHSM)) {
                    manejoHorasPor = ManejoHorasPor.HSM;
                }
            }
            //</editor-fold>

            //<editor-fold defaultstate="collapsed" desc="Manejo de pagos dias naturales">
            if (manejaPagoDiasNaturales == null) {//BUSQUEDA POR PERIODICIDAD//JSA08
                strQuery.delete(0, strQuery.length()).append("SELECT cr.valor");
                strQuery.append(" FROM Cruce cr ");
                strQuery.append(" INNER JOIN cr.parametros pr ");
                strQuery.append(" INNER JOIN cr.elementosaplicacion ea ");
                strQuery.append(" WHERE pr.clave = :parametro ");
                strQuery.append(" and ea.clave = :elementoAplicacion ");
                strQuery.append(" and cr.claveElemento = :claveElemento");
                if (periodicidadTipoNomina != null) {
                    valor = (String) ejecutaQueryUnico(strQuery.toString(), new String[]{"parametro", "elementoAplicacion", "claveElemento"}, new Object[]{ClavesParametrosModulos.clavePagarNominaDiasNaturales, ClavesParametrosModulos.claveElementoAplicacionPeriodicidad, periodicidadTipoNomina.getClave()});
                }
                valor = (valor == null ? "" : valor);
                if (valor.isEmpty()) {//BUSQUEDA POR RAZON SOCIAL
                    strQuery.delete(0, strQuery.length()).append("SELECT cr.valor");
                    strQuery.append(" FROM Cruce cr ");
                    strQuery.append(" INNER JOIN cr.parametros pr ");
                    strQuery.append(" INNER JOIN cr.elementosaplicacion ea ");
                    strQuery.append(" WHERE pr.clave = :parametro ");
                    strQuery.append(" and ea.clave = :elementoAplicacion ");
                    strQuery.append(" and cr.claveElemento = :claveElemento");
                    valor = (String) ejecutaQueryUnico(strQuery.toString(), new String[]{"parametro", "elementoAplicacion", "claveElemento"}, new Object[]{ClavesParametrosModulos.clavePagarNominaDiasNaturales, ClavesParametrosModulos.claveElementoAplicacionRazonSocial, clavesElementoAplicacion});
                    valor = (valor == null ? "" : valor);
                }
                if (valor.isEmpty()) {//BUSQUEDA GLOBAL
                    strQuery.delete(0, strQuery.length()).append("SELECT pr.valor ");
                    strQuery.append(" FROM Parametros pr ");
                    strQuery.append(" INNER JOIN pr.modulo m ");
                    strQuery.append(" WHERE pr.clave = :parametro ");
                    strQuery.append(" AND m.clave =:modulo");
                    valor = (String) ejecutaQueryUnico(strQuery.toString(), new String[]{"parametro", "modulo"}, new Object[]{ClavesParametrosModulos.clavePagarNominaDiasNaturales, ClavesParametrosModulos.claveModuloGlobal});
                }
                if (valor.equals(ClavesParametrosModulos.opcionParametroPagarPorDiaNatural)) {
                    manejaPagoDiasNaturales = true;
                } else {
                    manejaPagoDiasNaturales = false;
                }
            }
            //</editor-fold>

            //<editor-fold defaultstate="collapsed" desc="Manejo de Salario Diario">
            if (manejaPagoDiasNaturales) {
                manejoSalarioDiario = ManejoSalarioDiario.DIARIO;
            } else {
                if (manejoSalarioDiario == null) {
                    strQuery.delete(0, strQuery.length()).append("SELECT cr.valor ");
                    strQuery.append(" FROM Cruce cr ");
                    strQuery.append(" INNER JOIN cr.parametros pr ");
                    strQuery.append(" INNER JOIN cr.elementosaplicacion ea ");
                    strQuery.append(" WHERE pr.clave = :parametro ");
                    strQuery.append(" and ea.clave = :elementoAplicacion ");
                    strQuery.append(" and cr.claveElemento = :claveElemento");
                    valor = (String) ejecutaQueryUnico(strQuery.toString(), new String[]{"parametro", "elementoAplicacion", "claveElemento"}, new Object[]{ClavesParametrosModulos.claveParametroManejarSalarioDiarioPor, ClavesParametrosModulos.claveElementoAplicacionRazonSocial, clavesElementoAplicacion});
                    valor = (valor == null ? "" : valor);
                    if (valor.isEmpty()) {
                        strQuery.delete(0, strQuery.length()).append("SELECT pr.valor ");
                        strQuery.append(" FROM Parametros pr");
                        strQuery.append(" INNER JOIN pr.modulo m ");
                        strQuery.append(" WHERE pr.clave = :parametro ");
                        strQuery.append(" AND m.clave =:modulo");
                        valor = (String) ejecutaQueryUnico(strQuery.toString(), new String[]{"parametro", "modulo"}, new Object[]{ClavesParametrosModulos.claveParametroManejarSalarioDiarioPor, ClavesParametrosModulos.claveModuloGlobal});
                    }
                    if (valor.equals(ClavesParametrosModulos.opcionParametroSalarioDiario)) {
                        manejoSalarioDiario = ManejoSalarioDiario.DIARIO;
                    } else if (valor.equals(ClavesParametrosModulos.opcionParametroSalarioSemanal)) {
                        manejoSalarioDiario = ManejoSalarioDiario.SEMANAL;
                    } else if (valor.equals(ClavesParametrosModulos.opcionParametroSalarioQuincenal)) {
                        manejoSalarioDiario = ManejoSalarioDiario.QUINCENAL;
                    } else {
                        manejoSalarioDiario = ManejoSalarioDiario.MENSUAL;
                    }
                }
            }
            //</editor-fold>

        } catch (Exception ex) {
            System.err.println(concatena.delete(0, concatena.length()).append(msgError).append("obtenerFactores()1_Error: ").append(ex));
            mensajeResultado.setNoError(ControlErroresHibernate.buscaNoErrorPorExcepcion(ex.getClass()));
            mensajeResultado.setError(ex.getLocalizedMessage());
            mensajeResultado.setResultado(null);
        }
    }

    private Object ejecutaQueryUnico(String strQuery, String[] camposParametro, Object[] valoresParametro) {
        Object result = null;
        try {
            Query query = getSession().createQuery(strQuery);
            int i;
            if (camposParametro != null & valoresParametro != null) {
                for (i = 0; i < camposParametro.length; i++) {
                    if (valoresParametro[i] instanceof Object[]) {
                        query.setParameterList(camposParametro[i], (Object[]) valoresParametro[i]);
                    } else {
                        if (valoresParametro[i] instanceof Calendar) {
                            Calendar c = (Calendar) valoresParametro[i];
                            query.setParameter(camposParametro[i], c.getTime());
                        } else {
                            query.setParameter(camposParametro[i], valoresParametro[i]);
                        }
                    }
                }
            }
            query.setMaxResults(1);
            result = query.uniqueResult();
        } catch (HibernateException ex) {
            System.err.println(concatena.delete(0, concatena.length()).append(msgError).append("ejecutaQueryUnico()1_Error: ").append(ex));
            mensajeResultado.setNoError(ControlErroresHibernate.buscaNoErrorPorExcepcion(ex.getClass()));
            mensajeResultado.setError(ex.getLocalizedMessage());
            mensajeResultado.setResultado(null);
        } catch (Exception e) {
            System.err.println(concatena.delete(0, concatena.length()).append(msgError).append("ejecutaQueryUnico()1_Error: ").append(e));
            mensajeResultado.setNoError(ControlErroresHibernate.buscaNoErrorPorExcepcion(e.getClass()));
            mensajeResultado.setError(e.getLocalizedMessage());
            mensajeResultado.setResultado(null);
        }
        return result;
    }

    private List<?> ejecutaQueryList(String strQuery, String[] camposParametro, Object[] valoresParametro, Integer maxResultados) {
        List<Object> result = null;
        try {
            Query query = getSession().createQuery(strQuery);

            int i;
            if (camposParametro != null & valoresParametro != null) {
                for (i = 0; i < camposParametro.length; i++) {
                    if (valoresParametro[i] instanceof Object[]) {
                        query.setParameterList(camposParametro[i], (Object[]) valoresParametro[i]);
                    } else {
                        if (valoresParametro[i] instanceof Calendar) {
                            Calendar c = (Calendar) valoresParametro[i];
                            query.setParameter(camposParametro[i], c.getTime());
                        } else {
                            query.setParameter(camposParametro[i], valoresParametro[i]);
                        }
                    }
                }
            }
            if (maxResultados == null) {
                maxResultados = 0;
            }

            if (maxResultados > 0) {
                query.setMaxResults(maxResultados);
            }

            result = query.list();
        } catch (HibernateException ex) {
            System.err.println(concatena.delete(0, concatena.length()).append(msgError).append("ejecutaQueryList()1_Error: ").append(ex));
            mensajeResultado.setNoError(ControlErroresHibernate.buscaNoErrorPorExcepcion(ex.getClass()));
            mensajeResultado.setError(ex.getLocalizedMessage());
            mensajeResultado.setResultado(null);
        } catch (Exception e) {
            System.err.println(concatena.delete(0, concatena.length()).append(msgError).append("ejecutaQueryList()1_Error: ").append(e));
            mensajeResultado.setNoError(ControlErroresHibernate.buscaNoErrorPorExcepcion(e.getClass()));
            mensajeResultado.setError(e.getLocalizedMessage());
            mensajeResultado.setResultado(null);
        }
        return result;
    }

    private List<?> ejecutaQueryList(String consulta, boolean conParametros, List<String> listParametros, Map<String, Object> parametrosCampos, Integer maxResultados) {
        List<Object> result = null;
        try {
            Query query = getSession().createQuery(consulta);

            int i;
            Object valor;
            String parametro;
            if (conParametros & listParametros != null) {
                for (i = 0; i < listParametros.size(); i++) {
                    parametro = listParametros.get(i);
                    valor = parametrosCampos.get(parametro);
                    if (valor instanceof Calendar) {
                        valor = ((Calendar) valor).getTime();
                    }
                    if (valor instanceof Object[]) {
                        query.setParameterList(parametro, (Object[]) valor);
                    } else if (valor instanceof ArrayList) {
                        query.setParameterList(parametro, ((ArrayList) valor).toArray());
                    } else {
                        query.setParameter(parametro, valor);
                    }
                }
            }
            if (maxResultados == null) {
                maxResultados = 0;
            }

            if (maxResultados > 0) {
                query.setMaxResults(maxResultados);
            }

            result = query.list();
        } catch (HibernateException ex) {
            System.err.println(concatena.delete(0, concatena.length()).append(msgError).append("ejecutaQueryList()1_Error: ").append(ex));
            mensajeResultado.setNoError(ControlErroresHibernate.buscaNoErrorPorExcepcion(ex.getClass()));
            mensajeResultado.setError(ex.getLocalizedMessage());
            mensajeResultado.setResultado(null);
        } catch (Exception e) {
            System.err.println(concatena.delete(0, concatena.length()).append(msgError).append("ejecutaQueryList()1_Error: ").append(e));
            mensajeResultado.setNoError(ControlErroresHibernate.buscaNoErrorPorExcepcion(e.getClass()));
            mensajeResultado.setError(e.getLocalizedMessage());
            mensajeResultado.setResultado(null);
        }
        return result;
    }

    private DatosParaTimbrar agregaIncapacidadesHorasExtras(List<Asistencias> listAsistencias, DatosParaTimbrar datosParaTimbrar) {
        if (datosParaTimbrar == null) {
            datosParaTimbrar = new DatosParaTimbrar();
        }
        Integer diasIncapacidadEnfermedad = 0, diasIncapacidadAccidente = 0, diasIncapacidadMaternidad = 0, diasHorasDobles = 0, diasHorasTriples = 0, diasTiempoExtra = 0;
        Double hrsExtraDoble = 0.0, hrsExtraTriple = 0.0;
        ///tiempoExtra = 0.0;
        int x;
        Asistencias asistenciaEnfermedad = null, asistenciaAccidente = null, asistenciaMaternidad = null, asistenciaHrsDobles = null, asistenciaHrsTriples = null;
        if (listAsistencias != null) {
            for (x = 0; x < listAsistencias.size(); x++) {
                switch (Integer.parseInt(listAsistencias.get(x).getExcepciones().getClave())) {
                    case 6://IncapacidadPorEnfermedad = "6";
                        asistenciaEnfermedad = listAsistencias.get(x);
                        diasIncapacidadEnfermedad++;
                        break;
                    case 7://IncapacidadPorAccidente = "7";
                        asistenciaAccidente = listAsistencias.get(x);
                        diasIncapacidadAccidente++;
                        break;
                    case 8://IncapacidadPorMaternidad = "8";
                        asistenciaMaternidad = listAsistencias.get(x);
                        diasIncapacidadMaternidad++;
                        asistenciaEnfermedad = listAsistencias.get(x);
                        break;
                    case 14://ExtraDoble = "14";
                        asistenciaHrsDobles = listAsistencias.get(x);
                        diasHorasDobles++;
                        hrsExtraDoble += listAsistencias.get(x).getCantidad();
                        break;
                    case 15://ExtraTriple = "15";
                        asistenciaHrsTriples = listAsistencias.get(x);
                        diasHorasTriples++;
                        hrsExtraTriple += listAsistencias.get(x).getCantidad();
                        break;
                }
            }
            List<DatosHorasExtras> listHrsExtras = new ArrayList<DatosHorasExtras>();
            DatosHorasExtras extra;
            if (asistenciaHrsDobles != null) {
                extra = new DatosHorasExtras();
                extra.setAsistencia(asistenciaHrsDobles);
                extra.setDias(diasHorasDobles);
                extra.setHrsExtas(hrsExtraDoble.intValue());
                listHrsExtras.add(extra);
            }
            if (asistenciaHrsTriples != null) {
                extra = new DatosHorasExtras();
                extra.setAsistencia(asistenciaHrsTriples);
                extra.setDias(diasHorasTriples);
                extra.setHrsExtas(hrsExtraTriple.intValue());
                listHrsExtras.add(extra);
            }
            List<DatosIncapacidades> listIncapacidades = new ArrayList<DatosIncapacidades>();
            DatosIncapacidades incapacidad;
            if (asistenciaAccidente != null) {
                incapacidad = new DatosIncapacidades();
                incapacidad.setAsistencia(asistenciaAccidente);
                incapacidad.setDias(diasIncapacidadAccidente);
                listIncapacidades.add(incapacidad);
            }
            if (asistenciaEnfermedad != null) {
                incapacidad = new DatosIncapacidades();
                incapacidad.setAsistencia(asistenciaEnfermedad);
                incapacidad.setDias(diasIncapacidadEnfermedad);
                listIncapacidades.add(incapacidad);
            }
            if (asistenciaMaternidad != null) {
                incapacidad = new DatosIncapacidades();
                incapacidad.setAsistencia(asistenciaMaternidad);
                incapacidad.setDias(diasIncapacidadMaternidad);
                listIncapacidades.add(incapacidad);
            }
            datosParaTimbrar.setDatosHorasExtras(listHrsExtras);
            datosParaTimbrar.setDatosIncapacidades(listIncapacidades);
        }
        return datosParaTimbrar;
    }

    private class DatosFiltradoEmpleados {

        private String claveRazonSocial;
        private String claveTipoNomina;
        private String claveRegistroPatronal;
        private String claveCentroCosto;
        private String claveDepartamento;
        private String claveInicioEmpleado;
        private String claveFinEmpleado;
        private String claveTipoCorrida;
        private Date fechaInicio;
        private Date fechaFin;

        public String getClaveRazonSocial() {
            return claveRazonSocial == null ? "" : claveRazonSocial;
        }

        public void setClaveRazonSocial(String claveRazonSocial) {
            this.claveRazonSocial = claveRazonSocial;
        }

        public String getClaveTipoNomina() {
            return claveTipoNomina == null ? "" : claveTipoNomina;
        }

        public void setClaveTipoNomina(String claveTipoNomina) {
            this.claveTipoNomina = claveTipoNomina;
        }

        public String getClaveRegistroPatronal() {
            return claveRegistroPatronal == null ? "" : claveRegistroPatronal;
        }

        public void setClaveRegistroPatronal(String claveRegistroPatronal) {
            this.claveRegistroPatronal = claveRegistroPatronal;
        }

        public String getClaveCentroCosto() {
            return claveCentroCosto == null ? "" : claveCentroCosto;
        }

        public void setClaveCentroCosto(String claveCentroCosto) {
            this.claveCentroCosto = claveCentroCosto;
        }

        public String getClaveDepartamento() {
            return claveDepartamento == null ? "" : claveDepartamento;
        }

        public void setClaveDepartamento(String claveDepartamento) {
            this.claveDepartamento = claveDepartamento;
        }

        public String getClaveInicioEmpleado() {
            return claveInicioEmpleado == null ? "" : claveInicioEmpleado;
        }

        public void setClaveInicioEmpleado(String claveInicioEmpleado) {
            this.claveInicioEmpleado = claveInicioEmpleado;
        }

        public String getClaveFinEmpleado() {
            return claveFinEmpleado == null ? "" : claveFinEmpleado;
        }

        public void setClaveFinEmpleado(String claveFinEmpleado) {
            this.claveFinEmpleado = claveFinEmpleado;
        }

        public Date getFechaInicio() {
            return fechaInicio;
        }

        public void setFechaInicio(Date fechaInicio) {
            this.fechaInicio = fechaInicio;
        }

        public Date getFechaFin() {
            return fechaFin;
        }

        public void setFechaFin(Date fechaFin) {
            this.fechaFin = fechaFin;
        }

        public String getClaveTipoCorrida() {
            return claveTipoCorrida == null ? "" : claveTipoCorrida;
        }

        public void setClaveTipoCorrida(String claveTipoCorrida) {
            this.claveTipoCorrida = claveTipoCorrida;
        }

    }

    private class DatosFiltradoMovNom {

        private String claveRazonSocial;
        private String claveTipoNomina;
        private String claveTipoCorrida;
        private String claveCentroCosto;
        private Date fechaInicio;
        private Date fechaFin;
//        private String[] clavesEmpleados;

        public String getClaveRazonSocial() {
            return claveRazonSocial == null ? "" : claveRazonSocial;
        }

        public void setClaveRazonSocial(String claveRazonSocial) {
            this.claveRazonSocial = claveRazonSocial;
        }

        public String getClaveTipoNomina() {
            return claveTipoNomina == null ? "" : claveTipoNomina;
        }

        public void setClaveTipoNomina(String claveTipoNomina) {
            this.claveTipoNomina = claveTipoNomina;
        }

        public String getClaveTipoCorrida() {
            return claveTipoCorrida == null ? "" : claveTipoCorrida;
        }

        public void setClaveTipoCorrida(String claveTipoCorrida) {
            this.claveTipoCorrida = claveTipoCorrida;
        }

        public String getClaveCentroCosto() {
            return claveCentroCosto == null ? "" : claveCentroCosto;
        }

        public void setClaveCentroCosto(String claveCentroCosto) {
            this.claveCentroCosto = claveCentroCosto;
        }

        public Date getFechaInicio() {
            return fechaInicio;
        }

        public void setFechaInicio(Date fechaInicio) {
            this.fechaInicio = fechaInicio;
        }

        public Date getFechaFin() {
            return fechaFin;
        }

        public void setFechaFin(Date fechaFin) {
            this.fechaFin = fechaFin;
        }

//        public String[] getClavesEmpleados() {
//            return clavesEmpleados;
//        }
//
//        public void setClavesEmpleados(String[] clavesEmpleados) {
//            this.clavesEmpleados = clavesEmpleados;
//        }
    }

    private class DatosFiltradoAsistencias {

        private String claveRazonSocial;
        private String claveTipoNomina;
        private String claveCentroCosto;
        private Date fechaInicio;
        private Date fechaFin;
        private String[] clavesEmpleados;

        public String getClaveRazonSocial() {
            return claveRazonSocial == null ? "" : claveRazonSocial;
        }

        public void setClaveRazonSocial(String claveRazonSocial) {
            this.claveRazonSocial = claveRazonSocial;
        }

        public String getClaveTipoNomina() {
            return claveTipoNomina == null ? "" : claveTipoNomina;
        }

        public void setClaveTipoNomina(String claveTipoNomina) {
            this.claveTipoNomina = claveTipoNomina;
        }

        public String getClaveCentroCosto() {
            return claveCentroCosto == null ? "" : claveCentroCosto;
        }

        public void setClaveCentroCosto(String claveCentroCosto) {
            this.claveCentroCosto = claveCentroCosto;
        }

        public Date getFechaInicio() {
            return fechaInicio;
        }

        public void setFechaInicio(Date fechaInicio) {
            this.fechaInicio = fechaInicio;
        }

        public Date getFechaFin() {
            return fechaFin;
        }

        public void setFechaFin(Date fechaFin) {
            this.fechaFin = fechaFin;
        }

        public String[] getClavesEmpleados() {
            return clavesEmpleados;
        }

        public void setClavesEmpleados(String[] clavesEmpleados) {
            this.clavesEmpleados = clavesEmpleados;
        }
    }
}
