/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mef.erp.servicios;

import com.mef.erp.modelo.entidad.Mensaje;
import com.mef.erp.modelo.entidad.Monedas;
import com.mef.erp.modelo.entidad.TiposDeCambio;
import java.util.Date;
import java.util.List;

/**
 *
 * @author daniel
 */
public interface ServicioTiposDeCambioIF {

    /*TiposDeCambio*/
    public Mensaje agregar(TiposDeCambio entity, String uuidCxn);

    /*boolean*/
    public Mensaje actualizar(TiposDeCambio entity, String uuidCxn);

    /*boolean*/
    public Mensaje eliminar(TiposDeCambio entity, String uuidCxn);

    /*List<TiposDeCambio>*/
    public Mensaje getTiposDeCambioAll(String uuidCxn);

    /*TiposDeCambio*/
    public Mensaje getTiposDeCambioPorClave(String clave, String uuidCxn);

    /*List<TiposDeCambio>*/
    public Mensaje getTiposDeCambioPorMoneda(Monedas m, String uuidCxn);

    /*List<TiposDeCambio>*/
    public Mensaje getTiposDeCambioPorFecha(Date fecha, String uuidCxn);

    /*TiposDeCambio*/
    public Mensaje guardaTiposDeCambio(List<TiposDeCambio> agrega, Object[] eliminados, String uuidCxn);

    /*boolean*/
    public Mensaje actualizaTiposDeCambio(List<TiposDeCambio> agrega, Object[] eliminados, String uuidCxn);

    /*boolean*/
    public Mensaje eliminaTiposDeCambio(Object[] eliminados, String uuidCxn);
}
