/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mef.erp.modelo;

import com.mef.erp.modelo.entidad.Mensaje;
import com.mef.erp.modelo.entidad.Parentesco;
import java.util.List;

/**
 *
 * @author Dayane
 */
public interface ParentescoDAOIF extends GenericDAO<Parentesco, String> {

    Mensaje agregar(Parentesco entity, String uuidCxn);

    public Mensaje actualizar(Parentesco entity, String uuidCxn);

    public Mensaje eliminar(Parentesco entity, String uuidCxn);

    Mensaje getParentescoAll(String uuidCxn);

    Mensaje getParentescosPorClave(String clave, String uuidCxn);

    public Mensaje consultaPorFiltrosParentescos(String query, Object[] campos, Object[] valores, String uuidCxn);

    public Mensaje consultaPorRangosParentescos(Integer inicio, Integer rango, String uuidCxn);

    public Mensaje existeDato(String campo, Object valor, String uuidCxn);

    public Mensaje saveDeleteParentesco(List<Parentesco> entitysCambios, Object[] clavesDelete, int rango, String uuidCxn);

}

