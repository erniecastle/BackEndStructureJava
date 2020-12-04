/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mef.erp.servicios;

import com.mef.erp.modelo.entidad.ElementosAplicacion;
import com.mef.erp.modelo.entidad.Mensaje;
import java.util.List;

/**
 *
 * @author Jose Armando
 */
public interface ServicioElementosAplicacionIF {

    /*List<ElementosAplicacion>*/
    public Mensaje getElementosAplicacionAll(String uuidCxn);

    /*List<ElementosAplicacion>*/
    public Mensaje getElementosAplicacionHert(String uuidCxn, long nodoPadre);

    /*ElementosAplicacion*/
    public Mensaje getElementosAplicacionPorClave(String uuidCxn, String clave, long parentID);

    /*List<ElementosAplicacion>*/
    public Mensaje guardarElementosAplicacion(String uuidCxn, List<ElementosAplicacion> agrega, Object[] eliminados);

    /*long*/
    public Mensaje getElementosAplicacionMaximo(String uuidCxn);
    
   
}
