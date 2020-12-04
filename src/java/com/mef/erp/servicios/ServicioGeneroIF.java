/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mef.erp.servicios;

import com.mef.erp.modelo.entidad.Genero;
import com.mef.erp.modelo.entidad.Mensaje;
import java.util.List;

/**
 *
 * @author Desarrollo 094
 */
public interface ServicioGeneroIF {

    /*Genero*/
    Mensaje agregar(Genero entity, String uuidCxn);
    
    /*boolean*/
    public Mensaje actualizar(Genero entity, String uuidCxn);
    
    /*boolean*/
    public Mensaje eliminar(Genero entity, String uuidCxn);
    
    /*List<Genero>*/
    Mensaje getGeneroAll(String uuidCxn);
    
    /*Genero*/
    Mensaje getGeneroPorClave(String clave, String uuidCxn);
    
    /*List<Genero>*/
    public Mensaje consultaPorFiltrosGenero(String query, Object[] campos, Object[] valores, String uuidCxn);
    
    /*List<Genero>*/
    public Mensaje consultaPorRangosGenero(Integer inicio, Integer rango, String uuidCxn);
    
    /*boolean*/
    public Mensaje existeDato(String campo, Object valor, String uuidCxn);
    
    /*List<Genero>*/
    public Mensaje saveDeleteGenero(List<Genero> entitysCambios, Object[] clavesDelete, int rango, String uuidCxn);

}
