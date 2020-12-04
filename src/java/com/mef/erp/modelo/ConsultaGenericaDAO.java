/**
 * @author: Jose Armando Sanchez Fecha de Creación: --/--/2011 Compañía: Exito
 * Software. Descripción del programa: inteface con metodos para servicio de
 * consulta generica.
 * -----------------------------------------------------------------------------
 * MODIFICACIONES:
 * -----------------------------------------------------------------------------
 * Clave: AAP01 Autor: Abraham Daniel Arjona Peraza Fecha: 25/08/2011
 * Descripción: Se agregó metodo consultaGenericaPorRangos(String tabla, Integer
 * inicio, Integer rango, String[] camposWhere, Object[] valoresWhere) para
 * realizar la consulta general con filtros y se modificó metodo
 * consultaGenericaPorRangos (String tabla, Integer inicio, Integer rango) para
 * que dentro de este metodo se mande llamar al nuevo método.
 * -----------------------------------------------------------------------------
 * Clave: AAP02 Autor: Abraham Daniel Arjona Peraza Fecha: 25/08/2011
 * Descripción: Se agregó metodo consultaGenericaAll(String tabla, String[]
 * camposWhere, Object[] valoresWhere) para realizar la consulta general con
 * filtros y se modificó metodo consultaGenericaAll(String tabla) para que
 * dentro de este metodo se mande llamar al nuevo método.
 *
 * Se agregó método generarQuery() con los parametros tanto para
 * consultaGenericaAll() y consultaGenericaPorRangos(). Dependiendo de los
 * parametros que vengan alimentados, es la forma en que se arma el query para
 * obtener la informacion
 * -----------------------------------------------------------------------------
 * Clave:JSA01 Autor:Jose Armando Sanchez Acosta Fecha:10/11/2011 Descripción:Se
 * cambiaron todos los metodos hibernate al dao
 * -----------------------------------------------------------------------------
 * * Clave:VIC01, vic02 Autor:Victor Lopez Fecha:28/11/2011 Descripción:Se
 * agregaron metodos obtenerClaveStringMax y obtenerClaveNuericaMax and ordenado
 * por clave en consultapor filtros Clave: JEVC Autor: José Ernesto Valenzuela
 * Castillo Fecha: 22/12/2011 Descripción: Se Agrego metodo getFechaSistema
 * Clave:VIC01, vic02 Autor:Victor Lopez Fecha:28/11/2011 Descripción:Se
 * agregaron metodos obtenerClaveStringMax y obtenerClaveNuericaMax and ordenado
 * por clave en consultapor filtros
 * -----------------------------------------------------------------------------
 * Clave: JEVC Autor: José Ernesto Valenzuela Castillo Fecha: 22/12/2011
 * Descripción: Se Agrego metodo getFechaSistema
 * -----------------------------------------------------------------------------
 * * * Clave:VIC03 Autor:Victor Lopez Fecha:19/06/2012 Descripción:Se agregaron
 * metodos de consulta generica por rangos y all agregando campos para el
 * ordenado de el resultado
 * -----------------------------------------------------------------------------
 * Clave: JSA02 Autor: Jose Armando Fecha: 21/06/2012 Descripción: Se cambio la
 * parte cuando un nombre de un parametros contengo "." se le concateno un
 * consecutivo para cuando existan en el where campos del misma entidad no
 * marque error por duplicidad.
 * -----------------------------------------------------------------------------
 * Clave: JEVC Autor: José Ernesto Valenzuela Castillo Fecha: 02/07/2012
 * Descripción: Se le cambio la sinstaxis en metodo construyeQuery()
 * anteponiendo where 1 = 1 para no depender del where en las consultas
 * genericas y evitar comparaciones innecesarias en los casos del where
 * -----------------------------------------------------------------------------
 * Clave: VIC02 Autor: Victor Lopez Fecha: 19/06/2012 Descripción: Se Agrego
 * metodo consultaPorRangosOrden, consultaAllOrden
 * -----------------------------------------------------------------------------
 * Clave: JSA03 Autor: Armando Sanchez Fecha: 09/11/2012 Descripción: Se agrega
 * validacion para cuando venta un valor de tipo ArrayList o Object[] se agregen
 * en los parametros como tipo parameterList.
 * -----------------------------------------------------------------------------
 */
package com.mef.erp.modelo;

import com.mef.erp.modelo.entidad.CampoOrden;
import com.mef.erp.modelo.entidad.Mensaje;
import com.mef.erp.util.ControlErroresHibernate;
import com.mef.erp.util.HibernateUtil;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.mapping.Table;

