/**
 * @author: Victor Lopez Fecha de Creación: 30/06/2011 Compañía: MacroPro.
 * Descripción del programa: clase Restricciones para llamados a metodos de
 * HIBERNATE
 * -----------------------------------------------------------------------------
 * MODIFICACIONES:
 * -----------------------------------------------------------------------------
 * Clave:JSA01 Autor:Jose Armando Sanchez Acosta Fecha:10/11/2011 Descripción:Se
 * cambiaron todos los metodos hibernate al dao
 * -----------------------------------------------------------------------------
 */
package com.mef.erp.modelo;

import com.mef.erp.modelo.entidad.Mensaje;
import com.mef.erp.modelo.entidad.Restriccion;
import com.mef.erp.modelo.entidad.Usuario;
import com.mef.erp.util.ControlErroresHibernate;
import com.mef.erp.util.HibernateUtil;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.HibernateException;

public class RestriccionesDAO extends GenericHibernateDAO<Restriccion, Long> implements RestriccionesDAOIF {

    private StringBuilder concatena = new StringBuilder(200);
    private final String msgError = concatena.append("ServerERP").append(".MSERR_F_").append(this.getClass().getName()).append(".").toString();

    public Mensaje agregar(Restriccion entity, String uuidCxnMaestra) {//JSA01
        try {
            inicializaVariableMensaje();
            setSession(HibernateUtil.currentSession(uuidCxnMaestra));
            getSession().beginTransaction();
            makePersistent(entity);
            mensajeResultado.setResultado(entity);
            mensajeResultado.setNoError(0);
            mensajeResultado.setError("");
            getSession().getTransaction().commit();
        } catch (HibernateException e) {
            System.err.println(concatena.delete(0, concatena.length()).append(msgError).append("agregar()1_Error: ").append(e));
            try {
                if (getSession().getTransaction().isActive()) {
                    getSession().getTransaction().rollback();
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

    public Mensaje actualizar(Restriccion entity, String uuidCxnMaestra) {//JSA01
        try {
            inicializaVariableMensaje();
            setSession(HibernateUtil.currentSession(uuidCxnMaestra));
            getSession().beginTransaction();
            makePersistent(entity);
            mensajeResultado.setResultado(true);
            mensajeResultado.setNoError(0);
            mensajeResultado.setError("");
            getSession().getTransaction().commit();
        } catch (HibernateException e) {
            System.err.println(concatena.delete(0, concatena.length()).append(msgError).append("actualizar()1_Error: ").append(e));
            try {
                if (getSession().getTransaction().isActive()) {
                    getSession().getTransaction().rollback();
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

    public Mensaje eliminar(Restriccion entity, String uuidCxnMaestra) {//JSA01
        try {
            inicializaVariableMensaje();
            setSession(HibernateUtil.currentSession(uuidCxnMaestra));
            getSession().beginTransaction();
            makeTransient(entity);
            mensajeResultado.setResultado(true);
            mensajeResultado.setNoError(0);
            mensajeResultado.setError("");
            getSession().getTransaction().commit();
        } catch (HibernateException e) {
            System.err.println(concatena.delete(0, concatena.length()).append(msgError).append("eliminar()1_Error: ").append(e));
            try {
                if (getSession().getTransaction().isActive()) {
                    getSession().getTransaction().rollback();
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

    public Mensaje getRestriccionesAll(String uuidCxnMaestra) {//JSA01
        List<Restriccion> values;
        try {
            inicializaVariableMensaje();
            setSession(HibernateUtil.currentSession(uuidCxnMaestra));
            getSession().beginTransaction();
            q = getSession().createQuery("from Restriccion");
            values = q.list();
            mensajeResultado.setResultado(values);
            mensajeResultado.setNoError(0);
            mensajeResultado.setError("");
            getSession().getTransaction().commit();
        } catch (HibernateException e) {
            System.err.println(concatena.delete(0, concatena.length()).append(msgError).append("getRestriccionesAll()1_Error: ").append(e));
            try {
                if (getSession().getTransaction().isActive()) {
                    getSession().getTransaction().rollback();
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

    public Mensaje getRestriccionesPorClave(String clave, String uuidCxnMaestra) {//JSA01
        Restriccion a;
        try {
            inicializaVariableMensaje();
            setSession(HibernateUtil.currentSession(uuidCxnMaestra));
            getSession().beginTransaction();
            q = getSession().createQuery("from Restriccion where clave=:clave");
            q.setParameter("clave", clave);
            a = (Restriccion) q.uniqueResult();
            mensajeResultado.setResultado(a);
            mensajeResultado.setNoError(0);
            mensajeResultado.setError("");
            getSession().getTransaction().commit();
        } catch (HibernateException e) {
            System.err.println(concatena.delete(0, concatena.length()).append(msgError).append("getRestriccionesPorClave()1_Error: ").append(e));
            try {
                if (getSession().getTransaction().isActive()) {
                    getSession().getTransaction().rollback();
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

    public Mensaje agregarListaRestricciones(List<Restriccion> entitys, int rango, String uuidCxnMaestra) {//JSA01
        List<Restriccion> listEsp = new ArrayList<Restriccion>();
        try {
            inicializaVariableMensaje();
            setSession(HibernateUtil.currentSession(uuidCxnMaestra));
            getSession().beginTransaction();
            int i;
            for (i = 0; i < entitys.size(); i++) {
                if (entitys.get(i).getId() == null) {
                    listEsp.add(makePersistent(entitys.get(i)));
                } else {
                    makePersistent(entitys.get(i));
                }
                if (i % rango == 0 & i > 0) {
                    flush();
                    clear();
                }
            }
            mensajeResultado.setResultado(listEsp);
            mensajeResultado.setNoError(0);
            mensajeResultado.setError("");
            getSession().getTransaction().commit();
        } catch (HibernateException ex) {
            System.err.println(concatena.delete(0, concatena.length()).append(msgError).append("agregarListaRestricciones()1_Error: ").append(ex));
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

    public Mensaje deleteListaRestricciones(List<Restriccion> entitys, Usuario user, int rango, String uuidCxnMaestra) {//JSA01
        List<Restriccion> listEsp = null;
        try {
            inicializaVariableMensaje();
            setSession(HibernateUtil.currentSession(uuidCxnMaestra));
            getSession().beginTransaction();
            int i;
            for (i = 0; i < entitys.size(); i++) {
                makeTransient(entitys.get(i));
                if (i % rango == 0 & i > 0) {
                    flush();
                    clear();
                }
            }
            getSession().getTransaction().commit();
            listEsp = new ArrayList<Restriccion>();
            mensajeResultado.setResultado(listEsp);
            mensajeResultado.setNoError(0);
            mensajeResultado.setError("");
        } catch (HibernateException ex) {
            System.err.println(concatena.delete(0, concatena.length()).append(msgError).append("deleteListaRestricciones()1_Error: ").append(ex));
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

    private Restriccion getRestriccionesPorUsuario(Usuario usuario) {
        q = getSession().createQuery("from Restriccion where usuario=:usuario");
        q.setParameter("usuario", usuario);
        return (Restriccion) q.uniqueResult();
    }
}
