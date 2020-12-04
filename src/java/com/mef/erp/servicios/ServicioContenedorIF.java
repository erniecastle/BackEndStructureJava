/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mef.erp.servicios;

import com.mef.erp.modelo.entidad.Contenedor;
import com.mef.erp.modelo.entidad.Herramienta;
import com.mef.erp.modelo.entidad.Mensaje;
import com.mef.erp.modelo.entidad.RazonSocial;
import com.mef.erp.modelo.entidad.TipoAcciones;
import com.mef.erp.modelo.entidad.Usuario;
import java.util.List;

/**
 *
 * @author Jose Armando
 */
public interface ServicioContenedorIF {

    /*List<Contenedor>*/
    public Mensaje getContenedor(int parentId, Herramienta herramienta, List<String> ventanasAOmitir, String uuidCxn);

    /*List<Contenedor>*/
    public Mensaje getContenedorAll(List<String> ventanasAOmitir, String uuidCxn);

    /*List<Contenedor>*/
    public Mensaje getContenedorHert(Herramienta herramienta, List<String> ventanasAOmitir, Usuario usuario, RazonSocial razonSocial, String uuidCxn);

    /*List<Contenedor>*/
    public Mensaje getContenedorHertCompartida(Herramienta herramienta, List<String> ventanasAOmitir, Usuario usuario, RazonSocial razonSocial, String uuidCxn);

    /*Integer*/
    public Mensaje getContenedorMax(String uuidCxn);

    /*Contenedor*/
    public Mensaje agregar(Contenedor entity, String uuidCxn);

    /*boolean*/
    public Mensaje actualizar(Contenedor entity, String uuidCxn);

    /*boolean*/
    public Mensaje eliminar(List<Contenedor> entity, String uuidCxn);

    /*Contenedor*/
    public Mensaje getContenedorPorId(Integer id, String uuidCxn);

    /*List<Contenedor>*/
    public Mensaje getContenedorOrder(List<String> ventanasAOmitir, String uuidCxn);

    /*boolean*/
    public Mensaje SaveContenedor(List<Contenedor> c, String uuidCxn);

    /*boolean*/
    public Mensaje DeleteContenedor(List<Contenedor> c, String uuidCxn);

    /*List<Contenedor>*/
    Mensaje getContenedorPorNombreVentana(String nombreVentana, String uuidCxn);

    /*List<Contenedor>*/
    Mensaje getContenedoresPorTipoAcciones(TipoAcciones[] tipoAcciones, String uuidCxn);

    /*List<Contenedor>*/
    Mensaje buscaPorTipoAccionesYidMultiUsos(TipoAcciones[] tipoAcciones, Long[] idMultiUsos, String claveRazonSocial, String uuidCxn);

}
