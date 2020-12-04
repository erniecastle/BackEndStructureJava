/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mef.erp.servicios;

import com.mef.erp.modelo.entidad.Contenedor;
import com.mef.erp.modelo.entidad.ElementoExterno;
import com.mef.erp.modelo.entidad.Mensaje;
import java.util.List;

/**
 *
 * @author Jose Armando
 */
public interface ServicioElementoExternoIF {

    /*List<ElementoExterno>*/
    public Mensaje getElementoExAll(String uuidCxn);

    /*ElementoExterno*/
    public Mensaje agregar(ElementoExterno entity, String uuidCxn);

    /*boolean*/
    public Mensaje actualizar(ElementoExterno entity, String uuidCxn);

    /*boolean*/
    public Mensaje eliminar(ElementoExterno entity, String uuidCxn);

    /*ElementoExterno*/
    public Mensaje getElementoExPorContenedor(Contenedor c, String uuidCxn);

    /*boolean*/
    public Mensaje SaveElementoExterno(List<ElementoExterno> e, String uuidCxn);

    /*boolean*/
    public Mensaje DeleteElementoExterno(List<ElementoExterno> e, String uuidCxn);

   
}
