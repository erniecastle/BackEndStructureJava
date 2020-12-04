/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mef.erp.modelo;

import com.mef.erp.modelo.entidad.Contenedor;
import com.mef.erp.modelo.entidad.ElementoExterno;
import com.mef.erp.modelo.entidad.Mensaje;
import java.util.List;

/**
 *
 * @author Jose Armando
 */
public interface ElementoExternoDAOIF extends GenericDAO<ElementoExterno, Integer> {

    public Mensaje getElementoExAll(String uuidCxn);

    public Mensaje agregar(ElementoExterno entity,String uuidCxn);

    public Mensaje actualizar(ElementoExterno entity,String uuidCxn);

    public Mensaje eliminar(ElementoExterno entity,String uuidCxn);

    public Mensaje getElementoExPorContenedor(Contenedor c,String uuidCxn);

    public Mensaje SaveElementoExterno(List<ElementoExterno> e,String uuidCxn);

    public Mensaje DeleteElementoExterno(List<ElementoExterno> e,String uuidCxn);
}
