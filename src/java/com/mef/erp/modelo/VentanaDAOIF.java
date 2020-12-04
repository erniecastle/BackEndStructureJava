/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mef.erp.modelo;

import com.mef.erp.modelo.entidad.Mensaje;
import com.mef.erp.modelo.entidad.TipoVentana;
import com.mef.erp.modelo.entidad.Ventana;

/**
 *
 * @author Jose Armando
 */
public interface VentanaDAOIF extends GenericDAO<Ventana, Integer> {

    public Mensaje getVentanaAll(String uuidCxn);

    Mensaje getVentanaPorTipoVentana(TipoVentana[] tipoVentanas, String uuidCxn);

    public Mensaje getVentanaPorNombre(String nombreVentana, String uuidCxn);

    public Mensaje agregar(Ventana entity, String uuidCxn);

    public Mensaje actualizar(Ventana entity, String uuidCxn);

    public Mensaje eliminar(Ventana entity, String uuidCxn);

    public Mensaje getVentanaPorSistemas(Integer id, String uuidCxn);

    public Mensaje getVentanaPorClave(Integer clave, String uuidCxn);
}
