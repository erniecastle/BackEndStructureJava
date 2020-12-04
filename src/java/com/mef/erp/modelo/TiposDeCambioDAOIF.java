/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mef.erp.modelo;

import com.mef.erp.modelo.entidad.Mensaje;
import com.mef.erp.modelo.entidad.Monedas;
import com.mef.erp.modelo.entidad.TiposDeCambio;
import java.util.Date;
import java.util.List;

/**
 *
 * @author daniel
 */
public interface TiposDeCambioDAOIF extends GenericDAO<TiposDeCambio, Long> {

    public Mensaje agregar(TiposDeCambio entity, String uuidCxn);

    public Mensaje actualizar(TiposDeCambio entity, String uuidCxn);

    public Mensaje eliminar(TiposDeCambio entity, String uuidCxn);

    public Mensaje getTiposDeCambioAll(String uuidCxn);

    public Mensaje getTiposDeCambioPorClave(String clave, String uuidCxn);

    public Mensaje getTiposDeCambioPorMoneda(Monedas m, String uuidCxn);

    public Mensaje getTiposDeCambioPorFecha(Date fecha, String uuidCxn);

    public Mensaje guardaTiposDeCambio(List<TiposDeCambio> agrega, Object[] eliminados, String uuidCxn);

    public Mensaje actualizaTiposDeCambio(List<TiposDeCambio> agrega, Object[] eliminados, String uuidCxn);

    public Mensaje eliminaTiposDeCambio(Object[] eliminados, String uuidCxn);
}
