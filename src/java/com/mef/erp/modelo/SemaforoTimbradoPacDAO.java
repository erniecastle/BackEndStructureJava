/**
 * @author: Victor Lopeez Fecha de Creación: 11/10/2014 Compañía: MacroPro.
 * Descripción del programa: clase SemaforoTimbradoPac para llamados a metodos
 * de HIBERNATE
 * -----------------------------------------------------------------------------
 * MODIFICACIONES:
 * -----------------------------------------------------------------------------
 */
package com.mef.erp.modelo;

import com.mef.erp.modelo.entidad.Mensaje;
import com.mef.erp.modelo.entidad.SemaforoTimbradoPac;
import com.mef.erp.util.ControlErroresHibernate;
import com.mef.erp.util.HibernateUtil;
import java.util.Calendar;
import org.hibernate.HibernateException;
import org.hibernate.exception.ConstraintViolationException;

public class SemaforoTimbradoPacDAO extends GenericHibernateDAO<SemaforoTimbradoPac, String>
        implements SemaforoTimbradoPacDAOIF {

    private final StringBuilder concatena = new StringBuilder(200);
    private final String msgError = concatena.append("ServerERP"/*util.getContexto()*/).append(".MSERR_C_").append(this.getClass().getName()).append(".").toString();

    public Mensaje agregar(SemaforoTimbradoPac entity, String uuidCxn) {
        Mensaje semaforo = new Mensaje();
        try {
            setSession(HibernateUtil.currentSession(uuidCxn));
            getSession().beginTransaction();

            SemaforoTimbradoPac smf = (SemaforoTimbradoPac) existeClave(SemaforoTimbradoPac.class.getSimpleName(), new String[]{"tipoNomina.clave", "periodoNomina.id"}, new Object[]{entity.getTipoNomina().getClave(), entity.getPeriodoNomina().getId()}, null);
            if (smf == null) {
                semaforo = addValoresSemaforo(semaforo, entity);
            } else {
                Calendar cal = Calendar.getInstance();
                Calendar calNueva = Calendar.getInstance();
                cal.setTime(smf.getTiempoInicio());
                calNueva.setTime(entity.getTiempoInicio());
                cal.set(Calendar.HOUR, 0);
                cal.set(Calendar.MINUTE, 0);
                cal.set(Calendar.SECOND, 0);
                cal.set(Calendar.MILLISECOND, 0);
                calNueva.set(Calendar.HOUR, 0);
                calNueva.set(Calendar.MINUTE, 0);
                calNueva.set(Calendar.SECOND, 0);
                calNueva.set(Calendar.MILLISECOND, 0);
                if (!calNueva.getTime().equals(cal.getTime())) {
                    makeTransient(smf);
                    flush();
                    semaforo = addValoresSemaforo(semaforo, entity);
                } else if (entity.getTiempoInicio().getTime() - smf.getTiempoInicio().getTime() > 10800000 /*3 hrs*/) {
                    makeTransient(smf);
                    flush();
                    semaforo = addValoresSemaforo(semaforo, entity);
                } else if (entity.getUsuario().equalsIgnoreCase(smf.getUsuario())) {
                    smf.setTiempoInicio(entity.getTiempoInicio());
                    semaforo = addValoresSemaforo(semaforo, smf);
                } else {
                    if (entity.getTipo() == SemaforoTimbradoPac.Tipo.ABRIENDO_PERIODO) {
                        if (smf.getTipo() == SemaforoTimbradoPac.Tipo.GENERANDO_DATOS_TIMBRADO) {
                            semaforo.setNoError(2);   //SemaforoTimbradoPac.Tipo.GENERANDO_DATOS_TIMBRADO
                        } else if (smf.getTipo() == SemaforoTimbradoPac.Tipo.TIMBRANDO) {
                            semaforo.setNoError(3);   //SemaforoTimbradoPac.Tipo.TIMBRANDO
                        } else if (smf.getTipo() == SemaforoTimbradoPac.Tipo.CANCELANDO_TIMBRADOS) {
                            semaforo.setNoError(4);   //SemaforoTimbradoPac.Tipo.CANCELANDO_TIMBRADOS
                        } else {
                            semaforo.setNoError(5);   //SemaforoTimbradoPac.Tipo.ABRIENDO_PERIODO
                        }
                    } else if (entity.getTipo() == SemaforoTimbradoPac.Tipo.GENERANDO_DATOS_TIMBRADO) {
                        if (smf.getTipo() == SemaforoTimbradoPac.Tipo.TIMBRANDO) {
                            semaforo.setNoError(3);   //SemaforoTimbradoPac.Tipo.TIMBRANDO
                        } else if (smf.getTipo() == SemaforoTimbradoPac.Tipo.CANCELANDO_TIMBRADOS) {
                            semaforo.setNoError(4);   //SemaforoTimbradoPac.Tipo.CANCELANDO_TIMBRADOS
                        } else if (smf.getTipo() == SemaforoTimbradoPac.Tipo.ABRIENDO_PERIODO) {
                            semaforo.setNoError(5);   //SemaforoTimbradoPac.Tipo.ABRIENDO_PERIODO
                        }  else {
                            semaforo.setNoError(2);   //SemaforoTimbradoPac.Tipo.GENERANDO_DATOS_TIMBRADO 
                        }
                    } else if (entity.getTipo() == SemaforoTimbradoPac.Tipo.TIMBRANDO) {
                        if (smf.getTipo() == SemaforoTimbradoPac.Tipo.GENERANDO_DATOS_TIMBRADO) {
                            semaforo.setNoError(2);   //SemaforoTimbradoPac.Tipo.GENERANDO_DATOS_TIMBRADO
                        } else if (smf.getTipo() == SemaforoTimbradoPac.Tipo.CANCELANDO_TIMBRADOS) {
                            semaforo.setNoError(4);   //SemaforoTimbradoPac.Tipo.CANCELANDO_TIMBRADOS
                        } else if (smf.getTipo() == SemaforoTimbradoPac.Tipo.ABRIENDO_PERIODO) {
                            semaforo.setNoError(5);   //SemaforoTimbradoPac.Tipo.ABRIENDO_PERIODO
                        }  else {
                            semaforo.setNoError(3);   //SemaforoTimbradoPac.Tipo.TIMBRANDO 
                        }
                    } else if (entity.getTipo() == SemaforoTimbradoPac.Tipo.CANCELANDO_TIMBRADOS) {
                        if (smf.getTipo() == SemaforoTimbradoPac.Tipo.GENERANDO_DATOS_TIMBRADO) {
                            semaforo.setNoError(2);   //SemaforoTimbradoPac.Tipo.GENERANDO_DATOS_TIMBRADO
                        } else if (smf.getTipo() == SemaforoTimbradoPac.Tipo.TIMBRANDO) {
                            semaforo.setNoError(3);   //SemaforoTimbradoPac.Tipo.TIMBRANDO
                        } else if (smf.getTipo() == SemaforoTimbradoPac.Tipo.ABRIENDO_PERIODO) {
                            semaforo.setNoError(5);   //SemaforoTimbradoPac.Tipo.ABRIENDO_PERIODO
                        } else {
                            semaforo.setNoError(4);   //SemaforoTimbradoPac.Tipo.CANCELANDO_TIMBRADOS
                        }
                    } else {
                        semaforo.setNoError(1);
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
            semaforo.setNoError(50); //error en base datos
        }
        return semaforo;
    }

    private Mensaje addValoresSemaforo(Mensaje mensaje, SemaforoTimbradoPac entity) {
        entity = makePersistent(entity);
        mensaje.setResultado(entity);
        mensaje.setNoError(entity == null ? 1 : 0);
        return mensaje;
    }

    //JSA01
    public Mensaje eliminar(SemaforoTimbradoPac entity, String uuidCxn) {
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
}
