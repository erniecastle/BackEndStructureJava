/**
 * @author: Ernesto Castillo Fecha de Creación: 01/12/2011 Compañía: Exito
 * Software. Descripción del programa: clase configuracion asistencias para
 * llamados a metodos de HIBERNATE
 * -----------------------------------------------------------------------------
 * MODIFICACIONES:
 * -----------------------------------------------------------------------------
 * Clave:JSA01 Autor:Jose Armando Fecha:10/NOV/2012 Descripción:Se agrego el
 * metodo PorGrupoMenu para regresar las configuraciones con ese tipo de
 * contenedor.
 * -----------------------------------------------------------------------------
 * Clave: Autor: Fecha: Descripción:
 * -----------------------------------------------------------------------------
 */
package com.mef.erp.modelo;

import com.mef.erp.modelo.entidad.ConfiguracionAsistencias;
import com.mef.erp.modelo.entidad.Mensaje;
import com.mef.erp.util.ControlErroresHibernate;
import com.mef.erp.util.HibernateUtil;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Transaction;

public class ConfiguracionAsistenciasDAO extends GenericHibernateDAO<ConfiguracionAsistencias, Long> implements ConfiguracionAsistenciasIF {

    Query query;
    private final StringBuilder concatena = new StringBuilder(200);
    private final String msgError = concatena.append("ServerERP").append(".MSERR_F_").append(this.getClass().getName()).append(".").toString();
    private List<ConfiguracionAsistencias> listEsp = new ArrayList<ConfiguracionAsistencias>();
    private Boolean commit = false;//, commitMaster = false;
    private int order;

