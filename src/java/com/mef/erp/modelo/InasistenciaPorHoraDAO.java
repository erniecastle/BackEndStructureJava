/**
 * @author: Victor Lopez Fecha de Creación: 05/12/2016 Compañía: Macropro
 * Descripción del programa: clase InasistenciaPorHora para llamados a metodos
 * de HIBERNATE
 * -----------------------------------------------------------------------------
 * MODIFICACIONES:
 * -----------------------------------------------------------------------------
 * Clave: Autor: Fecha: Descripción:
 * -----------------------------------------------------------------------------
 * Clave: Autor: Fecha: Descripción:
 * -----------------------------------------------------------------------------
 */
package com.mef.erp.modelo;

import com.mef.erp.modelo.entidad.InasistenciaPorHora;
import com.mef.erp.modelo.entidad.Mensaje;
import com.mef.erp.util.ControlErroresHibernate;
import com.mef.erp.util.HibernateUtil;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Transaction;

public class InasistenciaPorHoraDAO extends GenericHibernateDAO<Object, Long> implements InasistenciaPorHoraDAOIF {

    private final StringBuilder concatena = new StringBuilder(200);
    private final String msgError = concatena.append("ServerERP"/*util.getContexto()*/).append(".MSERR_C_").append(this.getClass().getName()).append(".").toString();

    public Mensaje getInasistenciaPorHoraAll(String uuidCxn) {
        List<InasistenciaPorHora> values;
        try {
            inicializaVariableMensaje();
            setSession(HibernateUtil.currentSession(uuidCxn));
            getSession().beginTransaction();
            q = getSession().createQuery("from  InasistenciaPorHora a");
            values = q.list();
            mensajeResultado.setResultado(values);
            mensajeResultado.setNoError(0);
            mensajeResultado.setError("");
            getSession().getTransaction().commit();
        } catch (HibernateException ex) {
            System.err.println(concatena.delete(0, concatena.length()).append(msgError).append("getInasistenciaPorHoraAll()1_Error: ").append(ex));
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

    private boolean deleteListQuerys(String tabla, String campo, Object[] valores) {
        try {
            deleteListQuery(tabla, campo, valores);
        } catch (HibernateException ex) {
            System.err.println(concatena.delete(0, concatena.length()).append(msgError).append("deleteListQuery()1_Error: ").append(ex));
            mensajeResultado.setNoError(ControlErroresHibernate.buscaNoErrorPorExcepcion(ex.getClass()));
            mensajeResultado.setError(ex.getLocalizedMessage());
            mensajeResultado.setResultado(null);
            return false;
        }
        return true;
    }

    public Mensaje saveDeleteInasistenciaPorHora(List<InasistenciaPorHora> AgreModif, Object[] clavesDelete, String uuidCxn) {
        boolean exito = true;
        Transaction transaction = null;
        InasistenciaPorHora asis = null;
        try {
            inicializaVariableMensaje();
            setSession(HibernateUtil.currentSession(uuidCxn));
            transaction = getSession().beginTransaction();

            if (clavesDelete != null) {
                if (clavesDelete.length > 0) {
                    exito = deleteListQuerys("InasistenciaPorHora", "id", clavesDelete);
                }
            }
            //Guardado de modificados
            if (exito) {
                AgreModif = (AgreModif == null ? new ArrayList<InasistenciaPorHora>() : AgreModif);
                for (InasistenciaPorHora Am : AgreModif) {
                    asis = Am;
                    makePersistent(Am);
                }
            }
            if (exito) {
                asis = null;
                mensajeResultado.setResultado(asis);
                mensajeResultado.setNoError(0);
                mensajeResultado.setError("");
                getSession().getTransaction().commit();
            } else {
                mensajeResultado.setResultado(asis);
                if (transaction != null) {
                    transaction.rollback();
                }
            }
        } catch (HibernateException ex) {
            System.err.println(concatena.delete(0, concatena.length()).append(msgError).append("saveDeleteInasistenciaPorHora()1_Error: ").append(ex));
            try {
                if (transaction != null) {
                    transaction.rollback();
                }
                mensajeResultado.setNoError(ControlErroresHibernate.buscaNoErrorPorExcepcion(ex.getClass()));
                mensajeResultado.setError(ex.getLocalizedMessage());
            } catch (HibernateException exc) { //cuando marca error al hacer rollback pro ejemplo se cae conexion servidor
                mensajeResultado.setNoError(ControlErroresHibernate.buscaNoErrorPorExcepcion(exc.getClass()));
                mensajeResultado.setError(exc.getLocalizedMessage());
            }
            mensajeResultado.setResultado(asis);
        }
        return mensajeResultado;
    }

    public Mensaje getInasistenciaPorNominaPeriodo(String claveTipoNomina, String claveRazonSocial, Long idPeriodo, String uuidCxn) {
        List<InasistenciaPorHora> values;
        try {
            inicializaVariableMensaje();
            setSession(HibernateUtil.currentSession(uuidCxn));
            getSession().beginTransaction();
            builder = new StringBuilder();
            builder.append("Select inas from InasistenciaPorHora inas inner join inas.tipoNomina t inner join inas.periodosNomina p inner join inas.plazasPorEmpleadosMov pem inner join pem.plazasPorEmpleado pe inner join pe.empleados em ");
            builder.append("where t.clave = :claveTipoNomina and p.id = :idPeriodo and pe.razonesSociales.clave = :claveRazonSocial order by em.clave");
            q = getSession().createQuery(builder.toString());
            q.setParameter("claveTipoNomina", claveTipoNomina);
            q.setParameter("claveRazonSocial", claveRazonSocial);
            q.setParameter("idPeriodo", idPeriodo);
            values = q.list();
            mensajeResultado.setResultado(values);
            mensajeResultado.setNoError(0);
            mensajeResultado.setError("");
            getSession().getTransaction().commit();
        } catch (HibernateException ex) {
            System.err.println(concatena.delete(0, concatena.length()).append(msgError).append("getInasistenciaPorNominaPeriodo()1_Error: ").append(ex));
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
