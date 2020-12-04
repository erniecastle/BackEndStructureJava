/**
 * @author: Victor Fecha de Creación: 06/06/2011 Compañía: Macropro Descripción
 * del programa: clase TablaBase para llamados a metodos de HIBERNATE
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

import com.mef.erp.modelo.entidad.Herramienta;
import com.mef.erp.modelo.entidad.HerramientaPersonalizada;
import com.mef.erp.modelo.entidad.Mensaje;
import com.mef.erp.modelo.entidad.Perfiles;
import com.mef.erp.modelo.entidad.Usuario;
import com.mef.erp.util.ControlErroresHibernate;
import com.mef.erp.util.HibernateUtil;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Transaction;

/**
 *
 * @author Jose Armando
 */
public class HerramientaDAO extends GenericHibernateDAO<Herramienta, Long>
        implements HerramientaDAOIF {

    private StringBuilder concatena = new StringBuilder(200);
    private final String msgError = concatena.append("ServerERP").append(".MSERR_C_").append(this.getClass().getName()).append(".").toString();

    public Mensaje getHerramientaAll(String uuidCxn) {//JSA02
        List<Herramienta> values;
        try {
            inicializaVariableMensaje();
            setSession(HibernateUtil.currentSession(uuidCxn));
            getSession().beginTransaction();
            q = getSession().createQuery("from Herramienta");
            values = q.list();
            mensajeResultado.setResultado(values);
            mensajeResultado.setNoError(0);
            mensajeResultado.setError("");
            getSession().getTransaction().commit();
        } catch (HibernateException ex) {
            System.err.println(concatena.delete(0, concatena.length()).append(msgError).append("getHerramientaAll()1_Error: ").append(ex));
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

    public Mensaje getHerramientaCompartidas(String uuidCxn) {//JSA02
        List<Herramienta> values;
        try {
            inicializaVariableMensaje();
            setSession(HibernateUtil.currentSession(uuidCxn));
            getSession().beginTransaction();
            q = getSession().createQuery("from Herramienta where compartir=true");
            values = q.list();
            mensajeResultado.setResultado(values);
            mensajeResultado.setNoError(0);
            mensajeResultado.setError("");
            getSession().getTransaction().commit();
        } catch (HibernateException ex) {
            System.err.println(concatena.delete(0, concatena.length()).append(msgError).append("getHerramientaCompartidas()1_Error: ").append(ex));
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

    boolean comit = true;

    public Mensaje getHerramientasPrincipales(Usuario usuario, String uuidCxn) {//JSA02
        List<Herramienta> values, herramientasConContenedores = new ArrayList<Herramienta>();

        try {
            comit = true;
            inicializaVariableMensaje();
            setSession(HibernateUtil.currentSession(uuidCxn));
            getSession().beginTransaction();
            concatena = new StringBuilder("from Herramienta h ");
            concatena.append(" where principal = true or secundario = true");
            concatena.append(" order by tipoHerramienta ");
            q = getSession().createQuery(concatena.toString());
            values = q.list();
            if (!values.isEmpty()) {
                concatena.delete(0, concatena.length()).append("select count(c) from Contenedor c  where c.herramienta = :herramienta ");
                int i;
                for (i = 0; i < values.size(); i++) {
                    q = getSession().createQuery(concatena.toString());
                    q.setParameter("herramienta", values.get(i));
                    Long cantidad = (Long) q.uniqueResult();
                    if (cantidad > 0) {
                        herramientasConContenedores.add(values.get(i));
                    }
                }
                if (usuario != null & !herramientasConContenedores.isEmpty()) {
                    herramientasConContenedores = getHerramientasPersonalizadas(usuario, values ,  uuidCxn);
                }

            }
            if (comit) {
                mensajeResultado.setResultado(herramientasConContenedores);
                mensajeResultado.setNoError(0);
                mensajeResultado.setError("");
                getSession().getTransaction().commit();
            } else {
                getSession().getTransaction().rollback();
            }
        } catch (HibernateException ex) {
            System.err.println(concatena.delete(0, concatena.length()).append(msgError).append("getHerramientasPrincipales()1_Error: ").append(ex));
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

    private List<Herramienta> getHerramientasPersonalizadas(Usuario usuario, List<Herramienta> values , String uuidCxn) {
        int i, x;
        try {
            comit = true;
            for (i = 0; i < values.size(); i++) {
                List<HerramientaPersonalizada> herramientaPersonalizadas = getHerramientaPersonalUsuarioHerr(usuario, values.get(i) ,  uuidCxn);
                if (!comit) {
                    break;
                }
                if (herramientaPersonalizadas == null ? true : (herramientaPersonalizadas.isEmpty())) {
                    herramientaPersonalizadas = getHerramientaPersonalPerfilHerr(usuario.getPerfiles(), values.get(i) ,  uuidCxn);
                    if (!comit) {
                        break;
                    }
                }
                for (HerramientaPersonalizada hp : herramientaPersonalizadas) {
                    for (x = 0; x < values.size(); x++) {
                        if (values.get(x).getId() == hp.getHerramienta().getId()) {
                            values.get(x).setNombre(hp.getNombre());
                            values.get(x).setVisible(hp.isVisible());
                            values.get(x).setHabilitado(hp.isHabilitado());
                        }
                    }
                }
            }
        } catch (Exception ex) {
            comit = false;
            System.err.println(concatena.delete(0, concatena.length()).append(msgError).append("getHerramientasPersonalizadas()1_Error: ").append(ex));
            mensajeResultado.setNoError(ControlErroresHibernate.buscaNoErrorPorExcepcion(ex.getClass()));
            mensajeResultado.setError(ex.getLocalizedMessage());
            mensajeResultado.setResultado(null);
        }
        return values;
    }

    private List<HerramientaPersonalizada> getHerramientaPersonalUsuarioHerr(Usuario user, Herramienta herramienta , String uuidCxn) {
        List<HerramientaPersonalizada> values = null;
        try {
            comit = true;
            concatena = new StringBuilder("from HerramientaPersonalizada o ");
            concatena.append(" where usuario = :usuario ");
            concatena.append(" or perfil = :perfil ");
            concatena.append(" and herramienta = :herramienta ");
            q = getSession().createQuery(concatena.toString());
            q.setEntity("usuario", user);
            q.setEntity("perfil", user.getPerfiles());
            q.setEntity("herramienta", herramienta);
            values = q.list();
        } catch (HibernateException ex) {
            comit = false;
            System.err.println(concatena.delete(0, concatena.length()).append(msgError).append("getHerramientaPersonalUsuarioHerr()1_Error: ").append(ex));
            mensajeResultado.setNoError(ControlErroresHibernate.buscaNoErrorPorExcepcion(ex.getClass()));
            mensajeResultado.setError(ex.getLocalizedMessage());
            mensajeResultado.setResultado(null);
        }

        return values;
    }

    private List<HerramientaPersonalizada> getHerramientaPersonalPerfilHerr(Perfiles perfil, Herramienta herramienta , String uuidCxn) {//JSA02
        List<HerramientaPersonalizada> values = null;
        try {
            comit = true;
            concatena = new StringBuilder("from HerramientaPersonalizada o ");
            concatena.append(" where perfil = :perfil ");
            concatena.append(" and herramienta = :herramienta");
            q = getSession().createQuery(concatena.toString());
            q.setEntity("herramienta", herramienta);
            q.setEntity("perfil", perfil);
            values = q.list();
        } catch (HibernateException ex) {
            comit = false;
            System.err.println(concatena.delete(0, concatena.length()).append(msgError).append("getHerramientaPersonalPerfilHerr()1_Error: ").append(ex));
            mensajeResultado.setNoError(ControlErroresHibernate.buscaNoErrorPorExcepcion(ex.getClass()));
            mensajeResultado.setError(ex.getLocalizedMessage());
            mensajeResultado.setResultado(null);
        }
        return values;
    }

    public Mensaje getHerramientasPrincipalesCompartidas(Usuario usuario , String uuidCxn) {//JSA02
        List<Herramienta> values, herramientasConContenedores = new ArrayList<Herramienta>();
        try {
            comit = true;
            inicializaVariableMensaje();
            setSession(HibernateUtil.currentSession(uuidCxn));
            getSession().beginTransaction();
            concatena = new StringBuilder("from Herramienta h ");
            concatena.append(" where principal = true or secundario = true and compartir=true");
            concatena.append(" order by tipoHerramienta ");
            q = getSession().createQuery(concatena.toString());
            values = q.list();
            if (!values.isEmpty()) {
                concatena.delete(0, concatena.length()).append("select count(*) from Contenedor c  where c.herramienta = :herramienta ");
                for (int i = 0; i < values.size(); i++) {
                    q = getSession().createQuery(concatena.toString());
                    q.setParameter("herramienta", values.get(i));
                    Long cantidad = (Long) q.uniqueResult();
                    if (cantidad > 0) {
                        herramientasConContenedores.add(values.get(i));
                    }
                }
                if (usuario != null & !herramientasConContenedores.isEmpty()) {
                    herramientasConContenedores = getHerramientasPersonalizadas(usuario, values ,  uuidCxn);
                }
            }
            if (comit) {
                mensajeResultado.setResultado(herramientasConContenedores);
                mensajeResultado.setNoError(0);
                mensajeResultado.setError("");
                getSession().getTransaction().commit();
            } else {
                getSession().getTransaction().rollback();
            }
        } catch (HibernateException ex) {
            System.err.println(concatena.delete(0, concatena.length()).append(msgError).append("getHerramientasPrincipalesCompartidas()1_Error: ").append(ex));
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

    public Mensaje agregar(Herramienta entity , String uuidCxn) {//JSA02
        Herramienta herramienta = null;
        try {
            inicializaVariableMensaje();
            setSession(HibernateUtil.currentSession(uuidCxn));
            getSession().beginTransaction();
            herramienta = makePersistent(entity);
            mensajeResultado.setResultado(herramienta);
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

    public Mensaje eliminar(Herramienta entity , String uuidCxn) {//JSA02
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

    public Mensaje actualizar(Herramienta entity , String uuidCxn) {//JSA02
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

    public Mensaje SaveHerramientas(List<Herramienta> h , String uuidCxn) {//JSA02
        Transaction tx = null;
        try {
            inicializaVariableMensaje();
            setSession(HibernateUtil.currentSession(uuidCxn));
            tx = getSession().beginTransaction();
            for (int i = 0; i < h.size(); i++) {
                makePersistent(h.get(i));
                if (i % 100 == 0) {
                    flush();
                }
            }
            mensajeResultado.setResultado(true);
            mensajeResultado.setNoError(0);
            mensajeResultado.setError("");
            getSession().getTransaction().commit();
        } catch (HibernateException e) {
            System.err.println(concatena.delete(0, concatena.length()).append(msgError).append("SaveHerramientas()1_Error: ").append(e));
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

    public Mensaje DeleteHerramientas(List<Herramienta> h, String uuidCxn) {//JSA02  
        Transaction tx = null;
        try {
            inicializaVariableMensaje();
            setSession(HibernateUtil.currentSession(uuidCxn));
            tx = getSession().beginTransaction();
            for (Herramienta he : h) {
                makeTransient(he);
            }
            mensajeResultado.setResultado(true);
            mensajeResultado.setNoError(0);
            mensajeResultado.setError("");
            getSession().getTransaction().commit();
        } catch (HibernateException e) {
            System.err.println(concatena.delete(0, concatena.length()).append(msgError).append("DeleteHerramientas()1_Error: ").append(e));
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
