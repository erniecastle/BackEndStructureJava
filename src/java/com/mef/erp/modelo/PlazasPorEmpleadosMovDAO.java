/**
 * @author: Victor Fecha de Creación: 15/06/2012 Compañía:MacroPro Descripción
 * del programa: PlazasPorEmpleadosMovDAO
 * -----------------------------------------------------------------------------
 * MODIFICACIONES:
 * -----------------------------------------------------------------------------
 * Clave: JEVC
 * @author: Ernesto c. Fecha: 25/06/2012 Compañía: Exito Descripción:se agrego
 * Metodo getPlazasPorEmpleadosMovPorRazonSocialPorEmpleadoPorActivo
 * -----------------------------------------------------------------------------
 * Clave:JSA01 Autor:ARMANDO Fecha:04/08/2012 Descripcion:Se cambiaron estos
 * metodos de ubicacion (se cambio a plazasporempleado) y nombre de estos
 * metodos por lo largo del nombre:
 * getPlazasPorEmpleadosMovPorEmpleadoPorRazonSocialPorFechaFinalPorFechaInicialPorTipoNomina
 * getPlazasPorEmpleadosMovPorEmpleadoPorRazonSocialPorFechaFinalPorFechaInicialPorTipoNominaPorReferencia
 * Por: getPlazasPorEmpleadosActivos getPlazasPorEmpleadosPorReferenciaActiva
 * los parametros son los filtros no es necesario poner todos solo ponerle el
 * nombre de acuerdo a la funcion que va hacer el metodo o lo que te va
 * regresar.
 * -----------------------------------------------------------------------------
 * Clave:JSA02 Autor:ARMANDO Fecha:06/06/2014 Descripcion:Se agrego el codigo
 * para obtener los calculos de las unidades y asi poder eliminar los
 * movimientos.
 * -----------------------------------------------------------------------------
 */
package com.mef.erp.modelo;

import com.mef.erp.modelo.entidad.Mensaje;
import com.mef.erp.modelo.entidad.PlazasPorEmpleado;
import com.mef.erp.modelo.entidad.PlazasPorEmpleadosMov;
import com.mef.erp.modelo.entidad.SalariosIntegrados;
import com.mef.erp.modelo.entidad.cfdi.CFDIEmpleado;
import com.mef.erp.util.ControlErroresHibernate;
import com.mef.erp.util.HibernateUtil;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author
 */
