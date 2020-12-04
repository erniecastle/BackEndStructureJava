/**
 * @author: Victor Lopez Compañía: Macropro. Descripción del programa: clase
 * FiniquitosLiquidacionesDAO para llamados a metodos de HIBERNATE
 * Fecha:04-07-2012
 * -----------------------------------------------------------------------------
 * MODIFICACIONES:
 * -----------------------------------------------------------------------------
 * Clave:JSA01 Autor:Jose Armando Fecha:20/09/2013 Descripción: Se agrego el
 * modoBaja y el TipoBaja
 * -----------------------------------------------------------------------------
 * Clave: JSA02 Autor: Armando Fecha: 11/05/2015 Descripcion:Se renombro la
 * tabla de ISRRetenido a CalculoISR para tener todos los calculos cerca y tener
 * todo lo relacionado con el resultado del calculo de la nomina en la misma
 * nomenclatura del calculo...
 * -----------------------------------------------------------------------------
 */
package com.mef.erp.modelo;

import com.mef.erp.modelo.entidad.CalculoIMSS;
import com.mef.erp.modelo.entidad.CalculoIMSSPatron;
import com.mef.erp.modelo.entidad.CalculoISR;
import com.mef.erp.modelo.entidad.FiniqLiquidCncNom;
import com.mef.erp.modelo.entidad.FiniqLiquidPlazas;
import com.mef.erp.modelo.entidad.FiniquitosLiquidaciones;
import com.mef.erp.modelo.entidad.IngresosReingresosBajas;
import com.mef.erp.modelo.entidad.Mensaje;
import com.mef.erp.modelo.entidad.ModoBaja;
import com.mef.erp.modelo.entidad.MovNomConcep;
import com.mef.erp.modelo.entidad.PeriodosNomina;
import com.mef.erp.modelo.entidad.PlazasPorEmpleado;
import com.mef.erp.modelo.entidad.PlazasPorEmpleadosMov;
import com.mef.erp.modelo.entidad.RazonesSociales;
import com.mef.erp.modelo.entidad.SalariosIntegrados;
import com.mef.erp.modelo.entidad.TipoBaja;
import com.mef.erp.modelo.entidad.TipoNomina;
import com.mef.erp.modelo.entidad.cfdi.CFDIEmpleado;
import com.mef.erp.util.ControlErroresHibernate;
import com.mef.erp.util.HibernateUtil;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Session;

