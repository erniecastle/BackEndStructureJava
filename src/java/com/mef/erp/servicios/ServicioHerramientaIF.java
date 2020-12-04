/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mef.erp.servicios;

import com.mef.erp.modelo.entidad.Herramienta;
import com.mef.erp.modelo.entidad.Mensaje;
import com.mef.erp.modelo.entidad.Usuario;
import java.util.List;

/**
 *
 * @author Jose Armando
 */
public interface ServicioHerramientaIF {

    /*List<Herramienta>*/
    public Mensaje getHerramientaAll(String uuidCxn);

    /*List<Herramienta>*/
    public Mensaje getHerramientaCompartidas(String uuidCxn);

    /*List<Herramienta>*/
    public Mensaje getHerramientasPrincipales(Usuario usuario, String uuidCxn);

    /*Herramienta*/
    public Mensaje agregar(Herramienta entity, String uuidCxn);

    /*boolean*/
    public Mensaje actualizar(Herramienta entity, String uuidCxn);

    /*boolean*/
    public Mensaje eliminar(Herramienta entity, String uuidCxn);

    /*boolean*/
    public Mensaje SaveHerramientas(List<Herramienta> h, String uuidCxn);

    /*boolean*/
    public Mensaje DeleteHerramientas(List<Herramienta> h, String uuidCxn);

    /*List<Herramienta>*/
    public Mensaje getHerramientasPrincipalesCompartidas(Usuario usuario, String uuidCxn);

    
}