public class PlazasPorEmpleadosMovDAO extends GenericHibernateDAO<PlazasPorEmpleadosMov, Long>
        implements PlazasPorEmpleadosMovDAOIF {

    private StringBuilder concatena = new StringBuilder(200);
    private final String msgError = concatena.append("ServerERP"/**
     * util.getContexto()
     */
    ).append(".MSERR_C_").append(this.getClass().getName()).append(".").toString();
    private List<PlazasPorEmpleadosMov> listEsp = new ArrayList<PlazasPorEmpleadosMov>();
    private StringBuilder strQuery = new StringBuilder();

    public Mensaje agregar(PlazasPorEmpleadosMov entity, SalariosIntegrados salariosIntegrados, String uuidCxn) {
        PlazasPorEmpleadosMov pem;
        try {
            inicializaVariableMensaje();
            setSession(HibernateUtil.currentSession(uuidCxn));
            Transaction transaction = null;
            transaction = getSession().beginTransaction();
            pem = makePersistent(entity);
            mensajeResultado.setResultado(pem);
            mensajeResultado.setNoError(0);
            mensajeResultado.setError("");
            if (salariosIntegrados != null) {
                getSession().saveOrUpdate(salariosIntegrados);
            }
            transaction.commit();
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

    public Mensaje actualizar(PlazasPorEmpleadosMov entity, SalariosIntegrados salariosIntegrados, String uuidCxn) {
        try {
            inicializaVariableMensaje();
            setSession(HibernateUtil.currentSession(uuidCxn));
            Transaction transaction = null;
            transaction = getSession().beginTransaction();
            makePersistent(entity);
            mensajeResultado.setResultado(true);
            mensajeResultado.setNoError(0);
            mensajeResultado.setError("");
            if (salariosIntegrados != null) {
                getSession().saveOrUpdate(salariosIntegrados);
            }
            transaction.commit();
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

    @Override
    public Mensaje eliminar(PlazasPorEmpleadosMov entity, String uuidCxn) {//JEVC01
        try {
            strQuery = new StringBuilder();
            inicializaVariableMensaje();
            setSession(HibernateUtil.currentSession(uuidCxn));
            getSession().beginTransaction();
            PlazasPorEmpleado plaza = entity.getPlazasPorEmpleado();
            strQuery.append("select count(*) from CFDIEmpleado o where o.razonesSociales.clave = :razonSocial ");
            strQuery.append("and o.plazaPorEmpleadoMov.plazasPorEmpleado.id = :plazaPorEmpleado ");
            strQuery.append("and o.cfdiRecibo.statusTimbrado IN(0,1)");
            q = getSession().createQuery(strQuery.toString());
            q.setParameter("razonSocial", plaza.getRazonesSociales().getClave());
            q.setParameter("plazaPorEmpleado", plaza.getId());
            Long countTimbres = (Long) q.uniqueResult();
            if (countTimbres > 0) {//Verificamos si esta plaza tiene timbres cancelados o timbrados
                mensajeResultado.setResultado("Timbre");
                if (getSession().getTransaction().isActive()) {
                    getSession().getTransaction().rollback();
                }
            } else {
                int periodo = existePeriodoCerradoEnPlaza(entity);
                if (periodo == -1) {
                    getSession().getTransaction().rollback();
                } else if (periodo > 0) {//Verificamos que la plaza no tenga periodos cerrados
                    mensajeResultado.setResultado("Periodo");
                    if (getSession().getTransaction().isActive()) {
                        getSession().getTransaction().rollback();
                    }
                } else {
                    q = getSession().createQuery("select mov.id FROM MovNomConcep mov INNER JOIN mov.plazasPorEmpleado pl WHERE pl.id = :plaza ");
                    q.setParameter("plaza", plaza.getId());//Verificamos que no tenga movimientos de Nomina
                    List<Long> movEliminar = q.list();

                    List<PlazasPorEmpleadosMov> value;
                    if (movEliminar.isEmpty()) {
                        Object o = obtenerMovimientosDePlaza(plaza.getClave(), plaza.getRazonesSociales().getClave(), true, 1);
                        if (o instanceof List) {
                            value = (List<PlazasPorEmpleadosMov>) o;
                            if (!value.isEmpty()) {
                                Date fechaEliminaSalario = value.get(0).getFechaIMSS();
                                entity.setFechaIMSS(fechaEliminaSalario);
                            }
                        } else {
                            if (getSession().getTransaction().isActive()) {
                                getSession().getTransaction().rollback();
                            }
                        }

                        if (eliminarPlazasPorEmpleadoMovimiento(entity) > -1) {//Eliminado de plaza total
                            mensajeResultado.setResultado("Eliminado");
                            getSession().getTransaction().commit();
                        } else {
                            getSession().getTransaction().rollback();
                        }
                    } else {
                        mensajeResultado.setResultado(movEliminar);
                        getSession().getTransaction().rollback();
                    }
                }
            }
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

    @Override
    public Mensaje eliminarMovimientosPorPlaza(PlazasPorEmpleadosMov entity, List<Long> movimientos, String uuidCxn
    ) {
        try {
            inicializaVariableMensaje();
            Session s = HibernateUtil.currentSession(uuidCxn);
            setSession(s);
            getSession().beginTransaction();
            PlazasPorEmpleado plaza = entity.getPlazasPorEmpleado();
            MovimientosNominaDAO movDAO = new MovimientosNominaDAO();
            movDAO.setSession(s);
            strQuery.delete(0, strQuery.length());//Extrae CFDIEmpleados por la plaza seleccionada
            strQuery.append("from CFDIEmpleado o where o.razonesSociales.clave = :razonSocial ");
            strQuery.append("and o.plazaPorEmpleadoMov.plazasPorEmpleado.id = :plazaPorEmpleado ");
            strQuery.append("and o.cfdiRecibo.statusTimbrado IN(2,3)");
            q = getSession().createQuery(strQuery.toString());
            q.setParameter("razonSocial", plaza.getRazonesSociales().getClave());
            q.setParameter("plazaPorEmpleado", plaza.getId());
            List<CFDIEmpleado> clavesCFDIEmpleado = q.list();
            List<String> camposFiltroCalculoUnidades = new ArrayList<String>();//JSA02
            List<Object> valoresFiltroCalculoUnidades = new ArrayList<Object>();
            camposFiltroCalculoUnidades.add("razonesSociales.clave");
            valoresFiltroCalculoUnidades.add(entity.getPlazasPorEmpleado().getRazonesSociales().getClave());
            camposFiltroCalculoUnidades.add("empleado.id");
            valoresFiltroCalculoUnidades.add(entity.getPlazasPorEmpleado().getEmpleados().getId());
            camposFiltroCalculoUnidades.add("tipoNomina.id");
            valoresFiltroCalculoUnidades.add(entity.getTipoNomina().getId());
            camposFiltroCalculoUnidades.add("plazasPorEmpleado.id");
            valoresFiltroCalculoUnidades.add(entity.getPlazasPorEmpleado().getId());
            List<Long> idCalculoUnidades;
            Mensaje mensajeDB = movDAO.getCalculosUnidadesPorFiltroEspecificoConsulta(camposFiltroCalculoUnidades.toArray(new String[]{}), valoresFiltroCalculoUnidades.toArray(), null);
            if (mensajeDB.getNoError() == 0) {
                idCalculoUnidades = (List<Long>) mensajeDB.getResultado();
            } else {
                if (getSession().getTransaction().isActive()) {
                    getSession().getTransaction().rollback();
                }
                return mensajeDB;
            }

            int val = movDAO.deleteListQueryMov("MovNomConcep", "id", movimientos.toArray(), clavesCFDIEmpleado, idCalculoUnidades.toArray(), null, true);
            if (val == -1) {
                getSession().getTransaction().rollback();
            } else {
                if (eliminarPlazasPorEmpleadoMovimiento(entity) > -1) {
                    mensajeResultado.setResultado("Eliminado");
                    getSession().getTransaction().commit();
                } else {
                    getSession().getTransaction().rollback();
                }
            }
        } catch (HibernateException ex) {
            System.err.println(concatena.delete(0, concatena.length()).append(msgError).append("eliminarMovimientosPorPlaza()1_Error: ").append(ex));
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

    @Override
    public Mensaje eliminarPlazasMovimientos(PlazasPorEmpleadosMov entity, String uuidCxn
    ) {
        try {
            strQuery = new StringBuilder();
            inicializaVariableMensaje();
            setSession(HibernateUtil.currentSession(uuidCxn));
            getSession().beginTransaction();
            PlazasPorEmpleado plaza = entity.getPlazasPorEmpleado();
            List<PlazasPorEmpleadosMov> value;
            Object o = obtenerMovimientosDePlaza(plaza.getClave(), plaza.getRazonesSociales().getClave(), false, 1);
            if (o instanceof List) {
                value = (List<PlazasPorEmpleadosMov>) o;
                if (!value.isEmpty()) {
                    if (entity.getId().equals(value.get(0).getId())) {
                        strQuery.delete(0, strQuery.length());
                        strQuery.append("select count(*) from CFDIEmpleado o where o.razonesSociales.clave = :razonSocial ");
                        strQuery.append("and o.plazaPorEmpleadoMov.id = :plazaPorEmpleadoMov ");
                        strQuery.append("and o.cfdiRecibo.statusTimbrado IN(0,1)");
                        q = getSession().createQuery(strQuery.toString());
                        q.setParameter("razonSocial", plaza.getRazonesSociales().getClave());
                        q.setParameter("plazaPorEmpleadoMov", entity.getId());
                        Long countTimbres = (Long) q.uniqueResult();
                        if (countTimbres > 0) {
                            mensajeResultado.setResultado("Timbre");
                            getSession().getTransaction().rollback();
                        } else {
                            //si no tiene movimientos se puede eliminar
                            //si tiene movimientos los periodos de ese mov estan cerrados
                            strQuery.delete(0, strQuery.length());
                            strQuery.append("select count(*) from MovNomConcep mov, ");
                            strQuery.append("PlazasPorEmpleadosMov pm ");
                            strQuery.append("where mov.plazasPorEmpleado.id = pm.plazasPorEmpleado.id ");
                            strQuery.append("and pm.id = :idPlazaMov  and mov.fechaIni >= pm.fechaInicial ");
                            q = getSession().createQuery(strQuery.toString());
                            q.setParameter("idPlazaMov", entity.getId());
                            Long movCalculados = (Long) q.uniqueResult();
                            if (movCalculados > 0) {
                                int periodo = existePeriodoCerradoEnPlaza(entity);
                                if (periodo == -1) {
                                    getSession().getTransaction().rollback();
                                } else if (periodo > 0) {
                                    mensajeResultado.setResultado("Periodo");
                                    if (getSession().getTransaction().isActive()) {
                                        getSession().getTransaction().rollback();
                                    }
                                } else {
                                    mensajeResultado.setResultado("Movimientos");
                                    if (getSession().getTransaction().isActive()) {
                                        getSession().getTransaction().rollback();
                                    }
                                }
                            } else {
                                boolean exito = true;
                                try {//Probar la fecha´por que esta eliminando todos los salarios
                                    deleteListQuery("PlazasPorEmpleadosMov", "id", new Object[]{entity.getId()});
                                    deleteListQueryConFiltrado("SalariosIntegrados", "empleados.id", new Object[]{plaza.getEmpleados().getId()},
                                            new String[]{"fecha#>=", "registroPatronal.id"}, new Object[]{entity.getFechaInicial(), plaza.getRegistroPatronal().getId()});
                                } catch (HibernateException e) {
                                    mensajeResultado.setNoError(ControlErroresHibernate.buscaNoErrorPorExcepcion(e.getClass()));
                                    mensajeResultado.setError(e.getLocalizedMessage());
                                    exito = false;
                                }
                                if (exito) {
                                    mensajeResultado.setResultado("Eliminado");
                                    getSession().getTransaction().commit();
                                } else {
                                    if (getSession().getTransaction().isActive()) {
                                        getSession().getTransaction().rollback();
                                    }
                                }
                            }
                        }
                    } else {
                        mensajeResultado.setResultado("NoUltimo");
                        if (getSession().getTransaction().isActive()) {
                            getSession().getTransaction().rollback();
                        }
                    }
                } else {
                    mensajeResultado.setResultado("Eliminado");
                    if (getSession().getTransaction().isActive()) {
                        getSession().getTransaction().rollback();
                    }
                }
            } else {
                if (getSession().getTransaction().isActive()) {
                    getSession().getTransaction().rollback();
                }
            }
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

    @Override
    public Mensaje getPlazasPorEmpleadosMovMaxPorClave(String clave, String razonSocial, String uuidCxn
    ) {
        PlazasPorEmpleadosMov r;
        try {
            inicializaVariableMensaje();
            setSession(HibernateUtil.currentSession(uuidCxn));
            getSession().beginTransaction();
            consulta = new StringBuilder(" from PlazasPorEmpleadosMov p Where p.plazasPorEmpleado.clave =:clave ");
            consulta.append("AND p.plazasPorEmpleado.razonesSociales.clave = :claveRazonSocial ");
            consulta.append(" AND p.fechaInicial = ");
            consulta.append(" ( Select max(pm.fechaInicial) from PlazasPorEmpleadosMov pm Where pm.plazasPorEmpleado.clave = :clave ");
            consulta.append(" AND pm.plazasPorEmpleado.razonesSociales.clave =:claveRazonSocial and pm.fechaInicial <= :fechaInicial ) ");

            q = getSession().createQuery(consulta.toString());
            q.setString("clave", clave);
            q.setString("claveRazonSocial", razonSocial);
            q.setDate("fechaInicial", Calendar.getInstance().getTime());
            r = (PlazasPorEmpleadosMov) q.uniqueResult();
            mensajeResultado.setResultado(r);
            mensajeResultado.setNoError(0);
            mensajeResultado.setError("");
            getSession().getTransaction().commit();
        } catch (HibernateException ex) {
            System.err.println(concatena.delete(0, concatena.length()).append(msgError).append("getPlazasPorEmpleadosMovMaxPorClave()1_Error: ").append(ex));
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

    @Override
    public Mensaje getPlazasPorEmpleadosMovMaxPorEmpleado(String claveEmpleado, String razonSocial, String uuidCxn
    ) {
        PlazasPorEmpleadosMov r;
        try {
            inicializaVariableMensaje();
            setSession(HibernateUtil.currentSession(uuidCxn));
            getSession().beginTransaction();
            consulta = new StringBuilder(" from PlazasPorEmpleadosMov p Where p.plazasPorEmpleado.empleados.clave =:claveEmpleado ");
            consulta.append("AND p.plazasPorEmpleado.razonesSociales.clave = :claveRazonSocial ");
            consulta.append(" AND p.fechaInicial = ");
            consulta.append(" (Select max(pm.fechaInicial) from PlazasPorEmpleadosMov pm Where pm.plazasPorEmpleado.empleados.clave = :claveEmpleado ");
            consulta.append(" AND pm.plazasPorEmpleado.razonesSociales.clave =:claveRazonSocial ) ");
            q = getSession().createQuery(consulta.toString());
            q.setString("claveEmpleado", claveEmpleado);
            q.setString("claveRazonSocial", razonSocial);
            r = (PlazasPorEmpleadosMov) q.uniqueResult();
            mensajeResultado.setResultado(r);
            mensajeResultado.setNoError(0);
            mensajeResultado.setError("");
            getSession().getTransaction().commit();
        } catch (HibernateException ex) {
            System.err.println(concatena.delete(0, concatena.length()).append(msgError).append("getPlazasPorEmpleadosMovMaxPorEmpleado()1_Error: ").append(ex));
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

    @Override
    public Mensaje getPlazasPorEmpleadosMovPorRazonSocial(String clave, String uuidCxn
    ) {
        List<PlazasPorEmpleadosMov> values;
        try {
            inicializaVariableMensaje();
            setSession(HibernateUtil.currentSession(uuidCxn));
            getSession().beginTransaction();
            q = getSession().createQuery("Select pm from PlazasPorEmpleadosMov pm Inner Join pm.plazasPorEmpleado pe Inner Join pe.razonesSociales rs Where rs.clave = :clave");
            q.setString("clave", clave);
            values = q.list();
            mensajeResultado.setResultado(values);
            mensajeResultado.setNoError(0);
            mensajeResultado.setError("");
            getSession().getTransaction().commit();
        } catch (HibernateException ex) {
            System.err.println(concatena.delete(0, concatena.length()).append(msgError).append("getPlazasPorEmpleadosMovPorRazonSocial()1_Error: ").append(ex));
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

    @Override
    public Mensaje getPlazasPorEmpleadosMovPorReferencia(String referencia, String claveRazonesSociales, Integer result, String uuidCxn
    ) {
        List<PlazasPorEmpleadosMov> values;
        try {
            inicializaVariableMensaje();
            setSession(HibernateUtil.currentSession(uuidCxn));
            getSession().beginTransaction();
            Object o = obtenerMovimientosDePlaza(referencia, claveRazonesSociales, false, result);
            if (o instanceof List) {
                values = (List<PlazasPorEmpleadosMov>) o;
                mensajeResultado.setResultado(values);
                mensajeResultado.setNoError(0);
                mensajeResultado.setError("");
                getSession().getTransaction().commit();
            } else {
                if (getSession().getTransaction().isActive()) {
                    getSession().getTransaction().rollback();
                }
            }
        } catch (HibernateException ex) {
            System.err.println(concatena.delete(0, concatena.length()).append(msgError).append("getPlazasPorEmpleadosMovPorReferencia()1_Error: ").append(ex));
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

    @Override
    public Mensaje getPlazasPorEmpleadosMovAnterior(Long id, String referencia, String claveRazonesSociales, Integer result, String uuidCxn
    ) {
        List<PlazasPorEmpleadosMov> values;
        try {
            //--------------------------
            inicializaVariableMensaje();
            setSession(HibernateUtil.currentSession(uuidCxn));
            getSession().beginTransaction();
            consulta = new StringBuilder("from PlazasPorEmpleadosMov  o where o.plazasPorEmpleado.clave= :referencia  and o.id < :id and o.plazasPorEmpleado.razonesSociales.clave =:claveRazonSocial order by id desc");
            q = getSession().createQuery(consulta.toString());
            q.setString("referencia", referencia);
            q.setLong("id", id);
            q.setString("claveRazonSocial", claveRazonesSociales);
            if (result != null) {
                q.setMaxResults(result);
            }
            values = q.list();
            mensajeResultado.setResultado(values);
            mensajeResultado.setNoError(0);
            mensajeResultado.setError("");
            getSession().getTransaction().commit();
        } catch (HibernateException ex) {
            System.err.println(concatena.delete(0, concatena.length()).append(msgError).append("getPlazasPorEmpleadosMovAnterior()1_Error: ").append(ex));
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

    @Override
    public Mensaje getPorEmpleadoYRazonSocialVigente(String claveEmpleado, String claveRazonSocial, String uuidCxn
    ) {
        List<PlazasPorEmpleadosMov> values;
        try {
            inicializaVariableMensaje();
            setSession(HibernateUtil.currentSession(uuidCxn));
            getSession().beginTransaction();
            consulta = new StringBuilder("FROM PlazasPorEmpleadosMov o ");
            consulta.append("WHERE o.id IN (SELECT MAX(m.id) FROM PlazasPorEmpleadosMov m WHERE m.plazasPorEmpleado.empleados.clave=:claveEmpleado AND m.plazasPorEmpleado.razonesSociales.clave =:claveRazonSocial ").append("AND :fechaActual BETWEEN  m.fechaInicial AND m.plazasPorEmpleado.fechaFinal + 1 GROUP BY m.plazasPorEmpleado.clave) ").append("ORDER BY o.plazasPorEmpleado.clave");
            q = getSession().createQuery(consulta.toString());
            q.setParameter("fechaActual", getFechaDelSistema().getTime());
            q.setParameter("claveEmpleado", claveEmpleado);
            q.setParameter("claveRazonSocial", claveRazonSocial);
            values = q.list();
            mensajeResultado.setResultado(values);
            mensajeResultado.setNoError(0);
            mensajeResultado.setError("");
            getSession().getTransaction().commit();
        } catch (HibernateException ex) {
            System.err.println(concatena.delete(0, concatena.length()).append(msgError).append("getPorEmpleadoYRazonSocialVigente()1_Error: ").append(ex));
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

    public Mensaje getPorEmpleadoYRazonSocialFiniquitoVigente(String claveEmpleado, String claveRazonSocial, String claveFiniquito, String uuidCxn) {
        List<PlazasPorEmpleadosMov> values;
        try {
            inicializaVariableMensaje();
            setSession(HibernateUtil.currentSession(uuidCxn));
            getSession().beginTransaction();
            consulta = new StringBuilder("FROM PlazasPorEmpleadosMov o ");
            consulta.append("WHERE o.id IN (SELECT MAX(m.id) FROM PlazasPorEmpleadosMov m WHERE m.plazasPorEmpleado.empleados.clave=:claveEmpleado AND m.plazasPorEmpleado.razonesSociales.clave =:claveRazonSocial ").append("AND fp.finiquitosLiquidacion.clave = :claveFiniquito AND fp.plazasPorEmpleado.clave = m.plazasPorEmpleado.clave GROUP BY m.plazasPorEmpleado.clave) ").append("ORDER BY o.plazasPorEmpleado.clave");
            q = getSession().createQuery(consulta.toString());
            q.setParameter("claveEmpleado", claveEmpleado);
            q.setParameter("claveRazonSocial", claveRazonSocial);
            q.setParameter("claveFiniquito", claveFiniquito);
            values = q.list();
            mensajeResultado.setResultado(values);
            mensajeResultado.setNoError(0);
            mensajeResultado.setError("");
            getSession().getTransaction().commit();
        } catch (HibernateException ex) {
            System.err.println(concatena.delete(0, concatena.length()).append(msgError).append("getPorEmpleadoYRazonSocialFiniquitoVigente()1_Error: ").append(ex));
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

    public Mensaje getPorEmpleadoYRazonSocial(String claveEmpleado, String claveRazonSocial, String uuidCxn) {
        List<PlazasPorEmpleadosMov> values;
        try {
            inicializaVariableMensaje();
            setSession(HibernateUtil.currentSession(uuidCxn));
            getSession().beginTransaction();
            consulta = new StringBuilder("FROM PlazasPorEmpleadosMov o ");
            consulta.append("WHERE o.id IN (SELECT MAX(m.id) FROM PlazasPorEmpleadosMov m WHERE m.plazasPorEmpleado.empleados.clave=:claveEmpleado AND m.plazasPorEmpleado.razonesSociales.clave =:claveRazonSocial ").append("GROUP BY m.plazasPorEmpleado.clave) ").append("ORDER BY o.plazasPorEmpleado.clave");
            q = getSession().createQuery(consulta.toString());
            q.setParameter("claveEmpleado", claveEmpleado);
            q.setParameter("claveRazonSocial", claveRazonSocial);
            values = q.list();
            mensajeResultado.setResultado(values);
            mensajeResultado.setNoError(0);
            mensajeResultado.setError("");
            getSession().getTransaction().commit();
        } catch (HibernateException ex) {
            System.err.println(concatena.delete(0, concatena.length()).append(msgError).append("getPorEmpleadoYRazonSocial()1_Error: ").append(ex));
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

    public Mensaje getPorEmpleadoYRazonSocialYFecha(String claveEmpleado, String claveRazonSocial, Date fecha, String uuidCxn) {
        List<PlazasPorEmpleadosMov> values;
        try {
            inicializaVariableMensaje();
            setSession(HibernateUtil.currentSession(uuidCxn));
            getSession().beginTransaction();
            consulta = new StringBuilder("FROM PlazasPorEmpleadosMov o ");
            consulta.append("WHERE o.id IN (SELECT MAX(o.id) from PlazasPorEmpleadosMov  o where o.plazasPorEmpleado.id IN ");
            consulta.append("(SELECT o.id FROM PlazasPorEmpleado o where o.empleados.clave=:claveEmpleado and o.razonesSociales.clave=:claveRazonSocial");
            consulta.append(" and o.fechaFinal >=:fecha) GROUP BY o.plazasPorEmpleado.id)");
            q = getSession().createQuery(consulta.toString());
            q.setParameter("claveEmpleado", claveEmpleado);
            q.setParameter("claveRazonSocial", claveRazonSocial);
            q.setParameter("fecha", fecha);
            values = q.list();
            mensajeResultado.setResultado(values);
            mensajeResultado.setNoError(0);
            mensajeResultado.setError("");
            getSession().getTransaction().commit();
        } catch (HibernateException ex) {
            System.err.println(concatena.delete(0, concatena.length()).append(msgError).append("getPorEmpleadoYRazonSocialYFecha()1_Error: ").append(ex));
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

    public Mensaje getCantidadPlazasPorEmpleado(String claveEmpleado, String claveRazonSocial, String uuidCxn) {
        Long values = 0L;
        try {
            inicializaVariableMensaje();
            setSession(HibernateUtil.currentSession(uuidCxn));
            getSession().beginTransaction();
            consulta = new StringBuilder("SELECT COUNT(o) FROM PlazasPorEmpleadosMov o ");
            consulta.append("WHERE o.id IN (SELECT MAX(m.id) FROM PlazasPorEmpleadosMov m WHERE m.plazasPorEmpleado.empleados.clave=:claveEmpleado AND m.plazasPorEmpleado.razonesSociales.clave =:claveRazonSocial ").append("GROUP BY m.plazasPorEmpleado.clave) ");
            q = getSession().createQuery(consulta.toString());
            q.setParameter("claveEmpleado", claveEmpleado);
            q.setParameter("claveRazonSocial", claveRazonSocial);
            values = (Long) q.uniqueResult();
            mensajeResultado.setResultado(values == null ? 0 : values.intValue());
            mensajeResultado.setNoError(0);
            mensajeResultado.setError("");
            getSession().getTransaction().commit();
        } catch (HibernateException ex) {
            System.err.println(concatena.delete(0, concatena.length()).append(msgError).append("getCantidadPlazasPorEmpleado()1_Error: ").append(ex));
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
            mensajeResultado.setResultado(0);
        }
        return mensajeResultado;
    }

    public Mensaje consultaPorRangos(Integer inicio, Integer rango, String uuidCxn) {
        try {
            inicializaVariableMensaje();
            setSession(HibernateUtil.currentSession(uuidCxn));
            getSession().beginTransaction();
            listEsp = (List<PlazasPorEmpleadosMov>) consultaPorRangos(inicio, rango, null, null);
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

    public Mensaje consultaPorFiltrosEmpleado(String query, Object[] campos, Object[] valores, String uuidCxn) {
        try {
            inicializaVariableMensaje();
            setSession(HibernateUtil.currentSession(uuidCxn));
            getSession().beginTransaction();
            listEsp = (List<PlazasPorEmpleadosMov>) consultaPorFiltros(query, campos, valores, null, null);
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

    public Mensaje agregarListaPlazasPorEmpleadosMovs(List<PlazasPorEmpleadosMov> entitys, int rango, String uuidCxn) {
        listEsp.clear();
        try {
            inicializaVariableMensaje();
            setSession(HibernateUtil.currentSession(uuidCxn));
            getSession().beginTransaction();
            int i = 0;
            for (i = 0; i < entitys.size(); i++) {
                if (entitys.get(i).getId() == null) {
                    listEsp.add(makePersistent(entitys.get(i)));
                } else {
                    makePersistent(entitys.get(i));
                }
                if (i % rango == 0 & i > 0) {
                    flush();
                    clear();
                }
            }
            mensajeResultado.setResultado(listEsp);
            mensajeResultado.setNoError(0);
            mensajeResultado.setError("");
            getSession().getTransaction().commit();
        } catch (HibernateException ex) {
            System.err.println(concatena.delete(0, concatena.length()).append(msgError).append("agregarListaPlazasPorEmpleadosMovs()1_Error: ").append(ex));
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
            existe = existeDato("PlazasPorEmpleadosMov", campo, valor);
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

    @Override
    public Mensaje deleteListQuerys(String tabla, String campo, Object[] valores, String uuidCxn) {
        try {
            inicializaVariableMensaje();
            setSession(HibernateUtil.currentSession(uuidCxn));
            getSession().beginTransaction();
            deleteListQuery(tabla, campo, valores);
            mensajeResultado.setResultado(true);
            mensajeResultado.setNoError(0);
            mensajeResultado.setError("");
            getSession().getTransaction().commit();
        } catch (HibernateException ex) {
            System.err.println(concatena.delete(0, concatena.length()).append(msgError).append("deleteListQuerys()1_Error: ").append(ex));
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

    private int eliminarPlazasPorEmpleadoMovimiento(PlazasPorEmpleadosMov plazaMov) {
        int exito;
        PlazasPorEmpleado plaza = plazaMov.getPlazasPorEmpleado();
        try {
            exito = deleteListQuery("PlazasPorEmpleadosMov", "plazasPorEmpleado.id", new Object[]{plaza.getId()});
            exito = deleteListQuery("IngresosReingresosBajas", "plazasPorEmpleado.id", new Object[]{plaza.getId()});
            exito = deleteListQuery("SalariosIntegradosDet", "plazasPorEmpleado.id", new Object[]{plaza.getId()});
            exito = deleteListQueryConFiltrado("SalariosIntegrados", "empleados.id", new Object[]{plaza.getEmpleados().getId()},
                    new String[]{"fecha#>=", "registroPatronal.id"}, new Object[]{plazaMov.getFechaIMSS(), plaza.getRegistroPatronal().getId()});
            exito = deleteListQuery("PlazasPorEmpleado", "id", new Object[]{plaza.getId()});

        } catch (HibernateException ex) {
            System.err.println(concatena.delete(0, concatena.length()).append(msgError).append("deleteListQueryMov()1_Error: ").append(ex));
            mensajeResultado.setNoError(ControlErroresHibernate.buscaNoErrorPorExcepcion(ex.getClass()));
            mensajeResultado.setError(ex.getLocalizedMessage());
            mensajeResultado.setResultado(null);
            exito = -1;
        }
        return exito;
    }

    /*
     inicializaVariableMensaje();
     inicializaVariableMensaje();
     setSession(HibernateUtil.currentSession(uuidCxn));
     getSession().beginTransaction();
     consulta = new StringBuilder("from PlazasPorEmpleadosMov  o where o.plazasPorEmpleado.clave= :referencia and o.plazasPorEmpleado.razonesSociales.clave = :claveRazonSocial order by id desc");
     q = getSession().createQuery(consulta.toString());
     q.setString("referencia", referencia);
     q.setString("claveRazonSocial", claveRazonesSociales);
     if (result != null) {
     q.setMaxResults(result);
     }
     values = q.list();
     mensajeResultado.setResultado(values);
     mensajeResultado.setNoError(0);
     mensajeResultado.setError("");
    
    
    
     */
    private Object obtenerMovimientosDePlaza(String referencia, String claveRazonesSociales, boolean ordenAsc, Integer result) {
        List<PlazasPorEmpleadosMov> values = null;
        try {
            strQuery.delete(0, strQuery.length());
            strQuery.append("from PlazasPorEmpleadosMov  o where o.plazasPorEmpleado.clave= :referencia ");
            strQuery.append("and o.plazasPorEmpleado.razonesSociales.clave = :claveRazonSocial ");
            if (ordenAsc) {
                strQuery.append("Order by id asc");
            } else {
                strQuery.append("Order by id desc");
            }
            q = getSession().createQuery(strQuery.toString());
            q.setString("referencia", referencia);
            q.setString("claveRazonSocial", claveRazonesSociales);
            if (result != null) {
                q.setMaxResults(result);
            }
            values = q.list();
        } catch (HibernateException ex) {
            System.err.println(concatena.delete(0, concatena.length()).append(msgError).append("obtenerUltimosMovimientosPlaza()1_Error: ").append(ex));
            mensajeResultado.setNoError(ControlErroresHibernate.buscaNoErrorPorExcepcion(ex.getClass()));
            mensajeResultado.setError(ex.getLocalizedMessage());
            mensajeResultado.setResultado(null);
            return false;
        }
        return values;
    }

    private int existePeriodoCerradoEnPlaza(PlazasPorEmpleadosMov entity) {
        int exito;
        try {
            strQuery.delete(0, strQuery.length());
            strQuery.append("select count(*) from MovNomConcep mov, PlazasPorEmpleadosMov pm ");
            strQuery.append("Left Join mov.periodosNomina p ");
            strQuery.append("where mov.plazasPorEmpleado.id = pm.plazasPorEmpleado.id ");
            strQuery.append("and pm.id = :idPlazaMov  and mov.fechaIni >= pm.fechaInicial ");
            strQuery.append("and p.tipoNomina.clave = :tipoNomina and p.status = false and p.tipoCorrida.clave = :tipoCorrida");
            q = getSession().createQuery(strQuery.toString());
            q.setParameter("idPlazaMov", entity.getId());
            q.setParameter("tipoNomina", entity.getTipoNomina().getClave());
            q.setParameter("tipoCorrida", "PER");
            Long countPeriodo = (Long) q.uniqueResult();
            exito = countPeriodo.intValue();
        } catch (HibernateException ex) {
            System.err.println(concatena.delete(0, concatena.length()).append(msgError).append("existePeriodoCerradoEnPlaza()1_Error: ").append(ex));
            mensajeResultado.setNoError(ControlErroresHibernate.buscaNoErrorPorExcepcion(ex.getClass()));
            mensajeResultado.setError(ex.getLocalizedMessage());
            mensajeResultado.setResultado(null);
            exito = -1;
        }
        return exito;
    }

    private boolean eliminaTimbres(PlazasPorEmpleado plaza) {//JEVC01
        boolean exito = true;
        try {
            strQuery.delete(0, strQuery.length());//Eliminamos timbres si no existen cancelados o timbrados
            strQuery.append("select o.id,cfdiRecibo.id from CFDIEmpleado o where o.razonesSociales.clave = :razonSocial ");
            strQuery.append("and o.plazaPorEmpleadoMov.plazasPorEmpleado.id = :plazaPorEmpleado ");
            q = getSession().createQuery(strQuery.toString());
            q.setParameter("razonSocial", plaza.getRazonesSociales().getClave());
            q.setParameter("plazaPorEmpleado", plaza.getId());
            List<Object[]> timbresEliminar = q.list();
            for (Object[] objects : timbresEliminar) {
                deleteListQuery("CFDIEmpleado", "id", new Object[]{objects[0]});
                //CFDIReciboConcepto
                strQuery.delete(0, strQuery.length());
                strQuery.append("select cfdiCnc.id from CFDIRecibo o INNER JOIN o.cfdiReciboConceptos cfdiCnc where o.id= :idRecibo ");
                q = getSession().createQuery(strQuery.toString());
                q.setParameter("idRecibo", objects[1]);
                List<Object[]> cfdiCnc = q.list();
                if (!cfdiCnc.isEmpty()) {
                    deleteListQuery("CFDIReciboConcepto", "id", cfdiCnc.toArray());
                }
                //CFDIReciboIncapacidad
                strQuery.delete(0, strQuery.length());
                strQuery.append("select cfdiInc.id from CFDIRecibo o INNER JOIN o.cfdiReciboIncapacidades cfdiInc where o.id= :idRecibo ");
                q = getSession().createQuery(strQuery.toString());
                q.setParameter("idRecibo", objects[1]);
                List<Object[]> cfdiInc = q.list();
                if (!cfdiInc.isEmpty()) {
                    deleteListQuery("CFDIReciboIncapacidad", "id", cfdiInc.toArray());
                }
                //CFDIReciboHrsExtras
                strQuery.delete(0, strQuery.length());
                strQuery.append("select cfdiHrs.id from CFDIRecibo o INNER JOIN o.cfdiReciboHrsExtras cfdiHrs  where o.id= :idRecibo ");
                q = getSession().createQuery(strQuery.toString());
                q.setParameter("idRecibo", objects[1]);
                List<Object[]> cfdiHrs = q.list();
                if (!cfdiHrs.isEmpty()) {
                    deleteListQuery("CFDIReciboHrsExtras", "id", cfdiHrs.toArray());
                }
                deleteListQuery("CFDIRecibo", "id", new Object[]{objects[1]});
            }
        } catch (HibernateException ex) {
            System.err.println(concatena.delete(0, concatena.length()).append(msgError).append("eliminaTimbres()1_Error: ").append(ex));
            mensajeResultado.setNoError(ControlErroresHibernate.buscaNoErrorPorExcepcion(ex.getClass()));
            mensajeResultado.setError(ex.getLocalizedMessage());
            mensajeResultado.setResultado(null);
            exito = false;
        }
        return exito;
    }

    public Mensaje getEmpleadosManejaPagoPorHoras(String claveTipoNomina, String claveRazonSocial, Date fechaInicial, Date fechaFinal, String uuidCxn) {
        List<PlazasPorEmpleadosMov> values;
        try {
            inicializaVariableMensaje();
            setSession(HibernateUtil.currentSession(uuidCxn));
            getSession().beginTransaction();
            consulta = new StringBuilder("Select o FROM PlazasPorEmpleadosMov o INNER JOIN o.plazasPorEmpleado ple INNER JOIN ple.empleados emp ");
            consulta.append("WHERE o.id IN (SELECT MAX(pem.id) from PlazasPorEmpleadosMov pem INNER JOIN pem.plazasPorEmpleado pe INNER JOIN pe.empleados em LEFT OUTER JOIN pem.tipoNomina t LEFT OUTER JOIN pem.puestos pu LEFT OUTER JOIN pu.categoriasPuestos cp  ");
            consulta.append("WHERE pe.razonesSociales.clave = :claveRazonSocial AND t.clave = :claveTipoNomina AND cp.pagarPorHoras = true AND ((pem.fechaInicial <= :fechaInicial) OR (pem.fechaInicial between :fechaInicial AND :fechaFinal))  ");
            consulta.append(" AND ((pe.fechaFinal >= :fechaFinal) OR (pe.fechaFinal between :fechaInicial AND :fechaFinal))  GROUP BY pe.clave) ORDER BY emp.clave");
            q = getSession().createQuery(consulta.toString());
            q.setParameter("claveTipoNomina", claveTipoNomina);
            q.setParameter("claveRazonSocial", claveRazonSocial);
            q.setParameter("fechaInicial", fechaInicial);
            q.setParameter("fechaFinal", fechaFinal);
            values = q.list();
            mensajeResultado.setResultado(values);
            mensajeResultado.setNoError(0);
            mensajeResultado.setError("");
            getSession().getTransaction().commit();
        } catch (HibernateException ex) {
            System.err.println(concatena.delete(0, concatena.length()).append(msgError).append("getEmpleadosManejaPagoPorHoras()1_Error: ").append(ex));
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
