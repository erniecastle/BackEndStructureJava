/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mef.erp.modelo;

import com.mef.erp.modelo.entidad.Genero;
import com.mef.erp.modelo.entidad.Mensaje;
import java.util.List;



/**
 *
 * @author Desarrollo 094
 */
public interface GeneroDAOIF extends GenericDAO<Genero, String>{
    
    Mensaje agregar(Genero entity,String uuidCxn);

    public Mensaje actualizar(Genero entity,String uuidCxn);

    public Mensaje eliminar(Genero entity,String uuidCxn);

    Mensaje getGeneroAll(String uuidCxn);

    Mensaje getGeneroPorClave(String clave,String uuidCxn);

    public Mensaje consultaPorFiltrosGenero(String query, Object[] campos, Object[] valores,String uuidCxn);

    public Mensaje consultaPorRangosGenero(Integer inicio, Integer rango,String uuidCxn);

    public Mensaje existeDato(String campo, Object valor,String uuidCxn);

    public Mensaje saveDeleteGenero(List<Genero> entitysCambios, Object[] clavesDelete, int rango,String uuidCxn);
    
}
