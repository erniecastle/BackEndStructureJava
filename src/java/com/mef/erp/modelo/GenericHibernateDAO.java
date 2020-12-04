/**
 * @author: Victor Lopez Fecha de Creación: 06/06/2011 Compañía: Macropro
 * Descripción del programa: clase GenericHibernate para llamados a metodos de
 * HIBERNATE
 * -----------------------------------------------------------------------------
 * MODIFICACIONES:
 * -----------------------------------------------------------------------------
 * Clave: JSA01 Autor: Jose Armando Sanchez Acosta Fecha: 08-07-2011
 * Descripción: Se aplicaron PDM.
 * -----------------------------------------------------------------------------
 * Clave: AAP01 Autor: Abraham Daniel Arjona Peraza Fecha: 24/08/2011
 * Descripción: Se corrigieron ciclos para la obtencion de los parametros en
 * método existeClave(String tabla, String[] campo, Object[] valores);
 * -----------------------------------------------------------------------------
 * Clave: JEVC Autor: José Ernesto Valenzuela Castillo Fecha: 22/09/2011
 * Descripción: Se Agrego metodo getObject
 * -----------------------------------------------------------------------------
 * Clave: VIC Autor: Victor Lopez Fecha: 26/11/2011 Descripción: Se Agrego
 * metodo obtenerClaveStringMax
 * -----------------------------------------------------------------------------
 * Clave: JSA02 Autor: Jose Armando Fecha: 21/06/2012 Descripción: Se cambio la
 * parte cuando un nombre de un parametros contengo "." se le concateno un
 * consecutivo para cuando existan en el where campos del misma entidad no
 * marque error por duplicidad.
 * -----------------------------------------------------------------------------
 * Clave: VIC01 Autor: Victor Lopez Fecha: 22/06/2012 Descripción: se agrego en
 * existe clave verificar si campo viene con el caracter # que se usara para
 * separar el campo del operacion de comparacion de ese parametro ejemplo:
 * "campo1#>=" quedaria como campo, >=
 * -----------------------------------------------------------------------------
 * Clave: JSA03 Autor: Armando Fecha: 22/08/2012 Descripción: Se agrego
 * programacion para el caso en especifico de los empleados ya que es una
 * consulta compleja se metio aqui.
 * -----------------------------------------------------------------------------
 * Clave: VIC01 Autor: Jose Armando Fecha: 11/09/2012 Descripción: Se agregó
 * método generarQuery() y construyeQuery al generico y se quito del consulta
 * generica
 */
package com.mef.erp.modelo;

import com.mef.erp.modelo.entidad.Mensaje;
import com.mef.erp.util.ConstruyeQueries;
import com.mef.erp.util.HibernateUtil;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Example;

