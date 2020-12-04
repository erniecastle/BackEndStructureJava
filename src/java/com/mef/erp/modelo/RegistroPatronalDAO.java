/**
 * @author: Ernesto Castillo Fecha de Creación: 13/04/2011 Compañía: Exito
 * Software. Descripción del programa: clase registro patronal para llamados a
 * metodos de HIBERNATE
 * -----------------------------------------------------------------------------
 * MODIFICACIONES:
 * -----------------------------------------------------------------------------
 * Clave: JSA01 Autor: Jose Armando Sanchez Acosta Fecha: 08-07-2011
 * Descripción: Se aplicaron PDM.
 * -----------------------------------------------------------------------------
 * Clave:JSA02 Autor:Jose Armando Sanchez Acosta Fecha:10/11/2011 Descripción:Se
 * cambiaron todos los metodos hibernate al dao
 * -----------------------------------------------------------------------------
 */
package com.mef.erp.modelo;

import com.mef.erp.modelo.entidad.Mensaje;
import com.mef.erp.modelo.entidad.Primas;
import com.mef.erp.modelo.entidad.RegistroPatronal;
import com.mef.erp.util.ControlErroresHibernate;
import com.mef.erp.util.HibernateUtil;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class RegistroPatronalDAO extends GenericHibernateDAO<RegistroPatronal, Long>
        implements RegistroPatronalIF {

    private StringBuilder concatena = new StringBuilder(200);
    private final String msgError = concatena.append("ServerERP"/*util.getContexto()*/).append(".MSERR_C_").append(this.getClass().getName()).append(".").toString();
    private List<RegistroPatronal> listReg = new ArrayList<RegistroPatronal>();
    private List<Primas> listPrim = new ArrayList<Primas>();

    public Mensaje agregar(RegistroPatronal entity, String uuidCxn) {//JSA02
        RegistroPatronal registroPatronal;
        try {
            inicializaVariableMensaje();
            setSession(HibernateUtil.currentSession(uuidCxn));
            getSession().beginTransaction();
            registroPatronal = makePersistent(entity);
            mensajeResultado.setResultado(registroPatronal);
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

    public Mensaje actualizar(RegistroPatronal entity, String uuidCxn) {//JSA02
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

    public Mensaje eliminar(RegistroPatronal entity, String uuidCxn) {//JSA02
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

    public Mensaje getRegistroPatronalAll(String claveRazonesSocial, String uuidCxn) {//JSA02
        List<RegistroPatronal> values;
        try {
            inicializaVariableMensaje();
            setSession(HibernateUtil.currentSession(uuidCxn));
            getSession().beginTransaction();
            q = getSession().createQuery("from RegistroPatronal c where c.razonesSociales.clave=:clave");
            q.setParameter("clave", claveRazonesSocial);
            values = q.list();
            mensajeResultado.setResultado(values);
            mensajeResultado.setNoError(0);
            mensajeResultado.setError("");
            getSession().getTransaction().commit();
        } catch (HibernateException ex) {
            System.err.println(concatena.delete(0, concatena.length()).append(msgError).append("getRegistroPatronalAll()1_Error: ").append(ex));
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

    public Mensaje getRegistroPatronalPorClave(String clave, String claveRazonesSocial, String uuidCxn) {//JSA02
        RegistroPatronal r;
        try {
            inicializaVariableMensaje();
            setSession(HibernateUtil.currentSession(uuidCxn));
            getSession().beginTransaction();
            q = getSession().createQuery("from RegistroPatronal r where r.clave=:clave and r.razonesSociales.clave=:claveRazonSocial");
            q.setParameter("claveRazonSocial", claveRazonesSocial);
            q.setParameter("clave", clave);
            r = (RegistroPatronal) q.uniqueResult();
            mensajeResultado.setResultado(r);
            mensajeResultado.setNoError(0);
            mensajeResultado.setError("");
            getSession().getTransaction().commit();
        } catch (HibernateException ex) {
            System.err.println(concatena.delete(0, concatena.length()).append(msgError).append("getRegistroPatronalPorClave()1_Error: ").append(ex));
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

    public Mensaje SaveRegistroPatronal(List<Primas> agrega, Object[] eliminados, RegistroPatronal entity, String uuidCxn) {//JSA02
        boolean exitoRegistro = true;
        RegistroPatronal registroPatronal;
        Transaction transaction = null;
        try {
            inicializaVariableMensaje();
            Session session = HibernateUtil.currentSession(uuidCxn);
            transaction = session.beginTransaction();
            if (eliminados.length > 0) {
                exitoRegistro = deleteListQueryEn(session, "Primas", "id", eliminados);
            }
            if (exitoRegistro) {
                session.saveOrUpdate(entity);
                agrega = agrega == null ? new ArrayList<Primas>() : agrega;
                for (int i = 0; i < agrega.size(); i++) {
                    agrega.get(i).setRegistrospatronal(entity);
                    session.saveOrUpdate(agrega.get(i));
                    if (i % 100 == 0 && i > 0) {
                        session.flush();
                    }
                }
            }
            if (exitoRegistro) {
                registroPatronal = entity;
                mensajeResultado.setResultado(registroPatronal);
                mensajeResultado.setNoError(0);
                mensajeResultado.setError("");
                transaction.commit();
            } else {
                transaction.rollback();
            }
        } catch (HibernateException ex) {
            System.err.println(concatena.delete(0, concatena.length()).append(msgError).append("SaveRegistroPatronal()1_Error: ").append(ex));
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

    public Mensaje UpdateRegistroPatronal(List<Primas> agrega, Object[] eliminados, RegistroPatronal entity, String uuidCxn) {//JSA02
        boolean exitoRegistro = true;
        Transaction transaction = null;
        try {
            inicializaVariableMensaje();
            Session session = HibernateUtil.currentSession(uuidCxn);
            transaction = session.beginTransaction();
            if (eliminados.length > 0) {
                exitoRegistro = deleteListQueryEn(session, "Primas", "id", eliminados);
            }
            if (exitoRegistro) {
                session.saveOrUpdate(entity);
                agrega = agrega == null ? new ArrayList<Primas>() : agrega;
                for (int i = 0; i < agrega.size(); i++) {
                    agrega.get(i).setRegistrospatronal(entity);
                    session.saveOrUpdate(agrega.get(i));
                    if (i % 100 == 0) {
                        session.flush();
                    }
                }
            }
            if (exitoRegistro) {
                mensajeResultado.setResultado(true);
                mensajeResultado.setNoError(0);
                mensajeResultado.setError("");
                transaction.commit();
            } else {
                transaction.rollback();
            }
        } catch (HibernateException ex) {
            System.err.println(concatena.delete(0, concatena.length()).append(msgError).append("UpdateRegistroPatronal()1_Error: ").append(ex));
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

    public Mensaje DeleteRegistroPatronal(RegistroPatronal entity, String uuidCxn) {//JSA02
        boolean exitoRegistro = true;

        Transaction transaction = null;
        try {
            inicializaVariableMensaje();
            Session session = HibernateUtil.currentSession(uuidCxn);
            transaction = session.beginTransaction();
            exitoRegistro = deleteListQuery(session, "Primas", "registrospatronal_id", entity.getId());
            if (exitoRegistro) {
                exitoRegistro = deleteListQuery(session, "RegistroPatronal", "id", entity.getId());
            }
            if (exitoRegistro) {
                mensajeResultado.setResultado(true);
                mensajeResultado.setNoError(0);
                mensajeResultado.setError("");
                transaction.commit();
            } else {
                transaction.rollback();
            }
        } catch (HibernateException ex) {
            System.err.println(concatena.delete(0, concatena.length()).append(msgError).append("DeleteRegistroPatronal()1_Error: ").append(ex));
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

    private boolean deleteListQueryEn(Session session, String tabla, String campo, Object[] valores) {//JSA02
        boolean exito = true;
        try {
            consulta = new StringBuilder("delete ");
            consulta.append(tabla).append(" where ").append(campo).append(" in(:valores)");
            q = session.createQuery(consulta.toString());
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

    private boolean deleteListQuery(Session session, String tabla, String campo, Object valores) {//JSA02
        boolean exito = true;
        try {
            consulta = new StringBuilder("delete ");
            consulta.append(tabla).append(" where ").append(campo).append("=:valores)");
            q = session.createQuery(consulta.toString());
            q.setParameter("valores", valores);
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

    public Mensaje consultaPorRangosPatronal(Integer inicio, Integer rango, String[] camposWhere, Object[] camposWhereValores, String uuidCxn) {//JSA02
        try {
            inicializaVariableMensaje();
            setSession(HibernateUtil.currentSession(uuidCxn));
            getSession().beginTransaction();
            listReg = (List<RegistroPatronal>) consultaPorRangos(inicio, rango, camposWhere, camposWhereValores);
            mensajeResultado.setResultado(listReg);
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

    public Mensaje consultaPorFiltrosPatronal(String query, Object[] campos, Object[] valores, Integer inicio, Integer rango, String uuidCxn) {//JSA02
        try {
            inicializaVariableMensaje();
            setSession(HibernateUtil.currentSession(uuidCxn));
            getSession().beginTransaction();
            listReg = (List<RegistroPatronal>) consultaPorFiltros(query, campos, valores, inicio, rango);
            mensajeResultado.setResultado(listReg);
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

    public Mensaje agregarListaRegistrosPatronales(List<RegistroPatronal> cambios, List<RegistroPatronal> temporales, List<Primas> cambioprima,
            Object[] clavesDelete, Object[] clavesPrimasDelete, int rango, String uuidCxn) {//JSA02
        listReg = new ArrayList<RegistroPatronal>();
        listPrim = new ArrayList<Primas>();
        RegistroPatronal r;
        Primas p;
        ArrayList nEntitys = new ArrayList<Object>(2);
        Transaction transaction = null;
        try {
            inicializaVariableMensaje();
            Session session = HibernateUtil.currentSession(uuidCxn);
            setSession(session);
            transaction = getSession().beginTransaction();
            int i;
            for (i = 0; i < cambios.size(); i++) {
                for (int j = 0; j < temporales.size(); j++) {
                    if (temporales.get(j).getId() == cambios.get(i).getId()) {
                        cambios.get(i).setId(null);
                        temporales.remove(j);
                        j--;
                        break;
                    }
                }
                if (cambios.get(i).getId() == null) {
                    getSession().saveOrUpdate(cambios.get(i));
                    r = cambios.get(i);
                    listReg.add(r);
                    for (Primas primas : cambioprima) {
                        if (primas.getRegistrospatronal().getClave().equals(r.getClave())) {
                            primas.setRegistrospatronal(r);
                            getSession().saveOrUpdate(primas);
                            p = primas;
                            listPrim.add(p);
                        }
                    }

                } else {
                    getSession().saveOrUpdate(cambios.get(i));
                    boolean nPrima = false;
                    for (Primas primas : cambioprima) {
                        if (primas.getRegistrospatronal().getId() == cambios.get(i).getId()) {
                            if (primas.getId() == null) {
                                nPrima = true;
                            }
                            getSession().saveOrUpdate(primas);
                            if (nPrima) {
                                p = primas;
                                listPrim.add(p);
                            }
                        }
                    }
                }

                if (i % rango == 0 & i > 0) {
                    session.flush();
                    session.clear();
                }
            }
            boolean exito = true;
            if (clavesPrimasDelete != null) {
                exito = deleteListQuerys(Primas.class.getSimpleName(), "id", clavesPrimasDelete);
            }
            if (clavesDelete != null & exito) {
                deleteListQuerys(RegistroPatronal.class.getSimpleName(), "Clave", clavesDelete);
            }
            if (exito) {
                nEntitys.add(listReg);
                nEntitys.add(listPrim);
                mensajeResultado.setResultado(nEntitys);
                mensajeResultado.setNoError(0);
                mensajeResultado.setError("");
                transaction.commit();
            } else {
                transaction.rollback();
            }
        } catch (HibernateException ex) {
            System.err.println(concatena.delete(0, concatena.length()).append(msgError).append("agregarListaRegistrosPatronales()1_Error: ").append(ex));
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

    public Mensaje existeDato(String campo, Object valor, String uuidCxn) {//JSA02
        Boolean existe;
        try {
            inicializaVariableMensaje();
            setSession(HibernateUtil.currentSession(uuidCxn));
            getSession().beginTransaction();
            existe = existeDato("RegistroPatronal", campo, valor);
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

    private boolean deleteListQuerys(String tabla, String campo, Object[] valores) {//JSA02
        try {
            deleteListQuery(tabla, campo, valores);
        } catch (HibernateException ex) {
            System.err.println(concatena.delete(0, concatena.length()).append(msgError).append("deleteListQuerys()1_Error: ").append(ex));
            mensajeResultado.setNoError(ControlErroresHibernate.buscaNoErrorPorExcepcion(ex.getClass()));
            mensajeResultado.setError(ex.getLocalizedMessage());
            mensajeResultado.setResultado(null);
            return false;
        }
        return true;
    }

    public Mensaje deleteListClavesRegistroPatronal(Object[] valores, String uuidCxn) {//JSA02
        try {
            inicializaVariableMensaje();
            setSession(HibernateUtil.currentSession(uuidCxn));
            getSession().beginTransaction();
            deleteListQuery(RegistroPatronal.class.getSimpleName(), "Clave", valores);
            mensajeResultado.setResultado(true);
            mensajeResultado.setNoError(0);
            mensajeResultado.setError("");
            getSession().getTransaction().commit();
        } catch (HibernateException ex) {
            System.err.println(concatena.delete(0, concatena.length()).append(msgError).append("deleteListClavesRegistroPatronal()1_Error: ").append(ex));
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
