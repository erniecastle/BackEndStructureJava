/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mef.erp.modelo;

import com.mef.erp.modelo.entidad.CreditoMovimientos;
import com.mef.erp.modelo.entidad.CreditoPorEmpleado;
import com.mef.erp.modelo.entidad.Mensaje;
import com.mef.erp.modelo.entidad.TiposMovimiento;
import com.mef.erp.util.ControlErroresHibernate;
import com.mef.erp.util.HibernateUtil;
import java.util.Date;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.exception.ConstraintViolationException;

/**
 *
 * @author Ernesto
 */
public class CreditoMovimientosDAO extends GenericHibernateDAO<CreditoMovimientos, Long>
        implements CreditoMovimientosIF {

    private final StringBuilder concatena = new StringBuilder(200);
    private final String msgError = concatena.append("ServerERP").append(".MSERR_C_").append(this.getClass().getName()).append(".").toString();

    public Mensaje getCreditoMovimientos(Date fecha, String tipoCredito, TiposMovimiento tipoMovimiento, String razonesSociales, String tipoConfiguracion, String uuidCxn) {
        List<CreditoMovimientos> values;
        try {
            inicializaVariableMensaje();
            setSession(HibernateUtil.currentSession(uuidCxn));
            getSession().beginTransaction();
            consulta = new StringBuilder();
            consulta.append("from CreditoMovimientos c where c.fecha=:fecha and ");
            consulta.append("c.creditoPorEmpleado.creditoAhorro.clave=:claveTipoCredito and ");
            consulta.append("c.tiposMovimiento=:tipoMovimiento and ");
            consulta.append("c.creditoPorEmpleado.creditoAhorro.tipoConfiguracion=:tipoConfiguracion  and ");
            consulta.append("c.creditoPorEmpleado.razonesSociales.clave=:claveRazonesSociales  ");
            q = getSession().createQuery(consulta.toString());
            q.setDate("fecha", fecha);
            q.setParameter("claveTipoCredito", tipoCredito);
            q.setParameter("tipoMovimiento", tipoMovimiento);
            q.setParameter("tipoConfiguracion", tipoConfiguracion);
            q.setParameter("claveRazonesSociales", razonesSociales);
            values = q.list();
            mensajeResultado.setResultado(values);
            mensajeResultado.setNoError(0);
            mensajeResultado.setError("");
            getSession().getTransaction().commit();
        } catch (HibernateException ex) {
            System.err.println(concatena.delete(0, concatena.length()).append(msgError).append("getCreditoMovimientos()1_Error: ").append(ex));
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
        return mensajeResultado;
    }

    public Mensaje getMaxNumeroCreditoMovimiento(CreditoPorEmpleado credito, TiposMovimiento tiposMovimiento, Date fecha, String uuidCxn) {
        Object maxValue;
        try {
            consulta = new StringBuilder();
            inicializaVariableMensaje();
            setSession(HibernateUtil.currentSession(uuidCxn));
            getSession().beginTransaction();
            consulta.append("select MAX(c.numero) from CreditoMovimientos c");
            consulta.append(" where c.creditoPorEmpleado=:credito");
            consulta.append(" and c.tiposMovimiento=:tiposMovimiento");
            consulta.append(" and c.fecha=:fecha");
            q = getSession().createQuery(consulta.toString());
            q.setParameter("credito", credito);
            q.setParameter("tiposMovimiento", tiposMovimiento);
            q.setParameter("fecha", fecha);
            maxValue = q.uniqueResult();
            mensajeResultado.setResultado(maxValue);
            mensajeResultado.setNoError(0);
            mensajeResultado.setError("");
            getSession().getTransaction().commit();
        } catch (HibernateException ex) {
            System.err.println(concatena.delete(0, concatena.length()).append(msgError).append("getMaxNumeroCreditoMovimiento()1_Error: ").append(ex));
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
        return mensajeResultado;
    }

    public Mensaje saveDeleteCreditosMov(List<CreditoMovimientos> entitysCambios, List<CreditoMovimientos> deleteCreditos, TiposMovimiento tiposMovimiento, String uuidCxn) {
        CreditoMovimientos credMov = null;
        try {
            boolean commit = true;
            inicializaVariableMensaje();
            setSession(HibernateUtil.currentSession(uuidCxn));
            getSession().beginTransaction();
            if (deleteCreditos == null ? false : !deleteCreditos.isEmpty()) {
                commit = deleteListQueryCreditoMovimientos(deleteCreditos, uuidCxn);
                if (commit) {
                    clear();
                }
            }
            if (commit && !entitysCambios.isEmpty()) {
                for (int i = 0; i < entitysCambios.size(); i++) {
                    credMov = entitysCambios.get(i);
                    getSession().saveOrUpdate(entitysCambios.get(i));
                }
            }
            getSession().getTransaction().commit();
            mensajeResultado.setResultado(null);
            mensajeResultado.setNoError(0);
            mensajeResultado.setError("");
        } catch (ConstraintViolationException ex) {
            System.err.println(concatena.delete(0, concatena.length()).append(msgError).append("saveDeleteCreditosMov()1_Error: ").append(ex));
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
            mensajeResultado.setResultado(credMov);
        } catch (HibernateException ex) {
            if (getSession().getTransaction().isActive()) {
                getSession().getTransaction().rollback();
            }
            System.err.println(concatena.delete(0, concatena.length()).append(msgError).append("saveDeleteCreditosMov()2_Error: ").append(ex));
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
            mensajeResultado.setResultado(credMov);
        }

        return mensajeResultado;
    }

    private boolean deleteListQueryCreditoMovimientos(List<CreditoMovimientos> deleteCreditos, String uuidCxn) {
        boolean exito = true;
        try {
            Object[] valores = new Object[deleteCreditos.size()];
            for (int i = 0; i < deleteCreditos.size(); i++) {
                valores[i] = deleteCreditos.get(i).getId();
            }
            deleteListQuery("CreditoMovimientos", "Id", valores);
//            for (int i = 0; i < deleteCreditos.size(); i++) {
////                if (deleteCreditos.get(i).getTiposMovimiento() == TiposMovimiento.Abono || deleteCreditos.get(i).getTiposMovimiento() == TiposMovimiento.Cargo) {
////                    if (deleteCreditos.get(i).getCreditoPorEmpleado().getCreditoAhorro().getTipoConfiguracion().equalsIgnoreCase("1"))//Creditos
////                    {
////                        if (deleteCreditos.get(i).getTiposMovimiento() == TiposMovimiento.Abono) {
////                            deleteCreditos.get(i).getCreditoPorEmpleado().setSaldo(deleteCreditos.get(i).getCreditoPorEmpleado().getSaldo() + deleteCreditos.get(i).getImporte());
////                        } else {
////                            deleteCreditos.get(i).getCreditoPorEmpleado().setSaldo(deleteCreditos.get(i).getCreditoPorEmpleado().getSaldo() - deleteCreditos.get(i).getImporte());
////                        }
////                    } else {
////                        deleteCreditos.get(i).getCreditoPorEmpleado().setSaldo(deleteCreditos.get(i).getCreditoPorEmpleado().getSaldo() - deleteCreditos.get(i).getImporte());
////                    }
////                    getSession().saveOrUpdate(deleteCreditos.get(i).getCreditoPorEmpleado());
////                }
//                getSession().delete(deleteCreditos.get(i));
//            }

        } catch (HibernateException ex) {
            System.err.println(concatena.delete(0, concatena.length()).append(msgError).append("deleteListQuery()1_Error: ").append(ex));
            mensajeResultado.setNoError(ControlErroresHibernate.buscaNoErrorPorExcepcion(ex.getClass()));
            mensajeResultado.setError(ex.getLocalizedMessage());
            mensajeResultado.setResultado(null);
            exito = false;
        }
        return exito;
    }
}
