/**
 * @author: Victor Fecha de Creación: 06/06/2011 Compañía: Macropro Descripción
 * del programa: clase TablaPersonalizada para llamados a metodos de HIBERNATE
 * -----------------------------------------------------------------------------
 * MODIFICACIONES:
 * -----------------------------------------------------------------------------
 * Clave: JSA01 Autor: Jose Armando Sanchez Acosta Fecha: 08-07-2011
 * Descripción: Se aplicaron PDM.
 * -----------------------------------------------------------------------------
 * Clave:JSA02 Autor:Jose Armando Sanchez Acosta Fecha:10/11/2011 Descripción:Se
 * cambiaron todos los metodos hibernate al dao
 * -----------------------------------------------------------------------------
 * Clave:JSA03 Autor:Jose Armando Sanchez Acosta Fecha:09/05/2013 Descripción:se
 * agrego el metodo saveDeleteTablaDatos para cuando se elimina una tablaDatos
 * este surja afecto en TablaBase primero antes de eliminarse.
 * -----------------------------------------------------------------------------
 */
package com.mef.erp.modelo;

import com.mef.erp.modelo.entidad.Mensaje;
import com.mef.erp.modelo.entidad.TablaBase;
import com.mef.erp.modelo.entidad.TablaDatos;
import com.mef.erp.modelo.entidad.TablaPersonalizada;
import com.mef.erp.util.ControlErroresHibernate;
import com.mef.erp.util.HibernateUtil;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.hibernate.HibernateException;

/**
 *
 * @author yo
 */
