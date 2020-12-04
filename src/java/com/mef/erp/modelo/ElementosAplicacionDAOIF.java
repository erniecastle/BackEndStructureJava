/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mef.erp.modelo;

import com.mef.erp.modelo.entidad.ElementosAplicacion;
import com.mef.erp.modelo.entidad.Mensaje;
import java.util.List;

/**
 *
 * @author Jose Armando
 */
public interface ElementosAplicacionDAOIF extends GenericDAO<ElementosAplicacion, Integer> {

    public Mensaje getElementosAplicacionAll(String uuidCxn);

    public Mensaje getElementosAplicacionHert(String uuidCxn,long nodoPadre);

    public Mensaje getElementosAplicacionPorClave(String uuidCxn,String clave, long parentID);

    public Mensaje guardarElementosAplicacion(String uuidCxn,List<ElementosAplicacion> agrega, Object[] eliminados);

    public Mensaje getElementosAplicacionMaximo(String uuidCxn);
}
