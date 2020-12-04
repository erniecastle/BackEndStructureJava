/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mef.erp.servicios;

import com.mef.erp.modelo.ContenedorDAO;
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
public class ServicioContenedor implements ServicioContenedorIF {

    private ContenedorDAO contenedorDAO;

    public Mensaje getContenedor(int parentId, Herramienta herramienta, List<String> ventanasAOmitir, String uuidCxn) {
        return contenedorDAO.getContenedor(parentId, herramienta, ventanasAOmitir, uuidCxn);
    }

    public Mensaje getContenedorAll(List<String> ventanasAOmitir, String uuidCxn) {
        return contenedorDAO.getContenedorAll(ventanasAOmitir, uuidCxn);
    }

    public Mensaje getContenedorHert(Herramienta herramienta, List<String> ventanasAOmitir, Usuario usuario, RazonSocial razonSocial, String uuidCxn) {
        return contenedorDAO.getContenedorHert(herramienta, ventanasAOmitir, usuario, razonSocial, uuidCxn);
    }

    public Mensaje getContenedorHertCompartida(Herramienta herramienta, List<String> ventanasAOmitir, Usuario usuario, RazonSocial razonSocial, String uuidCxn) {
        return contenedorDAO.getContenedorHertCompartida(herramienta, ventanasAOmitir, usuario, razonSocial, uuidCxn);
    }

    public Mensaje getContenedorMax(String uuidCxn) {
        return contenedorDAO.getContenedorMax(uuidCxn);
    }

    public Mensaje agregar(Contenedor entity, String uuidCxn) {
        return contenedorDAO.agregar(entity, uuidCxn);
    }

    public Mensaje eliminar(List<Contenedor> entity, String uuidCxn) {
        return contenedorDAO.eliminar(entity, uuidCxn);
    }

    public Mensaje actualizar(Contenedor entity, String uuidCxn) {
        return contenedorDAO.actualizar(entity, uuidCxn);
    }

    public Mensaje getContenedorPorId(Integer id, String uuidCxn) {
        return contenedorDAO.getContenedorPorId(id, uuidCxn);
    }

    public Mensaje getContenedorOrder(List<String> ventanasAOmitir, String uuidCxn) {
        return contenedorDAO.getContenedorOrder(ventanasAOmitir, uuidCxn);
    }

    public Mensaje SaveContenedor(List<Contenedor> c, String uuidCxn) {
        return contenedorDAO.SaveContenedor(c, uuidCxn);
    }

    public Mensaje DeleteContenedor(List<Contenedor> c, String uuidCxn) {
        return contenedorDAO.DeleteContenedor(c, uuidCxn);
    }

    public Mensaje getContenedorPorNombreVentana(String nombreVentana, String uuidCxn) {
        return contenedorDAO.getContenedorPorNombreVentana(nombreVentana, uuidCxn);
    }

    public Mensaje getContenedoresPorTipoAcciones(TipoAcciones[] tipoAcciones, String uuidCxn) {
        return contenedorDAO.getContenedoresPorTipoAcciones(tipoAcciones, uuidCxn);
    }

    public Mensaje buscaPorTipoAccionesYidMultiUsos(TipoAcciones[] tipoAcciones, Long[] idMultiUsos, String claveRazonSocial, String uuidCxn) {
        return contenedorDAO.buscaPorTipoAccionesYidMultiUsos(tipoAcciones, idMultiUsos, claveRazonSocial, uuidCxn); 
    }

    public ContenedorDAO getContenedorDAO() {
        return contenedorDAO;
    }

    public void setContenedorDAO(ContenedorDAO contenedorDAO) {
        this.contenedorDAO = contenedorDAO;
    }
}
