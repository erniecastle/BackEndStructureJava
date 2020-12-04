/**
 * @author: Victor Lopez Fecha de Creación: 11/10/2014 Compañía: MacroPro.
 * Descripción del programa: clase SemaforoCalculoNomina para llamados a metodos
 * de HIBERNATE
 * -----------------------------------------------------------------------------
 * MODIFICACIONES:
 * -----------------------------------------------------------------------------
 */
package com.mef.erp.modelo;

import com.mef.erp.modelo.entidad.Mensaje;
import com.mef.erp.modelo.entidad.PeriodosNomina;
import com.mef.erp.modelo.entidad.SemaforoCalculoNomina;
import com.mef.erp.util.ControlErroresHibernate;
import com.mef.erp.util.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.exception.ConstraintViolationException;

public class SemaforoCalculoNominaDAO extends GenericHibernateDAO<SemaforoCalculoNomina, String>
        implements SemaforoCalculoNominaDAOIF {

    private final StringBuilder concatena = new StringBuilder(200);
    private final String msgError = concatena.append("ServerERP"/*util.getContexto()*/).append(".MSERR_C_").append(this.getClass().getName()).append(".").toString();

    public Mensaje agregar(SemaforoCalculoNomina entity, String uuidCxn) {
        Mensaje semaforo = new Mensaje();
        try {
            setSession(HibernateUtil.currentSession(uuidCxn));
            getSession().beginTransaction();

            SemaforoCalculoNomina smf = (SemaforoCalculoNomina) existeClave(SemaforoCalculoNomina.class.getSimpleName(), new String[]{"tipoNomina.clave", "periodoNomina.id"}, new Object[]{entity.getTipoNomina().getClave(), entity.getPeriodoNomina().getId()}, null);
            if (smf == null) {
                semaforo = addValoresSemaforo(semaforo, entity);
//                if (entity.getPeriodoNomina().isStatus()) {
//                    PeriodosNomina periodo = (PeriodosNomina) existeClave(PeriodosNomina.class.getSimpleName(), new String[]{"id", "status"}, new Object[]{entity.getPeriodoNomina().getId(), false}, null);
//                    if (periodo == null) {
//                        entity = makePersistent(entity);
//                        semaforo.setResultado(entity);
//                        semaforo.setNoError(entity == null ? 1 : 0);
//                    } else {
//                        semaforo.setResultado(null);
//                        semaforo.setNoError(2);
//                    }
//                }
            } else {
                if (!smf.getPeriodoNomina().isStatus()) {
                    semaforo.setNoError(2);
                    semaforo.setResultado(null);
                } else if (entity.getTiempoInicio().getTime() - smf.getTiempoInicio().getTime() > 10800000 /*3 hrs*/) {
                    makeTransient(smf);
                    flush();
                    semaforo = addValoresSemaforo(semaforo, entity);
//                    PeriodosNomina periodo = (PeriodosNomina) existeClave(PeriodosNomina.class.getSimpleName(), new String[]{"id", "status"}, new Object[]{entity.getPeriodoNomina().getId(), false}, null);
//                    if (periodo == null) {
//                        entity = makePersistent(entity);
//                        semaforo.setResultado(entity);
//                        semaforo.setNoError(entity == null ? 1 : 0);
//                    } else {
//                        semaforo.setResultado(null);
//                        semaforo.setNoError(2);
//                    }
                } else if (entity.getUsuario().equalsIgnoreCase(smf.getUsuario()) & entity.getTipo() == smf.getTipo()) {
                    if (entity.getPeriodoNomina().isStatus()) {
                        smf.setTiempoInicio(entity.getTiempoInicio());
                        semaforo = addValoresSemaforo(semaforo, smf);
                    } else {
                        semaforo.setNoError(2);
                        semaforo.setResultado(null);
                    }
                } else {
                    if (entity.getTipo() == SemaforoCalculoNomina.Tipo.AGREGA_MOVIMIENTOS) {
                        if (smf.getTipo() == SemaforoCalculoNomina.Tipo.CIERRE_PERIODO) {
                            semaforo.setNoError(7);
                        } else {
                            semaforo.setNoError(8);
                        }

                    } else if (entity.getTipo() == SemaforoCalculoNomina.Tipo.CIERRE_PERIODO) {
                        if (smf.getTipo() == SemaforoCalculoNomina.Tipo.CALCULO_NOMINA) {
                            semaforo.setNoError(3);
                        } else {
                            semaforo.setNoError(4);
                        }

                    } else if (entity.getTipo() == SemaforoCalculoNomina.Tipo.CALCULO_NOMINA) {
                        if (smf.getTipo() == SemaforoCalculoNomina.Tipo.CIERRE_PERIODO) {
                            semaforo.setNoError(2);
                        } else if (smf.getTipo() == SemaforoCalculoNomina.Tipo.AGREGA_MOVIMIENTOS) {
                            semaforo.setNoError(5);
                        } else {
                            semaforo.setNoError(1);
                        }

                    } else if (entity.getPeriodoNomina().isStatus()) {
                        semaforo.setNoError(1);
                    } else {
                        semaforo.setNoError(2);
                    }
                    semaforo.setResultado(null);
                }
            }
            getSession().getTransaction().commit();
        } catch (ConstraintViolationException ex) {
            System.err.println(concatena.delete(0, concatena.length()).append(msgError).append("agregar()1_Error: ").append(ex));
            try {
                if (getSession().getTransaction().isActive()) {
                    getSession().getTransaction().rollback();
                }
                semaforo.setError(ex.getLocalizedMessage());
            } catch (HibernateException exc) {  //cuando marca error al hacer rollback pro ejemplo se cae conexion servidor
                semaforo.setError(exc.getLocalizedMessage());
            }
            semaforo.setResultado(null);
            semaforo.setNoError(1);
        } catch (HibernateException ex) {
            System.err.println(concatena.delete(0, concatena.length()).append(msgError).append("agregar()1_Error: ").append(ex));
            try {
                if (getSession().getTransaction().isActive()) {
                    getSession().getTransaction().rollback();
                }
                semaforo.setError(ex.getLocalizedMessage());
            } catch (HibernateException exc) {  //cuando marca error al hacer rollback pro ejemplo se cae conexion servidor
                semaforo.setError(exc.getLocalizedMessage());
            }
            semaforo.setResultado(null);
            semaforo.setNoError(1);
        }
        return semaforo;
    }

    private Mensaje addValoresSemaforo(Mensaje mensaje, SemaforoCalculoNomina entity) {
        if (entity.getPeriodoNomina().isStatus()) {
            PeriodosNomina periodo = (PeriodosNomina) existeClave(PeriodosNomina.class.getSimpleName(), new String[]{"id", "status"}, new Object[]{entity.getPeriodoNomina().getId(), false}, null);
            if (periodo == null) {
                entity = makePersistent(entity);
                mensaje.setResultado(entity);
                mensaje.setNoError(entity == null ? 1 : 0);
            } else {
                mensaje.setResultado(null);
                mensaje.setNoError(2);
            }
        } else {
            entity = makePersistent(entity);
            mensaje.setResultado(entity);
            mensaje.setNoError(entity == null ? 1 : 0);
        }
        return mensaje;
    }

    //JSA01
    public Mensaje eliminar(SemaforoCalculoNomina entity, String uuidCxn) {
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
            mensajeResultado.setResultado(null);;
        }
        return mensajeResultado;
    }
}
