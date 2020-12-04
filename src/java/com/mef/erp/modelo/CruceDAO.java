/**
 * @author: Ernesto Valenzuela Fecha de Creación: 27/05/2011 Compañía: Exito
 * Software. Descripción del programa: clase Cruces para llamados a metodos de
 * HIBERNATE
 * -----------------------------------------------------------------------------
 * MODIFICACIONES:
 * -----------------------------------------------------------------------------
 * Clave: JSA01 Autor: Jose Armando Sanchez Acosta Fecha: 07-07-2011
 * Descripción: Se agregaron PMD.
 * -----------------------------------------------------------------------------
 * Clave:JSA02 Autor:Jose Armando Sanchez Acosta Fecha:10/11/2011 Descripción:Se
 * cambiaron todos los metodos hibernate al dao
 * -----------------------------------------------------------------------------
 */
package com.mef.erp.modelo;

import com.mef.erp.modelo.entidad.Cruce;
import com.mef.erp.modelo.entidad.Mensaje;
import com.mef.erp.util.ControlErroresHibernate;
import com.mef.erp.util.HibernateUtil;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Transaction;

public class CruceDAO extends GenericHibernateDAO<Cruce, Integer> implements CruceDAOIF {

    private final StringBuilder concatena = new StringBuilder(200);
    private final String msgError = concatena.append("ServerERP").append(".MSERR_C_").append(this.getClass().getName()).append(".").toString();

    public Mensaje agregar(String uuidCxn, Cruce entity) {//JSA01

        Transaction tx = null;
        Cruce cruce;
        try {
            inicializaVariableMensaje();
            setSession(HibernateUtil.currentSession(uuidCxn));
            tx = getSession().beginTransaction();
            cruce = makePersistent(entity);
            mensajeResultado.setResultado(cruce);
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
            } catch (HibernateException exc) { //cuando marca error al hacer rollback pro ejemplo se cae conexion servidor
                mensajeResultado.setNoError(ControlErroresHibernate.buscaNoErrorPorExcepcion(exc.getClass()));
                mensajeResultado.setError(exc.getLocalizedMessage());
            }
            mensajeResultado.setResultado(null);
        }

