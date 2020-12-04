/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mef.erp.modelo;

import com.mef.erp.modelo.entidad.Mensaje;
import com.mef.erp.modelo.entidad.PtuDatosGenerales;
import com.mef.erp.modelo.entidad.PtuEmpleados;
import com.mef.erp.util.ControlErroresHibernate;
import com.mef.erp.util.HibernateUtil;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import org.hibernate.HibernateException;

/**
 *
 * @author Desarrollo99
 */
public class CalculoPtuDAO extends GenericHibernateDAO<Object, Long> implements CalculoPtuDAOIF {

    private final StringBuilder concatena = new StringBuilder(200);
    private final String msgError = concatena.append("ServerERP").append(".MSERR_C_").append(this.getClass().getName()).append(".").toString();
    private StringBuilder query = new StringBuilder();

    @Override
    public Mensaje guardarCargaAcumulados(PtuDatosGenerales ptuDatosGenerales, List<PtuEmpleados> ptuEmpleados, String uuidCxn) {

        List<PtuEmpleados> listEsp = new ArrayList<PtuEmpleados>();
        try {
            inicializaVariableMensaje();
            setSession(HibernateUtil.currentSession(uuidCxn));
            getSession().beginTransaction();
            getSession().saveOrUpdate(ptuDatosGenerales);
            int i;
            for (i = 0; i < ptuEmpleados.size(); i++) {
                getSession().saveOrUpdate(ptuEmpleados.get(i));
                listEsp.add(ptuEmpleados.get(i));
                if (i % 50 == 0 & i > 0) {
                    flush();
                    clear();
                }
            }
            mensajeResultado.setResultado(new Object[]{listEsp, ptuDatosGenerales});
            mensajeResultado.setNoError(0);
            mensajeResultado.setError("");
            getSession().getTransaction().commit();
        } catch (HibernateException ex) {
            System.err.println(concatena.delete(0, concatena.length()).append(msgError).append("guardarCargaAcumulados()1_Error: ").append(ex));
            try {
                if (getSession().getTransaction().isActive()) {
                    getSession().getTransaction().rollback();
                }
                mensajeResultado.setNoError(ControlErroresHibernate.buscaNoErrorPorExcepcion(ex.getClass()));
                mensajeResultado.setError(ex.getLocalizedMessage());
            } catch (HibernateException exc) {
                mensajeResultado.setNoError(ControlErroresHibernate.buscaNoErrorPorExcepcion(exc.getClass()));
                mensajeResultado.setError(exc.getLocalizedMessage());
            }
            mensajeResultado.setResultado(null);
        }

        return mensajeResultado;
    }

