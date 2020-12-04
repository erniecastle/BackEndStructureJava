/**
 * @author: Daniel Fecha de Creación: --/--/-- Compañía: FineSoft Descripción
 * del programa:
 * -----------------------------------------------------------------------------
 * MODIFICACIONES:
 * -----------------------------------------------------------------------------
 * Clave:JSA01 Autor:Jose Armando Sanchez Acosta Fecha:23/07/2012 Descripción:Se
 * agrego la programacion del guardado y se quedo pendiente lo del llamado del
 * Calculo del SDI.
 * -----------------------------------------------------------------------------
 * Clave:JSA02 Autor:Jose Armando Sanchez Acosta Fecha:30/07/2012 Descripción:Se
 * agrego el metodo existeRFC para verificar que el RFC no exista aun.
 * -----------------------------------------------------------------------------
 * Clave:JSA03 Autor:Jose Armando Sanchez Acosta Fecha:24/06/2013 Descripción:Se
 * agrego el codigo para guardar o modificar el creditoInfonavit.
 * -----------------------------------------------------------------------------
 */
package com.mef.erp.modelo;

import com.mef.erp.modelo.entidad.Capacitaciones;
import com.mef.erp.modelo.entidad.Documentacion;
import com.mef.erp.modelo.entidad.Empleados;
import com.mef.erp.modelo.entidad.ExperienciaLaboralExterna;
import com.mef.erp.modelo.entidad.ExperienciaLaboralInterna;
import com.mef.erp.modelo.entidad.Familiares;
import com.mef.erp.modelo.entidad.FormacionEconomica;
import com.mef.erp.modelo.entidad.IngresosReingresosBajas;
import com.mef.erp.modelo.entidad.Mensaje;
import com.mef.erp.modelo.entidad.PlazasPorEmpleado;
import com.mef.erp.modelo.entidad.PlazasPorEmpleadosMov;
import com.mef.erp.modelo.entidad.SalariosIntegrados;
import com.mef.erp.modelo.entidad.VacacionesAplicacion;
import com.mef.erp.modelo.entidad.VacacionesDevengadas;
import com.mef.erp.util.ControlErroresHibernate;
import com.mef.erp.util.HibernateUtil;
import java.util.Date;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author daniel
 */
public class EmpleadosDAO extends GenericHibernateDAO<Empleados, Long> implements EmpleadosDAOIF {

    Query query;
    private final StringBuilder concatena = new StringBuilder(200);
    private final String msgError = concatena.append("ServerERP"/**
     * util.getContexto()
     */
    ).append(".MSERR_C_").append(this.getClass().getName()).append(".").toString();

