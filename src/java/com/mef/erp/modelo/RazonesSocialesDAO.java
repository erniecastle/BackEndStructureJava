/**
 * @author: Ernesto Castillo Fecha de Creación: 07/04/2011 Compañía: Exito
 * Software. Descripción del programa: clase razones sociales para llamados a
 * metodos de HIBERNATE
 * -----------------------------------------------------------------------------
 * MODIFICACIONES:
 * -----------------------------------------------------------------------------
 * Clave: JSA01 Autor: Jose Armando Sanchez Acosta Fecha: 08-07-2011
 * Descripción: Se aplicaron PDM.
 * -----------------------------------------------------------------------------
 * Clave: AAP01 Autor: Abraham Daniel Arjona Peraza Fecha: 30/07/2011
 * Descripción: Se Cambió clave a tipo String
 * -----------------------------------------------------------------------------
 * Clave: JSA02 Autor: Jose Armando Sanchez Acosta Fecha: 18-10-2011
 * Descripción: se agrego el metodo consultaPorFiltroIN para consultar la
 * informacion mas facil.
 * -----------------------------------------------------------------------------
 * Clave:JSA03 Autor:Jose Armando Sanchez Acosta Fecha:28/11/2014 Descripción:
 * Se agrego la programacion para eliminar, actualizar y agregar RazonSocial.
 * -----------------------------------------------------------------------------
 */
package com.mef.erp.modelo;

import com.mef.erp.modelo.entidad.Mensaje;
import com.mef.erp.modelo.entidad.RazonSocial;
import com.mef.erp.modelo.entidad.RazonesSociales;
import com.mef.erp.util.ControlErroresHibernate;
import com.mef.erp.util.HibernateUtil;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.HibernateException;