    @Override
    public Mensaje cargaDeAcumulados(Integer ejercicio, String claveRazonsocial, String uuidCxn) {
        try {
            inicializaVariableMensaje();
            setSession(HibernateUtil.currentSession(uuidCxn));
            getSession().beginTransaction();
            query.delete(0, query.length());
            query.append("DELETE PtuEmpleados p WHERE p.id ");
            query.append("IN (SELECT ptuEm.id from PtuEmpleados ptuEm WHERE ptuEm.ejercicio =:anio ");
            query.append("AND ptuEm.razonesSociales.clave =:claveRazonsocial)");
            q = getSession().createQuery(query.toString());
            q.setParameter("claveRazonsocial", claveRazonsocial);
            q.setParameter("anio", ejercicio);
            q.executeUpdate();
            Double dias = 365D;
            if (isLeapYear(ejercicio)) {
                dias = 366D;
            }
            //Inicial
            Calendar fechaInicial = Calendar.getInstance(), fechaFinal = Calendar.getInstance();
            fechaInicial.set(Calendar.DAY_OF_MONTH, 1);
            fechaInicial.set(Calendar.MONTH, Calendar.JANUARY);
            fechaInicial.set(Calendar.YEAR, ejercicio);

            //Final
            fechaFinal.set(Calendar.DAY_OF_MONTH, 31);
            fechaFinal.set(Calendar.MONTH, Calendar.DECEMBER);
            fechaFinal.set(Calendar.YEAR, ejercicio);

            query.delete(0, query.length());

            //Obtiene empleados dados de alta en la empresa durante el ejercicio (carga acumulados)
            query.append("SELECT  ");
            query.append("o.puestos,");
            query.append("o.plazasPorEmpleado.empleados,");
            query.append("CASE WHEN (o.fechaInicial IS NULL)  THEN cast('1900-01-01' as date) ELSE  o.fechaInicial END,");
            query.append("CASE WHEN (o.plazasPorEmpleado.fechaFinal >=year(:fechaActual)) THEN o.plazasPorEmpleado.fechaFinal ELSE  '' END,");
            query.append("CASE WHEN (o.tipoRelacionLaboral IS NULL)  THEN ''  ELSE  o.tipoRelacionLaboral  END, ");
            query.append("(SELECT :noDias - COUNT(a)*1.0 as dias FROM Asistencias a INNER JOIN a.excepciones ex INNER JOIN a.empleados em ");
            query.append("INNER JOIN a.razonesSociales rs INNER JOIN a.tipoNomina t INNER JOIN a.periodosNomina p ");
            query.append("WHERE em.id=o.plazasPorEmpleado.empleados.id  AND rs.clave =:claveRazonsocial ");
            query.append("AND ex.clave NOT IN (3,5,6) AND a.fecha BETWEEN :fechaInicial AND :fechaFinal), ");
            query.append("(SELECT CASE WHEN COUNT(mov) = 0 THEN 0.0 ELSE SUM(CASE WHEN (mov.resultado IS NULL) THEN 0.0 ELSE mov.resultado END) END ");
            query.append("FROM MovNomConcep mov LEFT OUTER JOIN mov.periodosNomina per ");
            query.append("LEFT OUTER JOIN mov.concepNomDefi cnc LEFT OUTER JOIN mov.empleado emple ");
            query.append("LEFT OUTER JOIN mov.plazasPorEmpleado Hx11 LEFT OUTER JOIN mov.razonesSociales rs ");
            query.append("WHERE cnc.naturaleza = 0 AND emple.id = o.plazasPorEmpleado.empleados.id AND rs.clave =:claveRazonsocial ");
            query.append("AND per.año =:anio AND mov.mes IN(:meses)), ");

            query.append("(select Distinct CASE WHEN (pm.tipoRelacionLaboral IS NULL)  THEN ''  ELSE  pm.tipoRelacionLaboral END ");
            query.append("FROM PlazasPorEmpleadosMov pm where pm.tipoRelacionLaboral=1 AND  pm.plazasPorEmpleado.id =(");
            query.append("select Distinct mov.plazasPorEmpleado.id FROM MovNomConcep mov ");
            query.append("LEFT OUTER JOIN mov.empleado emple LEFT OUTER JOIN mov.razonesSociales rs ");
            query.append("WHERE emple.id = o.plazasPorEmpleado.empleados.id AND rs.clave =:claveRazonsocial ");
            query.append("AND year(pm.fechaInicial) =:anio )) as base, ");

            query.append("(select Distinct CASE WHEN (pm.tipoRelacionLaboral IS NULL)  THEN ''  ELSE  pm.tipoRelacionLaboral END ");
            query.append("FROM PlazasPorEmpleadosMov pm where pm.tipoRelacionLaboral=2 AND  pm.plazasPorEmpleado.id =(");
            query.append("select Distinct mov.plazasPorEmpleado.id FROM MovNomConcep mov ");
            query.append("LEFT OUTER JOIN mov.empleado emple LEFT OUTER JOIN mov.razonesSociales rs ");
            query.append("WHERE emple.id = o.plazasPorEmpleado.empleados.id AND rs.clave =:claveRazonsocial ");
            query.append("AND year(pm.fechaInicial) =:anio )) as eventual, ");

            query.append("(select Distinct CASE WHEN (pm.tipoRelacionLaboral IS NULL)  THEN ''  ELSE  pm.tipoRelacionLaboral END ");
            query.append("FROM PlazasPorEmpleadosMov pm where pm.tipoRelacionLaboral=3 AND  pm.plazasPorEmpleado.id =(");
            query.append("select Distinct mov.plazasPorEmpleado.id FROM MovNomConcep mov ");
            query.append("LEFT OUTER JOIN mov.empleado emple LEFT OUTER JOIN mov.razonesSociales rs ");
            query.append("WHERE emple.id = o.plazasPorEmpleado.empleados.id AND rs.clave =:claveRazonsocial ");
            query.append("AND year(pm.fechaInicial) =:anio )) as confianza, ");

            query.append("(SELECT CASE WHEN (pm.tipoRelacionLaboral IS NULL)  THEN ''  ELSE  pm.tipoRelacionLaboral END ");
            query.append("FROM PlazasPorEmpleadosMov pm WHERE pm.fechaInicial=(select Max(pm2.fechaInicial)  FROM PlazasPorEmpleadosMov pm2 ");
            query.append("LEFT OUTER JOIN pm2.plazasPorEmpleado.empleados emp2 LEFT OUTER JOIN pm2.plazasPorEmpleado.razonesSociales rs ");
            query.append(" where emp2.id=o.plazasPorEmpleado.empleados.id AND rs.clave=:claveRazonsocial AND year(pm2.fechaInicial) <=:anio ) ");
            query.append("and pm.plazasPorEmpleado.empleados.id =o.plazasPorEmpleado.empleados.id AND pm.plazasPorEmpleado.razonesSociales =:claveRazonsocial) ");
            query.append("from PlazasPorEmpleadosMov o where o.id IN (Select MAX(m.id) from PlazasPorEmpleadosMov m ");
            query.append("where  m.plazasPorEmpleado.razonesSociales.clave =:claveRazonsocial ");
            query.append("AND (year(m.fechaInicial) <=:anio AND year(m.plazasPorEmpleado.fechaFinal) >=:anio) ");
            query.append("OR (year(m.plazasPorEmpleado.fechaFinal) =:anio) ");
            query.append("group by m.plazasPorEmpleado.empleados.clave) ");
            q = getSession().createQuery(query.toString());
            q.setParameter("claveRazonsocial", claveRazonsocial);
            q.setParameter("anio", ejercicio);
            q.setParameter("fechaActual", getFechaDelSistema().getTime());
            q.setParameter("noDias", dias);
            q.setParameter("fechaInicial", fechaInicial.getTime());
            q.setParameter("fechaFinal", fechaFinal.getTime());
            q.setParameterList("meses", new Integer[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12});
            ArrayList plazas = (ArrayList) q.list();
            mensajeResultado.setResultado(plazas);
            mensajeResultado.setNoError(0);
            mensajeResultado.setError("");
            getSession().getTransaction().commit();
        } catch (HibernateException ex) {
            System.err.println(concatena.delete(0, concatena.length()).append(msgError).append("cargaDeAcumulados()1_Error: ").append(ex));
            try {
                if (getSession().getTransaction().isActive()) {
                    getSession().getTransaction().rollback();
                }
                mensajeResultado.setNoError(ControlErroresHibernate.buscaNoErrorPorExcepcion(ex.getClass()));
                mensajeResultado.setError(ex.getLocalizedMessage());
            } catch (HibernateException exc) {
                mensajeResultado.setNoError(ControlErroresHibernate.buscaNoErrorPorExcepcion(exc.getClass()));
                mensajeResultado.setError(exc.getLocalizedMessage());
            }
            mensajeResultado.setResultado(null);
        }
        return mensajeResultado;
    }