    public Mensaje agregar(Empleados entity, String uuidCxn) {
        Empleados turnos = null;
        try {
            inicializaVariableMensaje();
            setSession(HibernateUtil.currentSession(uuidCxn));
            getSession().beginTransaction();
            turnos = makePersistent(entity);
            mensajeResultado.setResultado(turnos);
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

    public Mensaje actualizar(Empleados entity, String uuidCxn) {
        boolean exito = true;
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

    public Mensaje eliminar(Empleados entity, String uuidCxn) {
        boolean exito = true;
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

    public Mensaje getEmpleadosAll(String claveRazonesSocial, String uuidCxn) {
        List<Empleados> values = null;
        try {
            inicializaVariableMensaje();
            setSession(HibernateUtil.currentSession(uuidCxn));
            getSession().beginTransaction();
            q = getSession().createQuery("from Empleados c where c.razonesSociales.clave=:clave");
            q.setParameter("clave", claveRazonesSocial);
            values = q.list();
            mensajeResultado.setResultado(values);
            mensajeResultado.setNoError(0);
            mensajeResultado.setError("");
            getSession().getTransaction().commit();
        } catch (HibernateException ex) {
            System.err.println(concatena.delete(0, concatena.length()).append(msgError).append("getEmpleadosAll()1_Error: ").append(ex));
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

    public Mensaje getEmpleadosPorClave(String clave, String uuidCxn) {
        Empleados t = null;
        try {
            inicializaVariableMensaje();
            setSession(HibernateUtil.currentSession(uuidCxn));
            getSession().beginTransaction();
            q = getSession().createQuery("from Empleados where clave=:clave");
            q.setParameter("clave", clave);
            t = (Empleados) q.uniqueResult();
            mensajeResultado.setResultado(t);
            mensajeResultado.setNoError(0);
            mensajeResultado.setError("");
            getSession().getTransaction().commit();
        } catch (HibernateException ex) {
            System.err.println(concatena.delete(0, concatena.length()).append(msgError).append("getEmpleadosPorClave()1_Error: ").append(ex));
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

    private void deleteListQueryIn(Session session, String tabla, String campo, Object[] valores, String uuidCxn) {//JSA02
        consulta = new StringBuilder("delete ");
        consulta.append(tabla).append(" where ").append(campo).append(" in(:valores)");
        query = session.createQuery(consulta.toString());
        query.setParameterList("valores", valores);
        query.executeUpdate();
    }

    private void deleteListQuery(Session session, String tabla, String campo, Object valores, String uuidCxn) {//JSA02
        consulta = new StringBuilder("delete ");
        consulta.append(tabla).append(" where ").append(campo).append("=:valores)");
        query = session.createQuery(consulta.toString());
        query.setParameter("valores", valores);
        query.executeUpdate();
    }

    private void deleteListQueryVacaciones(Session session, Object valores) {
        consulta = new StringBuilder("Select v.id From ");
        consulta.append(VacacionesAplicacion.class.getSimpleName()).append(" v where v.vacacionesDevengadas.plazasPorEmpleado.empleados.id =:valores");
        query = session.createQuery(consulta.toString());
        query.setParameter("valores", valores);
        List<Long> ids = query.list();

        if (ids == null ? false : !ids.isEmpty()) {
            consulta = new StringBuilder("delete from ");
            consulta.append(VacacionesAplicacion.class.getSimpleName()).append(" v where v.id in (:valores)");
            query = session.createQuery(consulta.toString());
            query.setParameterList("valores", ids.toArray());
            query.executeUpdate();
        }

        consulta = new StringBuilder("Select v.id From ");
        consulta.append(VacacionesDevengadas.class.getSimpleName()).append(" v where v.plazasPorEmpleado.empleados.id =:valores");
        query = session.createQuery(consulta.toString());
        query.setParameter("valores", valores);
        ids = query.list();

        if (ids == null ? false : !ids.isEmpty()) {
            consulta = new StringBuilder("delete ");
            consulta.append(VacacionesDevengadas.class.getSimpleName()).append(" v where v.id in (:valores)");
            query = session.createQuery(consulta.toString());
            query.setParameterList("valores",  ids.toArray());
            query.executeUpdate();
        }
    }

    public Mensaje SaveEmpleado(List<Familiares> agrega, Object[] eliminados,
            List<FormacionEconomica> agregaFE, Object[] eliminadosFE,
            List<Capacitaciones> agregaCap, Object[] eliminadosCap,
            List<ExperienciaLaboralExterna> agregaELE, Object[] eliminadosELE,
            List<ExperienciaLaboralInterna> agregaELI, Object[] eliminadosELI,
            List<Documentacion> agregaDoc, Object[] eliminadosDoc, List<VacacionesAplicacion> agregaVac,
            Empleados empleados, List<PlazasPorEmpleado> listPlazasPorEmpleados, List<PlazasPorEmpleadosMov> listPlazasPorEmpleadoMov, IngresosReingresosBajas ingresosReingresosBajas, SalariosIntegrados salariosIntegrados, String uuidCxn) {//JSA01
        inicializaVariableMensaje();
        setSession(HibernateUtil.currentSession(uuidCxn));
        Transaction transaction = null;
        try {
            transaction = getSession().beginTransaction();
            if (eliminados.length > 0) {
                deleteListQueryIn(getSession(), "Familiares", "id", eliminados, uuidCxn);
            }
            if (eliminadosFE.length > 0) {
                deleteListQueryIn(getSession(), "FormacionEconomica", "id", eliminadosFE, uuidCxn);
            }
            if (eliminadosCap.length > 0) {
                deleteListQueryIn(getSession(), "Capacitaciones", "id", eliminadosCap, uuidCxn);
            }
            if (eliminadosELE.length > 0) {
                deleteListQueryIn(getSession(), "ExperienciaLaboralExterna", "id", eliminadosELE, uuidCxn);
            }
            if (eliminadosELI.length > 0) {
                deleteListQueryIn(getSession(), "ExperienciaLaboralInterna", "id", eliminadosELI, uuidCxn);
            }
            if (eliminadosDoc.length > 0) {
                deleteListQueryIn(getSession(), "Documentacion", "id", eliminadosDoc, uuidCxn);
            }

            if (empleados.getId() == null) {
                empleados.setStatus(true);
                getSession().saveOrUpdate(empleados);
            }
            int i;
//            Plazas por empleado
            for (i = 0; i < listPlazasPorEmpleados.size(); i++) {
                listPlazasPorEmpleados.get(i).setEmpleados(empleados);
                getSession().saveOrUpdate(listPlazasPorEmpleados.get(i));
                if (i % 100 == 0 && i > 0) {
                    getSession().flush();
                }
            }
            //Plazas por empleado
            for (i = 0; i < listPlazasPorEmpleadoMov.size(); i++) {
                if (listPlazasPorEmpleadoMov.get(i).getPlazasPorEmpleado().getId() == null) {
                    listPlazasPorEmpleadoMov.get(i).setPlazasPorEmpleado(listPlazasPorEmpleados.get(0));
                }
                getSession().saveOrUpdate(listPlazasPorEmpleadoMov.get(i));
                if (i % 100 == 0 && i > 0) {
                    getSession().flush();
                }
            }

            if (ingresosReingresosBajas != null) {
                getSession().saveOrUpdate(ingresosReingresosBajas);
            }

            if (salariosIntegrados != null) {//JSA01
                salariosIntegrados.setEmpleados(empleados);
                getSession().saveOrUpdate(salariosIntegrados);
            }
            //FAMILIARES
            for (i = 0; i < agrega.size(); i++) {
                agrega.get(i).setEmpleados(empleados);
                getSession().saveOrUpdate(agrega.get(i));
                if (i % 100 == 0 && i > 0) {
                    getSession().flush();
                }
            }
            //FORMACION ECONOMICA
            for (i = 0; i < agregaFE.size(); i++) {
                agregaFE.get(i).setEmpleados(empleados);
                getSession().saveOrUpdate(agregaFE.get(i));
                if (i % 100 == 0 && i > 0) {
                    getSession().flush();
                }
            }
            //CAPACITACIONES
            for (i = 0; i < agregaCap.size(); i++) {
                agregaCap.get(i).setEmpleados(empleados);
                getSession().saveOrUpdate(agregaCap.get(i));
                if (i % 100 == 0 && i > 0) {
                    getSession().flush();
                }
            }
            //EXPERIENCIA LABORAL EXTERNA
            for (i = 0; i < agregaELE.size(); i++) {
                agregaELE.get(i).setEmpleados(empleados);
                getSession().saveOrUpdate(agregaELE.get(i));
                if (i % 100 == 0 && i > 0) {
                    getSession().flush();
                }
            }
            //EXPERIENCIA LABORAL INTERNA
            for (i = 0; i < agregaELI.size(); i++) {
                agregaELI.get(i).setEmpleados(empleados);
                getSession().saveOrUpdate(agregaELI.get(i));
                if (i % 100 == 0 && i > 0) {
                    getSession().flush();
                }
            }
            //DOCUMENTACION
            for (i = 0; i < agregaDoc.size(); i++) {
                agregaDoc.get(i).setEmpleados(empleados);
                getSession().saveOrUpdate(agregaDoc.get(i));
                if (i % 100 == 0 && i > 0) {
                    getSession().flush();
                }
            }
            if (agregaVac != null) {
                for (i = 0; i < agregaVac.size(); i++) {
                    agregaVac.get(i).getVacacionesDisfrutadas().setEmpleados(empleados);
                    getSession().saveOrUpdate(agregaVac.get(i));
                    if (i % 100 == 0 && i > 0) {
                        getSession().flush();
                    }
                }
            }
            mensajeResultado.setResultado(empleados);
            mensajeResultado.setNoError(0);
            mensajeResultado.setError("");
            transaction.commit();
        } catch (HibernateException ex) {
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

    public Mensaje getEmpleadoPorClaveRazonTipNomina(String clave, String claveRazon, String claveTipNom, Date fechaInicial, Date fechaFinal, String uuidCxn) {
        Empleados e;//JEVC01
        try {
            inicializaVariableMensaje();
            setSession(HibernateUtil.currentSession(uuidCxn));
            getSession().beginTransaction();
            builder = new StringBuilder();
            builder.append("select o.plazasPorEmpleado.empleados from PlazasPorEmpleadosMov o where ");
            builder.append("o.id IN(Select MAX(m.id) from PlazasPorEmpleadosMov m ");
            builder.append("where m.plazasPorEmpleado.empleados.clave=:clave ");
            builder.append("and m.plazasPorEmpleado.razonesSociales.clave=:claveRazon ");
            builder.append("and m.fechaInicial <=:fechaInicial and m.plazasPorEmpleado.fechaFinal >=:fechaFinal ");
            builder.append("and m.tipoNomina.clave=:claveTipNom ");
            builder.append("group by m.plazasPorEmpleado.empleados.clave)");
            q = getSession().createQuery(builder.toString());
            q.setParameter("clave", clave);
            q.setParameter("claveRazon", claveRazon);
            q.setParameter("claveTipNom", claveTipNom);
            q.setParameter("fechaInicial", fechaInicial);
            q.setParameter("fechaFinal", fechaFinal);
            e = (Empleados) q.uniqueResult();
            mensajeResultado.setResultado(e);
            mensajeResultado.setNoError(0);
            mensajeResultado.setError("");
            getSession().getTransaction().commit();
        } catch (HibernateException ex) {
            System.err.println(concatena.delete(0, concatena.length()).append(msgError).append("getEmpleadoPorClaveYRazon()1_Error: ").append(ex));
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

    public Mensaje DeleteEmpleado(Empleados entity, String uuidCxn) {
        inicializaVariableMensaje();
        setSession(HibernateUtil.currentSession(uuidCxn));
        Transaction transaction = null;
        try {
            transaction = getSession().beginTransaction();
            deleteListQuery(getSession(), "Familiares", "empleados_id", entity.getId(), uuidCxn);
            deleteListQuery(getSession(), "FormacionEconomica", "empleados_id", entity.getId(), uuidCxn);
            deleteListQuery(getSession(), "Capacitaciones", "empleados_id", entity.getId(), uuidCxn);
            deleteListQuery(getSession(), "ExperienciaLaboralExterna", "empleados_id", entity.getId(), uuidCxn);
            deleteListQuery(getSession(), "ExperienciaLaboralInterna", "empleados_id", entity.getId(), uuidCxn);
            deleteListQuery(getSession(), "Documentacion", "empleados_id", entity.getId(), uuidCxn);
            deleteListQueryVacaciones(getSession(), entity.getId());
            deleteListQuery(getSession(), "Empleados", "id", entity.getId(), uuidCxn);
            mensajeResultado.setResultado(true);
            mensajeResultado.setNoError(0);
            mensajeResultado.setError("");
            transaction.commit();
        } catch (HibernateException ex) {
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

    public Mensaje UpdateEmpleado(List<Familiares> agrega, Object[] eliminados,
            List<FormacionEconomica> agregaFE, Object[] eliminadosFE,
            List<Capacitaciones> agregaCap, Object[] eliminadosCap,
            List<ExperienciaLaboralExterna> agregaELE, Object[] eliminadosELE,
            List<ExperienciaLaboralInterna> agregaELI, Object[] eliminadosELI,
            List<Documentacion> agregaDoc, Object[] eliminadosDoc, List<VacacionesAplicacion> agregaVac, Empleados empleados,
            List<PlazasPorEmpleadosMov> listPlazasPorEmpleadoMov, IngresosReingresosBajas ingresosReingresosBajas, boolean calcularSDI, SalariosIntegrados salariosIntegrados, String uuidCxn) {//JSA01

        inicializaVariableMensaje();
        setSession(HibernateUtil.currentSession(uuidCxn));
        Transaction transaction = null;
        try {
            transaction = getSession().beginTransaction();
            if (eliminados.length > 0) {
                deleteListQueryIn(getSession(), "Familiares", "id", eliminados, uuidCxn);
            }
            if (eliminadosFE.length > 0) {
                deleteListQueryIn(getSession(), "FormacionEconomica", "id", eliminadosFE, uuidCxn);
            }
            if (eliminadosCap.length > 0) {
                deleteListQueryIn(getSession(), "Capacitaciones", "id", eliminadosCap, uuidCxn);
            }
            if (eliminadosELE.length > 0) {
                deleteListQueryIn(getSession(), "ExperienciaLaboralExterna", "id", eliminadosELE, uuidCxn);
            }
            if (eliminadosELI.length > 0) {
                deleteListQueryIn(getSession(), "ExperienciaLaboralInterna", "id", eliminadosELI, uuidCxn);
            }
            if (eliminadosDoc.length > 0) {
                deleteListQueryIn(getSession(), "Documentacion", "id", eliminadosDoc, uuidCxn);
            }
            if (empleados != null && (listPlazasPorEmpleadoMov == null ? true : listPlazasPorEmpleadoMov.isEmpty())) {
                getSession().saveOrUpdate(empleados);
            }

            int i;
//            //Plazas por empleado
            if (listPlazasPorEmpleadoMov != null) {
                //Plazas por empleado
                for (i = 0; i < listPlazasPorEmpleadoMov.size(); i++) {
//                listPlazasPorEmpleadoMov.get(i).setPlazasPorEmpleado(listPlazasPorEmpleados.get(i));
                    getSession().saveOrUpdate(listPlazasPorEmpleadoMov.get(i));
                    if (i % 100 == 0 && i > 0) {
                        getSession().flush();
                    }
                }
                if (empleados == null) {
                    empleados = listPlazasPorEmpleadoMov.get(0).getPlazasPorEmpleado().getEmpleados();
                }
            }
            if (ingresosReingresosBajas != null) {
                if (listPlazasPorEmpleadoMov != null) {
                    for (i = 0; i < listPlazasPorEmpleadoMov.size(); i++) {
                        if (listPlazasPorEmpleadoMov.get(i).getPlazasPorEmpleado().getClave().equalsIgnoreCase(ingresosReingresosBajas.getPlazasPorEmpleado().getClave())) {
                            ingresosReingresosBajas.setPlazasPorEmpleado(listPlazasPorEmpleadoMov.get(i).getPlazasPorEmpleado());
                            break;
                        }
                    }
                }
                getSession().saveOrUpdate(ingresosReingresosBajas);
            }

////            if (calcularSDI) {//JSA01
////                //Mandar a llamar el metodo del calcula DSI
////            } else {
////                if (salariosIntegrados != null) {
////                    getSession().saveOrUpdate(salariosIntegrados);
////                }
////            }
////            System.out.println("CalcularSDI = " + calcularSDI);
            if (salariosIntegrados != null) {//JSA01
                salariosIntegrados.setEmpleados(empleados);
                getSession().saveOrUpdate(salariosIntegrados);
            }

            //Empleados entity = listPlazasPorEmpleadoMov.get(0).getPlazasPorEmpleado().getEmpleados();
            for (i = 0; i < agrega.size(); i++) {
                agrega.get(i).setEmpleados(empleados);
                getSession().saveOrUpdate(agrega.get(i));
                if (i % 100 == 0) {
                    getSession().flush();
                }
            }
            for (i = 0; i < agregaFE.size(); i++) {
                agregaFE.get(i).setEmpleados(empleados);
                getSession().saveOrUpdate(agregaFE.get(i));
                if (i % 100 == 0 && i > 0) {
                    getSession().flush();
                }
            }
            for (i = 0; i < agregaCap.size(); i++) {
                agregaCap.get(i).setEmpleados(empleados);
                getSession().saveOrUpdate(agregaCap.get(i));
                if (i % 100 == 0 && i > 0) {
                    getSession().flush();
                }
            }
            for (i = 0; i < agregaELE.size(); i++) {
                agregaELE.get(i).setEmpleados(empleados);
                getSession().saveOrUpdate(agregaELE.get(i));
                if (i % 100 == 0 && i > 0) {
                    getSession().flush();
                }
            }
            for (i = 0; i < agregaELI.size(); i++) {
                agregaELI.get(i).setEmpleados(empleados);
                getSession().saveOrUpdate(agregaELI.get(i));
                if (i % 100 == 0 && i > 0) {
                    getSession().flush();
                }
            }
            for (i = 0; i < agregaDoc.size(); i++) {
                agregaDoc.get(i).setEmpleados(empleados);
                getSession().saveOrUpdate(agregaDoc.get(i));
                if (i % 100 == 0 && i > 0) {
                    getSession().flush();
                }
            }

            for (i = 0; i < agregaVac.size(); i++) {
                agregaVac.get(i).getVacacionesDisfrutadas().setEmpleados(empleados);
                getSession().saveOrUpdate(agregaVac.get(i));
                if (i % 100 == 0 && i > 0) {
                    getSession().flush();
                }
            }
            mensajeResultado.setResultado(true);
            mensajeResultado.setNoError(0);
            mensajeResultado.setError("");
            transaction.commit();
        } catch (HibernateException ex) {
            System.err.println(concatena.delete(0, concatena.length()).append(msgError).append("UpdateEmpleado()1_Error: ").append(ex));
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

    public Mensaje existeRFC(String rfc, String claveEmpleado, String uuidCxn) {//JSA02
        Long cantidad;
        Boolean existe = false;
        try {
            inicializaVariableMensaje();
            setSession(HibernateUtil.currentSession(uuidCxn));
            getSession().beginTransaction();
            if (claveEmpleado != null) {
                q = getSession().createQuery("select count(e) from Empleados e where e.RFC = :rfc  and e.clave != :claveEmpleado");
                q.setParameter("rfc", rfc);
                q.setParameter("claveEmpleado", claveEmpleado);
            } else {
                q = getSession().createQuery("select count(e) from Empleados e where e.RFC = :rfc");
                q.setParameter("rfc", rfc);
            }

            cantidad = (Long) q.uniqueResult();
            if (cantidad > 0) {
                existe = true;
            }
            mensajeResultado.setResultado(existe);
            mensajeResultado.setNoError(0);
            mensajeResultado.setError("");
            getSession().getTransaction().commit();
        } catch (HibernateException ex) {
            System.err.println(concatena.delete(0, concatena.length()).append(msgError).append("existeRFC()1_Error: ").append(ex));
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
