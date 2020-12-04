/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mef.erp.servicios;

import com.mef.erp.modelo.VentanaDAO;
import com.mef.erp.modelo.entidad.Mensaje;
import com.mef.erp.modelo.entidad.TipoVentana;
import com.mef.erp.modelo.entidad.Ventana;

/**
 *
 * @author Jose Armando
 */
public class ServicioVentana implements ServicioVentanaIF {

    private VentanaDAO ventanaDAO;

    public Mensaje agregar(Ventana entity, String uuidCxn) {
        return ventanaDAO.agregar(entity, uuidCxn);
    }

    public Mensaje actualizar(Ventana entity, String uuidCxn) {
        return ventanaDAO.actualizar(entity, uuidCxn);
    }

    public Mensaje eliminar(Ventana entity, String uuidCxn) {
        return ventanaDAO.eliminar(entity, uuidCxn);
    }

    public Mensaje getVentanaAll(String uuidCxn) {
        return ventanaDAO.getVentanaAll(uuidCxn);
    }

    public Mensaje getVentanaPorNombre(String nombreVentana, String uuidCxn) {
        return ventanaDAO.getVentanaPorNombre(nombreVentana, uuidCxn);
    }

    public Mensaje getVentanaPorSistemas(Integer id, String uuidCxn) {
        return ventanaDAO.getVentanaPorSistemas(id, uuidCxn);
    }

    public Mensaje getVentanaPorClave(Integer clave, String uuidCxn) {
        return ventanaDAO.getVentanaPorClave(clave, uuidCxn);
    }

    public Mensaje getVentanaPorTipoVentana(TipoVentana[] tipoVentanas, String uuidCxn) {
        return ventanaDAO.getVentanaPorTipoVentana(tipoVentanas, uuidCxn);
    }

    public VentanaDAO getVentanaDAO() {
        return ventanaDAO;
    }

    public void setVentanaDAO(VentanaDAO VentanaDAO) {
        this.ventanaDAO = VentanaDAO;
    }

}