public class ConsultaGenericaDAO extends GenericHibernateDAO<Object, Integer>
        implements ConsultaGenericaDAOIF {

    StringBuilder builder = new StringBuilder(50);
    private final String msgError = builder.append("ServerERP.MSERR_C_").append(this.getClass().getName()).append(".").toString();
    private String[] tablaQuery;
//    public String uuidCxnMaestra;

    public Mensaje getDataAll(String tabla, String uuidCxn, String uuidCxnMaestra) {//JSA01
        return getDataAllFiltro(tabla, null, null, uuidCxn, uuidCxnMaestra); //AAP02
    }

    public Mensaje getDataAllFiltro(String tabla, String[] campos, Object[] valores, String uuidCxn, String uuidCxnMaestra) {//AAP02//JSA01
        List<?> values;
        try {
            inicializaVariableMensaje();
            if (existeTablaMEFMaster(tabla)) {
                setSession(HibernateUtil.currentSession(uuidCxnMaestra));
            } else {
                setSession(HibernateUtil.currentSession(uuidCxn));
            }
            getSession().beginTransaction();
            values = consultaGenericaAll(tabla, campos, valores);//AAP02
            if (mensajeResultado.getNoError() == 0) {
                mensajeResultado.setResultado(values);
                mensajeResultado.setNoError(0);
                mensajeResultado.setError("");
                getSession().getTransaction().commit();
            } else {
                getSession().getTransaction().rollback();
            }
        } catch (HibernateException ex) {
            System.err.println(builder.delete(0, builder.length()).append(msgError).append("getDataAllFiltro()1_Error: ").append(ex));
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

    public Mensaje consultaPorFiltrosGenerico(String query, Object[] campos, Object[] valores, Integer inicio, Integer rango, String uuidCxn, String uuidCxnMaestra) {//JSA01
        List<?> values;
        tablaQuery = query.split(" ");
        try {
            inicializaVariableMensaje();
            if (existeTablaMEFMaster(tablaQuery[1])) {
                setSession(HibernateUtil.currentSession(uuidCxnMaestra));
            } else {
                setSession(HibernateUtil.currentSession(uuidCxn));
            }
            getSession().beginTransaction();
            values = consultaPorFiltros(query, campos, valores, inicio, rango);
            mensajeResultado.setResultado(values);
            mensajeResultado.setNoError(0);
            mensajeResultado.setError("");
            getSession().getTransaction().commit();
        } catch (HibernateException ex) {
            System.err.println(builder.delete(0, builder.length()).append(msgError).append("consultaPorFiltrosGenerico()1_Error: ").append(ex));
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

    public Mensaje consultaPorRangos(String tabla, Integer inicio, Integer rango, String uuidCxn, String uuidCxnMaestra) {//JSA01
        return consultaPorRangosFiltro(tabla, inicio, rango, null, null, uuidCxn, uuidCxnMaestra);//AAP01
    }

    public Mensaje consultaPorRangosFiltro(String tabla, Integer inicio, Integer rango, String[] camposWhere, Object[] valoresWhere, String uuidCxn, String uuidCxnMaestra) {//AAP01//JSA01
        List<?> values;
        try {//AAP01
            inicializaVariableMensaje();
            if (existeTablaMEFMaster(tabla)) {
                setSession(HibernateUtil.currentSession(uuidCxnMaestra));
            } else {
                setSession(HibernateUtil.currentSession(uuidCxn));
            }
            getSession().beginTransaction();

            values = consultaGenericaPorRangos(tabla, inicio, rango, camposWhere, valoresWhere);//AAP01//JSA01
            if (mensajeResultado.getNoError() == 0) {
                mensajeResultado.setResultado(values);
                mensajeResultado.setNoError(0);
                mensajeResultado.setError("");
                getSession().getTransaction().commit();
            } else {
                getSession().getTransaction().rollback();
            }
        } catch (HibernateException ex) {
            System.err.println(builder.delete(0, builder.length()).append(msgError).append("consultaPorRangosFiltro()1_Error: ").append(ex));
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
    }//AAP01

    public Mensaje consultaPorRangosFiltros(String tabla, Integer inicio, Integer rango, String[] campos, Object[] valores, String queryAntesDeFrom, String queryOrden, CampoOrden campoOrden, String uuidCxn, String uuidCxnMaestra) {//JEVC
        List<?> values;
        try {//JEVC
            inicializaVariableMensaje();
            if (existeTablaMEFMaster(tabla)) {
                setSession(HibernateUtil.currentSession(uuidCxnMaestra));
            } else {
                setSession(HibernateUtil.currentSession(uuidCxn));
            }
            getSession().beginTransaction();
            values = consultaGenericaPorRangos(tabla, inicio, rango, campos, valores, queryAntesDeFrom, queryOrden, campoOrden);
            if (mensajeResultado.getNoError() == 0) {
                mensajeResultado.setResultado(values);
                mensajeResultado.setNoError(0);
                mensajeResultado.setError("");
                getSession().getTransaction().commit();
            } else {
                getSession().getTransaction().rollback();
            }
        } catch (HibernateException ex) {
            System.err.println(builder.delete(0, builder.length()).append(msgError).append("consultaPorRangosFiltros()1_Error: ").append(ex));
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

    private List<?> consultaGenericaPorRangos(String tabla, Integer inicio, Integer rango) {//AAP01//JSA01
        return consultaGenericaPorRangos(tabla, inicio, rango, null, null);//AAP01
    }

    private List<?> consultaGenericaPorRangos(String tabla, Integer inicio, Integer rango, String[] camposWhere, Object[] valoresWhere) {//AAP01//JSA01
        return generarQuery(tabla, inicio, rango, camposWhere, valoresWhere, null, null, null);//AAP02
    }//AAP01

    private List<?> consultaGenericaPorRangos(String tabla, Integer inicio, Integer rango, String[] camposWhere,
            Object[] valoresWhere, String queryAntesDeFrom, String queryOrden, CampoOrden campoOrden) {
        return generarQuery(tabla, inicio, rango, camposWhere, valoresWhere, queryAntesDeFrom, queryOrden, campoOrden);
    }//JEVC

    public Mensaje existeDatoGenerico(String tabla, String campo, Object valor, String uuidCxn, String uuidCxnMaestra) {//JSA01
        Boolean existe;
        try {
            inicializaVariableMensaje();
            if (existeTablaMEFMaster(tabla)) {
                setSession(HibernateUtil.currentSession(uuidCxnMaestra));
            } else {
                setSession(HibernateUtil.currentSession(uuidCxn));
            }
            getSession().beginTransaction();
            existe = existeDato(tabla, campo, valor);
            mensajeResultado.setResultado(existe);
            mensajeResultado.setNoError(0);
            mensajeResultado.setError("");
            getSession().getTransaction().commit();
        } catch (HibernateException ex) {
            System.err.println(builder.delete(0, builder.length()).append(msgError).append("existeDatoGenerico()1_Error: ").append(ex));
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

    public Mensaje existeDatoList(String[] tabla, String[] campo, Object[] valor, String uuidCxn, String uuidCxnMaestra) {//JSA01
        Boolean existe;
        try {
            Object[] listDatos = new Object[tabla.length];
            Object[] datos = null;
            inicializaVariableMensaje();
            for (int i = 0; i < tabla.length; i++) {
                datos = new Object[2];
                if (existeTablaMEFMaster(tabla[i])) {
                    setSession(HibernateUtil.currentSession(uuidCxnMaestra));
                } else {
                    setSession(HibernateUtil.currentSession(uuidCxn));
                }
                getSession().beginTransaction();
                datos[0] = tabla[i];
                existe = existeDato(tabla[i], campo[i], valor[i]);
                datos[1] = existe;
                getSession().getTransaction().commit();
                listDatos[i] = datos;
            }
            mensajeResultado.setResultado(listDatos);
            mensajeResultado.setNoError(0);
            mensajeResultado.setError("");
        } catch (HibernateException ex) {
            System.err.println(builder.delete(0, builder.length()).append(msgError).append("existeDatoGenerico()1_Error: ").append(ex));
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

    public Mensaje existeValoresEntidad(String tabla, String[] campo, Object[] valores, String uuidCxn, String uuidCxnMaestra) {
        Boolean existe;
        try {
            inicializaVariableMensaje();
            if (existeTablaMEFMaster(tabla)) {
                setSession(HibernateUtil.currentSession(uuidCxnMaestra));
            } else {
                setSession(HibernateUtil.currentSession(uuidCxn));
            }
            getSession().beginTransaction();
            consulta = new StringBuilder();
            consulta.append(construyeQuery(tabla, campo, valores, "SELECT CASE WHEN (count(o) > 0) THEN true ELSE false END "));
            q = getSession().createQuery(consulta.toString());
            ///System.out.println(consulta);
            if (campo != null) {
                int i;
                for (i = 0; i < campo.length; i++) {
                    if (!campo[i].contains("@")) {
                        if (valores[i].getClass().equals(ArrayList.class) || valores[i].getClass().equals(Object[].class)) {//JSA03
                            if (valores[i].getClass().equals(Object[].class)) {
                                if (campo[i].contains("BETWEEN")) {
                                    q.setParameter("parametros".concat(String.valueOf(i)).concat("1"), ((Object[]) valores[i])[0]);
                                    q.setParameter("parametros".concat(String.valueOf(i)).concat("2"), ((Object[]) valores[i])[1]);
                                } else {
                                    q.setParameterList("parametros".concat(String.valueOf(i)), (Object[]) valores[i]);
                                }
                            } else {
                                q.setParameterList("parametros".concat(String.valueOf(i)), (ArrayList) valores[i]);
                            }
                        } else {
                            q.setParameter("parametros".concat(String.valueOf(i)), valores[i]);
                        }

                    }
                }
            }
            existe = (Boolean) q.uniqueResult();
            mensajeResultado.setResultado(existe);
            mensajeResultado.setNoError(0);
            mensajeResultado.setError("");
            getSession().getTransaction().commit();
        } catch (HibernateException ex) {
            System.err.println(builder.delete(0, builder.length()).append(msgError).append("existeValoresEntidad()1_Error: ").append(ex));
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

    public Mensaje existeClaveGenerico(String tabla, String[] campo, Object[] valores, String uuidCxn, String uuidCxnMaestra) {//JSA01
        Object object;
        try {
            inicializaVariableMensaje();
            if (existeTablaMEFMaster(tabla)) {
                setSession(HibernateUtil.currentSession(uuidCxnMaestra));
            } else {
                setSession(HibernateUtil.currentSession(uuidCxn));
            }
            getSession().beginTransaction();
            object = existeClave(tabla, campo, valores, null);
            mensajeResultado.setResultado(object);
            mensajeResultado.setNoError(0);
            mensajeResultado.setError("");
            getSession().getTransaction().commit();
        } catch (HibernateException ex) {
            System.err.println(builder.delete(0, builder.length()).append(msgError).append("existeClaveGenerico()1_Error: ").append(ex));
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

    public Mensaje selectExisteClave(String tabla, String[] campo, Object[] valores, String queryAntesDeFrom, String uuidCxn, String uuidCxnMaestra) {
        Object object;
        try {
            inicializaVariableMensaje();
            if (existeTablaMEFMaster(tabla)) {
                setSession(HibernateUtil.currentSession(uuidCxnMaestra));
            } else {
                setSession(HibernateUtil.currentSession(uuidCxn));
            }
            getSession().beginTransaction();
            object = existeClave(tabla, campo, valores, queryAntesDeFrom);
            mensajeResultado.setResultado(object);
            mensajeResultado.setNoError(0);
            mensajeResultado.setError("");
            getSession().getTransaction().commit();
        } catch (HibernateException ex) {
            System.err.println(builder.delete(0, builder.length()).append(msgError).append("selectExisteClave()1_Error: ").append(ex));
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

    public Mensaje existenClavesGenerico(String tabla, String[] campo, Object[] valores, String uuidCxn, String uuidCxnMaestra) {//JSA01
        List<Object> values;
        try {
            inicializaVariableMensaje();
            if (existeTablaMEFMaster(tabla)) {
                setSession(HibernateUtil.currentSession(uuidCxnMaestra));
            } else {
                setSession(HibernateUtil.currentSession(uuidCxn));
            }
            getSession().beginTransaction();
            values = super.existenClaves(tabla, campo, valores);
            mensajeResultado.setResultado(values);
            mensajeResultado.setNoError(0);
            mensajeResultado.setError("");
            getSession().getTransaction().commit();
        } catch (HibernateException ex) {
            System.err.println(builder.delete(0, builder.length()).append(msgError).append("existenClavesGenerico()1_Error: ").append(ex));
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

    public Mensaje existenClavesConOrden(String tabla, String[] campos, Object[] valores, CampoOrden campoOrden, String uuidCxn, String uuidCxnMaestra) {//JEVC01
        List<?> values;
        try {
            inicializaVariableMensaje();
            if (existeTablaMEFMaster(tabla)) {
                setSession(HibernateUtil.currentSession(uuidCxnMaestra));
            } else {
                setSession(HibernateUtil.currentSession(uuidCxn));
            }
            getSession().beginTransaction();
            values = generarQuery(tabla, null, null, campos, valores, null, null, campoOrden);
            if (mensajeResultado.getNoError() == 0) {
                mensajeResultado.setResultado(values);
                mensajeResultado.setNoError(0);
                mensajeResultado.setError("");
                getSession().getTransaction().commit();
            } else {
                getSession().getTransaction().rollback();
            }
        } catch (HibernateException ex) {
            System.err.println(builder.delete(0, builder.length()).append(msgError).append("existenClavesConOrden()1_Error: ").append(ex));
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

    private List<?> consultaGenericaAll(String tabla) {//JSA01
        return consultaGenericaAll(tabla, null, null);//AAP02
    }

    private List<?> consultaGenericaAll(String tabla, String[] camposWhere, Object[] valoresWhere) {//JSA01
        return generarQuery(tabla, null, null, camposWhere, valoresWhere, null, null, null);//AAP02
    }
    //select o.concepNomDefi from ConceptoPorTipoCorrida o where 1 =1 and  o.tipoCorrida.clave='VAC'
    //and o.concepNomDefi.activado = 1 and o.concepNomDefi.fecha=
    //(select max(fecha) from ConcepNomDefi cnd where o.concepNomDefi.clave=cnd.clave)

    private List<?> generarQuery(String tabla, Integer inicio, Integer rango, String[] camposWhere, Object[] valoresWhere,
            String queryAntesDeFrom, String queryOrden, CampoOrden campoOrden) {//JSA01
        int i;
        List<?> list;
        try { 
            builder = new StringBuilder(construyeQuery(tabla, camposWhere, valoresWhere, queryAntesDeFrom));
            if (campoOrden == null && queryOrden == null) {
                builder.append(" order by ").append(HibernateUtil.funcionLength).append("(o.clave),o.clave");
            } else if (queryOrden != null) {
                builder.append(" ").append(queryOrden);
            } else if (campoOrden != null) {
                StringBuilder orden = new StringBuilder(" Order by ");
                for (i = 0; i < campoOrden.getCamposOrden().length; i++) {
                    if (campoOrden.getTipoDatoOrden()[i].equals(String.class)) {
                        if (campoOrden.getCamposOrden()[i].equalsIgnoreCase("Clave")) {
                            orden.append(HibernateUtil.funcionLength).append("(o.").append(campoOrden.getCamposOrden()[i]).append(")");
                            if (!agregarCampoOrden(campoOrden.getAscendente(), i)) {
                                orden.append(" DESC ");
                            }
                            orden.append(",");
                        }
                        orden.append("o.").append(campoOrden.getCamposOrden()[i]);
                        if (!agregarCampoOrden(campoOrden.getAscendente(), i)) {
                            orden.append(" DESC ");
                        }
                    } else if (campoOrden.getTipoDatoOrden()[i].getSuperclass().equals(Number.class)) {
                        orden.append("o.").append(campoOrden.getCamposOrden()[i]);
                        if (!agregarCampoOrden(campoOrden.getAscendente(), i)) {
                            orden.append(" DESC ");
                        }
                    }
                    if (i < campoOrden.getCamposOrden().length - 1) {
                        orden.append(", ");
                    }
                }
                builder.append(orden);
            }
            q = getSession().createQuery(builder.toString());
            /*
             * cuando la variable "camposWhere" viene diferente de null, trae
             * campos para filtrar en la busqueda por rangos o en la busqueda
             * general
             */

            if (camposWhere != null) {//AAP01//AAP02
                for (i = 0; i < camposWhere.length; i++) {
                    if (!camposWhere[i].contains("@")) {
                        if (valoresWhere[i].getClass().equals(ArrayList.class) || valoresWhere[i].getClass().equals(Object[].class)) {//JSA03
                            if (valoresWhere[i].getClass().equals(Object[].class)) {
                                if (camposWhere[i].contains("BETWEEN")) {
                                    q.setParameter("parametros".concat(String.valueOf(i)).concat("1"), ((Object[]) valoresWhere[i])[0]);
                                    q.setParameter("parametros".concat(String.valueOf(i)).concat("2"), ((Object[]) valoresWhere[i])[1]);
                                } else {
                                    q.setParameterList("parametros".concat(String.valueOf(i)), (Object[]) valoresWhere[i]);
                                }
                            } else {
                                q.setParameterList("parametros".concat(String.valueOf(i)), (ArrayList) valoresWhere[i]);
                            }
                        } else {
                            q.setParameter("parametros".concat(String.valueOf(i)), valoresWhere[i]);
                        }

                    }
                }
            }

            /*
             * cuando se realizan busquedas por rango...las variables "inicio" y
             * "rango" vienen diferentes de nulo
             */
            if (inicio != null && rango != null) {//AAP02
                q.setFirstResult(inicio);
                q.setMaxResults(rango);
            }
            list = q.list();
        } catch (HibernateException e) {
            System.err.println(builder.delete(0, builder.length()).append(msgError).append("generarQuery()_Error").append(e));
            mensajeResultado.setNoError(ControlErroresHibernate.buscaNoErrorPorExcepcion(e.getClass()));
            mensajeResultado.setError(e.getLocalizedMessage());
            mensajeResultado.setResultado(null);
            list = null;
        }

        return list;
    }

    private boolean agregarCampoOrden(Boolean[] campoOrden, int posicion) {
        if (campoOrden == null) {
            return true;
        } else if (posicion < campoOrden.length) {
            return campoOrden[posicion] == null ? true : campoOrden[posicion];
        } else {
            return true;
        }
    }

    private String construyeQuery(String tabla, String[] camposWhere, Object[] valoresWhere, String queryAntesDeFrom) { //vic03

        // @ Para SubConsultas ("from tabla t where t.id = 1") 
        // # Para Indicar como deseas Filtrar (>,<,!=,IN) Ejemplos al usar canpos de
         /* filtrados: */
        //1.- NombreCampo#!=,NombreCampo#>,NombreCampo#<
        //2.- NombreCampo@
        //3.- NombreCampo#IN@
        /* Primer # para indicar como deseas filtrar,
         Segundo # para concatenar un simbolo o instruccion al principio de la consulta
         Tercer # para concatenar un simbolo al final de la consulta*/
        // 4.- NombreCampo#!=#(,OR#)
        if (queryAntesDeFrom != null) {
            builder = new StringBuilder(queryAntesDeFrom).append(" ").append("from ");
        } else {
            builder = new StringBuilder("from ");
        }
        int i;
        try {
            builder.append(tabla).append(" o");
            String[] campos;
            if (camposWhere != null) {
                builder.append(" where ");
                for (i = 0; i < camposWhere.length; i++) {
                    if (camposWhere[i].startsWith("@")) {
                        if (i > 0) {
                            builder.append(" and ");
                        }
                        campos = camposWhere[i].split("#");
                        builder.append("o.").append(campos[0].substring(1));

                        if (campos.length > 1) {
                            builder.append(" ").append(campos[1]);
                        } else {
                            builder.append(" = ");
                        }
                        builder.append(valoresWhere[i]);

                    } else {
                        campos = camposWhere[i].split("#");
                        if (i > 0) {
                            /*Segundo # en camposWhere para concatenar
                             * un simbolo o instruccion al principio de la consulta*/
                            if (campos.length > 2) {
                                if (campos[2].startsWith("or")) {
                                    builder.append(" ").append(campos[2]).append(" ");
                                } else {
                                    builder.append(" and ");
                                    builder.append(campos[2]).append(" ");
                                }
                            } else {
                                builder.append(" and ");
                            }
                        }
                        builder.append("o.").append(campos[0]);
                        if (campos.length > 1) {
                            if (campos[1].startsWith("IN")) {
                                if (campos[1].contains("@")) {//JEVC01
                                    builder.append(" ").append(campos[1].substring(0, campos[1].lastIndexOf("@"))).append(" (").append(valoresWhere[i]).append(")");
                                } else {
                                    builder.append(" ").append(campos[1]).append(" (:").append("parametros").append(String.valueOf(i)).append(")");
                                }
                            } else if (campos[1].equalsIgnoreCase("BETWEEN")) {
                                builder.append(" ").append(campos[1]).append(" :").append("parametros").append(String.valueOf(i)).append("1")
                                        .append(" ").append("AND").append(" :").append("parametros").append(String.valueOf(i)).append("2");
                            } else {
                                builder.append(" ").append(campos[1]).append(" :").append("parametros").append(String.valueOf(i));
                            }
                        } else {
                            builder.append(" = :").append("parametros").append(String.valueOf(i));
                        }
                        /*Tercer # en camposWhere para concatenar
                         * un simbolo o instruccion al final de la consulta*/
                        if (campos.length > 3) {
                            builder.append(" ").append(campos[3]).append(" ");
                        }
                    }
                }
                //builder.append(" and length(o.clave) in (select length(oo.clave) from ").append(tabla).append(" oo)");
            }
//            else {
//                //ordenado por clave
//                //builder.append(" where length(o.clave) in (select length(oo.clave) from ").append(tabla).append(" oo)"); //vic02
//            }
        } catch (Exception e) {
            System.err.println(builder.delete(0, builder.length()).append(msgError).append("construyeQuery()_Error").append(e));
            mensajeResultado.setNoError(ControlErroresHibernate.buscaNoErrorPorExcepcion(e.getClass()));
            mensajeResultado.setError(e.getLocalizedMessage());
            mensajeResultado.setResultado(null);
        }
        return builder.toString();
    }

    private Boolean existeTablaMEFMaster(String tabla) {//JSA01
        Iterator iterator;
        Boolean existeTabla = false;
        Boolean continuar = true;
        iterator = HibernateUtil.getConfigDBMaestra().getTableMappings();
        //iterator = HibernateUtil.getConfigMEFMaster().getTableMappings();
        while (continuar) {
            if (iterator.hasNext()) {
                Table element = (Table) iterator.next();
                if (element.getName().equals(tabla)) {
                    existeTabla = true;
                    continuar = false;
                }
            } else {
                continuar = false;
            }
        }
        return existeTabla;
    }

    public Mensaje obtenerClaveStringMax(String tabla, String campo, String[] camposWhere, Object[] valoresCamposWhere, String uuidCxn, String uuidCxnMaestra) {//VIC01
        String valor;
        try {
            inicializaVariableMensaje();
            if (existeTablaMEFMaster(tabla)) {
                setSession(HibernateUtil.currentSession(uuidCxnMaestra));
            } else {
                setSession(HibernateUtil.currentSession(uuidCxn));
            }
            getSession().beginTransaction();
            valor = claveStringMax(tabla, campo, camposWhere, valoresCamposWhere);
            mensajeResultado.setResultado(valor);
            mensajeResultado.setNoError(0);
            mensajeResultado.setError("");
            getSession().getTransaction().commit();
        } catch (HibernateException ex) {
            System.err.println(builder.delete(0, builder.length()).append(msgError).append("obtenerClaveStringMax()1_Error: ").append(ex));
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

    private String claveStringMax(String tabla, String campo, String[] camposWhere, Object[] valoresCamposWhere) {
        consulta = new StringBuilder("Select max(o.").append(campo).append(") from ");
        consulta.append(tabla).append(" o where ");
        int i;
        if (camposWhere != null) {
            consulta.append(" o.");

            consulta.append(camposWhere[0]);
            consulta.append(" = :").append((camposWhere[0].indexOf('.') > -1) ? (camposWhere[0].substring(0, camposWhere[0].indexOf('.'))) + "_0" : camposWhere[0]);//JSA02
            for (i = 1; i < camposWhere.length; i++) {
                consulta.append(" and ");
                consulta.append("o.").append(camposWhere[i]);
                consulta.append(" = :").append((camposWhere[i].indexOf('.') > -1) ? (camposWhere[i].substring(0, camposWhere[i].indexOf('.'))) + "_" + i : camposWhere[i]);//AAP01//JSA02
            }
            consulta.append(" and ");
        }
        consulta.append(" ").append(HibernateUtil.funcionLength).append("(o.").append(campo).append(") = (select max(").append(HibernateUtil.funcionLength).append("(oo.").append(campo).append(")) from ").append(tabla).append(" oo ");

        if (camposWhere != null) {
            consulta.append(" where oo.");
            consulta.append(camposWhere[0]);
            consulta.append(" = :").append((camposWhere[0].indexOf('.') > -1) ? (camposWhere[0].substring(0, camposWhere[0].indexOf('.'))) + "_0" : camposWhere[0]);//JSA02
            for (i = 1; i < camposWhere.length; i++) {
                consulta.append(" and ");
                consulta.append("oo.").append(camposWhere[i]);
                consulta.append(" = :").append((camposWhere[i].indexOf('.') > -1) ? (camposWhere[i].substring(0, camposWhere[i].indexOf('.'))) + "_" + i : camposWhere[i]);//AAP01//JSA02
            }
        }
        consulta.append(")");
        q = getSession().createQuery(consulta.toString());
        if (camposWhere != null) {
            for (i = 0; i < camposWhere.length; i++) {
                q.setParameter((camposWhere[i].indexOf('.') > -1) ? (camposWhere[i].substring(0, camposWhere[i].indexOf('.'))) + "_" + i : camposWhere[i], valoresCamposWhere[i]);//AAP01//JSA02
            }
        }
        return String.valueOf(q.uniqueResult());
    }

    public Mensaje obtenerClaveNumericaMax(String tabla, String campo, String[] camposWhere, Object[] valoresCamposWhere, String uuidCxn, String uuidCxnMaestra) {//VIC01
        Object valor;
        try {
            inicializaVariableMensaje();
            if (existeTablaMEFMaster(tabla)) {
                setSession(HibernateUtil.currentSession(uuidCxnMaestra));
            } else {
                setSession(HibernateUtil.currentSession(uuidCxn));
            }
            getSession().beginTransaction();
            valor = claveNumericaMax(tabla, campo, camposWhere, valoresCamposWhere);
            mensajeResultado.setResultado(valor);
            mensajeResultado.setNoError(0);
            mensajeResultado.setError("");
            getSession().getTransaction().commit();
        } catch (HibernateException ex) {
            System.err.println(builder.delete(0, builder.length()).append(msgError).append("obtenerClaveNumericaMax()1_Error: ").append(ex));
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

    private Object claveNumericaMax(String tabla, String campo, String[] camposWhere, Object[] valoresCamposWhere) {//vic
        consulta = new StringBuilder("Select max(o.").append(campo).append(") from ").append(tabla).append(" o");
        int i;
        if (camposWhere != null) {
            consulta.append(" where  o.");

            consulta.append(camposWhere[0]);
            consulta.append(" = :").append((camposWhere[0].indexOf('.') > -1) ? (camposWhere[0].substring(0, camposWhere[0].indexOf('.'))) + "_0" : camposWhere[0]);//JSA02
            for (i = 1; i < camposWhere.length; i++) {
                consulta.append(" and ");
                consulta.append("o.").append(camposWhere[i]);
                consulta.append(" = :").append((camposWhere[i].indexOf('.') > -1) ? (camposWhere[i].substring(0, camposWhere[i].indexOf('.'))) + "_" + i : camposWhere[i]);//AAP01//JSA02
            }
        }
        q = getSession().createQuery(consulta.toString());
        if (camposWhere != null) {
            for (i = 0; i < camposWhere.length; i++) {
                q.setParameter((camposWhere[i].indexOf('.') > -1) ? (camposWhere[i].substring(0, camposWhere[i].indexOf('.'))) + "_" + i : camposWhere[i], valoresCamposWhere[i]);//AAP01//JSA02
            }
        }
        return q.uniqueResult();
    }

    public Date getFechaSistema() {//JEVC
        return new Date();
    }

    //VIC02
    public Mensaje consultaPorRangosConOrdenado(String tabla, Integer inicio, Integer rango, CampoOrden campoOrden, String uuidCxn, String uuidCxnMaestra) {
        List<?> values;
        try {
            inicializaVariableMensaje();
            if (existeTablaMEFMaster(tabla)) {
                setSession(HibernateUtil.currentSession(uuidCxnMaestra));
            } else {
                setSession(HibernateUtil.currentSession(uuidCxn));
            }
            getSession().beginTransaction();
            values = generarQuery(tabla, inicio, rango, null, null, null, null, campoOrden);
            mensajeResultado.setResultado(values);
            mensajeResultado.setNoError(0);
            mensajeResultado.setError("");
            getSession().getTransaction().commit();
        } catch (HibernateException ex) {
            System.err.println(builder.delete(0, builder.length()).append(msgError).append("consultaPorRangosConOrdenado()1_Error: ").append(ex));
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

    //VIC02
    public Mensaje consultaAllConOrdenado(String tabla, String[] camposWhere, Object[] valoresWhere, CampoOrden campoOrden, String uuidCxn, String uuidCxnMaestra) {
        List<?> values;
        try {
            inicializaVariableMensaje();
            if (existeTablaMEFMaster(tabla)) {
                setSession(HibernateUtil.currentSession(uuidCxnMaestra));
            } else {
                setSession(HibernateUtil.currentSession(uuidCxn));
            }
            getSession().beginTransaction();
            values = generarQuery(tabla, null, null, camposWhere, valoresWhere, null, null, campoOrden);
            mensajeResultado.setResultado(values);
            mensajeResultado.setNoError(0);
            mensajeResultado.setError("");
            getSession().getTransaction().commit();
        } catch (HibernateException ex) {
            System.err.println(builder.delete(0, builder.length()).append(msgError).append("consultaAllConOrdenado()1_Error: ").append(ex));
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

    //VIC02
    public Mensaje consultaAllConOrdenado(String tabla, CampoOrden campoOrden, String uuidCxn, String uuidCxnMaestra) {
        return consultaAllConOrdenado(tabla, null, null, campoOrden, uuidCxn, uuidCxnMaestra);
    }

    //VIC02
    public Mensaje consultaPorRangoConFiltroYOrdenado(String tabla, Integer inicio, Integer rango, String[] camposWhere, Object[] valoresWhere, CampoOrden campoOrden, String uuidCxn, String uuidCxnMaestra) {
        List<?> values;
        try {
            inicializaVariableMensaje();
            if (existeTablaMEFMaster(tabla)) {
                setSession(HibernateUtil.currentSession(uuidCxnMaestra));
            } else {
                setSession(HibernateUtil.currentSession(uuidCxn));
            }
            getSession().beginTransaction();

            values = generarQuery(tabla, inicio, rango, camposWhere, valoresWhere, null, null, campoOrden);

            mensajeResultado.setResultado(values);
            mensajeResultado.setNoError(0);
            mensajeResultado.setError("");
            getSession().getTransaction().commit();
        } catch (HibernateException ex) {
            System.err.println(builder.delete(0, builder.length()).append(msgError).append("consultaPorRangoConFiltroYOrdenado()1_Error: ").append(ex));
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

    public Mensaje getObject(String tabla, String campo, Object valor, String[] camposFiltro, Object[] valoresFiltro, String queryAntesDeFrom, String uuidCxn, String uuidCxnMaestra) {
        Object object;
        try {
            inicializaVariableMensaje();
            if (existeTablaMEFMaster(tabla)) {
                setSession(HibernateUtil.currentSession(uuidCxnMaestra));
            } else {
                setSession(HibernateUtil.currentSession(uuidCxn));
            }
            getSession().beginTransaction();
            object = super.getObject(tabla, campo, valor, camposFiltro, valoresFiltro, queryAntesDeFrom);
            mensajeResultado.setResultado(object);
            mensajeResultado.setNoError(0);
            mensajeResultado.setError("");
            getSession().getTransaction().commit();
        } catch (HibernateException ex) {
            System.err.println(builder.delete(0, builder.length()).append(msgError).append("getObject()1_Error: ").append(ex));
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
