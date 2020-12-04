/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mef.erp.servicios;

import com.mef.erp.modelo.entidad.Mensaje;
import com.mef.erp.modelo.entidad.Modulo;
import java.util.List;

/**
 *
 * @author Jose Armando
 */
public interface ServicioModuloIF {

    /*List<Modulo>*/
    public Mensaje getModuloAll(String uuidCxn);

    /*Modulo*/
    public Mensaje getModuloPorNombre(String nombreModulo, String uuidCxn);

    /*Modulo*/
    public Mensaje agregar(Modulo entity, String uuidCxn);

    /*boolean*/
    public Mensaje actualizar(Modulo entity, String uuidCxn);

    /*boolean*/
    public Mensaje eliminar(Modulo entity, String uuidCxn);

    /*List<Modulo>*/
    public Mensaje getModuloPorSistemas(String id, String uuidCxn);

    /*Modulo*/
    public Mensaje getModuloPorClave(String clave, String uuidCxn);

    /*List<Modulo>*/
    Mensaje consultaPorFiltros(String query, Object[] campos, Object[] valores, Integer inicio, Integer rango, String uuidCxn);

    /*List<Modulo>*/
    Mensaje consultaPorRangos(Integer inicio, Integer rango, String uuidCxn);

    /*Boolean*/
    Mensaje existeDato(String campo, Object valor, String uuidCxn);

    public Mensaje saveDeleteModulo(List<Modulo> entitysCambios, Object[] clavesDelete, int rango, String uuidCxn);

}
