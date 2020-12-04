/**
 * @author: Jose Armando Sanchez Acosta Fecha de Creación: 06/06/2011 Compañía:
 * Macropro Descripción del programa: clase Usuario para llamados a metodos de
 * HIBERNATE
 * -----------------------------------------------------------------------------
 * MODIFICACIONES:
 * -----------------------------------------------------------------------------
 * Clave: JSA01 Autor: Jose Armando Sanchez Acosta Fecha: 25/11/2014
 * Descripción: Se agrego el metodo
 * getAccesoCorrectoConRazonSocialYRazonesSociales para que te regrese el
 * usuario y las razones sociales que tiene asignado. Tambien se agrego el
 * uuidCxnMaestra y el uuidCxn ya que se ocupa la conexion de la razones
 * sociales para el metodo getAccesoCorrectoConRazonSocialYRazonesSociales
 * -----------------------------------------------------------------------------
 */
package com.mef.erp.modelo;

import com.mef.erp.modelo.entidad.ContenedorPersonalizado;
import com.mef.erp.modelo.entidad.ExternoPersonalizado;
import com.mef.erp.modelo.entidad.HerramientaPersonalizada;
import com.mef.erp.modelo.entidad.Mensaje;
import com.mef.erp.modelo.entidad.Permisos;
import com.mef.erp.modelo.entidad.RazonSocial;
import com.mef.erp.modelo.entidad.RazonSocialConfiguracion;
import com.mef.erp.modelo.entidad.RazonesSociales;
import com.mef.erp.modelo.entidad.Restriccion;
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
public class UsuarioDAO extends GenericHibernateDAO<Usuario, Integer>
        implements UsuarioDAOIF {

    private final StringBuilder concatena = new StringBuilder(200);
    private final String msgError = concatena.append("ServerERP"/*
     * util.getContexto()
     */).append(".MSERR_C_").append(this.getClass().getName()).append(".").toString();

    public Mensaje getUsuarioAll(String uuidCxnMaestra) {//JSA02
        List<Usuario> values;
        try {
            inicializaVariableMensaje();
            setSession(HibernateUtil.currentSession(uuidCxnMaestra));
            getSession().beginTransaction();
            q = getSession().createQuery("from Usuario");
            values = q.list();
            mensajeResultado.setResultado(values);
            mensajeResultado.setNoError(0);
            mensajeResultado.setError("");
            getSession().getTransaction().commit();
        } catch (HibernateException ex) {
            System.err.println(concatena.delete(0, concatena.length()).append(msgError).append("getUsuarioAll()1_Error: ").append(ex));
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

    public Mensaje agregar(Usuario entity, String uuidCxnMaestra) {//JSA02
        Usuario usuario;
        try {
            inicializaVariableMensaje();
            setSession(HibernateUtil.currentSession(uuidCxnMaestra));
            getSession().beginTransaction();
            usuario = makePersistent(entity);
            mensajeResultado.setResultado(usuario);
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

    public Mensaje eliminar(Usuario entity, String uuidCxnMaestra) {//JSA02
        try {
            inicializaVariableMensaje();
            setSession(HibernateUtil.currentSession(uuidCxnMaestra));
            getSession().beginTransaction();
            if (entity.getRestricciones() != null) {
                entity.getRestricciones().clear();
            }
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

    public Mensaje actualizar(Usuario entity, String uuidCxnMaestra) {//JSA02
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

    public Mensaje getUsuarioPorClave(String clave, String uuidCxnMaestra) {//JSA02
        Usuario user;
        try {
            inicializaVariableMensaje();
            setSession(HibernateUtil.currentSession(uuidCxnMaestra));
            getSession().beginTransaction();
            q = getSession().createQuery("from Usuario where clave = :clave");
            q.setString("clave", clave);
            user = (Usuario) q.uniqueResult();
            mensajeResultado.setResultado(user);
            mensajeResultado.setNoError(0);
            mensajeResultado.setError("");
            getSession().getTransaction().commit();
        } catch (HibernateException ex) {
            System.err.println(concatena.delete(0, concatena.length()).append(msgError).append("getUsuarioPorClave()1_Error: ").append(ex));
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

    public Mensaje saveUsuariosRestriciones(List<Restriccion> agrega, Usuario entity, List<RazonSocialConfiguracion> listRazonSocial, String uuidCxnMaestra) {//JSA02
        Usuario usuario;
        Transaction transaction = null;
        try {
            inicializaVariableMensaje();
            setSession(HibernateUtil.currentSession(uuidCxnMaestra));
            transaction = getSession().beginTransaction();
            int i;
            for (i = 0; i < agrega.size(); i++) {
                getSession().saveOrUpdate(agrega.get(i));
                agrega.set(i, agrega.get(i));

                if (i % 100 == 0 && i > 0) {
                    flush();
                    clear();
                }
            }
            entity.setRestricciones(agrega);
            getSession().saveOrUpdate(entity);
            if (listRazonSocial == null) {
                listRazonSocial = new ArrayList<RazonSocialConfiguracion>();
            }
            for (i = 0; i < listRazonSocial.size(); i++) {
                listRazonSocial.get(i).setUsuario(entity);
                getSession().saveOrUpdate(listRazonSocial.get(i));
                listRazonSocial.set(i, listRazonSocial.get(i));

                if (i % 100 == 0 && i > 0) {
                    flush();
                    clear();
                }
            }
            usuario = entity;
            transaction.commit();
            mensajeResultado.setResultado(usuario);
            mensajeResultado.setNoError(0);
            mensajeResultado.setError("");
        } catch (HibernateException ex) {
            System.err.println(concatena.delete(0, concatena.length()).append(msgError).append("saveUsuariosRestriciones()1_Error: ").append(ex));
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

    public Mensaje saveUsuariosRestricionesMenus(List<Restriccion> agrega, Usuario entity, List<RazonSocialConfiguracion> listRazonSocial, List<Object> menus, List<Permisos> permisos, String uuidCxnMaestra) {
        Usuario usuario = null;
        Transaction transaction = null;
        try {
            inicializaVariableMensaje();
            setSession(HibernateUtil.currentSession(uuidCxnMaestra));
            transaction = getSession().beginTransaction();
            int i;
            for (i = 0; i < agrega.size(); i++) {
                getSession().saveOrUpdate(agrega.get(i));
                agrega.set(i, agrega.get(i));

                if (i % 100 == 0 && i > 0) {
                    flush();
                    clear();
                }
            }
            entity.setRestricciones(agrega);
            if (entity.getId() == null) {
                getSession().save(entity);
            } else {
                getSession().saveOrUpdate(entity);
            }
            if (listRazonSocial == null) {
                listRazonSocial = new ArrayList<RazonSocialConfiguracion>();
            }
            for (i = 0; i < listRazonSocial.size(); i++) {
                listRazonSocial.get(i).setUsuario(entity);
                getSession().saveOrUpdate(listRazonSocial.get(i));
                listRazonSocial.set(i, listRazonSocial.get(i));

                if (i % 100 == 0 && i > 0) {
                    flush();
                    clear();
                }
            }
            if (menus != null) {
                Object menu = null;
                for (i = 0; i < menus.size(); i++) {
                    if (menus.get(i) instanceof HerramientaPersonalizada) {
                        HerramientaPersonalizada hp = (HerramientaPersonalizada) menus.get(i);
                        hp.setUsuario(entity);
                        menu = hp;
                    } else if (menus.get(i) instanceof ContenedorPersonalizado) {
                        ContenedorPersonalizado hp = (ContenedorPersonalizado) menus.get(i);
                        hp.setUsuario(entity);
                        menu = hp;
                    } else if (menus.get(i) instanceof ExternoPersonalizado) {
                        ExternoPersonalizado hp = (ExternoPersonalizado) menus.get(i);
                        hp.setUsuario(entity);
                        menu = hp;
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
                for (i = 0; i < permisos.size(); i++) {
                    permisos.get(i).setUsuario(entity);
                    getSession().saveOrUpdate(permisos.get(i));
                    if (i % 100 == 0 && i > 0) {
                        flush();
                        clear();
                    }
                }
            }

            usuario = entity;
            transaction.commit();
            mensajeResultado.setResultado(usuario);
            mensajeResultado.setNoError(0);
            mensajeResultado.setError("");
        } catch (HibernateException ex) {
            System.err.println(concatena.delete(0, concatena.length()).append(msgError).append("saveUsuariosRestricionesMenus()1_Error: ").append(ex));
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

    public Mensaje updateUsuariosRestricciones(List<Restriccion> agrega, List<Restriccion> eliminados, Usuario entity, List<RazonSocialConfiguracion> listRazonSocial, String uuidCxnMaestra) {//JSA02
        Transaction transaction = null;
        try {
            inicializaVariableMensaje();
            setSession(HibernateUtil.currentSession(uuidCxnMaestra));
            transaction = getSession().beginTransaction();
            int i;
            if (eliminados != null) {
                for (i = 0; i < eliminados.size(); i++) {
                    getSession().delete(eliminados.get(i));
                    if (i % 100 == 0 && i > 0) {
                        flush();
                        clear();
                    }
                }
                for (Restriccion r : eliminados) {
                    entity.getRestricciones().remove(r);
                }
            }
            if (agrega != null) {
                for (i = 0; i < agrega.size(); i++) {
                    getSession().saveOrUpdate(agrega.get(i));
                    agrega.set(i, agrega.get(i));

                    if (i % 100 == 0 && i > 0) {
                        flush();
                        clear();
                    }
                }
                if (!agrega.isEmpty()) {
                    entity.setRestricciones(agrega);
                }
            }

            getSession().saveOrUpdate(entity);
            for (i = 0; i < listRazonSocial.size(); i++) {
                listRazonSocial.get(i).setUsuario(entity);
                getSession().saveOrUpdate(listRazonSocial.get(i));
                listRazonSocial.set(i, listRazonSocial.get(i));

                if (i % 100 == 0 && i > 0) {
                    flush();
                    clear();
                }
            }
            transaction.commit();
            mensajeResultado.setResultado(true);
            mensajeResultado.setNoError(0);
            mensajeResultado.setError("");
        } catch (HibernateException ex) {
            System.err.println(concatena.delete(0, concatena.length()).append(msgError).append("updateUsuariosRestricciones()1_Error: ").append(ex));
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

    public Mensaje updateUsuariosRestriccionesMenus(List<Restriccion> agrega, List<Restriccion> eliminados, Usuario entity, List<RazonSocialConfiguracion> listRazonSocial, List<Object> menus, List<Permisos> permisos, String uuidCxnMaestra) {
        Transaction transaction = null;
        try {
            inicializaVariableMensaje();
            setSession(HibernateUtil.currentSession(uuidCxnMaestra));
            transaction = getSession().beginTransaction();
            int i;
            if (eliminados != null) {
                for (i = 0; i < eliminados.size(); i++) {
                    getSession().delete(eliminados.get(i));
                    if (i % 100 == 0 && i > 0) {
                        flush();
                        clear();
                    }
                }
                for (Restriccion r : eliminados) {
                    entity.getRestricciones().remove(r);
                }
            }
            if (agrega != null) {
                for (i = 0; i < agrega.size(); i++) {
                    getSession().saveOrUpdate(agrega.get(i));
                    agrega.set(i, agrega.get(i));

                    if (i % 100 == 0 && i > 0) {
                        flush();
                        clear();
                    }
                }
                if (!agrega.isEmpty()) {
                    entity.setRestricciones(agrega);
                }
            }

            getSession().saveOrUpdate(entity);
            for (i = 0; i < listRazonSocial.size(); i++) {
                listRazonSocial.get(i).setUsuario(entity);
                getSession().saveOrUpdate(listRazonSocial.get(i));
                listRazonSocial.set(i, listRazonSocial.get(i));

                if (i % 100 == 0 && i > 0) {
                    flush();
                    clear();
                }
            }

            if (menus != null) {
                for (i = 0; i < menus.size(); i++) {
                    getSession().saveOrUpdate(menus.get(i));
                    if (i % 100 == 0 && i > 0) {
                        flush();
                        clear();
                    }
                }
            }

            if (permisos != null) {
                for (i = 0; i < permisos.size(); i++) {
                    getSession().saveOrUpdate(permisos.get(i));
                    if (i % 100 == 0 && i > 0) {
                        flush();
                        clear();
                    }
                }
            }

            transaction.commit();
            mensajeResultado.setResultado(true);
            mensajeResultado.setNoError(0);
            mensajeResultado.setError("");
        } catch (HibernateException ex) {
            System.err.println(concatena.delete(0, concatena.length()).append(msgError).append("updateUsuariosRestriccionesMenus()1_Error: ").append(ex));
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

    public Mensaje deleteUsuarioRestricciones(Usuario entity, String uuidCxnMaestra) {//JSA02
        Transaction transaction = null;
        try {
            inicializaVariableMensaje();
            setSession(HibernateUtil.currentSession(uuidCxnMaestra));
            transaction = getSession().beginTransaction();
            if (entity.getRestricciones() == null) {
                getSession().delete(entity);
            } else if (entity.getRestricciones().isEmpty()) {
                getSession().delete(entity);
            } else {
                //borra restriciiones que no tenga usuarios
                for (Restriccion r : entity.getRestricciones()) {
                    getSession().delete(r);
                }
                getSession().delete(entity);
            }
            transaction.commit();
            mensajeResultado.setResultado(true);
            mensajeResultado.setNoError(0);
            mensajeResultado.setError("");
        } catch (HibernateException ex) {
            System.err.println(concatena.delete(0, concatena.length()).append(msgError).append("deleteUsuarioRestricciones()1_Error: ").append(ex));
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

    public Mensaje getAccesoCorrecto(String apodo, String password, String uuidCxnMaestra) {//JSA02
        Usuario u;
        try {
            // Session s =  
            inicializaVariableMensaje();
            setSession(HibernateUtil.currentSession(uuidCxnMaestra));
            // setSession(HibernateUtil.currentSession(uuidCxn));
            getSession().beginTransaction();
            q = getSession().createQuery("from Usuario u where u.nombre = :nombre and u.password = :password");
            q.setString("nombre", apodo);
            q.setString("password", password);
            u = (Usuario) q.uniqueResult();
            mensajeResultado.setResultado(u);
            mensajeResultado.setNoError(0);
            mensajeResultado.setError("");
            getSession().getTransaction().commit();
        } catch (HibernateException ex) {
            System.err.println(concatena.delete(0, concatena.length()).append(msgError).append("getAccesoCorrecto()1_Error: ").append(ex));
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

    public Mensaje getAccesoCorrectoConRazonSocialYRazonesSociales(String apodo, String password, String uuidCxn, String uuidCxnMaestra) {//JSA01
        try {
            Usuario usuario;
            List<RazonSocial> listRazonSocial;
            List<RazonesSociales> listRazonesSociales = null;
            List<Object> listObject;
            // Session s =  
            inicializaVariableMensaje();
            setSession(HibernateUtil.currentSession(uuidCxnMaestra));
            // setSession(HibernateUtil.currentSession(uuidCxn));
            getSession().beginTransaction();
            q = getSession().createQuery("from Usuario u where u.nombre = :nombre and u.password = :password");
            q.setString("nombre", apodo);
            q.setString("password", password);
            usuario = (Usuario) q.uniqueResult();
            if (usuario != null) {
                q = getSession().createQuery("select distinct ra.razonSocial from RazonSocialConfiguracion ra where ra.usuario.clave =:clave and ra.permitido = true ");
                q.setParameter("clave", usuario.getClave());
                listRazonSocial = q.list();
                if (listRazonSocial == null) {
                    listRazonSocial = new ArrayList<RazonSocial>();
                }
                String[] listClaveRazonesSociales;
                if (listRazonSocial.isEmpty()) {
                    if (usuario.getPerfiles().getNivelAccesoSistema() == 0) {
                        q = getSession().createQuery("from RazonSocial");
                        listRazonSocial = q.list();
                        if (listRazonSocial == null) {
                            listRazonSocial = new ArrayList<RazonSocial>();
                        }
                    }
                }

                getSession().getTransaction().commit();
                listClaveRazonesSociales = new String[listRazonSocial.size()];
                int i;
                for (i = 0; i < listRazonSocial.size(); i++) {
                    listClaveRazonesSociales[i] = listRazonSocial.get(i).getClaveRazonSocial();
                }
                if (listClaveRazonesSociales.length > 0) {
                    setSession(HibernateUtil.currentSession(uuidCxn));
                    getSession().beginTransaction();
                    q = getSession().createQuery("from RazonesSociales r where r.clave in (:claves)");
                    q.setParameterList("claves", listClaveRazonesSociales);
                    listRazonesSociales = q.list();
                    getSession().getTransaction().commit();
                    listObject = new ArrayList<Object>();
                    listObject.add(usuario);
                    listObject.add(listRazonesSociales);
                    listObject.add(listRazonSocial);
                } else {
                    listObject = new ArrayList<Object>();
                    listObject.add(usuario);
                }
                mensajeResultado.setResultado(listObject);
                mensajeResultado.setNoError(0);
                mensajeResultado.setError("");
            }
        } catch (HibernateException ex) {
            System.err.println(concatena.delete(0, concatena.length()).append(msgError).append("getAccesoCorrecto()1_Error: ").append(ex));
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
