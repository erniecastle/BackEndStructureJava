/**
 * @author: Daniel Fecha de Creación: 29/12/2016 Compañía: Exito Software
 * Descripción del programa: clase DiasAguinaldo para llamados a metodos de
 * HIBERNATE
 * -----------------------------------------------------------------------------
 */
package com.mef.erp.modelo;

import com.mef.erp.modelo.entidad.DiasAguinaldo;
import com.mef.erp.modelo.entidad.Mensaje;
import com.mef.erp.util.ControlErroresHibernate;
import com.mef.erp.util.HibernateUtil;
import static java.util.Collections.list;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Transaction;

/**
 *
 * @author Desarrollo 094
 */
public class DiasAguinaldoDAO extends GenericHibernateDAO<DiasAguinaldo, Long>
        implements DiasAguinaldoDAOIF {

    Query query;
    private final StringBuilder concatena = new StringBuilder(200);
    private final String msgError = concatena.append("ServerERP"/*util.getContexto()*/).append(".MSERR_C_").append(this.getClass().getName()).append(".").toString();

    @Override
    public Mensaje agregar(DiasAguinaldo entity, String uuidCxn) {
        DiasAguinaldo diasAguinaldo;
        try {
            inicializaVariableMensaje();
            setSession(HibernateUtil.currentSession(uuidCxn));
            getSession().beginTransaction();
            diasAguinaldo = makePersistent(entity);
            mensajeResultado.setResultado(diasAguinaldo);
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
            } catch (HibernateException exc) { //cuando marca error al hacer rollback pro ejemplo se cae conexion servidor
                mensajeResultado.setNoError(ControlErroresHibernate.buscaNoErrorPorExcepcion(exc.getClass()));
                mensajeResultado.setError(exc.getLocalizedMessage());
            }
            mensajeResultado.setResultado(null);
        }
        return mensajeResultado;
    }

    @Override
    public Mensaje actualizar(DiasAguinaldo entity, String uuidCxn) {
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
            } catch (HibernateException exc) { //cuando marca error al hacer rollback pro ejemplo se cae conexion servidor
                mensajeResultado.setNoError(ControlErroresHibernate.buscaNoErrorPorExcepcion(exc.getClass()));
                mensajeResultado.setError(exc.getLocalizedMessage());
            }
            mensajeResultado.setResultado(null);
        }
        return mensajeResultado;
    }

    @Override
    public Mensaje eliminar(DiasAguinaldo entity, String uuidCxn) {
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
            } catch (HibernateException exc) { //cuando marca error al hacer rollback pro ejemplo se cae conexion servidor
                mensajeResultado.setNoError(ControlErroresHibernate.buscaNoErrorPorExcepcion(exc.getClass()));
                mensajeResultado.setError(exc.getLocalizedMessage());
            }
            mensajeResultado.setResultado(null);
        }
        return mensajeResultado;
    }

    @Override
    public Mensaje getDiasAguinaldoAll(String uuidCxn) {
        List<DiasAguinaldo> values;
        try {
            inicializaVariableMensaje();
            setSession(HibernateUtil.currentSession(uuidCxn));
            getSession().beginTransaction();
            query = getSession().createQuery("from DiasAguinaldo");
            values = query.list();
            mensajeResultado.setResultado(values);
            mensajeResultado.setNoError(0);
            mensajeResultado.setError("");
            getSession().getTransaction().commit();
        } catch (HibernateException ex) {
            System.err.println(concatena.delete(0, concatena.length()).append(msgError).append("getDiasAguinaldoAll()1_Error: ").append(ex));
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

    @Override
    public Mensaje SaveDiasAguinaldo(List<DiasAguinaldo> agrega, Object[] eliminados, String uuidCxn) {
        boolean exito = true;

        Transaction transaction = null;
        boolean commit = true;
        try {
            inicializaVariableMensaje();
            setSession(HibernateUtil.currentSession(uuidCxn));
            transaction = getSession().beginTransaction();
            if (eliminados != null && eliminados.length > 0) {
                commit = deleteListQuery(eliminados, uuidCxn);
                clear();
            }
            if (!agrega.isEmpty()) {
                for (int i = 0; i < agrega.size(); i++) {

                    getSession().saveOrUpdate(agrega.get(i));
                    if (i % 100 == 0) {
                        getSession().flush();
                    }
                }
            }

            if (commit) {
                mensajeResultado.setResultado(commit);
                mensajeResultado.setNoError(0);
                mensajeResultado.setError("");
                transaction.commit();
            }

        } catch (HibernateException ex) {
            System.err.println(concatena.delete(0, concatena.length()).append(msgError).append("SaveAguinaldoFechas()1_Error: ").append(ex));
            try {
                if (transaction != null) {
                    transaction.rollback();
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

    private boolean deleteListQuery(Object[] eliminados, String uuidCxn) {
        boolean commit = true;
        try {
            deleteListQuery("DiasAguinaldo", "Id", eliminados);
        } catch (HibernateException ex) {
            System.err.println(concatena.delete(0, concatena.length()).append(msgError).append("deleteListQuery()1_Error: ").append(ex));
            mensajeResultado.setNoError(ControlErroresHibernate.buscaNoErrorPorExcepcion(ex.getClass()));
            mensajeResultado.setError(ex.getLocalizedMessage());
            mensajeResultado.setResultado(null);
            commit = false;
        }
        return commit;
    }

    @Override
    public Mensaje getDiasAguinaldoPorClave(String claveRazonsocial, String uuidCxn) {
        List<DiasAguinaldo> r;
        try {
            inicializaVariableMensaje();
            setSession(HibernateUtil.currentSession(uuidCxn));
            getSession().beginTransaction();
            q = getSession().createQuery("from DiasAguinaldo d where d.razonesSociales.clave=:claveRazonsocial ");
            q.setParameter("claveRazonsocial", claveRazonsocial);
            r = q.list();
            mensajeResultado.setResultado(r);
            mensajeResultado.setNoError(0);
            mensajeResultado.setError("");
            getSession().getTransaction().commit();
        } catch (HibernateException ex) {
            System.err.println(concatena.delete(0, concatena.length()).append(msgError).append("getDiasAguinaldoPorClave()2_Error: ").append(ex));
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
