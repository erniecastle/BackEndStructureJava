/**
 * @author: Victor Lopez Compañía: Macropro. Descripción del programa: clase
 * FiniqLiquidCncNomDAO para llamados a metodos de HIBERNATE Fecha:04-07-2012
 * -----------------------------------------------------------------------------
 * MODIFICACIONES:
 * -----------------------------------------------------------------------------
 * Clave: Autor: Fecha: Descripción:
 * -----------------------------------------------------------------------------
 */
package com.mef.erp.modelo;

import com.mef.erp.modelo.entidad.FiniqLiquidCncNom;
import com.mef.erp.modelo.entidad.FiniquitosLiquidaciones;
import com.mef.erp.modelo.entidad.Mensaje;
import com.mef.erp.util.ControlErroresHibernate;
import com.mef.erp.util.HibernateUtil;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.HibernateException;

public class FiniqLiquidCncNomDAO extends GenericHibernateDAO<FiniqLiquidCncNom, Long>
        implements FiniqLiquidCncNomDAOIF {

    private final StringBuilder concatena = new StringBuilder(200);
    private final String msgError = concatena.append("ServerERP"/*
     * util.getContexto()
     */).append(".MSERR_C_").append(this.getClass().getName()).append(".").toString();
    private List<FiniqLiquidCncNom> listEsp = new ArrayList<FiniqLiquidCncNom>();
    boolean commit;

    public Mensaje agregar(FiniqLiquidCncNom entity, String uuidCxn) {
        FiniqLiquidCncNom finiqLiquidCncNom;
        try {
            inicializaVariableMensaje();
            setSession(HibernateUtil.currentSession(uuidCxn));
            getSession().beginTransaction();
            finiqLiquidCncNom = makePersistent(entity);
            mensajeResultado.setResultado(finiqLiquidCncNom);
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

    public Mensaje actualizar(FiniqLiquidCncNom entity, String uuidCxn) {
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

    public Mensaje eliminar(FiniqLiquidCncNom entity, String uuidCxn) {
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

    public Mensaje getFiniqLiquidCncNomAll(String uuidCxn) {
        List<FiniqLiquidCncNom> values;
        try {
            inicializaVariableMensaje();
            setSession(HibernateUtil.currentSession(uuidCxn));
            getSession().beginTransaction();
            q = getSession().createQuery("from FiniqLiquidCncNom order by id");
            values = q.list();
            mensajeResultado.setResultado(values);
            mensajeResultado.setNoError(0);
            mensajeResultado.setError("");
            getSession().getTransaction().commit();
        } catch (HibernateException ex) {
            System.err.println(concatena.delete(0, concatena.length()).append(msgError).append("getFiniqLiquidCncNomAll()1_Error: ").append(ex));
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

    public Mensaje getFiniqLiquidCncNomPorFiniquitosLiquidaciones(FiniquitosLiquidaciones finiquitosLiquidacion, String uuidCxn) {
        List<FiniqLiquidCncNom> values;
        try {
            inicializaVariableMensaje();
            setSession(HibernateUtil.currentSession(uuidCxn));
            getSession().beginTransaction();
            q = getSession().createQuery("Select si from FiniqLiquidCncNom si Where si.finiquitosLiquidacion = :finiquitosLiquidacion");
            q.setParameter("finiquitosLiquidacion", finiquitosLiquidacion);
            values = q.list();
            mensajeResultado.setResultado(values);
            mensajeResultado.setNoError(0);
            mensajeResultado.setError("");
            getSession().getTransaction().commit();
        } catch (HibernateException ex) {
            System.err.println(concatena.delete(0, concatena.length()).append(msgError).append("getFiniqLiquidCncNomPorFiniquitosLiquidaciones()1_Error: ").append(ex));
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

//    public FiniqLiquidCncNom getFiniqLiquidCncNomPorEmpERegPatyRazonSoc(Empleados empleados, RegistroPatronal registroPatronal, RazonesSociales razonSocial) {
//        FiniqLiquidCncNom r = null;
//        try {
//            inicializaVariableMensaje();            setSession(HibernateUtil.currentSession(uuidCxn));
//            getSession().beginTransaction();
//            q = getSession().createQuery("from FiniqLiquidCncNom where empleados=:empleados and registroPatronal = :registroPatronal and razonSocial = :razonSocial");
//            q.setParameter("empleados", empleados);
//            q.setParameter("razonSocial", razonSocial);
//            q.setParameter("empleados", empleados);
//            r = (FiniqLiquidCncNom) q.uniqueResult();
//            getSession().getTransaction().commit();
//        } catch (HibernateException ex) {
//            System.err.println(concatena.delete(0, concatena.length()).append(msgError).append("getFiniqLiquidCncNomPorClave()1_Error: ").append(ex));
//            getSession().getTransaction().rollback();
//        }
//        return r;
//    }
//    public List<FiniqLiquidCncNom> getFiniqLiquidCncNomPorRegPatronal(RegistroPatronal registroPatronal) {
//        List<FiniqLiquidCncNom> values = null;
//        try {
//            inicializaVariableMensaje();            setSession(HibernateUtil.currentSession(uuidCxn));
//            getSession().beginTransaction();
//            q = getSession().createQuery("Select si from FiniqLiquidCncNom si Where si.registroPatronal = :registroPatronal");
//            q.setParameter("registroPatronal", registroPatronal);
//            values = q.list();
//            getSession().getTransaction().commit();
//        } catch (HibernateException ex) {
//            System.err.println(concatena.delete(0, concatena.length()).append(msgError).append("getFiniqLiquidCncNomPorRegPatronal()1_Error: ").append(ex));
//            getSession().getTransaction().rollback();
//        }
//        return values;
//    }
//    public List<FiniqLiquidCncNom> getFiniqLiquidCncNomPorRazonSocial(RazonesSociales razonSocial) {
//        List<FiniqLiquidCncNom> values = null;
//        try {
//            inicializaVariableMensaje();            setSession(HibernateUtil.currentSession(uuidCxn));
//            getSession().beginTransaction();
//            q = getSession().createQuery("Select si from FiniqLiquidCncNom si Where si.razonSocial = :razonSocial");
//            q.setParameter("razonSocial", razonSocial);
//            values = q.list();
//            getSession().getTransaction().commit();
//        } catch (HibernateException ex) {
//            System.err.println(concatena.delete(0, concatena.length()).append(msgError).append("getFiniqLiquidCncNomPorRazonSocial()1_Error: ").append(ex));
//            getSession().getTransaction().rollback();
//        }
//        return values;
//    }
    public Mensaje consultaPorRangos(Integer inicio, Integer rango, String uuidCxn) {
        try {
            inicializaVariableMensaje();
            setSession(HibernateUtil.currentSession(uuidCxn));
            getSession().beginTransaction();
            listEsp = (List<FiniqLiquidCncNom>) consultaPorRangos(inicio, rango, null, null);
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
            } catch (HibernateException exc) { //cuando marca error al hacer rollback pro ejemplo se cae conexion servidor
                mensajeResultado.setNoError(ControlErroresHibernate.buscaNoErrorPorExcepcion(exc.getClass()));
                mensajeResultado.setError(exc.getLocalizedMessage());
            }
            mensajeResultado.setResultado(null);
        }
        return mensajeResultado;
    }

    public Mensaje consultaPorFiltrosFiniquitos(String query, Object[] campos, Object[] valores, String uuidCxn) {
        try {
            inicializaVariableMensaje();
            setSession(HibernateUtil.currentSession(uuidCxn));
            getSession().beginTransaction();
            listEsp = (List<FiniqLiquidCncNom>) consultaPorFiltros(query, campos, valores, null, null);
            mensajeResultado.setResultado(listEsp);
            mensajeResultado.setNoError(0);
            mensajeResultado.setError("");
            getSession().getTransaction().commit();
        } catch (HibernateException ex) {
            System.err.println(concatena.delete(0, concatena.length()).append(msgError).append("consultaPorFiltrosFiniquitos()1_Error: ").append(ex));
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

    public Mensaje existeDato(String campo, Object valor, String uuidCxn) {
        Boolean existe;
        try {
            inicializaVariableMensaje();
            setSession(HibernateUtil.currentSession(uuidCxn));
            getSession().beginTransaction();
            existe = existeDato("FiniqLiquidCncNom", campo, valor);
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
            } catch (HibernateException exc) { //cuando marca error al hacer rollback pro ejemplo se cae conexion servidor
                mensajeResultado.setNoError(ControlErroresHibernate.buscaNoErrorPorExcepcion(exc.getClass()));
                mensajeResultado.setError(exc.getLocalizedMessage());
            }
            mensajeResultado.setResultado(null);
        }
        return mensajeResultado;
    }

    public Mensaje saveDeleteFiniqLiquidCncNom(List<FiniqLiquidCncNom> entitysCambios, Object[] clavesDelete, String uuidCxn) {
        listEsp = new ArrayList<FiniqLiquidCncNom>();
        try {
            commit = true;
            inicializaVariableMensaje();
            setSession(HibernateUtil.currentSession(uuidCxn));
            getSession().beginTransaction();
            if (clavesDelete != null) {
                commit = deleteListFiniqLiquidCncNom("FiniqLiquidCncNom", "id", clavesDelete, uuidCxn);
            }
            entitysCambios = (entitysCambios == null ? new ArrayList<FiniqLiquidCncNom>() : entitysCambios);
            if (commit && !entitysCambios.isEmpty()) {
                listEsp = agregarListaFiniqLiquidCncNom(entitysCambios, 50, uuidCxn);
            }
            if (commit) {
                mensajeResultado.setResultado(listEsp);
                mensajeResultado.setNoError(0);
                mensajeResultado.setError("");
                getSession().getTransaction().commit();
            } else {
                getSession().getTransaction().rollback();
            }
        } catch (HibernateException ex) {
            System.err.println(concatena.delete(0, concatena.length()).append(msgError).append("saveDeleteFiniqLiquidCncNom()1_Error: ").append(ex));
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

    private List<FiniqLiquidCncNom> agregarListaFiniqLiquidCncNom(List<FiniqLiquidCncNom> entitys, int rango, String uuidCxn) {
        listEsp = new ArrayList<FiniqLiquidCncNom>();
        commit = true;
        try {
            int i;
            for (i = 0; i < entitys.size(); i++) {
                if (entitys.get(i).getId() == null) {
                    makePersistent(entitys.get(i));
                } else {
                    makePersistent(entitys.get(i));
                }
                listEsp.add(entitys.get(i));
                if (i % rango == 0 & i > 0) {
                    flush();
                    clear();
                }
            }
        } catch (HibernateException ex) {
            System.err.println(concatena.delete(0, concatena.length()).append(msgError).append("agregarListaFiniqLiquidCncNom()1_Error: ").append(ex));
            mensajeResultado.setNoError(ControlErroresHibernate.buscaNoErrorPorExcepcion(ex.getClass()));
            mensajeResultado.setError(ex.getLocalizedMessage());
            mensajeResultado.setResultado(null);
            commit = false;
        }
        return listEsp;
    }

    private boolean deleteListFiniqLiquidCncNom(String tabla, String campo, Object[] valores, String uuidCxn) {
        boolean committ = true;
        try {
            deleteListQuery(tabla, campo, valores);
        } catch (HibernateException ex) {
            System.err.println(concatena.delete(0, concatena.length()).append(msgError).append("deleteListFiniqLiquidCncNom()1_Error: ").append(ex));
            mensajeResultado.setNoError(ControlErroresHibernate.buscaNoErrorPorExcepcion(ex.getClass()));
            mensajeResultado.setError(ex.getLocalizedMessage());
            mensajeResultado.setResultado(null);
            committ = false;
        }
        return committ;
    }
}
