/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mef.erp.modelo;

import com.mef.erp.modelo.entidad.Asistencias;
import com.mef.erp.modelo.entidad.DetalleAsistencia;
import com.mef.erp.modelo.entidad.Mensaje;
import com.mef.erp.modelo.entidad.RegistroIncapacidad;
import com.mef.erp.util.ControlErroresHibernate;
import com.mef.erp.util.HibernateUtil;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Transaction;

public class AsistenciasDAO extends GenericHibernateDAO<Object, Long> implements AsistenciasDAOIF {

    private final StringBuilder concatena = new StringBuilder(200);
    private final String msgError = concatena.append("ServerERP"/*util.getContexto()*/).append(".MSERR_C_").append(this.getClass().getName()).append(".").toString();

    public Mensaje getAsistenciasAll(String claveRazonesSociales, String uuidCxn) {
        List<Asistencias> values;
        try {
            inicializaVariableMensaje();
            setSession(HibernateUtil.currentSession(uuidCxn));
            getSession().beginTransaction();
            q = getSession().createQuery("from  Asistencias a where a.razonesSociales.clave = :claveRazonesSociales");
            q.setParameter("claveRazonesSociales", claveRazonesSociales);
            values = q.list();
            mensajeResultado.setResultado(values);
            mensajeResultado.setNoError(0);
            mensajeResultado.setError("");
            getSession().getTransaction().commit();
        } catch (HibernateException ex) {
            System.err.println(concatena.delete(0, concatena.length()).append(msgError).append("getMovimientosNominaAll()1_Error: ").append(ex));
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

    public Mensaje saveDeleteAsistencias(List<Asistencias> AgreModif, List<Asistencias> Ordenados, Object[] clavesDelete,
            List<DetalleAsistencia> AgreModifDet, Object[] clavesDeleteDet, List<RegistroIncapacidad> incapacidades, Object[] clavesDeleteIncapacidades, String uuidCxn) {
        boolean exito = true;
        Transaction transaction = null;
        int order = 0;
        Asistencias asis = null;
        try {
            inicializaVariableMensaje();
            setSession(HibernateUtil.currentSession(uuidCxn));
            transaction = getSession().beginTransaction();

            if (clavesDelete != null) {
                if (clavesDelete.length > 0) {
                    exito = deleteListQuerys("Asistencias", "id", clavesDelete);
                }
            }
            //Guardado de modificados
            if (exito) {
                AgreModif = (AgreModif == null ? new ArrayList<Asistencias>() : AgreModif);
                for (Asistencias Am : AgreModif) {
                    asis = Am;
                    makePersistent(Am);
                }
                Ordenados = (Ordenados == null ? new ArrayList<Asistencias>() : Ordenados);
                if (Ordenados.size() > 0) {
                    order = Ordenados.get(0).getOrdenId();
                }
                //Guardado de Ordenados
                for (Asistencias Or : Ordenados) {
                    Or.setOrdenId(order);
                    asis = Or;
                    makePersistent(Or);
                    order++;
                }
                if (clavesDeleteDet != null) {
                    exito = deleteListQuerys("DetalleAsistencia", "id", clavesDeleteDet);
                }
                if (exito) {
                    //Guardado de detalles
                    AgreModifDet = (AgreModifDet == null ? new ArrayList<DetalleAsistencia>() : AgreModifDet);
                    for (DetalleAsistencia detalles : AgreModifDet) {
                        makePersistent(detalles);
                    }
                    exito = operacionIncidencias(incapacidades, clavesDeleteIncapacidades);
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
            System.err.println(concatena.delete(0, concatena.length()).append(msgError).append("saveDeleteAsistencias()1_Error: ").append(ex));
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

    private boolean operacionIncidencias(List<RegistroIncapacidad> incapacidades, Object[] clavesDeleteIncapacidades) {
        boolean exito = true;
        try {
            if (clavesDeleteIncapacidades != null) {
                if (clavesDeleteIncapacidades.length > 0) {
                    exito = deleteListQuerys(RegistroIncapacidad.class.getSimpleName(), "id", clavesDeleteIncapacidades);
                }
            }
            if (exito) {
                incapacidades = incapacidades == null ? new ArrayList<RegistroIncapacidad>() : incapacidades;
                int i;
                for (i = 0; i < incapacidades.size(); i++) {
                    makePersistent(incapacidades.get(i));
                }
            }
        } catch (Exception e) {
            System.err.println(concatena.delete(0, concatena.length()).append(msgError).append("operacionIncidencias()1_Error: ").append(e));
            mensajeResultado.setNoError(ControlErroresHibernate.buscaNoErrorPorExcepcion(e.getClass()));
            mensajeResultado.setError(e.getLocalizedMessage());
            mensajeResultado.setResultado(null);
            exito = false;
        }
        return exito;
    }

    public Mensaje getAsistenciasPorRangoFechas(String claveEmpleado, Date fechaInicio, Date fechaFinal, String claveRazonesSociales, String uuidCxn) {
        List<Asistencias> values;
        try {
            inicializaVariableMensaje();
            setSession(HibernateUtil.currentSession(uuidCxn));
            getSession().beginTransaction();
            q = getSession().createQuery("FROM Asistencias c WHERE c.empleados.clave = :claveEmpleado AND c.fecha BETWEEN :fechaInicio AND :fechaFinal and c.razonesSociales.clave = :claveRazonesSociales and c.empleados.razonesSociales.clave = :claveRazonesSociales ");
            q.setParameter("claveEmpleado", claveEmpleado);
            q.setDate("fechaInicio", fechaInicio);
            q.setDate("fechaFinal", fechaFinal);
            q.setParameter("claveRazonesSociales", claveRazonesSociales);
            values = (List<Asistencias>) q.list();
            mensajeResultado.setResultado(values);
            mensajeResultado.setNoError(0);
            mensajeResultado.setError("");
            getSession().getTransaction().commit();
        } catch (HibernateException ex) {
            System.err.println(concatena.delete(0, concatena.length()).append(msgError).append("getAsistenciasPorRangoFechas()1_Error: ").append(ex));
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
