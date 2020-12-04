/**
 * @author: Victor Lopez Fecha de Creación: 28/04/2016 Compañía: MacroPro.
 * Descripción del programa: clase de Tipos Vacaciones para llamados a metodos
 * de HIBERNATE
 * -----------------------------------------------------------------------------
 * MODIFICACIONES:
 * -----------------------------------------------------------------------------
 * -----------------------------------------------------------------------------
 * -----------------------------------------------------------------------------
 */
package com.mef.erp.modelo;

import com.mef.erp.modelo.entidad.Mensaje;
import com.mef.erp.modelo.entidad.TiposVacaciones;
import com.mef.erp.util.ControlErroresHibernate;
import com.mef.erp.util.HibernateUtil;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.HibernateException;

public class TiposVacacionesDAO extends GenericHibernateDAO<TiposVacaciones, Long>
        implements TiposVacacionesDAOIF {

    private StringBuilder Concatena = new StringBuilder(200);
    private final String msgError = Concatena.append("ServerERP"/*util.getContexto()*/).append(".MSERR_F_").append(this.getClass().getName()).append(".").toString();
    private List<TiposVacaciones> listTiposNomina = new ArrayList<TiposVacaciones>();//AAP01
    private Boolean commit = false;

    public Mensaje agregar(TiposVacaciones entity, String uuidCxn) {
        try {
            inicializaVariableMensaje();
            setSession(HibernateUtil.currentSession(uuidCxn));
            getSession().beginTransaction();
            makePersistent(entity);
            mensajeResultado.setResultado(entity);
            mensajeResultado.setNoError(0);
            mensajeResultado.setError("");
            getSession().getTransaction().commit();
        } catch (HibernateException e) {
            System.err.println(Concatena.delete(0, Concatena.length()).append(msgError).append("agregar()1_Error: ").append(e));
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

    public Mensaje actualizar(TiposVacaciones entity, String uuidCxn) {
        try {
            inicializaVariableMensaje();
            setSession(HibernateUtil.currentSession(uuidCxn));
            getSession().beginTransaction();
            makePersistent(entity);
            mensajeResultado.setResultado(true);
            mensajeResultado.setNoError(0);
            mensajeResultado.setError("");
            getSession().getTransaction().commit();
        } catch (HibernateException e) {
            System.err.println(Concatena.delete(0, Concatena.length()).append(msgError).append("actualizar()1_Error: ").append(e));
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

    public Mensaje eliminar(TiposVacaciones entity, String uuidCxn) {
        try {
            inicializaVariableMensaje();
            setSession(HibernateUtil.currentSession(uuidCxn));
            getSession().beginTransaction();
            makeTransient(entity);
            mensajeResultado.setResultado(true);
            mensajeResultado.setNoError(0);
            mensajeResultado.setError("");
            getSession().getTransaction().commit();
        } catch (HibernateException e) {
            System.err.println(Concatena.delete(0, Concatena.length()).append(msgError).append("eliminar()1_Error: ").append(e));
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

    public Mensaje getTiposVacacionesAll(String uuidCxn) {
        List<TiposVacaciones> values;
        try {
            inicializaVariableMensaje();
            setSession(HibernateUtil.currentSession(uuidCxn));
            getSession().beginTransaction();
            q = getSession().createQuery("from TiposVacaciones");
            values = q.list();
            mensajeResultado.setResultado(values);
            mensajeResultado.setNoError(0);
            mensajeResultado.setError("");
            getSession().getTransaction().commit();
        } catch (HibernateException e) {
            System.err.println(Concatena.delete(0, Concatena.length()).append(msgError).append("getTiposVacacionesAll()1_Error: ").append(e));
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

    public Mensaje getTiposVacacionesPorClave(String clave, String uuidCxn) {
        TiposVacaciones p;
        try {
            inicializaVariableMensaje();
            setSession(HibernateUtil.currentSession(uuidCxn));
            getSession().beginTransaction();
            q = getSession().createQuery("from TiposVacaciones where clave = :clave");
            q.setParameter("clave", clave);
            p = (TiposVacaciones) q.uniqueResult();
            mensajeResultado.setResultado(p);
            mensajeResultado.setNoError(0);
            mensajeResultado.setError("");
            getSession().getTransaction().commit();
        } catch (HibernateException e) {
            System.err.println(Concatena.delete(0, Concatena.length()).append(msgError).append("getTiposVacacionesPorClave()1_Error: ").append(e));
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

    public Mensaje consultaPorFiltrosTiposVacaciones(String q, Object[] campos, Object[] valores, String uuidCxn) {
        List<TiposVacaciones> listEsp;
        try {
            inicializaVariableMensaje();
            setSession(HibernateUtil.currentSession(uuidCxn));
            getSession().beginTransaction();
            listEsp = (List<TiposVacaciones>) consultaPorFiltros(q, campos, valores, null, null);
            mensajeResultado.setResultado(listEsp);
            mensajeResultado.setNoError(0);
            mensajeResultado.setError("");
            getSession().getTransaction().commit();
        } catch (HibernateException e) {
            System.err.println(Concatena.delete(0, Concatena.length()).append(msgError).append("consultaPorFiltros()1_Error: ").append(e));
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

    public Mensaje existeClave(String tabla, String[] campo, Object[] valores, String queryAntesDeFrom, String uuidCxn) {
        TiposVacaciones tipoNomina;
        try {
            inicializaVariableMensaje();
            setSession(HibernateUtil.currentSession(uuidCxn));
            getSession().beginTransaction();
            tipoNomina = (TiposVacaciones) super.existeClave(tabla, campo, valores, queryAntesDeFrom);
            mensajeResultado.setResultado(tipoNomina);
            mensajeResultado.setNoError(0);
            mensajeResultado.setError("");
            getSession().getTransaction().commit();
        } catch (HibernateException e) {
            System.err.println(Concatena.delete(0, Concatena.length()).append(msgError).append("existeClave()1_Error: ").append(e));
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

    public Mensaje consultaPorRangos(Integer inicio, Integer rango, String uuidCxn) {
        List<TiposVacaciones> listEsp;
        try {
            inicializaVariableMensaje();
            setSession(HibernateUtil.currentSession(uuidCxn));
            getSession().beginTransaction();
            listEsp = (List<TiposVacaciones>) consultaPorRangos(inicio, rango, null, null);
            mensajeResultado.setResultado(listEsp);
            mensajeResultado.setNoError(0);
            mensajeResultado.setError("");
            getSession().getTransaction().commit();
        } catch (HibernateException e) {
            System.err.println(Concatena.delete(0, Concatena.length()).append(msgError).append("consultaPorRangos()1_Error: ").append(e));
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

    public Mensaje existeDato(String campo, Object valor, String uuidCxn) {
        Boolean b;
        try {
            inicializaVariableMensaje();
            setSession(HibernateUtil.currentSession(uuidCxn));
            getSession().beginTransaction();
            b = existeDato("TiposVacaciones", campo, valor);
            mensajeResultado.setResultado(b);
            mensajeResultado.setNoError(0);
            mensajeResultado.setError("");
            getSession().getTransaction().commit();
        } catch (HibernateException e) {
            System.err.println(Concatena.delete(0, Concatena.length()).append(msgError).append("existeDato()1_Error: ").append(e));
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

    public Mensaje saveDeleteTiposVacaciones(List<TiposVacaciones> entitysCambios, Object[] clavesDelete, int rango, String uuidCxn) {
        listTiposNomina = new ArrayList<TiposVacaciones>();
        try {
            commit = true;
            inicializaVariableMensaje();
            setSession(HibernateUtil.currentSession(uuidCxn));
            getSession().beginTransaction();
            if (clavesDelete != null) {
                commit = deleteListQuerys("TiposVacaciones", "Clave", clavesDelete);
            }
            entitysCambios = (entitysCambios == null ? new ArrayList<TiposVacaciones>() : entitysCambios);
            if (commit && !entitysCambios.isEmpty()) {
                listTiposNomina = agregarListTiposVacaciones(entitysCambios, rango);
            }
            if (commit) {
                mensajeResultado.setResultado(listTiposNomina);
                mensajeResultado.setNoError(0);
                mensajeResultado.setError("");
                getSession().getTransaction().commit();
            } else {
                getSession().getTransaction().rollback();
            }
        } catch (HibernateException ex) {
            System.err.println(Concatena.delete(0, Concatena.length()).append(msgError).append("saveDeleteTiposVacaciones()1_Error: ").append(ex));
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

    private List<TiposVacaciones> agregarListTiposVacaciones(List<TiposVacaciones> entitys, int rango) {
        listTiposNomina.clear();
        commit = true;
        try {
            int i;
            for (i = 0; i < entitys.size(); i++) {
                if (entitys.get(i).getId() == null) {
                    listTiposNomina.add(makePersistent(entitys.get(i)));
                } else {
                    makePersistent(entitys.get(i));
                }
                if (i % rango == 0 & i > 0) {
                    flush();
                    clear();
                }
            }
        } catch (HibernateException ex) {
            System.err.println(Concatena.delete(0, Concatena.length()).append(msgError).append("agregarListTiposVacaciones()1_Error: ").append(ex));
            mensajeResultado.setNoError(ControlErroresHibernate.buscaNoErrorPorExcepcion(ex.getClass()));
            mensajeResultado.setError(ex.getLocalizedMessage());
            mensajeResultado.setResultado(null);
            commit = false;
        }
        return listTiposNomina;
    }

    private boolean deleteListQuerys(String tabla, String campo, Object[] valores) {
        boolean exito = true;
        try {
            deleteListQuery(tabla, campo, valores);
        } catch (HibernateException ex) {
            System.err.println(Concatena.delete(0, Concatena.length()).append(msgError).append("deleteListQuerys()1_Error: ").append(ex));
            mensajeResultado.setNoError(ControlErroresHibernate.buscaNoErrorPorExcepcion(ex.getClass()));
            mensajeResultado.setError(ex.getLocalizedMessage());
            mensajeResultado.setResultado(null);
            exito = false;
        }
        return exito;
    }

}
