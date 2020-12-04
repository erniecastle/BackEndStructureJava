/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mef.erp.modelo;

import com.mef.erp.modelo.entidad.Despensa;
import com.mef.erp.modelo.entidad.Incidencias;
import com.mef.erp.modelo.entidad.Mensaje;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author daniel
 */
public interface DespensaDAOIF extends GenericDAO<Despensa, Long> {
    
    public Mensaje agregar(Despensa entity , String uuidCxn);

    public Mensaje actualizar(Despensa entity , String uuidCxn);

    public Mensaje eliminar(Despensa entity , String uuidCxn);

    public Mensaje getDespensaAll(String uuidCxn);
    
    public Mensaje getDespensaPorClave(Date clave, String uuidCxn);
    
    Mensaje SaveDespensa(List<Incidencias> agrega, Object[] eliminados, Despensa entity , String uuidCxn);

    Mensaje DeleteDespensa(Despensa entity , String uuidCxn);

    Mensaje UpdateDespensa(List<Incidencias> agrega, Object[] eliminados, Despensa entity , String uuidCxn);

    Mensaje consultaPorFiltrosDespensa(String query, Object[] campos, Object[] valores , String uuidCxn);

    Mensaje consultaPorRangos(Integer inicio, Integer rango, String uuidCxn);

    Mensaje existeDato(String campo, Object valor, String uuidCxn);

    Mensaje agregarListaDespensa(List<Despensa> cambios, List<Despensa> temporales, List<Incidencias> cambioIncidencias, int rango , String uuidCxn);

}
