/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mef.erp.modelo;

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
public interface ContenedorDAOIF extends GenericDAO<Contenedor, Integer> {

    public Mensaje getContenedor(int parentId, Herramienta herramienta, List<String> ventanasAOmitir, String uuidCxn);

    public Mensaje getContenedorAll(List<String> ventanasAOmitir, String uuidCxn);

    public Mensaje getContenedorHert(Herramienta herramienta, List<String> ventanasAOmitir, Usuario usuario, RazonSocial razonSocial, String uuidCxn);

    public Mensaje getContenedorHertCompartida(Herramienta herramienta, List<String> ventanasAOmitir, Usuario usuario, RazonSocial razonSocial, String uuidCxn);

    public Mensaje getContenedorMax(String uuidCxn);

    public Mensaje agregar(Contenedor entity, String uuidCxn);

    public Mensaje actualizar(Contenedor entity, String uuidCxn);

    public Mensaje eliminar(List<Contenedor> entity, String uuidCxn);

    public Mensaje getContenedorPorId(Integer id, String uuidCxn);

    public Mensaje getContenedorOrder(List<String> ventanasAOmitir, String uuidCxn);

    public Mensaje SaveContenedor(List<Contenedor> c, String uuidCxn);

    public Mensaje DeleteContenedor(List<Contenedor> c, String uuidCxn);

    Mensaje getContenedorPorNombreVentana(String nombreVentana, String uuidCxn);

    Mensaje getContenedoresPorTipoAcciones(TipoAcciones[] tipoAcciones, String uuidCxn);

    Mensaje buscaPorTipoAccionesYidMultiUsos(TipoAcciones[] tipoAcciones, Long[] idMultiUsos, String claveRazonSocial, String uuidCxn);

}