    @Override
    public Mensaje ptuDatosGeneralesPorEjercioyEmpresa(Integer ejercicio, String claveRazonsocial, String uuidCxn) {
        PtuDatosGenerales ptuDatosGenerales = null;
        try {
            inicializaVariableMensaje();
            setSession(HibernateUtil.currentSession(uuidCxn));
            getSession().beginTransaction();
            String query = "from PtuDatosGenerales p where p.ejercicio = :ejercicio AND p.razonesSociales.clave=:claveRazonsocial";
            q = getSession().createQuery(query);
            q.setString("ejercicio", String.valueOf(ejercicio));
            q.setParameter("claveRazonsocial", claveRazonsocial);
            ptuDatosGenerales = (PtuDatosGenerales) q.uniqueResult();
            mensajeResultado.setResultado(ptuDatosGenerales);
            mensajeResultado.setNoError(0);
            mensajeResultado.setError("");
            getSession().getTransaction().commit();
        } catch (HibernateException ex) {
            System.err.println(concatena.delete(0, concatena.length()).append(msgError).append("ptuDatosGeneralesPorEjercioyEmpresa()1_Error: ").append(ex));
            try {
                if (getSession().getTransaction().isActive()) {
                    getSession().getTransaction().rollback();
                }
                mensajeResultado.setNoError(ControlErroresHibernate.buscaNoErrorPorExcepcion(ex.getClass()));
                mensajeResultado.setError(ex.getLocalizedMessage());
            } catch (HibernateException exc) {
                mensajeResultado.setNoError(ControlErroresHibernate.buscaNoErrorPorExcepcion(exc.getClass()));
                mensajeResultado.setError(exc.getLocalizedMessage());
            }
            mensajeResultado.setResultado(null);
        }
        return mensajeResultado;
    }

