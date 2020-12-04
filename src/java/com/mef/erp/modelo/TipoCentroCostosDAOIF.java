/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mef.erp.modelo;

import com.mef.erp.modelo.entidad.Mensaje;
import com.mef.erp.modelo.entidad.TipoCentroCostos;
import java.util.List;

/**
 *
 * @author daniel
 */
public interface TipoCentroCostosDAOIF extends GenericDAO<TipoCentroCostos, Long> {

    public Mensaje agregar(TipoCentroCostos entity, String uuidCxn);

    public Mensaje actualizar(TipoCentroCostos entity, String uuidCxn);

    public Mensaje eliminar(TipoCentroCostos entity, String uuidCxn);

    Mensaje getTipoCentroCostosAll(String claveRazonesSocial, String uuidCxn);

    Mensaje getTipoCentroCostosPorClave(String clave,String claveRazonesSocial, String uuidCxn);

    Mensaje consultaPorFiltrosTipoCentroCostos(String query, Object[] campos, Object[] valores, Integer inicio, Integer rango, String uuidCxn);

    Mensaje consultaPorRangosTipoCentroCostos(Integer inicio, Integer rango, String[] camposWhere, Object[] camposWhereValores, String uuidCxn);

    Mensaje existeDato(String campo, Object valor, String uuidCxn);

    Mensaje saveDeleteTipoCentroCostos(List<TipoCentroCostos> entitysCambios, Object[] clavesDelete, int rango, String uuidCxn);
}