public class FiniquitosLiquidacionesDAO extends GenericHibernateDAO<FiniquitosLiquidaciones, Long>
        implements FiniquitosLiquidacionesDAOIF {

    private final StringBuilder concatena = new StringBuilder(200);
    private final String msgError = concatena.append("ServerERP"/*
     * util.getContexto()
     */).append(".MSERR_C_").append(this.getClass().getName()).append(".").toString();
    private List<FiniquitosLiquidaciones> listEsp = new ArrayList<FiniquitosLiquidaciones>();
    private StringBuilder strQuery = new StringBuilder();

    public Mensaje agregar(FiniquitosLiquidaciones entity, String uuidCxn) {
        FiniquitosLiquidaciones finiquitosLiquidaciones;
        try {
            inicializaVariableMensaje();
            setSession(HibernateUtil.currentSession(uuidCxn));
            getSession().beginTransaction();
            finiquitosLiquidaciones = makePersistent(entity);
            mensajeResultado.setResultado(finiquitosLiquidaciones);
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

    public Mensaje actualizar(FiniquitosLiquidaciones entity, String uuidCxn) {
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

    public Mensaje eliminar(FiniquitosLiquidaciones entity, String uuidCxn) {
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

    public Mensaje getFiniquitosLiquidacionesAll(String claveRazonesSociales, TipoBaja tipoBaja, ModoBaja modoBaja, String uuidCxn) {//JSA01
        List<FiniquitosLiquidaciones> values;
        try {
            inicializaVariableMensaje();
            setSession(HibernateUtil.currentSession(uuidCxn));
            getSession().beginTransaction();
            q = getSession().createQuery("from FiniquitosLiquidaciones where razonesSociales.clave =:claveRazonesSociales and tipoBaja = :tipoBaja and modoBaja = :modoBaja order by clave");
            q.setParameter("claveRazonesSociales", claveRazonesSociales);
            q.setParameter("modoBaja", modoBaja);
            q.setParameter("tipoBaja", tipoBaja);
            values = q.list();
            mensajeResultado.setResultado(values);
            mensajeResultado.setNoError(0);
            mensajeResultado.setError("");
            getSession().getTransaction().commit();
        } catch (HibernateException ex) {
            System.err.println(concatena.delete(0, concatena.length()).append(msgError).append("getFiniquitosLiquidacionesAll()1_Error: ").append(ex));
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

    public Mensaje getFiniquitosLiquidacionesPorCamposClave(String referencia, RazonesSociales razonSocial, ModoBaja modoBaja, TipoBaja tipoBaja, String uuidCxn) {//JSA01
        FiniquitosLiquidaciones r;
        try {
            inicializaVariableMensaje();
            setSession(HibernateUtil.currentSession(uuidCxn));
            getSession().beginTransaction();
            q = getSession().createQuery("from FiniquitosLiquidaciones where clave = :referencia and razonesSociales = :razonSocial and modoBaja = :modoBaja and tipoBaja = :tipoBaja");
            q.setParameter("referencia", referencia);
            q.setParameter("razonSocial", razonSocial);
            q.setParameter("modoBaja", modoBaja);
            q.setParameter("tipoBaja", tipoBaja);
            r = (FiniquitosLiquidaciones) q.uniqueResult();
            mensajeResultado.setResultado(r);
            mensajeResultado.setNoError(0);
            mensajeResultado.setError("");
            getSession().getTransaction().commit();
        } catch (HibernateException ex) {
            System.err.println(concatena.delete(0, concatena.length()).append(msgError).append("getFiniquitosLiquidacionesPorClave()1_Error: ").append(ex));
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

    public Mensaje getPorEmpleado(String claveEmpleado, String claveRazonSocial, ModoBaja modoBaja, TipoBaja tipoBaja, String uuidCxn) {//JSA01
        FiniquitosLiquidaciones r = null;
        try {
            inicializaVariableMensaje();
            setSession(HibernateUtil.currentSession(uuidCxn));
            getSession().beginTransaction();
            q = getSession().createQuery("from FiniquitosLiquidaciones where empleados.clave = :claveEmpleado and razonesSociales.clave = :claveRazonSocial and modoBaja = :modoBaja and tipoBaja = :tipoBaja");
            q.setParameter("claveEmpleado", claveEmpleado);
            q.setParameter("claveRazonSocial", claveRazonSocial);
            q.setParameter("modoBaja", modoBaja);
            q.setParameter("tipoBaja", tipoBaja);
            r = (FiniquitosLiquidaciones) q.uniqueResult();
            mensajeResultado.setResultado(r);
            mensajeResultado.setNoError(0);
            mensajeResultado.setError("");
            getSession().getTransaction().commit();
        } catch (HibernateException ex) {
            System.err.println(concatena.delete(0, concatena.length()).append(msgError).append("getPorEmpleado()1_Error: ").append(ex));
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

    public Mensaje getFiniquitosLiquidacionesGuardarModificar(FiniquitosLiquidaciones finiquitosLiquidaciones, Object[] clavesDeleteMovimientos,
            List<MovNomConcep> AgreModifMovimientos, List<FiniqLiquidPlazas> finiqLiquidPlazas, Object[] eliminadosfiniqLiquidPlazas,
            List<FiniqLiquidCncNom> listFiniqLiquidCncNom, Object[] clavesDeleteFiniqLiquidCncNom,
            int cantPlazasFiniquitadas, int cantPlazasEmpleado, IngresosReingresosBajas ingresosReingresosBajas, List<PlazasPorEmpleado> cerrarPlazaEmpleado, SalariosIntegrados salariosIntegrado, int rango, String uuidCxn) {//JSA01
        FiniquitosLiquidaciones r = null;
        int i, contador = 0;
        boolean commit = true;
        try {
            inicializaVariableMensaje();
            setSession(HibernateUtil.currentSession(uuidCxn));
            getSession().beginTransaction();

            makePersistent(finiquitosLiquidaciones);
            contador++;
            try {
                //<editor-fold defaultstate="collapsed" desc="guardando-modificando-eliminando movimientos">
                //Borrando movimientos de nomina
                if (clavesDeleteMovimientos != null) {
                    commit = deleteListMovimientosNomina(clavesDeleteMovimientos, uuidCxn);
                    flush();
                }
                if (commit) {
                    //Guardado de modificados de los movimientos
                    for (MovNomConcep movNomConcep : AgreModifMovimientos) {
                        contador++;
                        getSession().saveOrUpdate(movNomConcep);
                        if (contador % rango == 0 & contador > 0) {
                            flush();
                            clear();
                        }
                    }
                }
                //</editor-fold>
                try {
                    //<editor-fold defaultstate="collapsed" desc="guardado-modificado-eliminado finiquitosPlaza">
                    if (eliminadosfiniqLiquidPlazas != null) {
                        commit = deleteListFiniqLiquidPlazas("FiniqLiquidPlazas", "id", eliminadosfiniqLiquidPlazas, uuidCxn);
                    }
                    finiqLiquidPlazas = (finiqLiquidPlazas == null ? new ArrayList<FiniqLiquidPlazas>() : finiqLiquidPlazas);
                    if (commit && !finiqLiquidPlazas.isEmpty()) {
                        for (i = 0; i < finiqLiquidPlazas.size(); i++) {
                            contador++;
                            finiqLiquidPlazas.get(i).setFiniquitosLiquidacion(finiquitosLiquidaciones);
                            getSession().saveOrUpdate(finiqLiquidPlazas.get(i));
                            if (contador % rango == 0 & contador > 0) {
                                flush();
                                clear();
                            }
                        }
                    }
                    //</editor-fold>
                    try {
                        //<editor-fold defaultstate="collapsed" desc="guardar-modificar-eliminar FiniquitosLiquidaConcepto">
                        if (clavesDeleteFiniqLiquidCncNom != null) {
                            commit = deleteListFiniqLiquidCncNom("FiniqLiquidCncNom", "id", clavesDeleteFiniqLiquidCncNom, uuidCxn);
                        }
                        listFiniqLiquidCncNom = (listFiniqLiquidCncNom == null ? new ArrayList<FiniqLiquidCncNom>() : listFiniqLiquidCncNom);
                        if (commit && !listFiniqLiquidCncNom.isEmpty()) {
                            for (i = 0; i < listFiniqLiquidCncNom.size(); i++) {
                                contador++;
                                listFiniqLiquidCncNom.get(i).setFiniquitosLiquidacion(finiquitosLiquidaciones);
                                getSession().saveOrUpdate(listFiniqLiquidCncNom.get(i));
                                if (contador % rango == 0 & contador > 0) {
                                    flush();
                                    clear();
                                }
                            }
                        }

                        //</editor-fold>
                        try {
                            //<editor-fold defaultstate="collapsed" desc="modificacion de Plaza por empleado">
                            PlazasPorEmpleado plazaEmpCerrada = null;
                            if (finiquitosLiquidaciones.getModoBaja() != ModoBaja.PROYECCION & cerrarPlazaEmpleado != null) {
                                for (i = 0; i < cerrarPlazaEmpleado.size(); i++) {
                                    contador++;
                                    plazaEmpCerrada = finiqLiquidPlazas.get(i).getPlazasPorEmpleado();
                                    plazaEmpCerrada.setFechaFinal(finiquitosLiquidaciones.getFechaBaja());
                                    getSession().saveOrUpdate(cerrarPlazaEmpleado.get(i));
                                    if (contador % rango == 0 & contador > 0) {
                                        flush();
                                        clear();
                                    }
                                }
                            }
                            //</editor-fold>
                            try {
                                //<editor-fold defaultstate="collapsed" desc="Ingresos y Reingresos, salariosIntegrado">
                                if (finiquitosLiquidaciones.getModoBaja() != ModoBaja.PROYECCION) {
                                    if (cerrarPlazaEmpleado != null) {
                                        if (cantPlazasEmpleado <= cantPlazasFiniquitadas + cerrarPlazaEmpleado.size()) {
                                            if (ingresosReingresosBajas != null) {
                                                ingresosReingresosBajas.setFechaBaja(finiquitosLiquidaciones.getFechaBaja());
                                                ingresosReingresosBajas.setFiniquitosLiquidaciones(finiquitosLiquidaciones);
                                                ingresosReingresosBajas.setPlazasPorEmpleado(plazaEmpCerrada);
                                                getSession().saveOrUpdate(ingresosReingresosBajas);
                                            }
                                            Calendar c = Calendar.getInstance();
                                            c.setTime(finiquitosLiquidaciones.getFechaCalculo());
                                            c.set(Calendar.DAY_OF_MONTH, c.get(Calendar.DAY_OF_MONTH) + 1);
                                            if (salariosIntegrado == null) {
                                                salariosIntegrado = new SalariosIntegrados();
                                            } else {
                                                if (salariosIntegrado.getFiniquitosLiquidaciones() == null) {
                                                    salariosIntegrado.setId(null);
                                                }
                                            }
                                            salariosIntegrado.setEmpleados(plazaEmpCerrada.getEmpleados());
                                            salariosIntegrado.setRegistroPatronal(plazaEmpCerrada.getRegistroPatronal());
                                            salariosIntegrado.setFecha(finiquitosLiquidaciones.getFechaCalculo());
                                            salariosIntegrado.setTipoDeSalario(1);
                                            salariosIntegrado.setSalarioDiarioFijo(0);
                                            salariosIntegrado.setSalarioDiarioIntegrado(0);
                                            salariosIntegrado.setSalarioDiarioVariable(0);
                                            salariosIntegrado.setFactorIntegracion(0);
                                            salariosIntegrado.setFiniquitosLiquidaciones(finiquitosLiquidaciones);
                                            getSession().saveOrUpdate(salariosIntegrado);
                                        }
                                    }
                                }
                                //</editor-fold>
                            } catch (HibernateException e) {
                                commit = false;
                                mensajeResultado.setNoError(ControlErroresHibernate.buscaNoErrorPorExcepcion(e.getClass()));
                                mensajeResultado.setError(e.getMessage());
                                mensajeResultado.setResultado(e);
                                System.err.println(concatena.delete(0, concatena.length()).append(msgError).append("getFiniquitosLiquidacionesGuardarModificar()1_Error: ").append(e));
                            }
                        } catch (HibernateException e) {
                            commit = false;
                            mensajeResultado.setNoError(ControlErroresHibernate.buscaNoErrorPorExcepcion(e.getClass()));
                            mensajeResultado.setError(e.getMessage());
                            mensajeResultado.setResultado(e);
                            System.err.println(concatena.delete(0, concatena.length()).append(msgError).append("getFiniquitosLiquidacionesGuardarModificar()2_Error: ").append(e));
                        }
                    } catch (HibernateException e) {
                        commit = false;
                        mensajeResultado.setNoError(ControlErroresHibernate.buscaNoErrorPorExcepcion(e.getClass()));
                        mensajeResultado.setError(e.getMessage());
                        mensajeResultado.setResultado(e);
                        System.err.println(concatena.delete(0, concatena.length()).append(msgError).append("getFiniquitosLiquidacionesGuardarModificar()3_Error: ").append(e));
                    }
                } catch (HibernateException e) {
                    commit = false;
                    mensajeResultado.setNoError(ControlErroresHibernate.buscaNoErrorPorExcepcion(e.getClass()));
                    mensajeResultado.setError(e.getMessage());
                    mensajeResultado.setResultado(e);
                    System.err.println(concatena.delete(0, concatena.length()).append(msgError).append("getFiniquitosLiquidacionesGuardarModificar()4_Error: ").append(e));
                }
            } catch (HibernateException e) {
                commit = false;
                mensajeResultado.setNoError(ControlErroresHibernate.buscaNoErrorPorExcepcion(e.getClass()));
                mensajeResultado.setError(e.getMessage());
                mensajeResultado.setResultado(e);
                System.err.println(concatena.delete(0, concatena.length()).append(msgError).append("getFiniquitosLiquidacionesGuardarModificar()5_Error: ").append(e));
            }
            if (commit) {
                getSession().getTransaction().commit();
                mensajeResultado.setNoError(0);
                mensajeResultado.setError("");
                mensajeResultado.setResultado(finiquitosLiquidaciones);
            } else {
                mensajeResultado.setError("ERROR en algun proceso del finiquito");
                mensajeResultado.setResultado("ERROR en algun proceso del finiquito");
                System.err.println(concatena.delete(0, concatena.length()).append(msgError).append("getFiniquitosLiquidacionesGuardarModificar()5_Error: "));
                try {
                    if (getSession().getTransaction().isActive()) {
                        getSession().getTransaction().rollback();
                    }
                } catch (HibernateException exc) {  //cuando marca error al hacer rollback pro ejemplo se cae conexion servidor
                    mensajeResultado.setNoError(ControlErroresHibernate.buscaNoErrorPorExcepcion(exc.getClass()));
                    mensajeResultado.setError(exc.getLocalizedMessage());
                }
                mensajeResultado.setResultado(null);
            }
        } catch (HibernateException ex) {
            mensajeResultado.setError(ex.getMessage());
            mensajeResultado.setResultado(ex);
            System.err.println(concatena.delete(0, concatena.length()).append(msgError).append("getFiniquitosLiquidacionesGuardarModificar()6_Error: ").append(ex));
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
            mensajeResultado.setResultado(ex);
        }
        return mensajeResultado;
    }

    public Mensaje getCancelarFiniquito(Object[] eliminadoMovNomConceps, List<PlazasPorEmpleado> listPlazasPorEmpleado, IngresosReingresosBajas ingresosReingresosBajas,
            SalariosIntegrados salariosIntegrado, FiniquitosLiquidaciones finiquitosLiquidaciones, String uuidCxn) {
        FiniquitosLiquidaciones r = null;
        int i, contador = 0;
        boolean commit = true;
        try {
            inicializaVariableMensaje();
            setSession(HibernateUtil.currentSession(uuidCxn));
            getSession().beginTransaction();

            for (i = 0; i < listPlazasPorEmpleado.size(); i++) {
                getSession().saveOrUpdate(listPlazasPorEmpleado.get(i));
            }

            makePersistent(finiquitosLiquidaciones);
            if (ingresosReingresosBajas != null) {
                ingresosReingresosBajas.setPlazasPorEmpleado(listPlazasPorEmpleado.get(0));
                getSession().saveOrUpdate(ingresosReingresosBajas);
            }
            if (eliminadoMovNomConceps != null) {
                commit = deleteListMovimientosNomina(eliminadoMovNomConceps, uuidCxn);
            }
            contador++;
            if (salariosIntegrado != null) {
                deleteListQuery(SalariosIntegrados.class.getSimpleName(), "id", new Object[]{salariosIntegrado.getId()});
            }
            if (commit) {
                getSession().getTransaction().commit();
                mensajeResultado.setResultado(finiquitosLiquidaciones);
                mensajeResultado.setNoError(0);
                mensajeResultado.setError("");
            } else {
                mensajeResultado.setError("ERROR en algun proceso del finiquito");
                mensajeResultado.setResultado("ERROR en algun proceso del finiquito");
                System.err.println(concatena.delete(0, concatena.length()).append(msgError).append("getFiniquitosLiquidacionesGuardarModificar()5_Error: "));
                try {
                    if (getSession().getTransaction().isActive()) {
                        getSession().getTransaction().rollback();
                    }
                } catch (HibernateException exc) {  //cuando marca error al hacer rollback pro ejemplo se cae conexion servidor
                    mensajeResultado.setNoError(ControlErroresHibernate.buscaNoErrorPorExcepcion(exc.getClass()));
                    mensajeResultado.setError(exc.getLocalizedMessage());
                }
                mensajeResultado.setResultado(null);
            }
        } catch (HibernateException ex) {
            System.err.println(concatena.delete(0, concatena.length()).append(msgError).append("getFiniquitosLiquidacionesGuardarModificar()6_Error: ").append(ex));
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
            mensajeResultado.setResultado(ex);
        }
        return mensajeResultado;
    }

    private boolean deleteListFiniqLiquidCncNom(String tabla, String campo, Object[] valores, String uuidCxn) {
        boolean committ = true;
        try {
            deleteListQuery(tabla, campo, valores);
        } catch (HibernateException ex) {
            System.err.println(concatena.delete(0, concatena.length()).append(msgError).append("deleteListFiniqLiquidCncNom()1_Error: ").append(ex));
            mensajeResultado.setNoError(ControlErroresHibernate.buscaNoErrorPorExcepcion(ex.getClass()));
            mensajeResultado.setError(ex.getLocalizedMessage());
            mensajeResultado.setResultado(null);
            committ = false;
        }
        return committ;
    }

    private boolean deleteListFiniqLiquidPlazas(String tabla, String campo, Object[] valores, String uuidCxn) {
        boolean committ = true;
        try {
            deleteListQuery(tabla, campo, valores);
        } catch (HibernateException ex) {
            System.err.println(concatena.delete(0, concatena.length()).append(msgError).append("deleteListFiniqLiquidPlazas()1_Error: ").append(ex));
            mensajeResultado.setNoError(ControlErroresHibernate.buscaNoErrorPorExcepcion(ex.getClass()));
            mensajeResultado.setError(ex.getLocalizedMessage());
            mensajeResultado.setResultado(null);
            committ = false;
        }
        return committ;
    }

    private boolean deleteListMovimientosNomina(Object[] clavesDeleteMovimientos, String uuidCxn) {
        boolean committ = true;
        try {
            deleteListQuery("MovNomBaseAfecta ", "movNomConcep.id", clavesDeleteMovimientos);
            deleteListQuery(CalculoISR.class.getSimpleName(), "movNomConcep.id", clavesDeleteMovimientos);//JSA02
            deleteListQuery("CalculoIMSS", "movNomConcep.id", clavesDeleteMovimientos);
            deleteListQuery("MovNomConceParam ", "movNomConcep.id", clavesDeleteMovimientos);
            deleteListQuery("MovNomConcep", "id", clavesDeleteMovimientos);
        } catch (HibernateException ex) {
            System.err.println(concatena.delete(0, concatena.length()).append(msgError).append("deleteListMovimientosNomina()1_Error: ").append(ex));
            mensajeResultado.setNoError(ControlErroresHibernate.buscaNoErrorPorExcepcion(ex.getClass()));
            mensajeResultado.setError(ex.getLocalizedMessage());
            mensajeResultado.setResultado(null);
            committ = false;
        }
        return committ;
    }

    public Mensaje consultaPorRangos(Integer inicio, Integer rango, String uuidCxn) {
        try {
            inicializaVariableMensaje();
            setSession(HibernateUtil.currentSession(uuidCxn));
            getSession().beginTransaction();
            listEsp = (List<FiniquitosLiquidaciones>) consultaPorRangos(inicio, rango, null, null);
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

    public Mensaje consultaPorFiltrosFiniquitos(String query, Object[] campos, Object[] valores, String uuidCxn) {
        try {
            inicializaVariableMensaje();
            setSession(HibernateUtil.currentSession(uuidCxn));
            getSession().beginTransaction();
            listEsp = (List<FiniquitosLiquidaciones>) consultaPorFiltros(query, campos, valores, null, null);
            mensajeResultado.setResultado(listEsp);
            mensajeResultado.setNoError(0);
            mensajeResultado.setError("");
            getSession().getTransaction().commit();
        } catch (HibernateException ex) {
            System.err.println(concatena.delete(0, concatena.length()).append(msgError).append("consultaPorFiltrosFiniquitos()1_Error: ").append(ex));
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
            existe = existeDato("FiniquitosLiquidaciones", campo, valor);
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
    public Mensaje EliminarFiniquitosLiquidacion(FiniquitosLiquidaciones finiquitosLiquidaciones, List<PlazasPorEmpleadosMov> plazasPorEmpleados, String uuidCxn) {
        try {

            inicializaVariableMensaje();
            setSession(HibernateUtil.currentSession(uuidCxn));
            getSession().beginTransaction();

            for (int i = 0; i < plazasPorEmpleados.size(); i++) {

                TipoNomina tn = plazasPorEmpleados.get(i).getTipoNomina();
                Date fecha = finiquitosLiquidaciones.getFechaCalculo();
                strQuery.delete(0, strQuery.length());
                strQuery.append("Select p from PeriodosNomina p INNER JOIN p.tipoCorrida tc where (p.fechaInicial >=:fecha or p.fechaFinal >=:fecha) and p.tipoNomina.clave =:claveTipoNomina and tc.clave = :claveTipoCorrida");
                q = getSession().createQuery(strQuery.toString());
                q.setParameter("fecha", fecha);
                q.setParameter("claveTipoNomina", tn.getClave());
                q.setParameter("claveTipoCorrida", "FIN");
                q.setFirstResult(0);
                q.setMaxResults(1);
                PeriodosNomina per = (PeriodosNomina) q.uniqueResult();

                strQuery.delete(0, strQuery.length());
                strQuery.append("Select o from CFDIEmpleado o INNER JOIN o.cfdiRecibo cr where o.periodosNomina.id=:idPer ");
                strQuery.append("and o.plazaPorEmpleadoMov.id=:idplazaEmpMov and o.tipoCorrida.clave=:clavetipoCorrida ");
                strQuery.append("and o.tipoNomina.id=:idtNomina and cr.statusTimbrado=0");

                q = getSession().createQuery(strQuery.toString());
                q.setParameter("idPer", per.getId());
                q.setParameter("idplazaEmpMov", plazasPorEmpleados.get(i).getId());
                q.setParameter("clavetipoCorrida", "FIN");
                q.setParameter("idtNomina", tn.getId());
                CFDIEmpleado cfdiEmpleado = (CFDIEmpleado) q.uniqueResult();

                if (cfdiEmpleado != null) {
                    mensajeResultado.setResultado("Timbrado");
                    getSession().getTransaction().rollback();

                } else if (!per.isStatus()) {
                    mensajeResultado.setResultado("Periodo");
                    getSession().getTransaction().rollback();
                } else {
                    boolean exito = eliminarMovFinLiq(plazasPorEmpleados.get(i), finiquitosLiquidaciones);
                    if (exito) {

                        Calendar c;
                        c = Calendar.getInstance();
                        c.setTime(plazasPorEmpleados.get(i).getPlazasPorEmpleado().getFechaFinal());

                        if (c.get(Calendar.YEAR) < 2100) {
                            c.set(Calendar.YEAR, c.get(Calendar.YEAR) + 100);
                        }
                        Session session = getSession().getSessionFactory().openSession();
                        plazasPorEmpleados.get(i).getPlazasPorEmpleado().setFechaFinal(c.getTime());
                        session.beginTransaction();
                        session.saveOrUpdate(plazasPorEmpleados.get(i));
////                        getSession().saveOrUpdate(plazasPorEmpleados.get(i));
                        session.beginTransaction().commit();
                        session.close();
                        mensajeResultado.setResultado("Eliminado");

                        getSession().getTransaction().commit();
                    } else {
                        getSession().getTransaction().rollback();
                    }

                }
            }

        } catch (HibernateException ex) {
            System.err.println(concatena.delete(0, concatena.length()).append(msgError).append("eliminarFiniquitosLiquidacion()1_Error: ").append(ex));
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

    private boolean eliminarMovFinLiq(PlazasPorEmpleadosMov plazasPorEmpleados, FiniquitosLiquidaciones finiquitosLiquidaciones) {
        boolean values = false;
        int exito = 0;
        try {
            List<FiniqLiquidCncNom> finLiqCncNom;
            List<MovNomConcep> movNomConcep = new ArrayList<>();
            strQuery.delete(0, strQuery.length());
            strQuery.append("select o from FiniqLiquidCncNom o INNER JOIN o.finiquitosLiquidacion f where f.id=:idFinLiq");
            q = getSession().createQuery(strQuery.toString());
            q.setParameter("idFinLiq", finiquitosLiquidaciones.getId());
            finLiqCncNom = q.list();

            for (int i = 0; i < finLiqCncNom.size(); i++) {
                strQuery.delete(0, strQuery.length());
                strQuery.append("select m from MovNomConcep m where m.finiqLiquidCncNom.id=:idfinLiqCN ");
                strQuery.append("and m.plazasPorEmpleado.id=:idplPorEmpleado and m.tipoCorrida.clave=:claveTCorrida ");
                strQuery.append("and m.tipoNomina.id=:idTipoNomina");
                q = getSession().createQuery(strQuery.toString());
                q.setParameter("idfinLiqCN", finLiqCncNom.get(i).getId());
                q.setParameter("idplPorEmpleado", plazasPorEmpleados.getPlazasPorEmpleado().getId());
                q.setParameter("claveTCorrida", "FIN");
                q.setParameter("idTipoNomina", plazasPorEmpleados.getTipoNomina().getId());
//                movNomConcep = q.list();
                if (q.uniqueResult() != null) {
                    movNomConcep.add((MovNomConcep) q.uniqueResult());
                }
            }
            System.out.printf(" ", movNomConcep);
            for (int i = 0; i < movNomConcep.size(); i++) {

                strQuery.delete(0, strQuery.length());
                strQuery.append("delete MovNomBaseAfecta o where o.movNomConcep.id=:id");
                q = getSession().createQuery(strQuery.toString());
                q.setParameter("id", movNomConcep.get(i).getId());
                exito = q.executeUpdate();

                strQuery.delete(0, strQuery.length());
                strQuery.append("delete MovNomConceParam o where o.movNomConcep.id=:id");
                q = getSession().createQuery(strQuery.toString());
                q.setParameter("id", movNomConcep.get(i).getId());
                exito = q.executeUpdate();

                strQuery.delete(0, strQuery.length());
                strQuery.append("delete  MovNomConceParam o where o.movNomConcep.id=:id");
                q = getSession().createQuery(strQuery.toString());
                q.setParameter("id", movNomConcep.get(i).getId());
                exito = q.executeUpdate();

                strQuery.delete(0, strQuery.length());
                strQuery.append("delete ");
                strQuery.append(CalculoISR.class.getSimpleName()).append(" o where o.movNomConcep.id=:id");
                q = getSession().createQuery(strQuery.toString());
                q.setParameter("id", movNomConcep.get(i).getId());
                exito = q.executeUpdate();

                strQuery.delete(0, strQuery.length());
                strQuery.append("delete ");
                strQuery.append(CalculoIMSS.class.getSimpleName()).append(" o where o.movNomConcep.id=:id");
                q = getSession().createQuery(strQuery.toString());
                q.setParameter("id", movNomConcep.get(i).getId());
                exito = q.executeUpdate();

                strQuery.delete(0, strQuery.length());
                strQuery.append("delete ");
                strQuery.append(CalculoIMSSPatron.class.getSimpleName()).append(" o where o.movNomConcep.id=:id");
                q = getSession().createQuery(strQuery.toString());
                q.setParameter("id", movNomConcep.get(i).getId());
                exito = q.executeUpdate();

                strQuery.delete(0, strQuery.length());
                strQuery.append("delete  MovNomConcep o where o.id=:id");
                q = getSession().createQuery(strQuery.toString());
                q.setParameter("id", movNomConcep.get(i).getId());
                exito = q.executeUpdate();

            }

            strQuery.delete(0, strQuery.length());
            strQuery.append("select r from IngresosReingresosBajas r where r.finiquitosLiquidaciones.id=:idFinLiq ");
            strQuery.append("and r.plazasPorEmpleado.id=:idplPorEmpl");
            q = getSession().createQuery(strQuery.toString());
            q.setParameter("idFinLiq", finiquitosLiquidaciones.getId());
            q.setParameter("idplPorEmpl", plazasPorEmpleados.getPlazasPorEmpleado().getId());
            IngresosReingresosBajas ingReinBajas = (IngresosReingresosBajas) q.uniqueResult();
            if (ingReinBajas != null) {
                ingReinBajas.setFiniquitosLiquidaciones(null);

                Calendar c;
                c = Calendar.getInstance();
                c.setTime(ingReinBajas.getFechaBaja());

                if (c.get(Calendar.YEAR) < 2100) {
                    c.set(Calendar.YEAR, c.get(Calendar.YEAR) + 100);
                }

                ingReinBajas.setFechaBaja(c.getTime());
                getSession().saveOrUpdate(ingReinBajas);
                getSession().flush();
            }

            strQuery.delete(0, strQuery.length());
            strQuery.append("delete FiniqLiquidCncNom f where f.finiquitosLiquidacion.id=:idFinLiq");
            q = getSession().createQuery(strQuery.toString());
            q.setParameter("idFinLiq", finiquitosLiquidaciones.getId());
            exito = q.executeUpdate();

            strQuery.delete(0, strQuery.length());
            strQuery.append("delete FiniqLiquidPlazas f where f.finiquitosLiquidacion.id=:idFinLiq");
            q = getSession().createQuery(strQuery.toString());
            q.setParameter("idFinLiq", finiquitosLiquidaciones.getId());
            exito = q.executeUpdate();

            strQuery.delete(0, strQuery.length());
            strQuery.append("select s from SalariosIntegrados s where s.finiquitosLiquidaciones.id=:idFinLiq ");
            strQuery.append("and s.empleados.id=:idEmpleado");
            q = getSession().createQuery(strQuery.toString());
            q.setParameter("idFinLiq", finiquitosLiquidaciones.getId());
            q.setParameter("idEmpleado", plazasPorEmpleados.getPlazasPorEmpleado().getEmpleados().getId());
            SalariosIntegrados salarioInt = (SalariosIntegrados) q.uniqueResult();

            if (salarioInt != null) {
                strQuery.delete(0, strQuery.length());
                strQuery.append("delete SalariosIntegradosDet f where f.salarioIntegrado.id=:idSalario");
                q = getSession().createQuery(strQuery.toString());
                q.setParameter("idSalario", salarioInt.getId());
                exito = q.executeUpdate();
            }

            strQuery.delete(0, strQuery.length());
            strQuery.append("delete SalariosIntegrados f where f.finiquitosLiquidaciones.id=:idFinLiq");
            q = getSession().createQuery(strQuery.toString());
            q.setParameter("idFinLiq", finiquitosLiquidaciones.getId());
            exito = q.executeUpdate();

            strQuery.delete(0, strQuery.length());
            strQuery.append("delete FiniquitosLiquidaciones f where f.id=:idFinLiq");
            q = getSession().createQuery(strQuery.toString());
            q.setParameter("idFinLiq", finiquitosLiquidaciones.getId());
            exito = q.executeUpdate();

            values = true;

        } catch (HibernateException ex) {
            if (getSession().getTransaction().isActive()) {
                getSession().getTransaction().rollback();
            }
            System.err.println(concatena.delete(0, concatena.length()).append(msgError).append("obtenerIngresosReingresosBajas()1_Error: ").append(ex));
            mensajeResultado.setNoError(ControlErroresHibernate.buscaNoErrorPorExcepcion(ex.getClass()));
            mensajeResultado.setError(ex.getLocalizedMessage());
            mensajeResultado.setResultado(null);
            return false;
        }
        return values;
    }

    @Override
    public Mensaje validaFiniquitosLiquidacionTimbrados(Date fechaCalculo, List<PlazasPorEmpleadosMov> plazasPorEmpleados, String uuidCxn) {
        try {

            inicializaVariableMensaje();
            setSession(HibernateUtil.currentSession(uuidCxn));
            getSession().beginTransaction();

            for (int i = 0; i < plazasPorEmpleados.size(); i++) {

                TipoNomina tn = plazasPorEmpleados.get(i).getTipoNomina();

                strQuery.delete(0, strQuery.length());
                strQuery.append("Select p from PeriodosNomina p INNER JOIN p.tipoCorrida tc where (p.fechaInicial >=:fecha or p.fechaFinal >=:fecha) and p.tipoNomina.clave =:claveTipoNomina and tc.clave = :claveTipoCorrida");
                q = getSession().createQuery(strQuery.toString());
                q.setParameter("fecha", fechaCalculo);
                q.setParameter("claveTipoNomina", tn.getClave());
                q.setParameter("claveTipoCorrida", "FIN");
                q.setFirstResult(0);
                q.setMaxResults(1);
                PeriodosNomina per = (PeriodosNomina) q.uniqueResult();

                if (per != null) {
                    strQuery.delete(0, strQuery.length());
                    strQuery.append("Select o from CFDIEmpleado o INNER JOIN o.cfdiRecibo cr where o.periodosNomina.id=:idPer ");
                    strQuery.append("and o.plazaPorEmpleadoMov.id=:idplazaEmpMov and o.tipoCorrida.clave=:clavetipoCorrida ");
                    strQuery.append("and o.tipoNomina.id=:idtNomina and cr.statusTimbrado=0");

                    q = getSession().createQuery(strQuery.toString());
                    q.setParameter("idPer", per.getId());
                    q.setParameter("idplazaEmpMov", plazasPorEmpleados.get(i).getId());
                    q.setParameter("clavetipoCorrida", "FIN");
                    q.setParameter("idtNomina", tn.getId());
                    CFDIEmpleado cfdiEmpleado = (CFDIEmpleado) q.uniqueResult();

                    if (cfdiEmpleado != null) {
                        mensajeResultado.setResultado("Timbrado");
                        getSession().getTransaction().commit();

                    } else if (!per.isStatus()) {
                        mensajeResultado.setResultado("Periodo");
                        getSession().getTransaction().commit();
                    } else {
                        mensajeResultado.setResultado("Calcula");
                        getSession().getTransaction().commit();

                    }
                } else {
                    mensajeResultado.setResultado("NoPeriodoFiniquito");
                }
            }

        } catch (HibernateException ex) {
            System.err.println(concatena.delete(0, concatena.length()).append(msgError).append("eliminarFiniquitosLiquidacion()1_Error: ").append(ex));
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
