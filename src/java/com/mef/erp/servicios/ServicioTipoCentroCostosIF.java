/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mef.erp.servicios;

import com.mef.erp.modelo.entidad.Mensaje;
import com.mef.erp.modelo.entidad.TipoCentroCostos;
import java.util.List;

/**
 *
 * @author daniel
 */
public interface ServicioTipoCentroCostosIF {

    /*TipoCentroCostos*/
    public Mensaje agregar(TipoCentroCostos entity, String uuidCxn);

    /*boolean*/
    public Mensaje actualizar(TipoCentroCostos entity, String uuidCxn);

    /*boolean*/
    public Mensaje eliminar(TipoCentroCostos entity, String uuidCxn);

    /*List<TipoCentroCostos>*/
    Mensaje getTipoCentroCostosAll(String claveRazonesSocial, String uuidCxn);

    /*TipoCentroCostos*/
    Mensaje getTipoCentroCostosPorClave(String clave, String claveRazonesSocial, String uuidCxn);

    /*List<TipoCentroCostos>*/
    Mensaje consultaPorFiltros(String query, Object[] campos, Object[] valores, Integer inicio, Integer rango, String uuidCxn);

    /*List<TipoCentroCostos>*/
    Mensaje consultaPorRangos(Integer inicio, Integer rango, String[] camposWhere, Object[] camposWhereValores, String uuidCxn);

    /*Boolean*/
    Mensaje existeDato(String campo, Object valor, String uuidCxn);

	/*List<TipoCentroCostos>*/
    Mensaje saveDeleteTipoCentroCostos(List<TipoCentroCostos> entitysCambios, Object[] clavesDelete, int rango, String uuidCxn);
}
