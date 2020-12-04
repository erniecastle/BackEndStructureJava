/**
 * @author: Victor Lopez Compañía: Macropro. Descripción del programa: clase
 * IngresosReingresosBajasDAO para llamados a metodos de HIBERNATE
 * Fecha:04-07-2012
 * -----------------------------------------------------------------------------
 * MODIFICACIONES:
 * -----------------------------------------------------------------------------
 * Clave: Autor: Fecha: Descripción:
 * -----------------------------------------------------------------------------
 */
package com.mef.erp.modelo;

import com.mef.erp.modelo.entidad.Empleados;
import com.mef.erp.modelo.entidad.RegistroPatronal;
import com.mef.erp.modelo.entidad.IngresosReingresosBajas;
import com.mef.erp.modelo.entidad.Mensaje;
import com.mef.erp.modelo.entidad.RazonesSociales;
import com.mef.erp.util.ControlErroresHibernate;
import com.mef.erp.util.HibernateUtil;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import org.hibernate.HibernateException;

public class IngresosReingresosBajasDAO extends GenericHibernateDAO<IngresosReingresosBajas, Long>
        implements IngresosReingresosBajasDAOIF {

    private StringBuilder concatena = new StringBuilder(200);
    private final String msgError = concatena.append("ServerERP"/*util.getContexto()*/).append(".MSERR_C_").append(this.getClass().getName()).append(".").toString();
    private List<IngresosReingresosBajas> listEsp = new ArrayList<IngresosReingresosBajas>();

    public Mensaje agregar(IngresosReingresosBajas entity, String uuidCxn) {
        IngresosReingresosBajas ingresosReingresosBajas;
        try {
            inicializaVariableMensaje();
            setSession(HibernateUtil.currentSession(uuidCxn));
            getSession().beginTransaction();
            ingresosReingresosBajas = makePersistent(entity);
            mensajeResultado.setResultado(ingresosReingresosBajas);
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

    public Mensaje actualizar(IngresosReingresosBajas entity, String uuidCxn) {
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

    public Mensaje eliminar(IngresosReingresosBajas entity, String uuidCxn) {
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

    public Mensaje getIngresosReingresosBajasAll(String uuidCxn) {
        List<IngresosReingresosBajas> values;
        try {
            inicializaVariableMensaje();
            setSession(HibernateUtil.currentSession(uuidCxn));
            getSession().beginTransaction();
            q = getSession().createQuery("from IngresosReingresosBajas order by id");
            values = q.list();
            mensajeResultado.setResultado(values);
            mensajeResultado.setNoError(0);
            mensajeResultado.setError("");
            getSession().getTransaction().commit();
        } catch (HibernateException ex) {
            System.err.println(concatena.delete(0, concatena.length()).append(msgError).append("getIngresosReingresosBajasAll()1_Error: ").append(ex));
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

    public Mensaje getIngresosReingresosBajasPorEmpRegPatyRazonSoc(String claveEmpleado, String claveRegPat, String claveRazonSocial, String uuidCxn) {
        List<IngresosReingresosBajas> listIngresosReingresosBajas;
        IngresosReingresosBajas ingresosReingresosBajas = null;
        try {
            if (claveRegPat == null) {
                claveRegPat = "";
            }
            inicializaVariableMensaje();
            setSession(HibernateUtil.currentSession(uuidCxn));
            getSession().beginTransaction();
            builder = new StringBuilder();
            builder.append("from IngresosReingresosBajas ing where ing.empleados.clave = :claveEmpleado");
            if (claveRegPat.length() > 0) {
                builder.append(" and ing.registroPatronal.clave = :claveRegPat");
            }
            builder.append(" and ing.razonesSociales.clave = :claveRazonSocial");
            q = getSession().createQuery(builder.toString());
            q.setParameter("claveEmpleado", claveEmpleado);
            if (claveRegPat.length() > 0) {
                q.setParameter("claveRegPat", claveRegPat);
            }
            q.setParameter("claveRazonSocial", claveRazonSocial);
            listIngresosReingresosBajas = (List<IngresosReingresosBajas>) q.list();
            if (listIngresosReingresosBajas != null) {
                Calendar fechaActual = Calendar.getInstance();
                for (int i = 0; i < listIngresosReingresosBajas.size(); i++) {
                    if (listIngresosReingresosBajas.get(i).getFechaBaja().compareTo(fechaActual.getTime()) <= 0) {
                        ingresosReingresosBajas = listIngresosReingresosBajas.get(i);
                        for (int j = 0; j < listIngresosReingresosBajas.size(); j++) {
                            if (listIngresosReingresosBajas.get(j).getTipoMovimiento() == IngresosReingresosBajas.TipoMovimiento.R) {
                                if (listIngresosReingresosBajas.get(j).getFechaBaja().compareTo(listIngresosReingresosBajas.get(i).getFechaBaja()) >= 0) {
                                    ingresosReingresosBajas = listIngresosReingresosBajas.get(j);
                                }
                            }
                        }
                    } else {
                        ingresosReingresosBajas = listIngresosReingresosBajas.get(i);
                    }
                }
            }
            mensajeResultado.setResultado(ingresosReingresosBajas);
            mensajeResultado.setNoError(0);
            mensajeResultado.setError("");
            getSession().getTransaction().commit();
        } catch (HibernateException ex) {
            System.err.println(concatena.delete(0, concatena.length()).append(msgError).append("getIngresosReingresosBajasPorEmpRegPatyRazonSoc()1_Error: ").append(ex));
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

    public Mensaje getIngresosReingresosBajasPorRegPatronal(RegistroPatronal registroPatronal, String uuidCxn) {
        List<IngresosReingresosBajas> values;
        try {
            inicializaVariableMensaje();
            setSession(HibernateUtil.currentSession(uuidCxn));
            getSession().beginTransaction();
            q = getSession().createQuery("Select si from IngresosReingresosBajas si Where si.registroPatronal = :registroPatronal");
            q.setParameter("registroPatronal", registroPatronal);
            values = q.list();
            mensajeResultado.setResultado(values);
            mensajeResultado.setNoError(0);
            mensajeResultado.setError("");
            getSession().getTransaction().commit();
        } catch (HibernateException ex) {
            System.err.println(concatena.delete(0, concatena.length()).append(msgError).append("getIngresosReingresosBajasPorRegPatronal()1_Error: ").append(ex));
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

    ///////////////////////
    public Mensaje getIngresosReingresosBajasPorIdEmpleado(Empleados empleados, String uuidCxn) {
        Date fechaEmpleado;
        try {
            inicializaVariableMensaje();
            setSession(HibernateUtil.currentSession(uuidCxn));
            getSession().beginTransaction();
            q = getSession().createQuery("Select fechaIngreso from IngresosReingresosBajas  Where empleados = :empleados");
            q.setParameter("empleados", empleados);
            fechaEmpleado = (Date) q.uniqueResult();
            mensajeResultado.setResultado(fechaEmpleado);
            mensajeResultado.setNoError(0);
            mensajeResultado.setError("");
            getSession().getTransaction().commit();
        } catch (HibernateException ex) {
            System.err.println(concatena.delete(0, concatena.length()).append(msgError).append("getIngresosReingresosBajasPorRegPatronal()1_Error: ").append(ex));
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

    /////////////////////////
    public Mensaje getIngresosReingresosBajasPorRazonSocial(RazonesSociales razonSocial, String uuidCxn) {
        List<IngresosReingresosBajas> values;
        try {
            inicializaVariableMensaje();
            setSession(HibernateUtil.currentSession(uuidCxn));
            getSession().beginTransaction();
            q = getSession().createQuery("Select si from IngresosReingresosBajas si Where si.razonSocial = :razonSocial");
            q.setParameter("razonSocial", razonSocial);
            values = q.list();
            mensajeResultado.setResultado(values);
            mensajeResultado.setNoError(0);
            mensajeResultado.setError("");
            getSession().getTransaction().commit();
        } catch (HibernateException ex) {
            System.err.println(concatena.delete(0, concatena.length()).append(msgError).append("getIngresosReingresosBajasPorRazonSocial()1_Error: ").append(ex));
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

    public Mensaje getPorEmpleadoActivo(String claveEmpleado, String claveRegPatronal, String claveRazonSocial, String uuidCxn) {
        IngresosReingresosBajas values;
        try {
            inicializaVariableMensaje();
            setSession(HibernateUtil.currentSession(uuidCxn));
            getSession().beginTransaction();
            q = getSession().createQuery("Select si from IngresosReingresosBajas si Where si.empleados.clave = :claveEmpleado and si.registroPatronal.clave = :claveRegPatronal and si.razonesSociales.clave = :claveRazonSocial and si.fechaBaja >= :fechaActual");
            q.setParameter("claveEmpleado", claveEmpleado);
            q.setParameter("claveRegPatronal", claveRegPatronal);
            q.setParameter("claveRazonSocial", claveRazonSocial);
            q.setParameter("fechaActual", getFechaDelSistema().getTime());
            values = (IngresosReingresosBajas) q.uniqueResult();
            mensajeResultado.setResultado(values);
            mensajeResultado.setNoError(0);
            mensajeResultado.setError("");
            getSession().getTransaction().commit();
        } catch (HibernateException ex) {
            System.err.println(concatena.delete(0, concatena.length()).append(msgError).append("getPorEmpleadoActivo()1_Error: ").append(ex));
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

    public Mensaje getPorEmpleadoInactivo(String claveEmpleado, String claveRegPatronal, String claveRazonSocial, Date fechaActual, String uuidCxn) {
        List<IngresosReingresosBajas> listIngresosReingresosBajas;
        IngresosReingresosBajas ingresosReingresosBajas = null;
        try {
            inicializaVariableMensaje();
            setSession(HibernateUtil.currentSession(uuidCxn));
            getSession().beginTransaction();
            q = getSession().createQuery("Select si from IngresosReingresosBajas si Where si.empleados.clave = :claveEmpleado and si.registroPatronal.clave = :claveRegPatronal and si.razonesSociales.clave = :claveRazonSocial order by fechaBaja");
            q.setParameter("claveEmpleado", claveEmpleado);
            q.setParameter("claveRegPatronal", claveRegPatronal);
            q.setParameter("claveRazonSocial", claveRazonSocial);
            //q.setParameter("fechaActual", fechaActual);
            listIngresosReingresosBajas = (List<IngresosReingresosBajas>) q.list();
            for (int i = 0; i < listIngresosReingresosBajas.size(); i++) {
                if (listIngresosReingresosBajas.get(i).getFechaBaja().compareTo(fechaActual) <= 0) {
                    ingresosReingresosBajas = listIngresosReingresosBajas.get(i);
                    for (int j = 0; j < listIngresosReingresosBajas.size(); j++) {
                        if (listIngresosReingresosBajas.get(j).getTipoMovimiento() == IngresosReingresosBajas.TipoMovimiento.R) {
                            if (listIngresosReingresosBajas.get(j).getFechaBaja().compareTo(listIngresosReingresosBajas.get(i).getFechaBaja()) >= 0) {
                                if (listIngresosReingresosBajas.get(j).getFechaBaja().compareTo(fechaActual) >= 0) {
                                    ingresosReingresosBajas = null;
                                } else {
                                    ingresosReingresosBajas = listIngresosReingresosBajas.get(i);
                                }
                            }
                        }
                    }
                }
            }
            mensajeResultado.setResultado(ingresosReingresosBajas);
            mensajeResultado.setNoError(0);
            mensajeResultado.setError("");
            getSession().getTransaction().commit();
        } catch (HibernateException ex) {
            System.err.println(concatena.delete(0, concatena.length()).append(msgError).append("getPorEmpleadoInactivo()1_Error: ").append(ex));
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

    public Mensaje getPorReferenciaPlazaEmpActivo(String claveReferenciaPlazaEmp, String claveRegPatronal, String claveRazonSocial, String uuidCxn) {
        IngresosReingresosBajas values;
        try {
            inicializaVariableMensaje();
            setSession(HibernateUtil.currentSession(uuidCxn));
            getSession().beginTransaction();
            q = getSession().createQuery("Select si from IngresosReingresosBajas si Where si.plazasPorEmpleado.clave = :claveReferenciaPlazaEmp and si.registroPatronal.clave = :claveRegPatronal and si.razonesSociales.clave = :claveRazonSocial and si.fechaBaja >= :fechaActual");
            q.setParameter("claveReferenciaPlazaEmp", claveReferenciaPlazaEmp);
            q.setParameter("claveRegPatronal", claveRegPatronal);
            q.setParameter("claveRazonSocial", claveRazonSocial);
            q.setParameter("fechaActual", getFechaDelSistema().getTime());
            values = (IngresosReingresosBajas) q.uniqueResult();
            mensajeResultado.setResultado(values);
            mensajeResultado.setNoError(0);
            mensajeResultado.setError("");
            getSession().getTransaction().commit();
        } catch (HibernateException ex) {
            System.err.println(concatena.delete(0, concatena.length()).append(msgError).append("getPorReferenciaPlazaEmpActivo()1_Error: ").append(ex));
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

    public Mensaje getPorClaveEmpleado(String claveEmp, String claveRegPatronal, String claveRazonSocial, String uuidCxn) {
        IngresosReingresosBajas values;
        try {
            inicializaVariableMensaje();
            setSession(HibernateUtil.currentSession(uuidCxn));
            getSession().beginTransaction();
            q = getSession().createQuery("Select si from IngresosReingresosBajas si Where si.empleados.clave = :claveEmp and si.registroPatronal.clave = :claveRegPatronal and si.razonesSociales.clave = :claveRazonSocial and si.fechaBaja >= :fechaActual");
            q.setParameter("claveEmp", claveEmp);
            q.setParameter("claveRegPatronal", claveRegPatronal);
            q.setParameter("claveRazonSocial", claveRazonSocial);
            q.setParameter("fechaActual", getFechaDelSistema().getTime());
            values = (IngresosReingresosBajas) q.uniqueResult();
            mensajeResultado.setResultado(values);
            mensajeResultado.setNoError(0);
            mensajeResultado.setError("");
            getSession().getTransaction().commit();
        } catch (HibernateException ex) {
            System.err.println(concatena.delete(0, concatena.length()).append(msgError).append("getPorClaveEmpleado()1_Error: ").append(ex));
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

    public Mensaje getPorReferenciaPlazaEmpInactiva(String claveReferenciaPlazaEmp, String claveRegPatronal, String claveRazonSocial, String uuidCxn) {
        IngresosReingresosBajas values;
        try {
            inicializaVariableMensaje();
            setSession(HibernateUtil.currentSession(uuidCxn));
            getSession().beginTransaction();
            q = getSession().createQuery("Select si from IngresosReingresosBajas si Where si.plazasPorEmpleado.clave = :claveReferenciaPlazaEmp and si.registroPatronal.clave = :claveRegPatronal and si.razonesSociales.clave = :claveRazonSocial and si.fechaBaja < :fechaActual");
            q.setParameter("claveReferenciaPlazaEmp", claveReferenciaPlazaEmp);
            q.setParameter("claveRegPatronal", claveRegPatronal);
            q.setParameter("claveRazonSocial", claveRazonSocial);
            q.setParameter("fechaActual", getFechaDelSistema().getTime());
            values = (IngresosReingresosBajas) q.uniqueResult();
            mensajeResultado.setResultado(values);
            mensajeResultado.setNoError(0);
            mensajeResultado.setError("");
            getSession().getTransaction().commit();
        } catch (HibernateException ex) {
            System.err.println(concatena.delete(0, concatena.length()).append(msgError).append("getPorReferenciaPlazaEmpInactiva()1_Error: ").append(ex));
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
            listEsp = (List<IngresosReingresosBajas>) consultaPorRangos(inicio, rango, null, null);
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

    public Mensaje consultaPorFiltrosReIngresos(String query, Object[] campos, Object[] valores, String uuidCxn) {
        try {
            inicializaVariableMensaje();
            setSession(HibernateUtil.currentSession(uuidCxn));
            getSession().beginTransaction();
            listEsp = (List<IngresosReingresosBajas>) consultaPorFiltros(query, campos, valores, null, null);
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
            existe = existeDato("IngresosReingresosBajas", campo, valor);
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
}
