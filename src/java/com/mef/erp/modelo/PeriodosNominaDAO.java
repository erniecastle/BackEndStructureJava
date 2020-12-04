/**
 * @author: Fecha de Creación: Compañía: Descripción del programa:
 * -----------------------------------------------------------------------------
 * MODIFICACIONES:
 * -----------------------------------------------------------------------------
 * Clave: JSA01 Autor: Armando Fecha: 23/03/2015 Descripción: Se le agrego al
 * metodo getUltimoPeriodoCerradoPorFecha el filtro por año ya que los periodos
 * son por año.
 * -----------------------------------------------------------------------------
 */
package com.mef.erp.modelo;

import com.mef.erp.modelo.entidad.Mensaje;
import com.mef.erp.modelo.entidad.PeriodosNomina;
import com.mef.erp.modelo.entidad.TipoCorrida;
import com.mef.erp.modelo.entidad.TipoNomina;
import com.mef.erp.util.ControlErroresHibernate;
import com.mef.erp.util.HibernateUtil;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Query;

/**
 *
 * @author daniel
 */
public class PeriodosNominaDAO extends GenericHibernateDAO<PeriodosNomina, Long>
        implements PeriodosNominaDAOIF {

    private StringBuilder concatena = new StringBuilder(200);
    private final String msgError = concatena.append("ServerERP").append(".MSERR_C_").append(this.getClass().getName()).append(".").toString();
    private List<PeriodosNomina> listEsp = new ArrayList<PeriodosNomina>();
    private Boolean commit;

    public Mensaje agregar(PeriodosNomina entity, String uuidCxn) {
        PeriodosNomina periodoNomina;
        try {
            inicializaVariableMensaje();
            setSession(HibernateUtil.currentSession(uuidCxn));
            getSession().beginTransaction();
            periodoNomina = makePersistent(entity);
            mensajeResultado.setResultado(periodoNomina);
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

    public Mensaje actualizar(PeriodosNomina entity, String uuidCxn) {
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

    public Mensaje eliminar(PeriodosNomina entity, String uuidCxn) {
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

    public Mensaje getPeriodosNominaAll(String uuidCxn) {
        List<PeriodosNomina> values;
        try {
            inicializaVariableMensaje();
            setSession(HibernateUtil.currentSession(uuidCxn));
            getSession().beginTransaction();
            q = getSession().createQuery("from PeriodosNomina");
            values = q.list();
            mensajeResultado.setResultado(values);
            mensajeResultado.setNoError(0);
            mensajeResultado.setError("");
            getSession().getTransaction().commit();
        } catch (HibernateException ex) {
            System.err.println(concatena.delete(0, concatena.length()).append(msgError).append("getPeriodosNominaAll()1_Error: ").append(ex));
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

    public Mensaje getPeriodosNominaPorClave(String clave, int year, String claveTipoCorrida, String uuidCxn) {
        PeriodosNomina r;
        try {
            inicializaVariableMensaje();
            setSession(HibernateUtil.currentSession(uuidCxn));
            getSession().beginTransaction();
            q = getSession().createQuery("Select p from PeriodosNomina p INNER JOIN p.tipoCorrida tc where p.clave=:clave and tc.clave = :claveTipoCorrida and p.año = :year");
            q.setParameter("clave", clave);
            q.setParameter("claveTipoCorrida", claveTipoCorrida);

            q.setParameter("year", clave);
            r = (PeriodosNomina) q.uniqueResult();
            mensajeResultado.setResultado(r);
            mensajeResultado.setNoError(0);
            mensajeResultado.setError("");
            getSession().getTransaction().commit();
        } catch (HibernateException ex) {
            System.err.println(concatena.delete(0, concatena.length()).append(msgError).append("getPeriodosNominaPorClave()1_Error: ").append(ex));
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

    public Mensaje getPeriodosNominaPorClaveYTipoDeNominaCorrida(String clave, String claveTipoNomina, String claveTipoCorrida, String uuidCxn) {
        PeriodosNomina p;
        try {
            inicializaVariableMensaje();
            setSession(HibernateUtil.currentSession(uuidCxn));
            getSession().beginTransaction();
            q = getSession().createQuery("Select p from PeriodosNomina p INNER JOIN p.tipoCorrida tc where  p.clave=:clave and p.tipoNomina.clave=:claveTipoNomina and tc.clave = :claveTipoCorrida");
            q.setParameter("clave", clave);
            q.setParameter("claveTipoNomina", claveTipoNomina);
            q.setParameter("claveTipoCorrida", claveTipoCorrida);

            p = (PeriodosNomina) q.uniqueResult();
            mensajeResultado.setResultado(p);
            mensajeResultado.setNoError(0);
            mensajeResultado.setError("");
            getSession().getTransaction().commit();
        } catch (HibernateException ex) {
            System.err.println(concatena.delete(0, concatena.length()).append(msgError).append("getPeriodosNominaPorClaveYTipoDeNominaCorrida()1_Error: ").append(ex));
            getSession().getTransaction().rollback();
        }
        return mensajeResultado;
    }

    public Mensaje getPeriodosNominaPorClaveYTipoDeNominaECorrida(String clave, TipoNomina tipoNomina, String claveTipoCorrida, String uuidCxn) {
        PeriodosNomina p;
        try {
            inicializaVariableMensaje();
            setSession(HibernateUtil.currentSession(uuidCxn));
            getSession().beginTransaction();
            q = getSession().createQuery("Select p from PeriodosNomina p INNER JOIN p.tipoCorrida tc where  p.clave=:clave and p.tipoNomina_ID=:tipoNomina and tc.clave = :claveTipoCorrida");
            q.setParameter("clave", clave);
            q.setEntity("tipoNomina", tipoNomina);
            q.setParameter("claveTipoCorrida", claveTipoCorrida);

            p = (PeriodosNomina) q.uniqueResult();
            mensajeResultado.setResultado(p);
            mensajeResultado.setNoError(0);
            mensajeResultado.setError("");
            getSession().getTransaction().commit();
        } catch (HibernateException ex) {
            System.err.println(concatena.delete(0, concatena.length()).append(msgError).append("getPeriodosNominaPorClaveYTipoDeNominaECorrida()1_Error: ").append(ex));
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

    public Mensaje getPeriodosNominaActualPorFecha(Date fecha, String claveTipoNomina, String claveTipoCorrida, boolean status, String uuidCxn) {
        PeriodosNomina p;
        try {
            inicializaVariableMensaje();
            setSession(HibernateUtil.currentSession(uuidCxn));
            getSession().beginTransaction();
            q = getSession().createQuery("Select p from PeriodosNomina p INNER JOIN p.tipoCorrida tc where (p.fechaInicial >=:fecha or p.fechaFinal >=:fecha) and p.tipoNomina.clave =:claveTipoNomina and p.status =:status and tc.clave = :claveTipoCorrida");
            q.setDate("fecha", fecha);
            q.setParameter("claveTipoNomina", claveTipoNomina);
            q.setParameter("status", status);
            q.setParameter("claveTipoCorrida", claveTipoCorrida);

            q.setFirstResult(0);
            q.setMaxResults(1);
            p = (PeriodosNomina) q.uniqueResult();
            mensajeResultado.setResultado(p);
            mensajeResultado.setNoError(0);
            mensajeResultado.setError("");
            getSession().getTransaction().commit();
        } catch (HibernateException ex) {
            System.err.println(concatena.delete(0, concatena.length()).append(msgError).append("getPeriodosNominaActualPorFecha()1_Error: ").append(ex));
            getSession().getTransaction().rollback();
        }
        return mensajeResultado;
    }

    public Mensaje getPeriodosNominaPorTipoNominaYRangoDeFechas(Date fechaInicial, Date fechaFinal, String claveTipoNomina, String claveTipoCorrida, String uuidCxn) {
        List<PeriodosNomina> values;//JEVC05
        try {
            inicializaVariableMensaje();
            setSession(HibernateUtil.currentSession(uuidCxn));
            getSession().beginTransaction();
            q = getSession().createQuery("Select p from PeriodosNomina p INNER JOIN p.tipoCorrida tc where p.tipoNomina.clave =:claveTipoNomina and tc.clave = :claveTipoCorrida and p.fechaFinal >=:fechaInicial and p.fechaInicial <=:fechaFinal ");
            q.setDate("fechaInicial", fechaInicial);
            q.setDate("fechaFinal", fechaFinal);
            q.setParameter("claveTipoNomina", claveTipoNomina);
            q.setParameter("claveTipoCorrida", claveTipoCorrida);

            values = q.list();
            mensajeResultado.setResultado(values);
            mensajeResultado.setNoError(0);
            mensajeResultado.setError("");
            getSession().getTransaction().commit();
        } catch (HibernateException ex) {
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
            /*
             from PeriodosNomina p where p.tipoNomina.clave =:claveTipoNomina and 
             ((p.fechaInicial BETWEEN :fechaInicial AND :fechaFinal) or (p.fechaFinal 
             BETWEEN :fechaInicial AND :fechaFinal))
             */
        }
        return mensajeResultado;
    }

    /*busca periodos cerrados segun fecha y regresa el mas cercano ala fecha ingresada*/
    public Mensaje getUltimoPeriodoCerradoPorFecha(Date fecha, String claveTipoNomina, String claveTipoCorrida, String uuidCxn) {
        PeriodosNomina p;
        try {
            inicializaVariableMensaje();
            setSession(HibernateUtil.currentSession(uuidCxn));
            getSession().beginTransaction();
            q = getSession().createQuery("Select p from PeriodosNomina p INNER JOIN p.tipoCorrida tc where (p.fechaInicial <=:fecha or p.fechaFinal <=:fecha) and p.tipoNomina.clave =:claveTipoNomina and tc.clave = :claveTipoCorrida and p.status = false and p.año = :anio Order by p.clave desc");
            q.setDate("fecha", fecha);
            q.setParameter("claveTipoNomina", claveTipoNomina);
            q.setParameter("claveTipoCorrida", claveTipoCorrida);

            Calendar c = Calendar.getInstance();
            c.setTime(fecha);
            q.setParameter("anio", c.get(Calendar.YEAR));//JSA01
            q.setFirstResult(0);
            q.setMaxResults(1);
            p = (PeriodosNomina) q.uniqueResult();
            mensajeResultado.setResultado(p);
            mensajeResultado.setNoError(0);
            mensajeResultado.setError("");
            getSession().getTransaction().commit();
        } catch (HibernateException ex) {
            System.err.println(concatena.delete(0, concatena.length()).append(msgError).append("getUltimoPeriodoCerradoPorFecha()1_Error: ").append(ex));
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

//    public Mensaje getPeriodosNominaPorAñoYTipoNomina(Integer año, String tipoNomina, String uuidCxn) {
//        List<PeriodosNomina> values = null;
//        try {
//            inicializaVariableMensaje();
//            setSession(HibernateUtil.currentSession(uuidCxn));
//            getSession().beginTransaction();
//            if (HibernateUtil.usaTypeBigInt) {
//                q = getSession().createQuery("Select p from PeriodosNomina INNER JOIN p.tipoCorrida tc where p.año=:año and p.tipoNomina.clave=:claveTipoNomina order by cast(p.clave as int)");
//            } else {
//                q = getSession().createQuery("Select p from PeriodosNomina INNER JOIN p.tipoCorrida tc where p.año=:año and p.tipoNomina.clave=:claveTipoNomina order by cast(p.clave as long)");
//            }
//            q.setInteger("año", año);
//            q.setParameter("claveTipoNomina", tipoNomina);
//            values = q.list();
//            mensajeResultado.setResultado(values);
//            mensajeResultado.setNoError(0);
//            mensajeResultado.setError("");
//            getSession().getTransaction().commit();
//        } catch (HibernateException ex) {
//            System.err.println(concatena.delete(0, concatena.length()).append(msgError).append("getPeriodosNominaPorAñoYTipoNomina()1_Error: ").append(ex));
//            try {
//                if (getSession().getTransaction().isActive()) {
//                    getSession().getTransaction().rollback();
//                }
//                mensajeResultado.setNoError(ControlErroresHibernate.buscaNoErrorPorExcepcion(ex.getClass()));
//                mensajeResultado.setError(ex.getLocalizedMessage());
//            } catch (HibernateException exc) {  //cuando marca error al hacer rollback pro ejemplo se cae conexion servidor
//                mensajeResultado.setNoError(ControlErroresHibernate.buscaNoErrorPorExcepcion(exc.getClass()));
//                mensajeResultado.setError(exc.getLocalizedMessage());
//            }
//            mensajeResultado.setResultado(null);
//        }
//        return mensajeResultado;
//    }
    public Mensaje getPeriodosNominaPorAñoYTipoNominaYTipoCorrida(Integer año, String tipoNomina, String tipoCorrida, String uuidCxn) {
        List<PeriodosNomina> values = null;
        try {
            inicializaVariableMensaje();
            setSession(HibernateUtil.currentSession(uuidCxn));
            getSession().beginTransaction();
            if (HibernateUtil.usaTypeBigInt) {
                q = getSession().createQuery("from PeriodosNomina p where  p.año=:año and p.tipoNomina.clave=:claveTipoNomina and p.tipoCorrida.clave=:claveTipoCorrida order by cast(p.clave as int)");
            } else {
                q = getSession().createQuery("from PeriodosNomina p where  p.año=:año and p.tipoNomina.clave=:claveTipoNomina and p.tipoCorrida.clave=:claveTipoCorrida order by cast(p.clave as long)");
            }
            q.setInteger("año", año);
            q.setParameter("claveTipoNomina", tipoNomina);
            q.setParameter("claveTipoCorrida", tipoCorrida);
            values = q.list();
            mensajeResultado.setResultado(values);
            mensajeResultado.setNoError(0);
            mensajeResultado.setError("");
            getSession().getTransaction().commit();
        } catch (HibernateException ex) {
            System.err.println(concatena.delete(0, concatena.length()).append(msgError).append("getPeriodosNominaPorAñoYTipoNominaYTipoCorrida()1_Error: ").append(ex));
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

    public Mensaje getPeriodosNominaPorFechasYTipoNominaCorrida(Date inicio, Date fin, TipoNomina tipoNomina, String claveTipoCorrida, String uuidCxn) {
        List<PeriodosNomina> valuesTmp, values;
        try {
            Calendar fechaInicial = Calendar.getInstance(), fechaFinal = Calendar.getInstance();
            fechaInicial.setTime(inicio);
            fechaFinal.setTime(fin);
            inicializaVariableMensaje();
            setSession(HibernateUtil.currentSession(uuidCxn));
            getSession().beginTransaction();
            List<Object> fechas;
            //fecha inicial menor
            q = getSession().createQuery("select p.fechaInicial from PeriodosNomina p where p.tipoNomina.clave = :claveTipoNomina and p.tipoCorrida.clave=:claveTipoCorrida and p.fechaInicial <=  :fechaInicio order by p.fechaInicial desc");
            q.setDate("fechaInicio", inicio);
            //q.setParameter("claveTipoNomina", "3");
            q.setParameter("claveTipoNomina", tipoNomina.getClave());
            q.setParameter("claveTipoCorrida", claveTipoCorrida);

            q.setFirstResult(0);
            q.setMaxResults(1);
            fechas = q.list();
            if (fechas.isEmpty()) {
                fechaInicial.setTime(inicio);
            } else {
                fechaInicial.setTime((Date) fechas.get(0));
            }
            //fecha final mayor
            q = getSession().createQuery("select p.fechaFinal from PeriodosNomina p where p.tipoNomina.clave = :claveTipoNomina and p.tipoCorrida.clave=:claveTipoCorrida and p.fechaFinal >= :fechaFinal order by  p.fechaFinal asc");
            q.setDate("fechaFinal", fin);
            //q.setParameter("claveTipoNomina", "3");
            q.setParameter("claveTipoNomina", tipoNomina.getClave());
            q.setParameter("claveTipoCorrida", claveTipoCorrida);

            q.setFirstResult(0);
            q.setMaxResults(1);
            fechas = q.list();
            if (fechas.isEmpty()) {
                fechaFinal.setTime(fechaInicial.getTime());
            } else {
                fechaFinal.setTime((Date) fechas.get(0));
            }
            q = getSession().createQuery("from PeriodosNomina p where p.tipoNomina.clave = :claveTipoNomina and p.tipoCorrida.clave=:claveTipoCorrida and p.fechaInicial >= :fechaInicio and p.fechaFinal <= :fechaFinal and p.status = true and p.bloquear = false");
            q.setDate("fechaInicio", fechaInicial.getTime());
            q.setDate("fechaFinal", fechaFinal.getTime());
            //q.setParameter("claveTipoNomina", "3");
            q.setParameter("claveTipoNomina", tipoNomina.getClave());
            q.setParameter("claveTipoCorrida", claveTipoCorrida);

            valuesTmp = q.list();
            if (valuesTmp.size() > 1) {
                if (fechaInicial.getTime().equals(valuesTmp.get(0).getFechaCierre())) {
                    valuesTmp.remove(0);
                } else {
                    valuesTmp.remove(1);
                }
            }
            values = new ArrayList<PeriodosNomina>();
            Integer anioOEjercicio = fechaInicial.get(Calendar.YEAR), anioOEjercicio2 = fechaFinal.get(Calendar.YEAR);
            for (int i = 0; i < valuesTmp.size(); i++) {
                if (valuesTmp.get(i).getAño().equals(anioOEjercicio) || valuesTmp.get(i).getAño().equals(anioOEjercicio2)) {
                    values.add(valuesTmp.get(i));
                }
            }

            mensajeResultado.setResultado(values);
            mensajeResultado.setNoError(0);
            mensajeResultado.setError("");
            getSession().getTransaction().commit();
        } catch (HibernateException ex) {
            System.err.println(concatena.delete(0, concatena.length()).append(msgError).append("getPeriodosNominaPorFechasYTipoNominaCorrida()1_Error: ").append(ex));
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

//<editor-fold defaultstate="collapsed" desc="comment">
//    private int calculaFechaInicioPeriodo(int diasPeriodo, int diaInicio) {
//        switch (diasPeriodo) {
//            case 7:
//                if (diaInicio > 7 & diaInicio < 14) {
//                    return 8;
//                } else if (diaInicio > 15 & diaInicio < 22) {
//                    return 15;
//                } else if (diaInicio < 8) {
//                    return 1;
//                } else {
//                    return 23;
//                }
//            case 15:
//                if (diaInicio >= 15 & diaInicio <= 31) {
//                    return 16;
//                } else {
//                    return 1;
//                }
//            default:
//                return 0;
//        }
//    }
//    private int calculaFechaCierrePeriodo(int diasPeriodo, int diaCierre) {
//        switch (diasPeriodo) {
//            case 15:
//                if (diaCierre < 16) {
//                    return 16;
//                } else {
//                    return 1;
//                }
//            default:
//                return 0;
//        }
//    }
//</editor-fold>
    public Mensaje consultaPorRangos(Integer inicio, Integer rango, String uuidCxn) {
        try {
            inicializaVariableMensaje();
            setSession(HibernateUtil.currentSession(uuidCxn));
            getSession().beginTransaction();
            listEsp = (List<PeriodosNomina>) consultaPorRangos(inicio, rango, null, null);
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

    public Mensaje consultaPorFiltrosPeriodos(String query, Object[] campos, Object[] valores, String uuidCxn) {
        try {
            inicializaVariableMensaje();
            setSession(HibernateUtil.currentSession(uuidCxn));
            getSession().beginTransaction();
            listEsp = (List<PeriodosNomina>) consultaPorFiltros(query, campos, valores, null, null);
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

    public Mensaje existeDato(String campo, Object valor, String uuidCxn) {
        Boolean existe;
        try {
            inicializaVariableMensaje();
            setSession(HibernateUtil.currentSession(uuidCxn));
            getSession().beginTransaction();
            existe = existeDato("PeriodosNomina", campo, valor);
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

    public Mensaje saveDeletePeriodosNomina(List<PeriodosNomina> entitysCambios, List<PeriodosNomina> eliminados, TipoCorrida tCorrida, String uuidCxn) {
        listEsp = new ArrayList<PeriodosNomina>();
        boolean exitoRegistro = true;
        PeriodosNomina p = null;
        Calendar cal = Calendar.getInstance();
        try {
            inicializaVariableMensaje();
            setSession(HibernateUtil.currentSession(uuidCxn));
            getSession().beginTransaction();

            if (eliminados != null && eliminados.size() > 0) {
                Object[] eliminados2 = new Object[eliminados.size()];
                String[] claveEliminar = new String[eliminados.size()];
                String claveTipoNomina = eliminados.get(0).getTipoNomina().getClave();
                for (int i = 0; i < eliminados.size(); i++) {
                    eliminados2[i] = eliminados.get(i).getId();
                    claveEliminar[i] = eliminados.get(i).getClave();

                }
                exitoRegistro = deleteListQuery(eliminados2, claveEliminar, claveTipoNomina, tCorrida);
                clear();
            }
            entitysCambios = (entitysCambios == null ? new ArrayList<PeriodosNomina>() : entitysCambios);
            int cantidadSaveUpdate = 0, cantidadFlush = 50;
            if (exitoRegistro && !entitysCambios.isEmpty()) {
                /////////System.out.println(entitysCambios.size());
                for (int i = 0; i < entitysCambios.size(); i++) {
                    cal.setTime(entitysCambios.get(i).getFechaFinal());
                    if (entitysCambios.get(i).getAño() >= cal.get(Calendar.YEAR) || entitysCambios.get(i).isCierreMes()) {
                        boolean existeId = entitysCambios.get(i).getId() != null;

                        getSession().saveOrUpdate(entitysCambios.get(i));
                        if (existeId && tCorrida.getClave().equals("PER")) {
                            String query = "UPDATE PeriodosNomina o set o.descripcion=:descripcion,"
                                    + "o.detalleConceptoRecibo =:detalleConceptoRecibo,"
                                    + "o.fechaInicial=:fechaInicial,o.fechaFinal=:fechaFinal,o.fechaPago=:fechaPago,"
                                    + "o.fechaAsistenciInicial=:fechaAsistenciInicial,"
                                    + "o.fechaAsistenciaFinal=:fechaAsistenciaFinal,o.descontarAhorro=:descontarAhorro,"
                                    + "o.descontarPrestamo=:descontarPrestamo,o.soloPercepciones=:soloPercepciones,"
                                    + "o.incluirBajas=:incluirBajas,o.bloquear=:bloquear,o.cierreMes=:cierreMes,o.fechaModificado=:fechaModificado "
                                    + "WHERE o.id IN(select p.id from PeriodosNomina p where p.clave=:clavesEliminar "
                                    + "AND p.tipoNomina.clave=:claveTipoNomina AND p.tipoCorrida.usaCorrPeriodica=1 AND p.origen=1)";
                            Query q = getSession().createQuery(query);
                            q.setParameter("descripcion", entitysCambios.get(i).getDescripcion());
                            q.setParameter("detalleConceptoRecibo", entitysCambios.get(i).getDetalleConceptoRecibo());
                            q.setParameter("fechaInicial", entitysCambios.get(i).getFechaInicial());
                            q.setParameter("fechaFinal", entitysCambios.get(i).getFechaFinal());
                            q.setParameter("fechaPago", entitysCambios.get(i).getFechaPago());
                            q.setParameter("fechaAsistenciInicial", entitysCambios.get(i).getFechaAsistenciInicial());
                            q.setParameter("fechaAsistenciaFinal", entitysCambios.get(i).getFechaAsistenciaFinal());
                            q.setParameter("descontarAhorro", entitysCambios.get(i).isDescontarAhorro());
                            q.setParameter("descontarPrestamo", entitysCambios.get(i).isDescontarPrestamo());
                            q.setParameter("soloPercepciones", entitysCambios.get(i).isSoloPercepciones());
                            q.setParameter("incluirBajas", entitysCambios.get(i).isIncluirBajas());
                            q.setParameter("bloquear", entitysCambios.get(i).isBloquear());
                            q.setParameter("cierreMes", entitysCambios.get(i).isCierreMes());
                            q.setParameter("fechaModificado", entitysCambios.get(i).getFechaModificado());
                            q.setParameter("clavesEliminar", entitysCambios.get(i).getClave());
                            q.setParameter("claveTipoNomina", entitysCambios.get(i).getTipoNomina().getClave());

                            int result = q.executeUpdate();
                            ///////System.out.println("Rows affected: " + result);
                        }
                        listEsp.add(entitysCambios.get(i));
                    }
                    cantidadSaveUpdate++;
                    if (cantidadSaveUpdate % cantidadFlush == 0 & cantidadSaveUpdate > 0) {
                        getSession().flush();
                        getSession().clear();
                    }
                }
            }
            if (exitoRegistro) {
                mensajeResultado.setResultado(true);
                mensajeResultado.setNoError(0);
                mensajeResultado.setError("");
                getSession().getTransaction().commit();
            } else {
                getSession().getTransaction().rollback();
            }
        } catch (HibernateException ex) {
            System.err.println(concatena.delete(0, concatena.length()).append(msgError).append("saveDeletePeriodosNomina()1_Error: ").append(ex));
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

    private boolean deleteListQuery(Object[] eliminados, String[] clavesEliminar, String claveTipoNomina, TipoCorrida tCorrida) {
        boolean existe = true;
        try {
            deleteListQuery("PeriodosNomina", "Id", eliminados);
            if (tCorrida.getClave().equals("PER")) {
                String query = "DELETE FROM PeriodosNomina o  WHERE o.id IN(select p.id from PeriodosNomina p WHERE p.clave IN(:clavesEliminar) AND p.tipoNomina.clave=:claveTipoNomina AND p.tipoCorrida.usaCorrPeriodica=1 AND p.origen=1)";
                q = getSession().createQuery(query);
                q.setParameterList("clavesEliminar", clavesEliminar);

                q.setParameter("claveTipoNomina", claveTipoNomina);
                int result = q.executeUpdate();
                ////////////System.out.println("Rows affected: " + result);
            }
        } catch (HibernateException ex) {
            System.err.println(concatena.delete(0, concatena.length()).append(msgError).append("deleteListQuery()1_Error: ").append(ex));
            mensajeResultado.setNoError(ControlErroresHibernate.buscaNoErrorPorExcepcion(ex.getClass()));
            mensajeResultado.setError(ex.getLocalizedMessage());
            mensajeResultado.setResultado(null);
            existe = false;
        }
        return existe;
    }

    public Mensaje ObtenerFechaFinalMax(String claveTipoNomina, String claveTipoCorrida, Integer año, String uuidCxn) {
        Date d;
        try {
            inicializaVariableMensaje();
            setSession(HibernateUtil.currentSession(uuidCxn));
            getSession().beginTransaction();
            if (año == null) {
                q = getSession().createQuery("Select max(p.fechaFinal) from PeriodosNomina p where p.tipoNomina.clave = :clave and p.tipoCorrida.clave=:claveTipoCorrida");
            } else {
                q = getSession().createQuery("Select max(p.fechaFinal) from PeriodosNomina p where p.tipoNomina.clave = :clave and p.tipoCorrida.clave=:claveTipoCorrida and p.año = :año");
            }
            q.setParameter("clave", claveTipoNomina);
            q.setParameter("claveTipoCorrida", claveTipoCorrida);

            if (año != null) {
                q.setParameter("año", año);
            }
            d = (Date) q.uniqueResult();
            mensajeResultado.setResultado(d);
            mensajeResultado.setNoError(0);
            mensajeResultado.setError("");
            getSession().getTransaction().commit();
        } catch (HibernateException ex) {
            System.err.println(concatena.delete(0, concatena.length()).append(msgError).append("ObtenerFechaFinalMax()1_Error: ").append(ex));
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

    public Mensaje ObtenerFechaFinalMin(String claveTipoNomina, String claveTipoCorrida, Integer año, String uuidCxn) {
        Date d;
        try {
            inicializaVariableMensaje();
            setSession(HibernateUtil.currentSession(uuidCxn));
            getSession().beginTransaction();
            q = getSession().createQuery("Select min(p.fechaInicial) from PeriodosNomina p where p.tipoNomina.clave = :clave and p.año = :año and p.tipoCorrida.clave=:claveTipoCorrida");
            q.setParameter("clave", claveTipoNomina);
            q.setParameter("año", año);
            q.setParameter("claveTipoCorrida", claveTipoCorrida);

            d = (Date) q.uniqueResult();
            mensajeResultado.setResultado(d);
            mensajeResultado.setNoError(0);
            mensajeResultado.setError("");
            getSession().getTransaction().commit();
        } catch (HibernateException ex) {
            System.err.println(concatena.delete(0, concatena.length()).append(msgError).append("ObtenerFechaFinalMax()1_Error: ").append(ex));
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

    public Mensaje getUltimoPeriodo(Integer año, String claveTipoNomina, String claveTipoCorrida, String uuidCxn) {
        PeriodosNomina r;
        try {
            inicializaVariableMensaje();
            setSession(HibernateUtil.currentSession(uuidCxn));
            getSession().beginTransaction();
            q = getSession().createQuery("from PeriodosNomina p where p.año=:año AND p.tipoNomina.clave = :claveTipoNomina AND p.tipoCorrida.clave = :claveTipoCorrida order by LEN(p.clave) desc, p.clave desc".replace("LEN", HibernateUtil.funcionLength));
            q.setParameter("año", año);
            q.setParameter("claveTipoNomina", claveTipoNomina);
            q.setParameter("claveTipoCorrida", claveTipoCorrida);

            q.setFirstResult(0);
            q.setMaxResults(1);
            r = (PeriodosNomina) q.uniqueResult();
            mensajeResultado.setResultado(r);
            mensajeResultado.setNoError(0);
            mensajeResultado.setError("");
            getSession().getTransaction().commit();
        } catch (HibernateException ex) {
            System.err.println(concatena.delete(0, concatena.length()).append(msgError).append("getUltimoPeriodo()1_Error: ").append(ex));
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

    public Mensaje getPrimerPeriodo(Integer año, String claveTipoNomina, String claveTipoCorrida, String uuidCxn) {
        PeriodosNomina r;
        try {
            inicializaVariableMensaje();
            setSession(HibernateUtil.currentSession(uuidCxn));
            getSession().beginTransaction();
            q = getSession().createQuery("from PeriodosNomina p where p.año=:año AND p.tipoNomina.clave = :claveTipoNomina AND p.tipoCorrida.clave = :claveTipoCorrida order by LEN(p.clave) asc, p.clave asc".replace("LEN", HibernateUtil.funcionLength));
            q.setParameter("año", año);
            q.setParameter("claveTipoNomina", claveTipoNomina);
            q.setParameter("claveTipoCorrida", claveTipoCorrida);

            q.setFirstResult(0);
            q.setMaxResults(1);
            r = (PeriodosNomina) q.uniqueResult();
            mensajeResultado.setResultado(r);
            mensajeResultado.setNoError(0);
            mensajeResultado.setError("");
            getSession().getTransaction().commit();
        } catch (HibernateException ex) {
            System.err.println(concatena.delete(0, concatena.length()).append(msgError).append("getPrimerPeriodo()1_Error: ").append(ex));
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

    public Mensaje getPeriodosNominaPorFechaTipoNominaCorrida(Date fecha, String claveTipoNomina, String claveTipoCorrida, String uuidCxn) {
        PeriodosNomina r;
        try {
            inicializaVariableMensaje();
            setSession(HibernateUtil.currentSession(uuidCxn));
            getSession().beginTransaction();
            q = getSession().createQuery("from PeriodosNomina p where (:fecha BETWEEN p.fechaInicial AND p.fechaFinal) AND p.tipoNomina.clave = :claveTipoNomina AND p.tipoCorrida.clave = :claveTipoCorrida");
            q.setParameter("fecha", fecha);
            q.setParameter("claveTipoNomina", claveTipoNomina);
            q.setParameter("claveTipoCorrida", claveTipoCorrida);

            r = (PeriodosNomina) q.uniqueResult();
            mensajeResultado.setResultado(r);
            mensajeResultado.setNoError(0);
            mensajeResultado.setError("");
            getSession().getTransaction().commit();
        } catch (HibernateException ex) {
            System.err.println(concatena.delete(0, concatena.length()).append(msgError).append("getPeriodosNominaPorFechaTipoNominaCorrida()1_Error: ").append(ex));
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

    public Mensaje actualizaListaPorCampos(String[] campoModificar, Object[] valoresModificado, String[] camposWhere, Object[] valoresWhere, String uuidCxn) {
        int resultado;
        try {
            inicializaVariableMensaje();
            setSession(HibernateUtil.currentSession(uuidCxn));
            getSession().beginTransaction();
            resultado = updateListQuery(PeriodosNomina.class.getSimpleName(), campoModificar, valoresModificado, camposWhere, valoresWhere);
            mensajeResultado.setResultado(resultado);
            mensajeResultado.setNoError(0);
            mensajeResultado.setError("");
            getSession().getTransaction().commit();
        } catch (HibernateException ex) {
            System.err.println(concatena.delete(0, concatena.length()).append(msgError).append("actualizaListaPorCampos()1_Error: ").append(ex));
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

    /*boolean*/
    public Mensaje getStatusPeriodo(Long idPeriodo, String uuidCxn) {
        Boolean existe;
        try {
            inicializaVariableMensaje();
            setSession(HibernateUtil.currentSession(uuidCxn));
            getSession().beginTransaction();
            q = getSession().createQuery("Select p.status From PeriodosNomina p where p.id = :idPeriodo");
            q.setParameter("idPeriodo", idPeriodo);
            existe = q.uniqueResult() == null ? false : (Boolean) q.uniqueResult();
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

    @Override
    public Mensaje getPeriodosNominaPorAñoYTipoCorrida(Integer year, String claveTipoCorrida, String uuidCxn) {
        List<PeriodosNomina> periodos;
        try {
            inicializaVariableMensaje();
            setSession(HibernateUtil.currentSession(uuidCxn));
            getSession().beginTransaction();
            q = getSession().createQuery("from PeriodosNomina o where o.año=:year and o.tipoCorrida.clave=:claveTipoCorrida ORDER by o.tipoNomina.clave");
            q.setParameter("year", year);
            q.setParameter("claveTipoCorrida", claveTipoCorrida);
            periodos = q.list();
            mensajeResultado.setResultado(periodos);
            mensajeResultado.setNoError(0);
            mensajeResultado.setError("");
            getSession().getTransaction().commit();
        } catch (HibernateException ex) {
            System.err.println(concatena.delete(0, concatena.length()).append(msgError).append("getPeriodosNominaPorAñoYTipoCorrida()1_Error: ").append(ex));
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
    public Mensaje getPeriodosNominaPorAño(Integer year, String uuidCxn) {
        try {
            inicializaVariableMensaje();
            setSession(HibernateUtil.currentSession(uuidCxn));
            getSession().beginTransaction();
            q = getSession().createQuery("select count(*) from PeriodosNomina o where o.año=:year");
            q.setParameter("year", year);
            if (((Number) q.uniqueResult()).intValue() > 0) {
                mensajeResultado.setResultado(true);
            } else {
                mensajeResultado.setResultado(false);
            }
            mensajeResultado.setNoError(0);
            mensajeResultado.setError("");
            getSession().getTransaction().commit();
        } catch (HibernateException ex) {
            System.err.println(concatena.delete(0, concatena.length()).append(msgError).append("getPeriodosNominaPorAño()1_Error: ").append(ex));
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
    public Mensaje getPeriodosNominaPorFechayTipoCorridaSinStatus(Date fecha, String claveTipoNomina, String claveTipoCorrida, String uuidCxn) {
       PeriodosNomina p;
        try {
            inicializaVariableMensaje();
            setSession(HibernateUtil.currentSession(uuidCxn));
            getSession().beginTransaction();
            q = getSession().createQuery("Select p from PeriodosNomina p INNER JOIN p.tipoCorrida tc where (p.fechaInicial >=:fecha or p.fechaFinal >=:fecha) and p.tipoNomina.clave =:claveTipoNomina and tc.clave = :claveTipoCorrida order by p.año,p.clave");
            q.setDate("fecha", fecha);
            q.setParameter("claveTipoNomina", claveTipoNomina);
            
            q.setParameter("claveTipoCorrida", claveTipoCorrida);
            
            q.setFirstResult(0);
            q.setMaxResults(1);
            p = (PeriodosNomina) q.uniqueResult();
            mensajeResultado.setResultado(p);
            mensajeResultado.setNoError(0);
            mensajeResultado.setError("");
            getSession().getTransaction().commit();
        } catch (HibernateException ex) {
            System.err.println(concatena.delete(0, concatena.length()).append(msgError).append("getPeriodosNominaPorFechayTipoCorridaSinStatus()1_Error: ").append(ex));
            getSession().getTransaction().rollback();
        }
        return mensajeResultado;
    }

}
