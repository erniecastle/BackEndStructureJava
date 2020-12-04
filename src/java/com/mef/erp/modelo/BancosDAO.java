/**
 * @author: Daniel Fecha de Creación: --/--/-- Compañía: FineSoft Descripción
 * del programa: clase Bancos para llamados a metodos de HIBERNATE
 * -----------------------------------------------------------------------------
 * MODIFICACIONES:
 * -----------------------------------------------------------------------------
 * Clave: JSA01 Autor: Jose Armando Sanchez Acosta Fecha: 07-07-2011
 * Descripción: Se aplicó el PMD
 * -----------------------------------------------------------------------------
 * Clave:JSA02 Autor:Jose Armando Sanchez Acosta Fecha:10/11/2011 Descripción:Se
 * cambiaron todos los metodos hibernate al dao
 * -----------------------------------------------------------------------------
 */
package com.mef.erp.modelo;

import com.mef.erp.modelo.entidad.Bancos;
import com.mef.erp.modelo.entidad.Contactos;
import com.mef.erp.modelo.entidad.Mensaje;
import com.mef.erp.util.ControlErroresHibernate;
import com.mef.erp.util.HibernateUtil;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author daniel
 */
public class BancosDAO extends GenericHibernateDAO<Bancos, Long>
        implements BancosDAOIF {

    //JSA01
    Query query;
    private final StringBuilder concatena = new StringBuilder(200);
    private final String msgError = concatena.append("ServerERP"/*util.getContexto()*/).append(".MSERR_C_").append(this.getClass().getName()).append(".").toString();
    private List<Bancos> listBancos = new ArrayList<Bancos>();
    private final List<Contactos> listContactos = new ArrayList<Contactos>();

    public Mensaje agregar(Bancos entity, String uuidCxn) {//JSA02
        Bancos bancos;
        try {
            inicializaVariableMensaje();
            setSession(HibernateUtil.currentSession(uuidCxn));
            getSession().beginTransaction();
            bancos = makePersistent(entity);
            mensajeResultado.setResultado(bancos);
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

    public Mensaje actualizar(Bancos entity, String uuidCxn) {//JSA02
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

    public Mensaje eliminar(Bancos entity, String uuidCxn) {//JSA02
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

    public Mensaje getBancosAll(String uuidCxn) {//JSA02
        List<Bancos> values;
        try {
            inicializaVariableMensaje();
            setSession(HibernateUtil.currentSession(uuidCxn));
            getSession().beginTransaction();
            query = getSession().createQuery("from Bancos");
            values = query.list();
            mensajeResultado.setResultado(values);
            mensajeResultado.setNoError(0);
            mensajeResultado.setError("");
            getSession().getTransaction().commit();
        } catch (HibernateException ex) {
            System.err.println(concatena.delete(0, concatena.length()).append(msgError).append("getBancosAll()1_Error: ").append(ex));
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

    public Mensaje getBancosPorClave(String clave, String uuidCxn) {//JSA02
        Bancos b;
        try {
            inicializaVariableMensaje();
            setSession(HibernateUtil.currentSession(uuidCxn));
            getSession().beginTransaction();
            query = getSession().createQuery("from Bancos where clave = :clave");
            query.setString("clave", clave);
            b = (Bancos) query.uniqueResult();
            mensajeResultado.setResultado(b);
            mensajeResultado.setNoError(0);
            mensajeResultado.setError("");
            getSession().getTransaction().commit();
        } catch (HibernateException ex) {
            System.err.println(concatena.delete(0, concatena.length()).append(msgError).append("getBancosPorClave()1_Error: ").append(ex));
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

    public Mensaje SaveBanco(List<Contactos> agrega, Object[] eliminados, Bancos entity, String uuidCxn) {//JSA02
        boolean exitoRegistro = true;
        Bancos banco;
        Transaction transaction = null;
        try {
            inicializaVariableMensaje();
            setSession(HibernateUtil.currentSession(uuidCxn));
            transaction = getSession().beginTransaction();
            if (eliminados.length > 0) {
                deleteListQueryEn(getSession(), "Contactos", "id", eliminados);
            }
            getSession().saveOrUpdate(entity);
            for (int i = 0; i < agrega.size(); i++) {
                agrega.get(i).setBancos(entity);
                getSession().saveOrUpdate(agrega.get(i));
                if (i % 100 == 0 && i > 0) {
                    getSession().flush();
                }
            }
            if (exitoRegistro) {
                banco = entity;
                mensajeResultado.setResultado(banco);
                mensajeResultado.setNoError(0);
                mensajeResultado.setError("");
                transaction.commit();
            }
        } catch (HibernateException ex) {
            System.err.println(concatena.delete(0, concatena.length()).append(msgError).append("SaveBanco()1_Error: ").append(ex));
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

    public Mensaje UpdateBanco(List<Contactos> agrega, Object[] eliminados, Bancos entity, String uuidCxn) {//JSA02
        boolean exito = true;

        Transaction transaction = null;
        try {
            inicializaVariableMensaje();
            setSession(HibernateUtil.currentSession(uuidCxn));
            transaction = getSession().beginTransaction();
            if (eliminados.length > 0) {
                deleteListQueryEn(getSession(), "Contactos", "id", eliminados);
            }
            getSession().saveOrUpdate(entity);
            for (int i = 0; i < agrega.size(); i++) {
                agrega.get(i).setBancos(entity);
                getSession().saveOrUpdate(agrega.get(i));
                if (i % 100 == 0) {
                    getSession().flush();
                }
            }
            if (exito) {
                mensajeResultado.setResultado(exito);
                mensajeResultado.setNoError(0);
                mensajeResultado.setError("");
                transaction.commit();
            }
        } catch (HibernateException ex) {
            System.err.println(concatena.delete(0, concatena.length()).append(msgError).append("SaveBanco()1_Error: ").append(ex));
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

    public Mensaje DeleteBanco(Bancos entity, String uuidCxn) {//JSA02
        boolean exito = true;
        Transaction transaction = null;
        try {
            inicializaVariableMensaje();
            setSession(HibernateUtil.currentSession(uuidCxn));
            transaction = getSession().beginTransaction();
            deleteListQuery(getSession(), "Contactos", "bancos_id", entity.getId());
            deleteListQuery(getSession(), "Bancos", "id", entity.getId());
            if (exito) {
                mensajeResultado.setResultado(exito);
                mensajeResultado.setNoError(0);
                mensajeResultado.setError("");
                transaction.commit();
            }
        } catch (HibernateException ex) {
            System.err.println(concatena.delete(0, concatena.length()).append(msgError).append("SaveBanco()1_Error: ").append(ex));
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

    private void deleteListQueryEn(Session session, String tabla, String campo, Object[] valores) {//JSA02
        consulta = new StringBuilder("delete ");
        consulta.append(tabla).append(" where ").append(campo).append(" in(:valores)");
        query = session.createQuery(consulta.toString());
        query.setParameterList("valores", valores);
        query.executeUpdate();
    }

    private void deleteListQuery(Session session, String tabla, String campo, Object valores) {//JSA02
        consulta = new StringBuilder("delete ");
        consulta.append(tabla).append(" where ").append(campo).append("=:valores)");
        query = session.createQuery(consulta.toString());
        query.setParameter("valores", valores);
        query.executeUpdate();
    }

    public Mensaje consultaPorFiltrosBancos(String query, Object[] campos, Object[] valores, String uuidCxn) {//JSA02
        try {
            inicializaVariableMensaje();
            setSession(HibernateUtil.currentSession(uuidCxn));
            getSession().beginTransaction();
            listBancos = (List<Bancos>) consultaPorFiltros(query, campos, valores, null, null);
            mensajeResultado.setResultado(listBancos);
            mensajeResultado.setNoError(0);
            mensajeResultado.setError("");
            getSession().getTransaction().commit();
        } catch (HibernateException ex) {
            System.err.println(concatena.delete(0, concatena.length()).append(msgError).append("consultaPorFiltrosBancos()1_Error: ").append(ex));
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

    public Mensaje consultaPorRangos(Integer inicio, Integer rango, String uuidCxn) {//JSA02
        try {
            inicializaVariableMensaje();
            setSession(HibernateUtil.currentSession(uuidCxn));
            getSession().beginTransaction();
            listBancos = (List<Bancos>) consultaPorRangos(inicio, rango, null, null);
            mensajeResultado.setResultado(listBancos);
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

    public Mensaje existeDato(String campo, Object valor, String uuidCxn) {//JSA02
        Boolean existe;
        try {
            inicializaVariableMensaje();
            setSession(HibernateUtil.currentSession(uuidCxn));
            getSession().beginTransaction();
            existe = existeDato("Bancos", campo, valor);
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

    public Mensaje agregarListaBancos(List<Bancos> cambios, List<Bancos> temporales, List<Contactos> cambioContactos, Object[] clavesContactosDelete, int rango, String uuidCxn) {//JSA02        listBancos.clear();
        listContactos.clear();
        listBancos.clear();
        Bancos r;
        Contactos p;
        ArrayList nEntitys = new ArrayList<Object>(2);
        inicializaVariableMensaje();
        setSession(HibernateUtil.currentSession(uuidCxn));
        Transaction transaction = null;
        try {
            transaction = getSession().beginTransaction();
            int i;
            for (i = 0; i < cambios.size(); i++) {
                for (int j = 0; j < temporales.size(); j++) {
                    if (temporales.get(j).getId() == cambios.get(i).getId()) {
                        cambios.get(i).setId(null);
                        temporales.remove(j);
                        j--;
                    }
                }
                if (cambios.get(i).getId() == null) {
                    getSession().saveOrUpdate(cambios.get(i));
                    r = cambios.get(i);
                    listBancos.add(r);
                    for (Contactos contactos : cambioContactos) {
                        if (contactos.getBancos().getClave().equals(r.getClave())) {
                            contactos.setBancos(r);
                            getSession().saveOrUpdate(contactos);
                            p = contactos;
                            listContactos.add(p);
                        }
                    }

                } else {
                    getSession().saveOrUpdate(cambios.get(i));
                    boolean nPrima = false;
                    for (Contactos contactos : cambioContactos) {
                        if (contactos.getBancos().getId() == cambios.get(i).getId()) {
                            if (contactos.getId() == null) {
                                nPrima = true;
                            }
                            getSession().saveOrUpdate(contactos);
                            if (nPrima) {
                                p = contactos;
                                listContactos.add(p);
                            }
                        }
                    }
                }

                if (i % rango == 0 & i > 0) {
                    getSession().flush();
                    getSession().clear();
                }
            }
            boolean exito = true;
            if (clavesContactosDelete != null) {
                exito = deleteListQuerys(Contactos.class.getSimpleName(), "id", clavesContactosDelete);
            }
            if (exito) {
                nEntitys.add(listBancos);
                nEntitys.add(listContactos);
                mensajeResultado.setResultado(nEntitys);
                mensajeResultado.setNoError(0);
                mensajeResultado.setError("");
                transaction.commit();
            } else {
                mensajeResultado.setResultado(null);
                transaction.rollback();
            }
        } catch (HibernateException ex) {
            System.err.println(concatena.delete(0, concatena.length()).append(msgError).append("agregarListaBancos()1_Error: ").append(ex));
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

    private boolean deleteListQuerys(String tabla, String campo, Object[] valores) {//JSA02
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

    public Mensaje existeRFC(String rfc, String claveBancos, String uuidCxn) {//JSA02
        Long cantidad;
        Boolean existe = false;
        try {
            inicializaVariableMensaje();
            setSession(HibernateUtil.currentSession(uuidCxn));
            getSession().beginTransaction();
            if (claveBancos != null) {
                q = getSession().createQuery("select count(b) from Bancos b where b.RFC = :rfc  and b.clave != :claveBancos");
                q.setParameter("rfc", rfc);
                q.setParameter("claveBancos", claveBancos);
            } else {
                q = getSession().createQuery("select count(b) from Bancos b where b.RFC = :rfc");
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