public abstract class GenericHibernateDAO<T, ID extends Serializable>
        implements GenericDAO<T, ID> {

//    private final StringBuilder concatenaMensajes = new StringBuilder(200);
    StringBuilder builder = new StringBuilder(50);
    private final String msgError = builder.append("ServerERP.MSERR_C_").append(this.getClass().getName()).append(".").toString();
    private Class<T> persistentClass;
    private Session session;
    StringBuilder consulta;//JSA01
    Query q;//JSA01
    Criteria crit;//JSA01
    Mensaje mensajeResultado = new Mensaje();

    /*
     * usa para cargar a variables en el compilador los conceptos con
     * nomenclatura iniciando CONCEP_
     */
    static String CONSULTA_CONCEPTO_CON_NOMENCLATURA = "Select new list(concat('CONCEP_',c.clave) , concat('CONCEP_',c.descripcion)) From ConcepNomDefi c Where c.fecha in (Select Max(cc.fecha) From ConcepNomDefi cc Where cc.clave = c.clave) Order By c.prioridadDeCalculo";

    public GenericHibernateDAO() {
        //regresa el valor contenido en <T>
        this.persistentClass = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }

    public void setSession(Session session) {
        this.session = session;
    }

    public Session getSession() {
        if (session == null) {
            System.out.println("La sesión no ha sido inicializada en el DAO antes de usarse.");
        }
        return session;
    }

    public Class<T> getPersistentClass() {
        return persistentClass;
    }

    @Override
    public T findById(ID id) {
        return (T) getSession().get(getPersistentClass(), id);
    }

    @Override
    public List<T> findAll() {
        return findByCriteria();
    }

    @Override
    public List<Object> findAllQuery(String className) {
        consulta = new StringBuilder("from ");//JSA01
        consulta.append(className);//JSA01
        q = getSession().createQuery(consulta.toString());
        return q.list();
    }

    @Override
    public List<Object> queryByColumn(String columns, String className) {
        consulta = new StringBuilder("Select ");//JSA01
        consulta.append(columns).append(" from ");//JSA01
        consulta.append(className);//JSA01
        q = getSession().createQuery(consulta.toString());//JSA01
        return q.list();
    }

    public Object findIdByClass(Class<?> className, ID id) {
        return getSession().load(className, id);

    }

    public List<T> findByExample(T exampleInstance, String[] excludeProperty) {
        crit = getSession().createCriteria(getPersistentClass());//JSA01
        Example example = Example.create(exampleInstance);
        for (String exclude : excludeProperty) {
            example.excludeProperty(exclude);
        }
        crit.add(example);
        return crit.list();
    }

    @Override
    public T makePersistent(T entity) {
        getSession().saveOrUpdate(entity);
        return entity;
    }

    @Override
    public void makeTransient(T entity) {
        getSession().delete(getSession().merge(entity));
    }

    public void flush() {
        getSession().flush();
    }

    public void clear() {
        getSession().clear();
    }

    /**
     * Use this inside subclasses as a convenience method.
     */
    protected List<T> findByCriteria(Criterion... criterion) {
        crit = getSession().createCriteria(getPersistentClass());//JSA01
        crit.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);//JSA01
        for (Criterion c : criterion) {
            crit.add(c);
        }
        return crit.list();
    }

    public Long obtenerIdMax() {
        consulta = new StringBuilder("Select max(o.id) from ");//JSA01
        consulta.append(getPersistentClass().getName()).append(" o");//JSA01
        q = getSession().createQuery(consulta.toString());//JSA01
        return (Long) q.uniqueResult();
    }

    public Long obtenerClaveMax() {
        consulta = new StringBuilder("Select max(o.clave) from ");//JSA01
        consulta.append(getPersistentClass().getName()).append(" o");//JSA01
        q = getSession().createQuery(consulta.toString());//JSA01
        return (Long) q.uniqueResult();
    }

    public String obtenerClaveStringMax(String tabla, String[] camposWhere, Object[] valoresCamposWhere) {
        return getClaveStringMax(tabla, camposWhere, valoresCamposWhere, null);
    }

    public String obtenerClaveStringMax(String[] camposWhere, Object[] valoresCamposWhere) {
        return getClaveStringMax(null, camposWhere, valoresCamposWhere, null);
    }

    public String obtenerClaveStringMax(String tabla, String[] camposWhere, Object[] valoresCamposWhere, String campoValorMaximo) {
        return getClaveStringMax(tabla, camposWhere, valoresCamposWhere, campoValorMaximo);
    }

    public String getClaveStringMax(String tabla, String[] camposWhere, Object[] valoresCamposWhere, String campoValorMaximo) {//VIC01
        //select max(p.clave) from Paises p 
        //where length(p.clave) = (select max(length(o.clave)) from Paises o)
        //o 
        //select max(p.clave) from CentroDeCosto p
        //where length(p.clave) = (select max(length(o.clave)) 
        //from CentroDeCosto o where o.razonesSociales.clave=1)
        if (campoValorMaximo == null) {
            consulta = new StringBuilder("Select max(o.clave) from ");
        } else {
            consulta = new StringBuilder("Select max").append("(o.").append(campoValorMaximo).append(") from ");
        }
        consulta.append("");
        consulta.append(tabla == null ? getPersistentClass().getName() : tabla).
                append(" o where ");
        if (campoValorMaximo == null) {
            consulta.append(HibernateUtil.funcionLength).append("(o.clave) = (select max(").append(HibernateUtil.funcionLength).append("(oo.clave)) from ");
        } else {
            consulta.append(HibernateUtil.funcionLength).append("(o.").append(campoValorMaximo).append(") ");
            consulta.append("= (select max(").append(HibernateUtil.funcionLength).append("(oo.").append(campoValorMaximo).append(")) from ");
        }
        consulta.append(tabla == null ? getPersistentClass().getName() : tabla).append(" oo ");
        int i;
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

    public List<Object> selectIdNombreEntidad() {
        consulta = new StringBuilder("Select New ");//JSA01
        consulta.append(getNombreClase(getPersistentClass().getName()));//JSA01
        consulta.append(" (o.id, o.nombre) from ");//JSA01
        consulta.append(getNombreClase(getPersistentClass().getName())).append(" o");//JSA01
        q = getSession().createQuery(consulta.toString());
        return (List<Object>) q.list();
    }

    public String getNombreClase(String nombreClase) {
        return nombreClase.substring(nombreClase.lastIndexOf('.') + 1);
    }

    public List<?> consultaPorFiltros(String query, Object[] campos, Object[] valores, Integer inicio, Integer rango) {
        q = getSession().createQuery(query);

        for (int i = 0; i < campos.length; i++) {
            if (valores[i] instanceof ArrayList || valores[i] instanceof Object[]) {
                q.setParameterList(campos[i].toString(), (ArrayList) valores[i]);
            } else {
                q.setParameter(campos[i].toString(), valores[i]);//(campos[i].toString(), valores[i]);
            }
        }
        if (inicio != null && rango != null) {
            q.setFirstResult(inicio);
            q.setMaxResults(rango);
        }
        return q.list();
    }

    public List<?> consultaPorRangoFiltros(String query, Integer inicio, Integer rango, Object[] campos, Object[] valores) {//JEVC
        q = getSession().createQuery(query);

        for (int i = 0; i < campos.length; i++) {
            q.setParameter(campos[i].toString(), valores[i]);
        }

        if (inicio != null && rango != null) {
            q.setFirstResult(inicio);
            q.setMaxResults(rango);
        }
        return q.list();
    }

    public List<?> consultaPorRangos(Integer inicio, Integer rango, String[] camposWhere, Object[] camposWhereValores) {
        consulta = new StringBuilder("from ");//JSA01
        consulta.append(getPersistentClass().getName()).append(" o");//JSA01
        if (camposWhere != null) {
            consulta.append(" where ");
            int i;
            consulta.append("o.").append(camposWhere[0]);
            consulta.append(" = :").append((camposWhere[0].indexOf('.') > -1) ? (camposWhere[0].substring(0, camposWhere[0].indexOf('.'))) + "_0" : camposWhere[0]);//JSA02
            for (i = 1; i < camposWhere.length; i++) {
                consulta.append(" and ");
                consulta.append("o.").append(camposWhere[i]);
                consulta.append(" = :").append((camposWhere[i].indexOf('.') > -1) ? (camposWhere[i].substring(0, camposWhere[i].indexOf('.'))) + "_" + i : camposWhere[i]);//AAP01//JSA02
            }
            q = getSession().createQuery(consulta.toString());//JSA01
            for (i = 0; i < camposWhere.length; i++) {
                q.setParameter((camposWhere[i].indexOf('.') > -1) ? (camposWhere[i].substring(0, camposWhere[i].indexOf('.'))) + "_" + i : camposWhere[i], camposWhereValores[i]);//AAP01//JSA02
            }
        } else {
            q = getSession().createQuery(consulta.toString());//JSA01
        }
        if (inicio != null & rango != null) {
            q.setFirstResult(inicio);
            q.setMaxResults(rango);
        }
        return q.list();
    }

    public List<?> consultaPorFiltroLike(String query, Object[] campos, Object[] valores) {
        q = getSession().createQuery(query);

        for (int i = 0; i < campos.length; i++) {
            q.setParameter(campos[i].toString(), valores[i]);
        }
        return q.list();
    }

    public Boolean existeDato(String tabla, String campo, Object valor) {
        if (campo != null && campo.charAt(0) == '.') {
            consulta = new StringBuilder("select count(o").append(campo).append(")from ");
        } else {
            consulta = new StringBuilder("select count(o) from ");
        }
        consulta.append(tabla).append(" o ");

        if (campo != null && valor != null) {
            consulta.append(" where ");
            consulta.append(campo).append(" = :").append(campo);//JSA01
        }
        q = getSession().createQuery(consulta.toString());//JSA01
        if (campo != null && valor != null) {
            q.setParameter(campo, valor);
        }

        if ((Long) q.uniqueResult() > 0) {
            return true;
        } else {
            return false;
        }
    }

    public Object existeClave(String tabla, String[] campo, Object[] valores, String queryAntesDeFrom) {
        /*
         * ---@ Para SubConsultas ("from tabla t where t.id = 1") ---#Para
         * Indicar como deseas Filtrar (>,<,!=,IN) Ejemplos al usar campos de
         * filtrados: 1.- NombreCampo#!=,NombreCampo#>,NombreCampo#< 2.-
         * NombreCampo@ 3.- NombreCampo#IN@
         */
        /*
         * ---@ Para SubConsultas ("from tabla t where t.id = 1") ---#Para
         * Indicar como deseas Filtrar (>,<,!=,IN) Ejemplos al usar campos de
         * filtrados: 1.- NombreCampo#!=,NombreCampo#>,NombreCampo#< 2.-
         * NombreCampo@ 3.- NombreCampo#IN@
         */
        if (queryAntesDeFrom != null) {//JEVC02
            consulta = new StringBuilder(queryAntesDeFrom).append(" ").append("from ");
        } else {
            consulta = new StringBuilder("from ");
        }
        consulta.append(tabla).append(" o where ");
        int i;
        String[] campos = null;//JEVC
        for (i = 0; i < campo.length; i++) {
            if (i > 0) {
                consulta.append(" and ");
            }

            if (campo[i].startsWith("@")) {
                campos = campo[i].split("#"); //VIC01
                if (campos.length > 1) {
                    consulta.append("o.").append(campos[0].substring(1)).append(" ").append(campos[1]);
                } else {
                    consulta.append("o.").append(campo[i].substring(1)).append(" = ");
                }
                consulta.append(valores[i]);
            } else {
                campos = campo[i].split("#"); //VIC01
                if (campos.length > 1) {
                    if (campos[1].startsWith("IN")) {//JEVC03
                        consulta.append("o.").append(campos[0]);
                        if (campos[1].contains("@")) {
                            consulta.append(" ").append(campos[1].substring(0, campos[1].lastIndexOf("@"))).append(" (").append(valores[i]).append(")");
                        } else {
                            consulta.append(" ").append(campos[1]).append(" (:").append("parametro").append(String.valueOf(i)).append(")");
                        }
                    } else {
                        consulta.append("o.").append(campos[0]);
                        consulta.append(" ").append(campos[1]).append(" :").append("parametro".concat(String.valueOf(i)));
                    }
                } else {
                    consulta.append("o.").append(campos[0]);
                    consulta.append(" = :").append("parametro".concat(String.valueOf(i)));
                }
            }
        }
        //////System.out.println(consulta.toString());
        q = getSession().createQuery(consulta.toString());//JSA01
        for (i = 0; i < campo.length; i++) {
            if (!campo[i].contains("@")) {
                if (valores[i] instanceof Object[]) {
                    q.setParameterList("parametro".concat(String.valueOf(i)), (Object[]) valores[i]);
                } else if (valores[i] instanceof ArrayList) {
                    q.setParameterList("parametro".concat(String.valueOf(i)), ((ArrayList) valores[i]).toArray());
                } else {
                    q.setParameter("parametro".concat(String.valueOf(i)), valores[i]);//AAP01
                }
            }
        }
        return q.uniqueResult();
    }

    public List<Object> existenClaves(String tabla, String[] campo, Object[] valores) {
        consulta = new StringBuilder("from ");//JSA01
        consulta.append(tabla).append(" o where ");
        int i;
        String[] campos = null;//JEVC01
        for (i = 0; i < campo.length; i++) {
            if (i > 0) {
                consulta.append(" and ");
            }
            campos = campo[i].split("#"); //VIC01
            if (campos.length > 1) {
                consulta.append("o.").append(campos[0]);
                consulta.append(" ").append(campos[1]).append(" :").append("parametro".concat(String.valueOf(i)));
            } else {
                consulta.append("o.").append(campos[0]);
                consulta.append(" = :").append("parametro".concat(String.valueOf(i)));
            }
        }
        q = getSession().createQuery(consulta.toString());//JSA01
        for (i = 0; i < campo.length; i++) {
            q.setParameter("parametro".concat(String.valueOf(i)), valores[i]);//AAP01
        }
        return q.list();
    }

    public Boolean existeClave(String tabla, String clave) {
        consulta = new StringBuilder("select count(o) from ");//JSA01
        consulta.append(tabla).append(" o where o.clave = :clave");//JSA01
        q = getSession().createQuery(consulta.toString());//JSA01
        q.setString("clave", clave);
        return ((Long) q.uniqueResult() > 0) ? true : false;
    }

    /*
     * debe pasarse valores por tipo de dato del campo elimina segun valores
     * contenidos en la instruccion in
     */
    public int deleteListQuery(String tabla, String campo, Object[] valores) {
        consulta = new StringBuilder("delete ");//JSA01
        consulta.append(tabla).append(" where ").append(campo).append(" in(:valores)");//JSA01
        q = getSession().createQuery(consulta.toString());//JSA01
        q.setParameterList("valores", valores);
        return q.executeUpdate();
    }

    public int deleteListQueryConFiltrado(String tabla, String campo, Object[] valores, String[] camposFiltro, Object[] valoresFiltro) {
        consulta = new StringBuilder("delete ");//JSA01
        consulta.append(tabla).append(" o ").append(" where ").append(" o.").append(campo).append(" in(:valores) ");
        int i;
        String[] campos;
        if (camposFiltro != null & valoresFiltro != null) {
            for (i = 0; i < camposFiltro.length; i++) {
                consulta.append(" and ");
                campos = camposFiltro[i].split("#"); //VIC01
                if (campos.length > 1) {
                    consulta.append("o.").append(campos[0]);
                    consulta.append(" ").append(campos[1]).append(" :").append("parametro".concat(String.valueOf(i)));
                } else {
                    consulta.append("o.").append(campos[0]);
                    consulta.append(" = :").append("parametro".concat(String.valueOf(i)));
                }
            }
        }

        q = getSession().createQuery(consulta.toString());
        q.setParameterList("valores", valores);
        if (camposFiltro != null & valoresFiltro != null) {
            for (i = 0; i < camposFiltro.length; i++) {
                q.setParameter("parametro".concat(String.valueOf(i)), valoresFiltro[i]);
            }
        }
        return q.executeUpdate();
    }

    public int insertListQuery(String tabla, String campo, Object[] valores) {
        consulta = new StringBuilder("insert ");//JSA01
        consulta.append(tabla).append(" where ").append(campo).append(" in(:valores)");//JSA01
        q = getSession().createQuery(consulta.toString());//JSA01
        q.setParameterList("valores", valores);
        return q.executeUpdate();
    }

    public int updateListQuery(String tabla, String campoModificar[], Object[] valoresModificado, String camposWhere[], Object[] valoresWhere) {
        consulta = new StringBuilder("UPDATE ");
        consulta.append(tabla).append(" o SET ");

        consulta.append(construyeQueryCampos(campoModificar, "campoModificar", ","));
        consulta.append(" WHERE ");
        consulta.append(construyeQueryCampos(camposWhere, "camposWhere", "AND"));
        q = getSession().createQuery(consulta.toString());
        int i;
        if (campoModificar != null & valoresModificado != null) {
            for (i = 0; i < valoresModificado.length; i++) {
                q.setParameter("campoModificar".concat(String.valueOf(i)), valoresModificado[i]);
            }
        }
        if (camposWhere != null & valoresWhere != null) {
            for (i = 0; i < valoresWhere.length; i++) {
                if (valoresWhere[i] instanceof Object[]) {
                    q.setParameterList("camposWhere".concat(String.valueOf(i)), (Object[]) valoresWhere[i]);
                } else {
                    q.setParameter("camposWhere".concat(String.valueOf(i)), valoresWhere[i]);
                }
            }
        }
        return q.executeUpdate();
    }

    /*
     * ---@ Para SubConsultas ("from tabla t where t.id = 1")
     ---#Para
     * Indicar como deseas Filtrar (>,<,!=,IN) Ejemplos al usar campos de
     * filtrados: 1.- NombreCampo#!=,NombreCampo#>,NombreCampo#< 2.-
     * NombreCampo@ 3.- NombreCampo#IN@
     */
    /*
     * ---@ Para SubConsultas ("from tabla t where t.id = 1") ---#Para
     * Indicar como deseas Filtrar (>,<,!=,IN) Ejemplos al usar campos de
     * filtrados: 1.- NombreCampo#!=,NombreCampo#>,NombreCampo#< 2.-
     * NombreCampo@ 3.- NombreCampo#IN@
     *
     * queryAntesDeFrom es necesario Select COUNT(*)  por que regresa un solo valor
     * solo esta validado que regrese valor unico   
     */
    public Object getObject(String tabla, String campo, Object valor, String[] camposFiltro, Object[] valoresFiltro, String queryAntesDeFrom) {

        List<String> campoArroba = new ArrayList<>();
        List<Object> valorArroba = new ArrayList<>();
        List<String> campoWhere = new ArrayList<>();
        List<Object> valorWhere = new ArrayList<>();
        ConstruyeQueries construye = new ConstruyeQueries();
        builder = new StringBuilder(queryAntesDeFrom == null ? "" : queryAntesDeFrom);
        camposFiltro = camposFiltro == null ? new String[]{} : camposFiltro;
        if (camposFiltro.length > 0) {
            for (int i = 0; i < camposFiltro.length; i++) {
                if (camposFiltro[i].toString().contains("@")) {
                    //reemplaza el arroba por nombre tabla
                    camposFiltro[i] = camposFiltro[i].replace("@", tabla.concat("."));
                    if (camposFiltro[i].toString().contains("#")) {
                        camposFiltro[i] = camposFiltro[i].replace("#", "|");
                    }
                    campoArroba.add(camposFiltro[i]);

                    valorArroba.add(valoresFiltro[i]);

                } else {
                    camposFiltro[i] = tabla.concat(".").concat(camposFiltro[i]);
                    if (camposFiltro[i].toString().contains("#")) {
                        camposFiltro[i] = "|".concat(camposFiltro[i].replace("#", "|"));
                    }
                    campoWhere.add(camposFiltro[i]);
                    valorWhere.add(valoresFiltro[i]);
                }
            }
            construye.mapeaTablasCampo(camposFiltro);
            if (construye.getAliasTablaOuter().containsKey(tabla)) {
                construye.getAliasTablaOuter().put(tabla, "o");
            }
            builder.append(" ").append(construye.construyeFromConsulta(construye.LEFTJOIN)).append(" ");
            builder.append(construye.construyeWhereConParametro(campoWhere.toArray(new String[]{}), valorWhere.toArray(), null));
            if (!campoArroba.isEmpty()) {
                if (campoWhere.isEmpty()) {
                    builder.append(" WHERE ");
                } else {
                    builder.append(" AND ");
                }
                for (int i = 0; i < campoArroba.size(); i++) {
                    String path = campoArroba.get(i).substring(0, campoArroba.get(i).lastIndexOf("."));
                    path = path.replace(".", "_");
                    if (construye.getAliasTablaOuter().containsKey(path)) {
                        String alias = construye.getAliasTablaOuter().get(path);
                        String[] ms;
                        String operador = "";
                        if (campoArroba.get(i).contains("|")) {
                            ms = campoArroba.get(i).split("\\|");
                            if (ms.length > 1) {
                                operador = ms[1];
                            }
                            ms = ms[0].split("\\.");
                        } else {

                            ms = campoArroba.get(i).split("\\.");
                        }
                        int pos = ms.length;
                        if (campoArroba.size() > 1 & i > 0) {
                            builder.append(" AND ");
                        }

                        builder.append(alias).append(".").append(ms[pos - 1]).append(operador.isEmpty() ? " = " : " ".concat(operador).concat(" ")).append(valorArroba.get(i).toString());
                    }
                }
            }
        } else {
            builder.append(" FROM ").append(tabla);
        }

        q = getSession().createQuery(builder.toString());

        if (!campoWhere.isEmpty()) {
            String parametro;
            int i;
            if (construye.getListParametros() != null) {
                for (i = 0; i < construye.getListParametros().size(); i++) {
                    parametro = construye.getListParametros().get(i);
                    valor = construye.getParametrosQuery().get(parametro);
                    if (valor instanceof Calendar) {
                        valor = ((Calendar) valor).getTime();
                    }
                    if (valor instanceof Object[]) {
                        q.setParameterList(parametro, (Object[]) valor);
                    } else if (valor instanceof ArrayList) {
                        q.setParameterList(parametro, ((ArrayList) valor).toArray());
                    } else {
                        q.setParameter(parametro, valor);
                    }
                }
            }
        }
        return q.uniqueResult();
    }

//        if (camposFiltro == null) {
//            camposFiltro = new String[0];
//        }
//        if (queryAntesDeFrom != null) {//JEVC02
//            consulta = new StringBuilder(queryAntesDeFrom).append(" ").append("from ").append(tabla).append(" o ");
//        } else {
//            consulta = new StringBuilder("from ").append(tabla).append(" o ");
//        }
//        if ((campo == null ? false : !campo.isEmpty()) || camposFiltro.length > 0) {
//            consulta.append(" where ");
//        }
//        if (campo == null ? false : !campo.isEmpty()) {
//            consulta.append(" o.").append(campo).append(" =:valor ");
//            if (camposFiltro.length > 0) {
//                consulta.append(" and ");
//            }
//        }
//        int i;
//        String[] camposTmp = null;//JEVC
//        for (i = 0; i < camposFiltro.length; i++) {
//            if (i > 0) {
//                consulta.append(" and ");
//            }
//
//            if (camposFiltro[i].startsWith("@")) {
//                camposTmp = camposFiltro[i].split("#"); //VIC01
//                if (camposTmp.length > 1) {
//                    consulta.append("o.").append(camposTmp[0].substring(1)).append(" ").append(camposTmp[1]);
//                } else {
//                    consulta.append("o.").append(camposFiltro[i].substring(1)).append(" = ");
//                }
//                consulta.append(valoresFiltro[i]);
//            } else {
//                camposTmp = camposFiltro[i].split("#"); //VIC01
//                if (camposTmp.length > 1) {
//                    if (camposTmp[1].startsWith("IN")) {//JEVC03
//                        consulta.append("o.").append(camposTmp[0]);
//                        if (camposTmp[1].contains("@")) {
//                            consulta.append(" ").append(camposTmp[1].substring(0, camposTmp[1].lastIndexOf("@"))).append(" (").append(valoresFiltro[i]).append(")");
//                        } else {
//                            consulta.append(" ").append(camposTmp[1]).append(" (:").append("parametro").append(String.valueOf(i)).append(")");
//                        }
//                    } else {
//                        consulta.append("o.").append(camposTmp[0]);
//                        consulta.append(" ").append(camposTmp[1]).append(" :").append("parametro".concat(String.valueOf(i)));
//                    }
//                } else {
//                    consulta.append("o.").append(camposTmp[0]);
//                    consulta.append(" = :").append("parametro".concat(String.valueOf(i)));
//                }
//            }
//        }
//        q = getSession().createQuery(consulta.toString());
//        if (campo == null ? false : !campo.isEmpty()) {
//            q.setParameter("valor", valor);
//        }
//        for (i = 0; i < camposFiltro.length; i++) {
//            if (!camposFiltro[i].contains("@")) {
//                q.setParameter("parametro".concat(String.valueOf(i)), valoresFiltro[i]);//AAP01
//            }
//        }
//        return q.uniqueResult();
//}

    /*
     * usado para generar por ejemplo campos = :nombreParametro simbolo paises =
     * :parametro , este es usado para igualar valores separados por coma paises
     * = :parametro AND este es usado para igualar valores separados por AND
     */
    public String construyeQueryCampos(String[] camposWhere, String nombreParametro, String simbolo) {
        int i;
        builder.delete(0, builder.length());
        try {
            String[] campos;
            if (camposWhere != null) {
                for (i = 0; i < camposWhere.length; i++) {
                    campos = camposWhere[i].split("#");
                    if (i > 0) {
                        /* Segundo # en camposWhere para concatenar un simbolo o instruccion al principio
                         * de la consulta */
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
                            builder.append(" ").append(campos[1]).append(" (:").append(nombreParametro).append(String.valueOf(i)).append(")");
                        } else if (campos[1].equalsIgnoreCase("BETWEEN")) {
                            builder.append(" ").append(campos[1]).append(" :").append(nombreParametro).append(String.valueOf(i)).append("1").append(" ").append("AND").append(" :").append(nombreParametro).append(String.valueOf(i)).append("2");
                        } else {
                            builder.append(" ").append(campos[1]).append(" :").append(nombreParametro).append(String.valueOf(i));
                        }
                    } else {
                        builder.append(" = :").append(nombreParametro).append(String.valueOf(i));
                    }
                    /* Tercer # en camposWhere para concatenar un simbolo o instruccion al final de la consulta */
                    if (campos.length > 3) {
                        builder.append(" ").append(campos[3]).append(" ");
                    }
                }
            }
        } catch (Exception e) {
            System.err.println(builder.delete(0, builder.length()).append(msgError).append("construyeQuery()_Error").append(e));
        }
        return builder.toString();
    }

    public Calendar getFechaDelSistema() {
        return Calendar.getInstance();
    }

    public Class buscarTipoDatoCampo(String pathCampo) {
        Class tipoDato = null;
        try {
            String[] path = pathCampo.split("\\.");
            tipoDato = Class.forName("com.mef.erp.modelo.entidad.".concat(path[0]));

            if (path.length > 1) {
                Field field = tipoDato.getDeclaredField(path[1]);

                if (field.getType().equals(List.class
                )) {
                    tipoDato = tipoDatoDeLista(field);
                } else {
                    tipoDato = field.getType();
                }
                if (path.length > 2) {
                    int i;
                    StringBuilder ruta = new StringBuilder(tipoDato.getSimpleName());
                    for (i = 2; i < path.length; i++) {
                        ruta.append(".").append(path[i]);
                    }
                    tipoDato = buscarTipoDatoCampo(ruta.toString());
                }
            }
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        } catch (NoSuchFieldException ex) {
            ex.printStackTrace();
        } catch (SecurityException ex) {
            ex.printStackTrace();
        }
        return tipoDato;
    }

    private Class tipoDatoDeLista(Field method) {
        java.lang.reflect.Type returnType = method.getGenericType();
        if (returnType instanceof ParameterizedType) {
            ParameterizedType type = (ParameterizedType) returnType;
            java.lang.reflect.Type[] typeArguments = type.getActualTypeArguments();
            Class typeArgClass = null;
            for (java.lang.reflect.Type typeArgument : typeArguments) {
                typeArgClass = (Class) typeArgument;
            }
            if (typeArgClass != null) {
                return typeArgClass;

            }
        }
        return Class.class;
    }

    public String
            valorCasePorTipoDato(Class tipoDato) {
        if (tipoDato.isPrimitive()) {
            if (tipoDato.equals(int.class
            ) | tipoDato.equals(long.class
            ) | tipoDato.equals(byte.class
            )
                    | tipoDato.equals(short.class
                    ) | tipoDato.equals(boolean.class
                    )) {

                return "0";
            } else if (tipoDato.equals(float.class
            ) | tipoDato.equals(double.class
            )) {

                return "0.0";
            }
        } else {
            if (tipoDato.equals(Integer.class) | tipoDato.equals(Long.class) | tipoDato.equals(Byte.class)
                    | tipoDato.equals(Short.class) | tipoDato.equals(Boolean.class)) {
                return "0";
            } else if (tipoDato.equals(Float.class) | tipoDato.equals(Double.class)) {
                return "0.0";
            } else if (tipoDato.equals(Date.class)) {
                return "cast('1900-01-01' as date)";
            }
        }

        return "''";
    }

    public Object inicializaValorPorTipoDatoNulo(Class tipoDato) {
        if (tipoDato.isPrimitive()) {
            if (tipoDato.equals(int.class) | tipoDato.equals(long.class) | tipoDato.equals(byte.class)
                    | tipoDato.equals(short.class) | tipoDato.equals(boolean.class)) {
                return 0;
            } else if (tipoDato.equals(float.class) | tipoDato.equals(double.class)) {
                return 0.0;
            }
        } else {
            if (tipoDato.equals(Integer.class) | tipoDato.equals(Long.class) | tipoDato.equals(Byte.class)
                    | tipoDato.equals(Short.class) | tipoDato.equals(Boolean.class)) {
                return 0;
            } else if (tipoDato.equals(Float.class) | tipoDato.equals(Double.class)) {
                return 0.0;
            } else if (tipoDato.equals(Date.class)) {
                Calendar c = Calendar.getInstance();
                c.set(1900, Calendar.JANUARY, 01);
                return c.getTime();
            }
        }
        return null;
    }

    public void inicializaVariableMensaje() {
        if (mensajeResultado == null) {
            mensajeResultado = new Mensaje();
        }
        mensajeResultado.setResultado(null);
        mensajeResultado.setNoError(0);
        mensajeResultado.setError("");
    }

