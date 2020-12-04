/**
 * @author: Victor Lopez Fecha de Creación: 16/12/2011 Compañía: Macropro.
 * Descripción del programa: RegistroIncapacidadDAO para llamados a metodos de
 * HIBERNATE
 * -----------------------------------------------------------------------------
 * MODIFICACIONES:
 * -----------------------------------------------------------------------------
 * Clave:JSA01 Autor:Jose Armando Fecha:05/05/2015 Descripción: Se agrego el
 * codigo para generar las asistencias desde el servidor.
 * -----------------------------------------------------------------------------
 * Clave:JSA02 Autor:Jose Armando Fecha:07/05/2015 Descripción: correccion de
 * cuando se borra la incapacidad no se borraban las asistencias con dichas
 * incapacidades. Tambien cuando se modifica una fecha y la asistencia ya existe
 * se repetia la asistencia.
 * -----------------------------------------------------------------------------
 */
package com.mef.erp.modelo;

import com.mef.erp.modelo.entidad.Asistencias;
import com.mef.erp.modelo.entidad.DetalleAsistencia;
import com.mef.erp.modelo.entidad.Empleados;
import com.mef.erp.modelo.entidad.Excepciones;
import com.mef.erp.modelo.entidad.Mensaje;
import com.mef.erp.modelo.entidad.PeriodosNomina;
import com.mef.erp.modelo.entidad.PlazasPorEmpleadosMov;
import com.mef.erp.modelo.entidad.RazonesSociales;
import com.mef.erp.modelo.entidad.RegistroIncapacidad;
import com.mef.erp.modelo.entidad.TablaDatos;
import com.mef.erp.modelo.entidad.TipoNomina;
import com.mef.erp.util.ControlErroresHibernate;
import com.mef.erp.util.HibernateUtil;
import com.mef.erp.util.Utilerias;
import com.mef.erp.util.UtilidadesXML;
import com.mef.funciones.ClavesParametrosModulos;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.exception.LockAcquisitionException;

