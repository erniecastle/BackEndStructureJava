/**
 * @author: Fecha de Creacion: Compañía: Descripcion
 * -----------------------------------------------------------------------------
 * MODIFICACIONES:
 * -----------------------------------------------------------------------------
 * Clave:JSA01 Autor:Jose Armando Fecha:19/02/2015 Descripcion: Se modifico el
 * metodo getCreditosPorTipoCreditoYFecha se cambio el filtro de
 * c.fechaAutorizacion a c.fechaCredito
 * -----------------------------------------------------------------------------
 */
package com.mef.erp.modelo;

import com.mef.erp.modelo.entidad.CreditoPorEmpleado;
import com.mef.erp.modelo.entidad.Mensaje;
import com.mef.erp.util.ControlErroresHibernate;
import com.mef.erp.util.HibernateUtil;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.exception.ConstraintViolationException;

/**
 *
 * @author Ernesto
 */
public class CreditoPorEmpleadoDAO extends GenericHibernateDAO<CreditoPorEmpleado, Long>
        implements CreditoPorEmpleadoDAOIF {

    private final StringBuilder concatena = new StringBuilder(200);
    private final String msgError = concatena.append("ServerERP").append(".MSERR_C_").append(this.getClass().getName()).append(".").toString();
    private List<CreditoPorEmpleado> listEsp = new ArrayList<CreditoPorEmpleado>();
    private String max = null;

    public Mensaje agregar(CreditoPorEmpleado entity, String uuidCxn) {
        CreditoPorEmpleado credito;
        try {
            inicializaVariableMensaje();
            setSession(HibernateUtil.currentSession(uuidCxn));
            getSession().beginTransaction();
            credito = makePersistent(entity);
            mensajeResultado.setResultado(credito);
            mensajeResultado.setNoError(0);
            mensajeResultado.setError("");
            getSession().getTransaction().commit();
        } catch (HibernateException ex) {
            System.err.println(concatena.delete(0, concatena.length()).append(msgError).append("agregar()2_Error: ").append(ex));
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

    public Mensaje actualizar(CreditoPorEmpleado entity, String uuidCxn) {;
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
            System.err.println(concatena.delete(0, concatena.length()).append(msgError).append("actualizar()2_Error: ").append(ex));
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

    public Mensaje eliminar(CreditoPorEmpleado entity, String uuidCxn) {
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
            System.err.println(concatena.delete(0, concatena.length()).append(msgError).append("eliminar()2_Error: ").append(ex));
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

    public Mensaje getCreditosAll(String claveTipoCredito, String tipoConfiguracion, String uuidCxn) {
        List<CreditoPorEmpleado> values;
        try {
            inicializaVariableMensaje();
            setSession(HibernateUtil.currentSession(uuidCxn));
            getSession().beginTransaction();
            q = getSession().createQuery("from CreditoPorEmpleado c where  c.creditoAhorro.clave=:clave and c.creditoAhorro.tipoConfiguracion = :tipoConfiguracion");
            q.setParameter("tipoConfiguracion", tipoConfiguracion);
            q.setParameter("clave", claveTipoCredito);
            values = q.list();
            mensajeResultado.setResultado(values);
            mensajeResultado.setNoError(0);
            mensajeResultado.setError("");
            getSession().getTransaction().commit();
        } catch (HibernateException ex) {
            System.err.println(concatena.delete(0, concatena.length()).append(msgError).append("getCreditosAll()2_Error: ").append(ex));
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

    public Mensaje getCreditosPorClave(String numeroCredito, String claveTipoCredito, String tipoConfiguracion, String uuidCxn) {
        CreditoPorEmpleado r;
        try {
            inicializaVariableMensaje();
            setSession(HibernateUtil.currentSession(uuidCxn));
            getSession().beginTransaction();
            q = getSession().createQuery("from CreditoPorEmpleado where numeroCredito=:numeroCredito and c.creditoAhorro.clave=:claveTipoCredito  and c.creditoAhorro.tipoConfiguracion = :tipoConfiguracion");
            q.setParameter("numeroCredito", numeroCredito);
            q.setParameter("claveTipoCredito", claveTipoCredito);
            q.setParameter("tipoConfiguracion", tipoConfiguracion);
            r = (CreditoPorEmpleado) q.uniqueResult();
            mensajeResultado.setResultado(r);
            mensajeResultado.setNoError(0);
            mensajeResultado.setError("");
            getSession().getTransaction().commit();
        } catch (HibernateException ex) {
            System.err.println(concatena.delete(0, concatena.length()).append(msgError).append("getCreditosPorClave()2_Error: ").append(ex));
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

    public Mensaje getCreditosPorTipoCredito(String claveTipoCredito, String tipoConfiguracion, String uuidCxn) {
        List<CreditoPorEmpleado> r;
        try {
            inicializaVariableMensaje();
            setSession(HibernateUtil.currentSession(uuidCxn));
            getSession().beginTransaction();
            q = getSession().createQuery("from CreditoPorEmpleado c where c.creditoAhorro.clave=:claveTipoCredito and c.creditoAhorro.tipoConfiguracion = :tipoConfiguracion  order by c.numeroCredito desc");
            q.setParameter("claveTipoCredito", claveTipoCredito);
            q.setParameter("tipoConfiguracion", tipoConfiguracion);
//            q.setFirstResult(0);
//            q.setMaxResults(1);
            r = (List<CreditoPorEmpleado>) q.list();
            mensajeResultado.setResultado(r);
            mensajeResultado.setNoError(0);
            mensajeResultado.setError("");
            getSession().getTransaction().commit();
        } catch (HibernateException ex) {
            System.err.println(concatena.delete(0, concatena.length()).append(msgError).append("getCreditosPorTipoCredito()2_Error: ").append(ex));
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

    public Mensaje existenMovimientosEnCreditos(String numeroDeCredito, String claveCreditoAhorro, String tipoConfiguracion, String uuidCxn) {
        try {
            inicializaVariableMensaje();
            setSession(HibernateUtil.currentSession(uuidCxn));
            getSession().beginTransaction();
            consulta = new StringBuilder();
            consulta.append("select count(*) from CreditoMovimientos c where c.creditoPorEmpleado.numeroCredito=:numeroDeCredito and ");
            consulta.append("c.creditoPorEmpleado.creditoAhorro.clave=:claveCreditoAhorro and ");
            consulta.append("c.creditoPorEmpleado.creditoAhorro.tipoConfiguracion = :tipoConfiguracion  ");
            q = getSession().createQuery(consulta.toString());
            q.setParameter("numeroDeCredito", numeroDeCredito);
            q.setParameter("claveCreditoAhorro", claveCreditoAhorro);
            q.setParameter("tipoConfiguracion", tipoConfiguracion);

            if (((Number) q.uniqueResult()).intValue() > 0) {
                mensajeResultado.setResultado(true);
            } else {
                mensajeResultado.setResultado(false);
            }
            mensajeResultado.setNoError(0);
            mensajeResultado.setError("");
            getSession().getTransaction().commit();
        } catch (HibernateException ex) {
            System.err.println(concatena.delete(0, concatena.length()).append(msgError).append("existenMovimientosEnCreditos()2_Error: ").append(ex));
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
//        return false;
    }

    public Mensaje getCreditosPorTipoCreditoYFecha(Date fecha, String tipoCredito, String claveRazonsocial, String tipoConfiguracion, String uuidCxn) {
        List<CreditoPorEmpleado> values;
        try {
            inicializaVariableMensaje();
            setSession(HibernateUtil.currentSession(uuidCxn));
            getSession().beginTransaction();
            consulta = new StringBuilder();
            consulta.append("from CreditoPorEmpleado c where c.fechaCredito=:fecha and c.creditoAhorro.clave=:claveTipoCredito and c.razonesSociales.clave=:claveRazonsocial and ");//JSA01
            consulta.append("c.creditoAhorro.tipoConfiguracion = :tipoConfiguracion  ");
            q = getSession().createQuery(consulta.toString());
            q.setDate("fecha", fecha);
            q.setParameter("claveTipoCredito", tipoCredito);
            q.setParameter("claveRazonsocial", claveRazonsocial);
            q.setParameter("tipoConfiguracion", tipoConfiguracion);
            values = q.list();
            mensajeResultado.setResultado(values);
            mensajeResultado.setNoError(0);
            mensajeResultado.setError("");
            if (!getSession().getTransaction().wasCommitted()) {
                getSession().getTransaction().commit();
            }
        } catch (HibernateException ex) {
            System.err.println(concatena.delete(0, concatena.length()).append(msgError).append("getCreditosPorTipoCreditoYFecha()2_Error: ").append(ex));
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

    public Mensaje consultaPorRangos(Integer inicio, Integer rango, String uuidCxn) {
        try {
            inicializaVariableMensaje();
            setSession(HibernateUtil.currentSession(uuidCxn));
            getSession().beginTransaction();
            listEsp = (List<CreditoPorEmpleado>) consultaPorRangos(inicio, rango, null, null);
            mensajeResultado.setResultado(listEsp);
            mensajeResultado.setNoError(0);
            mensajeResultado.setError("");
            if (!getSession().getTransaction().wasCommitted()) {
                getSession().getTransaction().commit();
            }
        } catch (HibernateException ex) {
            System.err.println(concatena.delete(0, concatena.length()).append(msgError).append("consultaPorRangos()2_Error: ").append(ex));
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

    public Mensaje existeDato(String campo, Object valor, String uuidCxn) {
        Boolean existe;
        try {
            inicializaVariableMensaje();
            setSession(HibernateUtil.currentSession(uuidCxn));
            getSession().beginTransaction();
            existe = existeDato("CreditoPorEmpleado", campo, valor);
            mensajeResultado.setResultado(existe);
            mensajeResultado.setNoError(0);
            mensajeResultado.setError("");
            if (!getSession().getTransaction().wasCommitted()) {
                getSession().getTransaction().commit();
            }
        } catch (HibernateException ex) {
            System.err.println(concatena.delete(0, concatena.length()).append(msgError).append("existeDato()2_Error: ").append(ex));
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

    public Mensaje saveDeleteCreditos(List<CreditoPorEmpleado> entitysCambios, Object[] eliminados, String uuidCxn) {
        entitysCambios = (entitysCambios == null ? new ArrayList<CreditoPorEmpleado>() : entitysCambios);
        eliminados = eliminados == null ? new Object[]{} : eliminados;
        inicializaVariableMensaje();
        setSession(HibernateUtil.currentSession(uuidCxn));
        CreditoPorEmpleado cred = null;
        max = null;
        try {
            boolean commit = true;
            getSession().beginTransaction();
            if (!entitysCambios.isEmpty()) {
                max = obtenerClaveStringMax("CreditoPorEmpleado",
                        new String[]{"razonesSociales.clave", "creditoAhorro.clave", "creditoAhorro.tipoConfiguracion"},
                        new Object[]{entitysCambios.get(0).getRazonesSociales().getClave(),
                            entitysCambios.get(0).getCreditoAhorro().getClave(), entitysCambios.get(0).getCreditoAhorro().getTipoConfiguracion()}, "numeroCredito");
            }
            if (eliminados != null && eliminados.length > 0) {
                commit = deleteListQuery(eliminados, uuidCxn);
                clear();
            }

            if (commit && !entitysCambios.isEmpty()) {
                for (int i = 0; i < entitysCambios.size(); i++) {
                    cred = entitysCambios.get(i);
                    getSession().saveOrUpdate(entitysCambios.get(i));
                    flush();
                }
            }
            if (commit) {
                    getSession().getTransaction().commit();
                    mensajeResultado.setResultado(null);
                    mensajeResultado.setNoError(0);
                    mensajeResultado.setError("");
            } else {
                    getSession().getTransaction().rollback();
            }
        } catch (ConstraintViolationException ex) {
            if (cred != null) {
                cred.setNumeroCredito(max == null ? cred.getNumeroCredito() : max);
            }
            System.err.println(concatena.delete(0, concatena.length()).append(msgError).append("saveDeleteCreditos()1_Error: ").append(ex));
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
            mensajeResultado.setResultado(cred);
        } catch (HibernateException ex) {
            System.err.println(concatena.delete(0, concatena.length()).append(msgError).append("saveDeleteCreditos()2_Error: ").append(ex));
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
            mensajeResultado.setResultado(cred);
        }

        return mensajeResultado;
    }

    private List<CreditoPorEmpleado> agregarListaCreditos(List<CreditoPorEmpleado> entitys, int rango, String uuidCxn) {
        listEsp.clear();
        try {

            int i = 0;
            for (i = 0; i < entitys.size(); i++) {
                listEsp.add(makePersistent(entitys.get(i)));
                if (i % rango == 0 & i > 0) {
                    flush();
                    clear();
                }
            }
        } catch (HibernateException ex) {
            System.err.println(concatena.delete(0, concatena.length()).append(msgError).append("agregarListaCreditos()1_Error: ").append(ex));
        }
        return listEsp;
    }

    private boolean deleteListQuery(Object[] eliminados, String uuidCxn) {
        boolean commit = true;
        try {
            deleteListQuery("CreditoPorEmpleado", "Id", eliminados);
        } catch (HibernateException ex) {
            System.err.println(concatena.delete(0, concatena.length()).append(msgError).append("deleteListQuery()1_Error: ").append(ex));
            mensajeResultado.setNoError(ControlErroresHibernate.buscaNoErrorPorExcepcion(ex.getClass()));
            mensajeResultado.setError(ex.getLocalizedMessage());
            mensajeResultado.setResultado(null);
            commit = false;
        }
        return commit;
    }
}
