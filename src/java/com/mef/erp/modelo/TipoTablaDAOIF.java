/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mef.erp.modelo;

import com.mef.erp.modelo.entidad.Mensaje;
import com.mef.erp.modelo.entidad.TipoTabla;
import java.util.List;

public interface TipoTablaDAOIF extends GenericDAO<TipoTabla, Integer> {

    public Mensaje getTipoTablaAll(String uuidCxn);

    public Mensaje getTipoTablaPorClave(String clave, String uuidCxn);

    public Mensaje agregar(TipoTabla entity, String uuidCxn);

    public Mensaje actualizar(TipoTabla entity, String uuidCxn);

    public Mensaje eliminar(TipoTabla tipoTabla, String uuidCxn);

    public Mensaje consultaPorFiltrosTipoTabla(String query, Object[] campos, Object[] valores, Integer inicio, Integer rango, String uuidCxn);

    public Mensaje consultaPorRangos(Integer inicio, Integer rango, String uuidCxn);

    Mensaje saveDeleteTipoTabla(List<TipoTabla> entitysCambios, Object[] clavesDelete, int rango, String uuidCxn);

    public Mensaje existeDato(String campo, Object valor, String uuidCxn);
}
