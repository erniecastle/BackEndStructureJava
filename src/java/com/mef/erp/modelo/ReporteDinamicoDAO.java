/**
 * @author: Daniel Fecha de Creacion: --/--/2012 Compania: Finesoft. Descripcion
 * del programa:
 * -----------------------------------------------------------------------------
 * MODIFICACIONES:
 * -----------------------------------------------------------------------------
 * Clave: JSA01 Autor: Jose Armando Fecha: 23/11/2012 Descripcion: Se cambio el
 * origen de los datos, de MEF a MEFMaster
 * -----------------------------------------------------------------------------
 * Clave: JSA02 Autor: Armando Fecha: 06/09/2013 Descripción:Se agrego el
 * parametro de razones sociales y se cambio el metodo
 * getReporteDinamicoPorClave.
 */
package com.mef.erp.modelo;

import com.mef.erp.modelo.entidad.Contenedor;
import com.mef.erp.modelo.entidad.DatosConsulta;
import com.mef.erp.modelo.entidad.Mensaje;
import com.mef.erp.modelo.entidad.ReporteCamposEncabezado;
import com.mef.erp.modelo.entidad.ReporteCamposWhere;
import com.mef.erp.modelo.entidad.ReporteDatosIncluir;
import com.mef.erp.modelo.entidad.ReporteDatosResumen;
import com.mef.erp.modelo.entidad.ReporteDinamico;
import com.mef.erp.modelo.entidad.ReporteEstilos;
import com.mef.erp.modelo.entidad.ReporteOpcionGrupos;
import com.mef.erp.modelo.entidad.ReporteOrdenGrupo;
import com.mef.erp.modelo.entidad.ReporteOtrosDatosEncabezado;
import com.mef.erp.modelo.entidad.TipoAcciones;
import com.mef.erp.modelo.entidad.TipoElemento;
import com.mef.erp.util.ControlErroresHibernate;
import com.mef.erp.util.HibernateUtil;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Transaction;

/**
 *
 * @author Daniel
 */