//    public Mensaje ejecutaOperacionHibernate(Session session, T entity) {
//        try {
//            setSession(session);
//            getSession().beginTransaction();
//            mensajeResultado = realizaOperacionHibernate(entity);
//            if (!getSession().getTransaction().wasCommitted()) {
//                getSession().getTransaction().commit();
//            }
//        } catch (HibernateException ex) {
//            System.err.println(concatenaMensajes.delete(0, concatenaMensajes.length()).append(msgError).append("agregar()1_Error: ").append(ex));
//            try {
//                if (getSession().getTransaction().isActive()) {
//                    getSession().getTransaction().rollback();
//                }
//                mensajeResultado.setNoError(ControlErroresHibernate.buscaNoErrorPorExcepcion(ex.getClass()));
//                mensajeResultado.setError(ex.getLocalizedMessage());
//            } catch (HibernateException exc) {  //cuando marca error al hacer rollback pro ejemplo se cae conexion servidor
//                mensajeResultado.setNoError(ControlErroresHibernate.buscaNoErrorPorExcepcion(exc.getClass()));
//                mensajeResultado.setError(exc.getLocalizedMessage());
//            }
//            mensajeResultado.setResultado(null);
//        }
//
//        return mensajeResultado;
//    }
//
//    protected Mensaje realizaOperacionHibernate(T entity) {
//        return null;
//    }
}
