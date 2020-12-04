/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mef.erp.modelo;

import com.mef.erp.modelo.entidad.Mensaje;
import com.mef.erp.modelo.entidad.Sistemas;
import java.util.List;

/**
 *
 * @author daniel
 */
public interface SistemasDAOIF extends GenericDAO<Sistemas, Integer> {

    public Mensaje agregar(Sistemas entity, String uuidCxnMaestra);

    public Mensaje actualizar(Sistemas entity, String uuidCxnMaestra);

    public Mensaje eliminar(Sistemas entity, String uuidCxnMaestra);

    public Mensaje getSistemasAll(String uuidCxnMaestra);

    public Mensaje getSistemasPorClave(String clave, String uuidCxnMaestra);

    public Mensaje consultaPorFiltrosSistemas(String query, Object[] campos, Object[] valores, Integer inicio, Integer rango, String uuidCxnMaestra);

    public Mensaje consultaPorRangos(Integer inicio, Integer rango, String uuidCxnMaestra);

    Mensaje existeDato(String campo, Object valor, String uuidCxnMaestra);

    Mensaje saveDeleteSistemas(List<Sistemas> entitysCambios, Object[] clavesDelete, int rango, String uuidCxnMaestra);
}
