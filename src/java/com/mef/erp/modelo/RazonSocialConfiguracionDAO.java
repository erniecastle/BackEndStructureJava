/**
 * @author: Armando Fecha de Creación: 03/06/2014 Compañía:Macropro Descripción
 * del programa:
 * -----------------------------------------------------------------------------
 * MODIFICACIONES:
 * -----------------------------------------------------------------------------
 * Clave:JSA01 Autor:Jose Armando Sanchez Acosta Fecha:28/11/2014 Descripción: Se agrego el metodo
 * getRazonSocialConfiguracionPorRazonSocial para obtener las razonSocialConfiguracion por razon social.
 * -----------------------------------------------------------------------------
 */
package com.mef.erp.modelo;

import com.mef.erp.modelo.entidad.Mensaje;
import com.mef.erp.modelo.entidad.RazonSocialConfiguracion;
import com.mef.erp.util.ControlErroresHibernate;
import com.mef.erp.util.HibernateUtil;
import java.util.List;
import org.hibernate.HibernateException;

public class RazonSocialConfiguracionDAO extends GenericHibernateDAO<RazonSocialConfiguracion, Long>
        implements RazonSocialConfiguracionDAOIF {

    private StringBuilder concatena = new StringBuilder(200);
    private final String msgError = concatena.append("ServerERP"/*util.getContexto()*/).append(".MSERR_F_").append(this.getClass().getName()).append(".").toString();

    public Mensaje getRazonSocialConfiguracionAll( String  uuidCxnMaestra) {
        List<RazonSocialConfiguracion> values;
        try {
            inicializaVariableMensaje();
            setSession(HibernateUtil.currentSession(uuidCxnMaestra));
            getSession().beginTransaction();
            q = getSession().createQuery("from RazonSocialConfiguracion");
            values = q.list();
            mensajeResultado.setResultado(values);
            mensajeResultado.setNoError(0);
            mensajeResultado.setError("");
            getSession().getTransaction().commit();
        } catch (HibernateException ex) {
            System.err.println(concatena.delete(0, concatena.length()).append(msgError).append("getRazonSocialConfiguracionAll()1_Error: ").append(ex));
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

    public Mensaje getRazonSocialConfiguracionPorUsuario(String clavesUsuario, String  uuidCxnMaestra) {
        List<RazonSocialConfiguracion> listRazonSocial;
        try {
            inicializaVariableMensaje();
            setSession(HibernateUtil.currentSession(uuidCxnMaestra));
            getSession().beginTransaction();
            q = getSession().createQuery("from RazonSocialConfiguracion ra where ra.usuario.clave =:clave");
            q.setParameter("clave", clavesUsuario);
            listRazonSocial = q.list();
            mensajeResultado.setResultado(listRazonSocial);
            mensajeResultado.setNoError(0);
            mensajeResultado.setError("");
            getSession().getTransaction().commit();
        } catch (HibernateException ex) {
            System.err.println(concatena.delete(0, concatena.length()).append(msgError).append("getRazonSocialConfiguracionPorUsuario()1_Error: ").append(ex));
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

    public Mensaje getRazonSocialConfiguracionPorClave(String clavesRazonSocial, String claveUsuario, String  uuidCxnMaestra) {
        RazonSocialConfiguracion listRazonSocial;
        try {
            inicializaVariableMensaje();
            setSession(HibernateUtil.currentSession(uuidCxnMaestra));
            getSession().beginTransaction();
            q = getSession().createQuery("select ra from RazonSocialConfiguracion ra inner join ra.razonSocial r where r.claveRazonSocial =:clave and ra.usuario.clave =:claveUsuario");
            q.setParameter("clave", clavesRazonSocial);
            q.setParameter("claveUsuario", claveUsuario);
            listRazonSocial = (RazonSocialConfiguracion) q.uniqueResult();
            mensajeResultado.setResultado(listRazonSocial);
            mensajeResultado.setNoError(0);
            mensajeResultado.setError("");
            getSession().getTransaction().commit();
        } catch (HibernateException ex) {
            System.err.println(concatena.delete(0, concatena.length()).append(msgError).append("getRazonSocialConfiguracionPorClave()1_Error: ").append(ex));
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

    public Mensaje getRazonSocialConfiguracionPorRazonSocial(String claveRazonSocial, String  uuidCxnMaestra) {//JSA01
        List<RazonSocialConfiguracion> listRazonSocial;
        try {
            inicializaVariableMensaje();
            setSession(HibernateUtil.currentSession(uuidCxnMaestra));
            getSession().beginTransaction();
            q = getSession().createQuery("from RazonSocialConfiguracion ra where ra.razonSocial.claveRazonSocial =:clave");
            q.setParameter("clave", claveRazonSocial);
            listRazonSocial = q.list();
            mensajeResultado.setResultado(listRazonSocial);
            mensajeResultado.setNoError(0);
            mensajeResultado.setError("");
            getSession().getTransaction().commit();
        } catch (HibernateException ex) {
            System.err.println(concatena.delete(0, concatena.length()).append(msgError).append("getRazonSocialConfiguracionPorRazonSocial()1_Error: ").append(ex));
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