public class TablaDatosDAO extends GenericHibernateDAO<TablaDatos, Integer>
        implements TablaDatosDAOIF {

    private StringBuilder concatena = new StringBuilder(200);
    private final String msgError = concatena.append("ServerERP"/*util.getContexto()*/).append(".MSERR_C_").append(this.getClass().getName()).append(".").toString();

    public Mensaje getTablaDatosAll(String  uuidCxnMaestra) {//JSA02
        List<TablaDatos> values;
        try {
            inicializaVariableMensaje();
            setSession(HibernateUtil.currentSession(uuidCxnMaestra));
            getSession().beginTransaction();
            q = getSession().createQuery("from TablaDatos");
            values = q.list();
            mensajeResultado.setResultado(values);
            mensajeResultado.setNoError(0);
            mensajeResultado.setError("");
            getSession().getTransaction().commit();
        } catch (HibernateException ex) {
            System.err.println(concatena.delete(0, concatena.length()).append(msgError).append("getTablaDatosAll()1_Error: ").append(ex));
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

    public Mensaje getListTablaDatosPorTablaBase(String clave,String  uuidCxnMaestra) {//JSA02
        List<TablaDatos> values;
        try {
            inicializaVariableMensaje();
            setSession(HibernateUtil.currentSession(uuidCxnMaestra));
            getSession().beginTransaction();
            q = getSession().createQuery("from TablaDatos td where td.tablaBase.clave = :clave");
            q.setString("clave", clave);
            values = (List<TablaDatos>) q.list();
            mensajeResultado.setResultado(values);
            mensajeResultado.setNoError(0);
            mensajeResultado.setError("");
            getSession().getTransaction().commit();
        } catch (HibernateException ex) {
            System.err.println(concatena.delete(0, concatena.length()).append(msgError).append("getListTablaDatosPorTablaBase()1_Error: ").append(ex));
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

    public Mensaje agregar(TablaDatos entity,String  uuidCxnMaestra) {//JSA02
        TablaDatos tablaDatos;
        try {
            inicializaVariableMensaje();
            setSession(HibernateUtil.currentSession(uuidCxnMaestra));
            getSession().beginTransaction();
            tablaDatos = makePersistent(entity);
            mensajeResultado.setResultado(tablaDatos);
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

    public Mensaje actualizar(TablaDatos entity,String  uuidCxnMaestra) {//JSA02
        try {
            inicializaVariableMensaje();
            setSession(HibernateUtil.currentSession(uuidCxnMaestra));
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

    public Mensaje eliminar(TablaDatos entity,String  uuidCxnMaestra) {//JEVC05
        try {
            inicializaVariableMensaje();
            setSession(HibernateUtil.currentSession(uuidCxnMaestra));
            getSession().beginTransaction();
            makeTransient(entity);
            mensajeResultado.setResultado(true);
            mensajeResultado.setNoError(0);
            mensajeResultado.setError("");
            getSession().getTransaction().commit();
            clear();
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

    public Mensaje getTablaDatosPorTablas(TablaBase tablaBase, TablaPersonalizada tablaPersonalizada,String  uuidCxnMaestra) {//JSA02
        List<TablaDatos> values;
        try {
            inicializaVariableMensaje();
            setSession(HibernateUtil.currentSession(uuidCxnMaestra));
            getSession().beginTransaction();
            q = null;
            mensajeResultado.setResultado(new ArrayList<TablaDatos>());
            if (tablaBase != null) {
                q = getSession().createQuery("from TablaDatos where tablaBase = :tablaBase");
                q.setEntity("tablaBase", tablaBase);
//                q.setString("clave", clave);
                values = q.list();
                mensajeResultado.setResultado(values);
            } else if (tablaPersonalizada != null) {
                q = getSession().createQuery("from TablaDatos where tablaPersonalizada = :tablaPersonalizada");
                q.setEntity("tablaPersonalizada", tablaPersonalizada);
//                q.setString("clave", clave);
                values = q.list();
                mensajeResultado.setResultado(values);
            }
            mensajeResultado.setNoError(0);
            mensajeResultado.setError("");
            getSession().getTransaction().commit();
        } catch (HibernateException ex) {
            System.err.println(concatena.delete(0, concatena.length()).append(msgError).append("getTablaDatosPorTablas()1_Error: ").append(ex));
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

    public Mensaje getExisteDatosPorTablas(String tablaBaseClave, String tablaPersonalizadaClave,String  uuidCxnMaestra) {
        Long value = 0L;
        try {
            inicializaVariableMensaje();
            setSession(HibernateUtil.currentSession(uuidCxnMaestra));
            getSession().beginTransaction();
            q = null;
            tablaBaseClave = tablaBaseClave == null ? "" : tablaBaseClave.trim();
            tablaPersonalizadaClave = tablaPersonalizadaClave == null ? "" : tablaPersonalizadaClave.trim();

            if (!tablaBaseClave.isEmpty()) {
                q = getSession().createQuery("select count(o) from TablaDatos o where o.tablaBase.clave = :tablaBaseClave");
                q.setString("tablaBaseClave", tablaBaseClave);
                value = (Long) q.uniqueResult();
            } else if (!tablaPersonalizadaClave.isEmpty()) {
                q = getSession().createQuery("select count(o) from TablaDatos o where o.tablaPersonalizada.clave = :tablaPersonalizadaClave");
                q.setString("tablaPersonalizadaClave", tablaPersonalizadaClave);
                value = (Long) q.uniqueResult();
                mensajeResultado.setResultado(value);
            }
            mensajeResultado.setResultado(value > 0);
            mensajeResultado.setNoError(0);
            mensajeResultado.setError("");
            getSession().getTransaction().commit();
        } catch (HibernateException ex) {
            System.err.println(concatena.delete(0, concatena.length()).append(msgError).append("getExisteDatosPorTablas()1_Error: ").append(ex));
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

    public Mensaje getTablaDatosPorTablasPorControlador(String controladores, TablaBase tablaBase, TablaPersonalizada tablaPersonalizada,String  uuidCxnMaestra) {
        TablaDatos values = null;
        try {
            inicializaVariableMensaje();
            setSession(HibernateUtil.currentSession(uuidCxnMaestra));
            getSession().beginTransaction();
            q = null;
            if (tablaBase != null) {
                q = getSession().createQuery("from TablaDatos where tablaBase = :tablaBase and controladores like :controladores");
                q.setEntity("tablaBase", tablaBase);
//                q.setString("clave", clave);
                q.setString("controladores", "%".concat(controladores).concat("%"));
                values = (TablaDatos) q.uniqueResult();
            } else if (tablaPersonalizada != null) {
                q = getSession().createQuery("from TablaDatos where tablaPersonalizada = :tablaPersonalizada and controladores like :controladores");
                q.setEntity("tablaPersonalizada", tablaPersonalizada);
//                q.setString("clave", clave);
                q.setString("controladores", "%".concat(controladores).concat("%"));
                values = (TablaDatos) q.uniqueResult();
            }
            mensajeResultado.setResultado(values);
            mensajeResultado.setNoError(0);
            mensajeResultado.setError("");
            getSession().getTransaction().commit();
        } catch (HibernateException ex) {
            System.err.println(concatena.delete(0, concatena.length()).append(msgError).append("getTablaDatosPorTablasPorControlador()1_Error: ").append(ex));
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

    public Mensaje getTablaDatosPorTablasPorControladorPorFiltrosEspeciales(Date controlFecha, Integer controlAnio, String controladores, String tablaBaseClave, String tablaPersonalizadaClave,String  uuidCxnMaestra) {
        TablaDatos values;
        try {
            inicializaVariableMensaje();
            setSession(HibernateUtil.currentSession(uuidCxnMaestra));
            getSession().beginTransaction();
            q = null;
            List<String> campos = new ArrayList<String>();
            List<Object> valores = new ArrayList<Object>();
            tablaBaseClave = tablaBaseClave == null ? "" : tablaBaseClave.trim();
            tablaPersonalizadaClave = tablaPersonalizadaClave == null ? "" : tablaPersonalizadaClave.trim();
            controladores = controladores == null ? "" : controladores.trim();
            consulta = new StringBuilder("from TablaDatos o where");

            if (!tablaBaseClave.isEmpty()) {
                consulta.append(" o.tablaBase.clave = :tablaBaseClave ");
                campos.add("tablaBaseClave");
                valores.add(tablaBaseClave);
            } else if (!tablaPersonalizadaClave.isEmpty()) {
                consulta.append(" o.tablaPersonalizada.clave = :tablaPersonalizadaClave ");
                campos.add("tablaPersonalizadaClave");
                valores.add(tablaPersonalizadaClave);
            }

            if (controlFecha != null) {
                if (!tablaBaseClave.isEmpty()) {
                    consulta.append("AND o.id = (SELECT MAX(tb.id) FROM TablaDatos tb WHERE tb.tablaBase.id =  o.tablaBase.id ");
                } else {
                    consulta.append("AND o.id = (SELECT MAX(tb.id) FROM TablaDatos tb WHERE tb.tablaPersonalizada.id =  o.tablaPersonalizada.id ");
                }
                consulta.append(" AND tb.controlPorFecha = :controlFecha ");
                campos.add("controlFecha");
                valores.add(controlFecha);
                if (controlAnio != null) {
                    consulta.append("AND tb.controlPorAnio = :controlAnio ");
                    campos.add("controlAnio");
                    valores.add(controlAnio);
                }

                if (!controladores.isEmpty()) {
                    consulta.append("AND tb.controladores = :controladores");
                    campos.add("controladores");
                    valores.add(controladores);
                }
                consulta.append(")");
            } else {
                if (controlAnio != null) {
                    consulta.append("AND o.controlPorAnio = :controlAnio ");
                    campos.add("controlAnio");
                    valores.add(controlAnio);
                }

                if (!controladores.isEmpty()) {
                    consulta.append("AND o.controladores = :controladores ");
                    campos.add("controladores");
                    valores.add(controladores);
                }
            }
            q = getSession().createQuery(consulta.toString());
            int i;
            for (i = 0; i < campos.size(); i++) {
                q.setParameter(campos.get(i), valores.get(i));
            }
            values = (TablaDatos) q.uniqueResult();
            mensajeResultado.setResultado(values);
            mensajeResultado.setNoError(0);
            mensajeResultado.setError("");
            getSession().getTransaction().commit();
        } catch (HibernateException ex) {
            System.err.println(concatena.delete(0, concatena.length()).append(msgError).append("getTablaDatosPorTablasPorControladorPorFiltrosEspeciales()1_Error: ").append(ex));
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

    /*controladores estaran formados por nombre entidad y su clave ejemplo TipoNomina1*/
    public Mensaje consultaPorControladores(String controladores,String  uuidCxnMaestra) {
        List<TablaDatos> listEsp;
        try {
            inicializaVariableMensaje();
            setSession(HibernateUtil.currentSession(uuidCxnMaestra));
            getSession().beginTransaction();
            q = getSession().createQuery("from TablaDatos where controladores like :controladores");
            q.setString("controladores", "%".concat(controladores).concat("%"));
            listEsp = (List<TablaDatos>) q.list();
            mensajeResultado.setResultado(listEsp);
            mensajeResultado.setNoError(0);
            mensajeResultado.setError("");
            getSession().getTransaction().commit();
        } catch (HibernateException ex) {
            System.err.println(concatena.delete(0, concatena.length()).append(msgError).append("consultaPorControladores()1_Error: ").append(ex));
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

    /*controladores estaran formados por nombre entidad y su clave ejemplo TipoNomina1*/
    public Mensaje consultaPorListaControladores(String[] controladores,String  uuidCxnMaestra) {
        List<TablaDatos> listEsp = new ArrayList<TablaDatos>();
        try {
            inicializaVariableMensaje();
            setSession(HibernateUtil.currentSession(uuidCxnMaestra));
            getSession().beginTransaction();
            int i;
            for (i = 0; i < controladores.length; i++) {
                q = getSession().createQuery("from TablaDatos where controladores like :controladores");
                q.setString("controladores", "%".concat(controladores[i]).concat("%"));
                listEsp = (List<TablaDatos>) q.list();
                if (!listEsp.isEmpty()) {
                    break;
                }
            }
            mensajeResultado.setResultado(listEsp);
            mensajeResultado.setNoError(0);
            mensajeResultado.setError("");
            getSession().getTransaction().commit();
        } catch (HibernateException ex) {
            System.err.println(concatena.delete(0, concatena.length()).append(msgError).append("consultaPorControladores()1_Error: ").append(ex));
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
