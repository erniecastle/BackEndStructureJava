/**
 * @author: Fecha de Creación: --/--/---- Compañía: Descripción del programa:
 * -----------------------------------------------------------------------------
 * MODIFICACIONES:
 * -----------------------------------------------------------------------------
 * Clave:JSA01 Autor:14/11/2014 Fecha: SE AGREGO UNA VALIDACION EN EL METODO
 * buscaPorTipoAccionesYidMultiUsos PARA CUANDO LA CLAVE DE LA RAZON SOCIAL
 * VENGA NULL.
 * -----------------------------------------------------------------------------
 * Clave: Autor: Fecha:Descripción:
 * -----------------------------------------------------------------------------
 */
package com.mef.erp.modelo;

import com.mef.erp.modelo.entidad.Contenedor;
import com.mef.erp.modelo.entidad.ContenedorPersonalizado;
import com.mef.erp.modelo.entidad.ElementoExterno;
import com.mef.erp.modelo.entidad.ExternoPersonalizado;
import com.mef.erp.modelo.entidad.Herramienta;
import com.mef.erp.modelo.entidad.Mensaje;
import com.mef.erp.modelo.entidad.Perfiles;
import com.mef.erp.modelo.entidad.RazonSocial;
import com.mef.erp.modelo.entidad.TipoAcciones;
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
public class ContenedorDAO extends GenericHibernateDAO<Contenedor, Integer>
        implements ContenedorDAOIF {

    private StringBuilder Concatena = new StringBuilder(200);
    private final String msgError = Concatena.append("ServerERP").append(".MSERR_C_").append(this.getClass().getName()).append(".").toString();
    StringBuilder stringBuilder = new StringBuilder();

    public Mensaje getContenedor(int parentId, Herramienta herramienta, List<String> ventanasAOmitir, String uuidCxn) {
        List<Contenedor> values;
        try {
            inicializaVariableMensaje();
            setSession(HibernateUtil.currentSession(uuidCxn));
            getSession().beginTransaction();
            stringBuilder.delete(0, stringBuilder.length()).append("select c from Contenedor c  left outer join  c.ventana  v where c.parentId = :parentId and c.herramienta = :herramienta ");
            if (ventanasAOmitir == null) {
                ventanasAOmitir = new ArrayList<String>();
            }
            if (!ventanasAOmitir.isEmpty()) {
                stringBuilder.append(" and (v is null or (  ");
                for (int i = 0; i < ventanasAOmitir.size(); i++) {
                    stringBuilder.append(" v.nombre not like  '").append(ventanasAOmitir.get(i)).append("' ");
                    if (i < ventanasAOmitir.size() - 1) {
                        stringBuilder.append(" and ");
                    }
                }
                stringBuilder.append(" )) ");
            }
            stringBuilder.append(" order by ordenId asc");
            q = getSession().createQuery(stringBuilder.toString());
            q.setParameter("parentId", parentId);
            q.setParameter("herramienta", herramienta);
            values = q.list();
            mensajeResultado.setResultado(values);
            mensajeResultado.setNoError(0);
            mensajeResultado.setError("");
            getSession().getTransaction().commit();
        } catch (HibernateException ex) {
            System.err.println(builder.delete(0, builder.length()).append(msgError).append("getContenedor()1_Error: ").append(ex));
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

    public Mensaje getContenedorAll(List<String> ventanasAOmitir, String uuidCxn) {
        List<Contenedor> values;
        try {
            inicializaVariableMensaje();
            setSession(HibernateUtil.currentSession(uuidCxn));
            getSession().beginTransaction();
            stringBuilder.delete(0, stringBuilder.length()).append("select c from Contenedor c left outer join  c.ventana  v ");
            if (ventanasAOmitir == null) {
                ventanasAOmitir = new ArrayList<String>();
            }
            if (!ventanasAOmitir.isEmpty()) {
                stringBuilder.append(" where (v is null or (  ");
                for (int i = 0; i < ventanasAOmitir.size(); i++) {
                    stringBuilder.append(" v.nombre not like  '").append(ventanasAOmitir.get(i)).append("' ");
                    if (i < ventanasAOmitir.size() - 1) {
                        stringBuilder.append(" and ");
                    }
                }
                stringBuilder.append(" )) ");
            }
            q = getSession().createQuery(stringBuilder.toString());
            values = q.list();
            mensajeResultado.setResultado(values);
            mensajeResultado.setNoError(0);
            mensajeResultado.setError("");
            getSession().getTransaction().commit();
        } catch (HibernateException ex) {
            System.err.println(builder.delete(0, builder.length()).append(msgError).append("getContenedorAll()1_Error: ").append(ex));
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

    public Mensaje getContenedorHert(Herramienta herramienta, List<String> ventanasAOmitir, Usuario usuario, RazonSocial razonSocial, String uuidCxn) {
        List<Contenedor> values;
        try {
            inicializaVariableMensaje();
            setSession(HibernateUtil.currentSession(uuidCxn));
            getSession().beginTransaction();
            stringBuilder.delete(0, stringBuilder.length()).append("select new Contenedor(");
            stringBuilder.append("c.id, c.herramienta.id,c.herramienta.principal,");
            stringBuilder.append("c.herramienta.visible,c.herramienta.tipoHerramienta.id,c.herramienta.tipoHerramienta.nombre,c.nombre,");
            stringBuilder.append("c.accion,c.tipoAcciones,c.keyCode,c.modifiers,c.parentId,c.ordenId,t,c.icono,c.tipoIcono,c.habilitado,");
            stringBuilder.append("c.visible, c.ventana,c.idMultiUsos,r.claveRazonSocial )");
            stringBuilder.append("from Contenedor c  left outer join  c.ventana  v left outer join c.razonSocial r inner join c.tipoElemento t where c.herramienta = :herramienta and   r is  null ");
            if (ventanasAOmitir == null) {
                ventanasAOmitir = new ArrayList<String>();
            }
            if (!ventanasAOmitir.isEmpty()) {
                stringBuilder.append(" and (v is null or (  ");
                for (int i = 0; i < ventanasAOmitir.size(); i++) {
                    stringBuilder.append(" v.nombre not like  '").append(ventanasAOmitir.get(i)).append("' ");
                    if (i < ventanasAOmitir.size() - 1) {
                        stringBuilder.append(" and ");
                    }
                }
                stringBuilder.append(" )) ");
            }
            stringBuilder.append(" order by parentId, ordenId asc");
            q = getSession().createQuery(stringBuilder.toString());
            q.setParameter("herramienta", herramienta);
            values = q.list();
            if (usuario != null & values != null) {
                values = getObtenerContenedoresPersonalizados(values, usuario, herramienta);
            }
            if (mensajeResultado.getNoError() != 0) {
                try {
                    if (getSession().getTransaction().isActive()) {
                        getSession().getTransaction().rollback();
                    }
                } catch (HibernateException exc) { //cuando marca error al hacer rollback pro ejemplo se cae conexion servidor
                    mensajeResultado.setNoError(ControlErroresHibernate.buscaNoErrorPorExcepcion(exc.getClass()));
                    mensajeResultado.setError(exc.getLocalizedMessage());
                    mensajeResultado.setResultado(null);
                }
                return mensajeResultado;
            }
            if (values != null & razonSocial != null) {
                stringBuilder.delete(0, stringBuilder.length()).append("select new Contenedor(");
                stringBuilder.append("c.id, c.herramienta.id,c.herramienta.principal,");
                stringBuilder.append("c.herramienta.visible,c.herramienta.tipoHerramienta.id,c.herramienta.tipoHerramienta.nombre,c.nombre,");
                stringBuilder.append("c.accion,c.tipoAcciones,c.keyCode,c.modifiers,c.parentId,c.ordenId,t,c.icono,c.tipoIcono,c.habilitado,");
                stringBuilder.append("c.visible, c.ventana,c.idMultiUsos,r.claveRazonSocial )");
                stringBuilder.append("from Contenedor c  left outer join  c.ventana  v left outer join c.razonSocial r inner join c.tipoElemento t where c.herramienta = :herramienta");
                stringBuilder.append(" and   r.claveRazonSocial = :clave ");
                if (ventanasAOmitir == null) {
                    ventanasAOmitir = new ArrayList<String>();
                }
                if (!ventanasAOmitir.isEmpty()) {
                    stringBuilder.append(" and (v is null or (  ");
                    for (int i = 0; i < ventanasAOmitir.size(); i++) {
                        stringBuilder.append(" v.nombre not like  '").append(ventanasAOmitir.get(i)).append("' ");
                        if (i < ventanasAOmitir.size() - 1) {
                            stringBuilder.append(" and ");
                        }
                    }
                    stringBuilder.append(" )) ");
                }
                stringBuilder.append(" order by parentId, ordenId asc");
                q = getSession().createQuery(stringBuilder.toString());
                q.setParameter("herramienta", herramienta);
                q.setParameter("clave", razonSocial.getClaveRazonSocial());
                values.addAll(q.list());
            }
            mensajeResultado.setResultado(values);
            mensajeResultado.setNoError(0);
            mensajeResultado.setError("");
            getSession().getTransaction().commit();
        } catch (HibernateException ex) {
            System.err.println(builder.delete(0, builder.length()).append(msgError).append("getContenedorHert()1_Error: ").append(ex));
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

    private List<Contenedor> getObtenerContenedoresPersonalizados(List<Contenedor> values, Usuario usuario, Herramienta herramienta) {
        try {
            List<ExternoPersonalizado> externoPersonalizados = getExternoPersonalUsuarioHerr(usuario, herramienta);
            if (mensajeResultado.getNoError() != 0) {
                return null;
            }
            if (externoPersonalizados == null ? true : (externoPersonalizados.isEmpty() ? true : false)) {
                externoPersonalizados = getExternoPersonalPerfilHerr(usuario.getPerfiles(), herramienta);
                if (mensajeResultado.getNoError() != 0) {
                    return null;
                }
            }

            List<ContenedorPersonalizado> contenedorPersonalizados;
            contenedorPersonalizados = getContenedorPersonalUsuarioHerr(usuario, herramienta);
            if (mensajeResultado.getNoError() != 0) {
                return null;
            }
            if (contenedorPersonalizados == null ? true : (contenedorPersonalizados.isEmpty() ? true : false)) {
                contenedorPersonalizados = getContenedorPersonalPerfilHerr(usuario.getPerfiles(), herramienta);
                if (mensajeResultado.getNoError() != 0) {
                    return null;
                }
            }
            List<ElementoExterno> externos = getElementoExAll();
            int i;
            for (i = 0; i < values.size(); i++) {
                for (ElementoExterno externo : externos) {
                    if (values.get(i).getId().equals(externo.getContenedor().getId())) {
                        values.get(i).setExterno(externo.getUbicacion());
                    }
                }
                for (ExternoPersonalizado externoPersonalizado : externoPersonalizados) {
                    if (values.get(i).getId().equals(externoPersonalizado.getContenedor().getId())) {
                        values.get(i).setExterno(externoPersonalizado.getUbicacion());
                    }
                }
                for (ContenedorPersonalizado cp : contenedorPersonalizados) {
                    if (values.get(i).getId().equals(cp.getContenedor().getId())) {
                        values.get(i).setNombre(cp.getNombre());
                        values.get(i).setVisible(cp.isVisible());
                        values.get(i).setHabilitado(cp.isHabilitado());
                        values.get(i).setKeyCode(cp.getKeyCode());
                        values.get(i).setModifiers(cp.getModifiers());
                        values.get(i).setOrdenId(cp.getOrdenId());
                        values.get(i).setIcono(cp.getIcono());
                        values.get(i).setTipoIcono(cp.getTipoIcono());
                    }
                }
            }
        } catch (Exception ex) {
            System.err.println(builder.delete(0, builder.length()).append(msgError).append("getObtenerContenedoresPersonalizados()1_Error: ").append(ex));
            mensajeResultado.setNoError(ControlErroresHibernate.buscaNoErrorPorExcepcion(ex.getClass()));
            mensajeResultado.setError(ex.getLocalizedMessage());
            mensajeResultado.setResultado(null);
        }
        return values;
    }

    private List<ElementoExterno> getElementoExAll() {
        List<ElementoExterno> values = new ArrayList<ElementoExterno>();
        try {
            q = getSession().createQuery("from ElementoExterno");
            values = q.list();
        } catch (HibernateException ex) {
            System.err.println(builder.delete(0, builder.length()).append(msgError).append("getElementoExAll()1_Error: ").append(ex));
            mensajeResultado.setNoError(ControlErroresHibernate.buscaNoErrorPorExcepcion(ex.getClass()));
            mensajeResultado.setError(ex.getLocalizedMessage());
            mensajeResultado.setResultado(null);
        }
        return values;
    }

    private List<ContenedorPersonalizado> getContenedorPersonalUsuarioHerr(Usuario user, Herramienta herramienta) {
        List<ContenedorPersonalizado> values = new ArrayList<ContenedorPersonalizado>();
        try {
            builder = new StringBuilder("from ContenedorPersonalizado o ");
            builder.append(" where usuario = :usuario ").append(" or perfil = :perfil ");
            builder.append(" and herramienta = :herramienta ");
            q = getSession().createQuery(builder.toString());
            q.setEntity("usuario", user);
            q.setEntity("perfil", user.getPerfiles());
            q.setEntity("herramienta", herramienta);
            values = q.list();
        } catch (HibernateException ex) {
            System.err.println(builder.delete(0, builder.length()).append(msgError).append("getContenedorPersonalUsuarioHerr()1_Error: ").append(ex));
            mensajeResultado.setNoError(ControlErroresHibernate.buscaNoErrorPorExcepcion(ex.getClass()));
            mensajeResultado.setError(ex.getLocalizedMessage());
            mensajeResultado.setResultado(null);
        }

        return values;
    }

    private List<ContenedorPersonalizado> getContenedorPersonalPerfilHerr(Perfiles perfil, Herramienta herramienta) {
        List<ContenedorPersonalizado> values = new ArrayList<ContenedorPersonalizado>();
        try {
            builder = new StringBuilder("from ContenedorPersonalizado o ");
            builder.append(" where perfil = :perfil ").append(" and herramienta = :herramienta ");
            q = getSession().createQuery(builder.toString());
            q.setEntity("herramienta", herramienta);
            q.setEntity("perfil", perfil);
            values = q.list();
        } catch (HibernateException ex) {
            System.err.println(builder.delete(0, builder.length()).append(msgError).append("getContenedorPersonalPerfilHerr()1_Error: ").append(ex));
            mensajeResultado.setNoError(ControlErroresHibernate.buscaNoErrorPorExcepcion(ex.getClass()));
            mensajeResultado.setError(ex.getLocalizedMessage());
            mensajeResultado.setResultado(null);
        }

        return values;
    }

    private List<ExternoPersonalizado> getExternoPersonalUsuarioHerr(Usuario user, Herramienta herramienta) {//JSA01
        List<ExternoPersonalizado> values = new ArrayList<ExternoPersonalizado>();
        try {
            builder.delete(0, builder.length()).append("from ExternoPersonalizado o ");
            builder.append(" where usuario = :usuario ");
            builder.append(" or perfil = :perfil ");
            builder.append(" and herramienta = :herramienta ");
            q = getSession().createQuery(builder.toString());
            q.setEntity("usuario", user);
            q.setEntity("perfil", user.getPerfiles());
            q.setEntity("herramienta", herramienta);
            values = q.list();
        } catch (HibernateException ex) {
            System.err.println(builder.delete(0, builder.length()).append(msgError).append("getExternoPersonalUsuarioHerr()1_Error: ").append(ex));
            mensajeResultado.setNoError(ControlErroresHibernate.buscaNoErrorPorExcepcion(ex.getClass()));
            mensajeResultado.setError(ex.getLocalizedMessage());
            mensajeResultado.setResultado(null);
        }

        return values;
    }

    private List<ExternoPersonalizado> getExternoPersonalPerfilHerr(Perfiles perfil, Herramienta herramienta) {//JSA01
        List<ExternoPersonalizado> values = new ArrayList<ExternoPersonalizado>();
        try {
            builder.delete(0, builder.length()).append("from ExternoPersonalizado o ");
            builder.append(" where perfil = :perfil ");
            builder.append(" and herramienta = :herramienta ");
            q = getSession().createQuery(builder.toString());
            q.setEntity("herramienta", herramienta);
            q.setEntity("perfil", perfil);
            values = q.list();
        } catch (HibernateException ex) {
            System.err.println(builder.delete(0, builder.length()).append(msgError).append("getExternoPersonalPerfilHerr()1_Error: ").append(ex));
            mensajeResultado.setNoError(ControlErroresHibernate.buscaNoErrorPorExcepcion(ex.getClass()));
            mensajeResultado.setError(ex.getLocalizedMessage());
            mensajeResultado.setResultado(null);
        }
        return values;
    }

    public Mensaje getContenedorHertCompartida(Herramienta herramienta, List<String> ventanasAOmitir, Usuario usuario, RazonSocial razonSocial, String uuidCxn) {
        List<Contenedor> values;
        try {
            inicializaVariableMensaje();
            setSession(HibernateUtil.currentSession(uuidCxn));
            getSession().beginTransaction();
            stringBuilder.delete(0, stringBuilder.length()).append("select new Contenedor(");
            stringBuilder.append("c.id, c.herramienta.id,c.herramienta.principal,");
            stringBuilder.append("c.herramienta.visible,c.herramienta.tipoHerramienta.id,c.herramienta.tipoHerramienta.nombre,c.nombre,");
            stringBuilder.append("c.accion,c.tipoAcciones,c.keyCode,c.modifiers,c.parentId,c.ordenId,t,c.icono,c.tipoIcono,c.habilitado,");
            stringBuilder.append("c.visible, c.ventana,c.idMultiUsos,r.claveRazonSocial )");
            stringBuilder.append("from Contenedor c  left outer join  c.ventana  v left outer join c.razonSocial r inner join c.tipoElemento t where c.herramienta = :herramienta and c.compartir=true  and   r is  null ");
            if (ventanasAOmitir == null) {
                ventanasAOmitir = new ArrayList<String>();
            }
            if (!ventanasAOmitir.isEmpty()) {
                stringBuilder.append(" and (v is null or (  ");
                for (int i = 0; i < ventanasAOmitir.size(); i++) {
                    stringBuilder.append(" v.nombre not like  '").append(ventanasAOmitir.get(i)).append("' ");
                    if (i < ventanasAOmitir.size() - 1) {
                        stringBuilder.append(" and ");
                    }
                }
                stringBuilder.append(" )) ");
            }
            stringBuilder.append(" order by parentId, ordenId asc");
            q = getSession().createQuery(stringBuilder.toString());
            q.setParameter("herramienta", herramienta);
            values = q.list();
            if (usuario != null & values != null) {
                values = getObtenerContenedoresPersonalizados(values, usuario, herramienta);
            }
            if (mensajeResultado.getNoError() != 0) {
                try {
                    if (getSession().getTransaction().isActive()) {
                        getSession().getTransaction().rollback();
                    }
                } catch (HibernateException exc) { //cuando marca error al hacer rollback pro ejemplo se cae conexion servidor
                    mensajeResultado.setNoError(ControlErroresHibernate.buscaNoErrorPorExcepcion(exc.getClass()));
                    mensajeResultado.setError(exc.getLocalizedMessage());
                    mensajeResultado.setResultado(null);
                }
                return mensajeResultado;
            }
            if (values != null & razonSocial != null) {
                stringBuilder.delete(0, stringBuilder.length()).append("select new Contenedor(");
                stringBuilder.append("c.id, c.herramienta.id,c.herramienta.principal,");
                stringBuilder.append("c.herramienta.visible,c.herramienta.tipoHerramienta.id,c.herramienta.tipoHerramienta.nombre,c.nombre,");
                stringBuilder.append("c.accion,c.tipoAcciones,c.keyCode,c.modifiers,c.parentId,c.ordenId,t,c.icono,c.tipoIcono,c.habilitado,");
                stringBuilder.append("c.visible, c.ventana,c.idMultiUsos,r.claveRazonSocial )");
                stringBuilder.append("from Contenedor c  left outer join  c.ventana  v left outer join c.razonSocial r inner join c.tipoElemento t where c.herramienta = :herramienta and c.compartir=true");
                stringBuilder.append("  and   r.claveRazonSocial = :clave ");
                if (ventanasAOmitir == null) {
                    ventanasAOmitir = new ArrayList<String>();
                }
                if (!ventanasAOmitir.isEmpty()) {
                    stringBuilder.append(" and (v is null or (  ");
                    for (int i = 0; i < ventanasAOmitir.size(); i++) {
                        stringBuilder.append(" v.nombre not like  '").append(ventanasAOmitir.get(i)).append("' ");
                        if (i < ventanasAOmitir.size() - 1) {
                            stringBuilder.append(" and ");
                        }
                    }
                    stringBuilder.append(" )) ");
                }
                stringBuilder.append(" order by parentId, ordenId asc");
                q = getSession().createQuery(stringBuilder.toString());
                q.setParameter("herramienta", herramienta);
                q.setParameter("clave", razonSocial.getClaveRazonSocial());
                values.addAll(q.list());
            }
            mensajeResultado.setResultado(values);
            mensajeResultado.setNoError(0);
            mensajeResultado.setError("");
            getSession().getTransaction().commit();
        } catch (HibernateException ex) {
            System.err.println(builder.delete(0, builder.length()).append(msgError).append("getContenedorHertCompartida()1_Error: ").append(ex));
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

    public Mensaje getContenedorPorNombreVentana(String nombreVentana, String uuidCxn) {
        List<Contenedor> values;
        try {
            inicializaVariableMensaje();
            setSession(HibernateUtil.currentSession(uuidCxn));
            getSession().beginTransaction();
            stringBuilder.delete(0, stringBuilder.length()).append("from Contenedor c where c.ventana.nombre = :nombreVentana and c.tipoAcciones = :tipoAccion");
            q = getSession().createQuery(stringBuilder.toString());
            q.setParameter("nombreVentana", nombreVentana);
            q.setParameter("tipoAccion", TipoAcciones.VENTANA);
            values = q.list();
            mensajeResultado.setResultado(values);
            mensajeResultado.setNoError(0);
            mensajeResultado.setError("");
            getSession().getTransaction().commit();
        } catch (HibernateException ex) {
            System.err.println(builder.delete(0, builder.length()).append(msgError).append("getContenedorPorNombreVentana()1_Error: ").append(ex));
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

    public Mensaje getContenedorMax(String uuidCxn) {
        Integer values = 1;
        try {
            inicializaVariableMensaje();
            setSession(HibernateUtil.currentSession(uuidCxn));
            getSession().beginTransaction();
            q = getSession().createQuery("select max(id+1) as max from Contenedor c");
            values = (Integer) q.uniqueResult();
            mensajeResultado.setResultado(values);
            mensajeResultado.setNoError(0);
            mensajeResultado.setError("");
            getSession().getTransaction().commit();
        } catch (HibernateException ex) {
            System.err.println(builder.delete(0, builder.length()).append(msgError).append("getContenedorMax()1_Error: ").append(ex));
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

    public Mensaje agregar(Contenedor entity, String uuidCxn) {
        Contenedor contenedor;
        try {
            inicializaVariableMensaje();
            setSession(HibernateUtil.currentSession(uuidCxn));
            getSession().beginTransaction();
            contenedor = makePersistent(entity);
            mensajeResultado.setResultado(contenedor);
            mensajeResultado.setNoError(0);
            mensajeResultado.setError("");
            getSession().getTransaction().commit();
        } catch (HibernateException ex) {
            System.err.println(builder.delete(0, builder.length()).append(msgError).append("agregar()1_Error: ").append(ex));
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

    public Mensaje eliminar(List<Contenedor> entity, String uuidCxn) {
        try {
            inicializaVariableMensaje();
            setSession(HibernateUtil.currentSession(uuidCxn));
            getSession().beginTransaction();
            for (int i = 0; i < entity.size(); i++) {
                makeTransient(entity.get(i));
            }
            mensajeResultado.setResultado(true);
            mensajeResultado.setNoError(0);
            mensajeResultado.setError("");
            getSession().getTransaction().commit();
        } catch (HibernateException ex) {
            System.err.println(builder.delete(0, builder.length()).append(msgError).append("eliminar()1_Error: ").append(ex));
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

    public Mensaje actualizar(Contenedor entity, String uuidCxn) {
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
            System.err.println(builder.delete(0, builder.length()).append(msgError).append("actualizar()1_Error: ").append(ex));
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

    public Mensaje getContenedorPorId(Integer id, String uuidCxn) {
        Contenedor c;
        try {
            inicializaVariableMensaje();
            setSession(HibernateUtil.currentSession(uuidCxn));
            getSession().beginTransaction();
            c = findById(id);
            mensajeResultado.setResultado(c);
            mensajeResultado.setNoError(0);
            mensajeResultado.setError("");
            getSession().getTransaction().commit();
        } catch (HibernateException ex) {
            System.err.println(builder.delete(0, builder.length()).append(msgError).append("getContenedorPorId()1_Error: ").append(ex));
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

    public Mensaje getContenedorOrder(List<String> ventanasAOmitir, String uuidCxn) {
        List<Contenedor> values;
        try {
            inicializaVariableMensaje();
            setSession(HibernateUtil.currentSession(uuidCxn));
            getSession().beginTransaction();
            stringBuilder.delete(0, stringBuilder.length()).append("select c from Contenedor c left outer join  c.ventana  v ");
            if (ventanasAOmitir == null) {
                ventanasAOmitir = new ArrayList<String>();
            }
            if (!ventanasAOmitir.isEmpty()) {
                stringBuilder.append(" where (v is null or (  ");
                for (int i = 0; i < ventanasAOmitir.size(); i++) {
                    stringBuilder.append(" v.nombre not like  '").append(ventanasAOmitir.get(i)).append("' ");
                    if (i < ventanasAOmitir.size() - 1) {
                        stringBuilder.append(" and ");
                    }
                }
                stringBuilder.append(" )) ");
            }
            stringBuilder.append("   order by ordenId asc");
            q = getSession().createQuery(stringBuilder.toString());
            values = q.list();
            mensajeResultado.setResultado(values);
            mensajeResultado.setNoError(0);
            mensajeResultado.setError("");
            getSession().getTransaction().commit();
        } catch (HibernateException ex) {
            System.err.println(builder.delete(0, builder.length()).append(msgError).append("getContenedorOrder()1_Error: ").append(ex));
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

    public Mensaje SaveContenedor(List<Contenedor> c, String uuidCxn) {
        Transaction tx = null;
        try {
            inicializaVariableMensaje();
            setSession(HibernateUtil.currentSession(uuidCxn));
            tx = getSession().beginTransaction();
            for (int i = 0; i < c.size(); i++) {
                makePersistent(c.get(i));
                if (i % 100 == 0) {
                    flush();
                }
            }
            mensajeResultado.setResultado(true);
            mensajeResultado.setNoError(0);
            mensajeResultado.setError("");
            getSession().getTransaction().commit();
        } catch (HibernateException e) {
            System.err.println(Concatena.delete(0, Concatena.length()).append(msgError).append("SaveContenedor()1_Error: ").append(e));
            try {
                if (tx != null) {
                    tx.rollback();
                }
                mensajeResultado.setNoError(ControlErroresHibernate.buscaNoErrorPorExcepcion(e.getClass()));
                mensajeResultado.setError(e.getLocalizedMessage());
            } catch (HibernateException exc) { //cuando marca error al hacer rollback pro ejemplo se cae conexion servidor
                mensajeResultado.setNoError(ControlErroresHibernate.buscaNoErrorPorExcepcion(exc.getClass()));
                mensajeResultado.setError(exc.getLocalizedMessage());
            }
            mensajeResultado.setResultado(null);
        }
        return mensajeResultado;
    }

    public Mensaje DeleteContenedor(List<Contenedor> c, String uuidCxn) {
        Transaction tx = null;
        try {
            inicializaVariableMensaje();
            setSession(HibernateUtil.currentSession(uuidCxn));
            tx = getSession().beginTransaction();
            for (Contenedor co : c) {
                makeTransient(co);
            }
            mensajeResultado.setResultado(true);
            mensajeResultado.setNoError(0);
            mensajeResultado.setError("");
            getSession().getTransaction().commit();
        } catch (HibernateException e) {
            System.err.println(Concatena.delete(0, Concatena.length()).append(msgError).append("DeleteContenedor()1_Error: ").append(e));
            try {
                if (tx != null) {
                    tx.rollback();
                }
                mensajeResultado.setNoError(ControlErroresHibernate.buscaNoErrorPorExcepcion(e.getClass()));
                mensajeResultado.setError(e.getLocalizedMessage());
            } catch (HibernateException exc) { //cuando marca error al hacer rollback pro ejemplo se cae conexion servidor
                mensajeResultado.setNoError(ControlErroresHibernate.buscaNoErrorPorExcepcion(exc.getClass()));
                mensajeResultado.setError(exc.getLocalizedMessage());
            }
            mensajeResultado.setResultado(null);
        }
        return mensajeResultado;
    }

    public Mensaje getContenedoresPorTipoAcciones(TipoAcciones[] tipoAcciones, String uuidCxn) {
        List<Contenedor> values;
        try {
            inicializaVariableMensaje();
            setSession(HibernateUtil.currentSession(uuidCxn));
            getSession().beginTransaction();
            stringBuilder.delete(0, stringBuilder.length()).append("from Contenedor c where c.tipoAcciones IN (:tipoAcciones)");
            q = getSession().createQuery(stringBuilder.toString());
            q.setParameterList("tipoAcciones", tipoAcciones);
            values = q.list();
            mensajeResultado.setResultado(values);
            mensajeResultado.setNoError(0);
            mensajeResultado.setError("");
            getSession().getTransaction().commit();
        } catch (HibernateException ex) {
            System.err.println(builder.delete(0, builder.length()).append(msgError).append("getContenedoresPorTipoAcciones()1_Error: ").append(ex));
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

    public Mensaje buscaPorTipoAccionesYidMultiUsos(TipoAcciones[] tipoAcciones, Long[] idMultiUsos, String claveRazonSocial, String uuidCxn) {
        List<Contenedor> values;
        try {
            inicializaVariableMensaje();
            setSession(HibernateUtil.currentSession(uuidCxn));
            getSession().beginTransaction();
            if (claveRazonSocial != null) {//JSA01
                stringBuilder.delete(0, stringBuilder.length()).append("select c from Contenedor c left outer join c.razonSocial r where c.tipoAcciones IN (:tipoAcciones) AND c.idMultiUsos IN (:idMultiUsos)  and   r.claveRazonSocial = :clave Order by c.ordenId");
            } else {
                stringBuilder.delete(0, stringBuilder.length()).append("select c from Contenedor c left outer join c.razonSocial r where c.tipoAcciones IN (:tipoAcciones) AND c.idMultiUsos IN (:idMultiUsos) Order by c.ordenId");
            }
            q = getSession().createQuery(stringBuilder.toString());
            q.setParameterList("tipoAcciones", tipoAcciones);
            q.setParameterList("idMultiUsos", idMultiUsos);
            if (claveRazonSocial != null) {
                q.setParameter("clave", claveRazonSocial);
            }
            values = q.list();
            mensajeResultado.setResultado(values);
            mensajeResultado.setNoError(0);
            mensajeResultado.setError("");
            getSession().getTransaction().commit();
        } catch (HibernateException ex) {
            System.err.println(builder.delete(0, builder.length()).append(msgError).append("buscaPorTipoAccionesYidMultiUsos()1_Error: ").append(ex));
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
