/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mef.erp.servicios;

import com.mef.erp.modelo.entidad.Mensaje;
import com.mef.erp.modelo.entidad.TipoVentana;
import com.mef.erp.modelo.entidad.Ventana;

/**
 *
 * @author Jose Armando
 */
public interface ServicioVentanaIF {

    /*Ventana*/
    public Mensaje agregar(Ventana entity, String uuidCxn);

    /*boolean*/
    public Mensaje actualizar(Ventana entity, String uuidCxn);

    /*boolean*/
    public Mensaje eliminar(Ventana entity, String uuidCxn);

    /*List<Ventana> */
    public Mensaje getVentanaAll(String uuidCxn);

    /*List<Ventana> */
    Mensaje getVentanaPorTipoVentana(TipoVentana[] tipoVentanas, String uuidCxn);

    /*Ventana*/
    public Mensaje getVentanaPorNombre(String nombreVentana, String uuidCxn);

    /*List<Ventana> */
    public Mensaje getVentanaPorSistemas(Integer id, String uuidCxn);

    /*Ventana*/
    public Mensaje getVentanaPorClave(Integer clave, String uuidCxn);
}
