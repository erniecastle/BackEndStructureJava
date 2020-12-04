/**
 * @author: Ernesto Valenzuela Fecha de Creación: 16/05/2016 Compañía: Exito
 * Software. Descripción del programa: clase VacacionesDevengadasDAO para
 * llamados a metodos de HIBERNATE
 * -----------------------------------------------------------------------------
 * MODIFICACIONES:
 * ------------------------------------------------------------------------------
 */
package com.mef.erp.modelo;

import com.mef.erp.modelo.entidad.ControlVacDeveng;
import com.mef.erp.modelo.entidad.Mensaje;
import com.mef.erp.modelo.entidad.PlazasPorEmpleado;
import com.mef.erp.modelo.entidad.RazonesSociales;
import com.mef.erp.modelo.entidad.TablaDatos;
import com.mef.erp.modelo.entidad.VacacionesDevengadas;
import com.mef.erp.util.ControlErroresHibernate;
import com.mef.erp.util.HibernateUtil;
import com.mef.erp.util.UtilidadesXML;
import java.text.DateFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import org.hibernate.HibernateException;

/**
 *
 * @author Ernesto
 */
public class VacacionesDevengadasDAO extends GenericHibernateDAO<VacacionesDevengadas, Long>
        implements VacacionesDevengadasIF {

    private final StringBuilder concatena = new StringBuilder(200);
    private final String msgError = concatena.append("ServerERP").append(".MSERR_C_").append(this.getClass().getName()).append(".").toString();
    private StringBuilder query = new StringBuilder();

    @Override
    public Mensaje getVacacionesDevengadasAll(String uuidCxn) {
        List<VacacionesDevengadas> values;
        try {
            inicializaVariableMensaje();
            setSession(HibernateUtil.currentSession(uuidCxn));
            getSession().beginTransaction();
            q = getSession().createQuery("from VacacionesDevengadas");
            values = q.list();
            mensajeResultado.setResultado(values);
            mensajeResultado.setNoError(0);
            mensajeResultado.setError("");
            getSession().getTransaction().commit();
        } catch (HibernateException ex) {
            System.err.println(concatena.delete(0, concatena.length()).append(msgError).append("getVacacionesDevengadasAll()1_Error: ").append(ex));
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

    @Override
    public Mensaje calcularVacacionesDevengadasEmpleados(RazonesSociales razonesSociales, String uuidCxn, String uuidCxnMaestra) {
        Object[][] reglaFactor = null;
        try {
            inicializaVariableMensaje();
            setSession(HibernateUtil.currentSession(uuidCxn));
            getSession().beginTransaction();
            query.delete(0, query.length());
            //Obtiene el control de vacaciones por día
            query.append("from ControlVacDeveng o ");
            query.append("where o.razonesSociales.clave =:claveRazonsocial ");
            query.append("AND o.fecha = (select MAX(o.fecha) from ControlVacDeveng o) ");
            q = getSession().createQuery(query.toString());
            q.setParameter("claveRazonsocial", razonesSociales.getClave());
            ControlVacDeveng control = (ControlVacDeveng) q.uniqueResult();

            Date fechaUltimDev = null;
            List<Date> diasPendientes = null;
            if (control == null) {
                diasPendientes = new ArrayList<Date>();
                diasPendientes.add(new Date());
            } else {
                fechaUltimDev = control.getFecha();
                diasPendientes = getDaysBetweenDates(fechaUltimDev, new Date());
            }
            query.delete(0, query.length());

            int d = 0;
            ControlVacDeveng controlCalculadas = null;
            for (d = 0; d < diasPendientes.size(); d++) {
                if (!diasPendientes.isEmpty()) {
                    //Obtiene empleados que cumplen aniversario en la empresa al día
                    query.append("select o.plazasPorEmpleado from PlazasPorEmpleadosMov o ");
                    query.append("where o.id IN (Select MAX(m.id) from PlazasPorEmpleadosMov m   ");
                    query.append("where  m.plazasPorEmpleado.razonesSociales.clave =:claveRazonsocial ");
                    query.append("AND  m.plazasPorEmpleado.fechaFinal >= :fechaActual ");
                    query.append("AND month(o.plazasPorEmpleado.fechaPrestaciones)=month(:fechaActual) ");
                    query.append("AND day(o.plazasPorEmpleado.fechaPrestaciones)=day(:fechaActual)");
                    query.append("group by m.plazasPorEmpleado.empleados.clave)");
                    List<PlazasPorEmpleado> plazasEmpleados = null;
                    q = getSession().createQuery(query.toString());
                    q.setParameter("claveRazonsocial", razonesSociales.getClave());
                    q.setParameter("fechaActual", diasPendientes.get(d));
                    plazasEmpleados = q.list();
                    plazasEmpleados = plazasEmpleados == null ? new ArrayList<PlazasPorEmpleado>() : plazasEmpleados;
                    query.delete(0, query.length());

                    if (!plazasEmpleados.isEmpty()) {//Obtiene  los factores de integracion
                        if (reglaFactor == null) {
                            query.append("from TablaDatos o where o.tablaBase.clave = :clave ");
                            query.append("AND o.id = (SELECT MAX(t.id) FROM TablaDatos t WHERE t.tablaBase.id = o.tablaBase.id)");
                            setSession(HibernateUtil.currentSession(uuidCxnMaestra));
                            getSession().beginTransaction();
                            List<Object> values;
                            q = getSession().createQuery(query.toString());
                            q.setParameter("clave", "05");
                            values = q.list();
                            values = values == null ? new ArrayList<Object>() : values;
                            if (values.size() > 0) {
                                byte[] convert = ((TablaDatos) values.get(0)).getFileXml();
                                reglaFactor = UtilidadesXML.extraeValoresNodos(UtilidadesXML.convierteBytesToXML(convert));
                            }
                            getSession().getTransaction().commit();
                            query.delete(0, query.length());
                        }
                        //Llenar tabla de Vacaciones Devengadas por día
                        VacacionesDevengadas vd = null;
                        setSession(HibernateUtil.currentSession(uuidCxn));
                        getSession().beginTransaction();
                        for (int i = 0; i < plazasEmpleados.size(); i++) {
                            Double antiguedad = (Double) calcularAntiguedadExacta(plazasEmpleados.get(i).getFechaPrestaciones());
                            antiguedad.intValue();
                            //Obtiene vacaciones devengadas por año a ese empleado
                            query.append("select CASE WHEN (dev IS NULL) THEN 'NOCALCULADA' ELSE 'CALCULADA' END ");
                            query.append("from VacacionesDevengadas dev where dev.plazasPorEmpleado.id =:idplaza AND dev.ejercicio =:ejercicio");
                            q = getSession().createQuery(query.toString());
                            q.setParameter("idplaza", plazasEmpleados.get(i).getId());
                            q.setParameter("ejercicio", antiguedad.intValue());
                            String calculada = (String) q.uniqueResult();
                            if (calculada == null) {
                                vd = new VacacionesDevengadas();
                                vd.setRazonesSociales(plazasEmpleados.get(i).getRazonesSociales());
                                vd.setPlazasPorEmpleado(plazasEmpleados.get(i));
                                vd.setEjercicio(antiguedad.intValue());
                                Object[] factorEmpleado = (Object[]) obtieneFactorIntegracion(reglaFactor, antiguedad.intValue());
                                query.delete(0, query.length());
                                query.append("select MAX(sdi.fecha),sdi.salarioDiarioFijo from SalariosIntegrados sdi WHERE ");
                                query.append("empleados.id= :idEmpleado AND fecha <= :fechaPrestacion GROUP BY sdi.salarioDiarioFijo");
                                q = getSession().createQuery(query.toString());
                                q.setParameter("idEmpleado", plazasEmpleados.get(i).getEmpleados().getId());
                                q.setParameter("fechaPrestacion", plazasEmpleados.get(i).getFechaPrestaciones());
                                Object[] salarioAniv = (Object[]) q.uniqueResult();
                                if (salarioAniv == null) {
                                    vd.setSalarioAniversario(0D);
                                } else {
                                    vd.setSalarioAniversario((Double) salarioAniv[1]);
                                }
                                vd.setFactorPrima(Double.parseDouble(factorEmpleado[4].toString()));
                                vd.setDiasVacaciones(Integer.parseInt(factorEmpleado[3].toString()));
                                vd.setRegistroInicial(false);
                                double primaVac = Double.parseDouble(factorEmpleado[4].toString())
                                        / 100 * Integer.parseInt(factorEmpleado[3].toString());
                                vd.setDiasPrimavaca(primaVac);
                                getSession().save(vd);
                            }
                            query.delete(0, query.length());
                        }
                    }
                }
                controlCalculadas = new ControlVacDeveng();
                controlCalculadas.setFecha(diasPendientes.get(d));
                controlCalculadas.setRazonesSociales(razonesSociales);//razon
                getSession().save(controlCalculadas);
            }
            getSession().getTransaction().commit();
            mensajeResultado.setResultado(true);
            mensajeResultado.setNoError(0);
            mensajeResultado.setError("");
        } catch (HibernateException ex) {
            System.err.println(concatena.delete(0, concatena.length()).append(msgError).append("calcularVacacionesDevengadasEmpleados()1_Error: ").append(ex));
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

    @Override
    public Mensaje getVacacionesDenvengadasPorEmpleado(String claveEmpleado, String claveRazonSocial, String uuidCxn) {
        List<VacacionesDevengadas> values;
        try {
            inicializaVariableMensaje();
            setSession(HibernateUtil.currentSession(uuidCxn));
            getSession().beginTransaction();
            String query = "Select v from VacacionesDevengadas v   where v.plazasPorEmpleado.empleados.clave = :claveEmpleado and v.razonesSociales.clave = :claveRazonSocial";
            q = getSession().createQuery(query);
            q.setString("claveEmpleado", claveEmpleado);
            q.setParameter("claveRazonSocial", claveRazonSocial);
            values = q.list();
            mensajeResultado.setResultado(values);
            mensajeResultado.setNoError(0);
            mensajeResultado.setError("");
            getSession().getTransaction().commit();
        } catch (HibernateException ex) {
            System.err.println(concatena.delete(0, concatena.length()).append(msgError).append("getVacacionesPorEmpleadoAnio()1_Error: ").append(ex));
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

    private Object obtieneFactorIntegracion(Object[][] reglaFactor, Integer antiguedad) {
        int i = 0;
        for (i = 0; i < reglaFactor.length; i++) {
            Integer dataFact = Integer.parseInt(reglaFactor[i][0].toString());
            if (antiguedad <= dataFact) {
                break;
            }
        }
        return reglaFactor[i];
    }

    private Object calcularAntiguedadExacta(Date fechaInicial) {
        Date fechaFinal = new Date();
        DateFormat df = DateFormat.getDateInstance(DateFormat.MEDIUM);
        String fechaInicioString = df.format(fechaInicial);
        String fechaFinalString = df.format(fechaFinal);
        try {
            fechaInicial = df.parse(fechaInicioString);
            fechaFinal = df.parse(fechaFinalString);
            long fechaInicialMs, fechaFinalMs, diferencia;
            fechaInicialMs = fechaInicial.getTime();
            fechaFinalMs = fechaFinal.getTime();
            diferencia = fechaFinalMs - fechaInicialMs;
            double dias, antiguedad;
            dias = Math.floor(diferencia / (1000 * 60 * 60 * 24));
            antiguedad = dias / 365;
            return antiguedad;
        } catch (ParseException ex) {
            System.err.println(concatena.delete(0, concatena.length()).append(msgError).append("calcularAntiguedadExacta()1_Error: ").append(ex));
        }
        return 0.0;
    }

    private List<Date> getDaysBetweenDates(Date startdate, Date enddate) {
        List<Date> dates = new ArrayList<Date>();
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(startdate);
        calendar.add(Calendar.DATE, 1);

        while (calendar.getTime().before(enddate)) {
            Date result = calendar.getTime();
            dates.add(result);
            calendar.add(Calendar.DATE, 1);
        }
        return dates;
    }

    @Override
    public Mensaje saveDeleteVacacionesDevengadas(List<VacacionesDevengadas> entitysCambios, int rango, String uuidCxn) {
        List<VacacionesDevengadas> listEsp = new ArrayList<VacacionesDevengadas>();
        try {
            //commit = true;
            inicializaVariableMensaje();
            setSession(HibernateUtil.currentSession(uuidCxn));
            getSession().beginTransaction();
            //if (clavesDelete != null) {
            //  commit = deleteListQuerys("VacacionesDevengadas", "Id", clavesDelete);
            // if (commit) {
            //   getSession().flush();
            // getSession().clear();
            //}
            //}
            //entitysCambios = (entitysCambios == null ? new ArrayList<VacacionesDevengadas>() : entitysCambios);
            //if (commit && !entitysCambios.isEmpty()) {
            //  listEsp = agregarListaVacacionesDisfrutadas(entitysCambios, rango);

            //}
            //if (commit) 
            int i = 0;
            for (i = 0; i < entitysCambios.size(); i++) {
//                if (entitys.get(i).getId() == null) {
//                    listEsp.add(makePersistent(entitys.get(i)));
//                } else {
              listEsp.add(makePersistent(entitysCambios.get(i)));
//                }
//                if (i % rango == 0 & i > 0) {
//                    flush();
//                    clear();
//                }
            }
            mensajeResultado.setResultado(listEsp);
            mensajeResultado.setNoError(0);
            mensajeResultado.setError("");
            getSession().getTransaction().commit();
            //} else {
            
            //}
        } catch (HibernateException ex) {
            System.err.println(concatena.delete(0, concatena.length()).append(msgError).append("saveDeleteVacacionesDevengadas()1_Error: ").append(ex));
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
