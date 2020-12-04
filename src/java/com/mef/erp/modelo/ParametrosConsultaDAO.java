/**
 * @author: Victor Lopez Compañía: Macropro. Descripción del programa: clase
 * ParametrosConsultaDAO para llamados a metodos de HIBERNATE Fecha de Creación:
 * 28/09/2012
 * -----------------------------------------------------------------------------
 * MODIFICACIONES:
 * -----------------------------------------------------------------------------
 * Clave: Autor: Fecha: Descripción:
 * -----------------------------------------------------------------------------
 */
package com.mef.erp.modelo;

import com.mef.erp.modelo.entidad.Contenedor;
import com.mef.erp.modelo.entidad.Mensaje;
import com.mef.erp.modelo.entidad.ParametrosConsulta;
import com.mef.erp.modelo.entidad.TipoAcciones;
import com.mef.erp.modelo.entidad.TipoElemento;
import com.mef.erp.util.ControlErroresHibernate;
import com.mef.erp.util.HibernateUtil;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.HibernateException;

public class ParametrosConsultaDAO extends GenericHibernateDAO<ParametrosConsulta, Long>
        implements ParametrosConsultaDAOIF {

    private StringBuilder concatena = new StringBuilder(200);
    private final String msgError = concatena.append("ServerERP"/*
     * util.getContexto()
     */).append(".MSERR_C_").append(this.getClass().getName()).append(".").toString();
    private List<ParametrosConsulta> listEsp = new ArrayList<ParametrosConsulta>();

    public Mensaje agregar(ParametrosConsulta entity, Contenedor contenedorGrupoMenu, String uuidCxn) {
        ParametrosConsulta parametrosConsulta;
        try {
            inicializaVariableMensaje();
            setSession(HibernateUtil.currentSession(uuidCxn));
            getSession().beginTransaction();
            if (entity.getContenedorGrupo() == null) {
                builder = new StringBuilder();
                builder.delete(0, builder.length()).append("from TipoElemento where id = :clave");
                q = getSession().createQuery(builder.toString());
                q.setParameter("clave", 2);
                TipoElemento tipoElemento = (TipoElemento) q.uniqueResult();
                builder.delete(0, builder.length()).append("select max(c.id) from Contenedor c ");
                q = getSession().createQuery(builder.toString());
                Integer id = (Integer) q.uniqueResult();
                builder.delete(0, builder.length()).append("select max(c.ordenId) from Contenedor c where  c.parentId = :parentId");
                q = getSession().createQuery(builder.toString());
                q.setParameter("parentId", contenedorGrupoMenu.getId());
                Integer ordenId = (Integer) q.uniqueResult();
                if (id == null) {
                    id = 1;
                } else {
                    id++;
                }
                if (ordenId == null) {
                    ordenId = 1;
                } else {
                    ordenId++;
                }
                Contenedor c = new Contenedor(entity);
                c.setId(id);
                c.setParentId(contenedorGrupoMenu.getId());
                c.setOrdenId(ordenId);
                c.setTipoElemento(tipoElemento == null ? contenedorGrupoMenu.getTipoElemento() : tipoElemento);
                c.setHerramienta(contenedorGrupoMenu.getHerramienta());
                c.setTipoAcciones(TipoAcciones.GRUPOCONSULTA);
                entity.setContenedorGrupo(c);
                parametrosConsulta = makePersistent(entity);
                c.setIdMultiUsos(parametrosConsulta.getId());
                getSession().update(c);
            } else {
                parametrosConsulta = makePersistent(entity);
            }
            mensajeResultado.setResultado(parametrosConsulta);
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

    public Mensaje actualizar(ParametrosConsulta entity, Contenedor contenedorGrupoMenu, String uuidCxn) {
        ParametrosConsulta parametrosConsulta;
        try {
            inicializaVariableMensaje();
            setSession(HibernateUtil.currentSession(uuidCxn));
            getSession().beginTransaction();
            if (entity.getContenedorGrupo() == null) {
                builder = new StringBuilder();
                builder.delete(0, builder.length()).append("from TipoElemento where id = :clave");
                q = getSession().createQuery(builder.toString());
                q.setParameter("clave", 2);
                TipoElemento tipoElemento = (TipoElemento) q.uniqueResult();
                builder.delete(0, builder.length()).append("select max(c.id) from Contenedor c ");
                q = getSession().createQuery(builder.toString());
                Integer id = (Integer) q.uniqueResult();
                builder.delete(0, builder.length()).append("select max(c.ordenId) from Contenedor c where  c.parentId = :parentId");
                q = getSession().createQuery(builder.toString());
                q.setParameter("parentId", contenedorGrupoMenu.getId());
                Integer ordenId = (Integer) q.uniqueResult();
                if (id == null) {
                    id = 1;
                } else {
                    id++;
                }
                if (ordenId == null) {
                    ordenId = 1;
                } else {
                    ordenId++;
                }
                Contenedor c = new Contenedor(entity);
                c.setId(id);
                c.setParentId(contenedorGrupoMenu.getId());
                c.setOrdenId(ordenId);
                c.setTipoElemento(tipoElemento == null ? contenedorGrupoMenu.getTipoElemento() : tipoElemento);
                c.setHerramienta(contenedorGrupoMenu.getHerramienta());
                c.setTipoAcciones(TipoAcciones.GRUPOCONSULTA);
                entity.setContenedorGrupo(c);
                parametrosConsulta = makePersistent(entity);
                c.setIdMultiUsos(parametrosConsulta.getId());
                getSession().update(c);
            } else {
                parametrosConsulta = makePersistent(entity);
            }
            mensajeResultado.setResultado(parametrosConsulta);
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

    public Mensaje eliminar(ParametrosConsulta entity, String uuidCxn) {
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

    public Mensaje getParametrosConsultaAll(String uuidCxn) {
        List<ParametrosConsulta> values;
        try {
            inicializaVariableMensaje();
            setSession(HibernateUtil.currentSession(uuidCxn));
            getSession().beginTransaction();
            q = getSession().createQuery("from ParametrosConsulta order by nombre");
            values = q.list();
            mensajeResultado.setResultado(values);
            mensajeResultado.setNoError(0);
            mensajeResultado.setError("");
            getSession().getTransaction().commit();
        } catch (HibernateException ex) {
            System.err.println(concatena.delete(0, concatena.length()).append(msgError).append("getParametrosConsultaAll()1_Error: ").append(ex));
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

    public Mensaje getParametrosConsultaAllEspecifico(String uuidCxn) {
        List<ParametrosConsulta> values;
        try {
            inicializaVariableMensaje();
            setSession(HibernateUtil.currentSession(uuidCxn));
            getSession().beginTransaction();
            q = getSession().createQuery("select new ParametrosConsulta(r.id,r.nombre, r.nombreAbreviado,r.reporteFuenteDatos.nombre,r.contenedorGrupo.nombre) from ParametrosConsulta r order by r.nombre");
            values = q.list();
            mensajeResultado.setResultado(values);
            mensajeResultado.setNoError(0);
            mensajeResultado.setError("");
            getSession().getTransaction().commit();
        } catch (HibernateException ex) {
            System.err.println(concatena.delete(0, concatena.length()).append(msgError).append("getParametrosConsultaAllEspecifico()1_Error: ").append(ex));
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

    public Mensaje getParametrosConsultaPorID(Long idParametrosConsulta, String uuidCxn) {//JSA02
        ParametrosConsulta r;
        try {
            inicializaVariableMensaje();
            setSession(HibernateUtil.currentSession(uuidCxn));
            getSession().beginTransaction();
            q = getSession().createQuery("from ParametrosConsulta r where r.id = :ID");
            q.setParameter("ID", idParametrosConsulta);
            r = (ParametrosConsulta) q.uniqueResult();
            mensajeResultado.setResultado(r);
            mensajeResultado.setNoError(0);
            mensajeResultado.setError("");
            getSession().getTransaction().commit();
        } catch (HibernateException ex) {
            System.err.println(concatena.delete(0, concatena.length()).append(msgError).append("getParametrosConsultaPorID()1_Error: ").append(ex));
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
            listEsp = (List<ParametrosConsulta>) consultaPorRangos(inicio, rango, null, null);
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

    public Mensaje consultaPorFiltrosParametros(String query, Object[] campos, Object[] valores, String uuidCxn) {
        try {
            inicializaVariableMensaje();
            setSession(HibernateUtil.currentSession(uuidCxn));
            getSession().beginTransaction();
            listEsp = (List<ParametrosConsulta>) consultaPorFiltros(query, campos, valores, null, null);
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

    public Mensaje existeDato(String campo, Object valor, String uuidCxn) {
        Boolean existe;
        try {
            inicializaVariableMensaje();
            setSession(HibernateUtil.currentSession(uuidCxn));
            getSession().beginTransaction();
            existe = existeDato("ParametrosConsulta", campo, valor);
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

    public Mensaje PorGrupoMenuFuenteDatos(String fuenteDatos, Integer idContenedor, String uuidCxn) {
        List<ParametrosConsulta> values;
        try {
            StringBuilder cadena = new StringBuilder("select new ParametrosConsulta(o.id,o.nombre, o.nombreAbreviado,o.reporteFuenteDatos.nombre,o.contenedorGrupo.nombre) from ParametrosConsulta o ");
            fuenteDatos = fuenteDatos == null ? "" : fuenteDatos;
            idContenedor = idContenedor == null ? 0 : idContenedor;
            if (fuenteDatos.length() > 0 | idContenedor > 0) {
                cadena.append(" Where ");
            }

            if (fuenteDatos.length() > 0) {
                cadena.append("o.reporteFuenteDatos.clave = :fuenteDatos ");
            }
            if (fuenteDatos.length() > 0 & idContenedor > 0) {
                cadena.append(" AND ");
            }
            if (idContenedor > 0) {
                cadena.append("o.contenedorGrupo.parentId = :idContenedor ");
            }
            cadena.append("Order by o.contenedorGrupo.id, o.nombre ");
            inicializaVariableMensaje();
            setSession(HibernateUtil.currentSession(uuidCxn));
            getSession().beginTransaction();
            q = getSession().createQuery(cadena.toString());
            if (fuenteDatos.length() > 0) {
                q.setParameter("fuenteDatos", fuenteDatos);
            }
            if (idContenedor > 0) {
                q.setParameter("idContenedor", idContenedor);
            }
            values = q.list();
            mensajeResultado.setResultado(values);
            mensajeResultado.setNoError(0);
            mensajeResultado.setError("");
            getSession().getTransaction().commit();
        } catch (HibernateException ex) {
            System.err.println(concatena.delete(0, concatena.length()).append(msgError).append("PorGrupoMenuFuenteDatos()1_Error: ").append(ex));
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

    public Mensaje eliminarEspecifico(Long idReporte, String uuidCxn) {
        ParametrosConsulta entity;
        try {
            inicializaVariableMensaje();
            setSession(HibernateUtil.currentSession(uuidCxn));
            getSession().beginTransaction();
            q = getSession().createQuery("from ParametrosConsulta r where r.id = :ID");
            q.setParameter("ID", idReporte);
            entity = (ParametrosConsulta) q.uniqueResult();
            makeTransient(entity);
            deleteListQueryConFiltrado(Contenedor.class.getSimpleName(), "idMultiUsos", new Long[]{idReporte}, new String[]{"tipoAcciones"}, new TipoAcciones[]{TipoAcciones.GRUPOCONSULTA});
            mensajeResultado.setResultado(entity);
            mensajeResultado.setNoError(0);
            mensajeResultado.setError("");
            getSession().getTransaction().commit();
        } catch (HibernateException ex) {
            System.err.println(concatena.delete(0, concatena.length()).append(msgError).append("eliminarEspecifico()1_Error: ").append(ex));
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