    @Override
    public Mensaje ptuEmpleadosPorEjercioyEmpresa(Integer ejercicio, String claveRazonsocial, String uuidCxn) {
        List<PtuEmpleados> ptuEmpleados = null;
        try {
            inicializaVariableMensaje();
            setSession(HibernateUtil.currentSession(uuidCxn));
            getSession().beginTransaction();
            String query = "from PtuEmpleados p where p.ejercicio = :ejercicio AND p.razonesSociales.clave=:claveRazonsocial";
            q = getSession().createQuery(query);
            q.setString("ejercicio", String.valueOf(ejercicio));
            q.setParameter("claveRazonsocial", claveRazonsocial);
            ptuEmpleados = q.list();
            mensajeResultado.setResultado(ptuEmpleados);
            mensajeResultado.setNoError(0);
            mensajeResultado.setError("");
            getSession().getTransaction().commit();
        } catch (HibernateException ex) {
            System.err.println(concatena.delete(0, concatena.length()).append(msgError).append("ptuEmpleadosPorEjercioyEmpresa()1_Error: ").append(ex));
            try {
                if (getSession().getTransaction().isActive()) {
                    getSession().getTransaction().rollback();
                }
                mensajeResultado.setNoError(ControlErroresHibernate.buscaNoErrorPorExcepcion(ex.getClass()));
                mensajeResultado.setError(ex.getLocalizedMessage());
            } catch (HibernateException exc) {
                mensajeResultado.setNoError(ControlErroresHibernate.buscaNoErrorPorExcepcion(exc.getClass()));
                mensajeResultado.setError(exc.getLocalizedMessage());
            }
            mensajeResultado.setResultado(null);
        }
        return mensajeResultado;
    }

    @Override
    public Mensaje calculoPtu(PtuDatosGenerales ptuDatosGenerales, List<PtuEmpleados> ptuEmpleados, Double cantidadRepartir, Object[] totales, String uuidCxn) {
        try {
            inicializaVariableMensaje();
            setSession(HibernateUtil.currentSession(uuidCxn));
            getSession().beginTransaction();
            Double cantidadDistribuida = (cantidadRepartir / 2);
            Double totalDeDias = (Double) totales[0];
            Double totalDePercepciones = (Double) totales[1];
            Double totalDiasPtu = 0D;
            Double totalPercepcionesPtu = 0D;
            if (ptuEmpleados != null) {
                int i;
                for (i = 0; i < ptuEmpleados.size(); i++) {
                    Double ptuDiasEmple = ptuEmpleados.get(i).getDiasLaborados() / totalDeDias * cantidadDistribuida;
                    ptuEmpleados.get(i).setPtuDias(ptuDiasEmple);
                    totalDiasPtu += ptuEmpleados.get(i).getPtuDias();
                    Double ptuPercepEmple = ptuEmpleados.get(i).getPercepciones() / totalDePercepciones * cantidadDistribuida;
                    ptuEmpleados.get(i).setPtuPercepciones(ptuPercepEmple);
                    totalPercepcionesPtu += ptuEmpleados.get(i).getPtuPercepciones();
                    getSession().saveOrUpdate(ptuEmpleados.get(i));
                    if (i % 50 == 0 & i > 0) {
                        flush();
                        clear();
                    }
                }
            }
            ptuDatosGenerales.setTotalDiasptu(totalDiasPtu);
            ptuDatosGenerales.setTotalPercepcionesptu(totalPercepcionesPtu);
            ptuDatosGenerales.setStatus("CALCULADO");
            getSession().saveOrUpdate(ptuDatosGenerales);
            mensajeResultado.setResultado(ptuEmpleados);
            mensajeResultado.setNoError(0);
            mensajeResultado.setError("");
            getSession().getTransaction().commit();
        } catch (HibernateException ex) {
            System.err.println(concatena.delete(0, concatena.length()).append(msgError).append("ptuEmpleadosPorEjercioyEmpresa()1_Error: ").append(ex));
            try {
                if (getSession().getTransaction().isActive()) {
                    getSession().getTransaction().rollback();
                }
                mensajeResultado.setNoError(ControlErroresHibernate.buscaNoErrorPorExcepcion(ex.getClass()));
                mensajeResultado.setError(ex.getLocalizedMessage());
            } catch (HibernateException exc) {
                mensajeResultado.setNoError(ControlErroresHibernate.buscaNoErrorPorExcepcion(exc.getClass()));
                mensajeResultado.setError(exc.getLocalizedMessage());
            }
            mensajeResultado.setResultado(null);
        }
        return mensajeResultado;
    }

    private static boolean isLeapYear(int year) {
        return (year % 400 == 0) || ((year % 100 != 0) && (year % 4 == 0));
    }
}
