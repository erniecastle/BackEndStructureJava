/**
 * @author: Victor Fecha de Creación: 06/06/2011 Compañía: Macropro Descripción
 * del programa: clase Perfiles para llamados a metodos de HIBERNATE
 * -----------------------------------------------------------------------------
 * MODIFICACIONES:
 * -----------------------------------------------------------------------------
 * Clave: JSA01 Autor: Jose Armando Sanchez Acosta Fecha: 08-07-2011
 * Descripción: Se aplicaron PDM.
 * -----------------------------------------------------------------------------
 * Clave:JSA02 Autor:Jose Armando Sanchez Acosta Fecha:10/11/2011 Descripción:Se
 * cambiaron todos los metodos hibernate al dao
 * -----------------------------------------------------------------------------
 * Clave:JSA03 Autor:Jose Armando Sanchez Acosta Fecha:14/11/2014 Descripción:SE
 * AGREGO EL METODO deletePerfilMenusPermisos PARA CUANDO SE ELIMINA UN PERFIL
 * EL METODO NORMAL ELIMINAR NO FUNCIONA YA QUE TENEMOS QUE BORRAR PRIMERO LOS
 * PERMISOS.
 * -----------------------------------------------------------------------------
 *
 */
package com.mef.erp.modelo;

import com.mef.erp.modelo.entidad.ContenedorPersonalizado;
import com.mef.erp.modelo.entidad.ExternoPersonalizado;
import com.mef.erp.modelo.entidad.HerramientaPersonalizada;
import com.mef.erp.modelo.entidad.Mensaje;
import com.mef.erp.modelo.entidad.Perfiles;
import com.mef.erp.modelo.entidad.Permisos;
import com.mef.erp.util.ControlErroresHibernate;
import com.mef.erp.util.HibernateUtil;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Transaction;

/**
 *
 * @author Jose Armando
 */
public class PerfilDAO extends GenericHibernateDAO<Perfiles, Integer>
        implements PerfilDAOIF {

    private StringBuilder concatena = new StringBuilder(200);
    private final String msgError = concatena.append("ServerERP"/*util.getContexto()*/).append(".MSERR_C_").append(this.getClass().getName()).append(".").toString();

    public Mensaje getPerfilAll(String uuidCxnMaestra) {//JSA02
        List<Perfiles> values;
        try {
            inicializaVariableMensaje();
            setSession(HibernateUtil.currentSession(uuidCxnMaestra));
            getSession().beginTransaction();
            q = getSession().createQuery("from Perfiles");
            values = q.list();
            mensajeResultado.setResultado(values);
            mensajeResultado.setNoError(0);
            mensajeResultado.setError("");
            getSession().getTransaction().commit();
        } catch (HibernateException ex) {
            System.err.println(concatena.delete(0, concatena.length()).append(msgError).append("getPerfilAll()1_Error: ").append(ex));
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

    public Mensaje getPerfilesPorClave(String clave, String uuidCxnMaestra) {//JSA02
        Perfiles perfil;
        try {
            inicializaVariableMensaje();
            setSession(HibernateUtil.currentSession(uuidCxnMaestra));
            getSession().beginTransaction();
            q = getSession().createQuery("from Perfiles where clave = :clave");
            q.setString("clave", clave);
            perfil = (Perfiles) q.uniqueResult();
            mensajeResultado.setResultado(perfil);
            mensajeResultado.setNoError(0);
            mensajeResultado.setError("");
            getSession().getTransaction().commit();
        } catch (HibernateException ex) {
            System.err.println(concatena.delete(0, concatena.length()).append(msgError).append("getPerfilesPorClave()1_Error: ").append(ex));
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

    public Mensaje existeDato(String campo, Object valor, String uuidCxnMaestra) {//JSA02
        Boolean existe;
        try {
            inicializaVariableMensaje();
            setSession(HibernateUtil.currentSession(uuidCxnMaestra));
            getSession().beginTransaction();
            existe = existeDato("Perfiles", campo, valor);
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

    public Mensaje savePerfilMenusPermisos(Perfiles entity, List<Object> menus, List<Permisos> permisos, String uuidCxnMaestra) {
        Perfiles perfil = null;
        Transaction transaction = null;
        try {
            inicializaVariableMensaje();
            setSession(HibernateUtil.currentSession(uuidCxnMaestra));
            transaction = getSession().beginTransaction();
            if (entity.getId() == null) {
                getSession().save(entity);
            } else {
                getSession().saveOrUpdate(entity);
            }

            if (menus != null) {
                Object menu = null;
                int i;
                for (i = 0; i < menus.size(); i++) {
                    if (menus.get(i) instanceof HerramientaPersonalizada) {
                        HerramientaPersonalizada hp = (HerramientaPersonalizada) menus.get(i);
                        if (hp.getPerfil().getId() == null) {
                            hp.setPerfil(entity);
                        }
                        menu = hp;
                    } else if (menus.get(i) instanceof ContenedorPersonalizado) {
                        ContenedorPersonalizado cp = (ContenedorPersonalizado) menus.get(i);
                        if (cp.getPerfil().getId() == null) {
                            cp.setPerfil(entity);
                        }
                        menu = cp;
                    } else if (menus.get(i) instanceof ExternoPersonalizado) {
                        ExternoPersonalizado ep = (ExternoPersonalizado) menus.get(i);
                        if (ep.getPerfil().getId() == null) {
                            ep.setPerfil(entity);
                        }
                        menu = ep;
                    }
                    if (menu != null) {
                        getSession().saveOrUpdate(menu);
                    }
                    if (i % 100 == 0 && i > 0) {
                        flush();
                        clear();
                    }
                }
            }

            if (permisos != null) {
                int i;
                for (i = 0; i < permisos.size(); i++) {
                    if (permisos.get(i).getPerfil().getId() == null) {
                        permisos.get(i).setPerfil(perfil);
                    }
                    getSession().saveOrUpdate(permisos.get(i));
                }
            }
            perfil = entity;
            transaction.commit();
            mensajeResultado.setResultado(perfil);
            mensajeResultado.setNoError(0);
            mensajeResultado.setError("");
        } catch (HibernateException ex) {
            System.err.println(concatena.delete(0, concatena.length()).append(msgError).append("savePerfilMenusPermisos()1_Error: ").append(ex));
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

    public Mensaje deletePerfilMenusPermisos(Perfiles entity, List<Permisos> permisos, String uuidCxnMaestra) {//JSA03
        Transaction transaction = null;
        try {
            inicializaVariableMensaje();
            setSession(HibernateUtil.currentSession(uuidCxnMaestra));
            transaction = getSession().beginTransaction();
            if (permisos != null) {
                int i;
                for (i = 0; i < permisos.size(); i++) {
                    getSession().delete(permisos.get(i));
                }
            }
            makeTransient(entity);
            transaction.commit();
            mensajeResultado.setResultado(true);
            mensajeResultado.setNoError(0);
            mensajeResultado.setError("");
        } catch (HibernateException ex) {
            System.err.println(concatena.delete(0, concatena.length()).append(msgError).append("savePerfilMenusPermisos()1_Error: ").append(ex));
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
            mensajeResultado.setResultado(false);
        }
        return mensajeResultado;
    }

}