        return mensajeResultado;
    }

    public Mensaje actualizar(String uuidCxn, Cruce entity) {//JSA01
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
            } catch (HibernateException exc) { //cuando marca error al hacer rollback pro ejemplo se cae conexion servidor
                mensajeResultado.setNoError(ControlErroresHibernate.buscaNoErrorPorExcepcion(exc.getClass()));
                mensajeResultado.setError(exc.getLocalizedMessage());
            }
            mensajeResultado.setResultado(null);
        }
        return mensajeResultado;
    }

    public Mensaje eliminar(String uuidCxn, Cruce entity) {//JSA01
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
            } catch (HibernateException exc) { //cuando marca error al hacer rollback pro ejemplo se cae conexion servidor
                mensajeResultado.setNoError(ControlErroresHibernate.buscaNoErrorPorExcepcion(exc.getClass()));
                mensajeResultado.setError(exc.getLocalizedMessage());
            }
            mensajeResultado.setResultado(null);
        }
        return mensajeResultado;
    }

    public Mensaje getCruceAll(String uuidCxn) {//JSA01
        Transaction tx = null;
        List<Cruce> values;
        try {
            inicializaVariableMensaje();
            setSession(HibernateUtil.currentSession(uuidCxn));
            tx = getSession().beginTransaction();
            q = getSession().createQuery("from Cruce order by ordenId asc");
            values = q.list();
            mensajeResultado.setResultado(values);
            mensajeResultado.setNoError(0);
            mensajeResultado.setError("");
            getSession().getTransaction().commit();
        } catch (HibernateException e) {
            System.err.println(concatena.delete(0, concatena.length()).append(msgError).append("getCruceAll()1_Error: ").append(e));
            try {
                if (tx != null) {
                    tx.rollback();
                }
                mensajeResultado.setNoError(ControlErroresHibernate.buscaNoErrorPorExcepcion(e.getClass()));
                mensajeResultado.setError(e.getLocalizedMessage());
            } catch (HibernateException exc) { //cuando marca error al hacer rollback pro ejemplo se cae conexion servidor
                mensajeResultado.setNoError(ControlErroresHibernate.buscaNoErrorPorExcepcion(exc.getClass()));
                mensajeResultado.setError(exc.getLocalizedMessage());
            }
            mensajeResultado.setResultado(null);
        }

        return mensajeResultado;
    }

    public Mensaje existeClaveElemento(String uuidCxn, String claveelemento, String elemento, Long parametro) {//JSA01
        Transaction tx = null;
        boolean e;
        try {
            inicializaVariableMensaje();
            setSession(HibernateUtil.currentSession(uuidCxn));
            tx = getSession().beginTransaction();
            //JSA01        
            q = getSession().createQuery("from Cruce where claveElemento = :claveelemento and elementosaplicacion.clave = :elemento and parametros.clave = :parametro");
            q.setParameter("claveelemento", claveelemento);
            q.setParameter("elemento", elemento);
            q.setParameter("parametro", parametro);

            e = q.iterate().hasNext();
            if (tx != null) {
                tx.commit();
            }
            mensajeResultado.setResultado(e);
            mensajeResultado.setNoError(0);
            mensajeResultado.setError("");
        } catch (HibernateException ex) {
            System.err.println(concatena.delete(0, concatena.length()).append(msgError).append("existeClaveElemento()1_Error: ").append(ex));
            try {
                if (tx != null) {
                    tx.rollback();
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

    public Mensaje getCrucePorParametros(String uuidCxn, Long claveParametro) {//JSA01
        Transaction tx = null;
        List<Cruce> values;
        try {
            inicializaVariableMensaje();
            setSession(HibernateUtil.currentSession(uuidCxn));
            tx = getSession().beginTransaction();
            q = getSession().createQuery("from Cruce where parametros.clave = :clave order by ordenId asc");
            q.setParameter("clave", claveParametro);
            values = q.list();
            mensajeResultado.setResultado(values);
            mensajeResultado.setNoError(0);
            mensajeResultado.setError("");
            getSession().getTransaction().commit();

        } catch (HibernateException e) {
            System.err.println(concatena.delete(0, concatena.length()).append(msgError).append("getCrucePorParametros()1_Error: ").append(e));
            try {
                if (tx != null) {
                    tx.rollback();
                }
                mensajeResultado.setNoError(ControlErroresHibernate.buscaNoErrorPorExcepcion(e.getClass()));
                mensajeResultado.setError(e.getLocalizedMessage());
            } catch (HibernateException exc) { //cuando marca error al hacer rollback pro ejemplo se cae conexion servidor
                mensajeResultado.setNoError(ControlErroresHibernate.buscaNoErrorPorExcepcion(exc.getClass()));
                mensajeResultado.setError(exc.getLocalizedMessage());
            }
            mensajeResultado.setResultado(null);
        }
        return mensajeResultado;
    }

    public Mensaje getCrucePorElemento(String uuidCxn, String clave) {//JSA01
        Transaction tx = null;
        List<Cruce> values;
        try {
            inicializaVariableMensaje();
            setSession(HibernateUtil.currentSession(uuidCxn));
            tx = getSession().beginTransaction();
            q = getSession().createQuery("from Cruce where elementosaplicacion.clave = :clave order by ordenId asc");
            q.setParameter("clave", clave);
            values = q.list();
            mensajeResultado.setResultado(values);
            mensajeResultado.setNoError(0);
            mensajeResultado.setError("");
            getSession().getTransaction().commit();
        } catch (HibernateException e) {
            System.err.println(concatena.delete(0, concatena.length()).append(msgError).append("getCrucePorElemento()1_Error: ").append(e));
            try {
                if (tx != null) {
                    tx.rollback();
                }
                mensajeResultado.setNoError(ControlErroresHibernate.buscaNoErrorPorExcepcion(e.getClass()));
                mensajeResultado.setError(e.getLocalizedMessage());
            } catch (HibernateException exc) { //cuando marca error al hacer rollback pro ejemplo se cae conexion servidor
                mensajeResultado.setNoError(ControlErroresHibernate.buscaNoErrorPorExcepcion(exc.getClass()));
                mensajeResultado.setError(exc.getLocalizedMessage());
            }
            mensajeResultado.setResultado(null);
        }
        return mensajeResultado;
    }

    public Mensaje getCrucePorParamElemento(String uuidCxn, Long claveParametro, String elemento) {//JSA01
        Transaction tx = null;
        List<Cruce> values;
        try {
            inicializaVariableMensaje();
            setSession(HibernateUtil.currentSession(uuidCxn));
            tx = getSession().beginTransaction();

            //JSA01        
            q = getSession().createQuery("from Cruce where parametros.clave = :parametro and elementosaplicacion.clave = :elemento order by ordenId asc");
            q.setParameter("parametro", claveParametro);
            q.setParameter("elemento", elemento);
            values = q.list();
            mensajeResultado.setResultado(values);
            mensajeResultado.setNoError(0);
            mensajeResultado.setError("");
            getSession().getTransaction().commit();
        } catch (HibernateException e) {
            System.err.println(concatena.delete(0, concatena.length()).append(msgError).append("getCrucePorParamElemento()1_Error: ").append(e));
            try {
                if (tx != null) {
                    tx.rollback();
                }
                mensajeResultado.setNoError(ControlErroresHibernate.buscaNoErrorPorExcepcion(e.getClass()));
                mensajeResultado.setError(e.getLocalizedMessage());
            } catch (HibernateException exc) { //cuando marca error al hacer rollback pro ejemplo se cae conexion servidor
                mensajeResultado.setNoError(ControlErroresHibernate.buscaNoErrorPorExcepcion(exc.getClass()));
                mensajeResultado.setError(exc.getLocalizedMessage());
            }
            mensajeResultado.setResultado(null);
        }
        return mensajeResultado;
    }

    public Mensaje getCrucePorElemeYClave(String uuidCxn, String elemento, String claveelemento) {//JSA01
        Transaction tx = null;
        List<Cruce> values;
        try {
            inicializaVariableMensaje();
            setSession(HibernateUtil.currentSession(uuidCxn));
            tx = getSession().beginTransaction();
            //JSA01
            q = getSession().createQuery("from Cruce where elementosaplicacion.clave = :elemento and claveElemento = :claveelemento order by ordenId asc ");
            q.setParameter("elemento", elemento);
            q.setParameter("claveelemento", claveelemento);
            values = q.list();
            mensajeResultado.setResultado(values);
            mensajeResultado.setNoError(0);
            mensajeResultado.setError("");
            getSession().getTransaction().commit();
        } catch (HibernateException e) {
            System.err.println(concatena.delete(0, concatena.length()).append(msgError).append("getCrucePorElemeYClave()1_Error: ").append(e));
            try {
                if (tx != null) {
                    tx.rollback();
                }
                mensajeResultado.setNoError(ControlErroresHibernate.buscaNoErrorPorExcepcion(e.getClass()));
                mensajeResultado.setError(e.getLocalizedMessage());
            } catch (HibernateException exc) { //cuando marca error al hacer rollback pro ejemplo se cae conexion servidor
                mensajeResultado.setNoError(ControlErroresHibernate.buscaNoErrorPorExcepcion(exc.getClass()));
                mensajeResultado.setError(exc.getLocalizedMessage());
            }
            mensajeResultado.setResultado(null);
        }
        return mensajeResultado;
    }

    public Mensaje getCrucePorParaElemeYClave(String uuidCxn, Long claveParametro, String elemento, String claveelemento) {//JSA01
        Transaction tx = null;
        List<Cruce> values;
        try {
            inicializaVariableMensaje();
            setSession(HibernateUtil.currentSession(uuidCxn));
            tx = getSession().beginTransaction();
            //JSA01        
            q = getSession().createQuery("from Cruce where parametros.clave = :parametro and elementosaplicacion.clave = :elemento and claveElemento = :claveelemento order by ordenId asc");
            q.setParameter("parametro", claveParametro);
            q.setParameter("elemento", elemento);
            q.setParameter("claveelemento", claveelemento);
            values = q.list();
            mensajeResultado.setResultado(values);
            mensajeResultado.setNoError(0);
            mensajeResultado.setError("");
            getSession().getTransaction().commit();
        } catch (HibernateException e) {
            System.err.println(concatena.delete(0, concatena.length()).append(msgError).append("getCrucePorParaElemeYClave()1_Error: ").append(e));
            try {
                if (tx != null) {
                    tx.rollback();
                }
                mensajeResultado.setNoError(ControlErroresHibernate.buscaNoErrorPorExcepcion(e.getClass()));
                mensajeResultado.setError(e.getLocalizedMessage());
            } catch (HibernateException exc) { //cuando marca error al hacer rollback pro ejemplo se cae conexion servidor
                mensajeResultado.setNoError(ControlErroresHibernate.buscaNoErrorPorExcepcion(exc.getClass()));
                mensajeResultado.setError(exc.getLocalizedMessage());
            }
            mensajeResultado.setResultado(null);
        }
        return mensajeResultado;
    }

    public Mensaje SaveDeleteCruces(String uuidCxn, List<Cruce> AgreModif, List<Cruce> Ordenados, Object[] clavesDelete) {
        boolean exito = true;
        Transaction transaction = null;
        int order = 0;
        Cruce cru = null;
        try {
            inicializaVariableMensaje();
            setSession(HibernateUtil.currentSession(uuidCxn));
            transaction = getSession().beginTransaction();
            if (clavesDelete != null) {
                deleteListQuery("Cruce", "id", clavesDelete);
            }
            //Guardado de modificados
            for (Cruce Cm : AgreModif) {
                cru = Cm;
                makePersistent(Cm);
            }
            if (Ordenados.size() > 0) {
                order = Ordenados.get(0).getOrdenId();
            }
            //Guardado de Ordenados
            for (Cruce Or : Ordenados) {
                Or.setOrdenId(order);
                cru = Or;
                makePersistent(Or);
                order++;
            }
            if (exito) {
                cru = null;
                getSession().getTransaction().commit();
                mensajeResultado.setResultado(cru);
                mensajeResultado.setNoError(0);
                mensajeResultado.setError("");
            }
        } catch (HibernateException ex) {
            System.err.println(concatena.delete(0, concatena.length()).append(msgError).append("SaveDeleteCruces()1_Error: ").append(ex));
            try {
                if (transaction != null) {
                    transaction.rollback();
                }
                mensajeResultado.setNoError(ControlErroresHibernate.buscaNoErrorPorExcepcion(ex.getClass()));
                mensajeResultado.setError(ex.getLocalizedMessage());
            } catch (HibernateException exc) { //cuando marca error al hacer rollback pro ejemplo se cae conexion servidor
                mensajeResultado.setNoError(ControlErroresHibernate.buscaNoErrorPorExcepcion(exc.getClass()));
                mensajeResultado.setError(exc.getLocalizedMessage());
            }
            mensajeResultado.setResultado(cru);
        }
        return mensajeResultado;
    }

    public Mensaje SaveCruces(String uuidCxn, List<Cruce> c) {//JSA01
        Transaction tx = null;
        try {
            inicializaVariableMensaje();
            setSession(HibernateUtil.currentSession(uuidCxn));
            tx = getSession().beginTransaction();
            for (int i = 0; i < c.size(); i++) {
                makePersistent(c.get(i));
                if (i % 100 == 0) {
                    flush();
                }
            }
            mensajeResultado.setResultado(true);
            mensajeResultado.setNoError(0);
            mensajeResultado.setError("");
            getSession().getTransaction().commit();
        } catch (HibernateException e) {
            System.err.println(concatena.delete(0, concatena.length()).append(msgError).append("SaveCruces()1_Error: ").append(e));
            try {
                if (tx != null) {
                    tx.rollback();
                }
                mensajeResultado.setNoError(ControlErroresHibernate.buscaNoErrorPorExcepcion(e.getClass()));
                mensajeResultado.setError(e.getLocalizedMessage());
            } catch (HibernateException exc) { //cuando marca error al hacer rollback pro ejemplo se cae conexion servidor
                mensajeResultado.setNoError(ControlErroresHibernate.buscaNoErrorPorExcepcion(exc.getClass()));
                mensajeResultado.setError(exc.getLocalizedMessage());
            }
            mensajeResultado.setResultado(null);
        }
        return mensajeResultado;
    }

    public Mensaje DeleteCruces(String uuidCxn, List<Cruce> c) {//JSA01
        Transaction tx = null;
        try {
            inicializaVariableMensaje();
            setSession(HibernateUtil.currentSession(uuidCxn));
            tx = getSession().beginTransaction();
            for (Cruce cu : c) {
                makeTransient(cu);
            }
            mensajeResultado.setResultado(true);
            mensajeResultado.setNoError(0);
            mensajeResultado.setError("");
            getSession().getTransaction().commit();
        } catch (HibernateException e) {
            System.err.println(concatena.delete(0, concatena.length()).append(msgError).append("DeleteCruces()1_Error: ").append(e));
            try {
                if (tx != null) {
                    tx.rollback();
                }
                mensajeResultado.setNoError(ControlErroresHibernate.buscaNoErrorPorExcepcion(e.getClass()));
                mensajeResultado.setError(e.getLocalizedMessage());
            } catch (HibernateException exc) { //cuando marca error al hacer rollback pro ejemplo se cae conexion servidor
                mensajeResultado.setNoError(ControlErroresHibernate.buscaNoErrorPorExcepcion(exc.getClass()));
                mensajeResultado.setError(exc.getLocalizedMessage());
            }
            mensajeResultado.setResultado(null);
        }
        return mensajeResultado;
    }
}