public class RazonesSocialesDAO extends GenericHibernateDAO<RazonesSociales, String>//AAP01
        implements RazonesSocialesDAOIF {

    private StringBuilder concatena = new StringBuilder(200);
    private final String msgError = concatena.append("ServerERP"/*util.getContexto()*/).append(".MSERR_F_").append(this.getClass().getName()).append(".").toString();
    private List<RazonesSociales> listEsp = new ArrayList<RazonesSociales>();

    public Mensaje agregar(RazonesSociales entity, String uuidCxn, String uuidCxnMaestra) {//JSA01
        RazonesSociales razonesSociales;
        try {
            inicializaVariableMensaje();
            setSession(HibernateUtil.currentSession(uuidCxn));
            getSession().beginTransaction();
            razonesSociales = makePersistent(entity);
            getSession().getTransaction().commit();

            setSession(HibernateUtil.currentSession(uuidCxnMaestra));//JSA03
            getSession().beginTransaction();
            RazonSocial razonSocial = new RazonSocial();
            razonSocial.setClaveRazonSocial(entity.getClave());
            razonSocial.setNombreRazonSocial(entity.getRazonsocial());
            getSession().saveOrUpdate(razonSocial);
            getSession().getTransaction().commit();
            mensajeResultado.setResultado(razonesSociales);
            mensajeResultado.setNoError(0);
            mensajeResultado.setError("");
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

    public Mensaje actualizar(RazonesSociales entity, String uuidCxn, String uuidCxnMaestra) {//JSA01
        try {
            inicializaVariableMensaje();
            setSession(HibernateUtil.currentSession(uuidCxn));
            getSession().beginTransaction();
            makePersistent(entity);
            getSession().getTransaction().commit();

            setSession(HibernateUtil.currentSession(uuidCxnMaestra));//JSA03
            getSession().beginTransaction();
            q = getSession().createQuery("from RazonSocial r where r.claveRazonSocial =:clave ");
            q.setParameter("clave", entity.getClave());
            RazonSocial razonSocial = (RazonSocial) q.uniqueResult();
            razonSocial.setNombreRazonSocial(entity.getRazonsocial());
            getSession().saveOrUpdate(razonSocial);
            getSession().getTransaction().commit();
            mensajeResultado.setResultado(true);
            mensajeResultado.setNoError(0);
            mensajeResultado.setError("");
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

    public Mensaje eliminar(RazonesSociales entity, String uuidCxn, String uuidCxnMaestra) {//JSA01
        try {
            inicializaVariableMensaje();
            setSession(HibernateUtil.currentSession(uuidCxn));
            getSession().beginTransaction();
            makeTransient(entity);
            getSession().getTransaction().commit();

            setSession(HibernateUtil.currentSession(uuidCxnMaestra));//JSA03
            getSession().beginTransaction();
            q = getSession().createQuery("from RazonSocial r where r.claveRazonSocial =:clave ");
            q.setParameter("clave", entity.getClave());
            RazonSocial razonSocial = (RazonSocial) q.uniqueResult();
            getSession().delete(razonSocial);
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

    public Mensaje getRazonesAll( String uuidCxn) {//JSA01
        List<RazonesSociales> values;
        try {
            //inicializaVariableMensaje();            setSession(HibernateUtil.currentSession(uuidCxn));
            inicializaVariableMensaje();
            setSession(HibernateUtil.currentSession(uuidCxn));
            getSession().beginTransaction();
            q = getSession().createQuery("from RazonesSociales");
            values = q.list();
            mensajeResultado.setResultado(values);
            mensajeResultado.setNoError(0);
            mensajeResultado.setError("");
            getSession().getTransaction().commit();
        } catch (HibernateException ex) {
            System.err.println(concatena.delete(0, concatena.length()).append(msgError).append("getRazonesAll()1_Error: ").append(ex));
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

    public Mensaje getRazonesPorClave(String clave, String uuidCxn) {//AAP01//JSA01
        RazonesSociales r;
        try {
            inicializaVariableMensaje();
            setSession(HibernateUtil.currentSession(uuidCxn));
            getSession().beginTransaction();
            q = getSession().createQuery("from RazonesSociales where clave=:clave");
            q.setParameter("clave", clave);
            r = (RazonesSociales) q.uniqueResult();
            mensajeResultado.setResultado(r);
            mensajeResultado.setNoError(0);
            mensajeResultado.setError("");
            getSession().getTransaction().commit();
        } catch (HibernateException ex) {
            System.err.println(concatena.delete(0, concatena.length()).append(msgError).append("getRazonesPorClave()1_Error: ").append(ex));
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

    public Mensaje getRazonesSocialesPorClaves(String[] claveRazonesSociales, String uuidCxn) {
        List<RazonesSociales> r;
        try {
            inicializaVariableMensaje();
            setSession(HibernateUtil.currentSession(uuidCxn));
            getSession().beginTransaction();
            q = getSession().createQuery("from RazonesSociales r where r.clave in (:claves)");
            q.setParameterList("claves", claveRazonesSociales);
            r = (List<RazonesSociales>) q.list();
            mensajeResultado.setResultado(r);
            mensajeResultado.setNoError(0);
            mensajeResultado.setError("");
            getSession().getTransaction().commit();
        } catch (HibernateException ex) {
            System.err.println(concatena.delete(0, concatena.length()).append(msgError).append("getRazonesSocialesPorClaves()1_Error: ").append(ex));
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

    public Mensaje existeRFC(String rfc, String uuidCxn) {//JSA01
        Boolean existe;
        try {
            inicializaVariableMensaje();
            setSession(HibernateUtil.currentSession(uuidCxn));
            getSession().beginTransaction();
            q = getSession().createQuery("from RazonesSociales AS r where exists(from RazonesSociales as ra where ra.rfc=:rfc )");
            q.setParameter("rfc", rfc);
            existe = q.iterate().hasNext();
            mensajeResultado.setResultado(existe);
            mensajeResultado.setNoError(0);
            mensajeResultado.setError("");
            getSession().getTransaction().commit();
        } catch (HibernateException ex) {
            System.err.println(concatena.delete(0, concatena.length()).append(msgError).append("existeRFC()1_Error: ").append(ex));
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

    public Mensaje consultaPorRangos(Integer inicio, Integer rango, String uuidCxn) {//JSA01
        try {
            inicializaVariableMensaje();
            setSession(HibernateUtil.currentSession(uuidCxn));
            getSession().beginTransaction();
            listEsp = (List<RazonesSociales>) consultaPorRangos(inicio, rango, null, null);
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
            } catch (HibernateException exc) {  //cuando marca error al hacer rollback pro ejemplo se cae conexion servidor
                mensajeResultado.setNoError(ControlErroresHibernate.buscaNoErrorPorExcepcion(exc.getClass()));
                mensajeResultado.setError(exc.getLocalizedMessage());
            }
            mensajeResultado.setResultado(null);
        }
        return mensajeResultado;
    }

    boolean commit = true;

    public Mensaje saveDeleteRazonesSociales(List<RazonesSociales> entitysCambios, Object[] clavesDelete, int rango, String uuidCxn) {//JSA01
        listEsp = new ArrayList<RazonesSociales>();
        try {
            commit = true;
            inicializaVariableMensaje();
            setSession(HibernateUtil.currentSession(uuidCxn));
            getSession().beginTransaction();
            if (clavesDelete != null) {
                commit = deleteListQuerys("RazonesSociales", "Clave", clavesDelete);
            }
            entitysCambios = (entitysCambios == null ? new ArrayList<RazonesSociales>() : entitysCambios);
            if (commit && !entitysCambios.isEmpty()) {
                listEsp = agregarListaRazonesSociales(entitysCambios, rango);
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
            System.err.println(concatena.delete(0, concatena.length()).append(msgError).append("saveDeleteRazonesSociales()1_Error: ").append(ex));
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

    private List<RazonesSociales> agregarListaRazonesSociales(List<RazonesSociales> entitys, int rango) {//JSA01
        listEsp.clear();
        try {
            commit = true;
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
        } catch (HibernateException ex) {
            System.err.println(concatena.delete(0, concatena.length()).append(msgError).append("agregarListaRazonesSociales()1_Error: ").append(ex));
            mensajeResultado.setNoError(ControlErroresHibernate.buscaNoErrorPorExcepcion(ex.getClass()));
            mensajeResultado.setError(ex.getLocalizedMessage());
            mensajeResultado.setResultado(null);
            commit = false;
        }
        return listEsp;
    }

    private boolean deleteListQuerys(String tabla, String campo, Object[] valores) {//JSA01
        boolean exito = true;
        try {
            deleteListQuery(tabla, campo, valores);
        } catch (HibernateException ex) {
            System.err.println(concatena.delete(0, concatena.length()).append(msgError).append("deleteListQuerys()1_Error: ").append(ex));
            mensajeResultado.setNoError(ControlErroresHibernate.buscaNoErrorPorExcepcion(ex.getClass()));
            mensajeResultado.setError(ex.getLocalizedMessage());
            mensajeResultado.setResultado(null);
            exito = false;
        }
        return exito;
    }

    public Mensaje existeDato(String campo, Object valor, String uuidCxn) {//JSA01
        Boolean existe;
        try {
            inicializaVariableMensaje();
            setSession(HibernateUtil.currentSession(uuidCxn));
            getSession().beginTransaction();
            existe = existeDato("RazonesSociales", campo, valor);
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

    public Mensaje consultaPorFiltrosRazonSocial(String query, Object[] campos, Object[] valores, Integer inicio, Integer rango, String uuidCxn) {//JSA01
        try {
            inicializaVariableMensaje();
            setSession(HibernateUtil.currentSession(uuidCxn));
            getSession().beginTransaction();
            listEsp = (List<RazonesSociales>) consultaPorFiltros(query, campos, valores, inicio, rango);
            mensajeResultado.setResultado(listEsp);
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

    public Mensaje consultaPorFiltroIN(String query, Object[] campos, Object[] valores, String uuidCxn) {//JSA01
        try {
            inicializaVariableMensaje();
            setSession(HibernateUtil.currentSession(uuidCxn));
            getSession().beginTransaction();
            q = getSession().createQuery(query);

            for (int i = 0; i < campos.length; i++) {
                q.setParameterList(campos[i].toString(), (Object[]) valores[i]);
            }
            listEsp = (List<RazonesSociales>) q.list();
            mensajeResultado.setResultado(listEsp);
            mensajeResultado.setNoError(0);
            mensajeResultado.setError("");
            getSession().getTransaction().commit();
        } catch (HibernateException ex) {
            System.err.println(concatena.delete(0, concatena.length()).append(msgError).append("consultaPorFiltroIN()1_Error: ").append(ex));
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
