/**
 * @author: Jose Ernesto Valenzuela Castillo Fecha de Creación: 20/10/2011
 * Compañía: Exito software Descripción del programa: clase Modulo para llamados
 * a metodos de HIBERNATE
 * -----------------------------------------------------------------------------
 * MODIFICACIONES:
 * -----------------------------------------------------------------------------
 * Clave:JSA01 Autor:Jose Armando Sanchez Acosta Fecha:10/11/2011 Descripción:Se
 * cambiaron todos los metodos hibernate al dao
 * -----------------------------------------------------------------------------
 * Clave:JSA02 Autor:Jose Armando Fecha:31/10/2013 Descripción:Se agrego
 * programacion adicional para que cuando se borren conceptos que se encuentren
 * en los prestamos estos deshagan el movimiento de mismo, es decir, si es
 * prestamos que sube el saldo y si es ahorro que baje.
 * -----------------------------------------------------------------------------
 * Clave:JSA03 Autor:Jose Armando Sanchez Acosta Fecha:08/10/2014 Descripción:Se
 * le agrego el campo tipoPantalla la consulta getMovimientosPorFiltroEspecifico
 * para indicar en el cliente cuales se puedes eliminar.
 * -----------------------------------------------------------------------------
 * Clave:JSA04 Autor:Jose Armando Sanchez Acosta Fecha:13/04/2015 Descripción:
 * se le agrego order by al metodo getMovimientosPorFiltroEspecifico para el
 * modulo de eliminaSaldos.
 * -----------------------------------------------------------------------------
 * Clave:JSA05 Autor:Jose Armando Sanchez Acosta Fecha:27/04/2015 Descripción:
 * Se corrigio el problema de cuando eliminas movimentos de nomina y este tiene
 * creditos o ahorros y el periodo abarco 2 meses te descontaba o aumentaba 2
 * veces por que en el query nunca te regresaba un 1 CreditoMovimientos siempre
 * eran 2.
 * -----------------------------------------------------------------------------
 * Clave: JSA06 Autor: Armando Fecha: 11/05/2015 Descripcion:Se renombro la
 * tabla de ISRRetenido a CalculoISR para tener todos los calculos cerca y tener
 * todo lo relacionado con el resultado del calculo de la nomina en la misma
 * nomenclatura del calculo...
 * -----------------------------------------------------------------------------
 * Clave: JEVC02 Autor: Jose Ernesto Valenzuela Castillo Fecha de Creación:
 * 28/05/2015 Descripcion: Se agrego eliminado de CFDI para cuando se eliminen
 * movimientos en el metodo deleteListQueryMov()
 * -----------------------------------------------------------------------------
 * Clave: JSA07 Autor: Armando Fecha: 23/06/2015 Descripcion:Se agrego el codigo
 * para reestablecer el resultado de los movimientos de nomina de aquellos que
 * el usuario alla agregado desde las pantallas de movimientos y se tmb se les
 * remueve la relacion que tienen con el credito o ahorro.
 * -----------------------------------------------------------------------------
 */
package com.mef.erp.modelo;

import com.mef.erp.modelo.entidad.CalculoIMSS;
import com.mef.erp.modelo.entidad.CalculoIMSSPatron;
import com.mef.erp.modelo.entidad.CalculoISR;
import com.mef.erp.modelo.entidad.CalculoUnidades;
import com.mef.erp.modelo.entidad.CreditoMovimientos;
import com.mef.erp.modelo.entidad.Empleados;
import com.mef.erp.modelo.entidad.Mensaje;
import com.mef.erp.modelo.entidad.MovNomConceParam;
import com.mef.erp.modelo.entidad.MovNomConcep;
import com.mef.erp.modelo.entidad.PeriodosNomina;
import com.mef.erp.modelo.entidad.RazonSocial;
import com.mef.erp.modelo.entidad.RazonesSociales;
import com.mef.erp.modelo.entidad.TipoCorrida;
import com.mef.erp.modelo.entidad.cfdi.CFDIEmpleado;
import com.mef.erp.modelo.entidad.cfdi.StatusTimbrado;
import com.mef.erp.util.ControlErroresHibernate;
import com.mef.erp.util.HibernateUtil;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Transaction;
import org.hibernate.exception.ConstraintViolationException;

/**
 *
 * @author Ernesto
 */