public class RegistroIncapacidadDAO extends GenericHibernateDAO<Object, String>
        implements RegistroIncapacidadDAOIF {

    private StringBuilder concatena = new StringBuilder(200);
    private final String msgError = concatena.append("ServerERP"/*util.getContexto()*/).append(".MSERR_C_").append(this.getClass().getName()).append(".").toString();
    StringBuilder consulta = null;
    private List<RegistroIncapacidad> listEsp = new ArrayList<RegistroIncapacidad>();
    private Boolean commit = false;

    public Mensaje agregar(RegistroIncapacidad entity, String uuidCxn) {
        RegistroIncapacidad registroIncapacidad;
        try {
            inicializaVariableMensaje();
            setSession(HibernateUtil.currentSession(uuidCxn));
            getSession().beginTransaction();
            registroIncapacidad = (RegistroIncapacidad) makePersistent(entity);
            mensajeResultado.setResultado(registroIncapacidad);
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

    public Mensaje actualizar(RegistroIncapacidad entity, String uuidCxn) {
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

    public Mensaje eliminar(RegistroIncapacidad entity, String uuidCxn) {
        try {
            inicializaVariableMensaje();
            setSession(HibernateUtil.currentSession(uuidCxn));
            getSession().beginTransaction();
            entity.setIncapacidadAnterior(null); // elimina entidad relacionada
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

    public Mensaje getRegistroIncapacidadAll(String claveRazonesSocial, String uuidCxn) {
        List<RegistroIncapacidad> values;
        try {
            inicializaVariableMensaje();
            setSession(HibernateUtil.currentSession(uuidCxn));
            getSession().beginTransaction();
            q = getSession().createQuery("from RegistroIncapacidad c where c.razonesSociales.clave=:clave");
            q.setParameter("clave", claveRazonesSocial);
            values = q.list();
            mensajeResultado.setResultado(values);
            mensajeResultado.setNoError(0);
            mensajeResultado.setError("");
            getSession().getTransaction().commit();
        } catch (HibernateException ex) {
            System.err.println(concatena.delete(0, concatena.length()).append(msgError).append("getRegistroIncapacidadAll()1_Error: ").append(ex));
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

    public Mensaje getRegistroIncapacidadPorEmpleado(Empleados empleado, String uuidCxn) {
        List<RegistroIncapacidad> values;
        try {
            inicializaVariableMensaje();
            setSession(HibernateUtil.currentSession(uuidCxn));
            getSession().beginTransaction();
            q = getSession().createQuery("from RegistroIncapacidad c where c.empleados = :empleado");
            q.setEntity("empleado", empleado);
            values = q.list();
            mensajeResultado.setResultado(values);
            mensajeResultado.setNoError(0);
            mensajeResultado.setError("");
            getSession().getTransaction().commit();
        } catch (HibernateException ex) {
            System.err.println(concatena.delete(0, concatena.length()).append(msgError).append("getRegistroIncapacidadPorEmpleado()1_Error: ").append(ex));
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

    public Mensaje getIncapacidadPorEmpleadoYFecha(String claveEmpleado, Date fechaInicio, Date fechaFinal, String uuidCxn) {
        List<RegistroIncapacidad> values;
        try {
            inicializaVariableMensaje();
            setSession(HibernateUtil.currentSession(uuidCxn));
            getSession().beginTransaction();
            //c.empleados.clave = :claveEmpleado
            consulta = new StringBuilder();
            consulta.append("FROM RegistroIncapacidad c WHERE c.fechaInicial between :fechaInicio AND :fechaFinal or c.fechaFinal between :fechaInicio AND :fechaFinal ");
            if (claveEmpleado != null) {
                consulta.append("AND c.empleados.clave = :claveEmpleado");
            }
            q = getSession().createQuery(consulta.toString());
            if (claveEmpleado != null) {
                q.setParameter("claveEmpleado", claveEmpleado);
            }
            q.setDate("fechaInicio", fechaInicio);
            q.setDate("fechaFinal", fechaFinal);
            values = (List<RegistroIncapacidad>) q.list();
            mensajeResultado.setResultado(values);
            mensajeResultado.setNoError(0);
            mensajeResultado.setError("");
            getSession().getTransaction().commit();
        } catch (HibernateException ex) {
            System.err.println(concatena.delete(0, concatena.length()).append(msgError).append("getIncapacidadPorEmpleadoYFecha()1_Error: ").append(ex));
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

    public Mensaje getRegistroIncapacidadPorClave(String clave, String claveRazonesSocial, String uuidCxn) {
        RegistroIncapacidad r;
        try {
            inicializaVariableMensaje();
            setSession(HibernateUtil.currentSession(uuidCxn));
            getSession().beginTransaction();
            q = getSession().createQuery("from RegistroIncapacidad c where c.clave=:clave and c.razonesSociales.clave=:claveRazonSocial");
            q.setParameter("clave", clave);
            q.setParameter("claveRazonSocial", claveRazonesSocial);
            r = (RegistroIncapacidad) q.uniqueResult();
            mensajeResultado.setResultado(r);
            mensajeResultado.setNoError(0);
            mensajeResultado.setError("");
            getSession().getTransaction().commit();
        } catch (HibernateException ex) {
            System.err.println(concatena.delete(0, concatena.length()).append(msgError).append("getRegistroIncapacidadPorClave()1_Error: ").append(ex));
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

    public Mensaje getRegistroIncapacidadPorClaveYRazon(String clave, String claveRazon, String uuidCxn) {
        RegistroIncapacidad r;
        try {
            inicializaVariableMensaje();
            setSession(HibernateUtil.currentSession(uuidCxn));
            getSession().beginTransaction();
            q = getSession().createQuery("from RegistroIncapacidad c where c.clave=:clave and c.razonesSociales.clave=:claveRazon");
            q.setParameter("clave", clave);
            q.setParameter("claveRazon", claveRazon);
            r = (RegistroIncapacidad) q.uniqueResult();
            mensajeResultado.setResultado(r);
            mensajeResultado.setNoError(0);
            mensajeResultado.setError("");
            getSession().getTransaction().commit();
        } catch (HibernateException ex) {
            System.err.println(concatena.delete(0, concatena.length()).append(msgError).append("getRegistroIncapacidadPorClaveYRazon()1_Error: ").append(ex));
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

    public Mensaje consultaPorRangosIncapacidad(Integer inicio, Integer rango, String[] camposWhere, Object[] camposWhereValores, String uuidCxn) {
        try {
            inicializaVariableMensaje();
            setSession(HibernateUtil.currentSession(uuidCxn));
            getSession().beginTransaction();
            listEsp = (List<RegistroIncapacidad>) consultaPorRangos(inicio, rango, camposWhere, camposWhereValores);
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

    public Mensaje consultaPorFiltrosIncapacidad(String query, Object[] campos, Object[] valores, String uuidCxn) {
        try {
            inicializaVariableMensaje();
            setSession(HibernateUtil.currentSession(uuidCxn));
            getSession().beginTransaction();
            listEsp = (List<RegistroIncapacidad>) consultaPorFiltros(query, campos, valores, null, null);
            mensajeResultado.setResultado(listEsp);
            mensajeResultado.setNoError(0);
            mensajeResultado.setError("");
            getSession().getTransaction().commit();
        } catch (HibernateException ex) {
            System.err.println(concatena.delete(0, concatena.length()).append(msgError).append("consultaPorFiltros()1_Error: ").append(ex));
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

    public Mensaje saveDeleteRegistroIncapacidad(RegistroIncapacidad incapacidad, Object[] clavesDeleteIncapacidad, int rango, Empleados empleados, RazonesSociales razonesSociales, Date fechaInicial, Date fechaFinal, Date fechaInicialAnterior, Date fechaFinalAnterior, Object claveExcepcion, String formatoFecha, Date fechaIniEmpalme, Date fechaFinEmpalme, String uuidCxn, String uuidCxnMaestra) {//JSA01
        try {
            commit = true;
            inicializaVariableMensaje();
            RegistroIncapacidad registro = null;
            setSession(HibernateUtil.currentSession(uuidCxnMaestra));
            getSession().beginTransaction();
            StringBuilder builder = new StringBuilder();
            builder.append("from TablaDatos o where o.tablaBase.clave = :clave ");
            builder.append("AND o.id = (SELECT MAX(t.id) FROM TablaDatos t WHERE t.tablaBase.id  = o.tablaBase.id  ");
            builder.append("AND t.controlPorFecha <= :fechaActual)");
            q = getSession().createQuery(builder.toString());
            q.setParameter("clave", ClavesParametrosModulos.claveTipoTablaDiasFestivos);
            q.setDate("fechaActual", Calendar.getInstance().getTime());
            List<TablaDatos> organized = (List<TablaDatos>) q.list();
            organized = organized == null ? new ArrayList<TablaDatos>() : organized;
            List<Date> diasFestivos = new ArrayList<Date>();
            if (organized.size() > 0) {
                byte[] convert = organized.get(0).getFileXml();
                Object[][] dias = UtilidadesXML.extraeValoresNodos(UtilidadesXML.convierteBytesToXML(convert));
                SimpleDateFormat formato = new SimpleDateFormat(formatoFecha);
                Date fecha;
                int i;
                for (i = 0; i < dias.length; i++) {
                    try {
                        fecha = formato.parse(dias[i][0].toString());
                    } catch (ParseException ex) {
                        fecha = null;
                    }
                    if (fecha != null) {
                        diasFestivos.add(fecha);
                    }
                }
            }

            getSession().getTransaction().commit();

            setSession(HibernateUtil.currentSession(uuidCxn));
            getSession().beginTransaction();
            if (clavesDeleteIncapacidad != null) {
                if (clavesDeleteIncapacidad.length > 0) {
                    commit = deleteListQuerys("RegistroIncapacidad", "id", clavesDeleteIncapacidad);
                    registro = new RegistroIncapacidad();
                    getSession().clear();
                }
            }
            if (commit) {
                Object[] clavesDetallesAsistencia = getDetalleAsistencia(empleados, fechaInicialAnterior, fechaFinalAnterior, razonesSociales);
                if (clavesDetallesAsistencia != null) {
                    if (commit && clavesDetallesAsistencia.length > 0) {
                        commit = deleteListQuerys(DetalleAsistencia.class.getSimpleName(), "id", clavesDetallesAsistencia);
                        getSession().clear();
                    }
                }
            }
            if (commit) {//JSA02
                List<Asistencias> asistencias = getAsistenciasPorRangoFechas(empleados.getClave(), fechaInicial, fechaFinal, razonesSociales.getClave());
                if (mensajeResultado.getNoError() == 0) {
                    if (asistencias != null) {
                        if (asistencias.size() > 0) {
                            Object[] clavesDetallesAsistencia = new Object[asistencias.size()];
                            for (int i = 0; i < asistencias.size(); i++) {
                                /// getSession().delete(asistencias.get(i));
                                clavesDetallesAsistencia[i] = asistencias.get(i).getId();
                            }
                            commit = deleteListQuerys(Asistencias.class.getSimpleName(), "id", clavesDetallesAsistencia);
                            getSession().clear();
                        }
                    }
                }
            }

            if (commit & incapacidad != null) {
                DatosGlobales datosGlobales = cargaDatosGlobales(empleados, razonesSociales, fechaInicial, fechaFinal, claveExcepcion);
//                if (mensajeResultado.getNoError() != 0) {
//                    return mensajeResultado;
//                }
                List<Asistencias> asistencias = obtenerAsistencias(incapacidad, empleados, fechaInicialAnterior, fechaFinalAnterior, fechaInicial, fechaFinal, razonesSociales, datosGlobales, diasFestivos, fechaIniEmpalme, fechaFinEmpalme);
//                if (mensajeResultado.getNoError() != 0) {
//                    return mensajeResultado;
//                }
                commit = agregarListaAsistencias(asistencias, rango);
            }

            if (commit & incapacidad != null) {
                registro = (RegistroIncapacidad) makePersistent(incapacidad);
            }

            if (commit) {
                mensajeResultado.setResultado(registro);
                mensajeResultado.setNoError(0);
                mensajeResultado.setError("");
//                getSession().flush();
                getSession().getTransaction().commit();
            } else {
                mensajeResultado.setResultado(null);
                getSession().getTransaction().rollback();
            }
        } catch (LockAcquisitionException ex) {
            System.err.println(concatena.delete(0, concatena.length()).append(msgError).append("saveDeleteRegistroIncapacidad()1_Error: ").append(ex));
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
        } catch (HibernateException ex) {
            System.err.println(concatena.delete(0, concatena.length()).append(msgError).append("saveDeleteRegistroIncapacidad()1_Error: ").append(ex));
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

    private DatosGlobales cargaDatosGlobales(Empleados empleados, RazonesSociales razonesSociales, Date fechaInicial, Date fechaFinal, Object claveExcepcion) {
        DatosGlobales dg = new DatosGlobales();
        int i;
        List<PlazasPorEmpleadosMov> plazas = getPorEmpleadoYRazonSocialVigente(empleados.getClave(), razonesSociales.getClave(), fechaFinal);
        if (mensajeResultado.getNoError() != 0) {
            return null;
        }
        Excepciones excep, excepLaborado, excepFestivo;
        excep = getExcepcionPorClave(claveExcepcion);
        if (mensajeResultado.getNoError() != 0) {
            return null;
        }
        excepLaborado = getExcepcionPorClave(ClavesParametrosModulos.claveExcepcionLaborado);
        if (mensajeResultado.getNoError() != 0) {
            return null;
        }
        excepFestivo = getExcepcionPorClave(ClavesParametrosModulos.claveExcepcionFestivo);
        if (mensajeResultado.getNoError() != 0) {
            return null;
        }
        Calendar inicioPeriodo = Calendar.getInstance(), finPeriodo = Calendar.getInstance();
        inicioPeriodo.setTime(fechaInicial);
        finPeriodo.setTime(fechaFinal);

        HashMap mapTipoNomina = new HashMap();
        for (i = 0; i < plazas.size(); i++) {
            List<PeriodosNomina> periodos = getPeriodosNominaPorTipoNominaYRangoDeFechas(inicioPeriodo.getTime(), finPeriodo.getTime(), plazas.get(i).getTipoNomina().getClave());
            if (mensajeResultado.getNoError() != 0) {
                return null;
            }
            periodos = periodos == null ? new ArrayList<PeriodosNomina>() : periodos;
            mapTipoNomina.put(plazas.get(i).getTipoNomina().getClave(), periodos);
        }
        dg.setClaveExcepcion(claveExcepcion);
        dg.setExcepDefault(excepLaborado);
        dg.setExcepIncapacidad(excep);
        dg.setExcepFestivo(excepFestivo);
        dg.setMapTipoNomina(mapTipoNomina);
        dg.setPlazas(plazas);
        return dg;
    }

    private class DatosGlobales {

        List<PlazasPorEmpleadosMov> plazas;
        Object claveExcepcion = "";
        Excepciones excepIncapacidad;
        Excepciones excepDefault;
        Excepciones excepFestivo;
        HashMap mapTipoNomina = new HashMap();

        public Object getClaveExcepcion() {
            return claveExcepcion;
        }

        public void setClaveExcepcion(Object claveExcepcion) {
            this.claveExcepcion = claveExcepcion;
        }

        public Excepciones getExcepDefault() {
            return excepDefault;
        }

        public void setExcepDefault(Excepciones excepDefault) {
            this.excepDefault = excepDefault;
        }

        public Excepciones getExcepIncapacidad() {
            return excepIncapacidad;
        }

        public void setExcepIncapacidad(Excepciones excepIncapacidad) {
            this.excepIncapacidad = excepIncapacidad;
        }

        public Excepciones getExcepFestivo() {
            return excepFestivo;
        }

        public void setExcepFestivo(Excepciones excepFestivo) {
            this.excepFestivo = excepFestivo;
        }

        public HashMap getMapTipoNomina() {
            return mapTipoNomina;
        }

        public void setMapTipoNomina(HashMap mapTipoNomina) {
            this.mapTipoNomina = mapTipoNomina;
        }

        public List<PlazasPorEmpleadosMov> getPlazas() {
            return plazas;
        }

        public void setPlazas(List<PlazasPorEmpleadosMov> plazas) {
            this.plazas = plazas;
        }
    }

    private List<PlazasPorEmpleadosMov> getPorEmpleadoYRazonSocialVigente(String claveEmpleado, String claveRazonSocial, Date fechaFinal) {
        List<PlazasPorEmpleadosMov> values = null;
        try {
//            inicializaVariableMensaje();
//            setSession(HibernateUtil.currentSession(uuidCxn));
//            getSession().beginTransaction();
            consulta = new StringBuilder("FROM PlazasPorEmpleadosMov o ");
            consulta.append("WHERE o.id IN (SELECT MAX(m.id) FROM PlazasPorEmpleadosMov m WHERE m.plazasPorEmpleado.empleados.clave=:claveEmpleado AND m.plazasPorEmpleado.razonesSociales.clave =:claveRazonSocial ").append("AND :fechaActual BETWEEN  m.fechaInicial AND m.plazasPorEmpleado.fechaFinal + 1 GROUP BY m.plazasPorEmpleado.clave) ").append("ORDER BY o.plazasPorEmpleado.clave");
            q = getSession().createQuery(consulta.toString());
            q.setParameter("fechaActual", fechaFinal);
            q.setParameter("claveEmpleado", claveEmpleado);
            q.setParameter("claveRazonSocial", claveRazonSocial);
            values = q.list();
            mensajeResultado.setResultado(values);
            mensajeResultado.setNoError(0);
            mensajeResultado.setError("");
//            getSession().getTransaction().commit();
        } catch (HibernateException ex) {
            commit = false;
            System.err.println(concatena.delete(0, concatena.length()).append(msgError).append("getPorEmpleadoYRazonSocialVigente()1_Error: ").append(ex));
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
        return values;
    }

    private Excepciones getExcepcionPorClave(Object clave) {//AAP01
        Excepciones e = null;
        try {
//            inicializaVariableMensaje();
//            setSession(HibernateUtil.currentSession(uuidCxn));
//            getSession().beginTransaction();
            q = getSession().createQuery("from Excepciones where clave = :clave");
            q.setParameter("clave", clave);
            e = (Excepciones) q.uniqueResult();
            mensajeResultado.setResultado(e);
            mensajeResultado.setNoError(0);
            mensajeResultado.setError("");
//            getSession().getTransaction().commit();
        } catch (HibernateException ex) {
            commit = false;
            System.err.println(concatena.delete(0, concatena.length()).append(msgError).append("getExcepcionPorClave()1_Error: ").append(ex));
            try {
                if (getSession().getTransaction().isActive()) {
                    getSession().getTransaction().rollback();
                }
                mensajeResultado.setNoError(ControlErroresHibernate.buscaNoErrorPorExcepcion(ex.getClass()));
                mensajeResultado.setError(ex.getLocalizedMessage());
            } catch (HibernateException exc) { //cuando marca error al hacer rollback pro ejemplo se cae conexion servidor
                mensajeResultado.setNoError(ControlErroresHibernate.buscaNoErrorPorExcepcion(exc.getClass()));
                mensajeResultado.setError(exc.getLocalizedMessage());
            }
            mensajeResultado.setResultado(null);
        }
        return e;
    }

    private List<PeriodosNomina> getPeriodosNominaPorTipoNominaYRangoDeFechas(Date fechaInicial, Date fechaFinal, String claveTipoNomina) {
        List<PeriodosNomina> values = null;
        try {
//            inicializaVariableMensaje();
//            setSession(HibernateUtil.currentSession(uuidCxn));
//            getSession().beginTransaction();
            q = getSession().createQuery("from PeriodosNomina p where p.tipoNomina.clave =:claveTipoNomina and p.tipoCorrida.clave =:claveTipoCorrida and p.fechaFinal >=:fechaInicial and p.fechaInicial <=:fechaFinal ");
            q.setDate("fechaInicial", fechaInicial);
            q.setDate("fechaFinal", fechaFinal);
            q.setParameter("claveTipoNomina", claveTipoNomina);
            q.setParameter("claveTipoCorrida", "PER");
            values = q.list();
            mensajeResultado.setResultado(values);
            mensajeResultado.setNoError(0);
            mensajeResultado.setError("");
//            getSession().getTransaction().commit();
        } catch (HibernateException ex) {
            commit = false;
            System.err.println(concatena.delete(0, concatena.length()).append(msgError).append("getPeriodosNominaPorTipoNominaYRangoDeFechas()1_Error: ").append(ex));
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
        return values;
    }

    private List<Asistencias> obtenerAsistencias(RegistroIncapacidad registroIncapacidad, Empleados empleados, Date fechaInicialAnterior, Date fechaFinalAnterior, Date fechaInicial, Date fechaFinal, RazonesSociales razonesSociales, DatosGlobales datoGlobal, List<Date> diasFestivos, Date fechaIniEmpalme, Date fechaFinEmpalme) {
        List<Asistencias> asistencias;
        if (fechaInicialAnterior == null | fechaFinalAnterior == null) {
            asistencias = getAsistenciasPorRangoFechas(empleados.getClave(), fechaInicial, fechaFinal, razonesSociales.getClave());
        } else {
            asistencias = getAsistenciasPorRangoFechas(empleados.getClave(), fechaInicialAnterior, fechaFinalAnterior, razonesSociales.getClave());
        }

        if (mensajeResultado.getNoError() != 0) {
            return null;
        }
        asistencias = asistencias == null ? new ArrayList<Asistencias>() : asistencias;
        int i;
        boolean usaTiempoExtra = false;
        for (i = 0; i < asistencias.size(); i++) {
            if (asistencias.get(i).getExcepciones().getClave().equalsIgnoreCase(ClavesParametrosModulos.claveExcepcionTiempoExtra.toString())
                    | asistencias.get(i).getExcepciones().getClave().equalsIgnoreCase(ClavesParametrosModulos.claveExcepcionExtraDoble.toString())
                    | asistencias.get(i).getExcepciones().getClave().equalsIgnoreCase(ClavesParametrosModulos.claveExcepcionExtraTriple.toString())) {
                usaTiempoExtra = true;
                asistencias.get(i).setCantidad(null);
            }
        }

        asistencias = creaListaAsistenciasIncapacidad(asistencias, registroIncapacidad, datoGlobal, razonesSociales, diasFestivos, fechaIniEmpalme, fechaFinEmpalme, fechaInicial, fechaFinal);
        return asistencias;
    }

    private List<Asistencias> creaListaAsistenciasIncapacidad(List<Asistencias> asistencias, RegistroIncapacidad registroIncapacidad, DatosGlobales datoGlobal, RazonesSociales razonesSociales, List<Date> diasFestivos, Date fechaIniEmpalme, Date fechaFinEmpalme, Date fechaInicial, Date fechaFinal) {
        int i, j, k, cont = 0, orden = 0;
        TipoNomina nomina;
        List<PeriodosNomina> periodos;
        PeriodosNomina periodo = null;
        Asistencias as;
        try {
            Calendar fechaInicio = Calendar.getInstance();
            if (asistencias.isEmpty()) {
                fechaInicio.setTime(registroIncapacidad.getFechaInicial());

                for (i = 0; i < registroIncapacidad.getDiasIncapacidad(); i++) {
                    fechaInicio.set(Calendar.DATE, fechaInicio.get(Calendar.DATE) + cont);
                    cont = 1;
                    for (k = 0; k < datoGlobal.getPlazas().size(); k++) {
                        nomina = datoGlobal.getPlazas().get(k).getTipoNomina();
                        periodos = (List<PeriodosNomina>) datoGlobal.getMapTipoNomina().get(nomina.getClave());
                        for (j = 0; j < periodos.size(); j++) {
                            if (fechaInicio.getTime().after(periodos.get(j).getFechaInicial()) && fechaInicio.getTime().before(periodos.get(j).getFechaFinal()) || fechaInicio.getTime().equals(periodos.get(j).getFechaFinal()) || fechaInicio.getTime().equals(periodos.get(j).getFechaInicial())) {
                                periodo = periodos.get(j);
                                break;
                            }
                        }
                        as = creaAsistenciaIncapacidad(registroIncapacidad.getEmpleados(), nomina, fechaInicio.getTime(), datoGlobal.getExcepIncapacidad(), periodo, razonesSociales);
                        as.setOrdenId(orden);
                        orden++;
                        asistencias.add(as);
                    }
                }
            } else {
                DateFormat format = new SimpleDateFormat("dd-MMMM-yyyy");
                List<Asistencias> cambiosAsistencias = new ArrayList<Asistencias>();
                List<Calendar> listFechas = new ArrayList<Calendar>();
                for (i = 0; i < registroIncapacidad.getDiasIncapacidad(); i++) {
                    fechaInicio = Calendar.getInstance();
                    fechaInicio.setTime(registroIncapacidad.getFechaInicial());
                    fechaInicio.set(Calendar.DATE, fechaInicio.get(Calendar.DATE) + i);
                    listFechas.add(fechaInicio);
                   ///// System.out.println("i " + i + " fechaInicio " + format.format(fechaInicio.getTime()));
                    fechaInicio = null;
                }
                cont = 0;
                j = 0;
                boolean existe;
                fechaInicio = Calendar.getInstance();
                while (j < asistencias.size()) {
                    existe = false;
                    cont = 0;
                    fechaInicio.setTime(registroIncapacidad.getFechaInicial());
                    for (i = 0; i < registroIncapacidad.getDiasIncapacidad(); i++) {
                        fechaInicio.set(Calendar.DATE, fechaInicio.get(Calendar.DATE) + cont);
                        cont = 1;

                        if (fechaInicio.getTime().equals(asistencias.get(j).getFecha())) {
                            existe = true;
                            ////////System.out.println("remove" + format.format(fechaInicio.getTime()));
                            listFechas.remove(fechaInicio);
                            break;
                        }
                    }
                    if (existe) {
                        asistencias.get(j).setExcepciones(datoGlobal.getExcepIncapacidad());
                        cambiosAsistencias.add(asistencias.get(j));
                        asistencias.remove(j);
                    } else {
                        j++;
                    }
                }
                for (i = 0; i < listFechas.size(); i++) {
                    fechaInicio = listFechas.get(i);
                    ///////System.out.println("new " + format.format(fechaInicio.getTime()));
                    for (k = 0; k < datoGlobal.getPlazas().size(); k++) {
                        nomina = datoGlobal.getPlazas().get(k).getTipoNomina();
                        periodos = (List<PeriodosNomina>) datoGlobal.getMapTipoNomina().get(nomina.getClave());
                        for (j = 0; j < periodos.size(); j++) {
                            if (fechaInicio.getTime().after(periodos.get(j).getFechaInicial()) && fechaInicio.getTime().before(periodos.get(j).getFechaFinal()) || fechaInicio.getTime().equals(periodos.get(j).getFechaFinal()) || fechaInicio.getTime().equals(periodos.get(j).getFechaInicial())) {
                                periodo = periodos.get(j);
                                break;
                            }
                        }
                        as = creaAsistenciaIncapacidad(registroIncapacidad.getEmpleados(), nomina, fechaInicio.getTime(), datoGlobal.getExcepIncapacidad(), periodo, razonesSociales);
                        cambiosAsistencias.add(as);
                    }
                }
                boolean isDiasFestivo;
                for (i = 0; i < asistencias.size(); i++) {
                    isDiasFestivo = false;
                    for (j = 0; j < diasFestivos.size(); j++) {
                        if (diasFestivos.get(j).equals(asistencias.get(i).getFecha())) {
                            isDiasFestivo = true;
                            break;
                        }
                    }
                    if (isDiasFestivo) {
                        asistencias.get(i).setExcepciones(datoGlobal.getExcepFestivo());
                        asistencias.get(i).setJornada(0.0);
                    } else {
                        //if(asistencias.get(i).getFecha().compareTo(f)

                        if (fechaIniEmpalme != null & fechaFinEmpalme != null) {
                            if (!(asistencias.get(i).getFecha().compareTo(fechaIniEmpalme) >= 0 && asistencias.get(i).getFecha().compareTo(fechaFinEmpalme) <= 0)) {
                                asistencias.get(i).setExcepciones(datoGlobal.getExcepDefault());
                                asistencias.get(i).setJornada(8.0);
                            }
                        } else {
                            if (!(asistencias.get(i).getFecha().compareTo(fechaInicial) >= 0 && asistencias.get(i).getFecha().compareTo(fechaFinal) <= 0)) {
                                asistencias.get(i).setExcepciones(datoGlobal.getExcepDefault());
                                asistencias.get(i).setJornada(8.0);
                            }
                        }
                    }
                    //cambiosAsistencias.add(asistencias.get(i));
                    //getSession().delete(asistencias.get(i));
                }
                Utilerias.ordena(cambiosAsistencias, "Fecha");
                for (i = 0; i < cambiosAsistencias.size(); i++) {
                    cambiosAsistencias.get(i).setOrdenId(i);
                }
                asistencias = cambiosAsistencias;
            }
        } catch (Exception ex) {
            commit = false;
            mensajeResultado.setNoError(200);
            mensajeResultado.setError(ex.getLocalizedMessage());
        }
        return asistencias;
    }

    private List<Asistencias> getAsistenciasPorRangoFechas(String claveEmpleado, Date fechaInicio, Date fechaFinal, String claveRazonesSociales) {
        List<Asistencias> values = null;
        try {
            q = getSession().createQuery("FROM Asistencias c WHERE c.empleados.clave = :claveEmpleado AND c.fecha BETWEEN :fechaInicio AND :fechaFinal and c.razonesSociales.clave = :claveRazonesSociales and c.empleados.razonesSociales.clave = :claveRazonesSociales ");
            q.setParameter("claveEmpleado", claveEmpleado);
            q.setDate("fechaInicio", fechaInicio);
            q.setDate("fechaFinal", fechaFinal);
            q.setParameter("claveRazonesSociales", claveRazonesSociales);
            values = (List<Asistencias>) q.list();
            mensajeResultado.setResultado(values);
            mensajeResultado.setNoError(0);
            mensajeResultado.setError("");
        } catch (LockAcquisitionException ex) {
            commit = false;
            System.err.println(concatena.delete(0, concatena.length()).append(msgError).append("saveDeleteRegistroIncapacidad()1_Error: ").append(ex));
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
        } catch (HibernateException ex) {
            commit = false;
            System.err.println(concatena.delete(0, concatena.length()).append(msgError).append("getAsistenciasPorRangoFechas()1_Error: ").append(ex));
            try {
                if (getSession().getTransaction().isActive()) {
                    getSession().getTransaction().rollback();
                }
                mensajeResultado.setNoError(ControlErroresHibernate.buscaNoErrorPorExcepcion(ex.getClass()));
                mensajeResultado.setError(ex.getLocalizedMessage());
            } catch (HibernateException exc) { //cuando marca error al hacer rollback pro ejemplo se cae conexion servidor
                mensajeResultado.setNoError(ControlErroresHibernate.buscaNoErrorPorExcepcion(exc.getClass()));
                mensajeResultado.setError(exc.getLocalizedMessage());
            }
            mensajeResultado.setResultado(null);
        }
        return values;
    }

    private List<DetalleAsistencia> getDetalleAsistenciaPorRangoFechas(String claveEmpleado, Date fechaInicio, Date fechaFinal, String claveRazonesSociales) {
        List<DetalleAsistencia> values = null;
        try {
            q = getSession().createQuery("from DetalleAsistencia o where o.empleados.clave = :claveEmpleado and o.razonesSociales.clave = :claveRazonesSociales and o.dia between :fechaInicial and :fechaFinal");
            q.setParameter("claveEmpleado", claveEmpleado);
            q.setDate("fechaInicial", fechaInicio);
            q.setDate("fechaFinal", fechaFinal);
            q.setParameter("claveRazonesSociales", claveRazonesSociales);
            values = (List<DetalleAsistencia>) q.list();
            mensajeResultado.setResultado(values);
            mensajeResultado.setNoError(0);
            mensajeResultado.setError("");
        } catch (HibernateException ex) {
            commit = false;
            System.err.println(concatena.delete(0, concatena.length()).append(msgError).append("getAsistenciasPorRangoFechas()1_Error: ").append(ex));
            try {
                if (getSession().getTransaction().isActive()) {
                    getSession().getTransaction().rollback();
                }
                mensajeResultado.setNoError(ControlErroresHibernate.buscaNoErrorPorExcepcion(ex.getClass()));
                mensajeResultado.setError(ex.getLocalizedMessage());
            } catch (HibernateException exc) { //cuando marca error al hacer rollback pro ejemplo se cae conexion servidor
                mensajeResultado.setNoError(ControlErroresHibernate.buscaNoErrorPorExcepcion(exc.getClass()));
                mensajeResultado.setError(exc.getLocalizedMessage());
            }
            mensajeResultado.setResultado(null);
        }
        return values;
    }

    private Object[] getDetalleAsistencia(Empleados empleados, Date fechaInicial, Date fechaFinal, RazonesSociales razonesSociales) {
        Object[] clavesDetallesAsistencia = null;
        List<DetalleAsistencia> detalleAsis = getDetalleAsistenciaPorRangoFechas(empleados.getClave(), fechaInicial, fechaFinal, razonesSociales.getClave());

        if (mensajeResultado.getNoError() != 0) {
            return clavesDetallesAsistencia;
        }
        detalleAsis = (detalleAsis == null) ? new ArrayList<DetalleAsistencia>() : detalleAsis;
        if (detalleAsis.size() > 0) {
            clavesDetallesAsistencia = new Object[detalleAsis.size()];
            for (int i = 0; i < detalleAsis.size(); i++) {
                clavesDetallesAsistencia[i] = detalleAsis.get(i).getId();
            }
        }
        return clavesDetallesAsistencia;
    }

    private Asistencias creaAsistenciaIncapacidad(Empleados emp, TipoNomina nomina, Date fecha, Excepciones excepcion, PeriodosNomina periodo, RazonesSociales razonesSociales) {
        List<Asistencias> listAsistencia = getAsistenciasPorRangoFechas(emp.getClave(), fecha, fecha, razonesSociales.getClave());//JSA02
        Asistencias asist = new Asistencias();
        if (listAsistencia == null) {
            listAsistencia = new ArrayList<Asistencias>();
        }
        if (!listAsistencia.isEmpty()) {
            asist = listAsistencia.get(0);
        }
        asist.setRazonesSociales(razonesSociales);
        asist.setEmpleados(emp);
        asist.setTipoNomina(nomina);
        asist.setPeriodosNomina(periodo);
        asist.setExcepciones(excepcion);
        asist.setFecha(fecha);
        asist.setJornada(8.0);
        asist.setTipoPantalla(100);
        return asist;
    }

    private boolean agregarListaAsistencias(List<Asistencias> asistencias, int rango) {
        boolean exito = true;
        try {
            int i;
            for (i = 0; i < asistencias.size(); i++) {
                makePersistent(asistencias.get(i));
                if (i % rango == 0 & i > 0) {
                    flush();
                    clear();
                }
            }
        } catch (HibernateException ex) {
            System.err.println(concatena.delete(0, concatena.length()).append(msgError).append("agregarListaAsistencias()1_Error: ").append(ex));
            mensajeResultado.setNoError(ControlErroresHibernate.buscaNoErrorPorExcepcion(ex.getClass()));
            mensajeResultado.setError(ex.getLocalizedMessage());
            mensajeResultado.setResultado(null);
            exito = false;
        }
        return exito;
    }

    private boolean deleteListQuerys(String tabla, String campo, Object[] valores) {
        boolean exito = true;
        try {
            deleteListQuery(tabla, campo, valores);
        } catch (HibernateException ex) {
            System.err.println(concatena.delete(0, concatena.length()).append(msgError).append("deleteListQuerys()1_Error: ").append(ex));
            mensajeResultado.setNoError(ControlErroresHibernate.buscaNoErrorPorExcepcion(ex.getClass()));
            mensajeResultado.setError(ex.getLocalizedMessage());
            mensajeResultado.setResultado(null);
            exito = false;
        }
        return exito;
    }

    public Mensaje existeDato(String campo, Object valor, String uuidCxn) {
        Boolean existe;
        try {
            inicializaVariableMensaje();
            setSession(HibernateUtil.currentSession(uuidCxn));
            getSession().beginTransaction();
            existe = existeDato("RegistroIncapacidad", campo, valor);
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
}