public class ReporteDinamicoDAO extends GenericHibernateDAO<ReporteDinamico, Long>
        implements ReporteDinamicoDAOIF {

    private StringBuilder concatena = new StringBuilder(200);
    private final String msgError = concatena.append("ServerERP").append(".MSERR_C_").append(this.getClass().getName()).append(".").toString();
    private List<ReporteDinamico> listDinamico = new ArrayList<ReporteDinamico>();
    private Boolean commit;

    public Mensaje agregar(ReporteDinamico entity, String  uuidCxnMaestra) {
        ReporteDinamico rfd;
        try {
            inicializaVariableMensaje();
            setSession(HibernateUtil.currentSession(uuidCxnMaestra));
            getSession().beginTransaction();
            rfd = makePersistent(entity);
            mensajeResultado.setResultado(rfd);
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

    public Mensaje actualizar(ReporteDinamico entity, String  uuidCxnMaestra) {
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

    public Mensaje eliminar(ReporteDinamico entity, String  uuidCxnMaestra) {
        try {
            inicializaVariableMensaje();
            setSession(HibernateUtil.currentSession(uuidCxnMaestra));
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

    public Mensaje getReporteDinamicoAll( String  uuidCxnMaestra) {
        List<ReporteDinamico> values;
        try {
            inicializaVariableMensaje();
            setSession(HibernateUtil.currentSession(uuidCxnMaestra));
            getSession().beginTransaction();
            q = getSession().createQuery("from ReporteDinamico r  Order by r.contenedor.id , r.clave");
            values = q.list();
            mensajeResultado.setResultado(values);
            mensajeResultado.setNoError(0);
            mensajeResultado.setError("");
            getSession().getTransaction().commit();
        } catch (HibernateException ex) {
            System.err.println(concatena.delete(0, concatena.length()).append(msgError).append("getReporteDinamicoAll()1_Error: ").append(ex));
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

    public Mensaje getReporteDinamicoAllEspecificos( String  uuidCxnMaestra) {
        List<Object> values;
        try {
            inicializaVariableMensaje();
            setSession(HibernateUtil.currentSession(uuidCxnMaestra));
            getSession().beginTransaction();
            q = getSession().createQuery("select new list(r.id,r.clave, r.nombre,r.nombreAbreviado,r.reporteFuenteDatos.nombre) from ReporteDinamico r  Order by r.contenedor.id , r.clave");
            values = q.list();
            mensajeResultado.setResultado(values);
            mensajeResultado.setNoError(0);
            mensajeResultado.setError("");
            getSession().getTransaction().commit();
        } catch (HibernateException ex) {
            System.err.println(concatena.delete(0, concatena.length()).append(msgError).append("getReporteDinamicoAllEspecificos()1_Error: ").append(ex));
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

    public Mensaje getReporteDinamicoPorFuenteYGrupo(String fuenteDatos, Integer idContenedor, String  uuidCxnMaestra) {
        List<Object> values;
        try {
            StringBuilder cadena = new StringBuilder("select new list(o.id,o.clave, o.nombre,o.nombreAbreviado,o.reporteFuenteDatos.nombre) from ReporteDinamico o ");
            fuenteDatos = fuenteDatos == null ? "" : fuenteDatos;
            idContenedor = idContenedor == null ? -1 : idContenedor;
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
                cadena.append("o.contenedor.parentId = :idContenedor ");
            }

            cadena.append("Order by o.contenedor.id, o.nombre");
            inicializaVariableMensaje();
            setSession(HibernateUtil.currentSession(uuidCxnMaestra));
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
            System.err.println(concatena.delete(0, concatena.length()).append(msgError).append("getReporteDinamicoPorFuenteYGrupo()1_Error: ").append(ex));
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

    public Mensaje getReporteDinamicoPorContenedor(Integer contenedorID, String  uuidCxnMaestra) {
        List<ReporteDinamico> values;
        try {
            inicializaVariableMensaje();
            setSession(HibernateUtil.currentSession(uuidCxnMaestra));
            getSession().beginTransaction();
            q = getSession().createQuery("from ReporteDinamico r where r.contenedor.id=:contenedorId Order by r.contenedor.id , r.nombre");
            q.setParameter("contenedorId", contenedorID);
            values = q.list();
            mensajeResultado.setResultado(values);
            mensajeResultado.setNoError(0);
            mensajeResultado.setError("");
            getSession().getTransaction().commit();
        } catch (HibernateException ex) {
            System.err.println(concatena.delete(0, concatena.length()).append(msgError).append("getReporteDinamicoPorContenedor()1_Error: ").append(ex));
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

    public Mensaje getReporteDinamicoPorClave(String clave, String  uuidCxnMaestra) {//JSA02
        ReporteDinamico r;
        try {
            inicializaVariableMensaje();
            setSession(HibernateUtil.currentSession(uuidCxnMaestra));
            getSession().beginTransaction();
            q = getSession().createQuery("from ReporteDinamico r where r.clave=:clave ");
            q.setParameter("clave", clave);
            r = (ReporteDinamico) q.uniqueResult();
            mensajeResultado.setResultado(r);
            mensajeResultado.setNoError(0);
            mensajeResultado.setError("");
            getSession().getTransaction().commit();
        } catch (HibernateException ex) {
            System.err.println(concatena.delete(0, concatena.length()).append(msgError).append("getReporteDinamicoPorClave()1_Error: ").append(ex));
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

    public Mensaje getReporteDinamicoPorID(Long idReporte, String  uuidCxnMaestra) {//JSA02
        ReporteDinamico r;
        try {
            inicializaVariableMensaje();
            setSession(HibernateUtil.currentSession(uuidCxnMaestra));
            getSession().beginTransaction();
            q = getSession().createQuery("from ReporteDinamico r where r.id = :ID");
            q.setParameter("ID", idReporte);
            r = (ReporteDinamico) q.uniqueResult();
            mensajeResultado.setResultado(r);
            mensajeResultado.setNoError(0);
            mensajeResultado.setError("");
            getSession().getTransaction().commit();
        } catch (HibernateException ex) {
            System.err.println(concatena.delete(0, concatena.length()).append(msgError).append("getReporteDinamicoPorID()1_Error: ").append(ex));
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

    private void deleteListQuery(Object[] eliminados) {
        try {
            deleteListQuery("ReporteDinamico", "Id", eliminados);
        } catch (HibernateException ex) {
            System.err.println(concatena.delete(0, concatena.length()).append(msgError).append("deleteListQuery()1_Error: ").append(ex));
            mensajeResultado.setNoError(ControlErroresHibernate.buscaNoErrorPorExcepcion(ex.getClass()));
            mensajeResultado.setError(ex.getLocalizedMessage());
            mensajeResultado.setResultado(null);
            commit = false;
        }
    }

    public Mensaje saveDeleteReporteDinamico(ReporteDinamico entity, Object[] eliminarDatosConsulta, Object[] eliminarDatosIncluir, Object[] eliminarDatosRepOpcGrupo,
            Object[] eliminarDatosOrdenGrupo, Object[] eliminarCamposWhere, Object[] eliminarCamposEncabezados, Object[] eliminarDatosResumen, Object[] eliminarReporteEstilos, Contenedor contenedorGrupoMenu, String  uuidCxnMaestra) {
        ReporteDinamico p = null;
        Transaction transaction = null;
        try {
            inicializaVariableMensaje();
            setSession(HibernateUtil.currentSession(uuidCxnMaestra));
            transaction = getSession().beginTransaction();
            if (entity.getContenedor() == null) {
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
                c.setTipoAcciones(TipoAcciones.GRUPOREPORTE);
                entity.setContenedor(c);
                getSession().saveOrUpdate(entity);
                c.setIdMultiUsos(entity.getId());
                getSession().update(c);
            } else {
                getSession().saveOrUpdate(entity);
            }
            p = entity;
            if (eliminarDatosIncluir == null ? false : eliminarDatosIncluir.length > 0 ? true : false) {
                deleteListQuery(ReporteDatosIncluir.class.getCanonicalName(), "Id", eliminarDatosIncluir);
            }
            if (eliminarDatosRepOpcGrupo == null ? false : eliminarDatosRepOpcGrupo.length > 0 ? true : false) {
                deleteListQuery(ReporteOpcionGrupos.class.getCanonicalName(), "Id", eliminarDatosRepOpcGrupo);
            }
            if (eliminarDatosOrdenGrupo == null ? false : eliminarDatosOrdenGrupo.length > 0 ? true : false) {
                deleteListQuery(ReporteOrdenGrupo.class.getCanonicalName(), "Id", eliminarDatosOrdenGrupo);
            }
            if (eliminarDatosResumen == null ? false : eliminarDatosResumen.length > 0 ? true : false) {
                deleteListQuery(ReporteDatosResumen.class.getCanonicalName(), "Id", eliminarDatosResumen);
            }

            if (eliminarDatosConsulta == null ? false : eliminarDatosConsulta.length > 0 ? true : false) {
                deleteListQuery(DatosConsulta.class.getCanonicalName(), "Id", eliminarDatosConsulta);
            }
            if (eliminarCamposWhere == null ? false : eliminarCamposWhere.length > 0 ? true : false) {
                deleteListQuery(ReporteCamposWhere.class.getCanonicalName(), "Id", eliminarCamposWhere);
            }
            if (eliminarCamposEncabezados == null ? false : eliminarCamposEncabezados.length > 0 ? true : false) {
                deleteListQuery(ReporteOtrosDatosEncabezado.class.getCanonicalName(), "reporteCamposEncabezado_id", eliminarCamposEncabezados);
                deleteListQuery(ReporteCamposEncabezado.class.getCanonicalName(), "Id", eliminarCamposEncabezados);
            }

            if (eliminarReporteEstilos == null ? false : eliminarReporteEstilos.length > 0 ? true : false) {
                deleteListQuery(ReporteEstilos.class.getCanonicalName(), "Id", eliminarReporteEstilos);
            }
            mensajeResultado.setResultado(p);
            mensajeResultado.setNoError(0);
            mensajeResultado.setError("");
            transaction.commit();
        } catch (HibernateException ex) {
            System.err.println(concatena.delete(0, concatena.length()).append(msgError).append("saveDeleteReporteDinamico()1_Error: ").append(ex));
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

    public Mensaje consultaPorRangos(Integer inicio, Integer rango, String  uuidCxnMaestra) {
        try {
            inicializaVariableMensaje();
            setSession(HibernateUtil.currentSession(uuidCxnMaestra));
            getSession().beginTransaction();
            listDinamico = (List<ReporteDinamico>) consultaPorRangos(inicio, rango, null, null);
            mensajeResultado.setResultado(listDinamico);
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

    public Mensaje consultaPorFiltrosReporte(String query, Object[] campos, Object[] valores, String  uuidCxnMaestra) {
        try {
            inicializaVariableMensaje();
            setSession(HibernateUtil.currentSession(uuidCxnMaestra));
            getSession().beginTransaction();
            listDinamico = (List<ReporteDinamico>) super.consultaPorFiltros(query, campos, valores, null, null);
            mensajeResultado.setResultado(listDinamico);
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

    public Mensaje existeDato(String campo, Object valor, String  uuidCxnMaestra) {
        Boolean existe = false;
        try {
            inicializaVariableMensaje();
            setSession(HibernateUtil.currentSession(uuidCxnMaestra));
            getSession().beginTransaction();
            existe = existeDato("ReporteDinamico", campo, valor);
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

    public Mensaje actualizaListaPorCampos(String[] campoModificar, Object[] valoresModificado, String[] camposWhere, Object[] valoresWhere, String  uuidCxnMaestra) {
        int resultado;
        try {
            inicializaVariableMensaje();
            setSession(HibernateUtil.currentSession(uuidCxnMaestra));
            getSession().beginTransaction();
            resultado = updateListQuery(ReporteDinamico.class.getSimpleName(), campoModificar, valoresModificado, camposWhere, valoresWhere);
            mensajeResultado.setResultado(resultado);
            mensajeResultado.setNoError(0);
            mensajeResultado.setError("");
            getSession().getTransaction().commit();
        } catch (HibernateException ex) {
            System.err.println(concatena.delete(0, concatena.length()).append(msgError).append("actualizaListaPorCampos()1_Error: ").append(ex));
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

    public Mensaje eliminarEspecifico(Long idReporte, String  uuidCxnMaestra) {
        ReporteDinamico entity = null;
        try {
            inicializaVariableMensaje();
            setSession(HibernateUtil.currentSession(uuidCxnMaestra));
            getSession().beginTransaction();
            q = getSession().createQuery("from ReporteDinamico r where r.id = :ID");
            q.setParameter("ID", idReporte);
            entity = (ReporteDinamico) q.uniqueResult();
            makeTransient(entity);
            deleteListQueryConFiltrado(Contenedor.class.getSimpleName(), "idMultiUsos", new Long[]{idReporte}, new String[]{"tipoAcciones"}, new TipoAcciones[]{TipoAcciones.GRUPOREPORTE});
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