public class MovimientosNominaDAO extends GenericHibernateDAO<MovNomConcep, Long>
        implements MovimientosNominaIF {

    private StringBuilder concatena = new StringBuilder(200);
    private final String msgError = concatena.append("ServerERP"/*
     * util.getContexto()
     */).append(".MSERR_C_").append(this.getClass().getName()).append(".").toString();
    private final String[] camposConsulta = new String[]{"razonesSociales", "empleado",
        "tipoNomina", "periodosNomina", "tipoCorrida", "concepNomDefi", "numero", "ejercicio", "mes", "uso"};
    //plazasporempleado--nop
    //uso,mes,ejercicio
    private StringBuilder strQuery = new StringBuilder();

    public Mensaje getMovimientosNominaAll(String uuidCxn) {//JSA01
        List<MovNomConcep> values;
        try {
            inicializaVariableMensaje();
            setSession(HibernateUtil.currentSession(uuidCxn));
            getSession().beginTransaction();
            q = getSession().createQuery("from  MovNomConcep");
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
            } catch (HibernateException exc) {  //cuando marca error al hacer rollback pro ejemplo se cae conexion servidor
                mensajeResultado.setNoError(ControlErroresHibernate.buscaNoErrorPorExcepcion(exc.getClass()));
                mensajeResultado.setError(exc.getLocalizedMessage());
            }
            mensajeResultado.setResultado(null);
        }
        return mensajeResultado;
    }

    public Mensaje getMovimientosNominaAsc(String uuidCxn) {//JSA01
        List<MovNomConcep> values;
        try {
            inicializaVariableMensaje();
            setSession(HibernateUtil.currentSession(uuidCxn));
            getSession().beginTransaction();
            q = getSession().createQuery("from  MovNomConcep order by ordenId");
            values = q.list();
            mensajeResultado.setResultado(values);
            mensajeResultado.setNoError(0);
            mensajeResultado.setError("");
            getSession().getTransaction().commit();
        } catch (HibernateException ex) {
            System.err.println(concatena.delete(0, concatena.length()).append(msgError).append("getMovimientosNominaAsc()1_Error: ").append(ex));
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

    public Mensaje getMaxNumeroMovimientoPorTipoNominaYPeriodo(String claveTipoNomina, Long idPeriodo, String uuidCxn) {
        Object maxValue;
        try {
            inicializaVariableMensaje();
            setSession(HibernateUtil.currentSession(uuidCxn));
            getSession().beginTransaction();
            q = getSession().createQuery("select MAX(m.numero) from MovNomConcep m where m.tipoNomina.clave=:claveTipoNomina and m.periodosNomina.id=:idPeriodo");
            q.setParameter("claveTipoNomina", claveTipoNomina);
            q.setParameter("idPeriodo", idPeriodo);
            maxValue = q.uniqueResult();
            mensajeResultado.setResultado(maxValue);
            mensajeResultado.setNoError(0);
            mensajeResultado.setError("");
            getSession().getTransaction().commit();
        } catch (HibernateException ex) {
            System.err.println(concatena.delete(0, concatena.length()).append(msgError).append("getMaxNumeroMovimientoPorTipoNominaYPeriodo()1_Error: ").append(ex));
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

    public Mensaje saveDeleteMovimientosNomina(List<MovNomConcep> AgreModif, Object[] clavesDelete, Object[] valoresReestablecer, boolean incluirEliminadoDiferenteTipoPantalla100, String uuidCxn) {//JSA01
        Transaction transaction = null;
        List<MovNomConcep> onList = null;
        try {
            inicializaVariableMensaje();
            setSession(HibernateUtil.currentSession(uuidCxn));
            transaction = getSession().beginTransaction();
            boolean commit = true;
            if (clavesDelete != null) {
                int val = deleteListQueryMov("MovNomConcep", "id", clavesDelete, null, null, valoresReestablecer, incluirEliminadoDiferenteTipoPantalla100);
                if (val == -1) {
                    commit = false;
                }
            }
            AgreModif = (AgreModif == null ? new ArrayList<MovNomConcep>() : AgreModif);
            if (commit && !AgreModif.isEmpty()) {
                for (MovNomConcep Am : AgreModif) {
                    makePersistent(Am);
                }
            }
            if (commit) {
                mensajeResultado.setResultado(null);
                mensajeResultado.setNoError(0);
                mensajeResultado.setError("");
                transaction.commit();
            } else {
                transaction.rollback();
            }
        } catch (ConstraintViolationException ex) {
            if (transaction != null) {
                try {
                    transaction.rollback();
                    mensajeResultado.setNoError(ControlErroresHibernate.buscaNoErrorPorExcepcion(ex.getClass()));
                    mensajeResultado.setError(ex.getLocalizedMessage());
                } catch (HibernateException exc) {  //cuando marca error al hacer rollback pro ejemplo se cae conexion servidor
                    mensajeResultado.setNoError(ControlErroresHibernate.buscaNoErrorPorExcepcion(exc.getClass()));
                    mensajeResultado.setError(exc.getLocalizedMessage());
                }
                mensajeResultado.setResultado(null);
                inicializaVariableMensaje();
                setSession(HibernateUtil.currentSession(uuidCxn));
                transaction = getSession().beginTransaction();
                Object[] valores = new Object[camposConsulta.length];
                double suma;
                for (int i = 0; i < AgreModif.size(); i++) {//JEVC
                    /*Solo aplica para periodo semanales y se suman las cantidades que fueron partidas
                     para reflejar en la lista el valor original del parametro con el que se intento persistir*/
                    if (AgreModif.get(i).getNumMovParticion() == 2) {
                        List<MovNomConceParam> movParamPrin = AgreModif.get(i - 1).getMovNomConceParam();
                        List<MovNomConceParam> movParamSec = AgreModif.get(i).getMovNomConceParam();
                        if (movParamPrin != null) {
                            for (int j = 0; j < movParamPrin.size(); j++) {
                                suma = Double.valueOf(movParamPrin.get(j).getValor())
                                        + Double.valueOf(movParamSec.get(j).getValor());
                                movParamPrin.get(j).setValor(agregarMascaraValorNumerico(suma,
                                        movParamPrin.get(j).getParaConcepDeNom().getMascara().replace("#", "0")));
                            }
                        }
                        AgreModif.remove(i);
                        i--;
                    } else {
                        valores[0] = AgreModif.get(i).getRazonesSociales();
                        valores[1] = AgreModif.get(i).getEmpleado();
                        valores[2] = AgreModif.get(i).getTipoNomina();
                        valores[3] = AgreModif.get(i).getPeriodosNomina();
                        valores[4] = AgreModif.get(i).getTipoCorrida();
                        valores[5] = AgreModif.get(i).getConcepNomDefi();
                        valores[6] = AgreModif.get(i).getNumero();//--
                        valores[7] = AgreModif.get(i).getEjercicio();
                        valores[8] = AgreModif.get(i).getMes();//--
                        valores[9] = AgreModif.get(i).getUso();
                        MovNomConcep existe = (MovNomConcep) existeClave("MovNomConcep", camposConsulta, valores, null);
                        if (existe != null) {//Indicar que existe en base de datos
                            AgreModif.get(i).setIsEnBD(true);
                            //Object[] control = AgreModif.get(i).getIsEnBdAndBackupID();
                            //control[0] = true;//Para indicar que existe en la base de datos
                            //control[1] = existe.getId();//para respaldar el ID y poder recuperar el objeto original
                            //AgreModif.get(i).setIsEnBdAndBackupID(control);
                        }
                    }
                }
                onList = AgreModif;

                try {
                    transaction.commit();
                    mensajeResultado.setResultado(onList);
                    mensajeResultado.setNoError(0);
                    mensajeResultado.setError("");
                } catch (HibernateException e) {  //cuando marca error al hacer rollback pro ejemplo se cae conexion servidor
                    try {
                        transaction.rollback();
                        mensajeResultado.setNoError(ControlErroresHibernate.buscaNoErrorPorExcepcion(e.getClass()));
                        mensajeResultado.setError(e.getLocalizedMessage());
                    } catch (HibernateException exc) {  //cuando marca error al hacer rollback pro ejemplo se cae conexion servidor
                        mensajeResultado.setNoError(ControlErroresHibernate.buscaNoErrorPorExcepcion(exc.getClass()));
                        mensajeResultado.setError(exc.getLocalizedMessage());
                    }
                    mensajeResultado.setResultado(null);
                }
            }
            System.err.println(concatena.delete(0, concatena.length()).append(msgError).append("saveDeleteMovimientosNomina()1_Error: ").append(ex));;
        } catch (HibernateException ex) {
            if (transaction != null) {
                transaction.rollback();
            }
            System.err.println(concatena.delete(0, concatena.length()).append(msgError).append("saveDeleteMovimientosNomina()2_Error: ").append(ex));
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

    public int deleteListQueryMov(String tabla, String campo, Object[] valores, List<CFDIEmpleado> valoresCFDI, Object[] valoresCalculoUnidades, Object[] valoresReestablecer, boolean incluirEliminadoDiferenteTipoPantalla100) {
        consulta = new StringBuilder();
        int exito = 0;
        try {
            //Deshace el movimiento del credito o ahorro
            consulta.append("select credMov from CreditoMovimientos credMov inner join credMov.movNomConceps o where o.id in(:valores)");//JSA02
            q = getSession().createQuery(consulta.toString());
            if (valoresReestablecer == null ? false : valoresReestablecer.length > 0) {//JSA07
                List<Object> valoresx = new ArrayList<Object>();
                valoresx.addAll(Arrays.asList(valores));
                valoresx.addAll(Arrays.asList(valoresReestablecer));
                q.setParameterList("valores", valoresx.toArray());
            } else {
                q.setParameterList("valores", valores);
            }
            q.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);//este codigo te permite que no se regresen duplicados. //JSA05
            List<CreditoMovimientos> listCreditoMovimientos = q.list();

            //Elimina CreditoMovimientos y reestablece los importe.
            if (listCreditoMovimientos == null ? false : listCreditoMovimientos.isEmpty() ? false : true) {//JSA05            
                consulta.delete(0, consulta.length());
                consulta.append("delete ");
                consulta.append("CreditoMovimientos o where o.id = :valor");
                int i, j, k;
                for (i = 0; i < listCreditoMovimientos.size(); i++) {
//                    if (listCreditoMovimientos.get(i).getCreditoPorEmpleado().getCreditoAhorro().getTipoConfiguracion().equalsIgnoreCase("1")) {//TipoConfiguracion 1 Creditos
//                        listCreditoMovimientos.get(i).getCreditoPorEmpleado().setSaldo(listCreditoMovimientos.get(i).getCreditoPorEmpleado().getSaldo() + listCreditoMovimientos.get(i).getImporte());
//                    } else {//TipoConfiguracion 2 Ahorro
//                        listCreditoMovimientos.get(i).getCreditoPorEmpleado().setSaldo(listCreditoMovimientos.get(i).getCreditoPorEmpleado().getSaldo() - listCreditoMovimientos.get(i).getImporte());
//                    }
                    getSession().saveOrUpdate(listCreditoMovimientos.get(i).getCreditoPorEmpleado());
                    if (listCreditoMovimientos.get(i).getMovNomConceps() != null) {
                        int movimientosEliminados = 0;
                        for (j = 0; j < listCreditoMovimientos.get(i).getMovNomConceps().size(); j++) {
                            listCreditoMovimientos.get(i).getMovNomConceps().get(j).setCalculado(0.0);
                            listCreditoMovimientos.get(i).getMovNomConceps().get(j).setResultado(0.0);
                            listCreditoMovimientos.get(i).getMovNomConceps().get(j).setCreditoMovimientos(null);
                            if (listCreditoMovimientos.get(i).getMovNomConceps().get(j).getMovNomBaseAfecta() != null) {
                                for (k = 0; k < listCreditoMovimientos.get(i).getMovNomConceps().get(j).getMovNomBaseAfecta().size(); k++) {
                                    listCreditoMovimientos.get(i).getMovNomConceps().get(j).getMovNomBaseAfecta().get(k).setResultado(0.0);
                                    listCreditoMovimientos.get(i).getMovNomConceps().get(j).getMovNomBaseAfecta().get(k).setResultadoExento(0.0);
                                }
                            }
//                            if (listCreditoMovimientos.get(i).getMovNomConceps().get(j).getMovNomConceParam() != null) {
//                                for (k = 0; k < listCreditoMovimientos.get(i).getMovNomConceps().get(j).getMovNomConceParam().size(); k++) {
//                                    //listCreditoMovimientos.get(i).getMovNomConceps().get(j).getMovNomConceParam().get(k).setValor(null);
//                                }
//                            }
                            getSession().saveOrUpdate(listCreditoMovimientos.get(i).getMovNomConceps().get(j));
                            if (listCreditoMovimientos.get(i).getMovNomConceps().get(j).getTipoPantalla().equals(100) || incluirEliminadoDiferenteTipoPantalla100) {
                                getSession().delete(listCreditoMovimientos.get(i).getMovNomConceps().get(j));
                                movimientosEliminados++;
                            }
                        }
                        listCreditoMovimientos.get(i).getMovNomConceps().removeAll(listCreditoMovimientos.get(i).getMovNomConceps());
                        getSession().delete(listCreditoMovimientos.get(i));
                    }
                    try {
//                        q = getSession().createQuery(consulta.toString());
//                        q.setParameter("valor", listCreditoMovimientos.get(i).getId());
//                        exito = q.executeUpdate();
//                        System.out.println("exito " + exito);
                    } catch (HibernateException ex) {
                        //Estos no se pueden eliminar por que tienen una relacion ya que el tipo de pantalla es diferente de 100 y aparte la variable 
                        //incluirEliminadoDiferenteTipoPantalla100 viene false y esta bien, ya que los movimientos que usuario agrego no se deben de borrar solo cuando
                        //se desea borrar todos los movimientos de una plaza por empleado para poder eliminar la plazaporempleado.
                        System.err.println(concatena.delete(0, concatena.length()).append(msgError).append("deleteListQueryMov()2_Error: ").append(ex));
                    }
                }
            }
            consulta.delete(0, consulta.length());
            consulta.append("delete ");
            if (valores.length > 0) {
                //Elimina Bases Afectadas de Movimientos por Conceptos
                consulta.append("MovNomBaseAfecta o where o.movNomConcep.id in(:valores)");
                q = getSession().createQuery(consulta.toString());
                q.setParameterList("valores", valores);
                exito = q.executeUpdate();
                /////System.out.println("exito " + exito);
                consulta.delete(7, consulta.length());

                //Elimina Movimientos Por parametros de Movimientos por Conceptos
                consulta.append("MovNomConceParam o where o.movNomConcep.id in(:valores)");
                q = getSession().createQuery(consulta.toString());
                q.setParameterList("valores", valores);
                exito = q.executeUpdate();
                /////System.out.println("exito " + exito);
                consulta.delete(7, consulta.length());

                //Elimina Movimientos ISRRetenidos
                consulta.append(CalculoISR.class.getSimpleName()).append(" o where o.movNomConcep.id in(:valores)");//JSA06
                q = getSession().createQuery(consulta.toString());
                q.setParameterList("valores", valores);
                exito = q.executeUpdate();
                //////System.out.println("exito " + exito);
                consulta.delete(7, consulta.length());

                //Elimina Movimientos CalculoIMSS
                consulta.append(CalculoIMSS.class.getSimpleName()).append(" o where o.movNomConcep.id in(:valores)");
                q = getSession().createQuery(consulta.toString());
                q.setParameterList("valores", valores);
                exito = q.executeUpdate();
                ///////System.out.println("exito " + exito);
                consulta.delete(7, consulta.length());

                //Elimina Movimientos CalculoIMSSPatron
                consulta.append(CalculoIMSSPatron.class.getSimpleName()).append(" o where o.movNomConcep.id in(:valores)");
                q = getSession().createQuery(consulta.toString());
                q.setParameterList("valores", valores);
                exito = q.executeUpdate();
                /////////System.out.println("exito " + exito);
                consulta.delete(7, consulta.length());

                //Elimina Movimientos por Conceptos
                consulta.append(tabla).append(" where ").append(campo).append(" in(:valores)");
                q = getSession().createQuery(consulta.toString());
                q.setParameterList("valores", valores);
                exito = q.executeUpdate();
                //////////System.out.println("exito " + exito);
                consulta.delete(7, consulta.length());
            }
            if (valoresReestablecer == null ? false : valoresReestablecer.length > 0) {
                //Reestructurar Movimientos por Conceptos
                consulta.delete(0, consulta.length()).append("from ");
                consulta.append(tabla).append(" where ").append(campo).append(" in(:valores)");
                q = getSession().createQuery(consulta.toString());
                q.setParameterList("valores", valoresReestablecer);
                List<MovNomConcep> listMovNomConcepReestablecer = q.list();
                if (listMovNomConcepReestablecer != null) {
                    int j, k;
                    for (j = 0; j < listMovNomConcepReestablecer.size(); j++) {
                        listMovNomConcepReestablecer.get(j).setCalculado(0.0);
                        listMovNomConcepReestablecer.get(j).setResultado(0.0);
                        listMovNomConcepReestablecer.get(j).setCreditoMovimientos(null);
                        if (listMovNomConcepReestablecer.get(j).getMovNomBaseAfecta() != null) {
                            for (k = 0; k < listMovNomConcepReestablecer.get(j).getMovNomBaseAfecta().size(); k++) {
                                listMovNomConcepReestablecer.get(j).getMovNomBaseAfecta().get(k).setResultado(0.0);
                                listMovNomConcepReestablecer.get(j).getMovNomBaseAfecta().get(k).setResultadoExento(0.0);
                            }
                        }
//                        if (listMovNomConcepReestablecer.get(j).getMovNomConceParam() != null) {
//                            for (k = 0; k < listMovNomConcepReestablecer.get(j).getMovNomConceParam().size(); k++) {
//                                listMovNomConcepReestablecer.get(j).getMovNomConceParam().get(k).setValor(null);
//                            }
//                        }
                        getSession().saveOrUpdate(listMovNomConcepReestablecer.get(j));
                        if (listMovNomConcepReestablecer.get(j).getTipoPantalla().equals(100) || incluirEliminadoDiferenteTipoPantalla100) {
                            getSession().delete(listMovNomConcepReestablecer.get(j));
                        }
                    }
                }
                consulta.delete(0, consulta.length());
                consulta.append("delete ");
            }
            if (valoresCalculoUnidades != null ? valoresCalculoUnidades.length > 0 : false) {
                //Elimina Calculo de unidades
                consulta.append(CalculoUnidades.class.getSimpleName()).append(" where ").append(campo).append(" in(:valores)");
                q = getSession().createQuery(consulta.toString());
                q.setParameterList("valores", valoresCalculoUnidades);
                exito = q.executeUpdate();
                ///////System.out.println("exito " + exito);
                consulta.delete(7, consulta.length());
            }
            if (valoresCFDI == null ? false : !valoresCFDI.isEmpty()) {
                //Elimina Los timbres con status error o en proceso ancora
                for (CFDIEmpleado cfdiEmpl : valoresCFDI) {//JEVC05
                    exito = deleteListQuery("CFDIEmpleado", "id", new Object[]{cfdiEmpl.getId()});
                    consulta.delete(0, consulta.length());//CFDIReciboConcepto
                    consulta.append("select cfdiCnc.id from CFDIRecibo o INNER JOIN o.cfdiReciboConceptos cfdiCnc where o.id= :idRecibo ");
                    q = getSession().createQuery(consulta.toString());
                    if (cfdiEmpl.getCfdiRecibo().getId() != null) {
                        Long idRecibo = cfdiEmpl.getCfdiRecibo().getId();
                        q.setParameter("idRecibo", idRecibo);
                        List<Object[]> cfdiCnc = q.list();
                        if (!cfdiCnc.isEmpty()) {
                            exito = deleteListQuery("CFDIReciboConcepto", "id", cfdiCnc.toArray());
                            /////////////System.out.println("exito " + exito);
                        }
                        consulta.delete(0, consulta.length());//CFDIReciboIncapacidad
                        consulta.append("select cfdiInc.id from CFDIRecibo o INNER JOIN o.cfdiReciboIncapacidades cfdiInc where o.id= :idRecibo ");
                        q = getSession().createQuery(consulta.toString());
                        q.setParameter("idRecibo", idRecibo);
                        List<Object[]> cfdiInc = q.list();
                        if (!cfdiInc.isEmpty()) {
                            exito = deleteListQuery("CFDIReciboIncapacidad", "id", cfdiInc.toArray());
                            ///////////////System.out.println("exito " + exito);
                        }
                        consulta.delete(0, consulta.length());//CFDIReciboHrsExtras
                        consulta.append("select cfdiHrs.id from CFDIRecibo o INNER JOIN o.cfdiReciboHrsExtras cfdiHrs  where o.id= :idRecibo ");
                        q = getSession().createQuery(consulta.toString());
                        q.setParameter("idRecibo", idRecibo);
                        List<Object[]> cfdiHrs = q.list();
                        if (!cfdiHrs.isEmpty()) {
                            exito = deleteListQuery("CFDIReciboHrsExtras", "id", cfdiHrs.toArray());
                            /////////////System.out.println("exito " + exito);
                        }
                        exito = deleteListQuery("CFDIRecibo", "id", new Object[]{idRecibo});
                        ///////////System.out.println("exito " + exito);
                    }
                }
            }
        } catch (HibernateException ex) {
            System.err.println(concatena.delete(0, concatena.length()).append(msgError).append("deleteListQueryMov()1_Error: ").append(ex));
            mensajeResultado.setNoError(ControlErroresHibernate.buscaNoErrorPorExcepcion(ex.getClass()));
            mensajeResultado.setError(ex.getLocalizedMessage());
            mensajeResultado.setResultado(null);
            exito = -1;
        }
        return exito;
    }

    public Mensaje getMovimientosPorPlazaEmpleado(Object[] clavesPlazasPorEmpleado, String claveTipoCorrida, String claveRazonSocial, String uuidCxn) {
        List<MovNomConcep> values;
        try {
            inicializaVariableMensaje();
            setSession(HibernateUtil.currentSession(uuidCxn));
            getSession().beginTransaction();
            q = getSession().createQuery("from MovNomConcep m where m.plazasPorEmpleado.clave in(:clavesPlazasPorEmpleado) And m.tipoCorrida.clave = :claveTipoCorrida Order by m.plazasPorEmpleado.clave");
            q.setParameterList("clavesPlazasPorEmpleado", clavesPlazasPorEmpleado);
            q.setParameter("claveTipoCorrida", claveTipoCorrida);
            //q.setParameter("claveRazonSocial", claveRazonSocial);
            values = q.list();
            mensajeResultado.setResultado(values);
            mensajeResultado.setNoError(0);
            mensajeResultado.setError("");
            getSession().getTransaction().commit();
        } catch (HibernateException ex) {
            System.err.println(concatena.delete(0, concatena.length()).append(msgError).append("getMovimientosPorPlazaEmpleado()1_Error: ").append(ex));
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

    public Mensaje getMovimientosPorFiltro(String[] camposWhere, Object[] valoresWhere, String uuidCxn) {
        List<MovNomConcep> values;
        try {
            inicializaVariableMensaje();
            setSession(HibernateUtil.currentSession(uuidCxn));
            getSession().beginTransaction();
            consulta = new StringBuilder("from MovNomConcep o where ");
            consulta.append(construyeQueryCampos(camposWhere, "camposWhere", "AND"));
            q = getSession().createQuery(consulta.toString());
            int i;
            if (camposWhere != null & valoresWhere != null) {
                for (i = 0; i < valoresWhere.length; i++) {
                    if (valoresWhere[i].getClass().equals(ArrayList.class) || valoresWhere[i].getClass().equals(Object[].class)) {//JSA03
                        if (valoresWhere[i].getClass().equals(Object[].class)) {
                            if (camposWhere[i].contains("BETWEEN")) {
                                q.setParameter("camposWhere".concat(String.valueOf(i)).concat("1"), ((Object[]) valoresWhere[i])[0]);
                                q.setParameter("camposWhere".concat(String.valueOf(i)).concat("2"), ((Object[]) valoresWhere[i])[1]);
                            } else {
                                q.setParameterList("camposWhere".concat(String.valueOf(i)), (Object[]) valoresWhere[i]);
                            }
                        } else {
                            q.setParameterList("camposWhere".concat(String.valueOf(i)), (ArrayList) valoresWhere[i]);
                        }
                    } else {
                        q.setParameter("camposWhere".concat(String.valueOf(i)), valoresWhere[i]);
                    }
                }
            }
            values = q.list();
            mensajeResultado.setResultado(values);
            mensajeResultado.setNoError(0);
            mensajeResultado.setError("");
            getSession().getTransaction().commit();
        } catch (HibernateException ex) {
            System.err.println(concatena.delete(0, concatena.length()).append(msgError).append("getMovimientosPorFiltro()1_Error: ").append(ex));
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

    public Mensaje getMovimientosPorFiltroEspecifico(String[] camposWhere, Object[] valoresWhere, String uuidCxn) {
        List<Object> values;
        try {
            inicializaVariableMensaje();
            setSession(HibernateUtil.currentSession(uuidCxn));
            getSession().beginTransaction();
            consulta = new StringBuilder("select new list(o.id,o.concepNomDefi.clave,o.concepNomDefi.descripcion,o.empleado.clave, o.empleado.nombre, o.empleado.apellidoPaterno, o.empleado.apellidoMaterno, o.plazasPorEmpleado.clave, o.tipoNomina.descripcion, o.periodosNomina.descripcion, o.periodosNomina.id, o.resultado,o.periodosNomina.clave,o.periodosNomina.año,o.tipoPantalla )from MovNomConcep o where ");//JSA03
            consulta.append(construyeQueryCampos(camposWhere, "camposWhere", "AND"));
            consulta.append(" order by o.empleado.clave, o.concepNomDefi.clave ");//JSA04
            q = getSession().createQuery(consulta.toString());
            int i;
            if (camposWhere != null & valoresWhere != null) {
                for (i = 0; i < valoresWhere.length; i++) {
                    q.setParameter("camposWhere".concat(String.valueOf(i)), valoresWhere[i]);
                }
            }
            values = q.list();
            mensajeResultado.setResultado(values);
            mensajeResultado.setNoError(0);
            mensajeResultado.setError("");
            getSession().getTransaction().commit();
        } catch (HibernateException ex) {
            System.err.println(concatena.delete(0, concatena.length()).append(msgError).append("getMovimientosPorFiltroEspecifico()1_Error: ").append(ex));
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

    public Mensaje getCalculosUnidadesPorFiltroEspecificoConsulta(String[] camposWhere, Object[] valoresWhere, List<CFDIEmpleado> listCFDIEmpleado) {
        List<CalculoUnidades> listCalculoUnidades = null;
        List<Long> idCalculoUnidades = null;
        inicializaVariableMensaje();
        try {
            consulta = new StringBuilder("select o from ").append(CalculoUnidades.class.getSimpleName());
            consulta.append(" o where ");
            consulta.append(construyeQueryCampos(camposWhere, "camposWhere", "AND"));
            q = getSession().createQuery(consulta.toString());
            int i;
            if (camposWhere != null & valoresWhere != null) {
                for (i = 0; i < valoresWhere.length; i++) {
                    q.setParameter("camposWhere".concat(String.valueOf(i)), valoresWhere[i]);
                }
            }
            listCalculoUnidades = q.list();
            if (listCFDIEmpleado == null) {
                listCFDIEmpleado = new ArrayList<CFDIEmpleado>();
            }
            if (listCalculoUnidades == null) {
                listCalculoUnidades = new ArrayList<CalculoUnidades>();
            }
            idCalculoUnidades = new ArrayList<Long>();
            boolean encontrotimbrado = false;
            for (i = 0; i < listCalculoUnidades.size(); i++) {
                if (listCFDIEmpleado.isEmpty()) {
                    idCalculoUnidades.add(listCalculoUnidades.get(i).getId());
                } else {
                    encontrotimbrado = false;
                    for (int j = 0; j < listCFDIEmpleado.size(); j++) {
                        if (listCFDIEmpleado.get(j).getPlazaPorEmpleadoMov().getPlazasPorEmpleado().getEmpleados().getId().equals(listCalculoUnidades.get(i).getEmpleado().getId())
                                & listCFDIEmpleado.get(j).getTipoNomina().getId().equals(listCalculoUnidades.get(i).getTipoNomina().getId())
                                & listCFDIEmpleado.get(j).getPeriodosNomina().getId().equals(listCalculoUnidades.get(i).getPeriodosNomina().getId())
                                & listCFDIEmpleado.get(j).getRazonesSociales().getId().equals(listCalculoUnidades.get(i).getRazonesSociales().getId())
                                & listCFDIEmpleado.get(j).getTipoCorrida().getId().equals(listCalculoUnidades.get(i).getTipoCorrida().getId())) {
                            if (listCFDIEmpleado.get(j).getCfdiRecibo().isStatusTimbrado().equals(StatusTimbrado.TIMBRADO)) {
                                encontrotimbrado = true;
                                break;
                            }
                        }
                    }
                    if (!encontrotimbrado) {
                        idCalculoUnidades.add(listCalculoUnidades.get(i).getId());
                    }
                }
            }
            mensajeResultado.setResultado(idCalculoUnidades);
            mensajeResultado.setNoError(0);
            mensajeResultado.setError("");
        } catch (HibernateException ex) {
            System.err.println(concatena.delete(0, concatena.length()).append(msgError).append("getCalculosUnidadesPorFiltroEspecificoConsulta()1_Error: ").append(ex));
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

    public Mensaje getCalculosUnidadesPorFiltroEspecifico(String[] camposWhere, Object[] valoresWhere, List<CFDIEmpleado> listCFDIEmpleado, String uuidCxn) {
        try {
            setSession(HibernateUtil.currentSession(uuidCxn));
            getSession().beginTransaction();
            inicializaVariableMensaje();
            mensajeResultado = getCalculosUnidadesPorFiltroEspecificoConsulta(camposWhere, valoresWhere, listCFDIEmpleado);
            getSession().getTransaction().commit();
        } catch (HibernateException ex) {
            System.err.println(concatena.delete(0, concatena.length()).append(msgError).append("getCalculosUnidadesPorFiltroEspecifico()1_Error: ").append(ex));
            try {
                if (getSession().getTransaction().isActive()) {
                    getSession().getTransaction().rollback();
                }
                mensajeResultado.setNoError(ControlErroresHibernate.buscaNoErrorPorExcepcion(ex.getClass()));
                mensajeResultado.setError(ex.getLocalizedMessage());
            } catch (HibernateException exc) {//cuando marca error al hacer rollback pro ejemplo se cae conexion servidor
                mensajeResultado.setNoError(ControlErroresHibernate.buscaNoErrorPorExcepcion(exc.getClass()));
                mensajeResultado.setError(exc.getLocalizedMessage());
            }
            mensajeResultado.setResultado(null);
        }
        return mensajeResultado;
    }

    public Mensaje eliminaListaMovimientos(String campo, Object[] valores, List<CFDIEmpleado> valoresCFDI, Object[] valoresCalculoUnidades, Object[] valoresReestablecer, boolean incluirEliminadoDiferenteTipoPantalla100, String uuidCxn) {
        int resultado;
        try {
            inicializaVariableMensaje();
            setSession(HibernateUtil.currentSession(uuidCxn));
            getSession().beginTransaction();
            resultado = deleteListQueryMov(MovNomConcep.class.getSimpleName(), campo, valores, valoresCFDI, valoresCalculoUnidades, valoresReestablecer, incluirEliminadoDiferenteTipoPantalla100);
            mensajeResultado.setResultado(resultado);
            mensajeResultado.setNoError(0);
            mensajeResultado.setError("");
            getSession().getTransaction().commit();
        } catch (HibernateException ex) {
            System.err.println(concatena.delete(0, concatena.length()).append(msgError).append("eliminaListaMovimientos()1_Error: ").append(ex));
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

    //Metodo para las mascaras de los parametros
    private String agregarMascaraValorNumerico(Object valor, String formato) {
        String resultado;
        DecimalFormat decimalFormat = new DecimalFormat(formato);
        if (formato.lastIndexOf("&") != -1) {
            decimalFormat.setGroupingSize(3);
        }
        resultado = decimalFormat.format(valor);
        return resultado;
    }

    ////                System.out.println(Or.getTipoNomina().getClave() + " " + Or.getTipoCorrida().getClave()
////                        + " " + Or.getPeriodosNomina().getClave() + " " + Or.getConcepNomDefi().getClave()
////                        + " " + Or.getCentroDeCosto() + " " + Or.getEmpleado().getClave() + " "
////                        + Or.getPlazasPorEmpleado().getClave() + " " + Or.getRazonesSociales().getClave()
////                        + " " + Or.getTipoPantalla() + " " + Or.getNumero() + " " + Or.getMes());
    @Override
    public Mensaje buscaMovimientosNominaFiltrado(List<Object> valoresDeFiltrado, String uuidCxn) {
        List<MovNomConcep> movNomConceptos = new ArrayList<MovNomConcep>();
        valoresDeFiltrado = valoresDeFiltrado == null ? new ArrayList<Object>() : valoresDeFiltrado;
        strQuery = new StringBuilder("select mov From PlazasPorEmpleadosMov pm").append(" ");
        strQuery.append("LEFT JOIN pm.plazasPorEmpleado pl LEFT OUTER JOIN pl.empleados emp,");
        strQuery.append("MovNomConcep mov RIGHT OUTER JOIN mov.concepNomDefi cnc LEFT OUTER JOIN pl.razonesSociales rs").append(" ");
        strQuery.append("LEFT OUTER JOIN pm.departamentos dep LEFT OUTER JOIN pm.centroDeCosto cent").append(" ");
        strQuery.append("RIGHT OUTER JOIN pl.registroPatronal reg RIGHT OUTER JOIN mov.tipoCorrida tipcorr").append(" ");
        strQuery.append("RIGHT OUTER JOIN mov.periodosNomina per RIGHT OUTER JOIN").append(" ");
        strQuery.append("mov.tipoNomina tipNom RIGHT OUTER JOIN mov.empleado x9 RIGHT OUTER JOIN mov.plazasPorEmpleado x10").append(" ");
        strQuery.append("RIGHT OUTER JOIN mov.razonesSociales x11 RIGHT OUTER JOIN mov.empleado x9").append(" ");
        strQuery.append("LEFT OUTER JOIN pm.departamentos dep LEFT OUTER JOIN pm.centroDeCosto cent LEFT  OUTER JOIN pm.plazas plaz").append(" ");
        StringBuilder strQueryWhere = new StringBuilder("");
        int i;
        List<String> camposWhere = new ArrayList<String>();
        List<Object> valoresWhere = new ArrayList<Object>();
        boolean empleadoInicio = true;
        mensajeResultado.setNoError(0);
        mensajeResultado.setError("");
        mensajeResultado.setResultado(movNomConceptos);
        if (valoresDeFiltrado.size() > 0) {
            Empleados empIni = null;
            Empleados empFin = null;
            try {
                strQueryWhere.append("pl.id = mov.plazasPorEmpleado.id").append(" ");
                for (i = 0; i < valoresDeFiltrado.size(); i++) {
                    if (valoresDeFiltrado.get(i) instanceof Empleados & empleadoInicio) {
                        empleadoInicio = false;
                        empIni = (Empleados) valoresDeFiltrado.get(i);
                        if (empIni.getId() == null) {
                            empIni = null;
                        }
                    } else if (valoresDeFiltrado.get(i) instanceof Empleados) {
                        empFin = (Empleados) valoresDeFiltrado.get(i);
                        if (empFin.getId() == null) {
                            empFin = null;
                        }
                    }

                    if (valoresDeFiltrado.get(i) instanceof RazonesSociales) {
                        strQueryWhere.append(strQueryWhere.length() > 0 ? "AND " : "").append("rs.clave = :claveRazonSocial").append(" ");
                        camposWhere.add("claveRazonSocial");
                        valoresWhere.add(((RazonesSociales) valoresDeFiltrado.get(i)).getClave());
                    } else if (valoresDeFiltrado.get(i) instanceof TipoCorrida) {
                        strQueryWhere.append(strQueryWhere.length() > 0 ? "AND " : "").append("tipcorr.clave = :claveTipoCorrida AND per.tipoCorrida.clave = :claveTipoCorrida ").append(" ");
                        camposWhere.add("claveTipoCorrida");
                        valoresWhere.add(((TipoCorrida) valoresDeFiltrado.get(i)).getClave());
                    } else if (valoresDeFiltrado.get(i) instanceof PeriodosNomina) {
                        strQueryWhere.append(strQueryWhere.length() > 0 ? "AND" : "").append("(per.fechaInicial BETWEEN :fechaInicial AND :fechaFinal OR per.fechaFinal BETWEEN :fechaInicial AND  :fechaFinal)").append(" ");
                        camposWhere.add("fechaInicial");
                        camposWhere.add("fechaFinal");
                        valoresWhere.add(((PeriodosNomina) valoresDeFiltrado.get(i)).getFechaInicial());
                        valoresWhere.add(((PeriodosNomina) valoresDeFiltrado.get(i)).getFechaFinal());
                    } else if (valoresDeFiltrado.get(i) instanceof RazonesSociales) {
                        strQueryWhere.append(strQueryWhere.length() > 0 ? "AND" : "").append("pm.id IN (");
                        strQueryWhere.append("SELECT pm.id FROM PlazasPorEmpleadosMov pm LEFT OUTER JOIN pm.plazasPorEmpleado pl").append(" ");
                        strQueryWhere.append("LEFT OUTER JOIN pl.razonesSociales rs LEFT OUTER JOIN pl.empleados mov WHERE rs.clave =").append(" ");
                        strQueryWhere.append(":claveRazonsocial").append(" ");
                        camposWhere.add("claveRazonsocial");
                        valoresWhere.add(((RazonesSociales) valoresDeFiltrado.get(i)).getClave());
                    } else if (valoresDeFiltrado.get(i) instanceof PeriodosNomina) {
                        strQueryWhere.append(strQueryWhere.length() > 0 ? "AND" : "").append("pm.fechaInicial IN (SELECT max(x0X.fechaInicial) FROM PlazasPorEmpleadosMov x0X LEFT OUTER JOIN x0X.plazasPorEmpleado x1X").append(" ");
                        strQueryWhere.append("LEFT OUTER JOIN x1X.empleados x3X WHERE (");
                        strQueryWhere.append("x0X.fechaInicial <= :fechaFinal ) AND mov.id = x3X.id)").append(" ");
                        camposWhere.add("fechaFinal");
                        valoresWhere.add(((PeriodosNomina) valoresDeFiltrado.get(i)).getFechaFinal());
                    }

                }
                if (empIni != null && empFin != null) {
                    if (strQueryWhere.length() > 0) {
                        strQueryWhere.append(" AND ");
                    }
                    strQueryWhere.append("(pl.empleados.clave >= :claveEmpIni AND pl.empleados.clave <= :claveEmpFin)").append(" ");
                    camposWhere.add("claveEmpIni");
                    valoresWhere.add(empIni.getClave());
                    camposWhere.add("claveEmpFin");
                    valoresWhere.add(empFin.getClave());
                } else if (empIni != null) {
                    if (strQueryWhere.length() > 0) {
                        strQueryWhere.append(" AND ");
                    }
                    strQueryWhere.append("pl.empleados.clave >= :claveEmpleado").append(" ");
                    camposWhere.add("claveEmpleado");
                    valoresWhere.add(empIni.getClave());
                } else if (empFin != null) {
                    if (strQueryWhere.length() > 0) {
                        strQueryWhere.append(" AND ");
                    }
                    strQueryWhere.append("pl.empleados.clave <= :claveEmpleado").append(" ");
                    camposWhere.add("claveEmpleado");
                    valoresWhere.add(empFin.getClave());
                }
                inicializaVariableMensaje();
                setSession(HibernateUtil.currentSession(uuidCxn));
                getSession().beginTransaction();
                strQuery.append("WHERE").append(" ").append(strQueryWhere).append(")");
                movNomConceptos = (List<MovNomConcep>) ejecutaQueryList(strQuery.toString(), camposWhere.toArray(new String[]{}), valoresWhere.toArray(), 0);
                mensajeResultado.setResultado(movNomConceptos);
                getSession().getTransaction().commit();
            } catch (HibernateException ex) {
                System.err.println(concatena.delete(0, concatena.length()).append(msgError).append("buscaMovimientosNominaFiltrado()1_Error: ").append(ex));
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
                ////mensajeResultado.setError(concatena.delete(0, concatena.length()).append("No se guardo la informacion, la causa fue por el empleado con clave ").append(entitysCambios.get(i).getPlazaPorEmpleadoMov().getPlazasPorEmpleado().getEmpleados().getClave()).append(" ").append(msgError).append("saveDeleteCFDIEmpleado()1_Error: ").append(ex).toString());
                mensajeResultado.setResultado(null);
            }
        }

        return mensajeResultado;
    }

    private List<?> ejecutaQueryList(String strQuery, String[] camposParametro, Object[] valoresParametro, Integer maxResultados) {
        List<Object> result = null;
        try {
            Query query = getSession().createQuery(strQuery);

            int i;
            if (camposParametro != null & valoresParametro != null) {
                for (i = 0; i < camposParametro.length; i++) {
                    if (valoresParametro[i] instanceof Object[]) {
                        query.setParameterList(camposParametro[i], (Object[]) valoresParametro[i]);
                    } else {
                        if (valoresParametro[i] instanceof Calendar) {
                            Calendar c = (Calendar) valoresParametro[i];
                            query.setParameter(camposParametro[i], c.getTime());
                        } else {
                            query.setParameter(camposParametro[i], valoresParametro[i]);
                        }
                    }
                }
            }
            if (maxResultados == null) {
                maxResultados = 0;
            }

            if (maxResultados > 0) {
                query.setMaxResults(maxResultados);
            }

            result = query.list();
        } catch (HibernateException ex) {
            System.err.println(concatena.delete(0, concatena.length()).append(msgError).append("ejecutaQueryList()1_Error: ").append(ex));
            mensajeResultado.setNoError(ControlErroresHibernate.buscaNoErrorPorExcepcion(ex.getClass()));
            mensajeResultado.setError(ex.getLocalizedMessage());
            mensajeResultado.setResultado(null);
        } catch (Exception e) {
            System.err.println(concatena.delete(0, concatena.length()).append(msgError).append("ejecutaQueryList()1_Error: ").append(e));
            mensajeResultado.setNoError(ControlErroresHibernate.buscaNoErrorPorExcepcion(e.getClass()));
            mensajeResultado.setError(e.getLocalizedMessage());
            mensajeResultado.setResultado(null);
        }
        return result;
    }
}