    public Mensaje agregar(ConfiguracionAsistencias entity, String uuidCxn) {
        Transaction tx = null;
        try {
            inicializaVariableMensaje();
            setSession(HibernateUtil.currentSession(uuidCxn));
            tx = getSession().beginTransaction();
            ConfiguracionAsistencias ca = makePersistent(entity);
            mensajeResultado.setResultado(ca);
            mensajeResultado.setNoError(0);
            mensajeResultado.setError("");
            getSession().getTransaction().commit();
        } catch (HibernateException ex) {
            System.err.println(concatena.delete(0, concatena.length()).append(msgError).append("agregar()1_Error: ").append(ex));
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

    public Mensaje getExcepcionesPorConfiguracionAsistencias(String clave, String uuidCxn) {
        ConfiguracionAsistencias conf;
        try {
            inicializaVariableMensaje();
            setSession(HibernateUtil.currentSession(uuidCxn));
            getSession().beginTransaction();
            q = getSession().createQuery("from ConfiguracionAsistencias where id = :clave");
            q.setString("clave", clave);
            conf = (ConfiguracionAsistencias) q.uniqueResult();
            mensajeResultado.setResultado(conf);
            mensajeResultado.setNoError(0);
            mensajeResultado.setError("");
            getSession().getTransaction().commit();
        } catch (HibernateException ex) {
            System.err.println(concatena.delete(0, concatena.length()).append(msgError).append("getExcepcionesPorConfiguracionAsistencias()1_Error: ").append(ex));
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

    public Mensaje getConfiguracionAsistenciasAll(String claveRazonesSocial, String uuidCxn) {
        List<ConfiguracionAsistencias> values;
        try {
            inicializaVariableMensaje();
            setSession(HibernateUtil.currentSession(uuidCxn));
            getSession().beginTransaction();
            q = getSession().createQuery("from ConfiguracionAsistencias c where c.razonesSociales.clave=:clave order by ordenId asc");
            q.setParameter("clave", claveRazonesSocial);
            values = q.list();
            values = (values == null ? new ArrayList<ConfiguracionAsistencias>() : values);
            mensajeResultado.setResultado(values);
            mensajeResultado.setNoError(0);
            mensajeResultado.setError("");
            getSession().getTransaction().commit();
        } catch (HibernateException ex) {
            System.err.println(concatena.delete(0, concatena.length()).append(msgError).append("getConfiguracionAsistenciasAll()1_Error: ").append(ex));
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

    public Mensaje PorGrupoMenu(String idContenedor, String claveRazonSocial, String uuidCxn) {//JSA01
        List<ConfiguracionAsistencias> values;
        try {
            StringBuilder cadena = new StringBuilder("from ConfiguracionAsistencias c");
            cadena.append(" where c.contenedorPadre_ID = :clave ");
            cadena.append(" and c.razonesSociales.clave = :claveRazon ");
            cadena.append(" Order by  c.ordenId asc ");
            inicializaVariableMensaje();
            setSession(HibernateUtil.currentSession(uuidCxn));
            getSession().beginTransaction();
            q = getSession().createQuery(cadena.toString());
            q.setParameter("clave", idContenedor);
            q.setParameter("claveRazon", claveRazonSocial);
            values = q.list();
            mensajeResultado.setResultado(values);
            mensajeResultado.setNoError(0);
            mensajeResultado.setError("");
            getSession().getTransaction().commit();
        } catch (HibernateException ex) {
            System.err.println(concatena.delete(0, concatena.length()).append(msgError).append("PorGrupoMenu()1_Error: ").append(ex));
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

    public Mensaje saveDeleteConfiguracionAsistencias(List<ConfiguracionAsistencias> AgreModif, List<ConfiguracionAsistencias> Ordenados, List<ConfiguracionAsistencias> eliminados, String uuidCxn) {
        listEsp = new ArrayList<ConfiguracionAsistencias>();
        inicializaVariableMensaje();
        setSession(HibernateUtil.currentSession(uuidCxn));
        //sessionMaster = HibernateUtil.getSessionFactoryMEFMaster().getCurrentSession();
        Transaction transaction = null;
        //Transaction transactionMaster = null;
        try {
            commit = true;
            //      commitMaster = true;
            transaction = getSession().beginTransaction();
            //    transactionMaster = sessionMaster.beginTransaction();

            if (eliminados.size() > 0) {
                for (ConfiguracionAsistencias con : eliminados) {
                    con.setExcepciones(null);
                    getSession().saveOrUpdate(con);
                }
                Object[] clavesConf = new Object[eliminados.size()];
//////                Object[] clavesCont = new Object[eliminados.size()];

                for (int i = 0; i < eliminados.size(); i++) {
//////                    StringBuilder accion = new StringBuilder(0);
                    clavesConf[i] = eliminados.get(i).getId();
//////                    accion.delete(0, accion.length()).append("ARS,PAsistencia");
//////                    accion.insert(accion.indexOf(","), eliminados.get(i).getRazonesSociales().getClave());
//////                    accion.insert(accion.length(), String.valueOf(clavesConf[i]));
//////                    clavesCont[i] = accion;
                }

                clavesConf = clavesConf == null ? new Object[0] : clavesConf;
//////                clavesCont = clavesCont == null ? new Object[0] : clavesCont;

                if (clavesConf.length > 0) {
                    deleteListQuerys("ConfiguracionAsistencias", "id", clavesConf);
//                    if (commit) {
//                        deleteListContenedores(clavesCont);
//                    }
                }
            }
            AgreModif = (AgreModif == null ? new ArrayList<ConfiguracionAsistencias>() : AgreModif);
            if (AgreModif.size() > 0) {
                if (commit && !AgreModif.isEmpty()) {
                    listEsp = agregarListaConfiguraAsistencias(AgreModif, 100, false);
                }
            }

            Ordenados = (Ordenados == null ? new ArrayList<ConfiguracionAsistencias>() : Ordenados);
            if (Ordenados.size() > 0) {
                if (commit && !Ordenados.isEmpty()) {
                    listEsp.addAll(agregarListaConfiguraAsistencias(Ordenados, 100, true));
                }
            }

            if (commit) {
                mensajeResultado.setResultado(listEsp);
                mensajeResultado.setNoError(0);
                mensajeResultado.setError("");
                transaction.commit();
                //transactionMaster.commit();
            } else {
                transaction.rollback();
                //transactionMaster.rollback();
            }
        } catch (HibernateException ex) {
            System.err.println(concatena.delete(0, concatena.length()).append(msgError).append("saveDeleteConfiguracionAsistencias()1_Error: ").append(ex));
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
            mensajeResultado.setResultado(null);
        }
        return mensajeResultado;
    }

    private void deleteListQuerys(String tabla, String campo, Object[] valores) {
        try {
            commit = true;
            consulta = new StringBuilder("delete ");
            consulta.append(tabla).append(" where ").append(campo).append(" in(:valores)");
            q = getSession().createQuery(consulta.toString());
            q.setParameterList("valores", valores);
            q.executeUpdate();
        } catch (HibernateException ex) {
            System.err.println(concatena.delete(0, concatena.length()).append(msgError).append("deleteListQuerys()1_Error: ").append(ex));
            mensajeResultado.setNoError(ControlErroresHibernate.buscaNoErrorPorExcepcion(ex.getClass()));
            mensajeResultado.setError(ex.getLocalizedMessage());
            mensajeResultado.setResultado(null);
            commit = false;
        }
    }

    private List<ConfiguracionAsistencias> agregarListaConfiguraAsistencias(List<ConfiguracionAsistencias> entitys, int rango, boolean ordenado) {
        if (listEsp != null) {
            listEsp.clear();
        }
        try {
            commit = true;
            int i;
            order = (ordenado == false ? 0 : entitys.get(0).getOrdenId());
//            Contenedor contenedor = null, contenedorPadre = contenedorAsistenciaPadre();
            StringBuilder accion = new StringBuilder(0);
            for (i = 0; i < entitys.size(); i++) {
                //accion.delete(0, accion.length()).append("ARS,PAsistencia");
                if (entitys.get(i).getId() == null) {
                    if (ordenado) {
                        entitys.get(i).setOrdenId(order);
                        order++;
                    }
                    getSession().save(entitys.get(i));
                    listEsp.add(entitys.get(i));
                    //                  contenedor = construyeContenedor(infoContenedorPadre(contenedorPadre));
                    //                contenedor.setNombre(entitys.get(i).getNombre());
//                    accion.insert(accion.indexOf(","), entitys.get(i).getRazonesSociales().getClave());
//                    accion.insert(accion.length(), String.valueOf(entitys.get(i).getId()));
//                    contenedor.setAccion(accion.toString());
//                    sessionMaster.save(contenedor);
                } else {
                    if (ordenado) {
                        entitys.get(i).setOrdenId(order);
                        order++;
                    }
                    if (entitys.get(i).isCambio()) {
                        getSession().saveOrUpdate(entitys.get(i));
//                        accion.insert(accion.indexOf(","), entitys.get(i).getRazonesSociales().getClave());
//                        accion.insert(accion.length(), String.valueOf(entitys.get(i).getId()));
//                        contenedor = contenedoresAsistencias("Contenedor", accion.toString());
//                        if (!contenedor.getNombre().equalsIgnoreCase(entitys.get(i).getNombre())) {
//                            contenedor.setNombre(entitys.get(i).getNombre());
//                            sessionMaster.save(contenedor);
//                        }
                    } else {
                        getSession().saveOrUpdate(entitys.get(i));
                    }
                }
                if (i % rango == 0 & i > 0) {
                    flush();
                    clear();
                }
            }
        } catch (HibernateException ex) {
            System.err.println(concatena.delete(0, concatena.length()).append(msgError).append("agregarListaConfiguraAsistencias()1_Error: ").append(ex));
            mensajeResultado.setNoError(ControlErroresHibernate.buscaNoErrorPorExcepcion(ex.getClass()));
            mensajeResultado.setError(ex.getLocalizedMessage());
            mensajeResultado.setResultado(null);
            commit = false;
        }
        return listEsp;
    }

    public Mensaje buscaPorIdYRazonSocial(Long id, String claveRazonSocial, String uuidCxn) {
        ConfiguracionAsistencias value;
        try {
            StringBuilder cadena = new StringBuilder("from ConfiguracionAsistencias c");
            cadena.append(" where c.id = :id ");
            cadena.append(" and c.razonesSociales.clave = :claveRazon ");
            inicializaVariableMensaje();
            setSession(HibernateUtil.currentSession(uuidCxn));
            getSession().beginTransaction();
            q = getSession().createQuery(cadena.toString());
            q.setParameter("id", id);
            q.setParameter("claveRazon", claveRazonSocial);
            value = (ConfiguracionAsistencias) q.uniqueResult();
            mensajeResultado.setResultado(value);
            mensajeResultado.setNoError(0);
            mensajeResultado.setError("");
            getSession().getTransaction().commit();
        } catch (HibernateException ex) {
            System.err.println(concatena.delete(0, concatena.length()).append(msgError).append("buscaPorIdYRazonSocial()1_Error: ").append(ex));
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
    
     public Mensaje buscaConfiguracionAsistenciasSistema(Long id, String uuidCxn) {
        ConfiguracionAsistencias value;
        try {
            StringBuilder cadena = new StringBuilder("from ConfiguracionAsistencias c");
            cadena.append(" where c.id = :id  ");
            cadena.append(" and c.sistema = true ");
            inicializaVariableMensaje();
            setSession(HibernateUtil.currentSession(uuidCxn));
            getSession().beginTransaction();
            q = getSession().createQuery(cadena.toString());
            q.setParameter("id", id);
            value = (ConfiguracionAsistencias) q.uniqueResult();
            mensajeResultado.setResultado(value);
            mensajeResultado.setNoError(0);
            mensajeResultado.setError("");
            getSession().getTransaction().commit();
        } catch (HibernateException ex) {
            System.err.println(concatena.delete(0, concatena.length()).append(msgError).append("buscaPorIdYRazonSocial()1_Error: ").append(ex));
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

    public Mensaje getAllConfiguracionAsistenciasSistema(String uuidCxn) {
        List<ConfiguracionAsistencias> values;
        try {
            StringBuilder cadena = new StringBuilder("from ConfiguracionAsistencias c ");
            cadena.append("where c.sistema = true and c.razonesSociales IS EMPTY");
            inicializaVariableMensaje();
            setSession(HibernateUtil.currentSession(uuidCxn));
            getSession().beginTransaction();
            q = getSession().createQuery(cadena.toString());
            values = (List<ConfiguracionAsistencias>) q.list();
            mensajeResultado.setResultado(values);
            mensajeResultado.setNoError(0);
            mensajeResultado.setError("");
            getSession().getTransaction().commit();
        } catch (HibernateException ex) {
            System.err.println(concatena.delete(0, concatena.length()).append(msgError).append("asignaConfigMovGlobalARazonSocial()1_Error: ").append(ex));
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
}
