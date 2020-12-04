/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mef.erp.modelo;

import com.mef.erp.modelo.entidad.Mensaje;
import com.mef.erp.modelo.entidad.Monedas;
import com.mef.erp.modelo.entidad.TiposDeCambio;
import com.mef.erp.util.ControlErroresHibernate;
import com.mef.erp.util.HibernateUtil;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author daniel
 */
public class TiposDeCambioDAO extends GenericHibernateDAO<TiposDeCambio, Long>
        implements TiposDeCambioDAOIF {

    private StringBuilder concatena = new StringBuilder(200);
    private final String msgError = concatena.append("ServerERP").append(".MSERR_F_").append(this.getClass().getName()).append(".").toString();

    public Mensaje agregar(TiposDeCambio entity, String uuidCxn) {
        TiposDeCambio tiposDeCambio;
        Transaction tx = null;
        try {
            inicializaVariableMensaje();
            setSession(HibernateUtil.currentSession(uuidCxn));
            tx = getSession().beginTransaction();
            tiposDeCambio = makePersistent(entity);
            mensajeResultado.setResultado(tiposDeCambio);
            mensajeResultado.setNoError(0);
            mensajeResultado.setError("");
            getSession().getTransaction().commit();
        } catch (HibernateException e) {
            System.err.println(concatena.delete(0, concatena.length()).append(msgError).append("agregar()1_Error: ").append(e));
            try {
                if (tx != null) {
                    tx.rollback();
                }
                mensajeResultado.setNoError(ControlErroresHibernate.buscaNoErrorPorExcepcion(e.getClass()));
                mensajeResultado.setError(e.getLocalizedMessage());
            } catch (HibernateException exc) {  //cuando marca error al hacer rollback pro ejemplo se cae conexion servidor
                mensajeResultado.setNoError(ControlErroresHibernate.buscaNoErrorPorExcepcion(exc.getClass()));
                mensajeResultado.setError(exc.getLocalizedMessage());
            }
            mensajeResultado.setResultado(null);
        }
        return mensajeResultado;
    }

    public Mensaje actualizar(TiposDeCambio entity, String uuidCxn) {
        Transaction tx = null;
        try {
            inicializaVariableMensaje();
            setSession(HibernateUtil.currentSession(uuidCxn));
            tx = getSession().beginTransaction();
            makePersistent(entity);
            mensajeResultado.setResultado(true);
            mensajeResultado.setNoError(0);
            mensajeResultado.setError("");
            getSession().getTransaction().commit();
        } catch (HibernateException e) {
            System.err.println(concatena.delete(0, concatena.length()).append(msgError).append("actualizar()1_Error: ").append(e));
            try {
                if (tx != null) {
                    tx.rollback();
                }
                mensajeResultado.setNoError(ControlErroresHibernate.buscaNoErrorPorExcepcion(e.getClass()));
                mensajeResultado.setError(e.getLocalizedMessage());
            } catch (HibernateException exc) {  //cuando marca error al hacer rollback pro ejemplo se cae conexion servidor
                mensajeResultado.setNoError(ControlErroresHibernate.buscaNoErrorPorExcepcion(exc.getClass()));
                mensajeResultado.setError(exc.getLocalizedMessage());
            }
            mensajeResultado.setResultado(null);
        }
        return mensajeResultado;
    }

    public Mensaje eliminar(TiposDeCambio entity, String uuidCxn) {
        Transaction tx = null;
        try {
            inicializaVariableMensaje();
            setSession(HibernateUtil.currentSession(uuidCxn));
            tx = getSession().beginTransaction();
            makeTransient(entity);
            mensajeResultado.setResultado(true);
            mensajeResultado.setNoError(0);
            mensajeResultado.setError("");
            getSession().getTransaction().commit();
        } catch (HibernateException e) {
            System.err.println(concatena.delete(0, concatena.length()).append(msgError).append("eliminar()1_Error: ").append(e));
            try {
                if (tx != null) {
                    tx.rollback();
                }
                mensajeResultado.setNoError(ControlErroresHibernate.buscaNoErrorPorExcepcion(e.getClass()));
                mensajeResultado.setError(e.getLocalizedMessage());
            } catch (HibernateException exc) {  //cuando marca error al hacer rollback pro ejemplo se cae conexion servidor
                mensajeResultado.setNoError(ControlErroresHibernate.buscaNoErrorPorExcepcion(exc.getClass()));
                mensajeResultado.setError(exc.getLocalizedMessage());
            }
            mensajeResultado.setResultado(null);
        }
        return mensajeResultado;
    }

    public Mensaje getTiposDeCambioAll(String uuidCxn) {
        Transaction tx = null;
        List<TiposDeCambio> values;
        try {
            inicializaVariableMensaje();
            setSession(HibernateUtil.currentSession(uuidCxn));
            tx = getSession().beginTransaction();
            q = getSession().createQuery("from TiposDeCambio");
            values = q.list();
            mensajeResultado.setResultado(values);
            mensajeResultado.setNoError(0);
            mensajeResultado.setError("");
            getSession().getTransaction().commit();
        } catch (HibernateException e) {
            System.err.println(concatena.delete(0, concatena.length()).append(msgError).append("getTiposDeCambioAll()1_Error: ").append(e));
            try {
                if (tx != null) {
                    tx.rollback();
                }
                mensajeResultado.setNoError(ControlErroresHibernate.buscaNoErrorPorExcepcion(e.getClass()));
                mensajeResultado.setError(e.getLocalizedMessage());
            } catch (HibernateException exc) {  //cuando marca error al hacer rollback pro ejemplo se cae conexion servidor
                mensajeResultado.setNoError(ControlErroresHibernate.buscaNoErrorPorExcepcion(exc.getClass()));
                mensajeResultado.setError(exc.getLocalizedMessage());
            }
            mensajeResultado.setResultado(null);
        }
        return mensajeResultado;
    }

    public Mensaje getTiposDeCambioPorClave(String clave, String uuidCxn) {
        Transaction tx = null;
        TiposDeCambio a;
        try {
            inicializaVariableMensaje();
            setSession(HibernateUtil.currentSession(uuidCxn));
            tx = getSession().beginTransaction();
            q = getSession().createQuery("from TiposDeCambio where clave = :clave");
            q.setString("clave", clave);
            a = (TiposDeCambio) q.uniqueResult();
            mensajeResultado.setResultado(a);
            mensajeResultado.setNoError(0);
            mensajeResultado.setError("");
            getSession().getTransaction().commit();
        } catch (HibernateException e) {
            System.err.println(concatena.delete(0, concatena.length()).append(msgError).append("getTiposDeCambioPorClave()1_Error: ").append(e));
            try {
                if (tx != null) {
                    tx.rollback();
                }
                mensajeResultado.setNoError(ControlErroresHibernate.buscaNoErrorPorExcepcion(e.getClass()));
                mensajeResultado.setError(e.getLocalizedMessage());
            } catch (HibernateException exc) {  //cuando marca error al hacer rollback pro ejemplo se cae conexion servidor
                mensajeResultado.setNoError(ControlErroresHibernate.buscaNoErrorPorExcepcion(exc.getClass()));
                mensajeResultado.setError(exc.getLocalizedMessage());
            }
            mensajeResultado.setResultado(null);
        }
        return mensajeResultado;
    }

    public Mensaje getTiposDeCambioPorMoneda(Monedas m, String uuidCxn) {
        Transaction tx = null;
        List<TiposDeCambio> values;
        try {
            inicializaVariableMensaje();
            setSession(HibernateUtil.currentSession(uuidCxn));
            tx = getSession().beginTransaction();
            q = getSession().createQuery("from TiposDeCambio where monedas_id =:moneda order by fecha asc");
            q.setEntity("moneda", m);
            values = q.list();
            mensajeResultado.setResultado(values);
            mensajeResultado.setNoError(0);
            mensajeResultado.setError("");
            getSession().getTransaction().commit();
        } catch (HibernateException e) {
            System.err.println(concatena.delete(0, concatena.length()).append(msgError).append("getTiposDeCambioPorMoneda()1_Error: ").append(e));
            try {
                if (tx != null) {
                    tx.rollback();
                }
                mensajeResultado.setNoError(ControlErroresHibernate.buscaNoErrorPorExcepcion(e.getClass()));
                mensajeResultado.setError(e.getLocalizedMessage());
            } catch (HibernateException exc) {  //cuando marca error al hacer rollback pro ejemplo se cae conexion servidor
                mensajeResultado.setNoError(ControlErroresHibernate.buscaNoErrorPorExcepcion(exc.getClass()));
                mensajeResultado.setError(exc.getLocalizedMessage());
            }
            mensajeResultado.setResultado(null);
        }
        return mensajeResultado;
    }

    public Mensaje guardaTiposDeCambio(List<TiposDeCambio> agrega, Object[] eliminados, String uuidCxn) {
        boolean exitoRegistro = true;

        TiposDeCambio tiposDeCambio;
        Transaction transaction = null;
        try {
            inicializaVariableMensaje();
            setSession(HibernateUtil.currentSession(uuidCxn));
            transaction = getSession().beginTransaction();
            if (eliminados.length > 0) {
                exitoRegistro = eliminaListQueryEn(getSession(), "TiposDeCambio", "id", eliminados);
            }
            agrega = agrega == null ? new ArrayList<TiposDeCambio>() : agrega;
            if (exitoRegistro && !agrega.isEmpty()) {
                for (int i = 0; i < agrega.size(); i++) {
                    getSession().saveOrUpdate(agrega.get(i));
                    if (i % 100 == 0 && i > 0) {
                        flush();
                    }
                }
            }

            if (exitoRegistro) { //pendiente
                tiposDeCambio = new TiposDeCambio();
                mensajeResultado.setResultado(tiposDeCambio);
                mensajeResultado.setNoError(0);
                mensajeResultado.setError("");
                transaction.commit();
            } else {
                transaction.rollback();
            }
        } catch (HibernateException ex) {
            System.err.println(concatena.delete(0, concatena.length()).append(msgError).append("guardaTiposDeCambio()1_Error: ").append(ex));
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

    public Mensaje actualizaTiposDeCambio(List<TiposDeCambio> agrega, Object[] eliminados, String uuidCxn) {
        boolean exitoRegistro = true;
        Transaction transaction = null;
        try {
            inicializaVariableMensaje();
            setSession(HibernateUtil.currentSession(uuidCxn));
            transaction = getSession().beginTransaction();
            if (eliminados != null && eliminados.length > 0) {
                exitoRegistro = eliminaListQueryEn(getSession(), "TiposDeCambio", "id", eliminados);
            }
            agrega = agrega == null ? new ArrayList<TiposDeCambio>() : agrega;
            if (exitoRegistro && !agrega.isEmpty()) {
                for (int i = 0; i < agrega.size(); i++) {
                    getSession().saveOrUpdate(agrega.get(i));
                    if (i % 100 == 0 && i > 0) {
                        flush();
                    }
                }
            }
            if (exitoRegistro) {
                transaction.commit();
                mensajeResultado.setResultado(true);
                mensajeResultado.setNoError(0);
                mensajeResultado.setError("");
            } else {
                transaction.rollback();
            }
        } catch (HibernateException ex) {
            System.err.println(concatena.delete(0, concatena.length()).append(msgError).append("actualizaTiposDeCambio()1_Error: ").append(ex));
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

    public Mensaje eliminaTiposDeCambio(Object[] eliminados, String uuidCxn) {
        boolean exitoRegistro = true;
        Transaction transaction = null;
        try {
            inicializaVariableMensaje();
            Session session = HibernateUtil.currentSession(uuidCxn);
            transaction = session.beginTransaction();
            exitoRegistro = eliminaListQueryEn(session, "TiposDeCambio", "id", eliminados);
            if (exitoRegistro) {
                mensajeResultado.setResultado(true);
                mensajeResultado.setNoError(0);
                mensajeResultado.setError("");
                transaction.commit();
            } else {
                transaction.rollback();
            }
        } catch (HibernateException ex) {
            System.err.println(concatena.delete(0, concatena.length()).append(msgError).append("eliminaTiposDeCambio()1_Error: ").append(ex));
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

    private boolean eliminaListQueryEn(Session session, String tabla, String campo, Object[] valores) {
        boolean exito = true;
        try {
            concatena = new StringBuilder("delete ");
            concatena.append(tabla).append(" where ").append(campo).append(" in(:valores)");
            q = session.createQuery(concatena.toString());
            q.setParameterList("valores", valores);
            q.executeUpdate();
        } catch (HibernateException ex) {
            System.err.println(concatena.delete(0, concatena.length()).append(msgError).append("deleteListQuerys()1_Error: ").append(ex));
            mensajeResultado.setNoError(ControlErroresHibernate.buscaNoErrorPorExcepcion(ex.getClass()));
            mensajeResultado.setError(ex.getLocalizedMessage());
            mensajeResultado.setResultado(null);
            exito = false;
        }
        return exito;
    }

    public Mensaje getTiposDeCambioPorFecha(Date fecha, String uuidCxn) {
        Transaction tx = null;
        List<TiposDeCambio> values;
        try {
            inicializaVariableMensaje();
            setSession(HibernateUtil.currentSession(uuidCxn));
            tx = getSession().beginTransaction();
            q = getSession().createQuery("from TiposDeCambio where fecha =:fecha");
            q.setDate("fecha", fecha);
            values = q.list();
            mensajeResultado.setResultado(values);
            mensajeResultado.setNoError(0);
            mensajeResultado.setError("");
            getSession().getTransaction().commit();
        } catch (HibernateException e) {
            System.err.println(concatena.delete(0, concatena.length()).append(msgError).append("getTiposDeCambioPorFecha()1_Error: ").append(e));
            try {
                if (tx != null) {
                    tx.rollback();
                }
                mensajeResultado.setNoError(ControlErroresHibernate.buscaNoErrorPorExcepcion(e.getClass()));
                mensajeResultado.setError(e.getLocalizedMessage());
            } catch (HibernateException exc) {  //cuando marca error al hacer rollback pro ejemplo se cae conexion servidor
                mensajeResultado.setNoError(ControlErroresHibernate.buscaNoErrorPorExcepcion(exc.getClass()));
                mensajeResultado.setError(exc.getLocalizedMessage());
            }
            mensajeResultado.setResultado(null);
        }
        return mensajeResultado;
    }
}
