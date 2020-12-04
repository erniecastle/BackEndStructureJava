/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mef.erp.servicios;

import com.mef.erp.modelo.entidad.Mensaje;
import com.mef.erp.modelo.entidad.TipoTabla;
import java.util.List;

/**
 *
 * @author Jose Armando
 */
public interface ServicioTipoTablaIF {

    /*List<TipoTabla>*/
    public Mensaje getTipoTablaAll(String uuidCxn);

    /*TipoTabla*/
    public Mensaje getTipoTablaPorClave(String clave, String uuidCxn);

    /*TipoTabla*/
    public Mensaje agregar(TipoTabla entity, String uuidCxn);

    /*boolean*/
    public Mensaje actualizar(TipoTabla entity, String uuidCxn);

    /*boolean*/
    public Mensaje eliminar(TipoTabla tipoTabla, String uuidCxn);

    /*List<TipoTabla>*/
    public Mensaje consultaPorFiltros(String query, Object[] campos, Object[] valores, Integer inicio, Integer rango, String uuidCxn);

    /*List<TipoTabla>*/
    public Mensaje consultaPorRangos(Integer inicio, Integer rango, String uuidCxn);

    /*List<TipoTabla>*/
    Mensaje saveDeleteTipoTabla(List<TipoTabla> entitysCambios, Object[] clavesDelete, int rango, String uuidCxn);

    /*Boolean*/
    Mensaje existeDato(String campo, Object valor, String uuidCxn);
}
