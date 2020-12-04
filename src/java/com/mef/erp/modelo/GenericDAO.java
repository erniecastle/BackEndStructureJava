package com.mef.erp.modelo;

import java.io.Serializable;
import java.util.List;

public interface GenericDAO<T, ID extends Serializable> {

    T findById(ID id);

    List<T> findAll();

    List<Object> findAllQuery(String className);

    List<Object> queryByColumn(String columns, String className);

    T makePersistent(T entity);

    void makeTransient(T entity);

    Long obtenerIdMax();

    List<Object> selectIdNombreEntidad();

    List<?> consultaPorFiltros(String query, Object[] campos, Object[] valores, Integer inicio, Integer rango);

    List<?> consultaPorRangos(Integer inicio, Integer rango, String[] camposWhere, Object[] camposWhereValores);

    Boolean existeDato(String tabla, String campo, Object valor);

    Object existeClave(String tabla, String[] campo, Object[] valores, String queryAntesDeFrom);

    String obtenerClaveStringMax(String[] camposWhere, Object[] valoresCamposWhere); //VIC01
}
